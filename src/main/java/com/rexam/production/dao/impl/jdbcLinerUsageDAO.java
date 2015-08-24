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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.rexam.production.dao.LinerUsageDAO;
import com.rexam.production.model.LinerUsageModel;
import com.rexam.production.view.LinerUsageView;

public class jdbcLinerUsageDAO implements LinerUsageDAO {

	private DataSource dataSource;
	private LinerUsageModel lm;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int LinerUsageGetHighestID() {
		int highestID = 0;

		String sql = "SELECT MAX(LinerUsage.ID) FROM LinerUsage;";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				highestID = rs.getInt(1);

			}
			rs.close();
			ps.close();
			return highestID;
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
	public LinerUsageModel LinerUsageReturnEntryByDate(Date dateIn) {

		lm = new LinerUsageModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM LinerUsage WHERE Linerusage.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setLineNumber(rs.getString(2));
				lm.setDate(rs.getDate(3));

				lm.setCrew(rs.getString(4));
				lm.setCrew(rs.getString(5));
				lm.setLeadHand(rs.getString(6));

				lm.setReason(rs.getString(7));
				lm.setQuantityUsed(rs.getInt(8));
				lm.setPartNumber(rs.getString(9));

				lm.setGun1(rs.getInt(10));
				lm.setGun2(rs.getInt(11));
				lm.setGun3(rs.getInt(12));
				lm.setGun4(rs.getInt(13));
				lm.setGun5(rs.getInt(14));
				lm.setGun6(rs.getInt(15));
				lm.setGun7(rs.getInt(16));
				lm.setGun8(rs.getInt(17));

				lm.setComment(rs.getString(18));

			}
			rs.close();
			ps.close();
			return lm;
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
	public LinerUsageModel LinerUsageReturnEntryByID(int id) {

		lm = new LinerUsageModel();

		String sql = "SELECT * FROM LinerUsage WHERE Linerusage.Date = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setLineNumber(rs.getString(2));
				lm.setDate(rs.getDate(3));

				lm.setCrew(rs.getString(4));
				lm.setCrew(rs.getString(5));
				lm.setLeadHand(rs.getString(6));

				lm.setReason(rs.getString(7));
				lm.setQuantityUsed(rs.getInt(8));
				lm.setPartNumber(rs.getString(9));

				lm.setGun1(rs.getInt(10));
				lm.setGun2(rs.getInt(11));
				lm.setGun3(rs.getInt(12));
				lm.setGun4(rs.getInt(13));
				lm.setGun5(rs.getInt(14));
				lm.setGun6(rs.getInt(15));
				lm.setGun7(rs.getInt(16));
				lm.setGun8(rs.getInt(17));

				lm.setComment(rs.getString(18));

			}
			rs.close();
			ps.close();
			return lm;
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
	public void LinerUsageInsert(LinerUsageModel lm) {
		
		String sql = ("insert into LinerUsage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setNull(1, java.sql.Types.INTEGER);
			lmInsert.setString(2, lm.getLineNumber());
			lmInsert.setDate(3, convertToSqlDate(lm.getDate()));
			lmInsert.setString(4, lm.getCrew());
			lmInsert.setString(5, lm.getLeadHand());
			lmInsert.setString(6, lm.getReason());

			lmInsert.setInt(7, lm.getQuantityUsed());
			lmInsert.setString(8, lm.getPartNumber());

			lmInsert.setInt(9, lm.getGun1());
			lmInsert.setInt(10, lm.getGun2());
			lmInsert.setInt(11, lm.getGun3());
			lmInsert.setInt(12, lm.getGun4());
			lmInsert.setInt(13, lm.getGun5());
			lmInsert.setInt(14, lm.getGun6());
			lmInsert.setInt(15, lm.getGun7());
			lmInsert.setInt(16, lm.getGun8());

			lmInsert.setString(17, lm.getComment());

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
	public void LinerUsageUpdate(LinerUsageModel lu) {

		Connection conn = null;

		String sql = "update LinerUsage set LinerNumber=? , Date=? , Crew=? , LeadHand=? , Reason=? , "
				+ "QuantityUsed=? , PartNumber=? , Gun1=? , Gun2=? , Gun3=? , Gun4=? , Gun5=? , "
				+ "Gun6=?  , Gun7=? , Gun8=? , Comment=? where Date=?";

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setString(1, lm.getLineNumber());

			lmInsert.setString(2, lm.getCrew());
			lmInsert.setString(3, lm.getLeadHand());
			lmInsert.setString(4, lm.getReason());
			lmInsert.setInt(5, lm.getQuantityUsed());
			lmInsert.setString(6, lm.getPartNumber());
			lmInsert.setInt(7, lm.getGun1());
			lmInsert.setInt(8, lm.getGun2());
			lmInsert.setInt(9, lm.getGun3());
			lmInsert.setInt(10, lm.getGun4());
			lmInsert.setInt(11, lm.getGun5());
			lmInsert.setInt(12, lm.getGun6());
			lmInsert.setInt(13, lm.getGun7());
			lmInsert.setInt(14, lm.getGun8());
			lmInsert.setString(15, lm.getComment());

			lmInsert.setDate(16, convertToSqlDate(lm.getDate()));

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

	@Override
	public JPanel LinerUsageSummaryTable(int reportType) {

		JPanel outerPanel = new JPanel(new BorderLayout());

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			String sql1 = "SELECT LinerNumber, Date, Crew, LeadHand, Reason, QuantityUsed, Gun1 as G1, Gun2 as G2, Gun3 as G3, Gun4 as G4, Gun5 as G5, Gun6 as G6, Gun7 as G7, Gun8 as G8, Comment, ID  FROM LinerUsage ORDER BY Date DESC";
			String sql2 = "SELECT LinerNumber, Date, Crew, LeadHand, Reason, QuantityUsed, Gun1, Gun2, Gun3, Gun4, Gun5, Gun6, Gun7, Gun8, Comment, ID FROM LinerUsage Group BY Module";
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

				int q1 = rs.getInt(6);
				String q2 = String.format("%,d", q1);

				Vector row = new Vector(len);

				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(q2);
				row.add(rs.getBoolean(7));
				row.add(rs.getBoolean(8));
				row.add(rs.getBoolean(9));
				row.add(rs.getBoolean(10));
				row.add(rs.getBoolean(11));
				row.add(rs.getBoolean(12));
				row.add(rs.getBoolean(13));
				row.add(rs.getBoolean(14));
				row.add(rs.getString(15));
				row.add(rs.getString(16));

				data.add(row);
			}

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

			final JTable table = new JTable(model);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			// table.getColumnModel().getColumn(0).setMinWidth(100);
			// table.getColumnModel().getColumn(1).setMinWidth(100);
			// table.getColumnModel().getColumn(2).setMinWidth(100);
			// table.getColumnModel().getColumn(3).setMinWidth(100);
			// table.getColumnModel().getColumn(4).setMinWidth(100);
			// table.getColumnModel().getColumn(5).setMinWidth(100);
			//
			table.getColumnModel().getColumn(6).setMaxWidth(35);
			table.getColumnModel().getColumn(7).setMaxWidth(35);
			table.getColumnModel().getColumn(8).setMaxWidth(35);
			table.getColumnModel().getColumn(9).setMaxWidth(35);
			table.getColumnModel().getColumn(10).setMaxWidth(35);
			table.getColumnModel().getColumn(11).setMaxWidth(35);
			table.getColumnModel().getColumn(12).setMaxWidth(35);
			table.getColumnModel().getColumn(13).setMaxWidth(35);

			// table.getColumnModel().getColumn(7).setMinWidth(80);
			table.getColumnModel().getColumn(14).setMinWidth(500);
			table.getColumnModel().getColumn(15).setMaxWidth(40);

			// Render Checkbox
			TableColumn tc1 = table.getColumnModel().getColumn(6);
			tc1.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc2 = table.getColumnModel().getColumn(7);
			tc2.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc3 = table.getColumnModel().getColumn(8);
			tc3.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc4 = table.getColumnModel().getColumn(9);
			tc4.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc5 = table.getColumnModel().getColumn(10);
			tc5.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc6 = table.getColumnModel().getColumn(11);
			tc6.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc7 = table.getColumnModel().getColumn(12);
			tc7.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			TableColumn tc8 = table.getColumnModel().getColumn(13);
			tc8.setCellRenderer(table.getDefaultRenderer(Boolean.class));
			//
			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {

					if (e.getClickCount() == 2) {
						JTable target = (JTable) e.getSource();

						int row = target.getSelectedRow() + 1;
						// int column = target.getSelectedColumn();

						// System.out.println("Clicked : " + row );
						System.out.println(table.getValueAt(table.getSelectedRow(), 15).toString());
						String idString = table.getValueAt(table.getSelectedRow(), 15).toString();
						int id = Integer.valueOf(idString);
						LinerUsageView linerUsage = new LinerUsageView(1, 2);
						linerUsage.setLinerUsageToId(id);

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

	@Override
	public JComboBox[] fillCombos(JComboBox[] la) {
		
		JComboBox jCombo1, jCombo2, jCombo3, jCombo4;
		String sql;

		///////////////////////////////////////////////////////////////////
		Connection conn = null;
		jCombo1 = new JComboBox();
		sql = "select Liner.LinerName from Liner  ORDER BY LinerName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("LinerName");
				jCombo1.addItem(name);

			}
			rs.close();
			ps.close();
			la[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo2 = new JComboBox();
		sql = "SELECT Crew.CrewName FROM Crew  ORDER BY CrewName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				jCombo2.addItem(crewName);

			}
			rs.close();
			ps.close();
			la[1] = jCombo2;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo3 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String employeeName = rs.getString("Name");
				jCombo3.addItem(employeeName);

			}
			rs.close();
			ps.close();
			la[2] = jCombo3;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		conn = null;
		jCombo4 = new JComboBox();
		sql = "SELECT LinerReason.LinerReasonName FROM LinerReason ORDER BY LinerReasonName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String linerReason = rs.getString("LinerReasonName");
				jCombo4.addItem(linerReason);

			}
			rs.close();
			ps.close();
			la[3] = jCombo4;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return la;
	}

}
