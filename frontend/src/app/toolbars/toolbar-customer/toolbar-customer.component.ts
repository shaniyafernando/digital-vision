import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-toolbar-customer',
  templateUrl: './toolbar-customer.component.html',
  styleUrls: ['./toolbar-customer.component.css']
})
export class ToolbarCustomerComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }

  logout(){
    this.authenticationService.logout;
   }

}
