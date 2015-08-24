package com.rexam.par.dao.impl;

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

import com.rexam.maintenance.model.LineBalanceModel;
import com.rexam.par.dao.ParDAO;
import com.rexam.par.model.ParModel;
import com.rexam.production.model.LinerDataModel;

public class jdbcParDAO implements ParDAO {

	ParModel pm;

	private DataSource dataSource;
	LineBalanceModel lb;

	public int ParDatabaseGetHighestForm() {

		int highest = 0;

		pm = new ParModel();

		String sql = "SELECT MAX(Form) FROM Par ";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setForm(rs.getInt(1));
				highest = pm.getForm();

			}
			rs.close();
			ps.close();
			return highest;
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

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ParModel ParDatabaseReturnEntryByDate(Date dateIn) {
		pm = new ParModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM Par WHERE Par.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setDate(rs.getDate(1));
				pm.setForm(rs.getInt(2));
				pm.setCrew(rs.getString(3));
				pm.setCode(rs.getString(4));
				pm.setMachine(rs.getString(5));
				pm.setShiftManager(rs.getString(6));
				pm.setTechnician(rs.getString(7));
				pm.setOperator(rs.getString(8));
				pm.setEngineer(rs.getString(9));
				pm.setLinerHeadCheck1(rs.getBoolean(10));
				pm.setLinerHeadCheck2(rs.getBoolean(11));
				pm.setLinerHeadCheck3(rs.getBoolean(12));
				pm.setLinerHeadCheck4(rs.getBoolean(13));
				pm.setLinerHeadCheck5(rs.getBoolean(14));
				pm.setLinerHeadCheck6(rs.getBoolean(15));
				pm.setLinerHeadCheck7(rs.getBoolean(16));
				pm.setShellPressCheck1(rs.getBoolean(17));
				pm.setShellPressCheck2(rs.getBoolean(18));
				pm.setShellPressCheck3(rs.getBoolean(19));
				pm.setShellPressCheck4(rs.getBoolean(20));
				pm.setShellPressCheck5(rs.getBoolean(21));
				pm.setShellPressCheck6(rs.getBoolean(22));
				pm.setShellPressCheck7(rs.getBoolean(23));
				pm.setShellPressCheck8(rs.getBoolean(24));
				pm.setShellPressCheck9(rs.getBoolean(25));
				pm.setShellPressCheck10(rs.getBoolean(26));
				pm.setShellPressCheck11(rs.getBoolean(27));
				pm.setShellPressCheck12(rs.getBoolean(28));
				pm.setShellPressCheck13(rs.getBoolean(29));
				pm.setShellPressCheck14(rs.getBoolean(30));
				pm.setShellPressCheck15(rs.getBoolean(31));
				pm.setShellPressCheck16(rs.getBoolean(32));
				pm.setShellPressCheck17(rs.getBoolean(33));
				pm.setShellPressCheck18(rs.getBoolean(34));
				pm.setShellPressCheck19(rs.getBoolean(35));
				pm.setShellPressCheck20(rs.getBoolean(36));
				pm.setShellPressCheck21(rs.getBoolean(37));
				pm.setShellPressCheck22(rs.getBoolean(38));
				pm.setShellPressCheck23(rs.getBoolean(39));
				pm.setShellPressCheck24(rs.getBoolean(40));
				pm.setShellPressCheck25(rs.getBoolean(41));
				pm.setShellPressCheck26(rs.getBoolean(42));
				pm.setScore1A(rs.getDouble(43));
				pm.setScore1B(rs.getDouble(44));
				pm.setScore1C(rs.getDouble(45));
				pm.setScore1D(rs.getDouble(46));
				pm.setScore3A(rs.getDouble(47));
				pm.setScore3B(rs.getDouble(48));
				pm.setScore3C(rs.getDouble(49));
				pm.setScore3D(rs.getDouble(50));
				pm.setScore6A(rs.getDouble(51));
				pm.setScore6B(rs.getDouble(52));
				pm.setScore6C(rs.getDouble(53));
				pm.setScore6D(rs.getDouble(54));
				pm.setScore9A(rs.getDouble(55));
				pm.setScore9B(rs.getDouble(56));
				pm.setScore9C(rs.getDouble(57));
				pm.setScore9D(rs.getDouble(58));
				pm.setTimeStarted(rs.getString(59));
				pm.setTimeInToolRoom(rs.getString(60));
				pm.setTimeFinished(rs.getString(61));
				pm.setSigned(rs.getString(62));
				pm.setDateSigned(rs.getDate(63));
				pm.setStatus(rs.getBoolean(64));
				pm.setBefore(rs.getString(65));
				pm.setActionTaken(rs.getString(66));
				pm.setAfter(rs.getString(67));

			}
			rs.close();
			ps.close();
			return pm;
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
	public ParModel ParDatabaseReturnEntryByForm(int Form) {
		pm = new ParModel();

		String sql = "SELECT * FROM Par WHERE Par.Form = \"" + Form + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setDate(rs.getDate(1));
				pm.setForm(rs.getInt(2));
				pm.setCrew(rs.getString(3));
				pm.setCode(rs.getString(4));
				pm.setMachine(rs.getString(5));
				pm.setShiftManager(rs.getString(6));
				pm.setTechnician(rs.getString(7));
				pm.setOperator(rs.getString(8));
				pm.setEngineer(rs.getString(9));
				pm.setLinerHeadCheck1(rs.getBoolean(10));
				pm.setLinerHeadCheck2(rs.getBoolean(11));
				pm.setLinerHeadCheck3(rs.getBoolean(12));
				pm.setLinerHeadCheck4(rs.getBoolean(13));
				pm.setLinerHeadCheck5(rs.getBoolean(14));
				pm.setLinerHeadCheck6(rs.getBoolean(15));
				pm.setLinerHeadCheck7(rs.getBoolean(16));
				pm.setShellPressCheck1(rs.getBoolean(17));
				pm.setShellPressCheck2(rs.getBoolean(18));
				pm.setShellPressCheck3(rs.getBoolean(19));
				pm.setShellPressCheck4(rs.getBoolean(20));
				pm.setShellPressCheck5(rs.getBoolean(21));
				pm.setShellPressCheck6(rs.getBoolean(22));
				pm.setShellPressCheck7(rs.getBoolean(23));
				pm.setShellPressCheck8(rs.getBoolean(24));
				pm.setShellPressCheck9(rs.getBoolean(25));
				pm.setShellPressCheck10(rs.getBoolean(26));
				pm.setShellPressCheck11(rs.getBoolean(27));
				pm.setShellPressCheck12(rs.getBoolean(28));
				pm.setShellPressCheck13(rs.getBoolean(29));
				pm.setShellPressCheck14(rs.getBoolean(30));
				pm.setShellPressCheck15(rs.getBoolean(31));
				pm.setShellPressCheck16(rs.getBoolean(32));
				pm.setShellPressCheck17(rs.getBoolean(33));
				pm.setShellPressCheck18(rs.getBoolean(34));
				pm.setShellPressCheck19(rs.getBoolean(35));
				pm.setShellPressCheck20(rs.getBoolean(36));
				pm.setShellPressCheck21(rs.getBoolean(37));
				pm.setShellPressCheck22(rs.getBoolean(38));
				pm.setShellPressCheck23(rs.getBoolean(39));
				pm.setShellPressCheck24(rs.getBoolean(40));
				pm.setShellPressCheck25(rs.getBoolean(41));
				pm.setShellPressCheck26(rs.getBoolean(42));
				pm.setScore1A(rs.getDouble(43));
				pm.setScore1B(rs.getDouble(44));
				pm.setScore1C(rs.getDouble(45));
				pm.setScore1D(rs.getDouble(46));
				pm.setScore3A(rs.getDouble(47));
				pm.setScore3B(rs.getDouble(48));
				pm.setScore3C(rs.getDouble(49));
				pm.setScore3D(rs.getDouble(50));
				pm.setScore6A(rs.getDouble(51));
				pm.setScore6B(rs.getDouble(52));
				pm.setScore6C(rs.getDouble(53));
				pm.setScore6D(rs.getDouble(54));
				pm.setScore9A(rs.getDouble(55));
				pm.setScore9B(rs.getDouble(56));
				pm.setScore9C(rs.getDouble(57));
				pm.setScore9D(rs.getDouble(58));
				pm.setTimeStarted(rs.getString(59));
				pm.setTimeInToolRoom(rs.getString(60));
				pm.setTimeFinished(rs.getString(61));
				pm.setSigned(rs.getString(62));
				pm.setDateSigned(rs.getDate(63));
				pm.setStatus(rs.getBoolean(64));
				pm.setBefore(rs.getString(65));
				pm.setActionTaken(rs.getString(66));
				pm.setAfter(rs.getString(67));

			}
			rs.close();
			ps.close();
			return pm;
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
	public void ParDatabaseInsert(ParModel pm) {
		String sql = ("insert into Par values(" + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?," + "?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setDate(1, convertToSqlDate(pm.getDate()));
			lbInsert.setNull(2, java.sql.Types.INTEGER);

			lbInsert.setString(3, pm.getCrew());
			lbInsert.setString(4, pm.getCode());
			lbInsert.setString(5, pm.getMachine());
			lbInsert.setString(6, pm.getShiftManager());
			lbInsert.setString(7, pm.getTechnician());
			lbInsert.setString(8, pm.getOperator());
			lbInsert.setString(9, pm.getEngineer());

			lbInsert.setBoolean(10, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(11, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(12, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(13, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(14, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(15, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(16, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(17, pm.getLinerHeadCheck1());

			lbInsert.setBoolean(18, pm.getShellPressCheck1());
			lbInsert.setBoolean(19, pm.getShellPressCheck1());
			lbInsert.setBoolean(20, pm.getShellPressCheck1());
			lbInsert.setBoolean(21, pm.getShellPressCheck1());
			lbInsert.setBoolean(22, pm.getShellPressCheck1());

			lbInsert.setBoolean(23, pm.getShellPressCheck1());
			lbInsert.setBoolean(24, pm.getShellPressCheck1());
			lbInsert.setBoolean(25, pm.getShellPressCheck1());
			lbInsert.setBoolean(26, pm.getShellPressCheck1());
			lbInsert.setBoolean(27, pm.getShellPressCheck1());

			lbInsert.setBoolean(28, pm.getShellPressCheck1());
			lbInsert.setBoolean(29, pm.getShellPressCheck1());
			lbInsert.setBoolean(30, pm.getShellPressCheck1());
			lbInsert.setBoolean(31, pm.getShellPressCheck1());
			lbInsert.setBoolean(32, pm.getShellPressCheck1());

			lbInsert.setBoolean(33, pm.getShellPressCheck1());
			lbInsert.setBoolean(34, pm.getShellPressCheck1());
			lbInsert.setBoolean(35, pm.getShellPressCheck1());
			lbInsert.setBoolean(36, pm.getShellPressCheck1());
			lbInsert.setBoolean(37, pm.getShellPressCheck1());

			lbInsert.setBoolean(38, pm.getShellPressCheck1());
			lbInsert.setBoolean(39, pm.getShellPressCheck1());
			lbInsert.setBoolean(40, pm.getShellPressCheck1());
			lbInsert.setBoolean(41, pm.getShellPressCheck1());
			lbInsert.setBoolean(42, pm.getShellPressCheck1());
			lbInsert.setBoolean(43, pm.getShellPressCheck1());

			lbInsert.setBoolean(44, pm.getStolleCheck1());
			lbInsert.setBoolean(45, pm.getStolleCheck2());
			lbInsert.setBoolean(46, pm.getStolleCheck3());
			lbInsert.setBoolean(47, pm.getStolleCheck4());

			lbInsert.setDouble(48, pm.getScore1A());
			lbInsert.setDouble(49, pm.getScore1B());
			lbInsert.setDouble(50, pm.getScore1C());
			lbInsert.setDouble(51, pm.getScore1D());

			lbInsert.setDouble(52, pm.getScore3A());
			lbInsert.setDouble(53, pm.getScore3B());
			lbInsert.setDouble(54, pm.getScore3C());
			lbInsert.setDouble(55, pm.getScore3D());

			lbInsert.setDouble(56, pm.getScore6A());
			lbInsert.setDouble(57, pm.getScore6B());
			lbInsert.setDouble(58, pm.getScore6C());
			lbInsert.setDouble(59, pm.getScore6D());

			lbInsert.setDouble(60, pm.getScore9A());
			lbInsert.setDouble(61, pm.getScore9B());
			lbInsert.setDouble(62, pm.getScore9C());
			lbInsert.setDouble(63, pm.getScore9D());

			lbInsert.setString(64, pm.getTimeStarted());
			lbInsert.setString(65, pm.getTimeInToolRoom());
			lbInsert.setString(66, pm.getTimeFinished());
			lbInsert.setString(67, pm.getSigned());

			lbInsert.setDate(68, convertToSqlDate(pm.getDateSigned()));

			lbInsert.setBoolean(69, pm.getStatus());

			lbInsert.setString(70, pm.getBefore());
			lbInsert.setString(71, pm.getActionTaken());
			lbInsert.setString(72, pm.getAfter());

			lbInsert.executeUpdate();
			lbInsert.close();

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
	public void ParDatabaseUpdate(ParModel pm) {

		String sql = "update Par set Crew=? ,  Code=? , Machine=? , ShiftManager=? , "
				+ "Technician=? , Operator=? , Engineer=? , LinerHeadCheck1=? , LinerHeadCheck2=? , LinerHeadCheck3=? , "
				+ "LinerHeadCheck4=? , LinerHeadCheck5=? , LinerHeadCheck6=? , LinerHeadCheck7=? , LinerHeadCheck8=? ,"
				+ " ShellPressCheck1=? , ShellPressCheck2=? , ShellPressCheck3=? , ShellPressCheck4=?, ShellPressCheck5=?, "
				+ "ShellPressCheck6=?, ShellPressCheck7=?, ShellPressCheck8=?, ShellPressCheck9=?, ShellPressCheck10=?, "
				+ "ShellPressCheck11=?, ShellPressCheck12=?, ShellPressCheck13=?, ShellPressCheck14=?, ShellPressCheck15=?, "
				+ "ShellPressCheck16=?, ShellPressCheck17=?, ShellPressCheck18=?, ShellPressCheck19=?, ShellPressCheck20=?, "
				+ "ShellPressCheck21=?, ShellPressCheck22=?, ShellPressCheck23=? , ShellPressCheck24=? ,ShellPressCheck25=? ,ShellPressCheck26=? ,"
				+ " StolleCheck1=? , StolleCheck2=? , StolleCheck3=? , StolleCheck4=? , Score1A=? , Score1B=? ,"
				+ " Score1C=? , Score1D=? , Score3A=? , Score3B=? , Score3C=? , Score3D=? , Score6A=? , Score6B=? , Score6C=? ,"
				+ " Score6D=? , Score9A=? , Score9B=? , Score9C=? , Score9D=? , TimeStarted=? , TimeInToolRoom=? , TimeFinished=? ,"
				+ " Signed=? , DateSigned=? ,  Status=? , Before=?, ActionTaken=?, After=?  where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setString(1, pm.getCrew());
			lbInsert.setString(2, pm.getCode());
			lbInsert.setString(3, pm.getMachine());
			lbInsert.setString(4, pm.getShiftManager());
			lbInsert.setString(5, pm.getTechnician());
			lbInsert.setString(6, pm.getOperator());
			lbInsert.setString(7, pm.getEngineer());

			lbInsert.setBoolean(8, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(9, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(10, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(11, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(12, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(13, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(14, pm.getLinerHeadCheck1());
			lbInsert.setBoolean(15, pm.getLinerHeadCheck1());

			lbInsert.setBoolean(16, pm.getShellPressCheck1());
			lbInsert.setBoolean(17, pm.getShellPressCheck1());
			lbInsert.setBoolean(18, pm.getShellPressCheck1());
			lbInsert.setBoolean(19, pm.getShellPressCheck1());
			lbInsert.setBoolean(20, pm.getShellPressCheck1());

			lbInsert.setBoolean(21, pm.getShellPressCheck1());
			lbInsert.setBoolean(22, pm.getShellPressCheck1());
			lbInsert.setBoolean(23, pm.getShellPressCheck1());
			lbInsert.setBoolean(24, pm.getShellPressCheck1());
			lbInsert.setBoolean(25, pm.getShellPressCheck1());

			lbInsert.setBoolean(26, pm.getShellPressCheck1());
			lbInsert.setBoolean(27, pm.getShellPressCheck1());
			lbInsert.setBoolean(28, pm.getShellPressCheck1());
			lbInsert.setBoolean(29, pm.getShellPressCheck1());
			lbInsert.setBoolean(30, pm.getShellPressCheck1());

			lbInsert.setBoolean(31, pm.getShellPressCheck1());
			lbInsert.setBoolean(32, pm.getShellPressCheck1());
			lbInsert.setBoolean(33, pm.getShellPressCheck1());
			lbInsert.setBoolean(34, pm.getShellPressCheck1());
			lbInsert.setBoolean(35, pm.getShellPressCheck1());

			lbInsert.setBoolean(36, pm.getShellPressCheck1());
			lbInsert.setBoolean(37, pm.getShellPressCheck1());
			lbInsert.setBoolean(38, pm.getShellPressCheck1());
			lbInsert.setBoolean(39, pm.getShellPressCheck1());
			lbInsert.setBoolean(40, pm.getShellPressCheck1());
			lbInsert.setBoolean(41, pm.getShellPressCheck1());

			lbInsert.setBoolean(42, pm.getStolleCheck1());
			lbInsert.setBoolean(43, pm.getStolleCheck2());
			lbInsert.setBoolean(44, pm.getStolleCheck3());
			lbInsert.setBoolean(45, pm.getStolleCheck4());

			lbInsert.setDouble(46, pm.getScore1A());
			lbInsert.setDouble(47, pm.getScore1B());
			lbInsert.setDouble(48, pm.getScore1C());
			lbInsert.setDouble(49, pm.getScore1D());

			lbInsert.setDouble(50, pm.getScore3A());
			lbInsert.setDouble(51, pm.getScore3B());
			lbInsert.setDouble(52, pm.getScore3C());
			lbInsert.setDouble(53, pm.getScore3D());

			lbInsert.setDouble(54, pm.getScore6A());
			lbInsert.setDouble(55, pm.getScore6B());
			lbInsert.setDouble(56, pm.getScore6C());
			lbInsert.setDouble(57, pm.getScore6D());

			lbInsert.setDouble(58, pm.getScore9A());
			lbInsert.setDouble(59, pm.getScore9B());
			lbInsert.setDouble(60, pm.getScore9C());
			lbInsert.setDouble(61, pm.getScore9D());

			lbInsert.setString(62, pm.getTimeStarted());
			lbInsert.setString(63, pm.getTimeInToolRoom());
			lbInsert.setString(64, pm.getTimeFinished());
			lbInsert.setString(65, pm.getSigned());

			lbInsert.setDate(66, convertToSqlDate(pm.getDateSigned()));

			lbInsert.setBoolean(67, pm.getStatus());

			lbInsert.setString(68, pm.getBefore());
			lbInsert.setString(69, pm.getActionTaken());
			lbInsert.setString(70, pm.getAfter());
			lbInsert.setDate(71, convertToSqlDate(pm.getDate()));

			lbInsert.executeUpdate();
			lbInsert.close();

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
	public JPanel ParEntrySummaryTable() {

		Connection conn = null;
		String sql = "SELECT Form, DateString as Date, Crew, Code, Machine, ShiftManager, Technician, Operator, Engineer, Status FROM Par ORDER BY Form DESC";

		JPanel outerPanel = new JPanel(new BorderLayout());

		try {
			conn = dataSource.getConnection();

			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(10);

			PreparedStatement psmt = conn.prepareStatement(sql);
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

				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				row.add(rs.getString(7));
				row.add(rs.getString(8));
				row.add(rs.getString(9));
				row.add(rs.getBoolean(10));

				data.add(row);
			}

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

			JTable table = new JTable(model);
			table.setAutoCreateRowSorter(true);

			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.getColumnModel().getColumn(0).setMaxWidth(40);
			table.getColumnModel().getColumn(1).setMaxWidth(80);
			table.getColumnModel().getColumn(2).setMaxWidth(40);

			table.getColumnModel().getColumn(9).setMaxWidth(50);

			// Render Checkbox
			TableColumn tc = table.getColumnModel().getColumn(9);
			tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					// if (e.getClickCount() == 2) {
					// JTable target = (JTable) e.getSource();
					//
					// int row = target.getSelectedRow() + 1;
					// // int column = target.getSelectedColumn();
					//
					// // System.out.println("Clicked : " + row );
					// System.out.println(table.getValueAt(table.getSelectedRow(),
					// 0).toString());
					//
					// String idString =
					// table.getValueAt(table.getSelectedRow(), 0).toString();
					// int id = Integer.valueOf(idString);
					// ParEntry.frame.dispose();
					// ParEntry par = new ParEntry(2, "1", "August", "2014");
					// ParEntry.setParToForm(Integer.valueOf(idString));
					//
					// }

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
	public JTable PARReturnJTable(String type, String query) {

		Connection conn = null;

		JTable table = new JTable();
		table.setAutoCreateRowSorter(true);

		System.out.println("Query : " + query);
		System.out.println("Type : " + type);

		Statement stmt;
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();

			stmt.setQueryTimeout(10);

			PreparedStatement psmt = conn.prepareStatement(query);

			psmt.setQueryTimeout(10);
			ResultSet rs = psmt.executeQuery();
			DefaultTableModel dm = new DefaultTableModel();

			// get column names
			int len = rs.getMetaData().getColumnCount();
			System.out.println("LEN : " + len);
			Vector cols = new Vector(len);
			for (int i = 1; i <= len; i++) {// Note starting at 1

				cols.add(rs.getMetaData().getColumnName(i));
				// System.out.println(rs.getMetaData().getColumnName(i));

			}

			// Add Data
			Vector data = new Vector();

			if (type.equalsIgnoreCase("PAR")) {

				System.out.print(type);

				while (rs.next()) {
					Vector row = new Vector(len);

					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(3));
					row.add(rs.getString(4));
					row.add(rs.getString(5));
					row.add(rs.getString(6));
					row.add(rs.getString(7));
					row.add(rs.getString(8));
					row.add(rs.getString(9));
					row.add(rs.getBoolean(10));
					row.add(rs.getBoolean(11));
					row.add(rs.getBoolean(12));
					row.add(rs.getBoolean(13));
					row.add(rs.getBoolean(14));
					row.add(rs.getBoolean(15));
					row.add(rs.getBoolean(16));
					row.add(rs.getBoolean(17));
					row.add(rs.getBoolean(18));
					row.add(rs.getBoolean(19));
					row.add(rs.getBoolean(20));
					row.add(rs.getBoolean(21));
					row.add(rs.getBoolean(22));
					row.add(rs.getBoolean(23));
					row.add(rs.getBoolean(24));
					row.add(rs.getBoolean(25));
					row.add(rs.getBoolean(26));
					row.add(rs.getBoolean(27));
					row.add(rs.getBoolean(28));
					row.add(rs.getBoolean(29));
					row.add(rs.getBoolean(30));
					row.add(rs.getBoolean(31));
					row.add(rs.getBoolean(32));
					row.add(rs.getBoolean(33));
					row.add(rs.getBoolean(34));
					row.add(rs.getBoolean(35));
					row.add(rs.getBoolean(36));
					row.add(rs.getBoolean(37));
					row.add(rs.getBoolean(38));
					row.add(rs.getBoolean(39));
					row.add(rs.getBoolean(40));
					row.add(rs.getBoolean(41));
					row.add(rs.getBoolean(42));
					row.add(rs.getBoolean(43));
					row.add(rs.getBoolean(44));
					row.add(rs.getBoolean(45));
					row.add(rs.getBoolean(46));
					row.add(rs.getBoolean(47));
					row.add(rs.getString(48));
					row.add(rs.getString(49));
					row.add(rs.getString(50));
					row.add(rs.getString(51));
					row.add(rs.getString(52));
					row.add(rs.getString(53));
					row.add(rs.getString(54));
					row.add(rs.getString(55));
					row.add(rs.getString(56));
					row.add(rs.getString(57));
					row.add(rs.getString(58));
					row.add(rs.getString(59));
					row.add(rs.getString(60));
					row.add(rs.getString(61));
					row.add(rs.getString(62));
					row.add(rs.getString(63));
					row.add(rs.getString(64));
					row.add(rs.getString(65));
					row.add(rs.getString(66));
					row.add(rs.getString(67));
					row.add(rs.getString(68));
					row.add(rs.getString(69));
					row.add(rs.getString(70));
					row.add(rs.getString(71));
					row.add(rs.getString(72));
					row.add(rs.getString(73));

					data.add(row);
				}

			}

			table = new JTable(data, cols);
			table.setAutoCreateRowSorter(true);

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return table;
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

	public boolean LinerEntryExists(LinerDataModel lm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JComboBox[] fillCombos(JComboBox[] la) {
		JComboBox jCombo1, jCombo2, jCombo3, jCombo4, jCombo5, jCombo6, jCombo7, jCombo8, jCombo9;
		String sql;
		Connection conn = null;

		///////////////////////////////////////////////////////////////////
		conn = null;
		jCombo1 = new JComboBox();
		sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				System.out.println("CrewName : " + crewName);
				jCombo1.addItem(crewName);

			}
			rs.close();
			ps.close();
			la[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		jCombo2 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo2.addItem(name);

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
		sql = "SELECT ParCode.Title FROM ParCode ORDER BY Title Asc";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String code = rs.getString("Title");
				jCombo3.addItem(code);

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
		sql = "SELECT Machines.Title FROM Machines ORDER BY Title Asc";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String linerName = rs.getString("Title");
				jCombo4.addItem(linerName);

			}
			rs.close();
			ps.close();
			la[3] = jCombo4;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		jCombo5 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo5.addItem(name);

			}
			rs.close();
			ps.close();
			la[4] = jCombo5;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		jCombo6 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo6.addItem(name);

			}
			rs.close();
			ps.close();
			la[5] = jCombo6;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		jCombo7 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo7.addItem(name);

			}
			rs.close();
			ps.close();
			la[6] = jCombo7;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		jCombo8 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo8.addItem(name);

			}
			rs.close();
			ps.close();
			la[7] = jCombo8;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
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
