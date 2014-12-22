package com.maintenance.rexam;

import com.binentryscreens.rexam.LinerAndShellsEntry;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class LinerSpoilage {

    static JButton add, find, next, previous, delete, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;
    static JTextField Liner11JTextField, Liner12JTextField, Liner13JTextField, Liner14JTextField, Liner21JTextField, Liner22JTextField, Liner23JTextField, Liner24JTextField, Liner31JTextField, Liner32JTextField, Liner33JTextField, Liner34JTextField, Liner41JTextField, Liner42JTextField, Liner43JTextField, Liner44JTextField, Liner45JTextField, Liner46JTextField;
    static JTextField Liner11MonthlyJTextField, Liner12MonthlyJTextField, Liner13MonthlyJTextField, Liner14MonthlyJTextField, Liner21MonthlyJTextField, Liner22MonthlyJTextField, Liner23MonthlyJTextField, Liner24MonthlyJTextField, Liner31MonthlyJTextField, Liner32MonthlyJTextField, Liner33MonthlyJTextField, Liner34MonthlyJTextField, Liner41MonthlyJTextField, Liner42MonthlyJTextField, Liner43MonthlyJTextField, Liner44MonthlyJTextField, Liner45MonthlyJTextField, Liner46MonthlyJTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;
    static JFileChooser fileChooser;

    public static void main(String[] args) throws SQLException {

        new LinerSpoilage(1, -1);
        
//        try {
//
//            importdata();
//        } catch (IOException ex) {
//            Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public LinerSpoilage(int idIn, int view) throws SQLException {

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

        frame15.setTitle("Liner Spoilage");
        frame15.setSize(360, 700);
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

        Liner11JTextField = new JTextField();
        Liner11JTextField.setText("0.0");

        Liner12JTextField = new JTextField();
        Liner12JTextField.setText("0.0");

        Liner13JTextField = new JTextField();
        Liner13JTextField.setText("0.0");

        Liner14JTextField = new JTextField();
        Liner14JTextField.setText("0.0");

        Liner21JTextField = new JTextField();
        Liner21JTextField.setText("0.0");

        Liner22JTextField = new JTextField();
        Liner22JTextField.setText("0.0");

        Liner23JTextField = new JTextField();
        Liner23JTextField.setText("0.0");

        Liner24JTextField = new JTextField();
        Liner24JTextField.setText("0.0");

        Liner31JTextField = new JTextField();
        Liner31JTextField.setText("0.0");

        Liner32JTextField = new JTextField();
        Liner32JTextField.setText("0.0");

        Liner33JTextField = new JTextField();
        Liner33JTextField.setText("0.0");

        Liner34JTextField = new JTextField();
        Liner34JTextField.setText("0.0");

        Liner41JTextField = new JTextField();
        Liner41JTextField.setText("0.0");

        Liner42JTextField = new JTextField();
        Liner42JTextField.setText("0.0");

        Liner43JTextField = new JTextField();
        Liner43JTextField.setText("0.0");

        Liner44JTextField = new JTextField();
        Liner44JTextField.setText("0.0");

        Liner45JTextField = new JTextField();
        Liner45JTextField.setText("0.0");

        Liner46JTextField = new JTextField();
        Liner46JTextField.setText("0.0");

        Liner11MonthlyJTextField = new JTextField();

        Liner12MonthlyJTextField = new JTextField();
        Liner13MonthlyJTextField = new JTextField();
        Liner14MonthlyJTextField = new JTextField();
        Liner21MonthlyJTextField = new JTextField();
        Liner22MonthlyJTextField = new JTextField();
        Liner23MonthlyJTextField = new JTextField();
        Liner24MonthlyJTextField = new JTextField();
        Liner31MonthlyJTextField = new JTextField();
        Liner32MonthlyJTextField = new JTextField();
        Liner33MonthlyJTextField = new JTextField();
        Liner34MonthlyJTextField = new JTextField();
        Liner41MonthlyJTextField = new JTextField();
        Liner42MonthlyJTextField = new JTextField();
        Liner43MonthlyJTextField = new JTextField();
        Liner44MonthlyJTextField = new JTextField();
        Liner45MonthlyJTextField = new JTextField();
        Liner46MonthlyJTextField = new JTextField();

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceLinerSpoilageCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    Liner11MonthlyJTextField.setText(String.valueOf(total[0]));
                    Liner12MonthlyJTextField.setText(String.valueOf(total[1]));
                    Liner13MonthlyJTextField.setText(String.valueOf(total[2]));
                    Liner14MonthlyJTextField.setText(String.valueOf(total[3]));
                    Liner21MonthlyJTextField.setText(String.valueOf(total[4]));
                    Liner22MonthlyJTextField.setText(String.valueOf(total[5]));
                    Liner23MonthlyJTextField.setText(String.valueOf(total[6]));
                    Liner24MonthlyJTextField.setText(String.valueOf(total[7]));
                    Liner31MonthlyJTextField.setText(String.valueOf(total[8]));
                    Liner32MonthlyJTextField.setText(String.valueOf(total[9]));
                    Liner33MonthlyJTextField.setText(String.valueOf(total[10]));
                    Liner34MonthlyJTextField.setText(String.valueOf(total[11]));
                    Liner41MonthlyJTextField.setText(String.valueOf(total[12]));
                    Liner42MonthlyJTextField.setText(String.valueOf(total[13]));
                    Liner43MonthlyJTextField.setText(String.valueOf(total[14]));
                    Liner44MonthlyJTextField.setText(String.valueOf(total[15]));
                    Liner45MonthlyJTextField.setText(String.valueOf(total[16]));
                    Liner46MonthlyJTextField.setText(String.valueOf(total[17]));

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
                    new LinerSpoilage(1, -1);
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
                    SQLiteConnection.MaintenanceLinerSpoilageInsert(
                            SQLiteConnection.MaintenanceLinerSpoilageGetHighestID() + 1,
                            date,
                            Double.valueOf(Liner11JTextField.getText()),
                            Double.valueOf(Liner12JTextField.getText()),
                            Double.valueOf(Liner13JTextField.getText()),
                            Double.valueOf(Liner14JTextField.getText()),
                            Double.valueOf(Liner21JTextField.getText()),
                            Double.valueOf(Liner22JTextField.getText()),
                            Double.valueOf(Liner23JTextField.getText()),
                            Double.valueOf(Liner24JTextField.getText()),
                            Double.valueOf(Liner31JTextField.getText()),
                            Double.valueOf(Liner32JTextField.getText()),
                            Double.valueOf(Liner33JTextField.getText()),
                            Double.valueOf(Liner34JTextField.getText()),
                            Double.valueOf(Liner41JTextField.getText()),
                            Double.valueOf(Liner42JTextField.getText()),
                            Double.valueOf(Liner43JTextField.getText()),
                            Double.valueOf(Liner44JTextField.getText()),
                            Double.valueOf(Liner45JTextField.getText()),
                            Double.valueOf(Liner46JTextField.getText())
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
                    Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new LinerSpoilage(1, -1);
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
                    new LinerSpoilage(1, -2);
                    setLastEntry();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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
                    new LinerSpoilage(1, -3);
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
                    SQLiteConnection.MaintenanceLinerSpoilageUpdate(
                            date,
                            Double.valueOf(Liner11JTextField.getText()),
                            Double.valueOf(Liner12JTextField.getText()),
                            Double.valueOf(Liner13JTextField.getText()),
                            Double.valueOf(Liner14JTextField.getText()),
                            Double.valueOf(Liner21JTextField.getText()),
                            Double.valueOf(Liner22JTextField.getText()),
                            Double.valueOf(Liner23JTextField.getText()),
                            Double.valueOf(Liner24JTextField.getText()),
                            Double.valueOf(Liner31JTextField.getText()),
                            Double.valueOf(Liner32JTextField.getText()),
                            Double.valueOf(Liner33JTextField.getText()),
                            Double.valueOf(Liner34JTextField.getText()),
                            Double.valueOf(Liner41JTextField.getText()),
                            Double.valueOf(Liner42JTextField.getText()),
                            Double.valueOf(Liner43JTextField.getText()),
                            Double.valueOf(Liner44JTextField.getText()),
                            Double.valueOf(Liner45JTextField.getText()),
                            Double.valueOf(Liner46JTextField.getText()),
                            currentId
                    );

                    frame15.dispose();
                    new LinerSpoilage(1, -2);

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
                    LinerSpoilage.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] total = SQLiteConnection.MaintenanceLinerSpoilageGetNextEntryById(currentId);

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

                        Liner11JTextField.setText(String.valueOf(total[2]));
                        Liner12JTextField.setText(String.valueOf(total[3]));
                        Liner13JTextField.setText(String.valueOf(total[4]));
                        Liner14JTextField.setText(String.valueOf(total[5]));
                        Liner21JTextField.setText(String.valueOf(total[6]));
                        Liner22JTextField.setText(String.valueOf(total[7]));
                        Liner23JTextField.setText(String.valueOf(total[8]));
                        Liner24JTextField.setText(String.valueOf(total[9]));
                        Liner31JTextField.setText(String.valueOf(total[10]));
                        Liner32JTextField.setText(String.valueOf(total[11]));
                        Liner33JTextField.setText(String.valueOf(total[12]));
                        Liner34JTextField.setText(String.valueOf(total[13]));
                        Liner41JTextField.setText(String.valueOf(total[14]));
                        Liner42JTextField.setText(String.valueOf(total[15]));
                        Liner43JTextField.setText(String.valueOf(total[16]));
                        Liner44JTextField.setText(String.valueOf(total[17]));
                        Liner45JTextField.setText(String.valueOf(total[18]));
                        Liner46JTextField.setText(String.valueOf(total[19]));

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

                    Object[] total = SQLiteConnection.MaintenanceLinerSpoilageGetPreviousEntryById(currentId);

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

                        Liner11JTextField.setText(String.valueOf(total[2]));
                        Liner12JTextField.setText(String.valueOf(total[3]));
                        Liner13JTextField.setText(String.valueOf(total[4]));
                        Liner14JTextField.setText(String.valueOf(total[5]));
                        Liner21JTextField.setText(String.valueOf(total[6]));
                        Liner22JTextField.setText(String.valueOf(total[7]));
                        Liner23JTextField.setText(String.valueOf(total[8]));
                        Liner24JTextField.setText(String.valueOf(total[9]));
                        Liner31JTextField.setText(String.valueOf(total[10]));
                        Liner32JTextField.setText(String.valueOf(total[11]));
                        Liner33JTextField.setText(String.valueOf(total[12]));
                        Liner34JTextField.setText(String.valueOf(total[13]));
                        Liner41JTextField.setText(String.valueOf(total[14]));
                        Liner42JTextField.setText(String.valueOf(total[15]));
                        Liner43JTextField.setText(String.valueOf(total[16]));
                        Liner44JTextField.setText(String.valueOf(total[17]));
                        Liner45JTextField.setText(String.valueOf(total[18]));
                        Liner46JTextField.setText(String.valueOf(total[19]));

                    }

                    System.out.println(currentId);

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    try {
                        // Delete CurrentID
                        SQLiteConnection.MaintenanceLinerSpoilageDelete(currentId);

                        // Create Summary Screen
                        frameSummary.dispose();
                        frame15.dispose();

                        createSummaryScreen();
                    } catch (SQLException ex) {
                        Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
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
        buttonsPanel.add(delete);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        JPanel optionPanel1 = new JPanel(new GridLayout(19, 2));
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
            delete.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            back.setVisible(false);
            summary.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
            optionPanel1.add(Liner11JTextField);

            optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
            optionPanel1.add(Liner12JTextField);

            optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
            optionPanel1.add(Liner13JTextField);

            optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
            optionPanel1.add(Liner14JTextField);

            optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
            optionPanel1.add(Liner21JTextField);

            optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
            optionPanel1.add(Liner22JTextField);

            optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
            optionPanel1.add(Liner23JTextField);

            optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
            optionPanel1.add(Liner24JTextField);

            optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
            optionPanel1.add(Liner31JTextField);

            optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
            optionPanel1.add(Liner32JTextField);

            optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
            optionPanel1.add(Liner33JTextField);

            optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
            optionPanel1.add(Liner34JTextField);

            optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
            optionPanel1.add(Liner41JTextField);

            optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
            optionPanel1.add(Liner42JTextField);

            optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
            optionPanel1.add(Liner43JTextField);

            optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
            optionPanel1.add(Liner44JTextField);

            optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
            optionPanel1.add(Liner45JTextField);

            optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
            optionPanel1.add(Liner46JTextField);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceLinerProductionGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
            optionPanel1.add(Liner11JTextField);

            optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
            optionPanel1.add(Liner12JTextField);

            optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
            optionPanel1.add(Liner13JTextField);

            optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
            optionPanel1.add(Liner14JTextField);

            optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
            optionPanel1.add(Liner21JTextField);

            optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
            optionPanel1.add(Liner22JTextField);

            optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
            optionPanel1.add(Liner23JTextField);

            optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
            optionPanel1.add(Liner24JTextField);

            optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
            optionPanel1.add(Liner31JTextField);

            optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
            optionPanel1.add(Liner32JTextField);

            optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
            optionPanel1.add(Liner33JTextField);

            optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
            optionPanel1.add(Liner34JTextField);

            optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
            optionPanel1.add(Liner41JTextField);

            optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
            optionPanel1.add(Liner42JTextField);

            optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
            optionPanel1.add(Liner43JTextField);

            optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
            optionPanel1.add(Liner44JTextField);

            optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
            optionPanel1.add(Liner45JTextField);

            optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
            optionPanel1.add(Liner46JTextField);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(18, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
            optionPanel1.add(Liner11MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
            optionPanel1.add(Liner12MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
            optionPanel1.add(Liner13MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
            optionPanel1.add(Liner14MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
            optionPanel1.add(Liner21MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
            optionPanel1.add(Liner22MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
            optionPanel1.add(Liner23MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
            optionPanel1.add(Liner24MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
            optionPanel1.add(Liner31MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
            optionPanel1.add(Liner32MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
            optionPanel1.add(Liner33MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
            optionPanel1.add(Liner34MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
            optionPanel1.add(Liner41MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
            optionPanel1.add(Liner42MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
            optionPanel1.add(Liner43MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
            optionPanel1.add(Liner44MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
            optionPanel1.add(Liner45MonthlyJTextField);

            optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
            optionPanel1.add(Liner46MonthlyJTextField);

            monthly.setVisible(false);
            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            delete.setVisible(false);
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
        optionsPanel2.add(monthly);
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

        SQLiteConnection.AnalyticsUpdate("LinerSpoilage");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceLinerSpoilageGetHighestID();
            System.out.println("MaintenanceLinerProductionGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] total = new Object[20];
        try {
            total = SQLiteConnection.MaintenanceLinerSpoilageReturnEntryByID(highestID);
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

        currentId = currentId + 1;

        Liner11JTextField.setText(String.valueOf(total[2]));
        Liner12JTextField.setText(String.valueOf(total[3]));
        Liner13JTextField.setText(String.valueOf(total[4]));
        Liner14JTextField.setText(String.valueOf(total[5]));
        Liner21JTextField.setText(String.valueOf(total[6]));
        Liner22JTextField.setText(String.valueOf(total[7]));
        Liner23JTextField.setText(String.valueOf(total[8]));
        Liner24JTextField.setText(String.valueOf(total[9]));
        Liner31JTextField.setText(String.valueOf(total[10]));
        Liner32JTextField.setText(String.valueOf(total[11]));
        Liner33JTextField.setText(String.valueOf(total[12]));
        Liner34JTextField.setText(String.valueOf(total[13]));
        Liner41JTextField.setText(String.valueOf(total[14]));
        Liner42JTextField.setText(String.valueOf(total[15]));
        Liner43JTextField.setText(String.valueOf(total[16]));
        Liner44JTextField.setText(String.valueOf(total[17]));
        Liner45JTextField.setText(String.valueOf(total[18]));
        Liner46JTextField.setText(String.valueOf(total[19]));

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LinerSpoilage(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LinerSpoilage.createSummaryScreen();
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

                LinerSpoilage.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Spoilage Report");
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

        JPanel summaryPanel = SQLiteConnection.MaintenanceLinerSpoilageSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(lastSevenDaysPanel(), BorderLayout.NORTH);
        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static JPanel lastSevenDaysPanel() {

        String[] averages = new String[16];
        try {
            averages = SQLiteConnection.MaintenanceLinerSPoilageSevenDaysAverages();
        } catch (SQLException ex) {
            Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
        }

        String string11 = averages[0];
        String string12 = averages[1];
        String string13 = averages[2];
        String string14 = averages[3];
        String string21 = averages[4];
        String string22 = averages[5];
        String string23 = averages[6];
        String string24 = averages[7];
        String string31 = averages[8];
        String string32 = averages[9];
        String string33 = averages[10];
        String string34 = averages[11];
        String string41 = averages[12];
        String string42 = averages[13];
        String string43 = averages[14];
        String string44 = averages[15];

        JPanel panel = new JPanel(new GridLayout(1, 20));
        panel.setBackground(Color.GRAY);

        panel.add(new JLabel("7DAYAVG", JLabel.CENTER));

        panel.add(new JTextField(string11));
        panel.add(new JTextField(string12));
        panel.add(new JTextField(string13));
        panel.add(new JTextField(string14));

        panel.add(new JTextField(string21));
        panel.add(new JTextField(string22));
        panel.add(new JTextField(string23));
        panel.add(new JTextField(string24));

        panel.add(new JTextField(string31));
        panel.add(new JTextField(string32));
        panel.add(new JTextField(string33));
        panel.add(new JTextField(string34));

        panel.add(new JTextField(string41));
        panel.add(new JTextField(string42));
        panel.add(new JTextField(string43));
        panel.add(new JTextField(string44));

        panel.add(new JLabel("  ", JLabel.CENTER));

        return panel;
    }

    public void setLinerSpoilageToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceLinerSpoilageReturnEntryByID(currentId);

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

                Liner11JTextField.setText(String.valueOf(total[2]));
                Liner12JTextField.setText(String.valueOf(total[3]));
                Liner13JTextField.setText(String.valueOf(total[4]));
                Liner14JTextField.setText(String.valueOf(total[5]));
                Liner21JTextField.setText(String.valueOf(total[6]));
                Liner22JTextField.setText(String.valueOf(total[7]));
                Liner23JTextField.setText(String.valueOf(total[8]));
                Liner24JTextField.setText(String.valueOf(total[9]));
                Liner31JTextField.setText(String.valueOf(total[10]));
                Liner32JTextField.setText(String.valueOf(total[11]));
                Liner33JTextField.setText(String.valueOf(total[12]));
                Liner34JTextField.setText(String.valueOf(total[13]));
                Liner41JTextField.setText(String.valueOf(total[14]));
                Liner42JTextField.setText(String.valueOf(total[15]));
                Liner43JTextField.setText(String.valueOf(total[16]));
                Liner44JTextField.setText(String.valueOf(total[17]));
                Liner45JTextField.setText(String.valueOf(total[18]));
                Liner46JTextField.setText(String.valueOf(total[19]));

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

    public static void setLinerSpoilageToInput(int year, int month, int day, double liner11, double liner12, double liner13, double liner14, double liner21, double liner22, double liner23, double liner24, double liner31, double liner32, double liner33, double liner34, double liner41, double liner42, double liner43, double liner44, double liner45, double liner46) {

        model.setDate(year, month, day);

        Liner11JTextField.setText(liner11 + "");
        Liner12JTextField.setText(liner12 + "");
        Liner13JTextField.setText(liner13 + "");
        Liner14JTextField.setText(liner14 + "");
        Liner21JTextField.setText(liner21 + "");
        Liner22JTextField.setText(liner22 + "");
        Liner23JTextField.setText(liner23 + "");
        Liner24JTextField.setText(liner24 + "");
        Liner31JTextField.setText(liner31 + "");
        Liner32JTextField.setText(liner32 + "");
        Liner33JTextField.setText(liner33 + "");
        Liner34JTextField.setText(liner34 + "");
        Liner41JTextField.setText(liner41 + "");
        Liner42JTextField.setText(liner42 + "");
        Liner43JTextField.setText(liner43 + "");
        Liner44JTextField.setText(liner44 + "");
        Liner45JTextField.setText(liner45 + "");
        Liner46JTextField.setText(liner46 + "");

    }

    public static void exportToExcel() {

        String[] typesArray = {"Liner Spoilage"};
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

                query = "SELECT * FROM MainLinerSpoilage WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(LinerSpoilage.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void importdata() throws FileNotFoundException, IOException {

        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel Documents", "xls"));

        int result = fileChooser.showOpenDialog(frameSummary);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            ////////////////
            FileInputStream excelFile = new FileInputStream(selectedFile);
            HSSFWorkbook workbook = new HSSFWorkbook(excelFile);

            // Create 31 Arrays [31][8] // Each Array has A2, B2, C2, D2, E2, F2, G2, H2, I2 
            HSSFRow[] rows = new HSSFRow[32];
            HSSFSheet worksheet = workbook.getSheet("LinerSpoilage5");

            String[][] ints = new String[31][19];

            for (int i = 0; i < 32; i++) {

                rows[i] = worksheet.getRow(i);

            }

            String[][] strings = new String[31][19];
            HSSFCell[] cells = new HSSFCell[31];

            // get CellA1 to A31 and Assign to int
            for (int i = 0; i < 31; i++) {

                for (int j = 0; j < 19; j++) {

                    cells[i] = rows[i].getCell(j);
                    strings[i][j] = cells[i].getNumericCellValue() + "";
                    
                    System.out.println(strings[i][j]);

                }

                

            }

            for (int i = 0; i < 31; i++) {

                try {

                    LinerSpoilage linerDefects = new LinerSpoilage(1, -1);

                 // Set Date to 2014-01-i
                    String day = strings[i][0];
                    double dayDouble = Double.parseDouble(day);
                    int dayInt = (int) dayDouble;

                    model.setDate(2014, 00, dayInt);

                    linerDefects.setLinerSpoilageToInput(
                            
                            2014, 
                            04, 
                            dayInt, 
                            Double.parseDouble(strings[i][1]), 
                            Double.parseDouble(strings[i][2]), 
                            Double.parseDouble(strings[i][3]), 
                            Double.parseDouble(strings[i][4]), 
                            Double.parseDouble(strings[i][5]), 
                            Double.parseDouble(strings[i][6]), 
                            Double.parseDouble(strings[i][7]), 
                            Double.parseDouble(strings[i][8]), 
                            Double.parseDouble(strings[i][9]), 
                            Double.parseDouble(strings[i][10]), 
                            Double.parseDouble(strings[i][11]), 
                            Double.parseDouble(strings[i][12]), 
                            Double.parseDouble(strings[i][13]), 
                            Double.parseDouble(strings[i][14]), 
                            Double.parseDouble(strings[i][15]), 
                            Double.parseDouble(strings[i][16]), 
                            Double.parseDouble(strings[i][17]), 
                            Double.parseDouble(strings[i][18])
                    
                    );
                    
                    add.doClick();
                   

                } catch (SQLException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        // Import into Database with matching dates if it doesnt exist for LinersAndShells, LinerDefects, EndCounts
        // If exist skip that individual section
    }
}
