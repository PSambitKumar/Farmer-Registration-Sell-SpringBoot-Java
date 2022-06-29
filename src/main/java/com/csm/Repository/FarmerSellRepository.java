package com.csm.Repository;

import com.csm.Model.Farmer;
import com.csm.Model.FarmerSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface FarmerSellRepository extends JpaRepository<FarmerSell, Integer> {
	@Query("SELECT MAX (sellId) FROM FarmerSell ")
	int getMaxId();

	FarmerSell getFarmerSellByDateAndFarmer(Date date, Farmer farmer);
}
