package com.rexam.production.model;

import java.util.Date;

public class ProductionMeetingModel {

	private int ID, NonPetUnlinedSilverShells, NonPetlinedSilverShells, NonPetlinedSilverShellsTotal, NonPetUnlinedGoldRx219,
			NonPetlinedGoldRx219, NonPetlinedGoldRx219Total, Mod4FUnlinedSilverShells, Mod4FlinedSilverShells,
			Mod4FlinedSilverShellsTotal, NonPetUnlinedSilver215, NonPetlinedSilver215, NonPetlinedSilver215Total,
			A03HiFiShells, A04HiFiShells, A04HiFiShellsTotal, A03HiFiShellsRX219, A04HiFiShellsRX219,
			A04HiFiShellsRX219Total, A13HiFiShells, A14HiFiShells, A14HiFiShellsTotal, A07HiFiShells, A08HiFiShells,
			A08HiFiShellsTotal, DaysRemaining, PackedEnds, MonthlyLineLoad, Remaining, DailyAverage, DaysGone, LineLoad,
			PackedVsLineLoad;
			
	private Date date, MeetingDate;
	private String SafetyIssues, ProductionAction, HygieneManagementOfChange, EngineeringActions;
	private Double SpoiledPercentage;
	
	public ProductionMeetingModel(int iD, int nonPetUnlinedSilverShells, int nonPetlinedSilverShells,
			int nonPetlinedSilverShellsTotal, int nonPetUnlinedGoldRx219, int nonPetlinedGoldRx219,
			int nonPetlinedGoldRx219Total, int mod4fUnlinedSilverShells, int mod4FlinedSilverShells,
			int mod4FlinedSilverShellsTotal, int nonPetUnlinedSilver215, int nonPetlinedSilver215,
			int nonPetlinedSilver215Total, int a03HiFiShells, int a04HiFiShells, int a04HiFiShellsTotal,
			int a03HiFiShellsRX219, int a04HiFiShellsRX219, int a04HiFiShellsRX219Total, int a13HiFiShells,
			int a14HiFiShells, int a14HiFiShellsTotal, int a07HiFiShells, int a08HiFiShells, int a08HiFiShellsTotal,
			int daysRemaining, int packedEnds, int monthlyLineLoad, int remaining, int dailyAverage, int daysGone,
			int lineLoad, int packedVsLineLoad, Date date, Date meetingDate, String safetyIssues,
			String productionAction, String hygieneManagementOfChange, String engineeringActions,
			Double spoiledPercentage) {
		ID = iD;
		NonPetUnlinedSilverShells = nonPetUnlinedSilverShells;
		NonPetlinedSilverShells = nonPetlinedSilverShells;
		NonPetlinedSilverShellsTotal = nonPetlinedSilverShellsTotal;
		NonPetUnlinedGoldRx219 = nonPetUnlinedGoldRx219;
		NonPetlinedGoldRx219 = nonPetlinedGoldRx219;
		NonPetlinedGoldRx219Total = nonPetlinedGoldRx219Total;
		Mod4FUnlinedSilverShells = mod4fUnlinedSilverShells;
		Mod4FlinedSilverShells = mod4FlinedSilverShells;
		Mod4FlinedSilverShellsTotal = mod4FlinedSilverShellsTotal;
		NonPetUnlinedSilver215 = nonPetUnlinedSilver215;
		NonPetlinedSilver215 = nonPetlinedSilver215;
		NonPetlinedSilver215Total = nonPetlinedSilver215Total;
		A03HiFiShells = a03HiFiShells;
		A04HiFiShells = a04HiFiShells;
		A04HiFiShellsTotal = a04HiFiShellsTotal;
		A03HiFiShellsRX219 = a03HiFiShellsRX219;
		A04HiFiShellsRX219 = a04HiFiShellsRX219;
		A04HiFiShellsRX219Total = a04HiFiShellsRX219Total;
		A13HiFiShells = a13HiFiShells;
		A14HiFiShells = a14HiFiShells;
		A14HiFiShellsTotal = a14HiFiShellsTotal;
		A07HiFiShells = a07HiFiShells;
		A08HiFiShells = a08HiFiShells;
		A08HiFiShellsTotal = a08HiFiShellsTotal;
		DaysRemaining = daysRemaining;
		PackedEnds = packedEnds;
		MonthlyLineLoad = monthlyLineLoad;
		Remaining = remaining;
		DailyAverage = dailyAverage;
		DaysGone = daysGone;
		LineLoad = lineLoad;
		PackedVsLineLoad = packedVsLineLoad;
		this.date = date;
		MeetingDate = meetingDate;
		SafetyIssues = safetyIssues;
		ProductionAction = productionAction;
		HygieneManagementOfChange = hygieneManagementOfChange;
		EngineeringActions = engineeringActions;
		SpoiledPercentage = spoiledPercentage;
	}

