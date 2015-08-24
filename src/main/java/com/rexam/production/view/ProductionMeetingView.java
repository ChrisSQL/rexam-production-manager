package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.production.dao.ProductionMeetingDAO;
import com.rexam.production.model.ProductionMeetingModel;
import com.toedter.calendar.JDateChooser;

public class ProductionMeetingView extends JFrame {

	private JButton add, find, next, previous, update, addNew, search, refresh, summary;
	private JLabel date, meetingDate, safetyIssues, hygieneManagementOfChange, productionActions, engineeringActions,
			NonPetUnlinedSilverShells, NonPetlinedSilverShells, NonPetUnlinedGoldRx219, NonPetlinedGoldRx219,
			Mod4FUnlinedSilverShells, Mod4FlinedSilverShells, NonPetUnlinedSilver215, NonPetlinedSilver215,
			A03HiFiShells, A04HiFiShells, A03HiFiShellsRX219, A04HiFiShellsRX219, A13HiFiShells, A14HiFiShells,
			A07HiFiShells, A08HiFiShells, daysRemainingLabel, packedEndsLabel, monthlyLineLoadLabel, remainingLabel,
			dailyAverageLabel, spoiledPercentageLabel, daysGoneLabel, lineLoadLabel, packedVsLineLoadLabel, blank1,
			blank2, blank3, blank4, blank5, blank6, blank7, blank8, blank9, blank10, blank11, blank12, blank13, blank14,
			blank15, blank16, blank17, blank18, blank19, blank20, blank21, blank22, total1;
	private JTextArea safetyIssuesTextArea, hygieneManagementOfChangeTextArea, productionActionsTextArea,
			engineeringActionsTextArea;
	private Date selectedDate1, selectedDate2;
	private int currentId;
	private Date selectedDate;
	private JTextField NonPetUnlinedSilverShellsTextField1, NonPetlinedSilverShellsTextField1,
			NonPetlinedSilverShellsTotal, NonPetUnlinedSilverShellsTextField2, NonPetUnlinedSilverShellsTotal,
			NonPetlinedSilverShellsTextField2, NonPetUnlinedGoldRx219TextField1, NonPetUnlinedGoldRx219TextField2,
			NonPetUnlinedGoldRx219Total, NonPetlinedGoldRx219TextField1, NonPetlinedGoldRx219TextField2,
			NonPetlinedGoldRx219Total, Mod4FUnlinedSilverShellsTextField1, Mod4FUnlinedSilverShellsTextField2,
			Mod4FUnlinedSilverShellsTextFieldTotal, Mod4FlinedSilverShellsTextField1, Mod4FlinedSilverShellsTextField2,
			Mod4FlinedSilverShellsTextFieldTotal, NonPetUnlinedSilver215TextField1, NonPetUnlinedSilver215TextField2,
			NonPetUnlinedSilver215TextFieldTotal, NonPetlinedSilver215TextField1, NonPetlinedSilver215TextField2,
			NonPetlinedSilver215TextFieldTotal, A03HiFiShellsTextField1, A03HiFiShellsTextField2,
			A03HiFiShellsTextFieldTotal, A04HiFiShellsTextField1, A04HiFiShellsTextField2, A04HiFiShellsTextFieldTotal,
			A03HiFiShellsRX219TextField1, A03HiFiShellsRX219TextField2, A03HiFiShellsRX219TextFieldTotal,
			A04HiFiShellsRX219TextField1, A04HiFiShellsRX219TextField2, A04HiFiShellsRX219TextFieldTotal,
			A13HiFiShellsTextField1, A13HiFiShellsTextField2, A13HiFiShellsTextFieldTotal, A14HiFiShellsTextField1,
			A14HiFiShellsTextField2, A14HiFiShellsTextFieldTotal, A07HiFiShellsTextField1, A07HiFiShellsTextField2,
			A07HiFiShellsTextFieldTotal, A08HiFiShellsTextField1, A08HiFiShellsTextField2, A08HiFiShellsTextFieldTotal,
			daysRemainingTextField, packedEndsTextField, monthlyLineLoadTextField, remainingTextField,
			dailyAverageTextField, spoiledPercentageTextField, daysGoneTextField, lineLoadTextField,
			packedVsLineLoadTextField, meetingDateText;
	private JFrame frameSummary;
	private int view1;
	private JDateChooser chooser1, chooser2;
	private ProductionMeetingDAO productionMeetingDAO;
	private ProductionMeetingModel pm;

	public static void main(String[] args) {

		new ProductionMeetingView(1, -1);
	}

	public ProductionMeetingView(int id, int view) {
		
		pm = new ProductionMeetingModel();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		productionMeetingDAO = (ProductionMeetingDAO) context.getBean("ProductionMeetingDAO");

		view1 = view;

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

		setTitle("Production Meeting");
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1300, 900);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		

		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// UpperPanel
		JPanel innerPanelUpper = new JPanel(new BorderLayout());
		outerPanel.add(innerPanelUpper);

		// TopButton Panel
		date = new JLabel("Date", SwingConstants.CENTER);
		meetingDate = new JLabel("Meeting Date", SwingConstants.CENTER);

		// Date 1 /////////////////////
		chooser1 = new JDateChooser(new Date());
		chooser2 = new JDateChooser(new Date());		
		// /////////////////////////////
		

		meetingDateText = new JTextField();
		JPanel topButtonPanel = new JPanel(new GridLayout(1, 4));

