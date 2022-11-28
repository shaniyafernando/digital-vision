import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SearchDTO } from 'src/app/dtos/SearchDTO';
import { Product } from 'src/app/models/product';
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


  noProductData: boolean = true;
  productDetails!: Product;
  categories: string[] = [];
  component= "ProductListComponent";
  value : string = "";
  query: string = "";
  minPrice: number = 0;
  maxPrice: number = 0;
  searchDTO!: SearchDTO ;
  

  constructor(public productService: ProductService) {
   }

  ngOnInit(): void {
    // this.getAllProducts();
    this.getCategories();

    if(this.products != null){
      this.noProductData = false;
    }
  }


  public getCategories(){
    this.categories = Object.values(Category);
    
  }

  

  

  


  
  // public getAllProducts(): any {
  //   this.products = this.productService.getAllProducts();
  // }

}
