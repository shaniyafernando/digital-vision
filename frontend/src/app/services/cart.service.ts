import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../models/cart';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public getAllProductsInCart(userId: number): Observable<Cart> {
    return this.http.get<Cart>(`http://localhost:8080/api/v1/cart/products/${userId}`);
  }

  public getProduct(productId: number): Observable<Product> {
    return this.http.get<Product>(`http://localhost:8080/api/v1/cart/product/${productId}`);
  }

  public deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/cart/${cartItemId}`);
  }
}
