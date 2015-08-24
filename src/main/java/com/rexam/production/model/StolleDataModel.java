package com.rexam.production.model;

import java.util.Date;

public class StolleDataModel {
	
	int id, shift, pressSpeed, stolleProduction, packedEnds, hfiCreated, hfiRecovered, hfiScrapped, sacobaDowntime;
	Date date;
	String crew, press, operator, packer, qcInspector, comment;
	
	public StolleDataModel(int id, int shift, int pressSpeed, int stolleProduction, int packedEnds, int hfiCreated,
			int hfiRecovered, int hfiScrapped, int sacobaDowntime, Date date, String crew, String press,
			String operator, String packer, String qcInspector, String comment) {
		this.id = id;
		this.shift = shift;
		this.pressSpeed = pressSpeed;
		this.stolleProduction = stolleProduction;
		this.packedEnds = packedEnds;
		this.hfiCreated = hfiCreated;
		this.hfiRecovered = hfiRecovered;
		this.hfiScrapped = hfiScrapped;
		this.sacobaDowntime = sacobaDowntime;
		this.date = date;
		this.crew = crew;
		this.press = press;
		this.operator = operator;
		this.packer = packer;
		this.qcInspector = qcInspector;
		this.comment = comment;
	}
	
	public StolleDataModel(){
		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public int getPressSpeed() {
		return pressSpeed;
	}

	public void setPressSpeed(int pressSpeed) {
		this.pressSpeed = pressSpeed;
	}

	public int getStolleProduction() {
		return stolleProduction;
	}

	public void setStolleProduction(int stolleProduction) {
		this.stolleProduction = stolleProduction;
	}

	public int getPackedEnds() {
		return packedEnds;
	}

	public void setPackedEnds(int packedEnds) {
		this.packedEnds = packedEnds;
	}

	public int getHfiCreated() {
		return hfiCreated;
	}

	public void setHfiCreated(int hfiCreated) {
		this.hfiCreated = hfiCreated;
	}

	public int getHfiRecovered() {
		return hfiRecovered;
	}

	public void setHfiRecovered(int hfiRecovered) {
		this.hfiRecovered = hfiRecovered;
	}

	public int getHfiScrapped() {
		return hfiScrapped;
	}

	public void setHfiScrapped(int hfiScrapped) {
		this.hfiScrapped = hfiScrapped;
	}

	public int getSacobaDowntime() {
		return sacobaDowntime;
	}

	public void setSacobaDowntime(int sacobaDowntime) {
		this.sacobaDowntime = sacobaDowntime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getPacker() {
		return packer;
	}

	public void setPacker(String packer) {
		this.packer = packer;
	}

	public String getQcInspector() {
		return qcInspector;
	}

	public void setQcInspector(String qcInspector) {
		this.qcInspector = qcInspector;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	

}
