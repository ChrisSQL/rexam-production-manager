/**
 * 
 */
package com.rexam.maintenance.model;

import java.util.Date;

/**
 * @author Chris Maher - chrismaher.wit@gmail.com
 *
 */
public class ShellPressProductionModel {
	
	private int id, sp01, optime2, optime3, fmi41, fmi42, formatec04;
	private Date date; 
	
	public ShellPressProductionModel(int id, int sp01, int optime2, int optime3, int fmi41, int fmi42, int formatec04,
			Date date) {

		this.id = id;
		this.sp01 = sp01;
		this.optime2 = optime2;
		this.optime3 = optime3;
		this.fmi41 = fmi41;
		this.fmi42 = fmi42;
		this.formatec04 = formatec04;
		this.date = date;
	}


	public ShellPressProductionModel(){
		
			
	}
	

	public int getSp01() {
		return sp01;
	}

	public void setSp01(int sp01) {
		this.sp01 = sp01;
	}

	public int getOptime2() {
		return optime2;
	}

	public void setOptime2(int optime2) {
		this.optime2 = optime2;
	}

	public int getOptime3() {
		return optime3;
	}

	public void setOptime3(int optime3) {
		this.optime3 = optime3;
	}

	public int getFmi41() {
		return fmi41;
	}

	public void setFmi41(int fmi41) {
		this.fmi41 = fmi41;
	}

	public int getFmi42() {
		return fmi42;
	}

	public void setFmi42(int fmi42) {
		this.fmi42 = fmi42;
	}

	public int getFormatec04() {
		return formatec04;
	}

	public void setFormatec04(int formatec04) {
		this.formatec04 = formatec04;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		
		
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "ShellPressProduction \n[id=" + id + ", \nsp01=" + sp01 + ", \noptime2=" + optime2 + ", \noptime3="
				+ optime3 + ", \nfmi41=" + fmi41 + ", \nfmi42=" + fmi42 + ", \nformatec04=" + formatec04 + ", \ndate="
				+ date + "]";
	}

	
	
	
	

}
