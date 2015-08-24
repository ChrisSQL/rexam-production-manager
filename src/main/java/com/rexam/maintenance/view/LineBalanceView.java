package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
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
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.dao.LineBalanceDAO;
import com.rexam.maintenance.dao.LinerMaintenanceDAO;
import com.rexam.maintenance.model.LineBalanceModel;
import com.toedter.calendar.JDateChooser;

public class LineBalanceView  extends JFrame {
	
	JComboBox monthCombo, yearCombo;
	JDateChooser chooser1, chooser2;
	LineBalanceDAO lineBalanceDAO;
	LineBalanceModel lb;
	
	private  JButton add, find, next, previous, update, addNew, summary, refresh, search, monthly, go, back, calculateTotal1, calculateTotal2, calculateTotal3;
    JLabel dateLabel, dateLabel2;
    private JTextField Mod123ShellPressJTextfield, Mod123LinersJTextfield, Mod123ConversionJTextfield, 
    Mod4ShellPressJTextfield, Mod4LinersJTextfield, Mod4ConversionJTextfield, Mod123UnlinedJTextfield, 
    Mod123linedJTextfield, Mod4UnlinedJTextfield, Mod4linedJTextfield, totalJTextField, Mod123ShellPressMonthly, Mod123LinersMonthly, Mod123ConversionMonthly, 
    Mod4ShellPressMonthly, Mod4LinersMonthly, Mod4ConversionMonthly, Mod123UnlinedMonthly, 
    Mod123linedMonthly, Mod4UnlinedMonthly, Mod4linedMonthly, totalMonthly;
    private int view, currentId;

	public static void main(String[] args) throws SQLException {

		new LineBalanceView(1, -1);

	}

