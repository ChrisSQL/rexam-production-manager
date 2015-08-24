package com.rexam.production.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.rexam.binentry.model.EndCountsModel;
import com.rexam.production.dao.OpTimeDAO;
import com.rexam.production.model.OpTimeModel;

public class jdbcOpTimeDAO implements OpTimeDAO {

	OpTimeModel om;
	
	private DataSource dataSource;
	

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int OPTimeGetHighestID() {

		int highestID = 0;

		String sql = "SELECT MAX(OPTimeEntry.[ID]) FROM OPTimeEntry;";

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
	public OpTimeModel OPTimeReturnEntryByDate(Date dateIn) {

		om = new OpTimeModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM OptimeEntry WHERE OptimeEntry.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				om.setId(rs.getInt(1));
				om.setDate(rs.getDate(2));

				om.setShift(rs.getInt(3));
				om.setCrew(rs.getString(4));
				om.setOperator(rs.getString(5));
				om.setOptimeNumber(rs.getInt(6));
				om.setPressSpeed(rs.getInt(7));
				om.setShellType(rs.getString(8));
				om.setProduction(rs.getInt(8));
				om.setComment(rs.getString(9));

			}
			rs.close();
			ps.close();
			return om;
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
	public OpTimeModel OPTimeReturnEntryById(int idIn) {
		om = new OpTimeModel();

		String sql = "SELECT * FROM OptimeEntry WHERE OptimeEntry.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				om.setId(rs.getInt(1));
				om.setDate(rs.getDate(2));

				om.setShift(rs.getInt(3));
				om.setCrew(rs.getString(4));
				om.setOperator(rs.getString(5));
				om.setOptimeNumber(rs.getInt(6));
				om.setPressSpeed(rs.getInt(7));
				om.setShellType(rs.getString(8));
				om.setProduction(rs.getInt(8));
				om.setComment(rs.getString(9));

			}
			rs.close();
			ps.close();
			return om;
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
	public void OPTimeInsert(OpTimeModel ot) {

		String sql = ("insert into OptimeEntry values(?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setNull(1, java.sql.Types.INTEGER);
			lbInsert.setDate(2, convertToSqlDate(ot.getDate()));

			lbInsert.setInt(3, ot.getShift());
			lbInsert.setString(4, ot.getCrew());
			lbInsert.setString(5, ot.getOperator());
			lbInsert.setInt(6, ot.getOptimeNumber());
			lbInsert.setInt(7, ot.getPressSpeed());

			lbInsert.setString(8, ot.getShellType());
			lbInsert.setInt(9, ot.getProduction());
			lbInsert.setString(10, ot.getComment());

			// ot.setShift(ot.);
			// ot.setCrew(crew);
			// ot.setOperator(operator);
			//
			// ot.setOptimeNumber(optimeNumber);
			// ot.setPressSpeed(pressSpeed);
			// ot.setShellType(shellType);
			// ot.setProduction(production);
			// ot.setComment(comment);

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
	public void OPTimeUpdate(OpTimeModel ot) {

		String sql = "update OPTimeEntry set Shift=? , Crew=? , Operator=? , OptimeNumber=? , PressSpeed=? , "
				+ "ShellType=? , Production=? , Comment=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setInt(1, ot.getShift());
			lbInsert.setString(2, ot.getCrew());
			lbInsert.setString(3, ot.getOperator());
			lbInsert.setInt(4, ot.getOptimeNumber());
			lbInsert.setInt(5, ot.getPressSpeed());
			lbInsert.setString(6, ot.getShellType());
			lbInsert.setInt(7, ot.getProduction());
			lbInsert.setString(8, ot.getComment());

			lbInsert.setDate(9, convertToSqlDate(ot.getDate()));

			// ot.setShift(ot.);
			// ot.setCrew(crew);
			// ot.setOperator(operator);
			//
			// ot.setOptimeNumber(optimeNumber);
			// ot.setPressSpeed(pressSpeed);
			// ot.setShellType(shellType);
			// ot.setProduction(production);
			// ot.setComment(comment);

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
	public JPanel OPTimeSummaryCommentsTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel OPTimeShellsByMonthTable() {
		// TODO Auto-generated method stub
		return null;
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
	public boolean OptimeEntryExists(OpTimeModel ot) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JComboBox[] fillCombos(JComboBox[] oa) {

		JComboBox jCombo1, jCombo2, jCombo3, jCombo4, jCombo5, jCombo6;
		String sql;

		///////////////////////////////////////////////////////////////////
		Connection conn = null;
		jCombo1 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo1.addItem(name);

			}
			rs.close();
			ps.close();
			oa[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo3 = new JComboBox();
		sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				jCombo3.addItem(crewName);

			}
			rs.close();
			ps.close();
			oa[2] = jCombo3;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo4 = new JComboBox();
		sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String shiftName = rs.getString("ShiftName");
				jCombo4.addItem(shiftName);

			}
			rs.close();
			ps.close();
			oa[3] = jCombo4;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		conn = null;
		jCombo2 = new JComboBox();
		sql = "SELECT Press.PressName FROM Press ORDER BY PressName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String pressName = rs.getString("PressName");
				jCombo2.addItem(pressName);

			}
			rs.close();
			ps.close();
			oa[1] = jCombo2;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////

		conn = null;
		jCombo5 = new JComboBox();
		sql = "SELECT Optime.OptimeName FROM Optime ORDER BY OptimeName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String optimeName = rs.getString("OptimeName");
				jCombo5.addItem(optimeName);

			}
			rs.close();
			ps.close();
			oa[4] = jCombo5;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo6 = new JComboBox();
		sql = "SELECT ShellType.ShellTypeName FROM ShellType ORDER BY ShellTypeName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String shellTypeName = rs.getString("ShellTypeName");
				jCombo6.addItem(shellTypeName);

			}
			rs.close();
			ps.close();
			oa[5] = jCombo6;
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

		return oa;

	}

}
