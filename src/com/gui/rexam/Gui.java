package com.gui.rexam;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.binentryscreens.rexam.EndCounts;
import com.binentryscreens.rexam.LinerAndShellsEntry;
import com.binentryscreens.rexam.LinerDefects;
import com.binentryscreens.rexam.PlantSpoilage2;
import com.binentryscreens.rexam.ProductionWeeklyReport;
import com.binentryscreens.rexam.SpoilageByDay;
import com.nonconformance.rexam.LinerEntryMenu;
import com.nonconformance.rexam.OtherEntryMenu;
import com.nonconformance.rexam.ShellPressEntryMenu;
import com.nonconformance.rexam.StolleEntryMenu;
import com.par.rexam.ParEntry;
import com.productiontrackingscreens.rexam.AboutMenu;
import com.productiontrackingscreens.rexam.EmployeeAddressList;
import com.productiontrackingscreens.rexam.LSSPMActivityEntry2;
import com.productiontrackingscreens.rexam.LinerDataEntryScreen;
import com.productiontrackingscreens.rexam.LinerUsageEntryScreen;
import com.productiontrackingscreens.rexam.MeetingQualityIssues;
import com.productiontrackingscreens.rexam.OptimeDataEntryScreen;
import com.productiontrackingscreens.rexam.ProductionMeeting;
import com.productiontrackingscreens.rexam.StolleDataEntryScreen;

public class Gui extends JFrame {

	JFrame outerFrame;
	JPanel outerPanel;
	JLabel title;
	String titleString = "Rexam Waterford Production Database";
	JMenu fileMenu, employeeMenu, dataEntryMenu, reportingMenu, parDatabaseMenu, nonConformanceMenu, Maintenance, binMenu, aboutMenu;
	JMenuBar menuBar;
	OptimeDataEntryScreen optimeDataEntryScreen;
	int LSSPMtodaysEntry;
	Date date;

	JMenuItem exit, trackingMenu, OpTimeDataEntryMenu, LinerDataEntryMenu, StolleDataEntryMenu, LinerUsageEntryMenu, LSSPMActivityEntry, ProductionMeeting, MeetingQualityIssues,
			EmployeeAddressList, BeltChangeEntryMenu;

	JMenuItem linerAndShellsMenu, linerdefectsMenu, endCountsMenu, spoilageByDayMenu, plantSpoilageMenu, prodWeeklyReportMenu;
	
	JMenuItem NCRStolleEntryMenu, NCRLinerEntryMenu, NCROptimeEntryMenu, NCROtherEntryMenu, NCRStolleFormMenu, NCRLinerFormMenu, NCROptimeFormMenu, NCROtherFormMenu;
	
	JMenuItem parDataEntry;
	
	JMenuItem optimeProductionReport, optimeGroupReport, optimeReport, optimeCommentsLog, shellsByMonth, stolleProductionReport, stolleGroupReport, stolleWeeklyReview,
			stolleReport, stolleCommentsLog, endsByMonth;
        
        JMenuItem ShellPressMenu, BalancerMenu, CompoundLinerMenu, ConversionPressMenu, EHSStatutory, OtherEquipmentMenu, EquipmentOEE, StolleDownTimeShift, StolleDownTimeDay, AnnualEngineeringPMPlan, OtherTrackingInformation;
        
        // ShellPressMenu
        JMenuItem ShellPressProduction, ShellPressMaintenance;
        // BalancerMenu
        JMenuItem BalancerProduction, balancerMaintenance;
        // CompoundLinerMenu
        JMenuItem LinerProduction, LinerMaintenance, LinerSpoilage;
        // ConversionPressMenu
        JMenuItem ConversionPressProduction, ConversionPressMaintenance, ConversionPressSpoilage, TransferBeltReplacementRecord, ScoreTooling, mainTimingBelt, CDLProgression;

	JMenuItem AboutMenu, helpMenu;
	
	String month;

	public Gui() {

		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month);
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);

		JDesktopPane deskTopPane = new JDesktopPane();
		// JInternalFrame intFrame = new JInternalFrame("JInternal Frame");
		// intFrame.setSize(300, 300);
		// intFrame.setVisible(true);
		// deskTopPane.add(intFrame);
		// add(deskTopPane);

		date = new Date();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

