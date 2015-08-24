package com.rexam.maintenance.dao;

import java.util.Date;

import com.rexam.maintenance.model.LinerMaintenanceModel;

public interface LinerMaintenanceDAO {
	
	public int MaintenanceLinerMaintenanceGetHighestID();
	public LinerMaintenanceModel MaintenanceLinerMaintenanceReturnEntryByDate(Date dateIn);
	public LinerMaintenanceModel MaintenanceLinerMaintenanceReturnEntryByID(int id);
	public void MaintenanceLinerMaintenanceUpdate(LinerMaintenanceModel lm);

}
