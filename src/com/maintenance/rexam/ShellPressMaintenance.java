package com.maintenance.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ShellPressMaintenance {

    JFrame outerFrame;
    static JButton SP01, SP02, SP03, SP04;
    

    public static void main(String[] args) {

        try {
            new ShellPressMaintenance(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ShellPressMaintenance(int spIn) throws SQLException {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }

        JFrame frame = new JFrame("Shell Press Maintenance");
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new BorderLayout());

        outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
        outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);

        frame.add(outerPanel);
        frame.setVisible(true);

    }

    private static JPanel createOptionsPanel(int spIn) {

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        SP01 = new JButton("SP01");
        SP02 = new JButton("SP02");
        SP03 = new JButton("SP03");
        SP04 = new JButton("SP04");

        optionsPanel.add(SP01);
        optionsPanel.add(SP02);
        optionsPanel.add(SP03);
        optionsPanel.add(SP04);

        return optionsPanel;

    }

    private static JPanel createMiddlePanel(int spIn) {
        
        

        JPanel middlePanel = new JPanel(new GridLayout(8,10));
        middlePanel.setBackground(Color.WHITE);
        
        // ROW1
        middlePanel.add(new JLabel("Machine Code"));
        middlePanel.add(new JLabel("Machine Name"));
        middlePanel.add(new JLabel("Tooling Area"));
        middlePanel.add(new JLabel("Last Maintenance"));
        middlePanel.add(new JLabel("Maintenance Type"));
        middlePanel.add(new JLabel("Target Production"));
        middlePanel.add(new JLabel("Production"));
        middlePanel.add(new JLabel("+ / -"));
        middlePanel.add(new JLabel("Maintenance Due Date"));
        middlePanel.add(new JLabel("Days Remaining"));
        // ROW2
        middlePanel.add(new JLabel("SP02"));
        middlePanel.add(new JLabel("Optime 2"));
        middlePanel.add(new JLabel("Form 1-6"));
        
//            middlePanel.add(new JLabel(DateSelector));
//            middlePanel.add(new JLabel(HourJCombo));
//            middlePanel.add(new JLabel(JTextField));
//            middlePanel.add(new JLabel(JTextField));
//            middlePanel.add(new JLabel(JTextField));
//            middlePanel.add(new JLabel(DateSelector));
//            middlePanel.add(new JLabel(JTextField));
        
        // ROW3
        // ROW4
        // ROW5
        // ROW6
        // ROW7
        // ROW8   

        return middlePanel;
    }

}
