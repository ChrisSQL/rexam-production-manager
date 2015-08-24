package com.rexam.binentry.dao;

import java.util.Date;

import javax.swing.JPanel;

import com.rexam.binentry.model.LinerDefectsModel;

public interface LinerDefectsDAO {
	
	public LinerDefectsModel LinerDefectsReturnEntryByDate(Date dateIn);
	public LinerDefectsModel LinerDefectsReturnEntryByID(int id);
	public boolean LinerDefectsEntryExists(LinerDefectsModel ld);
	public void LinerDefectsInsert(LinerDefectsModel ld);
	public void LinerDefectsUpdate(LinerDefectsModel ld);
	public LinerDefectsModel LinerDefectsCalculateTotalsByMonth(String monthIn, String yearIn);
	public JPanel LinerDefectsSummaryTable(int in);
	
}
