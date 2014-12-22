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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.nonconformance.rexam.LinerEntryMenu.frame;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.view.JasperViewer;

public class OtherEntryMenu {

    static JLabel NCRNumberLabel, departmentLabel, crewLabel, pressNumberLabel, defectLabel, generalLabel, identifiedByLabel, dateIssuedLabel,
            timeLabel, nonConformanceLabel, immediateActionTakenLabel, personsAlertedLabel, shiftManagerlabel, technicianLabel, operatorLabel,
            engineerLabel, blankLabel1, blankLabel2, blankLabel3, blankLabel4;
    static JTextField NCRNumberJTextfield, identifiedByJTextfield, timeJTextfield, ItemTextfield, nonConformanceJTextfield, immediateActionTakenJTextfield,
            personsAlertedJTextfield, timeStartJTextField, unknown1JTextField, unknown2JTextField, totalDowntimeJTextField, departmentJTextField,
            dateSignOffCompletedJTextField, HFINumbersJTextField2;
    static JComboBox departmentCombo, crewCombo, pressNumberCombo, defectCombo, generalCombo, areaOfOriginCombo, identifiedByCombo, shiftManagerCombo, technicianCombo,
            operatorCombo, engineerCombo, departmentJCombo, departmentHeadCombo, venderJComboBox;

    static JLabel beforeHead1Label, beforeHead2Label, beforeHead3Label, beforeHead4Label, beforeHead5Label, beforeHead6Label, beforeHead7Label,
            beforeHead8Label, afterHead1Label, afterHead2Label, afterHead3Label, afterHead4Label, afterHead5Label, afterHead6Label, afterHead7Label,
            afterHead8Label;
    static JCheckBox beforeHead1JCheckBox, beforeHead2JCheckBox, beforeHead3JCheckBox, beforeHead4JCheckBox, beforeHead5JCheckBox,
            beforeHead6JCheckBox, beforeHead7JCheckBox, beforeHead8JCheckBox, afterHead1JCheckBox, afterHead2JCheckBox, afterHead3JCheckBox,
            afterHead4JCheckBox, afterHead5JCheckBox, afterHead6JCheckBox, afterHead7JCheckBox, afterHead8JCheckBox;
    static JTextArea beforeAJTextArea, ActionTakenAJTextArea, ResultAJTextArea, beforeBJTextArea, ActionTakenBJTextArea, ResultBJTextArea,
            beforeCJTextArea, ActionTakenCJTextArea, ResultCJTextArea, beforeDJTextArea, ActionTakenDJTextArea, ResultDJTextArea,
            HFINumbersJTextField, OvecSleevesScrapped, longTermActionJTextArea;
    static JTextArea actionTakenJTextArea, beforeJTextArea, ResultJTextArea, beforeHead1JTextArea, beforeHead2JTextArea, beforeHead3JTextArea, beforeHead4JTextArea, beforeHead5JTextArea,
            beforeHead6JTextArea, beforeHead7JTextArea, beforeHead8JTextArea, afterHead1JTextArea, afterHead2JTextArea, afterHead3JTextArea,
            afterHead4JTextArea, afterHead5JTextArea, afterHead6JTextArea, afterHead7JTextArea, afterHead8JTextArea, actionTakenJTextArea2, detailsJTextArea, resultsOfInvestigationJTextArea, defectJTextArea, defectJTextArea2;

    static JTextField customerJTextField, vendorJTextField;
    static JButton refresh, searchMode, saveRecord, newEntryMode, exportToExcel, updateRecord, print, find, previous, next, summary, delete, back;

    static JCheckBox die1, die2, die3, die4, die5, die6, die7, die8, die9, die10, die11, die12, die13,
            die14, die15, die16, die17, die18, die19, die20, die21, die22, die23, die24;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;

    public static JFrame frame;
    static int CurrentNCRNumber;
    static JScrollPane sp, sp2;

    public static void main(String[] args) {

        new OtherEntryMenu(1);
    }

