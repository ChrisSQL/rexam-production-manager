package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.binentry.dao.EndCountsDAO;
import com.rexam.production.dao.OpTimeDAO;
import com.rexam.production.model.OpTimeModel;
import com.toedter.calendar.JDateChooser;

public class OpTimeView extends JFrame{

	private JButton add, update, find, next, previous, addNew, search, exportToExcel, newEntry, print, refresh, summary,
			delete;
	private JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
			productionLabel, commentsLabel;
	private JTextField dateTextField, pressSpeedTextField, productionTextField;
	private JTextArea commentsTextArea;
	private Date todaysDate, selectedDate;
	private int view, currentId, year, month, day;
	private JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo,
			shellTypeCombo;
	JDateChooser chooser1, chooser2;
	OpTimeDAO opTimeDAO;
	OpTimeModel om;

	public static void main(String[] args) {

		new OpTimeView(1, 1);

	}

	public OpTimeView(int idIn, int view) {
				
		om = new OpTimeModel();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		opTimeDAO = (OpTimeDAO) context.getBean("OpTimeDAO");

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
		
		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());

		// Fill Combos From Database
		shiftCombo = new JComboBox();
		crewCombo = new JComboBox();
		optimeNumberCombo = new JComboBox();
		shellTypeCombo = new JComboBox();
		operatorCombo = new JComboBox();
		packerCombo = new JComboBox();
		qcCombo = new JComboBox();
		pressCombo = new JComboBox();
		fillCombos();
		// ////////////////////////////

		JFrame frame3 = new JFrame();
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel();
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Optime Data Entry");
		setSize(360, 480);
		setLocationRelativeTo(null);

		// System.out.println("Screen");
		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...
		currentId = 0;
		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		shiftLabel = new JLabel("Shift : ", SwingConstants.CENTER);
		crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
		operatorLabel = new JLabel("Operator : ", SwingConstants.CENTER);
		optimeNumberLabel = new JLabel("Optime Number : ", SwingConstants.CENTER);
		pressSpeedLabel = new JLabel("Press Speed : ", SwingConstants.CENTER);
		shellTypeLabel = new JLabel("Shell Type : ", SwingConstants.CENTER);
		productionLabel = new JLabel("Production : ", SwingConstants.CENTER);
		commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);		

		add = new JButton("Save Record");
		update = new JButton("Update Record");
		find = new JButton("Find Record");		
		next = new JButton("Next Record");		
		previous = new JButton("Previous Record");		
		search = new JButton("Search Mode");		
		summary = new JButton("Summary");		
		delete = new JButton("Delete");
		

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		buttonsPanel.add(previous);
		buttonsPanel.add(next);
		buttonsPanel.add(delete);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		// get todays date as Three ints - Format YYYY -
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month) - 1;
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);		

		pressSpeedTextField = new JTextField();
		pressSpeedTextField.setText("0");
		PlainDocument doc1 = (PlainDocument) pressSpeedTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		JPanel optionPanel1 = new JPanel(new GridLayout(8, 2));
		// optionPanel1.setBackground(Color.GRAY);

		optionPanel1.add(dateLabel);
		optionPanel1.add(chooser1);

		optionPanel1.add(shiftLabel);
		optionPanel1.add(shiftCombo);

		optionPanel1.add(crewLabel);
		optionPanel1.add(crewCombo);

		optionPanel1.add(operatorLabel);
		optionPanel1.add(operatorCombo);

		optionPanel1.add(optimeNumberLabel);
		optionPanel1.add(optimeNumberCombo);

		optionPanel1.add(pressSpeedLabel);
		optionPanel1.add(pressSpeedTextField);

		optionPanel1.add(shellTypeLabel);
		optionPanel1.add(shellTypeCombo);

		JPanel commentsPanel = new JPanel();

		productionTextField = new JTextField();
		productionTextField.setText("0");
		PlainDocument doc2 = (PlainDocument) productionTextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		commentsTextArea = new JTextArea(7, 30);
		commentsTextArea.setText("NA");
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setWrapStyleWord(true);

		optionPanel1.add(productionLabel);
		optionPanel1.add(productionTextField);

		commentsPanel.add(commentsLabel);
		commentsPanel.add(commentsTextArea);

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				new OpTimeView(1, 1);

			}
		});
		// addNew.setBackground(Color.GREEN);

		// Adding
		if (view == 1) {

			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);
			summary.setVisible(false);
			delete.setVisible(false);

		} // Searching
		else if (view == 2) {

			addNew.setVisible(false);

			currentId = opTimeDAO.OPTimeGetHighestID() + 1;
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);			
			add.setVisible(false);

		} else if (view == 3) {

			createSummaryScreen();

		}

		outerPanel.add(optionPanel1, BorderLayout.CENTER);

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		optionsPanel2.add(summary);

		optionsPanel2.add(addNew);
	//	optionsPanel2.add(search);
		optionsPanel2.add(update);
		optionsPanel2.add(add);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) chooser1.getDate();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
				// int crewInt =
				// Integer.parseInt((String)crewCombo.getSelectedItem());
				int optimeNumberInt = Integer.parseInt((String) optimeNumberCombo.getSelectedItem());
				int shiftInt = Integer.parseInt((String) shiftCombo.getSelectedItem());
				int pressSpeedInt = Integer.parseInt(pressSpeedTextField.getText());
				int productionInt = Integer.parseInt(productionTextField.getText());

				opTimeDAO.OPTimeUpdate(om);

				dispose();
                //	frameSummary.dispose();
				createSummaryScreen();

				// TODO Auto-generated method stub
			}
		});
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = chooser1.getDate();

				if (opTimeDAO.OptimeEntryExists(om)) {
					opTimeDAO.OPTimeUpdate(om);

					dispose();
					createSummaryScreen();
				} else {
					
					selectedDate = (Date) chooser1.getDate();
					om.setDate(selectedDate);
					
					
					om.setShift((Integer)(Integer.valueOf(shiftCombo.getSelectedItem()+"")));
					om.setCrew(crewCombo.getSelectedItem()+"");
					om.setOperator(operatorCombo.getSelectedItem()+"");
					om.setOptimeNumber((Integer)(Integer.valueOf(optimeNumberCombo.getSelectedItem()+"")));
					om.setPressSpeed((Integer)(Integer.valueOf(pressSpeedTextField.getText()+"")));
					om.setShellType(shellTypeCombo.getSelectedItem()+"");
					om.setProduction((Integer)(Integer.valueOf(productionTextField.getText()+"")));
					om.setComment(commentsTextArea.getText());
					

					opTimeDAO.OPTimeInsert(om);

					dispose();
					createSummaryScreen();
				}

				
			}
		});

		optionsPanel2.setBackground(Color.GRAY);

		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.add(commentsPanel, BorderLayout.NORTH);
		bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		outerPanel.repaint();
		add(outerPanel);

		setVisible(true);

		// opTimeDAO.AnalyticsUpdate("OpTimeView");

	}

	private void createSummaryScreen() {
		// TODO Auto-generated method stub
		
	}

	private void fillCombos() {
		
		JComboBox [] oa =  opTimeDAO.fillCombos(new JComboBox[6]);	
		
		operatorCombo.addItem("NA");
		
		operatorCombo = oa[0];			
		pressCombo = oa[1];
		crewCombo = oa[2];
		shiftCombo = oa[3];
		optimeNumberCombo = oa[4];
		shellTypeCombo = oa[5];
				
		
	}

}
