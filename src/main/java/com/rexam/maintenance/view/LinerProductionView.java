package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.dao.LinerProductionDAO;
import com.rexam.maintenance.model.LinerProductionModel;
import com.toedter.calendar.JDateChooser;

public class LinerProductionView extends JFrame {

	JDateChooser chooser1, chooser2;
	JComboBox monthCombo, yearCombo;
	JTextField Liner11JTextField, Liner12JTextField, Liner13JTextField, Liner14JTextField, Liner21JTextField,
			Liner22JTextField, Liner23JTextField, Liner24JTextField, Liner31JTextField, Liner32JTextField,
			Liner33JTextField, Liner34JTextField, Liner41JTextField, Liner42JTextField, Liner43JTextField,
			Liner44JTextField, Liner45JTextField, Liner46JTextField, Liner11MonthlyJTextField, Liner12MonthlyJTextField,
			Liner13MonthlyJTextField, Liner14MonthlyJTextField, Liner21MonthlyJTextField, Liner22MonthlyJTextField,
			Liner23MonthlyJTextField, Liner24MonthlyJTextField, Liner31MonthlyJTextField, Liner32MonthlyJTextField,
			Liner33MonthlyJTextField, Liner34MonthlyJTextField, Liner41MonthlyJTextField, Liner42MonthlyJTextField,
			Liner43MonthlyJTextField, Liner44MonthlyJTextField, Liner45MonthlyJTextField, Liner46MonthlyJTextField;
	JButton add, find, next, delete, previous, update, addNew, summary, refresh, search, monthly, go, back,
			calculateTotal1, calculateTotal2, calculateTotal3;
	JLabel dateLabel, dateLabel2;
	int currentId;
	LinerProductionModel lp;
	JFrame frameSummary;
	
	private LinerProductionDAO linerProductionDAO;

	public static void main(String[] args) throws SQLException {

		 new LinerProductionView(1, -1);

	}

	public LinerProductionView(int idIn, int view) {
		
		setStyle();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		linerProductionDAO = (LinerProductionDAO) context.getBean("LinerProductionDAO");

	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Liner Production");
		setSize(360, 700);
		setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		initializeVariables();
		addActionListeners();

		

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);
		buttonsPanel.add(delete);

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
			previous.setVisible(false);
			next.setVisible(false);
			delete.setVisible(false);
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

			currentId = linerProductionDAO.linerProductionGetHighestID();
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

