package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
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

import com.rexam.production.dao.StolleDataDAO;
import com.rexam.production.model.StolleDataModel;
import com.toedter.calendar.JDateChooser;

public class StolleDataView extends JFrame {

	private JButton add, find, next, previous, update, addNew, search, newEntry, refresh, exportToExcel, summary,
			delete;
	private JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressLabel, pressSpeedLabel,
			shellTypeLabel, productionLabel, commentsLabel, packerLabel, qcInspectorLabel, stolleProductionLabel,
			packedEndsLabel, HFICreatedLabel, HFIRecoveredLabel, HFIScrappedLabel, sacobaDowntimeLabel;
	private JTextField dateTextField, pressSpeedTextField, productionTextField, stolleProductionTextField,
			packedEndsTextField, HFICreatedTextField, HFIRecoveredTextField, HFIScrappedTextField,
			sacobaDowntimeTextField;
	private JTextArea commentsTextArea;
	private int view, currentId;
	private Date selectedDate;
	private JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo,
			shellTypeCombo;
	StolleDataModel sm;
	JDateChooser chooser1, chooser2;
	JFrame frameSummary;
	StolleDataDAO stolleDataDAO;

	public static void main(String[] args) throws SQLException {

		new StolleDataView(1, -1);

	}

	public StolleDataView(int idIn, int view) throws SQLException {
		
		sm = new StolleDataModel();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		stolleDataDAO = (StolleDataDAO) context.getBean("StolleDataDAO");

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
		chooser2 = new JDateChooser();

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

		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel();
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Stolle Data Entry");
		setSize(360, 700);
		setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		initializeVariables();


		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		// buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);
		buttonsPanel.add(delete);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		dateTextField = new JTextField();

		pressSpeedTextField = new JTextField("0");
		PlainDocument doc1 = (PlainDocument) pressSpeedTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		stolleProductionTextField = new JTextField("0");
		// PlainDocument doc2 = (PlainDocument)
		// stolleProductionTextField.getDocument();
		// doc2.setDocumentFilter(new MyIntFilter());

		packedEndsTextField = new JTextField("0");
		// PlainDocument doc3 = (PlainDocument)
		// packedEndsTextField.getDocument();
		// doc3.setDocumentFilter(new MyIntFilter());

		HFICreatedTextField = new JTextField("0");
		PlainDocument doc4 = (PlainDocument) HFICreatedTextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		HFIRecoveredTextField = new JTextField("0");
		PlainDocument doc5 = (PlainDocument) HFIRecoveredTextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		HFIScrappedTextField = new JTextField("0");
		PlainDocument doc6 = (PlainDocument) HFIScrappedTextField.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());

