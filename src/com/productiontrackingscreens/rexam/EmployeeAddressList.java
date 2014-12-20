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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EmployeeAddressList {
    
    static JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate;
    static JLabel employeeId, name, address, crew, phoneExt, departmentHead, processLeader, shiftManager, technician, leadHand, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector, phoneNumber,
            mobileNumber;
    static JTextField employeeIdText, nametext, addressText, phoneExtText, phoneNumberText, mobileNumberText;
    static JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, teamLeaderJCheckBox, leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox,
            qcInspectorJCheckBox, forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
    static JComboBox crewCombo, nameAndId, nameCombo, jobCombo;
    static int currentId;
    static JFrame frame9, frame10, frame11;
    static JFileChooser fileChooser;
    
    public static void main(String args[]) {
        
        new EmployeeAddressList(1, 1);
//        try {
//            importFromExcel();
//        } catch (IOException ex) {
//            Logger.getLogger(EmployeeAddressList.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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
        
        SQLiteConnection.AnalyticsUpdate("EmployeeAddressList");
    }
    
    private static JPanel createTopPanel() {
        
        employeeId = new JLabel("Employee ID :", SwingConstants.CENTER);
        name = new JLabel("Name :", SwingConstants.CENTER);
        address = new JLabel("Address :", SwingConstants.CENTER);
        crew = new JLabel("Crew :", SwingConstants.CENTER);
        phoneExt = new JLabel("Phone Ext :", SwingConstants.CENTER);
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
        phoneExtText = new JTextField();
        phoneNumberText = new JTextField();
        mobileNumberText = new JTextField();
        
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

                createNewEmployee();
                
                employeeIdText.setText("0");
                nametext.setText("");
                addressText.setText("");
                phoneExtText.setText("");
                phoneNumberText.setText("");
                mobileNumberText.setText("");
                
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
                            SQLiteConnection.EmployeeGetHighestID() + 1,
                            employeeIdText.getText(),
                            nametext.getText(),
                            addressText.getText(),
                            jobCombo.getSelectedItem() + "",
                            (String) crewCombo.getSelectedItem(),
                            phoneExtText.getText(),
                            phoneNumberText.getText(),
                            mobileNumberText.getText()
                    );
                    
                    frame10.dispose();
                    new EmployeeAddressList(1, 1);
                    createNewEmployee();
                    
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
        phoneExtText.setText(array[5] + "");
        phoneNumberText.setText(array[6] + "");
        mobileNumberText.setText(array[7] + "");
        
    }
    
    private static void createNewEmployee() {
        
        crewCombo = new JComboBox();
        jobCombo = new JComboBox();
        fillCrewCombos();
        
        frame10 = new JFrame("New Employee");
        frame10.setSize(400, 330);
        frame10.setLocationRelativeTo(null);
        
        reset2 = new JButton("Reset");
        reset2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                currentId = 0;
                employeeIdText.setText("");
                nametext.setText("");
                addressText.setText("");
                phoneExtText.setText("");
                crewCombo.setSelectedItem("A");
                jobCombo.setSelectedItem("Machine Operator");
                phoneNumberText.setText("");
                mobileNumberText.setText("");
                
            }
        });
        
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        panel.add(employeeId);
        panel.add(employeeIdText);
        
        panel.add(name);
        panel.add(nametext);
        
        panel.add(address);
        panel.add(addressText);
        
        panel.add(crew);
        panel.add(crewCombo);
        
        panel.add(phoneExt);
        panel.add(phoneExtText);
        
        panel.add(phoneNumber);
        panel.add(phoneNumberText);
        
        panel.add(mobileNumber);
        panel.add(mobileNumberText);
        
        panel.add(new JLabel("Job : ", JLabel.CENTER));
        panel.add(jobCombo);
        
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
        jobCombo = new JComboBox();
        nameCombo.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                Object[] result = new Object[15];
                
                try {
                    result = SQLiteConnection.EmployeeReturnEntryByName(nameCombo.getSelectedItem() + "");
                    
                    currentId = Integer.valueOf(result[0] + "");
                    employeeIdText.setText(result[1] + "");
                    nametext.setText(result[2] + "");
                    addressText.setText(result[3] + "");
                    jobCombo.setSelectedItem((String) result[4]);
                    crewCombo.setSelectedItem(result[5]);
                    phoneExtText.setText(result[6] + "");
                    phoneNumberText.setText(result[7] + "");
                    mobileNumberText.setText(result[8] + "");
                    
                } catch (Exception e1) {
                    
                    e1.printStackTrace();
                }
                
            }
        });
        crewCombo = new JComboBox();
        
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
                            phoneExtText.getText(),
                            crewCombo.getSelectedItem() + "",
                            jobCombo.getSelectedItem() + "",
                            phoneNumberText.getText(),
                            mobileNumberText.getText(),
                            id
                    );
                    
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
        
        frame10 = new JFrame("Update Employee");
        frame10.setSize(400, 330);
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
                
                jobCombo.setSelectedItem("Machine Operator");
                
                phoneExtText.setText("");
                phoneNumberText.setText("");
                mobileNumberText.setText("");
                
            }
        });
        
        JPanel panel = new JPanel(new GridLayout(10, 2));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        panel.add(new JLabel("Update Employee : ", SwingConstants.CENTER));
        panel.add(nameCombo);
        
        panel.add(employeeId);
        panel.add(employeeIdText);
        
        panel.add(new JLabel("New Name : ", SwingConstants.CENTER));
        panel.add(nametext);
        
        panel.add(address);
        panel.add(addressText);
        
        panel.add(new JLabel("Job ", JLabel.CENTER));
        panel.add(jobCombo);
        
        panel.add(crew);
        panel.add(crewCombo);
        
        panel.add(phoneExt);
        panel.add(phoneExtText);
        
        panel.add(phoneNumber);
        panel.add(phoneNumberText);
        
        panel.add(mobileNumber);
        panel.add(mobileNumberText);
        
        panel.add(reset2);
        panel.add(confirmUpdate);
        
        frame10.add(panel);
        frame10.setVisible(true);
        
        fillCrewCombos();
        fillDeleteCombo();
    }
    
    private static void fillCrewCombos() {

        // Jobs
        try {
            
            String sql = "SELECT Jobs.JobsTitle FROM Jobs";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                
                String JobTitle = rs.getString("JobsTitle");
                jobCombo.addItem(JobTitle);
            }
            
            pst.close();
            rs.close();
            conn.close();
            
        } catch (Exception e) {
            System.err.println("333 : " + e.getClass().getName() + ": " + e.getMessage());
            
        }

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
    
    public static void importFromExcel() throws FileNotFoundException, IOException {
        
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
    //    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel Documents", "xlsx"));
        int result = fileChooser.showOpenDialog(frame9);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            ////////////////
            FileInputStream excelFile = new FileInputStream(selectedFile);
            HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
            HSSFSheet worksheet = workbook.getSheet("Employees_Details");

            // Create Array of Rows
            HSSFRow[] rows = new HSSFRow[158];
            
            for (int i = 0; i < 157; i++) {
                
                rows[i + 1] = worksheet.getRow(i);
                
            }
            
            String[] names = new String[158];
            HSSFCell[] hssCellsA = new HSSFCell[158];
            
            String[] jobs = new String[158];
            HSSFCell[] hssCellsB = new HSSFCell[158];
            
            String[] crew = new String[158];
            HSSFCell[] hssCellsC = new HSSFCell[158];
            
            String[] phone = new String[158];
            HSSFCell[] hssCellsD = new HSSFCell[158];
            
            String[] mobile = new String[158];
            HSSFCell[] hssCellsE = new HSSFCell[158];
            
            for (int i = 1; i < 158; i++) {
                
                hssCellsA[i + 1] = rows[i + 1].getCell(0);
                names[i] = hssCellsA[i + 1].getRichStringCellValue().getString();
                
                System.out.println("Name " + names[i]);
                
                hssCellsB[i + 1] = rows[i + 1].getCell(1);
                jobs[i] = hssCellsB[i + 1].getRichStringCellValue().getString();
                
                hssCellsC[i + 1] = rows[i + 1].getCell(2);
                crew[i] = hssCellsC[i + 1].getRichStringCellValue().getString();
                
                hssCellsD[i + 1] = rows[i + 1].getCell(3);
                phone[i] = hssCellsD[i + 1].getRichStringCellValue().getString();
                
                hssCellsE[i + 1] = rows[i + 1].getCell(4);
                mobile[i] = hssCellsE[i + 1].getRichStringCellValue().getString();
                
                try {
                    SQLiteConnection.EmployeeInsert(
                            SQLiteConnection.EmployeeGetHighestID() + 1,
                            "000",
                            names[i],
                            "NA",
                            jobs[i],
                            crew[i],
                            "000",
                            phone[i],
                            mobile[i]
                    );
                    
                    
                    
                    
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
               
                
            }
            
             new EmployeeAddressList(1, 1);
            
        }

        // Import into Database with matching dates if it doesnt exist for LinersAndShells, LinerDefects, EndCounts
        // If exist skip that individual section
    }
    
}
