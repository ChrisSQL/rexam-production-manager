package com.maintenance.rexam;

//  Get Excel Totals Correct
import com.database.rexam.SQLiteConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class BalancerMaintenance {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton Balancer1A, Balancer2A, Balancer3A, Balancer4A, Balancer4ANew, Balancer1B, Balancer2B, Balancer3B, Balancer4B, update;
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
    
    static JFrame frame;

    public static void main(String[] args) {

        try {
            new BalancerMaintenance(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public BalancerMaintenance(int spIn) throws SQLException {

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

        frame = new JFrame("Balancer Maintenance");
        frame.setSize(1400, 350);
        frame.setLocationRelativeTo(null);

        outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
        outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
        outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);

        setToMachineCode(1);

        frame.add(outerPanel);
        frame.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("BalancerMaintenance");
        
        PlusMinusJTexField1.setEditable(false);
        PlusMinusJTexField2.setEditable(false);
        PlusMinusJTexField3.setEditable(false);
        PlusMinusJTexField4.setEditable(false);
        PlusMinusJTexFieldTotal.setEditable(false);
        
        

    }

    private static JPanel createOptionsPanel(int spIn) {

        optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        Balancer1A = new JButton("BAL 1A");
        Balancer1A.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
                updateDatesToBal1A();
            }
        });
        Balancer2A = new JButton("BAL 2A");
        Balancer2A.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
                updateDatesToBal1A();
            }
        });
        Balancer3A = new JButton("BAL 3A");
        Balancer3A.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
                updateDatesToBal3A();
            }
        });
        Balancer4A = new JButton("BAL 4A");
        Balancer4A.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(4);
                updateDatesToBal4A();
            }
        });
        Balancer4ANew = new JButton("BAL 4A New");
        Balancer4ANew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(5);
            }
        });
        Balancer1B = new JButton("BAL 1B");
        Balancer1B.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(6);
                updateDatesToBal1B();
            }
        });
        Balancer2B = new JButton("BAL 2B");
        Balancer2B.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(7);
                updateDatesToBal1B();
            }
        });
        Balancer3B = new JButton("BAL 3B");
        Balancer3B.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(8);
                updateDatesToBal3B();
            }
        });
        Balancer4B = new JButton("BAL 4B");
        Balancer4B.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(9);
                updateDatesToBal3B();
            }
        });

        optionsPanel.add(Balancer1A);
        optionsPanel.add(Balancer2A);
        optionsPanel.add(Balancer3A);
        optionsPanel.add(Balancer4A);
        optionsPanel.add(Balancer4ANew);
        optionsPanel.add(Balancer1B);
        optionsPanel.add(Balancer2B);
        optionsPanel.add(Balancer3B);
        optionsPanel.add(Balancer4B);

        return optionsPanel;

    }

    private static JPanel createBottomPanel(int spIn) {

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.print(MachineCodeJTexField1.getText() + "");

                if (MachineCodeJTexField1.getText().equals("BAL1A")) {
                    updateEntry(1);
                    currentId = 1;

                }
                if (MachineCodeJTexField1.getText().equals("BAL2A")) {
                    updateEntry(2);
                    currentId = 2;
                }
                if (MachineCodeJTexField1.getText().equals("BAL3A")) {
                    updateEntry(3);
                    currentId = 3;
                }
                if (MachineCodeJTexField1.getText().equals("BAL4A")) {
                    updateEntry(4);
                    currentId = 4;
                }
                if (MachineCodeJTexField1.getText().equals("BAL4ANEW")) {
                    updateEntry(5);
                    currentId = 5;
                }
                if (MachineCodeJTexField1.getText().equals("BAL1B")) {
                    updateEntry(6);
                    currentId = 6;
                }
                if (MachineCodeJTexField1.getText().equals("BAL2B")) {
                    updateEntry(7);
                    currentId = 7;
                }
                if (MachineCodeJTexField1.getText().equals("BAL3B")) {
                    updateEntry(8);
                    currentId = 8;
                }
                if (MachineCodeJTexField1.getText().equals("BAL4B")) {
                    updateEntry(9);
                    currentId = 9;
                }

                setToMachineCode(currentId);

            }
            
            
            
        });
        
        
        

        bottomPanel.add(update);

        return bottomPanel;

    }

    private static JPanel createMiddlePanel(int spIn) {

        toolingAreaLabel1 = new JLabel("Infeed", JLabel.CENTER);
        toolingAreaLabel2 = new JLabel("OutFeed", JLabel.CENTER);
        toolingAreaLabel3 = new JLabel("PickUp Heads", JLabel.CENTER);
        toolingAreaLabel4 = new JLabel("Lifts / Tray Movement", JLabel.CENTER);
        toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

        MaintenanceTypeLabel1 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel2 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel3 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel4 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);

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

        MaintenanceDue7 = new UtilDateModel();
        MaintenanceDueDatePanel7 = new JDatePanelImpl(MaintenanceDue7);
        MaintenanceDueDatePickerTotal = new JDatePickerImpl(MaintenanceDueDatePanel7);

        JPanel middlePanel = new JPanel(new GridLayout(6, 10));
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

        LastMaintenanceDatePicker3.setVisible(true);
        LastMaintenanceDatePicker4.setVisible(true);

        MaintenanceDueDatePicker3.setVisible(true);
        MaintenanceDueDatePicker4.setVisible(true);

        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);

        MaintenanceTypeLabel3.setVisible(true);
        MaintenanceTypeLabel4.setVisible(true);

        toolingAreaLabel1 = new JLabel("Infeed", JLabel.CENTER);
        toolingAreaLabel2 = new JLabel("OutFeed", JLabel.CENTER);
        toolingAreaLabel3 = new JLabel("PickUp Heads", JLabel.CENTER);
        toolingAreaLabel4 = new JLabel("Lifts / Tray Movement", JLabel.CENTER);
        toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

        MaintenanceTypeLabel1 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel2 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel3 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel4 = new JLabel("6 Hour", JLabel.CENTER);
        MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);

        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);

        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[50];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceBalancerMaintenanceReturnEntryByID(machineCodeIn);

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
            int LastMaintenanceDate7year = Integer.valueOf((machineCodeArray[7] + "").substring(0, 4));
            int LastMaintenanceDate7month = Integer.valueOf((machineCodeArray[7] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate7day = Integer.valueOf((machineCodeArray[7] + "").substring(8, 10));

            int MaintenanceDueDate1year = Integer.valueOf((machineCodeArray[23] + "").substring(0, 4));
            int MaintenanceDueDate1month = Integer.valueOf((machineCodeArray[23] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate1day = Integer.valueOf((machineCodeArray[23] + "").substring(8, 10));
            int MaintenanceDueDate2year = Integer.valueOf((machineCodeArray[24] + "").substring(0, 4));
            int MaintenanceDueDate2month = Integer.valueOf((machineCodeArray[24] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate2day = Integer.valueOf((machineCodeArray[24] + "").substring(8, 10));
            int MaintenanceDueDate3year = Integer.valueOf((machineCodeArray[25] + "").substring(0, 4));
            int MaintenanceDueDate3month = Integer.valueOf((machineCodeArray[25] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate3day = Integer.valueOf((machineCodeArray[25] + "").substring(8, 10));
            int MaintenanceDueDate4year = Integer.valueOf((machineCodeArray[26] + "").substring(0, 4));
            int MaintenanceDueDate4month = Integer.valueOf((machineCodeArray[26] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate4day = Integer.valueOf((machineCodeArray[26] + "").substring(8, 10));
            int MaintenanceDueDate7year = Integer.valueOf((machineCodeArray[27] + "").substring(0, 4));
            int MaintenanceDueDate7month = Integer.valueOf((machineCodeArray[27] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate7day = Integer.valueOf((machineCodeArray[27] + "").substring(8, 10));

            currentId = (int) machineCodeArray[0];

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            MachineNameJTexField1.setText(machineCodeArray[2] + "");
            LastMaintenanceModel1.setDate(LastMaintenanceDate1year, LastMaintenanceDate1month, LastMaintenanceDate1day);
            LastMaintenanceModel1.setSelected(true);
            TargetProductionJTexField1.setText(machineCodeArray[8] + "");
            ProductionJTexField1.setText(machineCodeArray[13] + "");
            PlusMinusJTexField1.setText(((int) machineCodeArray[8] - (int) machineCodeArray[13]) + "");
            MaintenanceDue1.setDate(MaintenanceDueDate1year, MaintenanceDueDate1month, MaintenanceDueDate1day);
            MaintenanceDue1.setSelected(true);
            DaysRemainingJTexField1.setText(machineCodeArray[28] + "");

            LastMaintenanceModel2.setDate(LastMaintenanceDate2year, LastMaintenanceDate2month, LastMaintenanceDate2day);
            LastMaintenanceModel2.setSelected(true);
            TargetProductionJTexField2.setText(machineCodeArray[9] + "");
            ProductionJTexField2.setText(machineCodeArray[14] + "");
            PlusMinusJTexField2.setText(((int) machineCodeArray[9] - (int) machineCodeArray[14]) + "");
            MaintenanceDue2.setDate(MaintenanceDueDate2year, MaintenanceDueDate2month, MaintenanceDueDate2day);
            MaintenanceDue2.setSelected(true);
            DaysRemainingJTexField2.setText(machineCodeArray[29] + "");

            LastMaintenanceModel3.setDate(LastMaintenanceDate3year, LastMaintenanceDate3month, LastMaintenanceDate3day);
            LastMaintenanceModel3.setSelected(true);
            TargetProductionJTexField3.setText(machineCodeArray[10] + "");
            ProductionJTexField3.setText(machineCodeArray[15] + "");
            PlusMinusJTexField3.setText(((int) machineCodeArray[10] - (int) machineCodeArray[15]) + "");
            MaintenanceDue3.setDate(MaintenanceDueDate3year, MaintenanceDueDate3month, MaintenanceDueDate3day);
            MaintenanceDue3.setSelected(true);
            DaysRemainingJTexField3.setText(machineCodeArray[30] + "");

            LastMaintenanceModel4.setDate(LastMaintenanceDate4year, LastMaintenanceDate4month, LastMaintenanceDate4day);
            LastMaintenanceModel4.setSelected(true);
            TargetProductionJTexField4.setText(machineCodeArray[11] + "");
            ProductionJTexField4.setText(machineCodeArray[16] + "");
            PlusMinusJTexField4.setText(((int) machineCodeArray[11] - (int) machineCodeArray[16]) + "");
            MaintenanceDue4.setDate(MaintenanceDueDate4year, MaintenanceDueDate4month, MaintenanceDueDate4day);
            MaintenanceDue4.setSelected(true);
            DaysRemainingJTexField4.setText(machineCodeArray[31] + "");

            LastMaintenanceModel7.setDate(LastMaintenanceDate7year, LastMaintenanceDate7month, LastMaintenanceDate7day);
            LastMaintenanceModel7.setSelected(true);
            TargetProductionJTexFieldTotal.setText(machineCodeArray[12] + "");
            ProductionJTexFieldTotal.setText(machineCodeArray[17] + "");
            PlusMinusJTexFieldTotal.setText(((int) machineCodeArray[12] - (int) machineCodeArray[17]) + "");
            MaintenanceDue7.setDate(MaintenanceDueDate7year, MaintenanceDueDate7month, MaintenanceDueDate7day);
            MaintenanceDue7.setSelected(true);
            DaysRemainingJTexFieldTotal.setText(machineCodeArray[32] + "");
        } catch (Exception ex) {
            Logger.getLogger(BalancerMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void setToSP04() {

        Object[] machineCodeArray = new Object[50];
        try {
            machineCodeArray = SQLiteConnection.MaintenanceBalancerMaintenanceReturnEntryByID(4);
        } catch (Exception ex) {
            Logger.getLogger(BalancerMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        LastMaintenanceDatePicker3.setVisible(false);
        LastMaintenanceDatePicker4.setVisible(false);

        MaintenanceDueDatePicker3.setVisible(false);
        MaintenanceDueDatePicker4.setVisible(false);

        int LastMaintenanceDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
        int LastMaintenanceDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
        int LastMaintenanceDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));
        int LastMaintenanceDate2year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
        int LastMaintenanceDate2month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
        int LastMaintenanceDate2day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));
        int LastMaintenanceDate7year = Integer.valueOf((machineCodeArray[9] + "").substring(0, 4));
        int LastMaintenanceDate7month = Integer.valueOf((machineCodeArray[9] + "").substring(5, 7)) - 1;
        int LastMaintenanceDate7day = Integer.valueOf((machineCodeArray[9] + "").substring(8, 10));

        int MaintenanceDueDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
        int MaintenanceDueDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
        int MaintenanceDueDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));
        int MaintenanceDueDate2year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
        int MaintenanceDueDate2month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
        int MaintenanceDueDate2day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));
        int MaintenanceDueDate7year = Integer.valueOf((machineCodeArray[9] + "").substring(0, 4));
        int MaintenanceDueDate7month = Integer.valueOf((machineCodeArray[9] + "").substring(5, 7)) - 1;
        int MaintenanceDueDate7day = Integer.valueOf((machineCodeArray[9] + "").substring(8, 10));

        MachineCodeJTexField1.setText(machineCodeArray[1] + "");
        MachineNameJTexField1.setText(machineCodeArray[2] + "");
        LastMaintenanceModel1.setDate(LastMaintenanceDate1year, LastMaintenanceDate1month, LastMaintenanceDate1day);
        LastMaintenanceModel1.setSelected(true);
        TargetProductionJTexField1.setText(machineCodeArray[10] + "");
        ProductionJTexField1.setText(machineCodeArray[17] + "");
        PlusMinusJTexField1.setText(((int) machineCodeArray[10] - (int) machineCodeArray[17]) + "");
        MaintenanceDue1.setDate(MaintenanceDueDate1year, MaintenanceDueDate1month, MaintenanceDueDate1day);
        MaintenanceDue1.setSelected(true);
        DaysRemainingJTexField1.setText(machineCodeArray[38] + "");

        LastMaintenanceModel2.setDate(LastMaintenanceDate2year, LastMaintenanceDate2month, LastMaintenanceDate2day);
        LastMaintenanceModel2.setSelected(true);
        TargetProductionJTexField2.setText(machineCodeArray[11] + "");
        ProductionJTexField2.setText(machineCodeArray[18] + "");
        PlusMinusJTexField2.setText(((int) machineCodeArray[11] - (int) machineCodeArray[18]) + "");
        MaintenanceDue2.setDate(MaintenanceDueDate2year, MaintenanceDueDate2month, MaintenanceDueDate2day);
        MaintenanceDue2.setSelected(true);
        DaysRemainingJTexField2.setText(machineCodeArray[39] + "");

        LastMaintenanceModel7.setDate(LastMaintenanceDate7year, LastMaintenanceDate7month, LastMaintenanceDate7day);
        LastMaintenanceModel7.setSelected(true);
        TargetProductionJTexFieldTotal.setText(machineCodeArray[16] + "");
        ProductionJTexFieldTotal.setText(machineCodeArray[23] + "");
        PlusMinusJTexFieldTotal.setText(((int) machineCodeArray[16] - (int) machineCodeArray[23]) + "");
        MaintenanceDue7.setDate(MaintenanceDueDate7year, MaintenanceDueDate7month, MaintenanceDueDate7day);
        MaintenanceDue7.setSelected(true);
        DaysRemainingJTexFieldTotal.setText(machineCodeArray[44] + "");

        optionsPanel.repaint();

    }

    private static void updateEntry(int machineCodeIn) {

        Date LastMaintenanceDate1 = (Date) LastMaintenanceDatePicker1.getModel().getValue();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate1);
        Date LastMaintenanceDate2 = (Date) LastMaintenanceDatePicker2.getModel().getValue();
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate2);
        Date LastMaintenanceDate3 = (Date) LastMaintenanceDatePicker3.getModel().getValue();
        String date3 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate3);
        Date LastMaintenanceDate4 = (Date) LastMaintenanceDatePicker4.getModel().getValue();
        String date4 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate4);
        Date LastMaintenanceDate7 = (Date) LastMaintenanceDatePicker7.getModel().getValue();
        String date7 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate7);

        Date MaintenanceDueDate1 = (Date) MaintenanceDueDatePicker1.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate1);
        Date MaintenanceDueDate2 = (Date) MaintenanceDueDatePicker2.getModel().getValue();
        String date9 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate2);
        Date MaintenanceDueDate3 = (Date) MaintenanceDueDatePicker3.getModel().getValue();
        String date10 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate3);
        Date MaintenanceDueDate4 = (Date) MaintenanceDueDatePicker4.getModel().getValue();
        String date11 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate4);
        Date MaintenanceDueDate7 = (Date) MaintenanceDueDatePickerTotal.getModel().getValue();
        String date14 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate7);

        try {
            SQLiteConnection.MaintenanceBalancerMaintenanceUpdate(
                    MachineCodeJTexField1.getText(),
                    MachineNameJTexField1.getText(),
                    date1,
                    date2,
                    date3,
                    date4,
                    date7,
                    Long.valueOf(TargetProductionJTexField1.getText()),
                    Long.valueOf(TargetProductionJTexField2.getText()),
                    Long.valueOf(TargetProductionJTexField3.getText()),
                    Long.valueOf(TargetProductionJTexField4.getText()),
                    Long.valueOf(TargetProductionJTexFieldTotal.getText()),
                    Long.valueOf(ProductionJTexField1.getText()),
                    Long.valueOf(ProductionJTexField2.getText()),
                    Long.valueOf(ProductionJTexField3.getText()),
                    Long.valueOf(ProductionJTexField4.getText()),
                    Long.valueOf(ProductionJTexFieldTotal.getText()),
                    Long.valueOf(PlusMinusJTexField1.getText()),
                    Long.valueOf(PlusMinusJTexField2.getText()),
                    Long.valueOf(PlusMinusJTexField3.getText()),
                    Long.valueOf(PlusMinusJTexField4.getText()),
                    Long.valueOf(PlusMinusJTexFieldTotal.getText()),
                    date8,
                    date9,
                    date10,
                    date11,
                    date14,
                    Integer.valueOf(DaysRemainingJTexField1.getText()),
                    Integer.valueOf(DaysRemainingJTexField2.getText()),
                    Integer.valueOf(DaysRemainingJTexField3.getText()),
                    Integer.valueOf(DaysRemainingJTexField4.getText()),
                    Integer.valueOf(DaysRemainingJTexFieldTotal.getText()),
                    machineCodeIn
            );
        } catch (SQLException ex) {
            Logger.getLogger(BalancerMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void updateDatesToBal1A() {
        
        Long daysRemaining1Int, daysRemaining2Int, daysRemaining3Int, daysRemaining4Int, daysRemaining5Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 4147200L);
        daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 4147200L);
        daysRemaining3Int = (Long.valueOf(PlusMinusJTexField3.getText()) / 4147200L);
        daysRemaining4Int = (Long.valueOf(PlusMinusJTexField4.getText()) / 4147200L);
        daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 4147200L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexField2.setText(daysRemaining2Int + "");
        DaysRemainingJTexField3.setText(daysRemaining3Int + "");
        DaysRemainingJTexField4.setText(daysRemaining4Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexField2.setEditable(false);
        DaysRemainingJTexField3.setEditable(false);
        DaysRemainingJTexField4.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days2, days3, days4, days5;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
        days3 = Integer.valueOf(DaysRemainingJTexField3.getText());
        days4 = Integer.valueOf(DaysRemainingJTexField4.getText());
        days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date2 = LastMaintenanceModel2.getValue();
        Date date3 = LastMaintenanceModel3.getValue();
        Date date4 = LastMaintenanceModel4.getValue();
        Date date5 = LastMaintenanceModel7.getValue();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        MaintenanceDue3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c4 = Calendar.getInstance();
        c4.setTime(date4); // Now use today date.
        c4.add(Calendar.DATE, days4); // Adding 5 days
        output1 = sdf1.format(c4.getTime());
        output2 = sdf2.format(c4.getTime());
        output3 = sdf3.format(c4.getTime());
        MaintenanceDue4.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c5 = Calendar.getInstance();
        c5.setTime(date5); // Now use today date.
        c5.add(Calendar.DATE, days5); // Adding 5 days
        output1 = sdf1.format(c5.getTime());
        output2 = sdf2.format(c5.getTime());
        output3 = sdf3.format(c5.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }

    private static void updateDatesToBal3A() {
        
        Long daysRemaining1Int, daysRemaining2Int, daysRemaining3Int, daysRemaining4Int, daysRemaining5Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 829440L);
        daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 829440L);
        daysRemaining3Int = (Long.valueOf(PlusMinusJTexField3.getText()) / 829440L);
        daysRemaining4Int = (Long.valueOf(PlusMinusJTexField4.getText()) / 829440L);
        daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 829440L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexField2.setText(daysRemaining2Int + "");
        DaysRemainingJTexField3.setText(daysRemaining3Int + "");
        DaysRemainingJTexField4.setText(daysRemaining4Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexField2.setEditable(false);
        DaysRemainingJTexField3.setEditable(false);
        DaysRemainingJTexField4.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days2, days3, days4, days5;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
        days3 = Integer.valueOf(DaysRemainingJTexField3.getText());
        days4 = Integer.valueOf(DaysRemainingJTexField4.getText());
        days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date2 = LastMaintenanceModel2.getValue();
        Date date3 = LastMaintenanceModel3.getValue();
        Date date4 = LastMaintenanceModel4.getValue();
        Date date5 = LastMaintenanceModel7.getValue();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        MaintenanceDue3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c4 = Calendar.getInstance();
        c4.setTime(date4); // Now use today date.
        c4.add(Calendar.DATE, days4); // Adding 5 days
        output1 = sdf1.format(c4.getTime());
        output2 = sdf2.format(c4.getTime());
        output3 = sdf3.format(c4.getTime());
        MaintenanceDue4.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c5 = Calendar.getInstance();
        c5.setTime(date5); // Now use today date.
        c5.add(Calendar.DATE, days5); // Adding 5 days
        output1 = sdf1.format(c5.getTime());
        output2 = sdf2.format(c5.getTime());
        output3 = sdf3.format(c5.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }

    private static void updateDatesToBal4A() {
        
        Long daysRemaining1Int, daysRemaining2Int, daysRemaining3Int, daysRemaining4Int, daysRemaining5Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 7753440L);
        daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 7753440L);
        daysRemaining3Int = (Long.valueOf(PlusMinusJTexField3.getText()) / 7753440L);
        daysRemaining4Int = (Long.valueOf(PlusMinusJTexField4.getText()) / 7753440L);
        daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 7753440L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexField2.setText(daysRemaining2Int + "");
        DaysRemainingJTexField3.setText(daysRemaining3Int + "");
        DaysRemainingJTexField4.setText(daysRemaining4Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexField2.setEditable(false);
        DaysRemainingJTexField3.setEditable(false);
        DaysRemainingJTexField4.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days2, days3, days4, days5;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
        days3 = Integer.valueOf(DaysRemainingJTexField3.getText());
        days4 = Integer.valueOf(DaysRemainingJTexField4.getText());
        days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date2 = LastMaintenanceModel2.getValue();
        Date date3 = LastMaintenanceModel3.getValue();
        Date date4 = LastMaintenanceModel4.getValue();
        Date date5 = LastMaintenanceModel7.getValue();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        MaintenanceDue3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c4 = Calendar.getInstance();
        c4.setTime(date4); // Now use today date.
        c4.add(Calendar.DATE, days4); // Adding 5 days
        output1 = sdf1.format(c4.getTime());
        output2 = sdf2.format(c4.getTime());
        output3 = sdf3.format(c4.getTime());
        MaintenanceDue4.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c5 = Calendar.getInstance();
        c5.setTime(date5); // Now use today date.
        c5.add(Calendar.DATE, days5); // Adding 5 days
        output1 = sdf1.format(c5.getTime());
        output2 = sdf2.format(c5.getTime());
        output3 = sdf3.format(c5.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }

    private static void updateDatesToBal1B() {
        
        Long daysRemaining1Int, daysRemaining2Int, daysRemaining3Int, daysRemaining4Int, daysRemaining5Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 4320000L);
        daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 4320000L);
        daysRemaining3Int = (Long.valueOf(PlusMinusJTexField3.getText()) / 4320000L);
        daysRemaining4Int = (Long.valueOf(PlusMinusJTexField4.getText()) / 4320000L);
        daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 4320000L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexField2.setText(daysRemaining2Int + "");
        DaysRemainingJTexField3.setText(daysRemaining3Int + "");
        DaysRemainingJTexField4.setText(daysRemaining4Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexField2.setEditable(false);
        DaysRemainingJTexField3.setEditable(false);
        DaysRemainingJTexField4.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days2, days3, days4, days5;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
        days3 = Integer.valueOf(DaysRemainingJTexField3.getText());
        days4 = Integer.valueOf(DaysRemainingJTexField4.getText());
        days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date2 = LastMaintenanceModel2.getValue();
        Date date3 = LastMaintenanceModel3.getValue();
        Date date4 = LastMaintenanceModel4.getValue();
        Date date5 = LastMaintenanceModel7.getValue();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        MaintenanceDue3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c4 = Calendar.getInstance();
        c4.setTime(date4); // Now use today date.
        c4.add(Calendar.DATE, days4); // Adding 5 days
        output1 = sdf1.format(c4.getTime());
        output2 = sdf2.format(c4.getTime());
        output3 = sdf3.format(c4.getTime());
        MaintenanceDue4.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c5 = Calendar.getInstance();
        c5.setTime(date5); // Now use today date.
        c5.add(Calendar.DATE, days5); // Adding 5 days
        output1 = sdf1.format(c5.getTime());
        output2 = sdf2.format(c5.getTime());
        output3 = sdf3.format(c5.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }

    private static void updateDatesToBal3B() {
        
        Long daysRemaining1Int, daysRemaining2Int, daysRemaining3Int, daysRemaining4Int, daysRemaining5Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 6480000L);
        daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 6480000L);
        daysRemaining3Int = (Long.valueOf(PlusMinusJTexField3.getText()) / 6480000L);
        daysRemaining4Int = (Long.valueOf(PlusMinusJTexField4.getText()) / 6480000L);
        daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 6480000L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexField2.setText(daysRemaining2Int + "");
        DaysRemainingJTexField3.setText(daysRemaining3Int + "");
        DaysRemainingJTexField4.setText(daysRemaining4Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexField2.setEditable(false);
        DaysRemainingJTexField3.setEditable(false);
        DaysRemainingJTexField4.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days2, days3, days4, days5;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
        days3 = Integer.valueOf(DaysRemainingJTexField3.getText());
        days4 = Integer.valueOf(DaysRemainingJTexField4.getText());
        days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date2 = LastMaintenanceModel2.getValue();
        Date date3 = LastMaintenanceModel3.getValue();
        Date date4 = LastMaintenanceModel4.getValue();
        Date date5 = LastMaintenanceModel7.getValue();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        MaintenanceDue3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        
        Calendar c4 = Calendar.getInstance();
        c4.setTime(date4); // Now use today date.
        c4.add(Calendar.DATE, days4); // Adding 5 days
        output1 = sdf1.format(c4.getTime());
        output2 = sdf2.format(c4.getTime());
        output3 = sdf3.format(c4.getTime());
        MaintenanceDue4.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c5 = Calendar.getInstance();
        c5.setTime(date5); // Now use today date.
        c5.add(Calendar.DATE, days5); // Adding 5 days
        output1 = sdf1.format(c5.getTime());
        output2 = sdf2.format(c5.getTime());
        output3 = sdf3.format(c5.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
        

    }

}
