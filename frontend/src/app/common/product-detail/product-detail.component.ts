import { Component,  OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProductFormComponent } from 'src/app/admin-frontend/product-form/product-form.component';
import { WishListDTO } from 'src/app/dtos/WishListDTO';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';
import { WishListService } from 'src/app/services/wish-list.service';

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

  data!:any;

  finalProduct: Product = {} as Product;
  finalComponent: String = "";
  quantityForm= new FormGroup({
    quantity: new FormControl(1,[Validators.required])
  })
  loggedIn: boolean = false;
  userIsAdmin: boolean = false;
  wishlistdto: WishListDTO = {} as WishListDTO;
  currentUserId: number = 1;

  constructor(private router: Router, private productService: ProductService, 
    private authenticationService : AuthenticationService, public dialog: MatDialog,
    private wishlistService: WishListService) { 
       this.currentUserId = this.authenticationService.getCurrentUser();
    }

  ngOnInit(): void {
    this.data = history.state.data;
    this.finalProduct = this.data.product;
    this.finalComponent = this.data.component;
    this.grantAccessToButtons();
  }

  private grantAccessToButtons(): void{
    this.userIsAdmin = false;
    // this.authenticationService.userRoleIsAdmin();
    this.loggedIn = true;
    // this.authenticationService.isLoggedIn();

    if(this.userIsAdmin && this.loggedIn){

      this.showEditActionButton = true;
      this.showDeleteActionButton = true;

      if(this.finalComponent == 'ProductListComponent'){
        this.showAddToWishListButton = false;
        this.showRemoveFromWishListButton = false;
        this.showQuantityFormField = false;
        this.showGoBackButton = false;
      }
      
    }

    if(!this.userIsAdmin && this.loggedIn){

      this.showEditActionButton = false;
      this.showDeleteActionButton = false;

      if(this.finalComponent == 'ProductListComponent'){
        this.showAddToWishListButton = true;
        this.showRemoveFromWishListButton = false;
        this.showQuantityFormField = true;
        this.showGoBackButton = true;
      }
      if(this.finalComponent == 'WishListComponent'){
        this.showAddToWishListButton = false;
        this.showRemoveFromWishListButton = true;
        this.showQuantityFormField = false;
        this.showGoBackButton = true;
      }
    }

    if(!this.loggedIn){
      this.showAddToWishListButton= false;
      this.showRemoveFromWishListButton = false;
      this.showQuantityFormField= false;
      this.showGoBackButton = true;
      this.showEditActionButton = false;
      this.showDeleteActionButton = false;
    }

  }

  public onSubmit(){}

  openDialog(product: Product): void {
    const dialogRef = this.dialog.open(ProductFormComponent, {
      width: '1000px',
      data: {product: product, action:"Edit"},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result: ' + result);
      this.router.navigate(['/home']);
    });
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

  public deleteProduct(id: number){
      console.log(id);
      this.productService.deleteProduct(id).subscribe(
        response => console.log(response)
      );
      this.router.navigate(['/home']);
  }

  public onAddToWishList(productId: number){

    this.wishlistdto.userId = 1;
    this.wishlistdto.productId = productId;
    console.log(this.wishlistdto);
    this.wishlistService.addToWishList(this.wishlistdto).subscribe(
      response => console.log(response)
    );
  }


}
