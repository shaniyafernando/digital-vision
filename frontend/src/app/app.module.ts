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
import {MatStepperModule} from '@angular/material/stepper';
import { ToolbarPublicComponent } from './toolbars/toolbar-public/toolbar-public.component';
import { ToolbarCustomerComponent } from './toolbars/toolbar-customer/toolbar-customer.component';
import { ToolbarAdminComponent } from './toolbars/toolbar-admin/toolbar-admin.component';
import { RegisterComponent } from "./authentication/register/register.component";
import { ProductFormComponent } from "./admin-frontend/product-form/product-form.component";
import { UserPermissionComponent } from "./admin-frontend/user-permission/user-permission.component";
import { LoginComponent } from "./authentication/login/login.component";
import { EmailConfirmationComponent } from "./authentication/register/email-confirmation/email-confirmation/email-confirmation.component";



@NgModule({
  declarations: [
    AppComponent,
    ProductDetailComponent,
    ProductFormComponent,
    LoginComponent,
    RegisterComponent,
    UserPermissionComponent,
    EmailConfirmationComponent,
    ToolbarPublicComponent,
    ToolbarCustomerComponent,
    ToolbarAdminComponent
  ],
  imports: [
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