		topButtonPanel.add(date);
		topButtonPanel.add(chooser1);
		topButtonPanel.add(meetingDate);
		topButtonPanel.add(chooser2);
		topButtonPanel.setBackground(Color.GRAY);
		innerPanelUpper.add(topButtonPanel, BorderLayout.NORTH);

		// MiddlePanel JLabels , JTextAreas
		JPanel middlePanel = new JPanel(new GridLayout(2, 2));

		// 4 panels with BorderLayouts
		JPanel panel1 = new JPanel(new BorderLayout());
		safetyIssues = new JLabel("Safety Issues", SwingConstants.CENTER);
		panel1.add(safetyIssues, BorderLayout.NORTH);
		safetyIssuesTextArea = new JTextArea("NA");
		safetyIssuesTextArea.setLineWrap(true);
		safetyIssuesTextArea.setWrapStyleWord(true);
		panel1.add(safetyIssuesTextArea, BorderLayout.CENTER);

		JPanel panel2 = new JPanel(new BorderLayout());
		productionActions = new JLabel("Production Action", SwingConstants.CENTER);
		panel2.add(productionActions, BorderLayout.NORTH);
		productionActionsTextArea = new JTextArea("NA");
		productionActionsTextArea = new JTextArea("NA");
		productionActionsTextArea.setLineWrap(true);
		panel2.add(productionActionsTextArea, BorderLayout.CENTER);

		JPanel panel3 = new JPanel(new BorderLayout());
		hygieneManagementOfChange = new JLabel("Hygiene/Management Of Change", SwingConstants.CENTER);
		panel3.add(hygieneManagementOfChange, BorderLayout.NORTH);
		hygieneManagementOfChangeTextArea = new JTextArea("NA");
		hygieneManagementOfChangeTextArea.setSize(new Dimension(250, 200));
		hygieneManagementOfChangeTextArea = new JTextArea("NA");
		hygieneManagementOfChangeTextArea.setLineWrap(true);
		panel3.add(hygieneManagementOfChangeTextArea, BorderLayout.CENTER);

		JPanel panel4 = new JPanel(new BorderLayout());
		engineeringActions = new JLabel("Engineering Actions", SwingConstants.CENTER);
		panel4.add(engineeringActions, BorderLayout.NORTH);
		engineeringActionsTextArea = new JTextArea("NA");
		engineeringActionsTextArea = new JTextArea("NA");
		engineeringActionsTextArea.setLineWrap(true);
		panel4.add(engineeringActionsTextArea, BorderLayout.CENTER);

		// JPanel labelHeader4 = new JPanel();
		// engineeringActions = new JLabel("Engineering Actions");
		// labelHeader4.add(engineeringActions);
		// panel4.add(labelHeader4);
		middlePanel.add(panel1);
		middlePanel.add(panel2);
		middlePanel.add(panel3);
		middlePanel.add(panel4);

		// JTablePanel
		JPanel tablePanel = new JPanel(new GridLayout(12, 8));
		// JTable table = new JTable(12, 8);
		// tablePanel.add(table);

		daysRemainingLabel = new JLabel("Days Remaining");
		packedEndsLabel = new JLabel("Packed Ends ");
		monthlyLineLoadLabel = new JLabel("Monthly Line Load");
		remainingLabel = new JLabel("Remaining");
		dailyAverageLabel = new JLabel("Daily Average");
		spoiledPercentageLabel = new JLabel("Spoiled Percentage");
		daysGoneLabel = new JLabel("Days Gone");
		lineLoadLabel = new JLabel("Line Load");
		packedVsLineLoadLabel = new JLabel("Packed Vs Line Load");

		blank1 = new JLabel("");
		blank2 = new JLabel("");
		blank3 = new JLabel("");
		blank4 = new JLabel("");
		blank5 = new JLabel("");
		blank6 = new JLabel("");
		blank7 = new JLabel("");
		blank8 = new JLabel("");
		blank9 = new JLabel("");
		blank10 = new JLabel("");
		blank11 = new JLabel("");
		blank12 = new JLabel("");
		blank13 = new JLabel("");
		blank14 = new JLabel("");
		blank15 = new JLabel("");
		blank16 = new JLabel("");
		blank17 = new JLabel("");
		blank18 = new JLabel("");
		blank19 = new JLabel("");
		blank20 = new JLabel("");
		blank21 = new JLabel("");
		blank22 = new JLabel("");

		total1 = new JLabel("0");

		NonPetUnlinedSilverShells = new JLabel("Non Pet Unlined SilverShells");
		NonPetlinedSilverShells = new JLabel("Non Pet lined Silver Shells");
		NonPetUnlinedGoldRx219 = new JLabel("Non Pet Unlined Gold Rx219");
		NonPetlinedGoldRx219 = new JLabel("Non Pet lined Gold Rx219");
		Mod4FUnlinedSilverShells = new JLabel("Mod4F Unlined Silver Shells");
		Mod4FlinedSilverShells = new JLabel("Mod4F lined Silver Shells");
		NonPetUnlinedSilver215 = new JLabel("Non Pet Unlined Silver 215");
		NonPetlinedSilver215 = new JLabel("Non Pet lined Silver 215");
		A03HiFiShells = new JLabel("A03 HiFi Shells");
		A04HiFiShells = new JLabel("A04 HiFi Shells");
		A03HiFiShellsRX219 = new JLabel("A03 HiFi Shells RX219");
		A04HiFiShellsRX219 = new JLabel("A04 HiFi Shells RX219");
		A13HiFiShells = new JLabel("13 HiFi Shells");
		A14HiFiShells = new JLabel("14 HiFi Shells");
		A07HiFiShells = new JLabel("07 HiFi Shells");
		A08HiFiShells = new JLabel("08 HiFi Shells");

