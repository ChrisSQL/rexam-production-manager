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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rexam.production.dao.ProductionMeetingDAO;
import com.rexam.production.model.ProductionMeetingModel;

public class jdbcProductionMeetingDAO implements ProductionMeetingDAO {

	ProductionMeetingModel pm;

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ProductionMeetingModel ProductionMeetingReturnEntryByDate(Date dateIn) {

		pm = new ProductionMeetingModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM LinerEntry WHERE LinerEntry.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setID(rs.getInt(1));

				pm.setDate(rs.getDate(2));
				pm.setMeetingDate(rs.getDate(3));

				pm.setSafetyIssues(rs.getString(4));
				pm.setProductionAction(rs.getString(5));
				pm.setHygieneManagementOfChange(rs.getString(6));
				pm.setEngineeringActions(rs.getString(7));

				pm.setNonPetUnlinedSilverShells(rs.getInt(8));
				pm.setNonPetlinedSilverShells(rs.getInt(9));
				pm.setNonPetlinedSilverShellsTotal(rs.getInt(10));
				pm.setNonPetUnlinedGoldRx219(rs.getInt(11));
				pm.setNonPetlinedGoldRx219(rs.getInt(12));
				pm.setNonPetlinedGoldRx219Total(rs.getInt(13));
				pm.setMod4FUnlinedSilverShells(rs.getInt(14));
				pm.setMod4FlinedSilverShells(rs.getInt(15));
				pm.setMod4FlinedSilverShellsTotal(rs.getInt(16));
				pm.setNonPetUnlinedSilver215(rs.getInt(17));
				pm.setNonPetlinedSilver215(rs.getInt(18));
				pm.setNonPetlinedSilver215Total(rs.getInt(19));
				pm.setA03HiFiShells(rs.getInt(20));
				pm.setA04HiFiShells(rs.getInt(21));
				pm.setA04HiFiShellsTotal(rs.getInt(22));
				pm.setA03HiFiShellsRX219(rs.getInt(23));
				pm.setA04HiFiShellsRX219(rs.getInt(24));
				pm.setA04HiFiShellsRX219Total(rs.getInt(25));
				pm.setA13HiFiShells(rs.getInt(26));
				pm.setA14HiFiShells(rs.getInt(27));
				pm.setA14HiFiShellsTotal(rs.getInt(28));
				pm.setA07HiFiShells(rs.getInt(29));
				pm.setA08HiFiShells(rs.getInt(30));
				pm.setA08HiFiShellsTotal(rs.getInt(31));
				pm.setDaysRemaining(rs.getInt(32));
				pm.setPackedEnds(rs.getInt(33));
				pm.setMonthlyLineLoad(rs.getInt(34));
				pm.setRemaining(rs.getInt(35));
				pm.setDailyAverage(rs.getInt(36));
				pm.setSpoiledPercentage(rs.getDouble(37));
				pm.setDaysGone(rs.getInt(38));
				pm.setLineLoad(rs.getInt(39));
				pm.setPackedVsLineLoad(rs.getInt(40));

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

	@Override
	public ProductionMeetingModel ProductionMeetingReturnEntryByID(int idIn) {
		pm = new ProductionMeetingModel();

		String sql = "SELECT * FROM LinerEntry WHERE LinerEntry.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				pm.setID(rs.getInt(1));

				pm.setDate(rs.getDate(2));
				pm.setMeetingDate(rs.getDate(3));

				pm.setSafetyIssues(rs.getString(4));
				pm.setProductionAction(rs.getString(5));
				pm.setHygieneManagementOfChange(rs.getString(6));
				pm.setEngineeringActions(rs.getString(7));

				pm.setNonPetUnlinedSilverShells(rs.getInt(8));
				pm.setNonPetlinedSilverShells(rs.getInt(9));
				pm.setNonPetlinedSilverShellsTotal(rs.getInt(10));
				pm.setNonPetUnlinedGoldRx219(rs.getInt(11));
				pm.setNonPetlinedGoldRx219(rs.getInt(12));
				pm.setNonPetlinedGoldRx219Total(rs.getInt(13));
				pm.setMod4FUnlinedSilverShells(rs.getInt(14));
				pm.setMod4FlinedSilverShells(rs.getInt(15));
				pm.setMod4FlinedSilverShellsTotal(rs.getInt(16));
				pm.setNonPetUnlinedSilver215(rs.getInt(17));
				pm.setNonPetlinedSilver215(rs.getInt(18));
				pm.setNonPetlinedSilver215Total(rs.getInt(19));
				pm.setA03HiFiShells(rs.getInt(20));
				pm.setA04HiFiShells(rs.getInt(21));
				pm.setA04HiFiShellsTotal(rs.getInt(22));
				pm.setA03HiFiShellsRX219(rs.getInt(23));
				pm.setA04HiFiShellsRX219(rs.getInt(24));
				pm.setA04HiFiShellsRX219Total(rs.getInt(25));
				pm.setA13HiFiShells(rs.getInt(26));
				pm.setA14HiFiShells(rs.getInt(27));
				pm.setA14HiFiShellsTotal(rs.getInt(28));
				pm.setA07HiFiShells(rs.getInt(29));
				pm.setA08HiFiShells(rs.getInt(30));
				pm.setA08HiFiShellsTotal(rs.getInt(31));
				pm.setDaysRemaining(rs.getInt(32));
				pm.setPackedEnds(rs.getInt(33));
				pm.setMonthlyLineLoad(rs.getInt(34));
				pm.setRemaining(rs.getInt(35));
				pm.setDailyAverage(rs.getInt(36));
				pm.setSpoiledPercentage(rs.getDouble(37));
				pm.setDaysGone(rs.getInt(38));
				pm.setLineLoad(rs.getInt(39));
				pm.setPackedVsLineLoad(rs.getInt(40));

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

	@Override
	public void ProductionMeetingInsert(ProductionMeetingModel pm) {
		String sql = ("insert into ProductionMeeting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement pmInsert = conn.prepareStatement(sql);

			pmInsert.setInt(1, pm.getID());

			pmInsert.setDate(2, convertToSqlDate(pm.getDate()));
			pmInsert.setDate(3, convertToSqlDate(pm.getMeetingDate()));

			pmInsert.setString(4, pm.getSafetyIssues());
			pmInsert.setString(5, pm.getProductionAction());
			pmInsert.setString(6, pm.getHygieneManagementOfChange());
			pmInsert.setString(7, pm.getEngineeringActions());

			pmInsert.setInt(8, pm.getNonPetUnlinedSilverShells());
			pmInsert.setInt(9, pm.getNonPetlinedSilverShells());
			pmInsert.setInt(10, pm.getNonPetlinedSilverShellsTotal());
			pmInsert.setInt(11, pm.getNonPetUnlinedGoldRx219());
			pmInsert.setInt(12, pm.getNonPetlinedGoldRx219());
			pmInsert.setInt(13, pm.getNonPetlinedGoldRx219Total());
			pmInsert.setInt(14, pm.getMod4FUnlinedSilverShells());
			pmInsert.setInt(15, pm.getMod4FlinedSilverShells());
			pmInsert.setInt(16, pm.getMod4FlinedSilverShellsTotal());
			pmInsert.setInt(17, pm.getNonPetUnlinedSilver215());
			pmInsert.setInt(18, pm.getNonPetlinedSilver215());
			pmInsert.setInt(19, pm.getNonPetlinedSilver215Total());
			pmInsert.setInt(20, pm.getA03HiFiShells());
			pmInsert.setInt(21, pm.getA04HiFiShells());
			pmInsert.setInt(22, pm.getA04HiFiShellsTotal());
			pmInsert.setInt(23, pm.getA03HiFiShellsRX219());
			pmInsert.setInt(24, pm.getA04HiFiShellsRX219());
			pmInsert.setInt(25, pm.getA04HiFiShellsRX219Total());
			pmInsert.setInt(26, pm.getA13HiFiShells());
			pmInsert.setInt(27, pm.getA14HiFiShells());
			pmInsert.setInt(28, pm.getA14HiFiShellsTotal());
			pmInsert.setInt(29, pm.getA07HiFiShells());
			pmInsert.setInt(30, pm.getA08HiFiShells());
			pmInsert.setInt(31, pm.getA08HiFiShellsTotal());
			pmInsert.setInt(32, pm.getDaysRemaining());
			pmInsert.setInt(33, pm.getPackedEnds());
			pmInsert.setInt(34, pm.getMonthlyLineLoad());
			pmInsert.setInt(35, pm.getRemaining());
			pmInsert.setInt(36, pm.getDailyAverage());
			pmInsert.setDouble(37, pm.getSpoiledPercentage());
			pmInsert.setInt(38, pm.getDaysGone());
			pmInsert.setInt(39, pm.getLineLoad());
			pmInsert.setInt(40, pm.getPackedVsLineLoad());

			pmInsert.executeUpdate();
			pmInsert.close();

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
	public void ProductionMeetingUpdate(ProductionMeetingModel pm) {
		String sql = "update ProductionMeeting set MeetingDate =?, SafetyIssues =?, ProductionAction =?, "
				+ "HygieneManagementOfChange =?, EngineeringActions =?, NonPetUnlinedSilverShells =?, NonPetlinedSilverShells =?,  "
				+ "NonPetlinedSilverShellsTotal =?, NonPetUnlinedGoldRx219 =?, NonPetlinedGoldRx219 =?, NonPetlinedGoldRx219Total =?, "
				+ "Mod4FUnlinedSilverShells =?, Mod4FlinedSilverShells =?, Mod4FlinedSilverShellsTotal =?, NonPetUnlinedSilver215 =?, "
				+ "NonPetlinedSilver215 =?, NonPetlinedSilver215Total =?, A03HiFiShells =?, A04HiFiShells =?, A04HiFiShellsTotal =?, "
				+ "A03HiFiShellsRX219 =?, A04HiFiShellsRX219 =?, A04HiFiShellsRX219Total =?, A13HiFiShells =?, A14HiFiShells =?, "
				+ "A14HiFiShellsTotal =?, A07HiFiShells =?, A08HiFiShells =?, A08HiFiShellsTotal =?, DaysRemaining =?, PackedEnds =?, "
				+ "MonthlyLineLoad =?, Remaining =?, DailyAverage =?, SpoiledPercentage = ?, DaysGone =?, LineLoad =?, PackedVsLineLoad =? where Date=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement pmInsert = conn.prepareStatement(sql);

			
			pmInsert.setDate(1, convertToSqlDate(pm.getMeetingDate()));

			pmInsert.setString(2, pm.getSafetyIssues());
			pmInsert.setString(3, pm.getProductionAction());
			pmInsert.setString(4, pm.getHygieneManagementOfChange());
			pmInsert.setString(5, pm.getEngineeringActions());

			pmInsert.setInt(6, pm.getNonPetUnlinedSilverShells());
			pmInsert.setInt(7, pm.getNonPetlinedSilverShells());
			pmInsert.setInt(8, pm.getNonPetlinedSilverShellsTotal());
			pmInsert.setInt(9, pm.getNonPetUnlinedGoldRx219());
			pmInsert.setInt(10, pm.getNonPetlinedGoldRx219());
			pmInsert.setInt(11, pm.getNonPetlinedGoldRx219Total());
			pmInsert.setInt(12, pm.getMod4FUnlinedSilverShells());
			pmInsert.setInt(13, pm.getMod4FlinedSilverShells());
			pmInsert.setInt(14, pm.getMod4FlinedSilverShellsTotal());
			pmInsert.setInt(15, pm.getNonPetUnlinedSilver215());
			pmInsert.setInt(16, pm.getNonPetlinedSilver215());
			pmInsert.setInt(17, pm.getNonPetlinedSilver215Total());
			pmInsert.setInt(18, pm.getA03HiFiShells());
			pmInsert.setInt(19, pm.getA04HiFiShells());
			pmInsert.setInt(20, pm.getA04HiFiShellsTotal());
			pmInsert.setInt(21, pm.getA03HiFiShellsRX219());
			pmInsert.setInt(22, pm.getA04HiFiShellsRX219());
			pmInsert.setInt(23, pm.getA04HiFiShellsRX219Total());
			pmInsert.setInt(24, pm.getA13HiFiShells());
			pmInsert.setInt(25, pm.getA14HiFiShells());
			pmInsert.setInt(26, pm.getA14HiFiShellsTotal());
			pmInsert.setInt(27, pm.getA07HiFiShells());
			pmInsert.setInt(28, pm.getA08HiFiShells());
			pmInsert.setInt(29, pm.getA08HiFiShellsTotal());
			pmInsert.setInt(30, pm.getDaysRemaining());
			pmInsert.setInt(31, pm.getPackedEnds());
			pmInsert.setInt(32, pm.getMonthlyLineLoad());
			pmInsert.setInt(33, pm.getRemaining());
			pmInsert.setInt(34, pm.getDailyAverage());
			pmInsert.setDouble(35, pm.getSpoiledPercentage());
			pmInsert.setInt(36, pm.getDaysGone());
			pmInsert.setInt(37, pm.getLineLoad());
			pmInsert.setInt(38, pm.getPackedVsLineLoad());
			
			pmInsert.setDate(39, convertToSqlDate(pm.getDate()));

			pmInsert.executeUpdate();
			pmInsert.close();

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
	public JPanel ProductionMeetingSummaryTable(int reportType) {
		
		String sql1 = "SELECT Date, MeetingDate, SafetyIssues, ProductionAction, HygieneManagementOfChange, EngineeringActions, ID  FROM ProductionMeeting ORDER BY Date DESC";
		String sql2 = "SELECT Date, MeetingDate, SafetyIssues, ProductionAction, HygieneManagementOfChange, EngineeringActions, ID  FROM ProductionMeeting ORDER BY Date DESC";
		Connection conn = null; 
		
		JPanel outerPanel = new JPanel(new BorderLayout());

		   try {
			conn = dataSource.getConnection();
		
	        Statement stmt = conn.createStatement();
	        stmt.setQueryTimeout(10);
	        PreparedStatement psmt = conn.prepareStatement("");
	        ResultSet rs = null;

	        if (reportType == 1) {
	            psmt = conn.prepareStatement(sql1);
	            psmt.setQueryTimeout(10);
	            rs = psmt.executeQuery();
	        } else if (reportType == 2) {
	            psmt = conn.prepareStatement(sql2);
	            psmt.setQueryTimeout(10);
	            rs = psmt.executeQuery();
	        }

	        // PreparedStatement psmt =
	        // conn.prepareStatement("SELECT ID, OptimeNumber as Optime, Date, Shift, Crew, PressSpeed, ShellType, Operator, Production, Comment FROM OptimeEntry ORDER BY Date DESC");
	        // psmt.setQueryTimeout(10);
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

	            data.add(row);
	        }

	        // Now create the table
	        DefaultTableModel model = new DefaultTableModel(data, cols);

	        JTable table = new JTable(model);
	        table.setAutoCreateRowSorter(true);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	        table.getColumnModel().getColumn(0).setMaxWidth(100);
	        table.getColumnModel().getColumn(1).setMaxWidth(100);
	        table.getColumnModel().getColumn(6).setMaxWidth(30);
	        //table.getColumnModel().getColumn(2).setMinWidth(500);
	        // table.getColumnModel().getColumn(1).setPreferredWidth(6);

	        // Render Checkbox
	        // TableColumn tc = table.getColumnModel().getColumn(9);
	        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	        //
	        table.addMouseListener(new MouseAdapter() {
	            public void mousePressed(MouseEvent e) {

	                if (e.getClickCount() == 2) {
	                    JTable target = (JTable) e.getSource();

//	                    int row = target.getSelectedRow() + 1;
//	                    System.out.println(table.getValueAt(table.getSelectedRow(), 6).toString());
//
//	                    String idString = table.getValueAt(table.getSelectedRow(), 6).toString();
//	                    int id = Integer.valueOf(idString);
//	                    try {
//	                        ProductionMeeting production = new ProductionMeeting(2, 2);
//	                        production.setProductionToID(id);
//	                    } catch (Exception ex) {
//	                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
//	                    }

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
