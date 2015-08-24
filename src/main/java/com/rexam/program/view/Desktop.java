package com.rexam.program.view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

import com.rexam.binentry.view.EndCountsView;
import com.rexam.binentry.view.LinerDefectsView;
import com.rexam.binentry.view.ProductionWeeklyReportView;
import com.rexam.maintenance.view.BalancerMaintenanceView;
import com.rexam.maintenance.view.BalancerProductionView;
import com.rexam.maintenance.view.LexanFingerTrackingView;
import com.rexam.maintenance.view.LineBalanceView;
import com.rexam.maintenance.view.LinerMaintenanceView;
import com.rexam.maintenance.view.LinerProductionView;
import com.rexam.maintenance.view.LinerSpoilageView;
import com.rexam.maintenance.view.ShellPressMaintenanceView;
import com.rexam.maintenance.view.ShellPressProductionView;
import com.rexam.maintenance.view.TransferBeltView;
import com.rexam.par.view.ParView;
import com.rexam.production.view.LSSPMActivityView;
import com.rexam.production.view.LinerDataView;
import com.rexam.production.view.LinerUsageView;
import com.rexam.production.view.MeetingQualityView;
import com.rexam.production.view.OpTimeView;
import com.rexam.production.view.ProductionMeetingView;

public class Desktop extends JFrame {

	JDesktopPane deskTopPane;
	JMenuBar menuBar;
	JMenu shellPressMenu, balancerMenu, compoundLinerMenu, conversionPressMenu, fileMenu, employeeMenu, dataEntryMenu,
			binMenu, parDatabaseMenu, maintenanceMenu, nonConformanceMenu, employeeOvertimeMenu, reportingMenu,
			aboutMenu;
	JMenuItem shellPressMaintenance, shellPressProduction, transferBelt, balancerProduction, balancerMaintenance,
			lexanFinger, linerProduction, linerMaintenance, linerSpoilage, conversionPressProduction,
			conversionPressMaintenance, conversionPressSpoilage, transferBeltReplacementRecord, scoreTooling,
			mainTimingBelt, cDLProgression, eHSStatutory, lineBalance, importFromViscan, opTimeDataEntryMenu,
			linerDataEntryMenu, linerUsageEntryMenu, stolleDataEntryMenu, lSSPMActivityEntry, productionMeeting,
			meetingQualityIssues, addNewShellsMenu, importFromViscan3, employeeAddressList, beltChangeEntryMenu,
			linerdefectsMenu, endCountsMenu, spoilageByDayMenu, plantSpoilageMenu, prodWeeklyReportMenu,
			importFromViscan2, parDataEntry, nCRStolleEntryMenu, nCRLinerEntryMenu, nCROptimeEntryMenu,
			nCROtherEntryMenu, nCRDefectsMenu, nCRStolleFormMenu, nCRLinerFormMenu, nCROptimeFormMenu, nCROtherFormMenu,
			overtimeSystem, optimeProductionReport, optimeGroupReport, optimeReport, shellsByMonth,
			stolleProductionReport, stolleGroupReport, stolleReport, endsByMonth, exit;
	JPanel outerPanel;
	JLabel title;

	public Desktop() {
		
	//	Desktop desktop = new Desktop();		
	//	desktop.
		setStyle();

		initializeVariables();
		addActionListeners();

		// Outer Frame Buildup
		title = new JLabel("Rexam Production Manager");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(menuBar);
		setTitle(title.getText());
		setExtendedState(Frame.MAXIMIZED_BOTH);
		// Outer Panel Buildup

		outerPanel = new JPanel(new BorderLayout());
		outerPanel.add(title, BorderLayout.NORTH);

		add(deskTopPane);
		setVisible(true);

	}

