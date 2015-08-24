package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.production.model.OpTimeModel;

public interface OpTimeDAO {

	public int OPTimeGetHighestID();
	public OpTimeModel OPTimeReturnEntryByDate(Date dateIn);
	public OpTimeModel OPTimeReturnEntryById(int idIn);
	public void OPTimeInsert(OpTimeModel ot);
	public void OPTimeUpdate(OpTimeModel ot);

	// public JPanel OPTimeSummaryTable(int reportType);
	// public JPanel OPTimeSummaryGroupTable(int reportType);
	
	public JPanel OPTimeSummaryCommentsTable();
	public JPanel OPTimeShellsByMonthTable();
	public boolean OptimeEntryExists(OpTimeModel ot);
	public JComboBox [] fillCombos(JComboBox [] oa);
	
	
}
