package com.rexam.maintenance.controller;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.maintenance.model.BalancerMaintenanceModel;
import com.rexam.maintenance.model.ShellPressMaintenanceModel;
import com.rexam.maintenance.model.ShellPressProductionModel;
import com.rexam.maintenance.model.TransferBeltModel;
import com.rexam.maintenance.view.BalancerProductionView;
import com.rexam.maintenance.view.ShellPressMaintenanceView;
import com.rexam.maintenance.view.ShellPressProductionView;

public class MaintenanceDatabaseController {

	public MaintenanceDatabaseController() {

		// shellPressProductionReturnEntryByDate(new Date());

	}

	public static Connection databaseConnect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rexam", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Opened Database Connection Successfully.");

		return conn;

	}

	/// Shell Press Production  ////////////////////////////////////
	
	public int shellPressProductionGetHighestID() throws SQLException {

		int highestID = 0;

		// Connect
		Connection conn = databaseConnect();
		Statement s = conn.createStatement();

		// Query
		String selTable = "SELECT MAX(ID) FROM MainShellPressProduction;";
		s.setQueryTimeout(10);
		s.execute(selTable);

		// Result
		ResultSet rs = s.getResultSet();
		while ((rs != null) && (rs.next())) {
			highestID = rs.getInt(1);

		}

		// Close
		rs.close();
		s.close();
		conn.close();
		return highestID;

	}

	public ShellPressProductionModel shellPressProductionReturnEntryByDate(Date dateIn) throws Exception {

		ShellPressProductionModel pm = new ShellPressProductionModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn));

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////

		String selTable = "SELECT * " + "FROM MainShellPressProduction " + "WHERE MainShellPressProduction.Date = \""
				+ df + "\";";

		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

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
		s.close();
		conn.close();

		// //////////////////////////////////////////////////////////////////////
		return pm;

	}

	public ShellPressProductionModel shellPressProductionReturnEntryByID(int id) throws Exception {

		ShellPressProductionModel pm = new ShellPressProductionModel();

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////
		String selTable = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.ID = \"" + id + "\";";
		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

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
		s.close();
		conn.close();

		// //////////////////////////////////////////////////////////////////////
		return pm;

	}

	public boolean shellPressProductionEntryExists(Date dateIn) throws SQLException {

		int count = 0;

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////
		String selTable = "SELECT MainShellPressProduction.Date FROM MainShellPressProduction WHERE MainShellPressProduction.Date = \'"
				+ convertToSqlDate(dateIn) + "\';";
		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

			count++;

		}

		rs.close();
		s.close();
		conn.close();

		// //////////////////////////////////////////////////////////////////////
		if (count > 0) {

			System.out.println("Production entry exists");
			return true;

		} else {

			System.out.println("Production entry does not exist.");
			return false;
		}

	}

	public void shellPressProductionInsert(ShellPressProductionModel pm) throws SQLException {

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);

		System.out.println("PM GetDate : " + pm.getDate());

		// QUERY
		// //////////////////////////////////////////////////////////////////////
		PreparedStatement ShellPressProductionInsert = conn
				.prepareStatement("insert into MainShellPressProduction values(?,?,?,?,?,?,?,?,?)");

		ShellPressProductionInsert.setInt(1, shellPressProductionGetHighestID() + 1);
		ShellPressProductionInsert.setDate(2, convertToSqlDate(pm.getDate()));
		ShellPressProductionInsert.setInt(3, pm.getSp01());
		ShellPressProductionInsert.setInt(4, pm.getOptime2());
		ShellPressProductionInsert.setInt(5, pm.getOptime3());
		ShellPressProductionInsert.setInt(6, pm.getFmi41());
		ShellPressProductionInsert.setInt(7, pm.getFmi42());
		ShellPressProductionInsert.setInt(8, pm.getFormatec04());
		ShellPressProductionInsert.setString(9, getCurrentTimeStamp());

		ShellPressProductionInsert.executeUpdate();

		// /////////////////////////////////////////////////////////////////////////////
		s.close();
		conn.close();

	}

	public void shellPressProductionUpdate(ShellPressProductionModel pm) throws SQLException {

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);

		// QUERY
		// //////////////////////////////////////////////////////////////////////
		String sql = "update MainShellPressProduction set date=?, SP01=? , SP02=? , SP03=? , FMI41=?, FMI42=? , "
				+ "SP04=? where ID=?";

		PreparedStatement ShellPressProductionUpdate = conn.prepareStatement(sql);

		ShellPressProductionUpdate.setDate(1, convertToSqlDate(pm.getDate()));
		ShellPressProductionUpdate.setInt(2, pm.getSp01());
		ShellPressProductionUpdate.setInt(3, pm.getOptime2());
		ShellPressProductionUpdate.setInt(4, pm.getOptime3());
		ShellPressProductionUpdate.setInt(5, pm.getFmi41());
		ShellPressProductionUpdate.setInt(6, pm.getFmi42());
		ShellPressProductionUpdate.setInt(7, pm.getFormatec04());
		ShellPressProductionUpdate.setInt(8, pm.getId());

		ShellPressProductionUpdate.executeUpdate();

		// /////////////////////////////////////////////////////////////////////////////
		s.close();
		conn.close();

	}

	public void shellPressProductionUpdateByDate(ShellPressProductionModel pm) throws SQLException {

		Connection conn = databaseConnect();
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);

		// QUERY
		// //////////////////////////////////////////////////////////////////////
		String sql = "UPDATE MainShellPressProduction SET SP01=? , SP02=? , SP03=? , FMI41=?, FMI42=? , SP04=? WHERE MainShellPressProduction.Date=?";

		System.out.println("DateIn : " + pm.getDate());

		PreparedStatement ShellPressProductionUpdate = conn.prepareStatement(sql);

		ShellPressProductionUpdate.setInt(1, pm.getSp01());
		ShellPressProductionUpdate.setInt(2, pm.getOptime2());
		ShellPressProductionUpdate.setInt(3, pm.getOptime3());
		ShellPressProductionUpdate.setInt(4, pm.getFmi41());
		ShellPressProductionUpdate.setInt(5, pm.getFmi42());
		ShellPressProductionUpdate.setInt(6, pm.getFormatec04());
		ShellPressProductionUpdate.setDate(7, convertToSqlDate(pm.getDate()));

		ShellPressProductionUpdate.executeUpdate();

		// /////////////////////////////////////////////////////////////////////////////
		s.close();
		conn.close();

	}

	public JTable generateExcelJTable(String type, String query) throws SQLException {

		JTable table = new JTable();
		table.setAutoCreateRowSorter(true);

		System.out.println("Query : " + query);
		System.out.println("Type : " + type);

		Vector data = new Vector();

		Connection conn = databaseConnect();
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

		return table;

	}

	public ShellPressProductionModel shellPressProductionCalculateTotalsByMonth(String monthIn, String yearIn)
			throws SQLException {

		System.out.println("MonthIn " + monthIn);
		System.out.println("YearIn " + yearIn);

		ShellPressProductionModel sp = new ShellPressProductionModel();

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

		// Return the sum of the selected month and Line - SQL Query SUM WHERE
		// Month Contains /06/
		// Query ////////
		Connection conn = databaseConnect();
		Statement s = conn.createStatement();

		String sql1 = "SELECT SUM(SP01) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql2 = "SELECT SUM(SP02) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql3 = "SELECT SUM(SP03) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql4 = "SELECT SUM(FMI41) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql5 = "SELECT SUM(FMI42) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";
		String sql6 = "SELECT SUM(SP04) FROM MainShellPressProduction WHERE Date LIKE '%" + date + "%';";

		s.setQueryTimeout(5);

		// W11 /////////////////
		s.execute(sql1);

		ResultSet rs1 = s.getResultSet();
		while ((rs1 != null) && (rs1.next())) {
			sp.setSp01(rs1.getInt(1));
		}

		// ///////////////////////////
		// W12 //////////////////
		s.execute(sql2);

		ResultSet rs2 = s.getResultSet();
		while ((rs2 != null) && (rs2.next())) {
			sp.setOptime2(rs2.getInt(1));
		}

		// ///////////////////////////
		// W21 /////////////////
		s.execute(sql3);

		ResultSet rs3 = s.getResultSet();
		while ((rs3 != null) && (rs3.next())) {
			sp.setOptime3(rs3.getInt(1));
		}

		// ///////////////////////////
		// W22 /////////////////
		s.execute(sql4);

		ResultSet rs4 = s.getResultSet();
		while ((rs4 != null) && (rs4.next())) {
			sp.setFmi41(rs4.getInt(1));
		}

		// ///////////////////////////
		// W32 /////////////////
		s.execute(sql5);

		ResultSet rs5 = s.getResultSet();
		while ((rs5 != null) && (rs5.next())) {
			sp.setFmi42(rs5.getInt(1));
		}

		// ///////////////////////////
		// W32 /////////////////
		s.execute(sql6);

		ResultSet rs6 = s.getResultSet();
		while ((rs6 != null) && (rs6.next())) {
			sp.setFormatec04(rs6.getInt(1));
		}

		// ///////////////////////////
		// ///////////////////////////
		rs1.close();
		rs2.close();
		rs3.close();
		rs4.close();
		rs5.close();
		rs6.close();

		s.close();
		conn.close();

		// ///////////////
		return sp;
	}

	/// Maintenance ////////////////////////////////////
	
	public void shellPressMaintenanceUpdate(ShellPressMaintenanceModel sm) {

		Connection conn = databaseConnect();
		Statement s;
		try {
			s = conn.createStatement();

			s.setQueryTimeout(10);

			System.out.println("PM GetDate : " + sm.getMaintenanceDueDate1());

			// QUERY
			// //////////////////////////////////////////////////////////////////////
			PreparedStatement mainUpdate = conn.prepareStatement(
					"" + "UPDATE MainShellPressMaintenance " + "SET MachineCode = ?, " + "MachineName = ?, "
							+ "LastMaintanenceDate1 = ? " + "LastMaintanenceDate2 = ? " + "LastMaintanenceDate3 = ? "
							+ "LastMaintanenceDate4 = ? " + "LastMaintanenceDate5 = ? " + "LastMaintanenceDate6 = ? "
							+ "LastMaintanenceDate7 = ? " + "MaintanenceDueDate1 = ? " + "MaintanenceDueDate2 = ? "
							+ "MaintanenceDueDate3 = ? " + "MaintanenceDueDate4 = ? " + "MaintanenceDueDate5 = ? "
							+ "MaintanenceDueDate6 = ? " + "production1 = ? " + "production2 = ? " + "production3 = ? "
							+ "production4 = ? " + "production5 = ? " + "production6 = ? " + "production7 = ? "
							+ "targetProduction1 = ? " + "targetProduction2 = ? " + "targetProduction3 = ? "
							+ "targetProduction4 = ? " + "targetProduction5 = ? " + "targetProduction6 = ? "
							+ "targetProduction7 = ? " + " WHERE ID = ?");

			mainUpdate.setString(1, sm.getMachineCode());
			mainUpdate.setString(2, sm.getMachineName());
			mainUpdate.setDate(3, convertToSqlDate(sm.getLastMaintenanceDate1()));
			mainUpdate.setDate(4, convertToSqlDate(sm.getLastMaintenanceDate2()));
			mainUpdate.setDate(5, convertToSqlDate(sm.getLastMaintenanceDate3()));
			mainUpdate.setDate(6, convertToSqlDate(sm.getLastMaintenanceDate4()));
			mainUpdate.setDate(7, convertToSqlDate(sm.getLastMaintenanceDate5()));
			mainUpdate.setDate(8, convertToSqlDate(sm.getLastMaintenanceDate6()));
			mainUpdate.setDate(9, convertToSqlDate(sm.getLastMaintenanceDate7()));
			mainUpdate.setDate(10, convertToSqlDate(sm.getMaintenanceDueDate1()));
			mainUpdate.setDate(11, convertToSqlDate(sm.getMaintenanceDueDate2()));
			mainUpdate.setDate(12, convertToSqlDate(sm.getMaintenanceDueDate3()));
			mainUpdate.setDate(13, convertToSqlDate(sm.getMaintenanceDueDate4()));
			mainUpdate.setDate(14, convertToSqlDate(sm.getMaintenanceDueDate5()));
			mainUpdate.setDate(15, convertToSqlDate(sm.getMaintenanceDueDate6()));
			mainUpdate.setDate(16, convertToSqlDate(sm.getMaintenanceDueDate7()));
			mainUpdate.setInt(17, sm.getProduction1());
			mainUpdate.setInt(18, sm.getProduction2());
			mainUpdate.setInt(19, sm.getProduction3());
			mainUpdate.setInt(20, sm.getProduction4());
			mainUpdate.setInt(21, sm.getProduction5());
			mainUpdate.setInt(22, sm.getProduction6());
			mainUpdate.setInt(23, sm.getProduction7());
			mainUpdate.setInt(24, sm.getTargetProduction1());
			mainUpdate.setInt(25, sm.getTargetProduction2());
			mainUpdate.setInt(26, sm.getTargetProduction3());
			mainUpdate.setInt(27, sm.getTargetProduction4());
			mainUpdate.setInt(28, sm.getTargetProduction5());
			mainUpdate.setInt(29, sm.getTargetProduction6());
			mainUpdate.setInt(30, sm.getId());

			mainUpdate.executeUpdate();

			// /////////////////////////////////////////////////////////////////////////////
			s.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ShellPressMaintenanceModel shellPressMaintenanceReturnEntryByID(int idIn) {

		ShellPressMaintenanceModel sm = new ShellPressMaintenanceModel();

		Connection conn = databaseConnect();
		Statement s;
		try {
			s = conn.createStatement();
		

		// QUERY /////////////////////////////////////////////////////////////
		String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + idIn
				+ "\";";
		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

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
		s.close();
		conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sm;
		
		

	}

	/// Transfer Belt  ////////////////////////////////////	
	
	public TransferBeltModel transferBeltReturnEntryByID(int idIn){
		
		TransferBeltModel tm = new TransferBeltModel();
		
		Connection conn = databaseConnect();
		Statement s;
		try {
			s = conn.createStatement();
		

		// QUERY /////////////////////////////////////////////////////////////
		String selTable = "SELECT * FROM MainTransferBelt WHERE MainTransferBelt.ID = \"" + idIn + "\";";
		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

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
		s.close();
		conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tm;
	}

	public void transferBeltUpdate(TransferBeltModel tm){
		
		try {
		
		Connection conn = databaseConnect();
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);

		// QUERY
		// //////////////////////////////////////////////////////////////////////
		String sql = "update MainTransferBelt set DateFitted=? , EndsTargetted=? , "
				+ "ActualEnds=? , PlusOrMinus=?, SetUpCheckDate=?, BeltsRemoved1=?, BeltsRemoved2=?, "
				+ "BeltsRemoved3=?, BeltsRemoved4=?, BeltsRemoved5=?, BeltsRemoved6=?, BeltsRemoved7=?, "
				+ "BeltsRemoved8=?, BeltsRemoved9=?, BeltsRemoved10=?, BeltsRemoved11=?, BeltsRemoved12=? where MachineCode=?";

		PreparedStatement tbUpdate;
		
			tbUpdate = conn.prepareStatement(sql);
		

		
		tbUpdate.setDate(2, convertToSqlDate(tm.getDateFitted()));
		tbUpdate.setInt(3, tm.getEndsTargeted());
		tbUpdate.setInt(4, tm.getActualEnds());
		tbUpdate.setInt(5, tm.getPlusOrMinus());
		tbUpdate.setDate(6, convertToSqlDate(tm.getSetUpCheckDate()));
		tbUpdate.setDate(7, convertToSqlDate(tm.getBeltsRemoved1()));
		tbUpdate.setDate(8, convertToSqlDate(tm.getBeltsRemoved2()));
		tbUpdate.setDate(9, convertToSqlDate(tm.getBeltsRemoved3()));
		tbUpdate.setDate(10, convertToSqlDate(tm.getBeltsRemoved4()));
		tbUpdate.setDate(11, convertToSqlDate(tm.getBeltsRemoved5()));
		tbUpdate.setDate(12, convertToSqlDate(tm.getBeltsRemoved6()));
		tbUpdate.setDate(13, convertToSqlDate(tm.getBeltsRemoved7()));
		tbUpdate.setDate(14, convertToSqlDate(tm.getBeltsRemoved8()));
		tbUpdate.setDate(15, convertToSqlDate(tm.getBeltsRemoved9()));
		tbUpdate.setDate(16, convertToSqlDate(tm.getBeltsRemoved10()));
		tbUpdate.setDate(17, convertToSqlDate(tm.getBeltsRemoved11()));
		tbUpdate.setDate(18, convertToSqlDate(tm.getBeltsRemoved12()));
		tbUpdate.setString(1, tm.getMachineCode());
						
		tbUpdate.executeUpdate();

		// /////////////////////////////////////////////////////////////////////////////
		s.close();
		conn.close();	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public int MaintenanceStolleProductionCalculateTotalsForMachine(String dateIn, String machineIn) throws SQLException {

        // Convert Input into Date Range
        int total = 0;

        String maxDate = MaintenanceStolleProductionGetMaxDateForMachine(machineIn);

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = databaseConnect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(" + machineIn + ") FROM MainStolleProduction WHERE Date BETWEEN \'" + dateIn + "\' AND \'" + maxDate + "\';";

        System.out.println(sql1);

        s.setQueryTimeout(5);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            total = rs1.getInt(1);
        }

        // ///////////////////////////
        // ///////////////////////////
        rs1.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

	public static String MaintenanceStolleProductionGetMaxDateForMachine(String machineIn) throws SQLException {

        // Convert Input into Date Range
        String date = "";

        Connection conn = databaseConnect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT MAX(Date) FROM MainStolleProduction ;";

        s.setQueryTimeout(5);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            date = rs1.getString(1);
        }

        // ///////////////////////////
        // ///////////////////////////
        rs1.close();

        s.close();
        conn.close();

        // ///////////////
        return date;
    }
	
	/// Balancer  ///////////////////////////////////
	
	public JPanel MaintenanceBalancerProductionSummaryTable(int in) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = databaseConnect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement(""
                + " SELECT MainShellPressProduction.Date, "
                + "(MainShellPressProduction.SP02)/2 AS Balancer1A, "
                + "(MainShellPressProduction.SP02)/2 AS Balancer2A, "
                + "MainShellPressProduction.SP03 AS Balancer3A,"
                + " MainShellPressProduction.FMI41 AS Balancer4A,"
                + " MainStolleProduction.Stolle11 + MainStolleProduction.Stolle12 AS Balancer4A,"
                + " MainStolleProduction.Stolle21 + MainStolleProduction.Stolle22 AS Balancer4B,"
                + " MainStolleProduction.Stolle31 + MainStolleProduction.Stolle32 + MainStolleProduction.Stolle33 AS Balancer4C,"
                + " MainStolleProduction.Stolle41 + MainStolleProduction.Stolle42 + MainStolleProduction.Stolle43 + MainStolleProduction.Stolle44 AS Balancer4D"
                + " FROM MainShellPressProduction"
                + " INNER JOIN  MainStolleProduction"
                + " ON MainShellPressProduction.Date=MainStolleProduction.Date"
                + " ORDER BY MainShellPressProduction.Date DESC");
        psmt.setQueryTimeout(10);
        ResultSet rs = psmt.executeQuery();
        DefaultTableModel dm = new DefaultTableModel();

        // get column names
        int len = rs.getMetaData().getColumnCount();
        System.out.println("LEN : " + len);
        Vector cols = new Vector(len);
        for (int i = 1; i <= len; i++) {// Note starting at 1

            cols.add(rs.getMetaData().getColumnName(i));
            System.out.println(rs.getMetaData().getColumnName(i));

        }

        // Add Data
        Vector data = new Vector();

        while (rs.next()) {

            Vector row = new Vector(len);

            int q1 = rs.getInt(2);
            String q2 = String.format("%,d", q1);

            int q3 = rs.getInt(3);
            String q4 = String.format("%,d", q3);

            int q5 = rs.getInt(4);
            String q6 = String.format("%,d", q5);

            int q7 = rs.getInt(5);
            String q8 = String.format("%,d", q7);

            int q9 = rs.getInt(6);
            String q10 = String.format("%,d", q9);

            int q11 = rs.getInt(7);
            String q12 = String.format("%,d", q11);

            int q13 = rs.getInt(8);
            String q14 = String.format("%,d", q13);

            int q15 = rs.getInt(9);
            String q16 = String.format("%,d", q15);

            row.add(rs.getString(1));
            row.add(q2);
            row.add(q4);
            row.add(q6);
            row.add(q8);
            row.add(q10);
            row.add(q12);
            row.add(q14);
            row.add(q16);

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        final JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

//        table.getColumnModel().getColumn(10).setMaxWidth(40);
        // Render Checkbox
//        TableColumn tc = table.getColumnModel().getColumn(9);
//        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
					// int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(), 10).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 10).toString();
                    int id = Integer.valueOf(idString);
                    ShellPressProductionModel linersAndShells;
                    BalancerProductionView balancerProduction = new BalancerProductionView();
                //   BalancerProductionView.setBalancerProductionToID(id);

                }
            }
        });

        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }
	
	public BalancerMaintenanceModel balancerMaintenanceReturnEntryByID(int idIn) {

		BalancerMaintenanceModel bm = new BalancerMaintenanceModel();

		Connection conn = databaseConnect();
		Statement s;
		try {
			s = conn.createStatement();
		

		// QUERY /////////////////////////////////////////////////////////////
		String selTable = "SELECT * FROM MainBalancerMaintenance WHERE MainBalancerMaintenance.ID = \"" + idIn
				+ "\";";
		System.out.println(selTable);
		s.setQueryTimeout(10);
		s.execute(selTable);

		ResultSet rs = s.getResultSet();

		while ((rs != null) && (rs.next())) {

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
		s.close();
		conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bm;
		
		

	}
	
	public void balancerMaintenanceUpdate(BalancerMaintenanceModel sm) {

		Connection conn = databaseConnect();
		Statement s;
		try {
			s = conn.createStatement();

			s.setQueryTimeout(10);

			System.out.println("PM GetDate : " + sm.getMaintenanceDueDate1());

			// QUERY
			// //////////////////////////////////////////////////////////////////////
			PreparedStatement bmInsert = conn.prepareStatement(
					"" + "UPDATE MainBalancerMaintenance " + "SET MachineCode = ?, " + "MachineName = ?, "
							+ "LastMaintanenceDate1 = ? " + "LastMaintanenceDate2 = ? " + "LastMaintanenceDate3 = ? "
							+ "LastMaintanenceDate4 = ? " + "LastMaintanenceDate5 = ? " + "LastMaintanenceDate6 = ? "
							+ "LastMaintanenceDate7 = ? " + "MaintanenceDueDate1 = ? " + "MaintanenceDueDate2 = ? "
							+ "MaintanenceDueDate3 = ? " + "MaintanenceDueDate4 = ? " + "MaintanenceDueDate5 = ? "
							+ "MaintanenceDueDate6 = ? " + "production1 = ? " + "production2 = ? " + "production3 = ? "
							+ "production4 = ? " + "production5 = ? " + "production6 = ? " + "production7 = ? "
							+ "targetProduction1 = ? " + "targetProduction2 = ? " + "targetProduction3 = ? "
							+ "targetProduction4 = ? " + "targetProduction5 = ? " + "targetProduction6 = ? "
							+ "targetProduction7 = ? " + " WHERE ID = ?");

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

			// /////////////////////////////////////////////////////////////////////////////
			s.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	///////////////////////////////////////

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
