package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
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
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

public class MeetingQualityIssues {

    static JButton add, find, next, previous, update, addNew, search, refresh, summary;
    JLabel date, percentageOfChecksDoneDays, percentageOfChecksDoneNights, customerComplaints, qualityIssuesPreviousDays, qualityIssuesToday,
            qualityEquipment, shellsMTD, HFICreateMTD, HFIRecoverMTD, HFIScrapMTD, endsInHFI, auditsDue;
    static JTextField percentageOfChecksDoneDaysText, percentageOfChecksDoneNightsText, customerComplaintsText, shellsMTDText, HFICreateMTDText,
            HFIRecoverMTDText, HFIScrapMTDText, endsInHFIText;
    static JTextArea qualityIssuesPreviousDaysTextArea, qualityIssuesTodayTextArea, qualityEquipmentTextArea, auditsDueTextArea;
    static int view, currentId;
    static Date selectedDate;

    static UtilDateModel model;
    static JDatePanelImpl datePanel;
    static JDatePickerImpl datePicker;

    static JFrame frameSummary, frame7;

    public static void main(String[] args) {

        new MeetingQualityIssues(1, -1);

    }

    public MeetingQualityIssues(int id, int view) {

        

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

        JFrame frame7 = new JFrame("Meeting Quality Issues");
        // frame7.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame7.setSize(1000, 500);
        frame7.setLocationRelativeTo(null);

        // JLabels
        date = new JLabel("Date", SwingConstants.CENTER);
        percentageOfChecksDoneDays = new JLabel("Percentage Of Checks Done - Days", SwingConstants.CENTER);
        percentageOfChecksDoneNights = new JLabel("Percentage Of Checks Done - Nights", SwingConstants.CENTER);
        customerComplaints = new JLabel("Customer Complaints", SwingConstants.CENTER);
        qualityIssuesPreviousDays = new JLabel("Quality Issues Previous Days", SwingConstants.CENTER);
        qualityIssuesToday = new JLabel("Quality Issues Today", SwingConstants.CENTER);
        qualityEquipment = new JLabel("Quality Equipment", SwingConstants.CENTER);
        shellsMTD = new JLabel("Shells MTD", SwingConstants.CENTER);
        HFICreateMTD = new JLabel("HFI Create MTD", SwingConstants.CENTER);
        HFIRecoverMTD = new JLabel("HFI Recover MTD", SwingConstants.CENTER);
        HFIScrapMTD = new JLabel("HFI Scrap MTD", SwingConstants.CENTER);
        endsInHFI = new JLabel("Ends In HFI", SwingConstants.CENTER);
        auditsDue = new JLabel("Audits Due", SwingConstants.CENTER);
        // ////////
        Date date2 = new Date();
        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date2);
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
        // JTextFields

        percentageOfChecksDoneDaysText = new JTextField("0");
//		PlainDocument doc1 = (PlainDocument) percentageOfChecksDoneDaysText.getDocument();
//		doc1.setDocumentFilter(new MyIntFilter());

        percentageOfChecksDoneNightsText = new JTextField("0");
//		PlainDocument doc2 = (PlainDocument) percentageOfChecksDoneNightsText.getDocument();
//		doc2.setDocumentFilter(new MyIntFilter());

        customerComplaintsText = new JTextField("0");

