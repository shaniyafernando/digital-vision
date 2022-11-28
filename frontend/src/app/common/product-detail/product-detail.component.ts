import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  

  showAddToWishListButton: boolean = false;
  showRemoveFromWishListButton: boolean = false;
  showQuantityFormField: boolean = false;
  showGoBackButton: boolean = false;
  showEditActionButton: boolean = false;
  showDeleteActionButton: boolean = false;
  showRemoveFromCartButton: boolean = false;

  data!:any;

  finalProduct!: Product;
  finalComponent!: String;
  quantityForm!: FormGroup;

  constructor(private router: Router, private productService: ProductService, private fb : FormBuilder,
    private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
    this.data = history.state.data;
    this.finalProduct = this.data.product;
    this.finalComponent = this.data.component;
    this.displayButtons();
    this.fb.group({
      quantity: new FormControl(0,[Validators.required])
    });
    this.grantAccessToButtons();
  }

  private grantAccessToButtons(): void{
    const admin = this.authenticationService.userRoleIsAdmin();
    if(admin == true){
      this.showAddToWishListButton= false;
      this.showRemoveFromWishListButton = false;
      this.showQuantityFormField= false;
      this.showGoBackButton = false;
      this.showEditActionButton = true;
      this.showDeleteActionButton = true;
      this.showRemoveFromCartButton = false;
    }

    this.showAddToWishListButton= true;
      this.showRemoveFromWishListButton = true;
      this.showQuantityFormField= true;
      this.showGoBackButton = true;
      this.showEditActionButton = false;
      this.showDeleteActionButton = false;
      this.showRemoveFromCartButton = true;
  }

  public onSubmit(){}

  public displayButtons():void{
    if(this.finalComponent == 'ProductListComponent'){
      this.showAddToWishListButton = true;
      this.showRemoveFromWishListButton = false;
      this.showQuantityFormField = true;
      this.showGoBackButton = true;
      this.showRemoveFromCartButton = false;
    }
    if(this.finalComponent == 'WishListComponent'){
      this.showAddToWishListButton = false;
      this.showRemoveFromWishListButton = true;
      this.showQuantityFormField = true;
      this.showGoBackButton = true;
      this.showRemoveFromCartButton = false;
    }
    if(this.finalComponent == 'CartComponent'){
      this.showAddToWishListButton = false;
      this.showRemoveFromWishListButton = false;
      this.showQuantityFormField = false;
      this.showGoBackButton = true;
      this.showRemoveFromCartButton = true;
    }
  }

  public navigateToPreviousPage(component:String):void{
    if(component == 'ProductListComponent'){
      this.router.navigate(['']);
    }

    if(component == 'WishListComponent'){
      this.router.navigate(['/wish-list']);
    }

    if(component == 'CartComponent'){
      this.router.navigate(['/cart']);
    }
  }

  public navigateToProductForm(product:Product){
      this.router.navigate(['/product-form'], {state: {data: {title:'Edit product',product: product}}});
  }

  public deleteProduct(id:number){
      this.productService.deleteProduct(id);
  }


}
