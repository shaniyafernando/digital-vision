import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-token-confirmation',
  templateUrl: './token-confirmation.component.html',
  styleUrls: ['./token-confirmation.component.css']
})
export class TokenConfirmationComponent implements OnInit {

  

  token = new FormControl('',[Validators.required]);

  tokenConfirmationForm: FormGroup = this.formBuilder.group({
    token: this.token
  });;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
  }

}
