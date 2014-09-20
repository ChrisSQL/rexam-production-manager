package com.maintenance.rexam;

// Fix SP04 Fill Boxes - Get Excel Totals Correct

import com.database.rexam.SQLiteConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ShellPressMaintenance {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton SP01, SP02, SP03, SP04, update;
    static JLabel toolingAreaLabel1, toolingAreaLabel2, toolingAreaLabel3, toolingAreaLabel4, toolingAreaLabel5, toolingAreaLabel6, toolingAreaLabel7;
    static JLabel MaintenanceTypeLabel1, MaintenanceTypeLabel2, MaintenanceTypeLabel3, MaintenanceTypeLabel4, MaintenanceTypeLabel5, MaintenanceTypeLabel6, MaintenanceTypeLabel7;
    static JTextField MachineCodeJTexField1, MachineNameJTexField1, TargetProductionJTexField1, ProductionJTexField1, PlusMinusJTexField1, MaintenanceDueDateJTexField1, DaysRemainingJTexField1;
    static JTextField MachineCodeJTexField2, MachineNameJTexField2, TargetProductionJTexField2, ProductionJTexField2, PlusMinusJTexField2, MaintenanceDueDateJTexField2, DaysRemainingJTexField2;
    static JTextField MachineCodeJTexField3, MachineNameJTexField3, TargetProductionJTexField3, ProductionJTexField3, PlusMinusJTexField3, MaintenanceDueDateJTexField3, DaysRemainingJTexField3;
    static JTextField MachineCodeJTexField4, MachineNameJTexField4, TargetProductionJTexField4, ProductionJTexField4, PlusMinusJTexField4, MaintenanceDueDateJTexField4, DaysRemainingJTexField4;
    static JTextField MachineCodeJTexField5, MachineNameJTexField5, TargetProductionJTexField5, ProductionJTexField5, PlusMinusJTexField5, MaintenanceDueDateJTexField5, DaysRemainingJTexField5;
    static JTextField MachineCodeJTexField6, MachineNameJTexField6, TargetProductionJTexField6, ProductionJTexField6, PlusMinusJTexField6, MaintenanceDueDateJTexField6, DaysRemainingJTexField6;
    static JTextField MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal, TargetProductionJTexFieldTotal, ProductionJTexFieldTotal, PlusMinusJTexFieldTotal, MaintenanceDueDateJTexFieldTotal, DaysRemainingJTexFieldTotal;

    static UtilDateModel LastMaintenanceModel1, LastMaintenanceModel2, LastMaintenanceModel3, LastMaintenanceModel4, LastMaintenanceModel5, LastMaintenanceModel6, LastMaintenanceModel7, LastMaintenanceModel8;
    static JDatePanelImpl LastMaintenanceDatePanel1, LastMaintenanceDatePanel2, LastMaintenanceDatePanel3, LastMaintenanceDatePanel4, LastMaintenanceDatePanel5, LastMaintenanceDatePanel6, LastMaintenanceDatePanel7, LastMaintenanceDatePanel8;
    static JDatePickerImpl LastMaintenanceDatePicker1, LastMaintenanceDatePicker2, LastMaintenanceDatePicker3, LastMaintenanceDatePicker4, LastMaintenanceDatePicker5, LastMaintenanceDatePicker6, LastMaintenanceDatePicker7, LastMaintenanceDatePicker8;

    static UtilDateModel MaintenanceDue1, MaintenanceDue2, MaintenanceDue3, MaintenanceDue4, MaintenanceDue5, MaintenanceDue6, MaintenanceDue7;
    static JDatePanelImpl MaintenanceDueDatePanel1, MaintenanceDueDatePanel2, MaintenanceDueDatePanel3, MaintenanceDueDatePanel4, MaintenanceDueDatePanel5, MaintenanceDueDatePanel6, MaintenanceDueDatePanel7;
    static JDatePickerImpl MaintenanceDueDatePicker1, MaintenanceDueDatePicker2, MaintenanceDueDatePicker3, MaintenanceDueDatePicker4, MaintenanceDueDatePicker5, MaintenanceDueDatePicker6, MaintenanceDueDatePickerTotal;

    private static int currentId;

    public static void main(String[] args) {

        try {
            new ShellPressMaintenance(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ShellPressMaintenance(int spIn) throws SQLException {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }
        
        

        JFrame frame = new JFrame("Shell Press Maintenance");
        frame.setSize(1400, 450);
        frame.setLocationRelativeTo(null);

        outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
        outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
        outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);
        
        setToMachineCode(1);

        frame.add(outerPanel);
        frame.setVisible(true);

    }

    private static JPanel createOptionsPanel(int spIn) {

        optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        SP01 = new JButton("SP01");
        SP01.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
            }
        });
        SP02 = new JButton("SP02");
        SP02.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
            }
        });
        SP03 = new JButton("SP03");
        SP03.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
            }
        });
        SP04 = new JButton("SP04");
        SP04.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToSP04();
            }
        });

        optionsPanel.add(SP01);
        optionsPanel.add(SP02);
        optionsPanel.add(SP03);
        optionsPanel.add(SP04);

        return optionsPanel;

    }

    private static JPanel createBottomPanel(int spIn) {

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (MachineCodeJTexField1.getText().equals("SP01")) {
                    updateEntry(1);
                }
                if (MachineCodeJTexField1.getText().equals("SP02")) {
                    updateEntry(2);
                }
                if (MachineCodeJTexField1.getText().equals("SP03")) {
                    updateEntry(3);
                }
                if (MachineCodeJTexField1.getText().equals("SP04")) {
                    updateEntry(4);
                }

            }
        });

        bottomPanel.add(update);

        return bottomPanel;

    }

    private static JPanel createMiddlePanel(int spIn) {

        toolingAreaLabel1 = new JLabel("Form 1-6", JLabel.CENTER);
        toolingAreaLabel2 = new JLabel("Form 7-12", JLabel.CENTER);
        toolingAreaLabel3 = new JLabel("Form 13-18", JLabel.CENTER);
        toolingAreaLabel4 = new JLabel("Form 19-24", JLabel.CENTER);
        toolingAreaLabel5 = new JLabel("Curl 1-12", JLabel.CENTER);
        toolingAreaLabel6 = new JLabel("Curl 13-24", JLabel.CENTER);

        MaintenanceTypeLabel1 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel2 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel3 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel4 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel5 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel6 = new JLabel("12 Hour", JLabel.CENTER);
        MaintenanceTypeLabel7 = new JLabel("Tri-Annual", JLabel.CENTER);

        LastMaintenanceModel1 = new UtilDateModel();
        LastMaintenanceDatePanel1 = new JDatePanelImpl(LastMaintenanceModel1);
        LastMaintenanceDatePicker1 = new JDatePickerImpl(LastMaintenanceDatePanel1);

        LastMaintenanceModel2 = new UtilDateModel();
        LastMaintenanceDatePanel2 = new JDatePanelImpl(LastMaintenanceModel2);
        LastMaintenanceDatePicker2 = new JDatePickerImpl(LastMaintenanceDatePanel2);

        LastMaintenanceModel3 = new UtilDateModel();
        LastMaintenanceDatePanel3 = new JDatePanelImpl(LastMaintenanceModel3);
        LastMaintenanceDatePicker3 = new JDatePickerImpl(LastMaintenanceDatePanel3);

        LastMaintenanceModel4 = new UtilDateModel();
        LastMaintenanceDatePanel4 = new JDatePanelImpl(LastMaintenanceModel4);
        LastMaintenanceDatePicker4 = new JDatePickerImpl(LastMaintenanceDatePanel4);

        LastMaintenanceModel5 = new UtilDateModel();
        LastMaintenanceDatePanel5 = new JDatePanelImpl(LastMaintenanceModel5);
        LastMaintenanceDatePicker5 = new JDatePickerImpl(LastMaintenanceDatePanel5);

        LastMaintenanceModel6 = new UtilDateModel();
        LastMaintenanceDatePanel6 = new JDatePanelImpl(LastMaintenanceModel6);
        LastMaintenanceDatePicker6 = new JDatePickerImpl(LastMaintenanceDatePanel6);

        LastMaintenanceModel7 = new UtilDateModel();
        LastMaintenanceDatePanel7 = new JDatePanelImpl(LastMaintenanceModel7);
        LastMaintenanceDatePicker7 = new JDatePickerImpl(LastMaintenanceDatePanel7);

        MaintenanceDue1 = new UtilDateModel();
        MaintenanceDueDatePanel1 = new JDatePanelImpl(MaintenanceDue1);
        MaintenanceDueDatePicker1 = new JDatePickerImpl(MaintenanceDueDatePanel1);

        MaintenanceDue2 = new UtilDateModel();
        MaintenanceDueDatePanel2 = new JDatePanelImpl(MaintenanceDue2);
        MaintenanceDueDatePicker2 = new JDatePickerImpl(MaintenanceDueDatePanel2);

        MaintenanceDue3 = new UtilDateModel();
        MaintenanceDueDatePanel3 = new JDatePanelImpl(MaintenanceDue3);
        MaintenanceDueDatePicker3 = new JDatePickerImpl(MaintenanceDueDatePanel3);

        MaintenanceDue4 = new UtilDateModel();
        MaintenanceDueDatePanel4 = new JDatePanelImpl(MaintenanceDue4);
        MaintenanceDueDatePicker4 = new JDatePickerImpl(MaintenanceDueDatePanel4);

        MaintenanceDue5 = new UtilDateModel();
        MaintenanceDueDatePanel5 = new JDatePanelImpl(MaintenanceDue5);
        MaintenanceDueDatePicker5 = new JDatePickerImpl(MaintenanceDueDatePanel5);

        MaintenanceDue6 = new UtilDateModel();
        MaintenanceDueDatePanel6 = new JDatePanelImpl(MaintenanceDue6);
        MaintenanceDueDatePicker6 = new JDatePickerImpl(MaintenanceDueDatePanel6);

        MaintenanceDue7 = new UtilDateModel();
        MaintenanceDueDatePanel7 = new JDatePanelImpl(MaintenanceDue7);
        MaintenanceDueDatePickerTotal = new JDatePickerImpl(MaintenanceDueDatePanel7);

        JPanel middlePanel = new JPanel(new GridLayout(8, 10));
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // middlePanel.setBackground(Color.WHITE);

        // ROW1
        middlePanel.add(new JLabel("Machine Code", JLabel.CENTER));
        middlePanel.add(new JLabel("Machine Name", JLabel.CENTER));
        middlePanel.add(new JLabel("Tooling Area", JLabel.CENTER));
        middlePanel.add(new JLabel("Last Maintenance", JLabel.CENTER));
        middlePanel.add(new JLabel("Maintenance Type", JLabel.CENTER));
        middlePanel.add(new JLabel("Target Production", JLabel.CENTER));
        middlePanel.add(new JLabel("Production", JLabel.CENTER));
        middlePanel.add(new JLabel("+ / -", JLabel.CENTER));
        middlePanel.add(new JLabel("Maintenance Due Date", JLabel.CENTER));
        middlePanel.add(new JLabel("Days Remaining", JLabel.CENTER));

        // ROW2   
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setEditable(false);
        MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
        MachineNameJTexField1 = new JTextField(" ");
        MachineNameJTexField1.setEditable(false);
        MachineNameJTexField1.setBackground(new Color(255, 255, 123));
        TargetProductionJTexField1 = new JTextField();
        ProductionJTexField1 = new JTextField();
        PlusMinusJTexField1 = new JTextField();
        MaintenanceDueDateJTexField1 = new JTextField();
        DaysRemainingJTexField1 = new JTextField();

        middlePanel.add(MachineCodeJTexField1);
        middlePanel.add(MachineNameJTexField1);
        middlePanel.add(toolingAreaLabel1);
        middlePanel.add(LastMaintenanceDatePicker1);
        middlePanel.add(MaintenanceTypeLabel1);
        middlePanel.add(TargetProductionJTexField1);
        middlePanel.add(ProductionJTexField1);
        middlePanel.add(PlusMinusJTexField1);
        middlePanel.add(MaintenanceDueDatePicker1);
        middlePanel.add(DaysRemainingJTexField1);

        // ROW3
        TargetProductionJTexField2 = new JTextField();
        ProductionJTexField2 = new JTextField();
        PlusMinusJTexField2 = new JTextField();
        MaintenanceDueDateJTexField2 = new JTextField();
        DaysRemainingJTexField2 = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(toolingAreaLabel2);
        middlePanel.add(LastMaintenanceDatePicker2);
        middlePanel.add(MaintenanceTypeLabel2);
        middlePanel.add(TargetProductionJTexField2);
        middlePanel.add(ProductionJTexField2);
        middlePanel.add(PlusMinusJTexField2);
        middlePanel.add(MaintenanceDueDatePicker2);
        middlePanel.add(DaysRemainingJTexField2);

        // ROW4
        TargetProductionJTexField3 = new JTextField();
        ProductionJTexField3 = new JTextField();
        PlusMinusJTexField3 = new JTextField();
        MaintenanceDueDateJTexField3 = new JTextField();
        DaysRemainingJTexField3 = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(toolingAreaLabel3);
        middlePanel.add(LastMaintenanceDatePicker3);
        middlePanel.add(MaintenanceTypeLabel3);
        middlePanel.add(TargetProductionJTexField3);
        middlePanel.add(ProductionJTexField3);
        middlePanel.add(PlusMinusJTexField3);
        middlePanel.add(MaintenanceDueDatePicker3);
        middlePanel.add(DaysRemainingJTexField3);
        // ROW5
        TargetProductionJTexField4 = new JTextField();
        ProductionJTexField4 = new JTextField();
        PlusMinusJTexField4 = new JTextField();
        MaintenanceDueDateJTexField4 = new JTextField();
        DaysRemainingJTexField4 = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(toolingAreaLabel4);
        middlePanel.add(LastMaintenanceDatePicker4);
        middlePanel.add(MaintenanceTypeLabel4);
        middlePanel.add(TargetProductionJTexField4);
        middlePanel.add(ProductionJTexField4);
        middlePanel.add(PlusMinusJTexField4);
        middlePanel.add(MaintenanceDueDatePicker4);
        middlePanel.add(DaysRemainingJTexField4);
        // ROW6
        TargetProductionJTexField5 = new JTextField();
        ProductionJTexField5 = new JTextField();
        PlusMinusJTexField5 = new JTextField();
        MaintenanceDueDateJTexField5 = new JTextField();
        DaysRemainingJTexField5 = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(toolingAreaLabel5);
        middlePanel.add(LastMaintenanceDatePicker5);
        middlePanel.add(MaintenanceTypeLabel5);
        middlePanel.add(TargetProductionJTexField5);
        middlePanel.add(ProductionJTexField5);
        middlePanel.add(PlusMinusJTexField5);
        middlePanel.add(MaintenanceDueDatePicker5);
        middlePanel.add(DaysRemainingJTexField5);

        // ROW7
        TargetProductionJTexField6 = new JTextField();
        ProductionJTexField6 = new JTextField();
        PlusMinusJTexField6 = new JTextField();
        MaintenanceDueDateJTexField6 = new JTextField();
        DaysRemainingJTexField6 = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(toolingAreaLabel6);
        middlePanel.add(LastMaintenanceDatePicker6);
        middlePanel.add(MaintenanceTypeLabel6);
        middlePanel.add(TargetProductionJTexField6);
        middlePanel.add(ProductionJTexField6);
        middlePanel.add(PlusMinusJTexField6);
        middlePanel.add(MaintenanceDueDatePicker6);
        middlePanel.add(DaysRemainingJTexField6);

        // ROW8 
        TargetProductionJTexFieldTotal = new JTextField();
        ProductionJTexFieldTotal = new JTextField();
        PlusMinusJTexFieldTotal = new JTextField();
        MaintenanceDueDateJTexFieldTotal = new JTextField();
        DaysRemainingJTexFieldTotal = new JTextField();

        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" "));
        middlePanel.add(new JLabel(" ", JLabel.CENTER));
        middlePanel.add(LastMaintenanceDatePicker7);
        middlePanel.add(MaintenanceTypeLabel7);
        middlePanel.add(TargetProductionJTexFieldTotal);
        middlePanel.add(ProductionJTexFieldTotal);
        middlePanel.add(PlusMinusJTexFieldTotal);
        middlePanel.add(MaintenanceDueDatePickerTotal);
        middlePanel.add(DaysRemainingJTexFieldTotal);

        return middlePanel;
    }

    private static void setToMachineCode(int machineCodeIn) {

        TargetProductionJTexField3.setVisible(true);
        ProductionJTexField3.setVisible(true);
        PlusMinusJTexField3.setVisible(true);
        MaintenanceDueDateJTexField3.setVisible(true);
        DaysRemainingJTexField3.setVisible(true);

        TargetProductionJTexField4.setVisible(true);
        ProductionJTexField4.setVisible(true);
        PlusMinusJTexField4.setVisible(true);
        MaintenanceDueDateJTexField4.setVisible(true);
        DaysRemainingJTexField4.setVisible(true);

        TargetProductionJTexField5.setVisible(true);
        ProductionJTexField5.setVisible(true);
        PlusMinusJTexField5.setVisible(true);
        MaintenanceDueDateJTexField5.setVisible(true);
        DaysRemainingJTexField5.setVisible(true);

        TargetProductionJTexField6.setVisible(true);
        ProductionJTexField6.setVisible(true);
        PlusMinusJTexField6.setVisible(true);
        MaintenanceDueDateJTexField6.setVisible(true);
        DaysRemainingJTexField6.setVisible(true);

        LastMaintenanceDatePicker3.setVisible(true);
        LastMaintenanceDatePicker4.setVisible(true);
        LastMaintenanceDatePicker5.setVisible(true);
        LastMaintenanceDatePicker6.setVisible(true);

        MaintenanceDueDatePicker3.setVisible(true);
        MaintenanceDueDatePicker4.setVisible(true);
        MaintenanceDueDatePicker5.setVisible(true);
        MaintenanceDueDatePicker6.setVisible(true);

        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);
        toolingAreaLabel5.setVisible(true);
        toolingAreaLabel6.setVisible(true);

        MaintenanceTypeLabel3.setVisible(true);
        MaintenanceTypeLabel4.setVisible(true);
        MaintenanceTypeLabel5.setVisible(true);
        MaintenanceTypeLabel6.setVisible(true);

        toolingAreaLabel1.setText("Form 1-6");
        toolingAreaLabel2.setText("Form 7-12");
        toolingAreaLabel3.setText("Form 13-18");
        toolingAreaLabel4.setText("Form 19-24");
        toolingAreaLabel5.setText("Curl 1-12");
        toolingAreaLabel6.setText("Curl 13-24");

        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);
        toolingAreaLabel5.setVisible(true);
        toolingAreaLabel6.setVisible(true);

        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[50];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceShellPressMaintenanceReturnEntryByID(machineCodeIn);

            int LastMaintenanceDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
            int LastMaintenanceDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));
            int LastMaintenanceDate2year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
            int LastMaintenanceDate2month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate2day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));
            int LastMaintenanceDate3year = Integer.valueOf((machineCodeArray[5] + "").substring(0, 4));
            int LastMaintenanceDate3month = Integer.valueOf((machineCodeArray[5] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate3day = Integer.valueOf((machineCodeArray[5] + "").substring(8, 10));
            int LastMaintenanceDate4year = Integer.valueOf((machineCodeArray[6] + "").substring(0, 4));
            int LastMaintenanceDate4month = Integer.valueOf((machineCodeArray[6] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate4day = Integer.valueOf((machineCodeArray[6] + "").substring(8, 10));
            int LastMaintenanceDate5year = Integer.valueOf((machineCodeArray[7] + "").substring(0, 4));
            int LastMaintenanceDate5month = Integer.valueOf((machineCodeArray[7] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate5day = Integer.valueOf((machineCodeArray[7] + "").substring(8, 10));
            int LastMaintenanceDate6year = Integer.valueOf((machineCodeArray[8] + "").substring(0, 4));
            int LastMaintenanceDate6month = Integer.valueOf((machineCodeArray[8] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate6day = Integer.valueOf((machineCodeArray[8] + "").substring(8, 10));
            int LastMaintenanceDate7year = Integer.valueOf((machineCodeArray[9] + "").substring(0, 4));
            int LastMaintenanceDate7month = Integer.valueOf((machineCodeArray[9] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate7day = Integer.valueOf((machineCodeArray[9] + "").substring(8, 10));

            int MaintenanceDueDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
            int MaintenanceDueDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));
            int MaintenanceDueDate2year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
            int MaintenanceDueDate2month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate2day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));
            int MaintenanceDueDate3year = Integer.valueOf((machineCodeArray[5] + "").substring(0, 4));
            int MaintenanceDueDate3month = Integer.valueOf((machineCodeArray[5] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate3day = Integer.valueOf((machineCodeArray[5] + "").substring(8, 10));
            int MaintenanceDueDate4year = Integer.valueOf((machineCodeArray[6] + "").substring(0, 4));
            int MaintenanceDueDate4month = Integer.valueOf((machineCodeArray[6] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate4day = Integer.valueOf((machineCodeArray[6] + "").substring(8, 10));
            int MaintenanceDueDate5year = Integer.valueOf((machineCodeArray[7] + "").substring(0, 4));
            int MaintenanceDueDate5month = Integer.valueOf((machineCodeArray[7] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate5day = Integer.valueOf((machineCodeArray[7] + "").substring(8, 10));
            int MaintenanceDueDate6year = Integer.valueOf((machineCodeArray[8] + "").substring(0, 4));
            int MaintenanceDueDate6month = Integer.valueOf((machineCodeArray[8] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate6day = Integer.valueOf((machineCodeArray[8] + "").substring(8, 10));
            int MaintenanceDueDate7year = Integer.valueOf((machineCodeArray[9] + "").substring(0, 4));
            int MaintenanceDueDate7month = Integer.valueOf((machineCodeArray[9] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate7day = Integer.valueOf((machineCodeArray[9] + "").substring(8, 10));

            currentId = (int) machineCodeArray[0];

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            MachineNameJTexField1.setText(machineCodeArray[2] + "");
            LastMaintenanceModel1.setDate(LastMaintenanceDate1year, LastMaintenanceDate1month, LastMaintenanceDate1day);
            LastMaintenanceModel1.setSelected(true);
            TargetProductionJTexField1.setText(machineCodeArray[10] + "");
            ProductionJTexField1.setText(machineCodeArray[17] + "");
            PlusMinusJTexField1.setText(machineCodeArray[24] + "");
            MaintenanceDue1.setDate(MaintenanceDueDate1year, MaintenanceDueDate1month, MaintenanceDueDate1day);
            MaintenanceDue1.setSelected(true);
            DaysRemainingJTexField1.setText(machineCodeArray[38] + "");

            LastMaintenanceModel2.setDate(LastMaintenanceDate2year, LastMaintenanceDate2month, LastMaintenanceDate2day);
            LastMaintenanceModel2.setSelected(true);
            TargetProductionJTexField2.setText(machineCodeArray[11] + "");
            ProductionJTexField2.setText(machineCodeArray[18] + "");
            PlusMinusJTexField2.setText(machineCodeArray[25] + "");
            MaintenanceDue2.setDate(MaintenanceDueDate2year, MaintenanceDueDate2month, MaintenanceDueDate2day);
            MaintenanceDue2.setSelected(true);
            DaysRemainingJTexField2.setText(machineCodeArray[39] + "");

            LastMaintenanceModel3.setDate(LastMaintenanceDate3year, LastMaintenanceDate3month, LastMaintenanceDate3day);
            LastMaintenanceModel3.setSelected(true);
            TargetProductionJTexField3.setText(machineCodeArray[12] + "");
            ProductionJTexField3.setText(machineCodeArray[19] + "");
            PlusMinusJTexField3.setText(machineCodeArray[26] + "");
            MaintenanceDue3.setDate(MaintenanceDueDate3year, MaintenanceDueDate3month, MaintenanceDueDate3day);
            MaintenanceDue3.setSelected(true);
            DaysRemainingJTexField3.setText(machineCodeArray[40] + "");

            LastMaintenanceModel4.setDate(LastMaintenanceDate4year, LastMaintenanceDate4month, LastMaintenanceDate4day);
            LastMaintenanceModel4.setSelected(true);
            TargetProductionJTexField4.setText(machineCodeArray[13] + "");
            ProductionJTexField4.setText(machineCodeArray[20] + "");
            PlusMinusJTexField4.setText(machineCodeArray[27] + "");
            MaintenanceDue4.setDate(MaintenanceDueDate4year, MaintenanceDueDate4month, MaintenanceDueDate4day);
            MaintenanceDue4.setSelected(true);
            DaysRemainingJTexField4.setText(machineCodeArray[41] + "");

            LastMaintenanceModel5.setDate(LastMaintenanceDate5year, LastMaintenanceDate5month, LastMaintenanceDate5day);
            LastMaintenanceModel5.setSelected(true);
            TargetProductionJTexField5.setText(machineCodeArray[14] + "");
            ProductionJTexField5.setText(machineCodeArray[21] + "");
            PlusMinusJTexField5.setText(machineCodeArray[28] + "");
            MaintenanceDue5.setDate(MaintenanceDueDate5year, MaintenanceDueDate5month, MaintenanceDueDate5day);
            MaintenanceDue5.setSelected(true);
            DaysRemainingJTexField5.setText(machineCodeArray[42] + "");

            LastMaintenanceModel6.setDate(LastMaintenanceDate6year, LastMaintenanceDate6month, LastMaintenanceDate6day);
            LastMaintenanceModel6.setSelected(true);
            TargetProductionJTexField6.setText(machineCodeArray[15] + "");
            ProductionJTexField6.setText(machineCodeArray[22] + "");
            PlusMinusJTexField6.setText(machineCodeArray[29] + "");
            MaintenanceDue6.setDate(MaintenanceDueDate6year, MaintenanceDueDate6month, MaintenanceDueDate6day);
            MaintenanceDue6.setSelected(true);
            DaysRemainingJTexField6.setText(machineCodeArray[43] + "");

            LastMaintenanceModel7.setDate(LastMaintenanceDate7year, LastMaintenanceDate7month, LastMaintenanceDate7day);
            LastMaintenanceModel7.setSelected(true);
            TargetProductionJTexFieldTotal.setText(machineCodeArray[16] + "");
            ProductionJTexFieldTotal.setText(machineCodeArray[23] + "");
            PlusMinusJTexFieldTotal.setText(machineCodeArray[30] + "");
            MaintenanceDue7.setDate(MaintenanceDueDate7year, MaintenanceDueDate7month, MaintenanceDueDate7day);
            MaintenanceDue7.setSelected(true);
            DaysRemainingJTexFieldTotal.setText(machineCodeArray[44] + "");
        } catch (Exception ex) {
            Logger.getLogger(ShellPressMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void setToSP04() {

        MachineCodeJTexField1.setText("SP04");
        MachineNameJTexField1.setText("Formatec 04");

        toolingAreaLabel1.setText("Zone 1");
        toolingAreaLabel2.setText("Zone 2");

        toolingAreaLabel3.setVisible(false);
        toolingAreaLabel4.setVisible(false);
        toolingAreaLabel5.setVisible(false);
        toolingAreaLabel6.setVisible(false);

        MaintenanceTypeLabel3.setVisible(false);
        MaintenanceTypeLabel4.setVisible(false);
        MaintenanceTypeLabel5.setVisible(false);
        MaintenanceTypeLabel6.setVisible(false);

        TargetProductionJTexField3.setVisible(false);
        ProductionJTexField3.setVisible(false);
        PlusMinusJTexField3.setVisible(false);
        MaintenanceDueDateJTexField3.setVisible(false);
        DaysRemainingJTexField3.setVisible(false);

        TargetProductionJTexField4.setVisible(false);
        ProductionJTexField4.setVisible(false);
        PlusMinusJTexField4.setVisible(false);
        MaintenanceDueDateJTexField4.setVisible(false);
        DaysRemainingJTexField4.setVisible(false);

        TargetProductionJTexField5.setVisible(false);
        ProductionJTexField5.setVisible(false);
        PlusMinusJTexField5.setVisible(false);
        MaintenanceDueDateJTexField5.setVisible(false);
        DaysRemainingJTexField5.setVisible(false);

        TargetProductionJTexField6.setVisible(false);
        ProductionJTexField6.setVisible(false);
        PlusMinusJTexField6.setVisible(false);
        MaintenanceDueDateJTexField6.setVisible(false);
        DaysRemainingJTexField6.setVisible(false);

        LastMaintenanceDatePicker3.setVisible(false);
        LastMaintenanceDatePicker4.setVisible(false);
        LastMaintenanceDatePicker5.setVisible(false);
        LastMaintenanceDatePicker6.setVisible(false);

        MaintenanceDueDatePicker3.setVisible(false);
        MaintenanceDueDatePicker4.setVisible(false);
        MaintenanceDueDatePicker5.setVisible(false);
        MaintenanceDueDatePicker6.setVisible(false);

        optionsPanel.repaint();

    }

    private static void updateEntry(int machineCodeIn) {

        Date LastMaintenanceDate1 = (Date) LastMaintenanceDatePanel1.getModel().getValue();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate1);
        Date LastMaintenanceDate2 = (Date) LastMaintenanceDatePanel2.getModel().getValue();
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate2);
        Date LastMaintenanceDate3 = (Date) LastMaintenanceDatePanel3.getModel().getValue();
        String date3 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate3);
        Date LastMaintenanceDate4 = (Date) LastMaintenanceDatePanel4.getModel().getValue();
        String date4 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate4);
        Date LastMaintenanceDate5 = (Date) LastMaintenanceDatePanel5.getModel().getValue();
        String date5 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate5);
        Date LastMaintenanceDate6 = (Date) LastMaintenanceDatePanel6.getModel().getValue();
        String date6 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate6);
        Date LastMaintenanceDate7 = (Date) LastMaintenanceDatePanel7.getModel().getValue();
        String date7 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate7);

        Date MaintenanceDueDate1 = (Date) MaintenanceDueDatePicker1.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate1);
        Date MaintenanceDueDate2 = (Date) MaintenanceDueDatePicker2.getModel().getValue();
        String date9 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate2);
        Date MaintenanceDueDate3 = (Date) MaintenanceDueDatePicker3.getModel().getValue();
        String date10 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate3);
        Date MaintenanceDueDate4 = (Date) MaintenanceDueDatePicker4.getModel().getValue();
        String date11 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate4);
        Date MaintenanceDueDate5 = (Date) MaintenanceDueDatePicker5.getModel().getValue();
        String date12 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate5);
        Date MaintenanceDueDate6 = (Date) MaintenanceDueDatePicker6.getModel().getValue();
        String date13 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate6);
        Date MaintenanceDueDate7 = (Date) MaintenanceDueDatePickerTotal.getModel().getValue();
        String date14 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate7);

        try {
            SQLiteConnection.MaintenanceShellPressMaintenanceUpdate(
                    MachineCodeJTexField1.getText(),
                    MachineNameJTexField1.getText(),
                    date1,
                    date2,
                    date3,
                    date4,
                    date5,
                    date6,
                    date7,
                    Integer.valueOf(TargetProductionJTexField1.getText()),
                    Integer.valueOf(TargetProductionJTexField2.getText()),
                    Integer.valueOf(TargetProductionJTexField3.getText()),
                    Integer.valueOf(TargetProductionJTexField4.getText()),
                    Integer.valueOf(TargetProductionJTexField5.getText()),
                    Integer.valueOf(TargetProductionJTexField6.getText()),
                    Integer.valueOf(TargetProductionJTexFieldTotal.getText()),
                    Integer.valueOf(ProductionJTexField1.getText()),
                    Integer.valueOf(ProductionJTexField2.getText()),
                    Integer.valueOf(ProductionJTexField3.getText()),
                    Integer.valueOf(ProductionJTexField4.getText()),
                    Integer.valueOf(ProductionJTexField5.getText()),
                    Integer.valueOf(ProductionJTexField6.getText()),
                    Integer.valueOf(ProductionJTexFieldTotal.getText()),
                    Integer.valueOf(PlusMinusJTexField1.getText()),
                    Integer.valueOf(PlusMinusJTexField2.getText()),
                    Integer.valueOf(PlusMinusJTexField3.getText()),
                    Integer.valueOf(PlusMinusJTexField4.getText()),
                    Integer.valueOf(PlusMinusJTexField5.getText()),
                    Integer.valueOf(PlusMinusJTexField6.getText()),
                    Integer.valueOf(PlusMinusJTexFieldTotal.getText()),
                    date8,
                    date9,
                    date10,
                    date11,
                    date12,
                    date13,
                    date14,
                    Integer.valueOf(DaysRemainingJTexField1.getText()),
                    Integer.valueOf(DaysRemainingJTexField2.getText()),
                    Integer.valueOf(DaysRemainingJTexField3.getText()),
                    Integer.valueOf(DaysRemainingJTexField4.getText()),
                    Integer.valueOf(DaysRemainingJTexField5.getText()),
                    Integer.valueOf(DaysRemainingJTexField6.getText()),
                    Integer.valueOf(DaysRemainingJTexFieldTotal.getText()),
                    machineCodeIn
            );
        } catch (SQLException ex) {
            Logger.getLogger(ShellPressMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