		// -------------------------------------------------------------------------
		NonPetUnlinedSilverShellsTextField1 = new JTextField("0");
		NonPetUnlinedSilverShellsTextField1.setText("0");
		NonPetUnlinedSilverShellsTextField2 = new JTextField("0");
		NonPetlinedSilverShellsTextField1 = new JTextField("0");
		NonPetlinedSilverShellsTextField1.setText("0");
		NonPetlinedSilverShellsTextField2 = new JTextField("0");
		NonPetlinedSilverShellsTotal = new JTextField("0");
		NonPetlinedSilverShellsTotal.setBackground(new Color(169, 198, 236));

		NonPetUnlinedSilverShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedSilverShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedSilverShellsTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		NonPetUnlinedGoldRx219TextField1 = new JTextField("0");
		NonPetUnlinedGoldRx219TextField1.setText("0");
		NonPetUnlinedGoldRx219TextField2 = new JTextField("0");

		NonPetlinedGoldRx219TextField1 = new JTextField("0");
		NonPetlinedGoldRx219TextField1.setText("0");
		NonPetlinedGoldRx219TextField2 = new JTextField("0");
		NonPetlinedGoldRx219Total = new JTextField("0");
		NonPetlinedGoldRx219Total.setBackground(new Color(169, 198, 236));

		NonPetUnlinedGoldRx219TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedGoldRx219TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedGoldRx219Total.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		Mod4FUnlinedSilverShellsTextField1 = new JTextField("0");
		Mod4FUnlinedSilverShellsTextField1.setText("0");
		Mod4FUnlinedSilverShellsTextField2 = new JTextField("0");

		Mod4FlinedSilverShellsTextField1 = new JTextField("0");
		Mod4FlinedSilverShellsTextField1.setText("0");
		Mod4FlinedSilverShellsTextField2 = new JTextField("0");
		Mod4FlinedSilverShellsTextFieldTotal = new JTextField("0");
		Mod4FlinedSilverShellsTextFieldTotal.setBackground(new Color(169, 198, 236));

		Mod4FUnlinedSilverShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		Mod4FlinedSilverShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		Mod4FlinedSilverShellsTextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		NonPetUnlinedSilver215TextField1 = new JTextField("0");
		NonPetUnlinedSilver215TextField1.setText("0");
		NonPetUnlinedSilver215TextField2 = new JTextField("0");

		NonPetlinedSilver215TextField1 = new JTextField("0");
		NonPetlinedSilver215TextField1.setText("0");
		NonPetlinedSilver215TextField2 = new JTextField("0");
		NonPetlinedSilver215TextFieldTotal = new JTextField("0");
		NonPetlinedSilver215TextFieldTotal.setBackground(new Color(169, 198, 236));

		NonPetUnlinedSilver215TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedSilver215TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		NonPetlinedSilver215TextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		A03HiFiShellsTextField1 = new JTextField("0");
		A03HiFiShellsTextField1.setText("0");
		A03HiFiShellsTextField2 = new JTextField("0");

		A04HiFiShellsTextField1 = new JTextField("0");
		A04HiFiShellsTextField1.setText("0");
		A04HiFiShellsTextField2 = new JTextField("0");
		A04HiFiShellsTextFieldTotal = new JTextField("0");
		A04HiFiShellsTextFieldTotal.setBackground(new Color(169, 198, 236));

		A03HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A04HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A04HiFiShellsTextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		A03HiFiShellsRX219TextField1 = new JTextField("0");
		A03HiFiShellsRX219TextField1.setText("0");
		A03HiFiShellsRX219TextField2 = new JTextField("0");

		A04HiFiShellsRX219TextField1 = new JTextField("0");
		A04HiFiShellsRX219TextField1.setText("0");
		A04HiFiShellsRX219TextField2 = new JTextField("0");
		A04HiFiShellsRX219TextFieldTotal = new JTextField("0");
		A04HiFiShellsRX219TextFieldTotal.setBackground(new Color(169, 198, 236));

		A03HiFiShellsRX219TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A04HiFiShellsRX219TextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A04HiFiShellsRX219TextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		A13HiFiShellsTextField1 = new JTextField("0");
		A13HiFiShellsTextField1.setText("0");
		A13HiFiShellsTextField2 = new JTextField("0");

		A14HiFiShellsTextField1 = new JTextField("0");
		A14HiFiShellsTextField1.setText("0");
		A14HiFiShellsTextField2 = new JTextField("0");
		A14HiFiShellsTextFieldTotal = new JTextField("0");
		A14HiFiShellsTextFieldTotal.setBackground(new Color(169, 198, 236));

		A13HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A14HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A14HiFiShellsTextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		A07HiFiShellsTextField1 = new JTextField("0");
		A07HiFiShellsTextField1.setText("0");
		A07HiFiShellsTextField2 = new JTextField("0");

		A08HiFiShellsTextField1 = new JTextField("");
		A08HiFiShellsTextField1.setText("0");

		A08HiFiShellsTextField2 = new JTextField("0");
		A08HiFiShellsTextFieldTotal = new JTextField("0");
		A08HiFiShellsTextFieldTotal.setBackground(new Color(169, 198, 236));

