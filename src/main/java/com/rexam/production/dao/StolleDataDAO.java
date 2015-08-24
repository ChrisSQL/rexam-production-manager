package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.production.model.StolleDataModel;

public interface StolleDataDAO {

	public StolleDataModel StolleReturnEntryByDate(Date dateIn);
	public StolleDataModel StolleReturnEntryByID(int idIn);
	public void StolleInsert(StolleDataModel sm);
	public void StolleUpdate(StolleDataModel sm);
	public JPanel StolleSummaryTable(int reportType);
	public JPanel StolleSummaryGroupTable(int reportType);	
	public JPanel StolleSummaryCommentsTable();	
	public JPanel StolleEndsByMonthTable();	
	public boolean StolleExists(StolleDataModel sm);
	public JComboBox [] fillCombos(JComboBox [] la);

}
