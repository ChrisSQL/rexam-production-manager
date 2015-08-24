package com.rexam.binentry.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;
import javax.swing.JPanel;

import com.rexam.binentry.dao.LinerDefectsDAO;
import com.rexam.binentry.model.LinerDefectsModel;
import com.rexam.maintenance.model.LineBalanceModel;

public class jdbcLinerDefectsDAO implements LinerDefectsDAO{
	
	LinerDefectsModel lm;
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public LinerDefectsModel LinerDefectsReturnEntryByDate(Date dateIn) {
		
		lm = new LinerDefectsModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM LinerDefects WHERE LinerDefects.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setDate(rs.getDate(2));				
				lm.setM1Defects(rs.getInt(3));
				lm.setM1Liner(rs.getInt(4));
				lm.setM2Defects(rs.getInt(5));
				lm.setM2Liner(rs.getInt(6));
				lm.setM3Defects(rs.getInt(7));
				lm.setM3Liner(rs.getInt(8));
				lm.setM4Defects(rs.getInt(9));
				lm.setM4Liner(rs.getInt(10));
				lm.setTotalDefects(rs.getInt(11));
				lm.setTotalLined(rs.getInt(12));
								
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
	public LinerDefectsModel LinerDefectsReturnEntryByID(int id) {
		
		lm = new LinerDefectsModel();

		String sql = "SELECT * FROM LinerDefects WHERE LinerDefects.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setDate(rs.getDate(2));				
				lm.setM1Defects(rs.getInt(3));
				lm.setM1Liner(rs.getInt(4));
				lm.setM2Defects(rs.getInt(5));
				lm.setM2Liner(rs.getInt(6));
				lm.setM3Defects(rs.getInt(7));
				lm.setM3Liner(rs.getInt(8));
				lm.setM4Defects(rs.getInt(9));
				lm.setM4Liner(rs.getInt(10));
				lm.setTotalDefects(rs.getInt(11));
				lm.setTotalLined(rs.getInt(12));
								
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
	public boolean LinerDefectsEntryExists(LinerDefectsModel ld) {
		lm = new LinerDefectsModel();

		String sql = "SELECT * FROM LinerDefects WHERE LinerDefects.Date = \"" + ld.getDate() + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				return true;
				
			}else {
				
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

	@Override
	public void LinerDefectsInsert(LinerDefectsModel ld) {
		
		String sql = ("insert into LinerDefects values(?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);

			lbInsert.setNull(1, java.sql.Types.INTEGER);
			lbInsert.setDate(2, convertToSqlDate(ld.getDate()));
			
			lbInsert.setInt(3, ld.getM1Defects());
			lbInsert.setInt(4, ld.getM1Liner());
			lbInsert.setInt(5, ld.getM2Defects());
			lbInsert.setInt(6, ld.getM2Liner());
			lbInsert.setInt(7, ld.getM3Defects());
			lbInsert.setInt(8, ld.getM3Liner());
			lbInsert.setInt(9, ld.getM4Defects());
			lbInsert.setInt(10, ld.getM4Liner());
			lbInsert.setInt(11, ld.getTotalDefects());
			lbInsert.setInt(12, ld.getTotalLined());
			
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
	public void LinerDefectsUpdate(LinerDefectsModel ld) {

        String sql = "update LinerDefects set M1Liner=? , M1Defects=? , M2Liner=? , M2Defects=?, M3Liner=? , "
                + "M3Defects=? , M4Liner=? , M4Defects=? , TotalLined=? , TotalDefects=?  where Date=?";

        Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lbInsert = conn.prepareStatement(sql);
						
			lbInsert.setInt(1, ld.getM1Defects());
			lbInsert.setInt(2, ld.getM1Liner());
			lbInsert.setInt(3, ld.getM2Defects());
			lbInsert.setInt(4, ld.getM2Liner());
			lbInsert.setInt(5, ld.getM3Defects());
			lbInsert.setInt(6, ld.getM3Liner());
			lbInsert.setInt(7, ld.getM4Defects());
			lbInsert.setInt(8, ld.getM4Liner());
			lbInsert.setInt(9, ld.getTotalDefects());
			lbInsert.setInt(10, ld.getTotalLined());
			
			lbInsert.setDate(11, convertToSqlDate(ld.getDate()));
			
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
	public LinerDefectsModel LinerDefectsCalculateTotalsByMonth(String monthIn, String yearIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel LinerDefectsSummaryTable(int in) {
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
