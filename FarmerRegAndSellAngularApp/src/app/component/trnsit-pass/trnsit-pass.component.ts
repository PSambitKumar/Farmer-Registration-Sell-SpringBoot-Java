import { Component, OnInit } from '@angular/core';
import {Farmer} from "../../model/farmer";
import {FarmerServiceService} from "../../service/farmer-service.service";
import {TransitPass} from "../../model/transit-pass";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trnsit-pass',
  templateUrl: './trnsit-pass.component.html',
  styleUrls: ['./trnsit-pass.component.css']
})
export class TrnsitPassComponent implements OnInit {
  farmerList : Farmer[] = [];
  transitPass : TransitPass = new TransitPass();

  constructor(private farmerService : FarmerServiceService, private router : Router) { }

  ngOnInit(): void {
    this.farmerService.getFarmerList().subscribe(data => {
      this.farmerList = data;
      console.log(this.farmerList)
    })
  }

  callRegisterFarmer(){
    this.router.navigate(["farmerRegistration"])
  }
  callFarmerSell(){
    this.router.navigate(["farmerSell"])
  }
  transitPassForm(){
    console.log(this.transitPass);
    this.farmerService.saveTransitPass(this.transitPass).subscribe(data =>{
      console.log(data);
      this.router.navigate(["farmerRegistration"]);
    })
  }

}
