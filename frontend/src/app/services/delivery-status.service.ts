import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DeliveryStatus } from '../models/DeliveryStatus';

@Injectable({
  providedIn: 'root'
})
export class DeliveryStatusService {

  constructor(private http: HttpClient) { }

  public getDeliveryStatus(paymentId: number): Observable<DeliveryStatus>{
    return this.http.get<DeliveryStatus>(`http://localhost:8080/api/v1/delivery-status/${paymentId}`);
  }
}
