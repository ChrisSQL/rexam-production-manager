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

import com.rexam.maintenance.dao.BalancerMaintenanceDAO;
import com.rexam.maintenance.model.BalancerMaintenanceModel;

public class jdbcBalancerMaintenanceDAO implements BalancerMaintenanceDAO {

	BalancerMaintenanceModel bm = new BalancerMaintenanceModel();
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public JPanel MaintenanceBalancerProductionSummaryTable(int in) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BalancerMaintenanceModel balancerMaintenanceReturnEntryByID(int idIn) {

		BalancerMaintenanceModel bm = new BalancerMaintenanceModel();

		String sql = "SELECT * FROM MainBalancerMaintenance WHERE MainBalancerMaintenance.ID = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				bm.setID(rs.getInt(1));
				bm.setMachineCode(rs.getString(2));
				bm.setMachineName(rs.getString(3));

				bm.setLastMaintenanceDate1(rs.getDate(4));
				bm.setLastMaintenanceDate2(rs.getDate(5));
				bm.setLastMaintenanceDate3(rs.getDate(6));
				bm.setLastMaintenanceDate4(rs.getDate(7));
				bm.setLastMaintenanceDate5(rs.getDate(8));
				bm.setLastMaintenanceDate6(rs.getDate(9));
				bm.setLastMaintenanceDate7(rs.getDate(10));

				bm.setMaintenanceDueDate1(rs.getDate(11));
				bm.setMaintenanceDueDate1(rs.getDate(12));
				bm.setMaintenanceDueDate1(rs.getDate(13));
				bm.setMaintenanceDueDate1(rs.getDate(14));
				bm.setMaintenanceDueDate1(rs.getDate(15));
				bm.setMaintenanceDueDate1(rs.getDate(16));
				bm.setMaintenanceDueDate1(rs.getDate(17));

				bm.setProduction1(rs.getInt(18));
				bm.setProduction2(rs.getInt(19));
				bm.setProduction3(rs.getInt(20));
				bm.setProduction4(rs.getInt(21));
				bm.setProduction5(rs.getInt(22));
				bm.setProduction6(rs.getInt(23));
				bm.setProduction7(rs.getInt(24));

				bm.setTargetProduction1(rs.getInt(25));
				bm.setTargetProduction1(rs.getInt(26));
				bm.setTargetProduction1(rs.getInt(27));
				bm.setTargetProduction1(rs.getInt(28));
				bm.setTargetProduction1(rs.getInt(29));
				bm.setTargetProduction1(rs.getInt(30));
				bm.setTargetProduction1(rs.getInt(31));

			}
			rs.close();
			ps.close();
			return bm;
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
	public void balancerMaintenanceUpdate(BalancerMaintenanceModel sm) {

		String sqlQuery = "UPDATE MainBalancerMaintenance " + "SET MachineCode = ?, " + "MachineName = ?, "
							+ "LastMaintanenceDate1 = ? " + "LastMaintanenceDate2 = ? " + "LastMaintanenceDate3 = ? "
							+ "LastMaintanenceDate4 = ? " + "LastMaintanenceDate5 = ? " + "LastMaintanenceDate6 = ? "
							+ "LastMaintanenceDate7 = ? " + "MaintanenceDueDate1 = ? " + "MaintanenceDueDate2 = ? "
							+ "MaintanenceDueDate3 = ? " + "MaintanenceDueDate4 = ? " + "MaintanenceDueDate5 = ? "
							+ "MaintanenceDueDate6 = ? " + "production1 = ? " + "production2 = ? " + "production3 = ? "
							+ "production4 = ? " + "production5 = ? " + "production6 = ? " + "production7 = ? "
							+ "targetProduction1 = ? " + "targetProduction2 = ? " + "targetProduction3 = ? "
							+ "targetProduction4 = ? " + "targetProduction5 = ? " + "targetProduction6 = ? "
							+ "targetProduction7 = ? " + " WHERE ID = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement bmInsert = conn.prepareStatement(sqlQuery);

			bmInsert.setString(1, sm.getMachineCode());
			bmInsert.setString(2, sm.getMachineName());
			bmInsert.setDate(3, convertToSqlDate(sm.getLastMaintenanceDate1()));
			bmInsert.setDate(4, convertToSqlDate(sm.getLastMaintenanceDate2()));
			bmInsert.setDate(5, convertToSqlDate(sm.getLastMaintenanceDate3()));
			bmInsert.setDate(6, convertToSqlDate(sm.getLastMaintenanceDate4()));
			bmInsert.setDate(7, convertToSqlDate(sm.getLastMaintenanceDate5()));
			bmInsert.setDate(8, convertToSqlDate(sm.getLastMaintenanceDate6()));
			bmInsert.setDate(9, convertToSqlDate(sm.getLastMaintenanceDate7()));
			bmInsert.setDate(10, convertToSqlDate(sm.getMaintenanceDueDate1()));
			bmInsert.setDate(11, convertToSqlDate(sm.getMaintenanceDueDate2()));
			bmInsert.setDate(12, convertToSqlDate(sm.getMaintenanceDueDate3()));
			bmInsert.setDate(13, convertToSqlDate(sm.getMaintenanceDueDate4()));
			bmInsert.setDate(14, convertToSqlDate(sm.getMaintenanceDueDate5()));
			bmInsert.setDate(15, convertToSqlDate(sm.getMaintenanceDueDate6()));
			bmInsert.setDate(16, convertToSqlDate(sm.getMaintenanceDueDate7()));
			bmInsert.setInt(17, sm.getProduction1());
			bmInsert.setInt(18, sm.getProduction2());
			bmInsert.setInt(19, sm.getProduction3());
			bmInsert.setInt(20, sm.getProduction4());
			bmInsert.setInt(21, sm.getProduction5());
			bmInsert.setInt(22, sm.getProduction6());
			bmInsert.setInt(23, sm.getProduction7());
			bmInsert.setInt(24, sm.getTargetProduction1());
			bmInsert.setInt(25, sm.getTargetProduction2());
			bmInsert.setInt(26, sm.getTargetProduction3());
			bmInsert.setInt(27, sm.getTargetProduction4());
			bmInsert.setInt(28, sm.getTargetProduction5());
			bmInsert.setInt(29, sm.getTargetProduction6());
			bmInsert.setInt(30, sm.getID());

			bmInsert.executeUpdate();
			bmInsert.close();

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
