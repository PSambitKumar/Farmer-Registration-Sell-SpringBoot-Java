import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FarmerRegistrationComponent} from "./component/farmer-registration/farmer-registration.component";
import {FarmerSellComponent} from "./component/farmer-sell/farmer-sell.component";
import {TrnsitPassComponent} from "./component/trnsit-pass/trnsit-pass.component";

const routes: Routes = [
  {path : "farmerRegistration", component : FarmerRegistrationComponent},
  {path : "", redirectTo : "farmerRegistration", pathMatch : "full"},
  {path : "farmerSell", component : FarmerSellComponent},
  {path : "transitPass", component : TrnsitPassComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
