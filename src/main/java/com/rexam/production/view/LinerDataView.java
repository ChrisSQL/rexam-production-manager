package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.production.dao.LinerDataDAO;
import com.rexam.production.model.LinerDataModel;
import com.toedter.calendar.JDateChooser;

public class LinerDataView extends JFrame {

	LinerDataModel lm;
	LinerDataDAO linerDataDAO;

	JButton add, find, next, previous, update, addNew, search, calculateSpoiledPercent, newEntry, refresh, summary,
			delete;
	JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
			productionLabel, commentsLabel, moduleLabel, linerLabel, linerInfeedLabel, shellsSpoiledLabel,
			percentSpoiled;
	JTextField dateTextField, pressSpeedTextField, prductionTextField, linerInfeedTextField, shellsSPoiledTextField,
			percentSpoiledTextField;
	JTextArea commentsTextArea;
	int view, currentId;
	Date selectedDate;
	JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo, shellTypeCombo,
			moduleCombo, linerCombo;
	JFrame frameSummary;
	JDateChooser chooser1, chooser2;

	public static void main(String[] args) {

		new LinerDataView(1, -1);

	}

	public LinerDataView(int idIn, int view) {

		lm = new LinerDataModel();

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		linerDataDAO = (LinerDataDAO) context.getBean("LinerDataDAO");

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

		dateTextField = new JTextField();

		pressSpeedTextField = new JTextField();

		linerInfeedTextField = new JTextField();
		linerInfeedTextField.setText(0.0 + "");
		// PlainDocument doc1 = (PlainDocument)
		// linerInfeedTextField.getDocument();
		// doc1.setDocumentFilter(new MyIntFilter());
		linerInfeedTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				calculateSpoiledPercentage();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateSpoiledPercentage();

			}
		});

		shellsSPoiledTextField = new JTextField();
		shellsSPoiledTextField.setText(0.0 + "");
		// PlainDocument doc2 = (PlainDocument)
		// shellsSPoiledTextField.getDocument();
		// doc2.setDocumentFilter(new MyIntFilter());
		shellsSPoiledTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				calculateSpoiledPercentage();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateSpoiledPercentage();

			}
		});

		percentSpoiledTextField = new JTextField();
		percentSpoiledTextField.setEditable(false);
		percentSpoiledTextField.setText("0.0");

		// Fill Combos From Database
		shiftCombo = new JComboBox();
		crewCombo = new JComboBox();
		optimeNumberCombo = new JComboBox();
		shellTypeCombo = new JComboBox();
		operatorCombo = new JComboBox();
		packerCombo = new JComboBox();
		qcCombo = new JComboBox();
		pressCombo = new JComboBox();
		moduleCombo = new JComboBox();
		linerCombo = new JComboBox();
		fillCombos();
		// ////////////////////////////

		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel();

		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setTitle("Liner Data Entry");
		setSize(360, 530);
		setLocationRelativeTo(null);

		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...
		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = chooser1.getDate();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				try {
					linerDataDAO.LinerUpdate(lm);

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
		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LinerDataView(1, -1);
				dispose();
			}
		});
		search = new JButton("Search Mode");
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
		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) chooser1.getDate();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
				String year = new SimpleDateFormat("yyyy").format(selectedDate);
				String month = new SimpleDateFormat("MM").format(selectedDate);
				String day = new SimpleDateFormat("dd").format(selectedDate);

				calculateSpoiledPercentage();

				if (linerDataDAO.LinerEntryExists(lm)) {

					try {
						linerDataDAO.LinerUpdate(lm);

						// (String) shiftCombo.getSelectedItem()),
						// (String) crewCombo.getSelectedItem(), (String)
						// moduleCombo.getSelectedItem(),
						// (String) operatorCombo.getSelectedItem(), (String)
						// linerCombo.getSelectedItem(),
						// (Double)
						// Double.parseDouble(linerInfeedTextField.getText()),
						// (Double)
						// Double.parseDouble(shellsSPoiledTextField.getText()),
						// (Double)
						// Double.parseDouble(percentSpoiledTextField.getText()),
						// commentsTextArea.getText(), currentId, date,
						// Integer.parseInt;

						dispose();
						createSummaryScreen();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					try {
						
						
						lm.setDate(chooser1.getDate());
						lm.setShift((Integer)Integer.valueOf(shiftCombo.getSelectedItem()+""));
						lm.setCrew(crewCombo.getSelectedItem()+"");
						lm.setModule(moduleCombo.getSelectedItem()+"");			
						lm.setOperator(operatorCombo.getSelectedItem()+"");
						lm.setLiner(linerCombo.getSelectedItem()+"");
						lm.setLinerInfeed((Double)Double.valueOf(linerInfeedTextField.getText()+""));				
						lm.setShellsSpoiled((Double)Double.valueOf(shellsSPoiledTextField.getText()+""));
						lm.setCalculatedSpoilage(0);
						lm.setComments(commentsTextArea.getText());

						

						linerDataDAO.LinerInsert(lm);

						dispose();
//						if (frameSummary.isShowing()) {
//							frameSummary.dispose();
//						}
						createSummaryScreen();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		find = new JButton("Find Record");

		next = new JButton("Next Record");

		previous = new JButton("Previous Record");

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				try {
					createSummaryScreen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		delete = new JButton("Delete");

		dateLabel = new JLabel("Date : ", SwingConstants.CENTER);
		shiftLabel = new JLabel("Shift : ", SwingConstants.CENTER);
		crewLabel = new JLabel("Crew : ", SwingConstants.CENTER);
		moduleLabel = new JLabel("Module : ", SwingConstants.CENTER);
		operatorLabel = new JLabel("Operator : ", SwingConstants.CENTER);
		linerLabel = new JLabel("Liner : ", SwingConstants.CENTER);
		linerInfeedLabel = new JLabel("Liner Infeed: ", SwingConstants.CENTER);
		pressSpeedLabel = new JLabel("Press Speed : ", SwingConstants.CENTER);
		shellTypeLabel = new JLabel("Shell Type : ", SwingConstants.CENTER);
		shellsSpoiledLabel = new JLabel("Shells Spoiled : ", SwingConstants.CENTER);

		// percentSpoiled = new JLabel("Shells Spoiled Percent: ",
		// JLabel.CENTER);
		productionLabel = new JLabel("Production : ", SwingConstants.CENTER);
		commentsLabel = new JLabel("Comments : ", SwingConstants.CENTER);

		calculateSpoiledPercent = new JButton("Calculate Spoilage");
		calculateSpoiledPercent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateSpoiledPercentage();

			}
		});

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		buttonsPanel.add(add);
		// buttonsPanel.add(find);
//		buttonsPanel.add(previous);
//		buttonsPanel.add(next);
//		buttonsPanel.add(delete);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1
		// dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel,
		// pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;
		JPanel optionPanel1 = new JPanel(new GridLayout(9, 2));
		// optionPanel1.setBackground(Color.GRAY);

		optionPanel1.add(dateLabel);
		optionPanel1.add(chooser1);

		optionPanel1.add(shiftLabel);
		optionPanel1.add(shiftCombo);

		optionPanel1.add(crewLabel);
		optionPanel1.add(crewCombo);

		optionPanel1.add(moduleLabel);
		optionPanel1.add(moduleCombo);

		optionPanel1.add(operatorLabel);
		optionPanel1.add(operatorCombo);

		optionPanel1.add(linerLabel);
		optionPanel1.add(linerCombo);

		optionPanel1.add(linerInfeedLabel);
		optionPanel1.add(linerInfeedTextField);

		optionPanel1.add(shellsSpoiledLabel);
		optionPanel1.add(shellsSPoiledTextField);

		optionPanel1.add(calculateSpoiledPercent);
		optionPanel1.add(percentSpoiledTextField);

		commentsTextArea = new JTextArea(7, 28);
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setWrapStyleWord(true);

		JPanel commentsPanel = new JPanel();

		commentsPanel.add(commentsLabel);
		commentsPanel.add(commentsTextArea);

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

			// try {
			// currentId = linerDataDAO.LinerGetHighestID() + 1;
			// } catch (SQLException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);
			addNew.setVisible(false);

		}

		outerPanel.add(optionPanel1, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(commentsPanel, BorderLayout.NORTH);

		// Options Panel 2
		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		optionsPanel2.add(addNew);
		optionsPanel2.add(summary);
		optionsPanel2.add(search);
		optionsPanel2.add(update);
		optionsPanel2.add(add);

		optionsPanel2.setBackground(Color.GRAY);
		// outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		bottomPanel.add(optionsPanel2);

		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		// JLabel dateLabel, shiftLabel, crewLabel, operatorLabel,
		// optimeNumberLabel, pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;
		outerPanel.repaint();
		add(outerPanel);

		setVisible(true);

		// linerDataDAO.AnalyticsUpdate("LinerDataEntryScreen");

	}

	public void createSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				new LinerDataView(1, -1);

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

		// ExportToExcel.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// LinerDataView.exportToExcel();
		//
		// }
		// });

		JButton importFromViscan = new JButton("Import from Viscan");

		// importFromViscan.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// try {
		// frameSummary.dispose();
		// // LinerDataView.importFromExcel();
		// } catch (IOException ex) {
		// Logger.getLogger(LinerDataView.class.getName()).log(Level.SEVERE,
		// null, ex);
		// }
		//
		// }
		// });

		// Outer Frame
		frameSummary = new JFrame("Liner Data Report");
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
		// optionsPanel2.add(print);
		optionsPanel2.add(ExportToExcel);
		optionsPanel2.add(importFromViscan);
		// }

		JPanel summaryPanel = linerDataDAO.LinerSummaryTable(1);
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

		JComboBox[] la = linerDataDAO.fillCombos(new JComboBox[5]);

		operatorCombo.addItem("NA");

		operatorCombo = la[0];
		shiftCombo = la[1];
		crewCombo = la[2];		
		moduleCombo = la[3];
		linerCombo = la[4];
		

		// // Operator
		// try {
		//
		// String sql = "select Employees.Name from Employees ORDER BY Name
		// ASC";
		// Connection conn = linerDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// operatorCombo.addItem("NA");
		//
		// while (rs.next()) {
		//
		// String name = rs.getString("Name");
		// operatorCombo.addItem(name);
		//
		// }
		//
		// conn.close();
		//
		// } catch (Exception e) {
		//
		// System.out.println("Fill Combos Error");
		//
		// }
		//
		// // Crew
		// try {
		//
		// String sql = "SELECT Crew.CrewName FROM Crew";
		// Connection conn = linerDataDAO.Connect();
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
		// conn.close();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Shift
		// try {
		//
		// String sql = "SELECT Shift.ShiftName FROM Shift";
		// Connection conn = linerDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String shiftName = rs.getString("ShiftName");
		// shiftCombo.addItem(shiftName);
		// }
		// conn.close();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Module
		// try {
		//
		// String sql = "SELECT Module.ModuleName FROM Module";
		// Connection conn = linerDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String moduleName = rs.getString("ModuleName");
		// moduleCombo.addItem(moduleName);
		// }
		// conn.close();
		//
		// } catch (Exception e) {
		//
		// }
		//
		// // Liner
		// try {
		//
		// String sql = "SELECT Liner.LinerName FROM Liner";
		// Connection conn = linerDataDAO.Connect();
		// PreparedStatement pst = conn.prepareStatement(sql);
		// pst.setQueryTimeout(5);
		// ResultSet rs = pst.executeQuery();
		//
		// while (rs.next()) {
		//
		// String linerName = rs.getString("LinerName");
		// linerCombo.addItem(linerName);
		// }
		// conn.close();
		//
		// } catch (Exception e) {
		//
		// }

	}


	public void setLinerDataEntryToID(int id) {

		try {

			int highestID = linerDataDAO.LinerGetHighestID();
			System.out.println("Highest ID : " + highestID);
			lm = linerDataDAO.LinerReturnEntryById(id);

			// Date
			chooser1.setDate(lm.getDate());
			// CalculatedSpoilage
			shiftCombo.setSelectedItem(lm.getShift());
			crewCombo.setSelectedItem(lm.getCrew());
			moduleCombo.setSelectedItem(lm.getModule());
			operatorCombo.setSelectedItem(lm.getOperator());
			linerCombo.setSelectedItem(lm.getLiner());
			linerInfeedTextField.setText(lm.getLinerInfeed() + "");
			shellsSPoiledTextField.setText(lm.getShellsSpoiled() + "");
			percentSpoiledTextField.setText(0.0 + "");
			commentsTextArea.setText(lm.getComments());

			currentId = lm.getId();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void calculateSpoiledPercentage() {

		if (Double.parseDouble(linerInfeedTextField.getText()) > 0.0) {

			DecimalFormat df = new DecimalFormat("###.##"); // "###.###"
			// specifies
			// precision

			double y = (Double.parseDouble(linerInfeedTextField.getText()));
			double x = (Double.parseDouble(shellsSPoiledTextField.getText()));
			double answer = (x / y) * 100;

			System.out.println("X : " + x);
			System.out.println("Y : " + y);

			double answerRounded = Double.parseDouble(df.format(answer).toString());

			System.out.println("Answer: " + df.format(answer));

			percentSpoiledTextField.setText(answerRounded + "");

			System.out.println(answer);

		}

	}

}
