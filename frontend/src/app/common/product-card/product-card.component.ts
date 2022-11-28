import { Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product!: Product;

  @Input()
  component! : String;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public navigateToProductDetails():void{
    this.router.navigate(['/product-details'], {state: {data: {product: this.product,component:this.component}}});
  }

}
