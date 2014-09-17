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
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class LinerEntryMenu {

    static JLabel NCRNumberLabel, departmentLabel, crewLabel, pressNumberLabel, defectLabel, generalLabel, identifiedByLabel, dateIssuedLabel,
            timeLabel, nonConformanceLabel, immediateActionTakenLabel, personsAlertedLabel, shiftManagerlabel, technicianLabel, operatorLabel,
            engineerLabel, blankLabel1, blankLabel2, blankLabel3, blankLabel4;
    static JTextField NCRNumberJTextfield, identifiedByJTextfield, timeJTextfield, nonConformanceJTextfield, immediateActionTakenJTextfield,
            personsAlertedJTextfield, timeStartJTextField, unknown1JTextField, unknown2JTextField, totalDowntimeJTextField, departmentJTextField,
            dateSignOffCompletedJTextField, HFINumbersJTextField2;
    static JComboBox departmentCombo, crewCombo, pressNumberCombo, defectCombo, generalCombo, identifiedByCombo, shiftManagerCombo, technicianCombo,
            operatorCombo, engineerCombo, departmentJCombo, departmentHeadCombo;

    static JLabel beforeHead1Label, beforeHead2Label, beforeHead3Label, beforeHead4Label, beforeHead5Label, beforeHead6Label, beforeHead7Label,
            beforeHead8Label, afterHead1Label, afterHead2Label, afterHead3Label, afterHead4Label, afterHead5Label, afterHead6Label, afterHead7Label,
            afterHead8Label;
    static JCheckBox beforeHead1JCheckBox, beforeHead2JCheckBox, beforeHead3JCheckBox, beforeHead4JCheckBox, beforeHead5JCheckBox,
            beforeHead6JCheckBox, beforeHead7JCheckBox, beforeHead8JCheckBox, afterHead1JCheckBox, afterHead2JCheckBox, afterHead3JCheckBox,
            afterHead4JCheckBox, afterHead5JCheckBox, afterHead6JCheckBox, afterHead7JCheckBox, afterHead8JCheckBox, actionTakenJTextArea;
    static JTextArea beforeAJTextArea, ActionTakenAJTextArea, ResultAJTextArea, beforeBJTextArea, ActionTakenBJTextArea, ResultBJTextArea,
            beforeCJTextArea, ActionTakenCJTextArea, ResultCJTextArea, beforeDJTextArea, ActionTakenDJTextArea, ResultDJTextArea,
            HFINumbersJTextField, OvecSleevesScrapped, longTermActionJTextArea;

    static JTextArea beforeHead1JTextArea, beforeHead2JTextArea, beforeHead3JTextArea, beforeHead4JTextArea, beforeHead5JTextArea,
            beforeHead6JTextArea, beforeHead7JTextArea, beforeHead8JTextArea, afterHead1JTextArea, afterHead2JTextArea, afterHead3JTextArea,
            afterHead4JTextArea, afterHead5JTextArea, afterHead6JTextArea, afterHead7JTextArea, afterHead8JTextArea, actionTakenJTextArea2;

    static JButton refresh, searchMode, saveRecord, newEntryMode, exportToExcel, updateRecord, print, find, previous, next, summary, back;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;

    static JFrame frame;
    static int CurrentNCRNumber;

    public static void main(String[] args) {

        new LinerEntryMenu(1);
    }

    public LinerEntryMenu(int viewIn) {

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

        frame = new JFrame("Liner NCR Entry");
        frame.setSize(1500, 768);
        frame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());

        if (viewIn == 2) {

            outerPanel.add(createTopPanel(viewIn), BorderLayout.NORTH);
            outerPanel.add(createSummaryPanel());
            outerPanel.add(createBottomPanel(viewIn), BorderLayout.SOUTH);
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
            //outerPanel.add(optionsPanel, BorderLayout.NORTH);
            outerPanel.add(panel, BorderLayout.SOUTH);
        } else if (viewIn == 1) {

            outerPanel.add(panel, BorderLayout.SOUTH);

        }

        return outerPanel;
    }

    public static JPanel createCenterPanel() {

        beforeHead1JCheckBox = new JCheckBox();
        beforeHead2JCheckBox = new JCheckBox();
        beforeHead3JCheckBox = new JCheckBox();
        beforeHead4JCheckBox = new JCheckBox();
        beforeHead5JCheckBox = new JCheckBox();
        beforeHead6JCheckBox = new JCheckBox();
        beforeHead7JCheckBox = new JCheckBox();
        beforeHead8JCheckBox = new JCheckBox();

        beforeHead1JTextArea = new JTextArea(7, 7);
        beforeHead1JTextArea.setLineWrap(true);
        beforeHead2JTextArea = new JTextArea(7, 7);
        beforeHead2JTextArea.setLineWrap(true);
        beforeHead3JTextArea = new JTextArea();
        beforeHead3JTextArea.setLineWrap(true);
        beforeHead4JTextArea = new JTextArea();
        beforeHead4JTextArea.setLineWrap(true);
        beforeHead5JTextArea = new JTextArea();
        beforeHead5JTextArea.setLineWrap(true);
        beforeHead6JTextArea = new JTextArea();
        beforeHead6JTextArea.setLineWrap(true);
        beforeHead7JTextArea = new JTextArea();
        beforeHead7JTextArea.setLineWrap(true);
        beforeHead8JTextArea = new JTextArea();
        beforeHead8JTextArea.setLineWrap(true);

//		afterHead1JCheckBox = new JCheckBox();
//		afterHead2JCheckBox = new JCheckBox();
//		afterHead3JCheckBox = new JCheckBox();
//		afterHead4JCheckBox = new JCheckBox();
//		afterHead5JCheckBox = new JCheckBox();
//		afterHead6JCheckBox = new JCheckBox();
//		afterHead7JCheckBox = new JCheckBox();
//		afterHead8JCheckBox = new JCheckBox();
        afterHead1JTextArea = new JTextArea(7, 7);
        afterHead1JTextArea.setLineWrap(true);
        afterHead2JTextArea = new JTextArea(7, 7);
        afterHead2JTextArea.setLineWrap(true);
        afterHead3JTextArea = new JTextArea();
        afterHead3JTextArea.setLineWrap(true);
        afterHead4JTextArea = new JTextArea();
        afterHead4JTextArea.setLineWrap(true);
        afterHead5JTextArea = new JTextArea();
        afterHead5JTextArea.setLineWrap(true);
        afterHead6JTextArea = new JTextArea();
        afterHead6JTextArea.setLineWrap(true);
        afterHead7JTextArea = new JTextArea();
        afterHead7JTextArea.setLineWrap(true);
        afterHead8JTextArea = new JTextArea();
        afterHead8JTextArea.setLineWrap(true);

        actionTakenJTextArea2 = new JTextArea(6, 20);
        actionTakenJTextArea2.setLineWrap(true);

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(1, 8));

        JPanel panel1Outer = new JPanel(new BorderLayout());
        JPanel panel2Outer = new JPanel(new BorderLayout());
        JPanel panel3Outer = new JPanel(new BorderLayout());
        JPanel panel4Outer = new JPanel(new BorderLayout());
        JPanel panel5Outer = new JPanel(new BorderLayout());
        JPanel panel6Outer = new JPanel(new BorderLayout());
        JPanel panel7Outer = new JPanel(new BorderLayout());
        JPanel panel8Outer = new JPanel(new BorderLayout());

        JPanel panel9Outer = new JPanel(new BorderLayout());
        JPanel panel10Outer = new JPanel(new BorderLayout());
        JPanel panel11Outer = new JPanel(new BorderLayout());
        JPanel panel12Outer = new JPanel(new BorderLayout());
        JPanel panel13Outer = new JPanel(new BorderLayout());
        JPanel panel14Outer = new JPanel(new BorderLayout());
        JPanel panel15Outer = new JPanel(new BorderLayout());
        JPanel panel16Outer = new JPanel(new BorderLayout());

        // 1
        JPanel panel1North = new JPanel(new FlowLayout());
        panel1North.add(new JLabel("Before Head 1"));
        panel1North.add(beforeHead1JCheckBox);
        JPanel center1Panel = new JPanel(new BorderLayout());
        center1Panel.add(beforeHead1JTextArea);
        panel1Outer.add(panel1North, BorderLayout.NORTH);
        panel1Outer.add(center1Panel, BorderLayout.CENTER);
        // 2
        JPanel panel2North = new JPanel(new FlowLayout());
        panel2North.add(new JLabel("Before Head 2"), BorderLayout.CENTER);
        panel2North.add(beforeHead2JCheckBox, BorderLayout.EAST);
        JPanel center2Panel = new JPanel(new BorderLayout());
        center2Panel.add(beforeHead2JTextArea);
        panel2Outer.add(panel2North, BorderLayout.NORTH);
        panel2Outer.add(center2Panel, BorderLayout.CENTER);
        // 3
        JPanel panel3North = new JPanel(new FlowLayout());
        panel3North.add(new JLabel("Before Head 3"), BorderLayout.CENTER);
        panel3North.add(beforeHead3JCheckBox, BorderLayout.EAST);
        JPanel center3Panel = new JPanel(new BorderLayout());
        center3Panel.add(beforeHead3JTextArea);
        panel3Outer.add(panel3North, BorderLayout.NORTH);
        panel3Outer.add(center3Panel, BorderLayout.CENTER);
        // 4
        JPanel panel4North = new JPanel(new FlowLayout());
        panel4North.add(new JLabel("Before Head 4"), BorderLayout.CENTER);
        panel4North.add(beforeHead4JCheckBox, BorderLayout.EAST);
        JPanel center4Panel = new JPanel(new BorderLayout());
        center4Panel.add(beforeHead4JTextArea);
        panel4Outer.add(panel4North, BorderLayout.NORTH);
        panel4Outer.add(center4Panel, BorderLayout.CENTER);
        // 5
        JPanel panel5North = new JPanel(new FlowLayout());
        panel5North.add(new JLabel("Before Head 5"), BorderLayout.CENTER);
        panel5North.add(beforeHead5JCheckBox, BorderLayout.EAST);
        JPanel center5Panel = new JPanel(new BorderLayout());
        center5Panel.add(beforeHead5JTextArea);
        panel5Outer.add(panel5North, BorderLayout.NORTH);
        panel5Outer.add(center5Panel, BorderLayout.CENTER);
        // 6
        JPanel panel6North = new JPanel(new FlowLayout());
        panel6North.add(new JLabel("Before Head 6"), BorderLayout.CENTER);
        panel6North.add(beforeHead6JCheckBox, BorderLayout.EAST);
        JPanel center6Panel = new JPanel(new BorderLayout());
        center6Panel.add(beforeHead6JTextArea);
        panel6Outer.add(panel6North, BorderLayout.NORTH);
        panel6Outer.add(center6Panel, BorderLayout.CENTER);
        // 7
        JPanel panel7North = new JPanel(new FlowLayout());
        panel7North.add(new JLabel("Before Head 7"), BorderLayout.CENTER);
        panel7North.add(beforeHead7JCheckBox, BorderLayout.EAST);
        JPanel center7Panel = new JPanel(new BorderLayout());
        center7Panel.add(beforeHead7JTextArea);
        panel7Outer.add(panel7North, BorderLayout.NORTH);
        panel7Outer.add(center7Panel, BorderLayout.CENTER);
        // 8
        JPanel panel8North = new JPanel(new FlowLayout());
        panel8North.add(new JLabel("Before Head 8"), BorderLayout.CENTER);
        panel8North.add(beforeHead8JCheckBox, BorderLayout.EAST);
        JPanel center8Panel = new JPanel(new BorderLayout());
        center8Panel.add(beforeHead8JTextArea);
        panel8Outer.add(panel8North, BorderLayout.NORTH);
        panel8Outer.add(center8Panel, BorderLayout.CENTER);

        // 9
        JPanel panel9North = new JPanel(new FlowLayout());
        panel9North.add(new JLabel("After Head 1"));
        //panel9North.add(afterHead1JCheckBox, BorderLayout.EAST);
        JPanel center9Panel = new JPanel(new BorderLayout());
        center9Panel.add(afterHead1JTextArea);
        panel9Outer.add(panel9North, BorderLayout.NORTH);
        panel9Outer.add(center9Panel, BorderLayout.CENTER);

        // 10
        JPanel panel10North = new JPanel(new FlowLayout());
        panel10North.add(new JLabel("After Head 2"), BorderLayout.CENTER);
        //panel10North.add(afterHead2JCheckBox, BorderLayout.EAST);
        JPanel center10Panel = new JPanel(new BorderLayout());
        center10Panel.add(afterHead2JTextArea);
        panel10Outer.add(panel10North, BorderLayout.NORTH);
        panel10Outer.add(center10Panel, BorderLayout.CENTER);

        // 11
        JPanel panel11North = new JPanel(new FlowLayout());
        panel11North.add(new JLabel("After Head 3"), BorderLayout.CENTER);
        //panel11North.add(afterHead3JCheckBox, BorderLayout.EAST);
        JPanel center11Panel = new JPanel(new BorderLayout());
        center11Panel.add(afterHead3JTextArea);
        panel11Outer.add(panel11North, BorderLayout.NORTH);
        panel11Outer.add(center11Panel, BorderLayout.CENTER);

        // 12
        JPanel panel12North = new JPanel(new FlowLayout());
        panel12North.add(new JLabel("After Head 4"), BorderLayout.CENTER);
        //panel12North.add(afterHead4JCheckBox, BorderLayout.EAST);
        JPanel center12Panel = new JPanel(new BorderLayout());
        center12Panel.add(afterHead4JTextArea);
        panel12Outer.add(panel12North, BorderLayout.NORTH);
        panel12Outer.add(center12Panel, BorderLayout.CENTER);

        // 13
        JPanel panel13North = new JPanel(new FlowLayout());
        panel13North.add(new JLabel("After Head 5"), BorderLayout.CENTER);
        //panel13North.add(afterHead5JCheckBox, BorderLayout.EAST);
        JPanel center13Panel = new JPanel(new BorderLayout());
        center13Panel.add(afterHead5JTextArea);
        panel13Outer.add(panel13North, BorderLayout.NORTH);
        panel13Outer.add(center13Panel, BorderLayout.CENTER);

        // 14
        JPanel panel14North = new JPanel(new FlowLayout());
        panel14North.add(new JLabel("After Head 6"), BorderLayout.CENTER);
        //panel14North.add(afterHead6JCheckBox, BorderLayout.EAST);
        JPanel center14Panel = new JPanel(new BorderLayout());
        center14Panel.add(afterHead6JTextArea);
        panel14Outer.add(panel14North, BorderLayout.NORTH);
        panel14Outer.add(center14Panel, BorderLayout.CENTER);

        // 15
        JPanel panel15North = new JPanel(new FlowLayout());
        panel15North.add(new JLabel("After Head 7"), BorderLayout.CENTER);
        //panel15North.add(afterHead7JCheckBox, BorderLayout.EAST);
        JPanel center15Panel = new JPanel(new BorderLayout());
        center15Panel.add(afterHead7JTextArea);
        panel15Outer.add(panel15North, BorderLayout.NORTH);
        panel15Outer.add(center15Panel, BorderLayout.CENTER);

        // 16
        JPanel panel16North = new JPanel(new FlowLayout());
        panel16North.add(new JLabel("After Head 8"), BorderLayout.CENTER);
        //panel16North.add(afterHead8JCheckBox, BorderLayout.EAST);
        JPanel center16Panel = new JPanel(new BorderLayout());
        center16Panel.add(afterHead8JTextArea);
        panel16Outer.add(panel16North, BorderLayout.NORTH);
        panel16Outer.add(center16Panel, BorderLayout.CENTER);

        northPanel.add(panel1Outer);
        northPanel.add(panel2Outer);
        northPanel.add(panel3Outer);
        northPanel.add(panel4Outer);
        northPanel.add(panel5Outer);
        northPanel.add(panel6Outer);
        northPanel.add(panel7Outer);
        northPanel.add(panel8Outer);

        JPanel southPanel = new JPanel(new GridLayout(1, 8));

        southPanel.add(panel9Outer);
        southPanel.add(panel10Outer);
        southPanel.add(panel11Outer);
        southPanel.add(panel12Outer);
        southPanel.add(panel13Outer);
        southPanel.add(panel14Outer);
        southPanel.add(panel15Outer);
        southPanel.add(panel16Outer);

        JPanel centerPanel = new JPanel(new BorderLayout());

        centerPanel.add(new JLabel("Action Taken", SwingConstants.CENTER), BorderLayout.NORTH);
        centerPanel.add(actionTakenJTextArea2, BorderLayout.SOUTH);

        outerPanel.add(northPanel, BorderLayout.NORTH);
        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(southPanel, BorderLayout.SOUTH);
        return outerPanel;

    }

    public static JPanel createBottomPanel(int viewIn) {

        departmentJCombo = new JComboBox();
        departmentHeadCombo = new JComboBox();

        longTermActionJTextArea = new JTextArea(5, 4);
        longTermActionJTextArea.setLineWrap(true);
        // longTermActionJTextArea.setBackground(Color.RED);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new LinerEntryMenu(1);

            }
        });
        summary = new JButton("Summary Mode");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new LinerEntryMenu(2);

            }
        });

        timeStartJTextField = new JTextField();
        unknown1JTextField = new JTextField();
        unknown2JTextField = new JTextField();
        totalDowntimeJTextField = new JTextField();
        departmentJTextField = new JTextField();
        dateSignOffCompletedJTextField = new JTextField();
        HFINumbersJTextField2 = new JTextField();

        searchMode = new JButton("Search Mode");
        searchMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new LinerEntryMenu(2);

            }
        });

        saveRecord = new JButton("Save Record");
        saveRecord.addActionListener(new ActionListener() {

            Date selectedDate = (Date) datePicker.getModel().getValue();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SQLiteConnection.NCRLinerInsert(
                            (SQLiteConnection.NCRGetHighestNCRNumber() + 1),
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
                            beforeHead1JCheckBox.isSelected(),
                            beforeHead1JTextArea.getText(),
                            beforeHead2JCheckBox.isSelected(),
                            beforeHead2JTextArea.getText(),
                            beforeHead3JCheckBox.isSelected(),
                            beforeHead3JTextArea.getText(),
                            beforeHead4JCheckBox.isSelected(),
                            beforeHead4JTextArea.getText(),
                            beforeHead5JCheckBox.isSelected(),
                            beforeHead5JTextArea.getText(),
                            beforeHead6JCheckBox.isSelected(),
                            beforeHead6JTextArea.getText(),
                            beforeHead7JCheckBox.isSelected(),
                            beforeHead7JTextArea.getText(),
                            beforeHead8JCheckBox.isSelected(),
                            beforeHead8JTextArea.getText(),
                            actionTakenJTextArea2.getText(),
                            afterHead1JTextArea.getText(),
                            afterHead2JTextArea.getText(),
                            afterHead3JTextArea.getText(),
                            afterHead4JTextArea.getText(),
                            afterHead5JTextArea.getText(),
                            afterHead6JTextArea.getText(),
                            afterHead7JTextArea.getText(),
                            afterHead8JTextArea.getText(),
                            HFINumbersJTextField2.getText() + "",
                            departmentJCombo.getSelectedItem() + "",
                            dateSignOffCompletedJTextField.getText() + "",
                            longTermActionJTextArea.getText() + "");

                    frame.dispose();
                    new LinerEntryMenu(2);
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
                new LinerEntryMenu(2);

            }
        });

        newEntryMode = new JButton("New Entry Mode");
        newEntryMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new LinerEntryMenu(1);

            }
        });

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                StolleEntryMenu.exportToExcel();

            }
        });

        print = new JButton("Print Liner Form");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    createLinerForm(NCRNumberJTextfield.getText() + "");
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
                    SQLiteConnection.NCRLinerUpdate(
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
                            beforeHead1JCheckBox.isSelected(),
                            beforeHead2JCheckBox.isSelected(),
                            beforeHead3JCheckBox.isSelected(),
                            beforeHead4JCheckBox.isSelected(),
                            beforeHead5JCheckBox.isSelected(),
                            beforeHead6JCheckBox.isSelected(),
                            beforeHead7JCheckBox.isSelected(),
                            beforeHead8JCheckBox.isSelected(),
                            beforeHead1JTextArea.getText(),
                            beforeHead2JTextArea.getText(),
                            beforeHead3JTextArea.getText(),
                            beforeHead4JTextArea.getText(),
                            beforeHead5JTextArea.getText(),
                            beforeHead6JTextArea.getText(),
                            beforeHead7JTextArea.getText(),
                            beforeHead8JTextArea.getText(),
                            actionTakenJTextArea2.getText(),
                            afterHead1JTextArea.getText(),
                            afterHead2JTextArea.getText(),
                            afterHead3JTextArea.getText(),
                            afterHead4JTextArea.getText(),
                            afterHead5JTextArea.getText(),
                            afterHead6JTextArea.getText(),
                            afterHead7JTextArea.getText(),
                            afterHead8JTextArea.getText(),
                            HFINumbersJTextField2.getText() + "",
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

        JPanel upperPanel = new JPanel(new GridLayout(1, 2));
        upperPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new GridLayout(3, 2));

        leftPanel.add(new JLabel("HFI Numbers "));
        leftPanel.add(HFINumbersJTextField2);

        leftPanel.add(new JLabel("Department Head "));
        leftPanel.add(departmentJCombo);

        leftPanel.add(new JLabel("Date Signed Off "));
        leftPanel.add(dateSignOffCompletedJTextField);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(new EmptyBorder(3, 3, 3, 3));

        rightPanel.add(new JLabel("Long Term Action Taken ", SwingConstants.CENTER), BorderLayout.WEST);
        rightPanel.add(longTermActionJTextArea, BorderLayout.CENTER);

        upperPanel.add(leftPanel);
        upperPanel.add(rightPanel);

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        if (viewIn == 1) {

            frame.setTitle("Liner Entry Screen (New Entry Mode)");
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

            frame.setTitle("Liner Entry Screen (Update Mode)");
            optionsPanel.add(summary);
            optionsPanel.add(updateRecord);
            optionsPanel.add(print);

        } else if (viewIn == 2) {

            frame.setTitle("(Summary Mode)");
            optionsPanel.add(newEntryMode);
            optionsPanel.add(refresh);
            optionsPanel.add(exportToExcel);

        }

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panel = new JPanel(new BorderLayout());

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

        Object[] result = new Object[47];

        result = SQLiteConnection.NCRReturnLinerByNCRNumber(NCRNumberIn);

        String dateFormatted = (String) result[8];
        System.out.println("Date Formatted : " + dateFormatted);
        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

