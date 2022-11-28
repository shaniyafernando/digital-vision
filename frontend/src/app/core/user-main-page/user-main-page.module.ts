import { NgModule } from "@angular/core";
import { FlexLayoutModule } from "@angular/flex-layout";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatDialogModule } from "@angular/material/dialog";
import { MatDividerModule } from "@angular/material/divider";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatMenuModule } from "@angular/material/menu";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { MatRadioModule } from "@angular/material/radio";
import { MatSelectModule } from "@angular/material/select";
import { MatSidenavModule } from "@angular/material/sidenav";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatToolbarModule } from "@angular/material/toolbar";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AppRoutingModule } from "src/app/app-routing.module";
import { ProductCardComponent } from "src/app/common/product-card/product-card.component";
import { MaterialModule } from "src/app/material.module";
import { CartComponent } from "./cart/cart.component";
import { DeliveryStatusComponent } from "./delivery-status/delivery-status.component";
import { OrderComponent } from "./order/order.component";
import { PaymentComponent } from "./payment/payment.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { WishListComponent } from "./wish-list/wish-list.component";
import {MatStepperModule} from '@angular/material/stepper';

@NgModule({
  declarations: [
    ProductListComponent,
    WishListComponent,
    CartComponent,
    OrderComponent,
    PaymentComponent,
    DeliveryStatusComponent,
    ProductCardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule,
    AppRoutingModule,
    MatRadioModule,
    MatDividerModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatButtonModule,
    MatCardModule,
    MatProgressBarModule,
    MatIconModule,
    MatSelectModule,
    MatToolbarModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatGridListModule,
    FlexLayoutModule,
    MatProgressBarModule,
    MatStepperModule,
  ],
  schemas: [],
  providers: []
})
export class UserMainPageModule { }