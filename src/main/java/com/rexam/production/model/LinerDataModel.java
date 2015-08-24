package com.rexam.production.model;

import java.util.Date;

public class LinerDataModel {
	
	int id, shift;
	Date date;
	String crew, module, operator, liner, comments; 
	double linerInfeed, shellsSpoiled, calculatedSpoilage;
	
	public LinerDataModel(int id, int shift, Date date, String crew, String module, String operator, String liner,
			String comments, double linerInfeed, double shellsSpoiled, double calculatedSpoilage) {
		this.id = id;
		this.shift = shift;
		this.date = date;
		this.crew = crew;
		this.module = module;
		this.operator = operator;
		this.liner = liner;
		this.comments = comments;
		this.linerInfeed = linerInfeed;
		this.shellsSpoiled = shellsSpoiled;
		this.calculatedSpoilage = calculatedSpoilage;
	}
	
	public LinerDataModel(){
		
		
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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLiner() {
		return liner;
	}

	public void setLiner(String liner) {
		this.liner = liner;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getLinerInfeed() {
		return linerInfeed;
	}

	public void setLinerInfeed(double linerInfeed) {
		this.linerInfeed = linerInfeed;
	}

	public double getShellsSpoiled() {
		return shellsSpoiled;
	}

	public void setShellsSpoiled(double shellsSpoiled) {
		this.shellsSpoiled = shellsSpoiled;
	}

	public double getCalculatedSpoilage() {
		return calculatedSpoilage;
	}

	public void setCalculatedSpoilage(double calculatedSpoilage) {
		this.calculatedSpoilage = calculatedSpoilage;
	}
	
	

}