	public ProductionMeetingModel(){}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getNonPetUnlinedSilverShells() {
		return NonPetUnlinedSilverShells;
	}

	public void setNonPetUnlinedSilverShells(int nonPetUnlinedSilverShells) {
		NonPetUnlinedSilverShells = nonPetUnlinedSilverShells;
	}

	public int getNonPetlinedSilverShells() {
		return NonPetlinedSilverShells;
	}

	public void setNonPetlinedSilverShells(int nonPetlinedSilverShells) {
		NonPetlinedSilverShells = nonPetlinedSilverShells;
	}

	public int getNonPetlinedSilverShellsTotal() {
		return NonPetlinedSilverShellsTotal;
	}

	public void setNonPetlinedSilverShellsTotal(int nonPetlinedSilverShellsTotal) {
		NonPetlinedSilverShellsTotal = nonPetlinedSilverShellsTotal;
	}

	public int getNonPetUnlinedGoldRx219() {
		return NonPetUnlinedGoldRx219;
	}

	public void setNonPetUnlinedGoldRx219(int nonPetUnlinedGoldRx219) {
		NonPetUnlinedGoldRx219 = nonPetUnlinedGoldRx219;
	}

	public int getNonPetlinedGoldRx219() {
		return NonPetlinedGoldRx219;
	}

	public void setNonPetlinedGoldRx219(int nonPetlinedGoldRx219) {
		NonPetlinedGoldRx219 = nonPetlinedGoldRx219;
	}

	public int getNonPetlinedGoldRx219Total() {
		return NonPetlinedGoldRx219Total;
	}

	public void setNonPetlinedGoldRx219Total(int nonPetlinedGoldRx219Total) {
		NonPetlinedGoldRx219Total = nonPetlinedGoldRx219Total;
	}

	public int getMod4FUnlinedSilverShells() {
		return Mod4FUnlinedSilverShells;
	}

	public void setMod4FUnlinedSilverShells(int mod4fUnlinedSilverShells) {
		Mod4FUnlinedSilverShells = mod4fUnlinedSilverShells;
	}

	public int getMod4FlinedSilverShells() {
		return Mod4FlinedSilverShells;
	}

	public void setMod4FlinedSilverShells(int mod4FlinedSilverShells) {
		Mod4FlinedSilverShells = mod4FlinedSilverShells;
	}

	public int getMod4FlinedSilverShellsTotal() {
		return Mod4FlinedSilverShellsTotal;
	}

	public void setMod4FlinedSilverShellsTotal(int mod4FlinedSilverShellsTotal) {
		Mod4FlinedSilverShellsTotal = mod4FlinedSilverShellsTotal;
	}

	public int getNonPetUnlinedSilver215() {
		return NonPetUnlinedSilver215;
	}

	public void setNonPetUnlinedSilver215(int nonPetUnlinedSilver215) {
		NonPetUnlinedSilver215 = nonPetUnlinedSilver215;
	}

	public int getNonPetlinedSilver215() {
		return NonPetlinedSilver215;
	}

	public void setNonPetlinedSilver215(int nonPetlinedSilver215) {
		NonPetlinedSilver215 = nonPetlinedSilver215;
	}

	public int getNonPetlinedSilver215Total() {
		return NonPetlinedSilver215Total;
	}

	public void setNonPetlinedSilver215Total(int nonPetlinedSilver215Total) {
		NonPetlinedSilver215Total = nonPetlinedSilver215Total;
	}

	public int getA03HiFiShells() {
		return A03HiFiShells;
	}

	public void setA03HiFiShells(int a03HiFiShells) {
		A03HiFiShells = a03HiFiShells;
	}

	public int getA04HiFiShells() {
		return A04HiFiShells;
	}

	public void setA04HiFiShells(int a04HiFiShells) {
		A04HiFiShells = a04HiFiShells;
	}

	public int getA04HiFiShellsTotal() {
		return A04HiFiShellsTotal;
	}

	public void setA04HiFiShellsTotal(int a04HiFiShellsTotal) {
		A04HiFiShellsTotal = a04HiFiShellsTotal;
	}

