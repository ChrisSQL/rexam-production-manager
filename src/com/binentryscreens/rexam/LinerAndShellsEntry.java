package com.binentryscreens.rexam;

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
import com.productiontrackingscreens.rexam.LinerDataEntryScreen;
import java.awt.Desktop;
import java.io.File;
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
import org.apache.poi.ss.usermodel.Row;

public class LinerAndShellsEntry {

    static JButton add, find, next, previous, update, addNew, search, monthly, go, back, refresh, summary, exportToExcel;
    JLabel dateLabel, dateLabel2, optime2Label, optime3Label, optime4Label, optimeTotal, m1LinersLabel, m2LinersLabel, m3LinersLabel, mod4LinersLabel, modTotal;
    JLabel optime2Monthly, optime3Monthly, optime4Monthly;
    static JTextField optime2TextField, optime3TextField, optime4TextField, optimeTotalTextfield, m1LinersTextField, m2LinersTextField, m3LinersTextField, m4LinersTextField,
            modTotalTextfield;
    static JTextField optime2ttextFieldMonthly;
    JLabel optime2Monthly2, optime3Monthly2, optime4Monthly2, optimeTotalMonthly, m1LinersMonthly, m2LinersMonthly, m3LinersMonthly, mod4LinersMonthly, modTotalMonthly;
    static JTextField optime2Monthly2TextField, optime3Monthly2TextField, optime4Monthly2TextField, optimeTotalMonthlyTextField, m1LinersMonthlyTextField, m2LinersMonthlyTextField,
            m3LinersMonthlyTextField, mod4LinersMonthlyTextField, modTotalMonthlyTextField;

    int view, currentId;
    Date selectedDate;
    JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo, shellTypeCombo, monthCombo, yearCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;

    static String query, item;
    
    static JFrame frameSummary, frame101;

    public static void main(String[] args) throws SQLException {

        new LinerAndShellsEntry(1, -1);

    }

