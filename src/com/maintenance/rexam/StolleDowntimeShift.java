package com.maintenance.rexam;

// ActionListener fill Daily Total Boxes
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
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class StolleDowntimeShift {

    static JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;
    static JTextField Stolle11JTextField1, Stolle12JTextField1, Stolle21JTextField1, Stolle22JTextField1, Stolle31JTextField1, Stolle32JTextField1, Stolle33JTextField1, Stolle41JTextField1, Stolle42JTextField1, Stolle43JTextField1, Stolle44JTextField1, StolleDailyTotalJTextField1, StolleDailyAverageJTextField1;
    static JTextField Stolle11JTextField2, Stolle12JTextField2, Stolle21JTextField2, Stolle22JTextField2, Stolle31JTextField2, Stolle32JTextField2, Stolle33JTextField2, Stolle41JTextField2, Stolle42JTextField2, Stolle43JTextField2, Stolle44JTextField2, StolleDailyTotalJTextField2, StolleDailyAverageJTextField2;
    static JTextField Stolle11MonthlyJTextField, Stolle12MonthlyJTextField, Stolle21MonthlyJTextField, Stolle22MonthlyJTextField, Stolle31MonthlyJTextField, Stolle32MonthlyJTextField, Stolle33MonthlyJTextField, Stolle41MonthlyJTextField, Stolle42MonthlyJTextField, Stolle43MonthlyJTextField, StolleMonthlyTotalJTextField, StolleMonthlyAverageJTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static JTextArea commentsBox;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;

    static int dailyTotalInt, dailyAverageInt;

    public static void main(String[] args) throws SQLException {

        new StolleDowntimeShift(1, -1);

    }

    public StolleDowntimeShift(int idIn, int view) throws SQLException {

        // Add a view to analytics.
       

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

        dailyTotalInt = 0;
        dailyAverageInt = 0;

        JFrame frame15 = new JFrame();
        // frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame15.setTitle("Stolle Downtime Shift");
        frame15.setSize(650, 500);
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

        UtilDateModel model2 = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        // JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

        Stolle11JTextField1 = new JTextField();
        Stolle11JTextField1.setText("0.0");

        Stolle12JTextField1 = new JTextField();
        Stolle12JTextField1.setText("0.0");

        Stolle21JTextField1 = new JTextField();
        Stolle21JTextField1.setText("0.0");

        Stolle22JTextField1 = new JTextField();
        Stolle22JTextField1.setText("0.0");

        Stolle31JTextField1 = new JTextField();
        Stolle31JTextField1.setText("0.0");

        Stolle32JTextField1 = new JTextField();
        Stolle32JTextField1.setText("0.0");

        Stolle33JTextField1 = new JTextField();
        Stolle33JTextField1.setText("0.0");

        Stolle41JTextField1 = new JTextField();
        Stolle41JTextField1.setText("0.0");

        Stolle42JTextField1 = new JTextField();
        Stolle42JTextField1.setText("0.0");

        Stolle43JTextField1 = new JTextField();
        Stolle43JTextField1.setText("0.0");

        Stolle44JTextField1 = new JTextField();
        Stolle44JTextField1.setText("0.0");

        StolleDailyTotalJTextField1 = new JTextField();
        StolleDailyTotalJTextField1.setText("0.0");

        StolleDailyAverageJTextField1 = new JTextField();
        StolleDailyAverageJTextField1.setText("0.0");

        Stolle11JTextField2 = new JTextField();
        Stolle11JTextField2.setText("0.0");

        Stolle12JTextField2 = new JTextField();
        Stolle12JTextField2.setText("0.0");

        Stolle21JTextField2 = new JTextField();
        Stolle21JTextField2.setText("0.0");

        Stolle22JTextField2 = new JTextField();
        Stolle22JTextField2.setText("0.0");

        Stolle31JTextField2 = new JTextField();
        Stolle31JTextField2.setText("0.0");

        Stolle32JTextField2 = new JTextField();
        Stolle32JTextField2.setText("0.0");

        Stolle33JTextField2 = new JTextField();
        Stolle33JTextField2.setText("0.0");

        Stolle41JTextField2 = new JTextField();
        Stolle41JTextField2.setText("0.0");

        Stolle42JTextField2 = new JTextField();
        Stolle42JTextField2.setText("0.0");

        Stolle43JTextField2 = new JTextField();
        Stolle43JTextField2.setText("0.0");

        Stolle44JTextField2 = new JTextField();
        Stolle44JTextField2.setText("0.0");

        StolleDailyTotalJTextField2 = new JTextField();
        StolleDailyTotalJTextField2.setText("0.0");

        StolleDailyAverageJTextField2 = new JTextField();
        StolleDailyAverageJTextField2.setText("0.0");

        commentsBox = new JTextArea();
        commentsBox.setLineWrap(true);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new StolleDowntimeShift(1, -1);
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
                
                try {
                    
                    try {
                        selectedDate = (Date) datePicker.getModel().getValue();
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                        Object[] array = SQLiteConnection.MaintenanceStolleDowntimeShiftReturnEntryByDate(selectedDate);
                        
                        
                        if(array[0] == null){
                            
                            SQLiteConnection.MaintenanceStolleDowntimeShiftInsert(
                                    SQLiteConnection.MaintenanceStolleDowntimeShiftGetHighestID(),
                                    date,
                                    Double.valueOf(Stolle11JTextField1.getText()),
                                    Double.valueOf(Stolle12JTextField1.getText()),
                                    Double.valueOf(Stolle21JTextField1.getText()),
                                    Double.valueOf(Stolle22JTextField1.getText()),
                                    Double.valueOf(Stolle31JTextField1.getText()),
                                    Double.valueOf(Stolle32JTextField1.getText()),
                                    Double.valueOf(Stolle33JTextField1.getText()),
                                    Double.valueOf(Stolle41JTextField1.getText()),
                                    Double.valueOf(Stolle42JTextField1.getText()),
                                    Double.valueOf(Stolle43JTextField1.getText()),
                                    Double.valueOf(Stolle44JTextField1.getText()),
                                    Double.valueOf(Stolle11JTextField2.getText()),
                                    Double.valueOf(Stolle12JTextField2.getText()),
                                    Double.valueOf(Stolle21JTextField2.getText()),
                                    Double.valueOf(Stolle22JTextField2.getText()),
                                    Double.valueOf(Stolle31JTextField2.getText()),
                                    Double.valueOf(Stolle32JTextField2.getText()),
                                    Double.valueOf(Stolle33JTextField2.getText()),
                                    Double.valueOf(Stolle41JTextField2.getText()),
                                    Double.valueOf(Stolle42JTextField2.getText()),
                                    Double.valueOf(Stolle43JTextField2.getText()),
                                    Double.valueOf(Stolle44JTextField2.getText()),
                                    commentsBox.getText()
                                    
                            );
                            
                        }else{
                            
                            SQLiteConnection.MaintenanceStolleDowntimeShiftUpdate(
                                    date,
                                    Double.valueOf(Stolle11JTextField1.getText()),
                                    Double.valueOf(Stolle12JTextField1.getText()),
                                    Double.valueOf(Stolle21JTextField1.getText()),
                                    Double.valueOf(Stolle22JTextField1.getText()),
                                    Double.valueOf(Stolle31JTextField1.getText()),
                                    Double.valueOf(Stolle32JTextField1.getText()),
                                    Double.valueOf(Stolle33JTextField1.getText()),
                                    Double.valueOf(Stolle41JTextField1.getText()),
                                    Double.valueOf(Stolle42JTextField1.getText()),
                                    Double.valueOf(Stolle43JTextField1.getText()),
                                    Double.valueOf(Stolle44JTextField1.getText()),
                                    Double.valueOf(Stolle11JTextField2.getText()),
                                    Double.valueOf(Stolle12JTextField2.getText()),
                                    Double.valueOf(Stolle21JTextField2.getText()),
                                    Double.valueOf(Stolle22JTextField2.getText()),
                                    Double.valueOf(Stolle31JTextField2.getText()),
                                    Double.valueOf(Stolle32JTextField2.getText()),
                                    Double.valueOf(Stolle33JTextField2.getText()),
                                    Double.valueOf(Stolle41JTextField2.getText()),
                                    Double.valueOf(Stolle42JTextField2.getText()),
                                    Double.valueOf(Stolle43JTextField2.getText()),
                                    Double.valueOf(Stolle44JTextField2.getText()),
                                    commentsBox.getText(),
                                    currentId
                            );
                            
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(StolleDowntimeDay.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    frame15.dispose();
                    StolleDowntimeShift.createSummaryScreen();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(StolleDowntimeDay.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new StolleDowntimeShift(1, -1);
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
                    StolleDowntimeShift.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(StolleDowntimeShift.class.getName()).log(Level.SEVERE, null, ex);
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
                    new StolleDowntimeShift(1, -3);
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

                // Get int value of a JTextfield
                calculateTotals();

                try {
                    SQLiteConnection.MaintenanceStolleDowntimeShiftUpdate(date,
                            Double.valueOf(Stolle11JTextField1.getText()),
                            Double.valueOf(Stolle12JTextField1.getText()),
                            Double.valueOf(Stolle21JTextField1.getText()),
                            Double.valueOf(Stolle22JTextField1.getText()),
                            Double.valueOf(Stolle31JTextField1.getText()),
                            Double.valueOf(Stolle32JTextField1.getText()),
                            Double.valueOf(Stolle33JTextField1.getText()),
                            Double.valueOf(Stolle41JTextField1.getText()),
                            Double.valueOf(Stolle42JTextField1.getText()),
                            Double.valueOf(Stolle43JTextField1.getText()),
                            Double.valueOf(Stolle44JTextField1.getText()),
                            Double.valueOf(Stolle11JTextField2.getText()),
                            Double.valueOf(Stolle12JTextField2.getText()),
                            Double.valueOf(Stolle21JTextField2.getText()),
                            Double.valueOf(Stolle22JTextField2.getText()),
                            Double.valueOf(Stolle31JTextField2.getText()),
                            Double.valueOf(Stolle32JTextField2.getText()),
                            Double.valueOf(Stolle33JTextField2.getText()),
                            Double.valueOf(Stolle41JTextField2.getText()),
                            Double.valueOf(Stolle42JTextField2.getText()),
                            Double.valueOf(Stolle43JTextField2.getText()),
                            Double.valueOf(Stolle44JTextField2.getText()),
                            commentsBox.getText(),
                            currentId
                    );

                    frame15.dispose();
                    StolleDowntimeShift.createSummaryScreen();

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
                    StolleDowntimeShift.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(StolleDowntimeShift.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        find = new JButton("Find Record");

        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Get int value of a JTextfield
                // Set ID
                try {

                    Object[] total = SQLiteConnection.MaintenanceStolleDowntimeShiftGetNextEntryById(currentId);

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

                        Stolle11JTextField1.setText(String.valueOf(total[2]));
                        Stolle12JTextField1.setText(String.valueOf(total[3]));
                        Stolle21JTextField1.setText(String.valueOf(total[4]));
                        Stolle22JTextField1.setText(String.valueOf(total[5]));
                        Stolle31JTextField1.setText(String.valueOf(total[6]));
                        Stolle32JTextField1.setText(String.valueOf(total[7]));
                        Stolle33JTextField1.setText(String.valueOf(total[8]));
                        Stolle41JTextField1.setText(String.valueOf(total[9]));
                        Stolle42JTextField1.setText(String.valueOf(total[10]));
                        Stolle43JTextField1.setText(String.valueOf(total[11]));
                        Stolle44JTextField1.setText(String.valueOf(total[12]));
                        Stolle11JTextField2.setText(String.valueOf(total[13]));
                        Stolle12JTextField2.setText(String.valueOf(total[14]));
                        Stolle21JTextField2.setText(String.valueOf(total[15]));
                        Stolle22JTextField2.setText(String.valueOf(total[16]));
                        Stolle31JTextField2.setText(String.valueOf(total[17]));
                        Stolle32JTextField2.setText(String.valueOf(total[18]));
                        Stolle33JTextField2.setText(String.valueOf(total[19]));
                        Stolle41JTextField2.setText(String.valueOf(total[20]));
                        Stolle42JTextField2.setText(String.valueOf(total[21]));
                        Stolle43JTextField2.setText(String.valueOf(total[22]));
                        Stolle44JTextField2.setText(String.valueOf(total[23]));
                        commentsBox.setText(String.valueOf(total[24]));

                    }

                    System.out.println("CurrentID " + currentId);

                    // Fill Boxes with results
                    // model.setDate(year2, month2, day2);
                    model.setSelected(true);

                    // Get int value of a JTextfield
                    calculateTotals();

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

                    Object[] total = SQLiteConnection.MaintenanceStolleDowntimeShiftGetPreviousEntryById(currentId);

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

                        Stolle11JTextField1.setText(String.valueOf(total[2]));
                        Stolle12JTextField1.setText(String.valueOf(total[3]));
                        Stolle21JTextField1.setText(String.valueOf(total[4]));
                        Stolle22JTextField1.setText(String.valueOf(total[5]));
                        Stolle31JTextField1.setText(String.valueOf(total[6]));
                        Stolle32JTextField1.setText(String.valueOf(total[7]));
                        Stolle33JTextField1.setText(String.valueOf(total[8]));
                        Stolle41JTextField1.setText(String.valueOf(total[9]));
                        Stolle42JTextField1.setText(String.valueOf(total[10]));
                        Stolle43JTextField1.setText(String.valueOf(total[11]));
                        Stolle44JTextField1.setText(String.valueOf(total[12]));
                        Stolle11JTextField2.setText(String.valueOf(total[13]));
                        Stolle12JTextField2.setText(String.valueOf(total[14]));
                        Stolle21JTextField2.setText(String.valueOf(total[15]));
                        Stolle22JTextField2.setText(String.valueOf(total[16]));
                        Stolle31JTextField2.setText(String.valueOf(total[17]));
                        Stolle32JTextField2.setText(String.valueOf(total[18]));
                        Stolle33JTextField2.setText(String.valueOf(total[19]));
                        Stolle41JTextField2.setText(String.valueOf(total[20]));
                        Stolle42JTextField2.setText(String.valueOf(total[21]));
                        Stolle43JTextField2.setText(String.valueOf(total[22]));
                        Stolle44JTextField2.setText(String.valueOf(total[23]));
                        commentsBox.setText(String.valueOf(total[24]));

                    }

                    System.out.println(currentId);
                    // Get int value of a JTextfield
                    calculateTotals();

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
        JPanel optionPanel1 = new JPanel(new GridLayout(12, 4));
		// optionPanel1.setBackground(Color.GRAY);

        // ComboPanelMonthly
        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(monthCombo);
        comboPanel.add(yearCombo);
   //     comboPanel.add(go);

        // Adding
        if (view == -1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            back.setVisible(false);
            summary.setVisible(false);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);
            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Stolle 11 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle11JTextField1);

            optionPanel1.add(new JLabel("Stolle 11 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle11JTextField2);

            optionPanel1.add(new JLabel("Stolle 12 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle12JTextField1);

            optionPanel1.add(new JLabel("Stolle 12 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle12JTextField2);

            optionPanel1.add(new JLabel("Stolle 21 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle21JTextField1);

            optionPanel1.add(new JLabel("Stolle 21 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle21JTextField2);

            optionPanel1.add(new JLabel("Stolle 22 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle22JTextField1);

            optionPanel1.add(new JLabel("Stolle 22 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle22JTextField2);

            optionPanel1.add(new JLabel("Stolle 31 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle31JTextField1);

            optionPanel1.add(new JLabel("Stolle 31 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle31JTextField2);

            optionPanel1.add(new JLabel("Stolle 32 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle32JTextField1);

            optionPanel1.add(new JLabel("Stolle 32 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle32JTextField2);

            optionPanel1.add(new JLabel("Stolle 33 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle33JTextField1);

            optionPanel1.add(new JLabel("Stolle 33 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle33JTextField2);

            optionPanel1.add(new JLabel("Stolle 41 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle41JTextField1);

            optionPanel1.add(new JLabel("Stolle 41 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle41JTextField2);

            optionPanel1.add(new JLabel("Stolle 42 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle42JTextField1);

            optionPanel1.add(new JLabel("Stolle 42 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle42JTextField2);

            optionPanel1.add(new JLabel("Stolle 43 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle43JTextField1);

            optionPanel1.add(new JLabel("Stolle 43 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle43JTextField2);

            optionPanel1.add(new JLabel("Stolle 44 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle44JTextField1);

            optionPanel1.add(new JLabel("Stolle 44 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle44JTextField2);

//            optionPanel1.add(new JLabel("Comment", SwingConstants.CENTER));
//            optionPanel1.add(commentsBox);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceStolleDowntimeShiftGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);
            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Stolle 11 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle11JTextField1);

            optionPanel1.add(new JLabel("Stolle 11 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle11JTextField2);

            optionPanel1.add(new JLabel("Stolle 12 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle12JTextField1);

            optionPanel1.add(new JLabel("Stolle 12 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle12JTextField2);

            optionPanel1.add(new JLabel("Stolle 21 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle21JTextField1);

            optionPanel1.add(new JLabel("Stolle 21 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle21JTextField2);

            optionPanel1.add(new JLabel("Stolle 22 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle22JTextField1);

            optionPanel1.add(new JLabel("Stolle 22 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle22JTextField2);

            optionPanel1.add(new JLabel("Stolle 31 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle31JTextField1);

            optionPanel1.add(new JLabel("Stolle 31 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle31JTextField2);

            optionPanel1.add(new JLabel("Stolle 32 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle32JTextField1);

            optionPanel1.add(new JLabel("Stolle 32 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle32JTextField2);

            optionPanel1.add(new JLabel("Stolle 33 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle33JTextField1);

            optionPanel1.add(new JLabel("Stolle 33 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle33JTextField2);

            optionPanel1.add(new JLabel("Stolle 41 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle41JTextField1);

            optionPanel1.add(new JLabel("Stolle 41 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle41JTextField2);

            optionPanel1.add(new JLabel("Stolle 42 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle42JTextField1);

            optionPanel1.add(new JLabel("Stolle 42 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle42JTextField2);

            optionPanel1.add(new JLabel("Stolle 43 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle43JTextField1);

            optionPanel1.add(new JLabel("Stolle 43 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle43JTextField2);

            optionPanel1.add(new JLabel("Stolle 44 - 1", SwingConstants.CENTER));
            optionPanel1.add(Stolle44JTextField1);

            optionPanel1.add(new JLabel("Stolle 44 - 2", SwingConstants.CENTER));
            optionPanel1.add(Stolle44JTextField2);

//            optionPanel1.add(new JLabel("Comment", SwingConstants.CENTER));
//            optionPanel1.add(commentsBox);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(11, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(new JLabel("Stolle 11", SwingConstants.CENTER));
            optionPanel1.add(Stolle11MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 12", SwingConstants.CENTER));
            optionPanel1.add(Stolle12MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 21", SwingConstants.CENTER));
            optionPanel1.add(Stolle21MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 22", SwingConstants.CENTER));
            optionPanel1.add(Stolle22MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 31 New", SwingConstants.CENTER));
            optionPanel1.add(Stolle31MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 32", SwingConstants.CENTER));
            optionPanel1.add(Stolle32MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 33", SwingConstants.CENTER));
            optionPanel1.add(Stolle33MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 41", SwingConstants.CENTER));
            optionPanel1.add(Stolle41MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 42", SwingConstants.CENTER));
            optionPanel1.add(Stolle42MonthlyJTextField);

            optionPanel1.add(new JLabel("Stolle 43", SwingConstants.CENTER));
            optionPanel1.add(Stolle43MonthlyJTextField);

            optionPanel1.add(new JLabel("Monthly Average", SwingConstants.CENTER));
            optionPanel1.add(StolleMonthlyAverageJTextField);

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
        outerPanel.add(new JTextArea(), BorderLayout.SOUTH);

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
        
        JPanel commentsPanel2 = new JPanel(new BorderLayout());
        commentsPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
        commentsPanel2.add(new JLabel("Comment :  "), BorderLayout.WEST);
        commentsPanel2.add(commentsBox, BorderLayout.CENTER);

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(commentsPanel2, BorderLayout.CENTER);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        outerPanel.repaint();
        frame15.add(outerPanel);

        frame15.setVisible(true);
        
        SQLiteConnection.AnalyticsUpdate("StolleDowntime");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceStolleDowntimeGetHighestID();
            System.out.println("MaintenanceStolleDowntimeGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] total = new Object[30];
        try {
            total = SQLiteConnection.MaintenanceStolleDowntimeShiftReturnEntryByID(highestID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Date " + total[1]);

        // Date
        String dateFormatted = (String) total[1];
        System.out.println("Date Formatted : " + dateFormatted);

        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

        model.setDate(year, month, day);
        model.setSelected(true);

        Stolle11JTextField1.setText(String.valueOf(total[2]));
        Stolle12JTextField1.setText(String.valueOf(total[3]));
        Stolle21JTextField1.setText(String.valueOf(total[4]));
        Stolle22JTextField1.setText(String.valueOf(total[5]));
        Stolle31JTextField1.setText(String.valueOf(total[6]));
        Stolle32JTextField1.setText(String.valueOf(total[7]));
        Stolle33JTextField1.setText(String.valueOf(total[8]));
        Stolle41JTextField1.setText(String.valueOf(total[9]));
        Stolle42JTextField1.setText(String.valueOf(total[10]));
        Stolle43JTextField1.setText(String.valueOf(total[11]));
        Stolle44JTextField1.setText(String.valueOf(total[12]));
        Stolle11JTextField2.setText(String.valueOf(total[13]));
        Stolle12JTextField2.setText(String.valueOf(total[14]));
        Stolle21JTextField2.setText(String.valueOf(total[15]));
        Stolle22JTextField2.setText(String.valueOf(total[16]));
        Stolle31JTextField2.setText(String.valueOf(total[17]));
        Stolle32JTextField2.setText(String.valueOf(total[18]));
        Stolle33JTextField2.setText(String.valueOf(total[19]));
        Stolle41JTextField2.setText(String.valueOf(total[20]));
        Stolle42JTextField2.setText(String.valueOf(total[21]));
        Stolle43JTextField2.setText(String.valueOf(total[22]));
        Stolle44JTextField2.setText(String.valueOf(total[23]));
        commentsBox.setText(String.valueOf(total[24]));

        // Get int value of a JTextfield
        calculateTotals();

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new StolleDowntimeShift(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(StolleDowntimeShift.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    StolleDowntimeShift.createSummaryScreen();
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

                StolleDowntimeShift.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Stolle Downtime Shift");
        frameSummary.setSize(1300, 700);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
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

        JPanel summaryPanel = SQLiteConnection.MaintenanceStolleDowntimeShiftSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);

//        print.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                getDate("Optime Production Report", 1);
//
//            }
//        });
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setStolleDowntimeToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceStolleDowntimeShiftReturnEntryByID(currentId);

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

                Stolle11JTextField1.setText(String.valueOf(total[2]));
                Stolle12JTextField1.setText(String.valueOf(total[3]));
                Stolle21JTextField1.setText(String.valueOf(total[4]));
                Stolle22JTextField1.setText(String.valueOf(total[5]));
                Stolle31JTextField1.setText(String.valueOf(total[6]));
                Stolle32JTextField1.setText(String.valueOf(total[7]));
                Stolle33JTextField1.setText(String.valueOf(total[8]));
                Stolle41JTextField1.setText(String.valueOf(total[9]));
                Stolle42JTextField1.setText(String.valueOf(total[10]));
                Stolle43JTextField1.setText(String.valueOf(total[11]));
                Stolle44JTextField1.setText(String.valueOf(total[12]));
                Stolle11JTextField2.setText(String.valueOf(total[13]));
                Stolle12JTextField2.setText(String.valueOf(total[14]));
                Stolle21JTextField2.setText(String.valueOf(total[15]));
                Stolle22JTextField2.setText(String.valueOf(total[16]));
                Stolle31JTextField2.setText(String.valueOf(total[17]));
                Stolle32JTextField2.setText(String.valueOf(total[18]));
                Stolle33JTextField2.setText(String.valueOf(total[19]));
                Stolle41JTextField2.setText(String.valueOf(total[20]));
                Stolle42JTextField2.setText(String.valueOf(total[21]));
                Stolle43JTextField2.setText(String.valueOf(total[22]));
                Stolle44JTextField2.setText(String.valueOf(total[23]));
                commentsBox.setText(String.valueOf(total[24]));

            }

            calculateTotals();

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

    public static void calculateTotals() {

    }

    public static void exportToExcel() {

        String[] typesArray = {"Stolle Downtime"};
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

                query = "SELECT * FROM MainStolleDowntimeShift WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(StolleDowntimeShift.class.getName()).log(Level.SEVERE, null, ex);
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
