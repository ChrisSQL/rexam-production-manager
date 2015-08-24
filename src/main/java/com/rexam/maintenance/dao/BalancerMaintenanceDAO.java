package com.rexam.maintenance.dao;

import javax.swing.JPanel;

import com.rexam.maintenance.model.BalancerMaintenanceModel;

public interface BalancerMaintenanceDAO {
	
	public JPanel MaintenanceBalancerProductionSummaryTable(int in);
	public BalancerMaintenanceModel balancerMaintenanceReturnEntryByID(int idIn);
	public void balancerMaintenanceUpdate(BalancerMaintenanceModel sm);

}
