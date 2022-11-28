import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SearchDTO } from '../dtos/SearchDTO';
import { Product } from '../models/product';



@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiServerUrl = environment.baseServerUrl;

  constructor(private http: HttpClient) { }


  // public getAllProducts(searchDTO: SearchDTO): Observable<any> {
  //   return this.http.get(`${this.apiServerUrl}product/search/all`, searchDTO);
  // }

  // public getAllProductsByQuery(query: String): Observable<any> {
  //   return this.http.get<any>(`${this.apiServerUrl}product/search/query`, query);
  // }
  public getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiServerUrl}product/all`);
  }

  public addNewProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiServerUrl}product`, product);
  }

  public updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiServerUrl}product`, product);
  }

  public deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/product/${id}`);
  }

}
