package com.overtime.rexam;

import com.productiontrackingscreens.rexam.*;
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
import javax.swing.text.PlainDocument;

import com.database.rexam.SQLiteConnection;

public class EmployeeOvertimeSystem {

    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate, go;
    static JLabel employeeId, name, address, crew, departmentHead, processLeader, shiftManager, technician, leadHand, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector, phoneNumber,
            mobileNumber;
    static JTextField employeeIdText, nametext, addressText, phoneNumberText, mobileNumberText;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, nameCombo, crewSelectCombo, jobSelectCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;

    public static void main(String args[]) {

        new EmployeeOvertimeSystem(1, 1);

    }

    public EmployeeOvertimeSystem(int id, int view) {

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

        String[] crewArray = {"A", "B", "C", "D"};
        String[] jobsArray = {"ForkLiftDriver", "Operator", "Packer", "QCInspector", "TeamLeader"};
        crewSelectCombo = new JComboBox(crewArray);
        jobSelectCombo = new JComboBox(jobsArray);
        go = new JButton("Go");

        // nameCombo = new JComboBox();
        frame9 = new JFrame("Employee List");
        // frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.setSize(1500, 768);
        frame9.setLocationRelativeTo(null);
        // frame9.setResizable(false);

        phoneNumberText = new JTextField();
        mobileNumberText = new JTextField();

        // ///////////
        if (view == 1) {

            frame9.add(createTopPanel(), BorderLayout.NORTH);
            frame9.add(createSummaryPanel(), BorderLayout.CENTER);
        } else if (view == 2) {

        } else if (view == 3) {

            frame9.add(createSummaryPanel(), BorderLayout.CENTER);

        }

        frame9.setVisible(true);

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

        JPanel outerPanel = new JPanel(new FlowLayout());
        outerPanel.setBorder(new EmptyBorder(10, 100, 10, 100));

//        outerPanel.add(reset);
//        outerPanel.add(addNew);      
//        outerPanel.add(delete);
//        outerPanel.add(update);
        outerPanel.add(new JLabel("Job "));
        outerPanel.add(jobSelectCombo);
        outerPanel.add(new JLabel("Crew "));
        outerPanel.add(crewSelectCombo);
        outerPanel.add(go);

        return outerPanel;
    }

    private static JPanel createSummaryPanel() {

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel tablePanel = new JPanel();
        try {
            tablePanel = SQLiteConnection.EmployeeOvertimeSummaryTableTeamLeader("B");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    private static void fillCrewCombos() {

        // Crew
        try {

            String sql = "SELECT Crew.CrewName FROM Crew";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("CrewName");
                System.out.println(crewName);
                crewCombo.addItem(crewName);
            }

            pst.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("333 : " + e.getClass().getName() + ": " + e.getMessage());

        }

        // Jobs
        try {

            String sql = "SELECT Jobs.JobsTitle FROM Jobs";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String jobTitle = rs.getString("JobsTitle");
                // jobTitleCombo.addItem(jobTitle);
            }

        } catch (Exception e) {

        }

    }

    public static void fillDeleteCombo() {

        // Crew
        try {

            String sql1 = "SELECT Employees.Name FROM Employees ORDER BY Name ASC";
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

}
