package com.csm.Bean;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FarmerSellBean {
	private int sellId;
	private int farmer;
	private double qty;
	private double ammount;
	private Date date;
}
