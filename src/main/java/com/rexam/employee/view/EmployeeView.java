package com.rexam.employee.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.employee.dao.EmployeeDAO;
import com.rexam.employee.model.EmployeeModel;

public class EmployeeView extends JFrame {

	EmployeeModel em;
	EmployeeDAO employeeDAO;

	private JButton save, delete, reset, reset2, addNew, confirmDelete, update, confirmUpdate;
	private JLabel employeeId, name, address, crew, phoneExt, departmentHead, processLeader, shiftManager, technician,
			leadHand, operator, engineer, forkliftDriver, toolmaker, fitter, electrician, packer, qcInspector,
			phoneNumber, mobileNumber;
	private JTextField employeeIdText, nametext, addressText, phoneExtText, phoneNumberText, mobileNumberText;
	private JCheckBox departmentHeadJCheckBox, shiftManagerJCheckBox, technicianJCheckBox, teamLeaderJCheckBox,
			leadHandJCheckBox, operatorJCheckBox, engineerJCheckBox, packerJCheckBox, qcInspectorJCheckBox,
			forkliftDriverJCheckBox, processLeaderJCheckBox, toolmakerJCheckBox, fitterJCheckBox, electricianJCheckBox;
	private JComboBox crewCombo, nameAndId, nameCombo, jobCombo;
	private int currentId;
	private JFrame frame9, frame10, frame11;
	private JFileChooser fileChooser;

	public static void main(String[] args) {

		new EmployeeView(1, 1);

	}

	public EmployeeView(int id, int view) {

		em = new EmployeeModel();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		employeeDAO = (EmployeeDAO) context.getBean("EmployeeDAO");

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

		// nameCombo = new JComboBox();
		setTitle("Employee List");
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1500, 768);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		// setResizable(false);

		phoneNumberText = new JTextField();
		PlainDocument doc1 = (PlainDocument) phoneNumberText.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		mobileNumberText = new JTextField();
		PlainDocument doc2 = (PlainDocument) mobileNumberText.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		// ///////////
		if (view == 1) {

			add(createTopPanel(), BorderLayout.NORTH);
			add(createSummaryPanel(), BorderLayout.CENTER);
		} else if (view == 2) {

		} else if (view == 3) {

			add(createSummaryPanel(), BorderLayout.CENTER);

		}

		setVisible(true);

