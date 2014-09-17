package com.nonconformance.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import org.apache.pdfbox.pdmodel.PDDocument;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.database.rexam.SQLiteConnection.NCRReturnJTable;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.TempFile;

public class StolleEntryMenu {

    static JLabel NCRNumberLabel, departmentLabel, crewLabel, pressNumberLabel, defectLabel, generalLabel, identifiedByLabel, dateIssuedLabel,
            timeLabel, nonConformanceLabel, immediateActionTakenLabel, personsAlertedLabel, shiftManagerlabel, technicianLabel, operatorLabel,
            engineerLabel, blankLabel1, blankLabel2, blankLabel3, blankLabel4;
    static JTextField NCRNumberJTextfield, identifiedByJTextfield, timeJTextfield, nonConformanceJTextfield, immediateActionTakenJTextfield,
            personsAlertedJTextfield, timeStartJTextField, unknown1JTextField, unknown2JTextField, totalDowntimeJTextField, departmentJTextField,
            dateSignOffCompletedJTextField;
    static JComboBox departmentCombo, crewCombo, pressNumberCombo, defectCombo, generalCombo, identifiedByCombo, shiftManagerCombo, technicianCombo,
            operatorCombo, engineerCombo, departmentJCombo;

    static JLabel ALaneLabel, BLaneLabel, CLaneLabel, DLaneLabel;
    static JCheckBox ALaneJCheckBox, BLaneJCheckBox, CLaneJCheckBox, DLaneJCheckBox;
    static JTextArea beforeAJTextArea, ActionTakenAJTextArea, ResultAJTextArea, beforeBJTextArea, ActionTakenBJTextArea, ResultBJTextArea,
            beforeCJTextArea, ActionTakenCJTextArea, ResultCJTextArea, beforeDJTextArea, ActionTakenDJTextArea, ResultDJTextArea,
            HFINumbersJTextField, OvecSleevesScrapped, longTermActionJTextArea;

    static JButton refresh, searchMode, saveRecord, newEntryMode, exportToExcel, updateRecord, print, find, previous, next, summary, back;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static JFrame frame;
    static int CurrentNCRNumber;

    static String query;

    public static void main(String[] args) {

        new StolleEntryMenu(1);
        //exportToExcel();
    }

    public StolleEntryMenu(int viewIn) {

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

        frame = new JFrame("Stolle NCR Entry");
        frame.setSize(1500, 768);
        frame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());

        if (viewIn == 2) {

            outerPanel.add(createTopPanel(viewIn), BorderLayout.NORTH);
            outerPanel.add(createSummaryPanel());
            //outerPanel.add(createBottomPanel(viewIn), BorderLayout.SOUTH);
        } else {

            outerPanel.add(createTopPanel(viewIn), BorderLayout.NORTH);
            outerPanel.add(createCenterPanel(), BorderLayout.CENTER);
            outerPanel.add(createBottomPanel(viewIn), BorderLayout.SOUTH);

        }

