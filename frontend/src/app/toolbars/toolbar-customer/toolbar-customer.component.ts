import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/dtos/userDTO';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-toolbar-customer',
  templateUrl: './toolbar-customer.component.html',
  styleUrls: ['./toolbar-customer.component.css']
})
export class ToolbarCustomerComponent implements OnInit {

  userId: number = 0;
  user: UserDTO = {} as UserDTO;

  constructor(private authenticationService: AuthenticationService,private router: Router) { }

  ngOnInit(): void {
    this.userId = this.authenticationService.getCurrentUser();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigate(["/home"]);
    window.location.reload();
  }

  deleteAccount(){
    this.authenticationService.deleteUser(this.userId).subscribe(
      response => {
        console.log(response);
        this.authenticationService.logout();
        this.router.navigate(["/home"]);
        window.location.reload();
      }
    );
  }

}
