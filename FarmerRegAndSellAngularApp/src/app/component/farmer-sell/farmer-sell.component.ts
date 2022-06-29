import { Component, OnInit } from '@angular/core';
import {FarmerSell} from "../../model/farmer-sell";
import {Farmer} from "../../model/farmer";
import {FarmerServiceService} from "../../service/farmer-service.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-farmer-sell',
  templateUrl: './farmer-sell.component.html',
  styleUrls: ['./farmer-sell.component.css']
})
export class FarmerSellComponent implements OnInit {
  farmerSell : FarmerSell =new FarmerSell();
  farmer : Farmer = new Farmer();
  farmerList : Farmer[] = [];
  constructor(private farmerService: FarmerServiceService, private router : Router) { }

  ngOnInit(): void {
    this.farmerService.getFarmerList().subscribe(data => {
      this.farmerList = data;
      console.log("Farmer Lists are : ");
      console.log(this.farmerList)
    })
  }
  callRegisterFarmer(){
    this.router.navigate(["farmerRegistration"]);
  }
  callTransit(){
    this.router.navigate(["transitPass"]);
  }
  farmerSellForm(){
    console.log(this.farmerSell);
    this.farmerService.saveFarmerSell(this.farmerSell).subscribe(data =>{
      console.log(data);
      this.router.navigate(["transitPass"]);
    })
  }
}
