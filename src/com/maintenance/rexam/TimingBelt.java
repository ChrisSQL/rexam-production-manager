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

public class TimingBelt {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton W11Button, W12Button, W21Button, W22Button, W31Button, W32Button, W33Button, W41Button, W42Button, W43Button, update;

    static JTextField MachineCodeJTexField1, MachineNameJTexField1, EndsTargetJTextField, ActualEndsJTextField, PlusMinusJTexField1;
    static JTextField MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal, TargetProductionJTexFieldTotal, ProductionJTexFieldTotal, PlusMinusJTexFieldTotal, MaintenanceDueDateJTexFieldTotal, DaysRemainingJTexFieldTotal;
    static UtilDateModel SetUpDateModel, DateFittedModel, beltsRemovedDateModel1, beltsRemovedDateModel2, beltsRemovedDateModel3, beltsRemovedDateModel4, beltsRemovedDateModel5, beltsRemovedDateModel6, beltsRemovedDateModel7, beltsRemovedDateModel8, beltsRemovedDateModel9, beltsRemovedDateModel10, beltsRemovedDateModel11, beltsRemovedDateModel12, beltsRemovedDateModel13, beltsRemovedDateModel14;
    static JDatePanelImpl SetUpDatePanel, DateFittedPanel, beltsRemovedDatePanel1, beltsRemovedDatePanel2, beltsRemovedDatePanel3, beltsRemovedDatePanel4, beltsRemovedDatePanel5, beltsRemovedDatePanel6, beltsRemovedDatePanel7, beltsRemovedDatePanel8, beltsRemovedDatePanel9, beltsRemovedDatePanel10, beltsRemovedDatePanel11, beltsRemovedDatePanel12, beltsRemovedDatePanel13, beltsRemovedDatePanel14;
    static JDatePickerImpl SetUpCheckDatePicker, DateFittedDatePicker, beltsRemovedDatePicker1, beltsRemovedDatePicker2, beltsRemovedDatePicker3, beltsRemovedDatePicker4, beltsRemovedDatePicker5, beltsRemovedDatePicker6, beltsRemovedDatePicker7, beltsRemovedDatePicker8, beltsRemovedDatePicker9, beltsRemovedDatePicker10, beltsRemovedDatePicker11, beltsRemovedDatePicker12, beltsRemovedDatePicker13, beltsRemovedDatePicker14;

    private static int currentId;

