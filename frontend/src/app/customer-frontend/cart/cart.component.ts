import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartDTO } from 'src/app/dtos/CartDTO';
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

  cartdtos: CartDTO[] = [];
  cartdto: CartDTO = {} as CartDTO;
  cartItems: CartItem[] = [];
  cartId: number = 0;
  subTotal: number = 0;
  noOfItems: number=0;
  deliveryFee: number = 20;
  total: number = 0;

  constructor(private router: Router, private authenticationService: AuthenticationService,
    private cartService: CartService) { }

  ngOnInit(): void {
    this.total = this.subTotal + this.deliveryFee;
    this.getAllProductsInCart();
  }

  checkout(){
    // pass in the product list with respective quantities and total amount to the order component. Check the product card component 
    // to see how i have passed the data into product details component and check the product details component on how the data is recieved.
    this.router.navigate(['/payment']);
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

}
