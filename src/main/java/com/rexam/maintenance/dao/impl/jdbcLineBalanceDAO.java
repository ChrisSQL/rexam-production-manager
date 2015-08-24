package com.rexam.maintenance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.rexam.maintenance.dao.LineBalanceDAO;
import com.rexam.maintenance.model.LineBalanceModel;
import com.rexam.maintenance.model.LinerProductionModel;

public class jdbcLineBalanceDAO implements LineBalanceDAO {

	private DataSource dataSource;
	LineBalanceModel lb;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public LineBalanceModel MaintenanceLineBalanceReturnEntryByDate(Date dateIn) {

		lb = new LineBalanceModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM MainLineBalance WHERE MainLineBalance.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lb.setID(rs.getInt(1));
				lb.setDate(rs.getDate(2));
				lb.setMod123Unlined(rs.getInt(3));
				lb.setMod4Lined(rs.getInt(4));
				lb.setMod123Lined(rs.getInt(5));
				lb.setMod4Lined(rs.getInt(6));

			}
			rs.close();
			ps.close();
			return lb;
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
	public LineBalanceModel MaintenanceLineBalanceReturnEntryByID(int id) {
		
		lb = new LineBalanceModel();

		String sql = "SELECT * FROM MainLineBalance WHERE MainLineBalance.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lb.setID(rs.getInt(1));
				lb.setDate(rs.getDate(2));
				lb.setMod123Unlined(rs.getInt(3));
				lb.setMod4Lined(rs.getInt(4));
				lb.setMod123Lined(rs.getInt(5));
				lb.setMod4Lined(rs.getInt(6));

			}
			rs.close();
			ps.close();
			return lb;
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
	public void MaintenanceLineBalanceInsert(LineBalanceModel lb) {
		
		String sql = ("insert into MainLineBalance values(?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setNull(1, java.sql.Types.INTEGER);
			lbInsert.setDate(2, convertToSqlDate(lb.getDate()));
			
			lbInsert.setInt(3, lb.getMod123Unlined());
			lbInsert.setInt(4, lb.getMod4Lined());
			lbInsert.setInt(5, lb.getMod123Lined());
			lbInsert.setInt(6, lb.getMod4Lined());
			

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
	public void MaintenanceLineBalanceUpdate(LineBalanceModel lb) {

		String sql = "update MainLineBalance set Mod123Unlined=? , Mod4Lined=? , "
				+ "Mod123Lined=? , Mod4Lined=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lpUpdate = conn.prepareStatement(sql);

			lpUpdate.setInt(1, lb.getMod123Unlined());
			lpUpdate.setInt(2, lb.getMod4Lined());
			lpUpdate.setInt(3, lb.getMod123Lined());
			lpUpdate.setInt(4, lb.getMod4Lined());
			lpUpdate.setDate(5, convertToSqlDate(lb.getDate()));

			lpUpdate.executeUpdate();
			lpUpdate.close();

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
	public int[] MaintenanceLineBalanceCalculateTotalsByMonth(String monthIn, String yearIn) {
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

}
