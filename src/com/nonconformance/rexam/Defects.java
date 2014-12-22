package com.nonconformance.rexam;

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
import java.awt.Frame;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;

public class Defects {

    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate;
    static JTextField defectGroupJTextField, defectTypeJTextField, areaOfOriginJTextField;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, teamLeaderJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, idCombo, jobCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;
    static JTextArea possibleCausesJTextArea, actionsJTextArea;

    public static void main(String args[]) {

        new Defects(1, 1);

    }

    public Defects(int id, int view) {

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
        frame9 = new JFrame("Defects List");
        // frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.setSize(1500, 768);
        frame9.setExtendedState(Frame.MAXIMIZED_BOTH);
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

        SQLiteConnection.AnalyticsUpdate("DefectsList");
    }

    private static JPanel createTopPanel() {

        defectGroupJTextField = new JTextField();
        defectTypeJTextField = new JTextField();
        areaOfOriginJTextField = new JTextField();

        possibleCausesJTextArea = new JTextArea();
        actionsJTextArea = new JTextArea();

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

                createNewDefects();

                defectGroupJTextField.setText("0");
                defectTypeJTextField.setText("0");
                areaOfOriginJTextField.setText("0");
                possibleCausesJTextArea.setText("0");
                actionsJTextArea.setText("0");

            }
        });

        reset = new JButton("Reset / Refresh");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                frame9.dispose();
                new Defects(1, 1);

            }
        });

        update = new JButton("View/Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                updateDefects();

            }
        });

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();

                try {
                    SQLiteConnection.DefectsInsert(
                            SQLiteConnection.DefectsGetHighestID() + 1,
                            defectGroupJTextField.getText(),
                            defectTypeJTextField.getText(),
                            areaOfOriginJTextField.getText(),
                            possibleCausesJTextArea.getText(),
                            actionsJTextArea.getText()
                    );

                    frame10.dispose();
                    new Defects(1, 1);
                    createNewDefects();

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
        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // frame9.dispose();
                deleteDefects();

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
            tablePanel = SQLiteConnection.DefectsSummaryTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    public static void setDefectsById(int idIn) {

        Object[] array = new Object[15];
        try {
            array = SQLiteConnection.DefectsReturnEntryById(idIn);
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
        possibleCausesJTextArea.setText(array[4] + "");
        actionsJTextArea.setText(array[5] + "");

    }

    private static void createNewDefects() {

        crewCombo = new JComboBox();
        jobCombo = new JComboBox();
        idCombo = new JComboBox();

        frame10 = new JFrame("New Defects");
        frame10.setSize(850, 400);
        frame10.setLocationRelativeTo(null);

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentId = 0;
                defectGroupJTextField.setText("0");
                defectTypeJTextField.setText("0");
                areaOfOriginJTextField.setText("0");
                possibleCausesJTextArea.setText("0");
                actionsJTextArea.setText("0");

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 4));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel(" "));
        panel.add(new JLabel(" "));

        panel.add(new JLabel("Defect Group : ", JLabel.CENTER));
        panel.add(defectGroupJTextField);

        panel.add(new JLabel("Defect Type : ", JLabel.CENTER));
        panel.add(defectTypeJTextField);

        panel.add(new JLabel("Area Of Origin : ", JLabel.CENTER));
        panel.add(areaOfOriginJTextField);

        panel.add(new JLabel("Possible Causes : ", JLabel.CENTER));
        panel.add(new JLabel(" "));

        panel.add(new JLabel("Actions in order of priority : ", JLabel.CENTER));
        panel.add(new JLabel(" "));

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout());
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(possibleCausesJTextArea);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(actionsJTextArea);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(reset2);
        bottomPanel.add(save);

        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame10.add(outerPanel);
        frame10.setVisible(true);
    }

    private static void deleteDefects() {

        idCombo = new JComboBox();
        fillDeleteComboNoSpace();

        confirmDelete = new JButton("Confirm Delete");
        confirmDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    frame9.dispose();
                    SQLiteConnection.DefectsDelete(Integer.valueOf(idCombo.getSelectedItem() + ""));
                    frame11.dispose();
                    new Defects(1, 1);

                } catch (SQLException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame11 = new JFrame("Delete Defects");
        frame11.setSize(500, 100);
        frame11.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel(new GridLayout(1, 3));
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel1.add(new JLabel("Defects ID :", SwingConstants.CENTER));
        panel1.add(idCombo);
        panel1.add(confirmDelete);

        frame11.add(panel1);
        frame11.setVisible(true);
    }

    public static void updateDefects() {

        idCombo = new JComboBox();
        fillDeleteCombo();
        idCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                Object[] result = new Object[15];

                try {
                    result = SQLiteConnection.DefectsReturnEntryById(Integer.valueOf(idCombo.getSelectedItem() + ""));

                    currentId = Integer.valueOf(result[0] + "");
                    defectGroupJTextField.setText(result[1] + "");
                    defectTypeJTextField.setText(result[2] + "");
                    areaOfOriginJTextField.setText(result[3] + "");
                    possibleCausesJTextArea.setText(result[4] + "");
                    actionsJTextArea.setText(result[5] + "");

                } catch (Exception e1) {

                    e1.printStackTrace();
                }

            }
        });

        confirmUpdate = new JButton("Confirm Update");
        confirmUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    frame9.dispose();

                    int id = SQLiteConnection.DefectsReturnIdByName(idCombo.getSelectedItem() + "");

                    System.out.println("name " + defectTypeJTextField.getText());

                    SQLiteConnection.DefectsUpdate(
                            defectGroupJTextField.getText(),
                            defectTypeJTextField.getText(),
                            areaOfOriginJTextField.getText(),
                            possibleCausesJTextArea.getText(),
                            actionsJTextArea.getText(),
                            Integer.valueOf(idCombo.getSelectedItem() + "")
                    );

                    frame9.dispose();
                    frame10.dispose();
                    new Defects(1, 1);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame10 = new JFrame("New Defects");
        frame10.setSize(850, 400);
        frame10.setLocationRelativeTo(null);

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentId = 0;
                idCombo.setSelectedItem("");
                defectGroupJTextField.setText("0");
                defectTypeJTextField.setText("0");
                areaOfOriginJTextField.setText("0");
                possibleCausesJTextArea.setText("0");
                actionsJTextArea.setText("0");

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 4));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel("Defects ID : ", JLabel.CENTER));
        panel.add(idCombo);

        panel.add(new JLabel("Defect Group : ", JLabel.CENTER));
        panel.add(defectGroupJTextField);

        panel.add(new JLabel("Defect Type : ", JLabel.CENTER));
        panel.add(defectTypeJTextField);

        panel.add(new JLabel("Area Of Origin : ", JLabel.CENTER));
        panel.add(areaOfOriginJTextField);

        panel.add(new JLabel("Possible Causes : ", JLabel.CENTER));
        panel.add(new JLabel(" "));

        panel.add(new JLabel("Actions in order of priority : ", JLabel.CENTER));
        panel.add(new JLabel(" "));

        // Center Panel
        JPanel centerPanel = new JPanel(new GridLayout());
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(possibleCausesJTextArea);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(actionsJTextArea);

        centerPanel.add(leftPanel);
        centerPanel.add(rightPanel);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(reset2);
        bottomPanel.add(confirmUpdate);

        outerPanel.add(panel, BorderLayout.NORTH);
        outerPanel.add(centerPanel, BorderLayout.CENTER);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame10.add(outerPanel);
        frame10.setVisible(true);
    }

    public static void fillDeleteCombo() {

        // Crew
        try {

            String sql1 = "SELECT Defects.ID FROM Defects ORDER BY ID ASC";
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

    public static void fillDeleteComboNoSpace() {

        // Crew
        try {

            String sql1 = "SELECT Defects.ID FROM Defects ORDER BY ID ASC";
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
