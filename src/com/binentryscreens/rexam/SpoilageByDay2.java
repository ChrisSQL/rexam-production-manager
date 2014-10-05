package com.binentryscreens.rexam;

import com.database.rexam.SQLiteConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.PlainDocument;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import java.awt.Font;

public class SpoilageByDay2 {

    static JButton save, update, monthly, back, exportToExcel;
    static JTextField dateTextField, optime1and2TextField, optime3TextField, m1BBalTextField, m3BBalTextField, m1LinerTextField, m2LinerTextField, stolle11TextField, stolle12TextField,
            m3ABalTextField, m1ABalTextField, ovecTesterTextField, qCLabTextField, bordenNo1TextField, m4QcAreaTextField, stolle21TextField, stolle22TextField, stolle33TextField,
            stolle31TextField, stolle32TextField, m2BBalTextField, m2ABalTextField, m3LinersTextField, m3QcAreaTextField, stolle42TextField, m4B2BalTextField, m4LinersTextField,
            qCMod1TextField, stolle41TextField, stolle43TextField, stolle44TextField, balancer4BTextField, balancer4ATextField, formatecTextField, blankTextField;
    static int currentID;
    static  JFrame frame, frameTrend;
    static JPanel outerPanel, innerPanel, leftPanel, rightPanel;
    static int currentId;

    static UtilDateModel model;
    static JDatePanelImpl datePanel;
    static JDatePickerImpl datePicker;
    static Date selectedDate;
    static int[] values;
    static int total;

    public static void main(String args[]) {

        new SpoilageByDay2();

    }

    public SpoilageByDay2() {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }

