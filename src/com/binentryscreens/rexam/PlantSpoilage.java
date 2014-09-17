package com.binentryscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import com.database.rexam.SQLiteConnection;

public class PlantSpoilage {

    JComboBox monthCombo, yearCombo;
    JButton back, go;

    public static void main(String[] args) {

		// Todays Month As String
        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);

        if (month.equals("01")) {
            month = "January";
        } else if (month.equals("02")) {
            month = "February";
        } else if (month.equals("03")) {
            month = "March";
        } else if (month.equals("04")) {
            month = "April";
        } else if (month.equals("05")) {
            month = "May";
        } else if (month.equals("06")) {
            month = "June";
        } else if (month.equals("07")) {
            month = "July";
        } else if (month.equals("08")) {
            month = "August";
        } else if (month.equals("09")) {
            month = "September";
        } else if (month.equals("10")) {
            month = "October";
        } else if (month.equals("11")) {
            month = "Novemebr";
        } else if (month.equals("12")) {
            month = "December";
        }

        try {
            new PlantSpoilage(1, month, yearInt + "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public PlantSpoilage(int view, String monthIn, String yearIn) throws Exception {

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

		// Create JFrame
        JFrame frame = new JFrame("Plant Spoilage");
        frame.setSize(1000, 660);
        frame.setLocationRelativeTo(null);

        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] years = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};
        JComboBox monthCombo = new JComboBox(monthArray);
        monthCombo.setSelectedItem(monthIn);
        JComboBox yearCombo = new JComboBox(years);
        yearCombo.setSelectedItem(yearIn);
        JButton go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				// Get Selected Month as String
                System.out.println("Selected Month : " + monthCombo.getSelectedItem());