		A07HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A08HiFiShellsTextField1.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});
		A08HiFiShellsTextFieldTotal.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		daysRemainingTextField = new JTextField("1");
		daysRemainingTextField.setText("1");
		daysRemainingTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		packedEndsTextField = new JTextField("1");
		packedEndsTextField.setText("1");
		packedEndsTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		monthlyLineLoadTextField = new JTextField("");
		monthlyLineLoadTextField.setText("770600000");
		monthlyLineLoadTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		remainingTextField = new JTextField("1");
		remainingTextField.setText("1");
		remainingTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		dailyAverageTextField = new JTextField("0");
		dailyAverageTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		spoiledPercentageTextField = new JTextField("0.0");
		spoiledPercentageTextField.setBackground(new Color(169, 198, 236));
		spoiledPercentageTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		daysGoneTextField = new JTextField("1");
		daysGoneTextField.setBackground(new Color(169, 198, 236));
		daysGoneTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		lineLoadTextField = new JTextField("");
		lineLoadTextField.setText("24166667");
		lineLoadTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		packedVsLineLoadTextField = new JTextField("0");
		packedVsLineLoadTextField.setBackground(new Color(169, 198, 236));
		packedVsLineLoadTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				// TODO Auto-generated method stub
				refreshTotals();
			}

			@Override
			public void focusGained(FocusEvent e) {

				refreshTotals();

			}
		});

		// -------------------------------------------------------------------------
		tablePanel.add(NonPetUnlinedSilverShells);
		tablePanel.add(NonPetUnlinedSilverShellsTextField1);
		tablePanel.add(NonPetUnlinedSilverShellsTextField2);

		tablePanel.add(A03HiFiShells);
		tablePanel.add(A03HiFiShellsTextField1);
		tablePanel.add(A03HiFiShellsTextField2);

		tablePanel.add(daysRemainingLabel);
		tablePanel.add(daysRemainingTextField);

		tablePanel.add(NonPetlinedSilverShells);
		tablePanel.add(NonPetlinedSilverShellsTextField1);
		tablePanel.add(NonPetlinedSilverShellsTextField2);

		tablePanel.add(A04HiFiShells);
		tablePanel.add(A04HiFiShellsTextField1);
		tablePanel.add(A04HiFiShellsTextField2);

		tablePanel.add(packedEndsLabel);
		tablePanel.add(packedEndsTextField);

		tablePanel.add(blank1);
		tablePanel.add(blank2);
		tablePanel.add(NonPetlinedSilverShellsTotal);

		tablePanel.add(blank3);
		tablePanel.add(blank4);
		tablePanel.add(A04HiFiShellsTextFieldTotal);

		tablePanel.add(monthlyLineLoadLabel);
		tablePanel.add(monthlyLineLoadTextField);

		tablePanel.add(NonPetUnlinedGoldRx219);
		tablePanel.add(NonPetUnlinedGoldRx219TextField1);
		tablePanel.add(NonPetUnlinedGoldRx219TextField2);

		tablePanel.add(A03HiFiShellsRX219);
		tablePanel.add(A03HiFiShellsRX219TextField1);
		tablePanel.add(A03HiFiShellsRX219TextField2);

		tablePanel.add(remainingLabel);
		tablePanel.add(remainingTextField);

		tablePanel.add(NonPetlinedGoldRx219);
		tablePanel.add(NonPetlinedGoldRx219TextField1);
		tablePanel.add(NonPetlinedGoldRx219TextField2);

		tablePanel.add(A04HiFiShellsRX219);
		tablePanel.add(A04HiFiShellsRX219TextField1);
		tablePanel.add(A04HiFiShellsRX219TextField2);

		tablePanel.add(dailyAverageLabel);
		tablePanel.add(dailyAverageTextField);

		tablePanel.add(blank5);
		tablePanel.add(blank6);
		tablePanel.add(NonPetlinedGoldRx219Total);

		tablePanel.add(blank7);
		tablePanel.add(blank8);
		tablePanel.add(A04HiFiShellsRX219TextFieldTotal);

		tablePanel.add(spoiledPercentageLabel);
		tablePanel.add(spoiledPercentageTextField);

		tablePanel.add(Mod4FUnlinedSilverShells);
		tablePanel.add(Mod4FUnlinedSilverShellsTextField1);
		tablePanel.add(Mod4FUnlinedSilverShellsTextField2);

		tablePanel.add(A13HiFiShells);
		tablePanel.add(A13HiFiShellsTextField1);
		tablePanel.add(A13HiFiShellsTextField2);

		tablePanel.add(daysGoneLabel);
		tablePanel.add(daysGoneTextField);

		tablePanel.add(Mod4FlinedSilverShells);
		tablePanel.add(Mod4FlinedSilverShellsTextField1);
		tablePanel.add(Mod4FlinedSilverShellsTextField2);

		tablePanel.add(A14HiFiShells);
		tablePanel.add(A14HiFiShellsTextField1);
		tablePanel.add(A14HiFiShellsTextField2);

		tablePanel.add(lineLoadLabel);
		tablePanel.add(lineLoadTextField);

		tablePanel.add(blank9);
		tablePanel.add(blank10);
		tablePanel.add(Mod4FlinedSilverShellsTextFieldTotal);

		tablePanel.add(blank11);
		tablePanel.add(blank12);
		tablePanel.add(A14HiFiShellsTextFieldTotal);

		tablePanel.add(packedVsLineLoadLabel);
		tablePanel.add(packedVsLineLoadTextField);

		tablePanel.add(NonPetUnlinedSilver215);
		tablePanel.add(NonPetUnlinedSilver215TextField1);
		tablePanel.add(NonPetUnlinedSilver215TextField2);

		tablePanel.add(A07HiFiShells);
		tablePanel.add(A07HiFiShellsTextField1);
		tablePanel.add(A07HiFiShellsTextField2);

		tablePanel.add(blank13);
		tablePanel.add(blank14);

		tablePanel.add(NonPetlinedSilver215);
		tablePanel.add(NonPetlinedSilver215TextField1);
		tablePanel.add(NonPetlinedSilver215TextField2);

		tablePanel.add(A08HiFiShells);
		tablePanel.add(A08HiFiShellsTextField1);
		tablePanel.add(A08HiFiShellsTextField2);

		tablePanel.add(blank15);
		tablePanel.add(blank16);

		tablePanel.add(blank17);
		tablePanel.add(blank18);
		tablePanel.add(NonPetlinedSilver215TextFieldTotal);

		tablePanel.add(blank19);
		tablePanel.add(blank20);
		tablePanel.add(A08HiFiShellsTextFieldTotal);

		tablePanel.add(blank21);
		tablePanel.add(blank22);

		// -------------------------------------------------------------------------
		JPanel centerPanel = new JPanel(new BorderLayout());

		centerPanel.add(tablePanel, BorderLayout.SOUTH);
		centerPanel.add(middlePanel, BorderLayout.CENTER);

		innerPanelUpper.add(centerPanel);
		// innerPanelUpper.add(tablePanel, BorderLayout.SOUTH);

		// LowerPanel
		JPanel innerPanelLower = new JPanel(new BorderLayout());

		// ButtonPanel
		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedDate1 = (Date) chooser1.getDate();
				String date1 = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate1);

				selectedDate2 = (Date) chooser2.getDate();
				String date2 = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate2);

				try {
					
					pm.setDate(chooser1.getDate());
					pm.setMeetingDate(chooser2.getDate());

					pm.setSafetyIssues(safetyIssuesTextArea.getText());
					pm.setProductionAction(productionActionsTextArea.getText());
					pm.setHygieneManagementOfChange(hygieneManagementOfChangeTextArea.getText());
					pm.setEngineeringActions(engineeringActionsTextArea.getText());

					pm.setNonPetUnlinedSilverShells(Integer.valueOf(NonPetUnlinedSilverShellsTextField1.getText()));
					pm.setNonPetlinedSilverShells(Integer.valueOf(NonPetlinedSilverShellsTextField1.getText()));
					pm.setNonPetlinedSilverShellsTotal(Integer.valueOf(NonPetlinedSilverShellsTotal.getText()));
					pm.setNonPetUnlinedGoldRx219(Integer.valueOf(NonPetUnlinedGoldRx219TextField1.getText()));
					pm.setNonPetlinedGoldRx219(Integer.valueOf(NonPetlinedGoldRx219TextField1.getText()));
					pm.setNonPetlinedGoldRx219Total(Integer.valueOf(NonPetlinedGoldRx219Total.getText()));
					pm.setMod4FUnlinedSilverShells(Integer.valueOf(Mod4FUnlinedSilverShellsTextField1.getText()));
					pm.setMod4FlinedSilverShells(Integer.valueOf(Mod4FlinedSilverShellsTextField1.getText()));
					pm.setMod4FlinedSilverShellsTotal(Integer.valueOf(Mod4FlinedSilverShellsTextFieldTotal.getText()));
					pm.setNonPetUnlinedSilver215(Integer.valueOf(NonPetUnlinedSilver215TextField1.getText()));
					pm.setNonPetlinedSilver215(Integer.valueOf(NonPetlinedSilver215TextField1.getText()));
					pm.setNonPetlinedSilver215Total(Integer.valueOf(NonPetlinedSilver215TextFieldTotal.getText()));
					pm.setA03HiFiShells(Integer.valueOf(A03HiFiShellsRX219TextField1.getText()));
					pm.setA04HiFiShells(Integer.valueOf(A04HiFiShellsTextField1.getText()));
					pm.setA04HiFiShellsTotal(Integer.valueOf(A04HiFiShellsTextFieldTotal.getText()));
					pm.setA03HiFiShellsRX219(Integer.valueOf(A03HiFiShellsRX219TextField1.getText()));
					pm.setA04HiFiShellsRX219(Integer.valueOf(A04HiFiShellsRX219TextField1.getText()));
					pm.setA04HiFiShellsRX219Total(Integer.valueOf(A04HiFiShellsRX219TextFieldTotal.getText()));
					pm.setA13HiFiShells(Integer.valueOf(A13HiFiShellsTextField1.getText()));
					pm.setA14HiFiShells(Integer.valueOf(A14HiFiShellsTextField1.getText()));
					pm.setA14HiFiShellsTotal(Integer.valueOf(A14HiFiShellsTextFieldTotal.getText()));
					pm.setA07HiFiShells(Integer.valueOf(A07HiFiShellsTextField1.getText()));
					pm.setA08HiFiShells(Integer.valueOf(A08HiFiShellsTextField2.getText()));
					pm.setA08HiFiShellsTotal(Integer.valueOf(A08HiFiShellsTextFieldTotal.getText()));
					
					pm.setDaysRemaining(Integer.valueOf(daysRemainingTextField.getText()));
					pm.setPackedEnds(Integer.valueOf(packedEndsTextField.getText()));
					pm.setMonthlyLineLoad(Integer.valueOf(monthlyLineLoadTextField.getText()));
					pm.setRemaining(Integer.valueOf(remainingTextField.getText()));
					pm.setDailyAverage(Integer.valueOf(dailyAverageTextField.getText()));
					pm.setSpoiledPercentage(Double.valueOf(spoiledPercentageTextField.getText()));
					pm.setDaysGone(Integer.valueOf(daysGoneTextField.getText()));
					pm.setLineLoad(Integer.valueOf(lineLoadTextField.getText()));
					pm.setPackedVsLineLoad(Integer.valueOf(packedVsLineLoadTextField.getText()));
					
					
					productionMeetingDAO.ProductionMeetingInsert(pm);

					dispose();
					createSummaryScreen();

				} catch (SQLException e1) {
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

				try {
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(ProductionMeetingView.class.getName()).log(Level.SEVERE, null, ex);
				}
				dispose();
			}
		});

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new ProductionMeetingView(1, -1);
				dispose();
			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				pm.setDate(chooser1.getDate());
				pm.setMeetingDate(chooser2.getDate());

				pm.setSafetyIssues(safetyIssuesTextArea.getText());
				pm.setProductionAction(productionActionsTextArea.getText());
				pm.setHygieneManagementOfChange(hygieneManagementOfChangeTextArea.getText());
				pm.setEngineeringActions(engineeringActionsTextArea.getText());

				pm.setNonPetUnlinedSilverShells(Integer.valueOf(NonPetUnlinedSilverShellsTextField1.getText()));
				pm.setNonPetlinedSilverShells(Integer.valueOf(NonPetlinedSilverShellsTextField1.getText()));
				pm.setNonPetlinedSilverShellsTotal(Integer.valueOf(NonPetlinedSilverShellsTotal.getText()));
				pm.setNonPetUnlinedGoldRx219(Integer.valueOf(NonPetUnlinedGoldRx219TextField1.getText()));
				pm.setNonPetlinedGoldRx219(Integer.valueOf(NonPetlinedGoldRx219TextField1.getText()));
				pm.setNonPetlinedGoldRx219Total(Integer.valueOf(NonPetlinedGoldRx219Total.getText()));
				pm.setMod4FUnlinedSilverShells(Integer.valueOf(Mod4FUnlinedSilverShellsTextField1.getText()));
				pm.setMod4FlinedSilverShells(Integer.valueOf(Mod4FlinedSilverShellsTextField1.getText()));
				pm.setMod4FlinedSilverShellsTotal(Integer.valueOf(Mod4FlinedSilverShellsTextFieldTotal.getText()));
				pm.setNonPetUnlinedSilver215(Integer.valueOf(NonPetUnlinedSilver215TextField1.getText()));
				pm.setNonPetlinedSilver215(Integer.valueOf(NonPetlinedSilver215.getText()));
				pm.setNonPetlinedSilver215Total(Integer.valueOf(NonPetlinedSilver215TextFieldTotal.getText()));
				pm.setA03HiFiShells(Integer.valueOf(A03HiFiShellsRX219TextField1.getText()));
				pm.setA04HiFiShells(Integer.valueOf(A04HiFiShellsTextField1.getText()));
				pm.setA04HiFiShellsTotal(Integer.valueOf(A04HiFiShellsTextFieldTotal.getText()));
				pm.setA03HiFiShellsRX219(Integer.valueOf(A03HiFiShellsRX219TextField1.getText()));
				pm.setA04HiFiShellsRX219(Integer.valueOf(A04HiFiShellsRX219TextField1.getText()));
				pm.setA04HiFiShellsRX219Total(Integer.valueOf(A04HiFiShellsRX219TextFieldTotal.getText()));
				pm.setA13HiFiShells(Integer.valueOf(A13HiFiShellsTextField1.getText()));
				pm.setA14HiFiShells(Integer.valueOf(A14HiFiShellsTextField1.getText()));
				pm.setA14HiFiShellsTotal(Integer.valueOf(A14HiFiShellsTextFieldTotal.getText()));
				pm.setA07HiFiShells(Integer.valueOf(A07HiFiShellsTextField1.getText()));
				pm.setA08HiFiShells(Integer.valueOf(A08HiFiShellsTextField2.getText()));
				pm.setA08HiFiShellsTotal(Integer.valueOf(A08HiFiShellsTextFieldTotal.getText()));
				
				pm.setDaysRemaining(Integer.valueOf(daysRemainingTextField.getText()));
				pm.setPackedEnds(Integer.valueOf(packedEndsTextField.getText()));
				pm.setMonthlyLineLoad(Integer.valueOf(monthlyLineLoadTextField.getText()));
				pm.setRemaining(Integer.valueOf(remainingTextField.getText()));
				pm.setDailyAverage(Integer.valueOf(dailyAverageTextField.getText()));
				pm.setSpoiledPercentage(Double.valueOf(spoiledPercentageTextField.getText()));
				pm.setDaysGone(Integer.valueOf(daysGoneTextField.getText()));
				pm.setLineLoad(Integer.valueOf(lineLoadTextField.getText()));
				pm.setPackedVsLineLoad(Integer.valueOf(packedVsLineLoadTextField.getText()));

				
				try {
					productionMeetingDAO.ProductionMeetingUpdate(pm);

					dispose();
					createSummaryScreen();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub
			}
		});

		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					dispose();
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(ProductionMeetingView.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		});

		find = new JButton("Find Record");
		previous = new JButton("Previous Entry");		
		next = new JButton("Next Record");
		

		JPanel buttonPanel = new JPanel(new FlowLayout());

		buttonPanel.add(search);
		buttonPanel.add(summary);

		// buttonPanel.add(addNew);
		buttonPanel.add(update);
		buttonPanel.add(add);
		buttonPanel.setBackground(Color.GRAY);
		innerPanelLower.add(buttonPanel, BorderLayout.SOUTH);
		outerPanel.add(innerPanelLower, BorderLayout.SOUTH);
		// Buttons Top Panel

		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		// buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		add(buttonsPanel, BorderLayout.NORTH);

		// Adding
		if (view == -1) {

			find.setVisible(false);
			previous.setVisible(false);
			next.setVisible(false);
			addNew.setVisible(false);
			update.setVisible(false);
			summary.setVisible(false);

		} // Searching
		else if (view == 2) {

			currentId = 1;

			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);

		}

		add(outerPanel);
		setVisible(true);

	//	productionMeetingDAO.AnalyticsUpdate("ProductionMeeting");
	}

	public void createSummaryScreen() throws SQLException {

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				new ProductionMeetingView(1, -1);

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
//			//	exportToExcel();
//
//			}
//		});

		// Outer Frame
		frameSummary = new JFrame("Liner Data Report");
		frameSummary.setSize(1300, 900);
		frameSummary.setExtendedState(Frame.MAXIMIZED_BOTH);
		frameSummary.setLocationRelativeTo(null);

		// JPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel optionsPanel2 = new JPanel(new FlowLayout());

		optionsPanel2.add(addNew);
		optionsPanel2.add(refresh);
		// optionsPanel2.add(print);
		optionsPanel2.add(ExportToExcel);

		JPanel summaryPanel = productionMeetingDAO.ProductionMeetingSummaryTable(1);
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

	public void setProductionToID(int idIn) {

		try {

			int highestID = idIn;

			pm = productionMeetingDAO.ProductionMeetingReturnEntryByID(currentId);

			currentId = idIn;

			// Date 1

			safetyIssuesTextArea.setText(pm.getSafetyIssues());
			productionActionsTextArea.setText(pm.getProductionAction());
			hygieneManagementOfChangeTextArea.setText(pm.getHygieneManagementOfChange());
			engineeringActionsTextArea.setText(pm.getEngineeringActions());

			NonPetUnlinedSilverShellsTextField1.setText(pm.getNonPetUnlinedSilverShells()+"");
			
//			NonPetlinedSilverShellsTextField1.setText(pm.getNonPetlinedSilverShells());
//			NonPetlinedSilverShellsTotal.setText(pm.getNonPetlinedSilverShellsTotal());
//
//			NonPetUnlinedGoldRx219TextField1.setText(pm.getNonPetUnlinedGoldRx219());
//			NonPetlinedGoldRx219TextField1.setText(pm.getNonPetlinedGoldRx219());
//			NonPetlinedGoldRx219Total.setText(pm.getNonPetlinedGoldRx219Total());
//
//			Mod4FUnlinedSilverShellsTextField1.setText(pm.getMod4FUnlinedSilverShells());
//			Mod4FlinedSilverShellsTextField1.setText(pm.getMod4FlinedSilverShells());
//			Mod4FlinedSilverShellsTextFieldTotal.setText(pm.getMod4FlinedSilverShellsTotal());
//
//			NonPetUnlinedSilver215TextField1.setText(pm.getNonPetUnlinedSilver215());
//			NonPetlinedSilver215TextField1.setText(pm.getNonPetlinedSilver215());
//			NonPetlinedSilver215TextFieldTotal.setText(pm.getNonPetlinedSilver215Total());
//
//			A03HiFiShellsTextField1.setText(pm.getA03HiFiShells());
//			A04HiFiShellsTextField1.setText(pm.getA04HiFiShells());
//			A04HiFiShellsTextFieldTotal.setText(pm.getA04HiFiShellsTotal());
//
//			A03HiFiShellsRX219TextField1.setText(pm.getA03HiFiShellsRX219());
//			A04HiFiShellsRX219TextField1.setText(pm.getA04HiFiShellsRX219());
//			A04HiFiShellsRX219TextFieldTotal.setText(pm.getA04HiFiShellsRX219Total());
//
//			A13HiFiShellsTextField1.setText(pm.getA13HiFiShells());
//			A14HiFiShellsTextField1.setText(pm.getA14HiFiShells());
//			A14HiFiShellsTextFieldTotal.setText(pm.getA14HiFiShellsTotal());
//
//			A07HiFiShellsTextField1.setText(pm.getA07HiFiShells());
//			A08HiFiShellsTextField1.setText(pm.getA08HiFiShells());
//			A08HiFiShellsTextFieldTotal.setText(pm.getA08HiFiShellsTotal());
//
//			daysRemainingTextField.setText(pm.getDaysRemaining());
//			packedEndsTextField.setText(pm.getPackedEnds());
//			monthlyLineLoadTextField.setText(pm.getMonthlyLineLoad());
//			remainingTextField.setText(pm.getRemaining());
//			dailyAverageTextField.setText(pm.getDailyAverage());
//			spoiledPercentageTextField.setText(pm.getSpoiledPercentage());
//			daysGoneTextField.setText(pm.getDaysGone());
//			lineLoadTextField.setText(pm.getLineLoad());
//			packedVsLineLoadTextField.setText(pm.getPackedVsLineLoad());

			System.out.println("CurrentID " + currentId);

			refreshTotals();

			// Fill Boxes with results
			// model.setDate(year2, month2, day2);

			currentId = highestID;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refreshTotals() {

		int box1 = Integer.valueOf(NonPetUnlinedSilverShellsTextField1.getText());
		int box2 = Integer.valueOf(NonPetlinedSilverShellsTextField1.getText());
		int box3 = box1 + box2;

		NonPetUnlinedSilverShellsTextField2.setText(box1 * 198000 + "");
		NonPetlinedSilverShellsTextField2.setText(box2 * 198000 + "");
		NonPetlinedSilverShellsTotal.setText(box3 * 198000 + "");

		// -----------------------------------------------------------------------
		int box4 = Integer.valueOf(NonPetUnlinedGoldRx219TextField1.getText());
		int box5 = Integer.valueOf(NonPetlinedGoldRx219TextField1.getText());
		int box6 = box4 + box5;

		NonPetUnlinedGoldRx219TextField2.setText(box4 * 198000 + "");
		NonPetlinedGoldRx219TextField2.setText(box5 * 198000 + "");
		NonPetlinedGoldRx219Total.setText(box6 * 198000 + "");
		// -----------------------------------------------------------------------

		int box7 = Integer.valueOf(Mod4FUnlinedSilverShellsTextField1.getText());
		int box8 = Integer.valueOf(Mod4FlinedSilverShellsTextField1.getText());
		int box9 = box7 + box8;

		Mod4FUnlinedSilverShellsTextField2.setText(box7 * 198000 + "");
		Mod4FlinedSilverShellsTextField2.setText(box8 * 198000 + "");
		Mod4FlinedSilverShellsTextFieldTotal.setText(box9 * 198000 + "");
		// -----------------------------------------------------------------------

		int box10 = Integer.valueOf(NonPetUnlinedSilver215TextField1.getText());
		int box11 = Integer.valueOf(NonPetlinedSilver215TextField1.getText());
		int box12 = box10 + box11;

		NonPetUnlinedSilver215TextField2.setText(box10 * 198000 + "");
		NonPetlinedSilver215TextField2.setText(box11 * 198000 + "");
		NonPetlinedSilver215TextFieldTotal.setText(box12 * 198000 + "");
		// -----------------------------------------------------------------------

		int box13 = Integer.valueOf(A03HiFiShellsTextField1.getText());
		int box14 = Integer.valueOf(A04HiFiShellsTextField1.getText());
		int box15 = box13 + box14;

		A03HiFiShellsTextField2.setText(box13 * 198000 + "");
		A04HiFiShellsTextField2.setText(box14 * 198000 + "");
		A04HiFiShellsTextFieldTotal.setText(box15 * 198000 + "");
		// -----------------------------------------------------------------------

		int box16 = Integer.valueOf(A03HiFiShellsRX219TextField1.getText());
		int box17 = Integer.valueOf(A04HiFiShellsRX219TextField1.getText());
		int box18 = box16 + box17;

		A03HiFiShellsRX219TextField2.setText(box16 * 198000 + "");
		A04HiFiShellsRX219TextField2.setText(box17 * 198000 + "");
		A04HiFiShellsRX219TextFieldTotal.setText(box18 * 198000 + "");
		// -----------------------------------------------------------------------

		int box22 = Integer.valueOf(A13HiFiShellsTextField1.getText());
		int box23 = Integer.valueOf(A14HiFiShellsTextField1.getText());
		int box24 = box22 + box23;

		A13HiFiShellsTextField2.setText(box22 * 198000 + "");
		A14HiFiShellsTextField2.setText(box23 * 198000 + "");
		A14HiFiShellsTextFieldTotal.setText(box24 * 198000 + "");
		// -----------------------------------------------------------------------

		int box25 = Integer.valueOf(A07HiFiShellsTextField1.getText());
		int box26 = Integer.valueOf(A08HiFiShellsTextField1.getText());
		int box27 = box25 + box26;

		A07HiFiShellsTextField2.setText(box25 * 198000 + "");
		A08HiFiShellsTextField2.setText(box26 * 198000 + "");
		A08HiFiShellsTextFieldTotal.setText(box27 * 198000 + "");
		// -----------------------------------------------------------------------

		int monthlyLineLoad = Integer.valueOf(monthlyLineLoadTextField.getText());
		int packedEnds = Integer.valueOf(packedEndsTextField.getText());
		int daysRemaining = Integer.valueOf(daysRemainingTextField.getText());
		int dailyAverage = (monthlyLineLoad - packedEnds) / daysRemaining;
		dailyAverageTextField.setText(dailyAverage + "");
		// -----------------------------------------------------------------------

		int remaining = monthlyLineLoad - packedEnds;
		remainingTextField.setText(remaining + "");

		// -----------------------------------------------------------------------
		int packedVsLined = packedEnds - ((Integer.valueOf(monthlyLineLoadTextField.getText())
				* (Integer.valueOf(daysGoneTextField.getText()))));
		packedVsLineLoadTextField.setText(packedVsLined + "");
		// -----------------------------------------------------------------------
	}

}
