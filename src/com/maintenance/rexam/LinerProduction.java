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
import javax.swing.text.PlainDocument;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
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

public class LinerProduction {

    static JButton add, find, next, delete, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
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

    static JFrame frameSummary, frame15;
    static JFileChooser fileChooser;

    public static void main(String[] args) throws SQLException {
        
//        try {
//            importdata();
//        } catch (IOException ex) {
//            Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
//        }
                

        new LinerProduction(1, -1);

    }

    public LinerProduction(int idIn, int view) throws SQLException {

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

        frame15.setTitle("Liner Production");
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
        Liner11JTextField.setText("0");
        PlainDocument doc01 = (PlainDocument) Liner11JTextField.getDocument();
        doc01.setDocumentFilter(new MyIntFilter());

        Liner12JTextField = new JTextField();
        Liner12JTextField.setText("0");
        PlainDocument doc1 = (PlainDocument) Liner12JTextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        Liner13JTextField = new JTextField();
        Liner13JTextField.setText("0");
        PlainDocument doc2 = (PlainDocument) Liner13JTextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());

        Liner14JTextField = new JTextField();
        Liner14JTextField.setText("0");
        PlainDocument doc3 = (PlainDocument) Liner14JTextField.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());

        Liner21JTextField = new JTextField();
        Liner21JTextField.setText("0");
        PlainDocument doc4 = (PlainDocument) Liner21JTextField.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());

        Liner22JTextField = new JTextField();
        Liner22JTextField.setText("0");
        PlainDocument doc5 = (PlainDocument) Liner22JTextField.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());

        Liner23JTextField = new JTextField();
        Liner23JTextField.setText("0");
        PlainDocument doc5A = (PlainDocument) Liner23JTextField.getDocument();
        doc5A.setDocumentFilter(new MyIntFilter());

        Liner24JTextField = new JTextField();
        Liner24JTextField.setText("0");
        PlainDocument doc5B = (PlainDocument) Liner24JTextField.getDocument();
        doc5B.setDocumentFilter(new MyIntFilter());

        Liner31JTextField = new JTextField();
        Liner31JTextField.setText("0");
        PlainDocument doc5C = (PlainDocument) Liner31JTextField.getDocument();
        doc5C.setDocumentFilter(new MyIntFilter());

        Liner32JTextField = new JTextField();
        Liner32JTextField.setText("0");
        PlainDocument doc5D = (PlainDocument) Liner32JTextField.getDocument();
        doc5D.setDocumentFilter(new MyIntFilter());

        Liner33JTextField = new JTextField();
        Liner33JTextField.setText("0");
        PlainDocument doc5E = (PlainDocument) Liner33JTextField.getDocument();
        doc5E.setDocumentFilter(new MyIntFilter());

        Liner34JTextField = new JTextField();
        Liner34JTextField.setText("0");
        PlainDocument doc5F = (PlainDocument) Liner34JTextField.getDocument();
        doc5F.setDocumentFilter(new MyIntFilter());

        Liner41JTextField = new JTextField();
        Liner41JTextField.setText("0");
        PlainDocument doc5G = (PlainDocument) Liner41JTextField.getDocument();
        doc5G.setDocumentFilter(new MyIntFilter());

        Liner42JTextField = new JTextField();
        Liner42JTextField.setText("0");
        PlainDocument doc5H = (PlainDocument) Liner42JTextField.getDocument();
        doc5H.setDocumentFilter(new MyIntFilter());

        Liner43JTextField = new JTextField();
        Liner43JTextField.setText("0");
        PlainDocument doc5I = (PlainDocument) Liner43JTextField.getDocument();
        doc5I.setDocumentFilter(new MyIntFilter());

        Liner44JTextField = new JTextField();
        Liner44JTextField.setText("0");
        PlainDocument doc5J = (PlainDocument) Liner44JTextField.getDocument();
        doc5J.setDocumentFilter(new MyIntFilter());

        Liner45JTextField = new JTextField();
        Liner45JTextField.setText("0");
        PlainDocument doc5K = (PlainDocument) Liner45JTextField.getDocument();
        doc5K.setDocumentFilter(new MyIntFilter());

        Liner46JTextField = new JTextField();
        Liner46JTextField.setText("0");
        PlainDocument doc5L = (PlainDocument) Liner46JTextField.getDocument();
        doc5L.setDocumentFilter(new MyIntFilter());

        Liner11MonthlyJTextField = new JTextField();
        PlainDocument doc02 = (PlainDocument) Liner11MonthlyJTextField.getDocument();
        doc02.setDocumentFilter(new MyIntFilter());
        Liner12MonthlyJTextField = new JTextField();
        PlainDocument doc13 = (PlainDocument) Liner12MonthlyJTextField.getDocument();
        doc13.setDocumentFilter(new MyIntFilter());
        Liner13MonthlyJTextField = new JTextField();
        PlainDocument doc14 = (PlainDocument) Liner13MonthlyJTextField.getDocument();
        doc14.setDocumentFilter(new MyIntFilter());
        Liner14MonthlyJTextField = new JTextField();
        PlainDocument doc15 = (PlainDocument) Liner14MonthlyJTextField.getDocument();
        doc15.setDocumentFilter(new MyIntFilter());
        Liner21MonthlyJTextField = new JTextField();
        PlainDocument doc16 = (PlainDocument) Liner21MonthlyJTextField.getDocument();
        doc16.setDocumentFilter(new MyIntFilter());

        Liner22MonthlyJTextField = new JTextField();
        PlainDocument doc17A = (PlainDocument) Liner22MonthlyJTextField.getDocument();
        doc17A.setDocumentFilter(new MyIntFilter());
        Liner23MonthlyJTextField = new JTextField();
        PlainDocument doc17B = (PlainDocument) Liner23MonthlyJTextField.getDocument();
        doc17B.setDocumentFilter(new MyIntFilter());
        Liner24MonthlyJTextField = new JTextField();
        PlainDocument doc17C = (PlainDocument) Liner24MonthlyJTextField.getDocument();
        doc17C.setDocumentFilter(new MyIntFilter());

        Liner31MonthlyJTextField = new JTextField();
        PlainDocument doc17D = (PlainDocument) Liner31MonthlyJTextField.getDocument();
        doc17D.setDocumentFilter(new MyIntFilter());
        Liner32MonthlyJTextField = new JTextField();
        PlainDocument doc17E = (PlainDocument) Liner32MonthlyJTextField.getDocument();
        doc17E.setDocumentFilter(new MyIntFilter());
        Liner33MonthlyJTextField = new JTextField();
        PlainDocument doc17F = (PlainDocument) Liner33MonthlyJTextField.getDocument();
        doc17F.setDocumentFilter(new MyIntFilter());
        Liner34MonthlyJTextField = new JTextField();
        PlainDocument doc17G = (PlainDocument) Liner34MonthlyJTextField.getDocument();
        doc17G.setDocumentFilter(new MyIntFilter());

        Liner41MonthlyJTextField = new JTextField();
        PlainDocument doc17H = (PlainDocument) Liner41MonthlyJTextField.getDocument();
        doc17H.setDocumentFilter(new MyIntFilter());
        Liner42MonthlyJTextField = new JTextField();
        PlainDocument doc17I = (PlainDocument) Liner42MonthlyJTextField.getDocument();
        doc17I.setDocumentFilter(new MyIntFilter());
        Liner43MonthlyJTextField = new JTextField();
        PlainDocument doc17J = (PlainDocument) Liner43MonthlyJTextField.getDocument();
        doc17J.setDocumentFilter(new MyIntFilter());
        Liner44MonthlyJTextField = new JTextField();
        PlainDocument doc17K = (PlainDocument) Liner44MonthlyJTextField.getDocument();
        doc17K.setDocumentFilter(new MyIntFilter());
        Liner45MonthlyJTextField = new JTextField();
        PlainDocument doc17L = (PlainDocument) Liner45MonthlyJTextField.getDocument();
        doc17J.setDocumentFilter(new MyIntFilter());
        Liner46MonthlyJTextField = new JTextField();
        PlainDocument doc17M = (PlainDocument) Liner46MonthlyJTextField.getDocument();
        doc17K.setDocumentFilter(new MyIntFilter());

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                System.out.print("Month : " + month);
                System.out.print("Year : " + year);

                try {
                    Object[] total = SQLiteConnection.MaintenanceLinerProductionCalculateTotalsByMonth(month, year);
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

                frame15.dispose();

                // TODO Auto-generated method stub
                try {
                    new LinerProduction(1, -1);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                String dayString = date.substring(8, 10); // Correct
                String monthString = date.substring(5, 7); // Correct
                String yearString = date.substring(0, 4); // Correct

                try {
                    if (SQLiteConnection.MaintenanceLinerProductionEntryExists(yearString + "", monthString + "", dayString + "") == false) {

                        // Get int value of a JTextfield
                        try {
                            SQLiteConnection.MaintenanceLinerProductionInsert(
                                    SQLiteConnection.MaintenanceLinerProductionGetHighestID() + 1,
                                    date,
                                    Integer.valueOf(Liner11JTextField.getText()),
                                    Integer.valueOf(Liner12JTextField.getText()),
                                    Integer.valueOf(Liner13JTextField.getText()),
                                    Integer.valueOf(Liner14JTextField.getText()),
                                    Integer.valueOf(Liner21JTextField.getText()),
                                    Integer.valueOf(Liner22JTextField.getText()),
                                    Integer.valueOf(Liner23JTextField.getText()),
                                    Integer.valueOf(Liner24JTextField.getText()),
                                    Integer.valueOf(Liner31JTextField.getText()),
                                    Integer.valueOf(Liner32JTextField.getText()),
                                    Integer.valueOf(Liner33JTextField.getText()),
                                    Integer.valueOf(Liner34JTextField.getText()),
                                    Integer.valueOf(Liner41JTextField.getText()),
                                    Integer.valueOf(Liner42JTextField.getText()),
                                    Integer.valueOf(Liner43JTextField.getText()),
                                    Integer.valueOf(Liner44JTextField.getText()),
                                    Integer.valueOf(Liner45JTextField.getText()),
                                    Integer.valueOf(Liner46JTextField.getText())
                            );

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    } else {

                        try {
                            SQLiteConnection.MaintenanceLinerProductionUpdateByDate(
                                    Integer.valueOf(Liner11JTextField.getText()),
                                    Integer.valueOf(Liner12JTextField.getText()),
                                    Integer.valueOf(Liner13JTextField.getText()),
                                    Integer.valueOf(Liner14JTextField.getText()),
                                    Integer.valueOf(Liner21JTextField.getText()),
                                    Integer.valueOf(Liner22JTextField.getText()),
                                    Integer.valueOf(Liner23JTextField.getText()),
                                    Integer.valueOf(Liner24JTextField.getText()),
                                    Integer.valueOf(Liner31JTextField.getText()),
                                    Integer.valueOf(Liner32JTextField.getText()),
                                    Integer.valueOf(Liner33JTextField.getText()),
                                    Integer.valueOf(Liner34JTextField.getText()),
                                    Integer.valueOf(Liner41JTextField.getText()),
                                    Integer.valueOf(Liner42JTextField.getText()),
                                    Integer.valueOf(Liner43JTextField.getText()),
                                    Integer.valueOf(Liner44JTextField.getText()),
                                    Integer.valueOf(Liner45JTextField.getText()),
                                    Integer.valueOf(Liner46JTextField.getText()),
                                    date
                            );

                            frame15.dispose();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

                // TODO Auto-generated method stub
                frame15.dispose();
                try {
                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new LinerProduction(1, -1);
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
                    LinerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
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
                    new LinerProduction(1, -3);
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
                    SQLiteConnection.MaintenanceLinerProductionUpdate(date,
                            Integer.valueOf(Liner11JTextField.getText()),
                            Integer.valueOf(Liner12JTextField.getText()),
                            Integer.valueOf(Liner13JTextField.getText()),
                            Integer.valueOf(Liner14JTextField.getText()),
                            Integer.valueOf(Liner21JTextField.getText()),
                            Integer.valueOf(Liner22JTextField.getText()),
                            Integer.valueOf(Liner23JTextField.getText()),
                            Integer.valueOf(Liner24JTextField.getText()),
                            Integer.valueOf(Liner31JTextField.getText()),
                            Integer.valueOf(Liner32JTextField.getText()),
                            Integer.valueOf(Liner33JTextField.getText()),
                            Integer.valueOf(Liner34JTextField.getText()),
                            Integer.valueOf(Liner41JTextField.getText()),
                            Integer.valueOf(Liner42JTextField.getText()),
                            Integer.valueOf(Liner43JTextField.getText()),
                            Integer.valueOf(Liner44JTextField.getText()),
                            Integer.valueOf(Liner45JTextField.getText()),
                            Integer.valueOf(Liner46JTextField.getText()),
                            currentId
                    );

                    frame15.dispose();
                    new LinerProduction(1, -2);

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
                    LinerProduction.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] total = SQLiteConnection.MaintenanceLinerProductionGetNextEntryById(currentId);

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

                    Object[] total = SQLiteConnection.MaintenanceLinerProductionGetPreviousEntryById(currentId);

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
                        SQLiteConnection.MaintenanceLinerDelete(currentId);

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

        SQLiteConnection.AnalyticsUpdate("LinerProduction");

    }

    private void setLastEntry() {

        int highestID = 0;
        try {
            highestID = SQLiteConnection.MaintenanceLinerProductionGetHighestID();
            System.out.println("MaintenanceLinerProductionGetHighestID  " + highestID);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Object[] result = new Object[16];
        try {
            result = SQLiteConnection.MaintenanceLinerProductionReturnEntryByID(highestID);
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

        Liner11JTextField.setText(String.valueOf(result[2]));
        Liner12JTextField.setText(String.valueOf(result[3]));
        Liner13JTextField.setText(String.valueOf(result[4]));
        Liner14JTextField.setText(String.valueOf(result[5]));
        Liner21JTextField.setText(String.valueOf(result[6]));
        Liner22JTextField.setText(String.valueOf(result[7]));
        Liner23JTextField.setText(String.valueOf(result[7]));
        Liner24JTextField.setText(String.valueOf(result[7]));
        Liner31JTextField.setText(String.valueOf(result[7]));

        currentId = highestID;

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LinerProduction(1, -1);
                } catch (SQLException ex) {
                    Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LinerProduction.createSummaryScreen();
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

                LinerProduction.exportToExcel();

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
        frameSummary = new JFrame("Liner Production Report");
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
        optionsPanel2.add(importFromExcel);

        JPanel summaryPanel = SQLiteConnection.MaintenanceLinerProductionSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public void setLinerProductionToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] total = SQLiteConnection.MaintenanceLinerProductionReturnEntryByID(currentId);

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

    public static void setLinerProductionToInput(int year, int month, int day, int liner11, int liner12, int liner13, int liner14, int liner21, int liner22, int liner23, int liner24, int liner31, int liner32, int liner33, int liner34, int liner41, int liner42, int liner43, int liner44, int liner45, int liner46) {

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

        String[] typesArray = {"Liner Production"};
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

                query = "SELECT * FROM MainLinerProduction WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

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
            Logger.getLogger(LinerProduction.class.getName()).log(Level.SEVERE, null, ex);
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
            HSSFSheet worksheet = workbook.getSheet("LinerProduction5");

            int[][] ints = new int[31][19];

            for (int i = 0; i < 32; i++) {

                rows[i] = worksheet.getRow(i);

            }

            HSSFCell[] cells = new HSSFCell[31];

            // get CellA1 to A31 and Assign to int
            for (int i = 0; i < 31; i++) {

                ints[i][0] = (int) rows[i].getCell((int) 0).getNumericCellValue();
                System.out.println("DAY : " + ints[i][0]);

                ints[i][1] = (int) rows[i].getCell((int) 1).getNumericCellValue();
                System.out.println("Column B : " + ints[i][1]);

                ints[i][2] = (int) rows[i].getCell((int) 2).getNumericCellValue();
                System.out.println("Column C : " + ints[i][2]);

                ints[i][3] = (int) rows[i].getCell((int) 3).getNumericCellValue();
                System.out.println("Column D : " + ints[i][3]);

                ints[i][4] = (int) rows[i].getCell((int) 4).getNumericCellValue();
                System.out.println("Column E : " + ints[i][4]);

                ints[i][5] = (int) rows[i].getCell((int) 5).getNumericCellValue();
                System.out.println("Column F : " + ints[i][5]);

                ints[i][6] = (int) rows[i].getCell((int) 6).getNumericCellValue();
                System.out.println("Column G : " + ints[i][6]);

                ints[i][7] = (int) rows[i].getCell((int) 7).getNumericCellValue();
                System.out.println("Column H : " + ints[i][7]);

                ints[i][8] = (int) rows[i].getCell((int) 8).getNumericCellValue();
                System.out.println("Column I : " + ints[i][8]);
                
                ints[i][9] = (int) rows[i].getCell((int)9).getNumericCellValue();
                System.out.println("Column J : " + ints[i][9]);
                
                ints[i][10] = (int) rows[i].getCell((int) 10).getNumericCellValue();
                System.out.println("Column K : " + ints[i][10]);
                
                ints[i][11] = (int) rows[i].getCell((int) 11).getNumericCellValue();
                System.out.println("Column L : " + ints[i][11]);
                
                ints[i][12] = (int) rows[i].getCell((int) 12).getNumericCellValue();
                System.out.println("Column M : " + ints[i][12]);
                
                ints[i][13] = (int) rows[i].getCell((int) 13).getNumericCellValue();
                System.out.println("Column N : " + ints[i][13]);
                
                ints[i][14] = (int) rows[i].getCell((int) 14).getNumericCellValue();
                System.out.println("Column O : " + ints[i][14]);
                
                ints[i][15] = (int) rows[i].getCell((int) 15).getNumericCellValue();
                System.out.println("Column P : " + ints[i][15]);
                
                ints[i][16] = (int) rows[i].getCell((int) 16).getNumericCellValue();
                System.out.println("Column Q : " + ints[i][16]);
                
            }

            for (int i = 0; i < 31; i++) {

                try {

                    LinerProduction linerDefects = new LinerProduction(1, -1);

                    // Set Date to 2014-01-i
                    int day = ints[i][0];

                    
                    linerDefects.setLinerProductionToInput(
                            
                    2014, 
                    04, 
                    day, 
                    ints[i][1], 
                    ints[i][2], 
                    ints[i][3],
                    ints[i][4], 
                    ints[i][5], 
                    ints[i][6], 
                    ints[i][7], 
                    ints[i][8], 
                    ints[i][9], 
                    ints[i][10], 
                    ints[i][11], 
                    ints[i][12], 
                    ints[i][13], 
                    ints[i][14], 
                    ints[i][15], 
                    ints[i][16], 
                    0, 
                    0
                            
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