    public LinerAndShellsEntry(int idIn, int view) throws SQLException {

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

        JFrame frame101 = new JFrame();

        JPanel outerPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame101.setTitle("Liners and Shells");
        frame101.setSize(360, 450);
        frame101.setLocationRelativeTo(null);
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

        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
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
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

        optime2Monthly2TextField = new JTextField();
        optime2Monthly2TextField.setEditable(false);
        optime3Monthly2TextField = new JTextField();
        optime3Monthly2TextField.setEditable(false);
        optime4Monthly2TextField = new JTextField();
        optime4Monthly2TextField.setEditable(false);
        optimeTotalMonthlyTextField = new JTextField();
        optimeTotalMonthlyTextField.setEditable(false);
        m1LinersMonthlyTextField = new JTextField();
        m1LinersMonthlyTextField.setEditable(false);
        m2LinersMonthlyTextField = new JTextField();
        m2LinersMonthlyTextField.setEditable(false);
        m3LinersMonthlyTextField = new JTextField();
        m3LinersMonthlyTextField.setEditable(false);
        mod4LinersMonthlyTextField = new JTextField();
        mod4LinersMonthlyTextField.setEditable(false);
        modTotalMonthlyTextField = new JTextField();
        modTotalMonthlyTextField.setEditable(false);

        optime2Monthly2 = new JLabel("Optime 2 Monthly", SwingConstants.CENTER);
        optime3Monthly2 = new JLabel("Optime 3 Monthly", SwingConstants.CENTER);
        optime4Monthly2 = new JLabel("Optime 4 Monthly", SwingConstants.CENTER);
        optimeTotalMonthly = new JLabel("Total Shells Monthly", SwingConstants.CENTER);
        optimeTotalMonthlyTextField.setBackground(Color.LIGHT_GRAY);
        m1LinersMonthly = new JLabel("M1 Liners Monthly", SwingConstants.CENTER);
        m2LinersMonthly = new JLabel("M2 Liners Monthly", SwingConstants.CENTER);
        m3LinersMonthly = new JLabel("M3 Liners Monthly", SwingConstants.CENTER);
        mod4LinersMonthly = new JLabel("M4 Liners Monthly", SwingConstants.CENTER);
        modTotalMonthly = new JLabel("Total Lined Monthly", SwingConstants.CENTER);
        modTotalMonthlyTextField.setBackground(Color.LIGHT_GRAY);

        optime2TextField = new JTextField();
        PlainDocument doc1 = (PlainDocument) optime2TextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());
        optime2TextField.setText("0");
        optime2TextField.addFocusListener(new FocusListener() {

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

        optime3TextField = new JTextField();
        PlainDocument doc2 = (PlainDocument) optime3TextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        optime3TextField.setText("0");
        optime3TextField.addFocusListener(new FocusListener() {

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

        optime4TextField = new JTextField();
        PlainDocument doc3 = (PlainDocument) optime4TextField.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());
        optime4TextField.setText("0");
        optime4TextField.addFocusListener(new FocusListener() {

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

        optimeTotalTextfield = new JTextField();
        optimeTotalTextfield.setEditable(false);
        PlainDocument doc4 = (PlainDocument) optimeTotalTextfield.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());
        optimeTotalTextfield.setText("0");
        optimeTotalTextfield.addFocusListener(new FocusListener() {

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

        m1LinersTextField = new JTextField();
        PlainDocument doc5 = (PlainDocument) m1LinersTextField.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());
        m1LinersTextField.setText("0");
        m1LinersTextField.addFocusListener(new FocusListener() {

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

        m2LinersTextField = new JTextField();
        PlainDocument doc6 = (PlainDocument) m2LinersTextField.getDocument();
        doc6.setDocumentFilter(new MyIntFilter());
        m2LinersTextField.setText("0");
        m2LinersTextField.addFocusListener(new FocusListener() {

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

        m3LinersTextField = new JTextField();
        PlainDocument doc7 = (PlainDocument) m3LinersTextField.getDocument();
        doc7.setDocumentFilter(new MyIntFilter());
        m3LinersTextField.setText("0");
        m3LinersTextField.addFocusListener(new FocusListener() {

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

        m4LinersTextField = new JTextField();
        PlainDocument doc8 = (PlainDocument) m4LinersTextField.getDocument();
        doc8.setDocumentFilter(new MyIntFilter());
        m4LinersTextField.setText("0");
        m4LinersTextField.addFocusListener(new FocusListener() {

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

        modTotalTextfield = new JTextField();
        modTotalTextfield.setEditable(false);
        PlainDocument doc9 = (PlainDocument) modTotalTextfield.getDocument();
        doc9.setDocumentFilter(new MyIntFilter());
        modTotalTextfield.setText("0");
        modTotalTextfield.addFocusListener(new FocusListener() {

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

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                calculateTotals();

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                try {
                    int[] total = SQLiteConnection.LinerAndShellsCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    optime2Monthly2TextField.setText(String.valueOf(total[0]));
                    optime3Monthly2TextField.setText(String.valueOf(total[1]));
                    optime4Monthly2TextField.setText(String.valueOf(total[2]));

                    optimeTotalMonthlyTextField.setText(String.valueOf(total[3]));

                    m1LinersMonthlyTextField.setText(String.valueOf(total[4]));
                    m2LinersMonthlyTextField.setText(String.valueOf(total[5]));
                    m3LinersMonthlyTextField.setText(String.valueOf(total[6]));
                    mod4LinersMonthlyTextField.setText(String.valueOf(total[7]));

                    modTotalMonthlyTextField.setText(String.valueOf(total[8]));

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
                    new LinerAndShellsEntry(1, -1);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame101.dispose();

            }
        });

        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				// Get int value of a JTextfield
                int optimeTotalSum2 = Integer.parseInt(optime2TextField.getText());
                int optimeTotalSum3 = Integer.parseInt(optime3TextField.getText());
                int optimeTotalSum4 = Integer.parseInt(optime4TextField.getText());

                System.out.println("optimeTotalSum2 " + optimeTotalSum2);
                System.out.println("optimeTotalSum3 " + optimeTotalSum3);
                System.out.println("optimeTotalSum4 " + optimeTotalSum4);

                int optimeTotalSum = (optimeTotalSum2 + optimeTotalSum3 + optimeTotalSum4);

                System.out.println("optimeTotalSum " + optimeTotalSum);

                int m1LinersSum1 = Integer.parseInt(m1LinersTextField.getText());
                int m2LinersSum2 = Integer.parseInt(m2LinersTextField.getText());
                int m3LinersSum3 = Integer.parseInt(m3LinersTextField.getText());
                int m4LinersSum4 = Integer.parseInt(m4LinersTextField.getText());
                int mLinerSum = (m1LinersSum1 + m2LinersSum2 + m3LinersSum3 + m4LinersSum4);

                try {
                    SQLiteConnection.LinerAndShellsInsert(
                            SQLiteConnection.LinerAndShellsGetHighestID() + 1, date, Integer.parseInt(optime2TextField.getText()),
                            Integer.parseInt(optime3TextField.getText()), Integer.parseInt(optime4TextField.getText()), optimeTotalSum,
                            Integer.parseInt(m1LinersTextField.getText()), Integer.parseInt(m2LinersTextField.getText()),
                            Integer.parseInt(m3LinersTextField.getText()), Integer.parseInt(m4LinersTextField.getText()), mLinerSum
                    );

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

				// TODO Auto-generated method stub
                frame101.dispose();

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
                try {
                    new LinerAndShellsEntry(1, -1);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame101.dispose();

            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame101.dispose();
                try {
                    LinerAndShellsEntry.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
                try {
                    new LinerAndShellsEntry(1, -2);
                    setLastEntry();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame101.dispose();

            }
        });

        monthly = new JButton("Monthly");
        monthly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
                try {
                    new LinerAndShellsEntry(1, -3);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame101.dispose();

            }
        });

        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.LinerAndShellsUpdate(
                            Integer.parseInt(optime2TextField.getText()), Integer.parseInt(optime3TextField.getText()),
                            Integer.parseInt(optime4TextField.getText()), Integer.parseInt(optimeTotalTextfield.getText()),
                            Integer.parseInt(m1LinersTextField.getText()), Integer.parseInt(m2LinersTextField.getText()),
                            Integer.parseInt(m3LinersTextField.getText()), Integer.parseInt(m4LinersTextField.getText()),
                            Integer.parseInt(modTotalTextfield.getText()), currentId
                    );

                    frame101.dispose();
                    new LinerAndShellsEntry(1, -2);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

				// TODO Auto-generated method stub
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

                    Object[] array = new Object[10];
                    array = SQLiteConnection.LinerAndShellsReturnEntryByDate(selectedDate);

					// String date = (String) array[1];
                    // need to do
                    if (array[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) array[0];

                        optime2TextField.setText(String.valueOf(array[2]));
                        optime3TextField.setText(String.valueOf(array[3]));
                        optime4TextField.setText(String.valueOf(array[4]));
                        optimeTotalTextfield.setText(String.valueOf(array[5]));
                        m1LinersTextField.setText(String.valueOf(array[6]));
                        m2LinersTextField.setText(String.valueOf(array[7]));
                        m3LinersTextField.setText(String.valueOf(array[8]));
                        m4LinersTextField.setText(String.valueOf(array[9]));
                        modTotalTextfield.setText(String.valueOf(array[10]));

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

                    Object[] array = SQLiteConnection.LinerAndShellsGetNextEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        currentId = currentId + 1;

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8,10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5,7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0,4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        optime2TextField.setText(String.valueOf(array[2]));
                        optime3TextField.setText(String.valueOf(array[3]));
                        optime4TextField.setText(String.valueOf(array[4]));
                        optimeTotalTextfield.setText(String.valueOf(array[5]));
                        m1LinersTextField.setText(String.valueOf(array[6]));
                        m2LinersTextField.setText(String.valueOf(array[7]));
                        m3LinersTextField.setText(String.valueOf(array[8]));
                        m4LinersTextField.setText(String.valueOf(array[9]));
                        modTotalTextfield.setText(String.valueOf(array[10]));

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

                    Object[] array = SQLiteConnection.LinerAndShellsGetPreviousEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        currentId = currentId - 1;

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8,10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5,7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0,4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        optime2TextField.setText(String.valueOf(array[2]));
                        optime3TextField.setText(String.valueOf(array[3]));
                        optime4TextField.setText(String.valueOf(array[4]));
                        optimeTotalTextfield.setText(String.valueOf(array[5]));
                        m1LinersTextField.setText(String.valueOf(array[6]));
                        m2LinersTextField.setText(String.valueOf(array[7]));
                        m3LinersTextField.setText(String.valueOf(array[8]));
                        m4LinersTextField.setText(String.valueOf(array[9]));
                        modTotalTextfield.setText(String.valueOf(array[10]));
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
        optime2Label = new JLabel("Optime 2 : ", SwingConstants.CENTER);
        optime2Monthly = new JLabel("Optime 2 Monthly: ", SwingConstants.CENTER);
        optime3Label = new JLabel("Optime 3 : ", SwingConstants.CENTER);
        optime3Monthly = new JLabel("Optime 3 Monthly: ", SwingConstants.CENTER);
        optime4Label = new JLabel("Optime 4 : ", SwingConstants.CENTER);
        optimeTotal = new JLabel("Total Shells : ", SwingConstants.CENTER);
        m1LinersLabel = new JLabel("M1 Liners : ", SwingConstants.CENTER);
        m2LinersLabel = new JLabel("M2 Liners : ", SwingConstants.CENTER);
        m3LinersLabel = new JLabel("M3 Liners : ", SwingConstants.CENTER);
        mod4LinersLabel = new JLabel("M4 Liners : ", SwingConstants.CENTER);
        modTotal = new JLabel("Total Liners : ", SwingConstants.CENTER);

		// Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        buttonsPanel.add(find);
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
            summary.setVisible(false);
            update.setVisible(false);
            back.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(optime2Label);
            optionPanel1.add(optime2TextField);

            optionPanel1.add(optime3Label);
            optionPanel1.add(optime3TextField);

            optionPanel1.add(optime4Label);
            optionPanel1.add(optime4TextField);

            optionPanel1.add(optimeTotal);
            optionPanel1.add(optimeTotalTextfield);
            optimeTotalTextfield.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(m1LinersLabel);
            optionPanel1.add(m1LinersTextField);

            optionPanel1.add(m2LinersLabel);
            optionPanel1.add(m2LinersTextField);

            optionPanel1.add(m3LinersLabel);
            optionPanel1.add(m3LinersTextField);

            optionPanel1.add(mod4LinersLabel);
            optionPanel1.add(m4LinersTextField);

            optionPanel1.add(modTotal);
            optionPanel1.add(modTotalTextfield);
            modTotalTextfield.setBackground(Color.LIGHT_GRAY);

        } // Searching
        else if (view == -2) {

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(optime2Label);
            optionPanel1.add(optime2TextField);

            optionPanel1.add(optime3Label);
            optionPanel1.add(optime3TextField);

            optionPanel1.add(optime4Label);
            optionPanel1.add(optime4TextField);

            optionPanel1.add(optimeTotal);
            optionPanel1.add(optimeTotalTextfield);
            optimeTotalTextfield.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(m1LinersLabel);
            optionPanel1.add(m1LinersTextField);

            optionPanel1.add(m2LinersLabel);
            optionPanel1.add(m2LinersTextField);

            optionPanel1.add(m3LinersLabel);
            optionPanel1.add(m3LinersTextField);

            optionPanel1.add(mod4LinersLabel);
            optionPanel1.add(m4LinersTextField);

            optionPanel1.add(modTotal);
            optionPanel1.add(modTotalTextfield);
            modTotalTextfield.setBackground(Color.LIGHT_GRAY);

            currentId = SQLiteConnection.LinerAndShellsGetHighestID() + 1;
            buttonsPanel.setBackground(Color.GRAY);

            addNew.setVisible(false);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(9, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(optime2Monthly2);
            optionPanel1.add(optime2Monthly2TextField);

            optionPanel1.add(optime3Monthly2);
            optionPanel1.add(optime3Monthly2TextField);

            optionPanel1.add(optime4Monthly2);
            optionPanel1.add(optime4Monthly2TextField);

            optionPanel1.add(optimeTotalMonthly);
            optionPanel1.add(optimeTotalMonthlyTextField);

            optionPanel1.add(m1LinersMonthly);
            optionPanel1.add(m1LinersMonthlyTextField);

            optionPanel1.add(m2LinersMonthly);
            optionPanel1.add(m2LinersMonthlyTextField);

            optionPanel1.add(m3LinersMonthly);
            optionPanel1.add(m3LinersMonthlyTextField);

            optionPanel1.add(mod4LinersMonthly);
            optionPanel1.add(mod4LinersMonthlyTextField);

            optionPanel1.add(modTotalMonthly);
            optionPanel1.add(modTotalMonthlyTextField);

            monthly.setVisible(false);
            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            search.setVisible(false);
            update.setVisible(false);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        outerPanel.add(optionPanel1, BorderLayout.CENTER);

		// Options Panel 2
        JPanel optionsPanel2 = new JPanel(new FlowLayout());
        optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(search);
        optionsPanel2.add(monthly);
        optionsPanel2.add(update);
        optionsPanel2.add(add);
        optionsPanel2.add(back);
        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		// JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
        // optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
        // productionLabel, commentsLabel;
        outerPanel.repaint();
        frame101.add(outerPanel);

        frame101.setVisible(true);

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.LinerAndShellsGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[16];
            result = SQLiteConnection.LinerAndShellsReturnEntryByID(highestID);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8,10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5,7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0,4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

            optime2TextField.setText(String.valueOf(result[2]));
            optime3TextField.setText(String.valueOf(result[3]));
            optime4TextField.setText(String.valueOf(result[4]));
            optimeTotalTextfield.setText(String.valueOf(result[5]));
            m1LinersTextField.setText(String.valueOf(result[6]));
            m2LinersTextField.setText(String.valueOf(result[7]));
            m3LinersTextField.setText(String.valueOf(result[8]));
            m4LinersTextField.setText(String.valueOf(result[9]));
            modTotalTextfield.setText(String.valueOf(result[10]));

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LinerAndShellsEntry(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LinerAndShellsEntry.createSummaryScreen();
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

                LinerAndShellsEntry.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Data Report");
        frameSummary.setSize(1300, 700);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        //optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(refresh);
        //   optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);

        JPanel summaryPanel = SQLiteConnection.LinersAndShellsSummaryTable(1);

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

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setLinersAndShellsToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] array = SQLiteConnection.LinerAndShellsReturnEntryByID(currentId);

            if (array[0] == null) {

                SQLiteConnection.infoBox("No Next Result.", "");

            } else {

                currentId = currentId + 1;

                String dateFormatted = (String) array[1];
                System.out.println("Date Formatted : " + dateFormatted);
                int day = Integer.parseInt(dateFormatted.substring(8,10)); // Correct
                int month = Integer.parseInt(dateFormatted.substring(5,7)) - 1; // Correct
                int year = Integer.parseInt(dateFormatted.substring(0,4)); // Correct

                model.setDate(year, month, day);
                model.setSelected(true);

                optime2TextField.setText(String.valueOf(array[2]));
                optime3TextField.setText(String.valueOf(array[3]));
                optime4TextField.setText(String.valueOf(array[4]));
                optimeTotalTextfield.setText(String.valueOf(array[5]));
                m1LinersTextField.setText(String.valueOf(array[6]));
                m2LinersTextField.setText(String.valueOf(array[7]));
                m3LinersTextField.setText(String.valueOf(array[8]));
                m4LinersTextField.setText(String.valueOf(array[9]));
                modTotalTextfield.setText(String.valueOf(array[10]));

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

    
    public static void exportToExcel() {

        String[] typesArray = {"Liners And Shells", "Liner Defects", "End Counts", "Spoilage By Day"};
        JComboBox typeCombo = new JComboBox(typesArray);

        excelModel1 = new UtilDateModel(new Date());
        excelModel2 = new UtilDateModel(new Date());
        excelDate1 = new JDatePanelImpl(excelModel1);
        excelDate2 = new JDatePanelImpl(excelModel2);
        excelPicker1 = new JDatePickerImpl(excelDate1);
        excelPicker2 = new JDatePickerImpl(excelDate2);

        query = "";

        String[] sortTypesArray = {"DateString"};
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

                if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Liners And Shells")) {
                    query = "SELECT * FROM LinerAndShells WHERE DateString BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Liner Defects")) {
                    query = "SELECT * FROM LinerDefects WHERE DateString BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("End Counts")) {
                    query = "SELECT * FROM EndCounts WHERE DateString BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Spoilage By Day")) {
                    query = "SELECT * FROM SpoilageByDay WHERE DateString BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                }

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
            Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create JTable with Query
        // Export to Excel File
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet1 = workBook.createSheet("Data");
        Row row = sheet1.createRow(2);
        TableModel model3 = table.getModel(); //Table model

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
        for (int headings = 0; headings < model3.getColumnCount(); headings++) { //For each column
            headerRow.createCell(headings).setCellValue(model3.getColumnName(headings));//Write column name
            headerRow.getCell(headings).setCellStyle(style);

        }

        for (int rows = 0; rows < model3.getRowCount(); rows++) { //For each table row
            for (int cols = 0; cols < table.getColumnCount(); cols++) { //For each table column
                row.createCell(cols).setCellValue(model3.getValueAt(rows, cols).toString()); //Write value
                row.getCell(cols).setCellStyle(style2);

            }

            //Set the row to the next one in the sequence 
            row = sheet1.createRow((rows + 3));
        }

        try {
            FileOutputStream output = new FileOutputStream("LinerShellsExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("LinerShellsExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void calculateTotals() {

        int optimeTotalSum2 = Integer.parseInt(optime2TextField.getText());
        int optimeTotalSum3 = Integer.parseInt(optime3TextField.getText());
        int optimeTotalSum4 = Integer.parseInt(optime4TextField.getText());

        System.out.println("optimeTotalSum2 " + optimeTotalSum2);
        System.out.println("optimeTotalSum3 " + optimeTotalSum3);
        System.out.println("optimeTotalSum4 " + optimeTotalSum4);

        int optimeTotalSum = (optimeTotalSum2 + optimeTotalSum3 + optimeTotalSum4);
        optimeTotalTextfield.setText(optimeTotalSum + "");

        System.out.println("optimeTotalSum " + optimeTotalSum);

        int m1LinersSum1 = Integer.parseInt(m1LinersTextField.getText());
        int m2LinersSum2 = Integer.parseInt(m2LinersTextField.getText());
        int m3LinersSum3 = Integer.parseInt(m3LinersTextField.getText());
        int m4LinersSum4 = Integer.parseInt(m4LinersTextField.getText());
        int mLinerSum = (m1LinersSum1 + m2LinersSum2 + m3LinersSum3 + m4LinersSum4);
        modTotalTextfield.setText(mLinerSum + "");

    }

}
