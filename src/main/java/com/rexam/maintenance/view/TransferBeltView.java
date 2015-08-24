package com.rexam.maintenance.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexam.maintenance.controller.MaintenanceDatabaseController;
import com.rexam.maintenance.dao.ShellPressMaintenanceDAO;
import com.rexam.maintenance.dao.TransferBeltDAO;
import com.rexam.maintenance.model.TransferBeltModel;
import com.toedter.calendar.JDateChooser;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class TransferBeltView extends JFrame{
	
	JPanel outerPanel, optionsPanel, bottomPanel, middlePanel;
    JButton W11Button, W12Button, W21Button, W22Button, W31Button, W32Button, W33Button, W41Button, W42Button, W43Button, update;
    JTextField MachineCodeJTexField1, MachineNameJTexField1, EndsTargetJTextField, ActualEndsJTextField, PlusMinusJTexField1;
    JTextField MachineCodeJTexFieldTotal, MachineNameJTexFieldTotal, TargetProductionJTexFieldTotal, ProductionJTexFieldTotal, PlusMinusJTexFieldTotal, MaintenanceDueDateJTexFieldTotal, DaysRemainingJTexFieldTotal;
    int currentId;
    private MaintenanceDatabaseController db;
    JDateChooser setUpDate, dateFitted, beltsRemoved1, beltsRemoved2, beltsRemoved3, beltsRemoved4, 
    beltsRemoved5, beltsRemoved6, beltsRemoved7, beltsRemoved8, beltsRemoved9, beltsRemoved10, beltsRemoved11,
    beltsRemoved12;
    TransferBeltModel tm;
    private TransferBeltDAO transferBeltDAO;
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new TransferBeltView(1);

	}
	
	public TransferBeltView(int spIn){
		
		setStyle();
		
		db = new MaintenanceDatabaseController();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		transferBeltDAO = (TransferBeltDAO) context.getBean("TransferBeltDAO");
		
		setTitle("Transfer Belt");
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
        
        // Add a view to analytics.
        // SQLiteConnection.AnalyticsUpdate("TransferBelt");
		
		
	}
	
	private void setToMachineCode(int machineCodeIn) {

        optionsPanel.repaint();

        tm = new TransferBeltModel();

        try {

            tm = transferBeltDAO.transferBeltReturnEntryByID(machineCodeIn);

            System.out.println("MachineCode : " + machineCodeIn);
            
            currentId = tm.getID();

            MachineCodeJTexField1.setText(tm.getMachineCode());
            dateFitted.setDate(tm.getDateFitted());
            EndsTargetJTextField.setText(tm.getEndsTargeted()+"");
            ActualEndsJTextField.setText(tm.getActualEnds()+"");
            PlusMinusJTexField1.setText(tm.getPlusOrMinus()+"");
            setUpDate.setDate(tm.getSetUpCheckDate());
            
            beltsRemoved1.setDate(tm.getBeltsRemoved1());
            beltsRemoved2.setDate(tm.getBeltsRemoved2());
            beltsRemoved3.setDate(tm.getBeltsRemoved3());
            beltsRemoved4.setDate(tm.getBeltsRemoved4());
            beltsRemoved5.setDate(tm.getBeltsRemoved5());
            beltsRemoved6.setDate(tm.getBeltsRemoved6());
            beltsRemoved7.setDate(tm.getBeltsRemoved7());
            beltsRemoved8.setDate(tm.getBeltsRemoved8());
            beltsRemoved9.setDate(tm.getBeltsRemoved9());
            beltsRemoved10.setDate(tm.getBeltsRemoved10());
            beltsRemoved11.setDate(tm.getBeltsRemoved11());
            beltsRemoved12.setDate(tm.getBeltsRemoved12());
            
            
            

        } catch (Exception ex) {
            Logger.getLogger(TransferBeltView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	
	private JPanel createOptionsPanel(int spIn) {

        optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        W11Button = new JButton("W11");
        W11Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(1);
                calculateandSetActualEndsForMachine("Stolle11");
            }
        });
        W12Button = new JButton("W12");
        W12Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(2);
                calculateandSetActualEndsForMachine("Stolle12");
            }
        });
        W21Button = new JButton("W21");
        W21Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(3);
                calculateandSetActualEndsForMachine("Stolle21");
            }
        });
        W22Button = new JButton("W22");
        W22Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(4);
                calculateandSetActualEndsForMachine("Stolle22");
            }
        });
        W31Button = new JButton("W31");
        W31Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(5);
                calculateandSetActualEndsForMachine("Stolle31");
            }
        });
        W32Button = new JButton("W32");
        W32Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(6);
                calculateandSetActualEndsForMachine("Stolle32");
            }
        });
        W33Button = new JButton("W33");
        W33Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(7);
                calculateandSetActualEndsForMachine("Stolle33");
            }
        });
        W41Button = new JButton("W41");
        W41Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(8);
                calculateandSetActualEndsForMachine("Stolle41");
            }
        });
        W42Button = new JButton("W42");
        W42Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(9);
                calculateandSetActualEndsForMachine("Stolle42");
            }
        });
        W43Button = new JButton("W43");
        W43Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setToMachineCode(10);
                calculateandSetActualEndsForMachine("Stolle43");
            }
        });

        optionsPanel.add(W11Button);
        optionsPanel.add(W12Button);
        optionsPanel.add(W21Button);
        optionsPanel.add(W22Button);
        optionsPanel.add(W31Button);
        optionsPanel.add(W32Button);
        optionsPanel.add(W33Button);
        optionsPanel.add(W41Button);
        optionsPanel.add(W42Button);
        optionsPanel.add(W43Button);

        return optionsPanel;

    }

	private JPanel createBottomPanel(int spIn) {

        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.print(MachineCodeJTexField1.getText() + "");

                if (MachineCodeJTexField1.getText().equals("W11")) {
                    updateEntry(1);
                    currentId = 1;

                }
                if (MachineCodeJTexField1.getText().equals("W12")) {
                    updateEntry(2);
                    currentId = 2;
                }
                if (MachineCodeJTexField1.getText().equals("W21")) {
                    updateEntry(3);
                    currentId = 3;
                }
                if (MachineCodeJTexField1.getText().equals("W22")) {
                    updateEntry(4);
                    currentId = 4;
                }
                if (MachineCodeJTexField1.getText().equals("W31")) {
                    updateEntry(5);
                    currentId = 5;
                }
                if (MachineCodeJTexField1.getText().equals("W32")) {
                    updateEntry(6);
                    currentId = 6;
                }
                if (MachineCodeJTexField1.getText().equals("W33")) {
                    updateEntry(7);
                    currentId = 7;
                }
                if (MachineCodeJTexField1.getText().equals("W41")) {
                    updateEntry(8);
                    currentId = 8;
                }
                if (MachineCodeJTexField1.getText().equals("W42")) {
                    updateEntry(9);
                    currentId = 9;
                }
                if (MachineCodeJTexField1.getText().equals("W43")) {
                    updateEntry(10);
                    currentId = 10;
                }

                setToMachineCode(currentId);

            }
        });

        bottomPanel.add(update);

        return bottomPanel;

    }

	private JPanel createMiddlePanel(int spIn) {
        
        setUpDate = new JDateChooser();
        dateFitted = new JDateChooser();
        beltsRemoved1 = new JDateChooser();
        beltsRemoved2 = new JDateChooser();
        beltsRemoved3 = new JDateChooser();
        beltsRemoved4 = new JDateChooser();
        beltsRemoved5 = new JDateChooser();
        beltsRemoved6 = new JDateChooser();
        beltsRemoved7 = new JDateChooser();
        beltsRemoved8 = new JDateChooser();
        beltsRemoved9 = new JDateChooser();
        beltsRemoved10 = new JDateChooser();
        beltsRemoved11 = new JDateChooser();
        beltsRemoved12 = new JDateChooser();
      

        middlePanel = new JPanel(new GridLayout(4, 7));
        middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // middlePanel.setBackground(Color.WHITE);

        // ROW 1
        middlePanel.add(new JLabel(" ", JLabel.CENTER));
        middlePanel.add(new JLabel("Press No.", JLabel.CENTER));
        middlePanel.add(new JLabel("Date Fitted", JLabel.CENTER));
        middlePanel.add(new JLabel("Ends Target", JLabel.CENTER));
        middlePanel.add(new JLabel("Actual Ends", JLabel.CENTER));
        middlePanel.add(new JLabel("+ OR -", JLabel.CENTER));
        middlePanel.add(new JLabel("Set-Up Check", JLabel.CENTER));

        // ROW 2 
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setBackground(new Color(255, 255, 123));
        EndsTargetJTextField = new JTextField();
        ActualEndsJTextField = new JTextField();
        PlusMinusJTexField1 = new JTextField();
        MachineCodeJTexField1 = new JTextField(" ");
        MachineCodeJTexField1.setEditable(false);
        EndsTargetJTextField = new JTextField();
        ActualEndsJTextField = new JTextField();
        PlusMinusJTexField1 = new JTextField();

        middlePanel.add(new JLabel("Belts Running", JLabel.CENTER));
        middlePanel.add(MachineCodeJTexField1);
        middlePanel.add(dateFitted);
        middlePanel.add(EndsTargetJTextField);
        middlePanel.add(ActualEndsJTextField);
        middlePanel.add(PlusMinusJTexField1);
        middlePanel.add(setUpDate);

        // ROW 3       
        middlePanel.add(new JLabel("Belts Removed", JLabel.CENTER));
        middlePanel.add(beltsRemoved1);
        middlePanel.add(beltsRemoved2);
        middlePanel.add(beltsRemoved3);
        middlePanel.add(beltsRemoved4);
        middlePanel.add(beltsRemoved5);
        middlePanel.add(beltsRemoved6);

        // ROW 3       
        middlePanel.add(new JLabel("Belts Removed", JLabel.CENTER));
        middlePanel.add(beltsRemoved7);
        middlePanel.add(beltsRemoved8);
        middlePanel.add(beltsRemoved9);
        middlePanel.add(beltsRemoved10);
        middlePanel.add(beltsRemoved11);
        middlePanel.add(beltsRemoved12);

        return middlePanel;
    }
	
    private void updateEntry(int machineCodeIn) {
        
        System.out.print("Machine Code : " + machineCodeIn);
        
        tm.setID(machineCodeIn);
        tm.setDateFitted(dateFitted.getDate());
        tm.setEndsTargeted(Integer.valueOf(EndsTargetJTextField.getText()));
        tm.setActualEnds(Integer.valueOf(ActualEndsJTextField.getText()));
        tm.setPlusOrMinus(Integer.valueOf(PlusMinusJTexField1.getText()));
        tm.setSetUpCheckDate(setUpDate.getDate());
        tm.setBeltsRemoved1(beltsRemoved1.getDate());
        tm.setBeltsRemoved2(beltsRemoved2.getDate());
        tm.setBeltsRemoved3(beltsRemoved3.getDate());
        tm.setBeltsRemoved4(beltsRemoved4.getDate());
        tm.setBeltsRemoved5(beltsRemoved5.getDate());
        tm.setBeltsRemoved6(beltsRemoved6.getDate());
        tm.setBeltsRemoved7(beltsRemoved7.getDate());
        tm.setBeltsRemoved8(beltsRemoved8.getDate());
        tm.setBeltsRemoved9(beltsRemoved9.getDate());
        tm.setBeltsRemoved10(beltsRemoved10.getDate());
        tm.setBeltsRemoved11(beltsRemoved11.getDate());
        tm.setBeltsRemoved12(beltsRemoved12.getDate());
        
        transferBeltDAO.transferBeltUpdate(tm);
    }
    
    private int[] calculateandSetActualEndsForMachine(String machineIn) {

        int[] sum = new int[3];

        // calculate the sum for the parameter machine
        Date date1 = dateFitted.getDate();

        String dateString1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);

        try {
            sum[0] = db.MaintenanceStolleProductionCalculateTotalsForMachine(dateString1, machineIn);
        } catch (SQLException ex) {
            Logger.getLogger(TransferBeltView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("AAA : " + sum[0]);

        ActualEndsJTextField.setText(sum[0] + "");

        return sum;

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
