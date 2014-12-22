package com.binentryscreens.rexam;

import static com.binentryscreens.rexam.LinerAndShellsEntry.frameSummary;
import static com.binentryscreens.rexam.LinerAndShellsEntry.importFromExcel;
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
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class LinerDefects {

    static JButton add, find, next, previous, delete, update, addNew, refresh, search, summary, monthly, go, back, calculateTotalLined, calculateTotalDefects,
            calculateSpoiledPercentage;
    JLabel dateLabel, dateLabel2;

    JLabel m1LinerLabel, m2LinerLabel, m3LinerLabel, m4LinerLabel, m1LinerDefectsLabel, m2LinerDefectsLabel, m3LinerDefectsLabel,
            m4LinerDefectsLabel, totalLinedLabel, totalDefectsLabel, totalPercentageSpoiledLabel;
    static JTextField m1LinerTextField, m2LinerTextField, m3LinerTextField, m4LinerTextField, m1LinerDefectsTextField, m2LinerDefectsTextField,
            m3LinerDefectsTextField, m4LinerDefectsTextField, totalLinedTextField, totalDefectsTextField, totalPercentageSpoiledTextField;
    JLabel m1LinerMonthlyLabel, m2LinerMonthlyLabel, m3LinerMonthlyLabel, m4LinerMonthlyLabel, m1LinerDefectsMonthlyLabel,
            m2LinerDefectsMonthlyLabel, m3LinerDefectsMonthlyLabel, m4LinerDefectsMonthlyLabel, totalLinedMonthlyLabel, totalDefectsMonthlyLabel,
            spoilagePercentageMonthlyLabel;
    static JTextField m1LinerMonthlyTextfield, m2LinerMonthlyTextfield, m3LinerMonthlyTextfield, m4LinerMonthlyTextfield,
            m1LinerDefectsMonthlyTextfield, m2LinerDefectsMonthlyTextfield, m3LinerDefectsMonthlyTextfield, m4LinerDefectsMonthlyTextfield,
            totalLinedMonthlyTextfield, totalDefectsMonthlyTextfield, spoilagePercentageMonthlyTextfield;
    int view, currentId;
    Date selectedDate;
    JComboBox monthCombo, yearCombo;

    static UtilDateModel model;
    static JDatePanelImpl datePanel;
    static JDatePickerImpl datePicker;
    static JFileChooser fileChooser;

    static JFrame frameSummary, frame15;

    public static void main(String[] args) throws SQLException {

//        try {
//            importdata();
//        } catch (IOException ex) {
//            Logger.getLogger(LinerDefects.class.getName()).log(Level.SEVERE, null, ex);
//        }

        new LinerDefects(1, -1);

    }

    public LinerDefects(int idIn, int view) throws SQLException {

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

        frame15.setTitle("Liners Defects");
        frame15.setSize(360, 600);
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

        m1LinerTextField = new JTextField();
        m1LinerTextField.setText("0");
        PlainDocument doc1 = (PlainDocument) m1LinerTextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());
        m1LinerTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m2LinerTextField = new JTextField();
        m2LinerTextField.setText("0");
        PlainDocument doc2 = (PlainDocument) m2LinerTextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        m2LinerTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m3LinerTextField = new JTextField();
        m3LinerTextField.setText("0");
        PlainDocument doc3 = (PlainDocument) m3LinerTextField.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());
        m3LinerTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m4LinerTextField = new JTextField();
        m4LinerTextField.setText("0");
        PlainDocument doc4 = (PlainDocument) m4LinerTextField.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());
        m4LinerTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m1LinerDefectsTextField = new JTextField();
        m1LinerDefectsTextField.setText("0");
        PlainDocument doc5 = (PlainDocument) m1LinerDefectsTextField.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());
        m1LinerDefectsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });
        m2LinerDefectsTextField = new JTextField();
        m2LinerDefectsTextField.setText("0");
        PlainDocument doc6 = (PlainDocument) m2LinerDefectsTextField.getDocument();
        doc6.setDocumentFilter(new MyIntFilter());
        m2LinerDefectsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m3LinerDefectsTextField = new JTextField();
        m3LinerDefectsTextField.setText("0");
        PlainDocument doc7 = (PlainDocument) m3LinerDefectsTextField.getDocument();
        doc7.setDocumentFilter(new MyIntFilter());
        m3LinerDefectsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m4LinerDefectsTextField = new JTextField();
        m4LinerDefectsTextField.setText("0");
        PlainDocument doc8 = (PlainDocument) m4LinerDefectsTextField.getDocument();
        doc8.setDocumentFilter(new MyIntFilter());
        m4LinerDefectsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        totalLinedTextField = new JTextField();
        totalLinedTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });
        totalDefectsTextField = new JTextField();
        totalDefectsTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });
        totalPercentageSpoiledTextField = new JTextField();
        totalPercentageSpoiledTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateTotalLined();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateTotalLined();

            }
        });

        m1LinerMonthlyTextfield = new JTextField();
        m2LinerMonthlyTextfield = new JTextField();
        m3LinerMonthlyTextfield = new JTextField();
        m4LinerMonthlyTextfield = new JTextField();
        m1LinerDefectsMonthlyTextfield = new JTextField();
        m2LinerDefectsMonthlyTextfield = new JTextField();
        m3LinerDefectsMonthlyTextfield = new JTextField();
        m4LinerDefectsMonthlyTextfield = new JTextField();
        totalLinedMonthlyTextfield = new JTextField();
        totalDefectsMonthlyTextfield = new JTextField();
        spoilagePercentageMonthlyTextfield = new JTextField();

        m1LinerLabel = new JLabel("M1 Liner ", SwingConstants.CENTER);
        m2LinerLabel = new JLabel("M2 Liner ", SwingConstants.CENTER);
        m3LinerLabel = new JLabel("M3 Liner ", SwingConstants.CENTER);
        m4LinerLabel = new JLabel("M4 Liner ", SwingConstants.CENTER);
        m1LinerDefectsLabel = new JLabel("M1 Liner Defects ", SwingConstants.CENTER);
        m2LinerDefectsLabel = new JLabel("M2 Liner Defects ", SwingConstants.CENTER);
        m3LinerDefectsLabel = new JLabel("M3 Liner Defects ", SwingConstants.CENTER);
        m4LinerDefectsLabel = new JLabel("M4 Liner Defects ", SwingConstants.CENTER);
        totalLinedLabel = new JLabel("Total Lined ", SwingConstants.CENTER);
        totalDefectsLabel = new JLabel("Total Defects ", SwingConstants.CENTER);
        totalPercentageSpoiledLabel = new JLabel("Spoiled % ", SwingConstants.CENTER);

        m1LinerMonthlyLabel = new JLabel("M1 Liner Monthly", SwingConstants.CENTER);
        m2LinerMonthlyLabel = new JLabel("M2 Liner Monthly", SwingConstants.CENTER);
        m3LinerMonthlyLabel = new JLabel("M3 Liner Monthly", SwingConstants.CENTER);
        m4LinerMonthlyLabel = new JLabel("M4 Liner Monthly", SwingConstants.CENTER);
        m1LinerDefectsMonthlyLabel = new JLabel("M1 Liner Defects Monthly", SwingConstants.CENTER);
        m2LinerDefectsMonthlyLabel = new JLabel("M2 Liner Defects Monthly", SwingConstants.CENTER);
        m3LinerDefectsMonthlyLabel = new JLabel("M3 Liner Defects Monthly", SwingConstants.CENTER);
        m4LinerDefectsMonthlyLabel = new JLabel("M4 Liner Defects Monthly", SwingConstants.CENTER);
        totalLinedMonthlyLabel = new JLabel("Total Lined Monthly ", SwingConstants.CENTER);
        totalDefectsMonthlyLabel = new JLabel("Total Defects Monthly ", SwingConstants.CENTER);
        spoilagePercentageMonthlyLabel = new JLabel("Spoiled % Monthly ", SwingConstants.CENTER);

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String month = monthCombo.getSelectedItem().toString();
                String year = yearCombo.getSelectedItem().toString();

                try {
                    Object[] total = SQLiteConnection.LinerDefectsCalculateTotalsByMonth(month, year);
                    System.out.println("Total0 " + total[0]);

                    m1LinerMonthlyTextfield.setText(String.valueOf(total[0]));
                    m2LinerMonthlyTextfield.setText(String.valueOf(total[1]));
                    m3LinerMonthlyTextfield.setText(String.valueOf(total[2]));
                    m4LinerMonthlyTextfield.setText(String.valueOf(total[3]));
                    totalLinedMonthlyTextfield.setText(String.valueOf(total[4]));
                    totalDefectsMonthlyTextfield.setText(String.valueOf(total[10]));
                    spoilagePercentageMonthlyTextfield.setText(String.valueOf(total[6]) + "%");

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
                    new LinerDefects(1, -1);
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

                calculateTotalLined();

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                // Get int value of a JTextfield
                int m1LinerInt = Integer.parseInt(m1LinerTextField.getText());
                int m2LinerInt = Integer.parseInt(m2LinerTextField.getText());
                int m3LinerInt = Integer.parseInt(m3LinerTextField.getText());
                int m4LinerInt = Integer.parseInt(m4LinerTextField.getText());

                System.out.println("m1LinerInt " + m1LinerInt);
                System.out.println("m2LinerInt " + m2LinerInt);
                System.out.println("m3LinerInt " + m3LinerInt);
                System.out.println("m4LinerInt " + m4LinerInt);

                Double LinerTotalSum = (m1LinerInt * 1.0 + m2LinerInt * 1.0 + m3LinerInt * 1.0 + m4LinerInt * 1.0);

                int M1DefectsInt = Integer.parseInt(m1LinerDefectsTextField.getText());
                int M2DefectsInt = Integer.parseInt(m2LinerDefectsTextField.getText());
                int M3DefectsInt = Integer.parseInt(m3LinerDefectsTextField.getText());
                int M4DefectsInt = Integer.parseInt(m4LinerDefectsTextField.getText());

                Double defectsSum = (M1DefectsInt * 1.0 + M2DefectsInt * 1.0 + M3DefectsInt * 1.0 + M4DefectsInt * 1.0);

                String dayString = date.substring(8, 10); // Correct
                String monthString = date.substring(5, 7); // Correct
                String yearString = date.substring(0, 4); // Correct

                try {

                    System.out.println("Exists : " + SQLiteConnection.LinerDefectsEntryExists(yearString + "", monthString + "", dayString + ""));

                    if (SQLiteConnection.LinerDefectsEntryExists(yearString + "", monthString + "", dayString + "") == false) {

                        try {
                            SQLiteConnection.LinerDefectsInsert(
                                    SQLiteConnection.LinerDefectsGetHighestID() + 1,
                                    date,
                                    m1LinerInt,
                                    M1DefectsInt,
                                    m2LinerInt,
                                    M2DefectsInt,
                                    m3LinerInt,
                                    M3DefectsInt,
                                    m4LinerInt,
                                    M4DefectsInt,
                                    LinerTotalSum,
                                    defectsSum,
                                    0.0
                            );

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    } else {

                        try {
                            SQLiteConnection.LinerDefectsUpdateByDate(
                                    m1LinerInt,
                                    M1DefectsInt,
                                    m2LinerInt,
                                    M2DefectsInt,
                                    m3LinerInt,
                                    M3DefectsInt,
                                    m4LinerInt,
                                    M4DefectsInt,
                                    LinerTotalSum,
                                    defectsSum, 0.0,
                                    date
                            );

                            frame15.dispose();
                            createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDefects.class.getName()).log(Level.SEVERE, null, ex);
                }
                // TODO Auto-generated method stub
                frame15.dispose();
                try {
                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDefects.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame15.dispose();
                try {
                    LinerDefects.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDefects.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {
                    new LinerDefects(1, -1);
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
                    LinerDefects.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDefects.class.getName()).log(Level.SEVERE, null, ex);
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
                    new LinerDefects(1, -3);
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

                int m1LinerInt = Integer.parseInt(m1LinerTextField.getText());
                int m2LinerInt = Integer.parseInt(m2LinerTextField.getText());
                int m3LinerInt = Integer.parseInt(m3LinerTextField.getText());
                int m4LinerInt = Integer.parseInt(m4LinerTextField.getText());

                System.out.println("m1LinerInt " + m1LinerInt);
                System.out.println("m2LinerInt " + m2LinerInt);
                System.out.println("m3LinerInt " + m3LinerInt);
                System.out.println("m4LinerInt " + m4LinerInt);

                Double LinerTotalSum = (m1LinerInt * 1.0 + m2LinerInt * 1.0 + m3LinerInt * 1.0 + m4LinerInt * 1.0);

                int M1DefectsInt = Integer.parseInt(m1LinerDefectsTextField.getText());
                int M2DefectsInt = Integer.parseInt(m2LinerDefectsTextField.getText());
                int M3DefectsInt = Integer.parseInt(m3LinerDefectsTextField.getText());
                int M4DefectsInt = Integer.parseInt(m4LinerDefectsTextField.getText());

                Double defectsSum = (M1DefectsInt * 1.0 + M2DefectsInt * 1.0 + M3DefectsInt * 1.0 + M4DefectsInt * 1.0);

                try {
                    SQLiteConnection.LinerDefectsUpdate(
                            date,
                            m1LinerInt,
                            M1DefectsInt,
                            m2LinerInt,
                            M2DefectsInt,
                            m3LinerInt,
                            M3DefectsInt,
                            m4LinerInt,
                            M4DefectsInt,
                            LinerTotalSum,
                            defectsSum, 0.0,
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

        find = new JButton("Find Record");
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {
                    selectedDate = (Date) datePicker.getModel().getValue();

                    Object[] total = SQLiteConnection.LinerDefectsReturnEntryByDate(selectedDate);

                    if (total[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) total[0];

                        m1LinerTextField.setText(String.valueOf(total[2]));
                        m1LinerDefectsTextField.setText(String.valueOf(total[3]));

                        m2LinerTextField.setText(String.valueOf(total[4]));
                        m2LinerDefectsTextField.setText(String.valueOf(total[5]));

                        m3LinerTextField.setText(String.valueOf(total[6]));
                        m3LinerDefectsTextField.setText(String.valueOf(total[7]));

                        m4LinerTextField.setText(String.valueOf(total[8]));
                        m4LinerDefectsTextField.setText(String.valueOf(total[9]));

                        totalLinedTextField.setText(String.valueOf(total[10]));
                        totalDefectsTextField.setText(String.valueOf(total[11]));
                        totalPercentageSpoiledTextField.setText(String.valueOf(total[12]));

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

                    Object[] total = SQLiteConnection.LinerDefectsGetNextEntryById(currentId);

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

                        m1LinerTextField.setText(String.valueOf(total[2]));
                        m1LinerDefectsTextField.setText(String.valueOf(total[3]));

                        m2LinerTextField.setText(String.valueOf(total[4]));
                        m2LinerDefectsTextField.setText(String.valueOf(total[5]));

                        m3LinerTextField.setText(String.valueOf(total[6]));
                        m3LinerDefectsTextField.setText(String.valueOf(total[7]));

                        m4LinerTextField.setText(String.valueOf(total[8]));
                        m4LinerDefectsTextField.setText(String.valueOf(total[9]));

                        totalLinedTextField.setText(String.valueOf(total[10]));
                        totalDefectsTextField.setText(String.valueOf(total[11]));
                        totalPercentageSpoiledTextField.setText(String.valueOf(total[12]) + "%");

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

                    Object[] total = SQLiteConnection.LinerDefectsGetPreviousEntryById(currentId);

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

                        m1LinerTextField.setText(String.valueOf(total[2]));
                        m1LinerDefectsTextField.setText(String.valueOf(total[3]));

                        m2LinerTextField.setText(String.valueOf(total[4]));
                        m2LinerDefectsTextField.setText(String.valueOf(total[5]));

                        m3LinerTextField.setText(String.valueOf(total[6]));
                        m3LinerDefectsTextField.setText(String.valueOf(total[7]));

                        m4LinerTextField.setText(String.valueOf(total[8]));
                        m4LinerDefectsTextField.setText(String.valueOf(total[9]));

                        totalLinedTextField.setText(String.valueOf(total[10]));
                        totalDefectsTextField.setText(String.valueOf(total[11]));
                        totalPercentageSpoiledTextField.setText(String.valueOf(total[12]) + "%");
                    }

                    System.out.println(currentId);

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        delete = new JButton("Delete ");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    SQLiteConnection.LinerDefectsDelete(currentId);
                    frameSummary.dispose();
                    frame15.dispose();
                    try {
                        createSummaryScreen();
                    } catch (SQLException ex) {
                        Logger.getLogger(EndCounts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }

            }
        });

        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

        calculateTotalLined = new JButton("Calculate Total Lined");
        calculateTotalLined.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
                int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
                int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
                int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
                int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

                int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
                int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
                int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
                int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
                int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

                totalLinedTextField.setText(String.valueOf(totalLined5));
                totalDefectsTextField.setText(String.valueOf(totalDefects5));

                System.out.println("TotalLined5 : " + totalLined5);
                System.out.println("total Defects5 : " + totalDefects5);

                Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
                totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

            }
        });

        calculateTotalDefects = new JButton("Calculate Total Defects");
        calculateTotalDefects.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
                int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
                int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
                int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
                int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

                int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
                int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
                int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
                int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
                int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

                totalLinedTextField.setText(String.valueOf(totalLined5));
                totalDefectsTextField.setText(String.valueOf(totalDefects5));

                System.out.println("TotalLined5 : " + totalLined5);
                System.out.println("total Defects5 : " + totalDefects5);

                Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
                totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

            }
        });

        calculateSpoiledPercentage = new JButton("Calculate Spoiled %");
        calculateSpoiledPercentage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
                int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
                int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
                int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
                int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

                int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
                int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
                int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
                int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
                int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

                totalLinedTextField.setText(String.valueOf(totalLined5));
                totalDefectsTextField.setText(String.valueOf(totalDefects5));

                System.out.println("TotalLined5 : " + totalLined5);
                System.out.println("total Defects5 : " + totalDefects5);

                Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
                totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

            }
        });

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        // buttonsPanel.setBackground(Color.GRAY);

        // buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);
        buttonsPanel.add(delete);

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
            delete.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            back.setVisible(false);
            summary.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(m1LinerLabel);
            optionPanel1.add(m1LinerTextField);

            optionPanel1.add(m1LinerDefectsLabel);
            optionPanel1.add(m1LinerDefectsTextField);

            optionPanel1.add(m2LinerLabel);
            optionPanel1.add(m2LinerTextField);

            optionPanel1.add(m2LinerDefectsLabel);
            optionPanel1.add(m2LinerDefectsTextField);

            optionPanel1.add(m3LinerLabel);
            optionPanel1.add(m3LinerTextField);

            optionPanel1.add(m3LinerDefectsLabel);
            optionPanel1.add(m3LinerDefectsTextField);

            optionPanel1.add(m4LinerLabel);
            optionPanel1.add(m4LinerTextField);

            optionPanel1.add(m4LinerDefectsLabel);
            optionPanel1.add(m4LinerDefectsTextField);

            optionPanel1.add(calculateTotalLined);
            optionPanel1.add(totalLinedTextField);
            totalLinedTextField.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(calculateTotalDefects);
            optionPanel1.add(totalDefectsTextField);
            totalDefectsTextField.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(calculateSpoiledPercentage);
            optionPanel1.add(totalPercentageSpoiledTextField);
            totalPercentageSpoiledTextField.setBackground(Color.LIGHT_GRAY);

        } // Searching
        else if (view == -2) {

            currentId = SQLiteConnection.LinerDefectsGetHighestID();
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            back.setVisible(false);
            addNew.setVisible(false);

            optionPanel1.add(dateLabel);
            optionPanel1.add(datePicker);

            optionPanel1.add(m1LinerLabel);
            optionPanel1.add(m1LinerTextField);

            optionPanel1.add(m1LinerDefectsLabel);
            optionPanel1.add(m1LinerDefectsTextField);

            optionPanel1.add(m2LinerLabel);
            optionPanel1.add(m2LinerTextField);

            optionPanel1.add(m2LinerDefectsLabel);
            optionPanel1.add(m2LinerDefectsTextField);

            optionPanel1.add(m3LinerLabel);
            optionPanel1.add(m3LinerTextField);

            optionPanel1.add(m3LinerDefectsLabel);
            optionPanel1.add(m3LinerDefectsTextField);

            optionPanel1.add(m4LinerLabel);
            optionPanel1.add(m4LinerTextField);

            optionPanel1.add(m4LinerDefectsLabel);
            optionPanel1.add(m4LinerDefectsTextField);

            optionPanel1.add(calculateTotalLined);
            optionPanel1.add(totalLinedTextField);
            totalLinedTextField.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(calculateTotalDefects);
            optionPanel1.add(totalDefectsTextField);
            totalDefectsTextField.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(calculateSpoiledPercentage);
            optionPanel1.add(totalPercentageSpoiledTextField);
            totalPercentageSpoiledTextField.setBackground(Color.LIGHT_GRAY);

        } // Monthly
        else {

            optionPanel1 = new JPanel(new GridLayout(11, 2));

            outerPanel.add(comboPanel, BorderLayout.NORTH);
            comboPanel.setBackground(Color.GRAY);

            optionPanel1.add(m1LinerMonthlyLabel);
            optionPanel1.add(m1LinerMonthlyTextfield);

            optionPanel1.add(m2LinerMonthlyLabel);
            optionPanel1.add(m2LinerMonthlyTextfield);

            optionPanel1.add(m3LinerMonthlyLabel);
            optionPanel1.add(m3LinerMonthlyTextfield);

            optionPanel1.add(m4LinerMonthlyLabel);
            optionPanel1.add(m4LinerMonthlyTextfield);

            optionPanel1.add(totalLinedMonthlyLabel);
            optionPanel1.add(totalLinedMonthlyTextfield);
            totalLinedMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(totalDefectsMonthlyLabel);
            optionPanel1.add(totalDefectsMonthlyTextfield);
            totalDefectsMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

            optionPanel1.add(spoilagePercentageMonthlyLabel);
            optionPanel1.add(spoilagePercentageMonthlyTextfield);
            spoilagePercentageMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

            monthly.setVisible(false);
            find.setVisible(false);
            previous.setVisible(false);
            delete.setVisible(false);
            next.setVisible(false);
            search.setVisible(false);
            update.setVisible(false);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        JPanel commentsPanel = new JPanel();

        // commentsTextArea = new JTextArea(7, 28);
        // commentsTextArea.setLineWrap(true);
        // commentsTextArea.setWrapStyleWord(true);
        //
        // commentsPanel.add(modTotal);
        // commentsPanel.add(commentsTextArea);
        //
        // // optionPanel1.add(productionLabel);
        // // optionPanel1.add(productionTextField);
        outerPanel.add(optionPanel1, BorderLayout.CENTER);

        // Options Panel 2
        JPanel optionsPanel2 = new JPanel(new FlowLayout());
        optionsPanel2.add(addNew);
        optionsPanel2.add(search);
        optionsPanel2.add(summary);
        optionsPanel2.add(monthly);
        optionsPanel2.add(update);
        optionsPanel2.add(add);
        optionsPanel2.add(back);
        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
        // optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
        // productionLabel, commentsLabel;
        outerPanel.repaint();
        frame15.add(outerPanel);

        frame15.setVisible(true);

        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("LinerDefects");

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.LinerDefectsGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[16];
            result = SQLiteConnection.LinerDefectsReturnEntryByID(highestID);

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

            m1LinerTextField.setText(String.valueOf(result[2]));
            m1LinerDefectsTextField.setText(String.valueOf(result[3]));

            m2LinerTextField.setText(String.valueOf(result[4]));
            m2LinerDefectsTextField.setText(String.valueOf(result[5]));

            m3LinerTextField.setText(String.valueOf(result[6]));
            m3LinerDefectsTextField.setText(String.valueOf(result[7]));

            m4LinerTextField.setText(String.valueOf(result[8]));
            m4LinerDefectsTextField.setText(String.valueOf(result[9]));

            totalLinedTextField.setText(String.valueOf(result[10]));
            totalDefectsTextField.setText(String.valueOf(result[11]));
            totalPercentageSpoiledTextField.setText(String.valueOf(result[12]) + "%");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setDefectToId(int idIn) {

        try {

            int highestID = idIn;
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[13];
            result = SQLiteConnection.LinerDefectsReturnEntryByID(highestID);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

            m1LinerTextField.setText(String.valueOf(result[2]));
            m1LinerDefectsTextField.setText(String.valueOf(result[3]));

            m2LinerTextField.setText(String.valueOf(result[4]));
            m2LinerDefectsTextField.setText(String.valueOf(result[5]));

            m3LinerTextField.setText(String.valueOf(result[6]));
            m3LinerDefectsTextField.setText(String.valueOf(result[7]));

            m4LinerTextField.setText(String.valueOf(result[8]));
            m4LinerDefectsTextField.setText(String.valueOf(result[9]));

            totalLinedTextField.setText(String.valueOf(result[10]));
            totalDefectsTextField.setText(String.valueOf(result[11]));
            totalPercentageSpoiledTextField.setText(String.valueOf(result[12]) + "%");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setDefectToInput(int year, int month, int day, int m1Liner, int m2Liner, int m3Liner, int m4Liner, int m1LinerDefects, int m2LinerDefects, int m3LinerDefects, int m4LinerDefects) {

        model.setDate(year, month, day);

        m1LinerTextField.setText(m1Liner + "");
        m2LinerTextField.setText(m2Liner + "");
        m3LinerTextField.setText(m3Liner + "");
        m4LinerTextField.setText(m4Liner + "");
        m1LinerDefectsTextField.setText(m1LinerDefects + "");
        m2LinerDefectsTextField.setText(m2LinerDefects + "");
        m3LinerDefectsTextField.setText(m3LinerDefects + "");
        m4LinerDefectsTextField.setText(m4LinerDefects + "");

        calculateTotalLined();

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LinerDefects(1, -1);
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
                    LinerDefects.createSummaryScreen();
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

        importFromExcel = new JButton("Import from Viscan");
        importFromExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    frameSummary.dispose();
                    LinerAndShellsEntry.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(LinerAndShellsEntry.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Defects Report");
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

        JPanel summaryPanel = SQLiteConnection.LinerDefectsSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static void calculateTotalLined() {

        int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
        int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
        int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
        int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
        int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

        int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
        int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
        int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
        int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
        int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

        totalLinedTextField.setText(String.valueOf(totalLined5));
        totalDefectsTextField.setText(String.valueOf(totalDefects5));

        System.out.println("TotalLined5 : " + totalLined5);
        System.out.println("total Defects5 : " + totalDefects5);

        Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
        totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

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
            HSSFSheet worksheet = workbook.getSheet("5");

            int[][] ints = new int[31][9];

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

            }

            for (int i = 0; i < 31; i++) {

                try {

                    LinerDefects linerDefects = new LinerDefects(1, -1);

                 // Set Date to 2014-01-i
                    int day = ints[i][0];

                    model.setDate(2014, 04, day);

                    // m1LinerTextField, m2LinerTextField, m3LinerTextField, m4LinerTextField, m1LinerDefectsTextField, m2LinerDefectsTextField,
                    // m3LinerDefectsTextField, m4LinerDefectsTextField

                    m1LinerTextField.setText(ints[i][1]+"");
                    m1LinerDefectsTextField.setText(ints[i][2]+"");
                    m2LinerTextField.setText(ints[i][3]+"");
                    m2LinerDefectsTextField.setText(ints[i][4]+"");
                    m3LinerTextField.setText(ints[i][5]+"");
                    m3LinerDefectsTextField.setText(ints[i][6]+"");
                    m4LinerTextField.setText(ints[i][7]+"");                                                         
                    m4LinerDefectsTextField.setText(ints[i][8]+"");

                    calculateTotalLined();
                    
                    add.doClick();
                    LinerDefects.frameSummary.dispose();

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

    public static void setM1LinerTextField(String m1LinerTextField) {
        LinerDefects.m1LinerTextField.setText(m1LinerTextField);
    }

    public static void setM2LinerTextField(String m2LinerTextField) {
        LinerDefects.m2LinerTextField.setText(m2LinerTextField);
    }

    public static void setM3LinerTextField(String m3LinerTextField) {
        LinerDefects.m3LinerTextField.setText(m3LinerTextField);
    }

    public static void setM4LinerTextField(String m4LinerTextField) {
        LinerDefects.m4LinerTextField.setText(m4LinerTextField);
    }

    public static void setM1LinerDefectsTextField(String m1LinerDefectsTextField) {
        LinerDefects.m1LinerDefectsTextField.setText(m1LinerDefectsTextField);
    }

    public static void setM2LinerDefectsTextField(String m2LinerDefectsTextField) {
        LinerDefects.m2LinerDefectsTextField.setText(m2LinerDefectsTextField);
    }

    public static void setM3LinerDefectsTextField(String m3LinerDefectsTextField) {
        LinerDefects.m3LinerDefectsTextField.setText(m3LinerDefectsTextField);
    }

    public static void setM4LinerDefectsTextField(String m4LinerDefectsTextField) {
        LinerDefects.m4LinerDefectsTextField.setText(m4LinerDefectsTextField);
    }

}
