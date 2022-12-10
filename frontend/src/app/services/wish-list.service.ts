import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ExistsDTO } from '../dtos/ExistsDTO';
import { WishListDTO } from '../dtos/WishListDTO';
import { Product } from '../models/product';
import { WishList } from '../models/WishList';

@Injectable({
  providedIn: 'root'
})
export class WishListService {

  constructor(private http: HttpClient) { }

  public getWishList(userId: number): Observable<WishList> {
    return this.http.get<WishList>(`http://localhost:8080/api/v1/wishlist/${userId}`);
  }

  public addFirstProductToWishList(wishlistDTO:WishListDTO): Observable<WishList> {
    return this.http.post<WishList>(`http://localhost:8080/api/v1/wishlist/new`, wishlistDTO);
  }

  public addProductToWishList(wishlistDTO:WishListDTO): Observable<WishList> {
    return this.http.post<WishList>(`http://localhost:8080/api/v1/wishlist/existing`, wishlistDTO);
  }

  public removeProductFromWishList(wishlistDTO:WishListDTO): Observable<any> {
    let productId = wishlistDTO.productId;
    let userId = wishlistDTO.userId;
    return this.http.delete(`http://localhost:8080/api/v1/wishlist/${productId}/${userId}`);
  }



  // public deleteFromWishList(id: number): Observable<any> {
  //   return this.http.delete(`http://localhost:8080/api/v1/product/${id}`);
  // }

}
