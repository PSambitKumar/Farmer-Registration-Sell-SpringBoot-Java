package com.csm.Repository;

import com.csm.Model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
	@Query("SELECT MAX (id) FROM Farmer")
	int maxSlno();

	Farmer getFarmerById(int Id);
}
