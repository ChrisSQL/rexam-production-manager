package com.rexam.par.dao;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.rexam.par.model.ParModel;

public interface ParDAO {
	
	public ParModel ParDatabaseReturnEntryByDate(Date dateIn);
	public ParModel ParDatabaseReturnEntryByForm(int id);
	public void ParDatabaseInsert(ParModel pm);
	public void ParDatabaseUpdate(ParModel pm);
	public JPanel ParEntrySummaryTable();
	public JTable PARReturnJTable(String type, String query);
	public JComboBox[] fillCombos(JComboBox[] la);
	public int ParDatabaseGetHighestForm();

}
