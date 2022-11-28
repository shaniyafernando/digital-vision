import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  placeOrder(){
    // pass in the order to the payment component. Check the product card component 
    // to see how i have passed the data into product details component and 
    // check the product details component on how the data is recieved.
    this.router.navigate(['/payment']);
  }

}