	public void setStyle() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
	}

	private void addActionListeners() {

		shellPressProduction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new ShellPressProductionView(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		shellPressMaintenance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ShellPressMaintenanceView(1);

			}
		});

		
		transferBelt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new TransferBeltView(1);

			}
		});
		balancerProduction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new BalancerProductionView();

			}
		});
		balancerMaintenance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new BalancerMaintenanceView(1);

			}
		});
		lexanFinger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LexanFingerTrackingView(1, -1);

			}
		});
		linerProduction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerProductionView(1, -1);

			}
		});
		linerMaintenance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerMaintenanceView(1);

			}
		});
		linerSpoilage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerSpoilageView(1, -1);

			}
		});

		lineBalance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LineBalanceView(1, -1);

			}
		});
//		importFromViscan.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					ShellPressProduction.importFromExcel();
//				} catch (IOException ex) {
//					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//				}
//
//			}
//		});
//
		opTimeDataEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new OpTimeView(1, 1);
				// System.out.println("clicked.");

			}
		});
		linerDataEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerDataView(1, -1);

			}
		});
		linerUsageEntryMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerUsageView(1, -1);
				// System.out.println("clicked.");

			}
		});
//		stolleDataEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					new StolleDataEntryScreen(1, -1);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// System.out.println("clicked.");
//
//			}
//		});
//		linerUsageEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new LinerUsageEntryScreen(1, -1);
//			}
//		});
		lSSPMActivityEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new LSSPMActivityView(-2, "", "", -1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println("clicked.");

			}
		});
		productionMeeting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ProductionMeetingView(1, -1);

			}
		});
		meetingQualityIssues.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new MeetingQualityView(1, -1);

			}
		});
//		addNewShellsMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new ShellType(1, 1);
//
//			}
//		});
//
//		importFromViscan3.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					LinerDataEntryScreen.importFromExcel();
//				} catch (IOException ex) {
//					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//				}
//
//			}
//		});
//
//		employeeAddressList.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new EmployeeAddressList(1, 1);
//				// System.out.println("clicked.");
//
//			}
//		});
//		linerAndShellsMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					new LinerAndShellsEntry(1, -1);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				// System.out.println("clicked.");
//
//			}
//		});
		linerdefectsMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerDefectsView(1, -1);

			}
		});
		endCountsMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new EndCountsView(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
//		spoilageByDayMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated catch block
//
//				new SpoilageByDay2();
//
//			}
//		});
//		plantSpoilageMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				Date date = new Date();
//				String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
//				String year = modifiedDate.substring(0, 4);
//				int yearInt = Integer.parseInt(year);
//				String month = modifiedDate.substring(5, 7);
//
//				if (month.equals("01")) {
//					month = "January";
//				} else if (month.equals("02")) {
//					month = "February";
//				} else if (month.equals("03")) {
//					month = "March";
//				} else if (month.equals("04")) {
//					month = "April";
//				} else if (month.equals("05")) {
//					month = "May";
//				} else if (month.equals("06")) {
//					month = "June";
//				} else if (month.equals("07")) {
//					month = "July";
//				} else if (month.equals("08")) {
//					month = "August";
//				} else if (month.equals("09")) {
//					month = "September";
//				} else if (month.equals("10")) {
//					month = "October";
//				} else if (month.equals("11")) {
//					month = "November";
//				} else if (month.equals("12")) {
//					month = "December";
//				}
//
//				try {
//					System.out.println("Month : " + month);
//					new PlantSpoilage2(month, "2014");
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
		prodWeeklyReportMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Date date = new Date();
				String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
				String year = modifiedDate.substring(0, 4);
				int yearInt = Integer.parseInt(year);
				String month = modifiedDate.substring(5, 7);

				if (month.equals("01")) {
					month = "January";
				} else if (month.equals("02")) {
					month = "February";
				} else if (month.equals("03")) {
					month = "March";
				} else if (month.equals("04")) {
					month = "April";
				} else if (month.equals("05")) {
					month = "May";
				} else if (month.equals("06")) {
					month = "June";
				} else if (month.equals("07")) {
					month = "July";
				} else if (month.equals("08")) {
					month = "August";
				} else if (month.equals("09")) {
					month = "September";
				} else if (month.equals("10")) {
					month = "October";
				} else if (month.equals("11")) {
					month = "November";
				} else if (month.equals("12")) {
					month = "December";
				}

				new ProductionWeeklyReportView(1, year, month);

			}
		});
