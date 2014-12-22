package com.maintenance.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.text.PlainDocument;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.database.rexam.SQLiteConnection.MaintenanceShellPressProductionReturnEntryByDate;
import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
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

public class MachineOEE {

    static JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3, sevenDayAverage, thirtyDayAverage;
    JLabel dateLabel, dateLabel2;
    static JTextField SP1_2JTextField, SP3JTextField, SP41JTextField, SP42JTextField, SP4JTextField, LinerM1JTextField, LinerM2JTextField, LinerM3JTextField, LinerM4JTextField, CP11JTextField, CP12JTextField, CP21JTextField, CP22JTextField, CP31JTextField, CP32JTextField, CP33JTextField, CP41JTextField, CP42JTextField, CP43JTextField, CP44JTextField, CPAverageJTextField, SevenDayAverageJTextField, ThirtyDayAverageJTextField;
    static JTextField SP1_2MonthlyJTextField, SP3MonthlyJTextField, SP41MonthlyJTextField, SP42MonthlyJTextField, SP4MonthlyJTextField, LinerM1MonthlyJTextField, LinerM2MonthlyJTextField, LinerM3MonthlyJTextField, LinerM4MonthlyJTextField, CP11MonthlyJTextField, CP12MonthlyJTextField, CP21MonthlyJTextField, CP22MonthlyJTextField, CP31MonthlyJTextField, CP32MonthlyJTextField, CP33MonthlyJTextField, CP41MonthlyJTextField, CP42MonthlyJTextField, CP43MonthlyJTextField, CP44MonthlyJTextField, CPAverageMonthlyJTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;

    public static void main(String[] args) throws SQLException {

        new MachineOEE(1, -1);
        // createSummaryScreen();

    }

    public MachineOEE(int idIn, int view) throws SQLException {

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

        frame15.setTitle("Machine OEE");
        frame15.setSize(700, 500);
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

        model = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);

        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                
                System.out.println("Date ActionListener : " + date);

