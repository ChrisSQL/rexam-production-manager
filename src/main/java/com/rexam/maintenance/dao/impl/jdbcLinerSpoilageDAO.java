package com.rexam.maintenance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JPanel;

import com.rexam.maintenance.dao.LinerSpoilageDAO;
import com.rexam.maintenance.model.LinerSpoilageModel;
import com.rexam.maintenance.model.ShellPressProductionModel;

public class jdbcLinerSpoilageDAO implements LinerSpoilageDAO{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public LinerSpoilageModel MaintenanceLinerSpoilageReturnEntryByDate(Date dateIn) {
		
		LinerSpoilageModel lm = new LinerSpoilageModel();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "SELECT * " + "FROM MainLinerSpoilage " + "WHERE MainLinerSpoilage.Date = \"" + df
				+ "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ShellPressProductionModel pm = new ShellPressProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setID(rs.getInt(1));
				lm.setDate(rs.getDate(2));
				
				lm.setLiner11(rs.getInt(3));
				lm.setLiner12(rs.getInt(4));
				lm.setLiner13(rs.getInt(5));
				lm.setLiner14(rs.getInt(6));
				
				lm.setLiner21(rs.getInt(7));
				lm.setLiner22(rs.getInt(8));
				lm.setLiner23(rs.getInt(9));
				lm.setLiner24(rs.getInt(10));
				
				lm.setLiner31(rs.getInt(11));
				lm.setLiner32(rs.getInt(12));
				lm.setLiner33(rs.getInt(13));
				lm.setLiner34(rs.getInt(14));
				
				lm.setLiner41(rs.getInt(15));
				lm.setLiner42(rs.getInt(16));
				lm.setLiner43(rs.getInt(17));
				lm.setLiner44(rs.getInt(18));
				lm.setLiner45(rs.getInt(19));
				lm.setLiner46(rs.getInt(20));
				
				

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
	public LinerSpoilageModel MaintenanceLinerSpoilageReturnEntryByID(int id) {
LinerSpoilageModel lm = new LinerSpoilageModel();
		
		String sql = "SELECT * " + "FROM MainLinerSpoilage " + "WHERE MainLinerSpoilage.ID = \"" + id
				+ "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ShellPressProductionModel pm = new ShellPressProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {


				lm.setDate(rs.getDate(2));
				
				lm.setLiner11(rs.getInt(3));
				lm.setLiner12(rs.getInt(4));
				lm.setLiner13(rs.getInt(5));
				lm.setLiner14(rs.getInt(6));
				
				lm.setLiner21(rs.getInt(7));
				lm.setLiner22(rs.getInt(8));
				lm.setLiner23(rs.getInt(9));
				lm.setLiner24(rs.getInt(10));
				
				lm.setLiner31(rs.getInt(11));
				lm.setLiner32(rs.getInt(12));
				lm.setLiner33(rs.getInt(13));
				lm.setLiner34(rs.getInt(14));
				
				lm.setLiner41(rs.getInt(15));
				lm.setLiner42(rs.getInt(16));
				lm.setLiner43(rs.getInt(17));
				lm.setLiner44(rs.getInt(18));
				lm.setLiner45(rs.getInt(19));
				lm.setLiner46(rs.getInt(20));
				
				

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
	public void MaintenanceLinerSpoilageInsert(LinerSpoilageModel ls) {
		
		String sql = ("insert into MainLinerSpoilage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lsInsert = conn.prepareStatement(sql);
			
			lsInsert.setNull(1, java.sql.Types.INTEGER);
			lsInsert.setDate(2, convertToSqlDate(ls.getDate()));
			
			lsInsert.setInt(3, ls.getLiner11());
			lsInsert.setInt(4, ls.getLiner12());
			lsInsert.setInt(5, ls.getLiner13());
			lsInsert.setInt(6, ls.getLiner14());
			
			lsInsert.setInt(7, ls.getLiner21());
			lsInsert.setInt(8, ls.getLiner22());
			lsInsert.setInt(9, ls.getLiner23());
			lsInsert.setInt(10, ls.getLiner24());
			
			lsInsert.setInt(11, ls.getLiner31());
			lsInsert.setInt(12, ls.getLiner32());
			lsInsert.setInt(13, ls.getLiner33());
			lsInsert.setInt(14, ls.getLiner34());
			
			lsInsert.setInt(15, ls.getLiner41());
			lsInsert.setInt(16, ls.getLiner42());
			lsInsert.setInt(17, ls.getLiner43());
			lsInsert.setInt(18, ls.getLiner44());
			lsInsert.setInt(19, ls.getLiner45());
			lsInsert.setInt(20, ls.getLiner46());
			

			
			
			lsInsert.executeUpdate();
			lsInsert.close();

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
	public void MaintenanceLinerSpoilageUpdate(LinerSpoilageModel ls) {
		
		String sql = "update MainLinerSpoilage set Date=?, Liner11=? , Liner12=? , Liner13=? , Liner14=?, Liner21=? , "
                + "Liner22=?,  Liner23=?, Liner24=?, Liner31=?, Liner32=?, Liner33=?, Liner34=?, Liner41=?, Liner42=?, Liner43=?, Liner44=?, Liner45=?, Liner46=? where ID=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lsInsert = conn.prepareStatement(sql);
			
			lsInsert.setDate(1, convertToSqlDate(ls.getDate()));
			
			lsInsert.setInt(2, ls.getLiner11());
			lsInsert.setInt(3, ls.getLiner12());
			lsInsert.setInt(4, ls.getLiner13());
			lsInsert.setInt(5, ls.getLiner14());
			
			lsInsert.setInt(6, ls.getLiner21());
			lsInsert.setInt(7, ls.getLiner22());
			lsInsert.setInt(8, ls.getLiner23());
			lsInsert.setInt(9, ls.getLiner24());
			
			lsInsert.setInt(10, ls.getLiner31());
			lsInsert.setInt(11, ls.getLiner32());
			lsInsert.setInt(12, ls.getLiner33());
			lsInsert.setInt(13, ls.getLiner34());
			
			lsInsert.setInt(14, ls.getLiner41());
			lsInsert.setInt(15, ls.getLiner42());
			lsInsert.setInt(16, ls.getLiner43());
			lsInsert.setInt(17, ls.getLiner44());
			lsInsert.setInt(18, ls.getLiner45());
			lsInsert.setInt(19, ls.getLiner46());
			lsInsert.setNull(20, ls.getID());
			

			lsInsert.executeUpdate();
			lsInsert.close();

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
	public Object[] MaintenanceLinerSpoilageCalculateTotalsByMonth(String monthIn, String yearIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel MaintenanceLinerSpoilageSummaryTable(int in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] MaintenanceLinerSpoilageSevenDaysAverages() {
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
