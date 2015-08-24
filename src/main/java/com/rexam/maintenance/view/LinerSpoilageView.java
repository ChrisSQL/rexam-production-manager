package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.dao.LinerProductionDAO;
import com.rexam.maintenance.dao.LinerSpoilageDAO;
import com.rexam.maintenance.model.LinerSpoilageModel;
import com.toedter.calendar.JDateChooser;

public class LinerSpoilageView extends JFrame {

	static JButton add, find, next, previous, delete, update, addNew, summary, refresh, search, monthly, go, back,
			calculateTotal1, calculateTotal2, calculateTotal3;
	JLabel dateLabel, dateLabel2;
	static JTextField Liner11JTextField, Liner12JTextField, Liner13JTextField, Liner14JTextField, Liner21JTextField,
			Liner22JTextField, Liner23JTextField, Liner24JTextField, Liner31JTextField, Liner32JTextField,
			Liner33JTextField, Liner34JTextField, Liner41JTextField, Liner42JTextField, Liner43JTextField,
			Liner44JTextField, Liner45JTextField, Liner46JTextField;
	static JTextField Liner11MonthlyJTextField, Liner12MonthlyJTextField, Liner13MonthlyJTextField,
			Liner14MonthlyJTextField, Liner21MonthlyJTextField, Liner22MonthlyJTextField, Liner23MonthlyJTextField,
			Liner24MonthlyJTextField, Liner31MonthlyJTextField, Liner32MonthlyJTextField, Liner33MonthlyJTextField,
			Liner34MonthlyJTextField, Liner41MonthlyJTextField, Liner42MonthlyJTextField, Liner43MonthlyJTextField,
			Liner44MonthlyJTextField, Liner45MonthlyJTextField, Liner46MonthlyJTextField;
	int view, currentId;
	Date selectedDate;
	JComboBox monthCombo, yearCombo;
	JDateChooser chooser1, chooser2;
	LinerSpoilageDAO linerSpoilageDAO;

	public static void main(String[] args) throws SQLException {

		new LinerSpoilageView(1, -1);

	}

