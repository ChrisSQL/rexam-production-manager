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
import java.awt.Desktop;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class BalancerProduction {

    static JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;

    static JTextField Balancer1AJTextField, Balancer2AJTextField, Balancer3AJTextField, Balancer4AJTextField, Balancer4ANewJTextField, Balancer1BJTextField, Balancer2BJTextField, Balancer3BJTextField, Balancer4BJTextField;

    static JTextField Balancer1AMonthlyJTextField, Balancer2AMonthlyJTextField, Balancer3AMonthlyJTextField, Balancer4AMonthlyTextField, Balancer4ANewMonthlyTextField, Balancer1BMonthlyTextField, Balancer2BMonthlyTextField, Balancer3BMonthlyTextField, Balancer4BMonthlyTextField;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    static JFrame frameSummary;

    public static void main(String[] args) throws SQLException {

       
                try {
                    BalancerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

           

    }

    public BalancerProduction(int idIn, int view) throws SQLException {

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
        
        createSummaryScreen();

        JFrame frame15 = new JFrame();
        // frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame15.setTitle("Balancer Production");
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

        Balancer1AJTextField = new JTextField();
        Balancer1AJTextField.setText("0");
        PlainDocument doc01 = (PlainDocument) Balancer1AJTextField.getDocument();
        doc01.setDocumentFilter(new MyIntFilter());

        Balancer1AJTextField.addFocusListener(new FocusListener() {

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

        Balancer2AJTextField = new JTextField();
        Balancer2AJTextField.setText("0");
        PlainDocument doc1 = (PlainDocument) Balancer2AJTextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        Balancer2AJTextField.addFocusListener(new FocusListener() {

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

        Balancer3AJTextField = new JTextField();
        Balancer3AJTextField.setText("0");
        PlainDocument doc2 = (PlainDocument) Balancer3AJTextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        Balancer3AJTextField.addFocusListener(new FocusListener() {

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
        Balancer4AJTextField = new JTextField();
        Balancer4AJTextField.setText("0");
        PlainDocument doc3 = (PlainDocument) Balancer4AJTextField.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());
        Balancer4AJTextField.addFocusListener(new FocusListener() {

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
        Balancer4ANewJTextField = new JTextField();
        Balancer4ANewJTextField.setText("0");
        PlainDocument doc4 = (PlainDocument) Balancer4ANewJTextField.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());
        Balancer4ANewJTextField.addFocusListener(new FocusListener() {

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
        Balancer1BJTextField = new JTextField();
        Balancer1BJTextField.setText("0");
        PlainDocument doc5 = (PlainDocument) Balancer1BJTextField.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());
        Balancer1BJTextField.addFocusListener(new FocusListener() {

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

        Balancer2BJTextField = new JTextField();
        Balancer2BJTextField.setText("0");
        PlainDocument doc5A = (PlainDocument) Balancer2BJTextField.getDocument();
        doc5A.setDocumentFilter(new MyIntFilter());
        Balancer2BJTextField.addFocusListener(new FocusListener() {

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

        Balancer3BJTextField = new JTextField();
        Balancer3BJTextField.setText("0");
        PlainDocument doc5B = (PlainDocument) Balancer3BJTextField.getDocument();
        doc5B.setDocumentFilter(new MyIntFilter());
        Balancer3BJTextField.addFocusListener(new FocusListener() {

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

        Balancer4BJTextField = new JTextField();
        Balancer4BJTextField.setText("0");
        PlainDocument doc5C = (PlainDocument) Balancer4BJTextField.getDocument();
        doc5C.setDocumentFilter(new MyIntFilter());
        Balancer4BJTextField.addFocusListener(new FocusListener() {

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

        Balancer1AMonthlyJTextField = new JTextField();
        PlainDocument doc02 = (PlainDocument) Balancer1AMonthlyJTextField.getDocument();
        doc02.setDocumentFilter(new MyIntFilter());
        Balancer2AMonthlyJTextField = new JTextField();
        PlainDocument doc13 = (PlainDocument) Balancer2AMonthlyJTextField.getDocument();
        doc13.setDocumentFilter(new MyIntFilter());
        Balancer3AMonthlyJTextField = new JTextField();
        PlainDocument doc14 = (PlainDocument) Balancer3AMonthlyJTextField.getDocument();
        doc14.setDocumentFilter(new MyIntFilter());
        Balancer4AMonthlyTextField = new JTextField();
        PlainDocument doc15 = (PlainDocument) Balancer4AMonthlyTextField.getDocument();
        doc15.setDocumentFilter(new MyIntFilter());
        Balancer4ANewMonthlyTextField = new JTextField();
        PlainDocument doc16 = (PlainDocument) Balancer4ANewMonthlyTextField.getDocument();
        doc16.setDocumentFilter(new MyIntFilter());

        Balancer1BMonthlyTextField = new JTextField();
        PlainDocument doc17A = (PlainDocument) Balancer1BMonthlyTextField.getDocument();
        doc17A.setDocumentFilter(new MyIntFilter());
        Balancer2BMonthlyTextField = new JTextField();
        PlainDocument doc17B = (PlainDocument) Balancer2BMonthlyTextField.getDocument();
        doc17B.setDocumentFilter(new MyIntFilter());
        Balancer3BMonthlyTextField = new JTextField();
        PlainDocument doc17C = (PlainDocument) Balancer3BMonthlyTextField.getDocument();
        doc17C.setDocumentFilter(new MyIntFilter());
        Balancer4BMonthlyTextField = new JTextField();
        PlainDocument doc17D = (PlainDocument) Balancer4BMonthlyTextField.getDocument();
        doc17D.setDocumentFilter(new MyIntFilter());

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceBalancerProductionCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    Balancer1AMonthlyJTextField.setText(String.valueOf(total[0]));
                    Balancer2AMonthlyJTextField.setText(String.valueOf(total[1]));
                    Balancer3AMonthlyJTextField.setText(String.valueOf(total[2]));
                    Balancer4AMonthlyTextField.setText(String.valueOf(total[3]));
                    Balancer4ANewMonthlyTextField.setText(String.valueOf(total[4]));
                    Balancer1BMonthlyTextField.setText(String.valueOf(total[5]));
                    Balancer2BMonthlyTextField.setText(String.valueOf(total[6]));
                    Balancer3BMonthlyTextField.setText(String.valueOf(total[7]));
                    Balancer4BMonthlyTextField.setText(String.valueOf(total[8]));

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
                    new BalancerProduction(1, -1);
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
                calculateTotals();
                


                try {
                    SQLiteConnection.MaintenanceBalancerProductionInsert(SQLiteConnection.MaintenanceBalancerProductionGetHighestID() + 1,
                            date,
                            Integer.valueOf(Balancer1AJTextField.getText()),
                            Integer.valueOf(Balancer2AJTextField.getText()),
                            Integer.valueOf(Balancer3AJTextField.getText()),
                            Integer.valueOf(Balancer4AJTextField.getText()),
                            Integer.valueOf(Balancer4ANewJTextField.getText()),
                            Integer.valueOf(Balancer1BJTextField.getText()),
                            Integer.valueOf(Balancer2BJTextField.getText()),
                            Integer.valueOf(Balancer3BJTextField.getText()),
                            Integer.valueOf(Balancer4BJTextField.getText())
                    );

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
                frame15.dispose();

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame15.dispose();
                try {
                    BalancerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame15.dispose();
                try {
                    BalancerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        monthly = new JButton("Monthly");
        monthly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new BalancerProduction(1, -3);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame15.dispose();

            }
        });

        update = new JButton("Update Record");      

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame15.dispose();
                try {
                    BalancerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] total = SQLiteConnection.MaintenanceBalancerProductionReturnEntryByDate(selectedDate);

                    if (total[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) total[0];

                        Balancer1AJTextField.setText(String.valueOf(total[2]));
                        Balancer2AJTextField.setText(String.valueOf(total[3]));
                        Balancer3AJTextField.setText(String.valueOf(total[4]));
                        Balancer4AJTextField.setText(String.valueOf(total[5]));
                        Balancer4ANewJTextField.setText(String.valueOf(total[6]));
                        Balancer1BJTextField.setText(String.valueOf(total[7]));

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

                    Object[] total = SQLiteConnection.MaintenanceBalancerProductionGetNextEntryById(currentId);

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

                        Balancer1AJTextField.setText(String.valueOf(total[2]));
                        Balancer2AJTextField.setText(String.valueOf(total[3]));
                        Balancer3AJTextField.setText(String.valueOf(total[4]));
                        Balancer4AJTextField.setText(String.valueOf(total[5]));
                        Balancer4ANewJTextField.setText(String.valueOf(total[6]));
                        Balancer1BJTextField.setText(String.valueOf(total[7]));
                        Balancer2BJTextField.setText(String.valueOf(total[8]));
                        Balancer3BJTextField.setText(String.valueOf(total[9]));
                        Balancer4BJTextField.setText(String.valueOf(total[10]));

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

                    Object[] total = SQLiteConnection.MaintenanceBalancerProductionGetPreviousEntryById(currentId);

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

                        Balancer1AJTextField.setText(String.valueOf(total[2]));
                        Balancer2AJTextField.setText(String.valueOf(total[3]));
                        Balancer3AJTextField.setText(String.valueOf(total[4]));
                        Balancer4AJTextField.setText(String.valueOf(total[5]));
                        Balancer4ANewJTextField.setText(String.valueOf(total[6]));
                        Balancer1BJTextField.setText(String.valueOf(total[7]));
                        Balancer2BJTextField.setText(String.valueOf(total[8]));
                        Balancer3BJTextField.setText(String.valueOf(total[9]));
                        Balancer4BJTextField.setText(String.valueOf(total[10]));

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
                int W11Int = Integer.valueOf(Balancer2AJTextField.getText());
                int W12Int = Integer.valueOf(Balancer3AJTextField.getText());
                int W21Int = Integer.valueOf(Balancer4AJTextField.getText());
                int W22Int = Integer.valueOf(Balancer4ANewJTextField.getText());
                int W31Int = Integer.valueOf(Balancer1BJTextField.getText());

            }
        });

        calculateTotal2 = new JButton("Calculate Total 2");
        calculateTotal2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int W11Int = Integer.valueOf(Balancer2AJTextField.getText());
                int W12Int = Integer.valueOf(Balancer3AJTextField.getText());
                int W21Int = Integer.valueOf(Balancer4AJTextField.getText());
                int W22Int = Integer.valueOf(Balancer4ANewJTextField.getText());
                int W31Int = Integer.valueOf(Balancer1BJTextField.getText());

            }
        });

        calculateTotal3 = new JButton("Calculate Total 3");
        calculateTotal3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int W11Int = Integer.valueOf(Balancer2AJTextField.getText());
                int W12Int = Integer.valueOf(Balancer3AJTextField.getText());
                int W21Int = Integer.valueOf(Balancer4AJTextField.getText());
                int W22Int = Integer.valueOf(Balancer4ANewJTextField.getText());
                int W31Int = Integer.valueOf(Balancer1BJTextField.getText());

            }
        });

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        //buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        JPanel optionPanel1 = new JPanel(new GridLayout(10, 2));
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

            optionPanel1.add(new JLabel("Balancer 1A", SwingConstants.CENTER));
            optionPanel1.add(Balancer1AJTextField);

            optionPanel1.add(new JLabel("Balancer 2A", SwingConstants.CENTER));
            optionPanel1.add(Balancer2AJTextField);

            optionPanel1.add(new JLabel("Balancer 3A", SwingConstants.CENTER));
            optionPanel1.add(Balancer3AJTextField);

            optionPanel1.add(new JLabel("Balancer 4A", SwingConstants.CENTER));
            optionPanel1.add(Balancer4AJTextField);

            optionPanel1.add(new JLabel("Balancer 4A New", SwingConstants.CENTER));
            optionPanel1.add(Balancer4ANewJTextField);

            optionPanel1.add(new JLabel("Balancer 1B", SwingConstants.CENTER));
            optionPanel1.add(Balancer1BJTextField);

            optionPanel1.add(new JLabel("Balancer 2B", SwingConstants.CENTER));
            optionPanel1.add(Balancer2BJTextField);

            optionPanel1.add(new JLabel("Balancer 3B", SwingConstants.CENTER));
            optionPanel1.add(Balancer3BJTextField);

            optionPanel1.add(new JLabel("Balancer 4B", SwingConstants.CENTER));
            optionPanel1.add(Balancer4BJTextField);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.MaintenanceBalancerProductionGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(new JLabel("Balancer 1A", SwingConstants.CENTER));
            optionPanel1.add(Balancer1AJTextField);

            optionPanel1.add(new JLabel("Balancer 2A", SwingConstants.CENTER));
            optionPanel1.add(Balancer2AJTextField);

            optionPanel1.add(new JLabel("Balancer 3A", SwingConstants.CENTER));
            optionPanel1.add(Balancer3AJTextField);

            optionPanel1.add(new JLabel("Balancer 4A", SwingConstants.CENTER));
            optionPanel1.add(Balancer4AJTextField);

            optionPanel1.add(new JLabel("Balancer 4A New", SwingConstants.CENTER));
            optionPanel1.add(Balancer4ANewJTextField);

            optionPanel1.add(new JLabel("Balancer 1B", SwingConstants.CENTER));
            optionPanel1.add(Balancer1BJTextField);

            optionPanel1.add(new JLabel("Balancer 2B", SwingConstants.CENTER));
            optionPanel1.add(Balancer2BJTextField);

            optionPanel1.add(new JLabel("Balancer 3B", SwingConstants.CENTER));
            optionPanel1.add(Balancer3BJTextField);

            optionPanel1.add(new JLabel("Balancer 4B", SwingConstants.CENTER));
            optionPanel1.add(Balancer4BJTextField);

        }
        else if(view == -5){
        
        
        
        }
        // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(9, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(new JLabel("Balancer 1A", SwingConstants.CENTER));
            optionPanel1.add(Balancer1AMonthlyJTextField);

            optionPanel1.add(new JLabel("Balancer 2A", SwingConstants.CENTER));
            optionPanel1.add(Balancer2AMonthlyJTextField);

            optionPanel1.add(new JLabel("Balancer 3A", SwingConstants.CENTER));
            optionPanel1.add(Balancer3AMonthlyJTextField);

            optionPanel1.add(new JLabel("Balancer 4A", SwingConstants.CENTER));
            optionPanel1.add(Balancer4AMonthlyTextField);

            optionPanel1.add(new JLabel("Balancer 4A New", SwingConstants.CENTER));
            optionPanel1.add(Balancer4ANewMonthlyTextField);

            optionPanel1.add(new JLabel("Balancer 1B", SwingConstants.CENTER));
            optionPanel1.add(Balancer1BMonthlyTextField);

            optionPanel1.add(new JLabel("Balancer 2B", SwingConstants.CENTER));
            optionPanel1.add(Balancer2BMonthlyTextField);

            optionPanel1.add(new JLabel("Balancer 3B", SwingConstants.CENTER));
            optionPanel1.add(Balancer3BMonthlyTextField);

            optionPanel1.add(new JLabel("Balancer 4B", SwingConstants.CENTER));
            optionPanel1.add(Balancer4BMonthlyTextField);

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
      //  optionsPanel2.add(update);
      //  optionsPanel2.add(add);
        optionsPanel2.add(back);
        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        outerPanel.repaint();
        frame15.add(outerPanel);

     //   frame15.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("BalancerProduction");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceBalancerProductionGetHighestID();
            System.out.println("MaintenanceBalancerProductionGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] result = new Object[16];
        try {
            result = SQLiteConnection.MaintenanceBalancerProductionReturnEntryByID(highestID);
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

        currentId = currentId;

        Balancer1AJTextField.setText(String.valueOf(result[2]));
        Balancer2AJTextField.setText(String.valueOf(result[3]));
        Balancer3AJTextField.setText(String.valueOf(result[4]));
        Balancer4AJTextField.setText(String.valueOf(result[5]));
        Balancer4ANewJTextField.setText(String.valueOf(result[6]));
        Balancer1BJTextField.setText(String.valueOf(result[7]));
        Balancer2BJTextField.setText(String.valueOf(result[8]));
        Balancer3BJTextField.setText(String.valueOf(result[9]));
        Balancer4BJTextField.setText(String.valueOf(result[10]));

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new BalancerProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    BalancerProduction.createSummaryScreen();
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

                BalancerProduction.exportToExcel();

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
        frameSummary = new JFrame("Balancer Production Report");
        frameSummary.setSize(1300, 700);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

//       optionsPanel2.add(addNew);
//        optionsPanel2.add(summary);
//        optionsPanel2.add(refresh);
//   optionsPanel2.add(print);
//   optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromExcel);

        JPanel summaryPanel = SQLiteConnection.MaintenanceBalancerProductionSummaryTable(1);
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
//        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setBalancerProductionToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceBalancerProductionReturnEntryByID(currentId);

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

                Balancer1AJTextField.setText(String.valueOf(total[2]));
                Balancer2AJTextField.setText(String.valueOf(total[3]));
                Balancer3AJTextField.setText(String.valueOf(total[4]));
                Balancer4AJTextField.setText(String.valueOf(total[5]));
                Balancer4ANewJTextField.setText(String.valueOf(total[6]));
                Balancer1BJTextField.setText(String.valueOf(total[7]));
                Balancer2BJTextField.setText(String.valueOf(total[8]));
                Balancer3BJTextField.setText(String.valueOf(total[9]));
                Balancer4BJTextField.setText(String.valueOf(total[10]));

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

    public void setBalancerProductionToInput(int year, int month, int day, int balancer1A, int balancer2A, int balancer3A, int balancer4A, int balancer4ANew, int balancer1B, int balancer2B, int balancer3B, int balancer4B) {

        model.setDate(year, month, day);

        Balancer1AJTextField.setText(balancer1A + "");
        Balancer2AJTextField.setText(balancer2A + "");
        Balancer3AJTextField.setText(balancer3A + "");
        Balancer4AJTextField.setText(balancer4A + "");
        Balancer4ANewJTextField.setText(balancer4ANew + "");
        Balancer1BJTextField.setText(balancer1B + "");
        Balancer2BJTextField.setText(balancer2B + "");
        Balancer3BJTextField.setText(balancer3B + "");
        Balancer4BJTextField.setText(balancer4B + "");

    }

    public static void calculateTotals() {

        // TODO Auto-generated method stub
        int W11Int = Integer.valueOf(Balancer2AJTextField.getText());
        int W12Int = Integer.valueOf(Balancer3AJTextField.getText());
        int W21Int = Integer.valueOf(Balancer4AJTextField.getText());
        int W22Int = Integer.valueOf(Balancer4ANewJTextField.getText());
        int W31Int = Integer.valueOf(Balancer1BJTextField.getText());

    }

    public static void exportToExcel() {

        String[] typesArray = {"Balancer Production"};
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

                query = "SELECT * FROM MainBalancerProduction WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(BalancerProduction.class.getName()).log(Level.SEVERE, null, ex);
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
