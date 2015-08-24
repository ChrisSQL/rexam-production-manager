package com.rexam.maintenance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.rexam.maintenance.dao.ShellPressMaintenanceDAO;
import com.rexam.maintenance.dao.ShellPressProductionDAO;
import com.rexam.maintenance.model.ShellPressMaintenanceModel;
import com.rexam.maintenance.model.ShellPressProductionModel;

public class jdbcShellPressMaintenanceDAO implements ShellPressMaintenanceDAO{
	
	ShellPressMaintenanceModel pm = new ShellPressMaintenanceModel();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	public void shellPressMaintenanceUpdate(ShellPressMaintenanceModel sm) {
				
		String sqlQuery = "UPDATE MainShellPressMaintenance " + "SET  MachineName = ?, "
							+ "LastMaintanenceDate1 = ? " + "LastMaintanenceDate2 = ? " + "LastMaintanenceDate3 = ? "
							+ "LastMaintanenceDate4 = ? " + "LastMaintanenceDate5 = ? " + "LastMaintanenceDate6 = ? "
							+ "LastMaintanenceDate7 = ? " + "MaintanenceDueDate1 = ? " + "MaintanenceDueDate2 = ? "
							+ "MaintanenceDueDate3 = ? " + "MaintanenceDueDate4 = ? " + "MaintanenceDueDate5 = ? "
							+ "MaintanenceDueDate6 = ? " + "production1 = ? " + "production2 = ? " + "production3 = ? "
							+ "production4 = ? " + "production5 = ? " + "production6 = ? " + "production7 = ? "
							+ "targetProduction1 = ? " + "targetProduction2 = ? " + "targetProduction3 = ? "
							+ "targetProduction4 = ? " + "targetProduction5 = ? " + "targetProduction6 = ? "
							+ "targetProduction7 = ? " + " WHERE MachineCode = ?";

		System.out.println("Query: " + pm.getId());
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement mainUpdate = conn.prepareStatement(sqlQuery);

			
			mainUpdate.setString(1, sm.getMachineName());
			mainUpdate.setDate(2, convertToSqlDate(sm.getLastMaintenanceDate1()));
			mainUpdate.setDate(3, convertToSqlDate(sm.getLastMaintenanceDate2()));
			mainUpdate.setDate(4, convertToSqlDate(sm.getLastMaintenanceDate3()));
			mainUpdate.setDate(5, convertToSqlDate(sm.getLastMaintenanceDate4()));
			mainUpdate.setDate(6, convertToSqlDate(sm.getLastMaintenanceDate5()));
			mainUpdate.setDate(7, convertToSqlDate(sm.getLastMaintenanceDate6()));
			mainUpdate.setDate(8, convertToSqlDate(sm.getLastMaintenanceDate7()));
			mainUpdate.setDate(9, convertToSqlDate(sm.getMaintenanceDueDate1()));
			mainUpdate.setDate(10, convertToSqlDate(sm.getMaintenanceDueDate2()));
			mainUpdate.setDate(11, convertToSqlDate(sm.getMaintenanceDueDate3()));
			mainUpdate.setDate(12, convertToSqlDate(sm.getMaintenanceDueDate4()));
			mainUpdate.setDate(13, convertToSqlDate(sm.getMaintenanceDueDate5()));
			mainUpdate.setDate(14, convertToSqlDate(sm.getMaintenanceDueDate6()));
			mainUpdate.setDate(15, convertToSqlDate(sm.getMaintenanceDueDate7()));
			mainUpdate.setInt(16, sm.getProduction1());
			mainUpdate.setInt(17, sm.getProduction2());
			mainUpdate.setInt(18, sm.getProduction3());
			mainUpdate.setInt(19, sm.getProduction4());
			mainUpdate.setInt(20, sm.getProduction5());
			mainUpdate.setInt(21, sm.getProduction6());
			mainUpdate.setInt(22, sm.getProduction7());
			mainUpdate.setInt(23, sm.getTargetProduction1());
			mainUpdate.setInt(24, sm.getTargetProduction2());
			mainUpdate.setInt(25, sm.getTargetProduction3());
			mainUpdate.setInt(26, sm.getTargetProduction4());
			mainUpdate.setInt(27, sm.getTargetProduction5());
			mainUpdate.setInt(28, sm.getTargetProduction6());
			mainUpdate.setString(29, sm.getMachineCode());
			
			mainUpdate.executeUpdate();
			mainUpdate.close();

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

	public ShellPressMaintenanceModel shellPressMaintenanceReturnEntryByID(int idIn) {
		
		ShellPressMaintenanceModel sm = new ShellPressMaintenanceModel();
		
		String sql = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				sm.setId(rs.getInt(1));
				sm.setMachineCode(rs.getString(2));
				sm.setMachineName(rs.getString(3));
				
				sm.setLastMaintenanceDate1(rs.getDate(4));
				sm.setLastMaintenanceDate2(rs.getDate(5));
				sm.setLastMaintenanceDate3(rs.getDate(6));
				sm.setLastMaintenanceDate4(rs.getDate(7));
				sm.setLastMaintenanceDate5(rs.getDate(8));
				sm.setLastMaintenanceDate6(rs.getDate(9));
				sm.setLastMaintenanceDate7(rs.getDate(10));
				
				sm.setMaintenanceDueDate1(rs.getDate(11));
				sm.setMaintenanceDueDate1(rs.getDate(12));
				sm.setMaintenanceDueDate1(rs.getDate(13));
				sm.setMaintenanceDueDate1(rs.getDate(14));
				sm.setMaintenanceDueDate1(rs.getDate(15));
				sm.setMaintenanceDueDate1(rs.getDate(16));
				sm.setMaintenanceDueDate1(rs.getDate(17));
				
				sm.setProduction1(rs.getInt(18));
				sm.setProduction2(rs.getInt(19));
				sm.setProduction3(rs.getInt(20));
				sm.setProduction4(rs.getInt(21));
				sm.setProduction5(rs.getInt(22));
				sm.setProduction6(rs.getInt(23));
				sm.setProduction7(rs.getInt(24));
				
				sm.setTargetProduction1(rs.getInt(25));
				sm.setTargetProduction1(rs.getInt(26));
				sm.setTargetProduction1(rs.getInt(27));
				sm.setTargetProduction1(rs.getInt(28));
				sm.setTargetProduction1(rs.getInt(29));
				sm.setTargetProduction1(rs.getInt(30));
				sm.setTargetProduction1(rs.getInt(31));

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
		

//		ShellPressMaintenanceModel sm = new ShellPressMaintenanceModel();
//
//		Connection conn = databaseConnect();
//		Statement s;
//		try {
//			s = conn.createStatement();
//		
//
//		// QUERY /////////////////////////////////////////////////////////////
//		String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + idIn
//				+ "\";";
//		System.out.println(selTable);
//		s.setQueryTimeout(10);
//		s.execute(selTable);
//
//		ResultSet rs = s.getResultSet();
//
//		while ((rs != null) && (rs.next())) {
//
//			sm.setId(rs.getInt(1));
//			sm.setMachineCode(rs.getString(2));
//			sm.setMachineName(rs.getString(3));
//			
//			sm.setLastMaintenanceDate1(rs.getDate(4));
//			sm.setLastMaintenanceDate2(rs.getDate(5));
//			sm.setLastMaintenanceDate3(rs.getDate(6));
//			sm.setLastMaintenanceDate4(rs.getDate(7));
//			sm.setLastMaintenanceDate5(rs.getDate(8));
//			sm.setLastMaintenanceDate6(rs.getDate(9));
//			sm.setLastMaintenanceDate7(rs.getDate(10));
//			
//			sm.setMaintenanceDueDate1(rs.getDate(11));
//			sm.setMaintenanceDueDate1(rs.getDate(12));
//			sm.setMaintenanceDueDate1(rs.getDate(13));
//			sm.setMaintenanceDueDate1(rs.getDate(14));
//			sm.setMaintenanceDueDate1(rs.getDate(15));
//			sm.setMaintenanceDueDate1(rs.getDate(16));
//			sm.setMaintenanceDueDate1(rs.getDate(17));
//			
//			sm.setProduction1(rs.getInt(18));
//			sm.setProduction2(rs.getInt(19));
//			sm.setProduction3(rs.getInt(20));
//			sm.setProduction4(rs.getInt(21));
//			sm.setProduction5(rs.getInt(22));
//			sm.setProduction6(rs.getInt(23));
//			sm.setProduction7(rs.getInt(24));
//			
//			sm.setTargetProduction1(rs.getInt(25));
//			sm.setTargetProduction1(rs.getInt(26));
//			sm.setTargetProduction1(rs.getInt(27));
//			sm.setTargetProduction1(rs.getInt(28));
//			sm.setTargetProduction1(rs.getInt(29));
//			sm.setTargetProduction1(rs.getInt(30));
//			sm.setTargetProduction1(rs.getInt(31));
//
//		}
//
//		rs.close();
//		s.close();
//		conn.close();
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return sm;
		
		

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
