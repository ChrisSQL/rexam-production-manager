package com.binentryscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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

public class SpoilageByDay {

	JButton add, find, next, previous, update, addNew, search, monthly, go;
	static JButton back;
	JButton calculateTotal1;
	JButton calculateTotal2;
	JButton calculateTotal3;
	JButton clearAll;
	JButton goTable;
	JLabel dateLabel, dateLabel2, selectMonth;

	JLabel optime1and2Label, optime3Label, W21Label, m1BBalLabel, m3BBalLabel, m1LinerLabel, m2LinerLabel, stolle11Label, stolle12Label, m3ABalLabel, m1ABalLabel, ovecTesterlabel,
			qCLabLabel, bordenNo1Label, m4QcAreaLabel, stolle21Label, stolle22Label, stolle33Label, stolle31Label, stolle32Label, m2BBalLabel, m2ABalLabel, m3LinersLabel,
			m3QcAreaLabel, stolle42Label, m4BBalLabel, m4LinersLabel, qCMod1Label, stolle41Label, stolle43Label, stolle44Label, balancer4BLabel, balancer4ALabel, formatecLabel,
			blankLabel1, blankLabel2, blankLabel3, blankLabel4;

	JLabel W11MonthlyLabel, W12MonthlyLabel, W21MonthlyLabel, W22MonthlyLabel, W31MonthlyLabel, W32MonthlyLabel, W33MonthlyLabel, total1MonthlyLabel, W41MonthlyLabel,
			W42MonthlyLabel, W43MonthlyLabel, W44MonthlyLabel, total2MonthlyLabel, total3MonthlyLabel;

	JTextField dateTextField, optime1and2TextField, optime3TextField, m1BBalTextField, m3BBalTextField, m1LinerTextField, m2LinerTextField, stolle11TextField, stolle12TextField,
			m3ABalTextField, m1ABalTextField, ovecTesterTextField, qCLabTextField, bordenNo1TextField, m4QcAreaTextField, stolle21TextField, stolle22TextField, stolle33TextField,
			stolle31TextField, stolle32TextField, m2BBalTextField, m2ABalTextField, m3LinersTextField, m3QcAreaTextField, stolle42TextField, m4B2BalTextField, m4LinersTextField,
			qCMod1TextField, stolle41TextField, stolle43TextField, stolle44TextField, balancer4BTextField, balancer4ATextField, formatecTextField, blankTextField;

	int view, currentId;
	static int total;
	Date selectedDate;
	JComboBox monthCombo, yearCombo;
	static int[] values;
	JFrame frame15;

	public static void main(String[] args) throws SQLException {

		new SpoilageByDay(1, -2);

	}

