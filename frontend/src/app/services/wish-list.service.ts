import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WishListDTO } from '../dtos/WishListDTO';
import { Product } from '../models/product';
import { WishList } from '../models/WishList';

@Injectable({
  providedIn: 'root'
})
export class WishListService {

  constructor(private http: HttpClient) { }

  public getWishList(userId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8080/api/v1/wishlist/${userId}`);
  }

  public addToWishList(wishlistDTO:WishListDTO): Observable<WishList> {
    return this.http.post<WishList>(`http://localhost:8080/api/v1/wishlist/new`, wishlistDTO);
  }

  // public deleteFromWishList(id: number): Observable<any> {
  //   return this.http.delete(`http://localhost:8080/api/v1/product/${id}`);
  // }

}
