import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginDTO } from '../dtos/loginDTO';
import { UserDTO } from '../dtos/userDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiServerUrl = environment.baseServerUrl;

  constructor(private http: HttpClient) { }

  public login(userData: UserDTO){
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

  public signIn(loginDetails: LoginDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.apiServerUrl}authentication/login`, loginDetails);
  }
}
