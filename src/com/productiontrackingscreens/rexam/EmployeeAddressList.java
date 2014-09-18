package com.productiontrackingscreens.rexam;

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

public class EmployeeAddressList {

    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate;
    static JLabel employeeId, name, address, crew, departmentHead, processLeader, shiftManager, technician, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector, phoneNumber,
            mobileNumber;
    static JTextField employeeIdText, nametext, addressText, phoneNumberText, mobileNumberText;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, nameCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;

    public static void main(String args[]) {

        new EmployeeAddressList(1, 1);

    }

    public EmployeeAddressList(int id, int view) {

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

        // nameCombo = new JComboBox();
        frame9 = new JFrame("Employee List");
        // frame9.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame9.setSize(1500, 768);
        frame9.setLocationRelativeTo(null);
        // frame9.setResizable(false);

        phoneNumberText = new JTextField();
        PlainDocument doc1 = (PlainDocument) phoneNumberText.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());

        mobileNumberText = new JTextField();
        PlainDocument doc2 = (PlainDocument) mobileNumberText.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());

        // ///////////
        if (view == 1) {

            frame9.add(createTopPanel(), BorderLayout.NORTH);
            frame9.add(createSummaryPanel(), BorderLayout.CENTER);
        } else if (view == 2) {

        } else if (view == 3) {

            frame9.add(createSummaryPanel(), BorderLayout.CENTER);

        }

        frame9.setVisible(true);
    }

    private static JPanel createTopPanel() {

        employeeId = new JLabel("Employee ID :", SwingConstants.CENTER);
        name = new JLabel("Name :", SwingConstants.CENTER);
        address = new JLabel("Address :", SwingConstants.CENTER);
        crew = new JLabel("Crew :", SwingConstants.CENTER);
        departmentHead = new JLabel("Department Head :", SwingConstants.CENTER);
        shiftManager = new JLabel("Shift Manager :", SwingConstants.CENTER);
        technician = new JLabel("Technician :", SwingConstants.CENTER);
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
        operatorJCheckBox = new JCheckBox();
        engineerJCheckBox = new JCheckBox();
        packerJCheckBox = new JCheckBox();
        qcInspectorJCheckBox = new JCheckBox();

        forkliftDriverJCheckBox = new JCheckBox();
        processLeaderJCheckBox = new JCheckBox();
        toolmakerJCheckBox = new JCheckBox();
        fitterJCheckBox = new JCheckBox();
        electricianJCheckBox = new JCheckBox();

        addNew = new JButton("Add New");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                createNewEmployee();

                employeeIdText.setText("");
                nametext.setText("");
                addressText.setText("");
                phoneNumberText.setText("");
                mobileNumberText.setText("");

                departmentHeadJCheckBox.setSelected(false);
                shiftManagerJCheckBox.setSelected(false);
                technicianJCheckBox.setSelected(false);
                operatorJCheckBox.setSelected(false);
                engineerJCheckBox.setSelected(false);
                packerJCheckBox.setSelected(false);
                qcInspectorJCheckBox.setSelected(false);
            }
        });

        reset = new JButton("Reset / Refresh");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                frame9.dispose();
                new EmployeeAddressList(1, 1);

            }
        });

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                frame9.dispose();
                updateEmployee();

            }
        });

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame9.dispose();

                try {
                    SQLiteConnection.EmployeeInsert(
                            SQLiteConnection.EmployeeGetHighestID() + 1, employeeIdText.getText(), nametext.getText(), addressText.getText(),
                            (String) crewCombo.getSelectedItem(), 
                            departmentHeadJCheckBox.isSelected(),
                            shiftManagerJCheckBox.isSelected(), 
                            technicianJCheckBox.isSelected(),
                            operatorJCheckBox.isSelected(), 
                            engineerJCheckBox.isSelected(),
                            packerJCheckBox.isSelected(), 
                            qcInspectorJCheckBox.isSelected(), 
                            forkliftDriverJCheckBox.isSelected(),
                            processLeaderJCheckBox.isSelected(),
                            toolmakerJCheckBox.isSelected(),
                            fitterJCheckBox.isSelected(),
                            electricianJCheckBox.isSelected(),
                            
                            phoneNumberText.getText(),
                            mobileNumberText.getText()
                    );

                    frame10.dispose();
                    new EmployeeAddressList(1, 1);

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
                deleteEmployee();

            }
        });

        JPanel outerPanel = new JPanel(new FlowLayout());
        outerPanel.setBorder(new EmptyBorder(10, 100, 10, 100));

        // outerPanel.add(employeeId);
        // outerPanel.add(employeeIdText);
        //
        // outerPanel.add(shiftManager);
        // outerPanel.add(shiftManagerJCheckBox);
        //
        // outerPanel.add(technician);
        // outerPanel.add(technicianJCheckBox);
        //
        // outerPanel.add(name);
        // outerPanel.add(nametext);
        //
        // outerPanel.add(operator);
        // outerPanel.add(operatorJCheckBox);
        //
        // outerPanel.add(engineer);
        // outerPanel.add(engineerJCheckBox);
        //
        // outerPanel.add(address);
        // outerPanel.add(addressText);
        //
        // outerPanel.add(packer);
        // outerPanel.add(packerJCheckBox);
        //
        // outerPanel.add(qcInspector);
        // outerPanel.add(qcInspectorJCheckBox);
        //
        // outerPanel.add(crew);
        // outerPanel.add(crewCombo);
        //
        // outerPanel.add(departmentHead);
        // outerPanel.add(departmentHeadJCheckBox);
        //
        // outerPanel.add(new JLabel());
        // outerPanel.add(new JLabel());
        //
        // outerPanel.add(phoneNumber);
        // outerPanel.add(phoneNumberText);
        //
        // outerPanel.add(new JLabel());
        // outerPanel.add(new JLabel());
        outerPanel.add(reset);
        outerPanel.add(addNew);

        // outerPanel.add(mobileNumber);
        // outerPanel.add(mobileNumberText);
        // outerPanel.add(new JLabel());
        // outerPanel.add(new JLabel());
        outerPanel.add(delete);
        outerPanel.add(update);

        return outerPanel;
    }

    private static JPanel createSummaryPanel() {

        JPanel outerPanel = new JPanel(new BorderLayout());

        JPanel tablePanel = new JPanel();
        try {
            tablePanel = SQLiteConnection.EmployeeSummaryTable();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        outerPanel.add(scrollPane);

        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        return outerPanel;
    }

    public static void setEmployeeById(int idIn) {

        Object[] array = new Object[15];
        try {
            array = SQLiteConnection.EmployeeReturnEntryById(idIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        currentId = Integer.valueOf(array[0] + "");
        employeeIdText.setText(array[1] + "");
        nametext.setText(array[2] + "");
        addressText.setText(array[3] + "");
        crewCombo.setSelectedItem(array[4]);

        departmentHeadJCheckBox.setSelected((boolean) array[5]);
        shiftManagerJCheckBox.setSelected((boolean) array[6]);
        technicianJCheckBox.setSelected((boolean) array[7]);
        operatorJCheckBox.setSelected((boolean) array[8]);
        engineerJCheckBox.setSelected((boolean) array[9]);
        packerJCheckBox.setSelected((boolean) array[10]);
        qcInspectorJCheckBox.setSelected((boolean) array[11]);

        phoneNumberText.setText(array[12] + "");
        mobileNumberText.setText(array[13] + "");

    }

    private static void createNewEmployee() {

        crewCombo = new JComboBox();
        fillCrewCombos();

        frame10 = new JFrame("New Employee");
        frame10.setSize(400, 650);
        frame10.setLocationRelativeTo(null);

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentId = 0;
                employeeIdText.setText("");
                nametext.setText("");
                addressText.setText("");
                crewCombo.setSelectedItem("A");

                departmentHeadJCheckBox.setSelected(false);
                shiftManagerJCheckBox.setSelected(false);
                technicianJCheckBox.setSelected(false);
                operatorJCheckBox.setSelected(false);
                engineerJCheckBox.setSelected(false);
                packerJCheckBox.setSelected(false);
                qcInspectorJCheckBox.setSelected(false);

                phoneNumberText.setText("");
                mobileNumberText.setText("");

            }
        });

        JPanel panel = new JPanel(new GridLayout(19, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(employeeId);
        panel.add(employeeIdText);

        panel.add(name);
        panel.add(nametext);

        panel.add(address);
        panel.add(addressText);

        panel.add(crew);
        panel.add(crewCombo);

        panel.add(phoneNumber);
        panel.add(phoneNumberText);

        panel.add(mobileNumber);
        panel.add(mobileNumberText);

        panel.add(shiftManager);
        panel.add(shiftManagerJCheckBox);

        panel.add(operator);
        panel.add(operatorJCheckBox);

        panel.add(packer);
        panel.add(packerJCheckBox);

        panel.add(departmentHead);
        panel.add(departmentHeadJCheckBox);

        panel.add(technician);
        panel.add(technicianJCheckBox);

        panel.add(engineer);
        panel.add(engineerJCheckBox);

        panel.add(qcInspector);
        panel.add(qcInspectorJCheckBox);

        panel.add(forkliftDriver);
        panel.add(forkliftDriverJCheckBox);

        panel.add(processLeader);
        panel.add(processLeaderJCheckBox);

        panel.add(toolmaker);
        panel.add(toolmakerJCheckBox);

        panel.add(fitter);
        panel.add(fitterJCheckBox);

        panel.add(electrician);
        panel.add(electricianJCheckBox);

        panel.add(reset2);
        panel.add(save);

        frame10.add(panel);
        frame10.setVisible(true);
    }

    private static void deleteEmployee() {

        nameCombo = new JComboBox();
        fillDeleteCombo();

        confirmDelete = new JButton("Confirm Delete");
        confirmDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    int id = SQLiteConnection.EmployeeReturnIdByName(nameCombo.getSelectedItem() + "");
                    frame9.dispose();
                    SQLiteConnection.EmployeeDelete(id);
                    frame11.dispose();
                    new EmployeeAddressList(1, 1);

                } catch (SQLException e1) {

                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame11 = new JFrame("Delete Employee");
        frame11.setSize(500, 100);
        frame11.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel(new GridLayout(1, 3));
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        panel1.add(new JLabel("Employee Name :", SwingConstants.CENTER));
        panel1.add(nameCombo);
        panel1.add(confirmDelete);

        frame11.add(panel1);
        frame11.setVisible(true);
    }

    public static void updateEmployee() {

        nameCombo = new JComboBox();
        nameCombo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                System.out.println("ComboBreaker");
                Object[] result = new Object[15];

                try {
                    result = SQLiteConnection.EmployeeReturnEntryByName(nameCombo.getSelectedItem() + "");

                    currentId = Integer.valueOf(result[0] + "");
                    employeeIdText.setText(result[1] + "");
                    nametext.setText(result[2] + "");
                    addressText.setText(result[3] + "");
                    crewCombo.setSelectedItem(result[4]);

                    departmentHeadJCheckBox.setSelected((boolean) result[5]);
                    shiftManagerJCheckBox.setSelected((boolean) result[6]);
                    technicianJCheckBox.setSelected((boolean) result[7]);
                    operatorJCheckBox.setSelected((boolean) result[8]);
                    engineerJCheckBox.setSelected((boolean) result[9]);
                    packerJCheckBox.setSelected((boolean) result[10]);
                    qcInspectorJCheckBox.setSelected((boolean) result[11]);
                    
                    forkliftDriverJCheckBox.setSelected((boolean) result[12]);
                    processLeaderJCheckBox.setSelected((boolean) result[13]);
                    toolmakerJCheckBox.setSelected((boolean) result[14]);
                    fitterJCheckBox.setSelected((boolean) result[15]);
                    electricianJCheckBox.setSelected((boolean) result[16]);

                    phoneNumberText.setText(result[17] + "");
                    mobileNumberText.setText(result[18] + "");
                    
                } catch (Exception e1) {

                    e1.printStackTrace();
                }

            }
        });
        crewCombo = new JComboBox();

        fillCrewCombos();
        fillDeleteCombo();

        confirmUpdate = new JButton("Confirm Update");
        confirmUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                try {

                    int id = SQLiteConnection.EmployeeReturnIdByName(nameCombo.getSelectedItem() + "");

                    System.out.println("name " + nametext.getText());

                    SQLiteConnection.EmployeeUpdate(
                            employeeIdText.getText(), 
                            nametext.getText(), 
                            addressText.getText(), 
                            crewCombo.getSelectedItem() + "",
                            departmentHeadJCheckBox.isSelected(), 
                            shiftManagerJCheckBox.isSelected(), 
                            technicianJCheckBox.isSelected(),
                            operatorJCheckBox.isSelected(), 
                            engineerJCheckBox.isSelected(), 
                            packerJCheckBox.isSelected(),
                            qcInspectorJCheckBox.isSelected(), 
                            forkliftDriverJCheckBox.isSelected(),
                            processLeaderJCheckBox.isSelected(),
                            toolmakerJCheckBox.isSelected(),
                            fitterJCheckBox.isSelected(),
                            electricianJCheckBox.isSelected(),
                            
                            phoneNumberText.getText(), 
                            mobileNumberText.getText(), id);

                    frame9.dispose();
                    frame10.dispose();
                    new EmployeeAddressList(1, 1);

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        frame10 = new JFrame("New Employee");
        frame10.setSize(400, 650);
        frame10.setLocationRelativeTo(null);

        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                currentId = 0;
                employeeIdText.setText("");
                nametext.setText("");
                addressText.setText("");
                crewCombo.setSelectedItem("A");

                departmentHeadJCheckBox.setSelected(false);
                shiftManagerJCheckBox.setSelected(false);
                technicianJCheckBox.setSelected(false);
                operatorJCheckBox.setSelected(false);
                engineerJCheckBox.setSelected(false);
                packerJCheckBox.setSelected(false);
                qcInspectorJCheckBox.setSelected(false);

                phoneNumberText.setText("");
                mobileNumberText.setText("");

            }
        });

        JPanel panel = new JPanel(new GridLayout(20, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        panel.add(new JLabel("Update Employee : ", SwingConstants.CENTER));
        panel.add(nameCombo);

        panel.add(employeeId);
        panel.add(employeeIdText);

        panel.add(new JLabel("New Name : ", SwingConstants.CENTER));
        panel.add(nametext);

        panel.add(address);
        panel.add(addressText);

        panel.add(crew);
        panel.add(crewCombo);

        panel.add(phoneNumber);
        panel.add(phoneNumberText);

        panel.add(mobileNumber);
        panel.add(mobileNumberText);

        panel.add(shiftManager);
        panel.add(shiftManagerJCheckBox);

        panel.add(operator);
        panel.add(operatorJCheckBox);

        panel.add(packer);
        panel.add(packerJCheckBox);

        panel.add(departmentHead);
        panel.add(departmentHeadJCheckBox);

        panel.add(technician);
        panel.add(technicianJCheckBox);

        panel.add(engineer);
        panel.add(engineerJCheckBox);

        panel.add(qcInspector);
        panel.add(qcInspectorJCheckBox);
      
        panel.add(forkliftDriver);
        panel.add(forkliftDriverJCheckBox);

        panel.add(processLeader);
        panel.add(processLeaderJCheckBox);

        panel.add(toolmaker);
        panel.add(toolmakerJCheckBox);

        panel.add(fitter);
        panel.add(fitterJCheckBox);

        panel.add(electrician);
        panel.add(electricianJCheckBox);


        panel.add(reset2);
        panel.add(confirmUpdate);

        frame10.add(panel);
        frame10.setVisible(true);
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
