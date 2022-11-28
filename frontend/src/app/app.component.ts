import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Digital Vision';

  showPublicDashboard: boolean = false;
  showUserDashboard: boolean = true;
  showAdminDashboard: boolean = false;

  constructor(private authenticationService: AuthenticationService){}

  ngOnInit(): void {
    // this.grantAccessToDashboards();
  }

  // grantAccessToDashboards(){
  //   const loggedIn = this.authenticationService.isLoggedIn();

  //   if(loggedIn == true){
  //      const role = this.authenticationService.userRoleIsAdmin();

  //      if(role == true){
  //       this.showAdminDashboard = true;
  //       this.showPublicDashboard = false;
  //       this.showUserDashboard = false;
  //      }

  //     this.showAdminDashboard = false;
  //     this.showPublicDashboard = false;
  //     this.showUserDashboard = true;
  //   }
  // }
}
