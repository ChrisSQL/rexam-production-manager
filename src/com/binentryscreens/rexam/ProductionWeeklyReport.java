package com.binentryscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.database.rexam.SQLiteConnection;

public class ProductionWeeklyReport {

	JLabel b64Label, shellsProducedLabel1, endsPackedLabel1, HFIOpeningLabel1, HFICreatedLabel1, HFIRecoveredLabel1, HFIScrappedLabel1, HFIRemainingLabel1, HFISpoilageLabel1,
			lineSpoilageLabel1, totalSpoilageLabel1, HFISpoilagePercentageLabel1, lineSpoilagePercentageLabel1, totalSpoilagePercentageLabel1, calculateLabel1, calculateLabel2,
			calculateLabel3;

	JTextField b64TextField;

	static JTextField shellsProducedTextField1;

	JTextField endsPackedTextField1;

	static JTextField HFIOpeningTextField1;

	static JTextField HFICreatedTextField1;

	static JTextField HFIRecoveredTextField1;

	static JTextField HFIScrappedTextField1;

	static JTextField HFIRemainingTextField1;

	static JTextField HFISpoilageTextField1;

	static JTextField lineSpoilageTextField1;

	static JTextField totalSpoilageTextField1;

	static JTextField HFISpoilagePercentageTextField1;

	static JTextField lineSpoilagePercentageTextField1;

	static JTextField totalSpoilagePercentageTextField1;

	JLabel cdlLabel, shellsProducedLabel2, endsPackedLabel2, HFIOpeningLabel2, HFICreatedLabel2, HFIRecoveredLabel2, HFIScrappedLabel2, HFIRemainingLabel2, HFISpoilageLabel2,
			lineSpoilageLabel2, totalSpoilageLabel2, HFISpoilagePercentageLabel2, lineSpoilagePercentageLabel2, totalSpoilagePercentageLabel2;

	JTextField cdlTextField;

	static JTextField shellsProducedTextField2;

	JTextField endsPackedTextField2;

	static JTextField HFIOpeningTextField2;

	static JTextField HFICreatedTextField2;

	static JTextField HFIRecoveredTextField2;

	static JTextField HFIScrappedTextField2;

	static JTextField HFIRemainingTextField2;

	static JTextField HFISpoilageTextField2;

	static JTextField lineSpoilageTextField2;

	static JTextField totalSpoilageTextField2;

	static JTextField HFISpoilagePercentageTextField2;

	static JTextField lineSpoilagePercentageTextField2;

	static JTextField totalSpoilagePercentageTextField2;

	JLabel totalsLabel, shellsProducedLabel3, endsPackedLabel3, HFIOpeningLabel3, HFICreatedLabel3, HFIRecoveredLabel3, HFIScrappedLabel3, HFIRemainingLabel3, HFISpoilageLabel3,
			lineSpoilageLabel3, totalSpoilageLabel3, HFISpoilagePercentageLabel3, lineSpoilagePercentageLabel3, totalSpoilagePercentageLabel3;

	JTextField totalsTextField;

	static JTextField shellsProducedTextField3;

	JTextField endsPackedTextField3;

	static JTextField HFIOpeningTextField3;

	static JTextField HFICreatedTextField3;

	static JTextField HFIRecoveredTextField3;

	static JTextField HFIScrappedTextField3;

	static JTextField HFIRemainingTextField3;

	static JTextField HFISpoilageTextField3, lineSpoilageTextField3, totalSpoilageTextField3, HFISpoilagePercentageTextField3;

	static JTextField lineSpoilagePercentageTextField3;

	static JTextField totalSpoilagePercentageTextField3;

	JButton calculateTotalSpoilage1, calculateTotalSpoilage2, calculateTotalSpoilage3, saveButton, backButton;

