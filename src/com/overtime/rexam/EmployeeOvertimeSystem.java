package com.overtime.rexam;

// Take Care Of Deleting / Modifying Employees
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.database.rexam.SQLiteConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EmployeeOvertimeSystem {

    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate, go, go2, summary, moveMarkerUpButton, moveMarkerDownButton;
    static JLabel employeeId, name, address, crew, departmentHead, processLeader, shiftManager, technician, leadHand, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector, phoneNumber,
            mobileNumber, nameLabel1, crewLabel1, nameLabel2, dateLabel2, statusLabel, currentTableLabel, selectTableLabel;
    static JTextField employeeIdText, nametext, addressText, phoneNumberText, mobileNumberText;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, nameCombo, crewSelectCombo, jobSelectCombo, statusCombo, currentJobCombo, currentCrewCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;
    static UtilDateModel model, model2;
    static JDatePanelImpl datePanel, datePanel2;
    static JDatePickerImpl datePicker, datePicker2;

    public static void main(String args[]) {

        for (int i = 0; i < 999; i++) {
            moveMarkerDown("Operator", "A");
            new EmployeeOvertimeSystem("Operator", "A");
        }
        for (int i = 0; i < 999; i++) {
            moveMarkerUp("Operator", "A");
            new EmployeeOvertimeSystem("Operator", "A");
        }

 //       new EmployeeOvertimeSystem("Operator", "A"); 
        //   modifyOvertimeEmployee(1,"AAA","TeamLeader","B");
    }

    public EmployeeOvertimeSystem(String typeIn, String crewIn) {

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

        model = new UtilDateModel();
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        nameLabel1 = new JLabel("Job ");
        crewLabel1 = new JLabel("Crew ");
        nameLabel2 = new JLabel("Name");
        dateLabel2 = new JLabel("Date Req. ");
        statusLabel = new JLabel("Status ");
        currentTableLabel = new JLabel("Current Table : ");
        selectTableLabel = new JLabel("Select Table : ");

        String[] crewArray = {"A", "B", "C", "D"};
        String[] jobsArray = {"Operator", "ForkLiftDriver", "Packer", "QCInspector", "TeamLeader"};
        String[] statusArray = {"Absent", "Accepted", "Dont Count", "Miss A Turn", "Refused", "Set Up"};
        crewSelectCombo = new JComboBox(crewArray);
        jobSelectCombo = new JComboBox(jobsArray);
        statusCombo = new JComboBox(statusArray);
        nameCombo = new JComboBox();
        currentCrewCombo = new JComboBox();
        currentCrewCombo.addItem(crewIn);
        currentJobCombo = new JComboBox();
        currentJobCombo.addItem(typeIn);

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selected = jobSelectCombo.getSelectedItem() + "";
                String crew = crewSelectCombo.getSelectedItem() + "";

                frame9.dispose();
                new EmployeeOvertimeSystem(selected, crew);

            }
        });
        go2 = new JButton("Go");
        go2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (nameCombo.getSelectedIndex() == -1) {
                    frame9.dispose();
                    new EmployeeOvertimeSystem("Operator", "A");
                } else {
                    insertEmployeeOvertime();
                    frame9.dispose();
                    
                    new EmployeeOvertimeSystem("All", "0");
                }

            }
        });
        // nameCombo = new JComboBox();
        frame9 = new JFrame("Employee Overtime List");
        // frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.setSize(1500, 768);
        frame9.setLocationRelativeTo(null);
        // frame9.setResizable(false);

        phoneNumberText = new JTextField();
        mobileNumberText = new JTextField();

        // ///////////
        frame9.add(createTopPanel(), BorderLayout.NORTH);

        System.out.println("TypeIn " + typeIn);
        System.out.println("CrewIn " + crewIn);

        frame9.add(createSummaryPanel(typeIn, crewIn), BorderLayout.CENTER);
        frame9.add(createBottomPanel(), BorderLayout.SOUTH);

        frame9.setVisible(true);

        if (!typeIn.equals("All")) {
            jobSelectCombo.setSelectedItem(typeIn);
            crewSelectCombo.setSelectedItem(crewIn);
            fillCrewCombos(typeIn, crewIn);
        }

