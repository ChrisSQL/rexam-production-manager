package com.rexam.maintenance.dao;

import java.util.Date;

import javax.swing.JPanel;

import com.rexam.maintenance.model.LinerSpoilageModel;

public interface LinerSpoilageDAO {
	
	public LinerSpoilageModel MaintenanceLinerSpoilageReturnEntryByDate(Date dateIn);
	public LinerSpoilageModel MaintenanceLinerSpoilageReturnEntryByID(int id);
	public void MaintenanceLinerSpoilageInsert(LinerSpoilageModel ls);
	public void MaintenanceLinerSpoilageUpdate(LinerSpoilageModel ls);
	public Object[] MaintenanceLinerSpoilageCalculateTotalsByMonth(String monthIn, String yearIn);
	public JPanel MaintenanceLinerSpoilageSummaryTable(int in);
	public String[] MaintenanceLinerSpoilageSevenDaysAverages();

}
