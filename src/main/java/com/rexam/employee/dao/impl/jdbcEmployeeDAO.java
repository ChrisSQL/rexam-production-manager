package com.rexam.employee.dao.impl;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.DataSource;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rexam.employee.dao.EmployeeDAO;
import com.rexam.employee.model.EmployeeModel;

public class jdbcEmployeeDAO implements EmployeeDAO {

	EmployeeModel em;
	EmployeeDAO employeeDAO;
	JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int EmployeeGetHighestID() {

		Connection conn = null;

		int highestID = 0;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT MAX(Employees.[ID]) FROM Employees;";
			// System.out.println(selTable);
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				highestID = rs.getInt(1);
				System.out.println("Highest ID :  " + highestID);

			}

			rs.close();
			s.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return highestID;
	}

	@Override
	public boolean EmployeeExists(EmployeeModel em) {

		Connection conn = null;
		boolean result = false;
		int count = 0;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT Employees.ID FROM Employees WHERE Employees.ID = " + em.getEmployeeId() + ";";
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				count++;
				System.out.println("Count++");

			}

			rs.close();
			s.close();
			conn.close();

			// //////////////////////////////////////////////////////////////////////
			if (count > 0) {

				System.out.println("True");
				return true;

			} else {

				System.out.println("True");
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public int EmployeeGetLowestID() {
		Connection conn = null;
		int highestID = 0;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT Min(Employees.[ID]) FROM Employees;";
			// System.out.println(selTable);
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				highestID = rs.getInt(1);
				System.out.println("Lowest ID :  " + highestID);

			}

			rs.close();
			s.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return highestID;
	}

	@Override
	public EmployeeModel EmployeeReturnEntryByName(String nameIn) {

		em = new EmployeeModel();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT * FROM Employees WHERE Employees.Name LIKE \"%" + nameIn + "%\";";
			System.out.println(selTable);
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				em.setId(rs.getInt(1));
				em.setEmployeeId(rs.getString(2));
				em.setName(rs.getString(3));
				em.setAddress(rs.getString(4));
				em.setJobsTitle(rs.getString(5));
				em.setCrew(rs.getString(6));
				em.setPhoneExtension(rs.getString(7));
				em.setPhoneNumber(rs.getString(8));
				em.setMobileNumber(rs.getString(9));

				s.close();
				conn.close();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return em;
	}

	@Override
	public EmployeeModel EmployeeReturnEntryById(int idIn) {
		em = new EmployeeModel();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT * FROM Employees WHERE Employees.Name LIKE \"%" + idIn + "%\";";
			System.out.println(selTable);
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				em.setId(rs.getInt(1));
				em.setEmployeeId(rs.getString(2));
				em.setName(rs.getString(3));
				em.setAddress(rs.getString(4));
				em.setJobsTitle(rs.getString(5));
				em.setCrew(rs.getString(6));
				em.setPhoneExtension(rs.getString(7));
				em.setPhoneNumber(rs.getString(8));
				em.setMobileNumber(rs.getString(9));

				s.close();
				conn.close();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return em;
	}

	@Override
	public int EmployeeReturnIdByName(String nameIn) {
		em = new EmployeeModel();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			Statement s = conn.createStatement();

			// QUERY
			// /////////////////////////////////////////////////////////////
			String selTable = "SELECT ID FROM Employees WHERE Employees.Name LIKE \"%" + nameIn + "%\";";
			System.out.println(selTable);
			s.setQueryTimeout(10);
			s.execute(selTable);

			ResultSet rs = s.getResultSet();

			while ((rs != null) && (rs.next())) {

				em.setId(rs.getInt(1));

				s.close();
				conn.close();

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return em.getId();
	}

	@Override
	public void EmployeeInsert(EmployeeModel em) {
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
		
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);
		
		String sql = "insert into Employees values(?,?,?,?,?,?,?,?,?)";
	 
			jdbcTemplate = new JdbcTemplate(dataSource);
	 
			jdbcTemplate.update(sql, new Object[] { 
					em.getId(),
					em.getEmployeeId(), 
					em.getName(), 
					em.getAddress(), 
					em.getJobsTitle(),
					em.getCrew(),
					em.getPhoneExtension(),
					em.getPhoneNumber(),
					em.getMobileNumber() });
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

//		// QUERY
//		// //////////////////////////////////////////////////////////////////////
//		PreparedStatement empInsert = conn
//				.prepareStatement("insert into Employees values(?,?,?,?,?,?,?,?,?,?)");
//
//		empInsert.setInt(1, em.getId());
//		empInsert.setString(2, em.getEmployeeId());
//		empInsert.setString(3, em.getName());
//		empInsert.setString(4, em.getAddress());
//		empInsert.setString(5, em.getJobsTitle());
//		empInsert.setString(6, em.getCrew());
//		empInsert.setString(7, em.getPhoneExtension());
//		empInsert.setString(8, em.getPhoneNumber());
//		empInsert.setString(9, em.getMobileNumber());
//		empInsert.executeUpdate();
//
//		// /////////////////////////////////////////////////////////////////////////////
//		empInsert.close();
//		s.close();
//		conn.close();
//		


	}
		

	@Override
	public void EmployeeUpdate(EmployeeModel em) {
		Connection conn = null;
        String sql = "update Employees set EmployeeID=?, Name=? , Address=? , PhoneExt=? ,jobsTitle=? , Crew=? , PhoneNumber=?, MobileNumber=? where ID=?";


		try {
			conn = dataSource.getConnection();
		
		Statement s = conn.createStatement();
		s.setQueryTimeout(10);
		

		// QUERY
		// //////////////////////////////////////////////////////////////////////
		PreparedStatement empInsert = conn
				.prepareStatement(sql);

		empInsert.setInt(1, em.getId());
		empInsert.setString(2, em.getEmployeeId());
		empInsert.setString(3, em.getName());
		empInsert.setString(4, em.getAddress());
		empInsert.setString(5, em.getJobsTitle());
		empInsert.setString(6, em.getCrew());
		empInsert.setString(7, em.getPhoneExtension());
		empInsert.setString(8, em.getPhoneNumber());
		empInsert.setString(9, em.getMobileNumber());
		empInsert.executeUpdate();

		// /////////////////////////////////////////////////////////////////////////////
		empInsert.close();
		s.close();
		conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void EmployeeDelete(EmployeeModel em) {
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
		
        conn.setAutoCommit(false);
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String selTable = "Delete FROM Employees WHERE Employees.EmployeeId = " + em.getEmployeeId() + ";";

        s.execute(selTable);

        // /////////////////////////////////////////////////////////////////////////////
        conn.commit();
        s.close();
        conn.close();
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int EmployeeGetRowCount() {
					
		Connection conn = null;
		int count = 0;
		
		try {
			conn = dataSource.getConnection();
		
        Statement s = conn.createStatement();
        s.setQueryTimeout(5);
        ResultSet r = s.executeQuery("SELECT COUNT(Name) AS rowcount FROM Employees");
        r.next();
        count = r.getInt("rowcount");
        System.out.println("Row Count : " + count);

        s.close();
        conn.close();
        r.close();
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return count;
	}

	@Override
	public Object[] EmployeeGetArraySorted() {
		
		 Object[] array = new Object[EmployeeGetRowCount()];

	        Connection conn = null;
	        
	        try {
				conn = dataSource.getConnection();
			
	        
	        Statement s = conn.createStatement();
	        s.setQueryTimeout(5);
	        ResultSet r = s.executeQuery("SELECT * FROM Employees ORDER by QueuePosition ASC");

	        while ((r != null) && (r.next())) {

	            System.out.println("Entry : " + r.getString(2));

	        }

	        s.close();
	        conn.close();
	        r.close();
	        
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return array;
	}

	@Override
	public JPanel EmployeeSummaryTable() {
		
		Connection conn = null;
		JPanel panel = new JPanel();
		
		
		 JPanel outerPanel = new JPanel(new BorderLayout());

	        try {
				conn = dataSource.getConnection();
			
	        Statement stmt = conn.createStatement();
	        stmt.setQueryTimeout(10);

	        PreparedStatement psmt = conn.prepareStatement("SELECT "
	                + "Name,"
	                + "EmployeeID, "
	                + "jobsTitle, "
	                + "Crew, "
	                + "PhoneExt "
	                + "FROM "
	                + "Employees "
	                + "ORDER BY Name ASC");
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

	            row.add(rs.getString(1));
	            row.add(rs.getString(2));
	            row.add(rs.getString(3));
	            row.add(rs.getString(4));
	            row.add(rs.getString(5));

	            data.add(row);
	        }

	        // Now create the table
	        DefaultTableModel model = new DefaultTableModel(data, cols);

	        JTable table = new JTable(model);
	        table.setAutoCreateRowSorter(true);

	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

//	        table.getColumnModel().getColumn(0).setMinWidth(100);
//	        table.getColumnModel().getColumn(2).setMaxWidth(40);
//	        table.getColumnModel().getColumn(17).setMaxWidth(40);
//	        TableColumn tc = table.getColumnModel().getColumn(3);
//	        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc1 = table.getColumnModel().getColumn(4);
//	        tc1.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc2 = table.getColumnModel().getColumn(5);
//	        tc2.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc3 = table.getColumnModel().getColumn(6);
//	        tc3.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc4 = table.getColumnModel().getColumn(7);
//	        tc4.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc5 = table.getColumnModel().getColumn(8);
//	        tc5.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc6 = table.getColumnModel().getColumn(9);
//	        tc6.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc7 = table.getColumnModel().getColumn(10);
//	        tc7.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc8 = table.getColumnModel().getColumn(11);
//	        tc8.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc9 = table.getColumnModel().getColumn(12);
//	        tc9.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc10 = table.getColumnModel().getColumn(13);
//	        tc10.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc11 = table.getColumnModel().getColumn(14);
//	        tc11.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc12 = table.getColumnModel().getColumn(15);
//	        tc12.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	//
//	        TableColumn tc13 = table.getColumnModel().getColumn(16);
//	        tc13.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	        JTableHeader header = table.getTableHeader();

	        outerPanel.add(header, BorderLayout.NORTH);
	        outerPanel.add(table, BorderLayout.CENTER);

	        psmt.close();
	        stmt.close();
	        conn.close();

	        JScrollPane scrollPane = new JScrollPane(outerPanel);
	        panel = new JPanel(new BorderLayout());
	        panel.add(scrollPane);
	        
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        return panel;
	}

	@Override
	public JComboBox[] fillCombos(JComboBox[] em) {

		JComboBox jCombo1, jCombo2, jCombo3, jCombo4, jCombo5, jCombo6;
		String sql;
		Connection conn = null;
		
		////////////////////////////////////////////////////////////////

		conn = null;
		jCombo1 = new JComboBox();
		 sql = "SELECT Jobs.JobsTitle FROM Jobs";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String JobTitle = rs.getString("JobsTitle");
				jCombo1.addItem(JobTitle);

			}
			rs.close();
			ps.close();
			em[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////

		conn = null;
		jCombo2 = new JComboBox();
		sql = "SELECT Crew.CrewName FROM Crew";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				jCombo2.addItem(crewName);
			

			}
			rs.close();
			ps.close();
			em[1] = jCombo2;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo3 = new JComboBox();
		 sql = "SELECT Employees.Name FROM Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo3.addItem(name);

			}
			rs.close();
			ps.close();
			em[2] = jCombo3;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		return em;
	}

	@Override
	public JComboBox[] fillDeleteCombos(JComboBox[] em) {
		
		JComboBox jCombo1, jCombo2, jCombo3, jCombo4;
		String sql;

		///////////////////////////////////////////////////////////////////
		Connection conn = null;
		jCombo1 = new JComboBox();
		 sql = "SELECT Employees.Name FROM Employees ORDER BY Name ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String name = rs.getString("Name");
				jCombo1.addItem(name);

			}
			rs.close();
			ps.close();
			em[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}

		////////////////////////////////////////////////////////////////
		
		
		
		return em;
	}

}
