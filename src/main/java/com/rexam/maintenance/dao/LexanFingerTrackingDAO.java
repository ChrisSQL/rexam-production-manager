package com.rexam.maintenance.dao;

import javax.swing.JPanel;

import com.rexam.maintenance.model.LexanFingerTrackingModel;

public interface LexanFingerTrackingDAO {
	
	public int MaintenanceLexanFingerTrackingGetHighestID();
	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingReturnEntryByID(int id);
	public void MaintenanceLexanFingerTrackingInsert(LexanFingerTrackingModel lft);
	public void MaintenanceLexanFingerTrackingUpdate(LexanFingerTrackingModel lft);
	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingGetNextEntryById(int id);
	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingGetPreviousEntryById(int id);
	public JPanel MaintenanceLexanFingerTrackingSummaryTable(int in);
	
}
