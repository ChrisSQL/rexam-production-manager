package com.rexam.binentry.model;

public class ProductionWeeklyReportModel {

	int id, HFIOpeningB64, HFIOpeningCDL, HFICreatedB64, HFICreatedCDL, HFIRecoverdB64, HFIRecoverCDL, HFIScrappedB64,
			HFIScrappedCDL;

	public ProductionWeeklyReportModel(int id, int hFIOpeningB64, int hFIOpeningCDL, int hFICreatedB64,
			int hFICreatedCDL, int hFIRecoverdB64, int hFIRecoverCDL, int hFIScrappedB64, int hFIScrappedCDL) {

		this.id = id;
		HFIOpeningB64 = hFIOpeningB64;
		HFIOpeningCDL = hFIOpeningCDL;
		HFICreatedB64 = hFICreatedB64;
		HFICreatedCDL = hFICreatedCDL;
		HFIRecoverdB64 = hFIRecoverdB64;
		HFIRecoverCDL = hFIRecoverCDL;
		HFIScrappedB64 = hFIScrappedB64;
		HFIScrappedCDL = hFIScrappedCDL;
	}

	public ProductionWeeklyReportModel() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHFIOpeningB64() {
		return HFIOpeningB64;
	}

	public void setHFIOpeningB64(int hFIOpeningB64) {
		HFIOpeningB64 = hFIOpeningB64;
	}

	public int getHFIOpeningCDL() {
		return HFIOpeningCDL;
	}

	public void setHFIOpeningCDL(int hFIOpeningCDL) {
		HFIOpeningCDL = hFIOpeningCDL;
	}

	public int getHFICreatedB64() {
		return HFICreatedB64;
	}

	public void setHFICreatedB64(int hFICreatedB64) {
		HFICreatedB64 = hFICreatedB64;
	}

	public int getHFICreatedCDL() {
		return HFICreatedCDL;
	}

	public void setHFICreatedCDL(int hFICreatedCDL) {
		HFICreatedCDL = hFICreatedCDL;
	}

	public int getHFIRecoverdB64() {
		return HFIRecoverdB64;
	}

	public void setHFIRecoverdB64(int hFIRecoverdB64) {
		HFIRecoverdB64 = hFIRecoverdB64;
	}

	public int getHFIRecoverCDL() {
		return HFIRecoverCDL;
	}

	public void setHFIRecoverCDL(int hFIRecoverCDL) {
		HFIRecoverCDL = hFIRecoverCDL;
	}

	public int getHFIScrappedB64() {
		return HFIScrappedB64;
	}

	public void setHFIScrappedB64(int hFIScrappedB64) {
		HFIScrappedB64 = hFIScrappedB64;
	}

	public int getHFIScrappedCDL() {
		return HFIScrappedCDL;
	}

	public void setHFIScrappedCDL(int hFIScrappedCDL) {
		HFIScrappedCDL = hFIScrappedCDL;
	}

}
