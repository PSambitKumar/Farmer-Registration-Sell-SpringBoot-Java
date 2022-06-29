import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FarmerRegistrationComponent} from "./component/farmer-registration/farmer-registration.component";

const routes: Routes = [
  {path : "farmerRegistration", component : FarmerRegistrationComponent},
  {path : "", redirectTo : "farmerRegistration", pathMatch : "full" ,}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
