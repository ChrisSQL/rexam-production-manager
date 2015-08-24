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

import com.rexam.maintenance.dao.TransferBeltDAO;
import com.rexam.maintenance.model.ShellPressProductionModel;
import com.rexam.maintenance.model.TransferBeltModel;

public class jdbcTransferBeltDAO implements TransferBeltDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public TransferBeltModel transferBeltReturnEntryByID(int idIn) {
		
		TransferBeltModel tm = new TransferBeltModel();
		
		String sql = "SELECT * FROM MainTransferBelt WHERE MainTransferBelt.ID = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ShellPressProductionModel pm = new ShellPressProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				tm.setID(idIn);
				tm.setMachineCode(rs.getString(2));
				tm.setDateFitted(rs.getDate(3));
	            tm.setEndsTargeted(rs.getInt(4));
	            tm.setActualEnds(rs.getInt(5));
	            tm.setPlusOrMinus(rs.getInt(6));
	            tm.setSetUpCheckDate(rs.getDate(7));
	            tm.setBeltsRemoved1(rs.getDate(8));
	            tm.setBeltsRemoved2(rs.getDate(9));
	            tm.setBeltsRemoved3(rs.getDate(10));
	            tm.setBeltsRemoved4(rs.getDate(11));
	            tm.setBeltsRemoved5(rs.getDate(12));
	            tm.setBeltsRemoved6(rs.getDate(13));
	            tm.setBeltsRemoved7(rs.getDate(14));
	            tm.setBeltsRemoved8(rs.getDate(15));
	            tm.setBeltsRemoved9(rs.getDate(16));
	            tm.setBeltsRemoved10(rs.getDate(17));
	            tm.setBeltsRemoved11(rs.getDate(18));
	            tm.setBeltsRemoved12(rs.getDate(19));

			}
			rs.close();
			ps.close();
			return tm;
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

	public void transferBeltUpdate(TransferBeltModel tm) {
		

		String sql = "update MainTransferBelt set DateFitted=? , EndsTargetted=? , "
				+ "ActualEnds=? , PlusOrMinus=?, SetUpCheckDate=?, BeltsRemoved1=?, BeltsRemoved2=?, "
				+ "BeltsRemoved3=?, BeltsRemoved4=?, BeltsRemoved5=?, BeltsRemoved6=?, BeltsRemoved7=?, "
				+ "BeltsRemoved8=?, BeltsRemoved9=?, BeltsRemoved10=?, BeltsRemoved11=?, BeltsRemoved12=? where MachineCode=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement tbUpdate = conn.prepareStatement(sql);

			tbUpdate.setDate(1, convertToSqlDate(tm.getDateFitted()));
			tbUpdate.setInt(2, tm.getEndsTargeted());
			tbUpdate.setInt(3, tm.getActualEnds());
			tbUpdate.setInt(4, tm.getPlusOrMinus());
			tbUpdate.setDate(5, convertToSqlDate(tm.getSetUpCheckDate()));
			tbUpdate.setDate(6, convertToSqlDate(tm.getBeltsRemoved1()));
			tbUpdate.setDate(7, convertToSqlDate(tm.getBeltsRemoved2()));
			tbUpdate.setDate(8, convertToSqlDate(tm.getBeltsRemoved3()));
			tbUpdate.setDate(9, convertToSqlDate(tm.getBeltsRemoved4()));
			tbUpdate.setDate(10, convertToSqlDate(tm.getBeltsRemoved5()));
			tbUpdate.setDate(11, convertToSqlDate(tm.getBeltsRemoved6()));
			tbUpdate.setDate(12, convertToSqlDate(tm.getBeltsRemoved7()));
			tbUpdate.setDate(13, convertToSqlDate(tm.getBeltsRemoved8()));
			tbUpdate.setDate(14, convertToSqlDate(tm.getBeltsRemoved9()));
			tbUpdate.setDate(15, convertToSqlDate(tm.getBeltsRemoved10()));
			tbUpdate.setDate(16, convertToSqlDate(tm.getBeltsRemoved11()));
			tbUpdate.setDate(17, convertToSqlDate(tm.getBeltsRemoved12()));
			tbUpdate.setString(18, tm.getMachineCode());
			tbUpdate.executeUpdate();
			tbUpdate.close();

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
