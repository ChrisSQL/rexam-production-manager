package com.rexam.maintenance.dao;

import java.util.Date;

import com.rexam.maintenance.model.LineBalanceModel;

public interface LineBalanceDAO {
	
	public LineBalanceModel MaintenanceLineBalanceReturnEntryByDate(Date dateIn);
	public LineBalanceModel MaintenanceLineBalanceReturnEntryByID(int id);
	public void MaintenanceLineBalanceInsert(LineBalanceModel lb);
	public void MaintenanceLineBalanceUpdate(LineBalanceModel lb);
	public int [] MaintenanceLineBalanceCalculateTotalsByMonth(String monthIn, String yearIn);

}
