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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.production.dao.StolleDataDAO;
import com.rexam.production.model.StolleDataModel;
import com.rexam.production.view.StolleDataView;

public class jdbcStolleDataDAO implements StolleDataDAO {

	StolleDataModel sm;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public StolleDataModel StolleReturnEntryByDate(Date dateIn) {

		sm = new StolleDataModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM StolleData WHERE StolleData.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				sm.setId(rs.getInt(1));
				sm.setDate(rs.getDate(2));
				sm.setShift(rs.getInt(3));
				sm.setCrew(rs.getString(4));
				sm.setPress(rs.getString(5));
				sm.setOperator(rs.getString(6));
				sm.setPacker(rs.getString(7));
				sm.setQcInspector(rs.getString(8));
				sm.setPressSpeed(rs.getInt(9));
				sm.setStolleProduction(rs.getInt(10));
				sm.setPackedEnds(rs.getInt(11));
				sm.setHfiCreated(rs.getInt(12));
				sm.setHfiRecovered(rs.getInt(13));
				sm.setHfiScrapped(rs.getInt(14));
				sm.setSacobaDowntime(rs.getInt(15));
				sm.setComment(rs.getString(rs.getString(16)));

			}
			rs.close();
			ps.close();
			return sm;
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
	public StolleDataModel StolleReturnEntryByID(int idIn) {
		sm = new StolleDataModel();

		String sql = "SELECT * FROM StolleData WHERE StolleData.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				sm.setId(rs.getInt(1));
				sm.setDate(rs.getDate(2));
				sm.setShift(rs.getInt(3));
				sm.setCrew(rs.getString(4));
				sm.setPress(rs.getString(5));
				sm.setOperator(rs.getString(6));
				sm.setPacker(rs.getString(7));
				sm.setQcInspector(rs.getString(8));
				sm.setPressSpeed(rs.getInt(9));
				sm.setStolleProduction(rs.getInt(10));
				sm.setPackedEnds(rs.getInt(11));
				sm.setHfiCreated(rs.getInt(12));
				sm.setHfiRecovered(rs.getInt(13));
				sm.setHfiScrapped(rs.getInt(14));
				sm.setSacobaDowntime(rs.getInt(15));
				sm.setComment(rs.getString(rs.getString(16)));

			}
			rs.close();
			ps.close();
			return sm;
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
	public void StolleInsert(StolleDataModel sm) {

		String sql = ("insert into StolleData values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setNull(1, java.sql.Types.INTEGER);
			lmInsert.setDate(2, convertToSqlDate(sm.getDate()));

			lmInsert.setInt(3, sm.getShift());
			lmInsert.setString(4, sm.getCrew());
			lmInsert.setString(5, sm.getPress());
			lmInsert.setString(6, sm.getOperator());
			lmInsert.setString(7, sm.getPacker());
			lmInsert.setString(8, sm.getQcInspector());
			lmInsert.setInt(9, sm.getPressSpeed());
			lmInsert.setInt(10, sm.getStolleProduction());
			lmInsert.setInt(11, sm.getPackedEnds());
			lmInsert.setInt(12, sm.getHfiCreated());
			lmInsert.setInt(13, sm.getHfiRecovered());
			lmInsert.setInt(14, sm.getHfiScrapped());
			lmInsert.setInt(15, sm.getSacobaDowntime());
			lmInsert.setString(16, sm.getComment());

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
	public void StolleUpdate(StolleDataModel sm) {

		String sql = "update Stolle set Shift=? , Crew=? , Press=? , Operator=? , Packer=? , "
				+ "QCInspector=? , pressSpeed=?, StolleProduction=? , PackedEnds=?, HFICreated=?, "
				+ "HFIRecovered=?, HFIScrapped=?, SacobaDowntime=?, Comment=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setInt(1, sm.getShift());
			lmInsert.setString(2, sm.getCrew());
			lmInsert.setString(3, sm.getPress());
			lmInsert.setString(4, sm.getOperator());
			lmInsert.setString(5, sm.getPacker());
			lmInsert.setString(6, sm.getQcInspector());
			lmInsert.setInt(7, sm.getPressSpeed());
			lmInsert.setInt(8, sm.getStolleProduction());
			lmInsert.setInt(9, sm.getPackedEnds());
			lmInsert.setInt(10, sm.getHfiCreated());
			lmInsert.setInt(11, sm.getHfiRecovered());
			lmInsert.setInt(12, sm.getHfiScrapped());
			lmInsert.setInt(13, sm.getSacobaDowntime());
			lmInsert.setString(14, sm.getComment());
			lmInsert.setDate(15, convertToSqlDate(sm.getDate()));

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
	public JPanel StolleSummaryTable(int reportType) {
		JPanel outerPanel = new JPanel(new BorderLayout());

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			PreparedStatement psmt = conn.prepareStatement("");
			ResultSet rs = null;

			if (reportType == 1) {
				psmt = conn.prepareStatement(
						"SELECT Press, Date, Shift, Crew, Operator, Packer, SacobaDowntime as DTime, QCInspector, StolleProduction as Production, PackedEnds, ID, Comment FROM StolleData ORDER BY Date DESC, Press");
				psmt.setQueryTimeout(10);
				rs = psmt.executeQuery();
			} else if (reportType == 2) {
				psmt = conn.prepareStatement("SELECT ID, Press, StolleProduction, Comment FROM StolleData Group BY Press");
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

				int q1 = rs.getInt(9);
				String q2 = String.format("%,d", q1);

				int q3 = rs.getInt(10);
				String q4 = String.format("%,d", q3);

				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				row.add(rs.getString(7));
				row.add(rs.getString(8)); //
				row.add(q2); //
				row.add(q4);
				row.add(rs.getString(11));
				row.add(rs.getString(12));

				data.add(row);
			}

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

			final JTable table = new JTable(model);
			table.setAutoCreateRowSorter(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			table.getColumnModel().getColumn(0).setMaxWidth(50);
			table.getColumnModel().getColumn(1).setMinWidth(80);
			table.getColumnModel().getColumn(2).setMaxWidth(40);
			table.getColumnModel().getColumn(3).setMaxWidth(40);
			table.getColumnModel().getColumn(4).setMinWidth(80);
			table.getColumnModel().getColumn(5).setMinWidth(80);
			table.getColumnModel().getColumn(6).setMinWidth(80);
			table.getColumnModel().getColumn(7).setMinWidth(80);
			table.getColumnModel().getColumn(8).setMinWidth(80);
			table.getColumnModel().getColumn(9).setMinWidth(80);
			table.getColumnModel().getColumn(10).setMaxWidth(30);
			table.getColumnModel().getColumn(11).setMinWidth(500);

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

						// System.out.println("Clicked : " + row );
						System.out
								.println("Selected NUmber " + table.getValueAt(table.getSelectedRow(), 10).toString());

						String idString = table.getValueAt(table.getSelectedRow(), 10).toString();
						int id = Integer.valueOf(idString);
						StolleDataView stolle = null;
						try {
							stolle = new StolleDataView(1, -2);
						} catch (SQLException ex) {
						}
						try {
							stolle.setStolleDataEntry(id);
						} catch (Exception ex) {
						}

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
	public JPanel StolleSummaryGroupTable(int reportType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel StolleSummaryCommentsTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel StolleEndsByMonthTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean StolleExists(StolleDataModel sm) {

		String sql = "SELECT * FROM StolleData WHERE StolleData.Date = \"" + sm.getDate() + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				return true;

			} else {

				return false;

			}

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
	public JComboBox[] fillCombos(JComboBox[] la) {

		JComboBox jCombo1, jCombo2, jCombo3, jCombo4, jCombo5, jCombo6;
		String sql;
		Connection conn = null;
		///////////////////////////////////////////////////////////////////
		
		conn = null;
		jCombo1 = new JComboBox();
		sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				 String shiftName = rs.getString("ShiftName");
	             jCombo1.addItem(shiftName);

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
		sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
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
		
		jCombo3 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo3.addItem(name);

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
		sql = "SELECT Press.PressName FROM Press ORDER BY PressName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String pressName = rs.getString("PressName");
				jCombo4.addItem(pressName);

			}
			rs.close();
			ps.close();
			la[3] = jCombo4;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo5 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String employeeName = rs.getString("Name");
				jCombo5.addItem(employeeName);

			}
			rs.close();
			ps.close();
			la[4] = jCombo5;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo6 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String employeeName = rs.getString("Name");
				jCombo6.addItem(employeeName);

			}
			rs.close();
			ps.close();
			la[5] = jCombo6;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		return la;
	}

}
