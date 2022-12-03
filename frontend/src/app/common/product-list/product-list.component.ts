import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SearchDTO } from 'src/app/dtos/SearchDTO';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

enum Category{
  Stands = "Stands",
  AutomobileCradles = "Automobile Cradles",
  Grips = "Grips",
  ScreenExpandersAndMagnifiers = "Screen Expanders & Magnifiers",
  HandheldGimbalsAndStabilizers = "Handheld Gimbals & Stabilizers"
}

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  // products: Product[]=[
  //   {id: 1,
  //   title:'Adjustable Cell Phone Stand',
  //   description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
  //   image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
  //   brand:'CreaDream',
  //   category:'Stands',
  //   colour:'Pink',
  //   price:30.99,quantity:50},
  // {id: 2,
  //   title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
  //   description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
  //   image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
  //   brand:'Loncaster',
  //   category:'Cell Phone Automobile Cradles',
  //   colour:'Black',
  //   price:25.36,quantity:75},
  //   {id: 1,
  //     title:'Adjustable Cell Phone Stand',
  //     description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
  //     image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
  //     brand:'CreaDream',
  //     category:'Stands',
  //     colour:'Pink',
  //     price:30.99,quantity:50},
  //   {id: 2,
  //     title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
  //     description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
  //     image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
  //     brand:'Loncaster',
  //     category:'Cell Phone Automobile Cradles',
  //     colour:'Black',
  //     price:25.36,quantity:75},
  //     {id: 1,
  //       title:'Adjustable Cell Phone Stand',
  //       description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
  //       image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
  //       brand:'CreaDream',
  //       category:'Stands',
  //       colour:'Pink',
  //       price:30.99,quantity:50},
  //     {id: 2,
  //       title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
  //       description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
  //       image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
  //       brand:'Loncaster',
  //       category:'Cell Phone Automobile Cradles',
  //       colour:'Black',
  //       price:25.36,quantity:75},
  //       {id: 1,
  //         title:'Adjustable Cell Phone Stand',
  //         description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
  //         image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
  //         brand:'CreaDream',
  //         category:'Stands',
  //         colour:'Pink',
  //         price:30.99,quantity:50},
  //       {id: 2,
  //         title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
  //         description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
  //         image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
  //         brand:'Loncaster',
  //         category:'Cell Phone Automobile Cradles',
  //         colour:'Black',
  //         price:25.36,quantity:75}];

  products: Product[] = [];
  productDetails: Product = {} as Product;
  categories: string[] = [];
  component= "ProductListComponent";
  value : string = "";
  query: string = "";
  minPrice: number = 0;
  maxPrice: number = 0;
  searchDTO!: SearchDTO ;
  isLoggedIn: boolean = true;
  userIsAnAdmin: boolean = true;
  

  constructor(public productService: ProductService, private authenticationService: AuthenticationService) {
   }

  ngOnInit(): void {
    this.getAllProducts();
    this.getCategories();
    // this.isLoggedIn = this.authenticationService.isLoggedIn();
    // this.userIsAnAdmin = this.authenticationService.userRoleIsAdmin();

  
  }


  public getCategories(){
    this.categories = Object.values(Category);
    
  }

  
  public getAllProducts(): any {
    this.productService.getAllProducts().subscribe(
      (response) => {
        this.products = response
      });
  }

}
