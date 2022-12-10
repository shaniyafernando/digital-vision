import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProductFormComponent } from 'src/app/admin-frontend/product-form/product-form.component';
import { SearchDTO } from 'src/app/dtos/SearchDTO';
import { Product } from 'src/app/models/product';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  filterForm = new FormGroup({
    category: new FormControl('',[Validators.required]),
    minPrice: new FormControl(0,[Validators.required]),
    maxPrice: new FormControl(0,[Validators.required])
  })

  dummyData: any[] = [
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.GtTEErEk8ul49Sr_LMkW-AHaGr&pid=Api&P=0",
      title: "Mobile Stand",
      description: "Its a mobile stand!",
      category: "1",
      colour: "white",
      brand: "KOKO",
      price: 36,
      quantity: 258
    },
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.rqK91vQXsugViYuTDpgQZgHaHa&pid=Api&P=0",
      title: "Mobile grip",
      description: "Its a mobile grip",
      category: "3",
      colour: "White",
      brand: "MOMO",
      price: 50,
      quantity: 257
    },
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.v7T1VkLCKLr_xWF-uCFioAHaFp&pid=Api&P=0",
      title: "Mobile screen magnifier",
      description: "Magnifies mobile screens.",
      category: "4",
      colour: "Black",
      brand: "DODO",
      price: 358,
      quantity: 125
    },
    {
      image: "https://tse3.mm.bing.net/th?id=OIP.MDyB7GdeD7MeTq9V3QnRrAHaFj&pid=Api&P=0",
      title: "Automobile gadjet",
      description: "Its a Automobile gadjet",
      category: "2",
      colour: "Black",
      brand: "YOYO",
      price: 50,
      quantity: 236
    },
    {
      image: "https://tse2.mm.bing.net/th?id=OIP.2i3koQSwJU5kiInbatK7uAHaHa&pid=Api&P=0",
      title: "Handheld mobile stabilizer",
      description: "It is a handheld mobile stabilizer",
      category: "5",
      colour: "Black",
      brand: "LOLO",
      price: 80,
      quantity: 563
    },
  ];

  products: Product[] = [];
  productDetails: Product = {} as Product;
  categories: any[] = [
    {value: "0", option: "All"},
    {value: "1", option: "Stands"},
    {value: "2", option: "Automobile Gradles"},
    {value: "3", option: "Grips"},
    {value: "4", option: "Screen Expanders & Magnifiers"},
    {value: "5", option: "Handheld Gimbals & Stabilizers"},
  ];
  component= "ProductListComponent";
  value : string = "";
  query: string = "";
  minPrice: number = 0;
  maxPrice: number = 0;
  searchDTO: SearchDTO = {} as SearchDTO ;
  isLoggedIn: boolean = false;
  userIsAnAdmin: boolean = false;
  noProductData: boolean = true
  formSubmitted: boolean = false;
  queryEntered: boolean = false;


  constructor(public productService: ProductService, private router: Router,
    private authenticationService: AuthenticationService, public dialog: MatDialog,
    ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authenticationService.isLoggedIn();
    this.userIsAnAdmin = this.authenticationService.userRoleIsAdmin();
    this.getAllProducts();
    // this.addDummyData();
  }

  // public addDummyData(){
  //   this.dummyData.forEach(element => {
  //     this.productService.addNewProduct(element).subscribe(
  //       response => console.log(response)
  //     )
  //   });
  // }


  openDialog(): void {
    const dialogRef = this.dialog.open(ProductFormComponent, {
      width: '1000px',
      data: {action: "Add", product: {} as Product},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result: ' + result);
    });
  }

  public onSearch(){
    this.productService.getAllProductsByQuery(this.query).subscribe(
      response => this.products = response
    );
  }

  public onFilter(){
    
    this.searchDTO.type = this.filterForm.value.category as string;
    this.searchDTO.minPrice = this.filterForm.value.minPrice as number;
    this.searchDTO.maxPrice = this.filterForm.value.maxPrice as number;

    console.log(this.searchDTO);
    this.productService.filterAllProducts(this.searchDTO.type,this.searchDTO.minPrice, this.searchDTO.maxPrice).subscribe(
      response => this.products = response
    );
  }

  
  public getAllProducts(): any {
    this.productService.getAllProducts().subscribe(
      (response) => {
        this.products = response
        if(this.products != null){
          this.noProductData = false;
        }
      });
  }

}