	public LineBalanceView(int idIn, int view){
		
		setStyle();
		
		lb = new LineBalanceModel();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		lineBalanceDAO = (LineBalanceDAO) context.getBean("LineBalanceDAO");

		// frame15.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel1 = new JPanel(new BorderLayout());
		
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Liner Balance");
		setSize(360, 650);

		setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...


		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] years = { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024",
				"2025", "2026", "2027", "2028" };
		monthCombo = new JComboBox(monthArray);
		yearCombo = new JComboBox(years);

		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());

		Mod123ShellPressJTextfield = new JTextField();
		Mod123ShellPressJTextfield.setEditable(false);
		Mod123ShellPressJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc01 = (PlainDocument) Mod123ShellPressJTextfield.getDocument();
		doc01.setDocumentFilter(new MyIntFilter());

		Mod123LinersJTextfield = new JTextField();
		Mod123LinersJTextfield.setEditable(false);
		Mod123LinersJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc1 = (PlainDocument) Mod123LinersJTextfield.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		Mod123ConversionJTextfield = new JTextField();
		Mod123ConversionJTextfield.setEditable(false);
		Mod123ConversionJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc2 = (PlainDocument) Mod123ConversionJTextfield.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		Mod4ShellPressJTextfield = new JTextField();
		Mod4ShellPressJTextfield.setEditable(false);
		Mod4ShellPressJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc3 = (PlainDocument) Mod4ShellPressJTextfield.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());

		Mod4LinersJTextfield = new JTextField();
		Mod4LinersJTextfield.setEditable(false);
		Mod4LinersJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc4 = (PlainDocument) Mod4LinersJTextfield.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		Mod4ConversionJTextfield = new JTextField();
		Mod4ConversionJTextfield.setEditable(false);
		Mod4ConversionJTextfield.setBackground(new Color(255, 255, 123));
		PlainDocument doc5 = (PlainDocument) Mod4ConversionJTextfield.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		Mod123UnlinedJTextfield = new JTextField();
		Mod123UnlinedJTextfield.setText("0");
		PlainDocument doc5A = (PlainDocument) Mod123UnlinedJTextfield.getDocument();
		doc5A.setDocumentFilter(new MyIntFilter());
		Mod123UnlinedJTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				LineBalanceView.calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				LineBalanceView.calculateTotals();

			}
		});

		Mod123linedJTextfield = new JTextField();
		Mod123linedJTextfield.setText("0");
		PlainDocument doc5B = (PlainDocument) Mod123linedJTextfield.getDocument();
		doc5B.setDocumentFilter(new MyIntFilter());
		Mod123linedJTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				LineBalanceView.calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				LineBalanceView.calculateTotals();

			}
		});

		Mod4UnlinedJTextfield = new JTextField();
		Mod4UnlinedJTextfield.setText("0");
		PlainDocument doc5C = (PlainDocument) Mod4UnlinedJTextfield.getDocument();
		doc5C.setDocumentFilter(new MyIntFilter());
		Mod4UnlinedJTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				LineBalanceView.calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				LineBalanceView.calculateTotals();

			}
		});

		Mod4linedJTextfield = new JTextField();
		Mod4linedJTextfield.setText("0");
		PlainDocument doc5D = (PlainDocument) Mod4linedJTextfield.getDocument();
		doc5D.setDocumentFilter(new MyIntFilter());
		Mod4linedJTextfield.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				LineBalanceView.calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				LineBalanceView.calculateTotals();

			}
		});

		totalJTextField = new JTextField();
		totalJTextField.setText("0");
		PlainDocument doc5E = (PlainDocument) totalJTextField.getDocument();
		doc5E.setDocumentFilter(new MyIntFilter());
		totalJTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				LineBalanceView.calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				LineBalanceView.calculateTotals();

			}
		});

		Mod123ShellPressMonthly = new JTextField();
		PlainDocument doc02 = (PlainDocument) Mod123ShellPressMonthly.getDocument();
		doc02.setDocumentFilter(new MyIntFilter());
		Mod123LinersMonthly = new JTextField();
		PlainDocument doc13 = (PlainDocument) Mod123LinersMonthly.getDocument();
		doc13.setDocumentFilter(new MyIntFilter());
		Mod123ConversionMonthly = new JTextField();
		PlainDocument doc14 = (PlainDocument) Mod123ConversionMonthly.getDocument();
		doc14.setDocumentFilter(new MyIntFilter());
		Mod4ShellPressMonthly = new JTextField();
		PlainDocument doc15 = (PlainDocument) Mod4ShellPressMonthly.getDocument();
		doc15.setDocumentFilter(new MyIntFilter());
		Mod4LinersMonthly = new JTextField();
		PlainDocument doc16 = (PlainDocument) Mod4LinersMonthly.getDocument();
		doc16.setDocumentFilter(new MyIntFilter());

		Mod4ConversionMonthly = new JTextField();
		PlainDocument doc17A = (PlainDocument) Mod4ConversionMonthly.getDocument();
		doc17A.setDocumentFilter(new MyIntFilter());
		Mod123UnlinedMonthly = new JTextField();
		PlainDocument doc17B = (PlainDocument) Mod123UnlinedMonthly.getDocument();
		doc17B.setDocumentFilter(new MyIntFilter());
		Mod123linedMonthly = new JTextField();
		PlainDocument doc17C = (PlainDocument) Mod123linedMonthly.getDocument();
		doc17C.setDocumentFilter(new MyIntFilter());

		Mod4UnlinedMonthly = new JTextField();
		PlainDocument doc17D = (PlainDocument) Mod4UnlinedMonthly.getDocument();
		doc17D.setDocumentFilter(new MyIntFilter());
		Mod4linedMonthly = new JTextField();
		PlainDocument doc17E = (PlainDocument) Mod4linedMonthly.getDocument();
		doc17E.setDocumentFilter(new MyIntFilter());
		totalMonthly = new JTextField();
		PlainDocument doc17F = (PlainDocument) totalMonthly.getDocument();
		doc17F.setDocumentFilter(new MyIntFilter());

	//	setMod1234ToDate(modifiedDate);

		go = new JButton("Go");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

	//			setMod1234ToMonth();

			}
		});

		back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LineBalanceView(1, -1);
				
				dispose();

			}
		});

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotals();

