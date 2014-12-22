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

public class ScoreInsert {

    static JFrame outerFrame;
    static JPanel optionsPanel, outerPanel;
    static JButton W11Button, W12Button, W21Button, W22Button, W31Button, W32Button, W33Button, W41Button, W42Button, W43Button, update;

    static JTextField TypeJTexField1, TypeJTexField2, TypeJTexField3, MachineCodeJTexField1, MachineCodeJTexField2, MachineCodeJTexField3, MachineNameJTexField1, EndsTargetJTextField1, EndsTargetJTextField2, EndsTargetJTextField3, ActualEndsJTextField1, ActualEndsJTextField2, ActualEndsJTextField3, PlusMinusJTexField1, PlusMinusJTexField2, PlusMinusJTexField3;
    static JTextField MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal, TargetProductionJTexFieldTotal, ProductionJTexFieldTotal, PlusMinusJTexFieldTotal, MaintenanceDueDateJTexFieldTotal, DaysRemainingJTexFieldTotal;
    static UtilDateModel ChangeDateModel1, ChangeDateModel2, ChangeDateModel3, DateInsertedModel1, DateInsertedModel2, DateInsertedModel3;
    static JDatePanelImpl ChangeDatePanel1, ChangeDatePanel2, ChangeDatePanel3, DateInsertedPanel1, DateInsertedPanel2, DateInsertedPanel3;
    static JDatePickerImpl ChangeDatePicker1, ChangeDatePicker2, ChangeDatePicker3, DateInsertedDatePicker1, DateInsertedDatePicker2, DateInsertedDatePicker3;

    private static int currentId;

    static JFrame frame;

    public static void main(String[] args) {

        try {
            new ScoreInsert(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ScoreInsert(int spIn) throws SQLException {

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

        frame = new JFrame("Score Tooling");
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

        setChangeDates();
        
        ActualEndsJTextField1.setEditable(false);
        ActualEndsJTextField2.setEditable(false);
        ActualEndsJTextField3.setEditable(false);
        
        PlusMinusJTexField1.setEditable(false);
        PlusMinusJTexField2.setEditable(false);
        PlusMinusJTexField3.setEditable(false);
        
        MachineCodeJTexField1.setEditable(false);
        MachineCodeJTexField2.setEditable(false);
        MachineCodeJTexField3.setEditable(false);
        
        TypeJTexField1.setEditable(false);
        TypeJTexField2.setEditable(false);
        TypeJTexField3.setEditable(false);
        
        
        SQLiteConnection.AnalyticsUpdate("ScoreInsert");

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

                if (MachineCodeJTexField1.getText().equals("W11A")) {
                    updateEntry(1);
                    currentId = 1;

                }
                if (MachineCodeJTexField1.getText().equals("W12A")) {
                    updateEntry(2);
                    currentId = 2;
                }
                if (MachineCodeJTexField1.getText().equals("W21A")) {
                    updateEntry(3);
                    currentId = 3;
                }
                if (MachineCodeJTexField1.getText().equals("W22A")) {
                    updateEntry(4);
                    currentId = 4;
                }
                if (MachineCodeJTexField1.getText().equals("W31A")) {
                    updateEntry(5);
                    currentId = 5;
                }
                if (MachineCodeJTexField1.getText().equals("W32A")) {
                    updateEntry(6);
                    currentId = 6;
                }
                if (MachineCodeJTexField1.getText().equals("W33A")) {
                    updateEntry(7);
                    currentId = 7;
                }
                if (MachineCodeJTexField1.getText().equals("W41A")) {
                    updateEntry(8);
                    currentId = 8;
                }
                if (MachineCodeJTexField1.getText().equals("W42A")) {
                    updateEntry(9);
                    currentId = 9;
                }
                if (MachineCodeJTexField1.getText().equals("W43A")) {
                    updateEntry(10);
                    currentId = 10;
                }

                //  setToMachineCode(currentId);
                setChangeDates();

            }
        });

        bottomPanel.add(update);

        return bottomPanel;

    }