    public OtherEntryMenu(int viewIn) {

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

        frame = new JFrame("Other NCR Entry");
        frame.setSize(1500, 768);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
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

        SQLiteConnection.AnalyticsUpdate("OtherEntryMenu");
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
        pressNumberLabel = new JLabel("Item", SwingConstants.CENTER);
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
        ItemTextfield = new JTextField();

        departmentCombo = new JComboBox();
        crewCombo = new JComboBox();
        pressNumberCombo = new JComboBox();
        defectCombo = new JComboBox();
        defectCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fillGeneralCombo();
                fillAreaOfOriginCombo();

            }
        });
        generalCombo = new JComboBox();
        generalCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fillAreaOfOriginCombo();
                fillPossibleCauseArea();

            }
        });
        areaOfOriginCombo = new JComboBox();
        identifiedByCombo = new JComboBox();
        shiftManagerCombo = new JComboBox();
        technicianCombo = new JComboBox();
        operatorCombo = new JComboBox();
        engineerCombo = new JComboBox();

        customerJTextField = new JTextField();
        vendorJTextField = new JTextField();
        venderJComboBox = new JComboBox();

        panel.add(NCRNumberLabel);
        panel.add(NCRNumberJTextfield);

        panel.add(departmentLabel);
        panel.add(departmentCombo);

        panel.add(crewLabel);
        panel.add(crewCombo);

        panel.add(pressNumberLabel);
        panel.add(ItemTextfield);

        panel.add(new JLabel(" ", SwingConstants.CENTER));
        panel.add(new JLabel(" ", SwingConstants.CENTER));

        panel.add(new JLabel(" ", SwingConstants.CENTER));
        panel.add(new JLabel(" ", SwingConstants.CENTER));

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

        panel.add(new JLabel("Customer ", SwingConstants.CENTER));
        panel.add(customerJTextField);

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

        panel.add(new JLabel("Vendor ", SwingConstants.CENTER));
        panel.add(vendorJTextField);

        if (viewIn == -1) {
            // outerPanel.add(optionsPanel, BorderLayout.NORTH);
            outerPanel.add(panel, BorderLayout.SOUTH);
        } else if (viewIn == 1) {

            outerPanel.add(panel, BorderLayout.SOUTH);

        }

        return outerPanel;
    }

    public static JPanel createCenterPanel() {

        detailsJTextArea = new JTextArea();
        resultsOfInvestigationJTextArea = new JTextArea();

        JPanel outerPanel = new JPanel(new BorderLayout());
        //   outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel northPanel = new JPanel(new GridLayout(1, 6));
        northPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        northPanel.add(new JLabel(" Defect ", JLabel.CENTER));
        northPanel.add(defectCombo);
        northPanel.add(new JLabel(" General ", JLabel.CENTER));
        northPanel.add(generalCombo);
        northPanel.add(new JLabel(" Area of Origin  ", JLabel.CENTER));
        northPanel.add(areaOfOriginCombo);

        defectJTextArea = new JTextArea(4, 20);
        defectJTextArea.setLineWrap(true);
        defectJTextArea.setEditable(false);
        sp = new JScrollPane(defectJTextArea);

        defectJTextArea2 = new JTextArea(4, 20);
        defectJTextArea2.setLineWrap(true);
        defectJTextArea2.setEditable(false);
        sp2 = new JScrollPane(defectJTextArea2);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2));

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Causes ", SwingConstants.CENTER), BorderLayout.NORTH);
        panel1.add(sp, BorderLayout.CENTER);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(new JLabel("Action ", SwingConstants.CENTER), BorderLayout.NORTH);
        panel2.add(sp2, BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(new JLabel("Enter Details", SwingConstants.CENTER), BorderLayout.NORTH);
        panel3.add(detailsJTextArea, BorderLayout.CENTER);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(new JLabel("Results Of Investigation", SwingConstants.CENTER), BorderLayout.NORTH);
        panel4.add(resultsOfInvestigationJTextArea, BorderLayout.CENTER);

        centerPanel.add(panel1);
        centerPanel.add(panel2);
        centerPanel.add(panel3);
        centerPanel.add(panel4);

        outerPanel.add(northPanel, BorderLayout.NORTH);
        outerPanel.add(centerPanel, BorderLayout.CENTER);

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

                OtherEntryMenu.frame.dispose();
                new OtherEntryMenu(2);

            }
        });
        delete = new JButton("Delete This NCR");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SQLiteConnection.NCROtherDelete(CurrentNCRNumber);
                    frame.dispose();
                    refresh.doClick();
                } catch (SQLException ex) {
                    Logger.getLogger(OtherEntryMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

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
                    SQLiteConnection.NCROtherInsert(
                            (SQLiteConnection.NCRGetHighestNCRNumber() + 1),
                            departmentCombo.getSelectedItem() + "",
                            crewCombo.getSelectedItem() + "",
                            ItemTextfield.getText() + "",
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
                            customerJTextField.getText(),
                            vendorJTextField.getText(),
                            detailsJTextArea.getText(),
                            resultsOfInvestigationJTextArea.getText(),
                            HFINumbersJTextField2.getText() + "",
                            departmentJCombo.getSelectedItem() + "",
                            dateSignOffCompletedJTextField.getText() + "",
                            longTermActionJTextArea.getText() + "");

                    frame.dispose();
                    new OtherEntryMenu(2);
                    setEntryToNCR(SQLiteConnection.NCRGetHighestNCRNumber());

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
                new OtherEntryMenu(2);

            }
        });

        newEntryMode = new JButton("New Entry Mode");
        newEntryMode.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                new OtherEntryMenu(1);

            }
        });

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                StolleEntryMenu.exportToExcel();

            }
        });

        print = new JButton("Print Form");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    createOtherForm(NCRNumberJTextfield.getText() + "");
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
                    SQLiteConnection.NCROtherUpdate(
                            Integer.valueOf(NCRNumberJTextfield.getText() + ""),
                            departmentCombo.getSelectedItem() + "",
                            crewCombo.getSelectedItem() + "",
                            ItemTextfield.getText() + "",
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
                            customerJTextField.getText() + "",
                            vendorJTextField.getText(),
                            detailsJTextArea.getText() + "",
                            resultsOfInvestigationJTextArea.getText() + "",
                            HFINumbersJTextField2.getText() + "",
                            departmentJCombo.getSelectedItem() + "",
                            dateSignOffCompletedJTextField.getText() + "",
                            longTermActionJTextArea.getText() + ""
                    );

                    frame.dispose();
                    new OtherEntryMenu(2);
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

            frame.setTitle("Other Entry Screen (New Entry Mode)");
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

            frame.setTitle("Other Entry Screen (Update Mode)");
            optionsPanel.add(summary);
            optionsPanel.add(delete);
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

    public static void setEntryToNCR(int NCRNumberIn) throws Exception {

        Object[] result = new Object[26];

        result = SQLiteConnection.NCRReturnOtherByNCRNumber(NCRNumberIn);

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
        ItemTextfield.setText(result[4] + "");
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

        customerJTextField.setText(result[17] + "");
        vendorJTextField.setText(result[18] + "");
        detailsJTextArea.setText(result[19] + "");
        resultsOfInvestigationJTextArea.setText(result[20] + "");

        HFINumbersJTextField2.setText(result[21] + "");
        departmentJCombo.setSelectedItem(result[22]);
        dateSignOffCompletedJTextField.setText(result[23] + "");
        longTermActionJTextArea.setText(result[24] + "");

        CurrentNCRNumber = NCRNumberIn;

    }

    public static void createOtherForm(String NCRNumber) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("Reports/OtherNCRReport.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("NCRNumberParameter", NCRNumber); // note here you can add parameters which
        // would be utilized by the report

        InputStream inputStream = new FileInputStream("Reports/OtherNCRReport.jrxml");
        JasperCompileManager.compileReportToFile("Reports/OtherNCRReport.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer view = new net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
        view.setVisible(true);
        view.toFront();

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

            String sql = "select Employees.Name from Employees ORDER BY Name ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            identifiedByCombo.addItem("NA");
            operatorCombo.addItem("NA");
            shiftManagerCombo.addItem("NA");
            technicianCombo.addItem("NA");
            engineerCombo.addItem("NA");
            departmentJCombo.addItem("NA");

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

        // Department
        try {
            String sql = "select Department.Name from Department ORDER BY Name ASC";

            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String employeeName = rs.getString("Name");
                departmentCombo.addItem(employeeName);
            }

        } catch (Exception e) {

        }

        // Defect Group
        try {
            String sql = "SELECT Defects.DefectGroup from Defects GROUP BY DefectGroup ORDER BY DefectGroup  ASC";

            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String employeeName = rs.getString("DefectGroup");
                defectCombo.addItem(employeeName);
            }

        } catch (Exception e) {

        }
    }

    public static void fillGeneralCombo() {

        String defect = defectCombo.getSelectedItem() + "";

        generalCombo.removeAllItems();

        // Defect General
        try {
            String sql = "SELECT Defects.General from Defects WHERE Defects.DefectGroup = \'" + defect + "\' ORDER BY General  ASC";
            System.out.println(sql);
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String employeeName = rs.getString("General");
                generalCombo.addItem(employeeName);
            }

        } catch (Exception e) {

        }

    }

    public static void fillAreaOfOriginCombo() {

        areaOfOriginCombo.removeAllItems();

        String defect2 = generalCombo.getSelectedItem() + "";

        // Defect General
        try {
            String sql = "SELECT Defects.AreaOfOrigin from Defects WHERE Defects.General = \'" + defect2 + "\' ORDER BY AreaOfOrigin  ASC";
            System.out.println(sql);
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String employeeName = rs.getString("AreaOfOrigin");
                areaOfOriginCombo.addItem(employeeName);
            }

            conn.close();

        } catch (Exception e) {

        }

    }

    public static void fillPossibleCauseArea() {

        defectJTextArea.setText(" ");

        String defect3 = generalCombo.getSelectedItem() + "";

        // Defect General
        try {
            String sql = "SELECT Defects.PossibleCauses from Defects WHERE Defects.General = \'" + defect3 + "\' ";
            System.out.println("Defect 3 " + defect3);
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            String employeeName = rs.getString("PossibleCauses");
            defectJTextArea.setText(employeeName);

            conn.close();

        } catch (Exception e) {

        }

        defectJTextArea2.setText(" ");

        String defect4 = generalCombo.getSelectedItem() + "";

        // Defect General
        try {
            String sql = "SELECT Defects.Actions from Defects WHERE Defects.General = \'" + defect4 + "\' ";
            System.out.println("Defect 4 " + defect4);
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            String employeeName = rs.getString("Actions");
            defectJTextArea2.setText(employeeName);

            conn.close();

        } catch (Exception e) {

        }

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

}
