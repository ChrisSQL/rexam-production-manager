package com.productiontrackingscreens.rexam;

import com.binentryscreens.rexam.EndCounts;
import com.binentryscreens.rexam.LinerAndShellsEntry;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.database.rexam.SQLiteConnection;
import static com.productiontrackingscreens.rexam.ProductionMeeting.refresh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

public class AboutMenu {

    // JButton infoButton = new JButton("Created by Chris@ChrisMaher.info");
    JLabel timesProgramUsed, employeeAddressList, linerDataEntryScreen, linerUsageEntryScreen, LSSPMEntryScreen, meetingQualityIssues,
            optimeDataEntryScreen, productionMeeting, stolleDataEntryScreen;
    JLabel timesProgramUsedText, employeeAddressListText, linerDataEntryScreenText, linerUsageEntryScreenText, LSSPMEntryScreenText,
            meetingQualityIssuesText, optimeDataEntryScreenText, productionMeetingText, stolleDataEntryScreenText;
    String employeeAddressListString, linerDataEntryScreenString, linerUsageEntryScreenString, LSSPMEntryScreenString,
            meetingQualityIssuesString, optimeDataEntryScreenString, productionMeetingString, stolleDataEntryScreenString;

    public static void main(String[] args) {

        try {
            createSummaryScreen();
        } catch (SQLException ex) {
            Logger.getLogger(AboutMenu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    public AboutMenu() throws SQLException {

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

        createSummaryScreen();
       
    }

    public static void createSummaryScreen() throws SQLException {
        
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

        // Outer Frame
        JFrame frameSummary = new JFrame("Analytics Report");
        frameSummary.setSize(500, 800);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        JPanel summaryPanel = SQLiteConnection.AnalyticsSummaryTable();

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
        optionsPanel2.add(new JLabel("Created by ChrisMaher @ ChrisMaher.info"), JLabel.CENTER);

        outerPanel.add(summaryPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

    }
    
}
