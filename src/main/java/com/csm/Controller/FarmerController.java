package com.csm.Controller;

import com.csm.Bean.BankDetailsBean;
import com.csm.Bean.FarmerSellBean;
import com.csm.Bean.Status;
import com.csm.Bean.TransitPassBean;
import com.csm.Model.Farmer;
import com.csm.Model.FarmerSell;
import com.csm.Model.TransitPass;
import com.csm.Repository.FarmerRepository;
import com.csm.Repository.FarmerSellRepository;
import com.csm.Repository.TransitPassRepository;
import com.csm.Utils.AadharValidation;
import com.csm.Utils.FarmerCodeGeneration;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FarmerController {

	@Autowired
	private FarmerRepository farmerRepository;
	@Autowired
	private FarmerSellRepository farmerSellRepository;
	@Autowired
	private TransitPassRepository transitPassRepository;

	@PostMapping(value = "/saveFarmer")
	public List<Farmer> saveFarmer(@RequestBody Farmer farmer){
		List<Farmer> farmerList;
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

	@GetMapping(value = "/getFarmerList")
	public List<Farmer> getFarmerList(){
		List<Farmer> farmerList = null;
		try {
			farmerList = farmerRepository.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		return farmerList;
	}

	@PostMapping(value = "/saveFarmerSell")
	public List<FarmerSell> saveFarmerSell(@RequestBody FarmerSellBean farmerSellBean){
		List<FarmerSell> farmerSellList;
		try {
			Farmer farmer = farmerRepository.getFarmerById(farmerSellBean.getFarmer());

			FarmerSell farmerSell = new FarmerSell();
			farmerSell.setFarmer(farmer);
			farmerSell.setAmmount(farmerSellBean.getAmmount());
			farmerSell.setQty(farmerSellBean.getQty());
			farmerSell.setDate(farmerSellBean.getDate());

			farmerSellRepository.save(farmerSell);
			farmerSellList = farmerSellRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return farmerSellList;
	}

	@PostMapping(value = "/saveTransitPass")
	public List<TransitPass> saveTransitPass(@RequestBody TransitPassBean transitPassBean){
		List<TransitPass> transitPassList;
		try {
			Farmer farmer = farmerRepository.getFarmerById(transitPassBean.getFarmer());
			FarmerSell farmerSell = farmerSellRepository.getFarmerSellByDateAndFarmer(transitPassBean.getDate(), farmer);
			TransitPass transitPass = new TransitPass();
			transitPass.setFarmer(farmer);
			transitPass.setVehicleNo(transitPassBean.getVehicleNo());
			transitPass.setTransStatus(transitPassBean.getTransStatus());
			transitPass.setTransQty(transitPassBean.getTransQty());
			transitPass.setDate(transitPassBean.getDate());
			transitPass.setFarmerSell(farmerSell);
			System.out.println("Transit Pass Data : " + transitPass);
			transitPassRepository.save(transitPass);
			transitPassList = transitPassRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return transitPassList;
	}

	@GetMapping(value = "/validateAadhar/{aadhar}")
	public ResponseEntity<Status> validateAadhar(@PathVariable("aadhar")String aadhar, Status status){
		if (AadharValidation.validateAadhar(aadhar))
			status.setStatus("Valid");
		else
			status.setStatus("Invalid");
		System.out.println(status);
		return ResponseEntity.ok(status);
	}

	@GetMapping(value = "/getBankUsingIFSCCode/{ifscCode}")
	public ResponseEntity<BankDetailsBean> getBankUsingIFSCCode(@PathVariable("ifscCode")String ifscCode){
		System.out.println("Inside Get Bank Details Using IFSC Code----------------------->>");
		System.out.println("IFSC Code : " + ifscCode);
		Gson gson = new Gson();
		RestTemplate restTemplate = new RestTemplate();
		String bankDetails = restTemplate.getForObject("https://ifsc.razorpay.com/"+ifscCode, String.class);
		System.out.println(bankDetails);

		BankDetailsBean bankDetailsBean = gson.fromJson(bankDetails, BankDetailsBean.class);
		System.out.println("Bank Details are : " + bankDetailsBean);

		return ResponseEntity.ok(bankDetailsBean);
	}
}
