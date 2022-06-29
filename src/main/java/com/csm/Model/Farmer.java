package com.csm.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "farmer_tbl")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Farmer implements Serializable{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String farmerCode;
	private String mobile;
	private String aadhar;
	private String bankName;
	private String ifscCode;
	private String accountNumber;
}