        frame.add(outerPanel);
        fillCombos();
        frame.setVisible(true);

    }

    public static JPanel createTopPanel(int viewIn) {

        JPanel outerPanel = new JPanel(new BorderLayout());

        find = new JButton("Find");

        previous = new JButton("Previous Record");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (CurrentNCRNumber > SQLiteConnection.NCRGetHighestNCRNumber()) {
                        CurrentNCRNumber = SQLiteConnection.NCRGetHighestNCRNumber();
                    }
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                int nextNCR = CurrentNCRNumber;

                try {

                    System.out.println("NCR : " + SQLiteConnection.NCRnextNCRType(nextNCR));

                    if (SQLiteConnection.NCRnextNCRType(nextNCR) == 1) {
                        frame.dispose();
                        new StolleEntryMenu(-1);
                        StolleEntryMenu.setStolleEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber - 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 2) {
                        frame.dispose();
                        new LinerEntryMenu(-1);
                        LinerEntryMenu.setStolleEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber - 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 3) {
                        frame.dispose();
                        new ShellPressEntryMenu(-1);
                        ShellPressEntryMenu.setEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber - 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 4) {
                        frame.dispose();
                        new OtherEntryMenu(-1);
                        OtherEntryMenu.setEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber - 1;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (CurrentNCRNumber == 0) {
                    CurrentNCRNumber = 1;
                }

                int nextNCR = CurrentNCRNumber;

                try {

                    System.out.println("NCR : " + SQLiteConnection.NCRnextNCRType(nextNCR));

                    if (SQLiteConnection.NCRnextNCRType(nextNCR) == 1) {
                        frame.dispose();
                        new StolleEntryMenu(-1);
                        StolleEntryMenu.setStolleEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber + 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 2) {
                        frame.dispose();
                        new LinerEntryMenu(-1);
                        LinerEntryMenu.setStolleEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber + 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 3) {
                        frame.dispose();
                        new ShellPressEntryMenu(-1);
                        ShellPressEntryMenu.setEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber + 1;
                    } else if (SQLiteConnection.NCRnextNCRType(nextNCR) == 4) {
                        frame.dispose();
                        new OtherEntryMenu(-1);
                        OtherEntryMenu.setEntryToNCR(nextNCR);
                        CurrentNCRNumber = CurrentNCRNumber + 1;
                    }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);
        optionsPanel.add(find);
        optionsPanel.add(previous);
        optionsPanel.add(next);

        JPanel panel = new JPanel(new GridLayout(3, 12));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month) - 1;
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);
        model = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        NCRNumberLabel = new JLabel("NCR Number", SwingConstants.CENTER);
        departmentLabel = new JLabel("Department", SwingConstants.CENTER);
        crewLabel = new JLabel("Crew", SwingConstants.CENTER);
        pressNumberLabel = new JLabel("Press Number", SwingConstants.CENTER);
        defectLabel = new JLabel("Defect", SwingConstants.CENTER);
        generalLabel = new JLabel("General", SwingConstants.CENTER);
        identifiedByLabel = new JLabel("Identified By", SwingConstants.CENTER);
        dateIssuedLabel = new JLabel("Date Issued", SwingConstants.CENTER);
        timeLabel = new JLabel("Time", SwingConstants.CENTER);
        nonConformanceLabel = new JLabel("Non Conformance", SwingConstants.CENTER);
        immediateActionTakenLabel = new JLabel("Immediate Action", SwingConstants.CENTER);
        personsAlertedLabel = new JLabel("Persons Alerted", SwingConstants.CENTER);
        shiftManagerlabel = new JLabel("Shift Manager", SwingConstants.CENTER);
        technicianLabel = new JLabel("Technician", SwingConstants.CENTER);
        operatorLabel = new JLabel("Operator", SwingConstants.CENTER);
        engineerLabel = new JLabel("Engineer", SwingConstants.CENTER);
        blankLabel1 = new JLabel("", SwingConstants.CENTER);
        blankLabel2 = new JLabel("", SwingConstants.CENTER);
        blankLabel3 = new JLabel("", SwingConstants.CENTER);
        blankLabel4 = new JLabel("", SwingConstants.CENTER);

        NCRNumberJTextfield = new JTextField("");
        NCRNumberJTextfield.setEditable(false);
        identifiedByJTextfield = new JTextField("");
        timeJTextfield = new JTextField("");
        nonConformanceJTextfield = new JTextField("");
        immediateActionTakenJTextfield = new JTextField("");
        personsAlertedJTextfield = new JTextField("");

        departmentCombo = new JComboBox();
        crewCombo = new JComboBox();
        pressNumberCombo = new JComboBox();
        defectCombo = new JComboBox();
        generalCombo = new JComboBox();
        identifiedByCombo = new JComboBox();
        shiftManagerCombo = new JComboBox();
        technicianCombo = new JComboBox();
        operatorCombo = new JComboBox();
        engineerCombo = new JComboBox();

        panel.add(NCRNumberLabel);
        panel.add(NCRNumberJTextfield);

        panel.add(departmentLabel);
        panel.add(departmentCombo);

        panel.add(crewLabel);
        panel.add(crewCombo);

        panel.add(pressNumberLabel);
        panel.add(pressNumberCombo);

        panel.add(defectLabel);
        panel.add(defectCombo);

        panel.add(generalLabel);
        panel.add(generalCombo);

        panel.add(identifiedByLabel);
        panel.add(identifiedByJTextfield);

        panel.add(dateIssuedLabel);
        panel.add(datePicker);

        panel.add(timeLabel);
        panel.add(timeJTextfield);

        panel.add(nonConformanceLabel);
        panel.add(nonConformanceJTextfield);

        panel.add(immediateActionTakenLabel);
        panel.add(immediateActionTakenJTextfield);

        panel.add(blankLabel1);
        panel.add(blankLabel2);

        panel.add(personsAlertedLabel);
        panel.add(personsAlertedJTextfield);

        panel.add(shiftManagerlabel);
        panel.add(shiftManagerCombo);

        panel.add(technicianLabel);
        panel.add(technicianCombo);

        panel.add(operatorLabel);
        panel.add(operatorCombo);

        panel.add(engineerLabel);
        panel.add(engineerCombo);

        panel.add(blankLabel3);
        panel.add(blankLabel4);

        if (viewIn == -1) {
            // outerPanel.add(optionsPanel, BorderLayout.NORTH);
            outerPanel.add(panel, BorderLayout.SOUTH);
        } else if (viewIn == 1) {

            outerPanel.add(panel, BorderLayout.SOUTH);

        }

        return outerPanel;
    }

    public static JPanel createCenterPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        ALaneLabel = new JLabel("A Lane", SwingConstants.CENTER);
        BLaneLabel = new JLabel("B Lane", SwingConstants.CENTER);
        CLaneLabel = new JLabel("C Lane", SwingConstants.CENTER);
        DLaneLabel = new JLabel("D Lane", SwingConstants.CENTER);
        ALaneJCheckBox = new JCheckBox();
        BLaneJCheckBox = new JCheckBox();
        CLaneJCheckBox = new JCheckBox();
        DLaneJCheckBox = new JCheckBox();

        JPanel panelLeft = new JPanel(new GridLayout(15, 1));
        panelLeft.setBorder(new EmptyBorder(20, 20, 20, 20));

        panelLeft.add(ALaneLabel);
        panelLeft.add(ALaneJCheckBox);
        panelLeft.add(new JLabel(""));
        panelLeft.add(new JLabel(""));
        panelLeft.add(BLaneLabel);
        panelLeft.add(BLaneJCheckBox);
        panelLeft.add(new JLabel(""));
        panelLeft.add(new JLabel(""));
        panelLeft.add(CLaneLabel);
        panelLeft.add(CLaneJCheckBox);
        panelLeft.add(new JLabel(""));
        panelLeft.add(new JLabel(""));
        panelLeft.add(DLaneLabel);
        panelLeft.add(DLaneJCheckBox);
        panelLeft.add(new JLabel(""));

        JPanel panelCenter = new JPanel(new GridLayout(4, 4));

        HFINumbersJTextField = new JTextArea();
        OvecSleevesScrapped = new JTextArea();

        beforeAJTextArea = new JTextArea();
        ActionTakenAJTextArea = new JTextArea();
        ResultAJTextArea = new JTextArea();

        beforeBJTextArea = new JTextArea();
        ActionTakenBJTextArea = new JTextArea();
        ResultBJTextArea = new JTextArea();

        beforeCJTextArea = new JTextArea();
        ActionTakenCJTextArea = new JTextArea();
        ResultCJTextArea = new JTextArea();

        beforeDJTextArea = new JTextArea();
        ActionTakenDJTextArea = new JTextArea();
        ResultDJTextArea = new JTextArea();

        longTermActionJTextArea = new JTextArea(5, 5);
        longTermActionJTextArea.setLineWrap(true);

        JPanel panel1 = new JPanel(new BorderLayout());

        panel1.add(new JLabel("Before A", SwingConstants.CENTER), BorderLayout.NORTH);
        panel1.add(beforeAJTextArea, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());

        panel2.add(new JLabel("Action Taken A", SwingConstants.CENTER), BorderLayout.NORTH);
        panel2.add(ActionTakenAJTextArea, BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());

        panel3.add(new JLabel("Result A", SwingConstants.CENTER), BorderLayout.NORTH);
        panel3.add(ResultAJTextArea, BorderLayout.CENTER);

        JPanel panel4 = new JPanel(new BorderLayout());

        panel4.add(new JLabel("HFI Numbers", SwingConstants.CENTER), BorderLayout.NORTH);
        panel4.add(HFINumbersJTextField, BorderLayout.CENTER);

        JPanel panel5 = new JPanel(new BorderLayout());

        panel5.add(new JLabel("Before B", SwingConstants.CENTER), BorderLayout.NORTH);
        panel5.add(beforeBJTextArea, BorderLayout.CENTER);

        JPanel panel6 = new JPanel(new BorderLayout());

        panel6.add(new JLabel("Action Taken B", SwingConstants.CENTER), BorderLayout.NORTH);
        panel6.add(ActionTakenBJTextArea, BorderLayout.CENTER);

        JPanel panel7 = new JPanel(new BorderLayout());

        panel7.add(new JLabel("Result B", SwingConstants.CENTER), BorderLayout.NORTH);
        panel7.add(ResultBJTextArea, BorderLayout.CENTER);

        JPanel panel8 = new JPanel(new BorderLayout());

        panel8.add(new JLabel("Sleeves Scrapped?", SwingConstants.CENTER), BorderLayout.NORTH);
        panel8.add(OvecSleevesScrapped, BorderLayout.CENTER);

        JPanel panel9 = new JPanel(new BorderLayout());

        panel9.add(new JLabel("Before C", SwingConstants.CENTER), BorderLayout.NORTH);
        panel9.add(beforeCJTextArea, BorderLayout.CENTER);

        JPanel panel10 = new JPanel(new BorderLayout());

        panel10.add(new JLabel("Action Taken C", SwingConstants.CENTER), BorderLayout.NORTH);
        panel10.add(ActionTakenCJTextArea, BorderLayout.CENTER);

        JPanel panel11 = new JPanel(new BorderLayout());

        panel11.add(new JLabel("Result C", SwingConstants.CENTER), BorderLayout.NORTH);
        panel11.add(ResultCJTextArea, BorderLayout.CENTER);

        JPanel panel12 = new JPanel(new BorderLayout());

        panel12.add(new JLabel(" ", SwingConstants.CENTER), BorderLayout.NORTH);
        panel12.add(new JLabel(" ", SwingConstants.CENTER), BorderLayout.CENTER);

        JPanel panel13 = new JPanel(new BorderLayout());

        panel13.add(new JLabel("Before D", SwingConstants.CENTER), BorderLayout.NORTH);
        panel13.add(beforeDJTextArea, BorderLayout.CENTER);

        JPanel panel14 = new JPanel(new BorderLayout());

        panel14.add(new JLabel("Action Taken D", SwingConstants.CENTER), BorderLayout.NORTH);
        panel14.add(ActionTakenDJTextArea, BorderLayout.CENTER);

        JPanel panel15 = new JPanel(new BorderLayout());

        panel15.add(new JLabel("Result D", SwingConstants.CENTER), BorderLayout.NORTH);
        panel15.add(ResultDJTextArea, BorderLayout.CENTER);

        JPanel panel16 = new JPanel(new BorderLayout());

        // panel16.add(new JLabel(" ", JLabel.CENTER), BorderLayout.NORTH);
        // panel16.add(new JLabel(" ", JLabel.CENTER), BorderLayout.CENTER);
        panel16.add(new JLabel("Long Term Action", SwingConstants.CENTER), BorderLayout.NORTH);
        panel16.add(longTermActionJTextArea, BorderLayout.CENTER);

        panel16.setBackground(new Color(169, 198, 236));

        panelCenter.add(panel1);
        panelCenter.add(panel2);
        panelCenter.add(panel3);
        panelCenter.add(panel4);
        panelCenter.add(panel5);
        panelCenter.add(panel6);
        panelCenter.add(panel7);
        panelCenter.add(panel8);
        panelCenter.add(panel9);
        panelCenter.add(panel10);
        panelCenter.add(panel11);
        panelCenter.add(panel12);
        panelCenter.add(panel13);
        panelCenter.add(panel14);
        panelCenter.add(panel15);
        panelCenter.add(panel16);

        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelCenter, BorderLayout.CENTER);
        return panel;

    }

    public static JPanel createBottomPanel(int viewIn) {

        departmentJCombo = new JComboBox();
        longTermActionJTextArea.setLineWrap(true);

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Clicked");
                exportToExcel();

            }
        });

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new StolleEntryMenu(1);

            }
        });
        summary = new JButton("Summary Mode");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new StolleEntryMenu(2);

            }
        });

        timeStartJTextField = new JTextField();
        unknown1JTextField = new JTextField();
        unknown2JTextField = new JTextField();
        totalDowntimeJTextField = new JTextField();
        totalDowntimeJTextField.setText("0");
        departmentJTextField = new JTextField();
        dateSignOffCompletedJTextField = new JTextField();

        searchMode = new JButton("Search Mode");
