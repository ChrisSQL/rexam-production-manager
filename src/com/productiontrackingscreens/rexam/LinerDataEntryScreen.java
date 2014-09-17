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

public class LinerDataEntryScreen {

    static JButton add, find, next, previous, update, addNew, search, calculateSpoiledPercent, newEntry, refresh, summary;
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

    public static void main(String args[]) {

        new LinerDataEntryScreen(1, -1);

    }

    public LinerDataEntryScreen(int idIn, int view) {

        // Add a view to analytics.
        try {
            SQLiteConnection.incrementViewsAnalytics(0, 0, 1, 0, 0, 0, 0, 0, 0);
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

                            date, Integer.parseInt((String) shiftCombo.getSelectedItem()), (String) crewCombo.getSelectedItem(),
                            (String) moduleCombo.getSelectedItem(), (String) operatorCombo.getSelectedItem(), (String) linerCombo.getSelectedItem(),
                            (Double) Double.parseDouble(linerInfeedTextField.getText()),
                            (Double) Double.parseDouble(shellsSPoiledTextField.getText()),
                            (Double) Double.parseDouble(percentSpoiledTextField.getText()), commentsTextArea.getText(), currentId
                    );

                    frame6.dispose();

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

                new LinerDataEntryScreen(1, -2);
                setLastEntry();
                frame6.dispose();

            }
        });
        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                calculateSpoiledPercentage();

                try {
                    SQLiteConnection.LinerInsert(
                            SQLiteConnection.LinerGetHighestID() + 1, date, Integer.parseInt((String) shiftCombo.getSelectedItem()),
                            (String) crewCombo.getSelectedItem(), (String) moduleCombo.getSelectedItem(), (String) operatorCombo.getSelectedItem(),
                            (String) linerCombo.getSelectedItem(), (Double) Double.parseDouble(linerInfeedTextField.getText()),
                            (Double) Double.parseDouble(shellsSPoiledTextField.getText()),
                            (Double) Double.parseDouble(percentSpoiledTextField.getText()), commentsTextArea.getText()
                    );

                    frame6.dispose();

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
                        int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

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
                        int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

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
        buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

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

        } // Searching
        else {

            try {
                currentId = SQLiteConnection.LinerGetHighestID() + 1;
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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

        // Outer Frame
        frameSummary = new JFrame("Liner Data Report");
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
        // }

        JPanel summaryPanel = SQLiteConnection.LinerSummaryTable(1);

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
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    private void fillCombos() {

        // Names
        try {

            String sql = "select Employees.Name from Employees";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String name = rs.getString("Name");
                operatorCombo.addItem(name);
                packerCombo.addItem(name);
                qcCombo.addItem(name);
            }

        } catch (Exception e) {

        }

        // Packers
        try {

            String sql = "SELECT Press.PressName FROM Press";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String pressName = rs.getString("PressName");
                pressCombo.addItem(pressName);
            }

        } catch (Exception e) {

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

        } catch (Exception e) {

        }

        // OpTime Number
        try {

            String sql = "SELECT Optime.OptimeName FROM Optime";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String optimeName = rs.getString("OptimeName");
                optimeNumberCombo.addItem(optimeName);
            }

        } catch (Exception e) {

        }

        // OpTime Number
        try {

            String sql = "SELECT ShellType.ShellTypeName FROM ShellType";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String shellTypeName = rs.getString("ShellTypeName");
                shellTypeCombo.addItem(shellTypeName);
            }

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
            int day = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
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
            FileOutputStream output = new FileOutputStream("LinerExcel.xls");
            workBook.write(output);

            Desktop dt = Desktop.getDesktop();
            dt.open(new File("LinerExcel.xls"));

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
