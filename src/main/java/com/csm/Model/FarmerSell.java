package com.csm.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "farmer_sell_tbl")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FarmerSell {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellId;
	@ManyToOne
	@JoinColumn(name = "farmerCode", referencedColumnName = "farmerCode")
	private Farmer farmer;
	private double qty;
	private double ammount;
	private Date date;
}
