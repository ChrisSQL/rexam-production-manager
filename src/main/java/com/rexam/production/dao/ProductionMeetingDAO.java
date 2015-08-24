package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JPanel;

import com.rexam.production.model.ProductionMeetingModel;

public interface ProductionMeetingDAO {
	
	 public ProductionMeetingModel ProductionMeetingReturnEntryByDate(Date dateIn);
	 public ProductionMeetingModel ProductionMeetingReturnEntryByID(int idIn);
	 public void ProductionMeetingInsert(ProductionMeetingModel pm);
	 public void ProductionMeetingUpdate(ProductionMeetingModel pm);
	 public JPanel ProductionMeetingSummaryTable(int reportType);

}
