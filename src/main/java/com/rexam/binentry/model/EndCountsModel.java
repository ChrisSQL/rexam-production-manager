package com.rexam.binentry.model;

import java.util.Date;

public class EndCountsModel {
	
	int id, w11, w12, w21, w22, w31, w32, w33, w41, w42, w43, w44;
	Date date;
	
	public EndCountsModel(int id, int w11, int w12, int w21, int w22, int w31, int w32, int w33, int w41, int w42,
			int w43, int w44, Date date) {
		super();
		this.id = id;
		this.w11 = w11;
		this.w12 = w12;
		this.w21 = w21;
		this.w22 = w22;
		this.w31 = w31;
		this.w32 = w32;
		this.w33 = w33;
		this.w41 = w41;
		this.w42 = w42;
		this.w43 = w43;
		this.w44 = w44;
		this.date = date;
	}
	
	public EndCountsModel(){
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getW11() {
		return w11;
	}

	public void setW11(int w11) {
		this.w11 = w11;
	}

	public int getW12() {
		return w12;
	}

	public void setW12(int w12) {
		this.w12 = w12;
	}

	public int getW21() {
		return w21;
	}

	public void setW21(int w21) {
		this.w21 = w21;
	}

	public int getW22() {
		return w22;
	}

	public void setW22(int w22) {
		this.w22 = w22;
	}

	public int getW31() {
		return w31;
	}

	public void setW31(int w31) {
		this.w31 = w31;
	}

	public int getW32() {
		return w32;
	}

	public void setW32(int w32) {
		this.w32 = w32;
	}

	public int getW33() {
		return w33;
	}

	public void setW33(int w33) {
		this.w33 = w33;
	}

	public int getW41() {
		return w41;
	}

	public void setW41(int w41) {
		this.w41 = w41;
	}

	public int getW42() {
		return w42;
	}

	public void setW42(int w42) {
		this.w42 = w42;
	}

	public int getW43() {
		return w43;
	}

	public void setW43(int w43) {
		this.w43 = w43;
	}

	public int getW44() {
		return w44;
	}

	public void setW44(int w44) {
		this.w44 = w44;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	

}