                try {
                    new PlantSpoilage(1, monthCombo.getSelectedItem() + "", yearCombo.getSelectedIndex() + "");
                    frame.dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });

		// Create JPanels
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.GRAY);
        topPanel.add(monthCombo);
        topPanel.add(yearCombo);
        topPanel.add(go);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(back);

        outerPanel.add(topPanel, BorderLayout.NORTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        try {
            outerPanel.add(createTablePanel(monthIn, yearIn), BorderLayout.CENTER);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        frame.add(outerPanel);
        frame.setVisible(true);
    }

    public static JPanel createTablePanel(String monthIn, String yearIn) throws Exception {

        JPanel panel = new JPanel(new BorderLayout());

        JTable spoilageTable = new JTable(32, 8);

		// Center all Labels ///////
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int x = 0; x < 8; x++) {
            spoilageTable.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
        }

		// //////////////////////////
        int lineSpoilageTotal = 0;
        int totalSpoilage = 0;
        try {
            lineSpoilageTotal = SQLiteConnection.SpoilageByDayTotalMonthlySum(monthIn, yearIn);
            totalSpoilage = SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4] + lineSpoilageTotal;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		// Spoilage Percentages
        // Hfi SPOILAGE
        DecimalFormat df = new DecimalFormat("###.##"); // <- // "###.###"
        // specifies precision
        double hfiSpoilageDouble = SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4] + 0.0;
        double shellsProducedDouble = (Double.parseDouble(SQLiteConnection.PlantSpoilageShellsProducedCalculateTotalsByMonth(monthIn, yearIn) + ""));
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (hfiSpoilageDouble == 0.0) {
            hfiSpoilageDouble = 0.01;
        }
        double HFISpoilagePercentage = (hfiSpoilageDouble / shellsProducedDouble) * 100;
        double HFISpoilagePercentageRounded = Double.parseDouble(df.format(HFISpoilagePercentage).toString());
		// ////////////////////

        // Line SPOILAGE
        double lineSpoilageDouble = lineSpoilageTotal + 0.0;
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (lineSpoilageDouble == 0.0) {
            lineSpoilageDouble = 0.01;
        }
        double lineSpoilagePercentage = (lineSpoilageDouble / shellsProducedDouble) * 100;
        double lineSpoilagePercentageRounded = Double.parseDouble(df.format(lineSpoilagePercentage).toString());
		// ////////////////////

        // Total SPOILAGE
        double totalSpoilageDouble = totalSpoilage + 0.0;
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (totalSpoilageDouble == 0.0) {
            totalSpoilageDouble = 0.01;
        }
        double totalSpoilagePercentage = (totalSpoilageDouble / shellsProducedDouble) * 100;
        double totalSpoilagePercentageRounded = Double.parseDouble(df.format(totalSpoilagePercentage).toString());
		// ////////////////////

		// /// Other Calculations //////
        // Fill Column 1 of JTable
        spoilageTable.getModel().setValueAt("Shells Produced", 0, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageShellsProducedCalculateTotalsByMonth(monthIn, yearIn), 1, 0);
        spoilageTable.getModel().setValueAt("Ends Packed", 2, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageEndCountsCalculateTotalsByMonth(monthIn, yearIn), 3, 0);
        spoilageTable.getModel().setValueAt("HFI Opening", 4, 0);
        spoilageTable.getModel().setValueAt("  ", 5, 0);
        spoilageTable.getModel().setValueAt("HFI Created", 6, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[0], 7, 0);
        spoilageTable.getModel().setValueAt("HFI Recovered", 8, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[1], 9, 0);
        spoilageTable.getModel().setValueAt("HFI Scrapped", 10, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[2], 11, 0);
        spoilageTable.getModel().setValueAt("HFI Remaining", 12, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[3], 13, 0);
        spoilageTable.getModel().setValueAt("HFI Spoilage", 14, 0);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4], 15, 0);
        spoilageTable.getModel().setValueAt("Line Spoilage", 16, 0);
        spoilageTable.getModel().setValueAt(lineSpoilageTotal, 17, 0);
        spoilageTable.getModel().setValueAt("Total Spoilage", 18, 0);
        spoilageTable.getModel().setValueAt(totalSpoilage, 19, 0);
        spoilageTable.getModel().setValueAt("HFI Spoilage %", 20, 0);
        spoilageTable.getModel().setValueAt(HFISpoilagePercentageRounded + "%", 21, 0);
        spoilageTable.getModel().setValueAt("Line Spoilage %", 22, 0);
        spoilageTable.getModel().setValueAt(lineSpoilagePercentageRounded + "%", 23, 0);
        spoilageTable.getModel().setValueAt("Total Spoilage %", 24, 0);
        spoilageTable.getModel().setValueAt(totalSpoilagePercentageRounded + "%", 25, 0);
        spoilageTable.getModel().setValueAt("  ", 26, 0);
        spoilageTable.getModel().setValueAt("  ", 27, 0);
        spoilageTable.getModel().setValueAt("  ", 28, 0);
        spoilageTable.getModel().setValueAt("  ", 29, 0);
        spoilageTable.getModel().setValueAt("  ", 30, 0);
        spoilageTable.getModel().setValueAt("  ", 31, 0);

        // Fill Column 2 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 1);
        spoilageTable.getModel().setValueAt(" ", 1, 1);
        spoilageTable.getModel().setValueAt(" ", 2, 1);
        spoilageTable.getModel().setValueAt(" ", 3, 1);
        spoilageTable.getModel().setValueAt(" ", 4, 1);
        spoilageTable.getModel().setValueAt(" ", 5, 1);
        spoilageTable.getModel().setValueAt(" ", 6, 1);
        spoilageTable.getModel().setValueAt(" ", 7, 1);
        spoilageTable.getModel().setValueAt(" ", 8, 1);
        spoilageTable.getModel().setValueAt("Shell Press", 9, 1);
        spoilageTable.getModel().setValueAt("A Balancer", 10, 1);
        spoilageTable.getModel().setValueAt("Liners", 11, 1);
        spoilageTable.getModel().setValueAt("B Balancer", 12, 1);
        spoilageTable.getModel().setValueAt("Conversion Press", 13, 1);
        spoilageTable.getModel().setValueAt("AutoBaggers", 14, 1);
        spoilageTable.getModel().setValueAt("QC Checks", 15, 1);
        spoilageTable.getModel().setValueAt("Total Line", 16, 1);
        spoilageTable.getModel().setValueAt("HFI", 17, 1);
        spoilageTable.getModel().setValueAt("Other", 18, 1);
        spoilageTable.getModel().setValueAt("Total", 19, 1);
        spoilageTable.getModel().setValueAt("  ", 20, 1);
        spoilageTable.getModel().setValueAt("Shell Press", 21, 1);
        spoilageTable.getModel().setValueAt("A Balancer", 22, 1);
        spoilageTable.getModel().setValueAt("Liners", 23, 1);
        spoilageTable.getModel().setValueAt("B Balancer", 24, 1);
        spoilageTable.getModel().setValueAt("Conversion Press", 25, 1);
        spoilageTable.getModel().setValueAt("AutoBaggers", 26, 1);
        spoilageTable.getModel().setValueAt("QC Checks", 27, 1);
        spoilageTable.getModel().setValueAt("Total Line", 28, 1);
        spoilageTable.getModel().setValueAt("HFI", 29, 1);
        spoilageTable.getModel().setValueAt("Other", 30, 1);
        spoilageTable.getModel().setValueAt("Total", 31, 1);

		// Conversion Presses
        int conversionPress1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16];
        int conversionPress2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7];
        int conversionPress3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18];
        int conversionPress4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[27] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[23]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[28] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[29];

		// QC Checks
        int QCChecks1 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[26])
                + ((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 5);

        int QCChecks2 = ((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 5);

        int QCChecks3 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[22])
                + (((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 10) * 3);

        int QCChecks4 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[13])
                + (((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 10) * 3);

        int QCChecks5 = QCChecks1 + QCChecks2 + QCChecks3 + QCChecks4;
        int QCChecks6 = QCChecks1 + QCChecks2 + QCChecks3;

		// Total Lined
        int totalLine1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16] + QCChecks1;

        int totalLine2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + // Optime2
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + // M2ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + // M2Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] + // M2BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14] + // STolle
                // 21
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7] + // STolle
                // 22
                QCChecks2;

        int totalLine3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + // Optime
                // 3
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] + // M3ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + // M3Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + // M3BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17] + // Stolle
                // 31
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + // Stolle
                // 32
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + // Stolle
                // 33
                QCChecks3;

        int totalLine4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + // Optime
                // 3
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] + // M3ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + // M3Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + // M3BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17] + // Stolle
                // 31
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + // Stolle
                // 32
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + // Stolle
                // 33
                QCChecks3;

        int totalLine5 = totalLine1 + totalLine2 + totalLine3 + totalLine4;

        int totalLine6 = totalLine1 + totalLine2 + totalLine3;

        int m4BBal = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[30] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[24];

        int total1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + // Optime
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1];

        int total2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + // M1ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8];

        int total3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4] + // Liners
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21];

        int total4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + // M1BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14];

        int total5 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6]
                + // Stolle
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15];

        int total6 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[32]; // Optime4
        int total7 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31]; // Balancer4A
        int total8 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25]; // LinerM4
        int total9 = total1 + m4BBal; // M4BBal
        int total10 = total5 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[27] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[23]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[28] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[29];

        // Calculating Others 1
        int h172 = SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn)
                + SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn);
        int h168 = SQLiteConnection.PlantSpoilageOptime2CalculateTotalsByMonth(monthIn, yearIn);
        if (h168 == 0) {
            h168 = 1;
        }
        if (h172 == 0) {
            h172 = 1;
        }
        System.out.println("h168 " + h168);
        System.out.println("h172 " + h172);
        int h165 = h168 / h172;
        int i165 = SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn) + h165;
        double n195 = totalSpoilagePercentageRounded + lineSpoilagePercentageRounded + HFISpoilagePercentageRounded;
        double totalOther = total6 * n195; // To be finished
        if (i165 == 0) {
            i165 = 1;
        }
        if (total6 == 0) {
            total6 = 1;
        }
        if (totalOther == 0) {
            totalOther = 1;
        }
        double other1 = (i165 / total6) * totalOther; // To be finished
        // //////////////////////////////
        // Calculating Others 2
        int j165 = SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn) + h165;
        double other2 = (j165 / total6) * totalOther; // To be finished
        // /////////////////////////////
        // Calculating Others 3
        double other3 = (SQLiteConnection.PlantSpoilageOptime3CalculateTotalsByMonth(monthIn, yearIn) / total6) * totalOther;
		// /////////////////////////////
        // Calculating Others 4
        double other4 = (SQLiteConnection.PlantSpoilageOptime4CalculateTotalsByMonth(monthIn, yearIn) / total6) * totalOther;
		// /////////////////////////////

		// Calculate totalFinals
        double totalFinal1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + conversionPress1 + QCChecks1 + totalLine1
                + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other1;
        double totalFinal2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] + conversionPress2
                + QCChecks2 + totalLine2 + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other2;
        double totalFinal3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + conversionPress3
                + QCChecks3 + totalLine3 + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other3;
        double totalFinal4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25] + m4BBal + conversionPress4 + QCChecks4 + totalLine4
                + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other4;
        double totalFinal5 = totalFinal1 + totalFinal2 + totalFinal3;
        double totalFinal6 = totalFinal5 + totalFinal4;

        // Fill Column 3 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 2);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 2);
        spoilageTable.getModel().setValueAt("  ", 2, 2);
        spoilageTable.getModel().setValueAt(" ", 3, 2);
        spoilageTable.getModel().setValueAt("Liners Mod 1", 4, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageM1CalculateTotalsByMonth(monthIn, yearIn), 5, 2);
        spoilageTable.getModel().setValueAt("Stolle Mod 1", 6, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn), 7, 2);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 2);
        spoilageTable.getModel().setValueAt("", 9, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9], 10, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4], 11, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2], 12, 2);
        spoilageTable.getModel().setValueAt(conversionPress1, 13, 2);
        spoilageTable.getModel().setValueAt(" ", 14, 2);
        spoilageTable.getModel().setValueAt(QCChecks1, 15, 2);
        spoilageTable.getModel().setValueAt(totalLine1, 16, 2);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4, 17, 2);
        spoilageTable.getModel().setValueAt(other1, 18, 2);
        spoilageTable.getModel().setValueAt(totalFinal1, 19, 2);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 2);
        spoilageTable.getModel().setValueAt(" ", 21, 2);
        spoilageTable.getModel().setValueAt(" ", 22, 2);
        spoilageTable.getModel().setValueAt(" ", 23, 2);
        spoilageTable.getModel().setValueAt(" ", 24, 2);
        spoilageTable.getModel().setValueAt(" ", 25, 2);
        spoilageTable.getModel().setValueAt(" ", 26, 2);
        spoilageTable.getModel().setValueAt(" ", 27, 2);
        spoilageTable.getModel().setValueAt(" ", 28, 2);
        spoilageTable.getModel().setValueAt(" ", 29, 2);
        spoilageTable.getModel().setValueAt(" ", 30, 2);
        spoilageTable.getModel().setValueAt(" ", 31, 2);

        // Fill Column 3 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 3);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 3);
        spoilageTable.getModel().setValueAt("Shell Press Mod 2", 2, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageOptime2CalculateTotalsByMonth(monthIn, yearIn), 3, 3);
        spoilageTable.getModel().setValueAt("Liners Mod 2", 4, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageM2CalculateTotalsByMonth(monthIn, yearIn), 5, 3);
        spoilageTable.getModel().setValueAt("Stolle Mod 2", 6, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn), 7, 3);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0], 9, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20], 10, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5], 11, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19], 12, 3);
        spoilageTable.getModel().setValueAt(conversionPress2, 13, 3);
        spoilageTable.getModel().setValueAt(" ", 14, 3);
        spoilageTable.getModel().setValueAt(QCChecks2, 15, 3);
        spoilageTable.getModel().setValueAt(totalLine2, 16, 3);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4, 17, 3);
        spoilageTable.getModel().setValueAt(other2, 18, 3);
        spoilageTable.getModel().setValueAt(totalFinal2, 19, 3);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 3);
        spoilageTable.getModel().setValueAt(" ", 21, 3);
        spoilageTable.getModel().setValueAt(" ", 22, 3);
        spoilageTable.getModel().setValueAt(" ", 23, 3);
        spoilageTable.getModel().setValueAt(" ", 24, 3);
        spoilageTable.getModel().setValueAt(" ", 25, 3);
        spoilageTable.getModel().setValueAt(" ", 26, 3);
        spoilageTable.getModel().setValueAt(" ", 27, 3);
        spoilageTable.getModel().setValueAt(" ", 28, 3);
        spoilageTable.getModel().setValueAt(" ", 29, 3);
        spoilageTable.getModel().setValueAt(" ", 30, 3);
        spoilageTable.getModel().setValueAt(" ", 31, 3);

        // Fill Column 4 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 4);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 4);
        spoilageTable.getModel().setValueAt("Shell Press Mod 3", 2, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageOptime3CalculateTotalsByMonth(monthIn, yearIn), 3, 4);
        spoilageTable.getModel().setValueAt("Liners Mod 3", 4, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageM3CalculateTotalsByMonth(monthIn, yearIn), 5, 4);
        spoilageTable.getModel().setValueAt("Stolle Mod 3", 6, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageStolleMod3CalculateTotalsByMonth(monthIn, yearIn), 7, 4);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1], 9, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8], 10, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21], 11, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3], 12, 4);
        spoilageTable.getModel().setValueAt(conversionPress3, 13, 4);
        spoilageTable.getModel().setValueAt(" ", 14, 4);
        spoilageTable.getModel().setValueAt(QCChecks3, 15, 4);
        spoilageTable.getModel().setValueAt(totalLine3, 16, 4);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4, 17, 4);
        spoilageTable.getModel().setValueAt(other3, 18, 4);
        spoilageTable.getModel().setValueAt(totalFinal3, 19, 4);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 4);
        spoilageTable.getModel().setValueAt(" ", 21, 4);
        spoilageTable.getModel().setValueAt(" ", 22, 4);
        spoilageTable.getModel().setValueAt(" ", 23, 4);
        spoilageTable.getModel().setValueAt(" ", 24, 4);
        spoilageTable.getModel().setValueAt(" ", 25, 4);
        spoilageTable.getModel().setValueAt(" ", 26, 4);
        spoilageTable.getModel().setValueAt(" ", 27, 4);
        spoilageTable.getModel().setValueAt(" ", 28, 4);
        spoilageTable.getModel().setValueAt(" ", 29, 4);
        spoilageTable.getModel().setValueAt(" ", 30, 4);
        spoilageTable.getModel().setValueAt(" ", 31, 4);

        // Fill Column 5 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 5);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 5);
        spoilageTable.getModel().setValueAt("B64 Totals", 2, 5);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[0], 3, 5);
        spoilageTable.getModel().setValueAt("B64 Totals", 4, 5);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[1], 5, 5);
        spoilageTable.getModel().setValueAt("B64 Totals", 6, 5);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[2], 7, 5);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 5);
        spoilageTable.getModel().setValueAt(total1, 9, 5);
        spoilageTable.getModel().setValueAt(total2, 10, 5);
        spoilageTable.getModel().setValueAt(total3, 11, 5);
        spoilageTable.getModel().setValueAt(total4, 12, 5);
        spoilageTable.getModel().setValueAt(total5, 13, 5);
        spoilageTable.getModel().setValueAt(" ", 14, 5);
        spoilageTable.getModel().setValueAt(QCChecks6, 15, 5);
        spoilageTable.getModel().setValueAt(totalLine6, 16, 5);
        spoilageTable.getModel().setValueAt(((SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) * 3), 17, 5);
        spoilageTable.getModel().setValueAt(other1 + other2 + other3, 18, 5);
        spoilageTable.getModel().setValueAt(totalFinal5, 19, 5);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 5);
        spoilageTable.getModel().setValueAt(" ", 21, 5);
        spoilageTable.getModel().setValueAt(" ", 22, 5);
        spoilageTable.getModel().setValueAt(" ", 23, 5);
        spoilageTable.getModel().setValueAt(" ", 24, 5);
        spoilageTable.getModel().setValueAt(" ", 25, 5);
        spoilageTable.getModel().setValueAt(" ", 26, 5);
        spoilageTable.getModel().setValueAt(" ", 27, 5);
        spoilageTable.getModel().setValueAt(" ", 28, 5);
        spoilageTable.getModel().setValueAt(" ", 29, 5);
        spoilageTable.getModel().setValueAt(" ", 30, 5);
        spoilageTable.getModel().setValueAt(" ", 31, 5);

        // Fill Column 6 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 6);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 6);
        spoilageTable.getModel().setValueAt("Shell Press Mod 4", 2, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageOptime4CalculateTotalsByMonth(monthIn, yearIn), 3, 6);
        spoilageTable.getModel().setValueAt("Liners Mod 4", 4, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageM4CalculateTotalsByMonth(monthIn, yearIn), 5, 6);
        spoilageTable.getModel().setValueAt("Stolle Mod 4", 6, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageStolleMod4CalculateTotalsByMonth(monthIn, yearIn), 7, 6);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2], 9, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31], 10, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25], 11, 6);
        spoilageTable.getModel().setValueAt(m4BBal, 12, 6);
        spoilageTable.getModel().setValueAt(conversionPress4, 13, 6);
        spoilageTable.getModel().setValueAt(" ", 14, 6);
        spoilageTable.getModel().setValueAt(QCChecks4, 15, 6);
        spoilageTable.getModel().setValueAt(totalLine4, 16, 6);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4, 17, 6);
        spoilageTable.getModel().setValueAt(other4, 18, 6);
        spoilageTable.getModel().setValueAt(totalFinal4, 19, 6);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 6);
        spoilageTable.getModel().setValueAt(" ", 21, 6);
        spoilageTable.getModel().setValueAt(" ", 22, 6);
        spoilageTable.getModel().setValueAt(" ", 23, 6);
        spoilageTable.getModel().setValueAt(" ", 24, 6);
        spoilageTable.getModel().setValueAt(" ", 25, 6);
        spoilageTable.getModel().setValueAt(" ", 26, 6);
        spoilageTable.getModel().setValueAt(" ", 27, 6);
        spoilageTable.getModel().setValueAt(" ", 28, 6);
        spoilageTable.getModel().setValueAt(" ", 29, 6);
        spoilageTable.getModel().setValueAt(" ", 30, 6);
        spoilageTable.getModel().setValueAt(" ", 31, 6);

        // Fill Column 7 of JTable
        spoilageTable.getModel().setValueAt(" ", 0, 7);
        spoilageTable.getModel().setValueAt("-------------------------", 1, 7);
        spoilageTable.getModel().setValueAt("Total", 2, 7);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[3], 3, 7);
        spoilageTable.getModel().setValueAt("Total", 4, 7);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[4], 5, 7);
        spoilageTable.getModel().setValueAt("Total", 6, 7);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[5], 7, 7);
        spoilageTable.getModel().setValueAt("-------------------------", 8, 7);
        spoilageTable.getModel().setValueAt(total6, 9, 7);
        spoilageTable.getModel().setValueAt(total7, 10, 7);
        spoilageTable.getModel().setValueAt(total8, 11, 7);
        spoilageTable.getModel().setValueAt(total9, 12, 7);
        spoilageTable.getModel().setValueAt(total10, 13, 7);
        spoilageTable.getModel().setValueAt(" ", 14, 7);
        spoilageTable.getModel().setValueAt(QCChecks5, 15, 7);
        spoilageTable.getModel().setValueAt(totalLine5, 16, 7);
        spoilageTable.getModel().setValueAt(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5], 17, 7);
        spoilageTable.getModel().setValueAt(other1 + other2 + other3 + other4, 18, 7);
        spoilageTable.getModel().setValueAt(totalFinal6, 19, 7);
        spoilageTable.getModel().setValueAt("-------------------------", 20, 7);
        spoilageTable.getModel().setValueAt(" ", 21, 7);
        spoilageTable.getModel().setValueAt(" ", 22, 7);
        spoilageTable.getModel().setValueAt(" ", 23, 7);
        spoilageTable.getModel().setValueAt(" ", 24, 7);
        spoilageTable.getModel().setValueAt(" ", 25, 7);
        spoilageTable.getModel().setValueAt(" ", 26, 7);
        spoilageTable.getModel().setValueAt(" ", 27, 7);
        spoilageTable.getModel().setValueAt(" ", 28, 7);
        spoilageTable.getModel().setValueAt(" ", 29, 7);
        spoilageTable.getModel().setValueAt(" ", 30, 7);
        spoilageTable.getModel().setValueAt(" ", 31, 7);

        panel.add(spoilageTable);
        return panel;
    }

}
