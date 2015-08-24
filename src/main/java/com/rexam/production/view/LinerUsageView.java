package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.production.dao.LinerDataDAO;
import com.rexam.production.dao.LinerUsageDAO;
import com.rexam.production.model.LinerUsageModel;
import com.toedter.calendar.JDateChooser;

public class LinerUsageView extends JFrame {

	private JButton add, find, next, previous, update, addNew, search, refresh, summary, delete;
	private JLabel dateLabel, linerNumberLabel, crewLabel, leadHandLabel, reasonlabel, quantityUsedLabel,
			partNumberLabel, Gun1Label, Gun2Label, Gun3Label, Gun4Label, Gun5Label, Gun6Label, Gun7Label, Gun8Label,
			commentsLabel;
	private JComboBox linerNumberCombo, crewCombo, leadHandCombo, reasonCombo;
	private JTextField quantityUsedTextField, partNumberTextField;
	private JCheckBox gun1CheckBox, gun2CheckBox, gun3CheckBox, gun4CheckBox, gun5CheckBox, gun6CheckBox, gun7CheckBox,
			gun8CheckBox;
	private JTextArea commentsTextArea;
	int view, currentId;
	private Date selectedDate;
	private JFrame frameSummary;
	JDateChooser chooser1, chooser2;
	LinerUsageModel lu;
	LinerUsageDAO linerUsageDAO;

	public static void main(String[] args) {

		new LinerUsageView(1, -1);

	}

	public LinerUsageView(int id, int view) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		linerUsageDAO = (LinerUsageDAO) context.getBean("LinerUsageDAO");

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