	public SpoilageByDay(int idIn, int view) throws SQLException {

		currentId = SQLiteConnection.SpoilageByDayGetHighestID() + 1;
		// System.out.println("CurrentID Start " + currentId);

		// Add a view to analytics.
		try {
			SQLiteConnection.incrementViewsAnalytics(0, 0, 0, 0, 0, 0, 0, 0, 1);
		} catch (SQLException e2) {
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
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		frame15 = new JFrame();
		// frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame15.setTitle("Spoilage By Day");
		frame15.setSize(800, 600);
		frame15.setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...

		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month);
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);

		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" };
		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		UtilDateModel model = new UtilDateModel();
		// model.setDate(yearInt, monthInt-1, dayInt);
		model.setSelected(true);
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

			}
		});

		UtilDateModel model2 = new UtilDateModel();
		model.setDate(yearInt, monthInt, dayInt);
		model.setSelected(true);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		// JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);

		dateTextField = new JTextField();
		PlainDocument doc1 = (PlainDocument) dateTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());
		optime1and2TextField = new JTextField();
		PlainDocument doc2 = (PlainDocument) optime1and2TextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());
		optime3TextField = new JTextField();
		PlainDocument doc3 = (PlainDocument) optime3TextField.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());
		m1BBalTextField = new JTextField();
		PlainDocument doc4 = (PlainDocument) m1BBalTextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());
		m3BBalTextField = new JTextField();
		PlainDocument doc5 = (PlainDocument) m3BBalTextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());
		m1LinerTextField = new JTextField();
		PlainDocument doc6 = (PlainDocument) m1LinerTextField.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());
		m2LinerTextField = new JTextField();
		PlainDocument doc7 = (PlainDocument) m2LinerTextField.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());
		stolle11TextField = new JTextField();
		PlainDocument doc8 = (PlainDocument) stolle11TextField.getDocument();
		doc8.setDocumentFilter(new MyIntFilter());
		stolle12TextField = new JTextField();
		PlainDocument doc9 = (PlainDocument) stolle12TextField.getDocument();
		doc9.setDocumentFilter(new MyIntFilter());
		m3ABalTextField = new JTextField();
		PlainDocument doc10 = (PlainDocument) m3ABalTextField.getDocument();
		doc10.setDocumentFilter(new MyIntFilter());
		m1ABalTextField = new JTextField();
		PlainDocument doc11 = (PlainDocument) m1ABalTextField.getDocument();
		doc11.setDocumentFilter(new MyIntFilter());
		ovecTesterTextField = new JTextField();
		PlainDocument doc12 = (PlainDocument) ovecTesterTextField.getDocument();
		doc12.setDocumentFilter(new MyIntFilter());
		qCLabTextField = new JTextField();
		PlainDocument doc13 = (PlainDocument) qCLabTextField.getDocument();
		doc13.setDocumentFilter(new MyIntFilter());
		bordenNo1TextField = new JTextField();
		PlainDocument doc14 = (PlainDocument) bordenNo1TextField.getDocument();
		doc14.setDocumentFilter(new MyIntFilter());
		m4QcAreaTextField = new JTextField();
		PlainDocument doc15 = (PlainDocument) m4QcAreaTextField.getDocument();
		doc15.setDocumentFilter(new MyIntFilter());
		stolle21TextField = new JTextField();
		PlainDocument doc16 = (PlainDocument) stolle21TextField.getDocument();
		doc16.setDocumentFilter(new MyIntFilter());
		stolle22TextField = new JTextField();
		PlainDocument doc17 = (PlainDocument) stolle22TextField.getDocument();
		doc17.setDocumentFilter(new MyIntFilter());
		stolle33TextField = new JTextField();
		PlainDocument doc18 = (PlainDocument) stolle33TextField.getDocument();
		doc18.setDocumentFilter(new MyIntFilter());
		stolle31TextField = new JTextField();
		PlainDocument doc19 = (PlainDocument) stolle31TextField.getDocument();
		doc19.setDocumentFilter(new MyIntFilter());
		stolle32TextField = new JTextField();
		PlainDocument doc20 = (PlainDocument) stolle32TextField.getDocument();
		doc20.setDocumentFilter(new MyIntFilter());
		m2BBalTextField = new JTextField();
		PlainDocument doc21 = (PlainDocument) m2BBalTextField.getDocument();
		doc21.setDocumentFilter(new MyIntFilter());
		m2ABalTextField = new JTextField();
		PlainDocument doc22 = (PlainDocument) m2ABalTextField.getDocument();
		doc22.setDocumentFilter(new MyIntFilter());
		m3LinersTextField = new JTextField();
		PlainDocument doc23 = (PlainDocument) m3LinersTextField.getDocument();
		doc23.setDocumentFilter(new MyIntFilter());
		m3QcAreaTextField = new JTextField();
		PlainDocument doc24 = (PlainDocument) m3QcAreaTextField.getDocument();
		doc24.setDocumentFilter(new MyIntFilter());
		stolle42TextField = new JTextField();
		PlainDocument doc25 = (PlainDocument) stolle42TextField.getDocument();
		doc25.setDocumentFilter(new MyIntFilter());
		m4B2BalTextField = new JTextField();
		PlainDocument doc26 = (PlainDocument) m4B2BalTextField.getDocument();
		doc26.setDocumentFilter(new MyIntFilter());
		m4LinersTextField = new JTextField();
		PlainDocument doc27 = (PlainDocument) m4LinersTextField.getDocument();
		doc27.setDocumentFilter(new MyIntFilter());
		qCMod1TextField = new JTextField();
		PlainDocument doc28 = (PlainDocument) qCMod1TextField.getDocument();
		doc28.setDocumentFilter(new MyIntFilter());
		stolle41TextField = new JTextField();
		PlainDocument doc29 = (PlainDocument) stolle41TextField.getDocument();
		doc29.setDocumentFilter(new MyIntFilter());
		stolle43TextField = new JTextField();
		PlainDocument doc30 = (PlainDocument) stolle43TextField.getDocument();
		doc30.setDocumentFilter(new MyIntFilter());
		stolle44TextField = new JTextField();
		PlainDocument doc31 = (PlainDocument) stolle44TextField.getDocument();
		doc31.setDocumentFilter(new MyIntFilter());
		balancer4BTextField = new JTextField();
		PlainDocument doc32 = (PlainDocument) balancer4BTextField.getDocument();
		doc32.setDocumentFilter(new MyIntFilter());
		balancer4ATextField = new JTextField();
		PlainDocument doc33 = (PlainDocument) balancer4ATextField.getDocument();
		doc33.setDocumentFilter(new MyIntFilter());
		formatecTextField = new JTextField();
		PlainDocument doc34 = (PlainDocument) formatecTextField.getDocument();
		doc34.setDocumentFilter(new MyIntFilter());
		blankTextField = new JTextField();
		PlainDocument doc35 = (PlainDocument) blankTextField.getDocument();
		doc35.setDocumentFilter(new MyIntFilter());

		optime1and2Label = new JLabel("Optime 1/2", SwingConstants.CENTER);
		optime3Label = new JLabel("Optime 3", SwingConstants.CENTER);
		m1BBalLabel = new JLabel("M1 B Bal", SwingConstants.CENTER);
		m3BBalLabel = new JLabel("M3 B Bal", SwingConstants.CENTER);
		m1LinerLabel = new JLabel("M1 Liner", SwingConstants.CENTER);
		m2LinerLabel = new JLabel("M2 Liner", SwingConstants.CENTER);
		stolle11Label = new JLabel("Stolle 11", SwingConstants.CENTER);
		stolle22Label = new JLabel("Stolle 22", SwingConstants.CENTER);
		stolle12Label = new JLabel("Stolle 12", SwingConstants.CENTER);
		m3ABalLabel = new JLabel("M3 A Bal", SwingConstants.CENTER);
		m1ABalLabel = new JLabel("M1 A Bal", SwingConstants.CENTER);
		ovecTesterlabel = new JLabel("Ovec Tester", SwingConstants.CENTER);
		qCLabLabel = new JLabel("QC Lab", SwingConstants.CENTER);
		bordenNo1Label = new JLabel("Bordern No1", SwingConstants.CENTER);
		m4QcAreaLabel = new JLabel("M4 QC Area", SwingConstants.CENTER);
		stolle21Label = new JLabel("Stolle 21", SwingConstants.CENTER);
		stolle22Label = new JLabel("Stolle 21", SwingConstants.CENTER);
		stolle33Label = new JLabel("Stolle 33", SwingConstants.CENTER);
		stolle31Label = new JLabel("Stolle 31", SwingConstants.CENTER);
		stolle32Label = new JLabel("Stolle 32", SwingConstants.CENTER);
		m2BBalLabel = new JLabel("M2 B Bal", SwingConstants.CENTER);
		m2ABalLabel = new JLabel("M2 A Bal", SwingConstants.CENTER);
		m3LinersLabel = new JLabel("M3 Liner", SwingConstants.CENTER);
		m3QcAreaLabel = new JLabel("M3 QC Area", SwingConstants.CENTER);
		stolle42Label = new JLabel("Stolle 42", SwingConstants.CENTER);
		m4BBalLabel = new JLabel("M4 2 Bal", SwingConstants.CENTER);
		m4LinersLabel = new JLabel("M4 Liner", SwingConstants.CENTER);
		qCMod1Label = new JLabel("QC Mod 1", SwingConstants.CENTER);
		stolle41Label = new JLabel("Stolle 41", SwingConstants.CENTER);
		stolle43Label = new JLabel("Stolle 43", SwingConstants.CENTER);
		stolle44Label = new JLabel("Stolle 44", SwingConstants.CENTER);
		balancer4BLabel = new JLabel("Balancer 4 B", SwingConstants.CENTER);
		balancer4ALabel = new JLabel("Balancer 4 A", SwingConstants.CENTER);
		formatecLabel = new JLabel("Formatec", SwingConstants.CENTER);
		selectMonth = new JLabel("Select Month ");
		blankLabel1 = new JLabel("");
		blankLabel2 = new JLabel("");
		blankLabel3 = new JLabel("");
		blankLabel4 = new JLabel("");

		clearAll = new JButton("Reset");
		clearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					currentId = SQLiteConnection.SpoilageByDayGetHighestID() + 1;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				model.setDate(yearInt, monthInt-1, dayInt);
				model.setSelected(true);

				optime1and2TextField.setText("0");
				optime3TextField.setText("0");
				m1BBalTextField.setText("0");
				m3BBalTextField.setText("0");
				m1LinerTextField.setText("0");
				m2LinerTextField.setText("0");
				stolle11TextField.setText("0");
				stolle12TextField.setText("0");
				m3ABalTextField.setText("0");
				m1ABalTextField.setText("0");
				ovecTesterTextField.setText("0");
				qCLabTextField.setText("0");
				bordenNo1TextField.setText("0");
				m4QcAreaTextField.setText("0");
				stolle21TextField.setText("0");
				stolle22TextField.setText("0");
				stolle33TextField.setText("0");
				stolle31TextField.setText("0");
				stolle32TextField.setText("0");
				m2BBalTextField.setText("0");
				m2ABalTextField.setText("0");
				m3LinersTextField.setText("0");
				m3QcAreaTextField.setText("0");
				stolle42TextField.setText("0");
				m4B2BalTextField.setText("0");
				m4LinersTextField.setText("0");
				qCMod1TextField.setText("0");
				stolle41TextField.setText("0");
				stolle43TextField.setText("0");
				stolle44TextField.setText("0");
				balancer4BTextField.setText("0");
				balancer4ATextField.setText("0");
				formatecTextField.setText("0");

			}
		});

		go = new JButton("Go");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();

				try {
					Object[] total = SQLiteConnection.EndCountsCalculateTotalsByMonth(month, year);
					// System.out.println("Total0 " + total[0]);

					// W11MonthlyTextField.setText(String.valueOf(total[0]));
					// W12MonthlyTextField.setText(String.valueOf(total[1]));
					// W21MonthlyTextField.setText(String.valueOf(total[2]));
					// W22MonthlyTextField.setText(String.valueOf(total[3]));
					// W31MonthlyTextField.setText(String.valueOf(total[4]));
					// W32MonthlyTextField.setText(String.valueOf(total[5]));
					// W33MonthlyTextField.setText(String.valueOf(total[6]));
					// total1MonthlyTextfield.setText(String.valueOf(total[7]));
					// W41MonthlyTextField.setText(String.valueOf(total[8]));
					// W42MonthlyTextField.setText(String.valueOf(total[9]));
					// W43MonthlyTextField.setText(String.valueOf(total[10]));
					// W44MonthlyTextField.setText(String.valueOf(total[11]));
					// total2MonthlyTextField.setText(String.valueOf(total[12]));
					// total3MonthlyTextField.setText(String.valueOf(total[13]));

				} catch (SQLException e1) {
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
					new SpoilageByDay(1, -2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		add = new JButton("Save/Update Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Object[] array = new Object[36];
				selectedDate = (Date) datePicker.getModel().getValue();
				try {
					array = SQLiteConnection.SpoilageByDayReturnEntryByDate(selectedDate);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				if (array[0] == null) {

					// Add

					selectedDate = (Date) datePicker.getModel().getValue();
					String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

					// Get int value of a JTextfield

					try {
						SQLiteConnection.SpoilageByDayInsert(

						SQLiteConnection.SpoilageByDayGetHighestID() + 1, date, Integer.parseInt(optime1and2TextField.getText()), Integer.parseInt(optime3TextField.getText()),
								Integer.parseInt(m1BBalTextField.getText()), Integer.parseInt(m3BBalTextField.getText()), Integer.parseInt(m1LinerTextField.getText()),
								Integer.parseInt(m2LinerTextField.getText()), Integer.parseInt(stolle11TextField.getText()), Integer.parseInt(stolle22TextField.getText()),
								Integer.parseInt(m3ABalTextField.getText()), Integer.parseInt(m1ABalTextField.getText()), Integer.parseInt(ovecTesterTextField.getText()),
								Integer.parseInt(qCLabTextField.getText()), Integer.parseInt(bordenNo1TextField.getText()), Integer.parseInt(m4QcAreaTextField.getText()),
								Integer.parseInt(stolle21TextField.getText()), Integer.parseInt(stolle33TextField.getText()), Integer.parseInt(stolle12TextField.getText()),
								Integer.parseInt(stolle31TextField.getText()), Integer.parseInt(stolle32TextField.getText()), Integer.parseInt(m2BBalTextField.getText()),
								Integer.parseInt(m2ABalTextField.getText()), Integer.parseInt(m3LinersTextField.getText()), Integer.parseInt(m3QcAreaTextField.getText()),
								Integer.parseInt(stolle42TextField.getText()), Integer.parseInt(m4B2BalTextField.getText()), Integer.parseInt(m4LinersTextField.getText()),
								Integer.parseInt(qCMod1TextField.getText()), Integer.parseInt(stolle41TextField.getText()), Integer.parseInt(stolle43TextField.getText()),
								Integer.parseInt(stolle44TextField.getText()), Integer.parseInt(balancer4BTextField.getText()), Integer.parseInt(balancer4ATextField.getText()),
								Integer.parseInt(formatecTextField.getText())

						);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// TODO Auto-generated method stub

					frame15.dispose();

				}

				else {

					// Update

					selectedDate = (Date) datePicker.getModel().getValue();
					String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

					try {
						SQLiteConnection.SpoilageByDayUpdate(

								// date,
								Integer.parseInt(optime1and2TextField.getText()), Integer.parseInt(optime3TextField.getText()), Integer.parseInt(m1BBalTextField.getText()),
								Integer.parseInt(m3BBalTextField.getText()), Integer.parseInt(m1LinerTextField.getText()), Integer.parseInt(m2LinerTextField.getText()),
								Integer.parseInt(stolle11TextField.getText()), Integer.parseInt(stolle22TextField.getText()), Integer.parseInt(m3ABalTextField.getText()),
								Integer.parseInt(m1ABalTextField.getText()), Integer.parseInt(ovecTesterTextField.getText()), Integer.parseInt(qCLabTextField.getText()),
								Integer.parseInt(bordenNo1TextField.getText()), Integer.parseInt(m4QcAreaTextField.getText()), Integer.parseInt(stolle21TextField.getText()),
								Integer.parseInt(stolle33TextField.getText()), Integer.parseInt(stolle12TextField.getText()), Integer.parseInt(stolle31TextField.getText()),
								Integer.parseInt(stolle32TextField.getText()), Integer.parseInt(m2BBalTextField.getText()), Integer.parseInt(m2ABalTextField.getText()),
								Integer.parseInt(m3LinersTextField.getText()), Integer.parseInt(m3QcAreaTextField.getText()), Integer.parseInt(stolle42TextField.getText()),
								Integer.parseInt(m4B2BalTextField.getText()), Integer.parseInt(m4LinersTextField.getText()), Integer.parseInt(qCMod1TextField.getText()),
								Integer.parseInt(stolle41TextField.getText()), Integer.parseInt(stolle43TextField.getText()), Integer.parseInt(stolle44TextField.getText()),
								Integer.parseInt(balancer4BTextField.getText()), Integer.parseInt(balancer4ATextField.getText()), Integer.parseInt(formatecTextField.getText()),
								currentId

						);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});

		goTable = new JButton("Go ");
		goTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					createTrendFrame((String) monthCombo.getSelectedItem(), (String) yearCombo.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new SpoilageByDay(1, -2);
				} catch (SQLException e1) {
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
					new SpoilageByDay(1, -2);
				} catch (SQLException e1) {
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
					new SpoilageByDay(1, -3);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame15.dispose();

			}
		});

		update = new JButton("Update/Save Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				try {
					SQLiteConnection.SpoilageByDayUpdate(

							// date,
							Integer.parseInt(optime1and2TextField.getText()), Integer.parseInt(optime3TextField.getText()), Integer.parseInt(m1BBalTextField.getText()),
							Integer.parseInt(m3BBalTextField.getText()), Integer.parseInt(m1LinerTextField.getText()), Integer.parseInt(m2LinerTextField.getText()),
							Integer.parseInt(stolle11TextField.getText()), Integer.parseInt(stolle22TextField.getText()), Integer.parseInt(m3ABalTextField.getText()),
							Integer.parseInt(m1ABalTextField.getText()), Integer.parseInt(ovecTesterTextField.getText()), Integer.parseInt(qCLabTextField.getText()),
							Integer.parseInt(bordenNo1TextField.getText()), Integer.parseInt(m4QcAreaTextField.getText()), Integer.parseInt(stolle21TextField.getText()),
							Integer.parseInt(stolle33TextField.getText()), Integer.parseInt(stolle12TextField.getText()), Integer.parseInt(stolle31TextField.getText()),
							Integer.parseInt(stolle32TextField.getText()), Integer.parseInt(m2BBalTextField.getText()), Integer.parseInt(m2ABalTextField.getText()),
							Integer.parseInt(m3LinersTextField.getText()), Integer.parseInt(m3QcAreaTextField.getText()), Integer.parseInt(stolle42TextField.getText()),
							Integer.parseInt(m4B2BalTextField.getText()), Integer.parseInt(m4LinersTextField.getText()), Integer.parseInt(qCMod1TextField.getText()),
							Integer.parseInt(stolle41TextField.getText()), Integer.parseInt(stolle43TextField.getText()), Integer.parseInt(stolle44TextField.getText()),
							Integer.parseInt(balancer4BTextField.getText()), Integer.parseInt(balancer4ATextField.getText()), Integer.parseInt(formatecTextField.getText()),
							currentId

					);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

					Object[] total = SQLiteConnection.SpoilageByDayReturnEntryByDate(selectedDate);

					if (total[1] == null) {

						SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

					}

					else {

						currentId = (int) total[0];

						optime1and2TextField.setText((String.valueOf(total[2])));
						optime3TextField.setText((String.valueOf(total[3])));
						m1BBalTextField.setText((String.valueOf(total[4])));
						m3BBalTextField.setText((String.valueOf(total[5])));
						m1LinerTextField.setText((String.valueOf(total[6])));
						m2LinerTextField.setText((String.valueOf(total[7])));
						stolle11TextField.setText((String.valueOf(total[8])));
						stolle12TextField.setText((String.valueOf(total[9])));
						m3ABalTextField.setText((String.valueOf(total[10])));
						m1ABalTextField.setText((String.valueOf(total[11])));
						ovecTesterTextField.setText((String.valueOf(total[12])));
						qCLabTextField.setText((String.valueOf(total[13])));
						bordenNo1TextField.setText((String.valueOf(total[14])));
						m4QcAreaTextField.setText((String.valueOf(total[15])));
						stolle21TextField.setText((String.valueOf(total[16])));
						stolle22TextField.setText((String.valueOf(total[17])));
						stolle33TextField.setText((String.valueOf(total[18])));
						stolle31TextField.setText((String.valueOf(total[19])));
						stolle32TextField.setText((String.valueOf(total[20])));
						m2BBalTextField.setText((String.valueOf(total[21])));
						m2ABalTextField.setText((String.valueOf(total[22])));
						m3LinersTextField.setText((String.valueOf(total[23])));
						m3QcAreaTextField.setText((String.valueOf(total[24])));
						stolle42TextField.setText((String.valueOf(total[25])));
						m4B2BalTextField.setText((String.valueOf(total[26])));
						m4LinersTextField.setText((String.valueOf(total[27])));
						qCMod1TextField.setText((String.valueOf(total[28])));
						stolle41TextField.setText((String.valueOf(total[29])));
						stolle43TextField.setText((String.valueOf(total[30])));
						stolle44TextField.setText((String.valueOf(total[31])));
						balancer4BTextField.setText((String.valueOf(total[32])));
						balancer4ATextField.setText((String.valueOf(total[33])));
						formatecTextField.setText((String.valueOf(total[34])));

					}

					// System.out.println("CurrentID " + currentId);

				} catch (Exception e1) {
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

					Object[] total = SQLiteConnection.SpoilageByDayGetNextEntryById(currentId);

					if (total[0] == null) {

						SQLiteConnection.infoBox("No Next Result.", "");

					}
					else {

						String dateFormatted = (String) total[1];
						int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
						int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						currentId = currentId + 1;

						optime1and2TextField.setText(String.valueOf(total[2]));
						optime3TextField.setText(String.valueOf(total[3]));
						m1BBalTextField.setText(String.valueOf(total[4]));
						m3BBalTextField.setText(String.valueOf(total[5]));
						m1LinerTextField.setText(String.valueOf(total[6]));
						m2LinerTextField.setText(String.valueOf(total[7]));
						stolle11TextField.setText(String.valueOf(total[8]));
						stolle22TextField.setText(String.valueOf(total[9]));
						m3ABalTextField.setText(String.valueOf(total[10]));
						m1ABalTextField.setText(String.valueOf(total[11]));
						ovecTesterTextField.setText(String.valueOf(total[12]));
						qCLabTextField.setText(String.valueOf(total[13]));
						bordenNo1TextField.setText(String.valueOf(total[14]));
						m4QcAreaTextField.setText(String.valueOf(total[15]));
						stolle21TextField.setText(String.valueOf(total[16]));
						stolle33TextField.setText(String.valueOf(total[17]));
						stolle12TextField.setText(String.valueOf(total[18]));
						stolle31TextField.setText(String.valueOf(total[19]));
						stolle32TextField.setText(String.valueOf(total[20]));
						m2BBalTextField.setText(String.valueOf(total[21]));
						m2ABalTextField.setText(String.valueOf(total[22]));
						m3LinersTextField.setText(String.valueOf(total[23]));
						m3QcAreaTextField.setText(String.valueOf(total[24]));
						stolle42TextField.setText(String.valueOf(total[25]));
						m4B2BalTextField.setText(String.valueOf(total[26]));
						m4LinersTextField.setText(String.valueOf(total[27]));
						qCMod1TextField.setText(String.valueOf(total[28]));
						stolle41TextField.setText(String.valueOf(total[29]));
						stolle43TextField.setText(String.valueOf(total[30]));
						stolle44TextField.setText(String.valueOf(total[31]));
						balancer4BTextField.setText(String.valueOf(total[32]));
						balancer4ATextField.setText(String.valueOf(total[33]));
						formatecTextField.setText(String.valueOf(total[34]));

					}

					// System.out.println("CurrentID " + currentId);

					// Fill Boxes with results

					// model.setDate(year2, month2, day2);
					model.setSelected(true);

				} catch (Exception e1) {
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

					Object[] total = SQLiteConnection.SpoilageByDayGetPreviousEntryById(currentId);

					if (total[0] == null) {

						// System.out.println("1 CurrentID : " + currentId);

						SQLiteConnection.infoBox("No Previous Result.", "");

					}
					else {

						String dateFormatted = (String) total[1];
						int day = Integer.parseInt(dateFormatted.substring(0, 2)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(3, 5)) - 1; // Correct
						int year = Integer.parseInt(dateFormatted.substring(6, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						currentId = currentId - 1;

						optime1and2TextField.setText(String.valueOf(total[2]));
						optime3TextField.setText(String.valueOf(total[3]));
						m1BBalTextField.setText(String.valueOf(total[4]));
						m3BBalTextField.setText(String.valueOf(total[5]));
						m1LinerTextField.setText(String.valueOf(total[6]));
						m2LinerTextField.setText(String.valueOf(total[7]));
						stolle11TextField.setText(String.valueOf(total[8]));
						stolle22TextField.setText(String.valueOf(total[9]));
						m3ABalTextField.setText(String.valueOf(total[10]));
						m1ABalTextField.setText(String.valueOf(total[11]));
						ovecTesterTextField.setText(String.valueOf(total[12]));
						qCLabTextField.setText(String.valueOf(total[13]));
						bordenNo1TextField.setText(String.valueOf(total[14]));
						m4QcAreaTextField.setText(String.valueOf(total[15]));
						stolle21TextField.setText(String.valueOf(total[16]));
						stolle33TextField.setText(String.valueOf(total[17]));
						stolle12TextField.setText(String.valueOf(total[18]));
						stolle31TextField.setText(String.valueOf(total[19]));
						stolle32TextField.setText(String.valueOf(total[20]));
						m2BBalTextField.setText(String.valueOf(total[21]));
						m2ABalTextField.setText(String.valueOf(total[22]));
						m3LinersTextField.setText(String.valueOf(total[23]));
						m3QcAreaTextField.setText(String.valueOf(total[24]));
						stolle42TextField.setText(String.valueOf(total[25]));
						m4B2BalTextField.setText(String.valueOf(total[26]));
						m4LinersTextField.setText(String.valueOf(total[27]));
						qCMod1TextField.setText(String.valueOf(total[28]));
						stolle41TextField.setText(String.valueOf(total[29]));
						stolle43TextField.setText(String.valueOf(total[30]));
						stolle44TextField.setText(String.valueOf(total[31]));
						balancer4BTextField.setText(String.valueOf(total[32]));
						balancer4ATextField.setText(String.valueOf(total[33]));
						formatecTextField.setText(String.valueOf(total[34]));

					}

					// System.out.println("CurrentID : " + currentId);

					// Fill Boxes with results

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

		// Buttons Top Panel

		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		//buttonsPanel.setBackground(Color.GRAY);

		buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1

		JPanel optionPanel1 = new JPanel(new GridLayout(32, 2));
		optionPanel1.setBackground(Color.GRAY);

		// ComboPanelMonthly

		JPanel comboPanel = new JPanel(new FlowLayout());
		comboPanel.setBackground(Color.GRAY);
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
			addNew.setVisible(false);

			optionPanel1 = new JPanel(new GridLayout(17, 4));

			optionPanel1.add(dateLabel);
			optionPanel1.add(datePicker);
			optionPanel1.add(stolle12Label);
			optionPanel1.add(stolle12TextField);
			optionPanel1.add(optime1and2Label);
			optionPanel1.add(optime1and2TextField);
			optionPanel1.add(stolle31Label);
			optionPanel1.add(stolle31TextField);
			optionPanel1.add(optime3Label);
			optionPanel1.add(optime3TextField);
			optionPanel1.add(stolle32Label);
			optionPanel1.add(stolle32TextField);
			optionPanel1.add(m1BBalLabel);
			optionPanel1.add(m1BBalTextField);
			optionPanel1.add(m2BBalLabel);
			optionPanel1.add(m2BBalTextField);
			optionPanel1.add(m3BBalLabel);
			optionPanel1.add(m3BBalTextField);
			optionPanel1.add(m2ABalLabel);
			optionPanel1.add(m2ABalTextField);
			optionPanel1.add(m1LinerLabel);
			optionPanel1.add(m1LinerTextField);
			optionPanel1.add(m3LinersLabel);
			optionPanel1.add(m3LinersTextField);
			optionPanel1.add(m2LinerLabel);
			optionPanel1.add(m2LinerTextField);
			optionPanel1.add(m3QcAreaLabel);
			optionPanel1.add(m3QcAreaTextField);
			optionPanel1.add(stolle11Label);
			optionPanel1.add(stolle11TextField);
			optionPanel1.add(stolle42Label);
			optionPanel1.add(stolle42TextField);
			optionPanel1.add(stolle22Label);
			optionPanel1.add(stolle22TextField);
			optionPanel1.add(m4BBalLabel);
			optionPanel1.add(m4B2BalTextField);
			optionPanel1.add(m3ABalLabel);
			optionPanel1.add(m3ABalTextField);
			optionPanel1.add(m4LinersLabel);
			optionPanel1.add(m4LinersTextField);
			optionPanel1.add(m1ABalLabel);
			optionPanel1.add(m1ABalTextField);
			optionPanel1.add(qCMod1Label);
			optionPanel1.add(qCMod1TextField);
			optionPanel1.add(ovecTesterlabel);
			optionPanel1.add(ovecTesterTextField);
			optionPanel1.add(stolle41Label);
			optionPanel1.add(stolle41TextField);
			optionPanel1.add(qCLabLabel);
			optionPanel1.add(qCLabTextField);
			optionPanel1.add(stolle43Label);
			optionPanel1.add(stolle43TextField);
			optionPanel1.add(bordenNo1Label);
			optionPanel1.add(bordenNo1TextField);
			optionPanel1.add(stolle44Label);
			optionPanel1.add(stolle44TextField);
			optionPanel1.add(m4QcAreaLabel);
			optionPanel1.add(m4QcAreaTextField);
			optionPanel1.add(balancer4BLabel);
			optionPanel1.add(balancer4BTextField);
			optionPanel1.add(stolle21Label);
			optionPanel1.add(stolle21TextField);
			optionPanel1.add(balancer4ALabel);
			optionPanel1.add(balancer4ATextField);
			optionPanel1.add(stolle33Label);
			optionPanel1.add(stolle33TextField);
			optionPanel1.add(formatecLabel);
			optionPanel1.add(formatecTextField);

		}

		// Searching
		else if (view == -2) {

			currentId = SQLiteConnection.SpoilageByDayGetHighestID();
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			model.setDate(yearInt, monthInt - 1, dayInt);
			model.setSelected(true);
			// add.setVisible(false);
			back.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);

			optionPanel1 = new JPanel(new GridLayout(17, 4));

			optionPanel1.add(dateLabel);
			optionPanel1.add(datePicker);
			optionPanel1.add(stolle12Label);
			optionPanel1.add(stolle12TextField);
			optionPanel1.add(optime1and2Label);
			optionPanel1.add(optime1and2TextField);
			optionPanel1.add(stolle31Label);
			optionPanel1.add(stolle31TextField);
			optionPanel1.add(optime3Label);
			optionPanel1.add(optime3TextField);
			optionPanel1.add(stolle32Label);
			optionPanel1.add(stolle32TextField);
			optionPanel1.add(m1BBalLabel);
			optionPanel1.add(m1BBalTextField);
			optionPanel1.add(m2BBalLabel);
			optionPanel1.add(m2BBalTextField);
			optionPanel1.add(m3BBalLabel);
			optionPanel1.add(m3BBalTextField);
			optionPanel1.add(m2ABalLabel);
			optionPanel1.add(m2ABalTextField);
			optionPanel1.add(m1LinerLabel);
			optionPanel1.add(m1LinerTextField);
			optionPanel1.add(m3LinersLabel);
			optionPanel1.add(m3LinersTextField);
			optionPanel1.add(m2LinerLabel);
			optionPanel1.add(m2LinerTextField);
			optionPanel1.add(m3QcAreaLabel);
			optionPanel1.add(m3QcAreaTextField);
			optionPanel1.add(stolle11Label);
			optionPanel1.add(stolle11TextField);
			optionPanel1.add(stolle42Label);
			optionPanel1.add(stolle42TextField);
			optionPanel1.add(stolle21Label);
			optionPanel1.add(stolle21TextField);
			optionPanel1.add(m4BBalLabel);
			optionPanel1.add(m4B2BalTextField);
			optionPanel1.add(m3ABalLabel);
			optionPanel1.add(m3ABalTextField);
			optionPanel1.add(m4LinersLabel);
			optionPanel1.add(m4LinersTextField);
			optionPanel1.add(m1ABalLabel);
			optionPanel1.add(m1ABalTextField);
			optionPanel1.add(qCMod1Label);
			optionPanel1.add(qCMod1TextField);
			optionPanel1.add(ovecTesterlabel);
			optionPanel1.add(ovecTesterTextField);
			optionPanel1.add(stolle41Label);
			optionPanel1.add(stolle41TextField);
			optionPanel1.add(qCLabLabel);
			optionPanel1.add(qCLabTextField);
			optionPanel1.add(stolle43Label);
			optionPanel1.add(stolle43TextField);
			optionPanel1.add(bordenNo1Label);
			optionPanel1.add(bordenNo1TextField);
			optionPanel1.add(stolle44Label);
			optionPanel1.add(stolle44TextField);
			optionPanel1.add(m4QcAreaLabel);
			optionPanel1.add(m4QcAreaTextField);
			optionPanel1.add(balancer4BLabel);
			optionPanel1.add(balancer4BTextField);
			optionPanel1.add(stolle22Label);
			optionPanel1.add(stolle22TextField);
			optionPanel1.add(balancer4ALabel);
			optionPanel1.add(balancer4ATextField);
			optionPanel1.add(stolle33Label);
			optionPanel1.add(stolle33TextField);
			optionPanel1.add(formatecLabel);
			optionPanel1.add(formatecTextField);

		}
		// Monthly
		else {

			if (monthInt == 1) {
				month = "January";
			}
			else if (monthInt == 2) {
				month = "February";
			}
			else if (monthInt == 3) {
				month = "March";
			}
			else if (monthInt == 4) {
				month = "April";
			}
			else if (monthInt == 5) {
				month = "May";
			}
			else if (monthInt == 6) {
				month = "June";
			}
			else if (monthInt == 7) {
				month = "July";
			}
			else if (monthInt == 8) {
				month = "August";
			}
			else if (monthInt == 9) {
				month = "September";
			}
			else if (monthInt == 10) {
				month = "October";
			}
			else if (monthInt == 11) {
				month = "November";
			}
			else if (monthInt == 12) {
				month = "December";
			}

			monthCombo.setSelectedItem(month);
			yearCombo.setSelectedItem(year);

			String monthSelected = (String) monthCombo.getSelectedItem();
			String yearSelected = (String) yearCombo.getSelectedItem();

			JPanel trendPanel = createTrendPanel(monthSelected, yearSelected);
			trendPanel.setSize(frame15.getWidth(), frame15.getHeight());

			JPanel monthComboPanel = new JPanel(new FlowLayout());
			monthComboPanel.add(monthCombo);
			monthComboPanel.add(yearCombo);
			monthComboPanel.add(goTable);

			optionPanel1 = new JPanel(new BorderLayout());
			frame15.setSize(1366, 768);
			frame15.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame15.setLocationRelativeTo(null);

			optionPanel1.add(monthComboPanel, BorderLayout.NORTH);
			optionPanel1.add(trendPanel, BorderLayout.CENTER);

			clearAll.setVisible(false);
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
		optionsPanel2.add(clearAll);
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

	public static JPanel createTrendPanel(String month, String year) throws SQLException {

		JPanel trendPanel = new JPanel(new BorderLayout());

		// LEFT PANEL
		JTable trendTableLeft = new JTable(37, 2);
		trendTableLeft.setPreferredSize(new Dimension(120, 820));

		for (int i = 0; i < 31; i++) {

			// trendTableLeft.getModel().setValueAt(" DATE  ", 0, 1);
			trendTableLeft.getColumnModel().getColumn(0).setPreferredWidth(25);
		}

		// Left Column Titles

		// Left Column Numbers
		trendTableLeft.getModel().setValueAt("Optime 1+2  ", 2, 1);
		trendTableLeft.getModel().setValueAt("Optime 3  ", 3, 1);
		trendTableLeft.getModel().setValueAt("M1 B Bal  ", 4, 1);
		trendTableLeft.getModel().setValueAt("M3 B Bal  ", 5, 1);
		trendTableLeft.getModel().setValueAt("M1 Liner  ", 6, 1);
		trendTableLeft.getModel().setValueAt("M2 Liner  ", 7, 1);
		trendTableLeft.getModel().setValueAt("Stolle 11  ", 8, 1);
		trendTableLeft.getModel().setValueAt("Stolle 22", 9, 1);
		trendTableLeft.getModel().setValueAt("M3 A Bal", 10, 1);
		trendTableLeft.getModel().setValueAt("M1 A Bal", 11, 1);
		trendTableLeft.getModel().setValueAt("Ovec Tester", 12, 1);
		trendTableLeft.getModel().setValueAt("QC Lab", 13, 1);
		trendTableLeft.getModel().setValueAt("Borden1", 14, 1);
		trendTableLeft.getModel().setValueAt("M4 QC Area", 15, 1);
		trendTableLeft.getModel().setValueAt("Stolle 21", 16, 1);
		trendTableLeft.getModel().setValueAt("Stolle 33", 17, 1);
		trendTableLeft.getModel().setValueAt("Stolle 12", 18, 1);
		trendTableLeft.getModel().setValueAt("Stolle 31", 19, 1);
		trendTableLeft.getModel().setValueAt("Stolle 32", 20, 1);
		trendTableLeft.getModel().setValueAt("M2 B Bal", 21, 1);
		trendTableLeft.getModel().setValueAt("M2 A Bal", 22, 1);
		trendTableLeft.getModel().setValueAt("M3 Liners", 23, 1);
		trendTableLeft.getModel().setValueAt("M3 QC Area", 24, 1);
		trendTableLeft.getModel().setValueAt("Stolle 42", 25, 1);
		trendTableLeft.getModel().setValueAt("M4 B2 Bal", 26, 1);
		trendTableLeft.getModel().setValueAt("M4 Liners", 27, 1);
		trendTableLeft.getModel().setValueAt("QC Mod 1", 28, 1);
		trendTableLeft.getModel().setValueAt("Stolle 41", 29, 1);
		trendTableLeft.getModel().setValueAt("Stolle 43", 30, 1);
		trendTableLeft.getModel().setValueAt("Stolle 44", 31, 1);
		trendTableLeft.getModel().setValueAt("Balance4B", 32, 1);
		trendTableLeft.getModel().setValueAt("Balance4A", 33, 1);
		trendTableLeft.getModel().setValueAt("Formatec", 34, 1);
		trendTableLeft.getModel().setValueAt("Totals", 36, 1);

		// ////////////////////////////////////////////////////

		// Left Column Numbers
		trendTableLeft.getModel().setValueAt("  01", 2, 0);
		trendTableLeft.getModel().setValueAt("  02", 3, 0);
		trendTableLeft.getModel().setValueAt("  03", 4, 0);
		trendTableLeft.getModel().setValueAt("  05", 5, 0);
		trendTableLeft.getModel().setValueAt("  06", 6, 0);
		trendTableLeft.getModel().setValueAt("  07", 7, 0);
		trendTableLeft.getModel().setValueAt("  09", 8, 0);
		trendTableLeft.getModel().setValueAt("  10", 9, 0);
		trendTableLeft.getModel().setValueAt("  12", 10, 0);
		trendTableLeft.getModel().setValueAt("  13", 11, 0);
		trendTableLeft.getModel().setValueAt("  17", 12, 0);
		trendTableLeft.getModel().setValueAt("  18", 13, 0);
		trendTableLeft.getModel().setValueAt("  36", 14, 0);
		trendTableLeft.getModel().setValueAt("  20", 15, 0);
		trendTableLeft.getModel().setValueAt("  24", 16, 0);
		trendTableLeft.getModel().setValueAt("  19", 17, 0);
		trendTableLeft.getModel().setValueAt("  27", 18, 0);
		trendTableLeft.getModel().setValueAt("  61", 19, 0);
		trendTableLeft.getModel().setValueAt("  54", 20, 0);
		trendTableLeft.getModel().setValueAt("  35", 21, 0);
		trendTableLeft.getModel().setValueAt("  33", 22, 0);
		trendTableLeft.getModel().setValueAt("  37", 23, 0);
		trendTableLeft.getModel().setValueAt("  41", 24, 0);
		trendTableLeft.getModel().setValueAt("  42", 25, 0);
		trendTableLeft.getModel().setValueAt("  63", 26, 0);
		trendTableLeft.getModel().setValueAt("  45", 27, 0);
		trendTableLeft.getModel().setValueAt("  46", 28, 0);
		trendTableLeft.getModel().setValueAt("  48", 29, 0);
		trendTableLeft.getModel().setValueAt("  49", 30, 0);
		trendTableLeft.getModel().setValueAt("  62", 31, 0);
		trendTableLeft.getModel().setValueAt("  51", 32, 0);
		trendTableLeft.getModel().setValueAt("  52", 33, 0);
		trendTableLeft.getModel().setValueAt("  53", 34, 0);

		// MIDDLE PANEL
		JTable trendTableMiddle = new JTable(37, 33);
		trendTableMiddle.setPreferredSize(new Dimension(1500, 600));

		// Create a [33] 3D Array of Results from function.

		int[][] array = new int[33][31];
		array = SQLiteConnection.SpoilageByDayTableFigures("01", month, year);

		// JTable Fill
		for (int i = 2; i < 35; i++) {
			for (int j = 1; j < 32; j++) {

				trendTableMiddle.getModel().setValueAt(array[i][j - 1], i, j);
			}

		}
		for (int i = 2; i < 35; i++) {

			// System.out.println("Number" + i + " : " + array[i][0]);
			trendTableMiddle.getModel().setValueAt(array[i][1], i, 2);

		}

		// Day Numbers Across Top
		for (int i = 0; i < 31; i++) {

			trendTableMiddle.getModel().setValueAt(i + 1, 0, i + 1);

		}

		// Total Numbers Across Bottom
		for (int i = 0; i < 31; i++) {

			int[] totals = new int[31];

			try {
				totals = SQLiteConnection.spoilageByDayGetDayTotals2(i + "", month, year);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("Totals : " + totals[i]);

			trendTableMiddle.getModel().setValueAt(totals[i], 36, i + 1);

		}

		// RIGHT PANEL

		JTable trendTableRight = new JTable(37, 3);
		trendTableRight.setPreferredSize(new Dimension(220, 820));
		trendTableRight.getColumnModel().getColumn(1).setPreferredWidth(40);

		// Total of all KGs in Month // Sum of totals[0] + totals[1] etc...

		int totalKGs = 0;

		trendTableRight.getModel().setValueAt("Ends", 0, 0);
		trendTableRight.getModel().setValueAt("KGs", 0, 1);
		trendTableRight.getModel().setValueAt("Total End", 0, 2);

		trendTableRight.getModel().setValueAt(408, 2, 1);
		trendTableRight.getModel().setValueAt(408, 3, 1);
		trendTableRight.getModel().setValueAt(408, 4, 1);
		trendTableRight.getModel().setValueAt(404, 5, 1);
		trendTableRight.getModel().setValueAt(404, 6, 1);
		trendTableRight.getModel().setValueAt(404, 7, 1);
		trendTableRight.getModel().setValueAt(361, 8, 1);
		trendTableRight.getModel().setValueAt(361, 9, 1);
		trendTableRight.getModel().setValueAt(408, 10, 1);
		trendTableRight.getModel().setValueAt(408, 11, 1);
		trendTableRight.getModel().setValueAt(361, 12, 1);
		trendTableRight.getModel().setValueAt(361, 13, 1);
		trendTableRight.getModel().setValueAt(361, 14, 1);
		trendTableRight.getModel().setValueAt(405, 15, 1);
		trendTableRight.getModel().setValueAt(361, 16, 1);
		trendTableRight.getModel().setValueAt(361, 17, 1);
		trendTableRight.getModel().setValueAt(361, 18, 1);
		trendTableRight.getModel().setValueAt(361, 19, 1);
		trendTableRight.getModel().setValueAt(361, 20, 1);
		trendTableRight.getModel().setValueAt(404, 21, 1);
		trendTableRight.getModel().setValueAt(408, 22, 1);
		trendTableRight.getModel().setValueAt(404, 23, 1);
		trendTableRight.getModel().setValueAt(361, 24, 1);
		trendTableRight.getModel().setValueAt(405, 25, 1);
		trendTableRight.getModel().setValueAt(460, 26, 1);
		trendTableRight.getModel().setValueAt(460, 27, 1);
		trendTableRight.getModel().setValueAt(361, 28, 1);
		trendTableRight.getModel().setValueAt(405, 29, 1);
		trendTableRight.getModel().setValueAt(405, 30, 1);
		trendTableRight.getModel().setValueAt(405, 31, 1);
		trendTableRight.getModel().setValueAt(460, 32, 1);
		trendTableRight.getModel().setValueAt(465, 33, 1);
		trendTableRight.getModel().setValueAt(465, 34, 1);

		try {
			trendTableRight.getModel().setValueAt(SQLiteConnection.totalKGs(month, year), 36, 0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// trendTableRight.getModel().setValueAt("Total Ends", 36, 1);

		trendTableRight.getModel().setValueAt("Total EndsXkg", 36, 2);

		// Create Row of Total Sums for Right hand side Total

		for (int i = 0; i < 33; i++) {

			int[] values;
			try {
				values = SQLiteConnection.SpoilageByDayGetMonthsTotal(month, year);
				trendTableRight.getModel().setValueAt(values[i], 2 + i, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// ////////////////////////////////////////////////////

		// Create Row of Total Sums * KG for Furthest Right hand side Total

		for (int i = 0; i < 33; i++) {

			try {
				values = SQLiteConnection.SpoilageByDayGetMonthsTotal(month, year);
				// trendTableRight.getModel().setValueAt(values[i], 2 + i, 2);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		int[] KGs = new int[33];

		for (int j = 0; j < 33; j++) {

			KGs[j] = (int) trendTableRight.getModel().getValueAt(j + 2, 1);

			// System.out.println(KGs[j]);
		}
		int total2 = 0;
		for (int i = 0; i < 33; i++) {

			total = Integer.valueOf(KGs[i]) * values[i];
			total2 = total2 + total;
			trendTableRight.getModel().setValueAt(total, 2 + i, 2);
		}

		trendTableRight.getModel().setValueAt(total2, 36, 2);

		// / Bottom Total 8x8 JTable

		JPanel totalsPanel = new JPanel(new BorderLayout());
		JTable totalsTable = new JTable(8, 8);

		totalsTable.getModel().setValueAt("Stolle & Sacoba", 0, 1);
		totalsTable.getModel().setValueAt("Balancers", 0, 2);
		totalsTable.getModel().setValueAt("Liners", 0, 3);
		totalsTable.getModel().setValueAt("Shell Presses", 0, 4);
		totalsTable.getModel().setValueAt("Border / Ovec", 0, 5);
		totalsTable.getModel().setValueAt("QC Areas", 0, 6);
		totalsTable.getModel().setValueAt("Total", 0, 7);

		totalsTable.getModel().setValueAt("Month To Date", 1, 0);
		totalsTable.getModel().setValueAt("Plant Production", 2, 0);
		totalsTable.getModel().setValueAt("As % of Production", 3, 0);
		totalsTable.getModel().setValueAt("Total Spoilage", 4, 0);
		totalsTable.getModel().setValueAt("As a % of Spoilage", 5, 0);

		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 1);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 2);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 3);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 4);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 5);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 6);
		totalsTable.getModel().setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 7);

		try {
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year), 1, 1);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year), 1, 2);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year), 1, 3);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year), 1, 4);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year), 1, 5);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year), 1, 6);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 1, 7);

			// Production Percentages

			DecimalFormat df = new DecimalFormat("###.##"); // <- // "###.###"
															// specifies
															// precision
			double y1 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x1 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year) + ""));
			double answer1 = (x1 / y1) * 100;
			System.out.println(" Y1 : " + y1 + " X1 " + x1);
			System.out.println("Answer1 : " + answer1);
			if (answer1 > 999999999) {
				answer1 = 0.00;
			}
			double answerRounded1 = Double.parseDouble(df.format(answer1).toString());
			totalsTable.getModel().setValueAt(answerRounded1, 3, 1);
			System.out.println("Stolle Percentage : " + answerRounded1);

			double y2 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x2 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year) + ""));
			double answer2 = (x2 / y2) * 100;
			if (answer2 > 999999999) {
				answer2 = 0.00;
			}
			System.out.println(x2 + " / " + y2);
			double answerRounded2 = Double.parseDouble(df.format(answer2).toString());
			totalsTable.getModel().setValueAt(answerRounded2, 3, 2);
			System.out.println("Stolle Percentage 2: " + answerRounded2);

			double y3 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x3 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year) + ""));
			double answer3 = (x3 / y3) * 100;
			if (answer3 > 999999999) {
				answer3 = 0.00;
			}
			System.out.println(x3 + " / " + y3);
			double answerRounded3 = Double.parseDouble(df.format(answer3).toString());
			totalsTable.getModel().setValueAt(answerRounded3, 3, 3);
			System.out.println("Stolle Percentage 3: " + answerRounded3);

			double y4 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x4 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year) + ""));
			double answer4 = (x4 / y4) * 100;
			if (answer4 > 999999999) {
				answer4 = 0.00;
			}
			System.out.println(x4 + " / " + y4);
			double answerRounded4 = Double.parseDouble(df.format(answer4).toString());
			totalsTable.getModel().setValueAt(answerRounded4, 3, 4);
			System.out.println("Stolle Percentage 4: " + answerRounded4);

			double y5 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x5 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year) + ""));
			double answer5 = (x5 / y5) * 100;
			if (answer5 > 999999999) {
				answer5 = 0.00;
			}
			System.out.println(x5 + " / " + y5);
			double answerRounded5 = Double.parseDouble(df.format(answer5).toString());
			totalsTable.getModel().setValueAt(answerRounded5, 3, 5);
			System.out.println("Stolle Percentage 5: " + answerRounded5);

			double y6 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
			double x6 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year) + ""));
			double answer6 = (x6 / y6) * 100;
			if (answer6 > 999999999) {
				answer6 = 0.00;
			}
			System.out.println(x2 + " / " + y2);
			double answerRounded6 = Double.parseDouble(df.format(answer6).toString());
			totalsTable.getModel().setValueAt(answerRounded6, 3, 6);
			System.out.println("Stolle Percentage 6: " + answerRounded6);

			// Spoilage Percentages

			double y11 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x11 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year) + ""));
			double answer11 = (x11 / y11) * 100;
			if (answer11 > 999999999) {
				answer11 = 0.00;
			}
			System.out.println(x11 + " / " + y11);
			double answerRounded11 = Double.parseDouble(df.format(answer11).toString());
			totalsTable.getModel().setValueAt(answerRounded11, 5, 1);
			System.out.println("Stolle Percentage : " + answerRounded11);

			double y12 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x12 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year) + ""));
			double answer12 = (x12 / y12) * 100;
			if (answer12 > 999999999) {
				answer12 = 0.00;
			}
			System.out.println(x12 + " / " + y12);
			double answerRounded12 = Double.parseDouble(df.format(answer12).toString());
			totalsTable.getModel().setValueAt(answerRounded12, 5, 2);
			System.out.println("Stolle Percentage : " + answerRounded12);

			double y13 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x13 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year) + ""));
			double answer13 = (x13 / y13) * 100;
			if (answer13 > 999999999) {
				answer13 = 0.00;
			}
			System.out.println(x13 + " / " + y13);
			double answerRounded13 = Double.parseDouble(df.format(answer13).toString());
			totalsTable.getModel().setValueAt(answerRounded13, 5, 3);
			System.out.println("Stolle Percentage : " + answerRounded13);

			double y14 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x14 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year) + ""));
			double answer14 = (x14 / y14) * 100;
			System.out.println(x14 + " / " + y14);
			if (answer14 > 999999999) {
				answer14 = 0.00;
			}
			double answerRounded14 = Double.parseDouble(df.format(answer14).toString());
			totalsTable.getModel().setValueAt(answerRounded14, 5, 4);
			System.out.println("Stolle Percentage : " + answerRounded14);

			double y15 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x15 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year) + ""));
			double answer15 = (x15 / y15) * 100;
			if (answer15 > 999999999) {
				answer15 = 0.00;
			}
			System.out.println(x15 + " / " + y15);
			double answerRounded15 = Double.parseDouble(df.format(answer15).toString());
			totalsTable.getModel().setValueAt(answerRounded15, 5, 5);
			System.out.println("Stolle Percentage : " + answerRounded15);

			double y16 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
			double x16 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year) + ""));
			double answer16 = (x16 / y16) * 100;
			if (answer16 > 999999999) {
				answer16 = 0.00;
			}
			System.out.println(x16 + " / " + y16);
			double answerRounded16 = Double.parseDouble(df.format(answer16).toString());
			totalsTable.getModel().setValueAt(answerRounded16, 5, 6);
			System.out.println("Stolle Percentage : " + answerRounded16);

			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 1);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 2);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 3);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 4);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 5);
			totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 6);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		totalsPanel.add(totalsTable);

		// /

		// ADD TO OUTER PANEL

		trendPanel.add(trendTableLeft, BorderLayout.WEST);
		trendPanel.add(trendTableMiddle, BorderLayout.CENTER);
		trendPanel.add(trendTableRight, BorderLayout.EAST);
		trendPanel.add(totalsPanel, BorderLayout.SOUTH);

		return trendPanel;
	}

	public static void createTrendFrame(String month, String year) throws SQLException {

		JFrame frameTrend = new JFrame();
		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" };
		JComboBox monthCombo = new JComboBox(monthArray);
		JComboBox yearCombo = new JComboBox(years);
		JButton goButton = new JButton("Go");
		goButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					createTrendFrame((String) monthCombo.getSelectedItem(), (String) yearCombo.getSelectedItem());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frameTrend.dispose();

			}
		});

		JPanel outerPanel = new JPanel(new BorderLayout());

		frameTrend.setSize(1366, 768);
		frameTrend.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameTrend.setLocationRelativeTo(null);

		monthCombo.setSelectedItem(month);
		yearCombo.setSelectedItem(year);

		JPanel trendPanel = createTrendPanel(month, year);
		outerPanel.add(trendPanel);

		JPanel monthComboPanel = new JPanel(new FlowLayout());
		monthComboPanel.setBackground(Color.GRAY);
		monthComboPanel.add(monthCombo);
		monthComboPanel.add(yearCombo);
		monthComboPanel.add(goButton);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.add(back);

		outerPanel.add(monthComboPanel, BorderLayout.NORTH);

		frameTrend.add(outerPanel);
		frameTrend.setVisible(true);

	}

}
