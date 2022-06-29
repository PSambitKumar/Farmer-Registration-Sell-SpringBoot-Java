package com.csm.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "transit_pass_tbl")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransitPass {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transId;
	@ManyToOne
	@JoinColumn(name = "sellId", referencedColumnName = "sellId")
	private FarmerSell farmerSell;
	@ManyToOne
	@JoinColumn(name = "farmerCode", referencedColumnName = "farmerCode")
	private Farmer farmer;
	private double transQty;
	private int transStatus;
	private Date date;
	private String vehicleNo;
}