		sacobaDowntimeTextField = new JTextField("0");
		PlainDocument doc7 = (PlainDocument) sacobaDowntimeTextField.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());

		// dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel,
		// pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;
		JPanel optionPanel1 = new JPanel(new GridLayout(14, 2));
		// optionPanel1.setBackground(Color.GRAY);
		

		optionPanel1.add(dateLabel);
		optionPanel1.add(chooser1);

		optionPanel1.add(shiftLabel);
		optionPanel1.add(shiftCombo);

		optionPanel1.add(crewLabel);
		optionPanel1.add(crewCombo);

		optionPanel1.add(pressLabel);
		optionPanel1.add(pressCombo);

		optionPanel1.add(operatorLabel);
		optionPanel1.add(operatorCombo);

		optionPanel1.add(packerLabel);
		optionPanel1.add(qcCombo);

		optionPanel1.add(qcInspectorLabel);
		optionPanel1.add(packerCombo);

		optionPanel1.add(pressSpeedLabel);
		optionPanel1.add(pressSpeedTextField);

		optionPanel1.add(stolleProductionLabel);
		optionPanel1.add(stolleProductionTextField);

		optionPanel1.add(packedEndsLabel);
		optionPanel1.add(packedEndsTextField);

		optionPanel1.add(HFICreatedLabel);
		optionPanel1.add(HFICreatedTextField);

		optionPanel1.add(HFIRecoveredLabel);
		optionPanel1.add(HFIRecoveredTextField);

		optionPanel1.add(HFIScrappedLabel);
		optionPanel1.add(HFIScrappedTextField);

		optionPanel1.add(sacobaDowntimeLabel);
		optionPanel1.add(sacobaDowntimeTextField);

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

			// currentId = stolleDataDAO.StolleGetHighestID() + 1;
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);

		}

		JPanel commentsPanel = new JPanel();

		commentsTextArea = new JTextArea(7, 28);
		commentsTextArea.setText("NA");
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setWrapStyleWord(true);

		commentsPanel.add(commentsLabel);
		commentsPanel.add(commentsTextArea);

		// optionPanel1.add(productionLabel);
		// optionPanel1.add(productionTextField);
		outerPanel.add(optionPanel1, BorderLayout.CENTER);

		// Options Panel 2
		JPanel optionsPanel2 = new JPanel(new FlowLayout());
		optionsPanel2.add(summary);

		optionsPanel2.add(search);
		optionsPanel2.add(update);
		optionsPanel2.add(add);
		optionsPanel2.setBackground(Color.GRAY);

		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.add(commentsPanel, BorderLayout.NORTH);
		bottomPanel.add(optionsPanel2, BorderLayout.SOUTH);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		// JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
		// optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;
		outerPanel.repaint();
		add(outerPanel);

		setVisible(true);
		
		

		// stolleDataDAO.AnalyticsUpdate("StolleDataView");

	}

	private void initializeVariables() {

		

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					dispose();
					createSummaryScreen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		delete = new JButton("Delete");

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sm.setDate(chooser1.getDate());
				sm.setShift(Integer.valueOf(shiftCombo.getSelectedItem() + ""));
				sm.setCrew(crewCombo.getSelectedItem() + "");
				sm.setPress(pressCombo.getSelectedItem() + "");
				sm.setOperator(operatorCombo.getSelectedItem() + "");
				sm.setPacker(packerCombo.getSelectedItem() + "");
				sm.setQcInspector(qcCombo.getSelectedItem() + "");
				sm.setPressSpeed(Integer.valueOf(pressSpeedTextField.getText()));
				sm.setStolleProduction(Integer.valueOf(stolleProductionTextField.getText()));
				sm.setPackedEnds(Integer.valueOf(packedEndsTextField.getText()));
				sm.setHfiCreated(Integer.valueOf(HFICreatedTextField.getText()));
				sm.setHfiRecovered(Integer.valueOf(HFIRecoveredTextField.getText()));
				sm.setHfiScrapped(Integer.valueOf(HFICreatedTextField.getText()));
				sm.setSacobaDowntime(Integer.valueOf(sacobaDowntimeTextField.getText()));
				sm.setComment(commentsTextArea.getText());

				if (stolleDataDAO.StolleExists(sm)) {

					try {
						stolleDataDAO.StolleUpdate(sm);

						dispose();
						createSummaryScreen();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					try {
						stolleDataDAO.StolleInsert(sm);

						dispose();
						createSummaryScreen();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					createSummaryScreen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					new StolleDataView(1, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();

			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sm.setDate(chooser1.getDate());
				sm.setShift(Integer.valueOf(shiftCombo.getSelectedItem() + ""));
				sm.setCrew(crewCombo.getSelectedItem() + "");
				sm.setPress(pressCombo.getSelectedItem() + "");
				sm.setOperator(operatorCombo.getSelectedItem() + "");
				sm.setPacker(packerCombo.getSelectedItem() + "");
				sm.setQcInspector(qcCombo.getSelectedItem() + "");
				sm.setPressSpeed(Integer.valueOf(pressSpeedTextField.getText()));
				sm.setStolleProduction(Integer.valueOf(stolleProductionTextField.getText()));
				sm.setPackedEnds(Integer.valueOf(packedEndsTextField.getText()));
				sm.setHfiCreated(Integer.valueOf(HFICreatedTextField.getText()));
				sm.setHfiRecovered(Integer.valueOf(HFIRecoveredTextField.getText()));
				sm.setHfiScrapped(Integer.valueOf(HFICreatedTextField.getText()));
				sm.setSacobaDowntime(Integer.valueOf(sacobaDowntimeTextField.getText()));
				sm.setComment(commentsTextArea.getText());

				try {
					stolleDataDAO.StolleUpdate(sm);

					dispose();
					frameSummary.dispose();
					createSummaryScreen();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub
			}
		});

		find = new JButton("Find Record");

		next = new JButton("Next Record");

		previous = new JButton("Previous Record");

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		shiftLabel = new JLabel("Shift : ", SwingConstants.CENTER);
		crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
		packerLabel = new JLabel("Packer : ", SwingConstants.CENTER);
		pressLabel = new JLabel("Press : ", SwingConstants.CENTER);
		operatorLabel = new JLabel("Operator : ", SwingConstants.CENTER);
		qcInspectorLabel = new JLabel("QC Inspector : ", SwingConstants.CENTER);
		optimeNumberLabel = new JLabel("Optime Number : ", SwingConstants.CENTER);
		pressSpeedLabel = new JLabel("Press Speed : ", SwingConstants.CENTER);
		stolleProductionLabel = new JLabel("Stolle Production : ", SwingConstants.CENTER);
		packedEndsLabel = new JLabel("Packed Ends : ", SwingConstants.CENTER);
		HFICreatedLabel = new JLabel("HFI Created : ", SwingConstants.CENTER);
		HFIRecoveredLabel = new JLabel("HFI Recovered : ", SwingConstants.CENTER);
		HFIScrappedLabel = new JLabel("HFI Scrapped : ", SwingConstants.CENTER);
		sacobaDowntimeLabel = new JLabel("Sacoba Downtime : ", SwingConstants.CENTER);
		shellTypeLabel = new JLabel("Shell Type : ", SwingConstants.CENTER);
		productionLabel = new JLabel("Production : ", SwingConstants.CENTER);
		commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);
	}

	public void setStolleDataEntry(int idIn) {

		try {

			int highestID = 1;
			sm = stolleDataDAO.StolleReturnEntryByID(idIn);

			shiftCombo.setSelectedItem(sm.getShift());
			crewCombo.setSelectedItem(sm.getCrew());
			pressCombo.setSelectedItem(sm.getPress());
			operatorCombo.setSelectedItem(sm.getOperator());
			packerCombo.setSelectedItem(sm.getPacker());
			qcCombo.setSelectedItem(sm.getQcInspector());
			pressSpeedTextField.setText(String.valueOf(sm.getPressSpeed()));
			stolleProductionTextField.setText((String) String.valueOf(sm.getStolleProduction()));
			packedEndsTextField.setText((String) String.valueOf(sm.getPackedEnds()));
			HFICreatedTextField.setText((String) String.valueOf(sm.getHfiCreated()));
			HFIRecoveredTextField.setText((String) String.valueOf(sm.getHfiRecovered()));
			HFIScrappedTextField.setText((String) String.valueOf(sm.getHfiScrapped()));
			sacobaDowntimeTextField.setText((String) String.valueOf(sm.getSacobaDowntime()));
			commentsTextArea.setText((String) sm.getComment());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void createSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new StolleDataView(2, -1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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

		exportToExcel = new JButton("Export To Excel");
		// exportToExcel.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// OptimeDataEntryScreen.exportToExcel();
		//
		// }
		// });

		JButton importFromViscan = new JButton("Import from Viscan");

		importFromViscan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();

			}
		});

		// Outer Frame
		frameSummary = new JFrame("Stolle Production Report");
		frameSummary.toFront();
		frameSummary.setSize(1366, 768);
		frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		// if (view == 2) {
		optionsPanel2.add(newEntry);
		optionsPanel2.add(refresh);
		optionsPanel2.add(print);
		optionsPanel2.add(exportToExcel);
		optionsPanel2.add(importFromViscan);
		// }

		JPanel summaryPanel = stolleDataDAO.StolleSummaryTable(1);
		JScrollPane scrollPane = new JScrollPane(summaryPanel);
		// print.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// getDate("Stolle Production Report", 1);
		//
		// }
		// });

		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(scrollPane, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}

	public void fillCombos() {
		
        JComboBox [] oa =  stolleDataDAO.fillCombos(new JComboBox[6]);	
        
		operatorCombo.addItem("NA");        
		shiftCombo = oa[0];
		crewCombo = oa[1];
		pressCombo = oa[3];
		operatorCombo = oa[2];	
		packerCombo = oa[4];	
		qcCombo = oa[5];	

		
		
		//
		// // Operator
		// try {
		//
		// String sql = "select Employees.Name from Employees ORDER BY Name
		// ASC";
		// Connection conn = stolleDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// operatorCombo.addItem("NA");
		// packerCombo.addItem("NA");
		// qcCombo.addItem("NA");
		//
		// while (rs.next()) {
		//
		// String name = rs.getString("Name");
		// operatorCombo.addItem(name);
		// packerCombo.addItem(name);
		// qcCombo.addItem(name);
		// }
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Press
		// try {
		//
		// String sql = "SELECT Press.PressName FROM Press ORDER BY PressName
		// ASC";
		// Connection conn = stolleDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String pressName = rs.getString("PressName");
		// pressCombo.addItem(pressName);
		// }
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Crew
		// try {
		//
		// String sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
		// Connection conn = stolleDataDAO.Connect();
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
		// // Shift
		// try {
		//
		// String sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName
		// ASC";
		// Connection conn = stolleDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String shiftName = rs.getString("ShiftName");
		// shiftCombo.addItem(shiftName);
		// }
		//
		// } catch (Exception e) {
		//
		// }

	}

}
