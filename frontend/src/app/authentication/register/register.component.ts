import { Component, OnInit } from '@angular/core';
import {  FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { RegistrationRequest } from 'src/app/dtos/RegistrationRequest';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  hide = true;

  registerForm = new FormGroup({
    email: new FormControl('',[Validators.email,Validators.required]),
    username: new FormControl('',[Validators.required]),
    billingAddress: new FormControl(''),
    deliveryAddress: new FormControl(''),
    password:new FormControl('',[Validators.required,Validators.minLength(8)])
  });

  guest: RegistrationRequest = {} as RegistrationRequest;
  isAfterNoAccessExpiryTime: boolean = true;
  hasNoAccess: boolean = false;

  get formControls() { return this.registerForm.controls; }

  constructor(private router: Router, private authenticationService: AuthenticationService) { 
    this.isAfterNoAccessExpiryTime = this.authenticationService.isAfterNoAccessExpiryTime();
  }

  ngOnInit(): void {
    this.hasNoAccess = this.authenticationService.hasNoAccess();
  }

  public signUp(){
    this.guest.username = this.registerForm.value.username as String;
    this.guest.email = this.registerForm.value.email as String;
    this.guest.password = this.registerForm.value.password as String;
    this.guest.billingAddress = this.registerForm.value.billingAddress as String;
    this.guest.deliveryAddress = this.registerForm.value.deliveryAddress as String;

    this.authenticationService.signUp(this.guest).subscribe(
      response => {
        console.log(response);
        this.authenticationService.token(response);
        
    });
    this.router.navigate(['/token'])
      // if(this.hasNoAccess == false){
      //   this.authenticationService.signUp(this.guest).subscribe(
      //     response => {
      //       console.log(response);
      //       this.authenticationService.token(response);
      //       this.router.navigate(['/token'])
      //   });
      // }
       
      // if(this.hasNoAccess == true){
        
      //   if(this.isAfterNoAccessExpiryTime == true){
      //     this.authenticationService.signUp(this.guest).subscribe(
      //       response => {
      //         console.log(response);
      //         this.authenticationService.token(response);
      //         this.router.navigate(['/token'])
      //     })
      //   }
  
      //   this.router.navigate(['/home']);
    // }  
  }
  

}
