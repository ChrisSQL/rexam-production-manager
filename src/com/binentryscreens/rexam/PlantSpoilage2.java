package com.binentryscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.database.rexam.SQLiteConnection;

public class PlantSpoilage2 {

    JFrame frame;
    static JLabel shellsProducedLabel, endsPackedLabel, HFIOpeningLabel, HFICreatedLabel, HFIRecoveredLabel, HFIScrappedLabel, HFIRemainingLabel, HFISpoilageLabel,
            lineSpoilageLabel, totalSpoilageLabel, HFISpoilagePercentageLabel, lineSpoilagePercentageLabel, totalSpoilagePercentageLabel;

    static JTextField shellsProducedJTextField, endsPackedJTextField, HFIOpeningJTextField, HFICreatedJTextField, HFIRecoveredJTextField, HFIScrappedJTextField,
            HFIRemainingJTextField, HFISpoilageJTextField, lineSpoilageJTextField, totalSpoilageJTextField, HFISpoilagePercentageJTextField, lineSpoilagePercentageJTextField,
            totalSpoilagePercentageJTextField;

    /* Line 1 */    static JLabel lineLabel1, lineLabel2, lineLabel3, lineLabel4, lineLabel5, lineLabel6, lineLabel7, productionByAreaLabel;
    /* Line 2 */
    static JLabel lineLabel20, shellPressMod1Label, shellPressMod2Label, shellPressMod3Label, shellPressTotals1Label, shellPressMod4Label, shellPressTotals2Label;
    /* Line 3 */
    static JTextField shellPressMod1JTextField, shellPressMod2JTextField, shellPressMod3JTextField, B64TotalJTextField, shellPressMod4JTextField,
            shellPressTotalJTextField;
    /* Line 4 */
    static JLabel lineLabel22, linerMod1Label, linerMod2Label, linerMod3Label, linerTotals1Label, linerMod4Label, linerTotals2Label;
    /* Line 5 */
    static JTextField linerMod1JTextField, linerMod2JTextField, linerMod3JTextField, linerTotals1JTextField, linerMod4JTextField, linerTotals2JTextField;
    /* Line 6 */
    static JLabel lineLabel24, stolleMod1Label, stolleMod2Label, stolleMod3Label, stolleTotals1Label, stolleMod4Label, stolleTotals2Label;
    /* Line 7 */
    static JTextField stolleMod1JTextField, stolleMod2JTextField, stolleMod3JTextField, stolleTotals1JTextField, stolleMod4JTextField, stolleTotals2JTextField;
    /* Line 8 */
    static JLabel lineLabel26, lineLabel8, lineLabel9, lineLabel10, lineLabel11, lineLabel12, lineLabel13, spoilageByArea;
    /* Line 9 */
    static JLabel shellPressLabel;
    /* Line 9 */
    static JTextField shellPressJTextField1, shellPressJTextField2, shellPressJTextField3, shellPressJTextFieldTotal1, shellPressJTextField4,
            shellPressJTextFieldTotal2;
    /* Line 10 */
    static JLabel ABalancerLabel;
    /* Line 10 */
    static JTextField ABalancerJTextField1, ABalancerJTextField2, ABalancerJTextField3, ABalancerJTextFieldTotal1, ABalancerJTextField4, ABalancerJTextFieldTotal2;
    /* Line 11 */
    static JLabel LinersLabel;
    /* Line 11 */
    static JTextField LinersJTextField1, LinersJTextField2, LinersJTextField3, LinersJTextFieldTotal1, LinersJTextField4, LinersTextFieldTotal2;
    /* Line 12 */
    static JLabel BBalancerLabel;
    /* Line 12 */
    static JTextField BBalancerJTextField1, BBalancerJTextField2, BBalancerJTextField3, BBalancerJTextFieldTotal1, BBalancerJTextField4, BBalancerTextFieldTotal2;
    /* Line 13 */
    static JLabel conversionPressLabel;
    /* Line 13 */
    static JTextField conversionPressJTextField1, conversionPressJTextField2, conversionPressJTextField3, conversionPressJTextFieldTotal1, conversionPressJTextField4,
            conversionPressTextFieldTotal2;
    /* Line 14 */
    static JLabel autoBaggersLabel;
    /* Line 14 */
    static JTextField autoBaggersJTextField1, autoBaggersJTextField2, autoBaggersJTextField3, autoBaggersJTextFieldTotal1, autoBaggersJTextField4,
            autoBaggersTextFieldTotal2;
    /* Line 15 */
    static JLabel QCChecksLabel;
    /* Line 15 */
    static JTextField QCChecksJTextField1, QCChecksJTextField2, QCChecksJTextField3, QCChecksJTextFieldTotal1, QCChecksJTextField4, QCChecksTextFieldTotal2;
    /* Line 16 */
    static JLabel TotalLineLabel;
    /* Line 16 */
    static JTextField TotalLineJTextField1, TotalLineJTextField2, TotalLineJTextField3, TotalLineJTextFieldTotal1, TotalLineJTextField4, TotalLineTextFieldTotal2;
    /* Line 17 */
    static JLabel HFILabel;
    /* Line 17 */
    static JTextField HFIJTextField1, HFIJTextField2, HFIJTextField3, HFIJTextFieldTotal1, HFIJTextField4, HFITextFieldTotal2;
    /* Line 18 */
    static JLabel OtherLabel;
    /* Line 18 */
    static JTextField OtherJTextField1, OtherJTextField2, OtherJTextField3, OtherJTextFieldTotal1, OtherJTextField4, OtherTextFieldTotal2;
    /* Line 19 */
    static JLabel finalTotalLabel;
    /* Line 19 */
    static JTextField finalTotalJTextField1, finalTotalJTextField2, finalTotalJTextField3, finalTotalJTextFieldTotal1, finalTotalJTextField4,
            finalTotalTextFieldTotal2;
    /* Line 20 */
    static JLabel lineLabel14, lineLabel15, lineLabel16, lineLabel17, lineLabel18, lineLabel19;
    /* Line XXX */
    static JLabel lineLabel21, lineLabel23, lineLabel25, lineLabel27, lineLabel28;

