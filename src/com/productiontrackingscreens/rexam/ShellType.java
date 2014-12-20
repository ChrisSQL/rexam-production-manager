package com.productiontrackingscreens.rexam;

import com.help.rexam.*;
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
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class ShellType {

    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate;
    static JTextField defectGroupJTextField, defectTypeJTextField, areaOfOriginJTextField,  nameJTextfield;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, teamLeaderJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, idCombo, jobCombo, sectionJCombo, nameJCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;
    static JTextArea problemJTextArea, solutionJTextArea;

    public static void main(String args[]) {

        new ShellType(1, 1);

    }

    public ShellType(int id, int view) {

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

        // idCombo = new JComboBox();
        frame9 = new JFrame("ShellType List");
        // frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.setSize(600, 768);
        frame9.setLocationRelativeTo(null);
        // frame9.setResizable(false);

        // ///////////
        if (view == 1) {

            frame9.add(createTopPanel(), BorderLayout.NORTH);
            frame9.add(createSummaryPanel(), BorderLayout.CENTER);
        } else if (view == 2) {

        } else if (view == 3) {

            frame9.add(createSummaryPanel(), BorderLayout.CENTER);

        }

        frame9.setVisible(true);

        SQLiteConnection.AnalyticsUpdate("ShellType");
    }

    private static JPanel createTopPanel() {

        defectGroupJTextField = new JTextField();
        defectTypeJTextField = new JTextField();
        areaOfOriginJTextField = new JTextField();

        problemJTextArea = new JTextArea();
        solutionJTextArea = new JTextArea();

        departmentHeadJCheckBox = new JCheckBox();
        shiftManagerJCheckBox = new JCheckBox();
        technicianJCheckBox = new JCheckBox();
        teamLeaderJCheckBox = new JCheckBox();
        leadHandJCheckBox = new JCheckBox();
        operatorJCheckBox = new JCheckBox();
        engineerJCheckBox = new JCheckBox();
        packerJCheckBox = new JCheckBox();
        qcInspectorJCheckBox = new JCheckBox();

        ButtonGroup buttonGroup = new ButtonGroup();

        forkliftDriverJCheckBox = new JCheckBox();
        processLeaderJCheckBox = new JCheckBox();
        toolmakerJCheckBox = new JCheckBox();
        fitterJCheckBox = new JCheckBox();
        electricianJCheckBox = new JCheckBox();

        buttonGroup.add(departmentHeadJCheckBox);
        buttonGroup.add(shiftManagerJCheckBox);
        buttonGroup.add(technicianJCheckBox);
        buttonGroup.add(teamLeaderJCheckBox);
        buttonGroup.add(leadHandJCheckBox);
        buttonGroup.add(operatorJCheckBox);
        buttonGroup.add(engineerJCheckBox);
        buttonGroup.add(packerJCheckBox);
        buttonGroup.add(qcInspectorJCheckBox);
        buttonGroup.add(forkliftDriverJCheckBox);
        buttonGroup.add(processLeaderJCheckBox);
        buttonGroup.add(toolmakerJCheckBox);
        buttonGroup.add(fitterJCheckBox);
        buttonGroup.add(electricianJCheckBox);

        addNew = new JButton("Add New");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                createNewShellType();

                defectGroupJTextField.setText("0");
                defectTypeJTextField.setText("0");
                areaOfOriginJTextField.setText("0");
                problemJTextArea.setText("0");
                solutionJTextArea.setText("0");

            }
        });

        reset = new JButton("Reset / Refresh");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                frame9.dispose();
                new ShellType(1, 1);

            }
        });

        update = new JButton("View/Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                updateShellType();

            }
        });

        
        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // frame9.dispose();
                deleteShellType();

            }
        });

        JPanel outerPanel = new JPanel(new FlowLayout());
        outerPanel.setBorder(new EmptyBorder(10, 100, 10, 100));

        outerPanel.add(reset);
        outerPanel.add(addNew);
        outerPanel.add(delete);
        outerPanel.add(update);

        return outerPanel;
    }

    private static JPanel createSummaryPanel() {

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel tablePanel = new JPanel();
        try {
            tablePanel = SQLiteConnection.ShellTypeSummaryTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    public static void setShellTypeById(int idIn) {

        Object[] array = new Object[15];
        try {
            array = SQLiteConnection.ShellTypeReturnEntryById(idIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentId = Integer.valueOf(array[0] + "");
        defectGroupJTextField.setText(array[1] + "");
        defectTypeJTextField.setText(array[2] + "");
        areaOfOriginJTextField.setText(array[3] + "");
        problemJTextArea.setText(array[4] + "");
        solutionJTextArea.setText(array[5] + "");

    }

    private static void createNewShellType() {

        JTextField nameJTextfield = new JTextField("");

        frame10 = new JFrame("New ShellType");
        frame10.setSize(600, 130);
        frame10.setLocationRelativeTo(null);
        
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();

                try {

                    SQLiteConnection.ShellTypeInsert(
                            SQLiteConnection.ShellTypeGetHighestID() + 1,                            
                            nameJTextfield.getText()
                            
                    );        

                    frame10.dispose();
                    new ShellType(1, 1);
                    createNewShellType();

                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }

        }
        );

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                nameJTextfield.setText("");

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        // Top Panel
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel("ShellType Name : ", JLabel.CENTER));
        panel.add(nameJTextfield);


        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(reset2);
        bottomPanel.add(save);

        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame10.add(outerPanel);
        frame10.setVisible(true);


    }

    private static void deleteShellType() {

        nameJCombo = new JComboBox();
        fillNameCombo();

        confirmDelete = new JButton("Confirm Delete");
        confirmDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    frame9.dispose();
                    SQLiteConnection.ShellTypeDelete(nameJCombo.getSelectedItem() + "");
                    frame11.dispose();
                    new ShellType(1, 1);

                } catch (SQLException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame11 = new JFrame("Delete ShellType");
        frame11.setSize(600, 100);
        frame11.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel1.add(new JLabel("ShellType Name :", SwingConstants.CENTER));
        panel1.add(nameJCombo);
        panel1.add(confirmDelete);

        frame11.add(panel1);
        frame11.setVisible(true);
    }

    public static void updateShellType() {
        
        nameJTextfield = new JTextField();

        sectionJCombo = new JComboBox();
        nameJCombo = new JComboBox();
        nameJCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               nameJTextfield.setText(nameJCombo.getSelectedItem()+"");
                
            }
        });

        confirmUpdate = new JButton("Confirm Update");
        confirmUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    frame9.dispose();

                    System.out.println("name " + nameJCombo.getSelectedItem()+"");

                    SQLiteConnection.ShellTypeUpdate(
                            nameJTextfield.getText(),
                            nameJCombo.getSelectedItem()+""
                    );

                    frame9.dispose();
                    frame10.dispose();
                    new ShellType(1, 1);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame10 = new JFrame("New ShellType");
        frame10.setSize(600, 150);
        frame10.setLocationRelativeTo(null);

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                nameJTextfield.setText("");

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        // Top Panel
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel("Name : ", JLabel.CENTER));
        panel.add(nameJCombo);

        panel.add(new JLabel("New Name : ", JLabel.CENTER));
        panel.add(nameJTextfield);
        
        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(reset2);
        bottomPanel.add(confirmUpdate);

        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame10.add(outerPanel);
        frame10.setVisible(true);

        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame10.add(outerPanel);
        frame10.setVisible(true);

        fillNameCombo();      
    }

    public static void fillDeleteCombo() {

        // Crew
        try {

            String sql1 = "SELECT ShellType.ID FROM ShellType ORDER BY ID ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            while ((rs != null) && (rs.next())) {

                String name = rs.getString("ID");
                idCombo.addItem(name);
            }

            pst1.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Names : " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void fillNameCombo() {

        // Crew
        try {

            String sql1 = "SELECT ShellType.ShellTypeName FROM ShellType ORDER BY ShellTypeName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            while ((rs != null) && (rs.next())) {

                String name = rs.getString("ShellTypeName");
                nameJCombo.addItem(name);
            }

            pst1.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Names : " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void fillMenuNamesCombo() {

        // Crew
        try {

            String sql1 = "SELECT Analytics.Name FROM Analytics ORDER BY Name ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            while ((rs != null) && (rs.next())) {

                String name = rs.getString("Name");
                sectionJCombo.addItem(name);
            }

            pst1.close();
            rs.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Names : " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void fillDeleteComboNoSpace() {

        // Crew
        try {

            String sql1 = "SELECT ShellType.ID FROM ShellType ORDER BY ID ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setQueryTimeout(5);
            ResultSet rs = pst1.executeQuery();

            while ((rs != null) && (rs.next())) {

                String name = rs.getString("ID");
                idCombo.addItem(name);
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
