package com.rexam.binentry.model;

import java.util.Date;

public class LinerDefectsModel {
	
	int id;
	Date date;
	int M1Liner, M1Defects, M2Liner, M2Defects, M3Liner, M3Defects, M4Liner, M4Defects, TotalLined, TotalDefects, LinerSpoiledPercentage;
	
	public LinerDefectsModel(){
		
		
		
	}
	
	public LinerDefectsModel(int id, Date date, int m1Liner, int m1Defects, int m2Liner, int m2Defects, int m3Liner,
			int m3Defects, int m4Liner, int m4Defects, int totalLined, int totalDefects, int linerSpoiledPercentage) {
		this.id = id;
		this.date = date;
		M1Liner = m1Liner;
		M1Defects = m1Defects;
		M2Liner = m2Liner;
		M2Defects = m2Defects;
		M3Liner = m3Liner;
		M3Defects = m3Defects;
		M4Liner = m4Liner;
		M4Defects = m4Defects;
		TotalLined = totalLined;
		TotalDefects = totalDefects;
		LinerSpoiledPercentage = linerSpoiledPercentage;
	}

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

	public int getM1Liner() {
		return M1Liner;
	}

	public void setM1Liner(int m1Liner) {
		M1Liner = m1Liner;
	}

	public int getM1Defects() {
		return M1Defects;
	}

	public void setM1Defects(int m1Defects) {
		M1Defects = m1Defects;
	}

	public int getM2Liner() {
		return M2Liner;
	}

	public void setM2Liner(int m2Liner) {
		M2Liner = m2Liner;
	}

	public int getM2Defects() {
		return M2Defects;
	}

	public void setM2Defects(int m2Defects) {
		M2Defects = m2Defects;
	}

	public int getM3Liner() {
		return M3Liner;
	}

	public void setM3Liner(int m3Liner) {
		M3Liner = m3Liner;
	}

	public int getM3Defects() {
		return M3Defects;
	}

	public void setM3Defects(int m3Defects) {
		M3Defects = m3Defects;
	}

	public int getM4Liner() {
		return M4Liner;
	}

	public void setM4Liner(int m4Liner) {
		M4Liner = m4Liner;
	}

	public int getM4Defects() {
		return M4Defects;
	}

	public void setM4Defects(int m4Defects) {
		M4Defects = m4Defects;
	}

	public int getTotalLined() {
		return TotalLined;
	}

	public void setTotalLined(int totalLined) {
		TotalLined = totalLined;
	}

	public int getTotalDefects() {
		return TotalDefects;
	}

	public void setTotalDefects(int totalDefects) {
		TotalDefects = totalDefects;
	}

	public int getLinerSpoiledPercentage() {
		return LinerSpoiledPercentage;
	}

	public void setLinerSpoiledPercentage(int linerSpoiledPercentage) {
		LinerSpoiledPercentage = linerSpoiledPercentage;
	}
	
	
	
	
}
