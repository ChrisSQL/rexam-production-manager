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

import com.binentryscreens.rexam.*;
import com.database.rexam.SQLiteConnection;
import com.help.rexam.Help;
import com.maintenance.rexam.*;
import com.nonconformance.rexam.*;
import com.overtime.rexam.EmployeeOvertimeSystem;
import com.par.rexam.ParEntry;
import com.productiontrackingscreens.rexam.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Gui extends JFrame {

    JFrame outerFrame;
    JPanel outerPanel;
    JLabel title;
    String titleString = "Rexam Waterford Production Suite";
    JMenu fileMenu, employeeMenu, dataEntryMenu, reportingMenu, parDatabaseMenu, nonConformanceMenu, employeeOvertimeMenu, MaintenanceMenu, binMenu, aboutMenu;
    JMenuBar menuBar;
    OptimeDataEntryScreen optimeDataEntryScreen;
    int LSSPMtodaysEntry;
    Date date;

    JMenuItem exit, trackingMenu, OpTimeDataEntryMenu, LinerDataEntryMenu, StolleDataEntryMenu, LinerUsageEntryMenu, LSSPMActivityEntry, ProductionMeeting, MeetingQualityIssues, AddNewShellsMenu,
            EmployeeAddressList, BeltChangeEntryMenu;

    JMenuItem linerAndShellsMenu, linerdefectsMenu, endCountsMenu, spoilageByDayMenu, plantSpoilageMenu, prodWeeklyReportMenu, importFromViscan, importFromViscan2, importFromViscan3;

    JMenuItem NCRStolleEntryMenu, NCRLinerEntryMenu, NCROptimeEntryMenu, NCROtherEntryMenu, NCRDefectsMenu, NCRStolleFormMenu, NCRLinerFormMenu, NCROptimeFormMenu, NCROtherFormMenu;

    JMenuItem overtimeSystem;

    JMenuItem parDataEntry;

    JMenuItem optimeProductionReport, optimeGroupReport, optimeReport, optimeCommentsLog, shellsByMonth, stolleProductionReport, stolleGroupReport, stolleWeeklyReview,
            stolleReport, stolleCommentsLog, endsByMonth;

    JMenuItem ShellPressMenu, BalancerMenu, CompoundLinerMenu, ConversionPressMenu, EHSStatutory, OtherEquipmentMenu, EquipmentOEE, StolleDownTimeShift, StolleDownTimeDay, AnnualEngineeringPMPlan, OtherTrackingInformation;

    // ShellPressMenu
    JMenuItem shellPressProduction, ShellPressMaintenance, transferBelt;
    // BalancerMenu
    JMenuItem BalancerProduction, balancerMaintenance, lexanFinger;
    // CompoundLinerMenu
    JMenuItem LinerProduction, LinerMaintenance, LinerSpoilage;
    // ConversionPressMenu
    JMenuItem ConversionPressProduction, ConversionPressMaintenance, ConversionPressSpoilage, TransferBeltReplacementRecord, ScoreTooling, mainTimingBelt, CDLProgression;

    JMenuItem AboutMenu, helpMenu;

    JMenuItem lineBalance;

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
        ShellPressMenu = new JMenu("Shell Press Menu");
        ShellPressMaintenance = new JMenuItem("Shell Press Maintenance");
        ShellPressMaintenance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new ShellPressMaintenance(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        shellPressProduction = new JMenuItem("Shell Press Production");
        shellPressProduction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new ShellPressProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        transferBelt = new JMenuItem("Transfer Belt");
        transferBelt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new TransferBelt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ShellPressMenu.add(shellPressProduction);
        ShellPressMenu.add(ShellPressMaintenance);
        ShellPressMenu.add(transferBelt);

        BalancerMenu = new JMenu("Balancer Menu");
        BalancerProduction = new JMenuItem("Balancer Production");
        BalancerProduction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new BalancerProduction(1, -5);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        balancerMaintenance = new JMenuItem("Balancer Maintenance");
        balancerMaintenance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new BalancerMaintenance(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        lexanFinger = new JMenuItem("Lexan Finger Tracking");
        lexanFinger.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new LexanFingerTracking(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        BalancerMenu.add(BalancerProduction);
        BalancerMenu.add(balancerMaintenance);
        BalancerMenu.add(lexanFinger);

        CompoundLinerMenu = new JMenu("Compound Liner Menu");
        // LinerProduction, LinerMaintenance, LinerSpoilage
        LinerProduction = new JMenuItem("Liner Production");
        LinerProduction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new LinerProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        LinerMaintenance = new JMenuItem("Liner Maintenance");
        LinerMaintenance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new LinerMaintenance(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        LinerSpoilage = new JMenuItem("Liner Spoilage");
        LinerSpoilage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new LinerSpoilage(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        CompoundLinerMenu.add(LinerProduction);
        CompoundLinerMenu.add(LinerMaintenance);
        CompoundLinerMenu.add(LinerSpoilage);

        ConversionPressMenu = new JMenu("Conversion Press Menu");
        // ConversionPressProduction, ConversionPressMaintenance, ConversionPressSpoilage, TransferBeltReplacementRecord, ScoreTooling, mainTimingBelt, CDLProgression
        ConversionPressProduction = new JMenuItem("Conversion Press Production");
        ConversionPressProduction.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new StolleProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ConversionPressMaintenance = new JMenuItem("Conversion Press Maintenance");
        ConversionPressMaintenance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new StolleMaintenance(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ConversionPressSpoilage = new JMenuItem("Conversion Press Spoilage");
        ConversionPressSpoilage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new StolleSpoilage(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        TransferBeltReplacementRecord = new JMenuItem("Transfer Belt Replacement Record");
        TransferBeltReplacementRecord.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new TransferBelt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ScoreTooling = new JMenuItem("Score Tooling");
        ScoreTooling.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new ScoreInsert(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        mainTimingBelt = new JMenuItem("Main Timing Belt");
        mainTimingBelt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new TimingBelt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        CDLProgression = new JMenuItem("CDL Progression");
        CDLProgression.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new TimingBelt(1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        ConversionPressMenu.add(ConversionPressProduction);
        ConversionPressMenu.add(ConversionPressMaintenance);
        ConversionPressMenu.add(ConversionPressSpoilage);
        ConversionPressMenu.add(TransferBeltReplacementRecord);
        ConversionPressMenu.add(ScoreTooling);
        ConversionPressMenu.add(mainTimingBelt);
        //  ConversionPressMenu.add(CDLProgression);

        EHSStatutory = new JMenuItem("EHS Statutory");
        EHSStatutory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new EHSStatutoryChecks(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        //OtherEquipmentMenu = new JMenu("Other Equipment Menu");
        EquipmentOEE = new JMenuItem("Equipment OEE");
        EquipmentOEE.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new MachineOEE(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        StolleDownTimeShift = new JMenuItem("Stolle DownTime Shift");
        StolleDownTimeShift.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new StolleDowntimeShift(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        StolleDownTimeDay = new JMenuItem("Stolle DownTime Day");
        StolleDownTimeDay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new StolleDowntimeDay(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        
        
        
        //AnnualEngineeringPMPlan = new JMenu("Annual Engineering PM Plan");
        lineBalance = new JMenuItem("Line Balance");
        lineBalance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new LineBalance(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        importFromViscan = new JMenuItem("Import From Viscan");
        importFromViscan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ShellPressProduction.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

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
        AddNewShellsMenu = new JMenuItem("Shell Types Menu");
        AddNewShellsMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new ShellType(1, 1);

            }
        });

        importFromViscan3 = new JMenuItem("Import From Viscan");
        importFromViscan3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LinerDataEntryScreen.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        EmployeeAddressList = new JMenuItem("Employee Menu");
        EmployeeAddressList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new EmployeeAddressList(1, 1);
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
                // TODO Auto-generated catch block

                new SpoilageByDay2();

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

                try {
                    System.out.println("Month : " + month);
                    new PlantSpoilage2(month, "2014");
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

                try {
                    new ProductionWeeklyReport(1, year, month);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // System.out.println("clicked.");

            }
        });
        importFromViscan2 = new JMenuItem("Import From Viscan");
        importFromViscan2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LinerAndShellsEntry.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

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

        NCRDefectsMenu = new JMenuItem("Defects Menu");
        NCRDefectsMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new Defects(1, 1);

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

        // Overtime Buildup
        overtimeSystem = new JMenuItem("Overtime System");
        overtimeSystem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new EmployeeOvertimeSystem("Machine Operator", "A", "", "All");
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
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
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        employeeMenu = new JMenu("Employee Menu");
        dataEntryMenu = new JMenu("Production Database");
        binMenu = new JMenu("Bin Data Entry");
        parDatabaseMenu = new JMenu("Par Database Menu");
        MaintenanceMenu = new JMenu("Maintenance");
        nonConformanceMenu = new JMenu("Non Conformance");
        employeeOvertimeMenu = new JMenu("Employee Overtime");
        reportingMenu = new JMenu("Reporting");
        aboutMenu = new JMenu("About");
        menuBar.add(fileMenu);
        menuBar.add(employeeMenu);
        menuBar.add(MaintenanceMenu);
        menuBar.add(binMenu);
        menuBar.add(dataEntryMenu);
        menuBar.add(parDatabaseMenu);
        menuBar.add(nonConformanceMenu);
        menuBar.add(employeeOvertimeMenu);
        menuBar.add(reportingMenu);
        menuBar.add(aboutMenu);
        exit = new JMenuItem("Exit");
        // trackingMenu = new JMenuItem("Tracking Menu");

        helpMenu = new JMenuItem("Help");
        helpMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new Help(1, 1);
            }
        });
        AboutMenu = new JMenuItem("About");
        AboutMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, " Created by Chris Maher @ ChrisMaher.info");

                // System.out.println("clicked.");
            }

        });

        employeeMenu.add(EmployeeAddressList);

        dataEntryMenu.add(importFromViscan3);
        dataEntryMenu.addSeparator();
        dataEntryMenu.add(OpTimeDataEntryMenu);
        dataEntryMenu.add(LinerDataEntryMenu);
        dataEntryMenu.add(LinerUsageEntryMenu);
        dataEntryMenu.add(StolleDataEntryMenu);
        dataEntryMenu.add(LSSPMActivityEntry);
        dataEntryMenu.add(ProductionMeeting);
        dataEntryMenu.add(MeetingQualityIssues);
        dataEntryMenu.add(AddNewShellsMenu);

        // Maintanence 
        MaintenanceMenu.add(importFromViscan);
        MaintenanceMenu.addSeparator();
        MaintenanceMenu.add(ShellPressMenu);
        MaintenanceMenu.add(BalancerMenu);
        MaintenanceMenu.add(CompoundLinerMenu);
        MaintenanceMenu.add(ConversionPressMenu);
        MaintenanceMenu.add(EHSStatutory);
        //MaintenanceMenu.add(OtherEquipmentMenu);
        MaintenanceMenu.add(EquipmentOEE);
        MaintenanceMenu.add(StolleDownTimeShift);
        MaintenanceMenu.add(StolleDownTimeDay);
        // MaintenanceMenu.add(StolleDownTimeDay);
        // MaintenanceMenu.add(AnnualEngineeringPMPlan);

        MaintenanceMenu.add(lineBalance);

        binMenu.add(importFromViscan2);
        binMenu.addSeparator();
        binMenu.add(linerAndShellsMenu);
        binMenu.add(linerdefectsMenu);
        binMenu.add(endCountsMenu);
        binMenu.add(spoilageByDayMenu);
        binMenu.add(plantSpoilageMenu);
        binMenu.add(prodWeeklyReportMenu);

        parDatabaseMenu.add(parDataEntry);

        nonConformanceMenu.add(NCRStolleEntryMenu);
        nonConformanceMenu.add(NCRLinerEntryMenu);
        nonConformanceMenu.add(NCROptimeEntryMenu);
        nonConformanceMenu.add(NCROtherEntryMenu);
        nonConformanceMenu.add(NCRDefectsMenu);

        employeeOvertimeMenu.add(overtimeSystem);

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

        //    aboutMenu.add(helpMenu);
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

        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("GUI");

    }

}
