import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BasicDialogComponent } from 'src/app/common/basic-dialog/basic-dialog.component';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-token-confirmation',
  templateUrl: './token-confirmation.component.html',
  styleUrls: ['./token-confirmation.component.css']
})
export class TokenConfirmationComponent implements OnInit {

  enteredToken: string = '';
  tokenMatches: boolean = false;
  thirdStrike: boolean = false;
  date: Date = new Date()
  

  tokenConfirmationForm= new FormGroup({
    token: new FormControl('',[Validators.required])
  });

  constructor(private authenticationService: AuthenticationService,
    public dialog: MatDialog, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.enteredToken = this.tokenConfirmationForm.value.token as string;
    
    this.tokenMatches = this.authenticationService.confirmToken(this.enteredToken);
    this.thirdStrike = this.authenticationService.thirdStrike();

    if(this.tokenMatches === true){
      this.authenticationService.confirm(this.enteredToken).subscribe(
        response => {
          console.log(response);
          if(response.token == "confirmed"){
            this.openTokenConfirmedDialog();
            this.authenticationService.login(response.user);
            this.authenticationService.setUserId(response.user);
            this.router.navigate(["/home"]);
          }
        }
      )
    }

    if(this.thirdStrike == true){
       this.openThirdStrikeDialog();
       this.date.setMinutes(new Date().getMinutes() + 10);
       this.authenticationService.noAccess(this.date);
       this.authenticationService.removeToken();
       this.authenticationService.removeStrike();
       this.router.navigate(["/home"]);
    }

    this.authenticationService.strike(1);
    this.openIncorrectTokenDialog();
   



  }

  public openTokenConfirmedDialog(){
    this.dialog.open(BasicDialogComponent, {
      data: {title: "Registration Successful!",
      message: "Thank you for registering with us!"},
    });
  }

  public openIncorrectTokenDialog(){
    this.dialog.open(BasicDialogComponent, {
      data: {title: "Incorrect token!",
      message: "The activation code is incorrect. Please try again!"},
    });
  }

  public openThirdStrikeDialog(){
    this.dialog.open(BasicDialogComponent, {
      data: {title: "Incorrect token!",
      message: "Its your third attempt! Please try again in 10 minutes."},
    });
  }

}
