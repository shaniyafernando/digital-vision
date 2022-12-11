import { Component, OnInit } from '@angular/core';
import { DeliveryStatusService } from 'src/app/services/delivery-status.service';

@Component({
  selector: 'app-delivery-status',
  templateUrl: './delivery-status.component.html',
  styleUrls: ['./delivery-status.component.css']
})
export class DeliveryStatusComponent implements OnInit {

  paymentId: number = 0;
  status: string = '';

  constructor(private deliveryStatusService: DeliveryStatusService) { }

  processingCSS: string = '';
  packingCSS: string = '';
  dispatchingCSS: string = '';
  deliveryCSS: string = '';
  closedCSS: string = '';

  ngOnInit(): void {
    this.getDeliveryStatus();
    this.paymentId = history.state.data.paymentId;
  }

  public getDeliveryStatus(){
    this.deliveryStatusService.getDeliveryStatus(this.paymentId).subscribe(
        response =>  {
          this.status = response.status
          if(this.status === "ORDER_PROCESSING"){
            this.processingCSS = 'completed';
            this.packingCSS = '';
            this.dispatchingCSS = '';
            this.deliveryCSS= '';
            this.closedCSS = '';
          }
          if(this.status === "ORDER_PACKING"){
            this.processingCSS = 'completed';
            this.packingCSS = 'completed';
            this.dispatchingCSS = '';
            this.deliveryCSS= '';
            this.closedCSS = '';
          }
          if(this.status === "ORDER_DISPATCHED"){
            this.processingCSS = 'completed';
            this.packingCSS = 'completed';
            this.dispatchingCSS = 'completed';
            this.deliveryCSS= '';
            this.closedCSS = '';
          }
          if(this.status === "ORDER_DELIVERED"){
            this.processingCSS = 'completed';
            this.packingCSS = 'completed';
            this.dispatchingCSS = 'completed';
            this.deliveryCSS= 'completed';
            this.closedCSS = '';
          }
          if(this.status === "ORDER_PROCESSING"){
            this.processingCSS = 'completed';
            this.packingCSS = 'completed';
            this.dispatchingCSS = 'completed';
            this.deliveryCSS= 'completed';
            this.closedCSS = 'completed';
          }
        }
    )
  }




}
