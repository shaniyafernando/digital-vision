import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {

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

  // products: Product[]=[];
  noProductData: boolean = false;
  component= "WishListComponent";

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    // this.getAllProducts();

    if(this.products != null){
      this.noProductData = false;
    }
  }

  

  // public getAllProducts():void{
    
  // }

}
