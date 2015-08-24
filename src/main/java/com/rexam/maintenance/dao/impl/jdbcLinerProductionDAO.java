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

import com.rexam.maintenance.dao.LinerProductionDAO;
import com.rexam.maintenance.model.LinerProductionModel;
import com.rexam.maintenance.model.ShellPressProductionModel;

public class jdbcLinerProductionDAO implements LinerProductionDAO {

	private DataSource dataSource;
	LinerProductionModel lp;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int linerProductionGetHighestID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinerProductionModel linerProductionReturnEntryByDate(LinerProductionModel lpIn) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(lpIn.getDate()));

		String sql = "SELECT * FROM MainLinerProduction WHERE MainLinerProduction.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			lp = new LinerProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lp.setID(rs.getInt(1));
				lp.setDate(rs.getDate(2));
				lp.setLiner11(rs.getInt(3));
				lp.setLiner12(rs.getInt(4));
				lp.setLiner13(rs.getInt(5));
				lp.setLiner14(rs.getInt(6));
				lp.setLiner21(rs.getInt(7));
				lp.setLiner22(rs.getInt(8));
				lp.setLiner23(rs.getInt(9));
				lp.setLiner24(rs.getInt(10));
				lp.setLiner31(rs.getInt(11));
				lp.setLiner32(rs.getInt(12));
				lp.setLiner33(rs.getInt(13));
				lp.setLiner34(rs.getInt(14));
				lp.setLiner41(rs.getInt(15));
				lp.setLiner42(rs.getInt(16));
				lp.setLiner43(rs.getInt(17));
				lp.setLiner44(rs.getInt(18));
				lp.setLiner45(rs.getInt(19));
				lp.setLiner46(rs.getInt(20));

			}
			rs.close();
			ps.close();
			return lp;
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
	public LinerProductionModel linerProductionReturnEntryByID(int id) {

		String sql = "SELECT * FROM MainLinerProduction WHERE MainLinerProduction.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			lp = new LinerProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lp.setID(rs.getInt(1));
				lp.setDate(rs.getDate(2));

				lp.setLiner11(rs.getInt(3));
				lp.setLiner12(rs.getInt(4));
				lp.setLiner13(rs.getInt(5));
				lp.setLiner14(rs.getInt(6));
				lp.setLiner21(rs.getInt(7));
				lp.setLiner22(rs.getInt(8));
				lp.setLiner23(rs.getInt(9));
				lp.setLiner24(rs.getInt(10));
				lp.setLiner31(rs.getInt(11));
				lp.setLiner32(rs.getInt(12));
				lp.setLiner33(rs.getInt(13));
				lp.setLiner34(rs.getInt(14));
				lp.setLiner41(rs.getInt(15));
				lp.setLiner42(rs.getInt(16));
				lp.setLiner43(rs.getInt(17));
				lp.setLiner44(rs.getInt(18));
				lp.setLiner45(rs.getInt(19));
				lp.setLiner46(rs.getInt(20));

			}
			rs.close();
			ps.close();
			return lp;
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
	public boolean linerProductionEntryExists(LinerProductionModel lp) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(lp.getDate()));

		String sql = "SELECT * " + "FROM MainLinerProduction " + "WHERE MainLinerProduction.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				System.out.println("Entry exists.");
				return true;

			} else {

				System.out.println("Entry does not exist.");
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
	public void linerProductionInsert(LinerProductionModel lp) {
		String sql = ("insert into MainLinerProduction values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lpInsert = conn.prepareStatement(sql);

			lpInsert.setNull(1, java.sql.Types.INTEGER);
			lpInsert.setDate(2, convertToSqlDate(lp.getDate()));

			lpInsert.setInt(3, lp.getLiner11());
			lpInsert.setInt(4, lp.getLiner12());
			lpInsert.setInt(5, lp.getLiner13());
			lpInsert.setInt(6, lp.getLiner14());
			lpInsert.setInt(7, lp.getLiner21());
			lpInsert.setInt(8, lp.getLiner22());
			lpInsert.setInt(9, lp.getLiner23());
			lpInsert.setInt(10, lp.getLiner24());
			lpInsert.setInt(11, lp.getLiner31());
			lpInsert.setInt(12, lp.getLiner32());
			lpInsert.setInt(13, lp.getLiner33());
			lpInsert.setInt(14, lp.getLiner34());
			lpInsert.setInt(15, lp.getLiner41());
			lpInsert.setInt(16, lp.getLiner42());
			lpInsert.setInt(17, lp.getLiner43());
			lpInsert.setInt(18, lp.getLiner44());
			lpInsert.setInt(19, lp.getLiner45());
			lpInsert.setInt(20, lp.getLiner46());

			lpInsert.setString(21, getCurrentTimeStamp());

			lpInsert.executeUpdate();
			lpInsert.close();

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
	public void linerProductionUpdate(LinerProductionModel lp) {

		Date dateIn = lp.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "update MainLinerProduction set Liner11=? , Liner12=? , Liner13=? , Liner14=?, " + "Liner21=? , "
				+ "Liner22=?,  Liner23=?, Liner24=?, Liner31=?, Liner32=?, Liner33=?, Liner34=?, "
				+ "Liner41=?, Liner42=?, Liner43=?, Liner44=?, Liner45=?, Liner46=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lpUpdate = conn.prepareStatement(sql);

			lpUpdate.setInt(3, lp.getLiner11());
			lpUpdate.setInt(4, lp.getLiner12());
			lpUpdate.setInt(5, lp.getLiner13());
			lpUpdate.setInt(6, lp.getLiner14());
			lpUpdate.setInt(7, lp.getLiner21());
			lpUpdate.setInt(8, lp.getLiner22());
			lpUpdate.setInt(9, lp.getLiner23());
			lpUpdate.setInt(10, lp.getLiner24());
			lpUpdate.setInt(11, lp.getLiner31());
			lpUpdate.setInt(12, lp.getLiner32());
			lpUpdate.setInt(13, lp.getLiner33());
			lpUpdate.setInt(14, lp.getLiner34());
			lpUpdate.setInt(15, lp.getLiner41());
			lpUpdate.setInt(16, lp.getLiner42());
			lpUpdate.setInt(17, lp.getLiner43());
			lpUpdate.setInt(18, lp.getLiner44());
			lpUpdate.setInt(19, lp.getLiner45());
			lpUpdate.setInt(20, lp.getLiner46());

			lpUpdate.setDate(21, convertToSqlDate(lp.getDate()));

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
	public void linerProductionUpdateByDate(LinerProductionModel lp) {

		Date dateIn = lp.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "update MainLinerProduction set Liner11=? , Liner12=? , Liner13=? , Liner14=?, " + "Liner21=? , "
				+ "Liner22=?,  Liner23=?, Liner24=?, Liner31=?, Liner32=?, Liner33=?, Liner34=?, "
				+ "Liner41=?, Liner42=?, Liner43=?, Liner44=?, Liner45=?, Liner46=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lpUpdate = conn.prepareStatement(sql);

			lpUpdate.setInt(1, lp.getLiner11());
			lpUpdate.setInt(2, lp.getLiner12());
			lpUpdate.setInt(3, lp.getLiner13());
			lpUpdate.setInt(4, lp.getLiner14());
			lpUpdate.setInt(5, lp.getLiner21());
			lpUpdate.setInt(6, lp.getLiner22());
			lpUpdate.setInt(7, lp.getLiner23());
			lpUpdate.setInt(8, lp.getLiner24());
			lpUpdate.setInt(9, lp.getLiner31());
			lpUpdate.setInt(10, lp.getLiner32());
			lpUpdate.setInt(11, lp.getLiner33());
			lpUpdate.setInt(12, lp.getLiner34());
			lpUpdate.setInt(13, lp.getLiner41());
			lpUpdate.setInt(14, lp.getLiner42());
			lpUpdate.setInt(15, lp.getLiner43());
			lpUpdate.setInt(16, lp.getLiner44());
			lpUpdate.setInt(17, lp.getLiner45());
			lpUpdate.setInt(18, lp.getLiner46());

			lpUpdate.setDate(19, convertToSqlDate(lp.getDate()));

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
	public int[] linerProductionCalculateTotalsByMonth(String monthIn, String yearIn) {


		System.out.println("MonthIn " + monthIn);
		System.out.println("YearIn " + yearIn);

	//	ShellPressProductionModel sp = new ShellPressProductionModel();

		// Convert Input into Date Range
		String month = "";
		String year = yearIn;

		if (monthIn.equals("January")) {
			month = "01";
		} else if (monthIn.equals("February")) {
			month = "02";
		} else if (monthIn.equals("March")) {
			month = "03";
		} else if (monthIn.equals("April")) {
			month = "04";
		} else if (monthIn.equals("May")) {
			month = "05";
		} else if (monthIn.equals("June")) {
			month = "06";
		} else if (monthIn.equals("July")) {
			month = "07";
		} else if (monthIn.equals("August")) {
			month = "08";
		} else if (monthIn.equals("September")) {
			month = "09";
		} else if (monthIn.equals("October")) {
			month = "10";
		} else if (monthIn.equals("November")) {
			month = "11";
		} else if (monthIn.equals("December")) {
			month = "12";
		}

		String date = (year + "-" + month);

		System.out.println("Date : " + date);

		int[] sums = new int[40];

//		    String sql1 = "SELECT SUM(Mod123Unlined) FROM MainLineBalance WHERE Date LIKE '%" + date + "%';";
//	        String sql2 = "SELECT SUM(Mod4Unlined) FROM MainLineBalance WHERE Date LIKE '%" + date + "%';";
//	        String sql3 = "SELECT SUM(Mod123Lined) FROM MainLineBalance WHERE Date LIKE '%" + date + "%';";
//	        String sql4 = "SELECT SUM(Mod4Lined) FROM MainLineBalance WHERE Date LIKE '%" + date + "%';";
//
//	        String sql5 = "SELECT SUM(SP01) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql6 = "SELECT SUM(SP02) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql7 = "SELECT SUM(SP03) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql8 = "SELECT SUM(FMI41) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql9 = "SELECT SUM(FMI42) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql10 = "SELECT SUM(SP04) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";

	        String sql11 = "SELECT SUM(Liner11) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql12 = "SELECT SUM(Liner12) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql13 = "SELECT SUM(Liner21) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql14 = "SELECT SUM(Liner22) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql15 = "SELECT SUM(Liner23) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql16 = "SELECT SUM(Liner24) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql17 = "SELECT SUM(Liner31) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql18 = "SELECT SUM(Liner32) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql19 = "SELECT SUM(Liner33) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql20 = "SELECT SUM(Liner34) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql21 = "SELECT SUM(Liner41) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql22 = "SELECT SUM(Liner42) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql23 = "SELECT SUM(Liner43) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";
	        String sql24 = "SELECT SUM(Liner44) FROM MainLinerProduction WHERE Date LIKE '%" + date + "%';";

//	        String sql25 = "SELECT SUM(Stolle11) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql26 = "SELECT SUM(Stolle12) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        
//	        String sql27 = "SELECT SUM(Stolle21) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql28 = "SELECT SUM(Stolle22) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql29 = "SELECT SUM(Stolle23) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql30 = "SELECT SUM(Stolle24) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        
//	        String sql31 = "SELECT SUM(Stolle31) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql32 = "SELECT SUM(Stolle32) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql33 = "SELECT SUM(Stolle33) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//            String sql34 = "SELECT SUM(Stolle34) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//            
//	        String sql35 = "SELECT SUM(Stolle41) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql36 = "SELECT SUM(Stolle42) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql37 = "SELECT SUM(Stolle43) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";
//	        String sql38 = "SELECT SUM(Stolle44) FROM MainStolleProduction WHERE Date LIKE '%" + date + "%';";

		//////////////////////////////////////////////////
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(sql11);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			sums[0] = rs.getInt(1);

		}
		rs.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql12);

		ResultSet rs1 = ps.executeQuery();

		if (rs1.next()) {

			sums[1] = rs1.getInt(1);

		}
		rs1.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql13);

		ResultSet rs2 = ps.executeQuery();

		if (rs2.next()) {

			sums[2] = rs2.getInt(1);

		}
		rs2.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql14);

		ResultSet rs3 = ps.executeQuery();

		if (rs3.next()) {

			sums[3] = rs3.getInt(1);

		}
		rs3.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql15);

		ResultSet rs4 = ps.executeQuery();

		if (rs4.next()) {

			sums[4] = rs4.getInt(1);

		}
		rs4.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql16);

		ResultSet rs5 = ps.executeQuery();

		if (rs5.next()) {

			sums[5] = rs5.getInt(1);

		}
		rs5.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql17);

		ResultSet rs6 = ps.executeQuery();

		if (rs6.next()) {

			sums[6] = rs6.getInt(1);

		}
		rs6.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql18);

		ResultSet rs7 = ps.executeQuery();

		if (rs7.next()) {

			sums[7] = rs7.getInt(1);

		}
		rs7.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql19);

		ResultSet rs8 = ps.executeQuery();

		if (rs8.next()) {

			sums[5] = rs8.getInt(1);

		}
		rs8.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql20);

		ResultSet rs9 = ps.executeQuery();

		if (rs9.next()) {

			sums[5] = rs9.getInt(1);

		}
		rs9.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql21);

		ResultSet rs10 = ps.executeQuery();

		if (rs10.next()) {

			sums[10] = rs10.getInt(1);

		}
		rs10.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql22);

		ResultSet rs11 = ps.executeQuery();

		if (rs11.next()) {

			sums[11] = rs11.getInt(1);

		}
		rs11.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql23);

		ResultSet rs12 = ps.executeQuery();

		if (rs12.next()) {

			sums[12] = rs12.getInt(1);

		}
		rs12.close();
		ps.close();
		//////////////////////////////////////////////////
		ps = conn.prepareStatement(sql24);

		ResultSet rs13 = ps.executeQuery();

		if (rs13.next()) {

			sums[13] = rs13.getInt(1);

		}
		rs13.close();
		ps.close();
		//////////////////////////////////////////////////
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return sums;
		
	}

	@Override
	public JPanel linerProductionSummaryTable(int in) {
		
		
		
		
		return new JPanel();
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

	private String getCurrentTimeStamp() {
		// TimeStamp in String Format
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String timeStamp = format.format(date);
		return timeStamp;
	}

}
