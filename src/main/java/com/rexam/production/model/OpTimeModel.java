package com.rexam.production.model;

import java.util.Date;

public class OpTimeModel {
	
	private int id, shift, optimeNumber, pressSpeed, production;
	private Date date;
	private String crew, operator, shellType, comment;
	
	public OpTimeModel(int id, int shift, int optimeNumber, int pressSpeed, int production, Date date, String crew,
			String operator, String shellType, String comment) {
		
		this.id = id;
		this.shift = shift;
		this.optimeNumber = optimeNumber;
		this.pressSpeed = pressSpeed;
		this.production = production;
		this.date = date;
		this.crew = crew;
		this.operator = operator;
		this.shellType = shellType;
		this.comment = comment;
	} 
	
	public OpTimeModel(){
		
		
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

	public int getOptimeNumber() {
		return optimeNumber;
	}

	public void setOptimeNumber(int optimeNumber) {
		this.optimeNumber = optimeNumber;
	}

	public int getPressSpeed() {
		return pressSpeed;
	}

	public void setPressSpeed(int pressSpeed) {
		this.pressSpeed = pressSpeed;
	}

	public int getProduction() {
		return production;
	}

	public void setProduction(int production) {
		this.production = production;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getShellType() {
		return shellType;
	}

	public void setShellType(String shellType) {
		this.shellType = shellType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	

}
