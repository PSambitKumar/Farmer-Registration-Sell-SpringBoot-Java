package com.csm.Repository;

import com.csm.Model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {
}
