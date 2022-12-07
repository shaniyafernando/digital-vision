import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../dtos/LoginRequest';
import { UserDTO } from '../dtos/userDTO';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  public login(userData: User){
    localStorage.setItem('USER_ROLE', userData.role);
  }
   
  public setUserId(userData: UserDTO){
    localStorage.setItem('USER_ID', userData.id.toString());
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
  }

  public signIn(loginDetails: LoginRequest): Observable<User> {
    return this.http.post<User>(`http://localhost:8080/api/v1/authentication/login`, loginDetails);
  }
}
