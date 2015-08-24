package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.production.dao.LSSPMActivityDAO;
import com.rexam.production.model.LSSPMActivityModel;
import com.toedter.calendar.JDateChooser;

public class LSSPMActivityView extends JFrame {

	private JPanel outerPanel, topButtonPanel, bottomButtonPanel, commentsPanel, dateJPanel;
	private JButton find, next, previous, search, update, save, addNew, refresh, summary;
	private JLabel selectDate, selectedDateThenFind;
	private JTextArea commentTextArea;
	JFrame frameSummary;
	LSSPMActivityModel ls;
	LSSPMActivityDAO lSSPMActivityDAO;
	int currentId;
	JDateChooser chooser1;

	public static void main(String args[]) {

		new LSSPMActivityView(2, "", "", -1);

	}

	public LSSPMActivityView(int id, String date, String comment, int view) {
		
		ls = new LSSPMActivityModel();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		lSSPMActivityDAO = (LSSPMActivityDAO) context.getBean("LSSPMActivityDAO");

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
		chooser1 = new JDateChooser(new Date());

		// Create OuterFrame and Panels
		setTitle("LSSPM Entry");
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(450, 370);
		setLocationRelativeTo(null);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(outerPanel);
		topButtonPanel = new JPanel(new FlowLayout());
		outerPanel.add(topButtonPanel, BorderLayout.NORTH);
		commentsPanel = new JPanel(new BorderLayout());
		bottomButtonPanel = new JPanel(new FlowLayout());
		outerPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
		dateJPanel = new JPanel(new FlowLayout());

		// Declare JButtons
		selectDate = new JLabel("Select Date : ");
		selectedDateThenFind = new JLabel("Select Date then Click Find : ");
		commentTextArea = new JTextArea();
		commentTextArea = new JTextArea(12, 20);
		commentTextArea.setLineWrap(true);
		commentTextArea.setWrapStyleWord(true);

		find = new JButton("Find");
		next = new JButton("Next");
	
		previous = new JButton("Previous");
		search = new JButton("Search Mode");
		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ls.setDate(chooser1.getDate());
				ls.setComment(commentTextArea.getText());

				try {
					lSSPMActivityDAO.LSSPMUpdate(ls);

					dispose();
					createSummaryScreen();

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
				
				
				ls.setDate(chooser1.getDate());
				ls.setComment(commentTextArea.getText());

				try {
					lSSPMActivityDAO.LSSPMInsert(ls);

					dispose();
					createSummaryScreen();

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

				dispose();
				try {
					new LSSPMActivityView(2, "", "", -1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(LSSPMActivityView.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(LSSPMActivityView.class.getName()).log(Level.SEVERE, null, ex);
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
			currentId = 1;
			addNew.setVisible(false);
			// dateArray =
			// lSSPMActivityDAO.LSSPMEntryqueryHighestArrayDateFormatted();
			// model.setDate((int) dateArray[0], (int) dateArray[1] - 1,
			// (int) dateArray[2]);
			// model.setSelected(true);
			// Set Comment to last comment Date
			// String commenttext =
			// lSSPMActivityDAO.LSSPMEntryqueryHighestCommentFormatted();
			// commentTextArea.setText(commenttext);
			search.setVisible(false);
			selectDate.setVisible(false);
			save.setVisible(false);

		}

		// Add JButtons
		// topButtonPanel.add(find);
		topButtonPanel.add(previous);
		topButtonPanel.add(next);

		outerPanel.add(commentsPanel);

		bottomButtonPanel.add(summary);
		bottomButtonPanel.add(addNew);
		bottomButtonPanel.add(search);
		bottomButtonPanel.add(update);
		bottomButtonPanel.add(save);

		// datePicker Setup
		

		
		dateJPanel.add(selectedDateThenFind);
		dateJPanel.add(selectDate);
		dateJPanel.add(chooser1);
		// model.setDate(todaysDateArray[0], todaysDateArray[1],
		// todaysDateArray[2]);
		// model.setDate(2014, 6, 13);
		// model.setSelected(true);
		commentsPanel.add(dateJPanel, BorderLayout.NORTH);
		commentsPanel.add(commentTextArea, BorderLayout.SOUTH);

		setVisible(true);

	//	lSSPMActivityDAO.AnalyticsUpdate("LSSPMActivityEntry");
	}

	public void setLinerUsageToId(int idIn) {

		try {

			int highestID = idIn;
			ls = lSSPMActivityDAO.LSSPMEntryqueryId(idIn);

			// Date

			// Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled,
			// CalculatedSpoilage
			commentTextArea.setText(ls.getComment());

			currentId = highestID;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createSummaryScreen() throws SQLException {

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new LSSPMActivityView(2, "", "", -1);
				} catch (Exception ex) {
					Logger.getLogger(LSSPMActivityView.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});
		refresh = new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					createSummaryScreen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		JButton print = new JButton("Print Report");
		JButton ExportToExcel = new JButton("Export To Excel");

//		ExportToExcel.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				LinerDataEntryScreen.exportToExcel();
//
//			}
//		});

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

		JPanel summaryPanel = lSSPMActivityDAO.LSSPMSummaryTable(1);
		JScrollPane scrollPane = new JScrollPane(summaryPanel);

		// print.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// getDate("Optime Production Report", 1);
		//
		// }
		// });
		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(scrollPane, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}

}
