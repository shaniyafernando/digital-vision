import { HttpClientModule } from "@angular/common/http";
import {  NgModule} from "@angular/core";
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
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { ProductDetailComponent } from "./common/product-detail/product-detail.component";
import { AdminMainPageComponent } from "./core/admin-main-page/admin-main-page.component";
import { ProductFormComponent } from "./core/admin-main-page/product-form/product-form.component";
import { UserPermissionComponent } from "./core/admin-main-page/user-permission/user-permission.component";
import { LoginComponent } from "./core/authentication/login/login.component";
import { EmailConfirmationComponent } from "./core/authentication/register/email-confirmation/email-confirmation/email-confirmation.component";
import { RegisterComponent } from "./core/authentication/register/register.component";
import { PublicMainPageComponent } from "./core/public-main-page/public-main-page.component";
import { UserMainPageComponent } from "./core/user-main-page/user-main-page.component";
import { UserMainPageModule } from "./core/user-main-page/user-main-page.module";
import { MaterialModule } from "./material.module";
import {MatStepperModule} from '@angular/material/stepper';



@NgModule({
  declarations: [
    AppComponent,
    ProductDetailComponent,
    AdminMainPageComponent,
    ProductFormComponent,
    LoginComponent,
    RegisterComponent,
    PublicMainPageComponent,
    UserPermissionComponent,
    EmailConfirmationComponent,
    UserMainPageComponent
  ],
  imports: [
    MaterialModule,
    UserMainPageModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
