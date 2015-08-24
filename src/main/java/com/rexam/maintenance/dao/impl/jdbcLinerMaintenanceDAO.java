package com.rexam.maintenance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.rexam.maintenance.dao.LinerMaintenanceDAO;
import com.rexam.maintenance.model.LexanFingerTrackingModel;
import com.rexam.maintenance.model.LinerMaintenanceModel;
import com.rexam.maintenance.model.ShellPressMaintenanceModel;

public class jdbcLinerMaintenanceDAO implements LinerMaintenanceDAO {

	private DataSource dataSource;
	LexanFingerTrackingModel lft;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int MaintenanceLinerMaintenanceGetHighestID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinerMaintenanceModel MaintenanceLinerMaintenanceReturnEntryByDate(Date dateIn) {

		LinerMaintenanceModel lm = new LinerMaintenanceModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "SELECT * FROM MainLinerMaintenance WHERE MainLinerMaintenance.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setID(rs.getInt(1));

				lm.setMachineCode(rs.getString(2));
				lm.setMachineName(rs.getString(3));

				lm.setLastMaintenanceDate1(rs.getDate(4));
				lm.setLastMaintenanceDate2(rs.getDate(5));
				lm.setLastMaintenanceDate3(rs.getDate(6));

				lm.setMaintenanceDueDate1(rs.getDate(7));
				lm.setMaintenanceDueDate2(rs.getDate(8));
				lm.setMaintenanceDueDate3(rs.getDate(9));

				lm.setProduction1(rs.getInt(10));
				lm.setProduction2(rs.getInt(11));
				lm.setProduction3(rs.getInt(12));

				lm.setTargetProduction1(rs.getInt(13));
				lm.setTargetProduction2(rs.getInt(14));
				lm.setTargetProduction3(rs.getInt(15));

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
	public LinerMaintenanceModel MaintenanceLinerMaintenanceReturnEntryByID(int id) {

		LinerMaintenanceModel lm = new LinerMaintenanceModel();

		String sql = "SELECT * FROM MainLinerMaintenance WHERE MainLinerMaintenance.ID = \"" + id + "\";";
		
		System.out.println("idIn : " + id);

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setID(rs.getInt(1));

				lm.setMachineCode(rs.getString(2));
				lm.setMachineName(rs.getString(3));

				lm.setLastMaintenanceDate1(rs.getDate(4));
				lm.setLastMaintenanceDate2(rs.getDate(5));
				lm.setLastMaintenanceDate3(rs.getDate(6));

				lm.setMaintenanceDueDate1(rs.getDate(7));
				lm.setMaintenanceDueDate2(rs.getDate(8));
				lm.setMaintenanceDueDate3(rs.getDate(9));

				lm.setProduction1(rs.getInt(10));
				lm.setProduction2(rs.getInt(11));
				lm.setProduction3(rs.getInt(12));

				lm.setTargetProduction1(rs.getInt(13));
				lm.setTargetProduction2(rs.getInt(14));
				lm.setTargetProduction3(rs.getInt(15));

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
	public void MaintenanceLinerMaintenanceUpdate(LinerMaintenanceModel lmIn) {
		

		String sqlQuery = "UPDATE MainShellPressMaintenance " + "SET  MachineName = ?, " + "LastMaintanenceDate1 = ?, "
				+ "LastMaintanenceDate2 = ?, " + "LastMaintanenceDate3 = ?, MaintanenceDueDate1 = ?, " + "MaintanenceDueDate2 = ?, " + "MaintanenceDueDate3 =?,"
						+ " production1 = ?, " + "production2 = ?, " + "production3 = ?, targetProduction1 = ?, " + "targetProduction2 = ?, "
				+ "targetProduction3 = ? WHERE MainShellPressMaintenance.ID = ?";

		Connection conn = null;
		
		System.out.println("LMin " + lmIn.getID());

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmUpdate = conn.prepareStatement(sqlQuery);

			lmUpdate.setString(1, lmIn.getMachineName());
			
			lmUpdate.setDate(2, convertToSqlDate(lmIn.getLastMaintenanceDate1()));
			lmUpdate.setDate(3, convertToSqlDate(lmIn.getLastMaintenanceDate2()));
			lmUpdate.setDate(4, convertToSqlDate(lmIn.getLastMaintenanceDate3()));
			
			lmUpdate.setDate(5, convertToSqlDate(lmIn.getMaintenanceDueDate1()));
			lmUpdate.setDate(6, convertToSqlDate(lmIn.getMaintenanceDueDate2()));
			lmUpdate.setDate(7, convertToSqlDate(lmIn.getMaintenanceDueDate3()));

			lmUpdate.setInt(8, lmIn.getProduction1());
			lmUpdate.setInt(9, lmIn.getProduction2());
			lmUpdate.setInt(10, lmIn.getProduction3());
			
			lmUpdate.setInt(11, lmIn.getTargetProduction1());
			lmUpdate.setInt(12, lmIn.getTargetProduction2());
			lmUpdate.setInt(13, lmIn.getTargetProduction3());
			
			lmUpdate.setInt(14, lmIn.getID());

			lmUpdate.executeUpdate();
			lmUpdate.close();

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

	
}
