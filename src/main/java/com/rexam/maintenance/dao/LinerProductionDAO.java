package com.rexam.maintenance.dao;

import javax.swing.JPanel;

import com.rexam.maintenance.model.LinerProductionModel;

public interface LinerProductionDAO {
	
	public int linerProductionGetHighestID();

	public LinerProductionModel linerProductionReturnEntryByDate(LinerProductionModel lpIn);

	public LinerProductionModel linerProductionReturnEntryByID(int id);

	public boolean linerProductionEntryExists(LinerProductionModel lpIn);

	public void linerProductionInsert(LinerProductionModel lpIn);

	public void linerProductionUpdate(LinerProductionModel lpIn);

	public void linerProductionUpdateByDate(LinerProductionModel lpIn);

	public int[] linerProductionCalculateTotalsByMonth(String monthIn, String yearIn);

	public JPanel linerProductionSummaryTable(int in);

}
