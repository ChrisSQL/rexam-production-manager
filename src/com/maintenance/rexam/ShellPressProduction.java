package com.maintenance.rexam;

import com.binentryscreens.rexam.LinerAndShellsEntry;
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
import java.awt.Desktop;
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

public class ShellPressProduction {

    static JButton add, find, next, previous, delete, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2, importFromExcel;

    static JTextField SP01JTextfield, Optime2JTextfield, Optime3JTextfield, FMI41JTextfield, FMI42JTextfield, Formatec04JTextfield;

    static JTextField SP01MonthlyJTextField, Optime2MonthlyJTextField, Optime3MonthlyJTextField, FMI41MonthlyTextField, FMI42MonthlyTextField, Formatec04MonthlyTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary, frame15;
    static JFileChooser fileChooser;

    public static void main(String[] args) throws SQLException {

        new ShellPressProduction(1, -1);

    }

    public ShellPressProduction(int idIn, int view) throws SQLException {

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

        frame15 = new JFrame();
        // frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame15.setTitle("Shell Press");
        frame15.setSize(360, 350);
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

        SP01JTextfield = new JTextField();
        SP01JTextfield.setText("0");
        PlainDocument doc01 = (PlainDocument) SP01JTextfield.getDocument();
        doc01.setDocumentFilter(new MyIntFilter());

        SP01JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });

        Optime2JTextfield = new JTextField();
        Optime2JTextfield.setText("0");
        PlainDocument doc1 = (PlainDocument) Optime2JTextfield.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        Optime2JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });

        Optime3JTextfield = new JTextField();
        Optime3JTextfield.setText("0");
        PlainDocument doc2 = (PlainDocument) Optime3JTextfield.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        Optime3JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });
        FMI41JTextfield = new JTextField();
        FMI41JTextfield.setText("0");
        PlainDocument doc3 = (PlainDocument) FMI41JTextfield.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());
        FMI41JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });
        FMI42JTextfield = new JTextField();
        FMI42JTextfield.setText("0");
        PlainDocument doc4 = (PlainDocument) FMI42JTextfield.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());
        FMI42JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });
        Formatec04JTextfield = new JTextField();
        Formatec04JTextfield.setText("0");
        PlainDocument doc5 = (PlainDocument) Formatec04JTextfield.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());
        Formatec04JTextfield.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotals();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotals();

            }
        });

        SP01MonthlyJTextField = new JTextField();
        PlainDocument doc02 = (PlainDocument) SP01MonthlyJTextField.getDocument();
        doc02.setDocumentFilter(new MyIntFilter());
        Optime2MonthlyJTextField = new JTextField();
        PlainDocument doc13 = (PlainDocument) Optime2MonthlyJTextField.getDocument();
        doc13.setDocumentFilter(new MyIntFilter());
        Optime3MonthlyJTextField = new JTextField();
        PlainDocument doc14 = (PlainDocument) Optime3MonthlyJTextField.getDocument();
        doc14.setDocumentFilter(new MyIntFilter());
        FMI41MonthlyTextField = new JTextField();
        PlainDocument doc15 = (PlainDocument) FMI41MonthlyTextField.getDocument();
        doc15.setDocumentFilter(new MyIntFilter());
        FMI42MonthlyTextField = new JTextField();
        PlainDocument doc16 = (PlainDocument) FMI42MonthlyTextField.getDocument();
        doc16.setDocumentFilter(new MyIntFilter());

        Formatec04MonthlyTextField = new JTextField();
        PlainDocument doc17 = (PlainDocument) Formatec04MonthlyTextField.getDocument();
        doc17.setDocumentFilter(new MyIntFilter());

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceShellPressProductionCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    SP01MonthlyJTextField.setText(String.valueOf(total[0]));
                    Optime2MonthlyJTextField.setText(String.valueOf(total[1]));
                    Optime3MonthlyJTextField.setText(String.valueOf(total[2]));
                    FMI41MonthlyTextField.setText(String.valueOf(total[3]));
                    FMI42MonthlyTextField.setText(String.valueOf(total[4]));
                    Formatec04MonthlyTextField.setText(String.valueOf(total[5]));

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
                    new ShellPressProduction(1, -1);
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

                int day = Integer.parseInt(date.substring(8, 10)); // Correct
                int month = Integer.parseInt(date.substring(5, 7)) - 1; // Correct
                int year = Integer.parseInt(date.substring(0, 4)); // Correct

                String dayString = date.substring(8, 10); // Correct
                String monthString = date.substring(5, 7); // Correct
                String yearString = date.substring(0, 4); // Correct

                // Get int value of a JTextfield
                calculateTotals();

                try {
                    System.out.println("MaintenanceShellPressProductionEntryExists : " + SQLiteConnection.MaintenanceShellPressProductionEntryExists(yearString + "", monthString + "", dayString + ""));
                    if (SQLiteConnection.MaintenanceShellPressProductionEntryExists(yearString + "", monthString + "", dayString + "") == true) {

                        // Update
                        try {
                            SQLiteConnection.MaintenanceShellPressProductionUpdateByDate(
                                    Integer.valueOf(SP01JTextfield.getText()),
                                    Integer.valueOf(Optime2JTextfield.getText()),
                                    Integer.valueOf(Optime3JTextfield.getText()),
                                    Integer.valueOf(FMI41JTextfield.getText()),
                                    Integer.valueOf(FMI42JTextfield.getText()),
                                    Integer.valueOf(Formatec04JTextfield.getText()),
                                    date
                            );

                            frame15.dispose();
                            ShellPressProduction.createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    } else {

                        try {
                            SQLiteConnection.MaintenanceShellPressProductionInsert(
                                    SQLiteConnection.MaintenanceShellPressProductionGetHighestID() + 1,
                                    date,
                                    Integer.valueOf(SP01JTextfield.getText()),
                                    Integer.valueOf(Optime2JTextfield.getText()),
                                    Integer.valueOf(Optime3JTextfield.getText()),
                                    Integer.valueOf(FMI41JTextfield.getText()),
                                    Integer.valueOf(FMI42JTextfield.getText()),
                                    Integer.valueOf(Formatec04JTextfield.getText())
                            );

                            frame15.dispose();
                            //  

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    // TODO Auto-generated method stub

                    ShellPressProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new ShellPressProduction(1, -1);
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
                    ShellPressProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame15.dispose();

            }
        });

        monthly = new JButton("Monthly");
        monthly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame15.dispose();
                

                // TODO Auto-generated method stub
                try {
                    new ShellPressProduction(1, -3);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                

            }
        });

        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.MaintenanceShellPressProductionUpdate(
                            date,
                            Integer.valueOf(SP01JTextfield.getText()),
                            Integer.valueOf(Optime2JTextfield.getText()),
                            Integer.valueOf(Optime3JTextfield.getText()),
                            Integer.valueOf(FMI41JTextfield.getText()),
                            Integer.valueOf(FMI42JTextfield.getText()),
                            Integer.valueOf(Formatec04JTextfield.getText()),
                            currentId
                    );

                    frame15.dispose();
                    new ShellPressProduction(1, -2);

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
                    ShellPressProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        find = new JButton("Find Record");
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {
                    selectedDate = (Date) datePicker.getModel().getValue();

                    Object[] total = SQLiteConnection.MaintenanceShellPressProductionReturnEntryByDate(selectedDate);

                    if (total[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) total[0];

                        SP01JTextfield.setText(String.valueOf(total[2]));
                        Optime2JTextfield.setText(String.valueOf(total[3]));
                        Optime3JTextfield.setText(String.valueOf(total[4]));
                        FMI41JTextfield.setText(String.valueOf(total[5]));
                        FMI42JTextfield.setText(String.valueOf(total[6]));
                        Formatec04JTextfield.setText(String.valueOf(total[7]));

                    }

                    System.out.println("CurrentID " + currentId);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });

        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] total = SQLiteConnection.MaintenanceShellPressProductionGetNextEntryById(currentId);

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

                        SP01JTextfield.setText(String.valueOf(total[2]));
                        Optime2JTextfield.setText(String.valueOf(total[3]));
                        Optime3JTextfield.setText(String.valueOf(total[4]));
                        FMI41JTextfield.setText(String.valueOf(total[5]));
                        FMI42JTextfield.setText(String.valueOf(total[6]));
                        Formatec04JTextfield.setText(String.valueOf(total[7]));

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
                    SQLiteConnection.MaintenanceShellPressDelete(currentId);

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

        previous = new JButton("Previous Record");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] total = SQLiteConnection.MaintenanceShellPressProductionGetPreviousEntryById(currentId);

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

                        SP01JTextfield.setText(String.valueOf(total[2]));
                        Optime2JTextfield.setText(String.valueOf(total[3]));
                        Optime3JTextfield.setText(String.valueOf(total[4]));
                        FMI41JTextfield.setText(String.valueOf(total[5]));
                        FMI42JTextfield.setText(String.valueOf(total[6]));
                        Formatec04JTextfield.setText(String.valueOf(total[7]));

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

        calculateTotal1 = new JButton("Calculate Total 1");
        calculateTotal1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int W11Int = Integer.valueOf(Optime2JTextfield.getText());
                int W12Int = Integer.valueOf(Optime3JTextfield.getText());
                int W21Int = Integer.valueOf(FMI41JTextfield.getText());
                int W22Int = Integer.valueOf(FMI42JTextfield.getText());
                int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

            }
        });

        calculateTotal2 = new JButton("Calculate Total 2");
        calculateTotal2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int W11Int = Integer.valueOf(Optime2JTextfield.getText());
                int W12Int = Integer.valueOf(Optime3JTextfield.getText());
                int W21Int = Integer.valueOf(FMI41JTextfield.getText());
                int W22Int = Integer.valueOf(FMI42JTextfield.getText());
                int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

            }
        });

        calculateTotal3 = new JButton("Calculate Total 3");
        calculateTotal3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int W11Int = Integer.valueOf(Optime2JTextfield.getText());
                int W12Int = Integer.valueOf(Optime3JTextfield.getText());
                int W21Int = Integer.valueOf(FMI41JTextfield.getText());
                int W22Int = Integer.valueOf(FMI42JTextfield.getText());
                int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

            }
        });

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
        JPanel optionPanel1 = new JPanel(new GridLayout(7, 2));
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

            optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
            optionPanel1.add(SP01JTextfield);

            optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
            optionPanel1.add(Optime2JTextfield);

            optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
            optionPanel1.add(Optime3JTextfield);

            optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
            optionPanel1.add(FMI41JTextfield);

            optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
            optionPanel1.add(FMI42JTextfield);

            optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
            optionPanel1.add(Formatec04JTextfield);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceShellPressProductionGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
            optionPanel1.add(SP01JTextfield);

            optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
            optionPanel1.add(Optime2JTextfield);

            optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
            optionPanel1.add(Optime3JTextfield);

            optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
            optionPanel1.add(FMI41JTextfield);

            optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
            optionPanel1.add(FMI42JTextfield);

            optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
            optionPanel1.add(Formatec04JTextfield);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(6, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
            optionPanel1.add(SP01MonthlyJTextField);

            optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
            optionPanel1.add(Optime2MonthlyJTextField);

            optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
            optionPanel1.add(Optime3MonthlyJTextField);

            optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
            optionPanel1.add(FMI41MonthlyTextField);

            optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
            optionPanel1.add(FMI42MonthlyTextField);

            optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
            optionPanel1.add(Formatec04MonthlyTextField);

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

        SQLiteConnection.AnalyticsUpdate("ShellPressProduction");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceShellPressProductionGetHighestID();
            System.out.println("MaintenanceShellPressProductionGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] result = new Object[16];
        try {
            result = SQLiteConnection.MaintenanceShellPressProductionReturnEntryByID(highestID);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Date " + result[1]);

        // Date
        String dateFormatted = (String) result[1];
        System.out.println("Date Formatted : " + dateFormatted);

        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

        model.setDate(year, month, day);
        model.setSelected(true);

        currentId = currentId + 1;

        SP01JTextfield.setText(String.valueOf(result[2]));
        Optime2JTextfield.setText(String.valueOf(result[3]));
        Optime3JTextfield.setText(String.valueOf(result[4]));
        FMI41JTextfield.setText(String.valueOf(result[5]));
        FMI42JTextfield.setText(String.valueOf(result[6]));
        Formatec04JTextfield.setText(String.valueOf(result[7]));

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new ShellPressProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    ShellPressProduction.createSummaryScreen();
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

                ShellPressProduction.exportToExcel();

            }
        });

        JButton importFromExcel = new JButton("Import From Viscan");
        importFromExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    frameSummary.dispose();
                    ShellPressProduction.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Shell Production Report");
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
        optionsPanel2.add(importFromExcel);

        JPanel summaryPanel = SQLiteConnection.MaintenanceShellPressProductionSummaryTable(1);
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

    public void setShellPressProductionToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceShellPressProductionReturnEntryByID(currentId);

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

                SP01JTextfield.setText(String.valueOf(total[2]));
                Optime2JTextfield.setText(String.valueOf(total[3]));
                Optime3JTextfield.setText(String.valueOf(total[4]));
                FMI41JTextfield.setText(String.valueOf(total[5]));
                FMI42JTextfield.setText(String.valueOf(total[6]));
                Formatec04JTextfield.setText(String.valueOf(total[7]));

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

    public static void calculateTotals() {

//        // TODO Auto-generated method stub
//        int W11Int = Integer.valueOf(Optime2JTextfield.getText());
//        int W12Int = Integer.valueOf(Optime3JTextfield.getText());
//        int W21Int = Integer.valueOf(FMI41JTextfield.getText());
//        int W22Int = Integer.valueOf(FMI42JTextfield.getText());
//        int W31Int = Integer.valueOf(Formatec04JTextfield.getText());
    }

    public static void importFromExcel() throws FileNotFoundException, IOException {

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
            HSSFSheet worksheet = workbook.getSheet("Spoilage Summary");

            // Create Array of Rows
            HSSFRow[] rows = new HSSFRow[56];

            for (int i = 0; i < 55; i++) {

                rows[i + 1] = worksheet.getRow(i);

            }

            HSSFCell cellB3 = rows[3].getCell((short) 1);
            String startDate = cellB3.getStringCellValue(); // Start Date

            HSSFCell cellB4 = rows[4].getCell((short) 1);
            String endDate = cellB4.getStringCellValue(); // End Date

            // Shell Press Infeeds
            HSSFCell cellB14 = rows[14].getCell((short) 1);
            double shellPress21Infeed = cellB14.getNumericCellValue();

            HSSFCell cellB15 = rows[15].getCell((short) 1);
            double shellPress31Infeed = cellB15.getNumericCellValue();

            HSSFCell cellB16 = rows[16].getCell((short) 1);
            double shellPress41Infeed = cellB16.getNumericCellValue();

            // Liner Infeeds
            HSSFCell cellB22 = rows[22].getCell((short) 1);
            double mod1LinersTotal = cellB22.getNumericCellValue();

            HSSFCell cellB27 = rows[27].getCell((short) 1);
            double mod2LinersTotal = cellB27.getNumericCellValue();

            HSSFCell cellB32 = rows[32].getCell((short) 1);
            double mod3LinersTotal = cellB32.getNumericCellValue();

            HSSFCell cellB39 = rows[39].getCell((short) 1);
            double mod4LinersTotal = cellB39.getNumericCellValue();

            // Liner Defects
            HSSFCell cellD22 = rows[22].getCell((short) 3);
            double mod1LinersTotalDefects = cellD22.getNumericCellValue();

            HSSFCell cellD27 = rows[27].getCell((short) 3);
            double mod2LinersTotalDefects = cellD27.getNumericCellValue();

            HSSFCell cellD32 = rows[32].getCell((short) 3);
            double mod3LinersTotalDefects = cellD32.getNumericCellValue();

            HSSFCell cellD39 = rows[39].getCell((short) 3);
            double mod4LinersTotalDefects = cellD39.getNumericCellValue();

            // Shell Press Infeeds
            HSSFCell cellB43 = rows[43].getCell((short) 1);
            double mod1StolleTotal = cellB43.getNumericCellValue();

            HSSFCell cellB46 = rows[46].getCell((short) 1);
            double mod2StolleTotal = cellB46.getNumericCellValue();

            HSSFCell cellB50 = rows[50].getCell((short) 1);
            double mod3StolleTotal = cellB50.getNumericCellValue();

            HSSFCell cellB55 = rows[55].getCell((short) 1);
            double mod4StolleTotal = cellB55.getNumericCellValue();

            // End Counts Infeed
            HSSFCell cellB41 = rows[41].getCell((short) 1);
            double Stolle11 = cellB41.getNumericCellValue();

            HSSFCell cellB42 = rows[42].getCell((short) 1);
            double Stolle12 = cellB42.getNumericCellValue();

            HSSFCell cellB44 = rows[44].getCell((short) 1);
            double Stolle21 = cellB44.getNumericCellValue();

            HSSFCell cellB45 = rows[45].getCell((short) 1);
            double Stolle22 = cellB45.getNumericCellValue();

            HSSFCell cellB47 = rows[47].getCell((short) 1);
            double Stolle31 = cellB47.getNumericCellValue();

            HSSFCell cellB48 = rows[48].getCell((short) 1);
            double Stolle32 = cellB48.getNumericCellValue();

            HSSFCell cellB49 = rows[49].getCell((short) 1);
            double Stolle33 = cellB49.getNumericCellValue();

            HSSFCell cellB51 = rows[51].getCell((short) 1);
            double Stolle41 = cellB51.getNumericCellValue();

            HSSFCell cellB52 = rows[52].getCell((short) 1);
            double Stolle42 = cellB52.getNumericCellValue();

            HSSFCell cellB53 = rows[53].getCell((short) 1);
            double Stolle43 = cellB53.getNumericCellValue();

            HSSFCell cellB54 = rows[54].getCell((short) 1);
            double Stolle44 = cellB54.getNumericCellValue();

            // End Counts Spoilage
            HSSFCell cellD41 = rows[41].getCell((short) 4);
            String Stolle11Spoilage = cellD41.getStringCellValue() + "";
            if (Stolle11Spoilage.contains("-")) {
                Stolle11Spoilage = "0.00";
            } else {
                Stolle11Spoilage = (cellD41.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD42 = rows[42].getCell((short) 4);
            String Stolle12Spoilage = cellD42.getStringCellValue() + "";
            if (Stolle12Spoilage.contains("-")) {
                Stolle12Spoilage = "0.00";
            } else {
                Stolle12Spoilage = (cellD42.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD44 = rows[44].getCell((short) 4);
            String Stolle21Spoilage = cellD44.getStringCellValue() + "";
            if (Stolle21Spoilage.contains("-")) {
                Stolle21Spoilage = "0.00";
            } else {
                Stolle21Spoilage = (cellD44.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD45 = rows[45].getCell((short) 4);
            String Stolle22Spoilage = cellD45.getStringCellValue() + "";
            if (Stolle22Spoilage.contains("-")) {
                Stolle22Spoilage = "0.00";
            } else {
                Stolle22Spoilage = (cellD45.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD47 = rows[47].getCell((short) 4);
            String Stolle31Spoilage = cellD47.getStringCellValue() + "";
            if (Stolle31Spoilage.contains("-")) {
                Stolle31Spoilage = "0.00";
            } else {
                Stolle31Spoilage = (cellD47.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD48 = rows[48].getCell((short) 4);
            String Stolle32Spoilage = cellD48.getStringCellValue() + "";
            if (Stolle32Spoilage.contains("-")) {
                Stolle32Spoilage = "0.00";
            } else {
                Stolle32Spoilage = (cellD48.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD49 = rows[49].getCell((short) 4);
            String Stolle33Spoilage = cellD49.getStringCellValue() + "";
            if (Stolle33Spoilage.contains("-")) {
                Stolle33Spoilage = "0.00";
            } else {
                Stolle33Spoilage = (cellD49.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD51 = rows[51].getCell((short) 4);
            String Stolle41Spoilage = cellD51.getStringCellValue() + "";
            if (Stolle41Spoilage.contains("-")) {
                Stolle41Spoilage = "0.00";
            } else {
                Stolle41Spoilage = (cellD51.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD52 = rows[52].getCell((short) 4);
            String Stolle42Spoilage = cellD52.getStringCellValue() + "";
            if (Stolle42Spoilage.contains("-")) {
                Stolle42Spoilage = "0.00";
            } else {
                Stolle42Spoilage = (cellD52.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD53 = rows[53].getCell((short) 4);
            String Stolle43Spoilage = cellD53.getStringCellValue() + "";
            if (Stolle43Spoilage.contains("-")) {
                Stolle43Spoilage = "0.00";
            } else {
                Stolle43Spoilage = (cellD53.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellD54 = rows[54].getCell((short) 4);
            String Stolle44Spoilage = cellD54.getStringCellValue() + "";
            if (Stolle44Spoilage.contains("-")) {
                Stolle44Spoilage = "0.00";
            } else {
                Stolle44Spoilage = (cellD54.getStringCellValue() + "").substring(0, 4);
            }

            // Liner Infeeds
            HSSFCell cellB18 = rows[18].getCell((short) 1);
            double liner11 = cellB18.getNumericCellValue();

            HSSFCell cellB19 = rows[19].getCell((short) 1);
            double liner12 = cellB19.getNumericCellValue();

            HSSFCell cellB20 = rows[20].getCell((short) 1);
            double liner13 = cellB20.getNumericCellValue();

            HSSFCell cellB21 = rows[21].getCell((short) 1);
            double liner14 = cellB21.getNumericCellValue();

            HSSFCell cellB23 = rows[23].getCell((short) 1);
            double liner21 = cellB23.getNumericCellValue();

            HSSFCell cellB24 = rows[24].getCell((short) 1);
            double liner22 = cellB24.getNumericCellValue();

            HSSFCell cellB25 = rows[25].getCell((short) 1);
            double liner23 = cellB25.getNumericCellValue();

            HSSFCell cellB26 = rows[26].getCell((short) 1);
            double liner24 = cellB26.getNumericCellValue();

            HSSFCell cellB28 = rows[28].getCell((short) 1);
            double liner31 = cellB28.getNumericCellValue();

            HSSFCell cellB29 = rows[29].getCell((short) 1);
            double liner32 = cellB29.getNumericCellValue();

            HSSFCell cellB30 = rows[30].getCell((short) 1);
            double liner33 = cellB30.getNumericCellValue();

            HSSFCell cellB31 = rows[31].getCell((short) 1);
            double liner34 = cellB31.getNumericCellValue();

            HSSFCell cellB33 = rows[33].getCell((short) 1);
            double liner41 = cellB33.getNumericCellValue();

            HSSFCell cellB34 = rows[34].getCell((short) 1);
            double liner42 = cellB34.getNumericCellValue();

            HSSFCell cellB35 = rows[35].getCell((short) 1);
            double liner43 = cellB35.getNumericCellValue();

            HSSFCell cellB36 = rows[36].getCell((short) 1);
            double liner44 = cellB36.getNumericCellValue();

            HSSFCell cellB37 = rows[37].getCell((short) 1);
            double liner45 = cellB37.getNumericCellValue();

            HSSFCell cellB38 = rows[38].getCell((short) 1);
            double liner46 = cellB38.getNumericCellValue();

            // Liner Spoilage
            HSSFCell cellE18 = rows[18].getCell((short) 4);
            String linerSpoilage11 = cellE18.getStringCellValue() + "";
            System.out.println("Liner Spoilage 11 : " + linerSpoilage11);
            if (linerSpoilage11.contains("-")) {
                linerSpoilage11 = "0.00";
            } else {
                linerSpoilage11 = (cellE18.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE19 = rows[19].getCell((short) 4);
            String linerSpoilage12 = cellE19.getStringCellValue() + "";
            if (linerSpoilage12.contains("-")) {
                linerSpoilage12 = "0.00";
            } else {
                linerSpoilage12 = (cellE19.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE20 = rows[20].getCell((short) 4);
            String linerSpoilage13 = cellE20.getStringCellValue() + "";
            if (linerSpoilage13.contains("-")) {
                linerSpoilage13 = "0.00";
            } else {
                linerSpoilage13 = (cellE20.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE21 = rows[21].getCell((short) 4);
            String linerSpoilage14 = cellE21.getStringCellValue() + "";
            if (linerSpoilage14.contains("-")) {
                linerSpoilage14 = "0.00";
            } else {
                linerSpoilage14 = (cellE21.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE23 = rows[23].getCell((short) 4);
            String linerSpoilage21 = cellE23.getStringCellValue() + "";
            if (linerSpoilage21.contains("-")) {
                linerSpoilage21 = "0.00";
            } else {
                linerSpoilage21 = (cellE23.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE24 = rows[24].getCell((short) 4);
            String linerSpoilage22 = cellE24.getStringCellValue() + "";
            if (linerSpoilage22.contains("-")) {
                linerSpoilage22 = "0.00";
            } else {
                linerSpoilage22 = (cellE24.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE25 = rows[25].getCell((short) 4);
            String linerSpoilage23 = cellE25.getStringCellValue() + "";
            if (linerSpoilage23.contains("-")) {
                linerSpoilage23 = "0.00";
            } else {
                linerSpoilage23 = (cellE25.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE26 = rows[26].getCell((short) 4);
            String linerSpoilage24 = cellE26.getStringCellValue() + "";
            if (linerSpoilage24.contains("-")) {
                linerSpoilage24 = "0.00";
            } else {
                linerSpoilage24 = (cellE26.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE28 = rows[28].getCell((short) 4);
            String linerSpoilage31 = cellE28.getStringCellValue() + "";
            System.out.println("Liner Spoilage 31 : " + linerSpoilage31);
            if (linerSpoilage31.contains("-")) {
                linerSpoilage31 = "0.00";
            } else {
                linerSpoilage31 = (cellE28.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE29 = rows[29].getCell((short) 4);
            String linerSpoilage32 = cellE29.getStringCellValue() + "";
            if (linerSpoilage32.contains("-")) {
                linerSpoilage32 = "0.00";
            } else {
                linerSpoilage32 = (cellE29.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE30 = rows[30].getCell((short) 4);
            String linerSpoilage33 = cellE30.getStringCellValue() + "";
            if (linerSpoilage33.contains("-")) {
                linerSpoilage33 = "0.00";
            } else {
                linerSpoilage33 = (cellE30.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE31 = rows[31].getCell((short) 4);
            String linerSpoilage34 = cellE31.getStringCellValue() + "";
            if (linerSpoilage34.contains("-")) {
                linerSpoilage34 = "0.00";
            } else {
                linerSpoilage34 = (cellE31.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE33 = rows[33].getCell((short) 4);
            String linerSpoilage41 = cellE33.getStringCellValue() + "";
            if (linerSpoilage41.contains("-")) {
                linerSpoilage41 = "0.00";
            } else {
                linerSpoilage41 = (cellE33.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE34 = rows[34].getCell((short) 4);
            String linerSpoilage42 = cellE34.getStringCellValue() + "";
            if (linerSpoilage42.contains("-")) {
                linerSpoilage42 = "0.00";
            } else {
                linerSpoilage42 = (cellE34.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE35 = rows[35].getCell((short) 4);
            String linerSpoilage43 = cellE35.getStringCellValue() + "";
            if (linerSpoilage43.contains("-")) {
                linerSpoilage43 = "0.00";
            } else {
                linerSpoilage43 = (cellE35.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE36 = rows[36].getCell((short) 4);
            String linerSpoilage44 = cellE36.getStringCellValue() + "";
            if (linerSpoilage44.contains("-")) {
                linerSpoilage44 = "0.00";
            } else {
                linerSpoilage44 = (cellE36.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE37 = rows[37].getCell((short) 4);
            String linerSpoilage45 = cellE37.getStringCellValue() + "";
            if (linerSpoilage45.contains("-")) {
                linerSpoilage45 = "0.00";
            } else {
                linerSpoilage45 = (cellE37.getStringCellValue() + "").substring(0, 4);
            }

            HSSFCell cellE38 = rows[38].getCell((short) 4);
            String linerSpoilage46 = cellE38.getStringCellValue() + "";
            System.out.println("LinerSpoilage46 : " + linerSpoilage46);
            if (linerSpoilage46.contains("-")) {
                linerSpoilage46 = "0.00";
            } else {
                linerSpoilage46 = (cellE38.getStringCellValue() + "").substring(0, 4);
            }

            /////////////////////////////////
            // Shell Press Production           
//            HSSFCell cellE38 = rows[38].getCell((short) 4);
//            String linerSpoilage46 = cellE38.getStringCellValue() + "";
//            System.out.println("LinerSpoilage46 : " + linerSpoilage46);
//            if (linerSpoilage46.contains("-")) {
//                linerSpoilage46 = "0.00";
//            } else {
//                linerSpoilage46 = (cellE38.getStringCellValue() + "").substring(0, 4);
//            }
//            
//            HSSFCell cellE38 = rows[38].getCell((short) 4);
//            String linerSpoilage46 = cellE38.getStringCellValue() + "";
//            System.out.println("LinerSpoilage46 : " + linerSpoilage46);
//            if (linerSpoilage46.contains("-")) {
//                linerSpoilage46 = "0.00";
//            } else {
//                linerSpoilage46 = (cellE38.getStringCellValue() + "").substring(0, 4);
//            }
            //////////////////////////////////
            System.out.println("Start Date : " + startDate);
            System.out.println("End Date : " + endDate);

            HSSFCell calendarDate = rows[8].getCell((short) 1);
            String calendarDateString = calendarDate.getStringCellValue();

            if (startDate.equalsIgnoreCase(endDate) && calendarDateString.equalsIgnoreCase("Yes")) {

                try {

                    // Create a new LinerAndShellsEntry and set JTextfields from Ecel File.
//                    LinerAndShellsEntry linerAndShellsEntry = new LinerAndShellsEntry(1, -1);
//
                    String year = startDate.substring(6, 10);
                    int yearInt = Integer.parseInt(year);
                    String month = startDate.substring(3, 5);
                    int monthInt = Integer.parseInt(month) - 1;
                    String day = startDate.substring(0, 2);
                    int dayInt = Integer.parseInt(day);

                    //     model.setDate(yearInt, monthInt, dayInt);
                    new ShellPressProduction(1, -1);

                    SP01JTextfield.setText("0");
                    Optime2JTextfield.setText("0");
                    Optime3JTextfield.setText((int) shellPress31Infeed + "");
                    FMI41JTextfield.setText("0");
                    FMI42JTextfield.setText("0");
                    Formatec04JTextfield.setText((int) shellPress31Infeed + "");

                    model.setDate(yearInt, monthInt, dayInt);

                    ////////////////////////////////////////////////////////////////////////
                    StolleProduction stolleProduction = new StolleProduction(1, -1);
                    stolleProduction.setStolleProductionToInput(
                            yearInt,
                            monthInt,
                            dayInt,
                            (int) Stolle11,
                            (int) Stolle12,
                            (int) Stolle21,
                            (int) Stolle22,
                            (int) Stolle31,
                            (int) Stolle32,
                            (int) Stolle33,
                            (int) Stolle41,
                            (int) Stolle42,
                            (int) Stolle43,
                            (int) Stolle44
                    );

                    stolleProduction.calculateTotals();

                    ////////////////////////////////////////////////////////////////////////
                    LinerProduction linerProduction = new LinerProduction(1, -1);
                    LinerProduction.setLinerProductionToInput(
                            yearInt,
                            monthInt,
                            dayInt,
                            (int) liner11,
                            (int) liner12,
                            (int) liner13,
                            (int) liner14,
                            (int) liner21,
                            (int) liner22,
                            (int) liner23,
                            (int) liner24,
                            (int) liner31,
                            (int) liner32,
                            (int) liner33,
                            (int) liner34,
                            (int) liner41,
                            (int) liner42,
                            (int) liner43,
                            (int) liner44,
                            (int) liner45,
                            (int) liner46
                    );

                    ////////////////////////////////////////////////////////////////////////   
                    StolleSpoilage stolleSpoilage = new StolleSpoilage(1, -1);
                    stolleSpoilage.setStolleSpoilageToInput(
                            yearInt,
                            monthInt,
                            dayInt,
                            Double.valueOf(Stolle11Spoilage),
                            Double.valueOf(Stolle12Spoilage),
                            Double.valueOf(Stolle21Spoilage),
                            Double.valueOf(Stolle22Spoilage),
                            Double.valueOf(Stolle31Spoilage),
                            Double.valueOf(Stolle32Spoilage),
                            Double.valueOf(Stolle33Spoilage),
                            Double.valueOf(Stolle41Spoilage),
                            Double.valueOf(Stolle42Spoilage),
                            Double.valueOf(Stolle43Spoilage),
                            Double.valueOf(Stolle44Spoilage)
                    );

                    ////////////////////////////////////////////////////////////////////////
                    LinerSpoilage linerSpoilage = new LinerSpoilage(1, -1);
                    LinerSpoilage.setLinerSpoilageToInput(
                            yearInt,
                            monthInt,
                            dayInt,
                            Double.valueOf(linerSpoilage11),
                            Double.valueOf(linerSpoilage12),
                            Double.valueOf(linerSpoilage13),
                            Double.valueOf(linerSpoilage14),
                            Double.valueOf(linerSpoilage21),
                            Double.valueOf(linerSpoilage22),
                            Double.valueOf(linerSpoilage23),
                            Double.valueOf(linerSpoilage24),
                            Double.valueOf(linerSpoilage31),
                            Double.valueOf(linerSpoilage32),
                            Double.valueOf(linerSpoilage33),
                            Double.valueOf(linerSpoilage34),
                            Double.valueOf(linerSpoilage41),
                            Double.valueOf(linerSpoilage42),
                            Double.valueOf(linerSpoilage43),
                            Double.valueOf(linerSpoilage44),
                            Double.valueOf(linerSpoilage45),
                            Double.valueOf(linerSpoilage46)
                    );

                    //    calculateTotals();
                    //     JOptionPane.showMessageDialog(null, "Double Check Dates and Values then Save Records.");
                } catch (SQLException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Excel File is for more than 1 day or not for correct TimeFrame.");
            }

            ////////////////
        }

        // Import into Database with matching dates if it doesnt exist for LinersAndShells, LinerDefects, EndCounts
        // If exist skip that individual section
    }

    public static void exportToExcel() {

        String[] typesArray = {"Shell Press Production",};
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

                query = "SELECT * FROM MainShellPressProduction WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(ShellPressProduction.class.getName()).log(Level.SEVERE, null, ex);
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
            FileOutputStream output = new FileOutputStream("MaintenanceExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("MaintenanceExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