    JComboBox monthCombo, yearCombo;
    JButton back, go;

    public static void main(String[] args) {

        try {
            new PlantSpoilage2("", "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public PlantSpoilage2(String monthIn, String yearIn) throws Exception {

		// try {
        // for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        // if ("Nimbus".equals(info.getName())) {
        // UIManager.setLookAndFeel(info.getClassName());
        // break;
        // }
        // }
        // } catch (Exception e) {
        // // If Nimbus is not available, you can set the GUI to another look
        // // and feel.
        // }
        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] years = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};
        JComboBox monthCombo = new JComboBox(monthArray);
        monthCombo.setSelectedItem(monthIn);
        JComboBox yearCombo = new JComboBox(years);
        yearCombo.setSelectedItem(yearIn);

        go = new JButton("Go");
        go.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Selected Month : " + monthCombo.getSelectedItem());

                try {
                    new PlantSpoilage2(monthCombo.getSelectedItem() + "", yearCombo.getSelectedItem() + "");

                    frame.dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });

        shellsProducedLabel = new JLabel("Shells Produced", SwingConstants.CENTER);
        endsPackedLabel = new JLabel("Ends Packed", SwingConstants.CENTER);
        HFIOpeningLabel = new JLabel("HFI Opening", SwingConstants.CENTER);
        HFICreatedLabel = new JLabel("HFI Created", SwingConstants.CENTER);
        HFIRecoveredLabel = new JLabel("HFI Recovered", SwingConstants.CENTER);
        HFIScrappedLabel = new JLabel("HFI Scrapped", SwingConstants.CENTER);
        HFIRemainingLabel = new JLabel("HFI Remaining", SwingConstants.CENTER);
        HFISpoilageLabel = new JLabel("HFI Spoilage", SwingConstants.CENTER);
        lineSpoilageLabel = new JLabel("Line Spoilage", SwingConstants.CENTER);
        totalSpoilageLabel = new JLabel("Total Spoilage", SwingConstants.CENTER);
        HFISpoilagePercentageLabel = new JLabel("HFI Spoilage %", SwingConstants.CENTER);
        lineSpoilagePercentageLabel = new JLabel("Line Spoilage %", SwingConstants.CENTER);
        totalSpoilagePercentageLabel = new JLabel("Total Spoilage %", SwingConstants.CENTER);

        shellsProducedJTextField = new JTextField();
        shellsProducedJTextField.setBackground(new Color(255, 255, 153));
        endsPackedJTextField = new JTextField();
        HFIOpeningJTextField = new JTextField();
        HFICreatedJTextField = new JTextField();
        HFIRecoveredJTextField = new JTextField();
        HFIScrappedJTextField = new JTextField();
        HFIRemainingJTextField = new JTextField();
        HFISpoilageJTextField = new JTextField();
        lineSpoilageJTextField = new JTextField();
        totalSpoilageJTextField = new JTextField();
        HFISpoilagePercentageJTextField = new JTextField();
        lineSpoilagePercentageJTextField = new JTextField();
        lineSpoilagePercentageJTextField.setBackground(new Color(255, 255, 153));
        totalSpoilagePercentageJTextField = new JTextField();

