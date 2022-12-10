import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { LoginRequest } from 'src/app/dtos/LoginRequest';
import { UserDTO } from 'src/app/dtos/userDTO';
import { User } from 'src/app/models/User';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;

  loginForm = new FormGroup({
    email: new FormControl('',[Validators.email,Validators.required]),
    username: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required,Validators.minLength(8)])
  });

  guest: LoginRequest = {} as LoginRequest;
  user: User = {} as User;
  isAfterNoAccessExpiryTime: boolean = true;
  hasNoAccess: boolean = false;

  get formControls() { return this.loginForm.controls; }

  constructor( private router: Router, 
    private authenticationService: AuthenticationService,) { 
      this.isAfterNoAccessExpiryTime = this.authenticationService.isAfterNoAccessExpiryTime();
      this.hasNoAccess = this.authenticationService.hasNoAccess();
    }

  ngOnInit(): void {
  }

  public signIn(){
    this.guest.email = this.loginForm.value.email as String;
    this.guest.username = this.loginForm.value.username as String ;
    this.guest.password = this.loginForm.value.password as String;

    
    if(this.hasNoAccess == false){
      this.authenticationService.signIn(this.guest).subscribe(
        response => {
          console.log(response);
          this.authenticationService.login(response);
          this.authenticationService.setUserId(response);
          this.router.navigate(['/home']);
        });
    }
     
    if(this.hasNoAccess == true){
      
      if(this.isAfterNoAccessExpiryTime == true){
        this.authenticationService.removeNoAccess();
        this.authenticationService.signIn(this.guest).subscribe(
          response => {
            console.log(response);
            this.authenticationService.login(response);
            this.authenticationService.setUserId(response);
            this.router.navigate(['/home']);
          });
      }

      this.router.navigate(['/home']);
    }
    
  }

  public navigateToRegister(){
    this.router.navigate(['/register']);
  }

}
