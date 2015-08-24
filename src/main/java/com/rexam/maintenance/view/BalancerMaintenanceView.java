package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.rexam.maintenance.dao.BalancerMaintenanceDAO;
import com.rexam.maintenance.dao.ShellPressMaintenanceDAO;
import com.rexam.maintenance.model.BalancerMaintenanceModel;
import com.rexam.program.view.Desktop;
import com.toedter.calendar.JDateChooser;

public class BalancerMaintenanceView extends JFrame {

	private JPanel outerPanel, optionsPanel;
	private JFrame outerFrame;
	private int currentId;
	private JButton Balancer1A, Balancer2A, Balancer3A, Balancer4A, Balancer4ANew, Balancer1B, Balancer2B, Balancer3B,
			Balancer4B, update;
	JLabel toolingAreaLabel1, toolingAreaLabel2, toolingAreaLabel3, toolingAreaLabel4, toolingAreaLabel5,
			toolingAreaLabel6, toolingAreaLabel7;
	JLabel MaintenanceTypeLabel1, MaintenanceTypeLabel2, MaintenanceTypeLabel3, MaintenanceTypeLabel4,
			MaintenanceTypeLabel5, MaintenanceTypeLabel6, MaintenanceTypeLabel7;
	JTextField MachineCodeJTexField1, MachineNameJTexField1, TargetProductionJTexField1, ProductionJTexField1,
			PlusMinusJTexField1, MaintenanceDueDateJTexField1, DaysRemainingJTexField1;
	JTextField MachineCodeJTexField2, MachineNameJTexField2, TargetProductionJTexField2, ProductionJTexField2,
			PlusMinusJTexField2, MaintenanceDueDateJTexField2, DaysRemainingJTexField2;
	JTextField MachineCodeJTexField3, MachineNameJTexField3, TargetProductionJTexField3, ProductionJTexField3,
			PlusMinusJTexField3, MaintenanceDueDateJTexField3, DaysRemainingJTexField3;
	JTextField MachineCodeJTexField4, MachineNameJTexField4, TargetProductionJTexField4, ProductionJTexField4,
			PlusMinusJTexField4, MaintenanceDueDateJTexField4, DaysRemainingJTexField4;
	JTextField MachineCodeJTexField5, MachineNameJTexField5, TargetProductionJTexField5, ProductionJTexField5,
			PlusMinusJTexField5, MaintenanceDueDateJTexField5, DaysRemainingJTexField5;
	JTextField MachineCodeJTexField6, MachineNameJTexField6, TargetProductionJTexField6, ProductionJTexField6,
			PlusMinusJTexField6, MaintenanceDueDateJTexField6, DaysRemainingJTexField6;
	JTextField MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal, TargetProductionJTexFieldTotal,
			ProductionJTexFieldTotal, PlusMinusJTexFieldTotal, MaintenanceDueDateJTexFieldTotal,
			DaysRemainingJTexFieldTotal;

	private JDateChooser LastMaintenanceDate1, LastMaintenanceDate2, LastMaintenanceDate3, LastMaintenanceDate4,
			LastMaintenanceDate5, LastMaintenanceDate6, LastMaintenanceDate7, MaintenanceDueDate1, MaintenanceDueDate2,
			MaintenanceDueDate3, MaintenanceDueDate4, MaintenanceDueDate5, MaintenanceDueDate6, MaintenanceDueDate7;

	private BalancerMaintenanceModel bm;
	private MaintenanceDatabaseController db;
	
