package com.rexam.maintenance.dao.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.DataSource;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.maintenance.dao.BalancerProductionDAO;
import com.rexam.maintenance.model.ShellPressProductionModel;
import com.rexam.maintenance.view.BalancerProductionView;

public class jdbcBalancerProductionDAO implements BalancerProductionDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public JPanel MaintenanceBalancerProductionSummaryTable(int in) {
		JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = null;
        
        try {
			conn = dataSource.getConnection();
		
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement(""
                + " SELECT MainShellPressProduction.Date, "
                + "(MainShellPressProduction.SP02)/2 AS Balancer1A, "
                + "(MainShellPressProduction.SP02)/2 AS Balancer2A, "
                + "MainShellPressProduction.SP03 AS Balancer3A,"
                + " MainShellPressProduction.FMI41 AS Balancer4A,"
                + " MainStolleProduction.Stolle11 + MainStolleProduction.Stolle12 AS Balancer4A,"
                + " MainStolleProduction.Stolle21 + MainStolleProduction.Stolle22 AS Balancer4B,"
                + " MainStolleProduction.Stolle31 + MainStolleProduction.Stolle32 + MainStolleProduction.Stolle33 AS Balancer4C,"
                + " MainStolleProduction.Stolle41 + MainStolleProduction.Stolle42 + MainStolleProduction.Stolle43 + MainStolleProduction.Stolle44 AS Balancer4D"
                + " FROM MainShellPressProduction"
                + " INNER JOIN  MainStolleProduction"
                + " ON MainShellPressProduction.Date=MainStolleProduction.Date"
                + " ORDER BY MainShellPressProduction.Date DESC");
        psmt.setQueryTimeout(10);
        ResultSet rs = psmt.executeQuery();
        DefaultTableModel dm = new DefaultTableModel();

        // get column names
        int len = rs.getMetaData().getColumnCount();
        System.out.println("LEN : " + len);
        Vector cols = new Vector(len);
        for (int i = 1; i <= len; i++) {// Note starting at 1

            cols.add(rs.getMetaData().getColumnName(i));
            System.out.println(rs.getMetaData().getColumnName(i));

        }

        // Add Data
        Vector data = new Vector();

        while (rs.next()) {

            Vector row = new Vector(len);

            int q1 = rs.getInt(2);
            String q2 = String.format("%,d", q1);

            int q3 = rs.getInt(3);
            String q4 = String.format("%,d", q3);

            int q5 = rs.getInt(4);
            String q6 = String.format("%,d", q5);

            int q7 = rs.getInt(5);
            String q8 = String.format("%,d", q7);

            int q9 = rs.getInt(6);
            String q10 = String.format("%,d", q9);

            int q11 = rs.getInt(7);
            String q12 = String.format("%,d", q11);

            int q13 = rs.getInt(8);
            String q14 = String.format("%,d", q13);

            int q15 = rs.getInt(9);
            String q16 = String.format("%,d", q15);

            row.add(rs.getString(1));
            row.add(q2);
            row.add(q4);
            row.add(q6);
            row.add(q8);
            row.add(q10);
            row.add(q12);
            row.add(q14);
            row.add(q16);

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        final JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

//        table.getColumnModel().getColumn(10).setMaxWidth(40);
        // Render Checkbox
//        TableColumn tc = table.getColumnModel().getColumn(9);
//        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
					// int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(), 10).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 10).toString();
                    int id = Integer.valueOf(idString);
                    ShellPressProductionModel linersAndShells;
                    BalancerProductionView balancerProduction = new BalancerProductionView();
                //   BalancerProductionView.setBalancerProductionToID(id);

                }
            }
        });

        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();
        
        } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        return outerPanel;
	}
	
	
	

}
