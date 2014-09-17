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

public class EndCounts {

	JButton add, find, next, previous, update, addNew, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
	JLabel dateLabel, dateLabel2;

	JLabel W11Label, W12Label, W21Label, W22Label, W31Label, W32Label, W33Label, total1Label, W41Label, W42Label, W43Label, W44Label, total2Label,
			total3Label;
	JLabel W11MonthlyLabel, W12MonthlyLabel, W21MonthlyLabel, W22MonthlyLabel, W31MonthlyLabel, W32MonthlyLabel, W33MonthlyLabel, total1MonthlyLabel,
			W41MonthlyLabel, W42MonthlyLabel, W43MonthlyLabel, W44MonthlyLabel, total2MonthlyLabel, total3MonthlyLabel;

	static JTextField W11TextField, W12TextField, W21TextField, W22TextField, W31TextField, W32TextField, W33TextField, total1TextField,
			W41TextField, W42TextField, W43TextField, W44TextField, total2TextField, total3TextField;

	static JTextField W11MonthlyTextField, W12MonthlyTextField, W21MonthlyTextField, W22MonthlyTextField, W31MonthlyTextField, W32MonthlyTextField,
			W33MonthlyTextField, total1MonthlyTextfield, W41MonthlyTextField, W42MonthlyTextField, W43MonthlyTextField, W44MonthlyTextField,
			total2MonthlyTextField, total3MonthlyTextField;
	int view, currentId;
	Date selectedDate;
	JComboBox monthCombo, yearCombo;

	static UtilDateModel model;
	static JDatePanelImpl datePanel;
	static JDatePickerImpl datePicker;

	public static void main(String[] args) throws SQLException {

		new EndCounts(1, -1);

	}

