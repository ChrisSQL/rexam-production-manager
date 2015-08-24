package com.rexam.maintenance.model;

import java.util.Date;

public class ShellPressMaintenanceModel {

	Date lastMaintenanceDate1, lastMaintenanceDate2, lastMaintenanceDate3, lastMaintenanceDate4, lastMaintenanceDate5,
			lastMaintenanceDate6, lastMaintenanceDate7, maintenanceDueDate1, maintenanceDueDate2, maintenanceDueDate3,
			maintenanceDueDate4, maintenanceDueDate5, maintenanceDueDate6, maintenanceDueDate7;

	int ID, production1, production2, production3, production4, production5, production6, production7,
			targetProduction1, targetProduction2, targetProduction3, targetProduction4, targetProduction5,
			targetProduction6, targetProduction7;

	String machineCode, machineName;

	public ShellPressMaintenanceModel(Date lastMaintenanceDate1, Date lastMaintenanceDate2, Date lastMaintenanceDate3,
			Date lastMaintenanceDate4, Date lastMaintenanceDate5, Date lastMaintenanceDate6, Date lastMaintenanceDate7,
			Date maintenanceDueDate1, Date maintenanceDueDate2, Date maintenanceDueDate3, Date maintenanceDueDate4,
			Date maintenanceDueDate5, Date maintenanceDueDate6, Date maintenanceDueDate7, int id, int production1,
			int production2, int production3, int production4, int production5, int production6, int production7,
			int targetProduction1, int targetProduction2, int targetProduction3, int targetProduction4,
			int targetProduction5, int targetProduction6, int targetProduction7, String machineCode,
			String machineName) {

		this.lastMaintenanceDate1 = lastMaintenanceDate1;
		this.lastMaintenanceDate2 = lastMaintenanceDate2;
		this.lastMaintenanceDate3 = lastMaintenanceDate3;
		this.lastMaintenanceDate4 = lastMaintenanceDate4;
		this.lastMaintenanceDate5 = lastMaintenanceDate5;
		this.lastMaintenanceDate6 = lastMaintenanceDate6;
		this.lastMaintenanceDate7 = lastMaintenanceDate7;
		this.maintenanceDueDate1 = maintenanceDueDate1;
		this.maintenanceDueDate2 = maintenanceDueDate2;
		this.maintenanceDueDate3 = maintenanceDueDate3;
		this.maintenanceDueDate4 = maintenanceDueDate4;
		this.maintenanceDueDate5 = maintenanceDueDate5;
		this.maintenanceDueDate6 = maintenanceDueDate6;
		this.maintenanceDueDate7 = maintenanceDueDate7;
		this.ID = id;
		this.production1 = production1;
		this.production2 = production2;
		this.production3 = production3;
		this.production4 = production4;
		this.production5 = production5;
		this.production6 = production6;
		this.production7 = production7;
		this.targetProduction1 = targetProduction1;
		this.targetProduction2 = targetProduction2;
		this.targetProduction3 = targetProduction3;
		this.targetProduction4 = targetProduction4;
		this.targetProduction5 = targetProduction5;
		this.targetProduction6 = targetProduction6;
		this.targetProduction7 = targetProduction7;
		this.machineCode = machineCode;
		this.machineName = machineName;
	}

	public ShellPressMaintenanceModel() {

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

	public Date getLastMaintenanceDate4() {
		return lastMaintenanceDate4;
	}

	public void setLastMaintenanceDate4(Date lastMaintenanceDate4) {
		this.lastMaintenanceDate4 = lastMaintenanceDate4;
	}

	public Date getLastMaintenanceDate5() {
		return lastMaintenanceDate5;
	}

	public void setLastMaintenanceDate5(Date lastMaintenanceDate5) {
		this.lastMaintenanceDate5 = lastMaintenanceDate5;
	}

	public Date getLastMaintenanceDate6() {
		return lastMaintenanceDate6;
	}

	public void setLastMaintenanceDate6(Date lastMaintenanceDate6) {
		this.lastMaintenanceDate6 = lastMaintenanceDate6;
	}

	public Date getLastMaintenanceDate7() {
		return lastMaintenanceDate7;
	}

	public void setLastMaintenanceDate7(Date lastMaintenanceDate7) {
		this.lastMaintenanceDate7 = lastMaintenanceDate7;
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

	public Date getMaintenanceDueDate4() {
		return maintenanceDueDate4;
	}

	public void setMaintenanceDueDate4(Date maintenanceDueDate4) {
		this.maintenanceDueDate4 = maintenanceDueDate4;
	}

	public Date getMaintenanceDueDate5() {
		return maintenanceDueDate5;
	}

	public void setMaintenanceDueDate5(Date maintenanceDueDate5) {
		this.maintenanceDueDate5 = maintenanceDueDate5;
	}

	public Date getMaintenanceDueDate6() {
		return maintenanceDueDate6;
	}

	public void setMaintenanceDueDate6(Date maintenanceDueDate6) {
		this.maintenanceDueDate6 = maintenanceDueDate6;
	}

	public Date getMaintenanceDueDate7() {
		return maintenanceDueDate7;
	}

	public void setMaintenanceDueDate7(Date maintenanceDueDate7) {
		this.maintenanceDueDate7 = maintenanceDueDate7;
	}

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
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

	public int getProduction4() {
		return production4;
	}

	public void setProduction4(int production4) {
		this.production4 = production4;
	}

	public int getProduction5() {
		return production5;
	}

	public void setProduction5(int production5) {
		this.production5 = production5;
	}

	public int getProduction6() {
		return production6;
	}

	public void setProduction6(int production6) {
		this.production6 = production6;
	}

	public int getProduction7() {
		return production7;
	}

	public void setProduction7(int production7) {
		this.production7 = production7;
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

	public int getTargetProduction4() {
		return targetProduction4;
	}

	public void setTargetProduction4(int targetProduction4) {
		this.targetProduction4 = targetProduction4;
	}

	public int getTargetProduction5() {
		return targetProduction5;
	}

	public void setTargetProduction5(int targetProduction5) {
		this.targetProduction5 = targetProduction5;
	}

	public int getTargetProduction6() {
		return targetProduction6;
	}

	public void setTargetProduction6(int targetProduction6) {
		this.targetProduction6 = targetProduction6;
	}

	public int getTargetProduction7() {
		return targetProduction7;
	}

	public void setTargetProduction7(int targetProduction7) {
		this.targetProduction7 = targetProduction7;
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
