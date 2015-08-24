package com.rexam.production.model;

import java.util.Date;

public class LinerUsageModel {
	
	int id, gun1, gun2, gun3, gun4, gun5, gun6, gun7, gun8, quantityUsed;
	Date date;
	String lineNumber, crew, leadHand, reason, partNumber, comment;
	public LinerUsageModel(int id, int gun1, int gun2, int gun3, int gun4, int gun5, int gun6, int gun7, int gun8,
			Date date, String lineNumber, String crew, String leadHand, String reason, int quantityUsed,
			String partNumber, String comment) {
		
		this.id = id;
		this.gun1 = gun1;
		this.gun2 = gun2;
		this.gun3 = gun3;
		this.gun4 = gun4;
		this.gun5 = gun5;
		this.gun6 = gun6;
		this.gun7 = gun7;
		this.gun8 = gun8;
		this.date = date;
		this.lineNumber = lineNumber;
		this.crew = crew;
		this.leadHand = leadHand;
		this.reason = reason;
		this.quantityUsed = quantityUsed;
		this.partNumber = partNumber;
		this.comment = comment;
	}
	
	public LinerUsageModel(){
		
				
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGun1() {
		return gun1;
	}

	public void setGun1(int gun1) {
		this.gun1 = gun1;
	}

	public int getGun2() {
		return gun2;
	}

	public void setGun2(int gun2) {
		this.gun2 = gun2;
	}

	public int getGun3() {
		return gun3;
	}

	public void setGun3(int gun3) {
		this.gun3 = gun3;
	}

	public int getGun4() {
		return gun4;
	}

	public void setGun4(int gun4) {
		this.gun4 = gun4;
	}

	public int getGun5() {
		return gun5;
	}

	public void setGun5(int gun5) {
		this.gun5 = gun5;
	}

	public int getGun6() {
		return gun6;
	}

	public void setGun6(int gun6) {
		this.gun6 = gun6;
	}

	public int getGun7() {
		return gun7;
	}

	public void setGun7(int gun7) {
		this.gun7 = gun7;
	}

	public int getGun8() {
		return gun8;
	}

	public void setGun8(int gun8) {
		this.gun8 = gun8;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getLeadHand() {
		return leadHand;
	}

	public void setLeadHand(String leadHand) {
		this.leadHand = leadHand;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getQuantityUsed() {
		return quantityUsed;
	}

	public void setQuantityUsed(int quantityUsed) {
		this.quantityUsed = quantityUsed;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	

}
