import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartListDTO } from 'src/app/dtos/CartListDTO';
import { PlaceOrderDTO } from 'src/app/dtos/PlaceOrderDTO';
import { Address } from 'src/app/models/Address';
import { Cart } from 'src/app/models/cart';
import { CartItem } from 'src/app/models/cartItem';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartdtos: CartListDTO[] = [];
  cartdto: CartListDTO = {} as CartListDTO;
  cartItems: CartItem[] = [];
  cartId: number = 0;
  subTotal: number = 0;
  noOfItems: number=0;
  deliveryFee: number = 150;
  total: number = 0;
  order: PlaceOrderDTO = {} as PlaceOrderDTO;
  oldAddress: Address = {} as Address;
  newAddress: Address = {} as Address;
  cart: Cart = {} as Cart;

  orderForm = new FormGroup({
    deliveryAddress: new FormControl('',[Validators.required])
  });

  get formControls() { return this.orderForm.controls; }

  constructor(private router: Router, private authenticationService: AuthenticationService,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.total = this.subTotal + this.deliveryFee;
    this.getAllProductsInCart();
    this.getDeliveryAddress();
  }

  checkout(){
    // update delivery address
    this.newAddress.id = this.oldAddress.id;
    this.newAddress.userId = this.oldAddress.userId;
    this.newAddress.billingAddress = this.oldAddress.billingAddress;
    this.newAddress.deliveryAddress = this.orderForm.value.deliveryAddress as string;
    this.cartService.updateDeliveryAddress(this.newAddress).subscribe(
      response => console.log(response)
    );
    // update quantity of cart items
    this.cartdtos.forEach(element => {
      
    });

    // checkout
    this.cartService.checkout(this.cart).subscribe(
      response => this.order = response
    );

    this.router.navigate(['/payment'],{state: {data: {order: this.order}}});
  }

  public getAllProductsInCart(){
    const userId = this.authenticationService.getCurrentUser();
    this.cartService.getAllProductsInCart(userId).subscribe(
      response => {
        this.subTotal = response.total;
        this.cartId = response.id;
        this.cartItems = response.cartItems;
        this.cartItems.forEach(
          item => {
            this.cartdto.cartId = this.cartId;
            this.cartdto.cartItemId = item.id;
            this.cartService.getProduct(item.productId).subscribe(
              response => this.cartdto.product = response);
            this.cartdto.quantity = item.quantityAddedToCart;
            this.cartdto.subtotal = this.cartdto.product.price * this.cartdto.quantity
            this.cartdtos.push(this.cartdto);
          }
        );
      }
    );
  }

  public removeCartItem(cartItemId: number){
    this.cartService.deleteCartItem(cartItemId);
    window.location.reload();
  }

  public getDeliveryAddress(){
    const userId = this.authenticationService.getCurrentUser();
    this.cartService.getAddress(userId).subscribe(
      response => {
        let deliveryAddress = response.deliveryAddress as string;
        this.oldAddress = response;
        this.orderForm.controls.deliveryAddress.setValue(deliveryAddress);
      }
    );
  }

  

}
