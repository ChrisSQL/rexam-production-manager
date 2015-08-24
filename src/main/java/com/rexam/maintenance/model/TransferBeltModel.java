package com.rexam.maintenance.model;

import java.util.Date;

public class TransferBeltModel {
	
	private int ID;
	private String machineCode;
	private int endsTargeted, actualEnds, plusOrMinus;
	private Date dateFitted, setUpCheckDate, beltsRemoved1, beltsRemoved2, beltsRemoved3, beltsRemoved4, beltsRemoved5, beltsRemoved6, beltsRemoved7, beltsRemoved8, beltsRemoved9, beltsRemoved10, beltsRemoved11, beltsRemoved12;
	public TransferBeltModel(int iD, String machineCode, int endsTargeted, int actualEnds, int plusOrMinus,
			Date dateFitted, Date setUpCheckDate, Date beltsRemoved1, Date beltsRemoved2, Date beltsRemoved3,
			Date beltsRemoved4, Date beltsRemoved5, Date beltsRemoved6, Date beltsRemoved7, Date beltsRemoved8,
			Date beltsRemoved9, Date beltsRemoved10, Date beltsRemoved11, Date beltsRemoved12) {

		ID = iD;
		this.machineCode = machineCode;
		this.endsTargeted = endsTargeted;
		this.actualEnds = actualEnds;
		this.plusOrMinus = plusOrMinus;
		this.dateFitted = dateFitted;
		this.setUpCheckDate = setUpCheckDate;
		this.beltsRemoved1 = beltsRemoved1;
		this.beltsRemoved2 = beltsRemoved2;
		this.beltsRemoved3 = beltsRemoved3;
		this.beltsRemoved4 = beltsRemoved4;
		this.beltsRemoved5 = beltsRemoved5;
		this.beltsRemoved6 = beltsRemoved6;
		this.beltsRemoved7 = beltsRemoved7;
		this.beltsRemoved8 = beltsRemoved8;
		this.beltsRemoved9 = beltsRemoved9;
		this.beltsRemoved10 = beltsRemoved10;
		this.beltsRemoved11 = beltsRemoved11;
		this.beltsRemoved12 = beltsRemoved12;
	}
	
	public TransferBeltModel(){
		
		
		
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public int getEndsTargeted() {
		return endsTargeted;
	}
	public void setEndsTargeted(int endsTargeted) {
		this.endsTargeted = endsTargeted;
	}
	public int getActualEnds() {
		return actualEnds;
	}
	public void setActualEnds(int actualEnds) {
		this.actualEnds = actualEnds;
	}
	public int getPlusOrMinus() {
		return plusOrMinus;
	}
	public void setPlusOrMinus(int plusOrMinus) {
		this.plusOrMinus = plusOrMinus;
	}
	public Date getDateFitted() {
		return dateFitted;
	}
	public void setDateFitted(Date dateFitted) {
		this.dateFitted = dateFitted;
	}
	public Date getSetUpCheckDate() {
		return setUpCheckDate;
	}
	public void setSetUpCheckDate(Date setUpCheckDate) {
		this.setUpCheckDate = setUpCheckDate;
	}
	public Date getBeltsRemoved1() {
		return beltsRemoved1;
	}
	public void setBeltsRemoved1(Date beltsRemoved1) {
		this.beltsRemoved1 = beltsRemoved1;
	}
	public Date getBeltsRemoved2() {
		return beltsRemoved2;
	}
	public void setBeltsRemoved2(Date beltsRemoved2) {
		this.beltsRemoved2 = beltsRemoved2;
	}
	public Date getBeltsRemoved3() {
		return beltsRemoved3;
	}
	public void setBeltsRemoved3(Date beltsRemoved3) {
		this.beltsRemoved3 = beltsRemoved3;
	}
	public Date getBeltsRemoved4() {
		return beltsRemoved4;
	}
	public void setBeltsRemoved4(Date beltsRemoved4) {
		this.beltsRemoved4 = beltsRemoved4;
	}
	public Date getBeltsRemoved5() {
		return beltsRemoved5;
	}
	public void setBeltsRemoved5(Date beltsRemoved5) {
		this.beltsRemoved5 = beltsRemoved5;
	}
	public Date getBeltsRemoved6() {
		return beltsRemoved6;
	}
	public void setBeltsRemoved6(Date beltsRemoved6) {
		this.beltsRemoved6 = beltsRemoved6;
	}
	public Date getBeltsRemoved7() {
		return beltsRemoved7;
	}
	public void setBeltsRemoved7(Date beltsRemoved7) {
		this.beltsRemoved7 = beltsRemoved7;
	}
	public Date getBeltsRemoved8() {
		return beltsRemoved8;
	}
	public void setBeltsRemoved8(Date beltsRemoved8) {
		this.beltsRemoved8 = beltsRemoved8;
	}
	public Date getBeltsRemoved9() {
		return beltsRemoved9;
	}
	public void setBeltsRemoved9(Date beltsRemoved9) {
		this.beltsRemoved9 = beltsRemoved9;
	}
	public Date getBeltsRemoved10() {
		return beltsRemoved10;
	}
	public void setBeltsRemoved10(Date beltsRemoved10) {
		this.beltsRemoved10 = beltsRemoved10;
	}
	public Date getBeltsRemoved11() {
		return beltsRemoved11;
	}
	public void setBeltsRemoved11(Date beltsRemoved11) {
		this.beltsRemoved11 = beltsRemoved11;
	}
	public Date getBeltsRemoved12() {
		return beltsRemoved12;
	}
	public void setBeltsRemoved12(Date beltsRemoved12) {
		this.beltsRemoved12 = beltsRemoved12;
	}
	
	
	

	
	
}