//		importFromViscan2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					LinerAndShellsEntry.importFromExcel();
//				} catch (IOException ex) {
//					Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
//				}
//
//			}
//		});
//
		// Par Database Buildup
		parDataEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ParView(1);

			}
		});
//
//		// Non Conformance Buildup
//		nCRStolleEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new StolleEntryMenu(1);
//
//			}
//		});
//		nCRLinerEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new LinerEntryMenu(1);
//
//			}
//		});
//		nCROptimeEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new ShellPressEntryMenu(1);
//
//			}
//		});
//		nCROtherEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new OtherEntryMenu(1);
//
//			}
//		});
//
//		nCRDefectsMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new Defects(1, 1);
//
//			}
//		});
//
//		nCRStolleEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//			}
//		});
//		nCRLinerEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//			}
//		});
//		nCROptimeEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//			}
//		});
//		nCROtherEntryMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//			}
//		});
//
//		overtimeSystem.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new EmployeeOvertimeSystem("Machine Operator", "A", "", "All");
//			}
//		});
//
//		optimeProductionReport.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				// OptimeDataEntryScreen optimeScreen = new
//				// OptimeDataEntryScreen(1, 1);
//				try {
//					OptimeDataEntryScreen.createSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		optimeGroupReport.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					OptimeDataEntryScreen.createGroupSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		optimeCommentsLog.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					OptimeDataEntryScreen.createCommentsSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		shellsByMonth.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					OptimeDataEntryScreen.createShellsByMonthSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		stolleProductionReport.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				// OptimeDataEntryScreen optimeScreen = new
//				// OptimeDataEntryScreen(1, 1);
//				try {
//					StolleDataEntryScreen.createSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		stolleGroupReport.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					StolleDataEntryScreen.createGroupSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		stolleCommentsLog.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					StolleDataEntryScreen.createCommentsSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		endsByMonth.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// TODO Auto-generated method stub
//				try {
//					StolleDataEntryScreen.createEndsByMonthSummaryScreen();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//
//		helpMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				new Help(1, 1);
//			}
//		});
//		aboutMenu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				JOptionPane.showMessageDialog(null, " Created by Chris Maher @ ChrisMaher.info");
//
//				// System.out.println("clicked.");
//			}
//
//		});
//
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

	}

	private void initializeVariables() {

		deskTopPane = new JDesktopPane();
		// Maintenance //////////////////////////////////////////////////
		shellPressMenu = new JMenu("Shell Press Menu");
		shellPressMaintenance = new JMenuItem("Shell Press Maintenance");
		shellPressProduction = new JMenuItem("Shell Press Production");
		transferBelt = new JMenuItem("Transfer Belt");
		shellPressMenu.add(shellPressProduction);
		shellPressMenu.add(shellPressMaintenance);
		shellPressMenu.add(transferBelt);
		//////////////////////////////////////////////////////////////////
		balancerMenu = new JMenu("Balancer Menu");
		balancerProduction = new JMenuItem("Balancer Production");
		balancerMaintenance = new JMenuItem("Balancer Maintenance");
		lexanFinger = new JMenuItem("Lexan Finger Tracking");
		balancerMenu.add(balancerProduction);
		balancerMenu.add(balancerMaintenance);
		balancerMenu.add(lexanFinger);
		//////////////////////////////////////////////////////////////////
		compoundLinerMenu = new JMenu("Compound Liner Menu");
		linerProduction = new JMenuItem("Liner Production");
		linerMaintenance = new JMenuItem("Liner Maintenance");
		linerSpoilage = new JMenuItem("Liner Spoilage");
		compoundLinerMenu.add(linerProduction);
		compoundLinerMenu.add(linerMaintenance);
		compoundLinerMenu.add(linerSpoilage);
		//////////////////////////////////////////////////////////////////
		conversionPressMenu = new JMenu("Conversion Press Menu");
		conversionPressProduction = new JMenuItem("Conversion Press Production");
		conversionPressMaintenance = new JMenuItem("Conversion Press Maintenance");
		conversionPressSpoilage = new JMenuItem("Conversion Press Spoilage");
		transferBeltReplacementRecord = new JMenuItem("Transfer Belt Replacement Record");
		scoreTooling = new JMenuItem("Score Tooling");
		mainTimingBelt = new JMenuItem("Main Timing Belt");
		cDLProgression = new JMenuItem("CDL Progression");
		eHSStatutory = new JMenuItem("EHS Statutory");
		lineBalance = new JMenuItem("Line Balance");

		///////////////////////////////////////////////////////////////////
		importFromViscan = new JMenuItem("Import From Viscan");
		opTimeDataEntryMenu = new JMenuItem("OpTime Data Entry Menu");
		linerDataEntryMenu = new JMenuItem("Liner Data Entry Menu");
		linerUsageEntryMenu = new JMenuItem("Liner Usage Entry Menu");
		stolleDataEntryMenu = new JMenuItem("Stolle Data Entry Menu");
		lSSPMActivityEntry = new JMenuItem("LSS/PM Activity Entry");
		productionMeeting = new JMenuItem("Production Meeting");
		meetingQualityIssues = new JMenuItem("Meeting Quality Issues");
		addNewShellsMenu = new JMenuItem("Shell Types Menu");
		importFromViscan3 = new JMenuItem("Import From Viscan");
		employeeAddressList = new JMenuItem("Employee Menu");
		beltChangeEntryMenu = new JMenuItem("Belt Change Entry Menu");
		linerdefectsMenu = new JMenuItem("Liner Defects Menu");
		endCountsMenu = new JMenuItem("End Counts Menu");
		spoilageByDayMenu = new JMenuItem("Spoilage By Day Menu");
		plantSpoilageMenu = new JMenuItem("Plant Spoilage Menu");
		prodWeeklyReportMenu = new JMenuItem("Production Weekly Report");
		importFromViscan2 = new JMenuItem("Import From Viscan");
		parDataEntry = new JMenuItem("Par Database Entry");
		nCRStolleEntryMenu = new JMenuItem("Stolle Entry Menu");
		nCRLinerEntryMenu = new JMenuItem("Liner Entry Menu");
		nCROptimeEntryMenu = new JMenuItem("Shell Press Entry Menu");
		nCROtherEntryMenu = new JMenuItem("Other Entry Menu");
		nCRDefectsMenu = new JMenuItem("Defects Menu");
		nCRStolleFormMenu = new JMenuItem("Stolle NCR Form");
		nCRLinerFormMenu = new JMenuItem("Liner NCR Form");
		nCROptimeFormMenu = new JMenuItem("Optime NCR Form");
		nCROtherFormMenu = new JMenuItem("Form NCR Form");
		overtimeSystem = new JMenuItem("Overtime System");
		optimeProductionReport = new JMenuItem("Optime Production Report");
		optimeGroupReport = new JMenuItem("Optime Group Report");
		optimeReport = new JMenuItem("Optime Report");
		shellsByMonth = new JMenuItem("Shells By Month");
		stolleProductionReport = new JMenuItem("Stolle Production Report");
		stolleGroupReport = new JMenuItem("Stolle Group Report");
		stolleReport = new JMenuItem("StolleReport");
		endsByMonth = new JMenuItem("Ends By Month");
		//////////////////////////////////////////////////////////////////
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		employeeMenu = new JMenu("Employee Menu");
		dataEntryMenu = new JMenu("Production Database");
		binMenu = new JMenu("Bin Data Entry");
		parDatabaseMenu = new JMenu("Par Database Menu");
		maintenanceMenu = new JMenu("Maintenance");
		nonConformanceMenu = new JMenu("Non Conformance");
		employeeOvertimeMenu = new JMenu("Employee Overtime");
		reportingMenu = new JMenu("Reporting");
		aboutMenu = new JMenu("About");
		exit = new JMenuItem("Exit");
		//////////////////////////////////////////////////////////////////
		employeeMenu.add(employeeAddressList);
		dataEntryMenu.add(importFromViscan3);
		dataEntryMenu.addSeparator();
		dataEntryMenu.add(opTimeDataEntryMenu);
		dataEntryMenu.add(linerDataEntryMenu);
		dataEntryMenu.add(linerUsageEntryMenu);
		dataEntryMenu.add(stolleDataEntryMenu);
		dataEntryMenu.add(lSSPMActivityEntry);
		dataEntryMenu.add(productionMeeting);
		dataEntryMenu.add(meetingQualityIssues);
//		dataEntryMenu.add(addNewShellsMenu);
		//////////////////////////////////////////////////////////////////
		conversionPressMenu.add(conversionPressProduction);
		conversionPressMenu.add(conversionPressMaintenance);
		conversionPressMenu.add(conversionPressSpoilage);
		conversionPressMenu.add(transferBeltReplacementRecord);
		conversionPressMenu.add(scoreTooling);
		conversionPressMenu.add(mainTimingBelt);
		//////////////////////////////////////////////////////////////////
		binMenu.add(importFromViscan2);
		binMenu.addSeparator();
		binMenu.add(linerdefectsMenu);
		binMenu.add(endCountsMenu);
	//	binMenu.add(spoilageByDayMenu);
	//	binMenu.add(plantSpoilageMenu);
		binMenu.add(prodWeeklyReportMenu);
		//////////////////////////////////////////////////////////////////
		parDatabaseMenu.add(parDataEntry);
		//////////////////////////////////////////////////////////////////
		maintenanceMenu.add(importFromViscan);
		maintenanceMenu.addSeparator();
		maintenanceMenu.add(shellPressMenu);
		maintenanceMenu.add(balancerMenu);
		maintenanceMenu.add(compoundLinerMenu);
	//	maintenanceMenu.add(conversionPressMenu);
	//	maintenanceMenu.add(eHSStatutory);
		maintenanceMenu.add(lineBalance);
		//////////////////////////////////////////////////////////////////
		nonConformanceMenu.add(nCRStolleEntryMenu);
		nonConformanceMenu.add(nCRLinerEntryMenu);
		nonConformanceMenu.add(nCROptimeEntryMenu);
		nonConformanceMenu.add(nCROtherEntryMenu);
		nonConformanceMenu.add(nCRDefectsMenu);
		//////////////////////////////////////////////////////////////////
	//	employeeOvertimeMenu.add(overtimeSystem);
		//////////////////////////////////////////////////////////////////
		reportingMenu.add(optimeProductionReport);
		reportingMenu.add(optimeGroupReport);
		reportingMenu.add(shellsByMonth);
		reportingMenu.add(stolleProductionReport);
		reportingMenu.add(stolleGroupReport);
		reportingMenu.add(endsByMonth);
		//////////////////////////////////////////////////////////////////
		fileMenu.add(exit);
		//////////////////////////////////////////////////////////////////
		menuBar.add(fileMenu);
		menuBar.add(employeeMenu);
		menuBar.add(maintenanceMenu);
		menuBar.add(binMenu);
		menuBar.add(dataEntryMenu);
		menuBar.add(parDatabaseMenu);
		menuBar.add(nonConformanceMenu);
	//	menuBar.add(employeeOvertimeMenu);
		menuBar.add(reportingMenu);
		//////////////////////////////////////////////////////////////////

	}

}
