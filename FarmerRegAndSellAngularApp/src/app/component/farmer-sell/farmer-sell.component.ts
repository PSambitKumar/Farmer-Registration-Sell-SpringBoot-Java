import { Component, OnInit } from '@angular/core';
import {FarmerSell} from "../../model/farmer-sell";
import {Farmer} from "../../model/farmer";
import {FarmerServiceService} from "../../service/farmer-service.service";

@Component({
  selector: 'app-farmer-sell',
  templateUrl: './farmer-sell.component.html',
  styleUrls: ['./farmer-sell.component.css']
})
export class FarmerSellComponent implements OnInit {
  farmerSell : FarmerSell =new FarmerSell();
  farmer : Farmer = new Farmer();
  farmerList : Farmer[] = [];
  constructor(private farmerService: FarmerServiceService) { }

  ngOnInit(): void {
    this.farmerService.getFarmerList().subscribe(data => {
      this.farmerList = data;
      console.log("Farmer Lists are : ");
      console.log(this.farmerList)
    })
  }
  callRegisterFarmer(){

  }
  callTransit(){

  }
  farmerSellForm(){
    console.log(this.farmerSell);
    this.farmerService.saveFarmerSell(this.farmerSell).subscribe(data =>{
      console.log(data);
    })
  }
}
