import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-permission',
  templateUrl: './user-permission.component.html',
  styleUrls: ['./user-permission.component.css']
})
export class UserPermissionComponent implements OnInit {

  options:String[] = [];
  
  role = new FormControl('customer',[Validators.required]);
  user = new FormControl('',[Validators.required]);

  permissionForm: FormGroup = this.formBuilder.group({
    user: this.user,
    role: this.role
  });;


  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {}


}
