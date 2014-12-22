package com.maintenance.rexam;

//  Get Excel Totals Correct

import com.database.rexam.SQLiteConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class StolleMaintenance {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton W11Button, W12Button,  W21Button, W22Button, W31Button, W32Button, W33Button, W41Button, W42Button, W43Button, update;
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
            new StolleMaintenance(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public StolleMaintenance(int spIn) throws SQLException {

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

        JFrame frame = new JFrame("StolleMaintenance");
        frame.setSize(1400, 250);
        frame.setLocationRelativeTo(null);

        outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
        outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
        outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);

        setToMachineCode(1);

        frame.add(outerPanel);
        frame.setVisible(true);
        
        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("StolleMaintenance");

    }

    private static JPanel createOptionsPanel(int spIn) {

        optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        W11Button = new JButton("W11");
        W11Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
            }
        });
        W12Button = new JButton("W12");
        W12Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
            }
        });       
        W21Button = new JButton("W21");
        W21Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
            }
        });
        W22Button = new JButton("W22");
        W22Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(4);
            }
        });
        W31Button = new JButton("W31");
        W31Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(5);
            }
        });
        W32Button = new JButton("W32");
        W32Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(6);
            }
        });
        W33Button = new JButton("W33");
        W33Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(7);
            }
        });
        W41Button = new JButton("W41");
        W41Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(8);
            }
        });
        W42Button = new JButton("W42");
        W42Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(9);
            }
        });
        W43Button = new JButton("W43");
        W43Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(10);
            }
        });
        
        optionsPanel.add(W11Button);
        optionsPanel.add(W12Button);
        optionsPanel.add(W21Button);
        optionsPanel.add(W22Button);
        optionsPanel.add(W31Button);
        optionsPanel.add(W32Button);
        optionsPanel.add(W33Button);
        optionsPanel.add(W41Button);
        optionsPanel.add(W42Button);
        optionsPanel.add(W43Button);
        
        

        return optionsPanel;

    }

    private static JPanel createBottomPanel(int spIn) {

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                System.out.print(MachineCodeJTexField1.getText()+"");
                

                if (MachineCodeJTexField1.getText().equals("W11")) {
                    updateEntry(1);
                    currentId = 1;
                    
                }
                if (MachineCodeJTexField1.getText().equals("W12")) {
                    updateEntry(2);
                    currentId = 2;
                }
                if (MachineCodeJTexField1.getText().equals("W21")) {
                    updateEntry(3);
                    currentId = 3;
                }
                if (MachineCodeJTexField1.getText().equals("W22")) {
                    updateEntry(4);
                    currentId = 4;
                }
                if (MachineCodeJTexField1.getText().equals("W31")) {
                    updateEntry(5);
                    currentId = 5;
                }
                if (MachineCodeJTexField1.getText().equals("W32")) {
                    updateEntry(6);
                    currentId = 6;
                }
                if (MachineCodeJTexField1.getText().equals("W33")) {
                    updateEntry(7);
                    currentId = 7;
                }
                if (MachineCodeJTexField1.getText().equals("W41")) {
                    updateEntry(8);
                    currentId = 8;
                }
                if (MachineCodeJTexField1.getText().equals("W42")) {
                    updateEntry(9);
                    currentId = 9;
                }
                if (MachineCodeJTexField1.getText().equals("W43")) {
                    updateEntry(10);
                    currentId = 10;
                }                
                
                setToMachineCode(currentId);
                

            }
        });

        bottomPanel.add(update);

        return bottomPanel;

    }

    private static JPanel createMiddlePanel(int spIn) {

        toolingAreaLabel1 = new JLabel(" ", JLabel.CENTER);
        toolingAreaLabel2 = new JLabel(" ", JLabel.CENTER);
        toolingAreaLabel3 = new JLabel(" ", JLabel.CENTER);
        toolingAreaLabel4 = new JLabel(" ", JLabel.CENTER);
        toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

        MaintenanceTypeLabel1 = new JLabel("250 Million", JLabel.CENTER);
        MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);

        LastMaintenanceModel1 = new UtilDateModel();
        LastMaintenanceDatePanel1 = new JDatePanelImpl(LastMaintenanceModel1);
        LastMaintenanceDatePicker1 = new JDatePickerImpl(LastMaintenanceDatePanel1);

        LastMaintenanceModel7 = new UtilDateModel();
        LastMaintenanceDatePanel7 = new JDatePanelImpl(LastMaintenanceModel7);
        LastMaintenanceDatePicker7 = new JDatePickerImpl(LastMaintenanceDatePanel7);

        MaintenanceDue1 = new UtilDateModel();
        MaintenanceDueDatePanel1 = new JDatePanelImpl(MaintenanceDue1);
        MaintenanceDueDatePicker1 = new JDatePickerImpl(MaintenanceDueDatePanel1);      

        MaintenanceDue7 = new UtilDateModel();
        MaintenanceDueDatePanel7 = new JDatePanelImpl(MaintenanceDue7);
        MaintenanceDueDatePickerTotal = new JDatePickerImpl(MaintenanceDueDatePanel7);

        JPanel middlePanel = new JPanel(new GridLayout(3, 10));
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // middlePanel.setBackground(Color.WHITE);

        // ROW1
        middlePanel.add(new JLabel("Machine Code", JLabel.CENTER));
        middlePanel.add(new JLabel("Machine Name", JLabel.CENTER));
        middlePanel.add(new JLabel(" ", JLabel.CENTER));
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

       
        toolingAreaLabel1 = new JLabel(" ", JLabel.CENTER);
        toolingAreaLabel2 = new JLabel(" ", JLabel.CENTER);
        

        MaintenanceTypeLabel1 = new JLabel("250 Million", JLabel.CENTER);             
        MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);


        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);


        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[50];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceStolleMaintenanceReturnEntryByID(machineCodeIn);

            int LastMaintenanceDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
            int LastMaintenanceDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));                    
            int LastMaintenanceDate7year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
            int LastMaintenanceDate7month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate7day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));

            int MaintenanceDueDate1year = Integer.valueOf((machineCodeArray[11] + "").substring(0, 4));
            int MaintenanceDueDate1month = Integer.valueOf((machineCodeArray[11] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate1day = Integer.valueOf((machineCodeArray[11] + "").substring(8, 10));                     
            int MaintenanceDueDate7year = Integer.valueOf((machineCodeArray[12] + "").substring(0, 4));
            int MaintenanceDueDate7month = Integer.valueOf((machineCodeArray[12] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate7day = Integer.valueOf((machineCodeArray[12] + "").substring(8, 10));

            currentId = (int) machineCodeArray[0];

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            MachineNameJTexField1.setText(machineCodeArray[2] + "");
            LastMaintenanceModel1.setDate(LastMaintenanceDate1year, LastMaintenanceDate1month, LastMaintenanceDate1day);
            LastMaintenanceModel1.setSelected(true);
            TargetProductionJTexField1.setText(machineCodeArray[5] + "");
            ProductionJTexField1.setText(machineCodeArray[7] + "");
            PlusMinusJTexField1.setText(((int) machineCodeArray[5] - (int) machineCodeArray[7]) + "");
            MaintenanceDue1.setDate(MaintenanceDueDate1year, MaintenanceDueDate1month, MaintenanceDueDate1day);
            MaintenanceDue1.setSelected(true);
            DaysRemainingJTexField1.setText(machineCodeArray[13] + "");
         

            LastMaintenanceModel7.setDate(LastMaintenanceDate7year, LastMaintenanceDate7month, LastMaintenanceDate7day);
            LastMaintenanceModel7.setSelected(true);
            TargetProductionJTexFieldTotal.setText(machineCodeArray[6] + "");
            ProductionJTexFieldTotal.setText(machineCodeArray[8] + "");
            PlusMinusJTexFieldTotal.setText(((int) machineCodeArray[6] - (int) machineCodeArray[8]) + "");
            MaintenanceDue7.setDate(MaintenanceDueDate7year, MaintenanceDueDate7month, MaintenanceDueDate7day);
            MaintenanceDue7.setSelected(true);
            DaysRemainingJTexFieldTotal.setText(machineCodeArray[14] + "");
            
        } catch (Exception ex) {
            Logger.getLogger(StolleMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        updateDates();

    }

    private static void updateDates() {
        
        Long daysRemaining1Int, daysRemaining7Int;

        daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 2563488L);
        daysRemaining7Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 2563488L);
        
        DaysRemainingJTexField1.setText(daysRemaining1Int + "");
        DaysRemainingJTexFieldTotal.setText(daysRemaining7Int + "");
        
        DaysRemainingJTexField1.setEditable(false);
        DaysRemainingJTexFieldTotal.setEditable(false);
        
        int days1, days7;

        days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
        days7 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
        
        Date date1 = LastMaintenanceModel1.getValue();
        Date date7 = LastMaintenanceModel7.getValue();
        
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

        Calendar c7 = Calendar.getInstance();
        c7.setTime(date7); // Now use today date.
        c7.add(Calendar.DATE, days7); // Adding 5 days
        output1 = sdf1.format(c7.getTime());
        output2 = sdf2.format(c7.getTime());
        output3 = sdf3.format(c7.getTime());
        MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }
    
    private static void updateEntry(int machineCodeIn) {

        Date LastMaintenanceDate1 = (Date) LastMaintenanceDatePicker1.getModel().getValue();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate1);         
        Date LastMaintenanceDate7 = (Date) LastMaintenanceDatePicker7.getModel().getValue();
        String date7 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate7);

        Date MaintenanceDueDate1 = (Date) MaintenanceDueDatePicker1.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate1);    
        Date MaintenanceDueDate7 = (Date) MaintenanceDueDatePickerTotal.getModel().getValue();
        String date14 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate7);

        try {
            SQLiteConnection.MaintenanceStolleMaintenanceUpdate(
                    
                    MachineCodeJTexField1.getText(),
                    MachineNameJTexField1.getText(),
                    
                    date1,
                    date7,
                    
                    Integer.valueOf(TargetProductionJTexField1.getText()),
                    Integer.valueOf(TargetProductionJTexFieldTotal.getText()),
                    
                    Integer.valueOf(ProductionJTexField1.getText()),
                    Integer.valueOf(ProductionJTexFieldTotal.getText()),
                    
                    Integer.valueOf(PlusMinusJTexField1.getText()),
                    Integer.valueOf(PlusMinusJTexFieldTotal.getText()),
                    
                    date8,
                    date14,
                    
                    Integer.valueOf(DaysRemainingJTexField1.getText()),
                    Integer.valueOf(DaysRemainingJTexFieldTotal.getText()),
                    
                    machineCodeIn
            );
        } catch (SQLException ex) {
            Logger.getLogger(StolleMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
