import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {


  billingAddress = new FormControl('',[Validators.required]);
  cvvCode = new FormControl('',[Validators.required, Validators.maxLength(3), Validators.minLength(3)]);
  cardNumber = new FormControl('',[Validators.required, Validators.maxLength(12), Validators.minLength(12)]);
  name = new FormControl('',[Validators.required]);
  expiryDate = new FormControl('',[Validators.required]);
  type= new FormControl('credit',[Validators.required]);
  isLoggedIn: boolean = true;
  userIsAnAdmin: boolean = false;

  
  paymentForm: FormGroup  =  this.formBuilder.group({
    billingAddress: this.billingAddress,
    cvvCode: this.cvvCode,
    cardNumber: this.cardNumber,
    name: this.name,
    expiryDate: this.expiryDate,
    type: this.type
  });;



  constructor(private formBuilder: FormBuilder, private authenticationService : AuthenticationService) { }


  ngOnInit(): void {
    this.isLoggedIn = this.authenticationService.isLoggedIn();
    this.userIsAnAdmin = this.authenticationService.userRoleIsAdmin();
  }

  

  onSubmit(){}

}
