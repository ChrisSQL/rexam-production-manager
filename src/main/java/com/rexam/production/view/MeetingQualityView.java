package com.rexam.production.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.text.PlainDocument;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.production.dao.LSSPMActivityDAO;
import com.rexam.production.dao.MeetingQualityDAO;
import com.rexam.production.model.MeetingQualityModel;
import com.toedter.calendar.JDateChooser;

public class MeetingQualityView extends JFrame{

	private JButton add, find, next, previous, update, addNew, search, refresh, summary;
	private JLabel date, percentageOfChecksDoneDays, percentageOfChecksDoneNights, customerComplaints,
			qualityIssuesPreviousDays, qualityIssuesToday, qualityEquipment, shellsMTD, HFICreateMTD, HFIRecoverMTD,
			HFIScrapMTD, endsInHFI, auditsDue;
	private JTextField percentageOfChecksDoneDaysText, percentageOfChecksDoneNightsText, customerComplaintsText,
			shellsMTDText, HFICreateMTDText, HFIRecoverMTDText, HFIScrapMTDText, endsInHFIText;
	private JTextArea qualityIssuesPreviousDaysTextArea, qualityIssuesTodayTextArea, qualityEquipmentTextArea,
			auditsDueTextArea;
	private int view, currentId;
	private Date selectedDate;
	private JFrame frameSummary;
	private MeetingQualityDAO meetingQualityDAO;
	JDateChooser chooser1;
	private MeetingQualityModel mq;

	public static void main(String[] args) {

		new MeetingQualityView(1, -1);

	}