//        if (typeIn.equals("") || typeIn.equals("Operator") || typeIn.equals("ForkLiftDriver") || typeIn.equals("QCInspector") || typeIn.equals("TeamLeader")) {
//
//        } else {
//
//            fillCrewCombos(typeIn, crewIn);
//
//        }
        SQLiteConnection.AnalyticsUpdate("EmployeeOvertimeSystem");

    }

    private static JPanel createTopPanel() {

        employeeId = new JLabel("Employee ID :", SwingConstants.CENTER);
        name = new JLabel("Name :", SwingConstants.CENTER);
        address = new JLabel("Address :", SwingConstants.CENTER);
        crew = new JLabel("Crew :", SwingConstants.CENTER);
        departmentHead = new JLabel("Department Head :", SwingConstants.CENTER);
        shiftManager = new JLabel("Shift Manager :", SwingConstants.CENTER);
        technician = new JLabel("Technician :", SwingConstants.CENTER);
        leadHand = new JLabel("Lead Hand :", SwingConstants.CENTER);
        operator = new JLabel("Operator :", SwingConstants.CENTER);
        engineer = new JLabel("Engineer :", SwingConstants.CENTER);
        packer = new JLabel("Packer :", SwingConstants.CENTER);
        phoneNumber = new JLabel("Home Phone Number :", SwingConstants.CENTER);
        mobileNumber = new JLabel("Mobile Number :", SwingConstants.CENTER);
        qcInspector = new JLabel("QC Inspector :", SwingConstants.CENTER);

        forkliftDriver = new JLabel("Forklift Driver :", SwingConstants.CENTER);
        processLeader = new JLabel("Process Leader", SwingConstants.CENTER);
        toolmaker = new JLabel("Toolmaker", SwingConstants.CENTER);
        fitter = new JLabel("Fitter", SwingConstants.CENTER);
        electrician = new JLabel("Electrician", SwingConstants.CENTER);

        employeeIdText = new JTextField();
        nametext = new JTextField();
        addressText = new JTextField();
        phoneNumberText = new JTextField();
        mobileNumberText = new JTextField();

        departmentHeadJCheckBox = new JCheckBox();
        shiftManagerJCheckBox = new JCheckBox();
        technicianJCheckBox = new JCheckBox();
        leadHandJCheckBox = new JCheckBox();
        operatorJCheckBox = new JCheckBox();
        engineerJCheckBox = new JCheckBox();
        packerJCheckBox = new JCheckBox();
        qcInspectorJCheckBox = new JCheckBox();

        forkliftDriverJCheckBox = new JCheckBox();
        processLeaderJCheckBox = new JCheckBox();
        toolmakerJCheckBox = new JCheckBox();
        fitterJCheckBox = new JCheckBox();
        electricianJCheckBox = new JCheckBox();

        JPanel outerPanel = new JPanel(new GridLayout(1, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new FlowLayout());
        JPanel rightPanel = new JPanel(new FlowLayout());

        leftPanel.add(selectTableLabel);
        leftPanel.add(nameLabel1);
        leftPanel.add(jobSelectCombo);
        leftPanel.add(crewLabel1);
        leftPanel.add(crewSelectCombo);
        leftPanel.add(go);

        rightPanel.add(nameLabel2);
        rightPanel.add(nameCombo);
        rightPanel.add(dateLabel2);
        rightPanel.add(datePicker);
        rightPanel.add(statusLabel);
        rightPanel.add(statusCombo);
        rightPanel.add(go2);

        outerPanel.add(leftPanel);
        outerPanel.add(rightPanel);

        return outerPanel;
    }

    private static JPanel createBottomPanel() {

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("All", "0");

            }
        });
        moveMarkerUpButton = new JButton("Move Marker Up");
        moveMarkerUpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                moveMarkerUp(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");
                frame9.dispose();
                new EmployeeOvertimeSystem(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");

            }
        });
        moveMarkerDownButton = new JButton("Move Marker Down");
        moveMarkerDownButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                
                moveMarkerDown(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");
                frame9.dispose();
                new EmployeeOvertimeSystem(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");

            }
        });

        JPanel outerPanel = new JPanel(new GridLayout(1, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftpanel = new JPanel(new FlowLayout());
        JPanel rightpanel = new JPanel(new FlowLayout());

        outerPanel.add(leftpanel);
        outerPanel.add(rightpanel);

        rightpanel.add(summary);

        leftpanel.add(currentTableLabel);
        leftpanel.add(currentJobCombo);
        leftpanel.add(currentCrewCombo);
        leftpanel.add(moveMarkerDownButton);
        leftpanel.add(moveMarkerUpButton);

        return outerPanel;

    }

    private static JPanel createSummaryPanel(String typeIn, String crewIn) {

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel tablePanel = new JPanel();
        try {
            if (typeIn.equalsIgnoreCase("ForkliftDriver")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableForkliftDriver(crewIn);
            } else if (typeIn.equalsIgnoreCase("Operator")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableMachineOperator(crewIn);
            } else if (typeIn.equalsIgnoreCase("TeamLeader")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableTeamLeader(crewIn);
            } else if (typeIn.equalsIgnoreCase("QCInspector")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableQCInspector(crewIn);
            } else if (typeIn.equalsIgnoreCase("Packer")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTablePacker(crewIn);
            } else if (typeIn.equalsIgnoreCase("All")) {
                frame9.setTitle("Employee Overtime - Previous Entries");
                nameLabel1.setVisible(false);
                crewLabel1.setVisible(false);
                jobSelectCombo.setVisible(false);
                crewSelectCombo.setVisible(false);
                go.setVisible(false);
                model.setSelected(false);
                statusCombo.setSelectedItem("");
                nameLabel2.setVisible(false);
                dateLabel2.setVisible(false);
                nameCombo.setVisible(false);
                datePicker.setVisible(false);
                statusCombo.setVisible(false);
                statusLabel.setVisible(false);
                currentCrewCombo.setVisible(false);
                currentJobCombo.setVisible(false);
                currentTableLabel.setVisible(false);
                selectTableLabel.setVisible(false);
                summary.setVisible(false);
                go2.setText("back");
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryEntries();
                moveMarkerDownButton.setVisible(false);
                moveMarkerUpButton.setVisible(false);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    private static void fillCrewCombos(String typeIn, String crewIn) {

        try {

            System.out.println(typeIn + " + " + crewIn);

            String sql1 = "SELECT Employees.Name FROM Employees WHERE " + typeIn + " = '1' AND Crew = \'" + crewIn + "\' ORDER BY Name ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            while ((rs != null) && (rs.next())) {

                String name = rs.getString("Name");
                nameCombo.addItem(name);
            }

            pst1.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Names : " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    private static void insertEmployeeOvertime() {

        Date selectedDate = (Date) datePicker.getModel().getValue();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

        try {
            try {
                SQLiteConnection.EmployeeOvertimeInsert(
                        nameCombo.getSelectedItem() + "",
                        jobSelectCombo.getSelectedItem() + "",
                        crewSelectCombo.getSelectedItem() + "",
                        date,
                        statusCombo.getSelectedItem() + "",
                        1
                );
                 moveMarkerDown(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NumberFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }

    }

    public static void modifyOvertimeEmployee(int idIn, String nameIn, String typeIn, String crewIn) {

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

        JTextField name = new JTextField();
        JTextField status = new JTextField();

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        model2 = new UtilDateModel();
        model2.setSelected(true);
        datePanel2 = new JDatePanelImpl(model2);
        datePicker2 = new JDatePickerImpl(datePanel2);

        nameCombo = new JComboBox();

        // Name, Date Requested, Status
        JFrame modifyFrame = new JFrame("Modify Overtime");
        modifyFrame.setSize(400, 200);
        modifyFrame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new GridLayout(4, 2));
        outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        outerPanel.add(new JLabel("Name ", JLabel.CENTER));
        outerPanel.add(nameCombo);
        outerPanel.add(new JLabel("Date Required ", JLabel.CENTER));
        outerPanel.add(datePicker2);
        outerPanel.add(new JLabel("Status ", JLabel.CENTER));
        outerPanel.add(status);

        outerPanel.add(cancel);
        outerPanel.add(save);

        modifyFrame.add(outerPanel);
        modifyFrame.setVisible(true);

        fillCrewCombos(typeIn, crewIn);
    }

    public static void shiftListOfEmployees(String typeIn, String crewIn) {

        // Get Amount of People in Department
        int amountInDepartmentAndCrew = 0;

        try {
            amountInDepartmentAndCrew = SQLiteConnection.EmployeeOverTimeGetQueueLength(typeIn, crewIn);
            System.out.println("Amount In Department And Crew " + amountInDepartmentAndCrew);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        // 
        for (int i = 1; i < amountInDepartmentAndCrew; i++) {

            System.out.println("Number " + i);

            // UPDATE Employee.PositionInQueue FROM Employee WHERE positionInQueue = i, Job = jobIn and Crew = crewIn
            // position i = position i + 1;
            // position(amountInDepartmentAndCrew) = 1
        }

        // Refresh Summary Screen with Selected Job and Crew
    }

    public static void moveMarkerDown(String typeIn, String crewIn) {

        try {

            int crewCount = SQLiteConnection.EmployeeOvertimeGetCrewCount(typeIn, crewIn);
            System.out.println("Count " + crewCount);

            if (SQLiteConnection.EmployeeOvertimeGetQueuePosition(typeIn, crewIn) == crewCount - 1) {
                SQLiteConnection.EmployeeOvertimeSetMarkerPosition(typeIn, crewIn, 1);
            } else {
                SQLiteConnection.EmployeeOvertimeSetQueuePositionUp(typeIn, crewIn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void moveMarkerUp(String typeIn, String crewIn) {

        try {

            if (SQLiteConnection.EmployeeOvertimeGetQueuePosition(typeIn, crewIn) == 0) {
                int position = SQLiteConnection.EmployeeOvertimeGetCrewCount(typeIn, crewIn);
                System.out.println("New Count " + position);
                SQLiteConnection.EmployeeOvertimeSetMarkerPosition(typeIn, crewIn, position);
            } else {
                SQLiteConnection.EmployeeOvertimeSetQueuePositionDown(typeIn, crewIn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
