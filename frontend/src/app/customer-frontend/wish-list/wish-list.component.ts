import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';
import { WishListService } from 'src/app/services/wish-list.service';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {

  products: Product[]=[];
  noProductData: boolean = false;
  component= "WishListComponent";
  userId: number = 1;

  constructor(public wishListService: WishListService,
     private authenticationService: AuthenticationService) { 
      // this.userId = this.authenticationService.getCurrentUser();
     }

  ngOnInit(): void {
    this.getWishList();
  }

  public getWishList(){
    this.wishListService.getWishList(1).subscribe(
      response => {
        this.products = response;
        if(this.products == null){this.noProductData = true}
      }
    )
  }

  

}
