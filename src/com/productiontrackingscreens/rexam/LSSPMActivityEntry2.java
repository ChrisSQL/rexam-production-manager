package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.AccessConnect;
import com.database.rexam.SQLiteConnection;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LSSPMActivityEntry2 extends JFrame {

    static JFrame frame2, frameSummary;
    static JPanel outerPanel, topButtonPanel, bottomButtonPanel, commentsPanel,
            dateJPanel;
    static JButton find, next, previous, search, update, save, addNew, refresh, summary;
    JLabel selectDate, selectedDateThenFind;
    static JTextArea commentTextArea;
    static JDatePickerImpl datePicker;
    static JDatePanelImpl datePanel;
    static UtilDateModel model;
    static Date todaysDate, selectedDate;
    AccessConnect accessConnect;
    static int[] todaysDateArray;
    static Object[] dateArray;
    static int day, month, year, currentId, highestId, lowestId;

    public static void main(String args[]) throws Exception {

        new LSSPMActivityEntry2(2, "", "", -1);

    }

    public LSSPMActivityEntry2(int id, String date, String comment, int view)
            throws Exception {

        // Add a view to analytics.
        try {
            SQLiteConnection.incrementViewsAnalytics(0, 0, 0, 0, 1, 0, 0, 0, 0);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

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
		// Date Picker

        // Create OuterFrame and Panels
        frame2 = new JFrame("LSSPM Entry");
        // frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.setSize(450, 370);
        frame2.setLocationRelativeTo(null);
        //frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame2.add(outerPanel);
        topButtonPanel = new JPanel(new FlowLayout());
        outerPanel.add(topButtonPanel, BorderLayout.NORTH);
        commentsPanel = new JPanel(new BorderLayout());
        bottomButtonPanel = new JPanel(new FlowLayout());
        outerPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        dateJPanel = new JPanel(new FlowLayout());

        // Declare JButtons
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        selectDate = new JLabel("Select Date : ");
        selectedDateThenFind = new JLabel("Select Date then Click Find : ");
        commentTextArea = new JTextArea();
        commentTextArea = new JTextArea(12, 20);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);

        find = new JButton("Find");
        find.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
                if (datePicker.getModel().getValue() != null) {

					// JOptionPane.showMessageDialog(null, "Select date then press find.");
                }

                selectedDate = (Date) datePicker.getModel().getValue();
                try {

                    // Set ID
                    System.out.println("Selected date : " + selectedDate);
                    Object[] array = SQLiteConnection.LSSPMEntryqueryDate(selectedDate);

                    System.out.println("ID : " + array[0]);
                    System.out.println("Date : " + array[1]);
                    System.out.println("Date : " + array[2]);

                    String id = (String) array[0];

                    currentId = Integer.parseInt(id);
                    commentTextArea.setText((String) array[2]);

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

				// Get First result from Selected Date and set Id, date and Comment
            }
        });
        next = new JButton("Next");
        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] array = new Object[3];
                try {
                    array = SQLiteConnection.LSSPMEntryqueryId(currentId + 1);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    highestId = SQLiteConnection.LSSPMEntryqueryHighest();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (currentId == highestId + 1) {

                    currentId = highestId - 1;
                    //commentsTextArea.setText("");

                } else if (array[1] == null) {

					// JOptionPane.showMessageDialog(null, "No Results Found.");
                    currentId = currentId - 1;

                } else {

                    String id = (String) array[0];

                    currentId = Integer.parseInt(id);
                    System.out.println("CurrentID : " + currentId);

                    String dateFormatted = (String) array[1];
                    // System.out.println("Date Formatted : " + dateFormatted);
                    int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                    int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                    int year = Integer.parseInt(dateFormatted.substring(1, 4)); // Correct

//					System.out.println(day);
//					System.out.println(month);
//					System.out.println(year);
                    model.setDate(year, month, day);
                    model.setSelected(true);
                    commentTextArea.setText((String) array[2]);

                }

            }
        });
        previous = new JButton("Previous");
        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] array = new Object[3];
                try {
                    array = SQLiteConnection.LSSPMEntryqueryId(currentId - 1);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    lowestId = SQLiteConnection.LSSPMEntryqueryLowest();
                    // System.out.println("Lowest Id : " + lowestId);
                    System.out.println("Current ID : " + currentId);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                if (currentId == lowestId - 1) {

                    currentId = lowestId + 1;
                    //commentsTextArea.setText("");

                } else if (array[1] == null) {

					// JOptionPane.showMessageDialog(null, "No Results Found.");
					//currentId = currentId + 1;
                } else {

                    String id = (String) array[0];

                    currentId = Integer.parseInt(id);

                    String dateFormatted = (String) array[1];
                    int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
                    int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
                    int year = Integer.parseInt(dateFormatted.substring(1, 4)); // Correct

//					System.out.println(day);
//					System.out.println(month);
//					System.out.println(year);
                    model.setDate(year, month, day);
                    model.setSelected(true);
                    commentTextArea.setText((String) array[2]);

                }

            }
        });
        search = new JButton("Search Mode");
        update = new JButton("Update Record");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                selectedDate = (Date) datePicker.getModel().getValue();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                System.out.println("Date" + date);
                System.out.println("CurrentID : " + currentId);

                try {
                    SQLiteConnection.LSSPMUpdate(currentId, date, commentTextArea.getText());

                    frame2.dispose();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

				// TODO Auto-generated method stub
            }
        });
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

                String dateFormatted = SQLiteConnection.convertDate2(model.getValue());

                System.out.println(dateFormatted);

                try {
                    SQLiteConnection.LSSPMInsert(dateFormatted, commentTextArea.getText());

                    frame2.dispose();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame2.dispose();
                try {
                    new LSSPMActivityEntry2(2, "", "", -1);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame2.dispose();
                try {
                    new LSSPMActivityEntry2(2, "", "", -2);
                    setLastEntry();

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        summary = new JButton("Summary");
        summary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame2.dispose();
                try {
                    LSSPMActivityEntry2.createSummaryScreen();
                } catch (SQLException ex) {
                    Logger.getLogger(LSSPMActivityEntry2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
		// Create Views
        // Add New
        if (view == -1) {

            addNew.setVisible(false);
            find.setVisible(false);
            next.setVisible(false);
            previous.setVisible(false);
            selectedDateThenFind.setVisible(false);
            update.setVisible(false);
            summary.setVisible(false);
        } // Search
        else {

            // Set date to last entry date
            currentId = SQLiteConnection.LSSPMGetHighestID() + 1;
            dateArray = new Object[3];
            addNew.setVisible(false);
			//dateArray = SQLiteConnection.LSSPMEntryqueryHighestArrayDateFormatted();
            //model.setDate((int) dateArray[0], (int) dateArray[1] - 1,
            //		(int) dateArray[2]);
            //model.setSelected(true);
            // Set Comment to last comment Date
            //String commenttext = SQLiteConnection.LSSPMEntryqueryHighestCommentFormatted();
            //commentTextArea.setText(commenttext);
            search.setVisible(false);
            selectDate.setVisible(false);
            save.setVisible(false);

        }

		// Add JButtons
        topButtonPanel.add(find);
        topButtonPanel.add(previous);
        topButtonPanel.add(next);

        outerPanel.add(commentsPanel);
        
        bottomButtonPanel.add(summary);
        bottomButtonPanel.add(addNew);
        bottomButtonPanel.add(search);
        bottomButtonPanel.add(update);
        bottomButtonPanel.add(save);

        // datePicker Setup
        todaysDateArray = new int[3];
        todaysDate = new Date();

        todaysDateArray = AccessConnect.dateToThreeInts(todaysDate);
        dateJPanel.add(selectedDateThenFind);
        dateJPanel.add(selectDate);
        dateJPanel.add(datePicker);
		// model.setDate(todaysDateArray[0], todaysDateArray[1],
        // todaysDateArray[2]);
        // model.setDate(2014, 6, 13);
        // model.setSelected(true);
        commentsPanel.add(dateJPanel, BorderLayout.NORTH);
        commentsPanel.add(commentTextArea, BorderLayout.SOUTH);

        frame2.setVisible(true);
    }

    public void setLinerUsageToId(int idIn) {

        try {

            int highestID = idIn;
            Object[] result = new Object[4];
            result = SQLiteConnection.LSSPMEntryqueryId(idIn);

            // Date
            String dateFormatted = (String) result[1];
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(1, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

			// Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled, CalculatedSpoilage
            commentTextArea.setText(result[2] + "");

            currentId = highestID;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    new LSSPMActivityEntry2(2, "", "", -1);
                } catch (Exception ex) {
                    Logger.getLogger(LSSPMActivityEntry2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                try {
                    LSSPMActivityEntry2.createSummaryScreen();
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
        frameSummary = new JFrame("LSS-PM Activity Data Report");
        frameSummary.setSize(1366, 768);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        // if (view == 2) {
        optionsPanel2.add(addNew);
        optionsPanel2.add(refresh);
       // optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);
        // }

        JPanel summaryPanel = SQLiteConnection.LSSPMSummaryTable(1);

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

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }

    private void setLastEntry() {

        try {

            int highestID = SQLiteConnection.LSSPMGetHighestID();
            System.out.println("Highest ID : " + highestID);
            Object[] result = new Object[3];
            result = SQLiteConnection.LSSPMEntryqueryId(highestID);

            System.out.println("Date " + result[1]);

            // Date
            String dateFormatted = (String) result[1];
            int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
            int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
            int year = Integer.parseInt(dateFormatted.substring(1, 4)); // Correct

            model.setDate(year, month, day);
            model.setSelected(true);

			// Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled, CalculatedSpoilage
            commentTextArea.setText(result[2] + "");

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
