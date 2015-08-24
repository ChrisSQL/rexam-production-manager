package com.rexam.binentry.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.rexam.binentry.dao.EndCountsDAO;
import com.rexam.binentry.model.EndCountsModel;
import com.rexam.binentry.model.LinerDefectsModel;

public class jdbcEndCountsDAO implements EndCountsDAO{
	
	EndCountsModel ec;
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public EndCountsModel EndCountsReturnEntryByDate(Date dateIn) {

		ec = new EndCountsModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM EndCounts WHERE EndCounts.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				ec.setId(rs.getInt(1));
				ec.setDate(rs.getDate(2));
				
				ec.setW11(rs.getInt(3));
				ec.setW12(rs.getInt(4));
				ec.setW21(rs.getInt(5));
				ec.setW22(rs.getInt(6));
				ec.setW31(rs.getInt(7));
				ec.setW32(rs.getInt(8));
				ec.setW33(rs.getInt(9));
				ec.setW41(rs.getInt(10));
				ec.setW42(rs.getInt(11));
				ec.setW43(rs.getInt(12));
				ec.setW44(rs.getInt(13));
				
								
			}
			rs.close();
			ps.close();
			return ec;
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
	public EndCountsModel EndCountsReturnEntryByID(int id) {
		ec = new EndCountsModel();

		String sql = "SELECT * FROM EndCounts WHERE EndCounts.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				ec.setId(rs.getInt(1));
				ec.setDate(rs.getDate(2));
				
				ec.setW11(rs.getInt(3));
				ec.setW12(rs.getInt(4));
				ec.setW21(rs.getInt(5));
				ec.setW22(rs.getInt(6));
				ec.setW31(rs.getInt(7));
				ec.setW32(rs.getInt(8));
				ec.setW33(rs.getInt(9));
				ec.setW41(rs.getInt(10));
				ec.setW42(rs.getInt(11));
				ec.setW43(rs.getInt(12));
				ec.setW44(rs.getInt(13));
				
								
			}
			rs.close();
			ps.close();
			return ec;
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
	public boolean EndCountsEntryExists(EndCountsModel ec) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void EndCountsInsert(EndCountsModel ec) {
		
		String sql = ("insert into EndCounts values(?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setNull(1, java.sql.Types.INTEGER);
			lbInsert.setDate(2, convertToSqlDate(ec.getDate()));
			
			lbInsert.setInt(3, ec.getW11());
			lbInsert.setInt(4, ec.getW12());
			lbInsert.setInt(5, ec.getW21());
			lbInsert.setInt(6, ec.getW22());
			lbInsert.setInt(7, ec.getW31());
			lbInsert.setInt(8, ec.getW32());
			lbInsert.setInt(9, ec.getW33());
			lbInsert.setInt(10, ec.getW41());
			lbInsert.setInt(11, ec.getW42());
			lbInsert.setInt(12, ec.getW43());
			
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
	public void EndCountsUpdate(EndCountsModel ec) {
		
		 String sql = "update EndCounts set W11=? , W12=? , W21=? , W22=?, W31=? , "
	                + "W32=? , W33=? , total1=? , W41=? , W42=?, W43=?, W44=?, total2=?, total3=?  where Date=?";

        Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);
						
			lbInsert.setInt(1, ec.getW11());
			lbInsert.setInt(2, ec.getW12());
			lbInsert.setInt(3, ec.getW21());
			lbInsert.setInt(4, ec.getW22());
			lbInsert.setInt(5, ec.getW31());
			lbInsert.setInt(6, ec.getW32());
			lbInsert.setInt(7, ec.getW33());
			lbInsert.setInt(8, ec.getW41());
			lbInsert.setInt(9, ec.getW42());
			lbInsert.setInt(10, ec.getW43());
			
			lbInsert.setDate(11, convertToSqlDate(ec.getDate()));
			
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
	public Object[] EndCountsCalculateTotalsByMonth(String monthIn, String yearIn) {
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