//		model.setDate(year, month, day);
//		model.setSelected(true);
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

        beforeHead1JCheckBox.setSelected((boolean) result[17]);
        beforeHead2JCheckBox.setSelected((boolean) result[18]);
        beforeHead3JCheckBox.setSelected((boolean) result[19]);
        beforeHead4JCheckBox.setSelected((boolean) result[20]);
        beforeHead5JCheckBox.setSelected((boolean) result[21]);
        beforeHead6JCheckBox.setSelected((boolean) result[22]);
        beforeHead7JCheckBox.setSelected((boolean) result[23]);
        beforeHead8JCheckBox.setSelected((boolean) result[24]);

        beforeHead1JTextArea.setText(result[25] + "");
        beforeHead2JTextArea.setText(result[26] + "");
        beforeHead3JTextArea.setText(result[27] + "");
        beforeHead4JTextArea.setText(result[28] + "");
        beforeHead5JTextArea.setText(result[29] + "");
        beforeHead6JTextArea.setText(result[30] + "");
        beforeHead7JTextArea.setText(result[31] + "");
        beforeHead8JTextArea.setText(result[32] + "");

        actionTakenJTextArea2.setText(result[33] + "");

        afterHead1JTextArea.setText(result[34] + "");
        afterHead2JTextArea.setText(result[35] + "");
        afterHead3JTextArea.setText(result[36] + "");
        afterHead4JTextArea.setText(result[37] + "");
        afterHead5JTextArea.setText(result[38] + "");
        afterHead6JTextArea.setText(result[39] + "");
        afterHead7JTextArea.setText(result[40] + "");
        afterHead8JTextArea.setText(result[41] + "");

        HFINumbersJTextField2.setText(result[42] + "");
        departmentJCombo.setSelectedItem(result[43]);
        dateSignOffCompletedJTextField.setText(result[44] + "");
        longTermActionJTextArea.setText(result[45] + "");

        CurrentNCRNumber = NCRNumberIn;

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
        JasperExportManager.exportReportToPdfFile(print, "Reports/Liners/LinerNCRReport" + NCRNumber + ".pdf");

        PDDocument pdf = PDDocument.load("Reports/Liners/LinerNCRReport" + NCRNumber + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

		// use JasperExportManager to export report to your desired requirement
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

}
