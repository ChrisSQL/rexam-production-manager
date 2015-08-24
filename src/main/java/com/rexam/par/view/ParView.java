package com.rexam.par.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.par.dao.ParDAO;
import com.rexam.par.model.ParModel;
import com.toedter.calendar.JDateChooser;

public class ParView extends JFrame {

	private ParModel pm;
	static JLabel formLabel, dateLabel, crewLabel, codeLabel, machineCodeLabel, shiftManagerLabel, technicianLabel,
			MachineOperatorLabel, engineerLabel, blankLabel1, blankLabel2;
	private JTextField formJTextField;
	private JComboBox crewCombo, codeCombo, machineCodeCombo, shiftManagerCombo, technicianCombo, MachineOperatorCombo,
			engineerCombo;

	// BOTTOM PANEL LEFT
	static JLabel timeStartedLabel, timeInToolRoomLabel, timeFinishedLabel, signedLabel, datelabel, statusLabel,
			blankLabel3, blankLabel4, blankLabel5, blankLabel6, blankLabel7, blankLabel8, blankLabel9, blankLabel10;
	private JTextField timeStartedJTextField, timeInToolRoomJTextField, timeFinishedJTextField;
	private String[] signedArray = { "NA", "Henry O'Sullivan", "Marc Mahon" };
	private JComboBox signedCombo = new JComboBox(signedArray);
	private JCheckBox statusCheckBox;

	// BOTTOM OPTIONS BAR
	private JButton save, searchMode, newRecordMode, updateRecord, printReport, summaryMode, refresh, exportToExcel;

	// TOP OPTIONS BAR
	private JButton find, previous, next, delete;

	// LEFT SIDE PANEL
	private JLabel linersLabel, head1Label, head2Label, head3Label, head4Label, head5Label, head6Label, head7Label,
			head8Label;
	private JCheckBox head1CheckBox, head2CheckBox, head3CheckBox, head4CheckBox, head5CheckBox, head6CheckBox,
			head7CheckBox, head8CheckBox;
	private JCheckBox number1Check, number2Check, number3Check, number4Check, number5Check, number6Check, number7Check,
			number8Check, number9Check, number10Check, number11Check, number12Check, number13Check, number14Check,
			number15Check, number16Check, number17Check, number18Check, number19Check, number20Check, number21Check,
			number22Check, number23Check, number24Check, number25Check, number26Check;
	private JTextArea beforeJTextArea, actionTakenJTextArea, afterJTextArea;
	// RIGHT SIDE PANEL
	private JCheckBox aJCheckBox, bJCheckBox, cJCheckBox, dJCheckBox;
	private JLabel stolleLabel;
	private JLabel score1ALabel, score1BLabel, score1CLabel, score1DLabel, score3ALabel, score3BLabel, score3CLabel,
			score3DLabel, score6ALabel, score6BLabel, score6CLabel, score6DLabel, score9ALabel, score9BLabel,
			score9CLabel, score9DLabel;
	static JTextField score1AJTextField, score1BJTextField, score1CJTextField, score1DJTextField, score3AJTextField,
			score3BJTextField, score3CJTextField, score3DJTextField, score6AJTextField, score6BJTextField,
			score6CJTextField, score6DJTextField, score9AJTextField, score9BJTextField, score9CJTextField,
			score9DJTextField;
	private String query, item;
	int currentForm;
	private JDateChooser chooser1, chooser2;
	private ParDAO parDAO;

	public static void main(String[] args) {

		new ParView(1);
		// setParToHighestForm();

	}

	public ParView(int viewIn) {
		
		pm = new ParModel();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		parDAO = (ParDAO) context.getBean("ParDAO");

		formJTextField = new JTextField();
		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());

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

		setTitle("PAR - New Entry Mode");
		setSize(1366, 768);
		// setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		JPanel mainOuterPanel = new JPanel(new BorderLayout());
		mainOuterPanel.add(createBottomOptionsBar(viewIn), BorderLayout.SOUTH);

		if (viewIn == 2) {

			setTitle("PAR - Search Mode");

			mainOuterPanel.add(createTopOptionsBar(), BorderLayout.NORTH);

			// try {
			// formJTextField.setText(parDAO.ParDatabaseGetHighestForm() + "");
			// currentForm = parDAO.ParDatabaseGetHighestForm();
			// } catch (SQLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
		}

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		if (viewIn == 1) {

			outerPanel.add(createLeftSidePanel(), BorderLayout.WEST);
			outerPanel.add(createTopPanel(viewIn), BorderLayout.NORTH);
			outerPanel.add(createBottomPanel(), BorderLayout.SOUTH);
			outerPanel.add(createRightPanel(), BorderLayout.CENTER);

		} else if (viewIn == 2) {

			outerPanel.add(createLeftSidePanel(), BorderLayout.WEST);
			outerPanel.add(createTopPanel(viewIn), BorderLayout.NORTH);
			outerPanel.add(createBottomPanel(), BorderLayout.SOUTH);
			outerPanel.add(createRightPanel(), BorderLayout.CENTER);

		} else {

			setTitle("PAR - Summary Mode");

			outerPanel.add(createSummaryPanel(), BorderLayout.CENTER);

		}

		mainOuterPanel.add(outerPanel, BorderLayout.CENTER);
		add(mainOuterPanel);
		setVisible(true);

		searchMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				new ParView(3);

