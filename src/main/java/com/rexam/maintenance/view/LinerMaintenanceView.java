package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.rexam.maintenance.dao.LinerMaintenanceDAO;
import com.rexam.maintenance.model.LinerMaintenanceModel;
import com.toedter.calendar.JDateChooser;

public class LinerMaintenanceView extends JFrame {

	Date lastMaintenanceDate1, lastMaintenanceDate2, lastMaintenanceDate3, maintenanceDueDate1, maintenanceDueDate2,
			maintenanceDueDate3;
	JButton L11Button, L12Button, L13Button, L14Button, L21Button, L22Button, L23Button, L24Button, L31Button,
			L32Button, L33Button, L34Button, L41Button, L42Button, L43Button, L44Button, update;
	JLabel toolingAreaLabel1, toolingAreaLabel2, toolingAreaLabel3, toolingAreaLabel4, toolingAreaLabel5,
			toolingAreaLabel6, toolingAreaLabel7;
	JLabel MaintenanceTypeLabel1, MaintenanceTypeLabel2, MaintenanceTypeLabel3, MaintenanceTypeLabel4,
			MaintenanceTypeLabel5, MaintenanceTypeLabel6, MaintenanceTypeLabel7;
	int ID, production1, production2, production3, targetProduction1, targetProduction2, targetProduction3;
	String machineCode, machineName;
	JPanel optionsPanel, outerPanel;
	JDateChooser chooser1, chooser2, chooser3, chooser4, chooser5, chooser6;
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
	int currentId;

	LinerMaintenanceModel lm;
	
	private LinerMaintenanceDAO linerMaintenanceDAO;

	public static void main(String[] args) {

		new LinerMaintenanceView(1);

	}

	public LinerMaintenanceView(int spIn) {
		
		setStyle();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		linerMaintenanceDAO = (LinerMaintenanceDAO) context.getBean("LinerMaintenanceDAO");

		setTitle("Liner Maintenance");
		setSize(1400, 300);
		setLocationRelativeTo(null);

		outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		outerPanel.add(createOptionsPanel(spIn), BorderLayout.NORTH);
		outerPanel.add(createMiddlePanel(spIn), BorderLayout.CENTER);
		outerPanel.add(createBottomPanel(spIn), BorderLayout.SOUTH);

		setToMachineCode(1);

		add(outerPanel);
		setVisible(true);

		// SQLiteConnection.AnalyticsUpdate("LinerMaintenance");

	}

	private JPanel createOptionsPanel(int spIn) {

		initializeVariables();

		optionsPanel.add(L11Button);
		optionsPanel.add(L12Button);
		optionsPanel.add(L13Button);
		optionsPanel.add(L14Button);
		optionsPanel.add(L21Button);
		optionsPanel.add(L22Button);
		optionsPanel.add(L23Button);
		optionsPanel.add(L24Button);
		optionsPanel.add(L31Button);
		optionsPanel.add(L32Button);
		optionsPanel.add(L33Button);
		optionsPanel.add(L34Button);
		optionsPanel.add(L41Button);
		optionsPanel.add(L42Button);
		optionsPanel.add(L43Button);
		optionsPanel.add(L44Button);

		return optionsPanel;

	}

	private void initializeVariables() {

		lm = new LinerMaintenanceModel();

		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());
		chooser3 = new JDateChooser(new Date());
		chooser4 = new JDateChooser(new Date());
		chooser5 = new JDateChooser(new Date());
		chooser6 = new JDateChooser(new Date());

		optionsPanel = new JPanel(new FlowLayout());
		optionsPanel.setBackground(Color.GRAY);

