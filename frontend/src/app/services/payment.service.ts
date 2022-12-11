import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlaceOrderDTO } from '../dtos/PlaceOrderDTO';
import { Address } from '../models/Address';
import { Payment } from '../models/Payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  public updateBillingAddress(address: Address):Observable<Address>{
    return this.http.put<Address>(`http://localhost:8080/api/v1/payment`, address);
  }

  public pay(order: PlaceOrderDTO): Observable<Payment>{
    return this.http.post<Payment>(`http://localhost:8080/api/v1/payment`, order);
  }
}