//		try {
//			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//				if ("Nimbus".equals(info.getName())) {
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		} catch (Exception e) {
//			// If Nimbus is not available, you can set the GUI to another look
//			// and feel.
//		}

		// Menu Bar Buildup

		// Data Entry BuildUp

		OpTimeDataEntryMenu = new JMenuItem("OpTime Data Entry Menu");
		OpTimeDataEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new OptimeDataEntryScreen(1, 1);
				// System.out.println("clicked.");

			}
		});
		LinerDataEntryMenu = new JMenuItem("Liner Data Entry Menu");
		LinerDataEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerDataEntryScreen(1, -1);
				// System.out.println("clicked.");

			}
		});
		LinerUsageEntryMenu = new JMenuItem("Liner Usage Entry Menu");
		LinerUsageEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerDataEntryScreen(1, -1);
				// System.out.println("clicked.");

			}
		});
		StolleDataEntryMenu = new JMenuItem("Stolle Data Entry Menu");
		StolleDataEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new StolleDataEntryScreen(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});
		LinerUsageEntryMenu = new JMenuItem("Liner Usage Entry Menu");
		LinerUsageEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerUsageEntryScreen(1, -1);
			}
		});
		LSSPMActivityEntry = new JMenuItem("LSS/PM Activity Entry");
		LSSPMActivityEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new LSSPMActivityEntry2(-2, "", "", -1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});
		ProductionMeeting = new JMenuItem("Production Meeting");
		ProductionMeeting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ProductionMeeting(1, -1);
				// System.out.println("clicked.");

			}
		});
		MeetingQualityIssues = new JMenuItem("Meeting Quality Issues");
		MeetingQualityIssues.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new MeetingQualityIssues(1, -1);
				// System.out.println("clicked.");

			}
		});
		EmployeeAddressList = new JMenuItem("Employee Menu");
		EmployeeAddressList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new EmployeeAddressList(1,1);
				// System.out.println("clicked.");

			}
		});
		BeltChangeEntryMenu = new JMenuItem("Belt Change Entry Menu");

		
		// Bin Menu Buildup

		linerAndShellsMenu = new JMenuItem("Liner & Shells Menu");
		linerAndShellsMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new LinerAndShellsEntry(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});
		linerdefectsMenu = new JMenuItem("Liner Defects Menu");
		linerdefectsMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new LinerDefects(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		endCountsMenu = new JMenuItem("End Counts Menu");
		endCountsMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new EndCounts(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		spoilageByDayMenu = new JMenuItem("Spoilage By Day Menu");
		spoilageByDayMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new SpoilageByDay(1, 1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		plantSpoilageMenu = new JMenuItem("Plant Spoilage Menu");
		plantSpoilageMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date date = new Date();
				String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String year = modifiedDate.substring(0, 4);
				int yearInt = Integer.parseInt(year);
				String month = modifiedDate.substring(5, 7);
				
				if(month.equals("01")){
					month = "January";}
				else if(month.equals("02")){
					month = "February";}
				else if(month.equals("03")){
					month = "March";}
				else if(month.equals("04")){
					month = "April";}
				else if(month.equals("05")){
					month = "May";}
				else if(month.equals("06")){
					month = "June";}
				else if(month.equals("07")){
					month = "July";}
				else if(month.equals("08")){
					month = "August";}
				else if(month.equals("09")){
					month = "September";}
				else if(month.equals("10")){
					month = "October";}
				else if(month.equals("11")){
					month = "Novemebr";}
				else if(month.equals("12")){
					month = "December";}

				try {
					new PlantSpoilage2(month,"2014");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		prodWeeklyReportMenu = new JMenuItem("Production Weekly Report");
		prodWeeklyReportMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new ProductionWeeklyReport(1, "", "");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});

		// Par Database Buildup
		
		parDataEntry = new JMenuItem("Par Database Entry");
		parDataEntry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new ParEntry(1, day, month, year);
				
			}
		});
		
		// Non Conformance Buildup
		
		NCRStolleEntryMenu = new JMenuItem("Stolle Entry Menu");
		NCRStolleEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				
					new StolleEntryMenu(1);
				
				
			}
		});
		NCRLinerEntryMenu = new JMenuItem("Liner Entry Menu");
		NCRLinerEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new LinerEntryMenu(1);
				
			}
		});
		NCROptimeEntryMenu = new JMenuItem("Shell Press Entry Menu");
		NCROptimeEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new ShellPressEntryMenu(1);
				
			}
		});
		NCROtherEntryMenu = new JMenuItem("Other Entry Menu");
		NCROtherEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new OtherEntryMenu(1);
				
			}
		});
		NCRStolleFormMenu = new JMenuItem("Stolle NCR Form");
		NCRStolleEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				
			}
		});
		NCRLinerFormMenu = new JMenuItem("Liner NCR Form");
		NCRLinerEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				
			}
		});
		NCROptimeFormMenu = new JMenuItem("Optime NCR Form");
		NCROptimeEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				
			}
		});
		NCROtherFormMenu = new JMenuItem("Form NCR Form");
		NCROtherEntryMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				// TODO Auto-generated method stub
				
			}
		});
		
		// Reports BuildUp

		optimeProductionReport = new JMenuItem("Optime Production Report");
		optimeProductionReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				// OptimeDataEntryScreen optimeScreen = new OptimeDataEntryScreen(1, 1);
				try {
					OptimeDataEntryScreen.createSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		optimeGroupReport = new JMenuItem("Optime Group Report");
		optimeGroupReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					OptimeDataEntryScreen.createGroupSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		optimeReport = new JMenuItem("Optime Report");
		optimeCommentsLog = new JMenuItem("Optime Comments Log");
		optimeCommentsLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					OptimeDataEntryScreen.createCommentsSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

		shellsByMonth = new JMenuItem("Shells By Month");
		shellsByMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					OptimeDataEntryScreen.createShellsByMonthSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		stolleProductionReport = new JMenuItem("Stolle Production Report");
		stolleProductionReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				// OptimeDataEntryScreen optimeScreen = new OptimeDataEntryScreen(1, 1);
				try {
					StolleDataEntryScreen.createSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		stolleGroupReport = new JMenuItem("Stolle Group Report");
		stolleGroupReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					StolleDataEntryScreen.createGroupSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		stolleReport = new JMenuItem("StolleReport");
		
		stolleCommentsLog = new JMenuItem("Stolle Comments Log");
		stolleCommentsLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					StolleDataEntryScreen.createCommentsSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		endsByMonth = new JMenuItem("Ends By Month");
		endsByMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
				
				// TODO Auto-generated method stub
				
				try {
					StolleDataEntryScreen.createEndsByMonthSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		employeeMenu = new JMenu("Employee Menu");
		dataEntryMenu = new JMenu("Production Tracking");
		binMenu = new JMenu("Bin Data Entry");
		parDatabaseMenu = new JMenu("Par Database Menu");
		nonConformanceMenu = new JMenu("Non Conformance");
		reportingMenu = new JMenu("Reporting");
		aboutMenu = new JMenu("About");
		menuBar.add(fileMenu);
		menuBar.add(employeeMenu);
		menuBar.add(dataEntryMenu);
		menuBar.add(binMenu);
		menuBar.add(parDatabaseMenu);
		menuBar.add(nonConformanceMenu);
		menuBar.add(reportingMenu);
		menuBar.add(aboutMenu);
		exit = new JMenuItem("Exit");
		// trackingMenu = new JMenuItem("Tracking Menu");

		helpMenu = new JMenuItem("Help");
		helpMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// new AboutMenu();
				// System.out.println("clicked.");

			}
		});
		AboutMenu = new JMenuItem("About");
		AboutMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new AboutMenu();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});
		
		employeeMenu.add(EmployeeAddressList);

		dataEntryMenu.add(OpTimeDataEntryMenu);
		dataEntryMenu.add(LinerDataEntryMenu);
		dataEntryMenu.add(LinerUsageEntryMenu);
		dataEntryMenu.add(StolleDataEntryMenu);
		dataEntryMenu.add(LSSPMActivityEntry);
		dataEntryMenu.add(ProductionMeeting);
		dataEntryMenu.add(MeetingQualityIssues);
		//dataEntryMenu.add(EmployeeAddressList);
		// dataEntryMenu.add(BeltChangeEntryMenu);

		// linerAndShellsMenu, linerdefectsMenu, endCountsMenu,
		// spoilageByDayMenu,
		// plantSpoilageMenu, prodWeeklyReportMenu

		binMenu.add(linerAndShellsMenu);
		binMenu.add(linerdefectsMenu);
		binMenu.add(endCountsMenu);
		binMenu.add(spoilageByDayMenu);
		binMenu.add(plantSpoilageMenu);
		binMenu.add(prodWeeklyReportMenu);
		
		
		
		// NCRStolleEntryMenu, NCRLinerEntryMenu, NCROptimeEntryMenu, NCROtherEntryMenu, 
		// NCRStolleFormMenu, NCRLinerFormMenu, NCROptimeFormMenu, NCROtherFormMenu
		
		 parDatabaseMenu.add(parDataEntry);
		
		nonConformanceMenu.add(NCRStolleEntryMenu);
		nonConformanceMenu.add(NCRLinerEntryMenu);
		nonConformanceMenu.add(NCROptimeEntryMenu);
		nonConformanceMenu.add(NCROtherEntryMenu);
