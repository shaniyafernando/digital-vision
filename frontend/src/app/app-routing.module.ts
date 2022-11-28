import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductDetailComponent } from './common/product-detail/product-detail.component';
import { AdminMainPageComponent } from './core/admin-main-page/admin-main-page.component';
import { ProductFormComponent } from './core/admin-main-page/product-form/product-form.component';
import { UserPermissionComponent } from './core/admin-main-page/user-permission/user-permission.component';
import { LoginComponent } from './core/authentication/login/login.component';
import { RegisterComponent } from './core/authentication/register/register.component';
import { PublicMainPageComponent } from './core/public-main-page/public-main-page.component';
import { CartComponent } from './core/user-main-page/cart/cart.component';
import { DeliveryStatusComponent } from './core/user-main-page/delivery-status/delivery-status.component';
import { OrderComponent } from './core/user-main-page/order/order.component';
import { PaymentComponent } from './core/user-main-page/payment/payment.component';
import { ProductListComponent } from './core/user-main-page/product-list/product-list.component';
import { WishListComponent } from './core/user-main-page/wish-list/wish-list.component';

const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'wish-list', component: WishListComponent },
  { path: 'product-details', component: ProductDetailComponent },
  { path: 'product-form', component: ProductFormComponent },
  { path: 'admin-panel', component: AdminMainPageComponent },
  {path:'register', component: RegisterComponent},
  {path:'cart', component: CartComponent},
  {path:'order', component: OrderComponent},
  {path:'payment', component: PaymentComponent},
  {path:'delivery', component: DeliveryStatusComponent},
  {path:'user-permission', component: UserPermissionComponent},
  { path: 'home',   redirectTo: '', pathMatch: 'full' },
  {path:'logout', component: PublicMainPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

