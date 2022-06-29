import { Component, OnInit } from '@angular/core';
import {Farmer} from "../../model/farmer";
import {FarmerServiceService} from "../../service/farmer-service.service";
import {Router} from "@angular/router";
import * as $ from 'jquery';
import {combineLatestAll} from "rxjs";
import {error} from "jquery";

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
  checkIFSCCode (ifscCode : any){
    var ifscRegX = "^[A-Z]{4}0[A-Z0-9]{6}$";
    if (ifscCode.length != 11 && !ifscCode.match(ifscRegX)){
      $('#ifscAlert').text("Invalid IFSC Code").css("color", "red");
      $('#ifscCode').css("border", "2px solid red");
    }else {
      console.log(ifscCode);
      $('#ifscAlert').text("Looks Good").css('color', 'green');
      $('#ifscCode').css('border', '2px solid green');
      this.farmerService.getBankDetailsUsingIFSC(ifscCode).subscribe(data =>{
        console.log(data);
        $('#branchName').val(data.branch);
        $('#branchAlert').text("Looks Good").css('color', 'green');
        $('#branchName').css('border', '2px solid green');
        $('#bankName').val(data.bank);
        $('#bankAlert').text("Looks Good").css('color', 'green');
        $('#bankName').css('border', '2px solid green');
      })
    }
  }

  farmerForm(){
    console.log(this.farmer);
    // console.log(JSON.stringify(this.farmer.aadhar).length == 12); Sometime length not working so need to Convert into String
    if (this.farmer.name == null || this.farmer.name == ""){
      $('#nameAlert').text("Name Must Not Be Empty!").css('color', 'red');
      $('#name').css('border', '2px solid red');
    }else if (this.farmer.mobile == null || this.farmer.mobile == "" && this.farmer.mobile.length != 10){
      $('#nameAlert').text("Looks Good").css('color', 'green');
      $('#name').css('border', '2px solid green');
      $('#mobileAlert').text("Invalid Mobile Number!").css('color', 'red');
      $('#mobile').css('border', '2px solid red');
    }else if (this.farmer.aadhar == null || this.farmer.aadhar == "" && this.farmer.aadhar.length != 12){
      $('#nameAlert').text("Looks Good").css('color', 'green');
      $('#name').css('border', '2px solid green');
      $('#mobileAlert').text("Looks Good").css('color', 'green');
      $('#mobile').css('border', '2px solid green');
      $('#aadharAlert').text("Invalid Aadhar Number!").css('color', 'red');
      $('#aadhar').css('border', '2px solid red');
    }else if (JSON.stringify(this.farmer.aadhar).length == 12){
      this.farmerService.validateAadhar(this.farmer.aadhar).subscribe(data => {
        if (data.status == "Invalid"){
          $('#aadharAlert').text("Invalid Aadhar").css('color', 'red');
          $('#aadhar').css('border', '2px solid red');
        }else if (this.farmer.accountNumber == null || this.farmer.accountNumber == ""){
          $('#nameAlert').text("Looks Good").css('color', 'green');
          $('#name').css('border', '2px solid green');
          $('#mobileAlert').text("Looks Good").css('color', 'green');
          $('#mobile').css('border', '2px solid green');
          $('#aadharAlert').text("Looks Good").css('color', 'green');
          $('#aadhar').css('border', '2px solid green');
          $('#accountNumberAlert').text("Name Must Not Be Empty!").css('color', 'red');
          $('#accountNumber').css('border', '2px solid red');
        }else if (this.farmer.ifscCode == null || this.farmer.ifscCode == ""){
          $('#nameAlert').text("Looks Good").css('color', 'green');
          $('#name').css('border', '2px solid green');
          $('#mobileAlert').text("Looks Good").css('color', 'green');
          $('#mobile').css('border', '2px solid green');
          $('#aadharAlert').text("Looks Good").css('color', 'green');
          $('#aadhar').css('border', '2px solid green');
          $('#accountNumberAlert').text("Looks Good").css('color', 'green');
          $('#accountNumber').css('border', '2px solid green');
          $('#ifscAlert').text("IFSC Code Must Not Empty").css('color', 'red');
          $('#ifscCode').css('border', '2px solid red');
        }else {
          this.farmerService.saveFarmer(this.farmer).subscribe(data => {
            this.farmerList = data;
            console.log(this.farmerList);
            this.router.navigate(["farmerSell"]);
          })
        }
      })
    }
  }
  callFarmerSell(){
    this.router.navigate(["farmerSell"]);
  }
  callTransit(){
    this.router.navigate(["transitPass"]);
  }

}
