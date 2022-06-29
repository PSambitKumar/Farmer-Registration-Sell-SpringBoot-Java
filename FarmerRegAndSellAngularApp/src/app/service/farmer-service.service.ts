import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Farmer} from "../model/farmer";
import {Observable} from "rxjs";
import {FarmerSell} from "../model/farmer-sell";

@Injectable({
  providedIn: 'root'
})
export class FarmerServiceService {

  constructor(private httpClient : HttpClient) { }
  private baseURL  = "http://localhost:8088";

  saveFarmer(farmer : Farmer) : Observable<Farmer[]>{
    return this.httpClient.post<Farmer[]>(`${this.baseURL + "/saveFarmer"}`, farmer);
  }
  getFarmerList() : Observable<Farmer[]>{
    return this.httpClient.get<Farmer[]>(`${this.baseURL + "/getFarmerList"}`);
  }
  saveFarmerSell(farmerSell : FarmerSell) : Observable<FarmerSell[]>{
    return this.httpClient.post<FarmerSell[]>(`${this.baseURL + "/saveFarmerSell"}`, farmerSell);
  }
}
