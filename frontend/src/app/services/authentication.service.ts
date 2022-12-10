import { HttpClient } from '@angular/common/http';
import { DeclarationListEmitMode } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../dtos/LoginRequest';
import { RegistrationRequest } from '../dtos/RegistrationRequest';
import { TokenResponse } from '../dtos/TokenResponse';
import { UserDTO } from '../dtos/userDTO';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  j: string = '';
  user: string = '';
  wishlistid: string = '';
  cartid: string ='';
  userId: string = '';

  constructor(private http: HttpClient) { }

  public noAccess(date:Date){
    localStorage.setItem("NO_ACCESS" , date.toString());
  }

  public removeNoAccess(){
    localStorage.removeItem('NO_ACCESS');
  }

  public isAfterNoAccessExpiryTime(){
    return new Date(localStorage.getItem('NO_ACCESS') as string).valueOf() > new Date().valueOf();
  }

  public hasNoAccess(){
    return localStorage.getItem('NO_ACCESS') !== null;
  }

  public login(userData: User){
    localStorage.setItem('USER_ROLE', userData.role);
    this.wishlistid = userData.wishListId?.toString() || '' ;
    localStorage.setItem('WISHLIST_ID', this.wishlistid);

    this.cartid = userData.cartId?.toString()|| '' ;
    localStorage.setItem('CART_ID', this.cartid);
  }
   
  public setUserId(userData: User){
    this.userId = userData.id?.toString() || '' ;
    localStorage.setItem('USER_ID', this.userId);
  }

  public setWishListId(userData: User){
    this.wishlistid = userData.wishListId?.toString() || '' ;
    localStorage.setItem('WISHLIST_ID', this.wishlistid);
  }

  public setCartId(userData: User){
    this.cartid = userData.cartId?.toString() || '' ;
    localStorage.setItem('CART_ID', this.cartid);
  }

  public userHasWishlistId(){
    return localStorage.getItem('WISHLIST_ID') !== "";
  }

  public userHasCartId(){
    return localStorage.getItem('CART_ID') !== "";
  }

  public token(response:TokenResponse){
    localStorage.setItem('TOKEN', response.token);
  }

  public confirmToken(token:string){
    return localStorage.getItem('TOKEN') === token;
  }

  public strike(num: number){
    const i = this.getStrikeNumber();
    
    if(i === null){ 
      this.j = num.toString as unknown as string}

    const k = num + i;
    this.j = k.toString as unknown  as string;

    localStorage.setItem('STRIKE',this.j)
  }

  public getStrikeNumber(){
    return Number(localStorage.getItem('STRIKE'));
  }

  public thirdStrike(){
    return localStorage.getItem('STRIKE') === "3";
  }

  public getCurrentUser(){
    return Number(localStorage.getItem('USER_ID'));
  }

  public isLoggedIn(){
    return localStorage.getItem('USER_ROLE') !== null;
  }

  public userRoleIsAdmin(){
    return localStorage.getItem("USER_ROLE") === "ADMIN";
  }

  public logout(){
    localStorage.removeItem('USER_ROLE');
    localStorage.removeItem('USER_ID');
    localStorage.removeItem('WISHLIST_ID');
    localStorage.removeItem('CART_ID');
  }

  public removeToken(){
    localStorage.removeItem('TOKEN');
  }

  public removeStrike(){
    localStorage.removeItem('STRIKE');
  }

  public signIn(loginDetails: LoginRequest): Observable<User> {
    return this.http.post<User>(`http://localhost:8080/api/v1/authentication/login`, loginDetails);
  }

  public signUp(registrationDetails: RegistrationRequest): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`http://localhost:8080/api/v1/authentication/register`, registrationDetails);
  }

  public confirm(token: string): Observable<TokenResponse> {
    return this.http.get<TokenResponse>(`http://localhost:8080/api/v1/authentication/token=${token}`);
  }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`http://localhost:8080/api/v1/authentication/all`);
  }

  public getUser(id: number): Observable<User> {
    return this.http.get<User>(`http://localhost:8080/api/v1/authentication/${id}`);
  }

  public updateUserRole(user: UserDTO): Observable<User> {
    return this.http.put<User>(`http://localhost:8080/api/v1/authentication/role`, user);
  }

  public deleteUser(userId: number): Observable<any> {
    return this.http.delete(`http://localhost:8080/api/v1/authentication/${userId}`);
  }
}
