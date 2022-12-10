import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  paymentForm = new FormGroup({
    billingAddress: new FormControl('',[Validators.required]),
    cvvCode: new FormControl('',[Validators.required,
       Validators.maxLength(3), Validators.minLength(3)]),
    cardNumber: new FormControl('',[Validators.required, 
      Validators.maxLength(12), Validators.minLength(12)]),
    name: new FormControl('',[Validators.required]),
    expiryDate: new FormControl('',[Validators.required]),
    type: new FormControl('credit',[Validators.required])
  });;



  constructor( private authenticationService : AuthenticationService,
    private cartService: CartService) { }


  ngOnInit(): void {}

  public getBillingAddress(){
    const userId = this.authenticationService.getCurrentUser();
    this.cartService.getAddress(userId).subscribe(
      response => {
        let billingAddress = response.billingAddress as string;
        this.paymentForm.controls.billingAddress.setValue(billingAddress);
      }
    );
  }
  

  onPay(){
//update billing address

//pay

  }

}
