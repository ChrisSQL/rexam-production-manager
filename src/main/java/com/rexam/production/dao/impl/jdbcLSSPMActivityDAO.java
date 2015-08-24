package com.rexam.production.dao.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.sql.DataSource;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.production.dao.LSSPMActivityDAO;
import com.rexam.production.model.LSSPMActivityModel;

public class jdbcLSSPMActivityDAO implements LSSPMActivityDAO {

	LSSPMActivityModel ls;

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public LSSPMActivityModel LSSPMEntryqueryDate(Date dateIn) {

		ls = new LSSPMActivityModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM LSSPMActivity WHERE LSSPMActivity.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				ls.setId(rs.getInt(1));
				ls.setDate(rs.getDate(2));
				ls.setComment(rs.getString(3));

			}
			rs.close();
			ps.close();
			return ls;
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
	}

	@Override
	public LSSPMActivityModel LSSPMEntryqueryId(int idIn) {
		ls = new LSSPMActivityModel();

		String sql = "SELECT * FROM LSSPMActivity WHERE LSSPMActivity.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				ls.setId(rs.getInt(1));
				ls.setDate(rs.getDate(2));
				ls.setComment(rs.getString(3));

			}
			rs.close();
			ps.close();
			return ls;
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
	}

	@Override
	public void LSSPMInsert(LSSPMActivityModel ls) {
		String sql = ("insert into LSSPMActivity values(?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setNull(1, java.sql.Types.INTEGER);
			lmInsert.setDate(2, convertToSqlDate(ls.getDate()));
			lmInsert.setString(3, ls.getComment());

			lmInsert.executeUpdate();
			lmInsert.close();

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

	}

	@Override
	public void LSSPMUpdate(LSSPMActivityModel ls) {
		String sql = "update LSSPMActivity set Comment=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setString(1, ls.getComment());
			lmInsert.setDate(2, convertToSqlDate(ls.getDate()));

			lmInsert.executeUpdate();
			lmInsert.close();

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

	}

	@Override
	public JPanel LSSPMSummaryTable(int reportType) {

		String sql1 = "SELECT ID, Date, Comment FROM LSSPMActivity ORDER BY Date DESC";
		String sql2 = "SELECT ID, Date, Comment FROM LSSPMActivity ORDER BY Date DESC";
		Connection conn = null;

		JPanel outerPanel = new JPanel(new BorderLayout());

		try {
			conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();
			PreparedStatement psmt = conn.prepareStatement("");
			ResultSet rs = null;

			if (reportType == 1) {
				psmt = conn.prepareStatement(sql1);
				psmt.setQueryTimeout(10);
				rs = psmt.executeQuery();
			} else if (reportType == 2) {
				psmt = conn.prepareStatement(sql2);
				psmt.setQueryTimeout(10);
				rs = psmt.executeQuery();
			}

			// PreparedStatement psmt =
			// conn.prepareStatement("SELECT ID, OptimeNumber as Optime, Date,
			// Shift, Crew, PressSpeed, ShellType, Operator, Production, Comment
			// FROM OptimeEntry ORDER BY Date DESC");
			// psmt.setQueryTimeout(10);
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

				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));

				data.add(row);
			}

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

			JTable table = new JTable(model);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.getColumnModel().getColumn(0).setMaxWidth(35);
			table.getColumnModel().getColumn(1).setMaxWidth(80);
			table.getColumnModel().getColumn(2).setMinWidth(500);
			// table.getColumnModel().getColumn(1).setPreferredWidth(6);

			// Render Checkbox
			// TableColumn tc = table.getColumnModel().getColumn(9);
			// tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			//
			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {

					if (e.getClickCount() == 2) {
						JTable target = (JTable) e.getSource();

						int row = target.getSelectedRow() + 1;
						// int column = target.getSelectedColumn();
						//
						// // System.out.println("Clicked : " + row );
						// System.out.println(table.getValueAt(table.getSelectedRow(),
						// 0).toString());
						//
						// String idString =
						// table.getValueAt(table.getSelectedRow(),
						// 0).toString();
						// int id = Integer.valueOf(idString);
						// try {
						// LSSPMActivityView lssPM = new LSSPMActivityView(2,
						// "", "", -2);
						// lssPM.setLinerUsageToId(id);
						// } catch (Exception ex) {
						// Logger.getLogger(lSSPMActivityDao.class.getName()).log(Level.SEVERE,
						// null, ex);
						// }

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

	public java.sql.Date convertToSqlDate(java.util.Date utilDateIn) {

		java.sql.Date sql = new java.sql.Date(0);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String DateToStr = format.format(utilDateIn);
		System.out.println("DateToString :" + DateToStr);

		Date parsed;
		try {
			parsed = format.parse(DateToStr);
			sql = new java.sql.Date(parsed.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sql;

	}

}