	public EndCounts(int idIn, int view) throws SQLException {

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

		frame15.setTitle("End Counts");
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

		W11TextField = new JTextField();
		W11TextField.setText("0");
		PlainDocument doc1 = (PlainDocument) W11TextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());
		W11TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W12TextField = new JTextField();
		W12TextField.setText("0");
		PlainDocument doc2 = (PlainDocument) W12TextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());
		W12TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W21TextField = new JTextField();
		W21TextField.setText("0");
		PlainDocument doc3 = (PlainDocument) W21TextField.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());
		W21TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W22TextField = new JTextField();
		W22TextField.setText("0");
		PlainDocument doc4 = (PlainDocument) W22TextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());
		W22TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W31TextField = new JTextField();
		W31TextField.setText("0");
		PlainDocument doc5 = (PlainDocument) W31TextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());
		W31TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W32TextField = new JTextField();
		W32TextField.setText("0");
		PlainDocument doc6 = (PlainDocument) W32TextField.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());
		W32TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W33TextField = new JTextField();
		W33TextField.setText("0");
		PlainDocument doc7 = (PlainDocument) W33TextField.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());
		W33TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		total1TextField = new JTextField();
		total1TextField.setEditable(false);
		total1TextField.setText("0");
		PlainDocument doc8 = (PlainDocument) total1TextField.getDocument();
		doc8.setDocumentFilter(new MyIntFilter());
		W41TextField = new JTextField();
		W41TextField.setText("0");
		PlainDocument doc9 = (PlainDocument) W41TextField.getDocument();
		doc9.setDocumentFilter(new MyIntFilter());
		W41TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W42TextField = new JTextField();
		W42TextField.setText("0");
		PlainDocument doc10 = (PlainDocument) W42TextField.getDocument();
		doc10.setDocumentFilter(new MyIntFilter());
		W42TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W43TextField = new JTextField();
		W43TextField.setText("0");
		PlainDocument doc11 = (PlainDocument) W43TextField.getDocument();
		doc11.setDocumentFilter(new MyIntFilter());
		W43TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		W44TextField = new JTextField();
		W44TextField.setText("0");
		PlainDocument doc12 = (PlainDocument) W44TextField.getDocument();
		doc12.setDocumentFilter(new MyIntFilter());
		W44TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		total2TextField = new JTextField();
		total2TextField.setEditable(false);
		total2TextField.setText("0");
		total2TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});

		total3TextField = new JTextField();
		total3TextField.setEditable(false);
		total3TextField.setText("0");
		total3TextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});

		W11Label = new JLabel("W11 ", SwingConstants.CENTER);
		W12Label = new JLabel("W12 ", SwingConstants.CENTER);
		W21Label = new JLabel("W21 ", SwingConstants.CENTER);
		W22Label = new JLabel("W22 ", SwingConstants.CENTER);
		W31Label = new JLabel("W31 ", SwingConstants.CENTER);
		W32Label = new JLabel("W32 ", SwingConstants.CENTER);
		W33Label = new JLabel("W33 ", SwingConstants.CENTER);
		total1Label = new JLabel("Total 1 ", SwingConstants.CENTER);
		W41Label = new JLabel("W41 ", SwingConstants.CENTER);
		W42Label = new JLabel("W42 ", SwingConstants.CENTER);
		W43Label = new JLabel("W43 ", SwingConstants.CENTER);
		W44Label = new JLabel("W44 ", SwingConstants.CENTER);
		total2Label = new JLabel("Total 2 ", SwingConstants.CENTER);
		total3Label = new JLabel("Total 3 ", SwingConstants.CENTER);

		W11MonthlyLabel = new JLabel("W11 ", SwingConstants.CENTER);
		W12MonthlyLabel = new JLabel("W12 ", SwingConstants.CENTER);
		W21MonthlyLabel = new JLabel("W21 ", SwingConstants.CENTER);
		W22MonthlyLabel = new JLabel("W22 ", SwingConstants.CENTER);
		W31MonthlyLabel = new JLabel("W31 ", SwingConstants.CENTER);
		W32MonthlyLabel = new JLabel("W32 ", SwingConstants.CENTER);
		W33MonthlyLabel = new JLabel("W33 ", SwingConstants.CENTER);
		total1MonthlyLabel = new JLabel("Total 1 ", SwingConstants.CENTER);
		W41MonthlyLabel = new JLabel("W41 ", SwingConstants.CENTER);
		W42MonthlyLabel = new JLabel("W42 ", SwingConstants.CENTER);
		W43MonthlyLabel = new JLabel("W43 ", SwingConstants.CENTER);
		W44MonthlyLabel = new JLabel("W44 ", SwingConstants.CENTER);
		total2MonthlyLabel = new JLabel("Total 2 ", SwingConstants.CENTER);
		total3MonthlyLabel = new JLabel("Total 3 ", SwingConstants.CENTER);

		W11MonthlyTextField = new JTextField();
		PlainDocument doc13 = (PlainDocument) W11MonthlyTextField.getDocument();
		doc13.setDocumentFilter(new MyIntFilter());
		W12MonthlyTextField = new JTextField();
		PlainDocument doc14 = (PlainDocument) W12MonthlyTextField.getDocument();
		doc14.setDocumentFilter(new MyIntFilter());
		W21MonthlyTextField = new JTextField();
		PlainDocument doc15 = (PlainDocument) W21MonthlyTextField.getDocument();
		doc15.setDocumentFilter(new MyIntFilter());
		W22MonthlyTextField = new JTextField();
		PlainDocument doc16 = (PlainDocument) W22MonthlyTextField.getDocument();
		doc16.setDocumentFilter(new MyIntFilter());
		W31MonthlyTextField = new JTextField();
		PlainDocument doc17 = (PlainDocument) W31MonthlyTextField.getDocument();
		doc17.setDocumentFilter(new MyIntFilter());
		W32MonthlyTextField = new JTextField();
		PlainDocument doc18 = (PlainDocument) W32MonthlyTextField.getDocument();
		doc18.setDocumentFilter(new MyIntFilter());
		W33MonthlyTextField = new JTextField();
		PlainDocument doc19 = (PlainDocument) W33MonthlyTextField.getDocument();
		doc19.setDocumentFilter(new MyIntFilter());
		total1MonthlyTextfield = new JTextField();
		total1MonthlyTextfield.setEditable(false);
		W41MonthlyTextField = new JTextField();
		PlainDocument doc20 = (PlainDocument) W41MonthlyTextField.getDocument();
		doc20.setDocumentFilter(new MyIntFilter());
		W42MonthlyTextField = new JTextField();
		PlainDocument doc21 = (PlainDocument) W42MonthlyTextField.getDocument();
		doc21.setDocumentFilter(new MyIntFilter());
		W43MonthlyTextField = new JTextField();
		PlainDocument doc22 = (PlainDocument) W43MonthlyTextField.getDocument();
		doc22.setDocumentFilter(new MyIntFilter());
		W44MonthlyTextField = new JTextField();
		PlainDocument doc23 = (PlainDocument) W44MonthlyTextField.getDocument();
		doc23.setDocumentFilter(new MyIntFilter());
		total2MonthlyTextField = new JTextField();
		total2MonthlyTextField.setEditable(false);
		total3MonthlyTextField = new JTextField();
		total3MonthlyTextField.setEditable(false);

		go = new JButton("Go");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();

				try {
					Object[] total = SQLiteConnection.EndCountsCalculateTotalsByMonth(month, year);
					System.out.println("Total0 " + total[0]);

					W11MonthlyTextField.setText(String.valueOf(total[0]));
					W12MonthlyTextField.setText(String.valueOf(total[1]));
					W21MonthlyTextField.setText(String.valueOf(total[2]));
					W22MonthlyTextField.setText(String.valueOf(total[3]));
					W31MonthlyTextField.setText(String.valueOf(total[4]));
					W32MonthlyTextField.setText(String.valueOf(total[5]));
					W33MonthlyTextField.setText(String.valueOf(total[6]));
					total1MonthlyTextfield.setText(String.valueOf(total[7]));
					W41MonthlyTextField.setText(String.valueOf(total[8]));
					W42MonthlyTextField.setText(String.valueOf(total[9]));
					W43MonthlyTextField.setText(String.valueOf(total[10]));
					W44MonthlyTextField.setText(String.valueOf(total[11]));
					total2MonthlyTextField.setText(String.valueOf(total[12]));
					total3MonthlyTextField.setText(String.valueOf(total[13]));

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
					new EndCounts(1, -1);
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

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				// Get int value of a JTextfield

				calculateTotals();

				try {
					SQLiteConnection.EndCountsInsert(

					SQLiteConnection.EndCountsGetHighestID() + 1, date, Integer.valueOf(W11TextField.getText()),
							Integer.valueOf(W12TextField.getText()), Integer.valueOf(W21TextField.getText()),
							Integer.valueOf(W22TextField.getText()), Integer.valueOf(W31TextField.getText()),
							Integer.valueOf(W32TextField.getText()), Integer.valueOf(W33TextField.getText()),
							Integer.valueOf(total1TextField.getText()), Integer.valueOf(W41TextField.getText()),
							Integer.valueOf(W42TextField.getText()), Integer.valueOf(W43TextField.getText()),
							Integer.valueOf(W44TextField.getText()), Integer.valueOf(total2TextField.getText()),
							Integer.valueOf(total3TextField.getText())

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
					new EndCounts(1, -1);
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
					new EndCounts(1, -2);
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
					new EndCounts(1, -3);
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

				try {
					SQLiteConnection.EndCountsUpdate(

					date, Integer.valueOf(W11TextField.getText()), Integer.valueOf(W12TextField.getText()), Integer.valueOf(W21TextField.getText()),
							Integer.valueOf(W22TextField.getText()), Integer.valueOf(W31TextField.getText()),
							Integer.valueOf(W32TextField.getText()), Integer.valueOf(W33TextField.getText()),
							Integer.valueOf(total1TextField.getText()), Integer.valueOf(W41TextField.getText()),
							Integer.valueOf(W42TextField.getText()), Integer.valueOf(W43TextField.getText()),
							Integer.valueOf(W44TextField.getText()), Integer.valueOf(total2TextField.getText()),
							Integer.valueOf(total3TextField.getText()), currentId

					);

					frame15.dispose();
					new EndCounts(1, -2);

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

					Object[] total = SQLiteConnection.EndCountsReturnEntryByDate(selectedDate);

					if (total[1] == null) {

						SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

					}

					else {

						currentId = (int) total[0];

						W11TextField.setText(String.valueOf(total[2]));
						W12TextField.setText(String.valueOf(total[3]));
						W21TextField.setText(String.valueOf(total[4]));
						W22TextField.setText(String.valueOf(total[5]));
						W31TextField.setText(String.valueOf(total[6]));
						W32TextField.setText(String.valueOf(total[7]));
						W33TextField.setText(String.valueOf(total[8]));
						total1TextField.setText(String.valueOf(total[9]));
						W41TextField.setText(String.valueOf(total[10]));
						W42TextField.setText(String.valueOf(total[11]));
						W43TextField.setText(String.valueOf(total[12]));
						W44TextField.setText(String.valueOf(total[13]));
						total2TextField.setText(String.valueOf(total[14]));
						total3TextField.setText(String.valueOf(total[15]));

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

					Object[] total = SQLiteConnection.EndCountsGetNextEntryById(currentId);

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

						W11TextField.setText(String.valueOf(total[2]));
						W12TextField.setText(String.valueOf(total[3]));
						W21TextField.setText(String.valueOf(total[4]));
						W22TextField.setText(String.valueOf(total[5]));
						W31TextField.setText(String.valueOf(total[6]));
						W32TextField.setText(String.valueOf(total[7]));
						W33TextField.setText(String.valueOf(total[8]));
						total1TextField.setText(String.valueOf(total[9]));
						W41TextField.setText(String.valueOf(total[10]));
						W42TextField.setText(String.valueOf(total[11]));
						W43TextField.setText(String.valueOf(total[12]));
						W44TextField.setText(String.valueOf(total[13]));
						total2TextField.setText(String.valueOf(total[14]));
						total3TextField.setText(String.valueOf(total[15]));

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

					Object[] total = SQLiteConnection.EndCountsGetPreviousEntryById(currentId);

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

						W11TextField.setText(String.valueOf(total[2]));
						W12TextField.setText(String.valueOf(total[3]));
						W21TextField.setText(String.valueOf(total[4]));
						W22TextField.setText(String.valueOf(total[5]));
						W31TextField.setText(String.valueOf(total[6]));
						W32TextField.setText(String.valueOf(total[7]));
						W33TextField.setText(String.valueOf(total[8]));
						total1TextField.setText(String.valueOf(total[9]));
						W41TextField.setText(String.valueOf(total[10]));
						W42TextField.setText(String.valueOf(total[11]));
						W43TextField.setText(String.valueOf(total[12]));
						W44TextField.setText(String.valueOf(total[13]));
						total2TextField.setText(String.valueOf(total[14]));
						total3TextField.setText(String.valueOf(total[15]));

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

		calculateTotal1 = new JButton("Calculate Total 1");
		calculateTotal1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int W11Int = Integer.valueOf(W11TextField.getText());
				int W12Int = Integer.valueOf(W12TextField.getText());
				int W21Int = Integer.valueOf(W21TextField.getText());
				int W22Int = Integer.valueOf(W22TextField.getText());
				int W31Int = Integer.valueOf(W31TextField.getText());
				int W32Int = Integer.valueOf(W32TextField.getText());
				int W33Int = Integer.valueOf(W33TextField.getText());
				int WTotals1 = (W11Int + W12Int + W21Int + W22Int + W31Int + W32Int + W33Int);

				int W41Int = Integer.valueOf(W41TextField.getText());
				int W42Int = Integer.valueOf(W42TextField.getText());
				int W43Int = Integer.valueOf(W43TextField.getText());
				int W44Int = Integer.valueOf(W44TextField.getText());
				int WTotals2 = (W41Int + W42Int + W43Int + W44Int);
				int WTotals3 = WTotals1 + WTotals2;

				total1TextField.setText(String.valueOf(WTotals1));
				total2TextField.setText(String.valueOf(WTotals2));
				total3TextField.setText(String.valueOf(WTotals3));

			}
		});

		calculateTotal2 = new JButton("Calculate Total 2");
		calculateTotal2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int W11Int = Integer.valueOf(W11TextField.getText());
				int W12Int = Integer.valueOf(W12TextField.getText());
				int W21Int = Integer.valueOf(W21TextField.getText());
				int W22Int = Integer.valueOf(W22TextField.getText());
				int W31Int = Integer.valueOf(W31TextField.getText());
				int W32Int = Integer.valueOf(W32TextField.getText());
				int W33Int = Integer.valueOf(W33TextField.getText());
				int WTotals1 = (W11Int + W12Int + W21Int + W22Int + W31Int + W32Int + W33Int);

				int W41Int = Integer.valueOf(W41TextField.getText());
				int W42Int = Integer.valueOf(W42TextField.getText());
				int W43Int = Integer.valueOf(W43TextField.getText());
				int W44Int = Integer.valueOf(W44TextField.getText());
				int WTotals2 = (W41Int + W42Int + W43Int + W44Int);
				int WTotals3 = WTotals1 + WTotals2;

				total1TextField.setText(String.valueOf(WTotals1));
				total2TextField.setText(String.valueOf(WTotals2));
				total3TextField.setText(String.valueOf(WTotals3));

			}
		});

		calculateTotal3 = new JButton("Calculate Total 3");
		calculateTotal3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				int W11Int = Integer.valueOf(W11TextField.getText());
				int W12Int = Integer.valueOf(W12TextField.getText());
				int W21Int = Integer.valueOf(W21TextField.getText());
				int W22Int = Integer.valueOf(W22TextField.getText());
				int W31Int = Integer.valueOf(W31TextField.getText());
				int W32Int = Integer.valueOf(W32TextField.getText());
				int W33Int = Integer.valueOf(W33TextField.getText());
				int WTotals1 = (W11Int + W12Int + W21Int + W22Int + W31Int + W32Int + W33Int);

				int W41Int = Integer.valueOf(W41TextField.getText());
				int W42Int = Integer.valueOf(W42TextField.getText());
				int W43Int = Integer.valueOf(W43TextField.getText());
				int W44Int = Integer.valueOf(W44TextField.getText());
				int WTotals2 = (W41Int + W42Int + W43Int + W44Int);
				int WTotals3 = WTotals1 + WTotals2;

				total1TextField.setText(String.valueOf(WTotals1));
				total2TextField.setText(String.valueOf(WTotals2));
				total3TextField.setText(String.valueOf(WTotals3));

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

		JPanel optionPanel1 = new JPanel(new GridLayout(15, 2));
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

			optionPanel1.add(W11Label);
			optionPanel1.add(W11TextField);

			optionPanel1.add(W12Label);
			optionPanel1.add(W12TextField);

			optionPanel1.add(W21Label);
			optionPanel1.add(W21TextField);

			optionPanel1.add(W22Label);
			optionPanel1.add(W22TextField);

			optionPanel1.add(W31Label);
			optionPanel1.add(W31TextField);

			optionPanel1.add(W32Label);
			optionPanel1.add(W32TextField);

			optionPanel1.add(W33Label);
			optionPanel1.add(W33TextField);

			optionPanel1.add(calculateTotal1);
			optionPanel1.add(total1TextField);
			total1TextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(W41Label);
			optionPanel1.add(W41TextField);

			optionPanel1.add(W42Label);
			optionPanel1.add(W42TextField);

			optionPanel1.add(W43Label);
			optionPanel1.add(W43TextField);

			optionPanel1.add(W44Label);
			optionPanel1.add(W44TextField);

			optionPanel1.add(calculateTotal2);
			optionPanel1.add(total2TextField);
			total2TextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateTotal3);
			optionPanel1.add(total3TextField);
			total3TextField.setBackground(Color.LIGHT_GRAY);

		}

		// Searching
		else if (view == -2) {

			currentId = SQLiteConnection.EndCountsGetHighestID();
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			model.setDate(yearInt, monthInt, dayInt);
			model.setSelected(true);
			add.setVisible(false);
			back.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(datePicker);

			optionPanel1.add(W11Label);
			optionPanel1.add(W11TextField);

			optionPanel1.add(W12Label);
			optionPanel1.add(W12TextField);

			optionPanel1.add(W21Label);
			optionPanel1.add(W21TextField);

			optionPanel1.add(W22Label);
			optionPanel1.add(W22TextField);

			optionPanel1.add(W31Label);
			optionPanel1.add(W31TextField);

			optionPanel1.add(W32Label);
			optionPanel1.add(W32TextField);

			optionPanel1.add(W33Label);
			optionPanel1.add(W33TextField);

			optionPanel1.add(calculateTotal1);
			optionPanel1.add(total1TextField);
			total1TextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(W41Label);
			optionPanel1.add(W41TextField);

			optionPanel1.add(W42Label);
			optionPanel1.add(W42TextField);

			optionPanel1.add(W43Label);
			optionPanel1.add(W43TextField);

			optionPanel1.add(W44Label);
			optionPanel1.add(W44TextField);

			optionPanel1.add(calculateTotal2);
			optionPanel1.add(total2TextField);
			total2TextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(calculateTotal3);
			optionPanel1.add(total3TextField);
			total3TextField.setBackground(Color.LIGHT_GRAY);

		}
		// Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(14, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(W11MonthlyLabel);
			optionPanel1.add(W11MonthlyTextField);

			optionPanel1.add(W12MonthlyLabel);
			optionPanel1.add(W12MonthlyTextField);

			optionPanel1.add(W21MonthlyLabel);
			optionPanel1.add(W21MonthlyTextField);

			optionPanel1.add(W22MonthlyLabel);
			optionPanel1.add(W22MonthlyTextField);

			optionPanel1.add(W31MonthlyLabel);
			optionPanel1.add(W31MonthlyTextField);

			optionPanel1.add(W32MonthlyLabel);
			optionPanel1.add(W32MonthlyTextField);

			optionPanel1.add(W33MonthlyLabel);
			optionPanel1.add(W33MonthlyTextField);

			optionPanel1.add(total1MonthlyLabel);
			optionPanel1.add(total1MonthlyTextfield);
			total1MonthlyTextfield.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(W41MonthlyLabel);
			optionPanel1.add(W41MonthlyTextField);

			optionPanel1.add(W42MonthlyLabel);
			optionPanel1.add(W42MonthlyTextField);

			optionPanel1.add(W43MonthlyLabel);
			optionPanel1.add(W43MonthlyTextField);

			optionPanel1.add(W44MonthlyLabel);
			optionPanel1.add(W44MonthlyTextField);

			optionPanel1.add(total2MonthlyLabel);
			optionPanel1.add(total2MonthlyTextField);
			total2MonthlyTextField.setBackground(Color.LIGHT_GRAY);

			optionPanel1.add(total3MonthlyLabel);
			optionPanel1.add(total3MonthlyTextField);
			total3MonthlyTextField.setBackground(Color.LIGHT_GRAY);

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

		

			int highestID=0;
			try {
				highestID = SQLiteConnection.EndCountsGetHighestID();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object[] result = new Object[16];
			try {
				result = SQLiteConnection.EndCountsReturnEntryByID(highestID);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

			W11TextField.setText(String.valueOf(result[2]));
			W12TextField.setText(String.valueOf(result[3]));
			W21TextField.setText(String.valueOf(result[4]));
			W22TextField.setText(String.valueOf(result[5]));
			W31TextField.setText(String.valueOf(result[6]));
			W32TextField.setText(String.valueOf(result[7]));
			W33TextField.setText(String.valueOf(result[8]));
			total1TextField.setText(String.valueOf(result[9]));
			W41TextField.setText(String.valueOf(result[10]));
			W42TextField.setText(String.valueOf(result[11]));
			W43TextField.setText(String.valueOf(result[12]));
			W44TextField.setText(String.valueOf(result[13]));
			total2TextField.setText(String.valueOf(result[14]));
			total3TextField.setText(String.valueOf(result[15]));

			currentId = highestID;

		}
	

	public static void calculateTotals() {

		// TODO Auto-generated method stub

		int W11Int = Integer.valueOf(W11TextField.getText());
		int W12Int = Integer.valueOf(W12TextField.getText());
		int W21Int = Integer.valueOf(W21TextField.getText());
		int W22Int = Integer.valueOf(W22TextField.getText());
		int W31Int = Integer.valueOf(W31TextField.getText());
		int W32Int = Integer.valueOf(W32TextField.getText());
		int W33Int = Integer.valueOf(W33TextField.getText());
		int WTotals1 = (W11Int + W12Int + W21Int + W22Int + W31Int + W32Int + W33Int);

		int W41Int = Integer.valueOf(W41TextField.getText());
		int W42Int = Integer.valueOf(W42TextField.getText());
		int W43Int = Integer.valueOf(W43TextField.getText());
		int W44Int = Integer.valueOf(W44TextField.getText());
		int WTotals2 = (W41Int + W42Int + W43Int + W44Int);
		int WTotals3 = WTotals1 + WTotals2;

		total1TextField.setText(String.valueOf(WTotals1));
		total2TextField.setText(String.valueOf(WTotals2));
		total3TextField.setText(String.valueOf(WTotals3));

	}
}
