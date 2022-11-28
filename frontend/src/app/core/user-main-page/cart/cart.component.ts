import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  checkout(){
    // pass in the product list with respective quantities and total amount to the order component. Check the product card component 
    // to see how i have passed the data into product details component and check the product details component on how the data is recieved.
    this.router.navigate(['/order']);
  }

}