		initializeVariables();

		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Liner Usage Entry");
		setSize(360, 700);
		setLocationRelativeTo(null);

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) chooser1.getDate();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				// Check-Boxes
				int gun1 = 0, gun2 = 0, gun3 = 0, gun4 = 0, gun5 = 0, gun6 = 0, gun7 = 0, gun8 = 0;

				if (gun1CheckBox.isSelected() == true) {
					gun1 = 1;
				}
				if (gun2CheckBox.isSelected() == true) {
					gun2 = 1;
				}
				if (gun3CheckBox.isSelected() == true) {
					gun3 = 1;
				}
				if (gun4CheckBox.isSelected() == true) {
					gun4 = 1;
				}
				if (gun5CheckBox.isSelected() == true) {
					gun5 = 1;
				}
				if (gun6CheckBox.isSelected() == true) {
					gun6 = 1;
				}
				if (gun7CheckBox.isSelected() == true) {
					gun7 = 1;
				}
				if (gun8CheckBox.isSelected() == true) {
					gun8 = 1;
				}

				
				lu.setLineNumber(linerNumberCombo.getSelectedItem()+"");
			//	lu.setLineNumber("Liner 11");
				lu.setDate(chooser1.getDate());
				lu.setCrew(crewCombo.getSelectedItem()+"");
				lu.setLeadHand(leadHandCombo.getSelectedItem()+"");
				lu.setReason(reasonCombo.getSelectedItem()+"");
				lu.setQuantityUsed(Integer.valueOf(quantityUsedTextField.getText()+""));
				lu.setPartNumber(partNumberTextField.getText());
				lu.setGun1(gun1);
				lu.setGun2(gun2);
				lu.setGun3(gun3);
				lu.setGun4(gun4);
				lu.setGun5(gun5);
				lu.setGun6(gun6);
				lu.setGun7(gun7);
				lu.setGun8(gun8);
				lu.setComment(commentsTextArea.getText());
				
				linerUsageDAO.LinerUsageInsert(lu);

				dispose();

				try {
					createSummaryScreen();

					// TODO Auto-generated method stub
				} catch (SQLException ex) {
					Logger.getLogger(LinerUsageView.class.getName()).log(Level.SEVERE, null, ex);
				}

				// TODO Auto-generated method stub
			}
		});

		find = new JButton("Find Record");
		

		next = new JButton("Next Record");
		

		previous = new JButton("Previous Record");
		
		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

							
				// Check-Boxes
				int gun1 = 0, gun2 = 0, gun3 = 0, gun4 = 0, gun5 = 0, gun6 = 0, gun7 = 0, gun8 = 0;

				if (gun1CheckBox.isSelected() == true) {
					gun1 = 1;
				}
				if (gun2CheckBox.isSelected() == true) {
					gun2 = 1;
				}
				if (gun3CheckBox.isSelected() == true) {
					gun3 = 1;
				}
				if (gun4CheckBox.isSelected() == true) {
					gun4 = 1;
				}
				if (gun5CheckBox.isSelected() == true) {
					gun5 = 1;
				}
				if (gun6CheckBox.isSelected() == true) {
					gun6 = 1;
				}
				if (gun7CheckBox.isSelected() == true) {
					gun7 = 1;
				}
				if (gun8CheckBox.isSelected() == true) {
					gun8 = 1;
				}
				
				lu.setLineNumber(linerNumberCombo.getSelectedItem()+"");
				lu.setDate(chooser1.getDate());
				lu.setCrew(crewCombo.getSelectedItem()+"");
				lu.setLeadHand(leadHandCombo.getSelectedItem()+"");
				lu.setReason(reasonCombo.getSelectedItem()+"");
				lu.setQuantityUsed(Integer.valueOf(quantityUsedTextField.getText()+""));
				lu.setPartNumber(partNumberTextField.getText());
				lu.setGun1(gun1);
				lu.setGun2(gun2);
				lu.setGun3(gun3);
				lu.setGun4(gun4);
				lu.setGun5(gun5);
				lu.setGun6(gun6);
				lu.setGun7(gun7);
				lu.setGun8(gun8);
				lu.setComment(commentsTextArea.getText());

				linerUsageDAO.LinerUsageUpdate(lu);

				dispose();
				try {
					createSummaryScreen();

					// TODO Auto-generated method stub
				} catch (SQLException ex) {
					Logger.getLogger(LinerUsageView.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(LinerUsageView.class.getName()).log(Level.SEVERE, null, ex);
				}
				dispose();
			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerUsageView(1, -1);
				dispose();
			}
		});

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(LinerUsageView.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		delete = new JButton("Delete");
	

		// BorderLayout - ButtonsPanels North and South - Gridlayout Middle
		// Top Buttons Panel
		JPanel buttonPanelTop = new JPanel(new FlowLayout());
		// buttonPanelTop.add(find);
		buttonPanelTop.add(previous);
		buttonPanelTop.add(next);
		buttonPanelTop.add(delete);
		outerPanel.add(buttonPanelTop, BorderLayout.NORTH);

		// Middle Panel
		JPanel middlePanel = new JPanel(new GridLayout(3, 1));

		// 3 Panel - Labels - Check-Boxes - CommentBox
		JPanel labelsTextfieldsPanel = new JPanel(new GridLayout(7, 2));
		labelsTextfieldsPanel.add(dateLabel);
		labelsTextfieldsPanel.add(chooser1);
		labelsTextfieldsPanel.add(linerNumberLabel);
		labelsTextfieldsPanel.add(linerNumberCombo);
		labelsTextfieldsPanel.add(crewLabel);
		labelsTextfieldsPanel.add(crewCombo);
		labelsTextfieldsPanel.add(leadHandLabel);
		labelsTextfieldsPanel.add(leadHandCombo);
		labelsTextfieldsPanel.add(reasonlabel);
		labelsTextfieldsPanel.add(reasonCombo);
		labelsTextfieldsPanel.add(quantityUsedLabel);
		labelsTextfieldsPanel.add(quantityUsedTextField);
		labelsTextfieldsPanel.add(partNumberLabel);
		labelsTextfieldsPanel.add(partNumberTextField);

		middlePanel.add(labelsTextfieldsPanel);

		JPanel checkBoxesPanel = new JPanel(new GridLayout(4, 4));
		checkBoxesPanel.add(Gun1Label);
		checkBoxesPanel.add(gun1CheckBox);
		checkBoxesPanel.add(Gun2Label);
		checkBoxesPanel.add(gun2CheckBox);
		checkBoxesPanel.add(Gun3Label);
		checkBoxesPanel.add(gun3CheckBox);
		checkBoxesPanel.add(Gun4Label);
		checkBoxesPanel.add(gun4CheckBox);
		checkBoxesPanel.add(Gun5Label);
		checkBoxesPanel.add(gun5CheckBox);
		checkBoxesPanel.add(Gun6Label);
		checkBoxesPanel.add(gun6CheckBox);
		checkBoxesPanel.add(Gun7Label);
		checkBoxesPanel.add(gun7CheckBox);
		checkBoxesPanel.add(Gun8Label);
		checkBoxesPanel.add(gun8CheckBox);

		middlePanel.add(checkBoxesPanel);

		JPanel commentsPanel = new JPanel(new BorderLayout());
		commentsPanel.add(commentsLabel, BorderLayout.NORTH);
		commentsPanel.add(commentsTextArea, BorderLayout.CENTER);

		middlePanel.add(commentsPanel);

		outerPanel.add(middlePanel, BorderLayout.CENTER);

		// Bottom Buttons Panel
		JPanel buttonPanelBottom = new JPanel(new FlowLayout());
		buttonPanelBottom.add(search);
		buttonPanelBottom.add(addNew);
		buttonPanelBottom.add(summary);

		buttonPanelBottom.add(update);
		buttonPanelBottom.add(add);
		buttonPanelBottom.setBackground(Color.GRAY);
		outerPanel.add(buttonPanelBottom, BorderLayout.SOUTH);

		// Adding
		if (view == -1) {

			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);
			summary.setVisible(false);
			delete.setVisible(false);

		} // Searching
		else {

			// currentId = linerUsageDAO.LinerGetHighestID() + 1;
			buttonPanelTop.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);
			addNew.setVisible(false);

		}

		// Set Frame Visible
		add(outerPanel);
		setVisible(true);

	//	linerUsageDAO.AnalyticsUpdate("LinerUsageView");

	}

	private void initializeVariables() {

		lu = new LinerUsageModel();
		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());
		linerNumberCombo = new JComboBox();
		crewCombo = new JComboBox();
		leadHandCombo = new JComboBox();
		reasonCombo = new JComboBox();
		fillCombos();

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		linerNumberLabel = new JLabel("Line Number : ", SwingConstants.CENTER);
		crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
		leadHandLabel = new JLabel("Lead Hand : ", SwingConstants.CENTER);
		reasonlabel = new JLabel("Reason : ", SwingConstants.CENTER);
		quantityUsedLabel = new JLabel("Quantity Used : ", SwingConstants.CENTER);
		partNumberLabel = new JLabel("Part Number : ", SwingConstants.CENTER);
		Gun1Label = new JLabel("Gun 1 : ", SwingConstants.CENTER);
		Gun2Label = new JLabel("Gun 2 : ", SwingConstants.CENTER);
		Gun3Label = new JLabel("Gun 3 : ", SwingConstants.CENTER);
		Gun4Label = new JLabel("Gun 4 : ", SwingConstants.CENTER);
		Gun5Label = new JLabel("Gun 5 : ", SwingConstants.CENTER);
		Gun6Label = new JLabel("Gun 6 : ", SwingConstants.CENTER);
		Gun7Label = new JLabel("Gun 7 : ", SwingConstants.CENTER);
		Gun8Label = new JLabel("Gun 8 : ", SwingConstants.CENTER);
		commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);

		quantityUsedTextField = new JTextField("0");
		PlainDocument doc2 = (PlainDocument) quantityUsedTextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());
		partNumberTextField = new JTextField("0");

		commentsTextArea = new JTextArea("NA");
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setWrapStyleWord(true);

		gun1CheckBox = new JCheckBox();
		gun2CheckBox = new JCheckBox();
		gun3CheckBox = new JCheckBox();
		gun4CheckBox = new JCheckBox();
		gun5CheckBox = new JCheckBox();
		gun6CheckBox = new JCheckBox();
		gun7CheckBox = new JCheckBox();
		gun8CheckBox = new JCheckBox();
	}

	public void createSummaryScreen() throws SQLException {

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				new LinerUsageView(1, 1);

			}
		});
		refresh = new JButton("Refresh");
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
		JButton print = new JButton("Print Report");
		JButton ExportToExcel = new JButton("Export To Excel");

