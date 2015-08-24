package com.rexam.binentry.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.rexam.binentry.dao.ProductionWeeklyReportDAO;
import com.rexam.binentry.model.ProductionWeeklyReportModel;

public class jdbcProductionWeeklyReportDAO implements ProductionWeeklyReportDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int ProductionWeeklyReportTotalOptime2And3ForMonth(String monthIn, String yearIn) {

		int total = 0;

		String date = convertDateToString(monthIn, yearIn);

		String sql = "SELECT SUM(Optime2) FROM LinersAndShells WHERE Date LIKE '%" + date + "%';";
		String sql2 = "SELECT SUM(Optime3) FROM LinersAndShells WHERE Date LIKE '%" + date + "%';";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

			  total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

		return total;
	}

	@Override
	public int ProductionWeeklyReportTotalOptime4ForMonth(String monthIn, String yearIn) {
		int total = 0;

		String date = convertDateToString(monthIn, yearIn);

		String sql = "SELECT SUM(Optime4) FROM LinersAndShells WHERE Date LIKE '%" + date + "%';";
		

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

		

		return total;
	}

	@Override
	public int ProductionWeeklyReportTotalOptime2And3And4ForMonth(String monthIn, String yearIn) {
		
		int total = 0;

        total = total + ProductionWeeklyReportTotalOptime2And3ForMonth(monthIn, yearIn);
		total = total + ProductionWeeklyReportTotalOptime4ForMonth(monthIn, yearIn);

        System.out.println("Total : " + total);
        return total;
	}

	@Override
	public int ProductionWeeklyReportTotalAllW1And2And3sForMonth(String monthIn, String yearIn) {
		
		String date = convertDateToString(monthIn, yearIn);

        int total = 0;
        

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W21) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W22) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(W32) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(W32) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(W33) FROM EndCounts WHERE Date LIKE '%" + date + "%';";

       

        // W11 /////////////////
        Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W12 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W21 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql3);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W22 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql4);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W31 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql5);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W32 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql6);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W33 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql7);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

		
		return total;
	}

	@Override
	public int ProductionWeeklyReportTotalAllW4sForMonth(String monthIn, String yearIn) {
		String date = convertDateToString(monthIn, yearIn);

        int total = 0;
        

        String sql1 = "SELECT SUM(W41) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W42) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W43) FROM EndCounts WHERE Date LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W44) FROM EndCounts WHERE Date LIKE '%" + date + "%';";

       

        // W11 /////////////////
        Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W12 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W21 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql3);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        // W22 /////////////////
		conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql4);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				total = total + rs.getInt(1);

			}
			rs.close();
			ps.close();

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

        // ///////////////////////////
        

		
		return total;
	}

	@Override
	public int ProductionWeeklyReportTotalAllWs(String monthIn, String yearIn) {
		int total = 0;

        total = total + ProductionWeeklyReportTotalAllW1And2And3sForMonth(monthIn, yearIn);
		total = total + ProductionWeeklyReportTotalAllW4sForMonth(monthIn, yearIn);

        System.out.println("All Ws : " + total);
        return total;
	}

	@Override
	public int ProductionWeeklyReportB64LineSpoilage(String monthIn, String yearIn) {
		// TODO Auto-generated method stub
		return 546;
	}

	@Override
	public int ProductionWeeklyReportCdlLineSpoilage(String monthIn, String yearIn) {
		// TODO Auto-generated method stub
		return 764;
	}

	@Override
	public int ProductionWeeklyReportTotalLineSpoilage(String monthIn, String yearIn) {
		// TODO Auto-generated method stub
		return 934;
	}

	private String convertDateToString(String monthIn, String yearIn) {
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
		return date;
	}

	@Override
	public void ProductionWeeklyReportUpdate(ProductionWeeklyReportModel pwr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int ProductionWeeklyReportQuery(int type) {
		// TODO Auto-generated method stub
		return 0;
	}

}