	public static void main(String[] args) {

		try {
			new ProductionWeeklyReport(1, "June", "2014");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ProductionWeeklyReport(int idIn, String monthIn, String yearIn) throws SQLException {

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

		// Labels and TextFields

		Border borderRed = BorderFactory.createLineBorder(Color.RED);
		Border borderBlue = BorderFactory.createLineBorder(Color.BLUE);
		Border borderYellow = BorderFactory.createLineBorder(Color.YELLOW);

		b64Label = new JLabel("B64", SwingConstants.CENTER);
		shellsProducedLabel1 = new JLabel("Shells Produced");
		endsPackedLabel1 = new JLabel("Ends Packed");
		HFIOpeningLabel1 = new JLabel("HFI Opening");
		HFIOpeningLabel1.setForeground(Color.RED);
		HFICreatedLabel1 = new JLabel("HFI Created");
		HFICreatedLabel1.setForeground(Color.RED);
		HFIRecoveredLabel1 = new JLabel("HFI Recovered");
		HFIRecoveredLabel1.setForeground(Color.RED);
		HFIScrappedLabel1 = new JLabel("HFI Scrapped");
		HFIScrappedLabel1.setForeground(Color.RED);
		HFIRemainingLabel1 = new JLabel("HFI Remaining");
		HFISpoilageLabel1 = new JLabel("HFI Spoilage");
		lineSpoilageLabel1 = new JLabel("Line Spoilage");
		totalSpoilageLabel1 = new JLabel("Total Spoilage");
		HFISpoilagePercentageLabel1 = new JLabel("HFI Spoilage %");
		lineSpoilagePercentageLabel1 = new JLabel("Line Spoilage %");
		totalSpoilagePercentageLabel1 = new JLabel("Total Spoilage %");

		calculateLabel1 = new JLabel("Calculate", SwingConstants.CENTER);
		calculateLabel2 = new JLabel("Calculate", SwingConstants.CENTER);
		calculateLabel3 = new JLabel("Calculate", SwingConstants.CENTER);

		b64TextField = new JTextField();
		shellsProducedTextField1 = new JTextField();
		shellsProducedTextField1.setBorder(borderYellow);
		shellsProducedTextField1.setEditable(false);
		shellsProducedTextField1.setText(SQLiteConnection.ProductionWeeklyReportTotalOptime2And3ForMonth(monthIn, yearIn) + "");
		endsPackedTextField1 = new JTextField();
		endsPackedTextField1.setEditable(false);
		endsPackedTextField1.setText(SQLiteConnection.ProductionWeeklyReportTotalAllW1And2And3sForMonth(monthIn, yearIn) + "");

		HFIOpeningTextField1 = new JTextField();
		HFIOpeningTextField1.setText(SQLiteConnection.ProductionWeeklyReportQuery(1) + "");
		HFIOpeningTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		HFICreatedTextField1 = new JTextField();
		HFICreatedTextField1.setText(SQLiteConnection.ProductionWeeklyReportQuery(3) + "");
		HFICreatedTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		HFIRecoveredTextField1 = new JTextField();
		HFIRecoveredTextField1.setText(SQLiteConnection.ProductionWeeklyReportQuery(5) + "");
		HFIRecoveredTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		HFIScrappedTextField1 = new JTextField();
		HFIScrappedTextField1.setText(SQLiteConnection.ProductionWeeklyReportQuery(7) + "");
		HFIScrappedTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});

		HFIRemainingTextField1 = new JTextField();
		HFIRemainingTextField1.setEditable(false);
		HFISpoilageTextField1 = new JTextField();
		HFISpoilageTextField1.setEditable(false);
		HFISpoilageTextField1.setText("0");
		lineSpoilageTextField1 = new JTextField();
		lineSpoilageTextField1.setEditable(false);
		lineSpoilageTextField1.setText(SQLiteConnection.ProductionWeeklyReportB64LineSpoilage(monthIn, yearIn) + "");
		totalSpoilageTextField1 = new JTextField();
		totalSpoilageTextField1.setEditable(false);
		HFISpoilagePercentageTextField1 = new JTextField();
		HFISpoilagePercentageTextField1.setEditable(false);
		HFISpoilagePercentageTextField1.setBorder(borderBlue);
		lineSpoilagePercentageTextField1 = new JTextField();
		lineSpoilagePercentageTextField1.setEditable(false);
		lineSpoilagePercentageTextField1.setBorder(borderBlue);
		totalSpoilagePercentageTextField1 = new JTextField();
		totalSpoilagePercentageTextField1.setEditable(false);
		totalSpoilagePercentageTextField1.setBorder(borderRed);

		calculateTotalSpoilage1 = new JButton("Calculate Totals");
		calculateTotalSpoilage1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotals();

			}

		});

		cdlLabel = new JLabel("CDL", SwingConstants.CENTER);
		shellsProducedLabel2 = new JLabel("Shells Produced");
		endsPackedLabel2 = new JLabel("Ends Packed");
		HFIOpeningLabel2 = new JLabel("HFI Opening");
		HFIOpeningLabel2.setForeground(Color.RED);
		HFICreatedLabel2 = new JLabel("HFI Created");
		HFICreatedLabel2.setForeground(Color.RED);
		HFIRecoveredLabel2 = new JLabel("HFI Recovered");
		HFIRecoveredLabel2.setForeground(Color.RED);
		HFIScrappedLabel2 = new JLabel("HFI Scrapped");
		HFIScrappedLabel2.setForeground(Color.RED);
		HFIRemainingLabel2 = new JLabel("HFI Remaining");
		HFISpoilageLabel2 = new JLabel("HFI Spoilage");
		lineSpoilageLabel2 = new JLabel("Line Spoilage");
		totalSpoilageLabel2 = new JLabel("Total Spoilage");
		HFISpoilagePercentageLabel2 = new JLabel("HFI Spoilage %");
		lineSpoilagePercentageLabel2 = new JLabel("Line Spoilage %");
		totalSpoilagePercentageLabel2 = new JLabel("Total Spoilage %");

		cdlTextField = new JTextField();
		shellsProducedTextField2 = new JTextField();
		shellsProducedTextField2.setBorder(borderYellow);
		shellsProducedTextField2.setEditable(false);
		shellsProducedTextField2.setText(SQLiteConnection.ProductionWeeklyReportTotalOptime4ForMonth(monthIn, yearIn) + "");
		endsPackedTextField2 = new JTextField();
		endsPackedTextField2.setEditable(false);
		endsPackedTextField2.setText(SQLiteConnection.ProductionWeeklyReportTotalAllW4sForMonth(monthIn, yearIn) + "");

		HFIOpeningTextField2 = new JTextField();
		HFIOpeningTextField2.setText(SQLiteConnection.ProductionWeeklyReportQuery(2) + "");
		HFIOpeningTextField2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		HFICreatedTextField2 = new JTextField();
		HFICreatedTextField2.setText(SQLiteConnection.ProductionWeeklyReportQuery(4) + "");
		HFICreatedTextField2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});
		HFIRecoveredTextField2 = new JTextField();
		HFIRecoveredTextField2.setText(SQLiteConnection.ProductionWeeklyReportQuery(6) + "");
		HFIRecoveredTextField2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});

		HFIScrappedTextField2 = new JTextField();
		HFIScrappedTextField2.setText(SQLiteConnection.ProductionWeeklyReportQuery(8) + "");
		HFIScrappedTextField2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub

				calculateTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				calculateTotals();

			}
		});

		HFIRemainingTextField2 = new JTextField();
		HFIRemainingTextField2.setEditable(false);
		HFISpoilageTextField2 = new JTextField();
		HFISpoilageTextField2.setEditable(false);
		HFISpoilageTextField2.setText("0");
		lineSpoilageTextField2 = new JTextField();
		lineSpoilageTextField2.setEditable(false);
		lineSpoilageTextField2.setText(SQLiteConnection.ProductionWeeklyReportCdlLineSpoilage(monthIn, yearIn) + "");
		totalSpoilageTextField2 = new JTextField();
		totalSpoilageTextField2.setEditable(false);
		HFISpoilagePercentageTextField2 = new JTextField();
		HFISpoilagePercentageTextField2.setEditable(false);
		HFISpoilagePercentageTextField2.setBorder(borderBlue);
		lineSpoilagePercentageTextField2 = new JTextField();
		lineSpoilagePercentageTextField2.setEditable(false);
		lineSpoilagePercentageTextField2.setBorder(borderBlue);
		totalSpoilagePercentageTextField2 = new JTextField();
		totalSpoilagePercentageTextField2.setEditable(false);
		totalSpoilagePercentageTextField2.setBorder(borderRed);

		calculateTotalSpoilage2 = new JButton("Calculate Totals");
		calculateTotalSpoilage2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotals();

			}

		});

		totalsLabel = new JLabel("Totals", SwingConstants.CENTER);
		shellsProducedLabel3 = new JLabel("Shells Produced");
		endsPackedLabel3 = new JLabel("Ends Packed");
		HFIOpeningLabel3 = new JLabel("HFI Opening");
		HFICreatedLabel3 = new JLabel("HFI Created");
		HFIRecoveredLabel3 = new JLabel("HFI Recovered");
		HFIScrappedLabel3 = new JLabel("HFI Scrapped");
		HFIRemainingLabel3 = new JLabel("HFI Remaining");
		HFISpoilageLabel3 = new JLabel("HFI Spoilage");
		lineSpoilageLabel3 = new JLabel("Line Spoilage");
		totalSpoilageLabel3 = new JLabel("Total Spoilage");
		HFISpoilagePercentageLabel3 = new JLabel("HFI Spoilage %");
		lineSpoilagePercentageLabel3 = new JLabel("Line Spoilage %");
		totalSpoilagePercentageLabel3 = new JLabel("Total Spoilage %");

		totalsTextField = new JTextField();
		shellsProducedTextField3 = new JTextField();
		shellsProducedTextField3.setBorder(borderYellow);
		shellsProducedTextField3.setEditable(false);
		shellsProducedTextField3.setText(SQLiteConnection.ProductionWeeklyReportTotalOptime2And3And4ForMonth(monthIn, yearIn) + "");
		endsPackedTextField3 = new JTextField();
		endsPackedTextField3.setEditable(false);
		endsPackedTextField3.setText(SQLiteConnection.ProductionWeeklyReportTotalAllWs(monthIn, yearIn) + "");
		HFIOpeningTextField3 = new JTextField();
		HFIOpeningTextField3.setText("0");
		HFIOpeningTextField3.setEditable(false);
		HFICreatedTextField3 = new JTextField();
		HFICreatedTextField3.setEditable(false);
		HFICreatedTextField3.setText("0");
		HFIRecoveredTextField3 = new JTextField();
		HFIRecoveredTextField3.setEditable(false);
		HFIRecoveredTextField3.setText("0");
		HFIScrappedTextField3 = new JTextField();
		HFIScrappedTextField3.setEditable(false);
		HFIScrappedTextField3.setText("0");
		HFIRemainingTextField3 = new JTextField();
		HFIRemainingTextField3.setEditable(false);
		HFISpoilageTextField3 = new JTextField();
		HFISpoilageTextField3.setEditable(false);
		HFISpoilageTextField3.setText("0");
		lineSpoilageTextField3 = new JTextField();
		lineSpoilageTextField3.setEditable(false);
		lineSpoilageTextField3.setText(SQLiteConnection.ProductionWeeklyReportTotalLineSpoilage(monthIn, yearIn) + "");
		totalSpoilageTextField3 = new JTextField();
		totalSpoilageTextField3.setEditable(false);
		HFISpoilagePercentageTextField3 = new JTextField();
		HFISpoilagePercentageTextField3.setEditable(false);
		HFISpoilagePercentageTextField3.setBorder(borderBlue);
		lineSpoilagePercentageTextField3 = new JTextField();
		lineSpoilagePercentageTextField3.setEditable(false);
		lineSpoilagePercentageTextField3.setBorder(borderBlue);
		totalSpoilagePercentageTextField3 = new JTextField();
		totalSpoilagePercentageTextField3.setEditable(false);
		totalSpoilagePercentageTextField3.setBorder(borderRed);

		calculateTotalSpoilage3 = new JButton("Calculate Totals");
		calculateTotalSpoilage3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotals();

			}

		});

		JFrame frame = new JFrame("Production Weekly Report");
		frame.setSize(800, 900);
		frame.setLocationRelativeTo(null);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				calculateTotals();
				updateRecord();

			}
		});
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.dispose();

			}
		});

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel leftPanel = new JPanel(new GridLayout(28, 1));
		leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// leftPanel.add(b64Label, SwingConstants.CENTER);
		// leftPanel.add(b64TextField);

		leftPanel.add(shellsProducedLabel1);
		leftPanel.add(shellsProducedTextField1); // Optime 2 and 3 total for
													// monthIn

		leftPanel.add(endsPackedLabel1);
		leftPanel.add(endsPackedTextField1);

		leftPanel.add(HFIOpeningLabel1);
		leftPanel.add(HFIOpeningTextField1);

		leftPanel.add(HFICreatedLabel1);
		leftPanel.add(HFICreatedTextField1);

		leftPanel.add(HFIRecoveredLabel1);
		leftPanel.add(HFIRecoveredTextField1);

		leftPanel.add(HFIScrappedLabel1);
		leftPanel.add(HFIScrappedTextField1);

		leftPanel.add(calculateLabel1);
		leftPanel.add(calculateTotalSpoilage1);

		leftPanel.add(HFIRemainingLabel1);
		leftPanel.add(HFIRemainingTextField1);

		leftPanel.add(HFISpoilageLabel1);
		leftPanel.add(HFISpoilageTextField1);

		leftPanel.add(lineSpoilageLabel1);
		leftPanel.add(lineSpoilageTextField1);

		leftPanel.add(totalSpoilageLabel1);
		leftPanel.add(totalSpoilageTextField1);

		leftPanel.add(HFISpoilagePercentageLabel1);
		leftPanel.add(HFISpoilagePercentageTextField1);

		leftPanel.add(lineSpoilagePercentageLabel1);
		leftPanel.add(lineSpoilagePercentageTextField1);

		leftPanel.add(totalSpoilagePercentageLabel1);
		leftPanel.add(totalSpoilagePercentageTextField1);

		JPanel middlePanel = new JPanel(new GridLayout(28, 1));
		middlePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// middlePanel.add(cdlLabel, SwingConstants.CENTER);
		// middlePanel.add(cdlTextField);

		middlePanel.add(shellsProducedLabel2);
		middlePanel.add(shellsProducedTextField2);

		middlePanel.add(endsPackedLabel2);
		middlePanel.add(endsPackedTextField2);

		middlePanel.add(HFIOpeningLabel2);
		middlePanel.add(HFIOpeningTextField2);

		middlePanel.add(HFICreatedLabel2);
		middlePanel.add(HFICreatedTextField2);

		middlePanel.add(HFIRecoveredLabel2);
		middlePanel.add(HFIRecoveredTextField2);

		middlePanel.add(HFIScrappedLabel2);
		middlePanel.add(HFIScrappedTextField2);

		middlePanel.add(calculateLabel2);
		middlePanel.add(calculateTotalSpoilage2);

		middlePanel.add(HFIRemainingLabel2);
		middlePanel.add(HFIRemainingTextField2);

		middlePanel.add(HFISpoilageLabel2);
		middlePanel.add(HFISpoilageTextField2);

		middlePanel.add(lineSpoilageLabel2);
		middlePanel.add(lineSpoilageTextField2);

		middlePanel.add(totalSpoilageLabel2);
		middlePanel.add(totalSpoilageTextField2);

		middlePanel.add(HFISpoilagePercentageLabel2);
		middlePanel.add(HFISpoilagePercentageTextField2);

		middlePanel.add(lineSpoilagePercentageLabel2);
		middlePanel.add(lineSpoilagePercentageTextField2);

		middlePanel.add(totalSpoilagePercentageLabel2);
		middlePanel.add(totalSpoilagePercentageTextField2);

		JPanel rightPanel = new JPanel(new GridLayout(28, 1));
		rightPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// rightPanel.add(totalsLabel, SwingConstants.CENTER);
		// rightPanel.add(totalsTextField);

		rightPanel.add(shellsProducedLabel3);
		rightPanel.add(shellsProducedTextField3);

		rightPanel.add(endsPackedLabel3);
		rightPanel.add(endsPackedTextField3);

		rightPanel.add(HFIOpeningLabel3);
		rightPanel.add(HFIOpeningTextField3);

		rightPanel.add(HFICreatedLabel3);
		rightPanel.add(HFICreatedTextField3);

		rightPanel.add(HFIRecoveredLabel3);
		rightPanel.add(HFIRecoveredTextField3);

		rightPanel.add(HFIScrappedLabel3);
		rightPanel.add(HFIScrappedTextField3);

		rightPanel.add(calculateLabel3);
		rightPanel.add(calculateTotalSpoilage3);

		rightPanel.add(HFIRemainingLabel3);
		rightPanel.add(HFIRemainingTextField3);

		rightPanel.add(HFISpoilageLabel3);
		rightPanel.add(HFISpoilageTextField3);

		rightPanel.add(lineSpoilageLabel3);
		rightPanel.add(lineSpoilageTextField3);

		rightPanel.add(totalSpoilageLabel3);
		rightPanel.add(totalSpoilageTextField3);

		rightPanel.add(HFISpoilagePercentageLabel3);
		rightPanel.add(HFISpoilagePercentageTextField3);

		rightPanel.add(lineSpoilagePercentageLabel3);
		rightPanel.add(lineSpoilagePercentageTextField3);

		rightPanel.add(totalSpoilagePercentageLabel3);
		rightPanel.add(totalSpoilagePercentageTextField3);

		JPanel topPanel = new JPanel(new GridLayout(1, 3));
		topPanel.setBackground(Color.GRAY);

		topPanel.add(b64Label);
		topPanel.add(cdlLabel);
		topPanel.add(totalsLabel);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(Color.GRAY);
		bottomPanel.add(backButton);
		bottomPanel.add(saveButton);

		leftPanel.setPreferredSize(new Dimension(260, 300));
		middlePanel.setPreferredSize(new Dimension(260, 300));
		rightPanel.setPreferredSize(new Dimension(260, 300));

		outerPanel.add(topPanel, BorderLayout.NORTH);
		outerPanel.add(leftPanel, BorderLayout.WEST);
		outerPanel.add(middlePanel, BorderLayout.CENTER);
		outerPanel.add(rightPanel, BorderLayout.EAST);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);

		calculateTotals();

		frame.add(outerPanel);
		frame.setVisible(true);

	}

	public static void calculateTotals() {

		int openingTotal = Integer.valueOf(HFIOpeningTextField1.getText()) + Integer.valueOf(HFIOpeningTextField2.getText());
		HFIOpeningTextField3.setText(openingTotal + "");

		int CreatedTotal = Integer.valueOf(HFICreatedTextField1.getText()) + Integer.valueOf(HFICreatedTextField2.getText());
		HFICreatedTextField3.setText(CreatedTotal + "");

		int RecoveredTotal = Integer.valueOf(HFIRecoveredTextField1.getText()) + Integer.valueOf(HFIRecoveredTextField2.getText());
		HFIRecoveredTextField3.setText(RecoveredTotal + "");

		int ScrappedTotal = Integer.valueOf(HFIScrappedTextField1.getText()) + Integer.valueOf(HFIScrappedTextField2.getText());
		HFIScrappedTextField3.setText(ScrappedTotal + "");

		int totalSpoilage1 = 0;
		if (Integer.valueOf(HFISpoilageTextField1.getText()) != null) {

			totalSpoilage1 = Integer.valueOf(HFISpoilageTextField1.getText());
			totalSpoilage1 = totalSpoilage1 + Integer.valueOf(lineSpoilageTextField1.getText());
			totalSpoilageTextField1.setText(totalSpoilage1 + "");
		}

		int totalSpoilage2 = 0;
		if (Integer.valueOf(HFISpoilageTextField2.getText()) != null) {

			totalSpoilage2 = Integer.valueOf(HFISpoilageTextField2.getText());
			totalSpoilage2 = totalSpoilage2 + Integer.valueOf(lineSpoilageTextField2.getText());
			totalSpoilageTextField2.setText(totalSpoilage2 + "");
		}

		int totalSpoilage3 = 0;
		if (Integer.valueOf(HFISpoilageTextField3.getText()) != null) {

			totalSpoilage3 = Integer.valueOf(HFISpoilageTextField3.getText());
			totalSpoilage3 = totalSpoilage3 + Integer.valueOf(lineSpoilageTextField3.getText());
			totalSpoilageTextField3.setText(totalSpoilage3 + "");
		}

		// HFI Spoilage Percentages

		DecimalFormat df = new DecimalFormat("###.##"); // <- // "###.###"
														// specifies precision
		double y1 = (Double.parseDouble(HFISpoilageTextField1.getText()));
		double x1 = (Double.parseDouble(shellsProducedTextField1.getText()));
		double answer1 = (y1 / x1) * 100;
		double answerRounded1 = Double.parseDouble(df.format(answer1).toString());
		HFISpoilagePercentageTextField1.setText(answerRounded1 + "%");

		double y2 = (Double.parseDouble(HFISpoilageTextField2.getText()));
		double x2 = (Double.parseDouble(shellsProducedTextField2.getText()));
		double answer2 = (y2 / x2) * 100;
		double answerRounded2 = Double.parseDouble(df.format(answer2).toString());
		HFISpoilagePercentageTextField2.setText(answerRounded2 + "%");

		double y3 = (Double.parseDouble(HFISpoilageTextField3.getText()));
		double x3 = (Double.parseDouble(shellsProducedTextField3.getText()));
		double answer3 = (y3 / x3) * 100;
		double answerRounded3 = Double.parseDouble(df.format(answer3).toString());
		HFISpoilagePercentageTextField3.setText(answerRounded3 + "%");

		// Line Spoilage Percentages

		double y4 = (Double.parseDouble(lineSpoilageTextField1.getText()));
		double x4 = (Double.parseDouble(shellsProducedTextField1.getText()));
		double answer4 = (y4 / x4) * 100;
		double answerRounded4 = Double.parseDouble(df.format(answer4).toString());
		lineSpoilagePercentageTextField1.setText(answerRounded4 + "%");

		double y5 = (Double.parseDouble(lineSpoilageTextField2.getText()));
		double x5 = (Double.parseDouble(shellsProducedTextField2.getText()));
		double answer5 = (y5 / x5) * 100;
		double answerRounded5 = Double.parseDouble(df.format(answer5).toString());
		lineSpoilagePercentageTextField2.setText(answerRounded5 + "%");

		double y6 = (Double.parseDouble(lineSpoilageTextField3.getText()));
		double x6 = (Double.parseDouble(shellsProducedTextField3.getText()));
		double answer6 = (y6 / x6) * 100;
		double answerRounded6 = Double.parseDouble(df.format(answer6).toString());
		lineSpoilagePercentageTextField3.setText(answerRounded6 + "%");

		// Total Spoilage Percentage

		double y7 = (Double.parseDouble(totalSpoilageTextField1.getText()));
		double x7 = (Double.parseDouble(shellsProducedTextField1.getText()));
		double answer7 = (y7 / x7) * 100;
		double answerRounded7 = Double.parseDouble(df.format(answer7).toString());
		totalSpoilagePercentageTextField1.setText(answerRounded7 + "%");

		double y8 = (Double.parseDouble(totalSpoilageTextField2.getText()));
		double x8 = (Double.parseDouble(shellsProducedTextField2.getText()));
		double answer8 = (y8 / x8) * 100;
		double answerRounded8 = Double.parseDouble(df.format(answer8).toString());
		totalSpoilagePercentageTextField2.setText(answerRounded8 + "%");

		double y9 = (Double.parseDouble(totalSpoilageTextField3.getText()));
		double x9 = (Double.parseDouble(shellsProducedTextField3.getText()));
		double answer9 = (y9 / x9) * 100;
		double answerRounded9 = Double.parseDouble(df.format(answer9).toString());
		totalSpoilagePercentageTextField3.setText(answerRounded9 + "%");

		// HFI Remaining = HFI Opening + HFI Created - (HFI Recovered + HFI
		// Scrapped);

		int remaining1 = Integer.valueOf(HFIOpeningTextField1.getText()) + Integer.valueOf(HFICreatedTextField1.getText());
		int remaining2 = Integer.valueOf(HFIRecoveredTextField1.getText()) + Integer.valueOf(HFIScrappedTextField1.getText());
		int remaining3 = remaining1 - remaining2;

		int remaining4 = Integer.valueOf(HFIOpeningTextField2.getText()) + Integer.valueOf(HFICreatedTextField2.getText());
		int remaining5 = Integer.valueOf(HFIRecoveredTextField2.getText()) + Integer.valueOf(HFIScrappedTextField2.getText());
		int remaining6 = remaining4 - remaining5;

		int remaining7 = Integer.valueOf(HFIOpeningTextField3.getText()) + Integer.valueOf(HFICreatedTextField3.getText());
		int remaining8 = Integer.valueOf(HFIRecoveredTextField3.getText()) + Integer.valueOf(HFIScrappedTextField3.getText());
		int remaining9 = remaining7 - remaining8;

		HFIRemainingTextField1.setText(remaining3 + "");
		HFIRemainingTextField2.setText(remaining6 + "");
		HFIRemainingTextField3.setText(remaining9 + "");

		int spoilage1 = Integer.valueOf(HFIRemainingTextField1.getText()) - Integer.valueOf(HFIOpeningTextField1.getText());
		int spoilage2 = spoilage1 + Integer.valueOf(HFIScrappedTextField1.getText());
		HFISpoilageTextField1.setText(spoilage2 + "");

		int spoilage3 = Integer.valueOf(HFIRemainingTextField1.getText()) - Integer.valueOf(HFIOpeningTextField1.getText());
		int spoilage4 = spoilage3 + Integer.valueOf(HFIScrappedTextField1.getText());
		HFISpoilageTextField2.setText(spoilage4 + "");

		int spoilage5 = Integer.valueOf(HFIRemainingTextField1.getText()) - Integer.valueOf(HFIOpeningTextField1.getText());
		int spoilage6 = spoilage5 + Integer.valueOf(HFIScrappedTextField1.getText());
		HFISpoilageTextField3.setText(spoilage6 + "");

		updateRecord();

	}

	public static void updateRecord() {

		try {
			SQLiteConnection.ProductionWeeklyReportUpdate(

			Integer.valueOf(HFIOpeningTextField1.getText()), Integer.valueOf(HFIOpeningTextField2.getText()), Integer.valueOf(HFICreatedTextField1.getText()),
					Integer.valueOf(HFICreatedTextField2.getText()), Integer.valueOf(HFIRecoveredTextField1.getText()), Integer.valueOf(HFIRecoveredTextField2.getText()),
					Integer.valueOf(HFIScrappedTextField1.getText()), Integer.valueOf(HFIScrappedTextField2.getText()));

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
