import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  productForm = new FormGroup({
    title: new FormControl<String>('',[Validators.required]),
    description: new FormControl('',[Validators.required]),
    image: new FormControl('',[Validators.required]),
    category: new FormControl('',[Validators.required]),
    brand: new FormControl('',[Validators.required]),
    colour: new FormControl('',[Validators.required]),
    price: new FormControl(0,[Validators.required]),
    quantity: new FormControl(0,[Validators.required])
  })

  
  product: Product = {} as Product;
  savedProduct: Product = {} as Product;
  action: String = "";
  showAddForm: boolean = true;

  get formControls() { return this.productForm.controls; }

  constructor(
    private snackBar: MatSnackBar, private productService: ProductService,
    public dialogRef: MatDialogRef<ProductFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,) { 
      this.product = this.data.product;
      this.action = this.data.action;

    }

  ngOnInit(): void {
    this.setValue();

    if(this.action == "Add"){this.showAddForm = true}else{this.showAddForm = false}
  }

  

  public setValue(){
    this.productForm.controls["title"].setValue(this.product.title);
    this.productForm.controls["description"].setValue(this.product.description);
    this.productForm.controls["image"].setValue(this.product.image);
    this.productForm.controls["category"].setValue(this.product.category);
    this.productForm.controls["brand"].setValue(this.product.brand);
    this.productForm.controls["colour"].setValue(this.product.colour);
    this.productForm.controls["price"].setValue(this.product.price);
    this.productForm.controls["quantity"].setValue(this.product.quantity);

  }

  public saveProduct(){
    
    this.savedProduct.title = this.productForm.value.title as string;
    this.savedProduct.description = this.productForm.value.description as string;
    this.savedProduct.image = this.productForm.value.image as string;
    this.savedProduct.category = this.productForm.value.category as string;
    this.savedProduct.brand = this.productForm.value.brand as string;
    this.savedProduct.colour = this.productForm.value.colour as string;
    this.savedProduct.price = this.productForm.value.price as number;
    this.savedProduct.quantity = this.productForm.value.quantity as number;

    if(this.productForm.invalid){
      this.invalidProductForm();
      console.log(this.productForm.value);
    }

    this.productService.addNewProduct(this.savedProduct).subscribe(
      response => console.log("new product: " + response)
    );
    this.dialogRef.close();
  }

  public editProduct(){
    
    this.savedProduct.title = this.productForm.value.title as string;
    this.savedProduct.description = this.productForm.value.description as string;
    this.savedProduct.image = this.productForm.value.image as string;
    this.savedProduct.category = this.productForm.value.category as string;
    this.savedProduct.brand = this.productForm.value.brand as string;
    this.savedProduct.colour = this.productForm.value.colour as string;
    this.savedProduct.price = this.productForm.value.price as number;
    this.savedProduct.quantity = this.productForm.value.quantity as number;
    this.savedProduct.id = this.product.id;

    this.productService.updateProduct(this.savedProduct).subscribe(
      response => console.log("updated product: " + response)
    );
    this.dialogRef.close();

    if(this.productForm.invalid){
      this.invalidProductForm();
      console.log(this.productForm.value);
    }
  }

  public invalidProductForm(){
    return this.snackBar.open('Product form is invalid!!','OK', {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    });
  }

  

  

}