                setMachineToDate(selectedDate);

            }
        });

        UtilDateModel model2 = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        // JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

        SP1_2JTextField = new JTextField();
        SP1_2JTextField.setText("0");

        SP1_2JTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                MachineOEE.calculateAverages();
            }

            @Override
            public void focusGained(FocusEvent e) {

                MachineOEE.calculateAverages();

            }
        });

        SP3JTextField = new JTextField();
        SP3JTextField.setText("0");

        SP41JTextField = new JTextField();
        SP41JTextField.setText("0");

        SP42JTextField = new JTextField();
        SP42JTextField.setText("0");

        SP4JTextField = new JTextField();
        SP4JTextField.setText("0");

        LinerM1JTextField = new JTextField();
        LinerM1JTextField.setText("0");

        LinerM2JTextField = new JTextField();
        LinerM2JTextField.setText("0");

        LinerM3JTextField = new JTextField();
        LinerM3JTextField.setText("0");

        LinerM4JTextField = new JTextField();
        LinerM4JTextField.setText("0");

        CP11JTextField = new JTextField();
        CP11JTextField.setText("0");

        CP12JTextField = new JTextField();
        CP12JTextField.setText("0");

        CP21JTextField = new JTextField();
        CP21JTextField.setText("0");

        CP22JTextField = new JTextField();
        CP22JTextField.setText("0");

        CP31JTextField = new JTextField();
        CP31JTextField.setText("0");

        CP32JTextField = new JTextField();
        CP32JTextField.setText("0");

        CP33JTextField = new JTextField();
        CP33JTextField.setText("0");

        CP41JTextField = new JTextField();
        CP41JTextField.setText("0");

        CP42JTextField = new JTextField();
        CP42JTextField.setText("0");

        CP43JTextField = new JTextField();
        CP43JTextField.setText("0");

        CP44JTextField = new JTextField();
        CP44JTextField.setText("0");

        CPAverageJTextField = new JTextField();
        CPAverageJTextField.setText("0");

        SevenDayAverageJTextField = new JTextField();
        SevenDayAverageJTextField.setText("0");

        ThirtyDayAverageJTextField = new JTextField();
        ThirtyDayAverageJTextField.setText("0");

        SP1_2MonthlyJTextField = new JTextField();
        SP3MonthlyJTextField = new JTextField();
        SP41MonthlyJTextField = new JTextField();
        SP42MonthlyJTextField = new JTextField();
        SP4MonthlyJTextField = new JTextField();
        LinerM1MonthlyJTextField = new JTextField();
        LinerM2MonthlyJTextField = new JTextField();
        LinerM3MonthlyJTextField = new JTextField();
        LinerM4MonthlyJTextField = new JTextField();
        CP11MonthlyJTextField = new JTextField();
        CP12MonthlyJTextField = new JTextField();
        CP21MonthlyJTextField = new JTextField();
        CP22MonthlyJTextField = new JTextField();
        CP31MonthlyJTextField = new JTextField();
        CP32MonthlyJTextField = new JTextField();
        CP33MonthlyJTextField = new JTextField();
        CP41MonthlyJTextField = new JTextField();
        CP42MonthlyJTextField = new JTextField();
        CP43MonthlyJTextField = new JTextField();
        CP44MonthlyJTextField = new JTextField();
        CPAverageMonthlyJTextField = new JTextField();

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceMachineOEECalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    SP1_2MonthlyJTextField.setText(String.valueOf(total[0]));
                    SP3MonthlyJTextField.setText(String.valueOf(total[1]));
                    SP41MonthlyJTextField.setText(String.valueOf(total[2]));
                    SP42MonthlyJTextField.setText(String.valueOf(total[3]));
                    SP4MonthlyJTextField.setText(String.valueOf(total[4]));
                    LinerM1MonthlyJTextField.setText(String.valueOf(total[5]));
                    LinerM2MonthlyJTextField.setText(String.valueOf(total[6]));
                    LinerM3MonthlyJTextField.setText(String.valueOf(total[7]));
                    LinerM4MonthlyJTextField.setText(String.valueOf(total[8]));
                    CP11MonthlyJTextField.setText(String.valueOf(total[9]));
                    CP12MonthlyJTextField.setText(String.valueOf(total[10]));
                    CP21MonthlyJTextField.setText(String.valueOf(total[11]));
                    CP22MonthlyJTextField.setText(String.valueOf(total[12]));
                    CP31MonthlyJTextField.setText(String.valueOf(total[13]));
                    CP32MonthlyJTextField.setText(String.valueOf(total[14]));
                    CP33MonthlyJTextField.setText(String.valueOf(total[15]));
                    CP41MonthlyJTextField.setText(String.valueOf(total[16]));
                    CP42MonthlyJTextField.setText(String.valueOf(total[17]));
                    CP43MonthlyJTextField.setText(String.valueOf(total[18]));
                    CP44MonthlyJTextField.setText(String.valueOf(total[19]));
                    CPAverageJTextField.setText(String.valueOf(total[20]));

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
                    new MachineOEE(1, -1);
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

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                // Get int value of a JTextfield
                try {
                    SQLiteConnection.MaintenanceMachineOEEInsert(SQLiteConnection.MaintenanceMachineOEEGetHighestID() + 1,
                            date,
                            Integer.valueOf(SP1_2JTextField.getText()),
                            Integer.valueOf(SP3JTextField.getText()),
                            Integer.valueOf(SP41JTextField.getText()),
                            Integer.valueOf(SP42JTextField.getText()),
                            Integer.valueOf(SP4JTextField.getText()),
                            Integer.valueOf(LinerM1JTextField.getText()),
                            Integer.valueOf(LinerM2JTextField.getText()),
                            Integer.valueOf(LinerM3JTextField.getText()),
                            Integer.valueOf(LinerM4JTextField.getText()),
                            Integer.valueOf(CP11JTextField.getText()),
                            Integer.valueOf(CP12JTextField.getText()),
                            Integer.valueOf(CP21JTextField.getText()),
                            Integer.valueOf(CP22JTextField.getText()),
                            Integer.valueOf(CP31JTextField.getText()),
                            Integer.valueOf(CP32JTextField.getText()),
                            Integer.valueOf(CP33JTextField.getText()),
                            Integer.valueOf(CP41JTextField.getText()),
                            Integer.valueOf(CP42JTextField.getText()),
                            Integer.valueOf(CP43JTextField.getText()),
                            Integer.valueOf(CP44JTextField.getText())
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
                    Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new MachineOEE(1, -1);
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
                    MachineOEE.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
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
                    new MachineOEE(1, -3);
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

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.MaintenanceMachineOEEUpdate(date,
                            Integer.valueOf(SP1_2JTextField.getText()),
                            Integer.valueOf(SP3JTextField.getText()),
                            Integer.valueOf(SP41JTextField.getText()),
                            Integer.valueOf(SP42JTextField.getText()),
                            Integer.valueOf(SP4JTextField.getText()),
                            Integer.valueOf(LinerM1JTextField.getText()),
                            Integer.valueOf(LinerM2JTextField.getText()),
                            Integer.valueOf(LinerM3JTextField.getText()),
                            Integer.valueOf(LinerM4JTextField.getText()),
                            Integer.valueOf(CP11JTextField.getText()),
                            Integer.valueOf(CP12JTextField.getText()),
                            Integer.valueOf(CP21JTextField.getText()),
                            Integer.valueOf(CP22JTextField.getText()),
                            Integer.valueOf(CP31JTextField.getText()),
                            Integer.valueOf(CP32JTextField.getText()),
                            Integer.valueOf(CP33JTextField.getText()),
                            Integer.valueOf(CP41JTextField.getText()),
                            Integer.valueOf(CP42JTextField.getText()),
                            Integer.valueOf(CP43JTextField.getText()),
                            Integer.valueOf(CP44JTextField.getText()),
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
                    MachineOEE.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] total = SQLiteConnection.MaintenanceMachineOEEGetNextEntryById(currentId);

                    if (total[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        String dateFormatted = (String) total[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        currentId = currentId + 1;

                        SP1_2JTextField.setText(String.valueOf(total[2]));
                        SP3JTextField.setText(String.valueOf(total[3]));
                        SP41JTextField.setText(String.valueOf(total[4]));
                        SP42JTextField.setText(String.valueOf(total[5]));
                        SP4JTextField.setText(String.valueOf(total[6]));
                        LinerM1JTextField.setText(String.valueOf(total[7]));
                        LinerM2JTextField.setText(String.valueOf(total[8]));
                        LinerM3JTextField.setText(String.valueOf(total[9]));
                        LinerM4JTextField.setText(String.valueOf(total[10]));
                        CP11JTextField.setText(String.valueOf(total[11]));
                        CP12JTextField.setText(String.valueOf(total[12]));
                        CP21JTextField.setText(String.valueOf(total[13]));
                        CP22JTextField.setText(String.valueOf(total[14]));
                        CP31JTextField.setText(String.valueOf(total[15]));
                        CP32JTextField.setText(String.valueOf(total[16]));
                        CP33JTextField.setText(String.valueOf(total[17]));
                        CP41JTextField.setText(String.valueOf(total[18]));
                        CP42JTextField.setText(String.valueOf(total[19]));
                        CP43JTextField.setText(String.valueOf(total[20]));
                        CP44JTextField.setText(String.valueOf(total[21]));
                        CPAverageJTextField.setText(String.valueOf(total[22]));

                    }

                    System.out.println("CurrentID " + currentId);

                    // Fill Boxes with results
                    // model.setDate(year2, month2, day2);
                    model.setSelected(true);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });

        previous = new JButton("Previous Record");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] total = SQLiteConnection.MaintenanceMachineOEEGetPreviousEntryById(currentId);

                    if (total[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        String dateFormatted = (String) total[1];
                        System.out.println("Date Formatted : " + dateFormatted);

                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        currentId = currentId - 1;

                        SP1_2JTextField.setText(String.valueOf(total[2]));
                        SP3JTextField.setText(String.valueOf(total[3]));
                        SP41JTextField.setText(String.valueOf(total[4]));
                        SP42JTextField.setText(String.valueOf(total[5]));
                        SP4JTextField.setText(String.valueOf(total[6]));
                        LinerM1JTextField.setText(String.valueOf(total[7]));
                        LinerM2JTextField.setText(String.valueOf(total[8]));
                        LinerM3JTextField.setText(String.valueOf(total[9]));
                        LinerM4JTextField.setText(String.valueOf(total[10]));
                        CP11JTextField.setText(String.valueOf(total[11]));
                        CP12JTextField.setText(String.valueOf(total[12]));
                        CP21JTextField.setText(String.valueOf(total[13]));
                        CP22JTextField.setText(String.valueOf(total[14]));
                        CP31JTextField.setText(String.valueOf(total[15]));
                        CP32JTextField.setText(String.valueOf(total[16]));
                        CP33JTextField.setText(String.valueOf(total[17]));
                        CP41JTextField.setText(String.valueOf(total[18]));
                        CP42JTextField.setText(String.valueOf(total[19]));
                        CP43JTextField.setText(String.valueOf(total[20]));
                        CP44JTextField.setText(String.valueOf(total[21]));
                        CPAverageJTextField.setText(String.valueOf(total[22]));

                    }

                    System.out.println(currentId);

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

        sevenDayAverage = new JButton("7 AVG");
        sevenDayAverage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MachineOEE.setAverage(7);
            }
        });
        thirtyDayAverage = new JButton("30 AVG");
        thirtyDayAverage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MachineOEE.setAverage(30);
            }
        });

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        //buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);
        buttonsPanel.add(sevenDayAverage);
        buttonsPanel.add(thirtyDayAverage);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        JPanel optionPanel1 = new JPanel(new GridLayout(12, 4));
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
            sevenDayAverage.setVisible(false);
            thirtyDayAverage.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("SP1/2", SwingConstants.CENTER));
            optionPanel1.add(SP1_2JTextField);

            optionPanel1.add(new JLabel("SP3", SwingConstants.CENTER));
            optionPanel1.add(SP3JTextField);

            optionPanel1.add(new JLabel("SP41", SwingConstants.CENTER));
            optionPanel1.add(SP41JTextField);

            optionPanel1.add(new JLabel("SP42", SwingConstants.CENTER));
            optionPanel1.add(SP42JTextField);

            optionPanel1.add(new JLabel("SP4", SwingConstants.CENTER));
            optionPanel1.add(SP4JTextField);

            optionPanel1.add(new JLabel("Liner M1", SwingConstants.CENTER));
            optionPanel1.add(LinerM1JTextField);

            optionPanel1.add(new JLabel("Liner M2", SwingConstants.CENTER));
            optionPanel1.add(LinerM2JTextField);

            optionPanel1.add(new JLabel("Liner M3", SwingConstants.CENTER));
            optionPanel1.add(LinerM3JTextField);

            optionPanel1.add(new JLabel("Liner M4", SwingConstants.CENTER));
            optionPanel1.add(LinerM4JTextField);

            optionPanel1.add(new JLabel("CP 11", SwingConstants.CENTER));
            optionPanel1.add(CP11JTextField);

            optionPanel1.add(new JLabel("CP 12", SwingConstants.CENTER));
            optionPanel1.add(CP12JTextField);

            optionPanel1.add(new JLabel("CP 21", SwingConstants.CENTER));
            optionPanel1.add(CP21JTextField);

            optionPanel1.add(new JLabel("CP 22", SwingConstants.CENTER));
            optionPanel1.add(CP22JTextField);

            optionPanel1.add(new JLabel("CP 31", SwingConstants.CENTER));
            optionPanel1.add(CP31JTextField);

            optionPanel1.add(new JLabel("CP 32", SwingConstants.CENTER));
            optionPanel1.add(CP32JTextField);

            optionPanel1.add(new JLabel("CP 33", SwingConstants.CENTER));
            optionPanel1.add(CP33JTextField);

            optionPanel1.add(new JLabel("CP 41", SwingConstants.CENTER));
            optionPanel1.add(CP41JTextField);

            optionPanel1.add(new JLabel("CP 42", SwingConstants.CENTER));
            optionPanel1.add(CP42JTextField);

            optionPanel1.add(new JLabel("CP 43", SwingConstants.CENTER));
            optionPanel1.add(CP43JTextField);

            optionPanel1.add(new JLabel("CP 44", SwingConstants.CENTER));
            optionPanel1.add(CP44JTextField);

            optionPanel1.add(new JLabel("CP Average", SwingConstants.CENTER));
            optionPanel1.add(CPAverageJTextField);

            optionPanel1.add(new JLabel("7 Day Average", SwingConstants.CENTER));
            optionPanel1.add(SevenDayAverageJTextField);

            optionPanel1.add(new JLabel("30 Day Average", SwingConstants.CENTER));
            optionPanel1.add(ThirtyDayAverageJTextField);

            // CP41JTextField, CP42JTextField, CP43JTextField, CP44JTextField, CPAverageJTextField, SevenDayAverageJTextField, ThirtyDayAverageJTextField
        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceMachineOEEGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("SP1/2", SwingConstants.CENTER));
            optionPanel1.add(SP1_2JTextField);

            optionPanel1.add(new JLabel("SP3", SwingConstants.CENTER));
            optionPanel1.add(SP3JTextField);

            optionPanel1.add(new JLabel("SP41", SwingConstants.CENTER));
            optionPanel1.add(SP41JTextField);

            optionPanel1.add(new JLabel("SP42", SwingConstants.CENTER));
            optionPanel1.add(SP42JTextField);

            optionPanel1.add(new JLabel("SP4", SwingConstants.CENTER));
            optionPanel1.add(SP4JTextField);

            optionPanel1.add(new JLabel("Liner M1", SwingConstants.CENTER));
            optionPanel1.add(LinerM1JTextField);

            optionPanel1.add(new JLabel("Liner M2", SwingConstants.CENTER));
            optionPanel1.add(LinerM2JTextField);

            optionPanel1.add(new JLabel("Liner M3", SwingConstants.CENTER));
            optionPanel1.add(LinerM3JTextField);

            optionPanel1.add(new JLabel("Liner M4", SwingConstants.CENTER));
            optionPanel1.add(LinerM4JTextField);

            optionPanel1.add(new JLabel("CP 11", SwingConstants.CENTER));
            optionPanel1.add(CP11JTextField);

            optionPanel1.add(new JLabel("CP 12", SwingConstants.CENTER));
            optionPanel1.add(CP12JTextField);

            optionPanel1.add(new JLabel("LCP 21", SwingConstants.CENTER));
            optionPanel1.add(CP21JTextField);

            optionPanel1.add(new JLabel("CP 22", SwingConstants.CENTER));
            optionPanel1.add(CP22JTextField);

            optionPanel1.add(new JLabel("CP 31", SwingConstants.CENTER));
            optionPanel1.add(CP31JTextField);

            optionPanel1.add(new JLabel("CP 32", SwingConstants.CENTER));
            optionPanel1.add(CP32JTextField);

            optionPanel1.add(new JLabel("CP 33", SwingConstants.CENTER));
            optionPanel1.add(CP33JTextField);

            optionPanel1.add(new JLabel("CP 41", SwingConstants.CENTER));
            optionPanel1.add(CP41JTextField);

            optionPanel1.add(new JLabel("CP 42", SwingConstants.CENTER));
            optionPanel1.add(CP42JTextField);

            optionPanel1.add(new JLabel("CP 43", SwingConstants.CENTER));
            optionPanel1.add(CP43JTextField);

            optionPanel1.add(new JLabel("CP 44", SwingConstants.CENTER));
            optionPanel1.add(CP44JTextField);

            optionPanel1.add(new JLabel("CP Average", SwingConstants.CENTER));
            optionPanel1.add(CPAverageJTextField);

            optionPanel1.add(new JLabel("7 Day Average", SwingConstants.CENTER));
            optionPanel1.add(SevenDayAverageJTextField);

            optionPanel1.add(new JLabel("30 Day Average", SwingConstants.CENTER));
            optionPanel1.add(ThirtyDayAverageJTextField);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(10, 4));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(new JLabel("SP 1/2 ", SwingConstants.CENTER));
            optionPanel1.add(SP1_2MonthlyJTextField);

            optionPanel1.add(new JLabel("SP 3", SwingConstants.CENTER));
            optionPanel1.add(SP3MonthlyJTextField);

            optionPanel1.add(new JLabel("SP 41", SwingConstants.CENTER));
            optionPanel1.add(SP41MonthlyJTextField);

            optionPanel1.add(new JLabel("SP 42", SwingConstants.CENTER));
            optionPanel1.add(SP42MonthlyJTextField);

            optionPanel1.add(new JLabel("SP 4", SwingConstants.CENTER));
            optionPanel1.add(SP4MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner M1", SwingConstants.CENTER));
            optionPanel1.add(LinerM1MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner M2", SwingConstants.CENTER));
            optionPanel1.add(LinerM2MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner M3", SwingConstants.CENTER));
            optionPanel1.add(LinerM3MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner M4", SwingConstants.CENTER));
            optionPanel1.add(LinerM4MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 11", SwingConstants.CENTER));
            optionPanel1.add(CP11MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 12", SwingConstants.CENTER));
            optionPanel1.add(CP12MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 21", SwingConstants.CENTER));
            optionPanel1.add(CP21MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 22", SwingConstants.CENTER));
            optionPanel1.add(CP22MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 31", SwingConstants.CENTER));
            optionPanel1.add(CP31MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 32", SwingConstants.CENTER));
            optionPanel1.add(CP32MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 33", SwingConstants.CENTER));
            optionPanel1.add(CP33MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 41", SwingConstants.CENTER));
            optionPanel1.add(CP41MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 42", SwingConstants.CENTER));
            optionPanel1.add(CP42MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 43", SwingConstants.CENTER));
            optionPanel1.add(CP43MonthlyJTextField);

            optionPanel1.add(new JLabel("CP 44", SwingConstants.CENTER));
            optionPanel1.add(CP44MonthlyJTextField);

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
        //   optionsPanel2.add(addNew);
        optionsPanel2.add(search);
        optionsPanel2.add(monthly);
        optionsPanel2.add(update);
        //  optionsPanel2.add(add);
        optionsPanel2.add(back);
        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        outerPanel.repaint();
        frame15.add(outerPanel);

        frame15.setVisible(true);

        selectedDate = (Date) datePicker.getModel().getValue();

        setMachineToDate(selectedDate);

        SQLiteConnection.AnalyticsUpdate("MachineOEE");

    }

    private void setLastEntry() {

        int highestID = 0;

        try {
            highestID = SQLiteConnection.MaintenanceMachineOEEGetHighestID();
        } catch (SQLException ex) {
            Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
        }

        setMachineOEEToID(highestID);

//          
    }

    public static void calculateAverages() {

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new MachineOEE(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    MachineOEE.createSummaryScreen();
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

                MachineOEE.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Machine OEE Report");
        frameSummary.setSize(1300, 700);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        //    optionsPanel2.add(addNew);
        //   optionsPanel2.add(summary);
        optionsPanel2.add(refresh);
        //   optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);

        JPanel summaryPanel = SQLiteConnection.MaintenanceMachineOEESummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
//        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setMachineOEEToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceMachineOEEReturnEntryByID(currentId);

            if (total[0] == null) {

                SQLiteConnection.infoBox("No Next Result.", "");

            } else {

                // setMachineOEEToID(idIn);
                String dateFormatted = (String) total[1];
                System.out.println("Date Formatted : " + dateFormatted);
                int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                model.setDate(year, month, day);
                model.setSelected(true);

                SP1_2JTextField.setText(String.valueOf(total[2]));
                SP3JTextField.setText(String.valueOf(total[3]));
                SP41JTextField.setText(String.valueOf(total[4]));
                SP42JTextField.setText(String.valueOf(total[5]));
                SP4JTextField.setText(String.valueOf(total[6]));
                LinerM1JTextField.setText(String.valueOf(total[7]));
                LinerM2JTextField.setText(String.valueOf(total[8]));
                LinerM3JTextField.setText(String.valueOf(total[9]));
                LinerM4JTextField.setText(String.valueOf(total[10]));
                CP11JTextField.setText(String.valueOf(total[11]));
                CP12JTextField.setText(String.valueOf(total[12]));
                CP21JTextField.setText(String.valueOf(total[13]));
                CP22JTextField.setText(String.valueOf(total[14]));
                CP31JTextField.setText(String.valueOf(total[15]));
                CP32JTextField.setText(String.valueOf(total[16]));
                CP33JTextField.setText(String.valueOf(total[17]));
                CP41JTextField.setText(String.valueOf(total[18]));
                CP42JTextField.setText(String.valueOf(total[19]));
                CP43JTextField.setText(String.valueOf(total[20]));
                CP44JTextField.setText(String.valueOf(total[21]));
                CPAverageJTextField.setText(String.valueOf(total[22]));

            }

            System.out.println("CurrentID " + currentId);

            // Fill Boxes with results
            // model.setDate(year2, month2, day2);
            model.setSelected(true);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void setAverage(int idIn) {

        int[] total = new int[20];

        try {
            total = SQLiteConnection.MaintenanceMachineOEEgetAverages(idIn);
        } catch (Exception e) {
            System.out.println("Error with Averages.");
        }

        model.setSelected(false);

        SP1_2JTextField.setText(String.valueOf(total[0]));
        SP3JTextField.setText(String.valueOf(total[1]));
        SP41JTextField.setText(String.valueOf(total[2]));
        SP42JTextField.setText(String.valueOf(total[3]));
        SP4JTextField.setText(String.valueOf(total[4]));
        LinerM1JTextField.setText(String.valueOf(total[5]));
        LinerM2JTextField.setText(String.valueOf(total[6]));
        LinerM3JTextField.setText(String.valueOf(total[7]));
        LinerM4JTextField.setText(String.valueOf(total[8]));
        CP11JTextField.setText(String.valueOf(total[9]));
        CP12JTextField.setText(String.valueOf(total[10]));
        CP21JTextField.setText(String.valueOf(total[11]));
        CP22JTextField.setText(String.valueOf(total[12]));
        CP31JTextField.setText(String.valueOf(total[13]));
        CP32JTextField.setText(String.valueOf(total[14]));
        CP33JTextField.setText(String.valueOf(total[15]));
        CP41JTextField.setText(String.valueOf(total[16]));
        CP42JTextField.setText(String.valueOf(total[17]));
        CP43JTextField.setText(String.valueOf(total[18]));
        CP44JTextField.setText(String.valueOf(total[19]));

    }

    public static void exportToExcel() {

        String[] typesArray = {"Machine OEE"};
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

                query = "SELECT * FROM MainMachineOEE WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void setMachineToDate(Date dateIn) {

        try {

            Object[] totalShellPress, totalLiner, totalStolle, stolleDowntime;

            totalShellPress = SQLiteConnection.MaintenanceShellPressProductionReturnEntryByDate(dateIn);
            totalLiner = SQLiteConnection.MaintenanceLinerProductionReturnEntryByDate(dateIn);
            totalStolle = SQLiteConnection.MaintenanceStolleProductionReturnEntryByDate(dateIn);
            stolleDowntime = SQLiteConnection.MaintenanceStolleDowntimeReturnEntryByDate(dateIn);

            if (totalShellPress[2] == null) {totalShellPress[2] = 0;}
            if (totalShellPress[3] == null) {totalShellPress[3] = 0;}
            if (totalShellPress[4] == null) {totalShellPress[4] = 0;}
            if (totalShellPress[5] == null) {totalShellPress[5] = 0;}
            if (totalShellPress[6] == null) {totalShellPress[6] = 0;}
            if (totalShellPress[7] == null) {totalShellPress[7] = 0;}
            
            if (totalLiner[2] == null) {totalLiner[2] = 0;}
            if (totalLiner[3] == null) {totalLiner[3] = 0;}
            if (totalLiner[4] == null) {totalLiner[4] = 0;}
            if (totalLiner[5] == null) {totalLiner[5] = 0;}
            if (totalLiner[6] == null) {totalLiner[6] = 0;}
            if (totalLiner[7] == null) {totalLiner[7] = 0;}
            if (totalLiner[8] == null) {totalLiner[8] = 0;}
            if (totalLiner[9] == null) {totalLiner[9] = 0;}
            if (totalLiner[10] == null) {totalLiner[10] = 0;}
            if (totalLiner[11] == null) {totalLiner[11] = 0;}
            if (totalLiner[12] == null) {totalLiner[12] = 0;}
            if (totalLiner[13] == null) {totalLiner[13] = 0;}
            if (totalLiner[14] == null) {totalLiner[14] = 0;}
            if (totalLiner[15] == null) {totalLiner[15] = 0;}
            if (totalLiner[16] == null) {totalLiner[16] = 0;}
            if (totalLiner[17] == null) {totalLiner[17] = 0;}
            if (totalLiner[18] == null) {totalLiner[18] = 0;}
            if (totalLiner[19] == null) {totalLiner[19] = 0;}
            
            if (totalStolle[2] == null) {totalStolle[2] = 0;}
            if (totalStolle[3] == null) {totalStolle[3] = 0;}
            if (totalStolle[4] == null) {totalStolle[4] = 0;}
            if (totalStolle[5] == null) {totalStolle[5] = 0;}
            if (totalStolle[6] == null) {totalStolle[6] = 0;}
            if (totalStolle[7] == null) {totalStolle[7] = 0;}
            if (totalStolle[8] == null) {totalStolle[8] = 0;}
            if (totalStolle[9] == null) {totalStolle[9] = 0;}
            if (totalStolle[10] == null) {totalStolle[10] = 0;}
            if (totalStolle[11] == null) {totalStolle[11] = 0;}
            if (totalStolle[12] == null) {totalStolle[12] = 0;}
            
            if (stolleDowntime[2] == null) {stolleDowntime[2] = 0.0;}
            if (stolleDowntime[3] == null) {stolleDowntime[3] = 0.0;}
            if (stolleDowntime[4] == null) {stolleDowntime[4] = 0.0;}
            if (stolleDowntime[5] == null) {stolleDowntime[5] = 0.0;}
            if (stolleDowntime[6] == null) {stolleDowntime[6] = 0.0;}
            if (stolleDowntime[7] == null) {stolleDowntime[7] = 0.0;}
            if (stolleDowntime[8] == null) {stolleDowntime[8] = 0.0;}
            if (stolleDowntime[9] == null) {stolleDowntime[9] = 0.0;}
            if (stolleDowntime[10] == null) {stolleDowntime[10] = 0.0;}
            if (stolleDowntime[11] == null) {stolleDowntime[11] = 0.0;}
            if (stolleDowntime[12] == null) {stolleDowntime[12] = 0.0;}
            
            

            int SP1_2JTextFieldInt = (Integer.parseInt(totalShellPress[2] + "") + Integer.parseInt(totalShellPress[3] + ""));
            int SP3JTextFieldInt = (Integer.parseInt(totalShellPress[4] + ""));

            int SP41JTextFieldInt = (Integer.parseInt(totalShellPress[5] + ""));
            int SP42JTextFieldInt = (Integer.parseInt(totalShellPress[6] + ""));
            int SP4JTextFieldInt = (Integer.parseInt(totalShellPress[7] + ""));

            int LinerM1JTextFieldInt = (Integer.parseInt(totalLiner[2] + "") + Integer.parseInt(totalLiner[3] + "") + Integer.parseInt(totalLiner[4] + "") + Integer.parseInt(totalLiner[5] + ""));
            int LinerM2JTextFieldInt = (Integer.parseInt(totalLiner[6] + "") + Integer.parseInt(totalLiner[7] + "") + Integer.parseInt(totalLiner[8] + "") + Integer.parseInt(totalLiner[9] + ""));
            int LinerM3JTextFieldInt = (Integer.parseInt(totalLiner[10] + "") + Integer.parseInt(totalLiner[11] + "") + Integer.parseInt(totalLiner[12] + "") + Integer.parseInt(totalLiner[13] + ""));
            int LinerM4JTextFieldInt = (Integer.parseInt(totalLiner[14] + "") + Integer.parseInt(totalLiner[15] + "") + Integer.parseInt(totalLiner[16] + "") + Integer.parseInt(totalLiner[17] + "") + Integer.parseInt(totalLiner[18] + "") + Integer.parseInt(totalLiner[19] + ""));

            int CP11JTextFieldInt = Integer.parseInt(totalStolle[2] + "");
            int CP12JTextFieldInt = Integer.parseInt(totalStolle[3] + "");
            int CP21JTextFieldInt = Integer.parseInt(totalStolle[4] + "");
            int CP22JTextFieldInt = Integer.parseInt(totalStolle[5] + "");
            int CP31JTextFieldInt = Integer.parseInt(totalStolle[6] + "");
            int CP32JTextFieldInt = Integer.parseInt(totalStolle[7] + "");
            int CP33JTextFieldInt = Integer.parseInt(totalStolle[8] + "");
            int CP41JTextFieldInt = Integer.parseInt(totalStolle[9] + "");
            int CP42JTextFieldInt = Integer.parseInt(totalStolle[10] + "");
            int CP43JTextFieldInt = Integer.parseInt(totalStolle[11] + "");
            int CP44JTextFieldInt = Integer.parseInt(totalStolle[12] + "");

            SP1_2JTextField.setText(String.valueOf("0"));
            SP3JTextField.setText(String.valueOf("0"));
            SP41JTextField.setText(String.valueOf("0"));
            SP42JTextField.setText(String.valueOf("0"));
            SP4JTextField.setText(String.valueOf("0"));
            LinerM1JTextField.setText(String.valueOf("0"));
            LinerM2JTextField.setText(String.valueOf("0"));
            LinerM3JTextField.setText(String.valueOf("0"));
            LinerM4JTextField.setText(String.valueOf("0"));
            CP11JTextField.setText(String.valueOf("0"));
            CP12JTextField.setText(String.valueOf("0"));
            CP21JTextField.setText(String.valueOf("0"));
            CP22JTextField.setText(String.valueOf("0"));
            CP31JTextField.setText(String.valueOf("0"));
            CP32JTextField.setText(String.valueOf("0"));
            CP33JTextField.setText(String.valueOf("0"));
            CP41JTextField.setText(String.valueOf("0"));
            CP42JTextField.setText(String.valueOf("0"));
            CP43JTextField.setText(String.valueOf("0"));
            CP44JTextField.setText(String.valueOf("0"));
            CPAverageJTextField.setText(String.valueOf("0"));
                  
            System.out.println("6789 : " + LinerM2JTextFieldInt);
            
            Double SP1_2JTextFieldDouble = ((SP1_2JTextFieldInt+0.0) / 10713600+0.0)*100 ; // LinerM1JTextField
            Double SP3JTextFieldDouble = ((SP3JTextFieldInt+0.0) / 10713600+0.0)*100 ; // LinerM1JTextField
            Double SP41JTextFieldDouble = ((SP41JTextFieldInt+0.0) / 10713600+0.0)*100 ; // LinerM1JTextField
            Double SP42JTextFieldDouble = ((SP42JTextFieldInt+0.0) / 10713600+0.0)*100 ; // LinerM1JTextField
            Double SP4JTextFieldDouble = ((SP4JTextFieldInt+0.0) / 10713600+0.0)*100 ; // LinerM1JTextField
                      
            Double LinerM1JTextFieldDouble = ((LinerM1JTextFieldInt+0.0) / 8640000+0.0)*100 ; // LinerM1JTextField
            Double LinerM3JTextFieldDouble = ((LinerM3JTextFieldInt+0.0) / 10368000+0.0)*100 ; // LinerM3JTextField
            Double LinerM4JTextFieldDouble = ((LinerM4JTextFieldInt+0.0) / 9216000+0.0)*100 ; // LinerM4JTextField
            
            Double CP11JTextFieldDouble = ((int)totalStolle[2]+0.0) / (124200 * (24-((Double)stolleDowntime[2]+0.0)))*100 ;
            Double CP12JTextFieldDouble = ((int)totalStolle[3]+0.0) / (124200 * (24-((Double)stolleDowntime[3]+0.0)))*100 ;
            
            Double CP21JTextFieldDouble = ((int)totalStolle[4]+0.0) / (117000 * (24-((Double)stolleDowntime[4]+0.0)))*100 ;
            Double CP22JTextFieldDouble = ((int)totalStolle[5]+0.0) / (117000 * (24-((Double)stolleDowntime[5]+0.0)))*100 ;
                 
            Double CP31JTextFieldDouble = ((int)totalStolle[6]+0.0) / (124200 * (24-((Double)stolleDowntime[6]+0.0)))*100 ;
            Double CP32JTextFieldDouble = ((int)totalStolle[7]+0.0) / (124200 * (24-((Double)stolleDowntime[7]+0.0)))*100 ;
            
            Double CP33JTextFieldDouble = ((int)totalStolle[8]+0.0) / (122400 * (24-((Double)stolleDowntime[8]+0.0)))*100 ;
            
            Double CP41JTextFieldDouble = ((int)totalStolle[9]) / (125100 * (24-((Double)stolleDowntime[9]+0.0)))*100 ;
            Double CP42JTextFieldDouble = ((int)totalStolle[10]) / (125100 * (24-((Double)stolleDowntime[10]+0.0)))*100 ;
            
            Double CP43JTextFieldDouble = ((int)totalStolle[11]) / (125100 * (24-((Double)stolleDowntime[11]+0.0)))*100 ;           
            Double CP44JTextFieldDouble = ((int)totalStolle[12]) / (180000 * (24-((Double)stolleDowntime[12]+0.0)))*100 ;
            
        //    0
             
                                     
            
            //System.out.println("AAA " + 125100 * (24-((Double)stolleDowntime[11]+0.0))*100);
            System.out.println("AAA " + ((int)totalStolle[12]));
            //System.out.println("BBB " + (180000 * (24-((Double)stolleDowntime[11]+0.0)))*100);
            

            SP1_2JTextField.setText(Math.round(SP1_2JTextFieldDouble)+"");
            SP3JTextField.setText(Math.round(SP3JTextFieldDouble)+"");
            SP41JTextField.setText(Math.round(SP41JTextFieldDouble)+"");
            SP42JTextField.setText(Math.round(SP42JTextFieldDouble)+"");
            SP4JTextField.setText(Math.round(SP4JTextFieldDouble)+"");

            LinerM1JTextField.setText(Math.round(LinerM1JTextFieldDouble)+"");
            LinerM2JTextField.setText("0");
            LinerM3JTextField.setText(Math.round(LinerM3JTextFieldDouble)+"");
            LinerM4JTextField.setText(Math.round(LinerM4JTextFieldDouble)+"");

            CP11JTextField.setText(Math.round(CP11JTextFieldDouble)+"");
            CP12JTextField.setText(Math.round(CP12JTextFieldDouble)+"");
            CP21JTextField.setText(Math.round(CP21JTextFieldDouble)+"");
            CP22JTextField.setText(Math.round(CP22JTextFieldDouble)+"");
            CP31JTextField.setText(Math.round(CP31JTextFieldDouble)+"");
            CP32JTextField.setText(Math.round(CP32JTextFieldDouble)+"");
            CP33JTextField.setText(Math.round(CP33JTextFieldDouble)+"");

            CP41JTextField.setText(Math.round(CP41JTextFieldDouble)+"");
            CP42JTextField.setText(Math.round(CP42JTextFieldDouble)+"");
            CP43JTextField.setText(Math.round(CP43JTextFieldDouble)+"");
            CP44JTextField.setText(Math.round(CP44JTextFieldDouble)+"");
            
            double cpAverage = (CP11JTextFieldDouble + CP12JTextFieldDouble + CP21JTextFieldDouble + CP22JTextFieldDouble + CP31JTextFieldDouble + CP32JTextFieldDouble + CP33JTextFieldDouble + CP41JTextFieldDouble + CP42JTextFieldDouble + CP43JTextFieldDouble + CP44JTextFieldDouble)/11;
            
            CPAverageJTextField.setText(Math.round(cpAverage)+"");
            

//            CPAverageJTextField.setText(String.valueOf(totalShellPress[22]));
        } catch (Exception ex) {
            Logger.getLogger(MachineOEE.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