        // LINE 1 //
        productionByAreaLabel = new JLabel("Production By Area");
        lineLabel1 = new JLabel(" ");
        lineLabel2 = new JLabel(" ");
        lineLabel3 = new JLabel(" ");
        lineLabel4 = new JLabel(" ");
        lineLabel5 = new JLabel(" ");
        lineLabel6 = new JLabel(" ");
        lineLabel7 = new JLabel(" ");
		// //////////
        // LINE 2 //
        lineLabel20 = new JLabel(" ");
        shellPressMod1Label = new JLabel("");
        shellPressMod2Label = new JLabel("Shell Press Mod 2", SwingConstants.CENTER);
        shellPressMod3Label = new JLabel("Shell Press Mod 3", SwingConstants.CENTER);
        shellPressTotals1Label = new JLabel("Total", SwingConstants.CENTER);
        shellPressMod4Label = new JLabel("Shell Press Mod 4", SwingConstants.CENTER);
        shellPressTotals2Label = new JLabel("Total", SwingConstants.CENTER);
		// //////////
        // LINE 3 //
        lineLabel27 = new JLabel();
        lineLabel21 = new JLabel(" ");
        lineLabel28 = new JLabel(" ");
        shellPressMod1JTextField = new JTextField();
        shellPressMod2JTextField = new JTextField();
        shellPressMod3JTextField = new JTextField();
        B64TotalJTextField = new JTextField();
        shellPressMod4JTextField = new JTextField();
        shellPressTotalJTextField = new JTextField();
		// //////////
        // LINE 4 //
        lineLabel22 = new JLabel(" ");
        linerMod1Label = new JLabel("Liners Mod 1", SwingConstants.CENTER);
        linerMod2Label = new JLabel("Liners Mod 2", SwingConstants.CENTER);
        linerMod3Label = new JLabel("Liners Mod 3", SwingConstants.CENTER);
        linerTotals1Label = new JLabel("Total", SwingConstants.CENTER);
        linerMod4Label = new JLabel("Liners Mod 4", SwingConstants.CENTER);
        linerTotals2Label = new JLabel("Total", SwingConstants.CENTER);
		// //////////
        // LINE 5 //
        lineLabel23 = new JLabel(" ");
        linerMod1JTextField = new JTextField();
        linerMod2JTextField = new JTextField();
        linerMod3JTextField = new JTextField();
        linerTotals1JTextField = new JTextField();
        linerMod4JTextField = new JTextField();
        linerTotals2JTextField = new JTextField();
		// //////////
        // LINE 6 //
        lineLabel24 = new JLabel(" ");
        stolleMod1Label = new JLabel("Stolle Mod 1", SwingConstants.CENTER);
        stolleMod2Label = new JLabel("Stolle Mod 2", SwingConstants.CENTER);
        stolleMod3Label = new JLabel("Stolle Mod 3", SwingConstants.CENTER);
        stolleTotals1Label = new JLabel("Total", SwingConstants.CENTER);
        stolleMod4Label = new JLabel("Stolle Mod 4", SwingConstants.CENTER);
        stolleTotals2Label = new JLabel("Total", SwingConstants.CENTER);
		// //////////
        // LINE 7 //
        lineLabel25 = new JLabel(" ");
        stolleMod1JTextField = new JTextField();
        stolleMod2JTextField = new JTextField();
        stolleMod3JTextField = new JTextField();
        stolleTotals1JTextField = new JTextField();
        stolleMod4JTextField = new JTextField();
        stolleTotals2JTextField = new JTextField();
		// //////////
        // LINE 8 //
        lineLabel26 = new JLabel(" ");
        lineLabel8 = new JLabel("");
        lineLabel9 = new JLabel("  ");
        lineLabel10 = new JLabel("  ");
        lineLabel11 = new JLabel("  ");
        lineLabel12 = new JLabel("");
        lineLabel13 = new JLabel("  ");
        spoilageByArea = new JLabel("Spoilage By Area");
		// //////////
        // LINE 9 //
        shellPressLabel = new JLabel("ShellPress", SwingConstants.CENTER);
        shellPressJTextField1 = new JTextField();
        shellPressJTextField2 = new JTextField();
        shellPressJTextField3 = new JTextField();
        shellPressJTextFieldTotal1 = new JTextField();
        shellPressJTextField4 = new JTextField();
        shellPressJTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 10 /
        ABalancerLabel = new JLabel("A Balancer", SwingConstants.CENTER);
        ABalancerJTextField1 = new JTextField();
        ABalancerJTextField2 = new JTextField();
        ABalancerJTextField3 = new JTextField();
        ABalancerJTextFieldTotal1 = new JTextField();
        ABalancerJTextField4 = new JTextField();
        ABalancerJTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 11 /
        LinersLabel = new JLabel("Liners", SwingConstants.CENTER);
        LinersJTextField1 = new JTextField();
        LinersJTextField2 = new JTextField();
        LinersJTextField3 = new JTextField();
        LinersJTextFieldTotal1 = new JTextField();
        LinersJTextField4 = new JTextField();
        LinersTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 12 /
        BBalancerLabel = new JLabel("BBalancer", SwingConstants.CENTER);
        BBalancerJTextField1 = new JTextField();
        BBalancerJTextField2 = new JTextField();
        BBalancerJTextField3 = new JTextField();
        BBalancerJTextFieldTotal1 = new JTextField();
        BBalancerJTextField4 = new JTextField();
        BBalancerTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 13 /
        conversionPressLabel = new JLabel("ConversionPress", SwingConstants.CENTER);
        conversionPressJTextField1 = new JTextField();
        conversionPressJTextField2 = new JTextField();
        conversionPressJTextField3 = new JTextField();
        conversionPressJTextFieldTotal1 = new JTextField();
        conversionPressJTextField4 = new JTextField();
        conversionPressTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 14 /
        autoBaggersLabel = new JLabel("Auto Baggers", SwingConstants.CENTER);
        autoBaggersJTextField1 = new JTextField();
        autoBaggersJTextField2 = new JTextField();
        autoBaggersJTextField3 = new JTextField();
        autoBaggersJTextFieldTotal1 = new JTextField();
        autoBaggersJTextField4 = new JTextField();
        autoBaggersTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 15 /
        QCChecksLabel = new JLabel("QCChecks", SwingConstants.CENTER);
        QCChecksJTextField1 = new JTextField();
        QCChecksJTextField2 = new JTextField();
        QCChecksJTextField3 = new JTextField();
        QCChecksJTextFieldTotal1 = new JTextField();
        QCChecksJTextField4 = new JTextField();
        QCChecksTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 16 /
        TotalLineLabel = new JLabel("TotalLine", SwingConstants.CENTER);
        TotalLineJTextField1 = new JTextField();
        TotalLineJTextField2 = new JTextField();
        TotalLineJTextField3 = new JTextField();
        TotalLineJTextFieldTotal1 = new JTextField();
        TotalLineJTextField4 = new JTextField();
        TotalLineTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 17 /
        HFILabel = new JLabel("HFI", SwingConstants.CENTER);
        HFIJTextField1 = new JTextField();
        HFIJTextField2 = new JTextField();
        HFIJTextField3 = new JTextField();
        HFIJTextFieldTotal1 = new JTextField();
        HFIJTextField4 = new JTextField();
        HFITextFieldTotal2 = new JTextField();
		// //////////
        // LINE 18 /
        OtherLabel = new JLabel("Other", SwingConstants.CENTER);
        OtherJTextField1 = new JTextField();
        OtherJTextField2 = new JTextField();
        OtherJTextField3 = new JTextField();
        OtherJTextFieldTotal1 = new JTextField();
        OtherJTextField4 = new JTextField();
        OtherTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 19 /
        finalTotalLabel = new JLabel("Total", SwingConstants.CENTER);
        finalTotalJTextField1 = new JTextField();
        finalTotalJTextField2 = new JTextField();
        finalTotalJTextField3 = new JTextField();
        finalTotalJTextFieldTotal1 = new JTextField();
        finalTotalJTextField4 = new JTextField();
        finalTotalTextFieldTotal2 = new JTextField();
		// //////////
        // LINE 20 /
        lineLabel14 = new JLabel(" ");
        lineLabel15 = new JLabel("  ");
        lineLabel16 = new JLabel("  ");
        lineLabel17 = new JLabel("  ");
        lineLabel18 = new JLabel("  ");
        lineLabel19 = new JLabel("  ");
        // //////////

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.GRAY);
        topPanel.add(monthCombo);
        topPanel.add(yearCombo);
        topPanel.add(go);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(back);

        frame = new JFrame("Plant Spoilage");
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        outerPanel.add(topPanel, BorderLayout.NORTH);
        outerPanel.add(bottomPanel, BorderLayout.SOUTH);
        outerPanel.add(createLeftPanel(monthIn, yearIn), BorderLayout.WEST);
        outerPanel.add(createTablePanel(monthIn, yearIn), BorderLayout.CENTER);
        frame.add(outerPanel);
        frame.setVisible(true);

        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("PlantSpoilage");
    }

    public static JPanel createLeftPanel(String monthIn, String yearIn) {

        JPanel panel = new JPanel(new GridLayout(26, 1));

        panel.add(shellsProducedLabel);
        panel.add(shellsProducedJTextField);
        panel.add(endsPackedLabel);
        panel.add(endsPackedJTextField);
        panel.add(HFIOpeningLabel);
        panel.add(HFIOpeningJTextField);
        panel.add(HFICreatedLabel);
        panel.add(HFICreatedJTextField);
        panel.add(HFIRecoveredLabel);
        panel.add(HFIRecoveredJTextField);
        panel.add(HFIScrappedLabel);
        panel.add(HFIScrappedJTextField);
        panel.add(HFIRemainingLabel);
        panel.add(HFIRemainingJTextField);
        panel.add(HFISpoilageLabel);
        panel.add(HFISpoilageJTextField);
        panel.add(lineSpoilageLabel);
        panel.add(lineSpoilageJTextField);
        panel.add(totalSpoilageLabel);
        panel.add(totalSpoilageJTextField);
        panel.add(HFISpoilagePercentageLabel);
        panel.add(HFISpoilagePercentageJTextField);
        panel.add(lineSpoilagePercentageLabel);
        panel.add(lineSpoilagePercentageJTextField);
        panel.add(totalSpoilagePercentageLabel);
        panel.add(totalSpoilagePercentageJTextField);

        panel.setPreferredSize(new Dimension(150, 900));

        return panel;
    }

    public static JPanel createTablePanel(String monthIn, String yearIn) throws Exception {

        JPanel panel = new JPanel(new GridLayout(20, 7));

        // Line 1
        panel.add(lineLabel1);
        panel.add(lineLabel2);
        panel.add(lineLabel3);
        panel.add(productionByAreaLabel);
        panel.add(lineLabel5);
        panel.add(lineLabel6);
        panel.add(lineLabel7);
        // Line 2
        panel.add(lineLabel20);
        panel.add(shellPressMod1Label);
        panel.add(shellPressMod2Label);
        panel.add(shellPressMod3Label);
        panel.add(shellPressTotals1Label);
        panel.add(shellPressMod4Label);
        panel.add(shellPressTotals2Label);
        // Line 3
        panel.add(lineLabel21);
        panel.add(lineLabel27);
        panel.add(shellPressMod2JTextField);
        panel.add(shellPressMod3JTextField);
        panel.add(B64TotalJTextField);
        panel.add(shellPressMod4JTextField);
        panel.add(shellPressTotalJTextField);
        // Line 4
        panel.add(lineLabel22);
        panel.add(linerMod1Label);
        panel.add(linerMod2Label);
        panel.add(linerMod3Label);
        panel.add(linerTotals1Label);
        panel.add(linerMod4Label);
        panel.add(linerTotals2Label);
        // Line 5
        panel.add(lineLabel23);
        panel.add(linerMod1JTextField);
        panel.add(linerMod2JTextField);
        panel.add(linerMod3JTextField);
        panel.add(linerTotals1JTextField);
        panel.add(linerMod4JTextField);
        panel.add(linerTotals2JTextField);
        // Line 6
        panel.add(lineLabel24);
        panel.add(stolleMod1Label);
        panel.add(stolleMod2Label);
        panel.add(stolleMod3Label);
        panel.add(stolleTotals1Label);
        panel.add(stolleMod4Label);
        panel.add(stolleTotals2Label);
        // Line 7
        panel.add(stolleMod1Label);
        panel.add(stolleMod2Label);
        panel.add(stolleMod3Label);
        panel.add(stolleTotals1Label);
        panel.add(stolleMod4Label);
        panel.add(stolleTotals2Label);
        // Line 8
        panel.add(lineLabel26);
        panel.add(stolleMod1JTextField);
        panel.add(stolleMod2JTextField);
        panel.add(stolleMod3JTextField);
        panel.add(stolleTotals1JTextField);
        panel.add(stolleMod4JTextField);
        panel.add(stolleTotals2JTextField);
        // Line 20
        panel.add(lineLabel14);
        panel.add(lineLabel15);
        panel.add(lineLabel16);
        panel.add(spoilageByArea);
        panel.add(lineLabel18);
        panel.add(lineLabel19);
        panel.add(lineLabel25);
        // Line 9
        panel.add(shellPressLabel);
        panel.add(lineLabel28);
        panel.add(shellPressJTextField2);
        panel.add(shellPressJTextField3);
        panel.add(shellPressJTextFieldTotal1);
        panel.add(shellPressJTextField4);
        panel.add(shellPressJTextFieldTotal2);
        // Line 10
        panel.add(ABalancerLabel);
        panel.add(ABalancerJTextField1);
        panel.add(ABalancerJTextField2);
        panel.add(ABalancerJTextField3);
        panel.add(ABalancerJTextFieldTotal1);
        panel.add(ABalancerJTextField4);
        panel.add(ABalancerJTextFieldTotal2);
        // Line 11
        panel.add(LinersLabel);
        panel.add(LinersJTextField1);
        panel.add(LinersJTextField2);
        panel.add(LinersJTextField3);
        panel.add(LinersJTextFieldTotal1);
        panel.add(LinersJTextField4);
        panel.add(LinersTextFieldTotal2);
        // Line 12
        panel.add(BBalancerLabel);
        panel.add(BBalancerJTextField1);
        panel.add(BBalancerJTextField2);
        panel.add(BBalancerJTextField3);
        panel.add(BBalancerJTextFieldTotal1);
        panel.add(BBalancerJTextField4);
        panel.add(BBalancerTextFieldTotal2);
        // Line 13
        panel.add(conversionPressLabel);
        panel.add(conversionPressJTextField1);
        panel.add(conversionPressJTextField2);
        panel.add(conversionPressJTextField3);
        panel.add(conversionPressJTextFieldTotal1);
        panel.add(conversionPressJTextField4);
        panel.add(conversionPressTextFieldTotal2);
        // Line 14
        panel.add(autoBaggersLabel);
        panel.add(autoBaggersJTextField1);
        panel.add(autoBaggersJTextField2);
        panel.add(autoBaggersJTextField3);
        panel.add(autoBaggersJTextFieldTotal1);
        panel.add(autoBaggersJTextField4);
        panel.add(autoBaggersTextFieldTotal2);
        // Line 15
        panel.add(QCChecksLabel);
        panel.add(QCChecksJTextField1);
        panel.add(QCChecksJTextField2);
        panel.add(QCChecksJTextField3);
        panel.add(QCChecksJTextFieldTotal1);
        panel.add(QCChecksJTextField4);
        panel.add(QCChecksTextFieldTotal2);
        // Line 16
        panel.add(TotalLineLabel);
        panel.add(TotalLineJTextField1);
        panel.add(TotalLineJTextField2);
        panel.add(TotalLineJTextField3);
        panel.add(TotalLineJTextFieldTotal1);
        panel.add(TotalLineJTextField4);
        panel.add(TotalLineTextFieldTotal2);
        // Line 17
        panel.add(HFILabel);
        panel.add(HFIJTextField1);
        panel.add(HFIJTextField2);
        panel.add(HFIJTextField3);
        panel.add(HFIJTextFieldTotal1);
        panel.add(HFIJTextField4);
        panel.add(HFITextFieldTotal2);
        // Line 18
        panel.add(OtherLabel);
        panel.add(OtherJTextField1);
        panel.add(OtherJTextField2);
        panel.add(OtherJTextField3);
        panel.add(OtherJTextFieldTotal1);
        panel.add(OtherJTextField4);
        panel.add(OtherTextFieldTotal2);
        // Line 19
        panel.add(finalTotalLabel);
        panel.add(finalTotalJTextField1);
        panel.add(finalTotalJTextField2);
        panel.add(finalTotalJTextField3);
        panel.add(finalTotalJTextFieldTotal1);
        panel.add(finalTotalJTextField4);
        panel.add(finalTotalTextFieldTotal2);

        B64TotalJTextField.setBackground(new Color(255, 255, 153));
        shellPressTotalJTextField.setBackground(new Color(255, 255, 153));

        linerTotals1JTextField.setBackground(new Color(255, 255, 153));
        linerTotals2JTextField.setBackground(new Color(255, 255, 153));

        stolleTotals1JTextField.setBackground(new Color(255, 255, 153));
        stolleTotals2JTextField.setBackground(new Color(255, 255, 153));

        shellPressJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        shellPressJTextFieldTotal2.setBackground(new Color(255, 255, 153));

        ABalancerJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        ABalancerJTextFieldTotal2.setBackground(new Color(255, 255, 153));

        LinersJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        LinersTextFieldTotal2.setBackground(new Color(255, 255, 153));

        BBalancerJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        BBalancerTextFieldTotal2.setBackground(new Color(255, 255, 153));

        conversionPressJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        conversionPressTextFieldTotal2.setBackground(new Color(255, 255, 153));

        autoBaggersJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        autoBaggersTextFieldTotal2.setBackground(new Color(255, 255, 153));

        QCChecksJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        QCChecksTextFieldTotal2.setBackground(new Color(255, 255, 153));

        TotalLineJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        TotalLineTextFieldTotal2.setBackground(new Color(255, 255, 153));

        HFIJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        HFITextFieldTotal2.setBackground(new Color(255, 255, 153));

        OtherJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        OtherTextFieldTotal2.setBackground(new Color(255, 255, 153));

        finalTotalJTextFieldTotal1.setBackground(new Color(255, 255, 153));
        finalTotalTextFieldTotal2.setBackground(new Color(255, 255, 153));

        try {
            fillTables(monthIn, yearIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return panel;
    }

    public static void fillTables(String monthIn, String yearIn) throws Exception {

        int m4BBal = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[30] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[24];

        int total1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + // Optime
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1];

        int total2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + // M1ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8];

        int total3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4] + // Liners
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21];

        int total4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + // M1BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14];

        int total5 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6]
                + // Stolle
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15];

        int total6 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[32]; // Optime4
        int total7 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31]; // Balancer4A
        int total8 = total1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25]; // LinerM4
        int total9 = total1 + m4BBal; // M4BBal
        int total10 = total5 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[27] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[23]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[28] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[29];
        // Conversion Presses

        int lineSpoilageTotal = 0;
        int totalSpoilage = 0;
        try {
            lineSpoilageTotal = SQLiteConnection.SpoilageByDayTotalMonthlySum(monthIn, yearIn);
            totalSpoilage = SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4] + lineSpoilageTotal;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		// Spoilage Percentages
        // Hfi SPOILAGE
        DecimalFormat df = new DecimalFormat("###.##"); // <- // "###.###"
        // specifies precision
        double hfiSpoilageDouble = SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4] + 0.0;
        double shellsProducedDouble = (Double.parseDouble(SQLiteConnection.PlantSpoilageShellsProducedCalculateTotalsByMonth(monthIn, yearIn) + ""));
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (hfiSpoilageDouble == 0.0) {
            hfiSpoilageDouble = 0.01;
        }
        double HFISpoilagePercentage = (hfiSpoilageDouble / shellsProducedDouble) * 100;
        double HFISpoilagePercentageRounded = Double.parseDouble(df.format(HFISpoilagePercentage).toString());
		// ////////////////////

        // Line SPOILAGE
        double lineSpoilageDouble = lineSpoilageTotal + 0.0;
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (lineSpoilageDouble == 0.0) {
            lineSpoilageDouble = 0.01;
        }
        double lineSpoilagePercentage = (lineSpoilageDouble / shellsProducedDouble) * 100;
        double lineSpoilagePercentageRounded = Double.parseDouble(df.format(lineSpoilagePercentage).toString());
		// ////////////////////

        // Total SPOILAGE
        double totalSpoilageDouble = totalSpoilage + 0.0;
        if (shellsProducedDouble == 0.0) {
            shellsProducedDouble = 0.01;
        }
        if (totalSpoilageDouble == 0.0) {
            totalSpoilageDouble = 0.01;
        }
        double totalSpoilagePercentage = (totalSpoilageDouble / shellsProducedDouble) * 100;
        double totalSpoilagePercentageRounded = Double.parseDouble(df.format(totalSpoilagePercentage).toString());
		// ////////////////////

        // Calculating Others 1
        int h172 = SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn)
                + SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn);
        int h168 = SQLiteConnection.PlantSpoilageOptime2CalculateTotalsByMonth(monthIn, yearIn);
        if (h168 == 0) {
            h168 = 1;
        }
        if (h172 == 0) {
            h172 = 1;
        }
        System.out.println("h168 " + h168);
        System.out.println("h172 " + h172);
        int h165 = h168 / h172;
        int i165 = SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn) + h165;
        double n195 = totalSpoilagePercentageRounded + lineSpoilagePercentageRounded + HFISpoilagePercentageRounded;
        double totalOther = total6 * n195; // To be finished
        if (i165 == 0) {
            i165 = 1;
        }
        if (total6 == 0) {
            total6 = 1;
        }
        if (totalOther == 0) {
            totalOther = 1;
        }

        double other1 = (i165 / total6) * totalOther; // To be finished
        // //////////////////////////////
        // Calculating Others 2
        int j165 = SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn) + h165;
        double other2 = (j165 / total6) * totalOther; // To be finished
        // /////////////////////////////
        // Calculating Others 3
        double other3 = (SQLiteConnection.PlantSpoilageOptime3CalculateTotalsByMonth(monthIn, yearIn) / total6) * totalOther;
		// /////////////////////////////
        // Calculating Others 4
        double other4 = (SQLiteConnection.PlantSpoilageOptime4CalculateTotalsByMonth(monthIn, yearIn) / total6) * totalOther;
        // /////////////////////////////

        int conversionPress1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16];
        int conversionPress2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7];
        int conversionPress3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18];
        int conversionPress4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[27] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[23]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[28] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[29];

		// QC Checks
        int QCChecks1 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[26])
                + ((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 5);

        int QCChecks2 = ((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 5);

        int QCChecks3 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[22])
                + (((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 10) * 3);

        int QCChecks4 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[13])
                + (((SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] + SQLiteConnection
                .SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12]) / 10) * 3);

        int QCChecks5 = QCChecks1 + QCChecks2 + QCChecks3 + QCChecks4;
        int QCChecks6 = QCChecks1 + QCChecks2 + QCChecks3;

        int totalLine1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16] + QCChecks1;

        int totalLine2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + // Optime2
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + // M2ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + // M2Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] + // M2BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14] + // STolle
                // 21
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7] + // STolle
                // 22
                QCChecks2;

        int totalLine3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + // Optime
                // 3
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] + // M3ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + // M3Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + // M3BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17] + // Stolle
                // 31
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + // Stolle
                // 32
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + // Stolle
                // 33
                QCChecks3;

        int totalLine4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + // Optime
                // 3
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] + // M3ABAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + // M3Liner
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + // M3BBAL
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17] + // Stolle
                // 31
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] + // Stolle
                // 32
                SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] + // Stolle
                // 33
                QCChecks3;

        int totalLine5 = totalLine1 + totalLine2 + totalLine3 + totalLine4;

        int totalLine6 = totalLine1 + totalLine2 + totalLine3;

		// Calculate totalFinals
        m4BBal = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[30] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[24];

        double totalFinal1 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + conversionPress1 + QCChecks1 + totalLine1
                + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other1;
        double totalFinal2 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] + conversionPress2
                + QCChecks2 + totalLine2 + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other2;
        double totalFinal3 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + conversionPress3
                + QCChecks3 + totalLine3 + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other3;
        double totalFinal4 = SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31]
                + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25] + m4BBal + conversionPress4 + QCChecks4 + totalLine4
                + (SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) + other4;
        double totalFinal5 = totalFinal1 + totalFinal2 + totalFinal3;
        double totalFinal6 = totalFinal5 + totalFinal4;

        int[] result1 = new int[13];

        try {

            shellsProducedJTextField.setText(SQLiteConnection.PlantSpoilageShellsProducedCalculateTotalsByMonth(monthIn, yearIn) + "");
            endsPackedJTextField.setText(SQLiteConnection.PlantSpoilageEndCountsCalculateTotalsByMonth(monthIn, yearIn) + "");
            HFIOpeningJTextField.setText("NA");
            HFICreatedJTextField.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[0] + "");
            HFIRecoveredJTextField.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[1] + "");
            HFIScrappedJTextField.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[2] + "");
            HFIRemainingJTextField.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[3] + "");
            HFISpoilageJTextField.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[4] + "");
            lineSpoilageJTextField.setText(lineSpoilageTotal + "");
            totalSpoilageJTextField.setText(totalSpoilage + "");
            HFISpoilagePercentageJTextField.setText(HFISpoilagePercentageRounded + "%");
            lineSpoilagePercentageJTextField.setText(lineSpoilagePercentageRounded + "%");
            totalSpoilagePercentageJTextField.setText(totalSpoilagePercentageRounded + "%");

            shellPressMod1JTextField.setText("");
            shellPressMod2JTextField.setText(SQLiteConnection.PlantSpoilageOptime2CalculateTotalsByMonth(monthIn, yearIn) + "");
            shellPressMod3JTextField.setText(SQLiteConnection.PlantSpoilageOptime3CalculateTotalsByMonth(monthIn, yearIn) + "");
            B64TotalJTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[0] + "");
            shellPressMod4JTextField.setText(SQLiteConnection.PlantSpoilageOptime4CalculateTotalsByMonth(monthIn, yearIn) + "");
            shellPressTotalJTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[3] + "");

            linerMod1JTextField.setText(SQLiteConnection.PlantSpoilageM1CalculateTotalsByMonth(monthIn, yearIn) + "");
            linerMod2JTextField.setText(SQLiteConnection.PlantSpoilageM2CalculateTotalsByMonth(monthIn, yearIn) + "");
            linerMod3JTextField.setText(SQLiteConnection.PlantSpoilageM3CalculateTotalsByMonth(monthIn, yearIn) + "");
            linerTotals1JTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[1] + "");
            linerMod4JTextField.setText(SQLiteConnection.PlantSpoilageM4CalculateTotalsByMonth(monthIn, yearIn) + "");
            linerTotals2JTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[4] + "");

            stolleMod1JTextField.setText(SQLiteConnection.PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn) + "");
            stolleMod2JTextField.setText(SQLiteConnection.PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn) + "");
            stolleMod3JTextField.setText(SQLiteConnection.PlantSpoilageStolleMod3CalculateTotalsByMonth(monthIn, yearIn) + "");
            stolleTotals1JTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[2] + "");
            stolleMod4JTextField.setText(SQLiteConnection.PlantSpoilageStolleMod4CalculateTotalsByMonth(monthIn, yearIn) + "");
            stolleTotals2JTextField.setText(SQLiteConnection.PlantSpoilageTotals(monthIn, yearIn)[5] + "");

            shellPressJTextField1.setText("");
            shellPressJTextField2.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] + "");
            shellPressJTextField3.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] + "");
            shellPressJTextFieldTotal1.setText(total1 + "");
            shellPressJTextField4.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + "");
            shellPressJTextFieldTotal2.setText(total6 + "");

            int AbalTotal1 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9]
                    + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20]
                    + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8]);
            int AbalTotal2 = (AbalTotal1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31]);

            ABalancerJTextField1.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] + "");
            ABalancerJTextField2.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] + "");
            ABalancerJTextField3.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] + "");
            ABalancerJTextFieldTotal1.setText(AbalTotal1 + "");
            ABalancerJTextField4.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31] + "");
            ABalancerJTextFieldTotal2.setText(AbalTotal2 + "");

            int linerTotal1 = (SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4]
                    + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5]
                    + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21]);
            int linerTotal2 = linerTotal1 + SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25];

            LinersJTextField1.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4] + "");
            LinersJTextField2.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] + "");
            LinersJTextField3.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] + "");
            LinersJTextFieldTotal1.setText(linerTotal1 + "");
            LinersJTextField4.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25] + "");
            LinersTextFieldTotal2.setText(linerTotal2 + "");

            BBalancerJTextField1.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] + "");
            BBalancerJTextField2.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] + "");
            BBalancerJTextField3.setText(SQLiteConnection.SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] + "");
            BBalancerJTextFieldTotal1.setText(total4 + "");
            BBalancerJTextField4.setText(m4BBal + "");
            BBalancerTextFieldTotal2.setText(total9 + "");

            conversionPressJTextField1.setText(conversionPress1 + "");
            conversionPressJTextField2.setText(conversionPress2 + "");
            conversionPressJTextField3.setText(conversionPress3 + "");
            conversionPressJTextFieldTotal1.setText(total5 + "");
            conversionPressJTextField4.setText(conversionPress4 + "");
            conversionPressTextFieldTotal2.setText(total10 + "");

            autoBaggersJTextField1.setText("");
            autoBaggersJTextField2.setText("");
            autoBaggersJTextField3.setText("");
            autoBaggersJTextFieldTotal1.setText("");
            autoBaggersJTextField4.setText("");
            autoBaggersTextFieldTotal2.setText("");

            QCChecksJTextField1.setText(QCChecks1 + "");
            QCChecksJTextField2.setText(QCChecks2 + "");
            QCChecksJTextField3.setText(QCChecks3 + "");
            QCChecksJTextFieldTotal1.setText(QCChecks6 + "");
            QCChecksJTextField4.setText(QCChecks4 + "");
            QCChecksTextFieldTotal2.setText(QCChecks5 + "");

            TotalLineJTextField1.setText(totalLine1 + "");
            TotalLineJTextField2.setText(totalLine2 + "");
            TotalLineJTextField3.setText(totalLine3 + "");
            TotalLineJTextFieldTotal1.setText(totalLine6 + "");
            TotalLineJTextField4.setText(totalLine4 + "");
            TotalLineTextFieldTotal2.setText(totalLine5 + "");

            HFIJTextField1.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4 + "");
            HFIJTextField2.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4 + "");
            HFIJTextField3.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4 + "");
            HFIJTextFieldTotal1.setText(((SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4) * 3) + "");
            HFIJTextField4.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] / 4 + "");
            HFITextFieldTotal2.setText(SQLiteConnection.PlantSpoilageGetHFI(monthIn, yearIn)[5] + "");

            OtherJTextField1.setText(other1 + "");
            OtherJTextField2.setText(other2 + "");
            OtherJTextField3.setText(other3 + "");
            OtherJTextFieldTotal1.setText(other1 + other2 + other3 + "");
            OtherJTextField4.setText(other4 + "");
            OtherTextFieldTotal2.setText(other1 + other2 + other3 + other4 + "");

            finalTotalJTextField1.setText(totalFinal1 + "");
            finalTotalJTextField2.setText(totalFinal2 + "");
            finalTotalJTextField3.setText(totalFinal3 + "");
            finalTotalJTextFieldTotal1.setText(totalFinal5 + "");
            finalTotalJTextField4.setText(totalFinal4 + "");
            finalTotalTextFieldTotal2.setText(totalFinal6 + "");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