	public int getA03HiFiShellsRX219() {
		return A03HiFiShellsRX219;
	}

	public void setA03HiFiShellsRX219(int a03HiFiShellsRX219) {
		A03HiFiShellsRX219 = a03HiFiShellsRX219;
	}

	public int getA04HiFiShellsRX219() {
		return A04HiFiShellsRX219;
	}

	public void setA04HiFiShellsRX219(int a04HiFiShellsRX219) {
		A04HiFiShellsRX219 = a04HiFiShellsRX219;
	}

	public int getA04HiFiShellsRX219Total() {
		return A04HiFiShellsRX219Total;
	}

	public void setA04HiFiShellsRX219Total(int a04HiFiShellsRX219Total) {
		A04HiFiShellsRX219Total = a04HiFiShellsRX219Total;
	}

	public int getA13HiFiShells() {
		return A13HiFiShells;
	}

	public void setA13HiFiShells(int a13HiFiShells) {
		A13HiFiShells = a13HiFiShells;
	}

	public int getA14HiFiShells() {
		return A14HiFiShells;
	}

	public void setA14HiFiShells(int a14HiFiShells) {
		A14HiFiShells = a14HiFiShells;
	}

	public int getA14HiFiShellsTotal() {
		return A14HiFiShellsTotal;
	}

	public void setA14HiFiShellsTotal(int a14HiFiShellsTotal) {
		A14HiFiShellsTotal = a14HiFiShellsTotal;
	}

	public int getA07HiFiShells() {
		return A07HiFiShells;
	}

	public void setA07HiFiShells(int a07HiFiShells) {
		A07HiFiShells = a07HiFiShells;
	}

	public int getA08HiFiShells() {
		return A08HiFiShells;
	}

	public void setA08HiFiShells(int a08HiFiShells) {
		A08HiFiShells = a08HiFiShells;
	}

	public int getA08HiFiShellsTotal() {
		return A08HiFiShellsTotal;
	}

	public void setA08HiFiShellsTotal(int a08HiFiShellsTotal) {
		A08HiFiShellsTotal = a08HiFiShellsTotal;
	}

	public int getDaysRemaining() {
		return DaysRemaining;
	}

	public void setDaysRemaining(int daysRemaining) {
		DaysRemaining = daysRemaining;
	}

	public int getPackedEnds() {
		return PackedEnds;
	}

	public void setPackedEnds(int packedEnds) {
		PackedEnds = packedEnds;
	}

	public int getMonthlyLineLoad() {
		return MonthlyLineLoad;
	}

	public void setMonthlyLineLoad(int monthlyLineLoad) {
		MonthlyLineLoad = monthlyLineLoad;
	}

	public int getRemaining() {
		return Remaining;
	}

	public void setRemaining(int remaining) {
		Remaining = remaining;
	}

	public int getDailyAverage() {
		return DailyAverage;
	}

	public void setDailyAverage(int dailyAverage) {
		DailyAverage = dailyAverage;
	}

	public int getDaysGone() {
		return DaysGone;
	}

	public void setDaysGone(int daysGone) {
		DaysGone = daysGone;
	}

	public int getLineLoad() {
		return LineLoad;
	}

	public void setLineLoad(int lineLoad) {
		LineLoad = lineLoad;
	}

	public int getPackedVsLineLoad() {
		return PackedVsLineLoad;
	}

	public void setPackedVsLineLoad(int packedVsLineLoad) {
		PackedVsLineLoad = packedVsLineLoad;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getMeetingDate() {
		return MeetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		MeetingDate = meetingDate;
	}

	public String getSafetyIssues() {
		return SafetyIssues;
	}

	public void setSafetyIssues(String safetyIssues) {
		SafetyIssues = safetyIssues;
	}

	public String getProductionAction() {
		return ProductionAction;
	}

	public void setProductionAction(String productionAction) {
		ProductionAction = productionAction;
	}

	public String getHygieneManagementOfChange() {
		return HygieneManagementOfChange;
	}

	public void setHygieneManagementOfChange(String hygieneManagementOfChange) {
		HygieneManagementOfChange = hygieneManagementOfChange;
	}

	public String getEngineeringActions() {
		return EngineeringActions;
	}

	public void setEngineeringActions(String engineeringActions) {
		EngineeringActions = engineeringActions;
	}

	public Double getSpoiledPercentage() {
		return SpoiledPercentage;
	}

	public void setSpoiledPercentage(Double spoiledPercentage) {
		SpoiledPercentage = spoiledPercentage;
	}
	
	
	
}