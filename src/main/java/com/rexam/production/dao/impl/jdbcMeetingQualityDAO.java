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

import com.rexam.production.dao.MeetingQualityDAO;
import com.rexam.production.model.MeetingQualityModel;

public class jdbcMeetingQualityDAO implements MeetingQualityDAO{
	
	MeetingQualityModel mq;
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public MeetingQualityModel MeetingQualityReturnEntryByDate(Date dateIn) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String df = (sdf.format(dateIn.getDate()));

		String sql = "SELECT * FROM MeetingQuality WHERE MeetingQuality.Date = \"" + df + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				mq.setId(rs.getInt(1));
				mq.setDate(rs.getDate(2));
				mq.setPercentageChecksDoneDays(rs.getDouble(3));
				mq.setPercentageChecksDoneNights(rs.getDouble(34));
				mq.setCustomerComplaints(rs.getString(5));
				mq.setQualityIssuesPreviousDays(rs.getString(6));
				mq.setQualityIssuesToday(rs.getString(7));		
				mq.setShellsMTD(rs.getInt(8));
				mq.setHfiCreated(rs.getInt(9));
				mq.setHfiRecoveredMTD(rs.getInt(10));
				mq.setHfiScrappedMTD(rs.getInt(11));
			    mq.setEndsInHFI(rs.getInt(12));
			    mq.setQualityEquipment(rs.getString(13));
			    mq.setAuditsDue(rs.getString(14));
				
			}
			rs.close();
			ps.close();
			return mq;
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
	public MeetingQualityModel MeetingQualityReturnEntryByID(int idIn) {
		
		String sql = "SELECT * FROM MeetingQuality WHERE MeetingQuality.Date = \"" + idIn + "\";";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				mq.setId(rs.getInt(1));
				mq.setDate(rs.getDate(2));
				mq.setPercentageChecksDoneDays(rs.getDouble(3));
				mq.setPercentageChecksDoneNights(rs.getDouble(34));
				mq.setCustomerComplaints(rs.getString(5));
				mq.setQualityIssuesPreviousDays(rs.getString(6));
				mq.setQualityIssuesToday(rs.getString(7));		
				mq.setShellsMTD(rs.getInt(8));
				mq.setHfiCreated(rs.getInt(9));
				mq.setHfiRecoveredMTD(rs.getInt(10));
				mq.setHfiScrappedMTD(rs.getInt(11));
			    mq.setEndsInHFI(rs.getInt(12));
			    mq.setQualityEquipment(rs.getString(13));
			    mq.setAuditsDue(rs.getString(14));
				
			}
			rs.close();
			ps.close();
			return mq;
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
	public void MeetingQualityInsert(MeetingQualityModel mq) {
		
		String sql = ("insert into MeetingQuality values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		Connection conn = null;
		
		

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);

			lmInsert.setNull(1, java.sql.Types.INTEGER);
			lmInsert.setDate(2, convertToSqlDate(mq.getDate()));			
            lmInsert.setDouble(3, mq.getPercentageChecksDoneDays());
            lmInsert.setDouble(4, mq.getPercentageChecksDoneNights());           
            lmInsert.setString(5,  mq.getCustomerComplaints());
            lmInsert.setString(6,  mq.getQualityIssuesPreviousDays());
            lmInsert.setString(7,  mq.getQualityIssuesToday());           
            lmInsert.setInt(8, mq.getShellsMTD());
            lmInsert.setInt(9, mq.getHfiCreated());
            lmInsert.setInt(10, mq.getHfiRecoveredMTD());
            lmInsert.setInt(11, mq.getHfiScrappedMTD());  
            lmInsert.setInt(12, mq.getEndsInHFI());
            lmInsert.setString(13,  mq.getQualityEquipment());
            lmInsert.setString(14,  mq.getAuditsDue());
									
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
	public void MeetingQualityUpdate(MeetingQualityModel mq) {
		
		String sql = "update MeetingQuality set PercentageChecksDoneDays=? , PercentageChecksDoneNights=? , CustomerComplaints=? , QualityIssuesPreviousDays=? , QualityIssuesToday=? , "
                + "ShellsMTD=? , HFICreateMTD=?, HFIRecoverMTD=? , HFIScrapMTD=?, EndsInHFI=?, QualityEquipment=?, AuditsDue=? where Date=?";

		Connection conn = null;
		
		

		try {
			conn = dataSource.getConnection();

			PreparedStatement lmInsert = conn.prepareStatement(sql);


						
            lmInsert.setDouble(1, mq.getPercentageChecksDoneDays());
            lmInsert.setDouble(2, mq.getPercentageChecksDoneNights());           
            lmInsert.setString(3,  mq.getCustomerComplaints());
            lmInsert.setString(4,  mq.getQualityIssuesPreviousDays());
            lmInsert.setString(5,  mq.getQualityIssuesToday());           
            lmInsert.setInt(6, mq.getShellsMTD());
            lmInsert.setInt(7, mq.getHfiCreated());
            lmInsert.setInt(8, mq.getHfiRecoveredMTD());
            lmInsert.setInt(9, mq.getHfiScrappedMTD());           
            lmInsert.setString(10,  mq.getQualityEquipment());
            lmInsert.setString(11,  mq.getAuditsDue());
            lmInsert.setDate(12, convertToSqlDate(mq.getDate()));
									
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
	public JPanel MeetingQualityIssuesSummaryTable(int reportType) {
		
		Connection conn = null;
		String sql1 = "SELECT Date, PercentageChecksDoneDays, PercentageChecksDoneNights, CustomerComplaints, ID FROM MeetingQuality ORDER BY Date DESC";
		String sql2 = "SELECT Date, PercentageChecksDoneDays, PercentageChecksDoneNights, CustomerComplaints, ID FROM MeetingQuality ORDER BY Date DESC";
		
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

        DefaultTableModel dm = new DefaultTableModel();

        // get column names
        int len = rs.getMetaData().getColumnCount();
        System.out.println("LEN : " + len);
        Vector cols = new Vector(len);
        for (int i = 1; i <= len; i++) {// Note starting at 1

            cols.add(rs.getMetaData().getColumnName(i));
            //System.out.println(rs.getMetaData().getColumnName(i));

        }

        // Add Data
        Vector data = new Vector();

        while (rs.next()) {

            Vector row = new Vector(len);

            row.add(rs.getString(1));
            row.add(rs.getDouble(2));
            row.add(rs.getDouble(3));
            row.add(rs.getString(4));
            row.add(rs.getString(5));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMinWidth(100);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMaxWidth(30);
        //table.getColumnModel().getColumn(2).setMinWidth(500);
        // table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

//                if (e.getClickCount() == 2) {
//                    JTable target = (JTable) e.getSource();
//
//                    int row = target.getSelectedRow() + 1;
//                    System.out.println(table.getValueAt(table.getSelectedRow(), 4).toString());
//
//                    String idString = table.getValueAt(table.getSelectedRow(), 4).toString();
//                    int id = Integer.valueOf(idString);
//                    try {
//                        MeetingQualityIssues meetingQuality = new MeetingQualityIssues(1, 2);
//                        meetingQuality.setMeetingQualityToID(id);
//                    } catch (Exception ex) {
//                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
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
