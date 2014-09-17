package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.apache.pdfbox.pdmodel.PDDocument;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.SQLiteConnection;

public class StolleDataEntryScreen {

	static JButton add, find, next, previous, update, addNew, search, newEntry, refresh, exportToExcel, summary;
	static JLabel dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel, pressLabel, pressSpeedLabel, shellTypeLabel, productionLabel,
			commentsLabel, packerLabel, qcInspectorLabel, stolleProductionLabel, packedEndsLabel, HFICreatedLabel, HFIRecoveredLabel,
			HFIScrappedLabel, sacobaDowntimeLabel;
	static JTextField dateTextField, pressSpeedTextField, productionTextField, stolleProductionTextField, packedEndsTextField, HFICreatedTextField,
			HFIRecoveredTextField, HFIScrappedTextField, sacobaDowntimeTextField;
	static JTextArea commentsTextArea;
	static int view, currentId;
	static Date selectedDate;
	static JComboBox operatorCombo, shiftCombo, crewCombo, pressCombo, packerCombo, qcCombo, optimeNumberCombo, shellTypeCombo;
	static JFrame frameSummary;

	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

	public static void main(String[] args) throws SQLException {

		new StolleDataEntryScreen(1, -1);

	}

	public StolleDataEntryScreen(int idIn, int view) throws SQLException {

		// Add a view to analytics.
		try {
			SQLiteConnection.incrementViewsAnalytics(0, 0, 0, 0, 0, 0, 0, 0, 1);
		}
		catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

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

		JFrame frame5 = new JFrame();
		// frame5.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JPanel outerPanel = new JPanel();
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame5.setTitle("Stolle Data Entry");
		frame5.setSize(360, 700);
		frame5.setLocationRelativeTo(null);
		outerPanel.setLayout(new BorderLayout());

		// Create Buttons , Labels, Checkboxes etc...

		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month) - 1;
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);
		model = new UtilDateModel();
		model.setDate(yearInt, monthInt, dayInt);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				try {
					frame5.dispose();
					createSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				try {
					SQLiteConnection.StolleInsert(

							// int idIn, String dateIn, int shiftIn,
							// String crewIn, String pressIn, String operatorIn,
							// String packerIn, String QCInspectorIn,
							// int stolleProductionIn, int packedEndsIn, int
							// HFICreatedIn, int HFIRecoveredIn,
							// int HFIScrappedIn, int SacobaDowntime

							SQLiteConnection.StolleGetHighestID() + 1, 
							date,
							Integer.parseInt((String) shiftCombo.getSelectedItem()), 
							(String) crewCombo.getSelectedItem(),
							(String) pressCombo.getSelectedItem(), 
							(String) operatorCombo.getSelectedItem(), 
							(String) packerCombo.getSelectedItem(),
							(String) qcCombo.getSelectedItem(), 
							Integer.parseInt(pressSpeedTextField.getText()),
							Integer.parseInt(stolleProductionTextField.getText()), 
							Integer.parseInt(packedEndsTextField.getText()),
							Integer.parseInt(HFICreatedTextField.getText()), 
							Integer.parseInt(HFIRecoveredTextField.getText()),
							Integer.parseInt(HFIScrappedTextField.getText()), 
							Integer.parseInt(sacobaDowntimeTextField.getText()),
							commentsTextArea.getText()

					);

					frame5.dispose();

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub

			}
		});

		search = new JButton("Search Mode");
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new StolleDataEntryScreen(1, -2);
					setLastEntry();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame5.dispose();

			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				try {
					new StolleDataEntryScreen(1, -1);
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame5.dispose();

			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate = (Date) datePicker.getModel().getValue();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

				try {
					SQLiteConnection.StolleUpdate(

					date, Integer.parseInt((String) shiftCombo.getSelectedItem()), (String) crewCombo.getSelectedItem(),
							(String) pressCombo.getSelectedItem(), (String) operatorCombo.getSelectedItem(), (String) packerCombo.getSelectedItem(),
							(String) qcCombo.getSelectedItem(), Integer.parseInt(pressSpeedTextField.getText()),
							Integer.parseInt(stolleProductionTextField.getText()), Integer.parseInt(packedEndsTextField.getText()),
							Integer.parseInt(HFICreatedTextField.getText()), Integer.parseInt(HFIRecoveredTextField.getText()),
							Integer.parseInt(HFIScrappedTextField.getText()), Integer.parseInt(sacobaDowntimeTextField.getText()),
							commentsTextArea.getText(), currentId

					);

					frame5.dispose();

				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub

			}
		});

		find = new JButton("Find Record");
		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				// Set ID
				try {
					selectedDate = (Date) datePicker.getModel().getValue();

					Object[] array = new Object[16];
					array = SQLiteConnection.StolleReturnEntryByDate(selectedDate);

					// String date = (String) array[1];
					// need to do

					if (array[1] == null) {

						SQLiteConnection.infoBox("No Result. Have you selected a date?", "");

					}

					else {

						currentId = (int) array[0];

						shiftCombo.setSelectedItem(array[2]);
						crewCombo.setSelectedItem(array[3]);
						pressCombo.setSelectedItem(array[4]);
						operatorCombo.setSelectedItem(array[5]);
						packerCombo.setSelectedItem(array[6]);
						qcCombo.setSelectedItem(array[7]);

						pressSpeedTextField.setText((String) array[8]);
						stolleProductionTextField.setText((String) array[9]);
						packedEndsTextField.setText((String) array[10]);
						HFICreatedTextField.setText((String) array[11]);
						HFIRecoveredTextField.setText((String) array[12]);
						HFIScrappedTextField.setText((String) array[13]);
						sacobaDowntimeTextField.setText((String) array[14]);

						commentsTextArea.setText((String) array[15]);

					}

					System.out.println("CurrentID " + currentId);

				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// currentId = (int)array[0];
				// Set Date
				// Send in

			}
		});

		next = new JButton("Next Record");
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				// Set ID
				try {

					Object[] array = SQLiteConnection.StolleGetNextEntryById(currentId);

					if (array[0] == null) {

						SQLiteConnection.infoBox("No Next Result.", "");

					}
					else {

						currentId = currentId + 1;

						System.out.println("Array[1]" + array[1]);

						String dateFormatted = (String) array[1];
						System.out.println("Date Formatted : " + dateFormatted);
						int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
						int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						shiftCombo.setSelectedItem(array[2]);
						crewCombo.setSelectedItem(array[3]);
						pressCombo.setSelectedItem(array[4]);
						operatorCombo.setSelectedItem(array[5]);
						packerCombo.setSelectedItem(array[6]);
						qcCombo.setSelectedItem(array[7]);

						pressSpeedTextField.setText((String) array[8]);
						stolleProductionTextField.setText((String) array[9]);
						packedEndsTextField.setText((String) array[10]);
						HFICreatedTextField.setText((String) array[11]);
						HFIRecoveredTextField.setText((String) array[12]);
						HFIScrappedTextField.setText((String) array[13]);
						sacobaDowntimeTextField.setText((String) array[14]);

						commentsTextArea.setText((String) array[15]);

					}

					System.out.println("CurrentID " + currentId);

					// Fill Boxes with results

					// model.setDate(year2, month2, day2);
					model.setSelected(true);

				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// currentId = (int)array[0];
				// Set Date
				// Send in

			}
		});

		previous = new JButton("Previous Record");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub

				// Set ID
				try {

					Object[] array = SQLiteConnection.StolleGetPreviousEntryById(currentId);

					if (array[0] == null) {

						SQLiteConnection.infoBox("No Previous Result.", "");

					}
					else {

						currentId = currentId - 1;

						System.out.println("Array[1]" + array[1]);

						String dateFormatted = (String) array[1];
						System.out.println("Date Formatted : " + dateFormatted);
						int year = Integer.parseInt(dateFormatted.substring(0, 4)); // Correct
						int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
						int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct

						model.setDate(year, month, day);
						model.setSelected(true);

						shiftCombo.setSelectedItem(array[2]);
						crewCombo.setSelectedItem(array[3]);
						pressCombo.setSelectedItem(array[4]);
						operatorCombo.setSelectedItem(array[5]);
						packerCombo.setSelectedItem(array[6]);
						qcCombo.setSelectedItem(array[7]);

						pressSpeedTextField.setText((String) array[8]);
						stolleProductionTextField.setText((String) array[9]);
						packedEndsTextField.setText((String) array[10]);
						HFICreatedTextField.setText((String) array[11]);
						HFIRecoveredTextField.setText((String) array[12]);
						HFIScrappedTextField.setText((String) array[13]);
						sacobaDowntimeTextField.setText((String) array[14]);

						commentsTextArea.setText((String) array[15]);

					}

					System.out.println(currentId);

					// Fill Boxes with results

				}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// currentId = (int)array[0];
				// Set Date
				// Send in

			}
		});

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

		// Buttons Top Panel

		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		// buttonsPanel.setBackground(Color.GRAY);

		buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Options Panel 1

		dateTextField = new JTextField();

		pressSpeedTextField = new JTextField();
		PlainDocument doc1 = (PlainDocument) pressSpeedTextField.getDocument();
		doc1.setDocumentFilter(new MyIntFilter());

		stolleProductionTextField = new JTextField();
		PlainDocument doc2 = (PlainDocument) stolleProductionTextField.getDocument();
		doc2.setDocumentFilter(new MyIntFilter());

		packedEndsTextField = new JTextField();
		PlainDocument doc3 = (PlainDocument) packedEndsTextField.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());

		HFICreatedTextField = new JTextField();
		PlainDocument doc4 = (PlainDocument) HFICreatedTextField.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		HFIRecoveredTextField = new JTextField();
		PlainDocument doc5 = (PlainDocument) HFIRecoveredTextField.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		HFIScrappedTextField = new JTextField();
		PlainDocument doc6 = (PlainDocument) HFIScrappedTextField.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());

		sacobaDowntimeTextField = new JTextField();
		PlainDocument doc7 = (PlainDocument) sacobaDowntimeTextField.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());

		// dateLabel, shiftLabel, crewLabel, operatorLabel, optimeNumberLabel,
		// pressSpeedLabel, shellTypeLabel,
		// productionLabel, commentsLabel;

		JPanel optionPanel1 = new JPanel(new GridLayout(14, 2));
		// optionPanel1.setBackground(Color.GRAY);

		optionPanel1.add(dateLabel);
		optionPanel1.add(datePicker);

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

		}

		// Searching
		else {

			currentId = SQLiteConnection.StolleGetHighestID() + 1;
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			model.setDate(yearInt, monthInt, dayInt);
			model.setSelected(true);
			add.setVisible(false);

		}

		JPanel commentsPanel = new JPanel();

		commentsTextArea = new JTextArea(7, 28);
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
		frame5.add(outerPanel);

		frame5.setVisible(true);

	}

	private void setLastEntry() {

		try {

			int highestID = SQLiteConnection.StolleGetHighestID();
			System.out.println("Highest ID : " + highestID);
			Object[] result = new Object[16];
			result = SQLiteConnection.StolleReturnEntryByID(highestID);

			System.out.println("Date " + result[1]);

			// Date
			String dateFormatted = (String) result[1];
			System.out.println("Date Formatted : " + dateFormatted);
			int day = Integer.parseInt(dateFormatted.substring(8, 10)); // Correct
			int month = Integer.parseInt(dateFormatted.substring(5, 7)) - 1; // Correct
			int year = Integer.parseInt(dateFormatted.substring(0,4)); // Correct
			model.setDate(year, month, day);
			model.setSelected(true);

			// Shift, Crew, Module, Operator, Liner, LinerInfeed, ShellsSpoiled,
			// CalculatedSpoilage

			shiftCombo.setSelectedItem(result[2]);
			crewCombo.setSelectedItem(result[3]);
			pressCombo.setSelectedItem(result[4]);
			operatorCombo.setSelectedItem(result[5]);
			packerCombo.setSelectedItem(result[6]);
			qcCombo.setSelectedItem(result[7]);
			pressSpeedTextField.setText((String) result[8]);
			stolleProductionTextField.setText((String) result[9]);
			packedEndsTextField.setText((String) result[10]);
			HFICreatedTextField.setText((String) result[11]);
			HFIRecoveredTextField.setText((String) result[12]);
			HFIScrappedTextField.setText((String) result[13]);
			sacobaDowntimeTextField.setText((String) result[14]);
			commentsTextArea.setText((String) result[15]);

			currentId = highestID;

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new StolleDataEntryScreen(2, -1);
				}
				catch (SQLException e1) {
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
					StolleDataEntryScreen.createSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		               
                JButton print = new JButton("Print Report");
                
                exportToExcel = new JButton("Export To Excel");
                exportToExcel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                                         
                    OptimeDataEntryScreen.exportToExcel();
                    
                    }
                });

		// Outer Frame
		frameSummary = new JFrame("Stolle Production Report");
		frameSummary.setSize(1366, 768);
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
		// }

		JPanel summaryPanel = SQLiteConnection.StolleSummaryTable(1);
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getDate("Stolle Production Report", 1);

			}
		});

		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(summaryPanel, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}

	public static void createGroupSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new StolleDataEntryScreen(1, 1);
				}
				catch (SQLException e1) {
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
					StolleDataEntryScreen.createGroupSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

                exportToExcel = new JButton("Export To Excel");
                exportToExcel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                                         
                    OptimeDataEntryScreen.exportToExcel();
                    
                    }
                });
                
		JButton printGroup = new JButton("Print Group Report");

		// Outer Frame
		frameSummary = new JFrame("Stolle Group Report");
		frameSummary.setSize(1366, 768);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		// if (view == 2) {
		optionsPanel2.add(newEntry);
		optionsPanel2.add(refresh);
		optionsPanel2.add(printGroup);
                optionsPanel2.add(exportToExcel);

		// }

		JPanel summaryPanel = SQLiteConnection.StolleSummaryGroupTable(1);
		printGroup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getDate("Stolle Group Report", 2);

			}
		});

		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(summaryPanel, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}
	
	public static void createCommentsSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new StolleDataEntryScreen(1, 1);
				}
				catch (SQLException e1) {
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
					StolleDataEntryScreen.createCommentsSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		JButton printComments = new JButton("Print Comments Report");

                exportToExcel = new JButton("Export To Excel");
                exportToExcel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                                         
                    OptimeDataEntryScreen.exportToExcel();
                    
                    }
                });
                
		// Outer Frame
		frameSummary = new JFrame("Stolle Comments Report");
		frameSummary.setSize(1366, 768);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		// if (view == 2) {
		optionsPanel2.add(newEntry);
		optionsPanel2.add(refresh);
		optionsPanel2.add(printComments);
                optionsPanel2.add(exportToExcel);

		// }

		JPanel summaryPanel = SQLiteConnection.StolleSummaryCommentsTable();
		printComments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getCommentsDate("Stolle Comments Report", 3);

			}
		});

		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(summaryPanel, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}

	public static void createEndsByMonthSummaryScreen() throws SQLException {

		newEntry = new JButton("New Entry Mode");
		newEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				try {
					new StolleDataEntryScreen(1, -1);
				}
				catch (SQLException e1) {
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
					StolleDataEntryScreen.createEndsByMonthSummaryScreen();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

                exportToExcel = new JButton("Export To Excel");
                exportToExcel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                                         
                    OptimeDataEntryScreen.exportToExcel();
                    
                    }
                });
                
		JButton printComments = new JButton("Print Shells By Month Report");

		// Outer Frame
		frameSummary = new JFrame("Stolle Ends By Month Report");
		frameSummary.setSize(1366, 768);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		// if (view == 2) {
		optionsPanel2.add(newEntry);
		optionsPanel2.add(refresh);
		optionsPanel2.add(printComments);
                optionsPanel2.add(exportToExcel);

		// }

		JPanel summaryPanel = SQLiteConnection.StolleEndsByMonthTable();
		printComments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getShellsByMonthDate("Stolle EndsByMonth Report");

			}
		});

		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(summaryPanel, BorderLayout.CENTER);
		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);
		frameSummary.add(outerPanel);
		frameSummary.setVisible(true);

	}
	
	public static void getDate(String titleIn, int reportType) {

		Date todaysDate = new Date();
		String year = new SimpleDateFormat("yyyy").format(todaysDate);
		String month = new SimpleDateFormat("MM").format(todaysDate);
		String day = new SimpleDateFormat("dd").format(todaysDate);

		String[] date = new String[2];
		Date[] dateArray = new Date[2];
		JButton printOptimeReportButton = new JButton("Stolle Report");
		JButton printOptimeGroupReportButton = new JButton("Stolle Group Report");
		JFrame frame = new JFrame(titleIn);
		frame.setSize(700, 90);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new FlowLayout());

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
		model1.setSelected(true);

		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
		model2.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));
		model2.setSelected(true);

		panel.add(new JLabel("Start Date : "));
		panel.add(datePicker1);
		panel.add(new JLabel("End Date : "));
		panel.add(datePicker2);
		panel.add(printOptimeReportButton);

		frame.add(panel);
		frame.setVisible(true);

		printOptimeReportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dateArray[0] = (Date) datePicker1.getModel().getValue();
				dateArray[1] = (Date) datePicker2.getModel().getValue();

				String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[0]);
				String modifiedDate2 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[1]);

				try {

					if (reportType == 1) {
						createReport(modifiedDate1, modifiedDate2);
						System.out.println("Clicked Report 1.");
					}
					else if (reportType == 2) {
						createGroupReport(modifiedDate1, modifiedDate2);
						System.out.println("Clicked Report 2.");
					}

				}
				catch (JRException | IOException | PrinterException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public static void createReport(String date1, String date2) throws JRException, IOException, PrinterException, SQLException {

		Connection conn = SQLiteConnection.Connect();

		File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/productiontrackingscreens/rexam/StolleProductionReport.jrxml");
		InputStream stream = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(stream);
		JasperReport report = JasperCompileManager.compileReport(design);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("Date1", date1); // note here you can add parameters which
									// would be utilized by the report
		params.put("Date2", date2);

		JasperPrint print = JasperFillManager.fillReport(report, params, conn);
		JasperExportManager.exportReportToPdfFile(print, "StolleReport" + date1 + "-" + date2 + ".pdf");

		PDDocument pdf = PDDocument.load("StolleReport" + date1 + "-" + date2 + ".pdf");
		pdf.print();
		pdf.close();

		conn.close();

		// use JasperExportManager to export report to your desired requirement

	}

	public static void createShellsByMonthReport(String date1) throws JRException, IOException, PrinterException, SQLException {

		Connection conn = SQLiteConnection.Connect();

		File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/productiontrackingscreens/rexam/StolleShellsByMonth.jrxml");
		InputStream stream = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(stream);
		JasperReport report = JasperCompileManager.compileReport(design);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("Date1", date1); // note here you can add parameters which
									// would be utilized by the report

		JasperPrint print = JasperFillManager.fillReport(report, params, conn);
		JasperExportManager.exportReportToPdfFile(print, "StolleShellsByMonthReport" + date1 + "" + "" + ".pdf");

		PDDocument pdf = PDDocument.load("StolleShellsByMonthReport" + date1 + "" + "" + ".pdf");
		pdf.print();
		pdf.close();

		conn.close();

		// use JasperExportManager to export report to your desired requirement

	}

	public static void createGroupReport(String date1, String date2) throws JRException, IOException, PrinterException, SQLException {

		Connection conn = SQLiteConnection.Connect();

		File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/productiontrackingscreens/rexam/StolleGroup.jrxml");
		InputStream stream = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(stream);
		JasperReport report = JasperCompileManager.compileReport(design);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("Date1", date1); // note here you can add parameters which
									// would be utilized by the report
		params.put("Date2", date2);

		JasperPrint print = JasperFillManager.fillReport(report, params, conn);
		JasperExportManager.exportReportToPdfFile(print, "StolleGroupReport" + date1 + "-" + date2 + ".pdf");

		PDDocument pdf = PDDocument.load("StolleGroupReport" + date1 + "-" + date2 + ".pdf");
		pdf.print();
		pdf.close();

		conn.close();

		// use JasperExportManager to export report to your desired requirement

	}

	public static void createCommentsReport(String date1, String shiftIn) throws JRException, IOException, PrinterException, SQLException {

		Connection conn = SQLiteConnection.Connect();

		File file = new File("C:/Users/Chris/Documents/SPRING/Rexam4/src/com/productiontrackingscreens/rexam/StolleCommentsReport.jrxml");
		InputStream stream = new FileInputStream(file);
		JasperDesign design = JRXmlLoader.load(stream);
		JasperReport report = JasperCompileManager.compileReport(design);

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("Date1", date1); // note here you can add parameters which
									// would be utilized by the report
		params.put("Shift", shiftIn);

		JasperPrint print = JasperFillManager.fillReport(report, params, conn);
		JasperExportManager.exportReportToPdfFile(print, "StolleCommentsReport" + date1 + "-" + shiftIn + ".pdf");

		PDDocument pdf = PDDocument.load("StolleCommentsReport" + date1 + "-" + shiftIn + ".pdf");
		pdf.print();
		pdf.close();

		conn.close();

		// use JasperExportManager to export report to your desired requirement

	}

	public static void getCommentsDate(String titleIn, int reportType) {

		Date todaysDate = new Date();
		String year = new SimpleDateFormat("yyyy").format(todaysDate);
		String month = new SimpleDateFormat("MM").format(todaysDate);
		String day = new SimpleDateFormat("dd").format(todaysDate);

		String[] crews = { "1", "2" };
		JComboBox crewsCombo = new JComboBox(crews);

		String[] date = new String[2];
		Date[] dateArray = new Date[2];
		JButton printOptimeReportButton = new JButton("Stolle Report");
		JButton printOptimeGroupReportButton = new JButton("Stolle Group Report");
		JButton printOptimeCommentsReportButton = new JButton("Print Comments Report");
		JFrame frame = new JFrame(titleIn);
		frame.setSize(700, 90);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new FlowLayout());

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
		model1.setSelected(true);

		panel.add(new JLabel("Date : "));
		panel.add(datePicker1);
		panel.add(new JLabel("Shift: "));
		panel.add(crewsCombo);
		panel.add(printOptimeCommentsReportButton);

		frame.add(panel);
		frame.setVisible(true);

		printOptimeCommentsReportButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				dateArray[0] = (Date) datePicker1.getModel().getValue();
				String crewString = crewsCombo.getSelectedObjects() + "";

				String modifiedDate1 = new SimpleDateFormat("yyyy-MM-dd").format(dateArray[0]);

				try {
					createCommentsReport(modifiedDate1, crewString);
				}
				catch (JRException | IOException | PrinterException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Clicked Report Comemnts.");

			}
		});

	}

	public static void getShellsByMonthDate(String titleIn) {

		Date todaysDate = new Date();
		String year = new SimpleDateFormat("yyyy").format(todaysDate);
		String month = new SimpleDateFormat("MM").format(todaysDate);
		String day = new SimpleDateFormat("dd").format(todaysDate);

		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		JComboBox monthsCombo = new JComboBox(months);

		String[] years = { "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
				"2028", "2029", "2030" };
		JComboBox yearsCombo = new JComboBox(years);

		JButton printShellsByMonthReport = new JButton("Print Comments Report");
		JFrame frame = new JFrame(titleIn);
		frame.setSize(700, 90);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(new FlowLayout());

		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
		JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
		model1.setDate(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day) - 1);
		model1.setSelected(true);

		panel.add(new JLabel("Date : "));
		panel.add(monthsCombo);
		panel.add(yearsCombo);
		panel.add(printShellsByMonthReport);

		frame.add(panel);
		frame.setVisible(true);

		printShellsByMonthReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String monthString = ((monthsCombo.getSelectedItem())) + "";
				String yearString = ((yearsCombo.getSelectedItem())) + "";

				String modifiedDate1 = yearString + "-" + monthString;

				System.out.println("Modidied Date : " + modifiedDate1);

				try {
					createShellsByMonthReport(modifiedDate1);
				}
				catch (JRException | IOException | PrinterException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Clicked Report Comemnts.");

			}
		});

	}

	private void fillCombos() {

		// Names

		try {

			String sql = "select Employees.Name from Employees ORDER BY Name ASC";
			Connection conn = SQLiteConnection.Connect();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setQueryTimeout(5);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String name = rs.getString("Name");
				operatorCombo.addItem(name);
				packerCombo.addItem(name);
				qcCombo.addItem(name);
			}

		}
		catch (Exception e) {

		}

		// Packers

		try {

			String sql = "SELECT Press.PressName FROM Press ORDER BY PressName ASC";
			Connection conn = SQLiteConnection.Connect();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setQueryTimeout(5);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String pressName = rs.getString("PressName");
				pressCombo.addItem(pressName);
			}

		}
		catch (Exception e) {

		}

		// Crew
		try {

			String sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
			Connection conn = SQLiteConnection.Connect();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setQueryTimeout(5);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				crewCombo.addItem(crewName);
			}

		}
		catch (Exception e) {

		}

		// Shift
		try {

			String sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName ASC";
			Connection conn = SQLiteConnection.Connect();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setQueryTimeout(5);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String shiftName = rs.getString("ShiftName");
				shiftCombo.addItem(shiftName);
			}

		}
		catch (Exception e) {

		}

	}

}
