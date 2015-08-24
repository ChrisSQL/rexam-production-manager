package com.rexam.production.dao.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.production.dao.LinerDataDAO;
import com.rexam.production.model.LinerDataModel;
import com.rexam.production.model.OpTimeModel;
import com.rexam.production.view.LinerDataView;

public class jdbcLinerDataDAO implements LinerDataDAO{
	
	private DataSource dataSource;
	LinerDataModel lm;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int LinerGetHighestID() {
		int highestID = 0;

		String sql = "SELECT MAX(LinerEntry.ID) FROM LinerEntry;";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				highestID = rs.getInt(1);

			}
			rs.close();
			ps.close();
			return highestID;
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
	public LinerDataModel LinerReturnEntryByDate(Date dateIn) {
		
		lm = new LinerDataModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM LinerEntry WHERE LinerEntry.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setDate(rs.getDate(2));
				lm.setShift(rs.getInt(3));
				lm.setCrew(rs.getString(4));
				lm.setModule(rs.getString(5));			
				lm.setOperator(rs.getString(6));
				lm.setLiner(rs.getString(7));
				lm.setLinerInfeed(rs.getDouble(8));				
				lm.setShellsSpoiled(rs.getDouble(9));
				lm.setCalculatedSpoilage(rs.getDouble(10));
				lm.setComments(rs.getString(11));

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
	public LinerDataModel LinerReturnEntryById(int idIn) {
		lm = new LinerDataModel();

		String sql = "SELECT * FROM LinerEntry WHERE LinerEntry.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				lm.setId(rs.getInt(1));
				lm.setDate(rs.getDate(2));
				lm.setShift(rs.getInt(3));
				lm.setCrew(rs.getString(4));
				lm.setModule(rs.getString(5));			
				lm.setOperator(rs.getString(6));
				lm.setLiner(rs.getString(7));
				lm.setLinerInfeed(rs.getDouble(8));				
				lm.setShellsSpoiled(rs.getDouble(9));
				lm.setCalculatedSpoilage(rs.getDouble(10));
				lm.setComments(rs.getString(11));

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
	public void LinerInsert(LinerDataModel lm) {
		
		String sql = ("insert into LinerEntry values(?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;
		
		

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setNull(1, java.sql.Types.INTEGER);
			lmInsert.setDate(2, convertToSqlDate(lm.getDate()));

			lmInsert.setInt(3, lm.getShift());
			lmInsert.setString(4, lm.getCrew());
			lmInsert.setString(5, lm.getModule());
			lmInsert.setString(6, lm.getOperator());
			lmInsert.setString(7, lm.getLiner());
			lmInsert.setDouble(8, lm.getLinerInfeed());
			lmInsert.setDouble(9, lm.getShellsSpoiled());
			lmInsert.setDouble(10, 0);
			lmInsert.setString(11, lm.getComments());
			
			lmInsert.executeUpdate();
			lmInsert.close();

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
	public void LinerUpdate(LinerDataModel lm) {
		
		String sql = "update LinerEntry set Shift=? , Crew=? , Module=? , Operator=? , Liner=? , "
                + "LinerInfeed=? , ShellsSpoiled=?, CalculatedSpoilage=? , Comments=? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setInt(1, lm.getShift());
			lmInsert.setString(2, lm.getCrew());
			lmInsert.setString(3, lm.getModule());
			lmInsert.setString(4, lm.getOperator());
			lmInsert.setString(5, lm.getLiner());
			lmInsert.setDouble(6, lm.getLinerInfeed());
			lmInsert.setDouble(7, lm.getShellsSpoiled());
			lmInsert.setDouble(8, 0);
			lmInsert.setString(9, lm.getComments());

			lmInsert.setDate(9, convertToSqlDate(lm.getDate()));

			// ot.setShift(ot.);
			// ot.setCrew(crew);
			// ot.setOperator(operator);
			//
			// ot.setOptimeNumber(optimeNumber);
			// ot.setPressSpeed(pressSpeed);
			// ot.setShellType(shellType);
			// ot.setProduction(production);
			// ot.setComment(comment);

			lmInsert.executeUpdate();
			lmInsert.close();

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
	public JPanel LinerSummaryTable(int reportType) {
		
		 JPanel outerPanel = new JPanel(new BorderLayout());

		    Connection conn = null;
		    try {
				conn = dataSource.getConnection();
			
	        Statement stmt = conn.createStatement();
	        PreparedStatement psmt = conn.prepareStatement("");
	        ResultSet rs = null;
	        String sql1 = "SELECT Module, Liner, Date, Shift, Crew, Operator, LinerInfeed, ShellsSpoiled, CalculatedSpoilage as Spoilage, ID, Comments FROM LinerEntry ORDER BY Date DESC, Module, Liner";
	        String sql2 = "SELECT ID, Module, LinerInfeed, ShellsSpoiled, CalculatedSpoilage, Comments FROM LinerEntry Group BY Module";
	        
	        if (reportType == 1) {
	            psmt = conn.prepareStatement(sql1);
	            rs = psmt.executeQuery();
	        } else if (reportType == 2) {
	            psmt = conn.prepareStatement(sql2);
	            rs = psmt.executeQuery();
	        }
	        
		    
	        
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
	            row.add(rs.getString(6));
	            row.add(rs.getString(7));
	            row.add(rs.getString(8));
	            row.add(rs.getString(9));
	            row.add(rs.getString(10));
	            row.add(rs.getString(11));

	            data.add(row);
	        }
	        
		    

	        // Now create the table
	        DefaultTableModel model = new DefaultTableModel(data, cols);

	        final JTable table = new JTable(model);
	        table.setAutoCreateRowSorter(true);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	        table.getColumnModel().getColumn(0).setMinWidth(80);
	        table.getColumnModel().getColumn(3).setMaxWidth(50);
	        table.getColumnModel().getColumn(4).setMaxWidth(50);
	        table.getColumnModel().getColumn(5).setMinWidth(130);
	        table.getColumnModel().getColumn(6).setMinWidth(80);
	        table.getColumnModel().getColumn(7).setMinWidth(80);
	        table.getColumnModel().getColumn(8).setMinWidth(80);
	        table.getColumnModel().getColumn(9).setMaxWidth(50);
	        table.getColumnModel().getColumn(10).setMinWidth(500);

	        //    Render Checkbox
//	        TableColumn tc = table.getColumnModel().getColumn(9);
//	        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {

	                if (e.getClickCount() == 2) {
	                    JTable target = (JTable) e.getSource();

	                    int row = target.getSelectedRow() + 1;
	         // int column = target.getSelectedColumn();

	                    // System.out.println("Clicked : " + row );
	                    System.out.println(table.getValueAt(table.getSelectedRow(),
	                            9).toString());

	                    String idString = table.getValueAt(table.getSelectedRow(),
	                            9).toString();
	                    int id = Integer.valueOf(idString);
	                    LinerDataView par = new LinerDataView(1, -2);
	                    par.setLinerDataEntryToID(id);
	                    System.out.println("ID ZZZZZZ = " + id);

	                }
	            }
	        });
	        JTableHeader header = table.getTableHeader();

	        outerPanel.add(header, BorderLayout.NORTH);
	        outerPanel.add(table, BorderLayout.CENTER);
	        
		    

	        psmt.close();
	        stmt.close();
	        conn.close();
	        
		    } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
	        

	        return outerPanel;
	}

	@Override
	public JPanel LinerUsageSummaryTable(int reportType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean LinerEntryExists(LinerDataModel lm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JComboBox[] fillCombos(JComboBox[] la) {
		JComboBox jCombo1, jCombo2, jCombo3, jCombo4, jCombo5, jCombo6;
		String sql;
		Connection conn = null;

		///////////////////////////////////////////////////////////////////
		
		conn = null;
		jCombo2 = new JComboBox();
		sql = "SELECT Shift.ShiftName FROM Shift ORDER BY ShiftName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				 String shiftName = rs.getString("ShiftName");
	             jCombo2.addItem(shiftName);

			}
			rs.close();
			ps.close();
			la[1] = jCombo2;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////
		conn = null;
		jCombo3 = new JComboBox();
		sql = "SELECT Crew.CrewName FROM Crew ORDER BY CrewName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String crewName = rs.getString("CrewName");
				jCombo3.addItem(crewName);

			}
			rs.close();
			ps.close();
			la[2] = jCombo3;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////
		
		conn = null;
		jCombo4 = new JComboBox();
		sql = "SELECT Module.ModuleName FROM Module ORDER BY ModuleName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				String moduleName = rs.getString("ModuleName");
				jCombo4.addItem(moduleName);

			}
			rs.close();
			ps.close();
			la[3] = jCombo4;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////
				
		conn = null;
		jCombo5 = new JComboBox();
		sql = "SELECT Liner.LinerName FROM Liner ORDER BY LinerName ASC";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String linerName = rs.getString("LinerName");
                jCombo5.addItem(linerName);

			}
			rs.close();
			ps.close();
			la[4] = jCombo5;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////				
		////////////////////////////////////////////////////////////////

		jCombo1 = new JComboBox();
		sql = "select Employees.Name from Employees ORDER BY Name ASC";
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
			la[0] = jCombo1;
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		////////////////////////////////////////////////////////////////
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		return la;
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
