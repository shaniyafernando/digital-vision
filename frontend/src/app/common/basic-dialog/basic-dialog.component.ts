import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-basic-dialog',
  templateUrl: './basic-dialog.component.html',
  styleUrls: ['./basic-dialog.component.css']
})
export class BasicDialogComponent implements OnInit {

  title: string = '';
  message: string = '';

  constructor(public dialogRef: MatDialogRef<BasicDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,) { 
      this.title = this.data.title;
      this.message = this.data.message;
    }

  ngOnInit(): void {
  }

  public closeDialog(){
    this.dialogRef.close();
  }
}
