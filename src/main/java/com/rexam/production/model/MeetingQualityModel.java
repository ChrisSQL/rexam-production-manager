package com.rexam.production.model;

import java.util.Date;

public class MeetingQualityModel {
	
	int id, shellsMTD, hfiCreated, hfiRecoveredMTD, hfiScrappedMTD, endsInHFI;
	Date date;
    Double percentageChecksDoneDays, percentageChecksDoneNights;
    String customerComplaints, qualityIssuesPreviousDays, qualityIssuesToday, qualityEquipment, auditsDue;
	
    public MeetingQualityModel(int id, int shellsMTD, int hfiCreated, int hfiRecoveredMTD, int hfiScrappedMTD,
			int endsInHFI, Date date, Double percentageChecksDoneDays, Double percentageChecksDoneNights,
			String customerComplaints, String qualityIssuesPreviousDays, String qualityIssuesToday,
			String qualityEquipment, String auditsDue) {
    	
		this.id = id;
		this.shellsMTD = shellsMTD;
		this.hfiCreated = hfiCreated;
		this.hfiRecoveredMTD = hfiRecoveredMTD;
		this.hfiScrappedMTD = hfiScrappedMTD;
		this.endsInHFI = endsInHFI;
		this.date = date;
		this.percentageChecksDoneDays = percentageChecksDoneDays;
		this.percentageChecksDoneNights = percentageChecksDoneNights;
		this.customerComplaints = customerComplaints;
		this.qualityIssuesPreviousDays = qualityIssuesPreviousDays;
		this.qualityIssuesToday = qualityIssuesToday;
		this.qualityEquipment = qualityEquipment;
		this.auditsDue = auditsDue;
	}
    
    public MeetingQualityModel(){
    	
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShellsMTD() {
		return shellsMTD;
	}

	public void setShellsMTD(int shellsMTD) {
		this.shellsMTD = shellsMTD;
	}

	public int getHfiCreated() {
		return hfiCreated;
	}

	public void setHfiCreated(int hfiCreated) {
		this.hfiCreated = hfiCreated;
	}

	public int getHfiRecoveredMTD() {
		return hfiRecoveredMTD;
	}

	public void setHfiRecoveredMTD(int hfiRecoveredMTD) {
		this.hfiRecoveredMTD = hfiRecoveredMTD;
	}

	public int getHfiScrappedMTD() {
		return hfiScrappedMTD;
	}

	public void setHfiScrappedMTD(int hfiScrappedMTD) {
		this.hfiScrappedMTD = hfiScrappedMTD;
	}

	public int getEndsInHFI() {
		return endsInHFI;
	}

	public void setEndsInHFI(int endsInHFI) {
		this.endsInHFI = endsInHFI;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPercentageChecksDoneDays() {
		return percentageChecksDoneDays;
	}

	public void setPercentageChecksDoneDays(Double percentageChecksDoneDays) {
		this.percentageChecksDoneDays = percentageChecksDoneDays;
	}

	public Double getPercentageChecksDoneNights() {
		return percentageChecksDoneNights;
	}

	public void setPercentageChecksDoneNights(Double percentageChecksDoneNights) {
		this.percentageChecksDoneNights = percentageChecksDoneNights;
	}

	public String getCustomerComplaints() {
		return customerComplaints;
	}

	public void setCustomerComplaints(String customerComplaints) {
		this.customerComplaints = customerComplaints;
	}

	public String getQualityIssuesPreviousDays() {
		return qualityIssuesPreviousDays;
	}

	public void setQualityIssuesPreviousDays(String qualityIssuesPreviousDays) {
		this.qualityIssuesPreviousDays = qualityIssuesPreviousDays;
	}

	public String getQualityIssuesToday() {
		return qualityIssuesToday;
	}

	public void setQualityIssuesToday(String qualityIssuesToday) {
		this.qualityIssuesToday = qualityIssuesToday;
	}

	public String getQualityEquipment() {
		return qualityEquipment;
	}

	public void setQualityEquipment(String qualityEquipment) {
		this.qualityEquipment = qualityEquipment;
	}

	public String getAuditsDue() {
		return auditsDue;
	}

	public void setAuditsDue(String auditsDue) {
		this.auditsDue = auditsDue;
	}
    
    
	
    
    
}