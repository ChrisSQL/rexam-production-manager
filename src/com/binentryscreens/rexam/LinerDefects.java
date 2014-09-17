package com.binentryscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.text.PlainDocument;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;

public class LinerDefects {

	JButton add, find, next, previous, update, addNew, search, monthly, go, back, calculateTotalLined, calculateTotalDefects,
			calculateSpoiledPercentage;
	JLabel dateLabel, dateLabel2;

	JLabel m1LinerLabel, m2LinerLabel, m3LinerLabel, m4LinerLabel, m1LinerDefectsLabel, m2LinerDefectsLabel, m3LinerDefectsLabel,
			m4LinerDefectsLabel, totalLinedLabel, totalDefectsLabel, totalPercentageSpoiledLabel;
	static JTextField m1LinerTextField, m2LinerTextField, m3LinerTextField, m4LinerTextField, m1LinerDefectsTextField, m2LinerDefectsTextField,
			m3LinerDefectsTextField, m4LinerDefectsTextField, totalLinedTextField, totalDefectsTextField, totalPercentageSpoiledTextField;
	JLabel m1LinerMonthlyLabel, m2LinerMonthlyLabel, m3LinerMonthlyLabel, m4LinerMonthlyLabel, m1LinerDefectsMonthlyLabel,
			m2LinerDefectsMonthlyLabel, m3LinerDefectsMonthlyLabel, m4LinerDefectsMonthlyLabel, totalLinedMonthlyLabel, totalDefectsMonthlyLabel,
			spoilagePercentageMonthlyLabel;
	static JTextField m1LinerMonthlyTextfield, m2LinerMonthlyTextfield, m3LinerMonthlyTextfield, m4LinerMonthlyTextfield,
			m1LinerDefectsMonthlyTextfield, m2LinerDefectsMonthlyTextfield, m3LinerDefectsMonthlyTextfield, m4LinerDefectsMonthlyTextfield,
			totalLinedMonthlyTextfield, totalDefectsMonthlyTextfield, spoilagePercentageMonthlyTextfield;
	int view, currentId;
	Date selectedDate;
	JComboBox monthCombo, yearCombo;

	static UtilDateModel model;
	static JDatePanelImpl datePanel;
	static JDatePickerImpl datePicker;

	public static void main(String[] args) throws SQLException {

		new LinerDefects(1, -1);

	}

	public LinerDefects(int idIn, int view) throws SQLException {

		// Add a view to analytics.
		try {
			SQLiteConnection.incrementViewsAnalytics(0, 0, 0, 0, 0, 0, 0, 0, 1);
		}
		catch (SQLException e2) {
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
		}
		catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		JFrame frame15 = new JFrame();
		// frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame15.setTitle("Liners Defects");
		frame15.setSize(360, 600);
		frame15.setLocationRelativeTo(null);
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

		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November",
				"December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" };
		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		model = new UtilDateModel();
		model.setDate(yearInt, monthInt, dayInt);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		UtilDateModel model2 = new UtilDateModel();
		model.setDate(yearInt, monthInt, dayInt);
		model.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		// JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

