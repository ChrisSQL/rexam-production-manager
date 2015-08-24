package com.rexam.maintenance.dao;

import java.util.Date;

import javax.swing.JTable;

import com.rexam.maintenance.model.ShellPressProductionModel;

public interface ShellPressProductionDAO {
	
	public int shellPressProductionGetHighestID();
	public ShellPressProductionModel shellPressProductionReturnEntryByDate(Date dateIn);
	public ShellPressProductionModel shellPressProductionReturnEntryByID(int id);
	public boolean shellPressProductionEntryExists(Date dateIn);
	public void shellPressProductionInsert(ShellPressProductionModel pm);
	public void shellPressProductionUpdate(ShellPressProductionModel pm);
	public void shellPressProductionUpdateByDate(ShellPressProductionModel pm);
	public JTable generateExcelJTable(String type, String query);
	public int[] shellPressProductionCalculateTotalsByMonth(String monthIn, String yearIn);
	public void generateExcelFile(String type, String query);
}
