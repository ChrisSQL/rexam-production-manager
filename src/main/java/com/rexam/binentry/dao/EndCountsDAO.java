package com.rexam.binentry.dao;

import java.util.Date;

import com.rexam.binentry.model.EndCountsModel;

public interface EndCountsDAO {
	
	
	public EndCountsModel EndCountsReturnEntryByDate(Date dateIn);
	public EndCountsModel EndCountsReturnEntryByID(int id);
	public boolean EndCountsEntryExists(EndCountsModel ec);
	public void EndCountsInsert(EndCountsModel ec);
	public void EndCountsUpdate(EndCountsModel ec);
	public Object[] EndCountsCalculateTotalsByMonth(String monthIn, String yearIn);
	

}
