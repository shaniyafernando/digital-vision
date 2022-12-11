import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartDTO } from '../dtos/CartDTO';
import { PlaceOrderDTO } from '../dtos/PlaceOrderDTO';
import { Address } from '../models/Address';
import { Cart } from '../models/cart';
import { CartItem } from '../models/cartItem';
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

  public getAddress(id: number): Observable<Address>{
    return this.http.get<Address>(`http://localhost:8080/api/v1/order/${id}`);
  }

  public updateDeliveryAddress(address: Address):Observable<Address>{
    return this.http.put<Address>(`http://localhost:8080/api/v1/order`, address);
  }

  public updateCartItem(cartItem: CartItem):Observable<CartItem>{
    return this.http.put<CartItem>(`http://localhost:8080/api/v1/order/item`, cartItem);
  }

  public checkout(cart: Cart): Observable<PlaceOrderDTO>{
    return this.http.post<PlaceOrderDTO>(`http://localhost:8080/api/v1/order`, cart);
  }

  public addFirstProductToCart(cartdto: CartDTO): Observable<Cart>{
    return this.http.post<Cart>(`http://localhost:8080/api/v1/cart`, cartdto);
  }

  public addProductToCart(cartdto: CartDTO): Observable<Cart>{
    return this.http.post<Cart>(`http://localhost:8080/api/v1/cart/existing`, cartdto);
  }
   
  public getCartItem(id: number): Observable<CartItem>{
    return this.http.get<CartItem>(`http://localhost:8080/api/v1/cart/item/${id}`);
  }
  
}
