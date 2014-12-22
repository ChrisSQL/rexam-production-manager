package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;
import static com.productiontrackingscreens.rexam.OptimeDataEntryScreen.item;
import static com.productiontrackingscreens.rexam.OptimeDataEntryScreen.query;
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

public class LinerDataEntryScreen {

    static JButton add, find, next, previous, update, addNew, search, calculateSpoiledPercent, newEntry, refresh, summary, delete;
    static JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressSpeedLabel, shellTypeLabel, productionLabel, commentsLabel,
            moduleLabel, linerLabel, linerInfeedLabel, shellsSpoiledLabel, percentSpoiled;
    static JTextField dateTextField, pressSpeedTextField, prductionTextField, linerInfeedTextField, shellsSPoiledTextField, percentSpoiledTextField;
    static JTextArea commentsTextArea;
    static int view, currentId;
    static Date selectedDate;
    static JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo, shellTypeCombo, moduleCombo, linerCombo;

    static UtilDateModel model, model2, excelModel1, excelModel2;
    static JDatePanelImpl datePanel, datePanel2, excelDate1, excelDate2;
    static JDatePickerImpl datePicker, datePicker2, excelPicker1, excelPicker2;

    static JFrame frameSummary;
    static JFileChooser fileChooser;

    public static void main(String args[]) {

        new LinerDataEntryScreen(1, -1);

    }

    public LinerDataEntryScreen(int idIn, int view) {

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

        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month) - 1;
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);
        model = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        dateTextField = new JTextField();

        pressSpeedTextField = new JTextField();

        linerInfeedTextField = new JTextField();
        linerInfeedTextField.setText(0.0 + "");
//		PlainDocument doc1 = (PlainDocument) linerInfeedTextField.getDocument();
//		doc1.setDocumentFilter(new MyIntFilter());
        linerInfeedTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateSpoiledPercentage();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateSpoiledPercentage();

            }
        });

        shellsSPoiledTextField = new JTextField();
        shellsSPoiledTextField.setText(0.0 + "");
