package com.maintenance.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class EHSStatutoryChecks {

    static JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;
    static JTextField RefNumberJTextField, IdentificationJTextField, LocationJTextField, LastExamJTextField, SWLJTextField, CertIssuerJTextField, DefectTypeJTextField, CorrectiveActionJTextField, SerialNumberJTextField, FrequencyJTextField, NextExamJTextField, DaysRemainingJTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel lastExamModel, nextExamModel, excelModel1, excelModel2;
    static JDatePanelImpl lastExamPanel, nextExamPanel, excelDate1, excelDate2;
    static JDatePickerImpl lastExamPicker, nextExamPicker, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;

    public static void main(String[] args) throws SQLException {

        new EHSStatutoryChecks(1, -1);

    }

    public EHSStatutoryChecks(int idIn, int view) throws SQLException {

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

        JFrame frame15 = new JFrame();
        // frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame15.setTitle("EHS Statutory Checks");
        frame15.setSize(360, 500);
        frame15.setLocationRelativeTo(null);
        outerPanel.setLayout(new BorderLayout());

        // Create Buttons , Labels, Checkboxes etc...
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month) - 1;
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);

        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
            "December"};
        String[] years = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};
        monthCombo = new JComboBox(monthArray);
        yearCombo = new JComboBox(years);

        lastExamModel = new UtilDateModel();
        lastExamPanel = new JDatePanelImpl(lastExamModel);
        lastExamPicker = new JDatePickerImpl(lastExamPanel);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        // JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

        nextExamModel = new UtilDateModel();
        nextExamPanel = new JDatePanelImpl(nextExamModel);
        nextExamPicker = new JDatePickerImpl(nextExamPanel);

        RefNumberJTextField = new JTextField();
        IdentificationJTextField = new JTextField();
        LocationJTextField = new JTextField();
        LastExamJTextField = new JTextField();
        SWLJTextField = new JTextField();
        CertIssuerJTextField = new JTextField();
        DefectTypeJTextField = new JTextField();
        CorrectiveActionJTextField = new JTextField();
        SerialNumberJTextField = new JTextField();
        FrequencyJTextField = new JTextField();
        NextExamJTextField = new JTextField();
        DaysRemainingJTextField = new JTextField();

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceEHSStatutoryChecksCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new EHSStatutoryChecks(1, -1);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame15.dispose();

            }
        });

        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) lastExamPicker.getModel().getValue();
                String lastDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                selectedDate = (Date) nextExamPicker.getModel().getValue();
                String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                updateDates();

                // Get int value of a JTextfield
                try {
                    SQLiteConnection.MaintenanceEHSStatutoryChecksInsert(
                            SQLiteConnection.MaintenanceEHSStatutoryChecksGetHighestID() + 1,
                            RefNumberJTextField.getText(),
                            IdentificationJTextField.getText(),
                            LocationJTextField.getText(),
                            lastDate,
                            SWLJTextField.getText(),
                            CertIssuerJTextField.getText(),
                            DefectTypeJTextField.getText(),
                            CorrectiveActionJTextField.getText(),
                            SerialNumberJTextField.getText(),
                            Double.parseDouble(FrequencyJTextField.getText()),
                            nextDate,
                            Double.parseDouble(DaysRemainingJTextField.getText())
                    );

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
                frame15.dispose();
                try {
                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(EHSStatutoryChecks.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new EHSStatutoryChecks(1, -1);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame15.dispose();

            }
        });

        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    EHSStatutoryChecks.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(EHSStatutoryChecks.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame15.dispose();

            }
        });

        monthly = new JButton("Monthly");
        monthly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new EHSStatutoryChecks(1, -3);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame15.dispose();

            }
        });

        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) lastExamPicker.getModel().getValue();
                String lastDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                selectedDate = (Date) nextExamPicker.getModel().getValue();
                String nextDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                updateDates();

                System.out.println("CurrentrID " + currentId + "");

                try {
                    SQLiteConnection.MaintenanceEHSStatutoryChecksUpdate(
                            RefNumberJTextField.getText(),
                            IdentificationJTextField.getText(),
                            LocationJTextField.getText(),
                            lastDate,
                            SWLJTextField.getText(),
                            CertIssuerJTextField.getText(),
                            DefectTypeJTextField.getText(),
                            CorrectiveActionJTextField.getText(),
                            SerialNumberJTextField.getText(),
                            Double.parseDouble(FrequencyJTextField.getText()),
                            nextDate,
                            Double.parseDouble(DaysRemainingJTextField.getText()),
                            currentId
                    );

                    frame15.dispose();
                    createSummaryScreen();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame15.dispose();
                try {
                    EHSStatutoryChecks.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(EHSStatutoryChecks.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        find = new JButton("Find Record");

        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] result = SQLiteConnection.MaintenanceEHSStatutoryChecksGetNextEntryById(currentId);

                    if (result[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        currentId++;

                        // Date
                        String dateFormatted = (String) result[4];
                        System.out.println("Date Formatted : " + dateFormatted);

                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        String dateFormatted2 = (String) result[11];
                        System.out.println("Date Formatted : " + dateFormatted);

                        int day2 = Integer.parseInt(dateFormatted2.substring(8, 10)); // Correct
                        int month2 = Integer.parseInt(dateFormatted2.substring(5, 7)) - 1; // Correct
                        int year2 = Integer.parseInt(dateFormatted2.substring(0, 4)); // Correct

                        lastExamModel.setDate(year, month, day);
                        lastExamModel.setSelected(true);

                        nextExamModel.setDate(year2, month2, day2);
                        nextExamModel.setSelected(true);

                        RefNumberJTextField.setText(String.valueOf(result[1]));
                        IdentificationJTextField.setText(String.valueOf(result[2]));
                        LocationJTextField.setText(String.valueOf(result[3]));

                        SWLJTextField.setText(String.valueOf(result[5]));
                        CertIssuerJTextField.setText(String.valueOf(result[6]));
                        DefectTypeJTextField.setText(String.valueOf(result[7]));
                        CorrectiveActionJTextField.setText(String.valueOf(result[8]));
                        SerialNumberJTextField.setText(String.valueOf(result[9]));
                        FrequencyJTextField.setText(Double.parseDouble(result[10] + "") + "");

                        DaysRemainingJTextField.setText(Double.parseDouble(result[12] + "") + "");

                    }

                    System.out.println("CurrentID " + currentId);

                    // Fill Boxes with results
                    // model.setDate(year2, month2, day2);
                    lastExamModel.setSelected(true);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

                setLastEntry();

            }
        });

        previous = new JButton("Previous Record");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] result = SQLiteConnection.MaintenanceEHSStatutoryChecksGetPreviousEntryById(currentId);

                    if (result[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        currentId = currentId - 1;

                        // Date
                        String dateFormatted = (String) result[4];
                        System.out.println("Date Formatted : " + dateFormatted);

                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        String dateFormatted2 = (String) result[11];
                        System.out.println("Date Formatted : " + dateFormatted);

                        int day2 = Integer.parseInt(dateFormatted2.substring(8, 10)); // Correct
                        int month2 = Integer.parseInt(dateFormatted2.substring(5, 7)) - 1; // Correct
                        int year2 = Integer.parseInt(dateFormatted2.substring(0, 4)); // Correct

                        lastExamModel.setDate(year, month, day);
                        lastExamModel.setSelected(true);

                        nextExamModel.setDate(year2, month2, day2);
                        nextExamModel.setSelected(true);

                        RefNumberJTextField.setText(String.valueOf(result[1]));
                        IdentificationJTextField.setText(String.valueOf(result[2]));
                        LocationJTextField.setText(String.valueOf(result[3]));

                        SWLJTextField.setText(String.valueOf(result[5]));
                        CertIssuerJTextField.setText(String.valueOf(result[6]));
                        DefectTypeJTextField.setText(String.valueOf(result[7]));
                        CorrectiveActionJTextField.setText(String.valueOf(result[8]));
                        SerialNumberJTextField.setText(String.valueOf(result[9]));
                        FrequencyJTextField.setText(Double.parseDouble(result[10] + "") + "");

                        DaysRemainingJTextField.setText(Double.parseDouble(result[12] + "") + "");

                    }

                    System.out.println(currentId);
                    setLastEntry();

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        //buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        JPanel optionPanel1 = new JPanel(new GridLayout(12, 2));
		// optionPanel1.setBackground(Color.GRAY);

        // ComboPanelMonthly
        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(monthCombo);
        comboPanel.add(yearCombo);
        comboPanel.add(go);

        // Adding
        if (view == -1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            back.setVisible(false);
            summary.setVisible(false);

            optionPanel1.add(new JLabel("Ref. No.", SwingConstants.CENTER));
            optionPanel1.add(RefNumberJTextField);

            optionPanel1.add(new JLabel("Identification", SwingConstants.CENTER));
            optionPanel1.add(IdentificationJTextField);

            optionPanel1.add(new JLabel("Location", SwingConstants.CENTER));
            optionPanel1.add(LocationJTextField);

            optionPanel1.add(new JLabel("Last Exam", SwingConstants.CENTER));
            optionPanel1.add(lastExamPicker);

            optionPanel1.add(new JLabel("SWL", SwingConstants.CENTER));
            optionPanel1.add(SWLJTextField);

            optionPanel1.add(new JLabel("Cert. Issuer", SwingConstants.CENTER));
            optionPanel1.add(CertIssuerJTextField);

            optionPanel1.add(new JLabel("Defect Type (A, B, C)", SwingConstants.CENTER));
            optionPanel1.add(DefectTypeJTextField);

            optionPanel1.add(new JLabel("Corrective Action", SwingConstants.CENTER));
            optionPanel1.add(CorrectiveActionJTextField);

            optionPanel1.add(new JLabel("Serial Number", SwingConstants.CENTER));
            optionPanel1.add(SerialNumberJTextField);

            optionPanel1.add(new JLabel("Frequency (Days)", SwingConstants.CENTER));
            optionPanel1.add(FrequencyJTextField);

            optionPanel1.add(new JLabel("Next Exam", SwingConstants.CENTER));
            optionPanel1.add(nextExamPicker);

            optionPanel1.add(new JLabel("Days Remaining", SwingConstants.CENTER));
            optionPanel1.add(DaysRemainingJTextField);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceEHSStatutoryChecksGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            lastExamModel.setDate(yearInt, monthInt, dayInt);
            lastExamModel.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(new JLabel("Ref. No.", SwingConstants.CENTER));
            optionPanel1.add(RefNumberJTextField);

            optionPanel1.add(new JLabel("Identification", SwingConstants.CENTER));
            optionPanel1.add(IdentificationJTextField);

            optionPanel1.add(new JLabel("Location", SwingConstants.CENTER));
            optionPanel1.add(LocationJTextField);

            optionPanel1.add(new JLabel("Last Exam", SwingConstants.CENTER));
            optionPanel1.add(lastExamPicker);

            optionPanel1.add(new JLabel("SWL", SwingConstants.CENTER));
            optionPanel1.add(SWLJTextField);

            optionPanel1.add(new JLabel("Cert. Issuer", SwingConstants.CENTER));
            optionPanel1.add(CertIssuerJTextField);

            optionPanel1.add(new JLabel("Defect Type (A, B, C)", SwingConstants.CENTER));
            optionPanel1.add(DefectTypeJTextField);

            optionPanel1.add(new JLabel("Corrective Action", SwingConstants.CENTER));
            optionPanel1.add(CorrectiveActionJTextField);

            optionPanel1.add(new JLabel("Serial Number", SwingConstants.CENTER));
            optionPanel1.add(SerialNumberJTextField);

            optionPanel1.add(new JLabel("Frequency (Days)", SwingConstants.CENTER));
            optionPanel1.add(FrequencyJTextField);

            optionPanel1.add(new JLabel("Next Exam", SwingConstants.CENTER));
            optionPanel1.add(nextExamPicker);

            optionPanel1.add(new JLabel("Days Remaining", SwingConstants.CENTER));
            optionPanel1.add(DaysRemainingJTextField);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(16, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            monthly.setVisible(false);
            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            search.setVisible(false);
            update.setVisible(false);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        JPanel commentsPanel = new JPanel();

        outerPanel.add(optionPanel1, BorderLayout.CENTER);

        // Options Panel 2
        JPanel optionsPanel2 = new JPanel(new FlowLayout());
        optionsPanel2.add(summary);
        optionsPanel2.add(addNew);
        optionsPanel2.add(search);
        //optionsPanel2.add(monthly);
        optionsPanel2.add(update);
        optionsPanel2.add(add);
        optionsPanel2.add(back);
        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        outerPanel.repaint();
        frame15.add(outerPanel);

        frame15.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("EHSStatutoryChecks");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceEHSStatutoryChecksGetHighestID();
            System.out.println("MaintenanceEHSStatutoryChecksGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] result = new Object[16];
        try {
            result = SQLiteConnection.MaintenanceEHSStatutoryChecksReturnEntryByID(highestID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Date " + result[4]);

        // Date
        String dateFormatted = (String) result[4];
        System.out.println("Date Formatted : " + dateFormatted);

        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

        String dateFormatted2 = (String) result[11];
        System.out.println("Date Formatted : " + dateFormatted);

        int day2 = Integer.parseInt(dateFormatted2.substring(8, 10)); // Correct
        int month2 = Integer.parseInt(dateFormatted2.substring(5, 7)) - 1; // Correct
        int year2 = Integer.parseInt(dateFormatted2.substring(0, 4)); // Correct

        lastExamModel.setDate(year, month, day);
        lastExamModel.setSelected(true);

        nextExamModel.setDate(year2, month2, day2);
        nextExamModel.setSelected(true);

        RefNumberJTextField.setText(String.valueOf(result[1]));
        IdentificationJTextField.setText(String.valueOf(result[2]));
        LocationJTextField.setText(String.valueOf(result[3]));

        SWLJTextField.setText(String.valueOf(result[5]));
        CertIssuerJTextField.setText(String.valueOf(result[6]));
        DefectTypeJTextField.setText(String.valueOf(result[7]));
        CorrectiveActionJTextField.setText(String.valueOf(result[8]));
        SerialNumberJTextField.setText(String.valueOf(result[9]));
        FrequencyJTextField.setText(Double.parseDouble(result[10] + "") + "");

        DaysRemainingJTextField.setText(Double.parseDouble(result[12] + "") + "");

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new EHSStatutoryChecks(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(EHSStatutoryChecks.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    EHSStatutoryChecks.createSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        JButton print = new JButton("Print Report");
        JButton ExportToExcel = new JButton("Export To Excel");

        ExportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                EHSStatutoryChecks.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("EHS Statutory Checks");
        frameSummary.setSize(1300, 700);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(refresh);
        //   optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);

        JPanel summaryPanel = SQLiteConnection.MaintenanceEHSStatutoryChecksSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setEHSStatutoryChecksToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] result = SQLiteConnection.MaintenanceEHSStatutoryChecksReturnEntryByID(currentId);

            if (result[0] == null) {

                SQLiteConnection.infoBox("No Next Result.", "");

            } else {

                // Date
                String dateFormatted = (String) result[4];
                System.out.println("Date Formatted : " + dateFormatted);

                int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                String dateFormatted2 = (String) result[11];
                System.out.println("Date Formatted : " + dateFormatted);

                int day2 = Integer.parseInt(dateFormatted2.substring(8, 10)); // Correct
                int month2 = Integer.parseInt(dateFormatted2.substring(5, 7)) - 1; // Correct
                int year2 = Integer.parseInt(dateFormatted2.substring(0, 4)); // Correct

                lastExamModel.setDate(year, month, day);
                lastExamModel.setSelected(true);

                nextExamModel.setDate(year2, month2, day2);
                nextExamModel.setSelected(true);

                RefNumberJTextField.setText(String.valueOf(result[1]));
                IdentificationJTextField.setText(String.valueOf(result[2]));
                LocationJTextField.setText(String.valueOf(result[3]));

                SWLJTextField.setText(String.valueOf(result[5]));
                CertIssuerJTextField.setText(String.valueOf(result[6]));
                DefectTypeJTextField.setText(String.valueOf(result[7]));
                CorrectiveActionJTextField.setText(String.valueOf(result[8]));
                SerialNumberJTextField.setText(String.valueOf(result[9]));
                FrequencyJTextField.setText(Double.parseDouble(result[10] + "") + "");

                DaysRemainingJTextField.setText(Double.parseDouble(result[12] + "") + "");

                currentId = Integer.valueOf(result[1] + "");

            }

            System.out.println("CurrentID " + currentId);

            // Fill Boxes with results
            // model.setDate(year2, month2, day2);
            lastExamModel.setSelected(true);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        updateDates() ;

    }

    private static void updateDates() {

        // Extract date from lastExamModel       
        Date lastDate = lastExamModel.getValue();

        // Add 182 Days onto Date
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        c1.setTime(lastDate); // Now use today date.
        c1.add(Calendar.DATE, 152); // Adding 5 days
        System.out.println("Date : " + c1);
        String output1 = sdf3.format(c1.getTime());
        System.out.println("Output 99 : " + output1);

        // Set NextExamDate to c1        
        String modifiedDate = output1;
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month);
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);
        nextExamModel.setDate(yearInt, monthInt, dayInt);

        // Calculate Days remaining
        // Days remaining = NextDate - today       
        Date nextDate = nextExamModel.getValue();
        Date todaysDate = new Date();
        long diffInMillies = nextDate.getTime() - todaysDate.getTime();
        System.out.println("Diff In Millies : " + diffInMillies);
        int milliesToDays = (int) (diffInMillies / (1000 * 60 * 60 * 24));
        DaysRemainingJTextField.setText(milliesToDays + "");
        DaysRemainingJTextField.setEditable(false);

    }

    public static void exportToExcel() {

        String[] typesArray = {"EHS Statutory Checks"};
        JComboBox typeCombo = new JComboBox(typesArray);
        JCheckBox datesCheck = new JCheckBox();

        excelModel1 = new UtilDateModel(new Date());
        excelModel2 = new UtilDateModel(new Date());
        excelDate1 = new JDatePanelImpl(excelModel1);
        excelDate2 = new JDatePanelImpl(excelModel2);
        excelPicker1 = new JDatePickerImpl(excelDate1);
        excelPicker2 = new JDatePickerImpl(excelDate2);

        query = "";

        String[] sortTypesArray = {"Date"};
        JComboBox sortTypesCombo = new JComboBox(sortTypesArray);

        String[] itemsArray = typesArray;
        JComboBox itemsCombo = new JComboBox(itemsArray);

        JButton export, cancel;

        cancel = new JButton("Cancel");
        export = new JButton("Export");

        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) excelPicker1.getModel().getValue());
                String modifiedDate2 = new SimpleDateFormat("yyyy-MM-dd").format((Date) excelPicker2.getModel().getValue());
                item = typeCombo.getSelectedItem() + "";

                System.out.println("Modified Date 1 : " + modifiedDate1);
                System.out.println("Modified Date 2 : " + modifiedDate2);
                System.out.println("Item : " + item);

                query = "SELECT * FROM MainEHSStatutoryChecks;";

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
            table = SQLiteConnection.OptimeProductionReturnJTable(type, query);

        } catch (SQLException ex) {
            Logger.getLogger(EHSStatutoryChecks.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create JTable with Query
        // Export to Excel File
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet1 = workBook.createSheet("Main");
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

        sheet1.setDefaultColumnWidth(14);

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
            row = sheet1.createRow((rows + 2));
        }

        try {
            FileOutputStream output = new FileOutputStream("ExcelFiles/MaintenanceExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("ExcelFiles/MaintenanceExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