				// dispose();
			}
		});
		// printReport.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// // TODO Auto-generated method stub
		// try {
		// createReport(Integer.valueOf(formJTextField.getText()));
		// System.out.println("Reference Number " + formJTextField.getText());
		// } catch (FileNotFoundException | JRException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (PrinterException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		//
		// // SQLConnection.ParGenerateReport(formJTextField.getText());
		// }
		// });
		newRecordMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				new ParView(1);

			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					pm.setDate(chooser1.getDate());
					pm.setForm(Integer.valueOf(formJTextField.getText()));
					pm.setCrew(crewCombo.getSelectedItem()+"");
					pm.setCode(codeCombo.getSelectedItem()+"");
					pm.setMachine(machineCodeCombo.getSelectedItem()+"");
					pm.setShiftManager(shiftManagerCombo.getSelectedItem()+"");
					pm.setTechnician(technicianCombo.getSelectedItem()+"");
					pm.setOperator(MachineOperatorCombo.getSelectedItem()+"");
					pm.setEngineer(engineerCombo.getSelectedItem()+"");
					
					pm.setLinerHeadCheck1(head1CheckBox.isSelected());
					pm.setLinerHeadCheck2(head2CheckBox.isSelected());
					pm.setLinerHeadCheck3(head3CheckBox.isSelected());
					pm.setLinerHeadCheck4(head4CheckBox.isSelected());
					pm.setLinerHeadCheck5(head5CheckBox.isSelected());
					pm.setLinerHeadCheck6(head6CheckBox.isSelected());
					pm.setLinerHeadCheck7(head7CheckBox.isSelected());
					
					pm.setShellPressCheck1(number1Check.isSelected());
					pm.setShellPressCheck2(number2Check.isSelected());
					pm.setShellPressCheck3(number3Check.isSelected());
					pm.setShellPressCheck4(number4Check.isSelected());
					pm.setShellPressCheck5(number5Check.isSelected());
					pm.setShellPressCheck6(number6Check.isSelected());
					pm.setShellPressCheck7(number7Check.isSelected());
					pm.setShellPressCheck8(number8Check.isSelected());
					pm.setShellPressCheck9(number9Check.isSelected());
					pm.setShellPressCheck10(number10Check.isSelected());
					pm.setShellPressCheck11(number11Check.isSelected());
					pm.setShellPressCheck12(number12Check.isSelected());
					pm.setShellPressCheck13(number13Check.isSelected());
					pm.setShellPressCheck14(number14Check.isSelected());
					pm.setShellPressCheck15(number15Check.isSelected());
					pm.setShellPressCheck16(number16Check.isSelected());
					pm.setShellPressCheck17(number17Check.isSelected());
					pm.setShellPressCheck18(number18Check.isSelected());
					pm.setShellPressCheck19(number19Check.isSelected());
					pm.setShellPressCheck20(number20Check.isSelected());
					pm.setShellPressCheck21(number21Check.isSelected());
					pm.setShellPressCheck22(number22Check.isSelected());
					pm.setShellPressCheck23(number23Check.isSelected());
					pm.setShellPressCheck24(number24Check.isSelected());
					pm.setShellPressCheck25(number25Check.isSelected());
					pm.setShellPressCheck26(number26Check.isSelected());
					
					pm.setStolleCheck1(aJCheckBox.isSelected());
					pm.setStolleCheck2(bJCheckBox.isSelected());
					pm.setStolleCheck3(cJCheckBox.isSelected());
					pm.setStolleCheck4(dJCheckBox.isSelected());
					
					pm.setScore1A(Double.valueOf(score1AJTextField.getText()));
					pm.setScore1B(Double.valueOf(score1BJTextField.getText()));
					pm.setScore1C(Double.valueOf(score1CJTextField.getText()));
					pm.setScore1D(Double.valueOf(score1DJTextField.getText()));
					pm.setScore3A(Double.valueOf(score3AJTextField.getText()));
					pm.setScore3B(Double.valueOf(score3BJTextField.getText()));
					pm.setScore3C(Double.valueOf(score3CJTextField.getText()));
					pm.setScore3D(Double.valueOf(score3DJTextField.getText()));
					pm.setScore6A(Double.valueOf(score6AJTextField.getText()));
					pm.setScore6B(Double.valueOf(score6BJTextField.getText()));
					pm.setScore6C(Double.valueOf(score6CJTextField.getText()));
					pm.setScore6D(Double.valueOf(score6DJTextField.getText()));
					pm.setScore9A(Double.valueOf(score9AJTextField.getText()));
					pm.setScore9B(Double.valueOf(score9BJTextField.getText()));
					pm.setScore9C(Double.valueOf(score9CJTextField.getText()));
					pm.setScore9D(Double.valueOf(score9DJTextField.getText()));
					
					
					pm.setTimeStarted(timeStartedJTextField.getText());
					pm.setTimeInToolRoom(timeInToolRoomJTextField.getText());
					pm.setTimeFinished(timeFinishedJTextField.getText());
					pm.setSigned(signedCombo.getSelectedItem()+"");
					pm.setDateSigned(chooser2.getDate());
					pm.setStatus(statusCheckBox.isSelected());
					pm.setBefore(beforeJTextArea.getText());
					pm.setActionTaken(actionTakenJTextArea.getText());
					pm.setAfter(afterJTextArea.getText());

					parDAO.ParDatabaseInsert(pm);

					Date date = new Date();
					String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
					String year = modifiedDate.substring(0, 4);
					String month = modifiedDate.substring(5, 7);
					String day = modifiedDate.substring(8, 10);

					JOptionPane.showMessageDialog(null, "Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new ParView(1);

				} catch (NumberFormatException e1) {

					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid Score Readings Entered. Numbers Only.", "Error",
							JOptionPane.ERROR_MESSAGE);

					e1.printStackTrace();
				}

			}
		});
		updateRecord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					pm.setDate(chooser1.getDate());
					pm.setForm(Integer.valueOf(formJTextField.getText()));
					pm.setCrew(crewCombo.getSelectedItem()+"");
					pm.setCode(codeCombo.getSelectedItem()+"");
					pm.setMachine(machineCodeCombo.getSelectedItem()+"");
					pm.setShiftManager(shiftManagerCombo.getSelectedItem()+"");
					pm.setTechnician(technicianCombo.getSelectedItem()+"");
					pm.setOperator(MachineOperatorCombo.getSelectedItem()+"");
					pm.setEngineer(engineerCombo.getSelectedItem()+"");
					
					pm.setLinerHeadCheck1(head1CheckBox.isSelected());
					pm.setLinerHeadCheck2(head2CheckBox.isSelected());
					pm.setLinerHeadCheck3(head3CheckBox.isSelected());
					pm.setLinerHeadCheck4(head4CheckBox.isSelected());
					pm.setLinerHeadCheck5(head5CheckBox.isSelected());
					pm.setLinerHeadCheck6(head6CheckBox.isSelected());
					pm.setLinerHeadCheck7(head7CheckBox.isSelected());
					
					pm.setShellPressCheck1(number1Check.isSelected());
					pm.setShellPressCheck2(number2Check.isSelected());
					pm.setShellPressCheck3(number3Check.isSelected());
					pm.setShellPressCheck4(number4Check.isSelected());
					pm.setShellPressCheck5(number5Check.isSelected());
					pm.setShellPressCheck6(number6Check.isSelected());
					pm.setShellPressCheck7(number7Check.isSelected());
					pm.setShellPressCheck8(number8Check.isSelected());
					pm.setShellPressCheck9(number9Check.isSelected());
					pm.setShellPressCheck10(number10Check.isSelected());
					pm.setShellPressCheck11(number11Check.isSelected());
					pm.setShellPressCheck12(number12Check.isSelected());
					pm.setShellPressCheck13(number13Check.isSelected());
					pm.setShellPressCheck14(number14Check.isSelected());
					pm.setShellPressCheck15(number15Check.isSelected());
					pm.setShellPressCheck16(number16Check.isSelected());
					pm.setShellPressCheck17(number17Check.isSelected());
					pm.setShellPressCheck18(number18Check.isSelected());
					pm.setShellPressCheck19(number19Check.isSelected());
					pm.setShellPressCheck20(number20Check.isSelected());
					pm.setShellPressCheck21(number21Check.isSelected());
					pm.setShellPressCheck22(number22Check.isSelected());
					pm.setShellPressCheck23(number23Check.isSelected());
					pm.setShellPressCheck24(number24Check.isSelected());
					pm.setShellPressCheck25(number25Check.isSelected());
					pm.setShellPressCheck26(number26Check.isSelected());
					
					pm.setStolleCheck1(aJCheckBox.isSelected());
					pm.setStolleCheck2(bJCheckBox.isSelected());
					pm.setStolleCheck3(cJCheckBox.isSelected());
					pm.setStolleCheck4(dJCheckBox.isSelected());
					
					pm.setScore1A(Double.valueOf(score1AJTextField.getText()));
					pm.setScore1B(Double.valueOf(score1BJTextField.getText()));
					pm.setScore1C(Double.valueOf(score1CJTextField.getText()));
					pm.setScore1D(Double.valueOf(score1DJTextField.getText()));
					pm.setScore3A(Double.valueOf(score3AJTextField.getText()));
					pm.setScore3B(Double.valueOf(score3BJTextField.getText()));
					pm.setScore3C(Double.valueOf(score3CJTextField.getText()));
					pm.setScore3D(Double.valueOf(score3DJTextField.getText()));
					pm.setScore6A(Double.valueOf(score6AJTextField.getText()));
					pm.setScore6B(Double.valueOf(score6BJTextField.getText()));
					pm.setScore6C(Double.valueOf(score6CJTextField.getText()));
					pm.setScore6D(Double.valueOf(score6DJTextField.getText()));
					pm.setScore9A(Double.valueOf(score9AJTextField.getText()));
					pm.setScore9B(Double.valueOf(score9BJTextField.getText()));
					pm.setScore9C(Double.valueOf(score9CJTextField.getText()));
					pm.setScore9D(Double.valueOf(score9DJTextField.getText()));
					
					
					pm.setTimeStarted(timeStartedJTextField.getText());
					pm.setTimeInToolRoom(timeInToolRoomJTextField.getText());
					pm.setTimeFinished(timeFinishedJTextField.getText());
					pm.setSigned(signedCombo.getSelectedItem()+"");
					pm.setDateSigned(chooser2.getDate());
					pm.setStatus(statusCheckBox.isSelected());
					pm.setBefore(beforeJTextArea.getText());
					pm.setActionTaken(actionTakenJTextArea.getText());
					pm.setAfter(afterJTextArea.getText());

					parDAO.ParDatabaseUpdate(pm);

					JOptionPane.showMessageDialog(null, "Record Updated", "Success", JOptionPane.INFORMATION_MESSAGE);

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		summaryMode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				new ParView(3);

			}
		});
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ParView(3);

			}
		});
		// exportToExcel.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// ParView.exportToExcel();
		//
		// }
		// });

		// parDAO.AnalyticsUpdate("ParView");

	}

	public JPanel createTopPanel(int viewIn) {

		JPanel panel = new JPanel(new GridLayout(2, 10));

		formLabel = new JLabel("Form", SwingConstants.CENTER);
		dateLabel = new JLabel("Date", SwingConstants.CENTER);
		crewLabel = new JLabel("Crew", SwingConstants.CENTER);
		codeLabel = new JLabel("Code", SwingConstants.CENTER);
		machineCodeLabel = new JLabel("Machine", SwingConstants.CENTER);
		shiftManagerLabel = new JLabel("Shift Manager", SwingConstants.CENTER);
		technicianLabel = new JLabel("Technician", SwingConstants.CENTER);
		MachineOperatorLabel = new JLabel("Machine Operator", SwingConstants.CENTER);
		engineerLabel = new JLabel("Engineer", SwingConstants.CENTER);
		blankLabel1 = new JLabel("", SwingConstants.CENTER);
		blankLabel2 = new JLabel("", SwingConstants.CENTER);

		// formJTextField = new JTextField("");
		if (viewIn == 1) {
			 formJTextField.setText(parDAO.ParDatabaseGetHighestForm() + 1 + "");
			formJTextField.setEditable(false);
		}

		crewCombo = new JComboBox();
		codeCombo = new JComboBox();
		machineCodeCombo = new JComboBox();
		shiftManagerCombo = new JComboBox();
		technicianCombo = new JComboBox();
		MachineOperatorCombo = new JComboBox();
		engineerCombo = new JComboBox();
		
		fillCombos();

		panel.add(formLabel);
		panel.add(formJTextField);
		panel.add(dateLabel);
		panel.add(chooser1);
		panel.add(crewLabel);
		panel.add(crewCombo);
		panel.add(codeLabel);
		panel.add(codeCombo);
		panel.add(machineCodeLabel);
		panel.add(machineCodeCombo);

		panel.add(shiftManagerLabel);
		panel.add(shiftManagerCombo);
		panel.add(technicianLabel);
		panel.add(technicianCombo);
		panel.add(MachineOperatorLabel);
		panel.add(MachineOperatorCombo);
		panel.add(engineerLabel);
		panel.add(engineerCombo);
		panel.add(blankLabel1);
		panel.add(blankLabel2);

		

		// panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		return panel;
	}

	public JPanel createBottomPanel() {

		timeStartedLabel = new JLabel("Time Started", SwingConstants.CENTER);
		timeInToolRoomLabel = new JLabel("Time in ToolRoom", SwingConstants.CENTER);
		timeFinishedLabel = new JLabel("Time Finished", SwingConstants.CENTER);
		signedLabel = new JLabel("Signed", SwingConstants.CENTER);
		datelabel = new JLabel("Date", SwingConstants.CENTER);
		statusLabel = new JLabel("Status", SwingConstants.CENTER);
		timeStartedJTextField = new JTextField();
		timeInToolRoomJTextField = new JTextField();
		timeFinishedJTextField = new JTextField();
		statusCheckBox = new JCheckBox();
		blankLabel3 = new JLabel("");
		blankLabel4 = new JLabel("");
		blankLabel5 = new JLabel("");
		blankLabel6 = new JLabel("");
		blankLabel7 = new JLabel("");
		blankLabel8 = new JLabel("");
		blankLabel9 = new JLabel("");
		blankLabel10 = new JLabel("");

		JPanel outerPanel = new JPanel(new GridLayout(2, 10));

		outerPanel.add(timeStartedLabel);
		outerPanel.add(timeStartedJTextField);

		outerPanel.add(timeInToolRoomLabel);
		outerPanel.add(timeInToolRoomJTextField);

		outerPanel.add(timeFinishedLabel);
		outerPanel.add(timeFinishedJTextField);

		outerPanel.add(blankLabel3);
		outerPanel.add(blankLabel4);
		outerPanel.add(blankLabel5);
		outerPanel.add(blankLabel6);

		outerPanel.add(signedLabel);
		outerPanel.add(signedCombo);

		outerPanel.add(datelabel);
		outerPanel.add(chooser2);

		outerPanel.add(statusLabel);
		outerPanel.add(statusCheckBox);

		outerPanel.add(blankLabel7);
		outerPanel.add(blankLabel8);

		outerPanel.add(blankLabel9);
		outerPanel.add(blankLabel10);

		// outerPanel.setBackground(Color.WHITE);
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		return outerPanel;
	}

	private void fillCombos() {
		
		

		JComboBox[] la = parDAO.fillCombos(new JComboBox[8]);

		 crewCombo = la[0];
		
		 MachineOperatorCombo = la[1];
		 codeCombo = la[2];
		 machineCodeCombo = la[3];
		 shiftManagerCombo = la[4];
		 signedCombo = la[5];
		 technicianCombo = la[6];
		 engineerCombo = la[7];

		// // Crew
		// try {
		//
		// String sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName Asc";
		// Connection conn = parDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String crewName = rs.getString("CrewName");
		// crewCombo.addItem(crewName);
		// }
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Machine Operator
		// try {
		//
		// String sql = "select Employees.Name from Employees ORDER BY Name
		// ASC";
		// Connection conn = parDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// MachineOperatorCombo.addItem("NA");
		// shiftManagerCombo.addItem("NA");
		// technicianCombo.addItem("NA");
		// engineerCombo.addItem("NA");
		//
		// while (rs.next()) {
		//
		// String employeeName = rs.getString("Name");
		//
		// MachineOperatorCombo.addItem(employeeName);
		// shiftManagerCombo.addItem(employeeName);
		// signedCombo.addItem(employeeName);
		// technicianCombo.addItem(employeeName);
		// engineerCombo.addItem(employeeName);
		// }
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Machine Code
		// try {
		//
		// String sql = "SELECT ParCode.Title FROM ParCode ORDER BY Title Asc";
		// Connection conn = parDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String code = rs.getString("Title");
		// codeCombo.addItem(code);
		// }
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Machine Titles
		// try {
		//
		// String sql = "SELECT Machines.Title FROM Machines ORDER BY Title
		// Asc";
		// Connection conn = parDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String machine = rs.getString("Title");
		// machineCodeCombo.addItem(machine);
		// }
		//
		// } catch (Exception e) {
		//
		// }

	}

	public JPanel createTopOptionsBar() {

		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.GRAY);
		// panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		find = new JButton("Find Record by Form");

		previous = new JButton("Previous Record");

		next = new JButton("Next Record");
		delete = new JButton("Delete This Record");

		panel.add(find);
		panel.add(previous);
		panel.add(next);
		panel.add(delete);

		return panel;

	}

	public JPanel createBottomOptionsBar(int viewIn) {

		JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.GRAY);
		// panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		save = new JButton("Save Record");
		summaryMode = new JButton("Summary Mode");
		refresh = new JButton("Refresh");
		exportToExcel = new JButton("Export To Excel");
		searchMode = new JButton("Search Mode");
		newRecordMode = new JButton("New Entry Mode");
		updateRecord = new JButton("Update Record");
		printReport = new JButton("Print Report");

		// New Entry
		if (viewIn == 1) {

			panel.add(searchMode);
			panel.add(save);

		} else if (viewIn == 2) {

			panel.add(summaryMode);
			panel.add(updateRecord);
			panel.add(printReport);

		} else {

			panel.add(newRecordMode);
			panel.add(refresh);
			panel.add(exportToExcel);

		}

		return panel;
	}

	public JPanel createLeftSidePanel() {

		JPanel outerPanel = new JPanel(new BorderLayout());

		outerPanel.add(createLeftSidePanel1(), BorderLayout.NORTH);
		outerPanel.add(createLeftSidePanel2(), BorderLayout.CENTER);
		outerPanel.add(createLeftSidePanel3(), BorderLayout.SOUTH);

		return outerPanel;
	}

	public JPanel createLeftSidePanel1() {

		linersLabel = new JLabel("Liners", SwingConstants.CENTER);
		head1Label = new JLabel("Head 1  ", SwingConstants.CENTER);
		head2Label = new JLabel("Head 2  ", SwingConstants.CENTER);
		head3Label = new JLabel("Head 3  ", SwingConstants.CENTER);
		head4Label = new JLabel("Head 4  ", SwingConstants.CENTER);
		head5Label = new JLabel("Head 5  ", SwingConstants.CENTER);
		head6Label = new JLabel("Head 6  ", SwingConstants.CENTER);
		head7Label = new JLabel("Head 7  ", SwingConstants.CENTER);
		head8Label = new JLabel("Head 8  ", SwingConstants.CENTER);

		head1CheckBox = new JCheckBox();
		head2CheckBox = new JCheckBox();
		head3CheckBox = new JCheckBox();
		head4CheckBox = new JCheckBox();
		head5CheckBox = new JCheckBox();
		head6CheckBox = new JCheckBox();
		head7CheckBox = new JCheckBox();
		head8CheckBox = new JCheckBox();

		JPanel outerPanel = new JPanel(new BorderLayout());

		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel northPanel0 = new JPanel(new FlowLayout());
		northPanel0.setBackground(Color.GRAY);

		JPanel northPanel1 = new JPanel(new GridLayout(2, 8));
		// northPanel1.setBackground(new Color(255, 255, 255));

		// northPanel.add(linersLabel);
		northPanel0.add(linersLabel);

		northPanel1.add(head1Label);
		northPanel1.add(head1CheckBox);
		northPanel1.add(head2Label);
		northPanel1.add(head2CheckBox);
		northPanel1.add(head3Label);
		northPanel1.add(head3CheckBox);
		northPanel1.add(head4Label);
		northPanel1.add(head4CheckBox);
		northPanel1.add(head5Label);
		northPanel1.add(head5CheckBox);
		northPanel1.add(head6Label);
		northPanel1.add(head6CheckBox);
		northPanel1.add(head7Label);
		northPanel1.add(head7CheckBox);
		northPanel1.add(head8Label);
		northPanel1.add(head8CheckBox);

		northPanel.add(northPanel0, BorderLayout.NORTH);
		northPanel.add(northPanel1, BorderLayout.SOUTH);

		outerPanel.add(northPanel, BorderLayout.NORTH);

		// outerPanel.setBackground(Color.WHITE);
		northPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		return outerPanel;
	}

	public JPanel createLeftSidePanel2() {

		number1Check = new JCheckBox();
		number2Check = new JCheckBox();
		number3Check = new JCheckBox();
		number4Check = new JCheckBox();
		number5Check = new JCheckBox();
		number6Check = new JCheckBox();
		number7Check = new JCheckBox();
		number8Check = new JCheckBox();
		number9Check = new JCheckBox();
		number10Check = new JCheckBox();
		number11Check = new JCheckBox();
		number12Check = new JCheckBox();
		number13Check = new JCheckBox();
		number14Check = new JCheckBox();
		number15Check = new JCheckBox();
		number16Check = new JCheckBox();
		number17Check = new JCheckBox();
		number18Check = new JCheckBox();
		number19Check = new JCheckBox();
		number20Check = new JCheckBox();
		number21Check = new JCheckBox();
		number22Check = new JCheckBox();
		number23Check = new JCheckBox();
		number24Check = new JCheckBox();
		number25Check = new JCheckBox();
		number26Check = new JCheckBox();

		JPanel outerPanel = new JPanel(new BorderLayout());

		JPanel centerPanel1 = new JPanel(new FlowLayout());
		centerPanel1.add(new JLabel("Shell Press"), SwingConstants.CENTER);
		centerPanel1.setBackground(Color.GRAY);

		JPanel centerPanel2 = new JPanel(new GridLayout(3, 16));
		// centerPanel2.setBackground(Color.WHITE);

		outerPanel.add(centerPanel1, BorderLayout.NORTH);

		centerPanel2.add(new JLabel("    1"));
		centerPanel2.add(number1Check);

		centerPanel2.add(new JLabel("    2"));
		centerPanel2.add(number2Check);

		centerPanel2.add(new JLabel("    3"));
		centerPanel2.add(number3Check);

		centerPanel2.add(new JLabel("    4"));
		centerPanel2.add(number4Check);

		centerPanel2.add(new JLabel("    5"));
		centerPanel2.add(number5Check);

		centerPanel2.add(new JLabel("    6"));
		centerPanel2.add(number6Check);

		centerPanel2.add(new JLabel("    7"));
		centerPanel2.add(number7Check);

		centerPanel2.add(new JLabel("    8"));
		centerPanel2.add(number8Check);

		centerPanel2.add(new JLabel("    9"));
		centerPanel2.add(number9Check);

		centerPanel2.add(new JLabel("    10"));
		centerPanel2.add(number10Check);

		centerPanel2.add(new JLabel("    11"));
		centerPanel2.add(number11Check);

		centerPanel2.add(new JLabel("    12"));
		centerPanel2.add(number12Check);

		centerPanel2.add(new JLabel("    13"));
		centerPanel2.add(number13Check);

		centerPanel2.add(new JLabel("    14"));
		centerPanel2.add(number14Check);

		centerPanel2.add(new JLabel("    15"));
		centerPanel2.add(number15Check);

		centerPanel2.add(new JLabel("    16"));
		centerPanel2.add(number16Check);

		centerPanel2.add(new JLabel("    17"));
		centerPanel2.add(number17Check);

		centerPanel2.add(new JLabel("    18"));
		centerPanel2.add(number18Check);

		centerPanel2.add(new JLabel("    19"));
		centerPanel2.add(number19Check);

		centerPanel2.add(new JLabel("    20"));
		centerPanel2.add(number20Check);

		centerPanel2.add(new JLabel("    21"));
		centerPanel2.add(number21Check);

		centerPanel2.add(new JLabel("    22"));
		centerPanel2.add(number22Check);

		centerPanel2.add(new JLabel("    23"));
		centerPanel2.add(number23Check);

		centerPanel2.add(new JLabel("    24"));
		centerPanel2.add(number24Check);

		centerPanel2.add(new JLabel("    25"));
		centerPanel2.add(number25Check);

		centerPanel2.add(new JLabel("    26"));
		centerPanel2.add(number26Check);

		outerPanel.add(centerPanel2, BorderLayout.CENTER);

		return outerPanel;

	}

	public JPanel createLeftSidePanel3() {

		beforeJTextArea = new JTextArea(5, 20);
		beforeJTextArea.setLineWrap(true);
		actionTakenJTextArea = new JTextArea(5, 20);
		actionTakenJTextArea.setLineWrap(true);
		afterJTextArea = new JTextArea(5, 20);
		afterJTextArea.setLineWrap(true);

		JPanel outerPanel = new JPanel(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setBackground(new Color(255, 255, 123));
		topPanel.add(new JLabel("Enter Details Below Except For [Before Score Readings]"));

		JPanel bottomPanelOuter = new JPanel(new BorderLayout());
		// bottomPanelOuter.setBackground(Color.WHITE);

		JPanel bottomPanel1 = new JPanel(new BorderLayout());
		// bottomPanel1.setBackground(Color.WHITE);
		bottomPanel1.add(new JLabel("Before              "), BorderLayout.WEST);
		bottomPanel1.add(beforeJTextArea, BorderLayout.CENTER);

		JPanel bottomPanel2 = new JPanel(new BorderLayout());
		// bottomPanel2.setBackground(Color.WHITE);
		bottomPanel2.add(new JLabel("Action Taken   "), BorderLayout.WEST);
		bottomPanel2.add(actionTakenJTextArea, BorderLayout.CENTER);

		JPanel bottomPanel3 = new JPanel(new BorderLayout());
		// bottomPanel3.setBackground(Color.WHITE);
		bottomPanel3.add(new JLabel("After                  "), BorderLayout.WEST);
		bottomPanel3.add(afterJTextArea, BorderLayout.CENTER);

		bottomPanelOuter.add(bottomPanel1, BorderLayout.NORTH);
		bottomPanelOuter.add(bottomPanel2, BorderLayout.CENTER);
		bottomPanelOuter.add(bottomPanel3, BorderLayout.SOUTH);

		JPanel bottomBar = new JPanel(new FlowLayout());
		bottomBar.setBackground(Color.GRAY);
		bottomBar.add(new JLabel(" "));

		outerPanel.add(topPanel, BorderLayout.NORTH);
		outerPanel.add(bottomPanelOuter, BorderLayout.CENTER);
		outerPanel.add(bottomBar, BorderLayout.SOUTH);

		// outerPanel.setBackground(Color.WHITE);
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		return outerPanel;

	}

	public JPanel createRightPanel() {

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.add(createRightPanelTop(), BorderLayout.NORTH);
		// outerPanel.setBackground(Color.WHITE);

		JPanel panel = new JPanel(new GridLayout(1, 3));
		// panel.setBackground(Color.WHITE);

		panel.add(createRightPanelLeft());
		panel.add(createRightPanelCenter());
		panel.add(createRightPanelRight());

		outerPanel.add(createRightPanelTop(), BorderLayout.NORTH);
		outerPanel.add(panel, BorderLayout.CENTER);

		outerPanel.add(panel);
		// outerPanel.setBackground(Color.WHITE);

		return outerPanel;
	}

	public JPanel createRightPanelLeft() {

		JPanel panel = new JPanel(new GridLayout(16, 1));
		// panel.setBackground(Color.WHITE);

		score9ALabel = new JLabel("Score 9A", SwingConstants.CENTER);
		score9BLabel = new JLabel("Score 9B", SwingConstants.CENTER);
		score9CLabel = new JLabel("Score 9C", SwingConstants.CENTER);
		score9DLabel = new JLabel("Score 9D", SwingConstants.CENTER);

		score9AJTextField = new JTextField("0.0");
		score9BJTextField = new JTextField("0.0");
		score9CJTextField = new JTextField("0.0");
		score9DJTextField = new JTextField("0.0");

		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(score9ALabel);
		panel.add(score9AJTextField);
		panel.add(score9BLabel);
		panel.add(score9BJTextField);
		panel.add(score9CLabel);
		panel.add(score9CJTextField);
		panel.add(score9DLabel);
		panel.add(score9DJTextField);
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));

		panel.setBorder(new EmptyBorder(10, 50, 10, 50));
		return panel;

	}

	public JPanel createRightPanelRight() {

		JPanel panel = new JPanel(new GridLayout(16, 1));
		// panel.setBackground(Color.WHITE);

		score3ALabel = new JLabel("Score 3A", SwingConstants.CENTER);
		score3BLabel = new JLabel("Score 3B", SwingConstants.CENTER);
		score3CLabel = new JLabel("Score 3C", SwingConstants.CENTER);
		score3DLabel = new JLabel("Score 3D", SwingConstants.CENTER);

		score3AJTextField = new JTextField("0.0");
		score3BJTextField = new JTextField("0.0");
		score3CJTextField = new JTextField("0.0");
		score3DJTextField = new JTextField("0.0");

		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(score3ALabel);
		panel.add(score3AJTextField);
		panel.add(score3BLabel);
		panel.add(score3BJTextField);
		panel.add(score3CLabel);
		panel.add(score3CJTextField);
		panel.add(score3DLabel);
		panel.add(score3DJTextField);
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));
		panel.add(new JLabel("", SwingConstants.CENTER));

		panel.setBorder(new EmptyBorder(10, 50, 10, 50));
		return panel;

	}

	public JPanel createRightPanelTop() {

		aJCheckBox = new JCheckBox();
		bJCheckBox = new JCheckBox();
		cJCheckBox = new JCheckBox();
		dJCheckBox = new JCheckBox();

		stolleLabel = new JLabel("                                                  Stolle         "
				+ "                                                         ");

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel titlePanel = new JPanel(new GridLayout(1, 1));
		titlePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		titlePanel.setBackground(Color.GRAY);
		titlePanel.add(stolleLabel, SwingConstants.CENTER);

		JPanel rightPanel = new JPanel(new GridLayout(1, 14));
		// rightPanel.setBackground(Color.WHITE);
		rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel.setBackground(Color.WHITE);

		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel(""));
		rightPanel.add(new JLabel("A"));
		rightPanel.add(aJCheckBox);
		rightPanel.add(new JLabel("B"));
		rightPanel.add(bJCheckBox);
		rightPanel.add(new JLabel("C"));
		rightPanel.add(cJCheckBox);
		rightPanel.add(new JLabel("D"));
		rightPanel.add(dJCheckBox);
		rightPanel.add(new JLabel(""));

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(new Color(255, 255, 123));
		bottomPanel.add(new JLabel("Enter Before Score Readings Only."));

		outerPanel.add(titlePanel, BorderLayout.WEST);
		outerPanel.add(rightPanel, BorderLayout.CENTER);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		return outerPanel;
	}

	public JPanel createRightPanelCenter() {

		JPanel outerpanel = new JPanel(new GridLayout(3, 1));
		// outerpanel.setBackground(Color.WHITE);

		score6ALabel = new JLabel("Score 6A");
		score6BLabel = new JLabel("Score 6B");
		score6CLabel = new JLabel("Score 6C");
		score6DLabel = new JLabel("Score 6D");

		score6AJTextField = new JTextField("0.0");

		score6BJTextField = new JTextField("0.0");

		score6CJTextField = new JTextField("0.0");

		score6DJTextField = new JTextField("0.0");

		score1ALabel = new JLabel("Score 1A");
		score1BLabel = new JLabel("Score 1B");
		score1CLabel = new JLabel("Score 1C");
		score1DLabel = new JLabel("Score 1D");

		score1AJTextField = new JTextField("0.0");
		score1BJTextField = new JTextField("0.0");
		score1CJTextField = new JTextField("0.0");
		score1DJTextField = new JTextField("0.0");

		JPanel topPanel = new JPanel(new GridLayout(5, 2));
		topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		// topPanel.setBackground(Color.WHITE);

		topPanel.add(score1ALabel);
		topPanel.add(score1BLabel);
		topPanel.add(score1AJTextField);
		topPanel.add(score1BJTextField);
		topPanel.add(score1CLabel);
		topPanel.add(score1DLabel);
		topPanel.add(score1CJTextField);
		topPanel.add(score1DJTextField);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));

		JPanel middlePanel = new JPanel();
		// middlePanel.setBackground(Color.WHITE);

		JLabel label = new JLabel("", SwingConstants.CENTER);
		ImageIcon image = new ImageIcon("Images/ring.jpg");
		label.setIcon(image);
		middlePanel.add(label, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new GridLayout(5, 2));
		// bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(score6ALabel);
		bottomPanel.add(score6BLabel);
		bottomPanel.add(score6AJTextField);
		bottomPanel.add(score6BJTextField);
		bottomPanel.add(score6CLabel);
		bottomPanel.add(score6DLabel);
		bottomPanel.add(score6CJTextField);
		bottomPanel.add(score6DJTextField);

		outerpanel.add(topPanel);
		outerpanel.add(middlePanel);
		outerpanel.add(bottomPanel);

		return outerpanel;

	}

	public JPanel createRightPanelCenter2() {

		score3ALabel = new JLabel("Score 3A");
		score3BLabel = new JLabel("Score 3B");
		score3CLabel = new JLabel("Score 3C");
		score3DLabel = new JLabel("Score 3D");
		score6ALabel = new JLabel("Score 6A");
		score6BLabel = new JLabel("Score 6B");
		score6CLabel = new JLabel("Score 6C");
		score6DLabel = new JLabel("Score 6D");

		score3AJTextField = new JTextField("0.0");
		score3BJTextField = new JTextField("0.0");
		score3CJTextField = new JTextField("0.0");
		score3DJTextField = new JTextField("0.0");

		score6AJTextField = new JTextField("0.0");
		score6BJTextField = new JTextField("0.0");
		score6CJTextField = new JTextField("0.0");
		score6DJTextField = new JTextField("0.0");

		JPanel panel = new JPanel(new BorderLayout());
		// panel.setBackground(Color.WHITE);

		JPanel topPanel = new JPanel(new GridLayout(4, 6));
		topPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(score1ALabel);
		topPanel.add(score1CLabel);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));

		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(score1AJTextField);
		topPanel.add(score1CJTextField);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));

		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(score1BLabel);
		topPanel.add(score1DLabel);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));

		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(score1BJTextField);
		topPanel.add(score1DJTextField);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));

		JPanel bottomPanel = new JPanel(new GridLayout(4, 6));
		bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(score6ALabel);
		bottomPanel.add(score6CLabel);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));

		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(score6AJTextField);
		bottomPanel.add(score6CJTextField);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));

		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(score6BLabel);
		bottomPanel.add(score6DLabel);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));

		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(score6BJTextField);
		bottomPanel.add(score6DJTextField);
		bottomPanel.add(new JLabel(""));
		bottomPanel.add(new JLabel(""));

		JPanel rightPanel = new JPanel(new GridLayout(9, 1));
		rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

		rightPanel.add(new JLabel("                                                                    "));

		rightPanel.add(score3ALabel);
		rightPanel.add(score3AJTextField);
		rightPanel.add(score3BLabel);
		rightPanel.add(score3BJTextField);
		rightPanel.add(score3CLabel);
		rightPanel.add(score3CJTextField);
		rightPanel.add(score3DLabel);
		rightPanel.add(score3DJTextField);
		// rightPanel.add(new JLabel(""));
		// rightPanel.add(new JLabel(""));
		// rightPanel.add(new JLabel(""));
		// rightPanel.add(new JLabel(""));
		// rightPanel.add(new JLabel(""));

		JPanel leftPanel = new JPanel(new GridLayout(14, 1));
		leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		leftPanel.add(new JLabel("                                                                    "));

		leftPanel.add(new JLabel(""));
		leftPanel.add(new JLabel(""));
		leftPanel.add(score9ALabel);
		leftPanel.add(score9AJTextField);
		leftPanel.add(score9BLabel);
		leftPanel.add(score9BJTextField);
		leftPanel.add(score9CLabel);
		leftPanel.add(score9CJTextField);
		leftPanel.add(score9DLabel);
		leftPanel.add(score9DJTextField);
		leftPanel.add(new JLabel(""));
		leftPanel.add(new JLabel(""));
		leftPanel.add(new JLabel(""));

		JPanel centrePanel = new JPanel(new BorderLayout());

		JLabel label = new JLabel("", SwingConstants.CENTER);

		ImageIcon image = new ImageIcon("images/ring.jpg");

		label.setIcon(image);
		centrePanel.add(label, BorderLayout.CENTER);

		centrePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		centrePanel.setBackground(Color.RED);

		panel.add(centrePanel, BorderLayout.CENTER);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(bottomPanel, BorderLayout.SOUTH);
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(rightPanel, BorderLayout.EAST);

		return panel;
	}

	public void setParToForm(int formIn) {
		//
		//
		// try {
		//
		// pm = parDAO.ParDatabaseReturnEntryByForm(formIn);
		//
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		//
		// // Set date to result[0]
		// formJTextField.setText(result[1] + "");
		// crewCombo.setSelectedItem(result[2]);
		// codeCombo.setSelectedItem(result[3]);
		// machineCodeCombo.setSelectedItem(result[4]);
		// shiftManagerCombo.setSelectedItem(result[5]);
		// technicianCombo.setSelectedItem(result[6]);
		// MachineOperatorCombo.setSelectedItem(result[7]);
		// engineerCombo.setSelectedItem(result[8]);
		//
		// head1CheckBox.setSelected((boolean) result[9]);
		// head2CheckBox.setSelected((boolean) result[10]);
		// head3CheckBox.setSelected((boolean) result[11]);
		// head4CheckBox.setSelected((boolean) result[12]);
		// head5CheckBox.setSelected((boolean) result[13]);
		// head6CheckBox.setSelected((boolean) result[14]);
		// head7CheckBox.setSelected((boolean) result[15]);
		// head8CheckBox.setSelected((boolean) result[16]);
		//
		// number1Check.setSelected((boolean) result[17]);
		// number2Check.setSelected((boolean) result[18]);
		// number3Check.setSelected((boolean) result[19]);
		// number4Check.setSelected((boolean) result[20]);
		// number5Check.setSelected((boolean) result[21]);
		// number6Check.setSelected((boolean) result[22]);
		// number7Check.setSelected((boolean) result[23]);
		// number8Check.setSelected((boolean) result[24]);
		// number9Check.setSelected((boolean) result[25]);
		// number10Check.setSelected((boolean) result[26]);
		// number11Check.setSelected((boolean) result[27]);
		// number12Check.setSelected((boolean) result[28]);
		// number13Check.setSelected((boolean) result[29]);
		// number14Check.setSelected((boolean) result[30]);
		// number15Check.setSelected((boolean) result[31]);
		// number16Check.setSelected((boolean) result[32]);
		// number17Check.setSelected((boolean) result[33]);
		// number18Check.setSelected((boolean) result[34]);
		// number19Check.setSelected((boolean) result[35]);
		// number20Check.setSelected((boolean) result[36]);
		// number21Check.setSelected((boolean) result[37]);
		// number22Check.setSelected((boolean) result[38]);
		// number23Check.setSelected((boolean) result[39]);
		// number24Check.setSelected((boolean) result[40]);
		// number25Check.setSelected((boolean) result[41]);
		// number26Check.setSelected((boolean) result[42]);
		//
		// aJCheckBox.setSelected((boolean) result[43]);
		// bJCheckBox.setSelected((boolean) result[44]);
		// cJCheckBox.setSelected((boolean) result[45]);
		// dJCheckBox.setSelected((boolean) result[46]);
		//
		// score1AJTextField.setText(result[47] + "");
		// score1BJTextField.setText(result[48] + "");
		// score1CJTextField.setText(result[49] + "");
		// score1DJTextField.setText(result[50] + "");
		// score3AJTextField.setText(result[51] + "");
		// score3BJTextField.setText(result[52] + "");
		// score3CJTextField.setText(result[53] + "");
		// score3DJTextField.setText(result[54] + "");
		// score6AJTextField.setText(result[55] + "");
		// score6BJTextField.setText(result[56] + "");
		// score6CJTextField.setText(result[57] + "");
		// score6DJTextField.setText(result[58] + "");
		// score9AJTextField.setText(result[59] + "");
		// score9BJTextField.setText(result[60] + "");
		// score9CJTextField.setText(result[61] + "");
		// score9DJTextField.setText(result[62] + "");
		//
		// timeStartedJTextField.setText(result[63] + "");
		// timeInToolRoomJTextField.setText(result[64] + "");
		// timeFinishedJTextField.setText(result[65] + "");
		//
		// signedCombo.setSelectedItem(result[66]);
		//
		// String modifiedDate = result[67] + "";
		// String year = modifiedDate.substring(0, 4);
		// int yearInt = Integer.parseInt(year);
		// String month = modifiedDate.substring(5, 7);
		// int monthInt = Integer.parseInt(month) - 1;
		// String day = modifiedDate.substring(8, 10);
		// int dayInt = Integer.parseInt(day);
		//
		// model2.setDate(yearInt, monthInt, dayInt);
		//
		// statusCheckBox.setSelected((boolean) result[68]);
		//
		// beforeJTextArea.setText(result[69] + "");
		// actionTakenJTextArea.setText(result[70] + "");
		// afterJTextArea.setText(result[71] + "");
		//
		// }
		//
		// public void createReport(int referenceNumber) throws JRException,
		// IOException, PrinterException, SQLException {
		//
		// Connection conn = parDAO.Connect();
		//
		// File file = new File("Reports/ParReport.jrxml");
		// InputStream stream = new FileInputStream(file);
		// JasperDesign design = JRXmlLoader.load(stream);
		// JasperReport report = JasperCompileManager.compileReport(design);
		//
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("ReferenceNumber", referenceNumber); // note here you can
		// add
		// // parameters which
		// // would be utilized by
		// // the report
		//
		// InputStream inputStream = new
		// FileInputStream("Reports/ParReport.jrxml");
		// JasperCompileManager.compileReportToFile("Reports/ParReport.jrxml");
		// JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		// JasperReport jasperReport =
		// JasperCompileManager.compileReport(jasperDesign);
		// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
		// params, conn);
		// JasperViewer view = new
		// net.sf.jasperreports.view.JasperViewer(jasperPrint, false);
		// view.setVisible(true);
		// view.toFront();
		//
		// conn.close();
		//
		// // use JasperExportManager to export report to your desired
		// requirement
	}

	private JPanel createSummaryPanel() {

		JPanel outerPanel = new JPanel(new BorderLayout());

		JPanel tablePanel = new JPanel();
		tablePanel = parDAO.ParEntrySummaryTable();

		JScrollPane scrollPane = new JScrollPane(tablePanel);

		outerPanel.add(scrollPane, BorderLayout.CENTER);
		// outerPanel.add(createBottomOptionsBar(3), BorderLayout.SOUTH);

		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		return outerPanel;
	}

}