    private static JPanel createMiddlePanel(int spIn) {

        ChangeDateModel1 = new UtilDateModel();
        ChangeDatePanel1 = new JDatePanelImpl(ChangeDateModel1);
        ChangeDatePicker1 = new JDatePickerImpl(ChangeDatePanel1);

        ChangeDateModel2 = new UtilDateModel();
        ChangeDatePanel2 = new JDatePanelImpl(ChangeDateModel2);
        ChangeDatePicker2 = new JDatePickerImpl(ChangeDatePanel2);

        ChangeDateModel3 = new UtilDateModel();
        ChangeDatePanel3 = new JDatePanelImpl(ChangeDateModel3);
        ChangeDatePicker3 = new JDatePickerImpl(ChangeDatePanel3);

        DateInsertedModel1 = new UtilDateModel();
        DateInsertedPanel1 = new JDatePanelImpl(DateInsertedModel1);
        DateInsertedDatePicker1 = new JDatePickerImpl(DateInsertedPanel1);

        DateInsertedModel2 = new UtilDateModel();
        DateInsertedPanel2 = new JDatePanelImpl(DateInsertedModel2);
        DateInsertedDatePicker2 = new JDatePickerImpl(DateInsertedPanel2);

        DateInsertedModel3 = new UtilDateModel();
        DateInsertedPanel3 = new JDatePanelImpl(DateInsertedModel3);
        DateInsertedDatePicker3 = new JDatePickerImpl(DateInsertedPanel3);

        JPanel middlePanel = new JPanel(new GridLayout(4, 7));
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // middlePanel.setBackground(Color.WHITE);

        // ROW 1
        middlePanel.add(new JLabel("Type", JLabel.CENTER));
        middlePanel.add(new JLabel("Press No.", JLabel.CENTER));
        middlePanel.add(new JLabel("Date Inserted", JLabel.CENTER));
        middlePanel.add(new JLabel("Ends Target", JLabel.CENTER));
        middlePanel.add(new JLabel("Actual Ends", JLabel.CENTER));
        middlePanel.add(new JLabel("+ OR -", JLabel.CENTER));
        middlePanel.add(new JLabel("Change Date", JLabel.CENTER));

        // ROW 2 
        TypeJTexField1 = new JTextField(" ");
        TypeJTexField1.setBackground(new Color(255, 255, 123));
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
        EndsTargetJTextField1 = new JTextField();
        ActualEndsJTextField1 = new JTextField();
        PlusMinusJTexField1 = new JTextField();

        middlePanel.add(TypeJTexField1);
        middlePanel.add(MachineCodeJTexField1);
        middlePanel.add(DateInsertedDatePicker1);
        middlePanel.add(EndsTargetJTextField1);
        middlePanel.add(ActualEndsJTextField1);
        middlePanel.add(PlusMinusJTexField1);
        middlePanel.add(ChangeDatePicker1);

        // ROW 3    
        TypeJTexField2 = new JTextField(" ");
        TypeJTexField2.setBackground(new Color(255, 255, 123));
        MachineCodeJTexField2 = new JTextField(" ");
        MachineCodeJTexField2.setBackground(new Color(255, 255, 123));
        EndsTargetJTextField2 = new JTextField();
        ActualEndsJTextField2 = new JTextField();
        PlusMinusJTexField2 = new JTextField();

        middlePanel.add(new JLabel(" ", JLabel.CENTER));
        middlePanel.add(MachineCodeJTexField2);
        middlePanel.add(DateInsertedDatePicker2);
        middlePanel.add(EndsTargetJTextField2);
        middlePanel.add(ActualEndsJTextField2);
        middlePanel.add(PlusMinusJTexField2);
        middlePanel.add(ChangeDatePicker2);

        // ROW 3   
        TypeJTexField3 = new JTextField(" ");
        TypeJTexField3.setBackground(new Color(255, 255, 123));
        MachineCodeJTexField3 = new JTextField(" ");
        MachineCodeJTexField3.setBackground(new Color(255, 255, 123));
        EndsTargetJTextField3 = new JTextField();
        ActualEndsJTextField3 = new JTextField();
        PlusMinusJTexField3 = new JTextField();

        middlePanel.add(new JLabel(" ", JLabel.CENTER));
        middlePanel.add(MachineCodeJTexField3);
        middlePanel.add(DateInsertedDatePicker3);
        middlePanel.add(EndsTargetJTextField3);
        middlePanel.add(ActualEndsJTextField3);
        middlePanel.add(PlusMinusJTexField3);
        middlePanel.add(ChangeDatePicker3);

        return middlePanel;
    }

