package com.rexam.binentry.dao;

import com.rexam.binentry.model.ProductionWeeklyReportModel;

public interface ProductionWeeklyReportDAO {
	
	public int ProductionWeeklyReportTotalOptime2And3ForMonth(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalOptime4ForMonth(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalOptime2And3And4ForMonth(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalAllW1And2And3sForMonth(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalAllW4sForMonth(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalAllWs(String monthIn, String yearIn);
	public int ProductionWeeklyReportB64LineSpoilage(String monthIn, String yearIn);
	public int ProductionWeeklyReportCdlLineSpoilage(String monthIn, String yearIn);
	public int ProductionWeeklyReportTotalLineSpoilage(String monthIn, String yearIn);
	public void ProductionWeeklyReportUpdate(ProductionWeeklyReportModel pwr);
	public int ProductionWeeklyReportQuery(int type);
}
