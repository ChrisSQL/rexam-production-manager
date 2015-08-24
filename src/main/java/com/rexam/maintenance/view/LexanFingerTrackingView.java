package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.dao.LexanFingerTrackingDAO;
import com.rexam.maintenance.model.LexanFingerTrackingModel;
import com.toedter.calendar.JDateChooser;

public class LexanFingerTrackingView extends JFrame {

	JPanel outerPanel, innerPanel1;
	JDateChooser chooser1;
	JComboBox monthCombo, yearCombo, balancerCombo, subCombo;
	JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1,
			calculateTotal2, calculateTotal3;
	JLabel dateLabel;
	LexanFingerTrackingModel ltm;
	private LexanFingerTrackingDAO lexanFingerTrackingDAO;
	JFrame frameSummary;

	public static void main(String[] args) {

		new LexanFingerTrackingView(1, -1);

	}

	public LexanFingerTrackingView(int idIn, int view) {
		
		setStyle();

		ltm = new LexanFingerTrackingModel();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		lexanFingerTrackingDAO = (LexanFingerTrackingDAO) context.getBean("LexanFingerTrackingDAO");

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		outerPanel = new JPanel(new BorderLayout());
		innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		setTitle("Lexan Tracking");
		setSize(360, 230);
		setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		chooser1 = new JDateChooser();
		chooser1.setDate(new Date());

		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028" };
		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);
		String[] balancerArray = { "Balancer 1A", "Balancer 2A", "Balancer 3A", "Balancer 4A", "Balancer 4A New",
				"Balancer 1B", "Balancer 2B", "Balancer 3B", "Balancer 4B" };
		balancerCombo = new JComboBox(balancerArray);
		String[] subArray = { "R-R", "R-L", "L-L", "L-R" };
		subCombo = new JComboBox(subArray);

		initializeVariables();

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		// buttonsPanel.add(previous);
		// buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		JPanel optionPanel1 = new JPanel(new GridLayout(3, 2));
		// optionPanel1.setBackground(Color.GRAY);

		// ComboPanelMonthly
		JPanel comboPanel = new JPanel(new FlowLayout());
		// comboPanel.add(monthCombo);
		// comboPanel.add(yearCombo);
		// comboPanel.add(go);

		// Adding
		if (view == -1) {

			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);
			back.setVisible(false);
			// summary.setVisible(false);

			optionPanel1.add(new JLabel("Balancer ", SwingConstants.CENTER));
			optionPanel1.add(balancerCombo);

			optionPanel1.add(new JLabel("Sub  ", SwingConstants.CENTER));
			optionPanel1.add(subCombo);

			optionPanel1.add(new JLabel("Date", SwingConstants.CENTER));
			optionPanel1.add(chooser1);

		} // Searching
		else if (view == -2) {

			// currentId =
			// lexanFingerTrackingDAO.MaintenanceShellPressProductionGetHighestID();
			// buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);
			back.setVisible(false);
			addNew.setVisible(false);

			optionPanel1.add(new JLabel("Balancer ", SwingConstants.CENTER));
			optionPanel1.add(balancerCombo);

			optionPanel1.add(new JLabel("Sub  ", SwingConstants.CENTER));
			optionPanel1.add(subCombo);

			optionPanel1.add(new JLabel("Date", SwingConstants.CENTER));
			optionPanel1.add(chooser1);

		} // Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(3, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(balancerCombo);
			optionPanel1.add(new JLabel("Date", SwingConstants.CENTER));
			optionPanel1.add(chooser1);

			monthly.setVisible(false);
			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			search.setVisible(false);
			update.setVisible(false);
			add.setVisible(false);
			addNew.setVisible(false);

		}

		JPanel commentsPanel = new JPanel();

		outerPanel.add(optionPanel1, BorderLayout.CENTER);

		// Options Panel 2
		JPanel optionsPanel2 = new JPanel(new FlowLayout());
		optionsPanel2.add(summary);
		optionsPanel2.add(addNew);
		// optionsPanel2.add(search);
		// optionsPanel2.add(monthly);
		optionsPanel2.add(update);
		optionsPanel2.add(add);
		optionsPanel2.add(back);
		optionsPanel2.setBackground(Color.GRAY);

		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.add(commentsPanel, BorderLayout.NORTH);
		bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		outerPanel.repaint();
		add(outerPanel);

		setVisible(true);

		// SQLiteConnection.AnalyticsUpdate("LexanFingerTracking");

	}

	private void initializeVariables() {

		back = new JButton("Back");
		add = new JButton("Save Record");
		addNew = new JButton("New Entry Mode");
		search = new JButton("Search Mode");
		monthly = new JButton("Monthly");
		update = new JButton("Update Record");
		summary = new JButton("Summary");
		find = new JButton("Find Record");
		next = new JButton("Next Record");
		previous = new JButton("Previous Record");
		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);

	}

	public void addActionListeners() {

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LexanFingerTrackingView(1, -1);
				dispose();

			}
		});
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Get int value of a JTextfield

				lexanFingerTrackingDAO.MaintenanceLexanFingerTrackingInsert(ltm);

				// TODO Auto-generated method stub
				dispose();

			}
		});
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LexanFingerTrackingView(1, -1);
				dispose();

			}
		});
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				lexanFingerTrackingDAO.MaintenanceLexanFingerTrackingUpdate(ltm);
				dispose();
				dispose();
				// LexanFingerTrackingView.createSummaryScreen();

				// TODO Auto-generated method stub
			}
		});
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					createSummaryScreen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});
	
	}

	public void createSummaryScreen() throws SQLException {

        addNew = new JButton("New Entry Mode");
        addNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();
                new LexanFingerTrackingView(1, -1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

//                frameSummary.dispose();
//                try {
//                    LexanFingerTrackingView.createSummaryScreen();
//                } catch (SQLException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }

            }
        });

        JButton print = new JButton("Print Report");
        JButton ExportToExcel = new JButton("Export To Excel");

        ExportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

           //     LexanFingerTrackingView.exportToExcel();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Lexan Finger Tracking");
        frameSummary.setSize(1300, 700);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(refresh);
        optionsPanel2.add(ExportToExcel);

        JPanel summaryPanel = lexanFingerTrackingDAO.MaintenanceLexanFingerTrackingSummaryTable(1);
        JScrollPane scrollPane = new JScrollPane(summaryPanel);

        optionsPanel2.setBackground(Color.GRAY);

        outerPanel.add(scrollPane, BorderLayout.CENTER);
        outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
        summary.setVisible(false);
        frameSummary.add(outerPanel);
        frameSummary.setVisible(true);

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
