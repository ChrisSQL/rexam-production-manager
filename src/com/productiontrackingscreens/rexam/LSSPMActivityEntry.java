package com.productiontrackingscreens.rexam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.database.rexam.AccessConnect;

public class LSSPMActivityEntry extends JFrame {

	JButton add, find, next, previous, reset, addNew, search;
	JLabel date, comments, selectDate;
	JTextArea commentsTextArea;
	JDatePickerImpl datePicker;
	JDatePanelImpl datePanel;
	UtilDateModel model;
	JFrame frame;
	JPanel outerPanel, buttonsPanel;
	Object currentIdString;
	JTextField currentCounterField;
	int currentId, highestId, addNewCheck;

	public LSSPMActivityEntry(int idIn, String dateIn, String CommentIn, int addNewIn)
			throws Exception {
		
		addNewCheck = addNewIn;

		commentsTextArea = new JTextArea(7, 28);
		

		// Get Latest ID //////////////////////////////
		Date date = new Date();
		currentIdString = (AccessConnect.LSSPMEntryqueryHighest() + "");

		currentId = Integer.parseInt(currentIdString + "");

		currentCounterField = new JTextField("Entry : " + currentIdString + "");
		// /////////////////////////////////////////////

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

		frame = new JFrame();
		// frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("LSS/PM Entry");
		frame.setSize(350, 280);
		frame.setLocationRelativeTo(null);

		outerPanel = new JPanel(new BorderLayout());
		buttonsPanel = new JPanel();

		// Buttons Panel

		add = new JButton("Save Record");
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				if(idIn == -2){
					
					JOptionPane.showMessageDialog(null, "Saved.");	
					
				}
				
			}
		});
		reset = new JButton("Reset");
		find = new JButton("Find Record");
		find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(datePicker.getModel().getValue() == null){
					
					JOptionPane.showMessageDialog(null, "No Date Selected.");
					
				}

				else{
					
					
					
				
				Date selectedDate = (Date) datePicker.getModel().getValue();

				try {

					Object[] array = AccessConnect.LSSPMEntryqueryDate(selectedDate);

					if (array[1] == null) {

						JOptionPane
								.showMessageDialog(null, "No Results Found.");

					} else {

						String id = (String) array[0];

						currentId = Integer.parseInt(id);

						String year1 = (String) array[1];
						int year2 = Integer.parseInt(year1.substring(0, 4)); // Correct

						String month1 = (String) array[1];
						int month2 = Integer.parseInt(month1.substring(5, 7)) - 1; // Correct

						String day1 = (String) array[1];
						int day2 = Integer.parseInt(day1.substring(8, 10)); // Correct

						model.setDate(year2, month2, day2);
						model.setSelected(true);
						commentsTextArea.setText((String) array[2]);
						currentCounterField.setText("Entry : " + currentId);

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			}
		});

		next = new JButton("Next Record");
		next.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {

				// Get record with currentId and display record with +1 id.

				Object[] array = new Object[3];
				try {
					array = AccessConnect.LSSPMEntryqueryId(currentId + 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					highestId = AccessConnect.LSSPMEntryqueryHighest();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (currentId == highestId+1) {

					currentId = highestId+1;
					//commentsTextArea.setText("");

				}

				else if (array[1] == null) {

					// JOptionPane.showMessageDialog(null, "No Results Found.");

					currentId = currentId + 1;

					currentCounterField.setText("Entry : " + currentId);

				} else {

					String id = (String) array[0];

					currentId = Integer.parseInt(id);

					String year1 = (String) array[1];
					int year2 = Integer.parseInt(year1.substring(0, 4)); // Correct

					String month1 = (String) array[1];
					int month2 = Integer.parseInt(month1.substring(5, 7)) - 1; // Correct

					String day1 = (String) array[1];
					int day2 = Integer.parseInt(day1.substring(8, 10)); // Correct

					model.setDate(year2, month2, day2);
					model.setSelected(true);
					commentsTextArea.setText((String) array[2]);

					currentCounterField.setText("Entry : " + currentId);

				}

			}
		});
		previous = new JButton("Previous Record");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Get record with currentId and display record with -1 id.

				Object[] array = new Object[3];

				try {
					array = AccessConnect.LSSPMEntryqueryId(currentId - 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				if (array[0] == null) {

					currentId = currentId - 1;

				}
				
				if (currentId <= 0) {

					currentId = 1;

				}
				

				else if (array[1] == null) {

					// JOptionPane.showMessageDialog(null, "No Results Found.");

				} else {

					String id = (String) array[0];

					currentId = Integer.parseInt(id);

					String year1 = (String) array[1];
					int year2 = Integer.parseInt(year1.substring(0, 4)); // Correct

					String month1 = (String) array[1];
					int month2 = Integer.parseInt(month1.substring(5, 7)) - 1; // Correct

					String day1 = (String) array[1];
					int day2 = Integer.parseInt(day1.substring(8, 10)); // Correct

					model.setDate(year2, month2, day2);
					model.setSelected(true);
					commentsTextArea.setText((String) array[2]);

					currentCounterField.setText("Entry : " + currentId);

				}

			}
		});

		// buttonsPanel.add(reset);
		// buttonsPanel.add(add);
		buttonsPanel.add(find);
		buttonsPanel.add(previous);
		buttonsPanel.add(next);

		outerPanel.add(buttonsPanel, BorderLayout.NORTH);

		// Comments Panel

		if (idIn == -1) {
			
			// Get highest date ID
			int highestId = AccessConnect.LSSPMEntryqueryHighest();
			// Get entry for that ID
			Object[] array = AccessConnect.LSSPMEntryqueryId(highestId);

			Date date2 = new Date();
			model = new UtilDateModel();
			String modifiedDate = (String) array[1];
			String year = modifiedDate.substring(0, 4);
			int yearInt = Integer.parseInt(year);
			String month = modifiedDate.substring(5, 7);
			int monthInt = Integer.parseInt(month) - 1;
			String day = modifiedDate.substring(8, 10);
			int dayInt = Integer.parseInt(day);
			model.setDate(yearInt, monthInt, dayInt);
			currentId = Integer.parseInt(currentIdString + "");
			model.setSelected(true);
			

			// Set commentsTextArea			
			String commentString = (String) array[2];
			commentsTextArea.setText(commentString);
			
		}
		
		if (idIn == -2) {
			
			buttonsPanel.setVisible(false);
			
		}

		else {

//			model = new UtilDateModel();
//			String year = dateIn.substring(0, 4);
//			int yearInt = Integer.parseInt(year);
//			String month = dateIn.substring(5, 7);
//			int monthInt = Integer.parseInt(month) - 1;
//			String day = dateIn.substring(8, 10);
//			int dayInt = Integer.parseInt(day);
//			model.setDate(yearInt, monthInt, dayInt);
//			// model.setSelected(true);

		}
		// ////////////////////////////////////////////////////////

		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		
		// Split todays date up into three Ints and use on set date.

		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		

		JPanel commentsPanel = new JPanel(new FlowLayout());
		comments = new JLabel("Comments");

		currentId = AccessConnect.LSSPMEntryqueryHighest();
		commentsTextArea.setLineWrap(true);
		commentsTextArea.setWrapStyleWord(true);
		commentsPanel.add(new JLabel("Select Date : "));
		commentsPanel.add(datePicker);
		commentsPanel.add(commentsTextArea);
		outerPanel.add(commentsPanel, BorderLayout.CENTER);

		// Add Record Panel

		JPanel optionsPanel2 = new JPanel(new FlowLayout());
		addNew = new JButton("Add New");
		search = new JButton("Search");
		search.setVisible(false);
		if(addNewCheck == -1){
		addNew.setVisible(false);
		search.setVisible(true);
		}
		addNew.setBackground(Color.GREEN);
		addNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Create Date
				Date date2 = new Date();
				// Convert Format
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				// ToString
				String todaysDate = (sdf.format(date2));
				///			
				model = new UtilDateModel();				
				frame.dispose();
				try {
					new LSSPMActivityEntry(-2,todaysDate,"",1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		// optionsPanel2.add(reset);
		optionsPanel2.add(search);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frame.dispose();
				try {
					new LSSPMActivityEntry(-1,"","", 1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		optionsPanel2.add(add);
		optionsPanel2.add(addNew);
		optionsPanel2.setBackground(Color.GRAY);

		outerPanel.add(optionsPanel2, BorderLayout.SOUTH);

		outerPanel.repaint();
		frame.add(outerPanel);

		frame.setVisible(true);

	}

}
