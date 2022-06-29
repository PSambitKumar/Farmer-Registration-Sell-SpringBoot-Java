import { Component, OnInit } from '@angular/core';
import {Farmer} from "../../model/farmer";
import {FarmerServiceService} from "../../service/farmer-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-farmer-registration',
  templateUrl: './farmer-registration.component.html',
  styleUrls: ['./farmer-registration.component.css']
})
export class FarmerRegistrationComponent implements OnInit {

  farmer : Farmer = new Farmer();
  farmerList : Farmer[] = [];

  constructor(private farmerService : FarmerServiceService, private router : Router) { }

  ngOnInit(): void {
  }

  addFarmer(){}
  viewFarmer(){}

  farmerForm(){
    console.log(this.farmer);
    this.farmerService.saveFarmer(this.farmer).subscribe(data => {
      this.farmerList = data;
      console.log(this.farmerList);
    })
  }
  callFarmerSell(){
    this.router.navigate(["farmerSell"]);
  }
  callTransit(){
    this.router.navigate(["transitPass"]);
  }

}
