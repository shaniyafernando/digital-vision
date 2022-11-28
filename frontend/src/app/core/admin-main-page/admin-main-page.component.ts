import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-admin-main-page',
  templateUrl: './admin-main-page.component.html',
  styleUrls: ['./admin-main-page.component.css']
})
export class AdminMainPageComponent implements OnInit {

  // products!: Product[] ;
  
  component = 'AdminMainPageComponent' ;
  products: Product[]=[
    {id: 1,
    title:'Adjustable Cell Phone Stand',
    description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
    image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
    brand:'CreaDream',
    category:'Stands',
    colour:'Pink',
    price:30.99,quantity:50},
  {id: 2,
    title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
    description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
    image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
    brand:'Loncaster',
    category:'Cell Phone Automobile Cradles',
    colour:'Black',
    price:25.36,quantity:75},
    {id: 1,
      title:'Adjustable Cell Phone Stand',
      description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
      image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
      brand:'CreaDream',
      category:'Stands',
      colour:'Pink',
      price:30.99,quantity:50},
    {id: 2,
      title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
      description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
      image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
      brand:'Loncaster',
      category:'Cell Phone Automobile Cradles',
      colour:'Black',
      price:25.36,quantity:75},
      {id: 1,
        title:'Adjustable Cell Phone Stand',
        description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
        image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
        brand:'CreaDream',
        category:'Stands',
        colour:'Pink',
        price:30.99,quantity:50},
      {id: 2,
        title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
        description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
        image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
        brand:'Loncaster',
        category:'Cell Phone Automobile Cradles',
        colour:'Black',
        price:25.36,quantity:75},
        {id: 1,
          title:'Adjustable Cell Phone Stand',
          description:'Special Design: MULTI-ANGLE design make cell phone stand possible to be adjusted to your desired angle (270 degree).',
          image:'http://img.dxcdn.com/productimages/sku_543622_1.jpg',
          brand:'CreaDream',
          category:'Stands',
          colour:'Pink',
          price:30.99,quantity:50},
        {id: 2,
          title:'Car Phone Mount Silicone Car Pad Mat for Various Dashboards',
          description:'The dashboard cell phone holder is suitable for most kinds of cell phones or GPS devices which are between 6-12mm thick.',
          image:'https://tse3.mm.bing.net/th?id=OIP.GxpFSKygm83njsYF1tFu4QHaF9&pid=Api&P=0',
          brand:'Loncaster',
          category:'Cell Phone Automobile Cradles',
          colour:'Black',
          price:25.36,quantity:75}];

  noProductData: boolean = false;

  constructor(public productService: ProductService, private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    // this.getAllProducts();
    if(this.products == null){this.noProductData = true}
  }

  public navigateToProductForm():void{
    this.router.navigate(['/product-form'], {state: {data: {title:'Add new product'}}});
  }

  logout(){
    this.authenticationService.logout();
  }

  

  // public getAllProducts(){
  //   this.products = this.productService.getAllProducts();
  // }

}