//		PlainDocument doc2 = (PlainDocument) shellsSPoiledTextField.getDocument();
//		doc2.setDocumentFilter(new MyIntFilter());
        shellsSPoiledTextField.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

                // TODO Auto-generated method stub
                calculateSpoiledPercentage();
            }

            @Override
            public void focusGained(FocusEvent e) {

                calculateSpoiledPercentage();

            }
        });

        percentSpoiledTextField = new JTextField();
        percentSpoiledTextField.setEditable(false);
        percentSpoiledTextField.setText("0.0");

        // Fill Combos From Database
        shiftCombo = new JComboBox();
        crewCombo = new JComboBox();
        optimeNumberCombo = new JComboBox();
        shellTypeCombo = new JComboBox();
        operatorCombo = new JComboBox();
        packerCombo = new JComboBox();
        qcCombo = new JComboBox();
        pressCombo = new JComboBox();
        moduleCombo = new JComboBox();
        linerCombo = new JComboBox();
        fillCombos();
        // ////////////////////////////

        JFrame frame6 = new JFrame();
        // frame6.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel outerPanel = new JPanel();

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        frame6.setTitle("Liner Data Entry");
        frame6.setSize(360, 550);
        frame6.setLocationRelativeTo(null);

        outerPanel.setLayout(new BorderLayout());

        // Create Buttons , Labels, Checkboxes etc...
        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.LinerUpdate(
                            // String dateIn, int shiftIn,
                            // String crewIn, String moduleIn, String
                            // operatorIn, String linerIn, Double linerInfeedIn,
                            // Double shellsSpoiledIn, Double
                            // calculatedSpoilageIn, String commentsIn, int idIn

                            date,
                            Integer.parseInt((String) shiftCombo.getSelectedItem()),
                            (String) crewCombo.getSelectedItem(),
                            (String) moduleCombo.getSelectedItem(),
                            (String) operatorCombo.getSelectedItem(),
                            (String) linerCombo.getSelectedItem(),
                            (Double) Double.parseDouble(linerInfeedTextField.getText()),
                            (Double) Double.parseDouble(shellsSPoiledTextField.getText()),
                            (Double) Double.parseDouble(percentSpoiledTextField.getText()),
                            commentsTextArea.getText(),
                            currentId
                    );

                    frame6.dispose();
                    frameSummary.dispose();
                    createSummaryScreen();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }
        });
        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new LinerDataEntryScreen(1, -1);
                frame6.dispose();
            }
        });
        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LinerDataEntryScreen.createSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                frame6.dispose();

            }
        });
        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    selectedDate = (Date) datePicker.getModel().getValue();
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                    String year = new SimpleDateFormat("yyyy").format(selectedDate);
                    String month = new SimpleDateFormat("MM").format(selectedDate);
                    String day = new SimpleDateFormat("dd").format(selectedDate);

                    calculateSpoiledPercentage();

                    if (SQLiteConnection.LinerEntryExists(year, month, day, (String) linerCombo.getSelectedItem(), (String) shiftCombo.getSelectedItem())) {

                        try {
                            SQLiteConnection.LinerUpdate(
                                    // String dateIn, int shiftIn,
                                    // String crewIn, String moduleIn, String
                                    // operatorIn, String linerIn, Double linerInfeedIn,
                                    // Double shellsSpoiledIn, Double
                                    // calculatedSpoilageIn, String commentsIn, int idIn

                                    date,
                                    Integer.parseInt((String) shiftCombo.getSelectedItem()),
                                    (String) crewCombo.getSelectedItem(),
                                    (String) moduleCombo.getSelectedItem(),
                                    (String) operatorCombo.getSelectedItem(),
                                    (String) linerCombo.getSelectedItem(),
                                    (Double) Double.parseDouble(linerInfeedTextField.getText()),
                                    (Double) Double.parseDouble(shellsSPoiledTextField.getText()),
                                    (Double) Double.parseDouble(percentSpoiledTextField.getText()),
                                    commentsTextArea.getText(),
                                    currentId
                            );

                            frame6.dispose();
                            createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    } else {

                        try {
                            SQLiteConnection.LinerInsert(
                                    SQLiteConnection.LinerGetHighestID() + 1,
                                    date,
                                    Integer.parseInt((String) shiftCombo.getSelectedItem()),
                                    (String) crewCombo.getSelectedItem(),
                                    (String) moduleCombo.getSelectedItem(),
                                    (String) operatorCombo.getSelectedItem(),
                                    (String) linerCombo.getSelectedItem(),
                                    (Double) Double.parseDouble(linerInfeedTextField.getText()),
                                    (Double) Double.parseDouble(shellsSPoiledTextField.getText()),
                                    (Double) Double.parseDouble(percentSpoiledTextField.getText()), commentsTextArea.getText()
                            );

                            frame6.dispose();
                            if (LinerDataEntryScreen.frameSummary.isShowing()) {
                                LinerDataEntryScreen.frameSummary.dispose();
                            }
                            createSummaryScreen();

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }

                    // TODO Auto-generated method stub
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
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

                    Object[] array = new Object[16];
                    array = SQLiteConnection.LinerReturnEntryByDate(selectedDate);

                    // String date = (String) array[1];
                    // need to do
                    if (array[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) array[0];

                        // int shiftIn,
                        // String crewIn, String moduleIn, String operatorIn,
                        // String linerIn, Double linerInfeedIn,
                        // Double shellsSpoiledIn, Double calculatedSpoilageIn,
                        // String commentsIn, int idIn
                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        moduleCombo.setSelectedItem(array[4]);
                        operatorCombo.setSelectedItem(array[5]);
                        linerCombo.setSelectedItem(array[6]);

                        linerInfeedTextField.setText((String) array[7]);
                        shellsSPoiledTextField.setText((String) array[8]);
                        percentSpoiledTextField.setText((String) array[9]);
                        commentsTextArea.setText((String) array[10]);
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

                    Object[] array = SQLiteConnection.LinerGetNextEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        currentId = currentId + 1;

                        System.out.println("Array[1]" + array[1]);

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        moduleCombo.setSelectedItem(array[4]);
                        operatorCombo.setSelectedItem(array[5]);
                        linerCombo.setSelectedItem(array[6]);

                        linerInfeedTextField.setText((String) array[7]);
                        shellsSPoiledTextField.setText((String) array[8]);
                        percentSpoiledTextField.setText((String) array[9]);
                        commentsTextArea.setText((String) array[10]);

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

                    Object[] array = SQLiteConnection.LinerGetPreviousEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        currentId = currentId - 1;

                        System.out.println("Array[1]" + array[1]);

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        shiftCombo.setSelectedItem(array[2]);
                        crewCombo.setSelectedItem(array[3]);
                        moduleCombo.setSelectedItem(array[4]);
                        operatorCombo.setSelectedItem(array[5]);
                        linerCombo.setSelectedItem(array[6]);

                        linerInfeedTextField.setText((String) array[7]);
                        shellsSPoiledTextField.setText((String) array[8]);
                        percentSpoiledTextField.setText((String) array[9]);
                        commentsTextArea.setText((String) array[10]);

                    }

                    System.out.println(currentId);

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

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame6.dispose();
                try {
                    LinerDataEntryScreen.createSummaryScreen();
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
                        SQLiteConnection.linerEntryDelete(currentId);

                        // Create Summary Screen
                        frameSummary.dispose();
                        frame6.dispose();

                        createSummaryScreen();
                    } catch (SQLException ex) {
                        Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }

            }
        });

        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        shiftLabel = new JLabel("Shift : ", SwingConstants.CENTER);
        crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
        moduleLabel = new JLabel("Module : ", SwingConstants.CENTER);
        operatorLabel = new JLabel("Operator : ", SwingConstants.CENTER);
        linerLabel = new JLabel("Liner : ", SwingConstants.CENTER);
        linerInfeedLabel = new JLabel("Liner Infeed: ", SwingConstants.CENTER);
        pressSpeedLabel = new JLabel("Press Speed : ", SwingConstants.CENTER);
        shellTypeLabel = new JLabel("Shell Type : ", SwingConstants.CENTER);
        shellsSpoiledLabel = new JLabel("Shells Spoiled : ", SwingConstants.CENTER);

        // percentSpoiled = new JLabel("Shells Spoiled Percent: ",
        // JLabel.CENTER);
        productionLabel = new JLabel("Production : ", SwingConstants.CENTER);
        commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);

        calculateSpoiledPercent = new JButton("Calculate Spoilage");
        calculateSpoiledPercent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                calculateSpoiledPercentage();

            }
        });

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(add);
        // buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);
        buttonsPanel.add(delete);

        outerPanel.add(buttonsPanel, BorderLayout.NORTH);

        // Options Panel 1
        // dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel,
        // pressSpeedLabel, shellTypeLabel,
        // productionLabel, commentsLabel;
        JPanel optionPanel1 = new JPanel(new GridLayout(9, 2));
        // optionPanel1.setBackground(Color.GRAY);

        optionPanel1.add(dateLabel);
        optionPanel1.add(datePicker);

        optionPanel1.add(shiftLabel);
        optionPanel1.add(shiftCombo);

        optionPanel1.add(crewLabel);
        optionPanel1.add(crewCombo);

        optionPanel1.add(moduleLabel);
        optionPanel1.add(moduleCombo);

        optionPanel1.add(operatorLabel);
        optionPanel1.add(operatorCombo);

        optionPanel1.add(linerLabel);
        optionPanel1.add(linerCombo);

        optionPanel1.add(linerInfeedLabel);
        optionPanel1.add(linerInfeedTextField);

        optionPanel1.add(shellsSpoiledLabel);
        optionPanel1.add(shellsSPoiledTextField);

        optionPanel1.add(calculateSpoiledPercent);
        optionPanel1.add(percentSpoiledTextField);

        commentsTextArea = new JTextArea(7, 28);
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);

        JPanel commentsPanel = new JPanel();

        commentsPanel.add(commentsLabel);
        commentsPanel.add(commentsTextArea);

        // Adding
        if (view == -1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            summary.setVisible(false);
            delete.setVisible(false);

        } // Searching
        else {

//            try {
//                currentId = SQLiteConnection.LinerGetHighestID() + 1;
//            } catch (SQLException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        outerPanel.add(optionPanel1, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(commentsPanel, BorderLayout.NORTH);

        // Options Panel 2
        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(search);
        optionsPanel2.add(update);
        optionsPanel2.add(add);

        optionsPanel2.setBackground(Color.GRAY);
        // outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        bottomPanel.add(optionsPanel2);

        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
        // optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
        // productionLabel, commentsLabel;
        outerPanel.repaint();
        frame6.add(outerPanel);

        frame6.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("LinerDataEntryScreen");

    }

    public static void createSummaryScreen() throws SQLException {

        newEntry = new JButton("New Entry Mode");
        newEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new LinerDataEntryScreen(1, -1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LinerDataEntryScreen.createSummaryScreen();
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

                LinerDataEntryScreen.exportToExcel();

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
        frameSummary = new JFrame("Liner Data Report");
        frameSummary.toFront();
        frameSummary.setSize(1366, 768);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(newEntry);
        optionsPanel2.add(refresh);
        //optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromViscan);
        // }

        JPanel summaryPanel = SQLiteConnection.LinerSummaryTable(1);
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
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    private void fillCombos() {

        // Operator
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

            conn.close();

        } catch (Exception e) {

            System.out.println("Fill Combos Error");

        }

        // Crew
        try {

            String sql = "SELECT Crew.CrewName FROM Crew";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("CrewName");
                crewCombo.addItem(crewName);
            }

            conn.close();

        } catch (Exception e) {

        }

        // Shift
        try {

            String sql = "SELECT Shift.ShiftName FROM Shift";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String shiftName = rs.getString("ShiftName");
                shiftCombo.addItem(shiftName);
            }
            conn.close();

        } catch (Exception e) {

        }

        // Module
        try {

            String sql = "SELECT Module.ModuleName FROM Module";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String moduleName = rs.getString("ModuleName");
                moduleCombo.addItem(moduleName);
            }
            conn.close();

        } catch (Exception e) {

        }

        // Liner
        try {

            String sql = "SELECT Liner.LinerName FROM Liner";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String linerName = rs.getString("LinerName");
                linerCombo.addItem(linerName);
            }
            conn.close();

        } catch (Exception e) {

        }

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.LinerGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[16];
            result = SQLiteConnection.LinerReturnEntryById(highestID);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            model.setDate(year, month, day);
            model.setSelected(true);

            // Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled, CalculatedSpoilage
            shiftCombo.setSelectedItem(result[2] + "");
            crewCombo.setSelectedItem(result[3] + "");
            moduleCombo.setSelectedItem(result[4] + "");
            operatorCombo.setSelectedItem(result[5] + "");
            linerCombo.setSelectedItem(result[6] + "");
            linerInfeedTextField.setText(result[7] + "");
            shellsSPoiledTextField.setText(result[8] + "");
            calculateSpoiledPercent.setText(result[9] + "");
            commentsTextArea.setText(result[10] + "");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setLinerDataEntryToID(int id) {

        try {

            int highestID = SQLiteConnection.LinerGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[16];
            result = SQLiteConnection.LinerReturnEntryById(id);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            model.setDate(year, month, day);
            model.setSelected(true);

            // Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled, CalculatedSpoilage
            shiftCombo.setSelectedItem(result[2] + "");
            crewCombo.setSelectedItem(result[3] + "");
            moduleCombo.setSelectedItem(result[4] + "");
            operatorCombo.setSelectedItem(result[5] + "");
            linerCombo.setSelectedItem(result[6] + "");
            linerInfeedTextField.setText(result[7] + "");
            shellsSPoiledTextField.setText(result[8] + "");
            percentSpoiledTextField.setText(result[9] + "");
            commentsTextArea.setText(result[10] + "");

            currentId = Integer.parseInt((result[0] + ""));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void calculateSpoiledPercentage() {

        if (Double.parseDouble(linerInfeedTextField.getText()) > 0.0) {

            DecimalFormat df = new DecimalFormat("###.##"); // "###.###"
            // specifies
            // precision

            double y = (Double.parseDouble(linerInfeedTextField.getText()));
            double x = (Double.parseDouble(shellsSPoiledTextField.getText()));
            double answer = (x / y) * 100;

            System.out.println("X : " + x);
            System.out.println("Y : " + y);

            double answerRounded = Double.parseDouble(df.format(answer).toString());

            System.out.println("Answer: " + df.format(answer));

            percentSpoiledTextField.setText(answerRounded + "");

            System.out.println(answer);

        }

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
            int Optime11Int = (int) shellPress21Infeed;

            HSSFCell cellB15 = rows[15].getCell((short) 1);
            double shellPress31Infeed = cellB15.getNumericCellValue();
            int Optime31Int = (int) shellPress31Infeed;

            HSSFCell cellB16 = rows[16].getCell((short) 1);
            double shellPress41Infeed = cellB16.getNumericCellValue();
            int Optime41Int = (int) shellPress41Infeed;

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
            int stolle11Int = (int) Stolle11;

            HSSFCell cellB42 = rows[42].getCell((short) 1);
            double Stolle12 = cellB42.getNumericCellValue();
            int stolle12Int = (int) Stolle12;

            HSSFCell cellB44 = rows[44].getCell((short) 1);
            double Stolle21 = cellB44.getNumericCellValue();
            int stolle21Int = (int) Stolle21;

            HSSFCell cellB45 = rows[45].getCell((short) 1);
            double Stolle22 = cellB45.getNumericCellValue();
            int stolle22Int = (int) Stolle22;

            HSSFCell cellB47 = rows[47].getCell((short) 1);
            double Stolle31 = cellB47.getNumericCellValue();
            int stolle31Int = (int) Stolle31;

            HSSFCell cellB48 = rows[48].getCell((short) 1);
            double Stolle32 = cellB48.getNumericCellValue();
            int stolle32Int = (int) Stolle32;

            HSSFCell cellB49 = rows[49].getCell((short) 1);
            double Stolle33 = cellB49.getNumericCellValue();
            int stolle33Int = (int) Stolle33;

            HSSFCell cellB51 = rows[51].getCell((short) 1);
            double Stolle41 = cellB51.getNumericCellValue();
            int stolle41Int = (int) Stolle41;

            HSSFCell cellB52 = rows[52].getCell((short) 1);
            double Stolle42 = cellB52.getNumericCellValue();
            int stolle42Int = (int) Stolle42;

            HSSFCell cellB53 = rows[53].getCell((short) 1);
            double Stolle43 = cellB53.getNumericCellValue();
            int stolle43Int = (int) Stolle43;

            HSSFCell cellB54 = rows[54].getCell((short) 1);
            double Stolle44 = cellB54.getNumericCellValue();
            int stolle44Int = (int) Stolle44;

            // End Counts Spoilage
            HSSFCell cellC41 = rows[41].getCell((short) 3);
            double Stolle11Spoilage = cellC41.getNumericCellValue();
            int stolle11SpoilageInt = (int) Stolle11Spoilage;

            HSSFCell cellC42 = rows[42].getCell((short) 3);
            double Stolle12Spoilage = cellC42.getNumericCellValue();
            int stolle12SpoilageInt = (int) Stolle12Spoilage;

            HSSFCell cellC44 = rows[44].getCell((short) 3);
            double Stolle21Spoilage = cellC44.getNumericCellValue();
            int stolle21SpoilageInt = (int) Stolle21Spoilage;

            HSSFCell cellC45 = rows[45].getCell((short) 3);
            double Stolle22Spoilage = cellC45.getNumericCellValue();
            int stolle22SpoilageInt = (int) Stolle22Spoilage;

            HSSFCell cellC47 = rows[47].getCell((short) 3);
            double Stolle31Spoilage = cellC47.getNumericCellValue();
            int stolle31SpoilageInt = (int) Stolle31Spoilage;

            HSSFCell cellC48 = rows[48].getCell((short) 3);
            double Stolle32Spoilage = cellC48.getNumericCellValue();
            int stolle32SpoilageInt = (int) Stolle32Spoilage;

            HSSFCell cellC49 = rows[49].getCell((short) 3);
            double Stolle33Spoilage = cellC49.getNumericCellValue();
            int stolle33SpoilageInt = (int) Stolle33Spoilage;

            HSSFCell cellC51 = rows[51].getCell((short) 3);
            double Stolle41Spoilage = cellC51.getNumericCellValue();
            int stolle41SpoilageInt = (int) Stolle41Spoilage;

            HSSFCell cellC52 = rows[52].getCell((short) 3);
            double Stolle42Spoilage = cellC52.getNumericCellValue();
            int stolle42SpoilageInt = (int) Stolle42Spoilage;

            HSSFCell cellC53 = rows[53].getCell((short) 3);
            double Stolle43Spoilage = cellC53.getNumericCellValue();
            int stolle43SpoilageInt = (int) Stolle43Spoilage;

            HSSFCell cellC54 = rows[54].getCell((short) 3);
            double Stolle44Spoilage = cellC54.getNumericCellValue();
            int stolle44SpoilageInt = (int) Stolle44Spoilage;

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

            // liner Discharge 
            HSSFCell cellC18 = rows[18].getCell((short) 2);
            double linerDischarge11 = cellC18.getNumericCellValue();

            HSSFCell cellC19 = rows[19].getCell((short) 2);
            double linerDischarge12 = cellC19.getNumericCellValue();

            HSSFCell cellC20 = rows[20].getCell((short) 2);
            double linerDischarge13 = cellC20.getNumericCellValue();

            HSSFCell cellC21 = rows[21].getCell((short) 2);
            double linerDischarge14 = cellC21.getNumericCellValue();

            HSSFCell cellC23 = rows[23].getCell((short) 2);
            double linerDischarge21 = cellC23.getNumericCellValue();

            HSSFCell cellC24 = rows[24].getCell((short) 2);
            double linerDischarge22 = cellC24.getNumericCellValue();

            HSSFCell cellC25 = rows[25].getCell((short) 2);
            double linerDischarge23 = cellC25.getNumericCellValue();

            HSSFCell cellC26 = rows[26].getCell((short) 2);
            double linerDischarge24 = cellC26.getNumericCellValue();

            HSSFCell cellC28 = rows[28].getCell((short) 2);
            double linerDischarge31 = cellC28.getNumericCellValue();

            HSSFCell cellC29 = rows[29].getCell((short) 2);
            double linerDischarge32 = cellC29.getNumericCellValue();

            HSSFCell cellC30 = rows[30].getCell((short) 2);
            double linerDischarge33 = cellC30.getNumericCellValue();

            HSSFCell cellC31 = rows[31].getCell((short) 2);
            double linerDischarge34 = cellC31.getNumericCellValue();

            HSSFCell cellC33 = rows[33].getCell((short) 2);
            double linerDischarge41 = cellC33.getNumericCellValue();

            HSSFCell cellC34 = rows[34].getCell((short) 2);
            double linerDischarge42 = cellC34.getNumericCellValue();

            HSSFCell cellC35 = rows[35].getCell((short) 2);
            double linerDischarge43 = cellC35.getNumericCellValue();

            HSSFCell cellC36 = rows[36].getCell((short) 2);
            double linerDischarge44 = cellC36.getNumericCellValue();

            HSSFCell cellC37 = rows[37].getCell((short) 2);
            double linerDischarge45 = cellC37.getNumericCellValue();

            HSSFCell cellC38 = rows[38].getCell((short) 2);
            double linerDischarge46 = cellC38.getNumericCellValue();

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

            //////////////////////////////////
            System.out.println("Start Date : " + startDate);
            System.out.println("End Date : " + endDate);

            HSSFCell calendarDate = rows[8].getCell((short) 1);
            String calendarDateString = calendarDate.getStringCellValue();

            HSSFCell shiftCell = rows[5].getCell((short) 1);
            String shiftCellString = shiftCell.getStringCellValue();

            HSSFCell crewCell = rows[6].getCell((short) 1);
            String crewCellString = crewCell.getStringCellValue();

            if (startDate.equalsIgnoreCase(endDate) && calendarDateString.equalsIgnoreCase("No")) {
                System.out.println("Equals No");
                if (crewCellString.contains("A") || crewCellString.contains("B") || crewCellString.contains("C") || crewCellString.contains("D")) {
                    System.out.println("Crew Yes");
                    if (shiftCellString.contains("1") || shiftCellString.contains("2")) {
                        System.out.println("Shift Yes");

                        /////////////////////////////////////////////////////////////////
                        // Date
                        String year = startDate.substring(6, 10);
                        int yearInt = Integer.parseInt(year);
                        String month = startDate.substring(3, 5);
                        int monthInt = Integer.parseInt(month) - 1;
                        String day = startDate.substring(0, 2);
                        int dayInt = Integer.parseInt(day);

                        ////////////////////////////////////////////////////////
                        // Array of Integers for Liners
                        Double[] infeedArray = new Double[36];

                        infeedArray[0] = (liner11);
                        infeedArray[1] = (liner12);
                        infeedArray[2] = (liner13);
                        infeedArray[3] = (liner14);

                        infeedArray[4] = (liner21);
                        infeedArray[5] = (liner22);
                        infeedArray[6] = (liner23);
                        infeedArray[7] = (liner24);

                        infeedArray[8] = (liner31);
                        infeedArray[9] = (liner32);
                        infeedArray[10] = (liner33);
                        infeedArray[11] = (liner34);

                        infeedArray[12] = (liner41);
                        infeedArray[13] = (liner42);
                        infeedArray[14] = (liner43);
                        infeedArray[15] = (liner44);
                        infeedArray[16] = (liner45);
                        infeedArray[17] = (liner46);

                        Double[] dischargeArray = new Double[36];

                        dischargeArray[0] = (linerDischarge11);
                        dischargeArray[1] = (linerDischarge12);
                        dischargeArray[2] = (linerDischarge13);
                        dischargeArray[3] = (linerDischarge14);

                        dischargeArray[4] = (linerDischarge21);
                        dischargeArray[5] = (linerDischarge22);
                        dischargeArray[6] = (linerDischarge23);
                        dischargeArray[7] = (linerDischarge24);

                        dischargeArray[8] = (linerDischarge31);
                        dischargeArray[9] = (linerDischarge32);
                        dischargeArray[10] = (linerDischarge33);
                        dischargeArray[11] = (linerDischarge34);

                        dischargeArray[12] = (linerDischarge41);
                        dischargeArray[13] = (linerDischarge42);
                        dischargeArray[14] = (linerDischarge43);
                        dischargeArray[15] = (linerDischarge44);
                        dischargeArray[16] = (linerDischarge45);
                        dischargeArray[17] = (linerDischarge46);

                        for (int i = 0; i < 18; i++) {

                            new LinerDataEntryScreen(1, -1);

                            model.setDate(yearInt, monthInt, dayInt);

                            ////////////////////////////////////////////////////
                            // Shift
                            if (shiftCellString.contains("1")) {
                                shiftCombo.setSelectedItem("1");
                            } else if (shiftCellString.contains("2")) {
                                shiftCombo.setSelectedItem("2");
                            }

                            ////////////////////////////////////////////////////                            
                            // Crew
                            if (crewCellString.contains("A")) {
                                crewCombo.setSelectedItem("A");
                            } else if (crewCellString.contains("B")) {
                                crewCombo.setSelectedItem("B");
                            } else if (crewCellString.contains("C")) {
                                crewCombo.setSelectedItem("C");
                            } else if (crewCellString.contains("D")) {
                                crewCombo.setSelectedItem("D");
                            }

                            ////////////////////////////////////////////////////
                            // Operator
                            operatorCombo.setSelectedItem("NA");

                            ////////////////////////////////////////////////////
                            // Module
                            if (i == 0 || i == 1 || i == 2 || i == 3) {

                                moduleCombo.setSelectedIndex(0);

                            } else if (i == 4 || i == 5 || i == 6 || i == 7) {

                                moduleCombo.setSelectedIndex(1);

                            } else if (i == 8 || i == 9 || i == 10 || i == 11) {

                                moduleCombo.setSelectedIndex(2);

                            } else if (i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {

                                moduleCombo.setSelectedIndex(3);

                            }

                            ////////////////////////////////////////////////////
                            // Liner
                            if (i == 0) {
                                linerCombo.setSelectedItem("Liner 11");
                            } else if (i == 1) {
                                linerCombo.setSelectedItem("Liner 12");
                            } else if (i == 2) {
                                linerCombo.setSelectedItem("Liner 13");
                            } else if (i == 3) {
                                linerCombo.setSelectedItem("Liner 14");
                            } else if (i == 4) {
                                linerCombo.setSelectedItem("Liner 21");
                            } else if (i == 5) {
                                linerCombo.setSelectedItem("Liner 22");
                            } else if (i == 6) {
                                linerCombo.setSelectedItem("Liner 23");
                            } else if (i == 7) {
                                linerCombo.setSelectedItem("Liner 24");
                            } else if (i == 8) {
                                linerCombo.setSelectedItem("Liner 31");
                            } else if (i == 9) {
                                linerCombo.setSelectedItem("Liner 32");
                            } else if (i == 10) {
                                linerCombo.setSelectedItem("Liner 33");
                            } else if (i == 11) {
                                linerCombo.setSelectedItem("Liner 34");
                            } else if (i == 12) {
                                linerCombo.setSelectedItem("Liner 41");
                            } else if (i == 13) {
                                linerCombo.setSelectedItem("Liner 42");
                            } else if (i == 14) {
                                linerCombo.setSelectedItem("Liner 43");
                            } else if (i == 15) {
                                linerCombo.setSelectedItem("Liner 44");
                            } else if (i == 16) {
                                linerCombo.setSelectedItem("Liner 45");
                            } else if (i == 17) {
                                linerCombo.setSelectedItem("Liner 46");
                            }

                            ////////////////////////////////////////////////////
                            // Liner Infeed and Discharge
                            linerInfeedTextField.setText(infeedArray[i] + "");
                            shellsSPoiledTextField.setText((infeedArray[i] - dischargeArray[i]) + "");

                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            calculateSpoiledPercent.doClick();
                            add.doClick();
                            frameSummary.dispose();

                        }

                        try {
                            LinerDataEntryScreen.createSummaryScreen();
                        } catch (SQLException ex) {
                            Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Stolle Start
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // Array of Integers for Liners
                        int[] infeedStolleArray = new int[11];

                        infeedStolleArray[0] = (stolle11Int);
                        infeedStolleArray[1] = (stolle12Int);

                        infeedStolleArray[2] = (stolle21Int);
                        infeedStolleArray[3] = (stolle22Int);

                        infeedStolleArray[4] = (stolle31Int);
                        infeedStolleArray[5] = (stolle32Int);
                        infeedStolleArray[6] = (stolle33Int);

                        infeedStolleArray[7] = (stolle41Int);
                        infeedStolleArray[8] = (stolle42Int);
                        infeedStolleArray[9] = (stolle43Int);
                        infeedStolleArray[10] = (stolle44Int);

                        int[] dischargeStolleArray = new int[11];

                        dischargeStolleArray[0] = (stolle11SpoilageInt);
                        dischargeStolleArray[1] = (stolle12SpoilageInt);

                        dischargeStolleArray[2] = (stolle21SpoilageInt);
                        dischargeStolleArray[3] = (stolle22SpoilageInt);

                        dischargeStolleArray[4] = (stolle31SpoilageInt);
                        dischargeStolleArray[5] = (stolle32SpoilageInt);
                        dischargeStolleArray[6] = (stolle33SpoilageInt);

                        dischargeStolleArray[7] = (stolle41SpoilageInt);
                        dischargeStolleArray[8] = (stolle42SpoilageInt);
                        dischargeStolleArray[9] = (stolle43SpoilageInt);
                        dischargeStolleArray[10] = (stolle44SpoilageInt);

                        for (int i = 0; i < 11; i++) {

                            try {
                                StolleDataEntryScreen stolleScreen = new StolleDataEntryScreen(1, -1);
                                //    stolleScreen.fillCombos();
                                stolleScreen.setModel(yearInt, monthInt, dayInt);

                                ////////////////////////////////////////////////////
                                // Shift
                                if (shiftCellString.contains("1")) {
                                    stolleScreen.setShiftCombo("1");
                                } else if (shiftCellString.contains("2")) {
                                    stolleScreen.setShiftCombo("2");
                                }

                                ////////////////////////////////////////////////////
                                // Crew
                                if (crewCellString.contains("A")) {
                                    stolleScreen.setCrewCombo("A");
                                } else if (crewCellString.contains("B")) {
                                    stolleScreen.setCrewCombo("B");
                                } else if (crewCellString.contains("C")) {
                                    stolleScreen.setCrewCombo("C");
                                } else if (crewCellString.contains("D")) {
                                    stolleScreen.setCrewCombo("D");
                                }

                                ////////////////////////////////////////////////////
                                // Operator
                                stolleScreen.setOperatorCombo("NA");
                                stolleScreen.setPackerCombo("NA");
                                stolleScreen.setQcCombo("NA");

                                ////////////////////////////////////////////////////
                                // Module
                                if (i == 0) {
                                    stolleScreen.setPressCombo("W11");
                                } else if (i == 1) {
                                    stolleScreen.setPressCombo("W12");
                                } else if (i == 2) {
                                    stolleScreen.setPressCombo("W21");
                                } else if (i == 3) {
                                    stolleScreen.setPressCombo("W22");
                                } else if (i == 4) {
                                    stolleScreen.setPressCombo("W31");
                                } else if (i == 5) {
                                    stolleScreen.setPressCombo("W32");
                                } else if (i == 6) {
                                    stolleScreen.setPressCombo("W33");
                                } else if (i == 7) {
                                    stolleScreen.setPressCombo("W41");
                                } else if (i == 8) {
                                    stolleScreen.setPressCombo("W42");
                                } else if (i == 9) {
                                    stolleScreen.setPressCombo("W43");
                                } else if (i == 10) {
                                    stolleScreen.setPressCombo("W44");
                                }

                                ////////////////////////////////////////////////////
                                // Stolle Infeed and Discharge                               
                                StolleDataEntryScreen.setStolleProductionTextField(infeedStolleArray[i] + "");
                                StolleDataEntryScreen.setPackedEndsTextField((dischargeStolleArray[i]) + "");

                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                StolleDataEntryScreen.add.doClick();
                                StolleDataEntryScreen.frameSummary.dispose();
                            } catch (SQLException ex) {
                                Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                        try {
                            StolleDataEntryScreen.createSummaryScreen();
                        } catch (SQLException ex) {
                            Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // Stolle End
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Optime Start
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        // Array of Integers for Liners
                        int[] infeedOptimeArray = new int[3];
                        infeedOptimeArray[0] = (Optime11Int);
                        infeedOptimeArray[1] = (Optime31Int);
                        infeedOptimeArray[2] = (Optime41Int);

                        for (int i = 0; i < 3; i++) {

                            OptimeDataEntryScreen optimeScreen = new OptimeDataEntryScreen(1, 1);
                            optimeScreen.setModel(year, month, day);
                            if (shiftCellString.contains("1")) {
                                optimeScreen.setShiftCombo("1");
                            } else if (shiftCellString.contains("2")) {
                                optimeScreen.setShiftCombo("2");
                            }
                            if (crewCellString.contains("A")) {
                                optimeScreen.setCrewCombo("A");
                            } else if (crewCellString.contains("B")) {
                                optimeScreen.setCrewCombo("B");
                            } else if (crewCellString.contains("C")) {
                                optimeScreen.setCrewCombo("C");
                            } else if (crewCellString.contains("D")) {
                                optimeScreen.setCrewCombo("D");
                            }

                            optimeScreen.setOperatorCombo("NA");
                            optimeScreen.setShellTypeCombo("NA");

                            if (i == 0) {
                                optimeScreen.setOptimeNumberCombo("1");
                            } else if (i == 1) {
                                optimeScreen.setOptimeNumberCombo("3");
                            } else if (i == 2) {
                                optimeScreen.setOptimeNumberCombo("4");
                            }

                            OptimeDataEntryScreen.setProductionTextField(infeedOptimeArray[i] + "");
                            OptimeDataEntryScreen.add.doClick();
                            OptimeDataEntryScreen.frameSummary.dispose();

                        }

                        try {
                            OptimeDataEntryScreen.createSummaryScreen();
                        } catch (SQLException ex) {
                            Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Optime End
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                        new LinerUsageEntryScreen(1, -1);
//                        LinerUsageEntryScreen.setModel(year, month, day);
//
//                        // Crew
//                        if (crewCellString.contains("A")) {
//                            LinerUsageEntryScreen.setCrewCombo("A");
//                        } else if (crewCellString.contains("B")) {
//                            LinerUsageEntryScreen.setCrewCombo("B");
//                        } else if (crewCellString.contains("C")) {
//                            LinerUsageEntryScreen.setCrewCombo("C");
//                        } else if (crewCellString.contains("D")) {
//                            LinerUsageEntryScreen.setCrewCombo("D");
//                        }
//                        try {
//                            /////////////////////////////////////////////////////////////////
//
//                            new StolleDataEntryScreen(1, -1);
//
//                            StolleDataEntryScreen.setModel(year, month, day);
//
//                            // Shift
//                            if (shiftCellString.contains("1")) {
//                                StolleDataEntryScreen.setShiftCombo("1");
//                            } else if (shiftCellString.contains("2")) {
//                                StolleDataEntryScreen.setShiftCombo("2");
//                            }
//
//                            // Crew
//                            if (crewCellString.contains("A")) {
//                                StolleDataEntryScreen.setCrewCombo("A");
//                            } else if (crewCellString.contains("B")) {
//                                StolleDataEntryScreen.setCrewCombo("B");
//                            } else if (crewCellString.contains("C")) {
//                                StolleDataEntryScreen.setCrewCombo("C");
//                            } else if (crewCellString.contains("D")) {
//                                StolleDataEntryScreen.setCrewCombo("D");
//                            }
//
//                            /////////////////////////////////////////////////////////////////
//                        } catch (SQLException ex) {
//                            Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong File. "
                                + "Excel File is for more than 1 day or not for correct TimeFrame.\n "
                                + "Ensure CREW and SHIFT Boxes are filled and Calendar is NO");
                    }

                    ////////////////
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong File. "
                            + "Excel File is for more than 1 day or not for correct TimeFrame.\n "
                            + "Ensure CREW and SHIFT Boxes are filled and Calendar is NO");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong File. "
                        + "Excel File is for more than 1 day or not for correct TimeFrame.\n "
                        + "Ensure CREW and SHIFT Boxes are filled and Calendar is NO");
            }
        }

        // Import into Database with matching dates if it doesnt exist for LinersAndShells, LinerDefects, EndCounts
        // If exist skip that individual section
    }

    public static void exportToExcel() {

        String[] typesArray = {"Optime Data", "Liner Data", "Liner Usage", "Stolle Data", "LSS-PM Activity", "Production Meeting", "Meeting Quality"};
        JComboBox typeCombo = new JComboBox(typesArray);
        JCheckBox datesCheck = new JCheckBox();

        excelModel1 = new UtilDateModel(new Date());
        excelModel2 = new UtilDateModel(new Date());
        excelDate1 = new JDatePanelImpl(excelModel1);
        excelDate2 = new JDatePanelImpl(excelModel2);
        excelPicker1 = new JDatePickerImpl(excelDate1);
        excelPicker2 = new JDatePickerImpl(excelDate2);

        query = "";

        String[] sortTypesArray = {"Date", "Shift", "Crew"};
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

                if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Optime Data")) {
                    query = "SELECT * FROM OptimeEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Liner Data")) {
                    query = "SELECT * FROM LinerEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Liner Usage")) {
                    query = "SELECT * FROM LinerUsage WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Stolle Data")) {
                    query = "SELECT * FROM Stolle WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("LSS-PM Activity")) {
                    query = "SELECT * FROM LSSPMEntry WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Production Meeting")) {
                    query = "SELECT * FROM ProductionMeeting WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
                } else if ((typeCombo.getSelectedItem() + "").equalsIgnoreCase("Meeting Quality")) {
                    query = "SELECT * FROM MeetingQuality WHERE Date BETWEEN \'" + modifiedDate1 + "\' AND \'" + modifiedDate2 + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";
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
            FileOutputStream output = new FileOutputStream("ExcelFiles/Production.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("ExcelFiles/Production.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