        model = new UtilDateModel();
        model.setSelected(true);
        datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                checkIfEntryExists();
            }
        });

        save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                insertEntry();
            }
        });
        update = new JButton("Update");
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                updateEntry();
            }
        });
        monthly = new JButton("Monthly");
        monthly.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                frame.dispose();
                
                monthly.setVisible(false);
                update.setVisible(false);
                exportToExcel.setVisible(true);
                back.setVisible(true);
                save.setVisible(false);
                

                Date date = new Date();
                String monthString = "January";
                String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                modifiedDate = sdf.format(date);

                String year = modifiedDate.substring(0, 4);
                int yearInt = Integer.parseInt(year);
                String month = modifiedDate.substring(5, 7);
                int monthInt = Integer.parseInt(month)-1;
                String day = modifiedDate.substring(8, 10);
                int dayInt = Integer.parseInt(day);

                if (monthInt == 0) {
                    monthString = "January";
                } else if (monthInt == 1) {
                    monthString = "February";
                } else if (monthInt == 2) {
                    monthString = "March";
                } else if (monthInt == 3) {
                    monthString = "April";
                } else if (monthInt == 4) {
                    monthString = "May";
                } else if (monthInt == 5) {
                    monthString = "June";
                } else if (monthInt == 6) {
                    monthString = "July";
                } else if (monthInt == 7) {
                    monthString = "August";
                } else if (monthInt == 8) {
                    monthString = "September";
                } else if (monthInt == 9) {
                    monthString = "October";
                } else if (monthInt == 10) {
                    monthString = "November";
                } else if (monthInt == 11) {
                    monthString = "December";
                }

                System.out.println("YearInt " + yearInt);

                try {
                    createTrendFrame(monthString, yearInt + "");
                } catch (SQLException ex) {
                    Logger.getLogger(SpoilageByDay2.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                frameTrend.dispose();
                new SpoilageByDay2();
                
            }
        });
        exportToExcel = new JButton("Export To Excel");
        dateTextField = new JTextField("0");
        PlainDocument doc1 = (PlainDocument) dateTextField.getDocument();
        doc1.setDocumentFilter(new MyIntFilter());
        optime1and2TextField = new JTextField("0");
        PlainDocument doc2 = (PlainDocument) optime1and2TextField.getDocument();
        doc2.setDocumentFilter(new MyIntFilter());
        optime3TextField = new JTextField("0");
        PlainDocument doc3 = (PlainDocument) optime3TextField.getDocument();
        doc3.setDocumentFilter(new MyIntFilter());
        m1BBalTextField = new JTextField("0");
        PlainDocument doc4 = (PlainDocument) m1BBalTextField.getDocument();
        doc4.setDocumentFilter(new MyIntFilter());
        m3BBalTextField = new JTextField("0");
        PlainDocument doc5 = (PlainDocument) m3BBalTextField.getDocument();
        doc5.setDocumentFilter(new MyIntFilter());
        m1LinerTextField = new JTextField("0");
        PlainDocument doc6 = (PlainDocument) m1LinerTextField.getDocument();
        doc6.setDocumentFilter(new MyIntFilter());
        m2LinerTextField = new JTextField("0");
        PlainDocument doc7 = (PlainDocument) m2LinerTextField.getDocument();
        doc7.setDocumentFilter(new MyIntFilter());
        stolle11TextField = new JTextField("0");
        PlainDocument doc8 = (PlainDocument) stolle11TextField.getDocument();
        doc8.setDocumentFilter(new MyIntFilter());
        stolle12TextField = new JTextField("0");
        PlainDocument doc9 = (PlainDocument) stolle12TextField.getDocument();
        doc9.setDocumentFilter(new MyIntFilter());
        m3ABalTextField = new JTextField("0");
        PlainDocument doc10 = (PlainDocument) m3ABalTextField.getDocument();
        doc10.setDocumentFilter(new MyIntFilter());
        m1ABalTextField = new JTextField("0");
        PlainDocument doc11 = (PlainDocument) m1ABalTextField.getDocument();
        doc11.setDocumentFilter(new MyIntFilter());
        ovecTesterTextField = new JTextField("0");
        PlainDocument doc12 = (PlainDocument) ovecTesterTextField.getDocument();
        doc12.setDocumentFilter(new MyIntFilter());
        qCLabTextField = new JTextField("0");
        PlainDocument doc13 = (PlainDocument) qCLabTextField.getDocument();
        doc13.setDocumentFilter(new MyIntFilter());
        bordenNo1TextField = new JTextField("0");
        PlainDocument doc14 = (PlainDocument) bordenNo1TextField.getDocument();
        doc14.setDocumentFilter(new MyIntFilter());
        m4QcAreaTextField = new JTextField("0");
        PlainDocument doc15 = (PlainDocument) m4QcAreaTextField.getDocument();
        doc15.setDocumentFilter(new MyIntFilter());
        stolle21TextField = new JTextField("0");
        PlainDocument doc16 = (PlainDocument) stolle21TextField.getDocument();
        doc16.setDocumentFilter(new MyIntFilter());
        stolle22TextField = new JTextField("0");
        PlainDocument doc17 = (PlainDocument) stolle22TextField.getDocument();
        doc17.setDocumentFilter(new MyIntFilter());
        stolle33TextField = new JTextField("0");
        PlainDocument doc18 = (PlainDocument) stolle33TextField.getDocument();
        doc18.setDocumentFilter(new MyIntFilter());
        stolle31TextField = new JTextField("0");
        PlainDocument doc19 = (PlainDocument) stolle31TextField.getDocument();
        doc19.setDocumentFilter(new MyIntFilter());
        stolle32TextField = new JTextField("0");
        PlainDocument doc20 = (PlainDocument) stolle32TextField.getDocument();
        doc20.setDocumentFilter(new MyIntFilter());
        m2BBalTextField = new JTextField("0");
        PlainDocument doc21 = (PlainDocument) m2BBalTextField.getDocument();
        doc21.setDocumentFilter(new MyIntFilter());
        m2ABalTextField = new JTextField("0");
        PlainDocument doc22 = (PlainDocument) m2ABalTextField.getDocument();
        doc22.setDocumentFilter(new MyIntFilter());
        m3LinersTextField = new JTextField("0");
        PlainDocument doc23 = (PlainDocument) m3LinersTextField.getDocument();
        doc23.setDocumentFilter(new MyIntFilter());
        m3QcAreaTextField = new JTextField("0");
        PlainDocument doc24 = (PlainDocument) m3QcAreaTextField.getDocument();
        doc24.setDocumentFilter(new MyIntFilter());
        stolle42TextField = new JTextField("0");
        PlainDocument doc25 = (PlainDocument) stolle42TextField.getDocument();
        doc25.setDocumentFilter(new MyIntFilter());
        m4B2BalTextField = new JTextField("0");
        PlainDocument doc26 = (PlainDocument) m4B2BalTextField.getDocument();
        doc26.setDocumentFilter(new MyIntFilter());
        m4LinersTextField = new JTextField("0");
        PlainDocument doc27 = (PlainDocument) m4LinersTextField.getDocument();
        doc27.setDocumentFilter(new MyIntFilter());
        qCMod1TextField = new JTextField("0");
        PlainDocument doc28 = (PlainDocument) qCMod1TextField.getDocument();
        doc28.setDocumentFilter(new MyIntFilter());
        stolle41TextField = new JTextField("0");
        PlainDocument doc29 = (PlainDocument) stolle41TextField.getDocument();
        doc29.setDocumentFilter(new MyIntFilter());
        stolle43TextField = new JTextField("0");
        PlainDocument doc30 = (PlainDocument) stolle43TextField.getDocument();
        doc30.setDocumentFilter(new MyIntFilter());
        stolle44TextField = new JTextField("0");
        PlainDocument doc31 = (PlainDocument) stolle44TextField.getDocument();
        doc31.setDocumentFilter(new MyIntFilter());
        balancer4BTextField = new JTextField("0");
        PlainDocument doc32 = (PlainDocument) balancer4BTextField.getDocument();
        doc32.setDocumentFilter(new MyIntFilter());
        balancer4ATextField = new JTextField("0");
        PlainDocument doc33 = (PlainDocument) balancer4ATextField.getDocument();
        doc33.setDocumentFilter(new MyIntFilter());
        formatecTextField = new JTextField("0");
        PlainDocument doc34 = (PlainDocument) formatecTextField.getDocument();
        doc34.setDocumentFilter(new MyIntFilter());
        blankTextField = new JTextField();
        PlainDocument doc35 = (PlainDocument) blankTextField.getDocument();
        doc35.setDocumentFilter(new MyIntFilter());

        SpoilageByDayGUI();

    }

    public static void SpoilageByDayGUI() {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }

        frame = new JFrame("Spoilage By Day");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        outerPanel = new JPanel(new BorderLayout());
        innerPanel = new JPanel(new GridLayout(1, 2));
        outerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        leftPanel = new JPanel(new GridLayout(16, 2));

        leftPanel.add(new JLabel("Date : ", SwingConstants.CENTER));
        leftPanel.add(datePicker);

        leftPanel.add(new JLabel("Optime 1-2", SwingConstants.CENTER));
        leftPanel.add(optime1and2TextField);

        leftPanel.add(new JLabel("Optime 3", SwingConstants.CENTER));
        leftPanel.add(optime3TextField);

        leftPanel.add(new JLabel("M1BBAL", SwingConstants.CENTER));
        leftPanel.add(m1BBalTextField);

        leftPanel.add(new JLabel("M3 B BAL", SwingConstants.CENTER));
        leftPanel.add(m3BBalTextField);

        leftPanel.add(new JLabel("M1 Liners", SwingConstants.CENTER));
        leftPanel.add(m1LinerTextField);

        leftPanel.add(new JLabel("M2 Liners", SwingConstants.CENTER));
        leftPanel.add(m2LinerTextField);

        leftPanel.add(new JLabel("Stolle 11", SwingConstants.CENTER));
        leftPanel.add(stolle11TextField);

        leftPanel.add(new JLabel("Stolle 22", SwingConstants.CENTER));
        leftPanel.add(stolle22TextField);

        leftPanel.add(new JLabel("M3 A BAL", SwingConstants.CENTER));
        leftPanel.add(m3ABalTextField);

        leftPanel.add(new JLabel("M1 A BAL", SwingConstants.CENTER));
        leftPanel.add(m1ABalTextField);

        leftPanel.add(new JLabel("Ovec Tester", SwingConstants.CENTER));
        leftPanel.add(ovecTesterTextField);

        leftPanel.add(new JLabel("QC Lab", SwingConstants.CENTER));
        leftPanel.add(qCLabTextField);

        leftPanel.add(new JLabel("Borden No1", SwingConstants.CENTER));
        leftPanel.add(bordenNo1TextField);

        leftPanel.add(new JLabel("M4 QC Area", SwingConstants.CENTER));
        leftPanel.add(m4QcAreaTextField);

        leftPanel.add(new JLabel("Stolle 21", SwingConstants.CENTER));
        leftPanel.add(stolle21TextField);

        rightPanel = new JPanel(new GridLayout(16, 2));

        rightPanel.add(new JLabel("Stolle 33", SwingConstants.CENTER));
        rightPanel.add(stolle33TextField);

        rightPanel.add(new JLabel("Stolle 12", SwingConstants.CENTER));
        rightPanel.add(stolle12TextField);

        rightPanel.add(new JLabel("Stolle 31", SwingConstants.CENTER));
        rightPanel.add(stolle31TextField);

        rightPanel.add(new JLabel("M2 B BAL", SwingConstants.CENTER));
        rightPanel.add(m2BBalTextField);

        rightPanel.add(new JLabel("M2 A BAL", SwingConstants.CENTER));
        rightPanel.add(m2ABalTextField);

        rightPanel.add(new JLabel("M3 Liners", SwingConstants.CENTER));
        rightPanel.add(m3LinersTextField);

        rightPanel.add(new JLabel("M3 QC Area", SwingConstants.CENTER));
        rightPanel.add(m3QcAreaTextField);

        rightPanel.add(new JLabel("Stolle 42", SwingConstants.CENTER));
        rightPanel.add(stolle42TextField);

        rightPanel.add(new JLabel("M4 Liners", SwingConstants.CENTER));
        rightPanel.add(m4LinersTextField);

        rightPanel.add(new JLabel("QC Mod1", SwingConstants.CENTER));
        rightPanel.add(qCMod1TextField);

        rightPanel.add(new JLabel("Stolle 41", SwingConstants.CENTER));
        rightPanel.add(stolle41TextField);

        rightPanel.add(new JLabel("Stolle 43", SwingConstants.CENTER));
        rightPanel.add(stolle43TextField);

        rightPanel.add(new JLabel("Stolle 44", SwingConstants.CENTER));
        rightPanel.add(stolle44TextField);

        rightPanel.add(new JLabel("Balancer 4B", SwingConstants.CENTER));
        rightPanel.add(balancer4BTextField);

        rightPanel.add(new JLabel("Balancer 4A", SwingConstants.CENTER));
        rightPanel.add(balancer4ATextField);

        rightPanel.add(new JLabel("Formatec", SwingConstants.CENTER));
        rightPanel.add(formatecTextField);

        innerPanel.add(leftPanel);
        innerPanel.add(rightPanel);
        outerPanel.add(innerPanel, BorderLayout.CENTER);
        outerPanel.add(optionsPanel(), BorderLayout.SOUTH);
        frame.add(outerPanel);
        frame.setVisible(true);

        checkIfEntryExists();

    }

    public static JPanel optionsPanel() {

        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.setBackground(Color.GRAY);

        optionsPanel.add(monthly);
        optionsPanel.add(save);
        optionsPanel.add(update);
        optionsPanel.add(back);
        optionsPanel.add(exportToExcel);

        return optionsPanel;
    }

    public static void checkIfEntryExists() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        selectedDate = (Date) datePicker.getModel().getValue();
        System.out.println("Date Selected : " + selectedDate);
        String dateInput = sdf.format(selectedDate);
        System.out.println("Date Input : " + dateInput);

        // TODO Auto-generated method stub
        System.out.println("Date Picked");
        try {
            // Check is Selected Date Exists
            if (SQLiteConnection.SpoilageByDayCheckIfEntryExists(dateInput)) {

                save.setVisible(false);
                update.setVisible(true);
                back.setVisible(false);
                exportToExcel.setVisible(false);

                // Fetch Entry and Fill                      
                Object[] total = SQLiteConnection.SpoilageByDayReturnEntryByDate(selectedDate);

                currentId = Integer.parseInt(total[0] + "");

                optime1and2TextField.setText(String.valueOf(total[2]));
                optime3TextField.setText(String.valueOf(total[3]));
                m1BBalTextField.setText(String.valueOf(total[4]));
                m3BBalTextField.setText(String.valueOf(total[5]));
                m1LinerTextField.setText(String.valueOf(total[6]));
                m2LinerTextField.setText(String.valueOf(total[7]));
                stolle11TextField.setText(String.valueOf(total[8]));
                stolle22TextField.setText(String.valueOf(total[9]));
                m3ABalTextField.setText(String.valueOf(total[10]));
                m1ABalTextField.setText(String.valueOf(total[11]));
                ovecTesterTextField.setText(String.valueOf(total[12]));
                qCLabTextField.setText(String.valueOf(total[13]));
                bordenNo1TextField.setText(String.valueOf(total[14]));
                m4QcAreaTextField.setText(String.valueOf(total[15]));
                stolle21TextField.setText(String.valueOf(total[16]));
                stolle33TextField.setText(String.valueOf(total[17]));
                stolle12TextField.setText(String.valueOf(total[18]));
                stolle31TextField.setText(String.valueOf(total[19]));
                stolle32TextField.setText(String.valueOf(total[20]));
                m2BBalTextField.setText(String.valueOf(total[21]));
                m2ABalTextField.setText(String.valueOf(total[22]));
                m3LinersTextField.setText(String.valueOf(total[23]));
                m3QcAreaTextField.setText(String.valueOf(total[24]));
                stolle42TextField.setText(String.valueOf(total[25]));
                m4B2BalTextField.setText(String.valueOf(total[26]));
                m4LinersTextField.setText(String.valueOf(total[27]));
                qCMod1TextField.setText(String.valueOf(total[28]));
                stolle41TextField.setText(String.valueOf(total[29]));
                stolle43TextField.setText(String.valueOf(total[30]));
                stolle44TextField.setText(String.valueOf(total[31]));
                balancer4BTextField.setText(String.valueOf(total[32]));
                balancer4ATextField.setText(String.valueOf(total[33]));
                formatecTextField.setText(String.valueOf(total[34]));

            } else {

                // Hide Add / Update Button
                update.setVisible(false);
                save.setVisible(true);
                back.setVisible(false);
                exportToExcel.setVisible(false);

                // Show Save Button
                // Set all To 0  
                optime1and2TextField.setText("0");
                optime3TextField.setText("0");
                m1BBalTextField.setText("0");
                m3BBalTextField.setText("0");
                m1LinerTextField.setText("0");
                m2LinerTextField.setText("0");
                stolle11TextField.setText("0");
                stolle12TextField.setText("0");
                m3ABalTextField.setText("0");
                m1ABalTextField.setText("0");
                ovecTesterTextField.setText("0");
                qCLabTextField.setText("0");
                bordenNo1TextField.setText("0");
                m4QcAreaTextField.setText("0");
                stolle21TextField.setText("0");
                stolle22TextField.setText("0");
                stolle33TextField.setText("0");
                stolle31TextField.setText("0");
                stolle32TextField.setText("0");
                m2BBalTextField.setText("0");
                m2ABalTextField.setText("0");
                m3LinersTextField.setText("0");
                m3QcAreaTextField.setText("0");
                stolle42TextField.setText("0");
                m4B2BalTextField.setText("0");
                m4LinersTextField.setText("0");
                qCMod1TextField.setText("0");
                stolle41TextField.setText("0");
                stolle43TextField.setText("0");
                stolle44TextField.setText("0");
                balancer4BTextField.setText("0");
                balancer4ATextField.setText("0");
                formatecTextField.setText("0");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SpoilageByDay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SpoilageByDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;

    }

    public static void insertEntry() {

        Object[] array = new Object[36];
        selectedDate = (Date) datePicker.getModel().getValue();
        try {
            array = SQLiteConnection.SpoilageByDayReturnEntryByDate(selectedDate);
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        // Add
        selectedDate = (Date) datePicker.getModel().getValue();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

        // Get int value of a JTextfield
        try {
            SQLiteConnection.SpoilageByDayInsert(
                    SQLiteConnection.SpoilageByDayGetHighestID() + 1,
                    date,
                    Integer.parseInt(optime1and2TextField.getText()),
                    Integer.parseInt(optime3TextField.getText()),
                    Integer.parseInt(m1BBalTextField.getText()),
                    Integer.parseInt(m3BBalTextField.getText()),
                    Integer.parseInt(m1LinerTextField.getText()),
                    Integer.parseInt(m2LinerTextField.getText()),
                    Integer.parseInt(stolle11TextField.getText()),
                    Integer.parseInt(stolle22TextField.getText()),
                    Integer.parseInt(m3ABalTextField.getText()),
                    Integer.parseInt(m1ABalTextField.getText()),
                    Integer.parseInt(ovecTesterTextField.getText()),
                    Integer.parseInt(qCLabTextField.getText()),
                    Integer.parseInt(bordenNo1TextField.getText()),
                    Integer.parseInt(m4QcAreaTextField.getText()),
                    Integer.parseInt(stolle21TextField.getText()),
                    Integer.parseInt(stolle33TextField.getText()),
                    Integer.parseInt(stolle12TextField.getText()),
                    Integer.parseInt(stolle31TextField.getText()),
                    Integer.parseInt(stolle32TextField.getText()),
                    Integer.parseInt(m2BBalTextField.getText()),
                    Integer.parseInt(m2ABalTextField.getText()),
                    Integer.parseInt(m3LinersTextField.getText()),
                    Integer.parseInt(m3QcAreaTextField.getText()),
                    Integer.parseInt(stolle42TextField.getText()),
                    Integer.parseInt(m4B2BalTextField.getText()),
                    Integer.parseInt(m4LinersTextField.getText()),
                    Integer.parseInt(qCMod1TextField.getText()),
                    Integer.parseInt(stolle41TextField.getText()),
                    Integer.parseInt(stolle43TextField.getText()),
                    Integer.parseInt(stolle44TextField.getText()),
                    Integer.parseInt(balancer4BTextField.getText()),
                    Integer.parseInt(balancer4ATextField.getText()),
                    Integer.parseInt(formatecTextField.getText())
            );

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // TODO Auto-generated method stub
        frame.dispose();

    }

    public static void updateEntry() {

        // Update
        selectedDate = (Date) datePicker.getModel().getValue();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);

        try {
            SQLiteConnection.SpoilageByDayUpdate(
                    // date,
                    Integer.parseInt(optime1and2TextField.getText()),
                    Integer.parseInt(optime3TextField.getText()),
                    Integer.parseInt(m1BBalTextField.getText()),
                    Integer.parseInt(m3BBalTextField.getText()),
                    Integer.parseInt(m1LinerTextField.getText()),
                    Integer.parseInt(m2LinerTextField.getText()),
                    Integer.parseInt(stolle11TextField.getText()),
                    Integer.parseInt(stolle22TextField.getText()),
                    Integer.parseInt(m3ABalTextField.getText()),
                    Integer.parseInt(m1ABalTextField.getText()),
                    Integer.parseInt(ovecTesterTextField.getText()),
                    Integer.parseInt(qCLabTextField.getText()),
                    Integer.parseInt(bordenNo1TextField.getText()),
                    Integer.parseInt(m4QcAreaTextField.getText()),
                    Integer.parseInt(stolle21TextField.getText()),
                    Integer.parseInt(stolle33TextField.getText()),
                    Integer.parseInt(stolle12TextField.getText()),
                    Integer.parseInt(stolle31TextField.getText()),
                    Integer.parseInt(stolle32TextField.getText()),
                    Integer.parseInt(m2BBalTextField.getText()),
                    Integer.parseInt(m2ABalTextField.getText()),
                    Integer.parseInt(m3LinersTextField.getText()),
                    Integer.parseInt(m3QcAreaTextField.getText()),
                    Integer.parseInt(stolle42TextField.getText()),
                    Integer.parseInt(m4B2BalTextField.getText()),
                    Integer.parseInt(m4LinersTextField.getText()),
                    Integer.parseInt(qCMod1TextField.getText()),
                    Integer.parseInt(stolle41TextField.getText()),
                    Integer.parseInt(stolle43TextField.getText()),
                    Integer.parseInt(stolle44TextField.getText()),
                    Integer.parseInt(balancer4BTextField.getText()),
                    Integer.parseInt(balancer4ATextField.getText()),
                    Integer.parseInt(formatecTextField.getText()),
                    currentId
            );

            JOptionPane.showMessageDialog(null, "Entry Updated", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public static void createTrendFrame(String month, String year) throws SQLException {

        frameTrend = new JFrame();
        String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] years = {"2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028"};
        JComboBox monthCombo = new JComboBox(monthArray);
        JComboBox yearCombo = new JComboBox(years);
        JButton goButton = new JButton("Go");
        goButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                frameTrend.dispose();

                // TODO Auto-generated method stub
                try {
                    createTrendFrame((String) monthCombo.getSelectedItem(), (String) yearCombo.getSelectedItem());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
               

            }
        });

        JPanel outerPanel = new JPanel(new BorderLayout());

        frameTrend.setSize(1366, 768);
        frameTrend.setExtendedState(Frame.MAXIMIZED_BOTH);
        frameTrend.setLocationRelativeTo(null);

        monthCombo.setSelectedItem(month);
        yearCombo.setSelectedItem(year);

        JPanel trendPanel = createTrendPanel(month, year);
        outerPanel.add(trendPanel);

        JPanel monthComboPanel = new JPanel(new FlowLayout());
        monthComboPanel.setBackground(Color.GRAY);
        monthComboPanel.add(monthCombo);
        monthComboPanel.add(yearCombo);
        monthComboPanel.add(goButton);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(Color.GRAY);
        bottomPanel.add(back);
        bottomPanel.add(exportToExcel);

        outerPanel.add(monthComboPanel, BorderLayout.NORTH);
        outerPanel.add(optionsPanel(), BorderLayout.SOUTH);

        frameTrend.add(outerPanel);
        frameTrend.setVisible(true);

    }

    public static JPanel createTrendPanel(String month, String year) throws SQLException {

        JPanel trendPanel = new JPanel(new BorderLayout());

        String[] headers = {" 1 ", " 2 "};
        String[][] data1 = new String[37][2];

        data1[2][0] = "  01";
        data1[3][0] = "  02";
        data1[4][0] = "  03";
        data1[5][0] = "  05";
        data1[6][0] = "  06";
        data1[7][0] = "  07";
        data1[8][0] = "  09";
        data1[9][0] = "  10";
        data1[10][0] = "  12";
        data1[11][0] = "  13";
        data1[12][0] = "  17";
        data1[13][0] = "  18";
        data1[14][0] = "  36";
        data1[15][0] = "  20";
        data1[16][0] = "  24";
        data1[17][0] = "  19";
        data1[18][0] = "  27";
        data1[19][0] = "  61";
        data1[20][0] = "  54";
        data1[21][0] = "  35";
        data1[22][0] = "  33";
        data1[23][0] = "  37";
        data1[24][0] = "  41";
        data1[25][0] = "  42";
        data1[26][0] = "  63";
        data1[27][0] = "  45";
        data1[28][0] = "  46";
        data1[29][0] = "  48";
        data1[30][0] = "  49";
        data1[31][0] = "  62";
        data1[32][0] = "  51";
        data1[33][0] = "  52";
        data1[34][0] = "  53";

        data1[2][1] = "Optime 1+2";
        data1[3][1] = "Optime 3";
        data1[4][1] = "M1 B Bal";
        data1[5][1] = "M3 B Bal";
        data1[6][1] = "M1 Liner";
        data1[7][1] = "M2 Liner";
        data1[8][1] = "Stolle 11";
        data1[9][1] = "Stolle 22";
        data1[10][1] = "M3 A Bal";
        data1[11][1] = "M1 A Bal";
        data1[12][1] = "Ovec Tester";
        data1[13][1] = "QC Lab";
        data1[14][1] = "Borden1";
        data1[15][1] = "M4 QC Area";
        data1[16][1] = "Stolle 21";
        data1[17][1] = "Stolle 33";
        data1[18][1] = "Stolle 12";
        data1[19][1] = "Stolle 31";
        data1[20][1] = "Stolle 32";
        data1[21][1] = "M2 B Bal";
        data1[22][1] = "M2 A Bal";
        data1[23][1] = "M3 Liners";
        data1[24][1] = "M3 QC Area";
        data1[25][1] = "Stolle 42";
        data1[26][1] = "M4 B2 Bal";
        data1[27][1] = "M4 Liners";
        data1[28][1] = "QC Mod 1";
        data1[29][1] = "Stolle 41";
        data1[30][1] = "Stolle 43";
        data1[31][1] = "Stolle 44";
        data1[32][1] = "Balance4B";
        data1[33][1] = "Balance4A";
        data1[34][1] = "Formatec";
        data1[35][1] = " ";
        data1[36][1] = "Totals";

        DefaultTableModel model1 = new DefaultTableModel(data1, headers);
        final JTable trendTableLeft = new JTable(model1);
        trendTableLeft.setPreferredSize(new Dimension(140, 820));
        trendTableLeft.getColumn(" 1 ").setPreferredWidth(30);

        trendTableLeft.setFont(new Font("Arial", Font.BOLD, 14));

        // MIDDLE PANEL
        JTable trendTableMiddle = new JTable(37, 33) {
            public Component prepareRenderer(TableCellRenderer renderer, int index_row, int index_col) {
                Component comp = super.prepareRenderer(renderer, index_row, index_col);
                //odd col index, selected or not selected
//                if (isCellSelected(index_row, index_col)) {
//                    comp.setFont(new Font("Serif", Font.BOLD, 12));
//                } 

                if (index_row == 0 || index_row == 36) {
                    comp.setFont(new Font("Serif", Font.BOLD, 14));

                }
                return comp;
            }
        };

        trendTableMiddle.setPreferredSize(
                new Dimension(1500, 600));

        // Create a [33] 3D Array of Results from function.
        int[][] array = new int[33][31];
        array = SQLiteConnection.SpoilageByDayTableFigures("01", month, year);

        // JTable Fill
        for (int i = 2;
                i < 35; i++) {
            for (int j = 1; j < 32; j++) {

                trendTableMiddle.getModel().setValueAt(array[i][j - 1], i, j);
            }

        }
        for (int i = 2;
                i < 35; i++) {

            // System.out.println("Number" + i + " : " + array[i][0]);
            trendTableMiddle.getModel().setValueAt(array[i][1], i, 2);

        }

        // Day Numbers Across Top
        for (int i = 0;
                i < 31; i++) {

            trendTableMiddle.getModel().setValueAt(i + 1, 0, i + 1);

        }

        // Total Numbers Across Bottom
        for (int i = 0;
                i < 31; i++) {

            int[] totals = new int[31];

            try {
                totals = SQLiteConnection.spoilageByDayGetDayTotals2(i + "", month, year);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // System.out.println("Totals : " + totals[i]);
            trendTableMiddle.getModel().setValueAt(totals[i], 36, i + 1);

        }

        // RIGHT PANEL
        JTable trendTableRight = new JTable(37, 3);
        trendTableRight.setFont(new Font("Arial", Font.BOLD, 14));

        trendTableRight.setPreferredSize(
                new Dimension(220, 820));
        trendTableRight.getColumnModel()
                .getColumn(1).setPreferredWidth(40);

        // Total of all KGs in Month // Sum of totals[0] + totals[1] etc...
        int totalKGs = 0;

        trendTableRight.getModel()
                .setValueAt("Ends", 0, 0);
        trendTableRight.getModel()
                .setValueAt("KGs", 0, 1);
        trendTableRight.getModel()
                .setValueAt("Total End", 0, 2);

        trendTableRight.getModel()
                .setValueAt(408, 2, 1);
        trendTableRight.getModel()
                .setValueAt(408, 3, 1);
        trendTableRight.getModel()
                .setValueAt(408, 4, 1);
        trendTableRight.getModel()
                .setValueAt(404, 5, 1);
        trendTableRight.getModel()
                .setValueAt(404, 6, 1);
        trendTableRight.getModel()
                .setValueAt(404, 7, 1);
        trendTableRight.getModel()
                .setValueAt(361, 8, 1);
        trendTableRight.getModel()
                .setValueAt(361, 9, 1);
        trendTableRight.getModel()
                .setValueAt(408, 10, 1);
        trendTableRight.getModel()
                .setValueAt(408, 11, 1);
        trendTableRight.getModel()
                .setValueAt(361, 12, 1);
        trendTableRight.getModel()
                .setValueAt(361, 13, 1);
        trendTableRight.getModel()
                .setValueAt(361, 14, 1);
        trendTableRight.getModel()
                .setValueAt(405, 15, 1);
        trendTableRight.getModel()
                .setValueAt(361, 16, 1);
        trendTableRight.getModel()
                .setValueAt(361, 17, 1);
        trendTableRight.getModel()
                .setValueAt(361, 18, 1);
        trendTableRight.getModel()
                .setValueAt(361, 19, 1);
        trendTableRight.getModel()
                .setValueAt(361, 20, 1);
        trendTableRight.getModel()
                .setValueAt(404, 21, 1);
        trendTableRight.getModel()
                .setValueAt(408, 22, 1);
        trendTableRight.getModel()
                .setValueAt(404, 23, 1);
        trendTableRight.getModel()
                .setValueAt(361, 24, 1);
        trendTableRight.getModel()
                .setValueAt(405, 25, 1);
        trendTableRight.getModel()
                .setValueAt(460, 26, 1);
        trendTableRight.getModel()
                .setValueAt(460, 27, 1);
        trendTableRight.getModel()
                .setValueAt(361, 28, 1);
        trendTableRight.getModel()
                .setValueAt(405, 29, 1);
        trendTableRight.getModel()
                .setValueAt(405, 30, 1);
        trendTableRight.getModel()
                .setValueAt(405, 31, 1);
        trendTableRight.getModel()
                .setValueAt(460, 32, 1);
        trendTableRight.getModel()
                .setValueAt(465, 33, 1);
        trendTableRight.getModel()
                .setValueAt(465, 34, 1);

        try {
            trendTableRight.getModel().setValueAt(SQLiteConnection.totalKGs(month, year), 36, 0);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // trendTableRight.getModel().setValueAt("Total Ends", 36, 1);
        trendTableRight.getModel()
                .setValueAt("Total EndsXkg", 36, 2);

        // Create Row of Total Sums for Right hand side Total
        for (int i = 0;
                i < 33; i++) {

            int[] values;
            try {
                values = SQLiteConnection.SpoilageByDayGetMonthsTotal(month, year);
                trendTableRight.getModel().setValueAt(values[i], 2 + i, 0);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // ////////////////////////////////////////////////////
        // Create Row of Total Sums * KG for Furthest Right hand side Total
        for (int i = 0;
                i < 33; i++) {

            try {
                values = SQLiteConnection.SpoilageByDayGetMonthsTotal(month, year);
                // trendTableRight.getModel().setValueAt(values[i], 2 + i, 2);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        int[] KGs = new int[33];

        for (int j = 0;
                j < 33; j++) {

            KGs[j] = (int) trendTableRight.getModel().getValueAt(j + 2, 1);

            // System.out.println(KGs[j]);
        }
        int total2 = 0;
        for (int i = 0;
                i < 33; i++) {

            total = Integer.valueOf(KGs[i]) * values[i];
            total2 = total2 + total;
            trendTableRight.getModel().setValueAt(total, 2 + i, 2);
        }

        trendTableRight.getModel()
                .setValueAt(total2, 36, 2);

        // / Bottom Total 8x8 JTable
        JPanel totalsPanel = new JPanel(new BorderLayout());
        JTable totalsTable = new JTable(8, 8) {
            public Component prepareRenderer(TableCellRenderer renderer, int index_row, int index_col) {
                Component comp = super.prepareRenderer(renderer, index_row, index_col);
                //odd col index, selected or not selected
//                if (isCellSelected(index_row, index_col)) {
//                    comp.setFont(new Font("Serif", Font.BOLD, 12));
//                } 

                if (index_col == 0 || index_row == 0) {
                    comp.setFont(new Font("Serif", Font.BOLD, 16));

                }
                if (index_row == 3) {
                    comp.setFont(new Font("Serif", Font.BOLD, 16));

                }
                if (index_row == 5) {
                    comp.setFont(new Font("Serif", Font.BOLD, 16));
                }

                return comp;
            }
        };;

        totalsTable.getModel()
                .setValueAt("Stolle & Sacoba", 0, 1);
        totalsTable.getModel()
                .setValueAt("Balancers", 0, 2);
        totalsTable.getModel()
                .setValueAt("Liners", 0, 3);
        totalsTable.getModel()
                .setValueAt("Shell Presses", 0, 4);
        totalsTable.getModel()
                .setValueAt("Border / Ovec", 0, 5);
        totalsTable.getModel()
                .setValueAt("QC Areas", 0, 6);
        totalsTable.getModel()
                .setValueAt("Total", 0, 7);

        totalsTable.getModel()
                .setValueAt("Month To Date", 1, 0);
        totalsTable.getModel()
                .setValueAt("Plant Production", 2, 0);
        totalsTable.getModel()
                .setValueAt("As % of Production", 3, 0);
        totalsTable.getModel()
                .setValueAt("Total Spoilage", 4, 0);
        totalsTable.getModel()
                .setValueAt("As a % of Spoilage", 5, 0);

        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 1);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 2);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 3);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 4);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 5);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 6);
        totalsTable.getModel()
                .setValueAt(SQLiteConnection.LinersAndShellsGetTotal("1", month, year), 2, 7);

        try { // Monthly Totals
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year), 1, 1);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year), 1, 2);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year), 1, 3);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year), 1, 4);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year), 1, 5);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year), 1, 6);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 1, 7);

            // Production Percentages
            DecimalFormat df = new DecimalFormat("###.##"); // <- // "###.###"
            // specifies
            // precision
            double y1 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x1 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year) + ""));
            double answer1 = (x1 / y1) * 100;
            System.out.println(" Y1 : " + y1 + " X1 " + x1);
            System.out.println("Answer1 : " + answer1);
            if (answer1 > 999999999) {
                answer1 = 0.00;
            }
            double answerRounded1 = Double.parseDouble(df.format(answer1).toString());
            totalsTable.getModel().setValueAt(answerRounded1 + " %", 3, 1);
            System.out.println("Stolle Percentage : " + answerRounded1);

            double y2 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x2 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year) + ""));
            double answer2 = (x2 / y2) * 100;
            if (answer2 > 999999999) {
                answer2 = 0.00;
            }
            System.out.println(x2 + " / " + y2);
            double answerRounded2 = Double.parseDouble(df.format(answer2).toString());
            totalsTable.getModel().setValueAt(answerRounded2 + " %", 3, 2);
            System.out.println("Stolle Percentage 2: " + answerRounded2);

            double y3 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x3 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year) + ""));
            double answer3 = (x3 / y3) * 100;
            if (answer3 > 999999999) {
                answer3 = 0.00;
            }
            System.out.println(x3 + " / " + y3);
            double answerRounded3 = Double.parseDouble(df.format(answer3).toString());
            totalsTable.getModel().setValueAt(answerRounded3 + " %", 3, 3);
            System.out.println("Stolle Percentage 3: " + answerRounded3);

            double y4 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x4 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year) + ""));
            double answer4 = (x4 / y4) * 100;
            if (answer4 > 999999999) {
                answer4 = 0.00;
            }
            System.out.println(x4 + " / " + y4);
            double answerRounded4 = Double.parseDouble(df.format(answer4).toString());
            totalsTable.getModel().setValueAt(answerRounded4 + " %", 3, 4);
            System.out.println("Stolle Percentage 4: " + answerRounded4);

            double y5 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x5 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year) + ""));
            double answer5 = (x5 / y5) * 100;
            if (answer5 > 999999999) {
                answer5 = 0.00;
            }
            System.out.println(x5 + " / " + y5);
            double answerRounded5 = Double.parseDouble(df.format(answer5).toString());
            totalsTable.getModel().setValueAt(answerRounded5 + " %", 3, 5);
            System.out.println("Stolle Percentage 5: " + answerRounded5);

            double y6 = (Double.parseDouble(SQLiteConnection.LinersAndShellsGetTotal("1", month, year) + ""));
            double x6 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year) + ""));
            double answer6 = (x6 / y6) * 100;
            if (answer6 > 999999999) {
                answer6 = 0.00;
            }
            System.out.println(x2 + " / " + y2);
            double answerRounded6 = Double.parseDouble(df.format(answer6).toString());
            totalsTable.getModel().setValueAt(answerRounded6 + " %", 3, 6);
            System.out.println("Stolle Percentage 6: " + answerRounded6);

            // Spoilage Percentages
            double y11 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x11 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalStolle(month, year) + ""));
            double answer11 = (x11 / y11) * 100;
            if (answer11 > 999999999) {
                answer11 = 0.00;
            }
            System.out.println(x11 + " / " + y11);
            double answerRounded11 = Double.parseDouble(df.format(answer11).toString());
            totalsTable.getModel().setValueAt(answerRounded11 + " %", 5, 1);
            System.out.println("Stolle Percentage : " + answerRounded11);

            double y12 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x12 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBalancers(month, year) + ""));
            double answer12 = (x12 / y12) * 100;
            if (answer12 > 999999999) {
                answer12 = 0.00;
            }
            System.out.println(x12 + " / " + y12);
            double answerRounded12 = Double.parseDouble(df.format(answer12).toString());
            totalsTable.getModel().setValueAt(answerRounded12 + " %", 5, 2);
            System.out.println("Stolle Percentage : " + answerRounded12);

            double y13 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x13 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalLiners(month, year) + ""));
            double answer13 = (x13 / y13) * 100;
            if (answer13 > 999999999) {
                answer13 = 0.00;
            }
            System.out.println(x13 + " / " + y13);
            double answerRounded13 = Double.parseDouble(df.format(answer13).toString());
            totalsTable.getModel().setValueAt(answerRounded13 + " %", 5, 3);
            System.out.println("Stolle Percentage : " + answerRounded13);

            double y14 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x14 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalShellPresses(month, year) + ""));
            double answer14 = (x14 / y14) * 100;
            System.out.println(x14 + " / " + y14);
            if (answer14 > 999999999) {
                answer14 = 0.00;
            }
            double answerRounded14 = Double.parseDouble(df.format(answer14).toString());
            totalsTable.getModel().setValueAt(answerRounded14 + " %", 5, 4);
            System.out.println("Stolle Percentage : " + answerRounded14);

            double y15 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x15 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalBordenOvec(month, year) + ""));
            double answer15 = (x15 / y15) * 100;
            if (answer15 > 999999999) {
                answer15 = 0.00;
            }
            System.out.println(x15 + " / " + y15);
            double answerRounded15 = Double.parseDouble(df.format(answer15).toString());
            totalsTable.getModel().setValueAt(answerRounded15 + " %", 5, 5);
            System.out.println("Stolle Percentage : " + answerRounded15);

            double y16 = (Double.parseDouble(SQLiteConnection.SpoilageByDayTotalFigure(month, year) + ""));
            double x16 = (Double.parseDouble(SQLiteConnection.SpoilageByDayGetMonthsTotalQCAreas(month, year) + ""));
            double answer16 = (x16 / y16) * 100;
            if (answer16 > 999999999) {
                answer16 = 0.00;
            }
            System.out.println(x16 + " / " + y16);
            double answerRounded16 = Double.parseDouble(df.format(answer16).toString());
            totalsTable.getModel().setValueAt(answerRounded16 + " %", 5, 6);
            System.out.println("Stolle Percentage : " + answerRounded16);

            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 1);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 2);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 3);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 4);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 5);
            totalsTable.getModel().setValueAt(SQLiteConnection.SpoilageByDayTotalFigure(month, year), 4, 6);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        totalsPanel.add(totalsTable);

        // /
        // ADD TO OUTER PANEL
        trendPanel.add(trendTableLeft, BorderLayout.WEST);

        trendPanel.add(trendTableMiddle, BorderLayout.CENTER);

        trendPanel.add(trendTableRight, BorderLayout.EAST);

        trendPanel.add(totalsPanel, BorderLayout.SOUTH);

        return trendPanel;
    }

    public static void setEntryToID() {

    }

}
