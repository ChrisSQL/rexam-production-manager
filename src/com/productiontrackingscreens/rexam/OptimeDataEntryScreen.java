package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class OptimeDataEntryScreen {

    static JButton add, update, find, next, previous, addNew, search, exportToExcel, newEntry, print, refresh, summary, delete;
    static JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressSpeedLabel, shellTypeLabel, productionLabel,
            commentsLabel;
    static JTextField dateTextField, pressSpeedTextField, productionTextField;
    static JTextArea commentsTextArea;
    static Date todaysDate, selectedDate;
    static int view, currentId, year, month, day;
    static JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo, shellTypeCombo;
    static JFrame frameSummary;
    static UtilDateModel model, model2, excelModel1, excelModel2;

    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;
    static String query, item;

    public static void main(String[] args) {

        new OptimeDataEntryScreen(1, 1);

    }

    public OptimeDataEntryScreen(int idIn, int view) {

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

        // Fill Combos From Database
        shiftCombo = new JComboBox();
        crewCombo = new JComboBox();
        optimeNumberCombo = new JComboBox();
        shellTypeCombo = new JComboBox();
        operatorCombo = new JComboBox();
        packerCombo = new JComboBox();
        qcCombo = new JComboBox();
        pressCombo = new JComboBox();
        fillCombos();
        // ////////////////////////////

        JFrame frame3 = new JFrame();
        // frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel();
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame3.setTitle("Optime Data Entry");
        frame3.setSize(360, 480);
        frame3.setLocationRelativeTo(null);

        // System.out.println("Screen");
        outerPanel.setLayout(new BorderLayout());

        // Create Buttons , Labels, Checkboxes etc...
        currentId = 0;
        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        shiftLabel = new JLabel("Shift : ", SwingConstants.CENTER);
        crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
        operatorLabel = new JLabel("Operator : ", SwingConstants.CENTER);
        optimeNumberLabel = new JLabel("Optime Number : ", SwingConstants.CENTER);
        pressSpeedLabel = new JLabel("Press Speed : ", SwingConstants.CENTER);
        shellTypeLabel = new JLabel("Shell Type : ", SwingConstants.CENTER);
        productionLabel = new JLabel("Production : ", SwingConstants.CENTER);
        commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        add = new JButton("Save Record");
        update = new JButton("Update Record");
        find = new JButton("Find Record");
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {
                    selectedDate = (Date) datePicker.getModel().getValue();

                    Object[] array = SQLiteConnection.OPTimeReturnEntryByDate(selectedDate);

                    // [ID], [Date], [Shift], [Crew], [Operator],
                    // [OptimeNumber],
                    // [PressSpeed], [ShellType], [Production], [TimeStamp],
                    // [Comment]
                    System.out.println("ID : " + array[0]);
                    System.out.println("Date : " + array[1]);
                    System.out.println("Shift : " + array[2]);
                    System.out.println("Crew : " + array[3]);
                    System.out.println("Operator : " + array[4]);
                    System.out.println("Optime Number : " + array[5]);
                    System.out.println("Press Speed : " + array[6]);
                    System.out.println("Shell Type : " + array[7]);
                    System.out.println("Production : " + array[8]);
                    System.out.println("Comment : " + array[9]);

                    // String date = (String) array[1];
                    // need to do
                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) array[0];
                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        operatorCombo.setSelectedItem(array[4]);
                        optimeNumberCombo.setSelectedItem(array[5]);
                        pressSpeedTextField.setText((String) array[6]);
                        shellTypeCombo.setSelectedItem(array[7]);
                        productionTextField.setText((String) array[8]);
                        commentsTextArea.setText((String) array[9]);

                    }

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
        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] array = SQLiteConnection.OPTimeGetNextEntryById(currentId);

                    // String date = (String) array[1];
                    // need to do
                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct

                        currentId = (int) array[0];
                        model.setDate(year, month, day);
                        model.setSelected(true);
                        System.out.println(array[2]);
                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        operatorCombo.setSelectedItem(array[4]);
                        optimeNumberCombo.setSelectedItem(array[5]);
                        pressSpeedTextField.setText((String) array[6]);
                        shellTypeCombo.setSelectedItem(array[7]);
                        productionTextField.setText((String) array[8]);
                        commentsTextArea.setText((String) array[9]);

                    }

                    System.out.println("Current ID" + currentId);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
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

                    Object[] array = SQLiteConnection.OPTimeGetPreviousEntryById(currentId);

                    // String date = (String) array[1];
                    // need to do
                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct

                        currentId = (int) array[0];
                        model.setDate(year, month, day);
                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        operatorCombo.setSelectedItem(array[4]);
                        optimeNumberCombo.setSelectedItem(array[5]);
                        pressSpeedTextField.setText((String) array[6]);
                        shellTypeCombo.setSelectedItem(array[7]);
                        productionTextField.setText((String) array[8]);
                        commentsTextArea.setText((String) array[9]);

                    }

                    System.out.println("Current ID : " + currentId);

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });
        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                frame3.dispose();
                try {
                    OptimeDataEntryScreen.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(OptimeDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame3.dispose();
                try {
                    OptimeDataEntryScreen.createSummaryScreen();
                } catch (SQLException e1) {
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
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("No button clicked");
                } else if (response == JOptionPane.YES_OPTION) {
                    try {
                    // Delete CurrentID
                    SQLiteConnection.OptimeEntryDelete(currentId);

                    // Create Summary Screen
                    frameSummary.dispose();
                    frame3.dispose();

                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }

                

            }
        });

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(previous);
        buttonsPanel.add(next);
        buttonsPanel.add(delete);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        // get todays date as Three ints - Format YYYY -
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month) - 1;
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);

        pressSpeedTextField = new JTextField();
        pressSpeedTextField.setText("0");
        PlainDocument doc1 = (PlainDocument) pressSpeedTextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        JPanel optionPanel1 = new JPanel(new GridLayout(8, 2));
        // optionPanel1.setBackground(Color.GRAY);

        optionPanel1.add(dateLabel);
        optionPanel1.add(datePicker);

        optionPanel1.add(shiftLabel);
        optionPanel1.add(shiftCombo);

        optionPanel1.add(crewLabel);
        optionPanel1.add(crewCombo);

        optionPanel1.add(operatorLabel);
        optionPanel1.add(operatorCombo);

        optionPanel1.add(optimeNumberLabel);
        optionPanel1.add(optimeNumberCombo);

        optionPanel1.add(pressSpeedLabel);
        optionPanel1.add(pressSpeedTextField);

        optionPanel1.add(shellTypeLabel);
        optionPanel1.add(shellTypeCombo);

        JPanel commentsPanel = new JPanel();

        productionTextField = new JTextField();
        productionTextField.setText("0");
        PlainDocument doc2 = (PlainDocument) productionTextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());

        commentsTextArea = new JTextArea(7, 30);
        commentsTextArea.setText("NA");
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);

        optionPanel1.add(productionLabel);
        optionPanel1.add(productionTextField);

        commentsPanel.add(commentsLabel);
        commentsPanel.add(commentsTextArea);

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame3.dispose();
                new OptimeDataEntryScreen(1, 1);

            }
        });
		// addNew.setBackground(Color.GREEN);

        // Adding
        if (view == 1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            summary.setVisible(false);
            delete.setVisible(false);

        } // Searching
        else if (view == 2) {

            addNew.setVisible(false);

            try {
                currentId = SQLiteConnection.OPTimeGetHighestID() + 1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);

        } else if (view == 3) {

            try {
                createSummaryScreen();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        outerPanel.add(optionPanel1, BorderLayout.CENTER);

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(summary);

        optionsPanel2.add(addNew);
        optionsPanel2.add(search);
        optionsPanel2.add(update);
        optionsPanel2.add(add);
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                // int crewInt =
                // Integer.parseInt((String)crewCombo.getSelectedItem());
                int optimeNumberInt = Integer.parseInt((String) optimeNumberCombo.getSelectedItem());
                int shiftInt = Integer.parseInt((String) shiftCombo.getSelectedItem());
                int pressSpeedInt = Integer.parseInt(pressSpeedTextField.getText());
                int productionInt = Integer.parseInt(productionTextField.getText());

                try {
                    SQLiteConnection.OPTimeUpdate(
                            currentId, date, shiftInt, (String) crewCombo.getSelectedItem(), (String) operatorCombo.getSelectedItem(),
                            optimeNumberInt, pressSpeedInt, (String) shellTypeCombo.getSelectedItem(), productionInt,
                            commentsTextArea.getText());

                    frame3.dispose();
                    OptimeDataEntryScreen.frameSummary.dispose();
                    createSummaryScreen();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }
        });
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    selectedDate = (Date) datePicker.getModel().getValue();
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                    String year = new SimpleDateFormat("yyyy").format(selectedDate);
                    String month = new SimpleDateFormat("MM").format(selectedDate);
                    String day = new SimpleDateFormat("dd").format(selectedDate);

                    int optimeNumberInt = Integer.parseInt((String) optimeNumberCombo.getSelectedItem());
                    int shiftInt = Integer.parseInt((String) shiftCombo.getSelectedItem());
                    int pressSpeedInt = Integer.parseInt(pressSpeedTextField.getText());
                    int productionInt = Integer.parseInt(productionTextField.getText());

                    if (SQLiteConnection.OptimeEntryExists(year, month, day, optimeNumberInt + "", (String) shiftCombo.getSelectedItem())) {
                        try {
                            SQLiteConnection.OPTimeUpdate(
                                    currentId,
                                    date,
                                    shiftInt,
                                    (String) crewCombo.getSelectedItem(),
                                    (String) operatorCombo.getSelectedItem(),
                                    optimeNumberInt, pressSpeedInt,
                                    (String) shellTypeCombo.getSelectedItem(),
                                    productionInt,
                                    commentsTextArea.getText());

                            frame3.dispose();
                            createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else {
                        try {
                            SQLiteConnection.OPTimeInsert(
                                    (SQLiteConnection.OPTimeGetHighestID() + 1),
                                    date,
                                    shiftInt,
                                    (String) crewCombo.getSelectedItem(),
                                    (String) operatorCombo.getSelectedItem(),
                                    optimeNumberInt, pressSpeedInt,
                                    (String) shellTypeCombo.getSelectedItem(),
                                    productionInt,
                                    commentsTextArea.getText());

                            frame3.dispose();
                            createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }

                    selectedDate = (Date) datePicker.getModel().getValue();
                    System.out.println("selectedDate : " + selectedDate);

                    System.out.println("date : " + date);

                    // int crewInt =
                    // Integer.parseInt((String)crewCombo.getSelectedItem());
                    // TODO Auto-generated method stub
                } catch (SQLException ex) {
                    Logger.getLogger(OptimeDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        optionsPanel2.setBackground(Color.GRAY);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(commentsPanel, BorderLayout.NORTH);
        bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        outerPanel.repaint();
        frame3.add(outerPanel);

        frame3.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("OptimeDataEntryScreen");

    }

    public static void createSummaryScreen() throws SQLException {

        newEntry = new JButton("New Entry Mode");
        newEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new OptimeDataEntryScreen(1, 1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    OptimeDataEntryScreen.createSummaryScreen();
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

                OptimeDataEntryScreen.exportToExcel();

            }
        });

        JButton importFromViscan = new JButton("Import from Viscan");

        importFromViscan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    frameSummary.dispose();
                    LinerDataEntryScreen.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Optime Production Report");
        frameSummary.toFront();
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(newEntry);
        optionsPanel2.add(refresh);
        optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromViscan);
        // }

        JPanel summaryPanel = SQLiteConnection.OPTimeSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getDate("Optime Production Report", 1);

            }
        });

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static void createGroupSummaryScreen() throws SQLException {

        newEntry = new JButton("New Entry Mode");
        newEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new OptimeDataEntryScreen(1, 1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    OptimeDataEntryScreen.createSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                OptimeDataEntryScreen.exportToExcel();

            }
        });

        JButton printGroup = new JButton("Print Group Report");

        // Outer Frame
        frameSummary = new JFrame("Optime Group Report");
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(newEntry);
        optionsPanel2.add(refresh);
        optionsPanel2.add(printGroup);
        optionsPanel2.add(exportToExcel);

        // }
        JPanel summaryPanel = SQLiteConnection.OPTimeSummaryGroupTable(1);
        printGroup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getDate("Optime Group Report", 2);

            }
        });

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static void createCommentsSummaryScreen() throws SQLException {

        newEntry = new JButton("New Entry Mode");
        newEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new OptimeDataEntryScreen(1, 1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    OptimeDataEntryScreen.createCommentsSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                OptimeDataEntryScreen.exportToExcel();

            }
        });

        JButton printComments = new JButton("Print Comments Report");

        // Outer Frame
        frameSummary = new JFrame("Optime Comments Report");
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(newEntry);
        optionsPanel2.add(refresh);
        optionsPanel2.add(printComments);
        optionsPanel2.add(exportToExcel);

        // }
        JPanel summaryPanel = SQLiteConnection.OPTimeSummaryCommentsTable();
        printComments.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getCommentsDate("Optime Comments Report", 3);

            }
        });

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static void createShellsByMonthSummaryScreen() throws SQLException {

        newEntry = new JButton("New Entry Mode");
        newEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new OptimeDataEntryScreen(1, 1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    OptimeDataEntryScreen.createShellsByMonthSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        exportToExcel = new JButton("Export To Excel");
        exportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                OptimeDataEntryScreen.exportToExcel();

            }
        });

        JButton printComments = new JButton("Print Shells By Month Report");

        // Outer Frame
        frameSummary = new JFrame("Optime Shells By Month Report");
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(newEntry);
        optionsPanel2.add(refresh);
        optionsPanel2.add(printComments);
        optionsPanel2.add(exportToExcel);

        // }
        JPanel summaryPanel = SQLiteConnection.OPTimeShellsByMonthTable();
        printComments.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getShellsByMonthDate("Optime ShellsByMonth Report");

            }
        });

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    public static void createReport(String date1, String date2) throws JRException, IOException, PrinterException, SQLException {

        //  JOptionPane.showMessageDialog(null, "Creating Report.");
        Connection conn = SQLiteConnection.Connect();

        File file = new File("Reports/Optime.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("Date1", date1); // note here you can add parameters which
        // would be utilized by the report
        params.put("Date2", date2);

        InputStream inputStream = new FileInputStream("Reports/Optime.jrxml");
        JasperCompileManager.compileReportToFile("Reports/Optime.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer view = new net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
        view.setVisible(true);
        view.toFront();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createGroupReport(String date1, String date2) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("Reports/OptimeGroup.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("Date1", date1); // note here you can add parameters which
        // would be utilized by the report
        params.put("Date2", date2);

        InputStream inputStream = new FileInputStream("Reports/OptimeGroup.jrxml");
        JasperCompileManager.compileReportToFile("Reports/OptimeGroup.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer view = new net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
        view.setVisible(true);
        view.toFront();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createCommentsReport(String date1, String shiftIn) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("Reports/OptimeCommentsReport.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("Date1", date1); // note here you can add parameters which
        // would be utilized by the report
        params.put("Shift", shiftIn);

        InputStream inputStream = new FileInputStream("Reports/OptimeCommentsReport.jrxml");
        JasperCompileManager.compileReportToFile("Reports/OptimeCommentsReport.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer view = new net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
        view.setVisible(true);
        view.toFront();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    public static void createShellsByMonthReport(String date1) throws JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();

        File file = new File("Reports/OptimeShellsByMonthReport2.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("Date1", date1); // note here you can add parameters which would be utilized by the report

        InputStream inputStream = new FileInputStream("Reports/OptimeShellsByMonthReport2.jrxml");
        JasperCompileManager.compileReportToFile("Reports/OptimeShellsByMonthReport2.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);
        JasperViewer view = new net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
        view.setVisible(true);
        view.toFront();

        conn.close();

        // use JasperExportManager to export report to your desired requirement
    }

    private void fillCombos() {

        // Names
        try {

            String sql = "select Employees.Name from Employees ORDER BY Name ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();
            
            operatorCombo.addItem("NA");

            while (rs.next()) {
                              
                String name = rs.getString("Name");
                operatorCombo.addItem(name);
                
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

        // Packers
        try {

            String sql = "SELECT Press.PressName FROM Press ORDER BY PressName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String pressName = rs.getString("PressName");
                pressCombo.addItem(pressName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

        // Crew
        try {

            String sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("CrewName");
                crewCombo.addItem(crewName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

        // Shift
        try {

            String sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String shiftName = rs.getString("ShiftName");
                shiftCombo.addItem(shiftName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

        // OpTime Number
        try {

            String sql = "SELECT Optime.OptimeName FROM Optime ORDER BY OptimeName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String optimeName = rs.getString("OptimeName");
                optimeNumberCombo.addItem(optimeName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

        // OpTime Number
        try {

            String sql = "SELECT ShellType.ShellTypeName FROM ShellType ORDER BY ShellTypeName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();
            
            shellTypeCombo.addItem("NA");

            while (rs.next()) {

                String shellTypeName = rs.getString("ShellTypeName");
                shellTypeCombo.addItem(shellTypeName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Error");

        }

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.OPTimeGetHighestID();
            Object[] result = new Object[12];
            result = SQLiteConnection.OPTimeReturnEntryById(highestID);

            // Date , Shift, Crew, Operator, OptimeNumber , PressSpeed,
            // ShellType, Production
            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            model.setDate(year, month, day);
            model.setSelected(true);

            // Shift
            shiftCombo.setSelectedItem(result[2] + "");
            crewCombo.setSelectedItem(result[3] + "");
            operatorCombo.setSelectedItem(result[4] + "");
            optimeNumberCombo.setSelectedItem(result[5] + "");
            pressSpeedTextField.setText(result[6] + "");
            shellTypeCombo.setSelectedItem(result[7] + "");
            productionTextField.setText(result[8] + "");
            commentsTextArea.setText(result[9] + "");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setOptimeEntry(int idIn) {

        try {

            int highestID = idIn;
            Object[] result = new Object[12];
            result = SQLiteConnection.OPTimeReturnEntryById(highestID);

            // Date , Shift, Crew, Operator, OptimeNumber , PressSpeed,
            // ShellType, Production
            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            model.setDate(year, month, day);
            model.setSelected(true);

            // Shift
            shiftCombo.setSelectedItem(result[2] + "");
            crewCombo.setSelectedItem(result[3] + "");
            operatorCombo.setSelectedItem(result[4] + "");
            optimeNumberCombo.setSelectedItem(result[5] + "");
            pressSpeedTextField.setText(result[6] + "");
            shellTypeCombo.setSelectedItem(result[7] + "");
            productionTextField.setText(result[8] + "");
            commentsTextArea.setText(result[9] + "");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void getDate(String titleIn, int reportType) {

        Date todaysDate = new Date();
        String year = new SimpleDateFormat("yyyy").format(todaysDate);
        String month = new SimpleDateFormat("MM").format(todaysDate);
        String day = new SimpleDateFormat("dd").format(todaysDate);

        String[] date = new String[2];
        Date[] dateArray = new Date[2];
        JButton printOptimeReportButton = new JButton("Optime Report");
        JButton printOptimeGroupReportButton = new JButton("Optime Group Report");
        JFrame frame = new JFrame(titleIn);
        frame.setSize(700, 90);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new FlowLayout());

        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
        model1.setSelected(true);

        UtilDateModel model2 = new UtilDateModel();
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        model2.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
        model2.setSelected(true);

        panel.add(new JLabel("Start Date : "));
        panel.add(datePicker1);
        panel.add(new JLabel("End Date : "));
        panel.add(datePicker2);
        panel.add(printOptimeReportButton);

        frame.add(panel);
        frame.setVisible(true);

        printOptimeReportButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                dateArray[0] = (Date) datePicker1.getModel().getValue();
                dateArray[1] = (Date) datePicker2.getModel().getValue();

                String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[0]);
                String modifiedDate2 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[1]);

                try {

                    if (reportType == 1) {
                        createReport(modifiedDate1, modifiedDate2);
                        System.out.println("Clicked Report 1.");
                    } else if (reportType == 2) {
                        createGroupReport(modifiedDate1, modifiedDate2);
                        System.out.println("Clicked Report 2.");
                    }

                } catch (JRException | IOException | PrinterException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

    }

    public static void getCommentsDate(String titleIn, int reportType) {

        Date todaysDate = new Date();
        String year = new SimpleDateFormat("yyyy").format(todaysDate);
        String month = new SimpleDateFormat("MM").format(todaysDate);
        String day = new SimpleDateFormat("dd").format(todaysDate);

        String[] crews = {"1", "2"};
        JComboBox crewsCombo = new JComboBox(crews);

        String[] date = new String[2];
        Date[] dateArray = new Date[2];
        JButton printOptimeReportButton = new JButton("Optime Report");
        JButton printOptimeGroupReportButton = new JButton("Optime Group Report");
        JButton printOptimeCommentsReportButton = new JButton("Print Comments Report");
        JFrame frame = new JFrame(titleIn);
        frame.setSize(700, 90);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new FlowLayout());

        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
        model1.setSelected(true);

        panel.add(new JLabel("Date : "));
        panel.add(datePicker1);
        panel.add(new JLabel("Shift: "));
        panel.add(crewsCombo);
        panel.add(printOptimeCommentsReportButton);

        frame.add(panel);
        frame.setVisible(true);

        printOptimeCommentsReportButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                dateArray[0] = (Date) datePicker1.getModel().getValue();
                String crewString = crewsCombo.getSelectedObjects() + "";

                String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[0]);

                try {
                    createCommentsReport(modifiedDate1, crewString);
                } catch (JRException | IOException | PrinterException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("Clicked Report Comemnts.");

            }
        });

    }

    public static void getShellsByMonthDate(String titleIn) {

        Date todaysDate = new Date();
        String year = new SimpleDateFormat("yyyy").format(todaysDate);
        String month = new SimpleDateFormat("MM").format(todaysDate);
        String day = new SimpleDateFormat("dd").format(todaysDate);

        String[] months = {"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12"};
        JComboBox monthsCombo = new JComboBox(months);

        String[] years = {"2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        JComboBox yearsCombo = new JComboBox(years);

        JButton printShellsByMonthReport = new JButton("Print Comments Report");
        JFrame frame = new JFrame(titleIn);
        frame.setSize(700, 90);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new FlowLayout());

        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
        model1.setSelected(true);

        panel.add(new JLabel("Date : "));
        panel.add(monthsCombo);
        panel.add(yearsCombo);
        panel.add(printShellsByMonthReport);

        frame.add(panel);
        frame.setVisible(true);

        printShellsByMonthReport.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String monthString = ((monthsCombo.getSelectedItem())) + "";
                String yearString = ((yearsCombo.getSelectedItem())) + "";

                String modifiedDate1 = yearString + "-" + monthString;

                System.out.println("Modidied Date : " + modifiedDate1);

                try {
                    createShellsByMonthReport(modifiedDate1);
                } catch (JRException | IOException | PrinterException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("Clicked Report Comemnts.");

            }
        });

    }

    public static void exportToExcel() {

        String[] typesArray = {"Optime Production Report", "Optime Group Report", "Optime Comments Log", "Shells By Month", "Stolle Production Report", "Stolle Group Report", "Stolle Comments Report", "Ends By Month"};
        JComboBox typeCombo = new JComboBox(typesArray);
        JCheckBox datesCheck = new JCheckBox();

        excelModel1 = new UtilDateModel(new Date());
        excelModel2 = new UtilDateModel(new Date());
        excelDate1 = new JDatePanelImpl(excelModel1);
        excelDate2 = new JDatePanelImpl(excelModel2);
        excelPicker1 = new JDatePickerImpl(excelDate1);
        excelPicker2 = new JDatePickerImpl(excelDate2);

        query = "";

        String[] sortTypesArray = {"Date", "Optime", "Shift", "Crew", "Production"};
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

                if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Optime Production Report")) {
                    query = "SELECT * FROM optimeEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Optime Group Report")) {
                    query = "SELECT * FROM optimeEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' Group BY OptimeNumber;";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Optime Comments Log")) {
                    query = "SELECT date, optimeNumber, shift, comment FROM OPTimeEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Shells By Month")) {
                    query = "SELECT optimeNumber, sum(production) As Total FROM OptimeEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' GROUP BY optimeNumber ;";

                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Stolle Production Report")) {
                    query = "SELECT * FROM Stolle WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Stolle Group Report")) {
                    query = "SELECT * FROM Stolle WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' Group BY Press;";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Stolle Comments Report")) {
                    query = "SELECT Date, Press, Shift, Comment from Stolle WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Ends By Month")) {
                    query = "SELECT Press, sum(StolleProduction) As Total FROM Stolle WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' GROUP BY Press ;";
                }

                // query = "SELECT * FROM " + item + " WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
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
            Logger.getLogger(OptimeDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create JTable with Query
        // Export to Excel File
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet1 = workBook.createSheet("NCR");
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
            FileOutputStream output = new FileOutputStream("ExcelFiles/OptimeExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("ExcelFiles/OptimeExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setModel(String yearIn, String monthIn, String dayIn) {

        // Date
        int yearInt = Integer.parseInt(yearIn);
        int monthInt = Integer.parseInt(monthIn) - 1;
        int dayInt = Integer.parseInt(dayIn);

        OptimeDataEntryScreen.model.setDate(yearInt, monthInt, dayInt);
    }

    public static void setShiftCombo(String shiftIn) {
        OptimeDataEntryScreen.shiftCombo.setSelectedItem(shiftIn);
    }

    public static void setCrewCombo(String crewIn) {
        OptimeDataEntryScreen.crewCombo.setSelectedItem(crewIn);
    }

    public static void setModel(int yearIn, int monthIn, int dayIn) {
        OptimeDataEntryScreen.model.setDate(yearIn, monthIn, dayIn);
    }

    public static void setCrewCombo(JComboBox crewCombo) {
        OptimeDataEntryScreen.crewCombo = crewCombo;
    }

    public static void setOptimeNumberCombo(String optimeNumber) {
        OptimeDataEntryScreen.optimeNumberCombo.setSelectedItem(optimeNumber);
    }

    public static void setOperatorCombo(String nameIn) {
        OptimeDataEntryScreen.operatorCombo.setSelectedItem(nameIn);
    }

    public static void setPressCombo(String selectedIn) {

        OptimeDataEntryScreen.pressCombo.setSelectedItem(selectedIn);
    }

    public static void setProductionTextField(String productionIn) {
        OptimeDataEntryScreen.productionTextField.setText(productionIn);
    }

    public static void setShellTypeCombo(String shellTypeIn) {
        OptimeDataEntryScreen.shellTypeCombo.setSelectedItem(shellTypeIn);
    }

}
