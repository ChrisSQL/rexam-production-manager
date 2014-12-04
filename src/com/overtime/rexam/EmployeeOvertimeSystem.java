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
import java.awt.Color;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.apache.pdfbox.pdmodel.PDDocument;

public class EmployeeOvertimeSystem {

    static JButton save, delete, home, reset, reset2, addNew, confirmDelete, update, confirmUpdate, go, go2, summary, summaryGrouped, moveMarkerUpButton, moveMarkerDownButton, moveQueuePositionDown, moveQueuePositionUp, changeEmployeePosition, manualEditEntry, goSort, crewDisplayButton, print, previousEntries;
    static JLabel employeeId, name, address, crew, departmentHead, processLeader, shiftManager, technician, leadHand, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector, phoneNumber,
            mobileNumber, nameLabel1, crewLabel1, nameLabel2, dateLabel2, statusLabel, currentTableLabel, selectTableLabel, currentPersonLabel, currentCrew, sortBy, displayCrew;
    static JTextField employeeIdText, nametext, addressText, phoneNumberText, mobileNumberText;
    static JTextField nameEditJTextField, absentJTextField, acceptedJTextField, dontCountJTextField, refusedJTextField, missATurnJTextField, setUpJTextField;

    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, nameCombo, crewSelectCombo, jobSelectCombo, statusCombo, currentJobCombo, currentCrewCombo, currentName, currentCrewCombo2, positionCombo, sortByCombo, crewOnlyCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;
    static UtilDateModel model, model2;
    static JDatePanelImpl datePanel, datePanel2;
    static JDatePickerImpl datePicker, datePicker2;

    public static String selectedName;

    public static void main(String args[]) {

        new EmployeeOvertimeSystem("Machine Operator", "A", "", "All");
    }