	public LinerSpoilageView(int idIn, int view) {
		
		setStyle();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		linerSpoilageDAO = (LinerSpoilageDAO) context.getBean("LinerSpoilageDAO");

		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());

		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Liner Spoilage");
		setSize(360, 700);
		setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month) - 1;
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);

		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028" };
		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		// JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

		initializeVariables();

		addActionListeners();

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		// buttonsPanel.add(previous);
		// buttonsPanel.add(next);
		// buttonsPanel.add(delete);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		JPanel optionPanel1 = new JPanel(new GridLayout(19, 2));
		// optionPanel1.setBackground(Color.GRAY);

		// ComboPanelMonthly
		JPanel comboPanel = new JPanel(new FlowLayout());
		comboPanel.add(monthCombo);
		comboPanel.add(yearCombo);
		comboPanel.add(go);

		// Adding
		if (view == -1) {

			find.setVisible(false);
			// previous.setVisible(false);
			next.setVisible(false);
			// delete.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);
			back.setVisible(false);
			summary.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(chooser1);

			optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
			optionPanel1.add(Liner11JTextField);

			optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
			optionPanel1.add(Liner12JTextField);

			optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
			optionPanel1.add(Liner13JTextField);

			optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
			optionPanel1.add(Liner14JTextField);

			optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
			optionPanel1.add(Liner21JTextField);

			optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
			optionPanel1.add(Liner22JTextField);

			optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
			optionPanel1.add(Liner23JTextField);

			optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
			optionPanel1.add(Liner24JTextField);

			optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
			optionPanel1.add(Liner31JTextField);

			optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
			optionPanel1.add(Liner32JTextField);

			optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
			optionPanel1.add(Liner33JTextField);

			optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
			optionPanel1.add(Liner34JTextField);

			optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
			optionPanel1.add(Liner41JTextField);

			optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
			optionPanel1.add(Liner42JTextField);

			optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
			optionPanel1.add(Liner43JTextField);

			optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
			optionPanel1.add(Liner44JTextField);

			optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
			optionPanel1.add(Liner45JTextField);

			optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
			optionPanel1.add(Liner46JTextField);

		} // Searching
		else if (view == -2) {

			// currentId =
			// linerSpoilageDAO.MaintenanceLinerProductionGetHighestID();
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);

			add.setVisible(false);
			back.setVisible(false);
			addNew.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(chooser1);

			optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
			optionPanel1.add(Liner11JTextField);

			optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
			optionPanel1.add(Liner12JTextField);

			optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
			optionPanel1.add(Liner13JTextField);

			optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
			optionPanel1.add(Liner14JTextField);

			optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
			optionPanel1.add(Liner21JTextField);

			optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
			optionPanel1.add(Liner22JTextField);

			optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
			optionPanel1.add(Liner23JTextField);

			optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
			optionPanel1.add(Liner24JTextField);

			optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
			optionPanel1.add(Liner31JTextField);

			optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
			optionPanel1.add(Liner32JTextField);

			optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
			optionPanel1.add(Liner33JTextField);

			optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
			optionPanel1.add(Liner34JTextField);

			optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
			optionPanel1.add(Liner41JTextField);

			optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
			optionPanel1.add(Liner42JTextField);

			optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
			optionPanel1.add(Liner43JTextField);

			optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
			optionPanel1.add(Liner44JTextField);

			optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
			optionPanel1.add(Liner45JTextField);

			optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
			optionPanel1.add(Liner46JTextField);

		} // Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(18, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(new JLabel("Liner 11", SwingConstants.CENTER));
			optionPanel1.add(Liner11MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 12", SwingConstants.CENTER));
			optionPanel1.add(Liner12MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 13", SwingConstants.CENTER));
			optionPanel1.add(Liner13MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 14", SwingConstants.CENTER));
			optionPanel1.add(Liner14MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 21", SwingConstants.CENTER));
			optionPanel1.add(Liner21MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 22", SwingConstants.CENTER));
			optionPanel1.add(Liner22MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 23", SwingConstants.CENTER));
			optionPanel1.add(Liner23MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 24", SwingConstants.CENTER));
			optionPanel1.add(Liner24MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 31", SwingConstants.CENTER));
			optionPanel1.add(Liner31MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 32", SwingConstants.CENTER));
			optionPanel1.add(Liner32MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 33", SwingConstants.CENTER));
			optionPanel1.add(Liner33MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 34", SwingConstants.CENTER));
			optionPanel1.add(Liner34MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 41", SwingConstants.CENTER));
			optionPanel1.add(Liner41MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 42", SwingConstants.CENTER));
			optionPanel1.add(Liner42MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 43", SwingConstants.CENTER));
			optionPanel1.add(Liner43MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 44", SwingConstants.CENTER));
			optionPanel1.add(Liner44MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 45", SwingConstants.CENTER));
			optionPanel1.add(Liner45MonthlyJTextField);

			optionPanel1.add(new JLabel("Liner 46", SwingConstants.CENTER));
			optionPanel1.add(Liner46MonthlyJTextField);

			monthly.setVisible(false);
			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			delete.setVisible(false);
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
		optionsPanel2.add(search);
		optionsPanel2.add(monthly);
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

		// SQLiteConnection.AnalyticsUpdate("LinerSpoilage");

	}

	private void addActionListeners() {
		go = new JButton("Go");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();

				System.out.print("Month : " + month);
				System.out.print("Year : " + year);

				Object[] total = linerSpoilageDAO.MaintenanceLinerSpoilageCalculateTotalsByMonth(month, year);
				System.out.println("Total0 " + total[0]);

				Liner11MonthlyJTextField.setText(String.valueOf(total[0]));
				Liner12MonthlyJTextField.setText(String.valueOf(total[1]));
				Liner13MonthlyJTextField.setText(String.valueOf(total[2]));
				Liner14MonthlyJTextField.setText(String.valueOf(total[3]));
				Liner21MonthlyJTextField.setText(String.valueOf(total[4]));
				Liner22MonthlyJTextField.setText(String.valueOf(total[5]));
				Liner23MonthlyJTextField.setText(String.valueOf(total[6]));
				Liner24MonthlyJTextField.setText(String.valueOf(total[7]));
				Liner31MonthlyJTextField.setText(String.valueOf(total[8]));
				Liner32MonthlyJTextField.setText(String.valueOf(total[9]));
				Liner33MonthlyJTextField.setText(String.valueOf(total[10]));
				Liner34MonthlyJTextField.setText(String.valueOf(total[11]));
				Liner41MonthlyJTextField.setText(String.valueOf(total[12]));
				Liner42MonthlyJTextField.setText(String.valueOf(total[13]));
				Liner43MonthlyJTextField.setText(String.valueOf(total[14]));
				Liner44MonthlyJTextField.setText(String.valueOf(total[15]));
				Liner45MonthlyJTextField.setText(String.valueOf(total[16]));
				Liner46MonthlyJTextField.setText(String.valueOf(total[17]));

			}
		});

		back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerSpoilageView(1, -1);
				dispose();

			}
		});

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				LinerSpoilageModel lm = new LinerSpoilageModel();

				lm.setDate(chooser1.getDate());

				lm.setLiner11(Integer.valueOf(Liner11JTextField.getText()));
				lm.setLiner12(Integer.valueOf(Liner12JTextField.getText()));
				lm.setLiner13(Integer.valueOf(Liner13JTextField.getText()));
				lm.setLiner14(Integer.valueOf(Liner14JTextField.getText()));

				lm.setLiner21(Integer.valueOf(Liner21JTextField.getText()));
				lm.setLiner22(Integer.valueOf(Liner22JTextField.getText()));
				lm.setLiner23(Integer.valueOf(Liner23JTextField.getText()));
				lm.setLiner24(Integer.valueOf(Liner24JTextField.getText()));

				lm.setLiner31(Integer.valueOf(Liner31JTextField.getText()));
				lm.setLiner32(Integer.valueOf(Liner32JTextField.getText()));
				lm.setLiner33(Integer.valueOf(Liner33JTextField.getText()));
				lm.setLiner34(Integer.valueOf(Liner34JTextField.getText()));

				lm.setLiner41(Integer.valueOf(Liner41JTextField.getText()));
				lm.setLiner42(Integer.valueOf(Liner42JTextField.getText()));
				lm.setLiner43(Integer.valueOf(Liner43JTextField.getText()));
				lm.setLiner44(Integer.valueOf(Liner44JTextField.getText()));
				lm.setLiner45(Integer.valueOf(Liner45JTextField.getText()));
				lm.setLiner46(Integer.valueOf(Liner46JTextField.getText()));

				linerSpoilageDAO.MaintenanceLinerSpoilageInsert(lm);

				// TODO Auto-generated method stub
				dispose();
				createSummaryScreen();

			}

		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerSpoilageView(1, -1);
				dispose();

			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerSpoilageView(1, -2);
				setLastEntry();
				dispose();

			}
		});

		monthly = new JButton("Monthly");
		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) chooser1.getDate();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				LinerSpoilageModel lm = new LinerSpoilageModel();

				lm.setDate(chooser1.getDate());

				lm.setID(currentId);

				lm.setLiner11(Integer.valueOf(Liner11JTextField.getText()));
				lm.setLiner12(Integer.valueOf(Liner12JTextField.getText()));
				lm.setLiner13(Integer.valueOf(Liner13JTextField.getText()));
				lm.setLiner14(Integer.valueOf(Liner14JTextField.getText()));

				lm.setLiner21(Integer.valueOf(Liner21JTextField.getText()));
				lm.setLiner22(Integer.valueOf(Liner22JTextField.getText()));
				lm.setLiner23(Integer.valueOf(Liner23JTextField.getText()));
				lm.setLiner24(Integer.valueOf(Liner24JTextField.getText()));

				lm.setLiner31(Integer.valueOf(Liner31JTextField.getText()));
				lm.setLiner32(Integer.valueOf(Liner32JTextField.getText()));
				lm.setLiner33(Integer.valueOf(Liner33JTextField.getText()));
				lm.setLiner34(Integer.valueOf(Liner34JTextField.getText()));

				lm.setLiner41(Integer.valueOf(Liner41JTextField.getText()));
				lm.setLiner42(Integer.valueOf(Liner42JTextField.getText()));
				lm.setLiner43(Integer.valueOf(Liner43JTextField.getText()));
				lm.setLiner44(Integer.valueOf(Liner44JTextField.getText()));
				lm.setLiner45(Integer.valueOf(Liner45JTextField.getText()));
				lm.setLiner46(Integer.valueOf(Liner46JTextField.getText()));

				linerSpoilageDAO.MaintenanceLinerSpoilageUpdate(lm);

				dispose();
				new LinerSpoilageView(1, -2);

				// TODO Auto-generated method stub
			}
		});

		summary = new JButton("Summary");
		find = new JButton("Find Record");

		next = new JButton("Next Record");

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);
	}

	protected void setLastEntry() {
		// TODO Auto-generated method stub

	}

	protected void createSummaryScreen() {
		// TODO Auto-generated method stub

	}

	private void initializeVariables() {
		Liner11JTextField = new JTextField();
		Liner11JTextField.setText("0.0");

		Liner12JTextField = new JTextField();
		Liner12JTextField.setText("0.0");

		Liner13JTextField = new JTextField();
		Liner13JTextField.setText("0.0");

		Liner14JTextField = new JTextField();
		Liner14JTextField.setText("0.0");

		Liner21JTextField = new JTextField();
		Liner21JTextField.setText("0.0");

		Liner22JTextField = new JTextField();
		Liner22JTextField.setText("0.0");

		Liner23JTextField = new JTextField();
		Liner23JTextField.setText("0.0");

		Liner24JTextField = new JTextField();
		Liner24JTextField.setText("0.0");

		Liner31JTextField = new JTextField();
		Liner31JTextField.setText("0.0");

		Liner32JTextField = new JTextField();
		Liner32JTextField.setText("0.0");

		Liner33JTextField = new JTextField();
		Liner33JTextField.setText("0.0");

		Liner34JTextField = new JTextField();
		Liner34JTextField.setText("0.0");

		Liner41JTextField = new JTextField();
		Liner41JTextField.setText("0.0");

		Liner42JTextField = new JTextField();
		Liner42JTextField.setText("0.0");

		Liner43JTextField = new JTextField();
		Liner43JTextField.setText("0.0");

		Liner44JTextField = new JTextField();
		Liner44JTextField.setText("0.0");

		Liner45JTextField = new JTextField();
		Liner45JTextField.setText("0.0");

		Liner46JTextField = new JTextField();
		Liner46JTextField.setText("0.0");

		Liner11MonthlyJTextField = new JTextField();

		Liner12MonthlyJTextField = new JTextField();
		Liner13MonthlyJTextField = new JTextField();
		Liner14MonthlyJTextField = new JTextField();
		Liner21MonthlyJTextField = new JTextField();
		Liner22MonthlyJTextField = new JTextField();
		Liner23MonthlyJTextField = new JTextField();
		Liner24MonthlyJTextField = new JTextField();
		Liner31MonthlyJTextField = new JTextField();
		Liner32MonthlyJTextField = new JTextField();
		Liner33MonthlyJTextField = new JTextField();
		Liner34MonthlyJTextField = new JTextField();
		Liner41MonthlyJTextField = new JTextField();
		Liner42MonthlyJTextField = new JTextField();
		Liner43MonthlyJTextField = new JTextField();
		Liner44MonthlyJTextField = new JTextField();
		Liner45MonthlyJTextField = new JTextField();
		Liner46MonthlyJTextField = new JTextField();
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
