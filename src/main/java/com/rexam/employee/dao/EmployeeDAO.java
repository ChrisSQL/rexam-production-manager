package com.rexam.employee.dao;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.employee.model.EmployeeModel;

public interface EmployeeDAO {
	
	 public int EmployeeGetHighestID();
	 public boolean EmployeeExists(EmployeeModel em);
	 public int EmployeeGetLowestID();
	 public EmployeeModel EmployeeReturnEntryByName(String nameIn);
	 public EmployeeModel EmployeeReturnEntryById(int idIn);
	 public int EmployeeReturnIdByName(String nameIn);
	 public void EmployeeInsert(EmployeeModel em);
	 public void EmployeeUpdate(EmployeeModel em);
	 public void EmployeeDelete(EmployeeModel em);
	 public int EmployeeGetRowCount();
	 public Object[] EmployeeGetArraySorted();
	 public JPanel EmployeeSummaryTable();
	 public JComboBox[] fillCombos(JComboBox[] em);
	 public JComboBox[] fillDeleteCombos(JComboBox[] em);
}
