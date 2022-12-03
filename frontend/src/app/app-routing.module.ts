import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductFormComponent } from './admin-frontend/product-form/product-form.component';
import { UserPermissionComponent } from './admin-frontend/user-permission/user-permission.component';
import { LoginComponent } from './authentication/login/login.component';
import { RegisterComponent } from './authentication/register/register.component';
import { TokenConfirmationComponent } from './authentication/token-confirmation/token-confirmation.component';
import { ProductDetailComponent } from './common/product-detail/product-detail.component';
import { ProductListComponent } from './common/product-list/product-list.component';
import { CartComponent } from './customer-frontend/cart/cart.component';
import { DeliveryStatusComponent } from './customer-frontend/delivery-status/delivery-status.component';
import { PaymentComponent } from './customer-frontend/payment/payment.component';
import { WishListComponent } from './customer-frontend/wish-list/wish-list.component';

const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'wish-list', component: WishListComponent },
  { path: 'product-details', component: ProductDetailComponent },
  { path: 'product-form', component: ProductFormComponent },
  {path:'register', component: RegisterComponent},
  {path:'cart', component: CartComponent},
  {path:'payment', component: PaymentComponent},
  {path:'delivery', component: DeliveryStatusComponent},
  {path:'user-permission', component: UserPermissionComponent},
  {path:'login', component: LoginComponent},
  {path:'token', component: TokenConfirmationComponent},
  { path: 'home',   redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

