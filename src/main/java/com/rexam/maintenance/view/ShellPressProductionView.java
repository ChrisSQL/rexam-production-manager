package com.rexam.maintenance.view;

// TODO

// Fix Dates
// DocFilter

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.controller.MaintenanceDatabaseController;
import com.rexam.maintenance.dao.ShellPressProductionDAO;
import com.rexam.maintenance.model.ShellPressProductionModel;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ShellPressProductionView extends JFrame {

	private MaintenanceDatabaseController db;
	private ShellPressProductionDAO shellPressProductionDAO;

	private JButton print, importFromExcel, ExportToExcel, add, find, next, previous, delete, update, addNew,
			summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
	private JLabel dateLabel, dateLabel2;
	private static JTextField SP01JTextfield, Optime2JTextfield, Optime3JTextfield, FMI41JTextfield, FMI42JTextfield,
			Formatec04JTextfield;
	private JTextField SP01MonthlyJTextField, Optime2MonthlyJTextField, Optime3MonthlyJTextField,
			FMI41MonthlyTextField, FMI42MonthlyTextField, Formatec04MonthlyTextField;
	private int view, currentId;
	private Date selectedDate;
	private JComboBox monthCombo, yearCombo, sortTypesCombo, itemsCombo;
	private JFrame frameSummary, excelQueryFrame;
	private JFileChooser fileChooser;
	private String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	private String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
			"2025", "2026", "2027", "2028" };
	private JPanel summaryPanel, optionsPanel2, outerPanelSummary, outerPanel, innerPanel1, optionPanel1, comboPanel,
			buttonsPanel, commentsPanel, bottomPanel;
	private int yearInt, monthInt, dayInt;
	private Date date;
	private String modifiedDate, year, month, day, dateString;
	private DocFilter dF;
	private JScrollPane scrollPane;
	private JDateChooser dateChooser1, excelChooser1, excelChooser2;
	private JDateChooser chooser1;
	private JDateChooser chooser2;
	private JComboBox typeCombo;
	private String query, item;
	private JCheckBox datesCheck;
	

	public static void main(String[] args) throws SQLException {

		new ShellPressProductionView(1, -1);
	}

	public ShellPressProductionView(int idIn, int view) throws SQLException {
		
		setStyle();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		shellPressProductionDAO = (ShellPressProductionDAO) context.getBean("ShellPressProductionDAO");

		instantiateVariables();
		addActionListeners();
		createFrame(view);
		addFocusListeners();
		addDocFilters();

	}

	private void createFrame(int view) throws SQLException {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Shell Press");
		setSize(360, 350);
		setLocationRelativeTo(null);

		// Setup panels
		outerPanel = new JPanel(new BorderLayout());
		innerPanel1 = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Create Buttons , Labels, Checkboxes etc...

		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		// Set Todays Date.
		dateChooser1.setDate(new Date());
		dateChooser1.setLocale(getLocale());

		// Options Panel 1
		optionPanel1 = new JPanel(new GridLayout(7, 2));
		// optionPanel1.setBackground(Color.GRAY);

		// ComboPanelMonthly
		comboPanel = new JPanel(new FlowLayout());
		comboPanel.add(monthCombo);
		comboPanel.add(yearCombo);
		comboPanel.add(go);

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		buttonsPanel = new JPanel(new FlowLayout());
		buttonsPanel.add(previous);
		buttonsPanel.add(next);
		buttonsPanel.add(delete);

		// outerPanel.add(buttonsPanel, BorderLayout.NORTH);

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
			optionPanel1.add(dateChooser1);

			optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
			optionPanel1.add(SP01JTextfield);

			optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
			optionPanel1.add(Optime2JTextfield);

			optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
			optionPanel1.add(Optime3JTextfield);

			optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
			optionPanel1.add(FMI41JTextfield);

			optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
			optionPanel1.add(FMI42JTextfield);

			optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
			optionPanel1.add(Formatec04JTextfield);

		} // Searching
		else if (view == -2) {

			currentId = shellPressProductionDAO.shellPressProductionGetHighestID();
			// buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);

			add.setVisible(false);
			back.setVisible(false);
			addNew.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(dateChooser1);

			optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
			optionPanel1.add(SP01JTextfield);

			optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
			optionPanel1.add(Optime2JTextfield);

			optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
			optionPanel1.add(Optime3JTextfield);

			optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
			optionPanel1.add(FMI41JTextfield);

			optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
			optionPanel1.add(FMI42JTextfield);

			optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
			optionPanel1.add(Formatec04JTextfield);

		} // Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(6, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			// comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(new JLabel("SP01", SwingConstants.CENTER));
			optionPanel1.add(SP01MonthlyJTextField);

			optionPanel1.add(new JLabel("Optime 2", SwingConstants.CENTER));
			optionPanel1.add(Optime2MonthlyJTextField);

			optionPanel1.add(new JLabel("Optime 3", SwingConstants.CENTER));
			optionPanel1.add(Optime3MonthlyJTextField);

			optionPanel1.add(new JLabel("FMI 41", SwingConstants.CENTER));
			optionPanel1.add(FMI41MonthlyTextField);

			optionPanel1.add(new JLabel("FMI 42", SwingConstants.CENTER));
			optionPanel1.add(FMI42MonthlyTextField);

			optionPanel1.add(new JLabel("Formatec 04", SwingConstants.CENTER));
			optionPanel1.add(Formatec04MonthlyTextField);

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

		commentsPanel = new JPanel();

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

		bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.add(commentsPanel, BorderLayout.NORTH);
		bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		outerPanel.repaint();

		add(outerPanel);

		setVisible(true);

	}

	private void addDocFilters() {

		PlainDocument doc01 = (PlainDocument) SP01JTextfield.getDocument();
		doc01.setDocumentFilter(new MyIntFilter());

		PlainDocument doc1 = (PlainDocument) Optime2JTextfield.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		PlainDocument doc2 = (PlainDocument) Optime3JTextfield.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		PlainDocument doc3 = (PlainDocument) FMI41JTextfield.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());

		PlainDocument doc4 = (PlainDocument) FMI42JTextfield.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		PlainDocument doc5 = (PlainDocument) Formatec04JTextfield.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		PlainDocument doc02 = (PlainDocument) SP01MonthlyJTextField.getDocument();
		doc02.setDocumentFilter(new MyIntFilter());

		PlainDocument doc13 = (PlainDocument) Optime2MonthlyJTextField.getDocument();
		doc13.setDocumentFilter(new MyIntFilter());

		PlainDocument doc14 = (PlainDocument) Optime3MonthlyJTextField.getDocument();
		doc14.setDocumentFilter(new MyIntFilter());

		PlainDocument doc15 = (PlainDocument) FMI41MonthlyTextField.getDocument();
		doc15.setDocumentFilter(new MyIntFilter());

		PlainDocument doc16 = (PlainDocument) FMI42MonthlyTextField.getDocument();
		doc16.setDocumentFilter(new MyIntFilter());

		PlainDocument doc17 = (PlainDocument) Formatec04MonthlyTextField.getDocument();
		doc17.setDocumentFilter(new MyIntFilter());

	}

	private void addFocusListeners() {

		SP01JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		Optime2JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		FMI41JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		Optime3JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		FMI42JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		Formatec04JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});
	}

	private void instantiateVariables() {

		dF = new DocFilter();

		db = new MaintenanceDatabaseController();

		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		add = new JButton("Save Record");
		find = new JButton("Find Record");
		next = new JButton("Next record");
		previous = new JButton("Previous Record");
		delete = new JButton("Delete");
		update = new JButton("Update");
		addNew = new JButton("Add New");
		summary = new JButton("Summary");
		refresh = new JButton("Refresh");
		search = new JButton("Search");
		monthly = new JButton("Monthly");
		go = new JButton("Go");
		back = new JButton("Back");
		calculateTotal1 = new JButton("1");
		calculateTotal2 = new JButton("2");
		calculateTotal3 = new JButton("3");

		addNew = new JButton("New Entry Mode");
		refresh = new JButton("Refresh");
		print = new JButton("Print Report");
		ExportToExcel = new JButton("Export To Excel");
		importFromExcel = new JButton("Import From Viscan");

		SP01JTextfield = new JTextField("0");
		Optime2JTextfield = new JTextField("0");
		Optime3JTextfield = new JTextField("0");
		FMI41JTextfield = new JTextField("0");
		FMI42JTextfield = new JTextField("0");
		Formatec04JTextfield = new JTextField("0");
		SP01MonthlyJTextField = new JTextField();
		Optime2MonthlyJTextField = new JTextField();
		Optime3MonthlyJTextField = new JTextField();
		FMI41MonthlyTextField = new JTextField();
		FMI42MonthlyTextField = new JTextField();
		Formatec04MonthlyTextField = new JTextField();

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

		calculateTotal1 = new JButton("Calculate Total 1");
		calculateTotal2 = new JButton("Calculate Total 2");
		calculateTotal3 = new JButton("Calculate Total 3");

		dateChooser1 = new JDateChooser();

		SP01JTextfield = new JTextField();
		SP01JTextfield.setText("0");
		PlainDocument doc01 = (PlainDocument) SP01JTextfield.getDocument();
		// doc01.setDocumentFilter(new MyIntFilter());

		SP01JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		Optime2JTextfield = new JTextField();
		Optime2JTextfield.setText("0");
		PlainDocument doc1 = (PlainDocument) Optime2JTextfield.getDocument();
		// doc1.setDocumentFilter(new MyIntFilter());

		Optime2JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		Optime3JTextfield = new JTextField();
		Optime3JTextfield.setText("0");
		PlainDocument doc2 = (PlainDocument) Optime3JTextfield.getDocument();
		// doc2.setDocumentFilter(new MyIntFilter());
		Optime3JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});
		FMI41JTextfield = new JTextField();
		FMI41JTextfield.setText("0");
		PlainDocument doc3 = (PlainDocument) FMI41JTextfield.getDocument();
		// doc3.setDocumentFilter(new MyIntFilter());
		FMI41JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});
		FMI42JTextfield = new JTextField();
		FMI42JTextfield.setText("0");
		PlainDocument doc4 = (PlainDocument) FMI42JTextfield.getDocument();
		// doc4.setDocumentFilter(new MyIntFilter());
		FMI42JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});
		Formatec04JTextfield = new JTextField();
		Formatec04JTextfield.setText("0");
		PlainDocument doc5 = (PlainDocument) Formatec04JTextfield.getDocument();
		// doc5.setDocumentFilter(new MyIntFilter());
		Formatec04JTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				// calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				// calculateTotals();

			}
		});

		SP01MonthlyJTextField = new JTextField();
		PlainDocument doc02 = (PlainDocument) SP01MonthlyJTextField.getDocument();
		// doc02.setDocumentFilter(new MyIntFilter());
		Optime2MonthlyJTextField = new JTextField();
		PlainDocument doc13 = (PlainDocument) Optime2MonthlyJTextField.getDocument();
		// doc13.setDocumentFilter(new MyIntFilter());
		Optime3MonthlyJTextField = new JTextField();
		PlainDocument doc14 = (PlainDocument) Optime3MonthlyJTextField.getDocument();
		// doc14.setDocumentFilter(new MyIntFilter());
		FMI41MonthlyTextField = new JTextField();
		PlainDocument doc15 = (PlainDocument) FMI41MonthlyTextField.getDocument();
		// doc15.setDocumentFilter(new MyIntFilter());
		FMI42MonthlyTextField = new JTextField();
		PlainDocument doc16 = (PlainDocument) FMI42MonthlyTextField.getDocument();
		// doc16.setDocumentFilter(new MyIntFilter());

		Formatec04MonthlyTextField = new JTextField();
		PlainDocument doc17 = (PlainDocument) Formatec04MonthlyTextField.getDocument();
		// doc17.setDocumentFilter(new MyIntFilter());

	}

	private void addActionListeners() {

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ShellPressProductionModel pm = new ShellPressProductionModel();

				pm.setSp01(Integer.valueOf(SP01JTextfield.getText()));
				pm.setOptime2(Integer.valueOf(Optime2JTextfield.getText()));
				pm.setOptime3(Integer.valueOf(Optime3JTextfield.getText()));
				pm.setFmi41(Integer.valueOf(FMI41JTextfield.getText()));
				pm.setFmi42(Integer.valueOf(FMI42JTextfield.getText()));
				pm.setFormatec04(Integer.valueOf(Formatec04JTextfield.getText()));
				pm.setDate(dateChooser1.getDate());

				// Get int value of a JTextfield
				// calculateTotals();

				if (shellPressProductionDAO.shellPressProductionEntryExists(pm.getDate()) == true) {
					shellPressProductionDAO.shellPressProductionUpdate(pm);
				} else {
					shellPressProductionDAO.shellPressProductionInsert(pm);
				}

			}
		});

		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					new ShellPressProductionView(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		search.addActionListener(new ActionListener() {

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

		monthly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				// TODO Auto-generated method stub
				try {
					new ShellPressProductionView(1, -3);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ShellPressProductionModel pm = new ShellPressProductionModel();

				try {

					shellPressProductionDAO.shellPressProductionUpdate(pm);

					dispose();
					new ShellPressProductionView(1, -2);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				// Set ID
				try {
					selectedDate = dateChooser1.getDate();

					ShellPressProductionModel sp = shellPressProductionDAO
							.shellPressProductionReturnEntryByDate(selectedDate);

					if (sp.getDate() == null) {

						// db.infoBox("No Result. Have you selected a date?",
						// "");

					} else {

						// currentId = (int) total[0];

						SP01JTextfield.setText(sp.getSp01() + "");
						Optime2JTextfield.setText(sp.getOptime2() + "");
						Optime3JTextfield.setText(sp.getOptime3() + "");
						FMI41JTextfield.setText(sp.getFmi41() + "");
						FMI42JTextfield.setText(sp.getFmi42() + "");
						Formatec04JTextfield.setText(sp.getFormatec04() + "");

					}

					System.out.println("CurrentID " + currentId);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// currentId = (int)array[0];
				// Set Date
				// Send in

			}
		});

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// // TODO Auto-generated method stub
				// // Set ID
				// try {
				//
				// ShellPressProduction pm =
				// db.shellPressProductionGetNextEntryById(currentId);
				//
				// if (pm.getSp01() == 0 && pm.getFmi41() == 0) {
				//
				// // db.infoBox("No Next Result.", "");
				//
				// } else {
				//
				// model.setDate(pm.getDate().getYear(),
				// pm.getDate().getMonth(), pm.getDate().getDay());
				// model.setSelected(true);
				//
				// currentId = currentId + 1;
				//
				// SP01JTextfield.setText(pm.getSp01() + "");
				// Optime2JTextfield.setText(pm.getOptime2() + "");
				// Optime3JTextfield.setText(pm.getOptime3() + "");
				// FMI41JTextfield.setText(pm.getFmi41() + "");
				// FMI42JTextfield.setText(pm.getFmi42() + "");
				// Formatec04JTextfield.setText(pm.getFormatec04() + "");
				//
				// }
				//
				// System.out.println("CurrentID " + currentId);
				//
				// // Fill Boxes with results
				// // model.setDate(year2, month2, day2);
				// model.setSelected(true);
				//
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				// // currentId = (int)array[0];
				// // Set Date
				// // Send in

			}
		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JDialog.setDefaultLookAndFeelDecorated(true);
				int response = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					System.out.println("No button clicked");
				} else if (response == JOptionPane.YES_OPTION) {
					// Create Summary Screen
					frameSummary.dispose();
					dispose();
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}

			}
		});

		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// // TODO Auto-generated method stub
				// // Set ID
				// try {
				//
				// ShellPressProduction pm =
				// db.shellPressProductionGetNextEntryById(currentId);
				//
				// if (pm.getSp01() == 0 && pm.getFmi41() == 0) {
				//
				// // db.infoBox("No Next Result.", "");
				//
				// } else {
				//
				// model.setDate(pm.getDate().getYear(),
				// pm.getDate().getMonth(), pm.getDate().getDay());
				// model.setSelected(true);
				//
				// currentId = currentId - 1;
				//
				// SP01JTextfield.setText(pm.getSp01() + "");
				// Optime2JTextfield.setText(pm.getOptime2() + "");
				// Optime3JTextfield.setText(pm.getOptime3() + "");
				// FMI41JTextfield.setText(pm.getFmi41() + "");
				// FMI42JTextfield.setText(pm.getFmi42() + "");
				// Formatec04JTextfield.setText(pm.getFormatec04() + "");
				//
				// }
				//
				// System.out.println("CurrentID " + currentId);
				//
				// // Fill Boxes with results
				// // model.setDate(year2, month2, day2);
				// model.setSelected(true);
				//
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				// // currentId = (int)array[0];
				// // Set Date
				// // Send in

			}
		});

		calculateTotal1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				int W11Int = Integer.valueOf(Optime2JTextfield.getText());
				int W12Int = Integer.valueOf(Optime3JTextfield.getText());
				int W21Int = Integer.valueOf(FMI41JTextfield.getText());
				int W22Int = Integer.valueOf(FMI42JTextfield.getText());
				int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

			}
		});
		calculateTotal2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				int W11Int = Integer.valueOf(Optime2JTextfield.getText());
				int W12Int = Integer.valueOf(Optime3JTextfield.getText());
				int W21Int = Integer.valueOf(FMI41JTextfield.getText());
				int W22Int = Integer.valueOf(FMI42JTextfield.getText());
				int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

			}
		});
		calculateTotal3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				int W11Int = Integer.valueOf(Optime2JTextfield.getText());
				int W12Int = Integer.valueOf(Optime3JTextfield.getText());
				int W21Int = Integer.valueOf(FMI41JTextfield.getText());
				int W22Int = Integer.valueOf(FMI42JTextfield.getText());
				int W31Int = Integer.valueOf(Formatec04JTextfield.getText());

			}
		});

		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new ShellPressProductionView(1, -1);
				} catch (SQLException ex) {
					Logger.getLogger(ShellPressProductionModel.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

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

		ExportToExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				 exportToExcelOptions();

			}
		});

		importFromExcel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String month = monthCombo.getSelectedItem().toString();
				String year = yearCombo.getSelectedItem().toString();
				
				int[] sums = new int[6];

				System.out.print("Month : " + month);
				System.out.print("Year : " + year);

				sums = shellPressProductionDAO.shellPressProductionCalculateTotalsByMonth(month, year);

				SP01MonthlyJTextField.setText(sums[0]+"");
				Optime2MonthlyJTextField.setText(sums[1]+"");
				Optime3MonthlyJTextField.setText(sums[2]+"");
				FMI41MonthlyTextField.setText(sums[3]+"");
				FMI42MonthlyTextField.setText(sums[4]+"");
				Formatec04MonthlyTextField.setText(sums[5]+"");

			}
		});

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				// TODO Auto-generated method stub
				try {
					new ShellPressProductionView(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		dateChooser1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {

				if ("date".equals(e.getPropertyName())) {

					System.out.println(e.getPropertyName() + ": " + e.getNewValue());

					try {
						ShellPressProductionModel pm = shellPressProductionDAO.shellPressProductionReturnEntryByDate((Date) e.getNewValue());
						setShellPressProductionToID(pm.getId());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

	}

	public void createSummaryScreen() throws SQLException {

		// Outer Frame
		frameSummary = new JFrame("Shell Production Report");
		frameSummary.setSize(1300, 700);
		frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		outerPanelSummary = new JPanel(new BorderLayout());
		outerPanelSummary.setBorder(new EmptyBorder(5, 5, 5, 5));

		optionsPanel2 = new JPanel(new FlowLayout());

		optionsPanel2.add(addNew);
		optionsPanel2.add(summary);
		optionsPanel2.add(refresh);
		optionsPanel2.add(ExportToExcel);
		optionsPanel2.add(importFromExcel);

		summaryPanel = shellPressProductionSummaryTable(1);
		scrollPane = new JScrollPane(summaryPanel);

		optionsPanel2.setBackground(Color.GRAY);

		outerPanelSummary.add(scrollPane, BorderLayout.CENTER);
		outerPanelSummary.add(optionsPanel2, BorderLayout.SOUTH);
		summary.setVisible(false);
		frameSummary.add(outerPanelSummary);
		frameSummary.setVisible(true);

	}

	public JPanel shellPressProductionSummaryTable(int in) throws SQLException {

		JPanel outerPanel = new JPanel(new BorderLayout());

		Connection conn = MaintenanceDatabaseController.databaseConnect();
		Statement stmt = conn.createStatement();
		stmt.setQueryTimeout(10);

		PreparedStatement psmt = conn.prepareStatement(
				"SELECT Date, SP01, SP02, SP03, FMI41, FMI42, SP04, ID FROM MainShellPressProduction ORDER BY Date DESC");
		psmt.setQueryTimeout(10);
		ResultSet rs = psmt.executeQuery();
		DefaultTableModel dm = new DefaultTableModel();

		// get column names
		int len = rs.getMetaData().getColumnCount();
		System.out.println("LEN : " + len);
		Vector cols = new Vector(len);
		for (int i = 1; i <= len; i++) {// Note starting at 1

			cols.add(rs.getMetaData().getColumnName(i));
			System.out.println(rs.getMetaData().getColumnName(i));

		}

		// Add Data
		Vector data = new Vector();

		while (rs.next()) {

			Vector row = new Vector(len);

			int q1 = rs.getInt(2);
			String q2 = String.format("%,d", q1);

			int q3 = rs.getInt(3);
			String q4 = String.format("%,d", q3);

			int q5 = rs.getInt(4);
			String q6 = String.format("%,d", q5);

			int q7 = rs.getInt(5);
			String q8 = String.format("%,d", q7);

			int q9 = rs.getInt(6);
			String q10 = String.format("%,d", q9);

			int q11 = rs.getInt(7);
			String q12 = String.format("%,d", q11);

			row.add(rs.getString(1));
			row.add(q2);
			row.add(q4);
			row.add(q6);
			row.add(q8);
			row.add(q10);
			row.add(q12);
			row.add(rs.getInt(8));

			data.add(row);
		}

		// Now create the table
		DefaultTableModel model = new DefaultTableModel(data, cols);

		final JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.getColumnModel().getColumn(7).setMaxWidth(40);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();

					int row = target.getSelectedRow() + 1;
					// int column = target.getSelectedColumn();

					// System.out.println("Clicked : " + row );
					System.out.println(table.getValueAt(table.getSelectedRow(), 7).toString());

					String idString = table.getValueAt(table.getSelectedRow(), 7).toString();
					int id = Integer.valueOf(idString);
					ShellPressProductionModel linersAndShells;
					try {
						ShellPressProductionView shellPressProduction = new ShellPressProductionView(1, -2);
						setShellPressProductionToID(id);

					} catch (SQLException ex) {
						// Logger.getLogger(db.class.getName()).log(Level.SEVERE,
						// null, ex);
					}

				}
			}
		});

		JTableHeader header = table.getTableHeader();
		outerPanel.add(header, BorderLayout.NORTH);
		outerPanel.add(table, BorderLayout.CENTER);

		psmt.close();
		stmt.close();
		conn.close();

		return outerPanel;

	}

	public void setShellPressProductionToID(int idIn) {

		try {

			int highestID = idIn;

			currentId = idIn;

			ShellPressProductionModel pm = shellPressProductionDAO.shellPressProductionReturnEntryByID(currentId);

			if (pm.equals(null)) {

				System.out.println("No Result.");

			} else {

				System.out.println("Result.");

				SP01JTextfield.setText(pm.getSp01() + "");
				Optime2JTextfield.setText(pm.getOptime2() + "");
				Optime3JTextfield.setText(pm.getOptime3() + "");
				FMI41JTextfield.setText(pm.getFmi41() + "");
				FMI42JTextfield.setText(pm.getFmi42() + "");
				Formatec04JTextfield.setText(pm.getFormatec04() + "");

			}

			System.out.println("ShellPress changed to ID :  " + currentId);

			// Fill Boxes with results

			currentId = highestID;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public void exportToExcelOptions() {

        String[] typesArray = {"Shell Press Production",};
        typeCombo = new JComboBox(typesArray);
        datesCheck = new JCheckBox();

        excelChooser1 = new JDateChooser(new Date());
        excelChooser2 = new JDateChooser(new Date());

        query = "";

        String[] sortTypesArray = {"Date"};
        sortTypesCombo = new JComboBox(sortTypesArray);

        String[] itemsArray = typesArray;
        itemsCombo = new JComboBox(itemsArray);

        JButton export, cancel;

        cancel = new JButton("Cancel");
        export = new JButton("Export");

        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                item = typeCombo.getSelectedItem() + "";
             

                query = "SELECT * FROM MainShellPressProduction";
//                		+ ""
//                		+ " WHERE Date BETWEEN \'" + excelChooser1.getDate() + "\' AND \'" + excelChooser2.getDate() + "\' ORDER BY " + sortTypesCombo.getSelectedItem() + ";";

                System.out.println(query);
                shellPressProductionDAO.generateExcelFile(item, query);

            }
        });

        excelQueryFrame = new JFrame("Export to Excel");
        excelQueryFrame.setSize(350, 230);
        excelQueryFrame.setLocationRelativeTo(null);

        JPanel outerPanel = new JPanel(new GridLayout(5, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        outerPanel.add(new JLabel("Type : ", JLabel.CENTER));
        outerPanel.add(typeCombo);

        outerPanel.add(new JLabel("Start Date : ", JLabel.CENTER));
        outerPanel.add(excelChooser1);

        outerPanel.add(new JLabel("End Date : ", JLabel.CENTER));
        outerPanel.add(excelChooser2);

        outerPanel.add(new JLabel("Sort By : ", JLabel.CENTER));
        outerPanel.add(sortTypesCombo);

        outerPanel.add(cancel);
        outerPanel.add(export);

        excelQueryFrame.add(outerPanel);
        excelQueryFrame.setVisible(true);

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