    public EmployeeOvertimeSystem(String typeIn, String crewIn, String sortByIn, String crewSelectIn) {

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

        nameLabel1 = new JLabel("Job : ");
        crewLabel1 = new JLabel("Crew : ");
        nameLabel2 = new JLabel("Name : ");
        dateLabel2 = new JLabel("Date Req. : ");
        statusLabel = new JLabel("Status : ");
        currentTableLabel = new JLabel("Current Table : ");
        selectTableLabel = new JLabel("Select Group - ");
        currentPersonLabel = new JLabel("Current Name - ");
        currentCrew = new JLabel("Crew : ");
        displayCrew = new JLabel("Display Only Crew : ");

        String[] crewArray = {"A", "B", "C", "D"};
        String[] jobsArray = {"Machine Operator", "Forklift Driver", "Packer", "QC Inspector", "Team Leader"};
        String[] statusArray = {"Absent", "Accepted", "DontCount", "MissATurn", "Refused", "SetUp"};
        String[] sortByArray = {"JobsTitle", "Employees.Name", "Crew", "Absent", "Accepted", "DontCount", "Refused", "MissATurn", "SetUp", "Total"};
        String[] crewOnlyArray = {"All", "A", "B", "C", "D"};
        sortByCombo = new JComboBox(sortByArray);
        sortByCombo.setSelectedItem(sortByIn);
        crewSelectCombo = new JComboBox(crewArray);
        jobSelectCombo = new JComboBox(jobsArray);
        statusCombo = new JComboBox(statusArray);
        crewOnlyCombo = new JComboBox(crewOnlyArray);
        crewOnlyCombo.setSelectedItem(crewSelectIn);
        nameCombo = new JComboBox();
        positionCombo = new JComboBox();
        currentCrewCombo = new JComboBox();
        currentCrewCombo2 = new JComboBox();
        currentCrewCombo.addItem(crewIn);
        currentCrewCombo2.addItem(crewIn);
        currentJobCombo = new JComboBox();
        currentJobCombo.addItem(typeIn);
        currentName = new JComboBox();

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selected = jobSelectCombo.getSelectedItem() + "";
                String crew = crewSelectCombo.getSelectedItem() + "";

                frame9.dispose();
                new EmployeeOvertimeSystem(selected, crew, "", crewOnlyCombo.getSelectedItem() + "");

            }
        });
        go2 = new JButton("Go");
        go2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    insertEmployeeOvertime();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame9.dispose();

                new EmployeeOvertimeSystem("All", "0", "", crewOnlyCombo.getSelectedItem() + "");

            }
        });

        home = new JButton("Back");
        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("Machine Operator", "A", "", "All");

            }
        });

        crewDisplayButton = new JButton("Display");
        crewDisplayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("All", "A", sortByCombo.getSelectedItem() + "", crewOnlyCombo.getSelectedItem() + "");

            }
        });

        sortBy = new JLabel("Sort By : ");
        goSort = new JButton("Go");
        goSort.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("All", "0", sortByCombo.getSelectedItem() + "", crewOnlyCombo.getSelectedItem() + "");

            }
        });
        // nameCombo = new JComboBox();
        frame9 = new JFrame("Employee Overtime System");
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

        frame9.add(createSummaryPanel(typeIn, crewIn, sortByCombo.getSelectedItem() + "", crewOnlyCombo.getSelectedItem() + ""), BorderLayout.CENTER);
        frame9.add(createBottomPanel(), BorderLayout.SOUTH);

        frame9.setVisible(true);
        manualEditEntry.setVisible(false);
        crewOnlyCombo.setVisible(false);
        displayCrew.setVisible(false);
        sortBy.setVisible(false);
        sortByCombo.setVisible(false);
        goSort.setVisible(false);

        if (typeIn.equals("Previous Entries")) {
            moveMarkerDownButton.setVisible(false);
            moveMarkerUpButton.setVisible(false);
            changeEmployeePosition.setVisible(false);
        }

        if (!typeIn.equals("All")) {
            jobSelectCombo.setSelectedItem(typeIn);
            crewSelectCombo.setSelectedItem(crewIn);

            fillCrewCombos(typeIn, crewIn);
        } else {
            moveMarkerDownButton.setVisible(false);
            moveMarkerUpButton.setVisible(false);
            summary.setVisible(false);
            manualEditEntry.setVisible(true);
            displayCrew.setVisible(true);
            crewOnlyCombo.setVisible(true);
            sortBy.setVisible(true);
            sortByCombo.setVisible(true);
            goSort.setVisible(true);
            // summaryGrouped.setVisible(false);
            changeEmployeePosition.setVisible(false);
        }

