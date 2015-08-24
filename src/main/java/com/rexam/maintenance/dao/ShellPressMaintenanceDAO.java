package com.rexam.maintenance.dao;

import com.rexam.maintenance.model.ShellPressMaintenanceModel;

public interface ShellPressMaintenanceDAO {
	
	public void shellPressMaintenanceUpdate(ShellPressMaintenanceModel sm);
	public ShellPressMaintenanceModel shellPressMaintenanceReturnEntryByID(int idIn);
	
	
}
