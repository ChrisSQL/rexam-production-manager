package com.rexam.maintenance.model;

import java.util.Date;

public class LinerMaintenanceModel {

	Date lastMaintenanceDate1, lastMaintenanceDate2, lastMaintenanceDate3, maintenanceDueDate1, maintenanceDueDate2,
			maintenanceDueDate3;

	int ID, production1, production2, production3, targetProduction1, targetProduction2, targetProduction3;

	String machineCode, machineName;

	public LinerMaintenanceModel(Date lastMaintenanceDate1, Date lastMaintenanceDate2, Date lastMaintenanceDate3,
			Date maintenanceDueDate1, Date maintenanceDueDate2, Date maintenanceDueDate3, int iD, int production1,
			int production2, int production3, int targetProduction1, int targetProduction2, int targetProduction3,
			String machineCode, String machineName) {

		this.lastMaintenanceDate1 = lastMaintenanceDate1;
		this.lastMaintenanceDate2 = lastMaintenanceDate2;
		this.lastMaintenanceDate3 = lastMaintenanceDate3;
		this.maintenanceDueDate1 = maintenanceDueDate1;
		this.maintenanceDueDate2 = maintenanceDueDate2;
		this.maintenanceDueDate3 = maintenanceDueDate3;
		ID = iD;
		this.production1 = production1;
		this.production2 = production2;
		this.production3 = production3;
		this.targetProduction1 = targetProduction1;
		this.targetProduction2 = targetProduction2;
		this.targetProduction3 = targetProduction3;
		this.machineCode = machineCode;
		this.machineName = machineName;
	}
	
	public LinerMaintenanceModel(){
		
		
		
	}

	public Date getLastMaintenanceDate1() {
		return lastMaintenanceDate1;
	}

	public void setLastMaintenanceDate1(Date lastMaintenanceDate1) {
		this.lastMaintenanceDate1 = lastMaintenanceDate1;
	}

	public Date getLastMaintenanceDate2() {
		return lastMaintenanceDate2;
	}

	public void setLastMaintenanceDate2(Date lastMaintenanceDate2) {
		this.lastMaintenanceDate2 = lastMaintenanceDate2;
	}

	public Date getLastMaintenanceDate3() {
		return lastMaintenanceDate3;
	}

	public void setLastMaintenanceDate3(Date lastMaintenanceDate3) {
		this.lastMaintenanceDate3 = lastMaintenanceDate3;
	}

	public Date getMaintenanceDueDate1() {
		return maintenanceDueDate1;
	}

	public void setMaintenanceDueDate1(Date maintenanceDueDate1) {
		this.maintenanceDueDate1 = maintenanceDueDate1;
	}

	public Date getMaintenanceDueDate2() {
		return maintenanceDueDate2;
	}

	public void setMaintenanceDueDate2(Date maintenanceDueDate2) {
		this.maintenanceDueDate2 = maintenanceDueDate2;
	}

	public Date getMaintenanceDueDate3() {
		return maintenanceDueDate3;
	}

	public void setMaintenanceDueDate3(Date maintenanceDueDate3) {
		this.maintenanceDueDate3 = maintenanceDueDate3;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getProduction1() {
		return production1;
	}

	public void setProduction1(int production1) {
		this.production1 = production1;
	}

	public int getProduction2() {
		return production2;
	}

	public void setProduction2(int production2) {
		this.production2 = production2;
	}

	public int getProduction3() {
		return production3;
	}

	public void setProduction3(int production3) {
		this.production3 = production3;
	}

	public int getTargetProduction1() {
		return targetProduction1;
	}

	public void setTargetProduction1(int targetProduction1) {
		this.targetProduction1 = targetProduction1;
	}

	public int getTargetProduction2() {
		return targetProduction2;
	}

	public void setTargetProduction2(int targetProduction2) {
		this.targetProduction2 = targetProduction2;
	}

	public int getTargetProduction3() {
		return targetProduction3;
	}

	public void setTargetProduction3(int targetProduction3) {
		this.targetProduction3 = targetProduction3;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	
	

}
