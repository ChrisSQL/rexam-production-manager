package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import com.database.rexam.SQLiteConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

public class LinerUsageEntryScreen {

    static JButton add, find, next, previous, update, addNew, search, refresh, summary, delete;
    static JLabel dateLabel, linerNumberLabel, crewLabel, leadHandLabel, reasonlabel, quantityUsedLabel, partNumberLabel, Gun1Label, Gun2Label, Gun3Label,
            Gun4Label, Gun5Label, Gun6Label, Gun7Label, Gun8Label, commentsLabel;
    static JComboBox linerNumberCombo, crewCombo, leadHandCombo, reasonCombo;
    static JTextField quantityUsedTextField, partNumberTextField;
    static JCheckBox gun1CheckBox, gun2CheckBox, gun3CheckBox, gun4CheckBox, gun5CheckBox, gun6CheckBox, gun7CheckBox, gun8CheckBox;
    static JTextArea commentsTextArea;
    static int view, currentId;
    static Date selectedDate;

    static UtilDateModel model;
    JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;

    static JFrame frameSummary, frame10;

    public static void main(String[] args) {

        new LinerUsageEntryScreen(1, -1);

    }

    public LinerUsageEntryScreen(int id, int view) {

        

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

        Date date = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String year = modifiedDate.substring(0, 4);
        int yearInt = Integer.parseInt(year);
        String month = modifiedDate.substring(5, 7);
        int monthInt = Integer.parseInt(month) - 1;
        String day = modifiedDate.substring(8, 10);
        int dayInt = Integer.parseInt(day);
        model = new UtilDateModel();
        model.setDate(yearInt, monthInt, dayInt);
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        linerNumberCombo = new JComboBox();
        crewCombo = new JComboBox();
        leadHandCombo = new JComboBox();
        reasonCombo = new JComboBox();
        fillCombos();

        dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
        linerNumberLabel = new JLabel("Line Number : ", SwingConstants.CENTER);
        crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
        leadHandLabel = new JLabel("Lead Hand : ", SwingConstants.CENTER);
        reasonlabel = new JLabel("Reason : ", SwingConstants.CENTER);
        quantityUsedLabel = new JLabel("Quantity Used : ", SwingConstants.CENTER);
        partNumberLabel = new JLabel("Part Number : ", SwingConstants.CENTER);
        Gun1Label = new JLabel("Gun 1 : ", SwingConstants.CENTER);
        Gun2Label = new JLabel("Gun 2 : ", SwingConstants.CENTER);
        Gun3Label = new JLabel("Gun 3 : ", SwingConstants.CENTER);
        Gun4Label = new JLabel("Gun 4 : ", SwingConstants.CENTER);
        Gun5Label = new JLabel("Gun 5 : ", SwingConstants.CENTER);
        Gun6Label = new JLabel("Gun 6 : ", SwingConstants.CENTER);
        Gun7Label = new JLabel("Gun 7 : ", SwingConstants.CENTER);
        Gun8Label = new JLabel("Gun 8 : ", SwingConstants.CENTER);
        commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);

        quantityUsedTextField = new JTextField("0");
        PlainDocument doc2 = (PlainDocument) quantityUsedTextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        partNumberTextField = new JTextField("0");

        commentsTextArea = new JTextArea("NA");
        commentsTextArea.setLineWrap(true);
        commentsTextArea.setWrapStyleWord(true);

        gun1CheckBox = new JCheckBox();
        gun2CheckBox = new JCheckBox();
        gun3CheckBox = new JCheckBox();
        gun4CheckBox = new JCheckBox();
        gun5CheckBox = new JCheckBox();
        gun6CheckBox = new JCheckBox();
        gun7CheckBox = new JCheckBox();
        gun8CheckBox = new JCheckBox();

        JFrame frame10 = new JFrame();
        // frame10.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame10.setTitle("Liner Usage Entry");
        frame10.setSize(360, 700);
        frame10.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                // Check-Boxes
                int gun1 = 0, gun2 = 0, gun3 = 0, gun4 = 0, gun5 = 0, gun6 = 0, gun7 = 0, gun8 = 0;