	private BalancerMaintenanceDAO balancerMaintenanceDAO;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new BalancerMaintenanceView(1);

	}

	public BalancerMaintenanceView(int spIn) {
		
		setStyle();

		bm = new BalancerMaintenanceModel();
		db = new MaintenanceDatabaseController();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		balancerMaintenanceDAO = (BalancerMaintenanceDAO) context.getBean("BalancerMaintenanceDAO");

		setTitle("Balancer Maintenance");
		setSize(1400, 350);
		setLocationRelativeTo(null);

		outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
		outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
		outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);

		setToMachineCode(1);

		add(outerPanel);
		setVisible(true);

		// SQLiteConnection.AnalyticsUpdate("BalancerMaintenance");

		PlusMinusJTexField1.setEditable(false);
		PlusMinusJTexField2.setEditable(false);
		PlusMinusJTexField3.setEditable(false);
		PlusMinusJTexField4.setEditable(false);
		PlusMinusJTexFieldTotal.setEditable(false);

	}

	private JPanel createOptionsPanel(int spIn) {

		optionsPanel = new JPanel(new FlowLayout());
		optionsPanel.setBackground(Color.GRAY);

		Balancer1A = new JButton("BAL 1A");
		Balancer1A.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(1);
				updateDatesToBal1A();
			}
		});
		Balancer2A = new JButton("BAL 2A");
		Balancer2A.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(2);
				updateDatesToBal1A();
			}
		});
		Balancer3A = new JButton("BAL 3A");
		Balancer3A.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(3);
				updateDatesToBal3A();
			}
		});
		Balancer4A = new JButton("BAL 4A");
		Balancer4A.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(4);
				updateDatesToBal4A();
			}
		});
		Balancer4ANew = new JButton("BAL 4A New");
		Balancer4ANew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(5);
			}
		});
		Balancer1B = new JButton("BAL 1B");
		Balancer1B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(6);
				updateDatesToBal1B();
			}
		});
		Balancer2B = new JButton("BAL 2B");
		Balancer2B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(7);
				updateDatesToBal1B();
			}
		});
		Balancer3B = new JButton("BAL 3B");
		Balancer3B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(8);
				updateDatesToBal3B();
			}
		});
		Balancer4B = new JButton("BAL 4B");
		Balancer4B.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(9);
				updateDatesToBal3B();
			}
		});

		optionsPanel.add(Balancer1A);
		optionsPanel.add(Balancer2A);
		optionsPanel.add(Balancer3A);
		optionsPanel.add(Balancer4A);
		optionsPanel.add(Balancer4ANew);
		optionsPanel.add(Balancer1B);
		optionsPanel.add(Balancer2B);
		optionsPanel.add(Balancer3B);
		optionsPanel.add(Balancer4B);

		return optionsPanel;

	}

	private JPanel createBottomPanel(int spIn) {

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.GRAY);

		update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.print(MachineCodeJTexField1.getText() + "");

				if (MachineCodeJTexField1.getText().equals("BAL1A")) {
					updateEntry(1);
					currentId = 1;

				}
				if (MachineCodeJTexField1.getText().equals("BAL2A")) {
					updateEntry(2);
					currentId = 2;
				}
				if (MachineCodeJTexField1.getText().equals("BAL3A")) {
					updateEntry(3);
					currentId = 3;
				}
				if (MachineCodeJTexField1.getText().equals("BAL4A")) {
					updateEntry(4);
					currentId = 4;
				}
				if (MachineCodeJTexField1.getText().equals("BAL4ANEW")) {
					updateEntry(5);
					currentId = 5;
				}
				if (MachineCodeJTexField1.getText().equals("BAL1B")) {
					updateEntry(6);
					currentId = 6;
				}
				if (MachineCodeJTexField1.getText().equals("BAL2B")) {
					updateEntry(7);
					currentId = 7;
				}
				if (MachineCodeJTexField1.getText().equals("BAL3B")) {
					updateEntry(8);
					currentId = 8;
				}
				if (MachineCodeJTexField1.getText().equals("BAL4B")) {
					updateEntry(9);
					currentId = 9;
				}

				setToMachineCode(currentId);

			}

		});

		bottomPanel.add(update);

		return bottomPanel;

	}

	private JPanel createMiddlePanel(int spIn) {

		toolingAreaLabel1 = new JLabel("Infeed", JLabel.CENTER);
		toolingAreaLabel2 = new JLabel("OutFeed", JLabel.CENTER);
		toolingAreaLabel3 = new JLabel("PickUp Heads", JLabel.CENTER);
		toolingAreaLabel4 = new JLabel("Lifts / Tray Movement", JLabel.CENTER);
		toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

		MaintenanceTypeLabel1 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel2 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel3 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel4 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);

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

		JPanel middlePanel = new JPanel(new GridLayout(6, 10));
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

	private void updateEntry(int machineCodeIn) {

		// setDaysRemaining();

		bm = new BalancerMaintenanceModel();

		bm.setMachineCode(MachineCodeJTexField1.getText());
		bm.setMachineName(MachineNameJTexField1.getText());

		bm.setLastMaintenanceDate1(LastMaintenanceDate1.getDate());
		bm.setLastMaintenanceDate2(LastMaintenanceDate2.getDate());
		bm.setLastMaintenanceDate3(LastMaintenanceDate3.getDate());
		bm.setLastMaintenanceDate4(LastMaintenanceDate4.getDate());
		bm.setLastMaintenanceDate5(LastMaintenanceDate5.getDate());
		bm.setLastMaintenanceDate6(LastMaintenanceDate6.getDate());
		bm.setLastMaintenanceDate7(LastMaintenanceDate7.getDate());

		bm.setMaintenanceDueDate1(MaintenanceDueDate1.getDate());
		bm.setMaintenanceDueDate2(MaintenanceDueDate2.getDate());
		bm.setMaintenanceDueDate3(MaintenanceDueDate3.getDate());
		bm.setMaintenanceDueDate4(MaintenanceDueDate4.getDate());
		bm.setMaintenanceDueDate5(MaintenanceDueDate5.getDate());
		bm.setMaintenanceDueDate6(MaintenanceDueDate6.getDate());
		bm.setMaintenanceDueDate7(MaintenanceDueDate7.getDate());

		bm.setTargetProduction1(Integer.valueOf(TargetProductionJTexField1.getText()));
		bm.setTargetProduction2(Integer.valueOf(TargetProductionJTexField2.getText()));
		bm.setTargetProduction3(Integer.valueOf(TargetProductionJTexField3.getText()));
		bm.setTargetProduction4(Integer.valueOf(TargetProductionJTexField4.getText()));
		bm.setTargetProduction5(Integer.valueOf(TargetProductionJTexField5.getText()));
		bm.setTargetProduction6(Integer.valueOf(TargetProductionJTexField6.getText()));
		bm.setTargetProduction7(Integer.valueOf(TargetProductionJTexFieldTotal.getText()));

		balancerMaintenanceDAO.balancerMaintenanceUpdate(bm);

		dispose();
		new ShellPressMaintenanceView(1);

	}

	private void setToSP04() {

		try {
			bm = balancerMaintenanceDAO.balancerMaintenanceReturnEntryByID(4);
		} catch (Exception ex) {
			Logger.getLogger(BalancerMaintenanceView.class.getName()).log(Level.SEVERE, null, ex);
		}

		MachineCodeJTexField1.setText("SP04");
		MachineNameJTexField1.setText("Formatec 04");

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

		LastMaintenanceDate3.setVisible(false);
		LastMaintenanceDate4.setVisible(false);

		MaintenanceDueDate3.setVisible(false);
		MaintenanceDueDate4.setVisible(false);

		MachineCodeJTexField1.setText(bm.getMachineCode());
		MachineNameJTexField1.setText(bm.getMachineName());

		LastMaintenanceDate1.setDate(bm.getLastMaintenanceDate1());
		TargetProductionJTexField1.setText(bm.getTargetProduction1() + "");
		ProductionJTexField1.setText(bm.getProduction1() + "");
		PlusMinusJTexField1.setText((bm.getTargetProduction1() - bm.getProduction1()) + "");
		MaintenanceDueDate1.setDate(bm.getMaintenanceDueDate1());
		DaysRemainingJTexField1.setText(0 + "");

		LastMaintenanceDate2.setDate(bm.getLastMaintenanceDate2());
		TargetProductionJTexField2.setText(bm.getTargetProduction2() + "");
		ProductionJTexField2.setText(bm.getProduction2() + "");
		PlusMinusJTexField2.setText((bm.getTargetProduction2() - bm.getProduction2()) + "");
		MaintenanceDueDate2.setDate(bm.getMaintenanceDueDate2());
		DaysRemainingJTexField2.setText(0 + "");

		LastMaintenanceDate7.setDate(bm.getLastMaintenanceDate7());
		MaintenanceDueDate7.setDate(bm.getMaintenanceDueDate7());

		optionsPanel.repaint();

	}

	private void setToMachineCode(int machineCodeIn) {

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

		LastMaintenanceDate3.setVisible(true);
		LastMaintenanceDate4.setVisible(true);

		MaintenanceDueDate3.setVisible(true);
		MaintenanceDueDate4.setVisible(true);

		toolingAreaLabel3.setVisible(true);
		toolingAreaLabel4.setVisible(true);

		MaintenanceTypeLabel3.setVisible(true);
		MaintenanceTypeLabel4.setVisible(true);

		toolingAreaLabel1 = new JLabel("Infeed", JLabel.CENTER);
		toolingAreaLabel2 = new JLabel("OutFeed", JLabel.CENTER);
		toolingAreaLabel3 = new JLabel("PickUp Heads", JLabel.CENTER);
		toolingAreaLabel4 = new JLabel("Lifts / Tray Movement", JLabel.CENTER);
		toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

		MaintenanceTypeLabel1 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel2 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel3 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel4 = new JLabel("6 Hour", JLabel.CENTER);
		MaintenanceTypeLabel7 = new JLabel("Annual", JLabel.CENTER);

		toolingAreaLabel3.setVisible(true);
		toolingAreaLabel4.setVisible(true);

		optionsPanel.repaint();

		try {

			bm = balancerMaintenanceDAO.balancerMaintenanceReturnEntryByID(machineCodeIn);

			currentId = (int) bm.getID();

			MachineCodeJTexField1.setText(bm.getMachineCode());
			MachineNameJTexField1.setText(bm.getMachineName());
			LastMaintenanceDate1.setDate(bm.getLastMaintenanceDate1());
			TargetProductionJTexField1.setText(bm.getTargetProduction1() + "");
			ProductionJTexField1.setText(bm.getProduction1() + "");
			PlusMinusJTexField1.setText(((int) bm.getTargetProduction1() - (int) bm.getProduction1()) + "");
			MaintenanceDueDate1.setDate(bm.getMaintenanceDueDate1());
			DaysRemainingJTexField1.setText("4");

//			MachineCodeJTexField2.setText(bm.getMachineCode());
//			MachineNameJTexField2.setText(bm.getMachineName());
			LastMaintenanceDate2.setDate(bm.getLastMaintenanceDate2());
			TargetProductionJTexField2.setText(bm.getTargetProduction2() + "");
			ProductionJTexField2.setText(bm.getProduction2() + "");
			PlusMinusJTexField2.setText(((int) bm.getTargetProduction2() - (int) bm.getProduction2()) + "");
			MaintenanceDueDate2.setDate(bm.getMaintenanceDueDate1());
			DaysRemainingJTexField1.setText("5");

//			MachineCodeJTexField3.setText(bm.getMachineCode());
//			MachineNameJTexField3.setText(bm.getMachineName());
			LastMaintenanceDate3.setDate(bm.getLastMaintenanceDate3());
			TargetProductionJTexField3.setText(bm.getTargetProduction3() + "");
			ProductionJTexField3.setText(bm.getProduction3() + "");
			PlusMinusJTexField3.setText(((int) bm.getTargetProduction3() - (int) bm.getProduction3()) + "");
			MaintenanceDueDate3.setDate(bm.getMaintenanceDueDate3());
			DaysRemainingJTexField1.setText("14");

//			MachineCodeJTexField4.setText(bm.getMachineCode());
//			MachineNameJTexField4.setText(bm.getMachineName());
			LastMaintenanceDate4.setDate(bm.getLastMaintenanceDate4());
			TargetProductionJTexField4.setText(bm.getTargetProduction4() + "");
			ProductionJTexField4.setText(bm.getProduction4() + "");
			PlusMinusJTexField4.setText(((int) bm.getTargetProduction4() - (int) bm.getProduction4()) + "");
			MaintenanceDueDate4.setDate(bm.getMaintenanceDueDate4());
			DaysRemainingJTexField4.setText("9");

//			MachineCodeJTexField5.setText(bm.getMachineCode());
//			MachineNameJTexField5.setText(bm.getMachineName());
			LastMaintenanceDate5.setDate(bm.getLastMaintenanceDate5());
			TargetProductionJTexField5.setText(bm.getTargetProduction5() + "");
			ProductionJTexField5.setText(bm.getProduction5() + "");
			PlusMinusJTexField5.setText(((int) bm.getTargetProduction5() - (int) bm.getProduction5()) + "");
			MaintenanceDueDate5.setDate(bm.getMaintenanceDueDate5());
			DaysRemainingJTexField5.setText("5");

//			MachineCodeJTexField6.setText(bm.getMachineCode());
//			MachineNameJTexField6.setText(bm.getMachineName());
			LastMaintenanceDate6.setDate(bm.getLastMaintenanceDate6());
			TargetProductionJTexField6.setText(bm.getTargetProduction6() + "");
			ProductionJTexField6.setText(bm.getProduction6() + "");
			PlusMinusJTexField6.setText(((int) bm.getTargetProduction6() - (int) bm.getProduction6()) + "");
			MaintenanceDueDate6.setDate(bm.getMaintenanceDueDate6());
			DaysRemainingJTexField6.setText("4");

			// MachineCodeJTexFieldTotal.setText(bm.getMachineCode());
			// MachineNameJTexFieldTotal.setText(bm.getMachineName());
			// LastMaintenanceDate7.setDate(bm.getLastMaintenanceDate1());
			// TargetProductionJTexField1.setText(bm.getTargetProduction1()+"");
			// ProductionJTexField1.setText(bm.getProduction1()+"");
			// PlusMinusJTexField1.setText(((int) bm.getTargetProduction1() -
			// (int) bm.getProduction1()) + "");
			// MaintenanceDueDate1.setDate(bm.getMaintenanceDueDate1());
			// DaysRemainingJTexField1.setText("4");

		} catch (Exception ex) {
			Logger.getLogger(BalancerMaintenanceView.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void updateDatesToBal1A() {

		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());
		MaintenanceDueDate5.setDate(c1.getTime());
		MaintenanceDueDate6.setDate(c1.getTime());
		MaintenanceDueDate7.setDate(c1.getTime());

	}

	private void updateDatesToBal3A() {



		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());

		

	}

	private void updateDatesToBal4A() {



		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());
		MaintenanceDueDate5.setDate(c1.getTime());
		MaintenanceDueDate6.setDate(c1.getTime());
		MaintenanceDueDate7.setDate(c1.getTime());
		

	}

	private void updateDatesToBal1B() {

		
		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());
		MaintenanceDueDate5.setDate(c1.getTime());

	}

	private void updateDatesToBal3B() {
		

		DaysRemainingJTexField1.setEditable(false);
		DaysRemainingJTexField2.setEditable(false);
		DaysRemainingJTexField3.setEditable(false);
		DaysRemainingJTexField4.setEditable(false);
		DaysRemainingJTexFieldTotal.setEditable(false);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date()); // Now use today date.
		c1.add(Calendar.DATE, 5); // Adding 5 days
		MaintenanceDueDate1.setDate(c1.getTime());
		MaintenanceDueDate2.setDate(c1.getTime());
		MaintenanceDueDate3.setDate(c1.getTime());
		MaintenanceDueDate4.setDate(c1.getTime());
		MaintenanceDueDate5.setDate(c1.getTime());

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