//		searchMode.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				// frame.dispose();
//
//				try {
//
//					System.out.println("Number : " + SQLiteConnection.NCRnextNCRType(SQLiteConnection.NCRGetHighestNCRNumber()));
//
//					if (SQLiteConnection.NCRnextNCRType(SQLiteConnection.NCRGetHighestNCRNumber()) == 1) {
//						frame.dispose();
//						StolleEntryMenu stolleMenu = new StolleEntryMenu(-1);
//						stolleMenu.setStolleEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());
//					}
//
//					else if (SQLiteConnection.NCRnextNCRType(SQLiteConnection.NCRGetHighestNCRNumber()) == 2) {
//						frame.dispose();
//						LinerEntryMenu linerMenu = new LinerEntryMenu(-1);
//						linerMenu.setStolleEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());
//					}
//
//					if (SQLiteConnection.NCRnextNCRType(SQLiteConnection.NCRGetHighestNCRNumber()) == 3) {
//						frame.dispose();
//						ShellPressEntryMenu shellPressmenu = new ShellPressEntryMenu(-1);
//						shellPressmenu.setEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());
//					}
//
//					if (SQLiteConnection.NCRnextNCRType(SQLiteConnection.NCRGetHighestNCRNumber()) == 4) {
//						frame.dispose();
//						OtherEntryMenu otherMenu = new OtherEntryMenu(-1);
//						otherMenu.setEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());
//					}
//
//				}
//				catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
        searchMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new OtherEntryMenu(2);

            }
        });

        saveRecord = new JButton("Save Record");
        saveRecord.addActionListener(new ActionListener() {

            Date selectedDate = (Date) datePicker.getModel().getValue();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SQLiteConnection.NCRStolleInsert(
                            (SQLiteConnection.NCRGetHighestNCRNumber() + 1), departmentCombo.getSelectedItem() + "", crewCombo.getSelectedItem() + "",
                            pressNumberCombo.getSelectedItem() + "", defectCombo.getSelectedItem() + "", generalCombo.getSelectedItem() + "",
                            identifiedByJTextfield.getText() + "", date, timeJTextfield.getText() + "", nonConformanceJTextfield.getText() + "",
                            immediateActionTakenJTextfield.getText() + "", personsAlertedJTextfield.getText() + "",
                            shiftManagerCombo.getSelectedItem() + "", technicianCombo.getSelectedItem() + "", operatorCombo.getSelectedItem() + "",
                            engineerCombo.getSelectedItem() + "", ALaneJCheckBox.isSelected(), BLaneJCheckBox.isSelected(),
                            CLaneJCheckBox.isSelected(), DLaneJCheckBox.isSelected(), beforeAJTextArea.getText() + "", beforeBJTextArea.getText()
                            + "", beforeCJTextArea.getText() + "", beforeDJTextArea.getText() + "", ActionTakenAJTextArea.getText() + "",
                            ActionTakenBJTextArea.getText() + "", ActionTakenCJTextArea.getText() + "", ActionTakenDJTextArea.getText() + "",
                            ResultAJTextArea.getText() + "", ResultBJTextArea.getText() + "", ResultCJTextArea.getText() + "",
                            ResultDJTextArea.getText() + "", HFINumbersJTextField.getText() + "", OvecSleevesScrapped.getText() + "",
                            timeStartJTextField.getText() + "", unknown1JTextField.getText() + "", unknown2JTextField.getText() + "",
                            Integer.valueOf(totalDowntimeJTextField.getText() + ""), departmentJCombo.getSelectedItem() + "",
                            dateSignOffCompletedJTextField.getText() + "", longTermActionJTextArea.getText() + ""
                    );

                    frame.dispose();
                    new StolleEntryMenu(2);
                    setStolleEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());

                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new StolleEntryMenu(2);

            }
        });

        newEntryMode = new JButton("New Entry Mode");
        newEntryMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new StolleEntryMenu(1);

            }
        });

        print = new JButton("Print Stolle Form");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    createStolleForm(NCRNumberJTextfield.getText() + "");
                } catch (JRException | IOException | PrinterException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        updateRecord = new JButton("Update Record");
        updateRecord.addActionListener(new ActionListener() {

            Date selectedDate = (Date) datePicker.getModel().getValue();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SQLiteConnection.NCRStolleUpdate(
                            Integer.valueOf(NCRNumberJTextfield.getText() + ""),
                            departmentCombo.getSelectedItem() + "",
                            crewCombo.getSelectedItem() + "",
                            pressNumberCombo.getSelectedItem() + "",
                            defectCombo.getSelectedItem() + "",
                            generalCombo.getSelectedItem() + "",
                            identifiedByJTextfield.getText() + "",
                            date,
                            timeJTextfield.getText() + "",
                            nonConformanceJTextfield.getText() + "",
                            immediateActionTakenJTextfield.getText() + "",
                            personsAlertedJTextfield.getText() + "",
                            shiftManagerCombo.getSelectedItem() + "",
                            technicianCombo.getSelectedItem() + "",
                            operatorCombo.getSelectedItem() + "",
                            engineerCombo.getSelectedItem() + "",
                            ALaneJCheckBox.isSelected(),
                            BLaneJCheckBox.isSelected(),
                            CLaneJCheckBox.isSelected(),
                            DLaneJCheckBox.isSelected(),
                            beforeAJTextArea.getText() + "",
                            beforeBJTextArea.getText() + "",
                            beforeCJTextArea.getText() + "",
                            beforeDJTextArea.getText() + "",
                            ActionTakenAJTextArea.getText() + "",
                            ActionTakenBJTextArea.getText() + "",
                            ActionTakenCJTextArea.getText() + "",
                            ActionTakenDJTextArea.getText() + "",
                            ResultAJTextArea.getText() + "",
                            ResultBJTextArea.getText() + "",
                            ResultCJTextArea.getText() + "",
                            ResultDJTextArea.getText() + "",
                            HFINumbersJTextField.getText() + "",
                            OvecSleevesScrapped.getText() + "",
                            timeStartJTextField.getText() + "",
                            unknown1JTextField.getText() + "",
                            unknown2JTextField.getText() + "",
                            Integer.valueOf(totalDowntimeJTextField.getText()),
                            departmentJCombo.getSelectedItem() + "",
                            dateSignOffCompletedJTextField.getText() + "",
                            longTermActionJTextArea.getText() + ""
                    );

                    frame.dispose();
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel upperPanel = new JPanel(new GridLayout(1, 12));
        upperPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        upperPanel.add(new JLabel("Time Start", SwingConstants.CENTER));
        upperPanel.add(timeStartJTextField);

        upperPanel.add(new JLabel("Unknown 1", SwingConstants.CENTER));
        upperPanel.add(unknown1JTextField);

        upperPanel.add(new JLabel("Unknown 2", SwingConstants.CENTER));
        upperPanel.add(unknown2JTextField);

        upperPanel.add(new JLabel("Downtime (Mins)", SwingConstants.CENTER));
        upperPanel.add(totalDowntimeJTextField);

        upperPanel.add(new JLabel("Department Head", SwingConstants.CENTER));
        upperPanel.add(departmentJCombo);

        upperPanel.add(new JLabel("Date Signed Off", SwingConstants.CENTER));
        upperPanel.add(dateSignOffCompletedJTextField);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        if (viewIn == 1) {

            frame.setTitle("Stolle Entry Screen (New Entry Mode)");
            optionsPanel.add(searchMode);
            optionsPanel.add(saveRecord);
            try {
                NCRNumberJTextfield.setText(SQLiteConnection.NCRGetHighestNCRNumber() + 1 + "");
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            NCRNumberJTextfield.setEditable(false);

        } else if (viewIn == -1) {

            frame.setTitle("Stolle Entry Screen (Update Mode)");

            optionsPanel.add(summary);
            optionsPanel.add(updateRecord);
            optionsPanel.add(print);

        } else if (viewIn == 2) {

            exportToExcel = new JButton("Export To Excel");
            exportToExcel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    System.out.println("Clicked");
                    exportToExcel();

                }
            });

            frame.setTitle("(Summary Mode)");
            optionsPanel.add(newEntryMode);
            optionsPanel.add(refresh);
            optionsPanel.add(exportToExcel);
            // optionsPanel.add(back);

        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel = new JPanel(new BorderLayout());
        // panel.add(new JLabel("Long Term Action", JLabel.CENTER),
        // BorderLayout.NORTH);
        // panel.add(longTermActionJTextArea, BorderLayout.CENTER);

        bottomPanel.add(new JPanel());
        bottomPanel.add(panel);
        bottomPanel.add(new JPanel());

        if (viewIn == 1) {
            outerPanel.add(upperPanel, BorderLayout.NORTH);
            outerPanel.add(bottomPanel, BorderLayout.CENTER);
            outerPanel.add(optionsPanel, BorderLayout.SOUTH);
        } else if (viewIn == -1) {
            outerPanel.add(upperPanel, BorderLayout.NORTH);
            outerPanel.add(bottomPanel, BorderLayout.CENTER);
            outerPanel.add(optionsPanel, BorderLayout.SOUTH);
        } else if (viewIn == 2) {
            outerPanel.add(optionsPanel, BorderLayout.SOUTH);
        }

        return outerPanel;
    }

    public static void setStolleEntryToNCR(int NCRNumberIn) throws Exception {

        Object[] result = new Object[44];

        result = SQLiteConnection.NCRReturnStolleByNCRNumber(NCRNumberIn);

        String dateFormatted = (String) result[8];
        System.out.println("Date Formatted : " + dateFormatted);
        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(1, 4)); // Correct

        model.setDate(year, month, day);
        model.setSelected(true);

        NCRNumberJTextfield.setText(result[0] + "");
        departmentCombo.setSelectedItem(result[2]);
        crewCombo.setSelectedItem(result[3]);
        pressNumberCombo.setSelectedItem(result[4]);
        defectCombo.setSelectedItem(result[5]);
        generalCombo.setSelectedItem(result[6]);
        identifiedByJTextfield.setText(result[7] + "");
        // date
        timeJTextfield.setText(result[9] + "");
        nonConformanceJTextfield.setText(result[10] + "");
        immediateActionTakenJTextfield.setText(result[11] + "");
        personsAlertedJTextfield.setText(result[12] + "");
        shiftManagerCombo.setSelectedItem(result[13]);
        technicianCombo.setSelectedItem(result[14]);
        operatorCombo.setSelectedItem(result[15]);
        engineerCombo.setSelectedItem(result[16]);

        ALaneJCheckBox.setSelected((boolean) result[17]);
        BLaneJCheckBox.setSelected((boolean) result[18]);
        CLaneJCheckBox.setSelected((boolean) result[19]);
        DLaneJCheckBox.setSelected((boolean) result[20]);

        beforeAJTextArea.setText(result[21] + "");
        beforeBJTextArea.setText(result[22] + "");
        beforeCJTextArea.setText(result[23] + "");
        beforeDJTextArea.setText(result[24] + "");
        ActionTakenAJTextArea.setText(result[25] + "");
        ActionTakenBJTextArea.setText(result[26] + "");
        ActionTakenCJTextArea.setText(result[27] + "");
        ActionTakenDJTextArea.setText(result[28] + "");
        ResultAJTextArea.setText(result[29] + "");
        ResultBJTextArea.setText(result[30] + "");
        ResultCJTextArea.setText(result[31] + "");
        ResultDJTextArea.setText(result[32] + "");
        HFINumbersJTextField.setText(result[33] + "");
        OvecSleevesScrapped.setText(result[34] + "");
        timeStartJTextField.setText(result[35] + "");
        unknown1JTextField.setText(result[36] + "");
        unknown2JTextField.setText(result[37] + "");
        totalDowntimeJTextField.setText(result[38] + "");
        departmentJCombo.setSelectedItem(result[39]);
        dateSignOffCompletedJTextField.setText(result[40] + "");
        longTermActionJTextArea.setText(result[41] + "");

        CurrentNCRNumber = NCRNumberIn;
        for (int j = 0; j < result.length; j++) {
            System.out.println("Result : " + j + " : " + result[j]);
        }

    }

    private static void fillCombos() {

        // Crew
        try {

            String sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName Asc";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("CrewName");
                crewCombo.addItem(crewName);
            }

        } catch (Exception e) {

        }

        // Press Name
        try {

            String sql = "SELECT Press.PressName FROM Press ORDER BY PressName Asc";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("PressName");
                pressNumberCombo.addItem(crewName);
            }

        } catch (Exception e) {

        }

        // Identified By
        try {

            String sql = "SELECT Employees.Name FROM Employees ORDER BY Name Asc";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String employeeName = rs.getString("Name");
                identifiedByCombo.addItem(employeeName);
                operatorCombo.addItem(employeeName);
                shiftManagerCombo.addItem(employeeName);
                technicianCombo.addItem(employeeName);
                engineerCombo.addItem(employeeName);
                departmentJCombo.addItem(employeeName);
            }

        } catch (Exception e) {

        }

        // Departments
        // Defect
        // General
        // Identified By
        // Shift Manager
        // Technician
        // Operator
        // Engineer
    }

    private static JPanel createSummaryPanel() {

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Clicked");
                StolleEntryMenu.exportToExcel();

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel tablePanel = new JPanel();
        try {
            tablePanel = SQLiteConnection.NCRCreateSummaryTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);

        outerPanel.add(scrollPane);

        return outerPanel;
    }

    public static void createStolleForm(String NCRNumber) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/nonconformance/rexam/StolleNCRForm.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("NCRNumberParameter", NCRNumber); // note here you can add parameters which
        // would be utilized by the report

        JasperPrint print = JasperFillManager.fillReport(report, params, conn);
        JasperExportManager.exportReportToPdfFile(print, "StolleNCRReport" + NCRNumber + ".pdf");

        PDDocument pdf = PDDocument.load("StolleNCRReport" + NCRNumber + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createLinerForm(String NCRNumber) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/nonconformance/rexam/LinerNCRForm.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("NCRNumberParameter", NCRNumber); // note here you can add parameters which
        // would be utilized by the report

        JasperPrint print = JasperFillManager.fillReport(report, params, conn);
        JasperExportManager.exportReportToPdfFile(print, "LinerNCRReport" + NCRNumber + ".pdf");

        PDDocument pdf = PDDocument.load("LinerNCRReport" + NCRNumber + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createShellPressForm(String NCRNumber) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/nonconformance/rexam/ShellPressNCRForm.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("NCRNumberParameter", NCRNumber); // note here you can add parameters which
        // would be utilized by the report

        JasperPrint print = JasperFillManager.fillReport(report, params, conn);
        JasperExportManager.exportReportToPdfFile(print, "ShellPressNCRReport" + NCRNumber + ".pdf");

        PDDocument pdf = PDDocument.load("ShellPressNCRReport" + NCRNumber + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createOtherForm(String NCRNumber) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/nonconformance/rexam/OtherNCRForm.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("NCRNumberParameter", NCRNumber); // note here you can add parameters which
        // would be utilized by the report

        JasperPrint print = JasperFillManager.fillReport(report, params, conn);
        JasperExportManager.exportReportToPdfFile(print, "Reports/Stolle/OtherNCRForm" + NCRNumber + ".pdf");

        PDDocument pdf = PDDocument.load("Reports/Stolle/OtherNCRForm" + NCRNumber + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void exportToExcel() {

        String[] typesArray = {"NCRStolle", "NCRLiner", "NCRShellPress", "NCROther"};
        JComboBox typeCombo = new JComboBox(typesArray);
        JCheckBox datesCheck = new JCheckBox();

        excelModel1 = new UtilDateModel(new Date());
        excelModel2 = new UtilDateModel(new Date());
        excelDate1 = new JDatePanelImpl(excelModel1);
        excelDate2 = new JDatePanelImpl(excelModel2);
        excelPicker1 = new JDatePickerImpl(excelDate1);
        excelPicker2 = new JDatePickerImpl(excelDate2);

        query = "";

        String[] sortTypesArray = {"NCRNumber", "DateIssued", "NCRType", "Defect"};
        JComboBox sortTypesCombo = new JComboBox(sortTypesArray);

        String[] itemsArray = {"NCRStolle", "NCRLiner", "NCRShellPress", "NCROther"};
        JComboBox itemsCombo = new JComboBox(itemsArray);

        JButton export, cancel;

        cancel = new JButton("Cancel");
        export = new JButton("Export");
        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) excelPicker1.getModel().getValue());
                String modifiedDate2 = new SimpleDateFormat("yyyy-MM-dd").format((Date) excelPicker2.getModel().getValue());
                String item = typeCombo.getSelectedItem() + "";

                query = "SELECT * FROM " + item + " WHERE DateIssued BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                System.out.println(query);
                generateExcelFile(item, query);

            }
        });

        JFrame excelQueryFrame = new JFrame("Export to Excel");
        excelQueryFrame.setSize(350, 230);
        excelQueryFrame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new GridLayout(5, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        outerPanel.add(new JLabel("Type : ", JLabel.CENTER));
        outerPanel.add(typeCombo);

        outerPanel.add(new JLabel("Start Date : ", JLabel.CENTER));
        outerPanel.add(excelPicker1);

        outerPanel.add(new JLabel("End Date : ", JLabel.CENTER));
        outerPanel.add(excelPicker2);

        outerPanel.add(new JLabel("Sort By : ", JLabel.CENTER));
        outerPanel.add(sortTypesCombo);

        outerPanel.add(cancel);
        outerPanel.add(export);

        excelQueryFrame.add(outerPanel);
        excelQueryFrame.setVisible(true);

    }

    public static void generateExcelFile(String type, String query) {

        // Create A JFrame to Query
        JTable table = new JTable();

        try {
//           table = NCRReturnJTable("SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer FROM NCRStolle ORDER BY NCRNumber ASC");
            table = NCRReturnJTable(type, query);

        } catch (SQLException ex) {
            Logger.getLogger(StolleEntryMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create JTable with Query
        // Export to Excel File
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet1 = workBook.createSheet("NCR");
        Row row = sheet1.createRow(1);
        TableModel model2 = table.getModel(); //Table model

        HSSFFont font = workBook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFCellStyle style = workBook.createCellStyle();
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        
        HSSFFont font2 = workBook.createFont();
        HSSFCellStyle style2 = workBook.createCellStyle();
        style2.setFont(font2);
        style2.setAlignment(CellStyle.ALIGN_CENTER);
        
        sheet1.setDefaultColumnWidth(12);

        Row headerRow = sheet1.createRow(0); //Create row at line 0
        for (int headings = 0; headings < model2.getColumnCount(); headings++) { //For each column
            headerRow.createCell(headings).setCellValue(model2.getColumnName(headings));//Write column name
            headerRow.getCell(headings).setCellStyle(style);
            
        }

        for (int rows = 0; rows < model2.getRowCount(); rows++) { //For each table row
            for (int cols = 0; cols < table.getColumnCount(); cols++) { //For each table column
                row.createCell(cols).setCellValue(model2.getValueAt(rows, cols).toString()); //Write value
                row.getCell(cols).setCellStyle(style2);
                
            }

            //Set the row to the next one in the sequence 
            row = sheet1.createRow((rows + 1));
        }

        try {
            FileOutputStream output = new FileOutputStream("TempExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("TempExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