		m1LinerTextField = new JTextField();
		m1LinerTextField.setText("0");
		PlainDocument doc1 = (PlainDocument) m1LinerTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());
		m1LinerTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m2LinerTextField = new JTextField();
		m2LinerTextField.setText("0");
		PlainDocument doc2 = (PlainDocument) m2LinerTextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());
		m2LinerTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m3LinerTextField = new JTextField();
		m3LinerTextField.setText("0");
		PlainDocument doc3 = (PlainDocument) m3LinerTextField.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());
		m3LinerTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m4LinerTextField = new JTextField();
		m4LinerTextField.setText("0");
		PlainDocument doc4 = (PlainDocument) m4LinerTextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());
		m4LinerTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m1LinerDefectsTextField = new JTextField();
		m1LinerDefectsTextField.setText("0");
		PlainDocument doc5 = (PlainDocument) m1LinerDefectsTextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());
		m1LinerDefectsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});
		m2LinerDefectsTextField = new JTextField();
		m2LinerDefectsTextField.setText("0");
		PlainDocument doc6 = (PlainDocument) m2LinerDefectsTextField.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());
		m2LinerDefectsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m3LinerDefectsTextField = new JTextField();
		m3LinerDefectsTextField.setText("0");
		PlainDocument doc7 = (PlainDocument) m3LinerDefectsTextField.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());
		m3LinerDefectsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m4LinerDefectsTextField = new JTextField();
		m4LinerDefectsTextField.setText("0");
		PlainDocument doc8 = (PlainDocument) m4LinerDefectsTextField.getDocument();
		doc8.setDocumentFilter(new MyIntFilter());
		m4LinerDefectsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		totalLinedTextField = new JTextField();
		totalLinedTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});
		totalDefectsTextField = new JTextField();
		totalDefectsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});
		totalPercentageSpoiledTextField = new JTextField();
		totalPercentageSpoiledTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotalLined();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotalLined();

			}
		});

		m1LinerMonthlyTextfield = new JTextField();
		m2LinerMonthlyTextfield = new JTextField();
		m3LinerMonthlyTextfield = new JTextField();
		m4LinerMonthlyTextfield = new JTextField();
		m1LinerDefectsMonthlyTextfield = new JTextField();
		m2LinerDefectsMonthlyTextfield = new JTextField();
		m3LinerDefectsMonthlyTextfield = new JTextField();
		m4LinerDefectsMonthlyTextfield = new JTextField();
		totalLinedMonthlyTextfield = new JTextField();
		totalDefectsMonthlyTextfield = new JTextField();
		spoilagePercentageMonthlyTextfield = new JTextField();

		m1LinerLabel = new JLabel("M1 Liner ", SwingConstants.CENTER);
		m2LinerLabel = new JLabel("M2 Liner ", SwingConstants.CENTER);
		m3LinerLabel = new JLabel("M3 Liner ", SwingConstants.CENTER);
		m4LinerLabel = new JLabel("M4 Liner ", SwingConstants.CENTER);
		m1LinerDefectsLabel = new JLabel("M1 Liner Defects ", SwingConstants.CENTER);
		m2LinerDefectsLabel = new JLabel("M2 Liner Defects ", SwingConstants.CENTER);
		m3LinerDefectsLabel = new JLabel("M3 Liner Defects ", SwingConstants.CENTER);
		m4LinerDefectsLabel = new JLabel("M4 Liner Defects ", SwingConstants.CENTER);
		totalLinedLabel = new JLabel("Total Lined ", SwingConstants.CENTER);
		totalDefectsLabel = new JLabel("Total Defects ", SwingConstants.CENTER);
		totalPercentageSpoiledLabel = new JLabel("Spoiled % ", SwingConstants.CENTER);

		m1LinerMonthlyLabel = new JLabel("M1 Liner Monthly", SwingConstants.CENTER);
		m2LinerMonthlyLabel = new JLabel("M2 Liner Monthly", SwingConstants.CENTER);
		m3LinerMonthlyLabel = new JLabel("M3 Liner Monthly", SwingConstants.CENTER);
		m4LinerMonthlyLabel = new JLabel("M4 Liner Monthly", SwingConstants.CENTER);
		m1LinerDefectsMonthlyLabel = new JLabel("M1 Liner Defects Monthly", SwingConstants.CENTER);
		m2LinerDefectsMonthlyLabel = new JLabel("M2 Liner Defects Monthly", SwingConstants.CENTER);
		m3LinerDefectsMonthlyLabel = new JLabel("M3 Liner Defects Monthly", SwingConstants.CENTER);
		m4LinerDefectsMonthlyLabel = new JLabel("M4 Liner Defects Monthly", SwingConstants.CENTER);
		totalLinedMonthlyLabel = new JLabel("Total Lined Monthly ", SwingConstants.CENTER);
		totalDefectsMonthlyLabel = new JLabel("Total Defects Monthly ", SwingConstants.CENTER);
		spoilagePercentageMonthlyLabel = new JLabel("Spoiled % Monthly ", SwingConstants.CENTER);

		go = new JButton("Go");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();

				try {
					Object[] total = SQLiteConnection.LinerDefectsCalculateTotalsByMonth(month, year);
					System.out.println("Total0 " + total[0]);

					m1LinerMonthlyTextfield.setText(String.valueOf(total[0]));
					m2LinerMonthlyTextfield.setText(String.valueOf(total[1]));
					m3LinerMonthlyTextfield.setText(String.valueOf(total[2]));
					m4LinerMonthlyTextfield.setText(String.valueOf(total[3]));
					totalLinedMonthlyTextfield.setText(String.valueOf(total[4]));
					totalDefectsMonthlyTextfield.setText(String.valueOf(total[10]));
					spoilagePercentageMonthlyTextfield.setText(String.valueOf(total[6]) + "%");

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new LinerDefects(1, -1);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotalLined();

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				// Get int value of a JTextfield

				int m1LinerInt = Integer.parseInt(m1LinerTextField.getText());
				int m2LinerInt = Integer.parseInt(m2LinerTextField.getText());
				int m3LinerInt = Integer.parseInt(m3LinerTextField.getText());
				int m4LinerInt = Integer.parseInt(m4LinerTextField.getText());

				System.out.println("m1LinerInt " + m1LinerInt);
				System.out.println("m2LinerInt " + m2LinerInt);
				System.out.println("m3LinerInt " + m3LinerInt);
				System.out.println("m4LinerInt " + m4LinerInt);

				Double LinerTotalSum = (m1LinerInt * 1.0 + m2LinerInt * 1.0 + m3LinerInt * 1.0 + m4LinerInt * 1.0);

				int M1DefectsInt = Integer.parseInt(m1LinerDefectsTextField.getText());
				int M2DefectsInt = Integer.parseInt(m2LinerDefectsTextField.getText());
				int M3DefectsInt = Integer.parseInt(m3LinerDefectsTextField.getText());
				int M4DefectsInt = Integer.parseInt(m4LinerDefectsTextField.getText());

				Double defectsSum = (M1DefectsInt * 1.0 + M2DefectsInt * 1.0 + M3DefectsInt * 1.0 + M4DefectsInt * 1.0);

				try {
					SQLiteConnection.LinerDefectsInsert(

					SQLiteConnection.LinerDefectsGetHighestID() + 1, date, m1LinerInt, M1DefectsInt, m2LinerInt, M2DefectsInt, m3LinerInt,
							M3DefectsInt, m4LinerInt, M4DefectsInt, LinerTotalSum, defectsSum, 0.0

					);

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub

				frame15.dispose();

			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new LinerDefects(1, -1);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					
					new LinerDefects(1, -2);
					setLastEntry();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		monthly = new JButton("Monthly");
		monthly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new LinerDefects(1, -3);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				int m1LinerInt = Integer.parseInt(m1LinerTextField.getText());
				int m2LinerInt = Integer.parseInt(m2LinerTextField.getText());
				int m3LinerInt = Integer.parseInt(m3LinerTextField.getText());
				int m4LinerInt = Integer.parseInt(m4LinerTextField.getText());

				System.out.println("m1LinerInt " + m1LinerInt);
				System.out.println("m2LinerInt " + m2LinerInt);
				System.out.println("m3LinerInt " + m3LinerInt);
				System.out.println("m4LinerInt " + m4LinerInt);

				Double LinerTotalSum = (m1LinerInt * 1.0 + m2LinerInt * 1.0 + m3LinerInt * 1.0 + m4LinerInt * 1.0);

				int M1DefectsInt = Integer.parseInt(m1LinerDefectsTextField.getText());
				int M2DefectsInt = Integer.parseInt(m2LinerDefectsTextField.getText());
				int M3DefectsInt = Integer.parseInt(m3LinerDefectsTextField.getText());
				int M4DefectsInt = Integer.parseInt(m4LinerDefectsTextField.getText());

				Double defectsSum = (M1DefectsInt * 1.0 + M2DefectsInt * 1.0 + M3DefectsInt * 1.0 + M4DefectsInt * 1.0);

				try {
					SQLiteConnection.LinerDefectsUpdate(

					m1LinerInt, M1DefectsInt, m2LinerInt, M2DefectsInt, m3LinerInt, M3DefectsInt, m4LinerInt, M4DefectsInt, LinerTotalSum,
							defectsSum, 0.0, currentId

					);

					frame15.dispose();
					new LinerDefects(1, -2);

				}
				catch (SQLException e1) {
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

					Object[] total = SQLiteConnection.LinerDefectsReturnEntryByDate(selectedDate);

					if (total[1] == null) {

						SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

					}

					else {

						currentId = (int) total[0];

						m1LinerTextField.setText(String.valueOf(total[2]));
						m1LinerDefectsTextField.setText(String.valueOf(total[3]));

						m2LinerTextField.setText(String.valueOf(total[4]));
						m2LinerDefectsTextField.setText(String.valueOf(total[5]));

						m3LinerTextField.setText(String.valueOf(total[6]));
						m3LinerDefectsTextField.setText(String.valueOf(total[7]));

						m4LinerTextField.setText(String.valueOf(total[8]));
						m4LinerDefectsTextField.setText(String.valueOf(total[9]));

						totalLinedTextField.setText(String.valueOf(total[10]));
						totalDefectsTextField.setText(String.valueOf(total[11]));
						totalPercentageSpoiledTextField.setText(String.valueOf(total[12]));

					}

					System.out.println("CurrentID " + currentId);

				}
				catch (Exception e1) {
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

					Object[] total = SQLiteConnection.LinerDefectsGetNextEntryById(currentId);

					if (total[0] == null) {

						SQLiteConnection.infoBox("No Next Result.", "");

					}
					else {

						String dateFormatted = (String) total[1];
						System.out.println("Date Formatted : " + dateFormatted);
						int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
						int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						currentId = currentId + 1;

						m1LinerTextField.setText(String.valueOf(total[2]));
						m1LinerDefectsTextField.setText(String.valueOf(total[3]));

						m2LinerTextField.setText(String.valueOf(total[4]));
						m2LinerDefectsTextField.setText(String.valueOf(total[5]));

						m3LinerTextField.setText(String.valueOf(total[6]));
						m3LinerDefectsTextField.setText(String.valueOf(total[7]));

						m4LinerTextField.setText(String.valueOf(total[8]));
						m4LinerDefectsTextField.setText(String.valueOf(total[9]));

						totalLinedTextField.setText(String.valueOf(total[10]));
						totalDefectsTextField.setText(String.valueOf(total[11]));
						totalPercentageSpoiledTextField.setText(String.valueOf(total[12]) + "%");

					}

					System.out.println("CurrentID " + currentId);

					// Fill Boxes with results

					// model.setDate(year2, month2, day2);
					model.setSelected(true);

				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// currentId = (int)array[0];
				// Set Date
				// Send in

			}
		});

		previous = new JButton("Previous Record");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				// Set ID
				try {

					Object[] total = SQLiteConnection.LinerDefectsGetPreviousEntryById(currentId);

					if (total[0] == null) {

						SQLiteConnection.infoBox("No Previous Result.", "");

					}
					else {

						String dateFormatted = (String) total[1];
						System.out.println("Date Formatted : " + dateFormatted);
						int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
						int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						currentId = currentId - 1;

						m1LinerTextField.setText(String.valueOf(total[2]));
						m1LinerDefectsTextField.setText(String.valueOf(total[3]));

						m2LinerTextField.setText(String.valueOf(total[4]));
						m2LinerDefectsTextField.setText(String.valueOf(total[5]));

						m3LinerTextField.setText(String.valueOf(total[6]));
						m3LinerDefectsTextField.setText(String.valueOf(total[7]));

						m4LinerTextField.setText(String.valueOf(total[8]));
						m4LinerDefectsTextField.setText(String.valueOf(total[9]));

						totalLinedTextField.setText(String.valueOf(total[10]));
						totalDefectsTextField.setText(String.valueOf(total[11]));
						totalPercentageSpoiledTextField.setText(String.valueOf(total[12]) + "%");
					}

					System.out.println(currentId);

					// Fill Boxes with results

				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

		calculateTotalLined = new JButton("Calculate Total Lined");
		calculateTotalLined.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
				int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
				int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
				int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
				int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

				int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
				int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
				int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
				int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
				int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

				totalLinedTextField.setText(String.valueOf(totalLined5));
				totalDefectsTextField.setText(String.valueOf(totalDefects5));

				System.out.println("TotalLined5 : " + totalLined5);
				System.out.println("total Defects5 : " + totalDefects5);

				Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
				totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

			}
		});

		calculateTotalDefects = new JButton("Calculate Total Defects");
		calculateTotalDefects.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
				int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
				int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
				int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
				int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

				int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
				int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
				int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
				int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
				int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

				totalLinedTextField.setText(String.valueOf(totalLined5));
				totalDefectsTextField.setText(String.valueOf(totalDefects5));

				System.out.println("TotalLined5 : " + totalLined5);
				System.out.println("total Defects5 : " + totalDefects5);

				Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
				totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

			}
		});

		calculateSpoiledPercentage = new JButton("Calculate Spoiled %");
		calculateSpoiledPercentage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
				int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
				int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
				int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
				int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

				int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
				int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
				int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
				int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
				int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

				totalLinedTextField.setText(String.valueOf(totalLined5));
				totalDefectsTextField.setText(String.valueOf(totalDefects5));

				System.out.println("TotalLined5 : " + totalLined5);
				System.out.println("total Defects5 : " + totalDefects5);

				Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
				totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

			}
		});

		// Buttons Top Panel

		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1

		JPanel optionPanel1 = new JPanel(new GridLayout(12, 2));
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
			addNew.setVisible(false);
			update.setVisible(false);
			back.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(datePicker);

			optionPanel1.add(m1LinerLabel);
			optionPanel1.add(m1LinerTextField);

			optionPanel1.add(m1LinerDefectsLabel);
			optionPanel1.add(m1LinerDefectsTextField);

			optionPanel1.add(m2LinerLabel);
			optionPanel1.add(m2LinerTextField);

			optionPanel1.add(m2LinerDefectsLabel);
			optionPanel1.add(m2LinerDefectsTextField);

			optionPanel1.add(m3LinerLabel);
			optionPanel1.add(m3LinerTextField);

			optionPanel1.add(m3LinerDefectsLabel);
			optionPanel1.add(m3LinerDefectsTextField);

			optionPanel1.add(m4LinerLabel);
			optionPanel1.add(m4LinerTextField);

			optionPanel1.add(m4LinerDefectsLabel);
			optionPanel1.add(m4LinerDefectsTextField);

			optionPanel1.add(calculateTotalLined);
			optionPanel1.add(totalLinedTextField);
			totalLinedTextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateTotalDefects);
			optionPanel1.add(totalDefectsTextField);
			totalDefectsTextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateSpoiledPercentage);
			optionPanel1.add(totalPercentageSpoiledTextField);
			totalPercentageSpoiledTextField.setBackground(Color.LIGHT_GRAY);

		}

		// Searching
		else if (view == -2) {

			currentId = SQLiteConnection.LinerDefectsGetHighestID();
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			model.setDate(yearInt, monthInt, dayInt);
			model.setSelected(true);
			add.setVisible(false);
			back.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(datePicker);

			optionPanel1.add(m1LinerLabel);
			optionPanel1.add(m1LinerTextField);

			optionPanel1.add(m1LinerDefectsLabel);
			optionPanel1.add(m1LinerDefectsTextField);

			optionPanel1.add(m2LinerLabel);
			optionPanel1.add(m2LinerTextField);

			optionPanel1.add(m2LinerDefectsLabel);
			optionPanel1.add(m2LinerDefectsTextField);

			optionPanel1.add(m3LinerLabel);
			optionPanel1.add(m3LinerTextField);

			optionPanel1.add(m3LinerDefectsLabel);
			optionPanel1.add(m3LinerDefectsTextField);

			optionPanel1.add(m4LinerLabel);
			optionPanel1.add(m4LinerTextField);

			optionPanel1.add(m4LinerDefectsLabel);
			optionPanel1.add(m4LinerDefectsTextField);

			optionPanel1.add(calculateTotalLined);
			optionPanel1.add(totalLinedTextField);
			totalLinedTextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateTotalDefects);
			optionPanel1.add(totalDefectsTextField);
			totalDefectsTextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateSpoiledPercentage);
			optionPanel1.add(totalPercentageSpoiledTextField);
			totalPercentageSpoiledTextField.setBackground(Color.LIGHT_GRAY);

		}
		// Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(11, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(m1LinerMonthlyLabel);
			optionPanel1.add(m1LinerMonthlyTextfield);

			optionPanel1.add(m2LinerMonthlyLabel);
			optionPanel1.add(m2LinerMonthlyTextfield);

			optionPanel1.add(m3LinerMonthlyLabel);
			optionPanel1.add(m3LinerMonthlyTextfield);

			optionPanel1.add(m4LinerMonthlyLabel);
			optionPanel1.add(m4LinerMonthlyTextfield);

			optionPanel1.add(totalLinedMonthlyLabel);
			optionPanel1.add(totalLinedMonthlyTextfield);
			totalLinedMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(totalDefectsMonthlyLabel);
			optionPanel1.add(totalDefectsMonthlyTextfield);
			totalDefectsMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(spoilagePercentageMonthlyLabel);
			optionPanel1.add(spoilagePercentageMonthlyTextfield);
			spoilagePercentageMonthlyTextfield.setBackground(Color.LIGHT_GRAY);

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

		// commentsTextArea = new JTextArea(7, 28);
		// commentsTextArea.setLineWrap(true);
		// commentsTextArea.setWrapStyleWord(true);
		//
		// commentsPanel.add(modTotal);
		// commentsPanel.add(commentsTextArea);
		//
		// // optionPanel1.add(productionLabel);
		// // optionPanel1.add(productionTextField);

		outerPanel.add(optionPanel1, BorderLayout.CENTER);

		// Options Panel 2

		JPanel optionsPanel2 = new JPanel(new FlowLayout());
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

		// JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
		// optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;

		outerPanel.repaint();
		frame15.add(outerPanel);

		frame15.setVisible(true);

	}

	private void setLastEntry() {

		try {

			int highestID = SQLiteConnection.LinerDefectsGetHighestID();
			System.out.println("Highest ID : " + highestID);
			Object[] result = new Object[16];
			result = SQLiteConnection.LinerDefectsReturnEntryByID(highestID);

			System.out.println("Date " + result[1]);

			// Date
			String dateFormatted = (String) result[1];
			System.out.println("Date Formatted : " + dateFormatted);
			int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
			int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
			int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

			model.setDate(year, month, day);
			model.setSelected(true);

			currentId = currentId + 1;

			m1LinerTextField.setText(String.valueOf(result[2]));
			m1LinerDefectsTextField.setText(String.valueOf(result[3]));

			m2LinerTextField.setText(String.valueOf(result[4]));
			m2LinerDefectsTextField.setText(String.valueOf(result[5]));

			m3LinerTextField.setText(String.valueOf(result[6]));
			m3LinerDefectsTextField.setText(String.valueOf(result[7]));

			m4LinerTextField.setText(String.valueOf(result[8]));
			m4LinerDefectsTextField.setText(String.valueOf(result[9]));

			totalLinedTextField.setText(String.valueOf(result[10]));
			totalDefectsTextField.setText(String.valueOf(result[11]));
			totalPercentageSpoiledTextField.setText(String.valueOf(result[12]) + "%");

			currentId = highestID;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void calculateTotalLined() {

		int totalLined1 = Integer.valueOf(m1LinerTextField.getText());
		int totalLined2 = Integer.valueOf(m2LinerTextField.getText());
		int totalLined3 = Integer.valueOf(m3LinerTextField.getText());
		int totalLined4 = Integer.valueOf(m4LinerTextField.getText());
		int totalLined5 = (totalLined1 + totalLined2 + totalLined3 + totalLined4);

		int totalDefects1 = Integer.valueOf(m1LinerDefectsTextField.getText());
		int totalDefects2 = Integer.valueOf(m2LinerDefectsTextField.getText());
		int totalDefects3 = Integer.valueOf(m3LinerDefectsTextField.getText());
		int totalDefects4 = Integer.valueOf(m4LinerDefectsTextField.getText());
		int totalDefects5 = (totalDefects1 + totalDefects2 + totalDefects3 + totalDefects4);

		totalLinedTextField.setText(String.valueOf(totalLined5));
		totalDefectsTextField.setText(String.valueOf(totalDefects5));

		System.out.println("TotalLined5 : " + totalLined5);
		System.out.println("total Defects5 : " + totalDefects5);

		Double totalDefectsPercentage = (totalDefects5 * 1.0 / totalLined5) * 100;
		totalPercentageSpoiledTextField.setText(String.valueOf(totalDefectsPercentage));

	}

}
