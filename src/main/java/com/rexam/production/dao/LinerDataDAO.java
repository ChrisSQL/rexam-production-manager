package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.production.model.LinerDataModel;

public interface LinerDataDAO {
	
	public int LinerGetHighestID();
	public LinerDataModel LinerReturnEntryByDate(Date dateIn);
	public LinerDataModel LinerReturnEntryById(int idIn);
	public void LinerInsert(LinerDataModel lm);
	public void LinerUpdate(LinerDataModel lm);
	public JPanel LinerSummaryTable(int reportType);
	public JPanel LinerUsageSummaryTable(int reportType);
	public boolean LinerEntryExists(LinerDataModel lm);
	public JComboBox [] fillCombos(JComboBox [] la);
	
}
