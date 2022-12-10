import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/dtos/userDTO';
import { User } from 'src/app/models/User';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-user-permission',
  templateUrl: './user-permission.component.html',
  styleUrls: ['./user-permission.component.css']
})
export class UserPermissionComponent implements OnInit {

  permissionForm = new FormGroup({
    user: new FormControl(0,[Validators.required]),
    role: new FormControl('USER',[Validators.required])
  });

  users: User[] = [];
  value: number = 0;
  selectedUser: UserDTO = {} as UserDTO;

  get formControls() { return this.permissionForm.controls; }

  constructor( private authenticationService: AuthenticationService,private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.authenticationService.getUsers().subscribe(
      response => {
        console.log(response);
        this.users = response;
      }
    );
  }

  onSubmit(){
    this.selectedUser.id = this.permissionForm.value.user as number;
    this.selectedUser.role = this.permissionForm.value.role as string;
    console.log(this.selectedUser)
    this.authenticationService.updateUserRole(this.selectedUser).subscribe(
      response => {
        console.log(response);
        this.router.navigate(["/home"]);
      }
    )
  }

}
