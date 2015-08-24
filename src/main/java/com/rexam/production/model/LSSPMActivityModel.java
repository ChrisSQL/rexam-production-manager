package com.rexam.production.model;

import java.util.Date;

public class LSSPMActivityModel {
	
	int id;
	Date date;
	String comment;
	
	public LSSPMActivityModel(int id, Date date, String comment) {
		super();
		this.id = id;
		this.date = date;
		this.comment = comment;
	}
	
	public LSSPMActivityModel(){}

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
