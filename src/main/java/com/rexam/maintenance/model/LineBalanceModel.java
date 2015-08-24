package com.rexam.maintenance.model;

import java.util.Date;

public class LineBalanceModel {
	
	int ID, Mod123Unlined, Mod4Unlined, Mod123Lined, Mod4Lined;
	Date date;
	
	public LineBalanceModel(int iD, int mod123Unlined, int mod4Unlined, int mod123Lined, int mod4Lined, Date date) {

		ID = iD;
		Mod123Unlined = mod123Unlined;
		Mod4Unlined = mod4Unlined;
		Mod123Lined = mod123Lined;
		Mod4Lined = mod4Lined;
		this.date = date;
	}
	
	public LineBalanceModel(){
		
		
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getMod123Unlined() {
		return Mod123Unlined;
	}

	public void setMod123Unlined(int mod123Unlined) {
		Mod123Unlined = mod123Unlined;
	}

	public int getMod4Unlined() {
		return Mod4Unlined;
	}

	public void setMod4Unlined(int mod4Unlined) {
		Mod4Unlined = mod4Unlined;
	}

	public int getMod123Lined() {
		return Mod123Lined;
	}

	public void setMod123Lined(int mod123Lined) {
		Mod123Lined = mod123Lined;
	}

	public int getMod4Lined() {
		return Mod4Lined;
	}

	public void setMod4Lined(int mod4Lined) {
		Mod4Lined = mod4Lined;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	

}
