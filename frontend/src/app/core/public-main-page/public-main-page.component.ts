import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';

enum Category{
  Stands = "Stands",
  AutomobileCradles = "Automobile Cradles",
  Grips = "Grips",
  ScreenExpandersAndMagnifiers = "Screen Expanders & Magnifiers",
  HandheldGimbalsAndStabilizers = "Handheld Gimbals & Stabilizers"
}

@Component({
  selector: 'app-public-main-page',
  templateUrl: './public-main-page.component.html',
  styleUrls: ['./public-main-page.component.css']
})
export class PublicMainPageComponent implements OnInit {
  noProductData: boolean = false;
  products: Product[] = [];
  component = 'PublicMainPage'
  categories: Category[] = [];

  constructor() { }

  ngOnInit(): void {
    if(this.products == null){this.noProductData = true};
    this.categories = Object.values(Category);
  }

}
