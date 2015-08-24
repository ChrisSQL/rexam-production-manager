package com.rexam.maintenance.dao.impl;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.sql.DataSource;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.rexam.maintenance.dao.ShellPressProductionDAO;
import com.rexam.maintenance.model.ShellPressProductionModel;

public class jdbcShellPressProductionDAO implements ShellPressProductionDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/// Shell Press Production ////////////////////////////////////

	public void shellPressProductionInsert(ShellPressProductionModel pm) {

		String sqlQuery = ("insert into MainShellPressProduction values(?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ShellPressProductionInsert = conn.prepareStatement(sqlQuery);
			ShellPressProductionInsert.setNull(1, java.sql.Types.INTEGER);
			ShellPressProductionInsert.setDate(2, convertToSqlDate(pm.getDate()));
			ShellPressProductionInsert.setInt(3, pm.getSp01());
			ShellPressProductionInsert.setInt(4, pm.getOptime2());
			ShellPressProductionInsert.setInt(5, pm.getOptime3());
			ShellPressProductionInsert.setInt(6, pm.getFmi41());
			ShellPressProductionInsert.setInt(7, pm.getFmi42());
			ShellPressProductionInsert.setInt(8, pm.getFormatec04());
			ShellPressProductionInsert.setString(9, getCurrentTimeStamp());
			ShellPressProductionInsert.executeUpdate();
			ShellPressProductionInsert.close();

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

	public int shellPressProductionGetHighestID() {

		int highestID = 0;
		//
		// // Connect
		// Connection conn = null;
		// conn = dataSource.getConnection();
		//
		// // Query
		// String query = "SELECT MAX(ID) FROM MainShellPressProduction;";
		//
		// highestID = getJdbcTemplate().queryForInt(query);
		//
		return highestID;

	}

	public ShellPressProductionModel shellPressProductionReturnEntryByDate(Date dateIn) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "SELECT * " + "FROM MainShellPressProduction " + "WHERE MainShellPressProduction.Date = \"" + df
				+ "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ShellPressProductionModel pm = new ShellPressProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setId(rs.getInt(1));
				pm.setDate(rs.getDate(2));
				pm.setSp01(rs.getInt(3));
				pm.setOptime2(rs.getInt(4));
				pm.setOptime3(rs.getInt(5));
				pm.setFmi41(rs.getInt(6));
				pm.setFmi42(rs.getInt(7));
				pm.setFormatec04(rs.getInt(8));

			}
			rs.close();
			ps.close();
			return pm;
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

	public ShellPressProductionModel shellPressProductionReturnEntryByID(int id) {

		String sql = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.ID = \"" + id + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ShellPressProductionModel pm = new ShellPressProductionModel();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setId(rs.getInt(1));
				pm.setDate(rs.getDate(2));
				pm.setSp01(rs.getInt(3));
				pm.setOptime2(rs.getInt(4));
				pm.setOptime3(rs.getInt(5));
				pm.setFmi41(rs.getInt(6));
				pm.setFmi42(rs.getInt(7));
				pm.setFormatec04(rs.getInt(8));

			}
			rs.close();
			ps.close();
			return pm;
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

	public boolean shellPressProductionEntryExists(Date dateIn) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sql = "SELECT * " + "FROM MainShellPressProduction " + "WHERE MainShellPressProduction.Date = \"" + df
				+ "\";";

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

	public void shellPressProductionUpdate(ShellPressProductionModel pm) {

		Date dateIn = pm.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sqlQuery = "update MainShellPressProduction set SP01=? , SP02=? , SP03=? , FMI41=?, FMI42=? , SP04=? WHERE MainShellPressProduction.Date = \""
				+ df + "\";";

		System.out.println("Query: " + pm.getId());
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ShellPressProductionInsert = conn.prepareStatement(sqlQuery);

			ShellPressProductionInsert.setInt(1, pm.getSp01());
			ShellPressProductionInsert.setInt(2, pm.getOptime2());
			ShellPressProductionInsert.setInt(3, pm.getOptime3());
			ShellPressProductionInsert.setInt(4, pm.getFmi41());
			ShellPressProductionInsert.setInt(5, pm.getFmi42());
			ShellPressProductionInsert.setInt(6, pm.getFormatec04());
			ShellPressProductionInsert.executeUpdate();
			ShellPressProductionInsert.close();

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

	public void shellPressProductionUpdateByDate(ShellPressProductionModel pm) {

		Date dateIn = pm.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		String sqlQuery = "update MainShellPressProduction set SP01=? , SP02=? , SP03=? , FMI41=?, FMI42=? , SP04=? WHERE MainShellPressProduction.Date = \""
				+ df + "\";";

		System.out.println("Query: " + pm.getId());
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ShellPressProductionInsert = conn.prepareStatement(sqlQuery);

			ShellPressProductionInsert.setInt(1, pm.getSp01());
			ShellPressProductionInsert.setInt(2, pm.getOptime2());
			ShellPressProductionInsert.setInt(3, pm.getOptime3());
			ShellPressProductionInsert.setInt(4, pm.getFmi41());
			ShellPressProductionInsert.setInt(5, pm.getFmi42());
			ShellPressProductionInsert.setInt(6, pm.getFormatec04());
			ShellPressProductionInsert.executeUpdate();
			ShellPressProductionInsert.close();

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

	public JTable generateExcelJTable(String type, String query) {

		JTable table = new JTable();
		Connection conn = null;
		table.setAutoCreateRowSorter(true);

		System.out.println("Query : " + query);
		System.out.println("Type : " + type);

		Vector data = new Vector();

		try {
			conn = dataSource.getConnection();

			Statement stmt;
			stmt = conn.createStatement();
			stmt.setQueryTimeout(10);
			PreparedStatement psmt = conn.prepareStatement(query);
			psmt.setQueryTimeout(10);

			// get column names
			DefaultTableModel dm = new DefaultTableModel();
			ResultSet rs = psmt.executeQuery();
			int len = rs.getMetaData().getColumnCount();
			System.out.println("LEN : " + len);
			Vector cols = new Vector(len);
			for (int i = 1; i <= len; i++) {// Note starting at 1

				cols.add(rs.getMetaData().getColumnName(i));
				System.out.println(rs.getMetaData().getColumnName(i));
			}

			while (rs.next()) {

				Vector row = new Vector(len);

				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				row.add(rs.getString(7));
				row.add(rs.getString(8));
				row.add(rs.getString(9));

				data.add(row);
			}

			table = new JTable(data, cols);

			// Now create the table
			DefaultTableModel model = new DefaultTableModel(data, cols);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return table;

	}

	public int[] shellPressProductionCalculateTotalsByMonth(String monthIn, String yearIn) {

		System.out.println("MonthIn " + monthIn);
		System.out.println("YearIn " + yearIn);

		// ShellPressProductionModel sp = new ShellPressProductionModel();

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

		int[] sums = new int[6];

		String sql1 = "SELECT SUM(SP01) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql2 = "SELECT SUM(SP02) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql3 = "SELECT SUM(SP03) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql4 = "SELECT SUM(FMI41) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql5 = "SELECT SUM(FMI42) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql6 = "SELECT SUM(SP04) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";

		//////////////////////////////////////////////////
		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql1);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				sums[0] = rs.getInt(1);

			}
			rs.close();
			ps.close();
			//////////////////////////////////////////////////
			ps = conn.prepareStatement(sql2);

			ResultSet rs1 = ps.executeQuery();

			if (rs1.next()) {

				sums[1] = rs1.getInt(1);

			}
			rs1.close();
			ps.close();
			//////////////////////////////////////////////////
			ps = conn.prepareStatement(sql3);

			ResultSet rs2 = ps.executeQuery();

			if (rs2.next()) {

				sums[2] = rs2.getInt(1);

			}
			rs2.close();
			ps.close();
			//////////////////////////////////////////////////
			ps = conn.prepareStatement(sql4);

			ResultSet rs3 = ps.executeQuery();

			if (rs3.next()) {

				sums[3] = rs3.getInt(1);

			}
			rs3.close();
			ps.close();
			//////////////////////////////////////////////////
			ps = conn.prepareStatement(sql5);

			ResultSet rs4 = ps.executeQuery();

			if (rs4.next()) {

				sums[4] = rs4.getInt(1);

			}
			rs4.close();
			ps.close();
			//////////////////////////////////////////////////
			ps = conn.prepareStatement(sql6);

			ResultSet rs5 = ps.executeQuery();

			if (rs5.next()) {

				sums[5] = rs5.getInt(1);

			}
			rs5.close();
			ps.close();
			//////////////////////////////////////////////////

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sums;

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

	public void generateExcelFile(String type, String query) {

		Connection conn = null;

		// Create A JFrame to Query
		JTable table = new JTable();

		table.setAutoCreateRowSorter(true);
		try {
			conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		stmt.setQueryTimeout(10);
		PreparedStatement psmt = conn.prepareStatement(query);

		psmt.setQueryTimeout(10);
		ResultSet rs = psmt.executeQuery();
		DefaultTableModel dm = new DefaultTableModel();

		// get column names
		int len = rs.getMetaData().getColumnCount();
		System.out.println("LEN : " + len);
		Vector cols = new Vector(len);
		for (int i = 1; i <= len; i++) {// Note starting at 1

			cols.add(rs.getMetaData().getColumnName(i));

		}

		Vector data = new Vector();

		System.out.print(type);

		while (rs.next()) {
			Vector row = new Vector(len);

			row.add(rs.getString(1));
			row.add(rs.getString(2));
			row.add(rs.getString(3));
			row.add(rs.getString(4));
			row.add(rs.getString(5));
			row.add(rs.getString(6));
			row.add(rs.getString(7));
			row.add(rs.getString(8));
		//	row.add(rs.getString(9));

			data.add(row);
		}

		table = new JTable(data, cols);

		// Create JTable with Query
		// Export to Excel File
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet1 = workBook.createSheet("Main");
		Row row = sheet1.createRow(1);
		TableModel model2 = table.getModel(); // Table model

		HSSFFont font = workBook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = workBook.createCellStyle();
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);

		HSSFFont font2 = workBook.createFont();
		HSSFCellStyle style2 = workBook.createCellStyle();
		style2.setFont(font2);
		style2.setAlignment(CellStyle.ALIGN_CENTER);

		sheet1.setDefaultColumnWidth(14);

		Row headerRow = sheet1.createRow(0); // Create row at line 0
		for (int headings = 0; headings < model2.getColumnCount(); headings++) { // For
																					// each
																					// column
			headerRow.createCell(headings).setCellValue(model2.getColumnName(headings));// Write
																						// column
																						// name
			headerRow.getCell(headings).setCellStyle(style);

		}

		for (int rows = 0; rows < model2.getRowCount(); rows++) { // For each
																	// table row
			for (int cols1 = 0; cols1 < table.getColumnCount(); cols1++) {
				row.createCell(cols1).setCellValue(model2.getValueAt(rows, cols1).toString()); 
				row.getCell(cols1).setCellStyle(style2);

			}

			// Set the row to the next one in the sequence
			row = sheet1.createRow((rows + 2));
		}

		try {
			FileOutputStream output = new FileOutputStream("excel/ShellPressProduction.xls");
			workBook.write(output);

			Desktop dt = Desktop.getDesktop();
			dt.open(new File("excel/ShellPressProduction.xls"));

			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
