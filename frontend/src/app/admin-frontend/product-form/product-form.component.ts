import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
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
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  title = new FormControl('',[Validators.required]);
  image = new FormControl('',[Validators.required]);
  colour = new FormControl('',[Validators.required]);
  brand = new FormControl('',[Validators.required]);
  description = new FormControl('',[Validators.required]);
  category = new FormControl('',[Validators.required]);
  price = new FormControl(0,[Validators.required]);
  quantity = new FormControl(0,[Validators.required]);

  productForm!: FormGroup;
  data!:any;
  formTitle!: String;
  product!: Product;
  isSubmitted: boolean = false;
  savedProduct!:Product;
  categories: String[] = [];

  get formControls() { return this.productForm.controls; }

  constructor(private formBuilder: FormBuilder, private router:Router,
    private snackBar: MatSnackBar, private productService: ProductService) { }

  ngOnInit(): void {
    this.categories = Object.values(Category);
    this.buildProductForm();
    this.data = history.state.data;
    this.formTitle = this.data.title;
    this.product = this.data.product;
    if(this.product != null){
      this.setValue();
    }
  }

  public buildProductForm():void{
    this.productForm  =  this.formBuilder.group({
      title:this.title,
      description: this.description,
      image: this.image,
      category: this.category,
      brand: this.brand,
      colour: this.colour,
      price: this.price,
      quantity: this.quantity
  });
  }

  public setValue(){
    this.productForm.get('title')?.setValue(this.product.title);
    this.productForm.get('description')?.setValue(this.product.description);
    this.productForm.get('image')?.setValue(this.product.image);
    this.productForm.get('category')?.setValue(this.product.category);
    this.productForm.get('brand')?.setValue(this.product.brand);
    this.productForm.get('colour')?.setValue(this.product.colour);
    this.productForm.get('price')?.setValue(this.product.price);
    this.productForm.get('quantity')?.setValue(this.product.quantity);

  }

  public saveProduct(){
    this.isSubmitted = true;
    this.savedProduct.title = this.productForm.get('title')?.value;
    this.savedProduct.description = this.productForm.get('description')?.value;
    this.savedProduct.image = this.productForm.get('image')?.value;
    this.savedProduct.category = this.productForm.get('category')?.value;
    this.savedProduct.brand = this.productForm.get('brand')?.value;
    this.savedProduct.colour = this.productForm.get('colour')?.value;
    this.savedProduct.price = this.productForm.get('price')?.value;
    this.savedProduct.quantity = this.productForm.get('quantity')?.value;
    if(this.productForm.invalid){
      this.invalidProductForm();
    }
    
    if(this.formTitle == 'Add new product'){
      this.productService.addNewProduct(this.savedProduct);
      this.router.navigate(["/home"]);
    }

    this.productService.updateProduct(this.savedProduct);
    this.router.navigate(["/home"]);
  }

  public invalidProductForm(){
    return this.snackBar.open('Product form is invalid!!','OK', {
      horizontalPosition: 'start',
      verticalPosition: 'bottom',
    });
  }

  

  

}