	public MeetingQualityView(int id, int view) {
		
		mq = new MeetingQualityModel();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		meetingQualityDAO = (MeetingQualityDAO) context.getBean("MeetingQualityDAO");

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

		setTitle("Meeting Quality Issues");
		// setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocationRelativeTo(null);
		
		chooser1 = new JDateChooser(new Date());

		// JLabels
		date = new JLabel("Date", SwingConstants.CENTER);
		percentageOfChecksDoneDays = new JLabel("Percentage Of Checks Done - Days", SwingConstants.CENTER);
		percentageOfChecksDoneNights = new JLabel("Percentage Of Checks Done - Nights", SwingConstants.CENTER);
		customerComplaints = new JLabel("Customer Complaints", SwingConstants.CENTER);
		qualityIssuesPreviousDays = new JLabel("Quality Issues Previous Days", SwingConstants.CENTER);
		qualityIssuesToday = new JLabel("Quality Issues Today", SwingConstants.CENTER);
		qualityEquipment = new JLabel("Quality Equipment", SwingConstants.CENTER);
		shellsMTD = new JLabel("Shells MTD", SwingConstants.CENTER);
		HFICreateMTD = new JLabel("HFI Create MTD", SwingConstants.CENTER);
		HFIRecoverMTD = new JLabel("HFI Recover MTD", SwingConstants.CENTER);
		HFIScrapMTD = new JLabel("HFI Scrap MTD", SwingConstants.CENTER);
		endsInHFI = new JLabel("Ends In HFI", SwingConstants.CENTER);
		auditsDue = new JLabel("Audits Due", SwingConstants.CENTER);
		
		// JTextFields

		percentageOfChecksDoneDaysText = new JTextField("0");
		// PlainDocument doc1 = (PlainDocument)
		// percentageOfChecksDoneDaysText.getDocument();
		// doc1.setDocumentFilter(new MyIntFilter());

		percentageOfChecksDoneNightsText = new JTextField("0");
		// PlainDocument doc2 = (PlainDocument)
		// percentageOfChecksDoneNightsText.getDocument();
		// doc2.setDocumentFilter(new MyIntFilter());

		customerComplaintsText = new JTextField("0");

		shellsMTDText = new JTextField("0");
		PlainDocument doc3 = (PlainDocument) shellsMTDText.getDocument();
		doc3.setDocumentFilter(new MyIntFilter());

		HFICreateMTDText = new JTextField("0");
		PlainDocument doc4 = (PlainDocument) HFICreateMTDText.getDocument();
		doc4.setDocumentFilter(new MyIntFilter());

		HFIRecoverMTDText = new JTextField("0");
		PlainDocument doc5 = (PlainDocument) HFIRecoverMTDText.getDocument();
		doc5.setDocumentFilter(new MyIntFilter());

		HFIScrapMTDText = new JTextField("0");
		PlainDocument doc6 = (PlainDocument) HFIScrapMTDText.getDocument();
		doc6.setDocumentFilter(new MyIntFilter());

		endsInHFIText = new JTextField("0");
		PlainDocument doc7 = (PlainDocument) endsInHFIText.getDocument();
		doc7.setDocumentFilter(new MyIntFilter());

		// ////////
		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				
				mq.setDate(chooser1.getDate());
				mq.setPercentageChecksDoneDays(Double.valueOf(percentageOfChecksDoneDaysText.getText()));
				mq.setPercentageChecksDoneNights(Double.valueOf(percentageOfChecksDoneNightsText.getText()));
				mq.setCustomerComplaints(customerComplaintsText.getText());
				mq.setQualityIssuesPreviousDays(qualityIssuesPreviousDaysTextArea.getText());
				mq.setQualityIssuesToday(qualityIssuesTodayTextArea.getText());
				mq.setShellsMTD(Integer.valueOf(shellsMTDText.getText()));
				mq.setHfiCreated(Integer.valueOf(HFICreateMTDText.getText()));
				mq.setHfiRecoveredMTD(Integer.valueOf(HFIRecoverMTDText.getText()));
				mq.setHfiScrappedMTD(Integer.valueOf(HFIScrapMTDText.getText()));
				mq.setEndsInHFI(Integer.valueOf(endsInHFIText.getText()));
				mq.setQualityEquipment(qualityEquipmentTextArea.getText());
				mq.setAuditsDue(auditsDueTextArea.getText());

				try {
					meetingQualityDAO.MeetingQualityInsert(mq);

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
					Logger.getLogger(MeetingQualityView.class.getName()).log(Level.SEVERE, null, ex);
				}
				dispose();
			}
		});
		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new MeetingQualityView(1, -1);
				dispose();
			}
		});

		update = new JButton("Update Record");
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mq.setDate(chooser1.getDate());
				mq.setPercentageChecksDoneDays(Double.valueOf(percentageOfChecksDoneDaysText.getText()));
				mq.setPercentageChecksDoneNights(Double.valueOf(percentageOfChecksDoneNightsText.getText()));
				mq.setCustomerComplaints(customerComplaintsText.getText());
				mq.setQualityIssuesPreviousDays(qualityIssuesPreviousDaysTextArea.getText());
				mq.setQualityIssuesToday(qualityIssuesTodayTextArea.getText());
				mq.setShellsMTD(Integer.valueOf(shellsMTDText.getText()));
				mq.setHfiCreated(Integer.valueOf(HFICreateMTDText.getText()));
				mq.setHfiRecoveredMTD(Integer.valueOf(HFIRecoverMTDText.getText()));
				mq.setHfiScrappedMTD(Integer.valueOf(HFIScrapMTDText.getText()));
				mq.setEndsInHFI(Integer.valueOf(endsInHFIText.getText()));
				mq.setQualityEquipment(qualityEquipmentTextArea.getText());
				mq.setAuditsDue(auditsDueTextArea.getText());

				try {
					meetingQualityDAO.MeetingQualityUpdate(mq);

					dispose();
					createSummaryScreen();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// TODO Auto-generated method stub
			}
		});

		find = new JButton("Find Record");
		
		previous = new JButton("Previous Entry");
		
		next = new JButton("Next Record");
	
		summary = new JButton("Summary");
		summary.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					dispose();
					createSummaryScreen();
				} catch (SQLException ex) {
					Logger.getLogger(MeetingQualityView.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		// JTextAreas
		qualityIssuesPreviousDaysTextArea = new JTextArea(7, 20);
		qualityIssuesPreviousDaysTextArea.setText("NA");
		qualityIssuesPreviousDaysTextArea.setLineWrap(true);
		qualityIssuesPreviousDaysTextArea.setWrapStyleWord(true);

		qualityIssuesTodayTextArea = new JTextArea(7, 20);
		qualityIssuesTodayTextArea.setText("NA");
		qualityIssuesTodayTextArea.setLineWrap(true);
		qualityIssuesTodayTextArea.setWrapStyleWord(true);

		qualityEquipmentTextArea = new JTextArea(7, 20);
		qualityEquipmentTextArea.setText("NA");
		qualityEquipmentTextArea.setLineWrap(true);
		qualityEquipmentTextArea.setWrapStyleWord(true);

		auditsDueTextArea = new JTextArea(7, 20);
		auditsDueTextArea.setText("NA");
		auditsDueTextArea.setLineWrap(true);
		auditsDueTextArea.setWrapStyleWord(true);
		// ////////

		// OuterPanel
		JPanel outerPanel = new JPanel(new BorderLayout());
		outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Buttons Top Panel
		// JPanel buttonsPanel = new JPanel(new GridLayout(1, 4));
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		// buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		add(buttonsPanel, BorderLayout.NORTH);

		// UpperPanel - JLabels and JTextfield
		JPanel upperPanelOuter = new JPanel(new GridLayout(1, 4));

		// 4 Upper Panels with FlowLayout
		JPanel left1 = new JPanel(new GridLayout(5, 1));
		left1.add(date);
		left1.add(percentageOfChecksDoneDays);
		left1.add(percentageOfChecksDoneNights);
		left1.add(customerComplaints);
		upperPanelOuter.add(left1);

		JPanel left2 = new JPanel(new GridLayout(5, 1));

		left2.add(chooser1);
		left2.add(percentageOfChecksDoneDaysText);
		left2.add(percentageOfChecksDoneNightsText);
		left2.add(customerComplaintsText);
		upperPanelOuter.add(left2);

		JPanel right1 = new JPanel(new GridLayout(5, 1));
		right1.add(shellsMTD);
		right1.add(HFICreateMTD);
		right1.add(HFIRecoverMTD);
		right1.add(HFIScrapMTD);
		right1.add(endsInHFI);
		upperPanelOuter.add(right1);

		JPanel right2 = new JPanel(new GridLayout(5, 1));
		right2.add(shellsMTDText);
		right2.add(HFICreateMTDText);
		right2.add(HFIRecoverMTDText);
		right2.add(HFIScrapMTDText);
		right2.add(endsInHFIText);
		upperPanelOuter.add(right2);

		// Add Panel to OuterPanel and OuterPanel to Frame
		outerPanel.add(upperPanelOuter, BorderLayout.NORTH);
		// frame.add(outerPanel);

		// LowerPanel - JLabels and JTextAreas
		JPanel lowerPanelOuter = new JPanel(new GridLayout(2, 4));

		// 4 Bottom Panels
		JPanel left1bottom = new JPanel();
		left1bottom.add(qualityIssuesPreviousDays);
		lowerPanelOuter.add(left1bottom);

		JPanel left2bottom = new JPanel(new GridLayout(1, 1));
		left2bottom.add(qualityIssuesPreviousDaysTextArea);
		lowerPanelOuter.add(left2bottom);

		JPanel right1bottom = new JPanel();
		right1bottom.add(qualityEquipment);
		lowerPanelOuter.add(right1bottom);

		JPanel right2bottom = new JPanel(new GridLayout(1, 1));
		right2bottom.add(qualityEquipmentTextArea);
		lowerPanelOuter.add(right2bottom);

		JPanel left1bottom2 = new JPanel();
		left1bottom2.add(qualityIssuesToday);
		lowerPanelOuter.add(left1bottom2);

		JPanel left2bottom2 = new JPanel(new GridLayout(1, 1));
		left2bottom2.add(qualityIssuesTodayTextArea);
		lowerPanelOuter.add(left2bottom2);

		JPanel right1bottom2 = new JPanel();
		right1bottom2.add(auditsDue);
		lowerPanelOuter.add(right1bottom2);

		JPanel right2bottom2 = new JPanel(new GridLayout(1, 1));
		right2bottom2.add(auditsDueTextArea);
		lowerPanelOuter.add(right2bottom2);

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

			// currentId = meetingQualityDAO.StolleGetHighestID()+1;
			buttonsPanel.setBackground(Color.GRAY);
			search.setVisible(false);
			add.setVisible(false);
			addNew.setVisible(false);

		}

		// Add Panel to OuterPanel and OuterPanel to Frame
		outerPanel.add(lowerPanelOuter, BorderLayout.CENTER);
		add(outerPanel);

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(Color.GRAY);

		buttonPanel.add(addNew);
		buttonPanel.add(summary);
		buttonPanel.add(search);
		buttonPanel.add(update);
		buttonPanel.add(add);
		outerPanel.add(buttonPanel, BorderLayout.SOUTH);

		setVisible(true);

	//	meetingQualityDAO.AnalyticsUpdate("MeetingQualityView");
	}

	public void createSummaryScreen() throws SQLException {

		addNew = new JButton("New Entry Mode");
		addNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameSummary.dispose();
				new MeetingQualityView(1, -1);

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
//				LinerDataEntryScreen.exportToExcel();
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

		JPanel summaryPanel = meetingQualityDAO.MeetingQualityIssuesSummaryTable(1);
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



}