	//	SQLiteConnection.AnalyticsUpdate("LinerProduction");

	}

	private void initializeVariables() {
		
		
		
		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028" };

		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);
		
		lp = new LinerProductionModel();

		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());

		Liner11JTextField = new JTextField();
		Liner11JTextField.setText("0");
		PlainDocument doc01 = (PlainDocument) Liner11JTextField.getDocument();
		doc01.setDocumentFilter(new MyIntFilter());

		Liner12JTextField = new JTextField();
		Liner12JTextField.setText("0");
		PlainDocument doc1 = (PlainDocument) Liner12JTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		Liner13JTextField = new JTextField();
		Liner13JTextField.setText("0");
		PlainDocument doc2 = (PlainDocument) Liner13JTextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		Liner14JTextField = new JTextField();
		Liner14JTextField.setText("0");
		PlainDocument doc3 = (PlainDocument) Liner14JTextField.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());

		Liner21JTextField = new JTextField();
		Liner21JTextField.setText("0");
		PlainDocument doc4 = (PlainDocument) Liner21JTextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		Liner22JTextField = new JTextField();
		Liner22JTextField.setText("0");
		PlainDocument doc5 = (PlainDocument) Liner22JTextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		Liner23JTextField = new JTextField();
		Liner23JTextField.setText("0");
		PlainDocument doc5A = (PlainDocument) Liner23JTextField.getDocument();
		doc5A.setDocumentFilter(new MyIntFilter());

		Liner24JTextField = new JTextField();
		Liner24JTextField.setText("0");
		PlainDocument doc5B = (PlainDocument) Liner24JTextField.getDocument();
		doc5B.setDocumentFilter(new MyIntFilter());

		Liner31JTextField = new JTextField();
		Liner31JTextField.setText("0");
		PlainDocument doc5C = (PlainDocument) Liner31JTextField.getDocument();
		doc5C.setDocumentFilter(new MyIntFilter());

		Liner32JTextField = new JTextField();
		Liner32JTextField.setText("0");
		PlainDocument doc5D = (PlainDocument) Liner32JTextField.getDocument();
		doc5D.setDocumentFilter(new MyIntFilter());

		Liner33JTextField = new JTextField();
		Liner33JTextField.setText("0");
		PlainDocument doc5E = (PlainDocument) Liner33JTextField.getDocument();
		doc5E.setDocumentFilter(new MyIntFilter());

		Liner34JTextField = new JTextField();
		Liner34JTextField.setText("0");
		PlainDocument doc5F = (PlainDocument) Liner34JTextField.getDocument();
		doc5F.setDocumentFilter(new MyIntFilter());

		Liner41JTextField = new JTextField();
		Liner41JTextField.setText("0");
		PlainDocument doc5G = (PlainDocument) Liner41JTextField.getDocument();
		doc5G.setDocumentFilter(new MyIntFilter());

		Liner42JTextField = new JTextField();
		Liner42JTextField.setText("0");
		PlainDocument doc5H = (PlainDocument) Liner42JTextField.getDocument();
		doc5H.setDocumentFilter(new MyIntFilter());

		Liner43JTextField = new JTextField();
		Liner43JTextField.setText("0");
		PlainDocument doc5I = (PlainDocument) Liner43JTextField.getDocument();
		doc5I.setDocumentFilter(new MyIntFilter());

		Liner44JTextField = new JTextField();
		Liner44JTextField.setText("0");
		PlainDocument doc5J = (PlainDocument) Liner44JTextField.getDocument();
		doc5J.setDocumentFilter(new MyIntFilter());

		Liner45JTextField = new JTextField();
		Liner45JTextField.setText("0");
		PlainDocument doc5K = (PlainDocument) Liner45JTextField.getDocument();
		doc5K.setDocumentFilter(new MyIntFilter());

		Liner46JTextField = new JTextField();
		Liner46JTextField.setText("0");
		PlainDocument doc5L = (PlainDocument) Liner46JTextField.getDocument();
		doc5L.setDocumentFilter(new MyIntFilter());

		Liner11MonthlyJTextField = new JTextField();
		PlainDocument doc02 = (PlainDocument) Liner11MonthlyJTextField.getDocument();
		doc02.setDocumentFilter(new MyIntFilter());
		Liner12MonthlyJTextField = new JTextField();
		PlainDocument doc13 = (PlainDocument) Liner12MonthlyJTextField.getDocument();
		doc13.setDocumentFilter(new MyIntFilter());
		Liner13MonthlyJTextField = new JTextField();
		PlainDocument doc14 = (PlainDocument) Liner13MonthlyJTextField.getDocument();
		doc14.setDocumentFilter(new MyIntFilter());
		Liner14MonthlyJTextField = new JTextField();
		PlainDocument doc15 = (PlainDocument) Liner14MonthlyJTextField.getDocument();
		doc15.setDocumentFilter(new MyIntFilter());
		Liner21MonthlyJTextField = new JTextField();
		PlainDocument doc16 = (PlainDocument) Liner21MonthlyJTextField.getDocument();
		doc16.setDocumentFilter(new MyIntFilter());

		Liner22MonthlyJTextField = new JTextField();
		PlainDocument doc17A = (PlainDocument) Liner22MonthlyJTextField.getDocument();
		doc17A.setDocumentFilter(new MyIntFilter());
		Liner23MonthlyJTextField = new JTextField();
		PlainDocument doc17B = (PlainDocument) Liner23MonthlyJTextField.getDocument();
		doc17B.setDocumentFilter(new MyIntFilter());
		Liner24MonthlyJTextField = new JTextField();
		PlainDocument doc17C = (PlainDocument) Liner24MonthlyJTextField.getDocument();
		doc17C.setDocumentFilter(new MyIntFilter());

		Liner31MonthlyJTextField = new JTextField();
		PlainDocument doc17D = (PlainDocument) Liner31MonthlyJTextField.getDocument();
		doc17D.setDocumentFilter(new MyIntFilter());
		Liner32MonthlyJTextField = new JTextField();
		PlainDocument doc17E = (PlainDocument) Liner32MonthlyJTextField.getDocument();
		doc17E.setDocumentFilter(new MyIntFilter());
		Liner33MonthlyJTextField = new JTextField();
		PlainDocument doc17F = (PlainDocument) Liner33MonthlyJTextField.getDocument();
		doc17F.setDocumentFilter(new MyIntFilter());
		Liner34MonthlyJTextField = new JTextField();
		PlainDocument doc17G = (PlainDocument) Liner34MonthlyJTextField.getDocument();
		doc17G.setDocumentFilter(new MyIntFilter());

		Liner41MonthlyJTextField = new JTextField();
		PlainDocument doc17H = (PlainDocument) Liner41MonthlyJTextField.getDocument();
		doc17H.setDocumentFilter(new MyIntFilter());
		Liner42MonthlyJTextField = new JTextField();
		PlainDocument doc17I = (PlainDocument) Liner42MonthlyJTextField.getDocument();
		doc17I.setDocumentFilter(new MyIntFilter());
		Liner43MonthlyJTextField = new JTextField();
		PlainDocument doc17J = (PlainDocument) Liner43MonthlyJTextField.getDocument();
		doc17J.setDocumentFilter(new MyIntFilter());
		Liner44MonthlyJTextField = new JTextField();
		PlainDocument doc17K = (PlainDocument) Liner44MonthlyJTextField.getDocument();
		doc17K.setDocumentFilter(new MyIntFilter());
		Liner45MonthlyJTextField = new JTextField();
		PlainDocument doc17L = (PlainDocument) Liner45MonthlyJTextField.getDocument();
		doc17J.setDocumentFilter(new MyIntFilter());
		Liner46MonthlyJTextField = new JTextField();
		PlainDocument doc17M = (PlainDocument) Liner46MonthlyJTextField.getDocument();
		doc17K.setDocumentFilter(new MyIntFilter());
		
		go = new JButton("Go");
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
		delete = new JButton("Delete");
		
		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);
		
		

	
	}

	private void addActionListeners() {
		
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();

				System.out.print("Month : " + month);
				System.out.print("Year : " + year);

				int[] total = linerProductionDAO.linerProductionCalculateTotalsByMonth(month, year);
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
	
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				new LinerProductionView(1, -1);

			}
		});
	
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerProductionView(1, -1);
				dispose();

			}
		});
		
		monthly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				new LinerProductionView(1, -3);

			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				lp.setLiner11(Integer.valueOf(Liner11JTextField.getText()));
				lp.setLiner12(Integer.valueOf(Liner12JTextField.getText()));
				lp.setLiner13(Integer.valueOf(Liner13JTextField.getText()));
				lp.setLiner14(Integer.valueOf(Liner14JTextField.getText()));
				
				lp.setLiner21(Integer.valueOf(Liner21JTextField.getText()));
				lp.setLiner22(Integer.valueOf(Liner22JTextField.getText()));
				lp.setLiner23(Integer.valueOf(Liner23JTextField.getText()));
				lp.setLiner24(Integer.valueOf(Liner24JTextField.getText()));
				
				lp.setLiner31(Integer.valueOf(Liner31JTextField.getText()));
				lp.setLiner32(Integer.valueOf(Liner32JTextField.getText()));
				lp.setLiner33(Integer.valueOf(Liner33JTextField.getText()));
				lp.setLiner34(Integer.valueOf(Liner34JTextField.getText()));
				
				lp.setLiner41(Integer.valueOf(Liner41JTextField.getText()));
				lp.setLiner42(Integer.valueOf(Liner42JTextField.getText()));
				lp.setLiner43(Integer.valueOf(Liner43JTextField.getText()));
				lp.setLiner44(Integer.valueOf(Liner44JTextField.getText()));
				lp.setLiner45(Integer.valueOf(Liner45JTextField.getText()));
				lp.setLiner46(Integer.valueOf(Liner46JTextField.getText()));
				
				lp.setDate(chooser1.getDate());
				

				if (linerProductionDAO.linerProductionEntryExists(lp) == false) {

					linerProductionDAO.linerProductionInsert(lp);

				} else {

					linerProductionDAO.linerProductionUpdateByDate(lp);

					dispose();

				}

				// TODO Auto-generated method stub
				dispose();

			}
		});
		
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {


				linerProductionDAO.linerProductionUpdate(lp);

				dispose();
				new LinerProductionView(1, -2);

				// TODO Auto-generated method stub
			}
		});


		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				try {
					
					createSummaryScreen();
					
				} catch (SQLException ex) {
					Logger.getLogger(LinerProductionView.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(LinerProductionView.class.getName()).log(Level.SEVERE, null, ex);
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
                new LinerProductionView(1, -1);

            }
        });
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();

            }
        });

        JButton print = new JButton("Print Report");
        JButton ExportToExcel = new JButton("Export To Excel");

        ExportToExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

         //       LinerProduction.exportToExcel();

            }
        });

        JButton importFromExcel = new JButton("Import From Viscan");
        importFromExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                frameSummary.dispose();

            }
        });

        // Outer Frame
        frameSummary = new JFrame("Liner Production Report");
        frameSummary.setSize(1300, 700);
        frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameSummary.setLocationRelativeTo(null);

        // JPanel
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel optionsPanel2 = new JPanel(new FlowLayout());

        optionsPanel2.add(addNew);
        optionsPanel2.add(summary);
        optionsPanel2.add(refresh);
        //   optionsPanel2.add(print);
        optionsPanel2.add(ExportToExcel);
        optionsPanel2.add(importFromExcel);

        JPanel summaryPanel = linerProductionDAO.linerProductionSummaryTable(1);
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
