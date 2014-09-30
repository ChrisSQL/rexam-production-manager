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
import javax.swing.text.PlainDocument;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.maintenance.rexam.LinerProduction.Liner34MonthlyJTextField;
import static com.maintenance.rexam.LinerProduction.Liner41MonthlyJTextField;
import static com.maintenance.rexam.LinerProduction.Liner42MonthlyJTextField;
import static com.maintenance.rexam.LinerProduction.Liner43MonthlyJTextField;
import static com.maintenance.rexam.LinerProduction.Liner44MonthlyJTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class LineBalance {

    static JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;
    static JTextField Mod123ShellPressJTextfield, Mod123LinersJTextfield, Mod123ConversionJTextfield, Mod4ShellPressJTextfield, Mod4LinersJTextfield, Mod4ConversionJTextfield, Mod123UnlinedJTextfield, Mod123linedJTextfield, Mod4UnlinedJTextfield, Mod4linedJTextfield, totalJTextField;
    static JTextField Mod123ShellPressMonthly, Mod123LinersMonthly, Mod123ConversionMonthly, Mod4ShellPressMonthly, Mod4LinersMonthly, Mod4ConversionMonthly, Mod123UnlinedMonthly, Mod123linedMonthly, Mod4UnlinedMonthly, Mod4linedMonthly, totalMonthly;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;

    public static void main(String[] args) throws SQLException {

        new LineBalance(1, -1);

    }

    public LineBalance(int idIn, int view) throws SQLException {

        // Add a view to analytics.
        try {
            SQLiteConnection.incrementViewsAnalytics(0, 0, 0, 0, 0, 0, 0, 0, 1);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

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

        frame15.setTitle("Liner Balance");
        frame15.setSize(360, 650);
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

        Mod123ShellPressJTextfield = new JTextField();
        Mod123ShellPressJTextfield.setEditable(false);
        Mod123ShellPressJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc01 = (PlainDocument) Mod123ShellPressJTextfield.getDocument();
        doc01.setDocumentFilter(new MyIntFilter());

        Mod123LinersJTextfield = new JTextField();
        Mod123LinersJTextfield.setEditable(false);
        Mod123LinersJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc1 = (PlainDocument) Mod123LinersJTextfield.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        Mod123ConversionJTextfield = new JTextField();
        Mod123ConversionJTextfield.setEditable(false);
        Mod123ConversionJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc2 = (PlainDocument) Mod123ConversionJTextfield.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());

        Mod4ShellPressJTextfield = new JTextField();
        Mod4ShellPressJTextfield.setEditable(false);
        Mod4ShellPressJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc3 = (PlainDocument) Mod4ShellPressJTextfield.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());

        Mod4LinersJTextfield = new JTextField();
        Mod4LinersJTextfield.setEditable(false);
        Mod4LinersJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc4 = (PlainDocument) Mod4LinersJTextfield.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());

        Mod4ConversionJTextfield = new JTextField();
        Mod4ConversionJTextfield.setEditable(false);
        Mod4ConversionJTextfield.setBackground(new Color(255, 255, 123));
        PlainDocument doc5 = (PlainDocument) Mod4ConversionJTextfield.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());

        Mod123UnlinedJTextfield = new JTextField();
        Mod123UnlinedJTextfield.setText("0");
        PlainDocument doc5A = (PlainDocument) Mod123UnlinedJTextfield.getDocument();
        doc5A.setDocumentFilter(new MyIntFilter());

        Mod123linedJTextfield = new JTextField();
        Mod123linedJTextfield.setText("0");
        PlainDocument doc5B = (PlainDocument) Mod123linedJTextfield.getDocument();
        doc5B.setDocumentFilter(new MyIntFilter());

        Mod4UnlinedJTextfield = new JTextField();
        Mod4UnlinedJTextfield.setText("0");
        PlainDocument doc5C = (PlainDocument) Mod4UnlinedJTextfield.getDocument();
        doc5C.setDocumentFilter(new MyIntFilter());

        Mod4linedJTextfield = new JTextField();
        Mod4linedJTextfield.setText("0");
        PlainDocument doc5D = (PlainDocument) Mod4linedJTextfield.getDocument();
        doc5D.setDocumentFilter(new MyIntFilter());

        totalJTextField = new JTextField();
        totalJTextField.setText("0");
        PlainDocument doc5E = (PlainDocument) totalJTextField.getDocument();
        doc5E.setDocumentFilter(new MyIntFilter());

        Mod123ShellPressMonthly = new JTextField();
        PlainDocument doc02 = (PlainDocument) Mod123ShellPressMonthly.getDocument();
        doc02.setDocumentFilter(new MyIntFilter());
        Mod123LinersMonthly = new JTextField();
        PlainDocument doc13 = (PlainDocument) Mod123LinersMonthly.getDocument();
        doc13.setDocumentFilter(new MyIntFilter());
        Mod123ConversionMonthly = new JTextField();
        PlainDocument doc14 = (PlainDocument) Mod123ConversionMonthly.getDocument();
        doc14.setDocumentFilter(new MyIntFilter());
        Mod4ShellPressMonthly = new JTextField();
        PlainDocument doc15 = (PlainDocument) Mod4ShellPressMonthly.getDocument();
        doc15.setDocumentFilter(new MyIntFilter());
        Mod4LinersMonthly = new JTextField();
        PlainDocument doc16 = (PlainDocument) Mod4LinersMonthly.getDocument();
        doc16.setDocumentFilter(new MyIntFilter());

        Mod4ConversionMonthly = new JTextField();
        PlainDocument doc17A = (PlainDocument) Mod4ConversionMonthly.getDocument();
        doc17A.setDocumentFilter(new MyIntFilter());
        Mod123UnlinedMonthly = new JTextField();
        PlainDocument doc17B = (PlainDocument) Mod123UnlinedMonthly.getDocument();
        doc17B.setDocumentFilter(new MyIntFilter());
        Mod123linedMonthly = new JTextField();
        PlainDocument doc17C = (PlainDocument) Mod123linedMonthly.getDocument();
        doc17C.setDocumentFilter(new MyIntFilter());

        Mod4UnlinedMonthly = new JTextField();
        PlainDocument doc17D = (PlainDocument) Mod4UnlinedMonthly.getDocument();
        doc17D.setDocumentFilter(new MyIntFilter());
        Mod4linedMonthly = new JTextField();
        PlainDocument doc17E = (PlainDocument) Mod4linedMonthly.getDocument();
        doc17E.setDocumentFilter(new MyIntFilter());
        totalMonthly = new JTextField();
        PlainDocument doc17F = (PlainDocument) totalMonthly.getDocument();
        doc17F.setDocumentFilter(new MyIntFilter());
        
        setMod1234ToDate(modifiedDate);

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceLineBalanceCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    Mod123ShellPressMonthly.setText(String.valueOf(total[0]));
                    Mod123LinersMonthly.setText(String.valueOf(total[1]));
                    Mod123ConversionMonthly.setText(String.valueOf(total[2]));
                    Mod4ShellPressMonthly.setText(String.valueOf(total[3]));
                    Mod4LinersMonthly.setText(String.valueOf(total[4]));
                    Mod4ConversionMonthly.setText(String.valueOf(total[5]));
                    Mod123UnlinedMonthly.setText(String.valueOf(total[6]));
                    Mod123linedMonthly.setText(String.valueOf(total[7]));
                    Mod4UnlinedMonthly.setText(String.valueOf(total[8]));
                    Mod4linedMonthly.setText(String.valueOf(total[9]));
                    totalMonthly.setText(String.valueOf(total[10]));
                    Liner34MonthlyJTextField.setText(String.valueOf(total[11]));
                    Liner41MonthlyJTextField.setText(String.valueOf(total[12]));
                    Liner42MonthlyJTextField.setText(String.valueOf(total[13]));
                    Liner43MonthlyJTextField.setText(String.valueOf(total[14]));
                    Liner44MonthlyJTextField.setText(String.valueOf(total[15]));

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
                    new LineBalance(1, -1);
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
                    SQLiteConnection.MaintenanceLineBalanceInsert(
                            SQLiteConnection.MaintenanceLineBalanceGetHighestID() + 1,
                            date,
                            Integer.valueOf(Mod123UnlinedJTextfield.getText()),
                            Integer.valueOf(Mod123linedJTextfield.getText()),
                            Integer.valueOf(Mod4UnlinedJTextfield.getText()),
                            Integer.valueOf(Mod4linedJTextfield.getText())
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
                    Logger.getLogger(LineBalance.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new LineBalance(1, -1);
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
                    new LineBalance(1, -2);
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
                    new LineBalance(1, -3);
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
                    SQLiteConnection.MaintenanceLineBalanceUpdate(
                            date,
                            Integer.valueOf(Mod123UnlinedJTextfield.getText()),
                            Integer.valueOf(Mod123linedJTextfield.getText()),
                            Integer.valueOf(Mod4UnlinedJTextfield.getText()),
                            Integer.valueOf(Mod4linedJTextfield.getText()),
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
                    LineBalance.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LineBalance.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] total = SQLiteConnection.MaintenanceLineBalanceGetNextEntryById(currentId);

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

                        Mod123UnlinedJTextfield.setText(String.valueOf(total[2]));
                        Mod123linedJTextfield.setText(String.valueOf(total[3]));
                        Mod4UnlinedJTextfield.setText(String.valueOf(total[4]));
                        Mod4linedJTextfield.setText(String.valueOf(total[5]));

                        int totalField = Integer.parseInt(String.valueOf(total[2])) + Integer.parseInt(String.valueOf(total[3])) + Integer.parseInt(String.valueOf(total[4])) + Integer.parseInt(String.valueOf(total[5]));
                        totalJTextField.setText(totalField + "");
                        
                        setMod1234ToDate(dateFormatted);

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

                    Object[] total = SQLiteConnection.MaintenanceLineBalanceGetPreviousEntryById(currentId);

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

                        Mod123UnlinedJTextfield.setText(String.valueOf(total[2]));
                        Mod123linedJTextfield.setText(String.valueOf(total[3]));
                        Mod4UnlinedJTextfield.setText(String.valueOf(total[4]));
                        Mod4linedJTextfield.setText(String.valueOf(total[5]));

                        int totalField = Integer.parseInt(String.valueOf(total[2])) + Integer.parseInt(String.valueOf(total[3])) + Integer.parseInt(String.valueOf(total[4])) + Integer.parseInt(String.valueOf(total[5]));
                        totalJTextField.setText(totalField + "");
                        
                        setMod1234ToDate(dateFormatted);

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

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        //buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        JPanel optionPanel1 = new JPanel(new GridLayout(16, 2));
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

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod123ShellPressJTextfield);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod123LinersJTextfield);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod123ConversionJTextfield);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 4", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod4ShellPressJTextfield);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod4LinersJTextfield);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod4ConversionJTextfield);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod123UnlinedJTextfield);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod123linedJTextfield);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 4", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod4UnlinedJTextfield);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod4linedJTextfield);

            optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
            optionPanel1.add(totalJTextField);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceLineBalanceGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod123ShellPressJTextfield);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod123LinersJTextfield);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod123ConversionJTextfield);

            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod4ShellPressJTextfield);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod4LinersJTextfield);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod4ConversionJTextfield);

            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 1-2-3 ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod123UnlinedJTextfield);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod123linedJTextfield);

            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod4UnlinedJTextfield);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod4linedJTextfield);

            optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
            optionPanel1.add(totalJTextField);
        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(15, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);
            
            optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
            optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod123ShellPressMonthly);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod123LinersMonthly);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod123ConversionMonthly);
            
            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
            optionPanel1.add(Mod4ShellPressMonthly);

            optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
            optionPanel1.add(Mod4LinersMonthly);

            optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
            optionPanel1.add(Mod4ConversionMonthly);
            
            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 1-2-3 ", SwingConstants.CENTER));

            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod123UnlinedMonthly);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod123linedMonthly);
            
            optionPanel1.add(new JLabel("", SwingConstants.CENTER));
            optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));
            
            optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
            optionPanel1.add(Mod4UnlinedMonthly);

            optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
            optionPanel1.add(Mod4linedMonthly);

            optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
            optionPanel1.add(totalMonthly);

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

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceLineBalanceGetHighestID();
            System.out.println("MaintenanceLineBalanceGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] total = new Object[7];
        try {
            total = SQLiteConnection.MaintenanceLineBalanceReturnEntryByID(highestID);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] total2 = new Object[8];
        try {
            total = SQLiteConnection.MaintenanceLineBalanceReturnEntryByID(highestID);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Date " + total[1]);

        String dateFormatted = (String) total[1];
        System.out.println("Date Formatted : " + dateFormatted);
        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

        model.setDate(year, month, day);
        model.setSelected(true);

        Mod123UnlinedJTextfield.setText(String.valueOf(total[2]));
        Mod123linedJTextfield.setText(String.valueOf(total[3]));
        Mod4UnlinedJTextfield.setText(String.valueOf(total[4]));
        Mod4linedJTextfield.setText(String.valueOf(total[5]));

        Mod123ShellPressJTextfield.setText(String.valueOf(total[10]));
        Mod123LinersJTextfield.setText(String.valueOf(total[39]));
        Mod123ConversionJTextfield.setText(String.valueOf(total[22]));
        Mod4ShellPressJTextfield.setText(String.valueOf(total[14]));
        Mod4LinersJTextfield.setText(String.valueOf(total[43]));
        Mod4ConversionJTextfield.setText(String.valueOf(total[27]));

        int totalField = Integer.parseInt(String.valueOf(total[2])) + Integer.parseInt(String.valueOf(total[3])) + Integer.parseInt(String.valueOf(total[4])) + Integer.parseInt(String.valueOf(total[5]));

        totalJTextField.setText(totalField + "");

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LineBalance(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(LineBalance.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LineBalance.createSummaryScreen();
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

                LineBalance.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Balance");
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

        JPanel summaryPanel = SQLiteConnection.MaintenanceLineBalanceSummaryTable(1);
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setLineBalanceToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceLineBalanceReturnEntryByID(currentId);

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

                Mod123UnlinedJTextfield.setText(String.valueOf(total[2]));
                Mod123linedJTextfield.setText(String.valueOf(total[3]));
                Mod4UnlinedJTextfield.setText(String.valueOf(total[4]));
                Mod4linedJTextfield.setText(String.valueOf(total[5]));

                int totalField = Integer.parseInt(String.valueOf(total[2])) + Integer.parseInt(String.valueOf(total[3])) + Integer.parseInt(String.valueOf(total[4])) + Integer.parseInt(String.valueOf(total[5]));

                totalJTextField.setText(totalField + "");

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

    public void setMod1234ToDate(String dateIn) {
        
        System.out.println("Date In : " + dateIn);

        try {

            Object[] total = SQLiteConnection.MaintenanceLineBalanceReturnEntryByDate2(dateIn);

//            if (total[0] == null) {
//
//             //   SQLiteConnection.infoBox("No Next Result.", "");
//
//            } else {

                Mod123ShellPressJTextfield.setText(String.valueOf(total[10]));
                Mod123LinersJTextfield.setText(String.valueOf(total[39]));
                Mod123ConversionJTextfield.setText(String.valueOf(total[22]));
                Mod4ShellPressJTextfield.setText(String.valueOf(total[14]));
                Mod4LinersJTextfield.setText(String.valueOf(total[43]));
                Mod4ConversionJTextfield.setText(String.valueOf(total[27]));



 //           }

            System.out.println("CurrentID " + currentId);

            // Fill Boxes with results
            // model.setDate(year2, month2, day2);
            model.setSelected(true);

         //   currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public void setMod1234ToMonth(String dateIn) {
        
        System.out.println("Date In : " + dateIn);

        try {

            Object[] total = SQLiteConnection.MaintenanceLineBalanceReturnEntryByDate2(dateIn);

//            if (total[0] == null) {
//
//             //   SQLiteConnection.infoBox("No Next Result.", "");
//
//            } else {

                Mod123ShellPressJTextfield.setText(String.valueOf(total[10]));
                Mod123LinersJTextfield.setText(String.valueOf(total[39]));
                Mod123ConversionJTextfield.setText(String.valueOf(total[22]));
                Mod4ShellPressJTextfield.setText(String.valueOf(total[14]));
                Mod4LinersJTextfield.setText(String.valueOf(total[43]));
                Mod4ConversionJTextfield.setText(String.valueOf(total[27]));



 //           }

            System.out.println("CurrentID " + currentId);

            // Fill Boxes with results
            // model.setDate(year2, month2, day2);
            model.setSelected(true);

         //   currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void exportToExcel() {

        String[] typesArray = {"Liner B"};
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

                query = "SELECT * FROM MainLineBalance WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(LineBalance.class.getName()).log(Level.SEVERE, null, ex);
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
