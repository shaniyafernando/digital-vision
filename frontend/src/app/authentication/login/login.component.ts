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

  get formControls() { return this.loginForm.controls; }

  constructor( private router: Router, 
    private authenticationService: AuthenticationService,) { }

  ngOnInit(): void {
  }

  public signIn(){
    this.guest.email = this.loginForm.value.email as String;
    this.guest.username = this.loginForm.value.username as String ;
    this.guest.password = this.loginForm.value.password as String;
    this.authenticationService.signIn(this.guest).subscribe(
      response => this.user = response
    );
    this.authenticationService.login(this.user);
  }

  public navigateToRegister(){
    this.router.navigate(['/register']);
  }

}