//				selectedDate = (Date) datePicker.getModel().getValue();
//				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
				
				lb.setDate(chooser1.getDate());
				lb.setMod123Unlined(Integer.valueOf(Mod123UnlinedJTextfield.getText()));
				lb.setMod123Lined(Integer.valueOf(Mod123linedJTextfield.getText()));
				lb.setMod4Lined(Integer.valueOf(Mod4linedJTextfield.getText()));
				lb.setMod4Unlined(Integer.valueOf(Mod4UnlinedJTextfield.getText()));

				lineBalanceDAO.MaintenanceLineBalanceInsert(lb);

				// TODO Auto-generated method stub
				dispose();
				createSummaryScreen();

			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LineBalanceView(1, -1);
				dispose();

			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				createSummaryScreen();
				dispose();

			}
		});

		monthly = new JButton("Monthly");
		monthly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LineBalanceView(1, -3);
				dispose();

			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				lb.setDate(chooser1.getDate());
				lb.setMod123Unlined(Integer.valueOf(Mod123UnlinedJTextfield.getText()));
				lb.setMod123Lined(Integer.valueOf(Mod123linedJTextfield.getText()));
				lb.setMod4Lined(Integer.valueOf(Mod4linedJTextfield.getText()));
				lb.setMod4Unlined(Integer.valueOf(Mod4UnlinedJTextfield.getText()));

				lineBalanceDAO.MaintenanceLineBalanceUpdate(lb);

				dispose();
				createSummaryScreen();

				// TODO Auto-generated method stub
			}
		});

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				createSummaryScreen();

			}
		});

		find = new JButton("Find Record");

		next = new JButton("Next Record");
		previous = new JButton("Previous Record");

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		dateLabel2 = new JLabel("Date : ", SwingConstants.CENTER);

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		JPanel optionPanel1 = new JPanel(new GridLayout(16, 2));
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
			summary.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(chooser1);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod123ShellPressJTextfield);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod123LinersJTextfield);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod123ConversionJTextfield);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 4", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod4ShellPressJTextfield);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod4LinersJTextfield);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod4ConversionJTextfield);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod123UnlinedJTextfield);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod123linedJTextfield);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 4", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod4UnlinedJTextfield);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod4linedJTextfield);

			optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
			optionPanel1.add(totalJTextField);

		} // Searching
		else if (view == -2) {

		//	currentId = lineBalanceDAO.MaintenanceLineBalanceGetHighestID();
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);
			back.setVisible(false);
			addNew.setVisible(false);

			optionPanel1.add(dateLabel);
			optionPanel1.add(chooser1);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod123ShellPressJTextfield);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod123LinersJTextfield);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod123ConversionJTextfield);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod4ShellPressJTextfield);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod4LinersJTextfield);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod4ConversionJTextfield);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 1-2-3 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod123UnlinedJTextfield);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod123linedJTextfield);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod4UnlinedJTextfield);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod4linedJTextfield);

			optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
			optionPanel1.add(totalJTextField);
		} // Monthly
		else {

			optionPanel1 = new JPanel(new GridLayout(15, 2));

			outerPanel.add(comboPanel, BorderLayout.NORTH);
			comboPanel.setBackground(Color.GRAY);

			optionPanel1.add(new JLabel(" ", SwingConstants.CENTER));
			optionPanel1.add(new JLabel("Mod 1-2-3", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod123ShellPressMonthly);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod123LinersMonthly);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod123ConversionMonthly);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("Shell Press", SwingConstants.CENTER));
			optionPanel1.add(Mod4ShellPressMonthly);

			optionPanel1.add(new JLabel("Liners", SwingConstants.CENTER));
			optionPanel1.add(Mod4LinersMonthly);

			optionPanel1.add(new JLabel("Conversion", SwingConstants.CENTER));
			optionPanel1.add(Mod4ConversionMonthly);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 1-2-3 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod123UnlinedMonthly);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod123linedMonthly);

			optionPanel1.add(new JLabel("", SwingConstants.CENTER));
			optionPanel1.add(new JLabel(" Mod 4 ", SwingConstants.CENTER));

			optionPanel1.add(new JLabel("UnLined", SwingConstants.CENTER));
			optionPanel1.add(Mod4UnlinedMonthly);

			optionPanel1.add(new JLabel("Lined", SwingConstants.CENTER));
			optionPanel1.add(Mod4linedMonthly);

			optionPanel1.add(new JLabel("Total", SwingConstants.CENTER));
			optionPanel1.add(totalMonthly);

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

	//	SQLiteConnection.AnalyticsUpdate("LineBalance");

	}

	protected void createSummaryScreen() {
		// TODO Auto-generated method stub
		
	}

	protected static void calculateTotals() {
		// TODO Auto-generated method stub
		
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



