package com.csm.Controller;

import com.csm.Bean.FarmerSellBean;
import com.csm.Model.Farmer;
import com.csm.Model.FarmerSell;
import com.csm.Repository.FarmerRepository;
import com.csm.Repository.FarmerSellRepository;
import com.csm.Repository.TransitPassRepository;
import com.csm.Utils.FarmerCodeGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class FarmerController {

	@ResponseBody
	@PostMapping(value = "/saveFarmer")
	public List<Farmer> saveFarmer(@RequestBody Farmer farmer){
		List<Farmer> farmerList = null;
		try {
			int maxId;
			try {
				maxId = farmerRepository.maxSlno();
			}catch (Exception e){
				maxId = 0;
			}
			String userCode = FarmerCodeGeneration.FarmerCodeGenreationMethod(maxId);
			farmer.setFarmerCode(userCode);
			farmerRepository.save(farmer);
			farmerList = farmerRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return farmerList;
	}

	@ResponseBody
	@GetMapping(value = "/getFarmerList")
	public List<Farmer> getFarmerList(){
		System.out.println("Inside Get Farmer List.");
		List<Farmer> farmerList = null;
		try {
			farmerList = farmerRepository.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		return farmerList;
	}

	@ResponseBody
	@PostMapping(value = "/saveFarmerSell")
	public List<FarmerSell> saveFarmerSell(@RequestBody FarmerSellBean farmerSellBean){
		FarmerSell farmerSell = new FarmerSell();
		Farmer farmer = farmerRepository.getById(farmerSellBean.getFarmer());
		System.out.println("Farmer Data : " + farmer);
		farmerSell.setFarmer(farmer);
		farmerSell.setQty(farmerSellBean.getQty());
		farmerSell.setDate(farmerSellBean.getDate());
		farmerSell.setAmmount(farmerSellBean.getAmmount());
		System.out.println("Farmer Sell Data : " + farmerSell);
		farmerSellRepository.save(farmerSell);
		List<FarmerSell> farmerSellList = farmerSellRepository.findAll();
		System.out.println("Farmer Sell Lists : " + farmerSellList);
		return null;
	}




















	@Autowired
	private FarmerRepository farmerRepository;
	@Autowired
	private FarmerSellRepository farmerSellRepository;
	@Autowired
	private TransitPassRepository transitPassRepository;
//
//	@GetMapping("/saveFarmer")
//	public String saveFarmer(){
//		Farmer farmer = new Farmer();
//		farmer.setId(1);
//		farmer.setFarmerCode("FARMER001");
//		farmer.setName("Sambit");
//		farmer.setAadhar("220778011303");
//		farmer.setAccountNumber("20296602554");
//		farmer.setBankName("State Bank Of India");
//		farmer.setIfscCode("SBIN0003942");
//		farmer.setMobile("7008095918");
//
//		farmerRepository.save(farmer);
//
//		FarmerSell farmerSell = new FarmerSell();
//		farmerSell.setFarmer(farmer);
//		farmerSell.setSellId(1);
//		farmerSell.setAmmount(20000.50);
//		farmerSell.setQty(1.6);
//		farmerSell.setDate(new Date());
//
//		System.out.println("Farmer Sell : " + farmerSell);
//		farmerSellRepository.save(farmerSell);
//
//		TransitPass transitPass = new TransitPass();
//		transitPass.setTransId(1);
//		transitPass.setFarmer(farmer);
//		transitPass.setFarmerSell(farmerSell);
//		transitPass.setDate(new Date());
//		transitPass.setTransQty(1.0);
//		transitPass.setTransStatus(1);
//		transitPass.setVehicleNo("OD070987");
//
//		System.out.println("Transit Pass : " + transitPass);
//		transitPassRepository.save(transitPass);
//		return null;
//	}
}
