package com.rexam.production.dao;

import java.util.Date;

import javax.swing.JPanel;

import com.rexam.production.model.MeetingQualityModel;

public interface MeetingQualityDAO {
	
	public MeetingQualityModel MeetingQualityReturnEntryByDate(Date dateIn);
	public MeetingQualityModel MeetingQualityReturnEntryByID(int idIn);
	public void MeetingQualityInsert(MeetingQualityModel mq);
	public void MeetingQualityUpdate(MeetingQualityModel mq);
	public JPanel MeetingQualityIssuesSummaryTable(int reportType);

}