    public static void main(String[] args) {

        try {
            new TimingBelt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public TimingBelt(int spIn) throws SQLException {

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

        JFrame frame = new JFrame("Main Belt");
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
        
        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("TimingBelt");

    }

    private static JPanel createOptionsPanel(int spIn) {

        optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        W11Button = new JButton("W11");
        W11Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
                calculateandSetActualEndsForMachine("Stolle11");
            }
        });
        W12Button = new JButton("W12");
        W12Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
                calculateandSetActualEndsForMachine("Stolle12");
            }
        });
        W21Button = new JButton("W21");
        W21Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
                calculateandSetActualEndsForMachine("Stolle21");
            }
        });
        W22Button = new JButton("W22");
        W22Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(4);
                calculateandSetActualEndsForMachine("Stolle22");
            }
        });
        W31Button = new JButton("W31");
        W31Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(5);
                calculateandSetActualEndsForMachine("Stolle31");
            }
        });
        W32Button = new JButton("W32");
        W32Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(6);
                calculateandSetActualEndsForMachine("Stolle32");
            }
        });
        W33Button = new JButton("W33");
        W33Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(7);
                calculateandSetActualEndsForMachine("Stolle33");
            }
        });
        W41Button = new JButton("W41");
        W41Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(8);
                calculateandSetActualEndsForMachine("Stolle41");
            }
        });
        W42Button = new JButton("W42");
        W42Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(9);
                calculateandSetActualEndsForMachine("Stolle42");
            }
        });
        W43Button = new JButton("W43");
        W43Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(10);
                calculateandSetActualEndsForMachine("Stolle43");
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

                System.out.print(MachineCodeJTexField1.getText() + "");

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

        SetUpDateModel = new UtilDateModel();
        SetUpDatePanel = new JDatePanelImpl(SetUpDateModel);
        SetUpCheckDatePicker = new JDatePickerImpl(SetUpDatePanel);

        DateFittedModel = new UtilDateModel();
        DateFittedPanel = new JDatePanelImpl(DateFittedModel);
        DateFittedDatePicker = new JDatePickerImpl(DateFittedPanel);

        beltsRemovedDateModel1 = new UtilDateModel();
        beltsRemovedDatePanel1 = new JDatePanelImpl(beltsRemovedDateModel1);
        beltsRemovedDatePicker1 = new JDatePickerImpl(beltsRemovedDatePanel1);

        beltsRemovedDateModel2 = new UtilDateModel();
        beltsRemovedDatePanel2 = new JDatePanelImpl(beltsRemovedDateModel2);
        beltsRemovedDatePicker2 = new JDatePickerImpl(beltsRemovedDatePanel2);

        beltsRemovedDateModel3 = new UtilDateModel();
        beltsRemovedDatePanel3 = new JDatePanelImpl(beltsRemovedDateModel3);
        beltsRemovedDatePicker3 = new JDatePickerImpl(beltsRemovedDatePanel3);

        beltsRemovedDateModel4 = new UtilDateModel();
        beltsRemovedDatePanel4 = new JDatePanelImpl(beltsRemovedDateModel4);
        beltsRemovedDatePicker4 = new JDatePickerImpl(beltsRemovedDatePanel4);

        beltsRemovedDateModel5 = new UtilDateModel();
        beltsRemovedDatePanel5 = new JDatePanelImpl(beltsRemovedDateModel5);
        beltsRemovedDatePicker5 = new JDatePickerImpl(beltsRemovedDatePanel5);

        beltsRemovedDateModel6 = new UtilDateModel();
        beltsRemovedDatePanel6 = new JDatePanelImpl(beltsRemovedDateModel6);
        beltsRemovedDatePicker6 = new JDatePickerImpl(beltsRemovedDatePanel6);

        beltsRemovedDateModel7 = new UtilDateModel();
        beltsRemovedDatePanel7 = new JDatePanelImpl(beltsRemovedDateModel7);
        beltsRemovedDatePicker7 = new JDatePickerImpl(beltsRemovedDatePanel7);

        beltsRemovedDateModel8 = new UtilDateModel();
        beltsRemovedDatePanel8 = new JDatePanelImpl(beltsRemovedDateModel8);
        beltsRemovedDatePicker8 = new JDatePickerImpl(beltsRemovedDatePanel8);

        beltsRemovedDateModel9 = new UtilDateModel();
        beltsRemovedDatePanel9 = new JDatePanelImpl(beltsRemovedDateModel9);
        beltsRemovedDatePicker9 = new JDatePickerImpl(beltsRemovedDatePanel9);

        beltsRemovedDateModel10 = new UtilDateModel();
        beltsRemovedDatePanel10 = new JDatePanelImpl(beltsRemovedDateModel10);
        beltsRemovedDatePicker10 = new JDatePickerImpl(beltsRemovedDatePanel10);

        beltsRemovedDateModel11 = new UtilDateModel();
        beltsRemovedDatePanel11 = new JDatePanelImpl(beltsRemovedDateModel11);
        beltsRemovedDatePicker11 = new JDatePickerImpl(beltsRemovedDatePanel11);

        beltsRemovedDateModel12 = new UtilDateModel();
        beltsRemovedDatePanel12 = new JDatePanelImpl(beltsRemovedDateModel12);
        beltsRemovedDatePicker12 = new JDatePickerImpl(beltsRemovedDatePanel12);

        beltsRemovedDateModel13 = new UtilDateModel();
        beltsRemovedDatePanel13 = new JDatePanelImpl(beltsRemovedDateModel13);
        beltsRemovedDatePicker13 = new JDatePickerImpl(beltsRemovedDatePanel13);

        beltsRemovedDateModel14 = new UtilDateModel();
        beltsRemovedDatePanel14 = new JDatePanelImpl(beltsRemovedDateModel14);
        beltsRemovedDatePicker14 = new JDatePickerImpl(beltsRemovedDatePanel14);

        JPanel middlePanel = new JPanel(new GridLayout(4, 7));
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // middlePanel.setBackground(Color.WHITE);

        // ROW 1
        middlePanel.add(new JLabel(" ", JLabel.CENTER));
        middlePanel.add(new JLabel("Press No.", JLabel.CENTER));
        middlePanel.add(new JLabel("Date Fitted", JLabel.CENTER));
        middlePanel.add(new JLabel("Ends Target", JLabel.CENTER));
        middlePanel.add(new JLabel("Actual Ends", JLabel.CENTER));
        middlePanel.add(new JLabel("+ OR -", JLabel.CENTER));
        middlePanel.add(new JLabel("Set-Up Check", JLabel.CENTER));

        // ROW 2 
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
        EndsTargetJTextField = new JTextField();
        ActualEndsJTextField = new JTextField();
        PlusMinusJTexField1 = new JTextField();
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setEditable(false);
        EndsTargetJTextField = new JTextField();
        ActualEndsJTextField = new JTextField();
        PlusMinusJTexField1 = new JTextField();

        middlePanel.add(new JLabel("Belts Running", JLabel.CENTER));
        middlePanel.add(MachineCodeJTexField1);
        middlePanel.add(DateFittedDatePicker);
        middlePanel.add(EndsTargetJTextField);
        middlePanel.add(ActualEndsJTextField);
        middlePanel.add(PlusMinusJTexField1);
        middlePanel.add(SetUpCheckDatePicker);

        // ROW 3       
        middlePanel.add(new JLabel("Belts Removed", JLabel.CENTER));
        middlePanel.add(beltsRemovedDatePicker1);
        middlePanel.add(beltsRemovedDatePicker2);
        middlePanel.add(beltsRemovedDatePicker3);
        middlePanel.add(beltsRemovedDatePicker4);
        middlePanel.add(beltsRemovedDatePicker5);
        middlePanel.add(beltsRemovedDatePicker6);

        // ROW 3       
        middlePanel.add(new JLabel("Belts Removed", JLabel.CENTER));
        middlePanel.add(beltsRemovedDatePicker7);
        middlePanel.add(beltsRemovedDatePicker8);
        middlePanel.add(beltsRemovedDatePicker9);
        middlePanel.add(beltsRemovedDatePicker10);
        middlePanel.add(beltsRemovedDatePicker11);
        middlePanel.add(beltsRemovedDatePicker12);

        return middlePanel;
    }

    private static void setToMachineCode(int machineCodeIn) {

        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[50];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceTimingBeltReturnEntryByID(machineCodeIn);

            System.out.println("Machine Code Array " + machineCodeArray[2]);

            int DateFittedDate1year = Integer.valueOf((machineCodeArray[2] + "").substring(0, 4));
            int DateFittedDueDate1month = Integer.valueOf((machineCodeArray[2] + "").substring(5, 7)) - 1;
            int DateFittedDueDate1day = Integer.valueOf((machineCodeArray[2] + "").substring(8, 10));

            int SetUpDate1year = Integer.valueOf((machineCodeArray[6] + "").substring(0, 4));
            int SetUpDate1month = Integer.valueOf((machineCodeArray[6] + "").substring(5, 7)) - 1;
            int SetUpDate1day = Integer.valueOf((machineCodeArray[6] + "").substring(8, 10));
            
            int BeltsRunningDueDate1year = Integer.valueOf((machineCodeArray[7] + "").substring(0, 4));
            int BeltsRunningDueDate1month = Integer.valueOf((machineCodeArray[7] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate1day = Integer.valueOf((machineCodeArray[7] + "").substring(8, 10));
            
            int BeltsRunningDueDate2year = Integer.valueOf((machineCodeArray[8] + "").substring(0, 4));
            int BeltsRunningDueDate2month = Integer.valueOf((machineCodeArray[8] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate2day = Integer.valueOf((machineCodeArray[8] + "").substring(8, 10));
            
            int BeltsRunningDueDate3year = Integer.valueOf((machineCodeArray[9] + "").substring(0, 4));
            int BeltsRunningDueDate3month = Integer.valueOf((machineCodeArray[9] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate3day = Integer.valueOf((machineCodeArray[9] + "").substring(8, 10));
            
            int BeltsRunningDueDate4year = Integer.valueOf((machineCodeArray[10] + "").substring(0, 4));
            int BeltsRunningDueDate4month = Integer.valueOf((machineCodeArray[10] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate4day = Integer.valueOf((machineCodeArray[10] + "").substring(8, 10));
            
            int BeltsRunningDueDate5year = Integer.valueOf((machineCodeArray[11] + "").substring(0, 4));
            int BeltsRunningDueDate5month = Integer.valueOf((machineCodeArray[11] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate5day = Integer.valueOf((machineCodeArray[11] + "").substring(8, 10));
            
            int BeltsRunningDueDate6year = Integer.valueOf((machineCodeArray[12] + "").substring(0, 4));
            int BeltsRunningDueDate6month = Integer.valueOf((machineCodeArray[12] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate6day = Integer.valueOf((machineCodeArray[12] + "").substring(8, 10));
            
            int BeltsRunningDueDate7year = Integer.valueOf((machineCodeArray[13] + "").substring(0, 4));
            int BeltsRunningDueDate7month = Integer.valueOf((machineCodeArray[13] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate7day = Integer.valueOf((machineCodeArray[13] + "").substring(8, 10));
            
            int BeltsRunningDueDate8year = Integer.valueOf((machineCodeArray[14] + "").substring(0, 4));
            int BeltsRunningDueDate8month = Integer.valueOf((machineCodeArray[14] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate8day = Integer.valueOf((machineCodeArray[14] + "").substring(8, 10));
            
            int BeltsRunningDueDate9year = Integer.valueOf((machineCodeArray[15] + "").substring(0, 4));
            int BeltsRunningDueDate9month = Integer.valueOf((machineCodeArray[15] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate9day = Integer.valueOf((machineCodeArray[15] + "").substring(8, 10));
            
            int BeltsRunningDueDate10year = Integer.valueOf((machineCodeArray[16] + "").substring(0, 4));
            int BeltsRunningDueDate10month = Integer.valueOf((machineCodeArray[16] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate10day = Integer.valueOf((machineCodeArray[16] + "").substring(8, 10));
            
            int BeltsRunningDueDate11year = Integer.valueOf((machineCodeArray[17] + "").substring(0, 4));
            int BeltsRunningDueDate11month = Integer.valueOf((machineCodeArray[17] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate11day = Integer.valueOf((machineCodeArray[17] + "").substring(8, 10));
            
            int BeltsRunningDueDate12year = Integer.valueOf((machineCodeArray[18] + "").substring(0, 4));
            int BeltsRunningDueDate12month = Integer.valueOf((machineCodeArray[18] + "").substring(5, 7)) - 1;
            int BeltsRunningDueDate12day = Integer.valueOf((machineCodeArray[18] + "").substring(8, 10));
            
            

            currentId = (int) machineCodeArray[0];

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            DateFittedModel.setDate(DateFittedDate1year, DateFittedDueDate1month, DateFittedDueDate1day);
            DateFittedModel.setSelected(true);
            EndsTargetJTextField.setText(machineCodeArray[3] + "");
            ActualEndsJTextField.setText(machineCodeArray[4] + "");
            PlusMinusJTexField1.setText(((int) machineCodeArray[3] - (int) machineCodeArray[4]) + "");
            SetUpDateModel.setDate(SetUpDate1year, SetUpDate1month, SetUpDate1day);
            SetUpDateModel.setSelected(true);
            
            beltsRemovedDateModel1.setDate(BeltsRunningDueDate1year, BeltsRunningDueDate1month, BeltsRunningDueDate1day);
            beltsRemovedDateModel1.setSelected(true);
            
            beltsRemovedDateModel2.setDate(BeltsRunningDueDate2year, BeltsRunningDueDate2month, BeltsRunningDueDate2day);
            beltsRemovedDateModel2.setSelected(true);
                     
            beltsRemovedDateModel3.setDate(BeltsRunningDueDate3year, BeltsRunningDueDate3month, BeltsRunningDueDate3day);
            beltsRemovedDateModel3.setSelected(true);
            
            beltsRemovedDateModel4.setDate(BeltsRunningDueDate4year, BeltsRunningDueDate4month, BeltsRunningDueDate4day);
            beltsRemovedDateModel4.setSelected(true);
            
            beltsRemovedDateModel5.setDate(BeltsRunningDueDate5year, BeltsRunningDueDate5month, BeltsRunningDueDate5day);
            beltsRemovedDateModel5.setSelected(true);
            
            beltsRemovedDateModel6.setDate(BeltsRunningDueDate6year, BeltsRunningDueDate6month, BeltsRunningDueDate6day);
            beltsRemovedDateModel6.setSelected(true);
            
            beltsRemovedDateModel7.setDate(BeltsRunningDueDate7year, BeltsRunningDueDate7month, BeltsRunningDueDate7day);
            beltsRemovedDateModel7.setSelected(true);
            
            beltsRemovedDateModel8.setDate(BeltsRunningDueDate8year, BeltsRunningDueDate8month, BeltsRunningDueDate8day);
            beltsRemovedDateModel8.setSelected(true);
            
            beltsRemovedDateModel9.setDate(BeltsRunningDueDate9year, BeltsRunningDueDate9month, BeltsRunningDueDate9day);
            beltsRemovedDateModel9.setSelected(true);
            
            beltsRemovedDateModel10.setDate(BeltsRunningDueDate10year, BeltsRunningDueDate10month, BeltsRunningDueDate10day);
            beltsRemovedDateModel10.setSelected(true);
            
            beltsRemovedDateModel11.setDate(BeltsRunningDueDate11year, BeltsRunningDueDate11month, BeltsRunningDueDate11day);
            beltsRemovedDateModel11.setSelected(true);
            
            beltsRemovedDateModel12.setDate(BeltsRunningDueDate12year, BeltsRunningDueDate12month, BeltsRunningDueDate12day);
            beltsRemovedDateModel12.setSelected(true);
            

        } catch (Exception ex) {
            Logger.getLogger(TimingBelt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int[] calculateandSetActualEndsForMachine(String machineIn) {

        int[] sum = new int[3];

        // calculate the sum for the parameter machine
        Date date1 = DateFittedModel.getValue();

        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        try {
            sum[0] = SQLiteConnection.MaintenanceStolleProductionCalculateTotalsForMachine(dateString1, machineIn);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("AAA : " + sum[0]);

        ActualEndsJTextField.setText(sum[0] + "");

        return sum;

    }
    
    private static void updateEntry(int machineCodeIn) {
        
        System.out.print("Machine Code : " + machineCodeIn);
        
        currentId = machineCodeIn;

        Date SetUpCheckDate1 = (Date) SetUpCheckDatePicker.getModel().getValue();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(SetUpCheckDate1);

        Date DateFittedDate1 = (Date) DateFittedDatePicker.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(DateFittedDate1);

        Date BeltFittedDate1 = (Date) beltsRemovedDatePicker1.getModel().getValue();
        String date9 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate1);
        
        Date BeltFittedDate2 = (Date) beltsRemovedDatePicker2.getModel().getValue();
        String date10 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate2);
        
        Date BeltFittedDate3 = (Date) beltsRemovedDatePicker3.getModel().getValue();
        String date11 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate3);
        
        Date BeltFittedDate4 = (Date) beltsRemovedDatePicker4.getModel().getValue();
        String date12 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate4);
        
        Date BeltFittedDate5 = (Date) beltsRemovedDatePicker5.getModel().getValue();
        String date13 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate5);
        
        Date BeltFittedDate6 = (Date) beltsRemovedDatePicker6.getModel().getValue();
        String date14 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate6);
        
        Date BeltFittedDate7 = (Date) beltsRemovedDatePicker7.getModel().getValue();
        String date15 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate7);
        
        Date BeltFittedDate8 = (Date) beltsRemovedDatePicker8.getModel().getValue();
        String date16 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate8);
        
        Date BeltFittedDate9 = (Date) beltsRemovedDatePicker9.getModel().getValue();
        String date17 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate9);
        
        Date BeltFittedDate10 = (Date) beltsRemovedDatePicker10.getModel().getValue();
        String date18 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate10);
        
        Date BeltFittedDate11 = (Date) beltsRemovedDatePicker11.getModel().getValue();
        String date19 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate11);
        
        Date BeltFittedDate12 = (Date) beltsRemovedDatePicker12.getModel().getValue();
        String date20 = new SimpleDateFormat("yyyy-MM-dd").format(BeltFittedDate12);
        

        try {
            SQLiteConnection.MaintenanceTimingBeltUpdate(
                    
                    MachineCodeJTexField1.getText(),
                    date8,
                    Integer.valueOf(EndsTargetJTextField.getText()),
                    Integer.valueOf(ActualEndsJTextField.getText()),
                    Integer.valueOf(PlusMinusJTexField1.getText()),                   
                    date1,                    
                    date9,
                    date10,
                    date11,
                    date12,
                    date13,
                    date14,
                    date15,
                    date16,
                    date17,
                    date18,
                    date19,
                    date20,
                    currentId
                    
                    
            );
        } catch (SQLException ex) {
            Logger.getLogger(TimingBelt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
