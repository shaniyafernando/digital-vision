import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';



@Injectable({
  providedIn: 'root'
})
export class ProductService {

  


  constructor(private http: HttpClient) { }


  public filterAllProducts(type:String, minPrice:number, maxPrice: number): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8080/api/v1/product/all/${type}/${minPrice}/${maxPrice}`);
  }

  public getAllProductsByQuery(query: String): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8080/api/v1/product/all/${query}`);
  }

  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/api/v1/product/all");
  }

  public addNewProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`http://localhost:8080/api/v1/product`, product);
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`http://localhost:8080/api/v1/product`, product);
  }

  public deleteProduct(id: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/product/${id}`);
  }

  

}