		// employeeDAO.AnalyticsUpdate("EmployeeView");
	}

	private JPanel createTopPanel() {

		employeeId = new JLabel("Employee ID :", SwingConstants.CENTER);
		name = new JLabel("Name :", SwingConstants.CENTER);
		address = new JLabel("Address :", SwingConstants.CENTER);
		crew = new JLabel("Crew :", SwingConstants.CENTER);
		phoneExt = new JLabel("Phone Ext :", SwingConstants.CENTER);
		departmentHead = new JLabel("Department Head :", SwingConstants.CENTER);
		shiftManager = new JLabel("Shift Manager :", SwingConstants.CENTER);
		technician = new JLabel("Technician :", SwingConstants.CENTER);
		leadHand = new JLabel("Lead Hand :", SwingConstants.CENTER);
		operator = new JLabel("Operator :", SwingConstants.CENTER);
		engineer = new JLabel("Engineer :", SwingConstants.CENTER);
		packer = new JLabel("Packer :", SwingConstants.CENTER);
		phoneNumber = new JLabel("Home Phone Number :", SwingConstants.CENTER);
		mobileNumber = new JLabel("Mobile Number :", SwingConstants.CENTER);
		qcInspector = new JLabel("QC Inspector :", SwingConstants.CENTER);

		forkliftDriver = new JLabel("Forklift Driver :", SwingConstants.CENTER);
		processLeader = new JLabel("Process Leader", SwingConstants.CENTER);
		toolmaker = new JLabel("Toolmaker", SwingConstants.CENTER);
		fitter = new JLabel("Fitter", SwingConstants.CENTER);
		electrician = new JLabel("Electrician", SwingConstants.CENTER);

		employeeIdText = new JTextField();
		nametext = new JTextField();
		addressText = new JTextField();
		phoneExtText = new JTextField();
		phoneNumberText = new JTextField();
		mobileNumberText = new JTextField();

		departmentHeadJCheckBox = new JCheckBox();
		shiftManagerJCheckBox = new JCheckBox();
		technicianJCheckBox = new JCheckBox();
		teamLeaderJCheckBox = new JCheckBox();
		leadHandJCheckBox = new JCheckBox();
		operatorJCheckBox = new JCheckBox();
		engineerJCheckBox = new JCheckBox();
		packerJCheckBox = new JCheckBox();
		qcInspectorJCheckBox = new JCheckBox();

		ButtonGroup buttonGroup = new ButtonGroup();

		forkliftDriverJCheckBox = new JCheckBox();
		processLeaderJCheckBox = new JCheckBox();
		toolmakerJCheckBox = new JCheckBox();
		fitterJCheckBox = new JCheckBox();
		electricianJCheckBox = new JCheckBox();

		buttonGroup.add(departmentHeadJCheckBox);
		buttonGroup.add(shiftManagerJCheckBox);
		buttonGroup.add(technicianJCheckBox);
		buttonGroup.add(teamLeaderJCheckBox);
		buttonGroup.add(leadHandJCheckBox);
		buttonGroup.add(operatorJCheckBox);
		buttonGroup.add(engineerJCheckBox);
		buttonGroup.add(packerJCheckBox);
		buttonGroup.add(qcInspectorJCheckBox);
		buttonGroup.add(forkliftDriverJCheckBox);
		buttonGroup.add(processLeaderJCheckBox);
		buttonGroup.add(toolmakerJCheckBox);
		buttonGroup.add(fitterJCheckBox);
		buttonGroup.add(electricianJCheckBox);

		addNew = new JButton("Add New");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				createNewEmployee();

				employeeIdText.setText("0");
				nametext.setText("");
				addressText.setText("");
				phoneExtText.setText("");
				phoneNumberText.setText("");
				mobileNumberText.setText("");

			}
		});

		reset = new JButton("Reset / Refresh");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dispose();
				new EmployeeView(1, 1);

			}
		});

		update = new JButton("Update");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dispose();
				updateEmployee();

			}
		});

		save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

				try {

					em.setEmployeeId(employeeIdText.getText());
					em.setName(nametext.getText());
					em.setAddress(addressText.getText());
					em.setJobsTitle(jobCombo.getSelectedItem() + "");
					em.setCrew(crewCombo.getSelectedItem() + "");
					em.setPhoneExtension(phoneExtText.getText());
					em.setPhoneNumber(phoneNumberText.getText());
					em.setMobileNumber(mobileNumberText.getText());

					employeeDAO.EmployeeInsert(em);

					frame10.dispose();
					new EmployeeView(1, 1);
					createNewEmployee();

				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub
			}

		});
		delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				// dispose();
				deleteEmployee();

			}
		});

		JPanel outerPanel = new JPanel(new FlowLayout());
		outerPanel.setBorder(new EmptyBorder(10, 100, 10, 100));

		outerPanel.add(reset);
		outerPanel.add(addNew);
		outerPanel.add(delete);
		outerPanel.add(update);

		return outerPanel;
	}

	private JPanel createSummaryPanel() {

		JPanel outerPanel = new JPanel(new BorderLayout());

		JPanel tablePanel = new JPanel();
		tablePanel = employeeDAO.EmployeeSummaryTable();

		JScrollPane scrollPane = new JScrollPane(tablePanel);
		outerPanel.add(scrollPane);

		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		return outerPanel;
	}

	public void setEmployeeById(int idIn) {

		try {
			em = employeeDAO.EmployeeReturnEntryById(idIn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentId = Integer.valueOf(em.getId());
		employeeIdText.setText(em.getEmployeeId());
		nametext.setText(em.getName());
		addressText.setText(em.getAddress());

		crewCombo.setSelectedItem(em.getCrew());
		phoneExtText.setText(em.getPhoneExtension());
		phoneNumberText.setText(em.getPhoneNumber());
		mobileNumberText.setText(em.getMobileNumber());

	}

	private void createNewEmployee() {

		crewCombo = new JComboBox();
		jobCombo = new JComboBox();
		fillCrewCombos();

		frame10 = new JFrame("New Employee");
		frame10.setSize(400, 330);
		frame10.setLocationRelativeTo(null);

		reset2 = new JButton("Reset");
		reset2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				currentId = 0;
				employeeIdText.setText("");
				nametext.setText("");
				addressText.setText("");
				phoneExtText.setText("");
				crewCombo.setSelectedItem("A");
				jobCombo.setSelectedItem("Machine Operator");
				phoneNumberText.setText("");
				mobileNumberText.setText("");

			}
		});

		JPanel panel = new JPanel(new GridLayout(9, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		panel.add(employeeId);
		panel.add(employeeIdText);

		panel.add(name);
		panel.add(nametext);

		panel.add(address);
		panel.add(addressText);

		panel.add(crew);
		panel.add(crewCombo);

		panel.add(phoneExt);
		panel.add(phoneExtText);

		panel.add(phoneNumber);
		panel.add(phoneNumberText);

		panel.add(mobileNumber);
		panel.add(mobileNumberText);

		panel.add(new JLabel("Job : ", JLabel.CENTER));
		panel.add(jobCombo);

		panel.add(reset2);
		panel.add(save);

		frame10.add(panel);
		frame10.setVisible(true);
	}

	private void deleteEmployee() {

		nameCombo = new JComboBox();
		fillDeleteCombo();

		confirmDelete = new JButton("Confirm Delete");
		confirmDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {

					int id = employeeDAO.EmployeeReturnIdByName(nameCombo.getSelectedItem() + "");
					dispose();
					em.setId(id);
					employeeDAO.EmployeeDelete(em);
					frame11.dispose();
					new EmployeeView(1, 1);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame11 = new JFrame("Delete Employee");
		frame11.setSize(500, 100);
		frame11.setLocationRelativeTo(null);

		JPanel panel1 = new JPanel(new GridLayout(1, 3));
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

		panel1.add(new JLabel("Employee Name :", SwingConstants.CENTER));
		panel1.add(nameCombo);
		panel1.add(confirmDelete);

		frame11.add(panel1);
		frame11.setVisible(true);
	}

	public void updateEmployee() {

		nameCombo = new JComboBox();
		jobCombo = new JComboBox();
		nameCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					em = employeeDAO.EmployeeReturnEntryByName(nameCombo.getSelectedItem() + "");

					currentId = Integer.valueOf(em.getId());
					employeeIdText.setText(em.getEmployeeId());
					nametext.setText(em.getName());
					addressText.setText(em.getAddress());

					crewCombo.setSelectedItem(em.getCrew());
					phoneExtText.setText(em.getPhoneExtension());
					phoneNumberText.setText(em.getPhoneNumber());
					mobileNumberText.setText(em.getMobileNumber());

				} catch (Exception e1) {

					e1.printStackTrace();
				}

			}
		});
		crewCombo = new JComboBox();

		confirmUpdate = new JButton("Confirm Update");
		confirmUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {

					em.setId(employeeDAO.EmployeeReturnIdByName(nameCombo.getSelectedItem() + ""));

					System.out.println("name " + nametext.getText());

					em.setEmployeeId(employeeIdText.getText());
					em.setName(nametext.getText());
					em.setAddress(addressText.getText());
					em.setJobsTitle(jobCombo.getSelectedItem() + "");
					em.setCrew(crewCombo.getSelectedItem() + "");
					em.setPhoneExtension(phoneExtText.getText());
					em.setPhoneNumber(phoneNumberText.getText());
					em.setMobileNumber(mobileNumberText.getText());

					employeeDAO.EmployeeUpdate(em);

					dispose();
					frame10.dispose();
					new EmployeeView(1, 1);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		frame10 = new JFrame("Update Employee");
		frame10.setSize(400, 370);
		frame10.setLocationRelativeTo(null);

		reset2 = new JButton("Reset");
		reset2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				currentId = 0;
				employeeIdText.setText("");
				nametext.setText("");
				addressText.setText("");
				crewCombo.setSelectedItem("A");

				jobCombo.setSelectedItem("Machine Operator");

				phoneExtText.setText("");
				phoneNumberText.setText("");
				mobileNumberText.setText("");

			}
		});

		JPanel panel = new JPanel(new GridLayout(10, 2));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		panel.add(new JLabel("Update Employee : ", SwingConstants.CENTER));
		panel.add(nameCombo);

		panel.add(employeeId);
		panel.add(employeeIdText);

		panel.add(new JLabel("New Name : ", SwingConstants.CENTER));
		panel.add(nametext);

		panel.add(address);
		panel.add(addressText);

		panel.add(new JLabel("Job ", JLabel.CENTER));
		panel.add(jobCombo);

		panel.add(crew);
		panel.add(crewCombo);

		panel.add(phoneExt);
		panel.add(phoneExtText);

		panel.add(phoneNumber);
		panel.add(phoneNumberText);

		panel.add(mobileNumber);
		panel.add(mobileNumberText);

		panel.add(reset2);
		panel.add(confirmUpdate);

		frame10.add(panel);
		frame10.setVisible(true);

		fillCrewCombos();
		fillDeleteCombo();
	}

	private void fillCrewCombos() {

		JComboBox[] la = employeeDAO.fillCombos(new JComboBox[4]);

		jobCombo = la[0];
		crewCombo = la[1];
		nameCombo = la[2];
		// leadHandCombo = la[2];
		// reasonCombo = la[3];

	}

	public void fillDeleteCombo() {

		JComboBox[] la = employeeDAO.fillDeleteCombos(new JComboBox[4]);

		nameCombo = la[0];

	}

}