    private static void setToMachineCode(int machineCodeIn) {

        optionsPanel.repaint();

        Object[] machineCodeArray = new Object[20];

        try {

            machineCodeArray = SQLiteConnection.MaintenanceScoreInsertReturnEntryByID(machineCodeIn);

            System.out.println("Machine Code Array " + machineCodeArray[0]);

            int DateInsertedDate1year = Integer.valueOf((machineCodeArray[4] + "").substring(0, 4));
            int DateInsertedDueDate1month = Integer.valueOf((machineCodeArray[4] + "").substring(5, 7)) - 1;
            int DateInsertedDueDate1day = Integer.valueOf((machineCodeArray[4] + "").substring(8, 10));

            int DateInsertedDate2year = Integer.valueOf((machineCodeArray[5] + "").substring(0, 4));
            int DateInsertedDueDate2month = Integer.valueOf((machineCodeArray[5] + "").substring(5, 7)) - 1;
            int DateInsertedDueDate2day = Integer.valueOf((machineCodeArray[5] + "").substring(8, 10));

            int DateInsertedDate3year = Integer.valueOf((machineCodeArray[6] + "").substring(0, 4));
            int DateInsertedDueDate3month = Integer.valueOf((machineCodeArray[6] + "").substring(5, 7)) - 1;
            int DateInsertedDueDate3day = Integer.valueOf((machineCodeArray[6] + "").substring(8, 10));

            int SetUpDate1year = Integer.valueOf((machineCodeArray[16] + "").substring(0, 4));
            int SetUpDate1month = Integer.valueOf((machineCodeArray[16] + "").substring(5, 7)) - 1;
            int SetUpDate1day = Integer.valueOf((machineCodeArray[16] + "").substring(8, 10));

            int SetUpDate2year = Integer.valueOf((machineCodeArray[17] + "").substring(0, 4));
            int SetUpDate2month = Integer.valueOf((machineCodeArray[17] + "").substring(5, 7)) - 1;
            int SetUpDate2day = Integer.valueOf((machineCodeArray[17] + "").substring(8, 10));

            int SetUpDate3year = Integer.valueOf((machineCodeArray[18] + "").substring(0, 4));
            int SetUpDate3month = Integer.valueOf((machineCodeArray[18] + "").substring(5, 7)) - 1;
            int SetUpDate3day = Integer.valueOf((machineCodeArray[18] + "").substring(8, 10));

            MachineCodeJTexField1.setText(machineCodeArray[1] + "");
            MachineCodeJTexField2.setText(machineCodeArray[2] + "");
            MachineCodeJTexField3.setText(machineCodeArray[3] + "");

            DateInsertedModel1.setDate(DateInsertedDate1year, DateInsertedDueDate1month, DateInsertedDueDate1day);
            DateInsertedModel1.setSelected(true);
            DateInsertedModel2.setDate(DateInsertedDate2year, DateInsertedDueDate2month, DateInsertedDueDate2day);
            DateInsertedModel2.setSelected(true);
            DateInsertedModel3.setDate(DateInsertedDate3year, DateInsertedDueDate3month, DateInsertedDueDate3day);
            DateInsertedModel3.setSelected(true);

            EndsTargetJTextField1.setText(machineCodeArray[7] + "");
            EndsTargetJTextField2.setText(machineCodeArray[8] + "");
            EndsTargetJTextField3.setText(machineCodeArray[9] + "");

            ActualEndsJTextField1.setText(machineCodeArray[10] + "");
            ActualEndsJTextField2.setText(machineCodeArray[11] + "");
            ActualEndsJTextField3.setText(machineCodeArray[12] + "");

            PlusMinusJTexField1.setText(((int) machineCodeArray[7] - (int) machineCodeArray[10]) + "");
            PlusMinusJTexField2.setText(((int) machineCodeArray[8] - (int) machineCodeArray[11]) + "");
            PlusMinusJTexField3.setText(((int) machineCodeArray[9] - (int) machineCodeArray[12]) + "");

            ChangeDateModel1.setDate(SetUpDate1year, SetUpDate1month, SetUpDate1day);
            ChangeDateModel1.setSelected(true);
            ChangeDateModel2.setDate(SetUpDate2year, SetUpDate2month, SetUpDate2day);
            ChangeDateModel2.setSelected(true);
            ChangeDateModel3.setDate(SetUpDate3year, SetUpDate3month, SetUpDate3day);
            ChangeDateModel3.setSelected(true);

            TypeJTexField1.setText(machineCodeArray[19] + "");
            TypeJTexField2.setText(machineCodeArray[19] + "");
            TypeJTexField3.setText(machineCodeArray[19] + "");

            currentId = (int) machineCodeArray[0];

        } catch (Exception ex) {
            Logger.getLogger(ScoreInsert.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int[] calculateandSetActualEndsForMachine(String machineIn) {

        int[] sum = new int[3];

        // calculate the sum for the parameter machine
        Date date1 = DateInsertedModel1.getValue();
        Date date2 = DateInsertedModel2.getValue();
        Date date3 = DateInsertedModel3.getValue();

        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        String dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        String dateString3 = new SimpleDateFormat("yyyy-MM-dd").format(date3);

        try {
            sum[0] = SQLiteConnection.MaintenanceStolleProductionCalculateTotalsForMachine(dateString1, machineIn);
            sum[1] = SQLiteConnection.MaintenanceStolleProductionCalculateTotalsForMachine(dateString2, machineIn);
            sum[2] = SQLiteConnection.MaintenanceStolleProductionCalculateTotalsForMachine(dateString3, machineIn);
        } catch (SQLException ex) {
            Logger.getLogger(ScoreInsert.class.getName()).log(Level.SEVERE, null, ex);
        }

        ActualEndsJTextField1.setText(sum[0] + "");
        ActualEndsJTextField2.setText(sum[1] + "");
        ActualEndsJTextField3.setText(sum[2] + "");

        setChangeDates();

        return sum;

    }

    private static void setChangeDates() {

        int days1 = (Integer.valueOf(PlusMinusJTexField1.getText()) / 2160000) * 3;
        int days2 = (Integer.valueOf(PlusMinusJTexField2.getText()) / 2160000) * 3;
        int days3 = (Integer.valueOf(PlusMinusJTexField3.getText()) / 2160000) * 3;

        // get Dates of DateInserted
        Date date1 = DateInsertedModel1.getValue();
        Date date2 = DateInsertedModel2.getValue();
        Date date3 = DateInsertedModel3.getValue();

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1); // Now use today date.
        c1.add(Calendar.DATE, days1); // Adding 5 days
        String output1 = sdf1.format(c1.getTime());
        String output2 = sdf2.format(c1.getTime());
        String output3 = sdf3.format(c1.getTime());
        ChangeDateModel1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2); // Now use today date.
        c2.add(Calendar.DATE, days2); // Adding 5 days
        output1 = sdf1.format(c2.getTime());
        output2 = sdf2.format(c2.getTime());
        output3 = sdf3.format(c2.getTime());
        ChangeDateModel2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

        Calendar c3 = Calendar.getInstance();
        c3.setTime(date3); // Now use today date.
        c3.add(Calendar.DATE, days3); // Adding 5 days
        output1 = sdf1.format(c3.getTime());
        output2 = sdf2.format(c3.getTime());
        output3 = sdf3.format(c3.getTime());
        ChangeDateModel3.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

    }

    private static void updateEntry(int machineCodeIn) {

        String stolle = "";

        setChangeDates();

        if (MachineCodeJTexField1.getText().equals("W11A")) {
            stolle = "Stolle11";

        }
        if (MachineCodeJTexField1.getText().equals("W12A")) {
            stolle = "Stolle12";

        }
        if (MachineCodeJTexField1.getText().equals("W21A")) {
            stolle = "Stolle21";
        }
        if (MachineCodeJTexField1.getText().equals("W22A")) {
            stolle = "Stolle21";
        }
        if (MachineCodeJTexField1.getText().equals("W31A")) {
            stolle = "Stolle31";
        }
        if (MachineCodeJTexField1.getText().equals("W32A")) {
            stolle = "Stolle32";
        }
        if (MachineCodeJTexField1.getText().equals("W33A")) {
            stolle = "Stolle33";
        }
        if (MachineCodeJTexField1.getText().equals("W41A")) {
            stolle = "Stolle41";
        }
        if (MachineCodeJTexField1.getText().equals("W42A")) {
            stolle = "Stolle42";
        }
        if (MachineCodeJTexField1.getText().equals("W43A")) {
            stolle = "Stolle43";
        }

        calculateandSetActualEndsForMachine(stolle);

        System.out.print("Machine Code : " + machineCodeIn);

        currentId = machineCodeIn;

        Date DateInserted1 = (Date) DateInsertedDatePicker1.getModel().getValue();
        String date8 = new SimpleDateFormat("yyyy-MM-dd").format(DateInserted1);
        Date DateInserted2 = (Date) DateInsertedDatePicker2.getModel().getValue();
        String date9 = new SimpleDateFormat("yyyy-MM-dd").format(DateInserted2);
        Date DateInserted3 = (Date) DateInsertedDatePicker3.getModel().getValue();
        String date10 = new SimpleDateFormat("yyyy-MM-dd").format(DateInserted3);

        Date ChangeDate1 = (Date) ChangeDatePicker1.getModel().getValue();
        String date11 = new SimpleDateFormat("yyyy-MM-dd").format(ChangeDate1);
        Date ChangeDate2 = (Date) ChangeDatePicker2.getModel().getValue();
        String date12 = new SimpleDateFormat("yyyy-MM-dd").format(ChangeDate2);
        Date ChangeDate3 = (Date) ChangeDatePicker3.getModel().getValue();
        String date13 = new SimpleDateFormat("yyyy-MM-dd").format(ChangeDate3);

        try {
            SQLiteConnection.MaintenanceScoreInsertUpdate(
                    TypeJTexField1.getText(),
                    MachineCodeJTexField1.getText(),
                    MachineCodeJTexField2.getText(),
                    MachineCodeJTexField3.getText(),
                    date8,
                    date9,
                    date10,
                    Integer.valueOf(EndsTargetJTextField1.getText()),
                    Integer.valueOf(EndsTargetJTextField2.getText()),
                    Integer.valueOf(EndsTargetJTextField3.getText()),
                    Integer.valueOf(ActualEndsJTextField1.getText()),
                    Integer.valueOf(ActualEndsJTextField2.getText()),
                    Integer.valueOf(ActualEndsJTextField3.getText()),
                    Integer.valueOf(PlusMinusJTexField1.getText()),
                    Integer.valueOf(PlusMinusJTexField2.getText()),
                    Integer.valueOf(PlusMinusJTexField3.getText()),
                    date11,
                    date12,
                    date13,
                    currentId
            );

            setChangeDates();

        } catch (SQLException ex) {
            Logger.getLogger(ScoreInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setToMachineCode(currentId);


    }




}
