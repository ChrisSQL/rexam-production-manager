package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.controller.MaintenanceDatabaseController;
import com.rexam.maintenance.dao.BalancerProductionDAO;
import com.rexam.maintenance.dao.LexanFingerTrackingDAO;

public class BalancerProductionView extends JFrame{
	
	JButton addNew, refresh;
	JPanel outerPanel, optionsPanel2, summaryPanel;
	JScrollPane scrollPane;
	MaintenanceDatabaseController db;
	BalancerProductionDAO balancerProductionDAO;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new BalancerProductionView();

	}
	
	public BalancerProductionView() {
		
		
		
		setStyle();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		balancerProductionDAO = (BalancerProductionDAO) context.getBean("BalancerProductionDAO");

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new BalancerProductionView();

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new BalancerProductionView();

            }
        });

        JButton print = new JButton("Print Report");
        JButton ExportToExcel = new JButton("Export To Excel");

        ExportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

          //    BalancerProduction.exportToExcel();

            }
        });

        JButton importFromExcel = new JButton("Import From Viscan");
        importFromExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
       //           ShellPressProduction.importFromExcel();

            }
        });

        // Outer Frame
        setTitle("Balancer Production Report");
        setSize(1300, 700);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        // JPanel
        outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        optionsPanel2 = new JPanel(new FlowLayout());

//      optionsPanel2.add(addNew);
//      optionsPanel2.add(summary);
//      optionsPanel2.add(refresh);
//      optionsPanel2.add(print);
//      optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromExcel);

        summaryPanel = balancerProductionDAO.MaintenanceBalancerProductionSummaryTable(1);
        scrollPane = new JScrollPane(summaryPanel);

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
//      summary.setVisible(false);
        add(outerPanel);
        setVisible(true);

    }
	
	public void setStyle() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
	}
	
	

}
