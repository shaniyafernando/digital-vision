import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PlaceOrderDTO } from 'src/app/dtos/PlaceOrderDTO';
import { Address } from 'src/app/models/Address';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CartService } from 'src/app/services/cart.service';
import { PaymentService } from 'src/app/services/payment.service';

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

  oldAddress: Address = {} as Address;
  newAddress: Address = {} as Address;
  order: PlaceOrderDTO = {} as PlaceOrderDTO;

  constructor( private authenticationService : AuthenticationService,
    private cartService: CartService, private paymentService: PaymentService,
    private router: Router) { }


  ngOnInit(): void {
    this.order = history.state.data.order;
  }

  public getBillingAddress(){
    const userId = this.authenticationService.getCurrentUser();
    this.cartService.getAddress(userId).subscribe(
      response => {
        let billingAddress = response.billingAddress as string;
        this.oldAddress = response;
        this.paymentForm.controls.billingAddress.setValue(billingAddress);
      }
    );
  }
  

  onPay(){
    console.log(this.paymentForm.value)
//update billing address
    const billingAddress = this.paymentForm.value.billingAddress;
    this.newAddress.id = this.oldAddress.id;
    this.newAddress.userId = this.oldAddress.userId;
    this.newAddress.deliveryAddress = this.oldAddress.deliveryAddress;
    this.newAddress.billingAddress = billingAddress as string;
    this.paymentService.updateBillingAddress(this.newAddress).subscribe(
      response => {console.log(response);}
    );
//pay
    this.paymentService.pay(this.order).subscribe(
      response => {
        this.router.navigate(['/delivery'], {state: {data: {paymentId: response.id}}});
      }
    )
  }

}
