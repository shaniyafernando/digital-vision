import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  hide = true;

  email = new FormControl('',[Validators.email,Validators.required]);
  username = new FormControl('',[Validators.required]);
  billingAddress = new FormControl('');
  deliveryAddress = new FormControl('');
  password  = new FormControl('',[Validators.required,Validators.minLength(8)]);

  registerForm!: FormGroup 

  get formControls() { return this.registerForm.controls; }

  constructor(private fb : FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.buildRegisterForm();
  }

  buildRegisterForm(){
    this.fb.group({
      email: this.email,
      username: this.username,
      password: this.password,
      billingAddress: this.billingAddress,
      deliveryAddress: this.deliveryAddress
    });
  }

  

}