//        if (typeIn.equals("") || typeIn.equals("Operator") || typeIn.equals("ForkLiftDriver") || typeIn.equals("QCInspector") || typeIn.equals("TeamLeader")) {
//
//        } else {
//
//            fillCrewCombos(typeIn, crewIn);
//
//        }
        SQLiteConnection.AnalyticsUpdate("EmployeeOvertimeSystem");

        System.out.println("Current Name : " + selectedName);
        nameCombo.setSelectedItem(selectedName);
        currentName.addItem(selectedName);

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
        leftPanel.add(sortBy);
        leftPanel.add(sortByCombo);
        leftPanel.add(goSort);

        rightPanel.add(nameLabel2);
        rightPanel.add(nameCombo);
        rightPanel.add(dateLabel2);
        rightPanel.add(datePicker);
        rightPanel.add(statusLabel);
        rightPanel.add(statusCombo);
        rightPanel.add(go2);

        rightPanel.add(displayCrew);
        rightPanel.add(crewOnlyCombo);
        rightPanel.add(crewDisplayButton);

        home.setVisible(false);
        displayCrew.setVisible(false);
        crewDisplayButton.setVisible(false);

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
                new EmployeeOvertimeSystem("All", "0", sortByCombo.getSelectedItem() + "", crewOnlyCombo.getSelectedItem() + "");

            }
        });

        print = new JButton("Overtime Lists");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                print();

            }
        });

        previousEntries = new JButton("Previous Entries");
        previousEntries.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("Previous Entries", "A", "", "A");

            }
        });

        manualEditEntry = new JButton("Edit Employee");
        manualEditEntry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                manualEditSummary(nameCombo.getSelectedItem() + "");

            }
        });

        crewDisplayButton = new JButton("Display");
        crewDisplayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                manualEditSummary(nameCombo.getSelectedItem() + "");

            }
        });

        summaryGrouped = new JButton("Summary Grouped");
        summaryGrouped.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();
                new EmployeeOvertimeSystem("Grouped", "0", "", crewOnlyCombo.getSelectedItem() + "");

            }
        });

        changeEmployeePosition = new JButton("Change Employee Queue Position");
        changeEmployeePosition.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                moveEmployeeFrame(nameCombo.getSelectedItem() + "", crewSelectCombo.getSelectedItem() + "");

            }
        });

        moveMarkerUpButton = new JButton("Move Marker Up");
        moveMarkerUpButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                moveMarkerUp(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");
                frame9.dispose();
                new EmployeeOvertimeSystem(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "", "", crewOnlyCombo.getSelectedItem() + "");

            }
        });
        moveMarkerDownButton = new JButton("Move Marker Down");
        moveMarkerDownButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                moveMarkerDown(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");
                frame9.dispose();
                new EmployeeOvertimeSystem(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "", "", crewOnlyCombo.getSelectedItem() + "");

            }
        });

        JPanel outerPanel = new JPanel(new GridLayout(1, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new GridLayout(1, 1));

        JPanel leftTopPanel = new JPanel(new FlowLayout());
//        leftTopPanel.add(currentTableLabel);
//        leftTopPanel.add(currentJobCombo);
//        leftTopPanel.add(currentCrewCombo);
//        leftTopPanel.add(moveMarkerDownButton);
//        leftTopPanel.add(moveMarkerUpButton);

        JPanel rightpanel = new JPanel(new FlowLayout());

        leftPanel.add(leftTopPanel);

        outerPanel.add(leftPanel);
        outerPanel.add(rightpanel);

        rightpanel.add(changeEmployeePosition);
        rightpanel.add(previousEntries);
        rightpanel.add(print);
        rightpanel.add(summary);
        rightpanel.add(manualEditEntry);
        rightpanel.add(home);
        //rightpanel.add(summaryGrouped);

        return outerPanel;

    }

    private static JPanel createSummaryPanel(String typeIn, String crewIn, String sortByIn, String viewCrewIn) {

        JPanel outerPanel = new JPanel(new BorderLayout());

        int position = 0;
        try {
            position = SQLiteConnection.EmployeeOvertimeGetQueuePosition(typeIn, crewIn);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        JTable tablePanel = new JTable();
        tablePanel.setAutoCreateRowSorter(true);

        try {
            if (typeIn.equalsIgnoreCase("Forklift Driver")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableForkliftDriver(crewIn);
            } else if (typeIn.equalsIgnoreCase("Machine Operator")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableMachineOperator(crewIn);
            } else if (typeIn.equalsIgnoreCase("Team Leader")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableTeamLeader(crewIn);
            } else if (typeIn.equalsIgnoreCase("QC Inspector")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableQCInspector(crewIn);
            } else if (typeIn.equalsIgnoreCase("Packer")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTablePacker(crewIn);
            } else if (typeIn.equalsIgnoreCase("Grouped")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableGrouped();
            } else if (typeIn.equalsIgnoreCase("Previous Entries")) {
                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryPreviousEntries();

                model.setSelected(false);
                statusCombo.setSelectedItem("");
                nameLabel2.setVisible(false);
                dateLabel2.setVisible(false);
                nameCombo.setVisible(false);
                datePicker.setVisible(false);
                statusCombo.setVisible(false);
                statusLabel.setVisible(false);
                go2.setVisible(false);

                currentCrewCombo.setVisible(false);
                currentJobCombo.setVisible(false);
                currentTableLabel.setVisible(false);
                selectTableLabel.setVisible(false);

                changeEmployeePosition.setVisible(false);
                manualEditEntry.setVisible(false);

                moveMarkerDownButton.setVisible(false);
                moveMarkerUpButton.setVisible(false);

                home.setVisible(true);

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
                manualEditEntry.setVisible(true);
                home.setVisible(true);
                crewDisplayButton.setVisible(true);
                changeEmployeePosition.setVisible(false);
                go2.setVisible(false);
                sortBy.setVisible(true);
                sortByCombo.setVisible(true);
                sortByCombo.setSelectedItem(sortByIn);

                System.out.println("Sort By In : " + sortByIn);

                tablePanel = SQLiteConnection.EmployeeOvertimeSummaryEntries(sortByIn, crewOnlyCombo.getSelectedItem() + "");

                moveMarkerDownButton.setText("");
                moveMarkerUpButton.setText("");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JTableHeader header = tablePanel.getTableHeader();

        JPanel tableOuterPanel = new JPanel(new BorderLayout());
        tableOuterPanel.add(tablePanel, BorderLayout.CENTER);
        tableOuterPanel.add(header, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(tableOuterPanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    private static void fillCrewCombos(String typeIn, String crewIn) {

        try {

            System.out.println(typeIn + " + " + crewIn);

            String sql1 = "SELECT Employees.Name FROM Employees WHERE JobsTitle = \'" + typeIn + "\' AND Crew = \'" + crewIn + "\' ORDER BY Name ASC";
            System.out.println("SQLNAME : " + sql1);
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

        try {

            System.out.println(typeIn + " + " + crewIn);

            String sql1 = "SELECT COUNT(OvertimeQueuePosition) AS COUNT "
                    + "FROM Employees "
                    + "WHERE JobsTitle = \'" + typeIn + "\' "
                    + "AND Crew = \'" + crewIn + "\'";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            int count = rs.getInt("COUNT");

            for (int i = 0; i < count; i++) {

                positionCombo.addItem(i + 1);

            }

            //positionCombo.addItem(count);
            pst1.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Names : " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();

        }

    }

    private static void fillNameCombo() {

        try {

            String sql1 = "SELECT Employees.Name FROM Employees";
            System.out.println("SQLNAME : " + sql1);
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

    private static void insertEmployeeOvertime() throws SQLException {

        System.out.println("insertEmployeeOvertime");

        // Insert into EmployeeOvertime2
        String status = statusCombo.getSelectedItem() + "";
        Date selectedDate = (Date) datePicker.getModel().getValue();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

        // Does Record Exist
        // Yes Update
        // No Insert
        String name = nameCombo.getSelectedItem() + "";
        String job = jobSelectCombo.getSelectedItem() + "";
        String crew = crewSelectCombo.getSelectedItem() + "";
        status = statusCombo.getSelectedItem() + "";

        if (SQLiteConnection.EmployeeOvertimeNameEntryExists(name)) {

            System.out.println("Employee Exists");

            // Update
            SQLiteConnection.EmployeeOvertimeIncrement(nameCombo.getSelectedItem() + "", statusCombo.getSelectedItem() + "");
            moveMarkerDown(currentJobCombo.getSelectedItem() + "", currentCrewCombo.getSelectedItem() + "");

        } else {

            // Insert
            System.out.println("Else Statement ");

            SQLiteConnection.EmployeeOvertimeInsertNewEmployee(
                    nameCombo.getSelectedItem() + "",
                    jobSelectCombo.getSelectedItem() + "",
                    crewSelectCombo.getSelectedItem() + "",
                    statusCombo.getSelectedItem() + "");

        }

        // Insert into Entries Screen
        SQLiteConnection.EmployeeOvertimeInsert(name, job, crew, date, status);

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

//        System.out.println("TypeIn 99 : " + typeIn);
//        System.out.println("crewIn 99 : " + crewIn);
//
//        try {
//
//            int crewCount = SQLiteConnection.EmployeeOvertimeGetCrewCount(typeIn, crewIn);
//            System.out.println("Count " + crewCount);
//
//            if (SQLiteConnection.EmployeeOvertimeGetQueuePosition(typeIn, crewIn) == crewCount - 1) {
//                SQLiteConnection.EmployeeOvertimeSetMarkerPosition(typeIn, crewIn, 1);
//            } else {
//                SQLiteConnection.EmployeeOvertimeSetQueuePositionUp(typeIn, crewIn);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public static void moveMarkerUp(String typeIn, String crewIn) {

//        try {
//            int crewCount = SQLiteConnection.EmployeeOvertimeGetCrewCount(typeIn, crewIn);
//
//            if (SQLiteConnection.EmployeeOvertimeGetQueuePosition(typeIn, crewIn) == 0) {
//                int position = SQLiteConnection.EmployeeOvertimeGetCrewCount(typeIn, crewIn);
//                System.out.println("New Count " + position);
//                SQLiteConnection.EmployeeOvertimeSetMarkerPosition(typeIn, crewIn, crewCount);
//            } else {
//                SQLiteConnection.EmployeeOvertimeSetQueuePositionDown(typeIn, crewIn);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public static void setSelectedName(String selectedName) {
        EmployeeOvertimeSystem.selectedName = selectedName;
    }

    public static void moveEmployeeFrame(String nameIn, String crewIn) {

        int count = 15;
        JFrame moveFrame = new JFrame("Move Employee");

        String job = jobSelectCombo.getSelectedItem() + "";
        try {
            count = SQLiteConnection.EmployeeOvertimeGetCrewCount(job, crewIn);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
        }

        JButton cancel, confirm;

        positionCombo = new JComboBox();
        nameCombo = new JComboBox();

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                moveFrame.dispose();

            }
        });
        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SQLiteConnection.EmployeeOvertimeSetQueuePosition(nameCombo.getSelectedItem() + "", jobSelectCombo.getSelectedItem() + "", crewIn, Integer.valueOf(positionCombo.getSelectedItem() + ""));
                    moveFrame.dispose();
                    frame9.dispose();
                    new EmployeeOvertimeSystem(jobSelectCombo.getSelectedItem() + "", crewIn, "", crewOnlyCombo.getSelectedItem() + "");
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        moveFrame.setSize(350, 180);
        moveFrame.setLocationRelativeTo(null);
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel centerPanel = new JPanel(new GridLayout(2, 2));
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        centerPanel.add(new JLabel("Move : ", JLabel.CENTER));
        centerPanel.add(nameCombo);
        centerPanel.add(new JLabel("To Position : ", JLabel.CENTER));
        centerPanel.add(positionCombo);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(cancel);
        bottomPanel.add(confirm);
        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        moveFrame.add(outerPanel);
        moveFrame.setVisible(true);
        System.out.println("Job - " + job);
        System.out.println("nameIN - " + nameIn);
        System.out.println("CrewIN - " + crewIn);
        fillCrewCombos(job, crewIn);
        nameCombo.setSelectedItem(nameIn);

    }

    public static void manualEditSummary(String nameIn) {

        JButton cancelEdit, saveEdit;

        absentJTextField = new JTextField();
        acceptedJTextField = new JTextField();
        dontCountJTextField = new JTextField();
        refusedJTextField = new JTextField();
        missATurnJTextField = new JTextField();
        setUpJTextField = new JTextField();

        nameCombo = new JComboBox();
        fillNameCombo();
        nameCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fillManualEditJTextFields(nameCombo.getSelectedItem() + "");

            }
        });
        cancelEdit = new JButton("Cancel");
        saveEdit = new JButton("Save");

        JFrame manualEditFrame = new JFrame("Manual Edit ");
        manualEditFrame.setSize(300, 300);
        manualEditFrame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel centerPanel = new JPanel(new GridLayout(7, 2));
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        centerPanel.add(new JLabel("Name :", JLabel.CENTER));
        centerPanel.add(nameCombo);

        centerPanel.add(new JLabel("Absent :", JLabel.CENTER));
        centerPanel.add(absentJTextField);

        centerPanel.add(new JLabel("Accepted :", JLabel.CENTER));
        centerPanel.add(acceptedJTextField);

        centerPanel.add(new JLabel("Dont Count :", JLabel.CENTER));
        centerPanel.add(dontCountJTextField);

        centerPanel.add(new JLabel("Refused :", JLabel.CENTER));
        centerPanel.add(refusedJTextField);

        centerPanel.add(new JLabel("Miss A Turn :", JLabel.CENTER));
        centerPanel.add(missATurnJTextField);

        centerPanel.add(new JLabel("Set Up :", JLabel.CENTER));
        centerPanel.add(setUpJTextField);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);

        bottomPanel.add(cancelEdit);
        bottomPanel.add(saveEdit);

        outerPanel.add(centerPanel, BorderLayout.CENTER);

        outerPanel.add(bottomPanel, BorderLayout.SOUTH);

        manualEditFrame.add(outerPanel);
        manualEditFrame.setVisible(true);

        fillManualEditJTextFields(nameCombo.getSelectedItem() + "");
    }

    public static void fillManualEditJTextFields(String nameIn) {

        Object[] numbers = SQLiteConnection.EmployeeOvertimeGetEmployeeSummary(nameCombo.getSelectedItem() + "");

        absentJTextField.setText(numbers[1] + "");
        acceptedJTextField.setText(numbers[2] + "");
        dontCountJTextField.setText(numbers[3] + "");
        refusedJTextField.setText(numbers[4] + "");
        missATurnJTextField.setText(numbers[5] + "");
        setUpJTextField.setText(numbers[6] + "");

    }

    public static void print() {

        JFrame printFrame = new JFrame("Overtime Lists");
        String[] crewArrayPrint = {"A", "B", "C", "D"};
        JComboBox crewComboPrint = new JComboBox(crewArrayPrint);

        JButton cancel, print;
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                printFrame.dispose();

            }
        });
        print = new JButton("Print");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                printFrame.dispose();

                try {
                    printOvertimeLists(crewComboPrint.getSelectedItem() + "");
                } catch (JRException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PrinterException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeOvertimeSystem.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        printFrame.setSize(300, 135);
        printFrame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        centerPanel.add(new JLabel("Select Crew : ", JLabel.CENTER));
        centerPanel.add(crewComboPrint);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(cancel);
        bottomPanel.add(print);

        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        printFrame.add(outerPanel);
        printFrame.setVisible(true);
    }

    public static void printOvertimeLists(String crewIn) throws FileNotFoundException, JRException, IOException, PrinterException, SQLException {

        Connection conn = SQLiteConnection.Connect();
        
        System.out.println("Printing Overtime List for Crew : " + crewIn);

        File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/overtime/rexam/OvertimeLists2.jrxml");
        InputStream stream = new FileInputStream(file);
        JasperDesign design = JRXmlLoader.load(stream);
        JasperReport report = JasperCompileManager.compileReport(design);

        Map<String, Object> params = new HashMap<String, Object>();

        params.put("Crew", crewIn); // note here you can add parameters which
        // would be utilized by the report

        JasperPrint print = JasperFillManager.fillReport(report, params, conn);
        JasperExportManager.exportReportToPdfFile(print, "OvertimeList" + crewIn + ".pdf");

        PDDocument pdf = PDDocument.load("OvertimeList" + crewIn + ".pdf");
        pdf.print();
        pdf.close();

        conn.close();

    }

}
