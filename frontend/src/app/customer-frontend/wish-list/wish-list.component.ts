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

  dummyData: any[] = [
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.GtTEErEk8ul49Sr_LMkW-AHaGr&pid=Api&P=0",
      title: "Mobile Stand",
      description: "Its a mobile stand!",
      category: "1",
      colour: "white",
      brand: "KOKO",
      price: 36,
      quantity: 258
    },
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.rqK91vQXsugViYuTDpgQZgHaHa&pid=Api&P=0",
      title: "Mobile grip",
      description: "Its a mobile grip",
      category: "3",
      colour: "White",
      brand: "MOMO",
      price: 50,
      quantity: 257
    },
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.v7T1VkLCKLr_xWF-uCFioAHaFp&pid=Api&P=0",
      title: "Mobile screen magnifier",
      description: "Magnifies mobile screens.",
      category: "4",
      colour: "Black",
      brand: "DODO",
      price: 358,
      quantity: 125
    }
  ];

  constructor(public wishListService: WishListService,
     private authenticationService: AuthenticationService) { 
      // this.userId = this.authenticationService.getCurrentUser();
     }

  ngOnInit(): void {
    // this.getWishList();
    this.onDummyData()
  }

  public onDummyData(){
    this.products = this.dummyData;
  }

  // public getWishList(){
  //   this.wishListService.getWishList(1).subscribe(
  //     response => {
  //       this.products = response;
  //       if(this.products == null){this.noProductData = true}
  //     }
  //   )
  // }

  

}