        shellsMTDText = new JTextField("0");
        PlainDocument doc3 = (PlainDocument) shellsMTDText.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());

        HFICreateMTDText = new JTextField("0");
        PlainDocument doc4 = (PlainDocument) HFICreateMTDText.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());

        HFIRecoverMTDText = new JTextField("0");
        PlainDocument doc5 = (PlainDocument) HFIRecoverMTDText.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());

        HFIScrapMTDText = new JTextField("0");
        PlainDocument doc6 = (PlainDocument) HFIScrapMTDText.getDocument();
        doc6.setDocumentFilter(new MyIntFilter());

        endsInHFIText = new JTextField("0");
        PlainDocument doc7 = (PlainDocument) endsInHFIText.getDocument();
        doc7.setDocumentFilter(new MyIntFilter());

        // ////////
        add = new JButton("Save Record");
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.MeetingQualityInsert(
                            SQLiteConnection.MeetingQualityGetHighestID() + 1, date,
                            Double.parseDouble(percentageOfChecksDoneDaysText.getText()),
                            Double.parseDouble(percentageOfChecksDoneNightsText.getText()),
                            customerComplaintsText.getText(), qualityIssuesPreviousDaysTextArea.getText(),
                            qualityIssuesTodayTextArea.getText(), Integer.parseInt(shellsMTDText.getText()),
                            Integer.parseInt(HFICreateMTDText.getText()), Integer.parseInt(HFIRecoverMTDText.getText()),
                            Integer.parseInt(HFIScrapMTDText.getText()), Integer.parseInt(endsInHFIText.getText()),
                            qualityEquipmentTextArea.getText(), auditsDueTextArea.getText()
                    );

                    frame7.dispose();
                    createSummaryScreen();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // TODO Auto-generated method stub
            }
        });

        search = new JButton("Search Mode");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    MeetingQualityIssues.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(MeetingQualityIssues.class.getName()).log(Level.SEVERE, null, ex);
                }
                frame7.dispose();
            }
        });
        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new MeetingQualityIssues(1, -1);
                frame7.dispose();
            }
        });

        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

                try {
                    SQLiteConnection.MeetingQualityUpdate(
                            date, Double.parseDouble(percentageOfChecksDoneDaysText.getText()),
                            Double.parseDouble(percentageOfChecksDoneNightsText.getText()),
                            customerComplaintsText.getText(), qualityIssuesPreviousDaysTextArea.getText(),
                            qualityIssuesTodayTextArea.getText(), Integer.parseInt(shellsMTDText.getText()),
                            Integer.parseInt(HFICreateMTDText.getText()), Integer.parseInt(HFIRecoverMTDText.getText()),
                            Integer.parseInt(HFIScrapMTDText.getText()), Integer.parseInt(endsInHFIText.getText()),
                            qualityEquipmentTextArea.getText(), auditsDueTextArea.getText(), currentId
                    );

                    frame7.dispose();
                    createSummaryScreen();

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
                    array = SQLiteConnection.MeetingQualityReturnEntryByDate(selectedDate);

                    // String date = (String) array[1];
                    // need to do
                    if (array[1] == null) {

                        SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

                    } else {

                        currentId = (int) array[0];

                        percentageOfChecksDoneDaysText.setText((String) array[2]);
                        percentageOfChecksDoneNightsText.setText((String) array[3]);
                        customerComplaintsText.setText((String) array[4]);
                        qualityIssuesPreviousDaysTextArea.setText((String) array[5]);
                        qualityIssuesTodayTextArea.setText((String) array[6]);
                        shellsMTDText.setText((String) array[7]);
                        HFICreateMTDText.setText((String) array[8]);
                        HFIRecoverMTDText.setText((String) array[9]);
                        HFIScrapMTDText.setText((String) array[10]);
                        endsInHFIText.setText((String) array[11]);
                        qualityEquipmentTextArea.setText((String) array[12]);
                        auditsDueTextArea.setText((String) array[13]);

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

        previous = new JButton("Previous Entry");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] array = SQLiteConnection.MeetingQualityGetPreviousEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Previous Result.", "");

                    } else {

                        currentId = currentId - 1;

                        System.out.println("Array[1]" + array[1]);

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        percentageOfChecksDoneDaysText.setText((String) array[2]);
                        percentageOfChecksDoneNightsText.setText((String) array[3]);
                        customerComplaintsText.setText((String) array[4]);
                        qualityIssuesPreviousDaysTextArea.setText((String) array[5]);
                        qualityIssuesTodayTextArea.setText((String) array[6]);
                        shellsMTDText.setText((String) array[7]);
                        HFICreateMTDText.setText((String) array[8]);
                        HFIRecoverMTDText.setText((String) array[9]);
                        HFIScrapMTDText.setText((String) array[10]);
                        endsInHFIText.setText((String) array[11]);
                        qualityEquipmentTextArea.setText((String) array[12]);
                        auditsDueTextArea.setText((String) array[13]);

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

        next = new JButton("Next Record");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // TODO Auto-generated method stub
                // Set ID
                try {

                    Object[] array = SQLiteConnection.MeetingQualityGetNextEntryById(currentId);

                    if (array[0] == null) {

                        SQLiteConnection.infoBox("No Next Result.", "");

                    } else {

                        currentId = currentId + 1;

                        System.out.println("Array[1]" + array[1]);

                        String dateFormatted = (String) array[1];
                        System.out.println("Date Formatted : " + dateFormatted);
                        int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                        int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                        int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

                        model.setDate(year, month, day);
                        model.setSelected(true);

                        percentageOfChecksDoneDaysText.setText((String) array[2]);
                        percentageOfChecksDoneNightsText.setText((String) array[3]);
                        customerComplaintsText.setText((String) array[4]);
                        qualityIssuesPreviousDaysTextArea.setText((String) array[5]);
                        qualityIssuesTodayTextArea.setText((String) array[6]);
                        shellsMTDText.setText((String) array[7]);
                        HFICreateMTDText.setText((String) array[8]);
                        HFIRecoverMTDText.setText((String) array[9]);
                        HFIScrapMTDText.setText((String) array[10]);
                        endsInHFIText.setText((String) array[11]);
                        qualityEquipmentTextArea.setText((String) array[12]);
                        auditsDueTextArea.setText((String) array[13]);

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

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    frame7.dispose();
                    MeetingQualityIssues.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(MeetingQualityIssues.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // JTextAreas
        qualityIssuesPreviousDaysTextArea = new JTextArea(7, 20);
        qualityIssuesPreviousDaysTextArea.setText("NA");
        qualityIssuesPreviousDaysTextArea.setLineWrap(true);
        qualityIssuesPreviousDaysTextArea.setWrapStyleWord(true);

        qualityIssuesTodayTextArea = new JTextArea(7, 20);
        qualityIssuesTodayTextArea.setText("NA");
        qualityIssuesTodayTextArea.setLineWrap(true);
        qualityIssuesTodayTextArea.setWrapStyleWord(true);

        qualityEquipmentTextArea = new JTextArea(7, 20);
        qualityEquipmentTextArea.setText("NA");
        qualityEquipmentTextArea.setLineWrap(true);
        qualityEquipmentTextArea.setWrapStyleWord(true);

        auditsDueTextArea = new JTextArea(7, 20);
        auditsDueTextArea.setText("NA");
        auditsDueTextArea.setLineWrap(true);
        auditsDueTextArea.setWrapStyleWord(true);
		// ////////

        // OuterPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Buttons Top Panel
        // JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
        JPanel buttonsPanel = new JPanel(new FlowLayout());

        // buttonsPanel.add(find);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

        frame7.add(buttonsPanel, BorderLayout.NORTH);

        // UpperPanel - JLabels and JTextfield
        JPanel upperPanelOuter = new JPanel(new GridLayout(1, 4));

        // 4 Upper Panels with FlowLayout
        JPanel left1 = new JPanel(new GridLayout(5, 1));
        left1.add(date);
        left1.add(percentageOfChecksDoneDays);
        left1.add(percentageOfChecksDoneNights);
        left1.add(customerComplaints);
        upperPanelOuter.add(left1);

        JPanel left2 = new JPanel(new GridLayout(5, 1));

        left2.add(datePicker);
        left2.add(percentageOfChecksDoneDaysText);
        left2.add(percentageOfChecksDoneNightsText);
        left2.add(customerComplaintsText);
        upperPanelOuter.add(left2);

        JPanel right1 = new JPanel(new GridLayout(5, 1));
        right1.add(shellsMTD);
        right1.add(HFICreateMTD);
        right1.add(HFIRecoverMTD);
        right1.add(HFIScrapMTD);
        right1.add(endsInHFI);
        upperPanelOuter.add(right1);

        JPanel right2 = new JPanel(new GridLayout(5, 1));
        right2.add(shellsMTDText);
        right2.add(HFICreateMTDText);
        right2.add(HFIRecoverMTDText);
        right2.add(HFIScrapMTDText);
        right2.add(endsInHFIText);
        upperPanelOuter.add(right2);

        // Add Panel to OuterPanel and OuterPanel to Frame
        outerPanel.add(upperPanelOuter, BorderLayout.NORTH);
		// frame.add(outerPanel);

        // LowerPanel - JLabels and JTextAreas
        JPanel lowerPanelOuter = new JPanel(new GridLayout(2, 4));

        // 4 Bottom Panels
        JPanel left1bottom = new JPanel();
        left1bottom.add(qualityIssuesPreviousDays);
        lowerPanelOuter.add(left1bottom);

        JPanel left2bottom = new JPanel(new GridLayout(1, 1));
        left2bottom.add(qualityIssuesPreviousDaysTextArea);
        lowerPanelOuter.add(left2bottom);

        JPanel right1bottom = new JPanel();
        right1bottom.add(qualityEquipment);
        lowerPanelOuter.add(right1bottom);

        JPanel right2bottom = new JPanel(new GridLayout(1, 1));
        right2bottom.add(qualityEquipmentTextArea);
        lowerPanelOuter.add(right2bottom);

        JPanel left1bottom2 = new JPanel();
        left1bottom2.add(qualityIssuesToday);
        lowerPanelOuter.add(left1bottom2);

        JPanel left2bottom2 = new JPanel(new GridLayout(1, 1));
        left2bottom2.add(qualityIssuesTodayTextArea);
        lowerPanelOuter.add(left2bottom2);

        JPanel right1bottom2 = new JPanel();
        right1bottom2.add(auditsDue);
        lowerPanelOuter.add(right1bottom2);

        JPanel right2bottom2 = new JPanel(new GridLayout(1, 1));
        right2bottom2.add(auditsDueTextArea);
        lowerPanelOuter.add(right2bottom2);

        // Adding
        if (view == -1) {

            find.setVisible(false);
            previous.setVisible(false);
            next.setVisible(false);
            addNew.setVisible(false);
            update.setVisible(false);
            summary.setVisible(false);

        } // Searching
        else if (view == 2) {

            // currentId = SQLiteConnection.StolleGetHighestID()+1;
            buttonsPanel.setBackground(Color.GRAY);
            search.setVisible(false);
            model.setDate(yearInt, monthInt, dayInt);
            model.setSelected(true);
            add.setVisible(false);
            addNew.setVisible(false);

        }

        // Add Panel to OuterPanel and OuterPanel to Frame
        outerPanel.add(lowerPanelOuter, BorderLayout.CENTER);
        frame7.add(outerPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.GRAY);

        buttonPanel.add(addNew);
        buttonPanel.add(summary);
        buttonPanel.add(search);
        buttonPanel.add(update);
        buttonPanel.add(add);
        outerPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame7.setVisible(true);
        
        SQLiteConnection.AnalyticsUpdate("MeetingQualityIssues");
    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new MeetingQualityIssues(1, -1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    ProductionMeeting.createSummaryScreen();
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

        // Outer Frame
        frameSummary = new JFrame("Liner Data Report");
        frameSummary.setSize(1300, 900);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(addNew);
        optionsPanel2.add(refresh);
        //   optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);

        JPanel summaryPanel = SQLiteConnection.MeetingQualityIssuesSummaryTable(1);
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

    public void setMeetingQualityToID(int idIn) {

        try {

            int highestID = idIn;

            currentId = idIn;

            Object[] array = SQLiteConnection.MeetingQualityReturnEntryByID(idIn);

            String dateFormatted = (String) array[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

            percentageOfChecksDoneDaysText.setText((Double) array[2] + "");
            percentageOfChecksDoneNightsText.setText((Double) array[3] + "");
            customerComplaintsText.setText((String) array[4]);
            qualityIssuesPreviousDaysTextArea.setText((String) array[5]);
            qualityIssuesTodayTextArea.setText((String) array[6]);
            shellsMTDText.setText((String) array[7]);
            HFICreateMTDText.setText((String) array[8]);
            HFIRecoverMTDText.setText((String) array[9]);
            HFIScrapMTDText.setText((String) array[10]);
            endsInHFIText.setText((String) array[11]);
            qualityEquipmentTextArea.setText((String) array[12]);
            auditsDueTextArea.setText((String) array[13]);

            // Fill Boxes with results
            // model.setDate(year2, month2, day2);
            model.setSelected(true);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.MeetingQualityGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[16];
            result = SQLiteConnection.MeetingQualityReturnEntryByID(highestID);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            System.out.println("Date Formatted : " + dateFormatted);
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

            percentageOfChecksDoneDaysText.setText(Double.valueOf(result[2] + "") + "");
            percentageOfChecksDoneNightsText.setText(Double.valueOf(result[3] + "") + "");
            customerComplaintsText.setText((String) result[4]);
            qualityIssuesPreviousDaysTextArea.setText((String) result[5]);
            qualityIssuesTodayTextArea.setText((String) result[6]);
            shellsMTDText.setText((String) result[7]);
            HFICreateMTDText.setText((String) result[8]);
            HFIRecoverMTDText.setText((String) result[9]);
            HFIScrapMTDText.setText((String) result[10]);
            endsInHFIText.setText((String) result[11]);
            qualityEquipmentTextArea.setText((String) result[12]);
            auditsDueTextArea.setText((String) result[13]);

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
