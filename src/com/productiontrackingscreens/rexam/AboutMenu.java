package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.database.rexam.SQLiteConnection;

public class AboutMenu {

	// JButton infoButton = new JButton("Created by Chris@ChrisMaher.info");
	JLabel timesProgramUsed, employeeAddressList, linerDataEntryScreen, linerUsageEntryScreen, LSSPMEntryScreen, meetingQualityIssues,
	optimeDataEntryScreen, productionMeeting, stolleDataEntryScreen;
	JLabel timesProgramUsedText, employeeAddressListText, linerDataEntryScreenText, linerUsageEntryScreenText, LSSPMEntryScreenText, 
	meetingQualityIssuesText, optimeDataEntryScreenText, productionMeetingText, stolleDataEntryScreenText;
	String employeeAddressListString, linerDataEntryScreenString, linerUsageEntryScreenString, LSSPMEntryScreenString, 
	meetingQualityIssuesString, optimeDataEntryScreenString, productionMeetingString, stolleDataEntryScreenString;
	
	
	public static void main(String[] args) {

		try {
			new AboutMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public AboutMenu() throws SQLException {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}
		
		timesProgramUsed = new JLabel("Times Program Used", SwingConstants.CENTER); 
		linerDataEntryScreen = new JLabel("Liner Data Entry Screen", SwingConstants.CENTER); 
		linerUsageEntryScreen = new JLabel("Liner Usage Entry Screen", SwingConstants.CENTER); 
		LSSPMEntryScreen = new JLabel("LSS/PM Entry Screen", SwingConstants.CENTER); 
		meetingQualityIssues = new JLabel("Meeting Quality Issues", SwingConstants.CENTER); 
		optimeDataEntryScreen = new JLabel("Optime Data Entry Screen", SwingConstants.CENTER); 
		productionMeeting = new JLabel("Production Meeting", SwingConstants.CENTER); 
		stolleDataEntryScreen = new JLabel("Stolle Data Entry Screen", SwingConstants.CENTER); 
		
		timesProgramUsedText = new JLabel((SQLiteConnection.getTotalViews("TimesOpened")+""), SwingConstants.CENTER);
		linerDataEntryScreenText = new JLabel((SQLiteConnection.getTotalViews("LinerEntryEntries")+""), SwingConstants.CENTER); 
		linerUsageEntryScreenText = new JLabel((SQLiteConnection.getTotalViews("LinerUsageEntries")+""), SwingConstants.CENTER); 
		LSSPMEntryScreenText = new JLabel((SQLiteConnection.getTotalViews("LSSPMEntries")+""), SwingConstants.CENTER); 
		meetingQualityIssuesText = new JLabel((SQLiteConnection.getTotalViews("MeetingQualityIssues")+""), SwingConstants.CENTER); 
		optimeDataEntryScreenText = new JLabel((SQLiteConnection.getTotalViews("OptimeEntries")+""), SwingConstants.CENTER); 
		productionMeetingText = new JLabel((SQLiteConnection.getTotalViews("ProductionMeetingEntries")+""), SwingConstants.CENTER); 
		stolleDataEntryScreenText = new JLabel((SQLiteConnection.getTotalViews("StolleEntries")+""), SwingConstants.CENTER); 

		// OuterFrame

		JFrame frame99 = new JFrame("About Menu");
		// frame99.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame99.setSize(350, 450);
		frame99.setLocationRelativeTo(null);

		// JPanel

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// Top Panel		
		JLabel usageStatistics = new JLabel("Usage Statistics",SwingConstants.CENTER);		
		JPanel infoBar = new JPanel(new FlowLayout());
		infoBar.add(usageStatistics);
		infoBar.setBackground(Color.GRAY);
		outerPanel.add(infoBar, BorderLayout.NORTH);

		// Middle panel

		JPanel middlePanel = new JPanel(new GridLayout(8, 2));
		
		middlePanel.add(timesProgramUsed);
		middlePanel.add(timesProgramUsedText);
				
		middlePanel.add(linerDataEntryScreen);
		middlePanel.add(linerDataEntryScreenText);
		
		middlePanel.add(linerUsageEntryScreen);
		middlePanel.add(linerUsageEntryScreenText);
		
		middlePanel.add(LSSPMEntryScreen);
		middlePanel.add(LSSPMEntryScreenText);
		
		middlePanel.add(meetingQualityIssues);
		middlePanel.add(meetingQualityIssuesText);
		
		middlePanel.add(optimeDataEntryScreen);
		middlePanel.add(optimeDataEntryScreenText);
		
		middlePanel.add(productionMeeting);
		middlePanel.add(productionMeetingText);
		
		middlePanel.add(stolleDataEntryScreen);
		middlePanel.add(stolleDataEntryScreenText);
		
		outerPanel.add(middlePanel, BorderLayout.CENTER);
		
		// Lower Panel
		JLabel chrisMaher = new JLabel("Created by Chris Maher @ ChrisMaher.info",SwingConstants.CENTER);
		JPanel infoBar2 = new JPanel(new FlowLayout());
		infoBar2.setBackground(Color.GRAY);
		infoBar2.add(chrisMaher);
		outerPanel.add(infoBar2, BorderLayout.SOUTH);

		frame99.add(outerPanel);
		frame99.setVisible(true);
	}

}
