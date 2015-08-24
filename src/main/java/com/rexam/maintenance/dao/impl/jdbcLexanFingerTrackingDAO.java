package com.rexam.maintenance.dao.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.maintenance.dao.BalancerMaintenanceDAO;
import com.rexam.maintenance.dao.LexanFingerTrackingDAO;
import com.rexam.maintenance.model.LexanFingerTrackingModel;
import com.rexam.maintenance.model.ShellPressProductionModel;

public class jdbcLexanFingerTrackingDAO implements LexanFingerTrackingDAO {
	
	private DataSource dataSource;
	LexanFingerTrackingModel lft;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int MaintenanceLexanFingerTrackingGetHighestID() {
		
		int highestID = 0;
		return highestID;
	}

	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingReturnEntryByID(int id) {
		
	
		lft = new LexanFingerTrackingModel();
		
		String sql = "SELECT * FROM MainLexanFingerTracking WHERE MainLexanFingerTracking.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				// 

			}
			rs.close();
			ps.close();
			return lft;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

//		Connection conn = Connect();
//		Statement s = conn.createStatement();
//
//		// QUERY /////////////////////////////////////////////////////////////
//		String selTable = "SELECT * FROM MainLexanFingerTracking WHERE MainLexanFingerTracking.ID = \"" + id + "\";";
//		System.out.println(selTable);
//		s.setQueryTimeout(10);
//		s.execute(selTable);
//
//		ResultSet rs = s.getResultSet();
//
//		while ((rs != null) && (rs.next())) {
//
//			result[0] = rs.getInt(1);
//
//			String df1 = (String) rs.getObject(2);
//			result[1] = df1;
//
//			result[2] = rs.getString(3);
//			result[3] = rs.getString(4);
//
//			rs.close();
//			s.close();
//			conn.close();
//
//		}
//
//		// //////////////////////////////////////////////////////////////////////
//		return lft;

	}

	public void MaintenanceLexanFingerTrackingInsert(LexanFingerTrackingModel lft) {
		
		lft = new LexanFingerTrackingModel();
		
		String sql = ("insert into MainLexanFingerTracking values(?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lftInsert = conn.prepareStatement(sql);
						
			// lft.get
						
			lftInsert.executeUpdate();
			lftInsert.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

//		Connection conn = Connect();
//		Statement s = conn.createStatement();
//		s.setQueryTimeout(10);
//
//		// TimeStamp in String Format
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		String dateF = format.format(date);
//
//		// QUERY
//		// //////////////////////////////////////////////////////////////////////
//		PreparedStatement BalancerMaintenanceInsert = conn
//				.prepareStatement("insert into MainLexanFingerTracking values(?,?,?,?,?)");
////
//		BalancerMaintenanceInsert.setInt(1, idIn);
//		BalancerMaintenanceInsert.setString(2, dateIn);
//		BalancerMaintenanceInsert.setString(3, balancerIn);
//		BalancerMaintenanceInsert.setString(4, subIn);
//		BalancerMaintenanceInsert.setString(5, dateF);
//
//		BalancerMaintenanceInsert.executeUpdate();
//
//		// /////////////////////////////////////////////////////////////////////////////
//		s.close();
//		conn.close();

	}

	public void MaintenanceLexanFingerTrackingUpdate(LexanFingerTrackingModel lft) {
		
//		Date dateIn = lft.getDate();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String df = (sdf.format(dateIn));

		String sqlQuery = "  UPDATE " + "MainLexanFingerTracking set " + "Date=?, " + "Balancer=?, \n" + "Sub=? "
				+ "WHERE ID=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lftInsert = conn.prepareStatement(sqlQuery);

//			lftInsert.setInt(1, pm.getSp01());
//			lftInsert.setInt(2, pm.getOptime2());
//			lftInsert.setInt(3, pm.getOptime3());
//			lftInsert.setInt(4, pm.getFmi41());
//			lftInsert.setInt(5, pm.getFmi42());
//			lftInsert.setInt(6, pm.getFormatec04());
			
			
			lftInsert.executeUpdate();
			lftInsert.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (conn != null) {

				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

//		Connection conn = Connect();
//		Statement s = conn.createStatement();
//		s.setQueryTimeout(10);
//
//		// QUERY
//		// //////////////////////////////////////////////////////////////////////
//		String sql = "  UPDATE " + "MainLexanFingerTracking set " + "Date=?, " + "Balancer=?, \n" + "Sub=? "
//				+ "WHERE ID=?";
//
//		PreparedStatement BalancerMaintenanceUpdate = conn.prepareStatement(sql);
//
//		BalancerMaintenanceUpdate.setString(1, dateIn);
//		BalancerMaintenanceUpdate.setString(2, balancerIn);
//
//		BalancerMaintenanceUpdate.setString(3, subIn);
//		BalancerMaintenanceUpdate.setInt(4, idIn);
//
//		BalancerMaintenanceUpdate.executeUpdate();
//
//		// /////////////////////////////////////////////////////////////////////////////
//		s.close();
//		conn.close();

	}

	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingGetNextEntryById(int idIn) {
		
		lft = new LexanFingerTrackingModel();

//		Object[] result = new Object[46];
//		int nextId = idIn + 1;
//
//		Connection conn = Connect();
//		Statement s = conn.createStatement();
//
//		// QUERY /////////////////////////////////////////////////////////////
//		String selTable = "SELECT * FROM MainLexanFingerTracking WHERE MainLexanFingerTracking.ID = \"" + nextId
//				+ "\";";
//		// System.out.println(selTable);
//		s.setQueryTimeout(10);
//		s.execute(selTable);
//
//		ResultSet rs = s.getResultSet();
//
//		while ((rs != null) && (rs.next())) {
//
//			result[0] = rs.getInt(1);
//			result[1] = rs.getString(2);
//			result[2] = rs.getString(3);
//
//			String df1 = (String) rs.getObject(4);
//			result[3] = df1;
//			String df2 = (String) rs.getObject(5);
//			result[4] = df2;
//			String df3 = (String) rs.getObject(6);
//			result[5] = df3;
//			String df4 = (String) rs.getObject(7);
//			result[6] = df4;
//			String df7 = (String) rs.getObject(8);
//			result[7] = df7;
//
//			result[8] = rs.getInt(9);
//			result[9] = rs.getInt(10);
//			result[10] = rs.getInt(11);
//			result[11] = rs.getInt(12);
//			result[12] = rs.getInt(13);
//
//			result[13] = rs.getInt(14);
//			result[14] = rs.getInt(15);
//			result[15] = rs.getInt(16);
//			result[16] = rs.getInt(17);
//			result[17] = rs.getInt(18);
//
//			result[18] = rs.getInt(19);
//			result[19] = rs.getInt(20);
//			result[20] = rs.getInt(21);
//			result[21] = rs.getInt(22);
//			result[22] = rs.getInt(23);
//
//			String df8 = (String) rs.getObject(24);
//			result[23] = df8;
//			String df9 = (String) rs.getObject(25);
//			result[24] = df9;
//			String df10 = (String) rs.getObject(26);
//			result[25] = df10;
//			String df11 = (String) rs.getObject(27);
//			result[26] = df11;
//			String df14 = (String) rs.getObject(28);
//			result[27] = df14;
//
//			result[28] = rs.getInt(29);
//			result[29] = rs.getInt(30);
//			result[30] = rs.getInt(31);
//			result[31] = rs.getInt(32);
//			result[32] = rs.getInt(33);
//
//			result[33] = rs.getInt(34);
//
//			rs.close();
//			s.close();
//			conn.close();
//
//		}
//
//		// //////////////////////////////////////////////////////////////////////
		
		return lft;
	}

	public LexanFingerTrackingModel MaintenanceLexanFingerTrackingGetPreviousEntryById(int idIn) {
		
		lft = new LexanFingerTrackingModel();

//		Object[] result = new Object[34];
//		int nextId = idIn - 1;
//
//		Connection conn = Connect();
//		Statement s = conn.createStatement();
//
//		// QUERY /////////////////////////////////////////////////////////////
//		String selTable = "SELECT * FROM MainLexanFingerTracking WHERE MainLexanFingerTracking.ID = \"" + nextId
//				+ "\";";
//		// System.out.println(selTable);
//		s.setQueryTimeout(10);
//		s.execute(selTable);
//
//		ResultSet rs = s.getResultSet();
//
//		while ((rs != null) && (rs.next())) {
//
//			result[0] = rs.getInt(1);
//			result[1] = rs.getString(2);
//			result[2] = rs.getString(3);
//
//			String df1 = (String) rs.getObject(4);
//			result[3] = df1;
//			String df2 = (String) rs.getObject(5);
//			result[4] = df2;
//			String df3 = (String) rs.getObject(6);
//			result[5] = df3;
//			String df4 = (String) rs.getObject(7);
//			result[6] = df4;
//			String df7 = (String) rs.getObject(8);
//			result[7] = df7;
//
//			result[8] = rs.getInt(9);
//			result[9] = rs.getInt(10);
//			result[10] = rs.getInt(11);
//			result[11] = rs.getInt(12);
//			result[12] = rs.getInt(13);
//
//			result[13] = rs.getInt(14);
//			result[14] = rs.getInt(15);
//			result[15] = rs.getInt(16);
//			result[16] = rs.getInt(17);
//			result[17] = rs.getInt(18);
//
//			result[18] = rs.getInt(19);
//			result[19] = rs.getInt(20);
//			result[20] = rs.getInt(21);
//			result[21] = rs.getInt(22);
//			result[22] = rs.getInt(23);
//
//			String df8 = (String) rs.getObject(24);
//			result[23] = df8;
//			String df9 = (String) rs.getObject(25);
//			result[24] = df9;
//			String df10 = (String) rs.getObject(26);
//			result[25] = df10;
//			String df11 = (String) rs.getObject(27);
//			result[26] = df11;
//			String df14 = (String) rs.getObject(28);
//			result[27] = df14;
//
//			result[28] = rs.getInt(29);
//			result[29] = rs.getInt(30);
//			result[30] = rs.getInt(31);
//			result[31] = rs.getInt(32);
//			result[32] = rs.getInt(33);
//
//			result[33] = rs.getInt(34);
//
//			rs.close();
//			s.close();
//			conn.close();
//
//		}
//
//		// //////////////////////////////////////////////////////////////////////
		
		
		return lft;
	}

	public JPanel MaintenanceLexanFingerTrackingSummaryTable(int in) {

		JPanel outerPanel = new JPanel(new BorderLayout());

//		Connection conn = Connect();
//		Statement stmt = conn.createStatement();
//		stmt.setQueryTimeout(10);
//
//		PreparedStatement psmt = conn
//				.prepareStatement("SELECT Date, Balancer, Sub, ID FROM MainLexanFingerTracking ORDER BY Balancer DESC");
//		psmt.setQueryTimeout(10);
//		ResultSet rs = psmt.executeQuery();
//		DefaultTableModel dm = new DefaultTableModel();
//
//		// get column names
//		int len = rs.getMetaData().getColumnCount();
//		System.out.println("LEN : " + len);
//		Vector cols = new Vector(len);
//		for (int i = 1; i <= len; i++) {// Note starting at 1
//
//			cols.add(rs.getMetaData().getColumnName(i));
//			System.out.println(rs.getMetaData().getColumnName(i));
//
//		}
//
//		// Add Data
//		Vector data = new Vector();
//
//		while (rs.next()) {
//
//			Vector row = new Vector(len);
//
//			row.add(rs.getString(1));
//			row.add(rs.getString(2));
//			row.add(rs.getString(3));
//			row.add(rs.getString(4));
//
//			data.add(row);
//		}
//
//		// Now create the table
//		DefaultTableModel model = new DefaultTableModel(data, cols);
//
//		JTable table = new JTable(model);
//		table.setAutoCreateRowSorter(true);
//
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
//		table.getColumnModel().getColumn(3).setMaxWidth(40);
//
//		// Render Checkbox
//		// TableColumn tc = table.getColumnModel().getColumn(9);
//		// tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent e) {
//
//				if (e.getClickCount() == 2) {
//					JTable target = (JTable) e.getSource();
//
//					int row = target.getSelectedRow() + 1;
//					// int column = target.getSelectedColumn();
//
//					// System.out.println("Clicked : " + row );
//					System.out.println(table.getValueAt(table.getSelectedRow(), 3).toString());
//
//					String idString = table.getValueAt(table.getSelectedRow(), 3).toString();
//					int id = Integer.valueOf(idString);
//					ShellPressProduction linersAndShells;
//					try {
//						LexanFingerTracking lexanFingerTracking = new LexanFingerTracking(1, -2);
//						lexanFingerTracking.setFingerLexanTrackingProductionToID(id);
//
//					} catch (SQLException ex) {
//						Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
//					}
//
//				}
//			}
//		});
//
//		JTableHeader header = table.getTableHeader();
//
//		outerPanel.add(header, BorderLayout.NORTH);
//		outerPanel.add(table, BorderLayout.CENTER);
//
//		psmt.close();
//		stmt.close();
//		conn.close();

		return outerPanel;

	}

	

}