//		ExportToExcel.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				LinerDataView.exportToExcel();
//
//			}
//		});

		JButton importFromViscan = new JButton("Import from Viscan");

//		importFromViscan.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				try {
//					frameSummary.dispose();
//					LinerDataView.importFromExcel();
//				} catch (IOException ex) {
//					Logger.getLogger(LinerUsageView.class.getName()).log(Level.SEVERE, null, ex);
//				}
//
//			}
//		});

		// Outer Frame
		frameSummary = new JFrame("Liner Data Report");
		frameSummary.setSize(1366, 768);
		frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		// if (view == 2) {
		optionsPanel2.add(addNew);
		optionsPanel2.add(refresh);
		// optionsPanel2.add(print);
		optionsPanel2.add(ExportToExcel);
		optionsPanel2.add(importFromViscan);
		// }

		JPanel summaryPanel = linerUsageDAO.LinerUsageSummaryTable(1);
		JScrollPane scrollPane = new JScrollPane(summaryPanel);

		// print.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// getDate("Optime Production Report", 1);
		//
		// }
		// });
		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(scrollPane, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}

	private void fillCombos() {

		JComboBox[] la = linerUsageDAO.fillCombos(new JComboBox[4]);
		
		linerNumberCombo = la[0];
		crewCombo = la[1]; 
		leadHandCombo = la[2]; 
		reasonCombo = la[3];
		

	}

	private void setLastEntry() {

		setLinerUsageToId(1);
	
	}

	public void setLinerUsageToId(int idIn) {

		try {

			int highestID = idIn;
			lu = linerUsageDAO.LinerUsageReturnEntryByID(idIn);


			Boolean gun1 = false, gun2 = false, gun3 = false, gun4 = false, gun5 = false, gun6 = false, gun7 = false,
					gun8 = false;

			linerNumberCombo.setSelectedItem(lu.getLineNumber());
			crewCombo.setSelectedItem(lu.getCrew());
			leadHandCombo.setSelectedItem(lu.getLeadHand());
			reasonCombo.setSelectedItem(lu.getReason());
			quantityUsedTextField.setText(lu.getQuantityUsed()+"");
			partNumberTextField.setText(lu.getPartNumber());

			int gun1Int = lu.getGun1();
			int gun2Int = lu.getGun2();
			int gun3Int = lu.getGun3();
			int gun4Int = lu.getGun4();
			int gun5Int = lu.getGun5();
			int gun6Int = lu.getGun6();
			int gun7Int = lu.getGun7();
			int gun8Int = lu.getGun8();

			if (gun1Int == 1) {
				gun1 = true;
			}
			if (gun2Int == 1) {
				gun2 = true;
			}
			if (gun3Int == 1) {
				gun3 = true;
			}
			if (gun4Int == 1) {
				gun4 = true;
			}
			if (gun5Int == 1) {
				gun5 = true;
			}
			if (gun6Int == 1) {
				gun6 = true;
			}
			if (gun7Int == 1) {
				gun7 = true;
			}
			if (gun8Int == 1) {
				gun8 = true;
			}

			gun1CheckBox.setSelected(gun1);
			gun2CheckBox.setSelected(gun2);
			gun3CheckBox.setSelected(gun3);
			gun4CheckBox.setSelected(gun4);
			gun5CheckBox.setSelected(gun5);
			gun6CheckBox.setSelected(gun6);
			gun7CheckBox.setSelected(gun7);
			gun8CheckBox.setSelected(gun8);

			commentsTextArea.setText(lu.getComment());

			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
