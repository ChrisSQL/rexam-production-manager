package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.controller.MaintenanceDatabaseController;
import com.rexam.maintenance.dao.ShellPressMaintenanceDAO;
import com.rexam.maintenance.dao.ShellPressProductionDAO;
import com.rexam.maintenance.model.ShellPressMaintenanceModel;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ShellPressMaintenanceView extends JFrame {

	private JPanel outerPanel, optionsPanel;
	private JButton SP01, SP02, SP03, SP04, update;
	private int currentId;
	ShellPressMaintenanceModel sm;

	private MaintenanceDatabaseController db;
	private ShellPressMaintenanceDAO shellPressMaintenanceDAO;

	private JLabel toolingAreaLabel1, toolingAreaLabel2, toolingAreaLabel3, toolingAreaLabel4, toolingAreaLabel5,
			toolingAreaLabel6, toolingAreaLabel7, MaintenanceTypeLabel1, MaintenanceTypeLabel2, MaintenanceTypeLabel3,
			MaintenanceTypeLabel4, MaintenanceTypeLabel5, MaintenanceTypeLabel6, MaintenanceTypeLabel7;

	private JTextField MachineCodeJTexField1, MachineNameJTexField1, TargetProductionJTexField1, ProductionJTexField1,
			PlusMinusJTexField1, MaintenanceDueDateJTexField1, DaysRemainingJTexField1, MachineCodeJTexField2,
			MachineNameJTexField2, TargetProductionJTexField2, ProductionJTexField2, PlusMinusJTexField2,
			MaintenanceDueDateJTexField2, DaysRemainingJTexField2, MachineCodeJTexField3, MachineNameJTexField3,
			TargetProductionJTexField3, ProductionJTexField3, PlusMinusJTexField3, MaintenanceDueDateJTexField3,
			DaysRemainingJTexField3, MachineCodeJTexField4, MachineNameJTexField4, TargetProductionJTexField4,
			ProductionJTexField4, PlusMinusJTexField4, MaintenanceDueDateJTexField4, DaysRemainingJTexField4,
			MachineCodeJTexField5, MachineNameJTexField5, TargetProductionJTexField5, ProductionJTexField5,
			PlusMinusJTexField5, MaintenanceDueDateJTexField5, DaysRemainingJTexField5, MachineCodeJTexField6,
			MachineNameJTexField6, TargetProductionJTexField6, ProductionJTexField6, PlusMinusJTexField6,
			MaintenanceDueDateJTexField6, DaysRemainingJTexField6, MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal,
			TargetProductionJTexFieldTotal, ProductionJTexFieldTotal, PlusMinusJTexFieldTotal,
			MaintenanceDueDateJTexFieldTotal, DaysRemainingJTexFieldTotal;

	private JDateChooser LastMaintenanceDate1, LastMaintenanceDate2, LastMaintenanceDate3, LastMaintenanceDate4,
			LastMaintenanceDate5, LastMaintenanceDate6, LastMaintenanceDate7, MaintenanceDueDate1, MaintenanceDueDate2,
			MaintenanceDueDate3, MaintenanceDueDate4, MaintenanceDueDate5, MaintenanceDueDate6, MaintenanceDueDate7;

	public static void main(String args[]) {

		new ShellPressMaintenanceView(1);

	}

	public ShellPressMaintenanceView(int spIn) {
		
		setStyle();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		shellPressMaintenanceDAO = (ShellPressMaintenanceDAO) context.getBean("ShellPressMaintenanceDAO");

		initializeVariables();

		setTitle("Shell Press Maintenance");
		setSize(1400, 450);
		setLocationRelativeTo(null);

		outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
		outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
		outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);

		// setToMachineCode(1);

		add(outerPanel);
		setVisible(true);
		
		setToMachineCode(1);
		setDatesSp01();

		// setDaysRemaining();
		// setDatesSp01();

		// SQLiteConnection.AnalyticsUpdate("ShellPressMaintenance");

	}

	private void initializeVariables() {

		outerPanel = new JPanel(new BorderLayout());
		sm = new ShellPressMaintenanceModel();
		db = new MaintenanceDatabaseController();

		SP01 = new JButton("SP01");
		SP02 = new JButton("SP02");
		SP03 = new JButton("SP03");
		SP04 = new JButton("SP04");

		// Row 2
		MachineCodeJTexField1 = new JTextField(" ");
		MachineCodeJTexField1.setEditable(false);
		MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
		MachineNameJTexField1 = new JTextField(" ");
		MachineNameJTexField1.setEditable(false);
		MachineNameJTexField1.setBackground(new Color(255, 255, 123));
		TargetProductionJTexField1 = new JTextField();
		ProductionJTexField1 = new JTextField();
		PlusMinusJTexField1 = new JTextField();
		MaintenanceDueDateJTexField1 = new JTextField();
		DaysRemainingJTexField1 = new JTextField();

		// ROW3
		TargetProductionJTexField2 = new JTextField();
		ProductionJTexField2 = new JTextField();
		PlusMinusJTexField2 = new JTextField();
		MaintenanceDueDateJTexField2 = new JTextField();
		DaysRemainingJTexField2 = new JTextField();

		// ROW4
		TargetProductionJTexField3 = new JTextField();
		ProductionJTexField3 = new JTextField();
		PlusMinusJTexField3 = new JTextField();
		MaintenanceDueDateJTexField3 = new JTextField();
		DaysRemainingJTexField3 = new JTextField();

		// ROW5
		TargetProductionJTexField4 = new JTextField();
		ProductionJTexField4 = new JTextField();
		PlusMinusJTexField4 = new JTextField();
		MaintenanceDueDateJTexField4 = new JTextField();
		DaysRemainingJTexField4 = new JTextField();

		// ROW6
		TargetProductionJTexField5 = new JTextField();
		ProductionJTexField5 = new JTextField();
		PlusMinusJTexField5 = new JTextField();
		MaintenanceDueDateJTexField5 = new JTextField();
		DaysRemainingJTexField5 = new JTextField();

		// ROW7
		TargetProductionJTexField6 = new JTextField();
		ProductionJTexField6 = new JTextField();
		PlusMinusJTexField6 = new JTextField();
		MaintenanceDueDateJTexField6 = new JTextField();
		DaysRemainingJTexField6 = new JTextField();

		// ROW8
		TargetProductionJTexFieldTotal = new JTextField();
		ProductionJTexFieldTotal = new JTextField();
		PlusMinusJTexFieldTotal = new JTextField();
		MaintenanceDueDateJTexFieldTotal = new JTextField();
		DaysRemainingJTexFieldTotal = new JTextField();

	}

	private JPanel createOptionsPanel(int spIn) {

		optionsPanel = new JPanel(new FlowLayout());
		optionsPanel.setBackground(Color.GRAY);

		SP01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(1);
				setDatesSp01();
			}
		});

		SP02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(2);
				setDatesSp01();
			}
		});

		SP03.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(3);
				setDatesSp01();
			}
		});

		SP04.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToSP04();
				setDatesSp04();
			}
		});

		optionsPanel.add(SP01);
		optionsPanel.add(SP02);
		optionsPanel.add(SP03);
		optionsPanel.add(SP04);

		return optionsPanel;

	}

	private void setToMachineCode(int idIn) {

		TargetProductionJTexField3.setVisible(true);
		ProductionJTexField3.setVisible(true);
		PlusMinusJTexField3.setVisible(true);
		MaintenanceDueDateJTexField3.setVisible(true);
		DaysRemainingJTexField3.setVisible(true);

		TargetProductionJTexField4.setVisible(true);
		ProductionJTexField4.setVisible(true);
		PlusMinusJTexField4.setVisible(true);
		MaintenanceDueDateJTexField4.setVisible(true);
		DaysRemainingJTexField4.setVisible(true);

		TargetProductionJTexField5.setVisible(true);
		ProductionJTexField5.setVisible(true);
		PlusMinusJTexField5.setVisible(true);
		MaintenanceDueDateJTexField5.setVisible(true);
		DaysRemainingJTexField5.setVisible(true);

		TargetProductionJTexField6.setVisible(true);
		ProductionJTexField6.setVisible(true);
		PlusMinusJTexField6.setVisible(true);
		MaintenanceDueDateJTexField6.setVisible(true);
		DaysRemainingJTexField6.setVisible(true);

		LastMaintenanceDate3.setVisible(true);
		LastMaintenanceDate4.setVisible(true);
		LastMaintenanceDate5.setVisible(true);
		LastMaintenanceDate6.setVisible(true);

		MaintenanceDueDate3.setVisible(true);
		MaintenanceDueDate4.setVisible(true);
		MaintenanceDueDate5.setVisible(true);
		MaintenanceDueDate6.setVisible(true);

		toolingAreaLabel3.setVisible(true);
		toolingAreaLabel4.setVisible(true);
		toolingAreaLabel5.setVisible(true);
		toolingAreaLabel6.setVisible(true);

		MaintenanceTypeLabel3.setVisible(true);
		MaintenanceTypeLabel4.setVisible(true);
		MaintenanceTypeLabel5.setVisible(true);
		MaintenanceTypeLabel6.setVisible(true);

		toolingAreaLabel1.setText("Form 1-6");
		toolingAreaLabel2.setText("Form 7-12");
		toolingAreaLabel3.setText("Form 13-18");
		toolingAreaLabel4.setText("Form 19-24");
		toolingAreaLabel5.setText("Curl 1-12");
		toolingAreaLabel6.setText("Curl 13-24");

		toolingAreaLabel3.setVisible(true);
		toolingAreaLabel4.setVisible(true);
		toolingAreaLabel5.setVisible(true);
		toolingAreaLabel6.setVisible(true);

		optionsPanel.repaint();

		// Object[] machineCodeArray = new Object[50];

		try {
			
			System.out.println("Machine Code : " + idIn);
			sm = shellPressMaintenanceDAO.shellPressMaintenanceReturnEntryByID(idIn);

			currentId = idIn;

			MachineCodeJTexField1.setText(sm.getMachineCode() + "");
			MachineNameJTexField1.setText(sm.getMachineName());

			LastMaintenanceDate1.setDate(sm.getLastMaintenanceDate1());
			TargetProductionJTexField1.setText(sm.getTargetProduction1() + "");
			ProductionJTexField1.setText(sm.getProduction1() + "");
			PlusMinusJTexField1.setText((sm.getTargetProduction1() - sm.getProduction1()) + "");
			MaintenanceDueDate1.setDate(sm.getMaintenanceDueDate1());
			DaysRemainingJTexField1.setText(0 + "");

			LastMaintenanceDate2.setDate(sm.getLastMaintenanceDate2());
			TargetProductionJTexField2.setText(sm.getTargetProduction2() + "");
			ProductionJTexField2.setText(sm.getProduction2() + "");
			PlusMinusJTexField2.setText((sm.getTargetProduction2() - sm.getProduction2()) + "");
			MaintenanceDueDate2.setDate(sm.getMaintenanceDueDate2());
			DaysRemainingJTexField2.setText(0 + "");

			LastMaintenanceDate3.setDate(sm.getLastMaintenanceDate3());
			TargetProductionJTexField3.setText(sm.getTargetProduction3() + "");
			ProductionJTexField3.setText(sm.getProduction3() + "");
			PlusMinusJTexField3.setText((sm.getTargetProduction3() - sm.getProduction3()) + "");
			MaintenanceDueDate3.setDate(sm.getMaintenanceDueDate3());
			DaysRemainingJTexField3.setText(0 + "");

			LastMaintenanceDate4.setDate(sm.getLastMaintenanceDate4());
			TargetProductionJTexField4.setText(sm.getTargetProduction4() + "");
			ProductionJTexField4.setText(sm.getProduction4() + "");
			PlusMinusJTexField4.setText((sm.getTargetProduction4() - sm.getProduction4()) + "");
			MaintenanceDueDate4.setDate(sm.getMaintenanceDueDate4());
			DaysRemainingJTexField4.setText(0 + "");

			LastMaintenanceDate5.setDate(sm.getLastMaintenanceDate5());
			TargetProductionJTexField5.setText(sm.getTargetProduction5() + "");
			ProductionJTexField5.setText(sm.getProduction5() + "");
			PlusMinusJTexField5.setText((sm.getTargetProduction5() - sm.getProduction5()) + "");
			MaintenanceDueDate5.setDate(sm.getMaintenanceDueDate5());
			DaysRemainingJTexField5.setText(0 + "");

			LastMaintenanceDate6.setDate(sm.getLastMaintenanceDate6());
			TargetProductionJTexField6.setText(sm.getTargetProduction6() + "");
			ProductionJTexField6.setText(sm.getProduction6() + "");
			PlusMinusJTexField6.setText((sm.getTargetProduction6() - sm.getProduction6()) + "");
			MaintenanceDueDate6.setDate(sm.getMaintenanceDueDate6());
			DaysRemainingJTexField6.setText(0 + "");

		} catch (Exception ex) {
			Logger.getLogger(ShellPressMaintenanceView.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private JPanel createMiddlePanel(int spIn) {

		toolingAreaLabel1 = new JLabel("Form 1-6", JLabel.CENTER);
		toolingAreaLabel2 = new JLabel("Form 7-12", JLabel.CENTER);
		toolingAreaLabel3 = new JLabel("Form 13-18", JLabel.CENTER);
		toolingAreaLabel4 = new JLabel("Form 19-24", JLabel.CENTER);
		toolingAreaLabel5 = new JLabel("Curl 1-12", JLabel.CENTER);
		toolingAreaLabel6 = new JLabel("Curl 13-24", JLabel.CENTER);

		MaintenanceTypeLabel1 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel2 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel3 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel4 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel5 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel6 = new JLabel("12 Hour", JLabel.CENTER);
		MaintenanceTypeLabel7 = new JLabel("Tri-Annual", JLabel.CENTER);

		LastMaintenanceDate1 = new JDateChooser();
		LastMaintenanceDate2 = new JDateChooser();
		LastMaintenanceDate3 = new JDateChooser();
		LastMaintenanceDate4 = new JDateChooser();
		LastMaintenanceDate5 = new JDateChooser();
		LastMaintenanceDate6 = new JDateChooser();
		LastMaintenanceDate7 = new JDateChooser();

		MaintenanceDueDate1 = new JDateChooser();
		MaintenanceDueDate2 = new JDateChooser();
		MaintenanceDueDate3 = new JDateChooser();
		MaintenanceDueDate4 = new JDateChooser();
		MaintenanceDueDate5 = new JDateChooser();
		MaintenanceDueDate6 = new JDateChooser();
		MaintenanceDueDate7 = new JDateChooser();

		JPanel middlePanel = new JPanel(new GridLayout(8, 10));
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// middlePanel.setBackground(Color.WHITE);

		// ROW1
		middlePanel.add(new JLabel("Machine Code", JLabel.CENTER));
		middlePanel.add(new JLabel("Machine Name", JLabel.CENTER));
		middlePanel.add(new JLabel("Tooling Area", JLabel.CENTER));
		middlePanel.add(new JLabel("Last Maintenance", JLabel.CENTER));
		middlePanel.add(new JLabel("Maintenance Type", JLabel.CENTER));
		middlePanel.add(new JLabel("Target Production", JLabel.CENTER));
		middlePanel.add(new JLabel("Production", JLabel.CENTER));
		middlePanel.add(new JLabel("+ / -", JLabel.CENTER));
		middlePanel.add(new JLabel("Maintenance Due Date", JLabel.CENTER));
		middlePanel.add(new JLabel("Days Remaining", JLabel.CENTER));

		// ROW2
		MachineCodeJTexField1 = new JTextField(" ");
		MachineCodeJTexField1.setEditable(false);
		MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
		MachineNameJTexField1 = new JTextField(" ");
		MachineNameJTexField1.setEditable(false);
		MachineNameJTexField1.setBackground(new Color(255, 255, 123));
		TargetProductionJTexField1 = new JTextField();
		ProductionJTexField1 = new JTextField();
		PlusMinusJTexField1 = new JTextField();
		MaintenanceDueDateJTexField1 = new JTextField();
		DaysRemainingJTexField1 = new JTextField();

		middlePanel.add(MachineCodeJTexField1);
		middlePanel.add(MachineNameJTexField1);
		middlePanel.add(toolingAreaLabel1);
		middlePanel.add(LastMaintenanceDate1);
		middlePanel.add(MaintenanceTypeLabel1);
		middlePanel.add(TargetProductionJTexField1);
		middlePanel.add(ProductionJTexField1);
		middlePanel.add(PlusMinusJTexField1);
		middlePanel.add(MaintenanceDueDate1);
		middlePanel.add(DaysRemainingJTexField1);

		// ROW3
		TargetProductionJTexField2 = new JTextField();
		ProductionJTexField2 = new JTextField();
		PlusMinusJTexField2 = new JTextField();
		MaintenanceDueDateJTexField2 = new JTextField();
		DaysRemainingJTexField2 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(toolingAreaLabel2);
		middlePanel.add(LastMaintenanceDate2);
		middlePanel.add(MaintenanceTypeLabel2);
		middlePanel.add(TargetProductionJTexField2);
		middlePanel.add(ProductionJTexField2);
		middlePanel.add(PlusMinusJTexField2);
		middlePanel.add(MaintenanceDueDate2);
		middlePanel.add(DaysRemainingJTexField2);

		// ROW4
		TargetProductionJTexField3 = new JTextField();
		ProductionJTexField3 = new JTextField();
		PlusMinusJTexField3 = new JTextField();
		MaintenanceDueDateJTexField3 = new JTextField();
		DaysRemainingJTexField3 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(toolingAreaLabel3);
		middlePanel.add(LastMaintenanceDate3);
		middlePanel.add(MaintenanceTypeLabel3);
		middlePanel.add(TargetProductionJTexField3);
		middlePanel.add(ProductionJTexField3);
		middlePanel.add(PlusMinusJTexField3);
		middlePanel.add(MaintenanceDueDate3);
		middlePanel.add(DaysRemainingJTexField3);
		// ROW5
		TargetProductionJTexField4 = new JTextField();
		ProductionJTexField4 = new JTextField();
		PlusMinusJTexField4 = new JTextField();
		MaintenanceDueDateJTexField4 = new JTextField();
		DaysRemainingJTexField4 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(toolingAreaLabel4);
		middlePanel.add(LastMaintenanceDate4);
		middlePanel.add(MaintenanceTypeLabel4);
		middlePanel.add(TargetProductionJTexField4);
		middlePanel.add(ProductionJTexField4);
		middlePanel.add(PlusMinusJTexField4);
		middlePanel.add(MaintenanceDueDate4);
		middlePanel.add(DaysRemainingJTexField4);
		// ROW6
		TargetProductionJTexField5 = new JTextField();
		ProductionJTexField5 = new JTextField();
		PlusMinusJTexField5 = new JTextField();
		MaintenanceDueDateJTexField5 = new JTextField();
		DaysRemainingJTexField5 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(toolingAreaLabel5);
		middlePanel.add(LastMaintenanceDate5);
		middlePanel.add(MaintenanceTypeLabel5);
		middlePanel.add(TargetProductionJTexField5);
		middlePanel.add(ProductionJTexField5);
		middlePanel.add(PlusMinusJTexField5);
		middlePanel.add(MaintenanceDueDate5);
		middlePanel.add(DaysRemainingJTexField5);

		// ROW7
		TargetProductionJTexField6 = new JTextField();
		ProductionJTexField6 = new JTextField();
		PlusMinusJTexField6 = new JTextField();
		MaintenanceDueDateJTexField6 = new JTextField();
		DaysRemainingJTexField6 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(toolingAreaLabel6);
		middlePanel.add(LastMaintenanceDate6);
		middlePanel.add(MaintenanceTypeLabel6);
		middlePanel.add(TargetProductionJTexField6);
		middlePanel.add(ProductionJTexField6);
		middlePanel.add(PlusMinusJTexField6);
		middlePanel.add(MaintenanceDueDate6);
		middlePanel.add(DaysRemainingJTexField6);

		// ROW8
		TargetProductionJTexFieldTotal = new JTextField();
		ProductionJTexFieldTotal = new JTextField();
		PlusMinusJTexFieldTotal = new JTextField();
		MaintenanceDueDateJTexFieldTotal = new JTextField();
		DaysRemainingJTexFieldTotal = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" ", JLabel.CENTER));
		middlePanel.add(LastMaintenanceDate7);
		middlePanel.add(MaintenanceTypeLabel7);
		middlePanel.add(TargetProductionJTexFieldTotal);
		middlePanel.add(ProductionJTexFieldTotal);
		middlePanel.add(PlusMinusJTexFieldTotal);
		middlePanel.add(MaintenanceDueDate7);
		middlePanel.add(DaysRemainingJTexFieldTotal);

		return middlePanel;
	}

	private JPanel createBottomPanel(int spIn) {

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.GRAY);

		update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (MachineCodeJTexField1.getText().equals("SP01")) {
					updateEntry(1);
				}
				if (MachineCodeJTexField1.getText().equals("SP02")) {
					updateEntry(2);
				}
				if (MachineCodeJTexField1.getText().equals("SP03")) {
					updateEntry(3);
				}
				if (MachineCodeJTexField1.getText().equals("SP04")) {
					updateEntry(4);
				}

			}

		});

		bottomPanel.add(update);

		return bottomPanel;

	}

	private void updateEntry(int machineCodeIn) {

		// setDaysRemaining();

		sm = new ShellPressMaintenanceModel();

		sm.setMachineCode(MachineCodeJTexField1.getText());
		sm.setMachineName(MachineNameJTexField1.getText());

		sm.setLastMaintenanceDate1(LastMaintenanceDate1.getDate());
		sm.setLastMaintenanceDate2(LastMaintenanceDate2.getDate());
		sm.setLastMaintenanceDate3(LastMaintenanceDate3.getDate());
		sm.setLastMaintenanceDate4(LastMaintenanceDate4.getDate());
		sm.setLastMaintenanceDate5(LastMaintenanceDate5.getDate());
		sm.setLastMaintenanceDate6(LastMaintenanceDate6.getDate());
		sm.setLastMaintenanceDate7(LastMaintenanceDate7.getDate());

		sm.setMaintenanceDueDate1(MaintenanceDueDate1.getDate());
		sm.setMaintenanceDueDate2(MaintenanceDueDate2.getDate());
		sm.setMaintenanceDueDate3(MaintenanceDueDate3.getDate());
		sm.setMaintenanceDueDate4(MaintenanceDueDate4.getDate());
		sm.setMaintenanceDueDate5(MaintenanceDueDate5.getDate());
		sm.setMaintenanceDueDate6(MaintenanceDueDate6.getDate());
		sm.setMaintenanceDueDate7(MaintenanceDueDate7.getDate());

		sm.setTargetProduction1(Integer.valueOf(TargetProductionJTexField1.getText()));
		sm.setTargetProduction2(Integer.valueOf(TargetProductionJTexField2.getText()));
		sm.setTargetProduction3(Integer.valueOf(TargetProductionJTexField3.getText()));
		sm.setTargetProduction4(Integer.valueOf(TargetProductionJTexField4.getText()));
		sm.setTargetProduction5(Integer.valueOf(TargetProductionJTexField5.getText()));
		sm.setTargetProduction6(Integer.valueOf(TargetProductionJTexField6.getText()));
		sm.setTargetProduction7(Integer.valueOf(TargetProductionJTexFieldTotal.getText()));

		shellPressMaintenanceDAO.shellPressMaintenanceUpdate(sm);

		dispose();
		new ShellPressMaintenanceView(1);

	}

	private void setDatesSp01() {

		DaysRemainingJTexField1.setText(4+"");
		DaysRemainingJTexField2.setText(6+"");
		DaysRemainingJTexField3.setText(2+"");
		DaysRemainingJTexField4.setText(54+"");
		DaysRemainingJTexField5.setText(14+"");
		DaysRemainingJTexField6.setText(23+"");
		DaysRemainingJTexFieldTotal.setText(76+"");

		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexField5.setEditable(false);
		DaysRemainingJTexField6.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		PlusMinusJTexField1.setEditable(false);
		PlusMinusJTexField2.setEditable(false);
		PlusMinusJTexField3.setEditable(false);
		PlusMinusJTexField4.setEditable(false);
		PlusMinusJTexField5.setEditable(false);
		PlusMinusJTexField6.setEditable(false);
		PlusMinusJTexFieldTotal.setEditable(false);
		

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		LastMaintenanceDate1.setDate(c1.getTime());
		LastMaintenanceDate2.setDate(c1.getTime());
		LastMaintenanceDate3.setDate(c1.getTime());
		LastMaintenanceDate4.setDate(c1.getTime());
		LastMaintenanceDate5.setDate(c1.getTime());
		LastMaintenanceDate6.setDate(c1.getTime());
		LastMaintenanceDate7.setDate(c1.getTime());
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());
		MaintenanceDueDate5.setDate(c1.getTime());
		MaintenanceDueDate6.setDate(c1.getTime());
		MaintenanceDueDate7.setDate(c1.getTime());

	}

	private void setDatesSp04() {

		Long daysRemaining1Int, daysRemaining2Int, daysRemaining7Int;

		daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText() + 1) / 11888640L);
		daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText() + 1) / 11888640L);
		daysRemaining7Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText() + 1) / 12879360L);

		DaysRemainingJTexField1.setText(daysRemaining1Int + "");
		DaysRemainingJTexField2.setText(daysRemaining2Int + "");
		DaysRemainingJTexFieldTotal.setText(daysRemaining7Int + "");

		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		PlusMinusJTexField1.setEditable(false);
		PlusMinusJTexField2.setEditable(false);
		PlusMinusJTexFieldTotal.setEditable(false);

		int days1, days2, days7;

		days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
		days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
		days7 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());

		Date date1 = LastMaintenanceDate1.getDate();
		Date date2 = LastMaintenanceDate2.getDate();
		Date date7 = LastMaintenanceDate7.getDate();

		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1); // Now use today date.
		c1.add(Calendar.DATE, days1); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2); // Now use today date.
		c2.add(Calendar.DATE, days2); // Adding 5 days
		MaintenanceDueDate2.setDate(c2.getTime());

		Calendar c7 = Calendar.getInstance();
		c7.setTime(date7); // Now use today date.
		c7.add(Calendar.DATE, days7); // Adding 5 days
		MaintenanceDueDate7.setDate(c7.getTime());

	}

	private void setToSP04() {

		sm = new ShellPressMaintenanceModel();

		try {
			sm = shellPressMaintenanceDAO.shellPressMaintenanceReturnEntryByID(4);
		} catch (Exception ex) {
			Logger.getLogger(ShellPressMaintenanceView.class.getName()).log(Level.SEVERE, null, ex);
		}

		MachineCodeJTexField1.setText(sm.getId() + "");
		MachineNameJTexField1.setText(sm.getMachineName() + "");

		toolingAreaLabel1.setText("Zone 1");
		toolingAreaLabel2.setText("Zone 2");

		toolingAreaLabel3.setVisible(false);
		toolingAreaLabel4.setVisible(false);
		toolingAreaLabel5.setVisible(false);
		toolingAreaLabel6.setVisible(false);

		MaintenanceTypeLabel3.setVisible(false);
		MaintenanceTypeLabel4.setVisible(false);
		MaintenanceTypeLabel5.setVisible(false);
		MaintenanceTypeLabel6.setVisible(false);

		TargetProductionJTexField3.setVisible(false);
		ProductionJTexField3.setVisible(false);
		PlusMinusJTexField3.setVisible(false);
		MaintenanceDueDateJTexField3.setVisible(false);
		DaysRemainingJTexField3.setVisible(false);

		TargetProductionJTexField4.setVisible(false);
		ProductionJTexField4.setVisible(false);
		PlusMinusJTexField4.setVisible(false);
		MaintenanceDueDateJTexField4.setVisible(false);
		DaysRemainingJTexField4.setVisible(false);

		TargetProductionJTexField5.setVisible(false);
		ProductionJTexField5.setVisible(false);
		PlusMinusJTexField5.setVisible(false);
		MaintenanceDueDateJTexField5.setVisible(false);
		DaysRemainingJTexField5.setVisible(false);

		TargetProductionJTexField6.setVisible(false);
		ProductionJTexField6.setVisible(false);
		PlusMinusJTexField6.setVisible(false);
		MaintenanceDueDateJTexField6.setVisible(false);
		DaysRemainingJTexField6.setVisible(false);

		LastMaintenanceDate3.setVisible(false);
		LastMaintenanceDate4.setVisible(false);
		LastMaintenanceDate5.setVisible(false);
		LastMaintenanceDate6.setVisible(false);

		MaintenanceDueDate3.setVisible(false);
		MaintenanceDueDate4.setVisible(false);
		MaintenanceDueDate5.setVisible(false);
		MaintenanceDueDate6.setVisible(false);

		MachineCodeJTexField1.setText(sm.getMachineCode());
		MachineNameJTexField1.setText(sm.getMachineName());

		LastMaintenanceDate1.setDate(sm.getLastMaintenanceDate1());
		TargetProductionJTexField1.setText(sm.getTargetProduction1() + "");
		ProductionJTexField1.setText(sm.getProduction1() + "");
		PlusMinusJTexField1.setText((sm.getTargetProduction1() - sm.getProduction1()) + "");
		MaintenanceDueDate1.setDate(sm.getMaintenanceDueDate1());
		DaysRemainingJTexField1.setText(0 + "");

		LastMaintenanceDate2.setDate(sm.getLastMaintenanceDate2());
		TargetProductionJTexField2.setText(sm.getTargetProduction2() + "");
		ProductionJTexField2.setText(sm.getProduction2() + "");
		PlusMinusJTexField2.setText((sm.getTargetProduction2() - sm.getProduction2()) + "");
		MaintenanceDueDate2.setDate(sm.getMaintenanceDueDate2());
		DaysRemainingJTexField2.setText(0 + "");

		LastMaintenanceDate7.setDate(sm.getLastMaintenanceDate7());
		MaintenanceDueDate7.setDate(sm.getMaintenanceDueDate7());

		optionsPanel.repaint();

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
