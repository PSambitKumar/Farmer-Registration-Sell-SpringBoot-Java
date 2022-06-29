package com.csm.Controller;

import com.csm.Model.Farmer;
import com.csm.Model.FarmerSell;
import com.csm.Model.TransitPass;
import com.csm.Repository.FarmerRepository;
import com.csm.Repository.FarmerSellRepository;
import com.csm.Repository.TransitPassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class FarmerController {

	@Autowired
	private FarmerRepository farmerRepository;
	@Autowired
	private FarmerSellRepository farmerSellRepository;
	@Autowired
	private TransitPassRepository transitPassRepository;

	@GetMapping("/saveFarmer")
	public String saveFarmer(){
		Farmer farmer = new Farmer();
		farmer.setId(1);
		farmer.setFarmerCode("FARMER001");
		farmer.setName("Sambit");
		farmer.setAadhar("220778011303");
		farmer.setAccountNumber("20296602554");
		farmer.setBankName("State Bank Of India");
		farmer.setIfscCode("SBIN0003942");
		farmer.setMobile("7008095918");

		farmerRepository.save(farmer);

		FarmerSell farmerSell = new FarmerSell();
		farmerSell.setFarmer(farmer);
		farmerSell.setSellId(1);
		farmerSell.setAmmount(20000.50);
		farmerSell.setQty(1.6);
		farmerSell.setDate(new Date());

		System.out.println("Farmer Sell : " + farmerSell);
		farmerSellRepository.save(farmerSell);

		TransitPass transitPass = new TransitPass();
		transitPass.setTransId(1);
		transitPass.setFarmer(farmer);
		transitPass.setFarmerSell(farmerSell);
		transitPass.setDate(new Date());
		transitPass.setTransQty(1.0);
		transitPass.setTransStatus(1);
		transitPass.setVehicleNo("OD070987");

		System.out.println("Transit Pass : " + transitPass);
		transitPassRepository.save(transitPass);
		return null;
	}
}
