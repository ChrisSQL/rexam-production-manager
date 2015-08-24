package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JPanel;

import com.rexam.production.model.LSSPMActivityModel;

public interface LSSPMActivityDAO {
	
	public LSSPMActivityModel LSSPMEntryqueryDate(Date dateIn);
	public LSSPMActivityModel LSSPMEntryqueryId(int idIn);
	public void LSSPMInsert(LSSPMActivityModel ls);
	public void LSSPMUpdate(LSSPMActivityModel ls);
	public JPanel LSSPMSummaryTable(int reportType);

}
