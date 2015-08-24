package com.rexam.employee.model;

public class EmployeeModel {
	
	int id ;
	String employeeId, name, address, jobsTitle, crew, phoneExtension, phoneNumber, mobileNumber;
	
	public EmployeeModel(int id, String employeeId, String name, String address, String jobsTitle, String crew,
			String phoneExtension, String phoneNumber, String mobileNumber) {
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.address = address;
		this.jobsTitle = jobsTitle;
		this.crew = crew;
		this.phoneExtension = phoneExtension;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
	}
	
	public EmployeeModel(){
		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobsTitle() {
		return jobsTitle;
	}

	public void setJobsTitle(String jobsTitle) {
		this.jobsTitle = jobsTitle;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getPhoneExtension() {
		return phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
;