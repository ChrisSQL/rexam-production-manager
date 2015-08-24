package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.production.model.LinerUsageModel;

public interface LinerUsageDAO {
	
	 public int LinerUsageGetHighestID();
	 public LinerUsageModel LinerUsageReturnEntryByDate(Date dateIn);
	 public LinerUsageModel LinerUsageReturnEntryByID(int id);
	 public void LinerUsageInsert(LinerUsageModel lu);
	 public void LinerUsageUpdate(LinerUsageModel lu);
	 public JPanel LinerUsageSummaryTable(int reportType);
	 public JComboBox [] fillCombos(JComboBox [] la);

}