                if (gun1CheckBox.isSelected() == true) {
                    gun1 = 1;
                }
                if (gun2CheckBox.isSelected() == true) {
                    gun2 = 1;
                }
                if (gun3CheckBox.isSelected() == true) {
                    gun3 = 1;
                }
                if (gun4CheckBox.isSelected() == true) {
                    gun4 = 1;
                }
                if (gun5CheckBox.isSelected() == true) {
                    gun5 = 1;
                }
                if (gun6CheckBox.isSelected() == true) {
                    gun6 = 1;
                }
                if (gun7CheckBox.isSelected() == true) {
                    gun7 = 1;
                }
                if (gun8CheckBox.isSelected() == true) {
                    gun8 = 1;
                }

                int quantityInt = Integer.parseInt(quantityUsedTextField.getText());

                try {
                    SQLiteConnection.LinerUsageInsert(
                            SQLiteConnection.LinerUsageGetHighestID() + 1, (String) linerNumberCombo.getSelectedItem(), date,
                            (String) crewCombo.getSelectedItem(), (String) leadHandCombo.getSelectedItem(), (String) reasonCombo.getSelectedItem(),
                            quantityInt, partNumberTextField.getText(), gun1, gun2, gun3, gun4, gun5, gun6, gun7, gun8,
                            commentsTextArea.getText()
                    );

                    frame10.dispose();
                    
                    try {
                    createSummaryScreen();
                    
                    // TODO Auto-generated method stub
                } catch (SQLException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }
        });

        find = new JButton("Find Record");
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {
                    selectedDate = (Date) datePicker.getModel().getValue();

                    Object[] array = new Object[16];
                    array = SQLiteConnection.LinerUsageReturnEntryByDate(selectedDate);

                    // String date = (String) array[1];
                    // need to do
                    if (array[0] == null) {
                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");
                    } else {

                        Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false, gun8 = false;

                        System.out.println("Array 8 " + array[8]);

                        int gun1Int = Integer.parseInt((String) array[8]);
                        int gun2Int = Integer.parseInt((String) array[9]);
                        int gun3Int = Integer.parseInt((String) array[10]);
                        int gun4Int = Integer.parseInt((String) array[11]);
                        int gun5Int = Integer.parseInt((String) array[12]);
                        int gun6Int = Integer.parseInt((String) array[13]);
                        int gun7Int = Integer.parseInt((String) array[14]);
                        int gun8Int = Integer.parseInt((String) array[15]);

                        if (gun1Int == 1) {
                            gun1 = true;
                        }
                        if (gun2Int == 1) {
                            gun2 = true;
                        }
                        if (gun3Int == 1) {
                            gun3 = true;
                        }
                        if (gun4Int == 1) {
                            gun4 = true;
                        }
                        if (gun5Int == 1) {
                            gun5 = true;
                        }
                        if (gun6Int == 1) {
                            gun6 = true;
                        }
                        if (gun7Int == 1) {
                            gun7 = true;
                        }
                        if (gun8Int == 1) {
                            gun8 = true;
                        }

                        currentId = (int) array[0];

                        linerNumberCombo.setSelectedItem(array[1]);
                        crewCombo.setSelectedItem(array[3]);
                        leadHandCombo.setSelectedItem(array[4]);
                        reasonCombo.setSelectedItem(array[5]);
                        quantityUsedTextField.setText((String) array[6]);
                        partNumberTextField.setText((String) array[7]);

                        gun1CheckBox.setSelected(gun1);
                        gun2CheckBox.setSelected(gun2);
                        gun3CheckBox.setSelected(gun3);
                        gun4CheckBox.setSelected(gun4);
                        gun5CheckBox.setSelected(gun5);
                        gun6CheckBox.setSelected(gun6);
                        gun7CheckBox.setSelected(gun7);
                        gun8CheckBox.setSelected(gun8);

                        commentsTextArea.setText((String) array[16]);
                    }

                    System.out.println("CurrentID " + currentId);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });

        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] array = SQLiteConnection.LinerUsageGetNextEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        currentId++;

                        String dateFormatted = (String) array[2];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false, gun8 = false;

                        linerNumberCombo.setSelectedItem(array[1]);
                        crewCombo.setSelectedItem(array[3]);
                        leadHandCombo.setSelectedItem(array[4]);
                        reasonCombo.setSelectedItem(array[5]);
                        quantityUsedTextField.setText((String) array[6]);
                        partNumberTextField.setText((String) array[7]);

                        int gun1Int = Integer.parseInt((String) array[8]);
                        int gun2Int = Integer.parseInt((String) array[9]);
                        int gun3Int = Integer.parseInt((String) array[10]);
                        int gun4Int = Integer.parseInt((String) array[11]);
                        int gun5Int = Integer.parseInt((String) array[12]);
                        int gun6Int = Integer.parseInt((String) array[13]);
                        int gun7Int = Integer.parseInt((String) array[14]);
                        int gun8Int = Integer.parseInt((String) array[15]);

                        if (gun1Int == 1) {
                            gun1 = true;
                        }
                        if (gun2Int == 1) {
                            gun2 = true;
                        }
                        if (gun3Int == 1) {
                            gun3 = true;
                        }
                        if (gun4Int == 1) {
                            gun4 = true;
                        }
                        if (gun5Int == 1) {
                            gun5 = true;
                        }
                        if (gun6Int == 1) {
                            gun6 = true;
                        }
                        if (gun7Int == 1) {
                            gun7 = true;
                        }
                        if (gun8Int == 1) {
                            gun8 = true;
                        }

                        gun1CheckBox.setSelected(gun1);
                        gun2CheckBox.setSelected(gun2);
                        gun3CheckBox.setSelected(gun3);
                        gun4CheckBox.setSelected(gun4);
                        gun5CheckBox.setSelected(gun5);
                        gun6CheckBox.setSelected(gun6);
                        gun7CheckBox.setSelected(gun7);
                        gun8CheckBox.setSelected(gun8);

                        commentsTextArea.setText((String) array[16]);
                    }

                    System.out.println("CurrentID " + currentId);

                    // Fill Boxes with results
                    // model.setDate(year2, month2, day2);
                    model.setSelected(true);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });

        previous = new JButton("Previous Record");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] array = SQLiteConnection.LinerUsageGetPreviousEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        currentId = currentId - 1;

                        String dateFormatted = (String) array[2];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false, gun8 = false;

                        int gun1Int = Integer.parseInt((String) array[8]);
                        int gun2Int = Integer.parseInt((String) array[9]);
                        int gun3Int = Integer.parseInt((String) array[10]);
                        int gun4Int = Integer.parseInt((String) array[11]);
                        int gun5Int = Integer.parseInt((String) array[12]);
                        int gun6Int = Integer.parseInt((String) array[13]);
                        int gun7Int = Integer.parseInt((String) array[14]);
                        int gun8Int = Integer.parseInt((String) array[15]);

                        if (gun1Int == 1) {
                            gun1 = true;
                        }
                        if (gun2Int == 1) {
                            gun2 = true;
                        }
                        if (gun3Int == 1) {
                            gun3 = true;
                        }
                        if (gun4Int == 1) {
                            gun4 = true;
                        }
                        if (gun5Int == 1) {
                            gun5 = true;
                        }
                        if (gun6Int == 1) {
                            gun6 = true;
                        }
                        if (gun7Int == 1) {
                            gun7 = true;
                        }
                        if (gun8Int == 1) {
                            gun8 = true;
                        }

                        gun1CheckBox.setSelected(gun1);
                        gun2CheckBox.setSelected(gun2);
                        gun3CheckBox.setSelected(gun3);
                        gun4CheckBox.setSelected(gun4);
                        gun5CheckBox.setSelected(gun5);
                        gun6CheckBox.setSelected(gun6);
                        gun7CheckBox.setSelected(gun7);
                        gun8CheckBox.setSelected(gun8);

                        linerNumberCombo.setSelectedItem(array[1]);
                        crewCombo.setSelectedItem(array[3]);
                        leadHandCombo.setSelectedItem(array[4]);
                        reasonCombo.setSelectedItem(array[5]);
                        quantityUsedTextField.setText((String) array[6]);
                        partNumberTextField.setText((String) array[7]);
                        commentsTextArea.setText((String) array[16]);

                    }

                    System.out.println(currentId);

                    // Fill Boxes with results
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // currentId = (int)array[0];
                // Set Date
                // Send in

            }
        });

        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int quantityInt = Integer.parseInt(quantityUsedTextField.getText());

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.LinerUsageUpdate(
                            (String) linerNumberCombo.getSelectedItem(), date, (String) crewCombo.getSelectedItem(),
                            (String) leadHandCombo.getSelectedItem(), (String) reasonCombo.getSelectedItem(), quantityInt,
                            partNumberTextField.getText(), gun1CheckBox.isSelected(), gun2CheckBox.isSelected(), gun3CheckBox.isSelected(),
                            gun4CheckBox.isSelected(), gun5CheckBox.isSelected(), gun6CheckBox.isSelected(), gun7CheckBox.isSelected(),
                            gun8CheckBox.isSelected(), commentsTextArea.getText(), currentId
                    );

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                frame10.dispose();
                try {
                    createSummaryScreen();
                    
                    // TODO Auto-generated method stub
                } catch (SQLException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

               try {
                    LinerUsageEntryScreen.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame10.dispose();
            }
        });

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new LinerUsageEntryScreen(1, -1);
                frame10.dispose();
            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame10.dispose();
                try {
                    LinerUsageEntryScreen.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    // Delete CurrentID
                    SQLiteConnection.LinerUsageDelete(currentId);

                    // Create Summary Screen
                    frameSummary.dispose();
                    frame10.dispose();
                    
                    createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LinerDataEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        // BorderLayout - ButtonsPanels North and South - Gridlayout Middle
        // Top Buttons Panel
        JPanel buttonPanelTop = new JPanel(new FlowLayout());
      //  buttonPanelTop.add(find);
        buttonPanelTop.add(previous);
        buttonPanelTop.add(next);
        buttonPanelTop.add(delete);
        outerPanel.add(buttonPanelTop, BorderLayout.NORTH);

        // Middle Panel
        JPanel middlePanel = new JPanel(new GridLayout(3, 1));

        // 3 Panel - Labels - Check-Boxes - CommentBox
        JPanel labelsTextfieldsPanel = new JPanel(new GridLayout(7, 2));
        labelsTextfieldsPanel.add(dateLabel);
        labelsTextfieldsPanel.add(datePicker);
        labelsTextfieldsPanel.add(linerNumberLabel);
        labelsTextfieldsPanel.add(linerNumberCombo);
        labelsTextfieldsPanel.add(crewLabel);
        labelsTextfieldsPanel.add(crewCombo);
        labelsTextfieldsPanel.add(leadHandLabel);
        labelsTextfieldsPanel.add(leadHandCombo);
        labelsTextfieldsPanel.add(reasonlabel);
        labelsTextfieldsPanel.add(reasonCombo);
        labelsTextfieldsPanel.add(quantityUsedLabel);
        labelsTextfieldsPanel.add(quantityUsedTextField);
        labelsTextfieldsPanel.add(partNumberLabel);
        labelsTextfieldsPanel.add(partNumberTextField);

        middlePanel.add(labelsTextfieldsPanel);

        JPanel checkBoxesPanel = new JPanel(new GridLayout(4, 4));
        checkBoxesPanel.add(Gun1Label);
        checkBoxesPanel.add(gun1CheckBox);
        checkBoxesPanel.add(Gun2Label);
        checkBoxesPanel.add(gun2CheckBox);
        checkBoxesPanel.add(Gun3Label);
        checkBoxesPanel.add(gun3CheckBox);
        checkBoxesPanel.add(Gun4Label);
        checkBoxesPanel.add(gun4CheckBox);
        checkBoxesPanel.add(Gun5Label);
        checkBoxesPanel.add(gun5CheckBox);
        checkBoxesPanel.add(Gun6Label);
        checkBoxesPanel.add(gun6CheckBox);
        checkBoxesPanel.add(Gun7Label);
        checkBoxesPanel.add(gun7CheckBox);
        checkBoxesPanel.add(Gun8Label);
        checkBoxesPanel.add(gun8CheckBox);

        middlePanel.add(checkBoxesPanel);

        JPanel commentsPanel = new JPanel(new BorderLayout());
        commentsPanel.add(commentsLabel, BorderLayout.NORTH);
        commentsPanel.add(commentsTextArea, BorderLayout.CENTER);

        middlePanel.add(commentsPanel);

        outerPanel.add(middlePanel, BorderLayout.CENTER);

        // Bottom Buttons Panel
        JPanel buttonPanelBottom = new JPanel(new FlowLayout());
        buttonPanelBottom.add(search);
        buttonPanelBottom.add(addNew);
        buttonPanelBottom.add(summary);
        
        buttonPanelBottom.add(update);
        buttonPanelBottom.add(add);
        buttonPanelBottom.setBackground(Color.GRAY);
        outerPanel.add(buttonPanelBottom, BorderLayout.SOUTH);

        // Adding
        if (view == -1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            summary.setVisible(false);
            delete.setVisible(false);

        } // Searching
        else {

            // currentId = SQLiteConnection.LinerGetHighestID() + 1;
            buttonPanelTop.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        // Set Frame Visible
        frame10.add(outerPanel);
        frame10.setVisible(true);
        
        SQLiteConnection.AnalyticsUpdate("LinerUsageEntryScreen");

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new LinerUsageEntryScreen(1, 1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LinerUsageEntryScreen.createSummaryScreen();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        JButton print = new JButton("Print Report");
        JButton ExportToExcel = new JButton("Export To Excel");

        ExportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                LinerDataEntryScreen.exportToExcel();

            }
        });
        
        JButton importFromViscan = new JButton("Import from Viscan");

        importFromViscan.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                

                try {
                    frameSummary.dispose();
                    LinerDataEntryScreen.importFromExcel();
                } catch (IOException ex) {
                    Logger.getLogger(LinerUsageEntryScreen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Data Report");
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(addNew);
        optionsPanel2.add(refresh);
        //optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromViscan);
        // }

        JPanel summaryPanel = SQLiteConnection.LinerUsageSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        
//        print.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                getDate("Optime Production Report", 1);
//
//            }
//        });
        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    private void fillCombos() {

        // Liner Number
        try {

            String sql = "select Liner.LinerName from Liner  ORDER BY LinerName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();
            
            
            while (rs.next()) {

                String name = rs.getString("LinerName");
                linerNumberCombo.addItem(name);
            }

        } catch (Exception e) {

        }

        // Crew
        try {

            String sql = "SELECT Crew.CrewName FROM Crew  ORDER BY CrewName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String crewName = rs.getString("CrewName");
                crewCombo.addItem(crewName);
            }

        } catch (Exception e) {

        }

        // Lead Hand
        try {

            String sql = "select Employees.Name from Employees ORDER BY Name ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();
            
            leadHandCombo.addItem("NA");

            while (rs.next()) {

                String employeeName = rs.getString("Name");
                leadHandCombo.addItem(employeeName);
            }

        } catch (Exception e) {

        }

        // Reason
        try {

            String sql = "SELECT LinerReason.LinerReasonName FROM LinerReason ORDER BY LinerReasonName ASC";
            Connection conn = SQLiteConnection.Connect();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setQueryTimeout(5);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                String linerReason = rs.getString("LinerReasonName");
                reasonCombo.addItem(linerReason);
            }

        } catch (Exception e) {

        }

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.LinerUsageGetHighestID();
            Object[] result = new Object[12];
            result = SQLiteConnection.LinerUsageReturnEntryByID(highestID);

            // Date , Shift, Crew, Operator, OptimeNumber , PressSpeed, ShellType, Production
            // Date
            String dateFormatted = (String) result[2];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
            model.setDate(year, month, day);
            model.setSelected(true);

            // Shift
            linerNumberCombo.setSelectedItem(result[1] + "");
            crewCombo.setSelectedItem(result[3] + "");
            leadHandCombo.setSelectedItem(result[4] + "");
            reasonCombo.setSelectedItem(result[5] + "");
            quantityUsedTextField.setText(result[6] + "");
            partNumberTextField.setText(result[7] + "");
            commentsTextArea.setText(result[16] + "");

            Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false, gun8 = false;

            int gun1Int = Integer.parseInt((String) result[8]);
            int gun2Int = Integer.parseInt((String) result[9]);
            int gun3Int = Integer.parseInt((String) result[10]);
            int gun4Int = Integer.parseInt((String) result[11]);
            int gun5Int = Integer.parseInt((String) result[12]);
            int gun6Int = Integer.parseInt((String) result[13]);
            int gun7Int = Integer.parseInt((String) result[14]);
            int gun8Int = Integer.parseInt((String) result[15]);

            if (gun1Int == 1) {
                gun1 = true;
            }
            if (gun2Int == 1) {
                gun2 = true;
            }
            if (gun3Int == 1) {
                gun3 = true;
            }
            if (gun4Int == 1) {
                gun4 = true;
            }
            if (gun5Int == 1) {
                gun5 = true;
            }
            if (gun6Int == 1) {
                gun6 = true;
            }
            if (gun7Int == 1) {
                gun7 = true;
            }
            if (gun8Int == 1) {
                gun8 = true;
            }

            gun1CheckBox.setSelected(gun1);
            gun2CheckBox.setSelected(gun2);
            gun3CheckBox.setSelected(gun3);
            gun4CheckBox.setSelected(gun4);
            gun5CheckBox.setSelected(gun5);
            gun6CheckBox.setSelected(gun6);
            gun7CheckBox.setSelected(gun7);
            gun8CheckBox.setSelected(gun8);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setLinerUsageToId(int idIn) {

        try {

            int highestID = idIn;
            Object[] result = new Object[12];
            result = SQLiteConnection.LinerUsageReturnEntryByID(idIn);

            String dateFormatted = (String) result[2];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

            Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false, gun8 = false;

            linerNumberCombo.setSelectedItem(result[1]);
            crewCombo.setSelectedItem(result[3]);
            leadHandCombo.setSelectedItem(result[4]);
            reasonCombo.setSelectedItem(result[5]);
            quantityUsedTextField.setText((String) result[6]);
            partNumberTextField.setText((String) result[7]);

            int gun1Int = Integer.parseInt((String) result[8]);
            int gun2Int = Integer.parseInt((String) result[9]);
            int gun3Int = Integer.parseInt((String) result[10]);
            int gun4Int = Integer.parseInt((String) result[11]);
            int gun5Int = Integer.parseInt((String) result[12]);
            int gun6Int = Integer.parseInt((String) result[13]);
            int gun7Int = Integer.parseInt((String) result[14]);
            int gun8Int = Integer.parseInt((String) result[15]);

            if (gun1Int == 1) {
                gun1 = true;
            }
            if (gun2Int == 1) {
                gun2 = true;
            }
            if (gun3Int == 1) {
                gun3 = true;
            }
            if (gun4Int == 1) {
                gun4 = true;
            }
            if (gun5Int == 1) {
                gun5 = true;
            }
            if (gun6Int == 1) {
                gun6 = true;
            }
            if (gun7Int == 1) {
                gun7 = true;
            }
            if (gun8Int == 1) {
                gun8 = true;
            }

            gun1CheckBox.setSelected(gun1);
            gun2CheckBox.setSelected(gun2);
            gun3CheckBox.setSelected(gun3);
            gun4CheckBox.setSelected(gun4);
            gun5CheckBox.setSelected(gun5);
            gun6CheckBox.setSelected(gun6);
            gun7CheckBox.setSelected(gun7);
            gun8CheckBox.setSelected(gun8);

            commentsTextArea.setText((String) result[16]);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void setModel(String yearIn, String monthIn, String dayIn) {

        // Date
        int yearInt = Integer.parseInt(yearIn);
        int monthInt = Integer.parseInt(monthIn) - 1;
        int dayInt = Integer.parseInt(dayIn);

        LinerUsageEntryScreen.model.setDate(yearInt, monthInt, dayInt);
    }
   

    public static void setCrewCombo(String crewIn) {
        LinerUsageEntryScreen.crewCombo.setSelectedItem(crewIn);
    }

    
    
}
