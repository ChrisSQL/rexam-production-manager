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

public class LinerMaintenance {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton L11Button, L12Button, L13Button, L14Button,  L21Button, L22Button, L23Button, L24Button, L31Button, L32Button, L33Button, L34Button, L41Button, L42Button, L43Button, L44Button, update;
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
            new LinerMaintenance(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public LinerMaintenance(int spIn) throws SQLException {

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

        JFrame frame = new JFrame("Liner Maintenance");
        frame.setSize(1400, 300);
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

        L11Button = new JButton("L11");
        L11Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
            }
        });
        L12Button = new JButton("L12");
        L12Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
            }
        });
        L13Button = new JButton("L13");
        L13Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
            }
        });
        L14Button = new JButton("L14");
        L14Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(4);
            }
        });
        L21Button = new JButton("L21");
        L21Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(5);
            }
        });
        L22Button = new JButton("L22");
        L22Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(6);
            }
        });
        L23Button = new JButton("L23");
        L23Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(7);
            }
        });
        L24Button = new JButton("L24");
        L24Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(8);
            }
        });
        L31Button = new JButton("L31");
        L31Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(9);
            }
        });
        L32Button = new JButton("L32");
        L32Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(10);
            }
        });
        L33Button = new JButton("L33");
        L33Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(11);
            }
        });
        L34Button = new JButton("L34");
        L34Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(12);
            }
        });
        L41Button = new JButton("L41");
        L41Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(13);
            }
        });
        L42Button = new JButton("L42");
        L42Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(14);
            }
        });
        L43Button = new JButton("L43");
        L43Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(15);
            }
        });
        L44Button = new JButton("L44");
        L44Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(16);
            }
        });

        optionsPanel.add(L11Button);
        optionsPanel.add(L12Button);
        optionsPanel.add(L13Button);
        optionsPanel.add(L14Button);
        optionsPanel.add(L21Button);
        optionsPanel.add(L22Button);
        optionsPanel.add(L23Button);
        optionsPanel.add(L24Button);
        optionsPanel.add(L31Button);
        optionsPanel.add(L32Button);
        optionsPanel.add(L33Button);
        optionsPanel.add(L34Button);
        optionsPanel.add(L41Button);
        optionsPanel.add(L42Button);
        optionsPanel.add(L43Button);
        optionsPanel.add(L44Button);
        
        

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
                

                if (MachineCodeJTexField1.getText().equals("L11")) {
                    updateEntry(1);
                    currentId = 1;
                    
                }
                if (MachineCodeJTexField1.getText().equals("L12")) {
                    updateEntry(2);
                    currentId = 2;
                }
                if (MachineCodeJTexField1.getText().equals("L13")) {
                    updateEntry(3);
                    currentId = 3;
                }
                if (MachineCodeJTexField1.getText().equals("L14")) {
                    updateEntry(4);
                    currentId = 4;
                }
                if (MachineCodeJTexField1.getText().equals("L21")) {
                    updateEntry(5);
                    currentId = 5;
                }
                if (MachineCodeJTexField1.getText().equals("L22")) {
                    updateEntry(6);
                    currentId = 6;
                }
                if (MachineCodeJTexField1.getText().equals("L23")) {
                    updateEntry(7);
                    currentId = 7;
                }
                if (MachineCodeJTexField1.getText().equals("L24")) {
                    updateEntry(8);
                    currentId = 8;
                }
                if (MachineCodeJTexField1.getText().equals("L31")) {
                    updateEntry(9);
                    currentId = 9;
                }
                if (MachineCodeJTexField1.getText().equals("L32")) {
                    updateEntry(10);
                    currentId = 10;
                }
                if (MachineCodeJTexField1.getText().equals("L33")) {
                    updateEntry(11);
                    currentId = 11;
                }
                if (MachineCodeJTexField1.getText().equals("L34")) {
                    updateEntry(12);
                    currentId = 12;
                }
                if (MachineCodeJTexField1.getText().equals("L41")) {
                    updateEntry(13);
                    currentId = 13;
                }
                if (MachineCodeJTexField1.getText().equals("L42")) {
                    updateEntry(14);
                    currentId = 14;
                }
                if (MachineCodeJTexField1.getText().equals("L43")) {
                    updateEntry(15);
                    currentId = 15;
                }
                if (MachineCodeJTexField1.getText().equals("L44")) {
                    updateEntry(16);
                    currentId = 16;
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

        MaintenanceTypeLabel1 = new JLabel("Gun Rebuild", JLabel.CENTER);
        MaintenanceTypeLabel2 = new JLabel("Annual", JLabel.CENTER);
        MaintenanceTypeLabel3 = new JLabel(" ", JLabel.CENTER);
        MaintenanceTypeLabel4 = new JLabel(" ", JLabel.CENTER);
        MaintenanceTypeLabel7 = new JLabel("Bi-Annual", JLabel.CENTER);

        LastMaintenanceModel1 = new UtilDateModel();
        LastMaintenanceDatePanel1 = new JDatePanelImpl(LastMaintenanceModel1);
        LastMaintenanceDatePicker1 = new JDatePickerImpl(LastMaintenanceDatePanel1);

        LastMaintenanceModel2 = new UtilDateModel();
        LastMaintenanceDatePanel2 = new JDatePanelImpl(LastMaintenanceModel2);
        LastMaintenanceDatePicker2 = new JDatePickerImpl(LastMaintenanceDatePanel2);

        LastMaintenanceModel7 = new UtilDateModel();
        LastMaintenanceDatePanel7 = new JDatePanelImpl(LastMaintenanceModel7);
        LastMaintenanceDatePicker7 = new JDatePickerImpl(LastMaintenanceDatePanel7);

        MaintenanceDue1 = new UtilDateModel();
        MaintenanceDueDatePanel1 = new JDatePanelImpl(MaintenanceDue1);
        MaintenanceDueDatePicker1 = new JDatePickerImpl(MaintenanceDueDatePanel1);

        MaintenanceDue2 = new UtilDateModel();
        MaintenanceDueDatePanel2 = new JDatePanelImpl(MaintenanceDue2);
        MaintenanceDueDatePicker2 = new JDatePickerImpl(MaintenanceDueDatePanel2);

        MaintenanceDue7 = new UtilDateModel();
        MaintenanceDueDatePanel7 = new JDatePanelImpl(MaintenanceDue7);
        MaintenanceDueDatePickerTotal = new JDatePickerImpl(MaintenanceDueDatePanel7);

        JPanel middlePanel = new JPanel(new GridLayout(4, 10));
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
        

        MaintenanceTypeLabel1 = new JLabel("Gun Rebuild", JLabel.CENTER);
        MaintenanceTypeLabel2 = new JLabel("Annual", JLabel.CENTER);      
        MaintenanceTypeLabel7 = new JLabel("Bi-Annual", JLabel.CENTER);


        toolingAreaLabel3.setVisible(true);
        toolingAreaLabel4.setVisible(true);


        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[50];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceLinerMaintenanceReturnEntryByID(machineCodeIn);

            int LastMaintenanceDate1year = Integer.valueOf((machineCodeArray[3] + "").substring(0, 4));
            int LastMaintenanceDate1month = Integer.valueOf((machineCodeArray[3] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate1day = Integer.valueOf((machineCodeArray[3] + "").substring(8, 10));
            int LastMaintenanceDate2year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
            int LastMaintenanceDate2month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate2day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));                
            int LastMaintenanceDate7year = Integer.valueOf((machineCodeArray[5] + "").substring(0, 4));
            int LastMaintenanceDate7month = Integer.valueOf((machineCodeArray[5] + "").substring(5, 7)) - 1;
            int LastMaintenanceDate7day = Integer.valueOf((machineCodeArray[5] + "").substring(8, 10));

            int MaintenanceDueDate1year = Integer.valueOf((machineCodeArray[15] + "").substring(0, 4));
            int MaintenanceDueDate1month = Integer.valueOf((machineCodeArray[15] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate1day = Integer.valueOf((machineCodeArray[15] + "").substring(8, 10));
            int MaintenanceDueDate2year = Integer.valueOf((machineCodeArray[16] + "").substring(0, 4));
            int MaintenanceDueDate2month = Integer.valueOf((machineCodeArray[16] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate2day = Integer.valueOf((machineCodeArray[16] + "").substring(8, 10));            
            int MaintenanceDueDate7year = Integer.valueOf((machineCodeArray[17] + "").substring(0, 4));
            int MaintenanceDueDate7month = Integer.valueOf((machineCodeArray[17] + "").substring(5, 7)) - 1;
            int MaintenanceDueDate7day = Integer.valueOf((machineCodeArray[17] + "").substring(8, 10));

            currentId = (int) machineCodeArray[0];

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            MachineNameJTexField1.setText(machineCodeArray[2] + "");
            LastMaintenanceModel1.setDate(LastMaintenanceDate1year, LastMaintenanceDate1month, LastMaintenanceDate1day);
            LastMaintenanceModel1.setSelected(true);
            TargetProductionJTexField1.setText(machineCodeArray[6] + "");
            ProductionJTexField1.setText(machineCodeArray[9] + "");
            PlusMinusJTexField1.setText(((int) machineCodeArray[6] - (int) machineCodeArray[9]) + "");
            MaintenanceDue1.setDate(MaintenanceDueDate1year, MaintenanceDueDate1month, MaintenanceDueDate1day);
            MaintenanceDue1.setSelected(true);
            DaysRemainingJTexField1.setText(machineCodeArray[18] + "");

            LastMaintenanceModel2.setDate(LastMaintenanceDate2year, LastMaintenanceDate2month, LastMaintenanceDate2day);
            LastMaintenanceModel2.setSelected(true);
            TargetProductionJTexField2.setText(machineCodeArray[7] + "");
            ProductionJTexField2.setText(machineCodeArray[10] + "");
            PlusMinusJTexField2.setText(((int) machineCodeArray[7] - (int) machineCodeArray[10]) + "");
            MaintenanceDue2.setDate(MaintenanceDueDate2year, MaintenanceDueDate2month, MaintenanceDueDate2day);
            MaintenanceDue2.setSelected(true);
            DaysRemainingJTexField2.setText(machineCodeArray[19] + "");

            LastMaintenanceModel7.setDate(LastMaintenanceDate7year, LastMaintenanceDate7month, LastMaintenanceDate7day);
            LastMaintenanceModel7.setSelected(true);
            TargetProductionJTexFieldTotal.setText(machineCodeArray[8] + "");
            ProductionJTexFieldTotal.setText(machineCodeArray[11] + "");
            PlusMinusJTexFieldTotal.setText(((int) machineCodeArray[8] - (int) machineCodeArray[11]) + "");
            MaintenanceDue7.setDate(MaintenanceDueDate7year, MaintenanceDueDate7month, MaintenanceDueDate7day);
            MaintenanceDue7.setSelected(true);
            DaysRemainingJTexFieldTotal.setText(machineCodeArray[20] + "");
            
        } catch (Exception ex) {
            Logger.getLogger(LinerMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void updateEntry(int machineCodeIn) {

        Date LastMaintenanceDate1 = (Date) LastMaintenanceDatePicker1.getModel().getValue();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate1);
        Date LastMaintenanceDate2 = (Date) LastMaintenanceDatePicker2.getModel().getValue();
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate2);           
        Date LastMaintenanceDate7 = (Date) LastMaintenanceDatePicker7.getModel().getValue();
        String date7 = new SimpleDateFormat("yyyy-MM-dd").format(LastMaintenanceDate7);

        Date MaintenanceDueDate1 = (Date) MaintenanceDueDatePicker1.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate1);
        Date MaintenanceDueDate2 = (Date) MaintenanceDueDatePicker2.getModel().getValue();
        String date9 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate2);     
        Date MaintenanceDueDate7 = (Date) MaintenanceDueDatePickerTotal.getModel().getValue();
        String date14 = new SimpleDateFormat("yyyy-MM-dd").format(MaintenanceDueDate7);

        try {
            SQLiteConnection.MaintenanceLinerMaintenanceUpdate(
                    
                    MachineCodeJTexField1.getText(),
                    MachineNameJTexField1.getText(),
                    
                    date1,
                    date2,
                    date7,
                    
                    Integer.valueOf(TargetProductionJTexField1.getText()),
                    Integer.valueOf(TargetProductionJTexField2.getText()),
                    Integer.valueOf(TargetProductionJTexFieldTotal.getText()),
                    
                    Integer.valueOf(ProductionJTexField1.getText()),
                    Integer.valueOf(ProductionJTexField2.getText()),
                    Integer.valueOf(ProductionJTexFieldTotal.getText()),
                    
                    Integer.valueOf(PlusMinusJTexField1.getText()),
                    Integer.valueOf(PlusMinusJTexField2.getText()),
                    Integer.valueOf(PlusMinusJTexFieldTotal.getText()),
                    
                    date8,
                    date9,
                    date14,
                    
                    Integer.valueOf(DaysRemainingJTexField1.getText()),
                    Integer.valueOf(DaysRemainingJTexField2.getText()),
                    Integer.valueOf(DaysRemainingJTexFieldTotal.getText()),
                    
                    machineCodeIn
            );
        } catch (SQLException ex) {
            Logger.getLogger(LinerMaintenance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
