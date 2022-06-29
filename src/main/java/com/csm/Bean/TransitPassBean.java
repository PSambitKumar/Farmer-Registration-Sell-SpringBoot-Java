package com.csm.Bean;

import java.util.Date;

public class TransitPassBean {
	private int farmer;
	private int transQty;
	private int transStatus;
	private String vehicleNo;
	private Date date;

	public int getFarmer() {
		return farmer;
	}

	public void setFarmer(int farmer) {
		this.farmer = farmer;
	}

	public int getTransQty() {
		return transQty;
	}

	public void setTransQty(int transQty) {
		this.transQty = transQty;
	}

	public int getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(int transStatus) {
		this.transStatus = transStatus;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "TransitPassBean{" +
			   "farmer=" + farmer +
			   ", transQty=" + transQty +
			   ", transStatus=" + transStatus +
			   ", vehicleNo='" + vehicleNo + '\'' +
			   ", date=" + date +
			   '}';
	}
}
