import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { LoginDTO } from 'src/app/dtos/loginDTO';
import { UserDTO } from 'src/app/dtos/userDTO';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;

  email = new FormControl('',[Validators.email,Validators.required]);
  username = new FormControl('',[Validators.required]);
  password  = new FormControl('',[Validators.required,Validators.minLength(8)]);

  loginForm!: FormGroup 

  guest!:LoginDTO;
  user!:UserDTO;

  get formControls() { return this.loginForm.controls; }

  constructor(private fb : FormBuilder, private router: Router, private authenticationService: AuthenticationService,
    private applicationComponent: AppComponent) { }

  ngOnInit(): void {
    this.buildLoginForm();
  }

  buildLoginForm(){
    this.fb.group({
      email: this.email,
      username: this.username,
      password: this.password
    });
  }

  public signIn(){
    // this.guest.email = this.loginForm.get('email')?.value;
    // this.guest.userName = this.loginForm.get('username')?.value;
    // this.guest.password = this.loginForm.get('password')?.value;
    // this.authenticationService.signIn(this.guest).subscribe(
    //   response => this.user = response
    // );
    // this.authenticationService.login(this.user);
  }

  public navigateToRegister(){
    this.router.navigate(['/register']);
  }

}