//		nonConformanceMenu.add(NCRStolleFormMenu);
//		nonConformanceMenu.add(NCRLinerFormMenu);
//		nonConformanceMenu.add(NCROptimeFormMenu);
//		nonConformanceMenu.add(NCROtherFormMenu);

		reportingMenu.add(optimeProductionReport);
		reportingMenu.add(optimeGroupReport);
		// reportingMenu.add(optimeReport);
		reportingMenu.add(optimeCommentsLog);
		reportingMenu.add(shellsByMonth);
		reportingMenu.add(stolleProductionReport);
		reportingMenu.add(stolleGroupReport);
		// reportingMenu.add(stolleReport);
		reportingMenu.add(stolleCommentsLog);
		reportingMenu.add(endsByMonth);

		aboutMenu.add(helpMenu);
		aboutMenu.add(AboutMenu);

		// fileMenu.add(trackingMenu);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				outerFrame.dispose();

			}
		});
		fileMenu.add(exit);

		// Outer Frame Buildup

		title = new JLabel(titleString);
		outerFrame = new JFrame();
		outerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		outerFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		outerFrame.setJMenuBar(menuBar);
		outerFrame.setTitle(titleString);
		outerFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		// Outer Panel Buildup

		outerPanel = new JPanel(new BorderLayout());
		outerPanel.add(title, BorderLayout.NORTH);
		// optimeDataEntryScreen = new optimeDataEntryScreen();
		// outerPanel.add(optimeDataEntryScreen, BorderLayout.CENTER);

		outerFrame.add(deskTopPane);
		outerFrame.setVisible(true);

	}

}