		L11Button = new JButton("L11");
		L11Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(1);
			}
		});
		L12Button = new JButton("L12");
		L12Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(2);
			}
		});
		L13Button = new JButton("L13");
		L13Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(3);
			}
		});
		L14Button = new JButton("L14");
		L14Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(4);
			}
		});
		L21Button = new JButton("L21");
		L21Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(5);
			}
		});
		L22Button = new JButton("L22");
		L22Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(6);
			}
		});
		L23Button = new JButton("L23");
		L23Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(7);
			}
		});
		L24Button = new JButton("L24");
		L24Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(8);
			}
		});
		L31Button = new JButton("L31");
		L31Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(9);
			}
		});
		L32Button = new JButton("L32");
		L32Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(10);
			}
		});
		L33Button = new JButton("L33");
		L33Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(11);
			}
		});
		L34Button = new JButton("L34");
		L34Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(12);
			}
		});
		L41Button = new JButton("L41");
		L41Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(13);
			}
		});
		L42Button = new JButton("L42");
		L42Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(14);
			}
		});
		L43Button = new JButton("L43");
		L43Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(15);
			}
		});
		L44Button = new JButton("L44");
		L44Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setToMachineCode(16);
			}
		});

		toolingAreaLabel1 = new JLabel(" ", JLabel.CENTER);
		toolingAreaLabel2 = new JLabel(" ", JLabel.CENTER);
		toolingAreaLabel3 = new JLabel(" ", JLabel.CENTER);
		toolingAreaLabel4 = new JLabel(" ", JLabel.CENTER);
		toolingAreaLabel6 = new JLabel(" ", JLabel.CENTER);

		MaintenanceTypeLabel1 = new JLabel("Gun Rebuild", JLabel.CENTER);
		MaintenanceTypeLabel2 = new JLabel("Annual", JLabel.CENTER);
		MaintenanceTypeLabel3 = new JLabel(" ", JLabel.CENTER);
		MaintenanceTypeLabel4 = new JLabel(" ", JLabel.CENTER);
		MaintenanceTypeLabel7 = new JLabel("Bi-Annual", JLabel.CENTER);
	}

	private JPanel createBottomPanel(int spIn) {

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.GRAY);

		update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.print(MachineCodeJTexField1.getText() + "");

				if (MachineCodeJTexField1.getText().equals("L11")) {
					updateEntry(1);
					currentId = 1;

				}
				if (MachineCodeJTexField1.getText().equals("L12")) {
					updateEntry(2);
					currentId = 1;
				}
				if (MachineCodeJTexField1.getText().equals("L13")) {
					updateEntry(3);
					currentId = 3;
				}
				if (MachineCodeJTexField1.getText().equals("L14")) {
					updateEntry(4);
					currentId = 4;
				}
				if (MachineCodeJTexField1.getText().equals("L21")) {
					updateEntry(5);
					currentId = 5;
				}
				if (MachineCodeJTexField1.getText().equals("L22")) {
					updateEntry(6);
					currentId = 6;
				}
				if (MachineCodeJTexField1.getText().equals("L23")) {
					updateEntry(7);
					currentId = 7;
				}
				if (MachineCodeJTexField1.getText().equals("L24")) {
					updateEntry(8);
					currentId = 8;
				}
				if (MachineCodeJTexField1.getText().equals("L31")) {
					updateEntry(9);
					currentId = 9;
				}
				if (MachineCodeJTexField1.getText().equals("L32")) {
					updateEntry(10);
					currentId = 10;
				}
				if (MachineCodeJTexField1.getText().equals("L33")) {
					updateEntry(11);
					currentId = 11;
				}
				if (MachineCodeJTexField1.getText().equals("L34")) {
					updateEntry(12);
					currentId = 12;
				}
				if (MachineCodeJTexField1.getText().equals("L41")) {
					updateEntry(13);
					currentId = 13;
				}
				if (MachineCodeJTexField1.getText().equals("L42")) {
					updateEntry(14);
					currentId = 14;
				}
				if (MachineCodeJTexField1.getText().equals("L43")) {
					updateEntry(15);
					currentId = 15;
				}
				if (MachineCodeJTexField1.getText().equals("L44")) {
					updateEntry(16);
					currentId = 16;
				}

				setToMachineCode(currentId);
				updateDates();

			}
		});

		bottomPanel.add(update);

		return bottomPanel;

	}

	private JPanel createMiddlePanel(int spIn) {

		JPanel middlePanel = new JPanel(new GridLayout(4, 10));
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// middlePanel.setBackground(Color.WHITE);

		// ROW1
		middlePanel.add(new JLabel("Machine Code", JLabel.CENTER));
		middlePanel.add(new JLabel("Machine Name", JLabel.CENTER));
		middlePanel.add(new JLabel(" ", JLabel.CENTER));
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
		middlePanel.add(chooser1);
		middlePanel.add(MaintenanceTypeLabel1);
		middlePanel.add(TargetProductionJTexField1);
		middlePanel.add(ProductionJTexField1);
		middlePanel.add(PlusMinusJTexField1);
		middlePanel.add(chooser4);
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
		middlePanel.add(chooser2);
		middlePanel.add(MaintenanceTypeLabel2);
		middlePanel.add(TargetProductionJTexField2);
		middlePanel.add(ProductionJTexField2);
		middlePanel.add(PlusMinusJTexField2);
		middlePanel.add(chooser5);
		middlePanel.add(DaysRemainingJTexField2);

		// ROW8
		TargetProductionJTexField3 = new JTextField();
		ProductionJTexField3 = new JTextField();
		PlusMinusJTexField3 = new JTextField();
		MaintenanceDueDateJTexField3 = new JTextField();
		DaysRemainingJTexField3 = new JTextField();

		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" "));
		middlePanel.add(new JLabel(" ", JLabel.CENTER));
		middlePanel.add(chooser3);
		middlePanel.add(MaintenanceTypeLabel7);
		middlePanel.add(TargetProductionJTexField3);
		middlePanel.add(ProductionJTexField3);
		middlePanel.add(PlusMinusJTexField3);
		middlePanel.add(chooser6);
		middlePanel.add(DaysRemainingJTexField3);

		return middlePanel;
	}

	private void setToMachineCode(int idIn) {

		toolingAreaLabel1 = new JLabel(" ", JLabel.CENTER);
		toolingAreaLabel2 = new JLabel(" ", JLabel.CENTER);

		MaintenanceTypeLabel1 = new JLabel("Gun Rebuild", JLabel.CENTER);
		MaintenanceTypeLabel2 = new JLabel("Annual", JLabel.CENTER);
		MaintenanceTypeLabel7 = new JLabel("Bi-Annual", JLabel.CENTER);

		toolingAreaLabel3.setVisible(true);
		toolingAreaLabel4.setVisible(true);

		optionsPanel.repaint();

		lm = new LinerMaintenanceModel();

		try {

			lm = linerMaintenanceDAO.MaintenanceLinerMaintenanceReturnEntryByID(idIn);

			currentId = lm.getID();

			MachineCodeJTexField1.setText(lm.getMachineCode() + "");
			MachineNameJTexField1.setText(lm.getMachineName() + "");
			TargetProductionJTexField1.setText(lm.getTargetProduction1()+ "");
			ProductionJTexField1.setText(lm.getProduction1() + "");
			PlusMinusJTexField1.setText(((int) lm.getTargetProduction1() - (int) lm.getProduction1() + ""));
			DaysRemainingJTexField1.setText(59+ "");
			
			TargetProductionJTexField2.setText(lm.getTargetProduction2()+ "");
			ProductionJTexField2.setText(lm.getProduction2() + "");
			PlusMinusJTexField2.setText(((int) lm.getTargetProduction2() - (int) lm.getProduction2() + ""));
			DaysRemainingJTexField2.setText(6 + "");
			
			TargetProductionJTexField3.setText(lm.getTargetProduction3()+ "");
			ProductionJTexField3.setText(lm.getProduction3() + "");
			PlusMinusJTexField3.setText(((int) lm.getTargetProduction3() - (int) lm.getProduction3() + ""));
			DaysRemainingJTexField3.setText(59+ "");

			

		} catch (Exception ex) {
			Logger.getLogger(LinerMaintenanceView.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void updateDates() {

//		Long daysRemaining1Int, daysRemaining2Int, daysRemaining5Int;
//
//		daysRemaining1Int = (Long.valueOf(PlusMinusJTexField1.getText()) / 1728000L);
//		daysRemaining2Int = (Long.valueOf(PlusMinusJTexField2.getText()) / 1728000L);
//		daysRemaining5Int = (Long.valueOf(PlusMinusJTexFieldTotal.getText()) / 1728000L);
//
//		DaysRemainingJTexField1.setText(daysRemaining1Int + "");
//		DaysRemainingJTexField2.setText(daysRemaining2Int + "");
//		DaysRemainingJTexFieldTotal.setText(daysRemaining5Int + "");
//
//		DaysRemainingJTexField1.setEditable(false);
//		DaysRemainingJTexField2.setEditable(false);
//		DaysRemainingJTexFieldTotal.setEditable(false);
//
//		int days1, days2, days5;
//
//		days1 = Integer.valueOf(DaysRemainingJTexField1.getText());
//		days2 = Integer.valueOf(DaysRemainingJTexField2.getText());
//		days5 = Integer.valueOf(DaysRemainingJTexFieldTotal.getText());
//
//		Date date1 = LastMaintenanceModel1.getValue();
//		Date date2 = LastMaintenanceModel2.getValue();
//		Date date5 = LastMaintenanceModel7.getValue();
//
//		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
//		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");

//		Calendar c1 = Calendar.getInstance();
//		c1.setTime(date1); // Now use today date.
//		c1.add(Calendar.DATE, days1); // Adding 5 days
//		String output1 = sdf1.format(c1.getTime());
//		String output2 = sdf2.format(c1.getTime());
//		String output3 = sdf3.format(c1.getTime());
//		MaintenanceDue1.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
//
//		Calendar c2 = Calendar.getInstance();
//		c2.setTime(date2); // Now use today date.
//		c2.add(Calendar.DATE, days2); // Adding 5 days
//		output1 = sdf1.format(c2.getTime());
//		output2 = sdf2.format(c2.getTime());
//		output3 = sdf3.format(c2.getTime());
//		MaintenanceDue2.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));
//
//		Calendar c5 = Calendar.getInstance();
//		c5.setTime(date5); // Now use today date.
//		c5.add(Calendar.DATE, days5); // Adding 5 days
//		output1 = sdf1.format(c5.getTime());
//		output2 = sdf2.format(c5.getTime());
//		output3 = sdf3.format(c5.getTime());
//		MaintenanceDue7.setDate(Integer.valueOf(output3), Integer.valueOf(output2) - 1, Integer.valueOf(output1));

	}

	private void updateEntry(int machineCodeIn) {
		
		

		lm = new LinerMaintenanceModel();
		
		lm.setID(machineCodeIn);
		lm.setMachineName(MachineNameJTexField1.getText()+"");

		lm.setLastMaintenanceDate1(chooser1.getDate());
		lm.setMaintenanceDueDate1(chooser2.getDate());
		lm.setLastMaintenanceDate2(chooser3.getDate());
		lm.setMaintenanceDueDate2(chooser4.getDate());
		lm.setLastMaintenanceDate3(chooser5.getDate());
		lm.setMaintenanceDueDate3(chooser6.getDate());

		lm.setProduction1(Integer.valueOf(ProductionJTexField1.getText() + ""));
		lm.setProduction2(Integer.valueOf(ProductionJTexField2.getText() + ""));
		lm.setProduction3(Integer.valueOf(ProductionJTexField3.getText() + ""));

		lm.setTargetProduction1(Integer.valueOf(TargetProductionJTexField1.getText() + ""));
		lm.setTargetProduction2(Integer.valueOf(TargetProductionJTexField2.getText() + ""));
		lm.setTargetProduction3(Integer.valueOf(TargetProductionJTexField3.getText() + ""));

		linerMaintenanceDAO.MaintenanceLinerMaintenanceUpdate(lm);

		updateDates();

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
