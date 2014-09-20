package com.database.rexam;

import com.binentryscreens.rexam.EndCounts;
import com.binentryscreens.rexam.LinerAndShellsEntry;
import com.maintenance.rexam.ShellPressProduction;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.nonconformance.rexam.LinerEntryMenu;
import com.nonconformance.rexam.OtherEntryMenu;
import com.nonconformance.rexam.ShellPressEntryMenu;
import com.nonconformance.rexam.StolleEntryMenu;
import com.par.rexam.ParEntry;
import com.productiontrackingscreens.rexam.LSSPMActivityEntry2;
import com.productiontrackingscreens.rexam.LinerUsageEntryScreen;
import com.productiontrackingscreens.rexam.MeetingQualityIssues;
import com.productiontrackingscreens.rexam.OptimeDataEntryScreen;
import com.productiontrackingscreens.rexam.ProductionMeeting;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {

    public static void main(String[] args) throws Exception {

        // TODO Auto-generated method stub
        Date date = new Date();
    }

    public SQLiteConnection() {

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

    }

    public static Connection Connect() {

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Connected...");

        return c;

    }

    // LSSPM Entry
    public static Object[] LSSEntryReturnLatestEntry() throws SQLException {

        // Declare
        Object[] result = new Object[3];

        int id;
        String date;
        String comment;

        // Connect
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            stmt.setQueryTimeout(10);
            ResultSet rs = stmt.executeQuery("SELECT * FROM LSSPMEntry ORDER BY TimeStamp DESC;");

            id = rs.getInt("ID");
            date = rs.getString("Date");
            comment = rs.getString("Comment");

            result[0] = id;
            result[1] = date;
            result[2] = comment;

            // System.out.println( "ID = " + id );
            // System.out.println( "Date = " + date );
            // System.out.println( "Comment = " + comment );
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return result;
    }

    public static Object[] LSSPMEntryqueryDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[3];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LSSPMEntry WHERE LSSPMEntry.Date = \"" + df + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {
            result[0] = rs.getString(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);

            // System.out.println(result[0]);
            // System.out.println(result[1]);
            // System.out.println(result[2]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LSSPMEntryqueryId(int idIn) throws Exception {

        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // String df = (sdf.format(dateIn));
        Object[] result = new Object[3];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LSSPMEntry WHERE LSSPMEntry.Id = \"" + idIn + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {
            result[0] = rs.getString(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);

            // System.out.println("ID : " + result[0]);
            // System.out.println("Date : " + result[1]);
            // System.out.println("Comment : " + result[2]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LSSPMEntryqueryHighestArrayDateFormatted() throws Exception {

        // Select all IDs then return highest ID as int.
        Object[] result = new Object[3];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT TOP 1 * From LSSPMEntry ORDER BY LSSPMEntry.ID DESC";
        System.out.println(selTable);
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            result[1] = rs.getObject(2);
            result[2] = rs.getString(3);

        }

        // Convert result[1] into 3 ints
        String modifiedDate = new SimpleDateFormat("yyyy,MM,dd").format((Date) result[1]);
        result[1] = modifiedDate;

        String dateString = result[1] + "";
        System.out.println(dateString);
        int year = Integer.parseInt(dateString.substring(0, 4)); // Correct
        int month = Integer.parseInt(dateString.substring(5, 7)); // Correct
        int day = Integer.parseInt(dateString.substring(8, 10)); // Correct

        // /////////////////////////////////////////////////////////////////////
        System.out.println(year + " " + month + " " + day);

        result[0] = (int) year;

        result[1] = (int) month;
        result[2] = (int) day;

        return result;

    }

    public static String LSSPMEntryqueryHighestCommentFormatted() throws Exception {

        // Select all IDs then return highest ID as int.
        String result = "";

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT TOP 1 * From LSSPMEntry ORDER BY LSSPMEntry.ID DESC";
        System.out.println(selTable);
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        while ((rs != null) && (rs.next())) {

            result = rs.getString(3);

        }

        System.out.println(result);

        return result;

    }

    public static String convertDate(Date dateIn) {

        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateIn);

        return modifiedDate;
    }

    public static int LSSPMEntryqueryHighest() throws Exception {

        // Select all IDs then return highest ID as int.
        Object[] result = new Object[3];
        String highestString;
        int highest = 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT Max(LSSPMEntry.[ID]) FROM LSSPMEntry";
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            highestString = (String) result[0];
            highest = Integer.parseInt(highestString);
            // System.out.println(highest);
        }

        // /////////////////////////////////////////////////////////////////////
        return highest;

    }

    public static int LSSPMEntryqueryLowest() throws Exception {

        // Select all IDs then return highest ID as int.
        Object[] result = new Object[3];
        String lowestString;
        int lowest = 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT Min(LSSPMEntry.[ID]) FROM LSSPMEntry";
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            lowestString = (String) result[0];
            lowest = Integer.parseInt(lowestString);
            // System.out.println(highest);
        }

        // /////////////////////////////////////////////////////////////////////
        return lowest;

    }

    public static int LSSPMGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(LSSPMEntry.[ID]) FROM LSSPMEntry;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            // System.out.println("Highest ID :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;

    }

    public static void LSSPMInsert(String dateIn, String commentIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        int id = LSSPMGetHighestID() + 1;

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateemp = conn.prepareStatement("insert into LSSPMEntry values(?,?,?,?)");

        updateemp.setInt(1, id);
        updateemp.setString(2, dateIn);
        updateemp.setString(3, commentIn);
        updateemp.setString(4, dateF);

        updateemp.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void LSSPMUpdate(int idIn, String dateIn, String commentIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        System.out.println(idIn);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update LSSPMEntry set Date=? , Comment=? where ID=?";

        PreparedStatement updateOptime = conn.prepareStatement(sql);

        updateOptime.setString(1, dateIn);
        updateOptime.setString(2, commentIn);
        updateOptime.setInt(3, idIn);

        updateOptime.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static JPanel LSSPMSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn.prepareStatement("SELECT ID, Date, Comment FROM LSSPMEntry ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT ID, Date, Comment FROM LSSPMEntry ORDER BY Date DESC");
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

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(35);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setMinWidth(500);
		// table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
         // int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(),
                            0).toString());

                    String idString = table.getValueAt(table.getSelectedRow(),
                            0).toString();
                    int id = Integer.valueOf(idString);
                    try {
                        LSSPMActivityEntry2 lssPM = new LSSPMActivityEntry2(2, "", "", -1);
                        lssPM.setLinerUsageToId(id);
                    } catch (Exception ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // OPTime Entry
    public static int OPTimeGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(OPTimeEntry.[ID]) FROM OPTimeEntry;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            // System.out.println("Highest ID :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        System.out.println("HighestID : " + highestID);

        return highestID;

    }

    public static Object[] OPTimeReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[12];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM OPTimeEntry WHERE OPTimeEntry.Date = \"" + df + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            // [ID], [Date], [Shift], [Crew], [Operator], [OptimeNumber],
            // [PressSpeed], [ShellType], [Production], [TimeStamp], [Comment]
            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Operator " + result[4]);
            // System.out.println("OptimeNumber " + result[5]);
            // System.out.println("PressSpeed " + result[6]);
            // System.out.println("ShellType " + result[7]);
            // System.out.println("Production " + result[8]);
            // System.out.println("Comment " + result[9]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] OPTimeReturnEntryById(int idIn) throws Exception {

        Object[] result = new Object[12];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM OPTimeEntry WHERE OPTimeEntry.ID = \"" + idIn + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Shift " + result[2]);
            System.out.println("Crew " + result[3]);
            System.out.println("Operator " + result[4]);
            System.out.println("OptimeNumber " + result[5]);
            System.out.println("PressSpeed " + result[6]);
            System.out.println("ShellType " + result[7]);
            System.out.println("Production " + result[8]);
            System.out.println("Comment " + result[9]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void OPTimeInsert(int idIn, String dateIn, int shiftIn, String crewIn, String operatorIn, int optimeNumberIn, int pressSpeedIn,
            String shellTypeIn, int productionIn, String commentIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateemp = conn.prepareStatement("insert into OPTimeEntry values(?,?,?,?,?,?,?,?,?,?,?)");

        updateemp.setInt(1, idIn);
        updateemp.setString(2, dateIn);
        updateemp.setInt(3, shiftIn);
        updateemp.setString(4, crewIn);
        updateemp.setString(5, operatorIn);
        updateemp.setInt(6, optimeNumberIn);
        updateemp.setInt(7, pressSpeedIn);
        updateemp.setString(8, shellTypeIn);
        updateemp.setInt(9, productionIn);
        updateemp.setString(10, commentIn);
        updateemp.setString(11, dateF);

        updateemp.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void OPTimeUpdate(int idIn, String dateIn, int shiftIn, String crewIn, String operatorIn, int optimeNumberIn, int pressSpeedIn,
            String shellTypeIn, int productionIn, String commentIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update OPTimeEntry set Date=? , Shift=? , Crew=? , Operator=? , OptimeNumber=? , PressSpeed=? , "
                + "ShellType=? , Production=? , Comment=? where ID=?";

        PreparedStatement updateOptime = conn.prepareStatement(sql);

        updateOptime.setString(1, dateIn);
        updateOptime.setInt(2, shiftIn);
        updateOptime.setString(3, crewIn);
        updateOptime.setString(4, operatorIn);
        updateOptime.setInt(5, optimeNumberIn);
        updateOptime.setInt(6, pressSpeedIn);
        updateOptime.setString(7, shellTypeIn);
        updateOptime.setInt(8, productionIn);
        updateOptime.setString(9, commentIn);
        updateOptime.setInt(10, idIn);

        updateOptime.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] OPTimeGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[10];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM OPTimeEntry WHERE OPTimeEntry.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            // [ID], [Date], [Shift], [Crew], [Operator], [OptimeNumber],
            // [PressSpeed], [ShellType], [Production], [TimeStamp], [Comment]
            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] OPTimeGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[10];
        int previousId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM OPTimeEntry WHERE OPTimeEntry.Id = \"" + previousId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            // [ID], [Date], [Shift], [Crew], [Operator], [OptimeNumber],
            // [PressSpeed], [ShellType], [Production], [TimeStamp], [Comment]
            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel OPTimeSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn
                    .prepareStatement("SELECT ID, OptimeNumber as Optime, Date, Shift, Crew, PressSpeed, ShellType, Operator, Production, Comment FROM OptimeEntry ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT ID, OptimeNumber as Optime, Production, Comment FROM OptimeEntry Group BY OptimeNumber");
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
            row.add(rs.getString(8));
            row.add(rs.getString(9));
            row.add(rs.getString(10));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMinWidth(180);
        table.getColumnModel().getColumn(8).setMaxWidth(100);
        table.getColumnModel().getColumn(9).setMinWidth(400);
		// table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
         // int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(),
                            0).toString());

                    String idString = table.getValueAt(table.getSelectedRow(),
                            0).toString();
                    int id = Integer.valueOf(idString);
                    OptimeDataEntryScreen optime = new OptimeDataEntryScreen(1, 2);
                    optime.setOptimeEntry(id);

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

    public static JPanel OPTimeSummaryGroupTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn
                .prepareStatement("SELECT ID, OptimeNumber as Optime, Date, Shift, Crew, PressSpeed, ShellType, Operator, Production, Comment FROM OptimeEntry ORDER by OptimeNumber");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
        table.getColumnModel().getColumn(4).setMaxWidth(50);
        table.getColumnModel().getColumn(6).setMinWidth(180);
        table.getColumnModel().getColumn(8).setMaxWidth(100);
        table.getColumnModel().getColumn(9).setMinWidth(400);
		// table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    public static JPanel OPTimeSummaryCommentsTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn.prepareStatement("SELECT OptimeNumber as Optime, Shift, Comment, ID FROM OptimeEntry ORDER by OptimeNumber");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMinWidth(40);
        table.getColumnModel().getColumn(1).setMinWidth(40);
        table.getColumnModel().getColumn(2).setMinWidth(1000);
        table.getColumnModel().getColumn(3).setMinWidth(40);
		// table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    public static JPanel OPTimeShellsByMonthTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn.prepareStatement("SELECT OptimeNumber as Optime, SUM(Production) as Total FROM OptimeEntry Group by OptimeNumber");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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
            row.add(rs.getInt(2));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMinWidth(500);
        table.getColumnModel().getColumn(1).setMinWidth(500);

        // table.getColumnModel().getColumn(1).setPreferredWidth(6);
        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    // Stolle Date Entry
    public static int StolleGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(Stolle.[ID]) FROM Stolle;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            // System.out.println("Highest ID :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] StolleReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Stolle WHERE Stolle.Date = \"" + df + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Press " + result[4]);
            // System.out.println("Operator " + result[5]);
            // System.out.println("Packer " + result[6]);
            // System.out.println("QC Inspector " + result[7]);
            // System.out.println("Press Speed " + result[8]);
            // System.out.println("Stolle Production " + result[9]);
            // System.out.println("Packed Ends " + result[10]);
            // System.out.println("HFI Created " + result[11]);
            // System.out.println("HFI Recovered " + result[12]);
            // System.out.println("HFI Scrapped " + result[13]);
            // System.out.println("Sacoba Downtime " + result[14]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] StolleReturnEntryByID(int idIn) throws Exception {

        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Stolle WHERE Stolle.ID = \"" + idIn + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Shift " + result[2]);
            System.out.println("Crew " + result[3]);
            System.out.println("Press " + result[4]);
            System.out.println("Operator " + result[5]);
            System.out.println("Packer " + result[6]);
            System.out.println("QC Inspector " + result[7]);
            System.out.println("Press Speed " + result[8]);
            System.out.println("Stolle Production " + result[9]);
            System.out.println("Packed Ends " + result[10]);
            System.out.println("HFI Created " + result[11]);
            System.out.println("HFI Recovered " + result[12]);
            System.out.println("HFI Scrapped " + result[13]);
            System.out.println("Sacoba Downtime " + result[14]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void StolleInsert(int idIn, String dateIn, int shiftIn, String crewIn, String pressIn, String operatorIn, String packerIn,
            String QCInspectorIn, int pressSpeedIn, int stolleProductionIn, int packedEndsIn, int HFICreatedIn, int HFIRecoveredIn,
            int HFIScrappedIn, int SacobaDowntime, String Comment) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateemp = conn.prepareStatement("insert into Stolle values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        updateemp.setInt(1, idIn);
        updateemp.setString(2, dateIn);
        updateemp.setInt(3, shiftIn);
        updateemp.setString(4, crewIn);
        updateemp.setString(5, pressIn);
        updateemp.setString(6, operatorIn);
        updateemp.setString(7, packerIn);
        updateemp.setString(8, QCInspectorIn);
        updateemp.setInt(9, pressSpeedIn);
        updateemp.setInt(10, stolleProductionIn);
        updateemp.setInt(11, packedEndsIn);
        updateemp.setInt(12, HFICreatedIn);
        updateemp.setInt(13, HFIRecoveredIn);
        updateemp.setInt(14, HFIScrappedIn);
        updateemp.setInt(15, SacobaDowntime);
        updateemp.setString(16, Comment);
        updateemp.setString(17, dateF);

        updateemp.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void StolleUpdate(String dateIn, int shiftIn, String crewIn, String pressIn, String operatorIn, String packerIn,
            String QCInspectorIn, int pressSpeed, int stolleProductionIn, int packedEndsIn, int HFICreatedIn, int HFIRecoveredIn, int HFIScrappedIn,
            int SacobaDowntime, String CommentIn, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update Stolle set Date=? , Shift=? , Crew=? , Press=? , Operator=? , Packer=? , "
                + "QCInspector=? , pressSpeed=?, StolleProduction=? , PackedEnds=?, HFICreated=?, "
                + "HFIRecovered=?, HFIScrapped=?, SacobaDowntime=?, Comment=? where ID=?";

        PreparedStatement updateStolle = conn.prepareStatement(sql);

        updateStolle.setString(1, dateIn);
        updateStolle.setInt(2, shiftIn);
        updateStolle.setString(3, crewIn);
        updateStolle.setString(4, pressIn);
        updateStolle.setString(5, operatorIn);
        updateStolle.setString(6, packerIn);
        updateStolle.setString(7, QCInspectorIn);
        updateStolle.setInt(8, pressSpeed);
        updateStolle.setInt(9, stolleProductionIn);
        updateStolle.setInt(10, packedEndsIn);
        updateStolle.setInt(11, HFICreatedIn);
        updateStolle.setInt(12, HFIRecoveredIn);
        updateStolle.setInt(13, HFIScrappedIn);
        updateStolle.setInt(14, SacobaDowntime);
        updateStolle.setString(15, CommentIn);
        updateStolle.setInt(16, idIn);

        updateStolle.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] StolleGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Stolle WHERE Stolle.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Press " + result[4]);
            // System.out.println("Operator " + result[5]);
            // System.out.println("Packer " + result[6]);
            // System.out.println("QC Inspector " + result[7]);
            // System.out.println("Press Speed " + result[8]);
            // System.out.println("Stolle Production " + result[9]);
            // System.out.println("Packed Ends " + result[10]);
            // System.out.println("HFI Created " + result[11]);
            // System.out.println("HFI Recovered " + result[12]);
            // System.out.println("HFI Scrapped " + result[13]);
            // System.out.println("Sacoba Downtime " + result[14]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] StolleGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Stolle WHERE Stolle.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Press " + result[4]);
            // System.out.println("Operator " + result[5]);
            // System.out.println("Packer " + result[6]);
            // System.out.println("QC Inspector " + result[7]);
            // System.out.println("Press Speed " + result[8]);
            // System.out.println("Stolle Production " + result[9]);
            // System.out.println("Packed Ends " + result[10]);
            // System.out.println("HFI Created " + result[11]);
            // System.out.println("HFI Recovered " + result[12]);
            // System.out.println("HFI Scrapped " + result[13]);
            // System.out.println("Sacoba Downtime " + result[14]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel StolleSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn
                    .prepareStatement("SELECT Press, Date, Shift, Crew, Operator, Packer, SacobaDowntime as DTime, QCInspector, StolleProduction as Production, PackedEnds, ID, Comment FROM Stolle ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT ID, Press, StolleProduction, Comment FROM Stolle Group BY Press");
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
            row.add(rs.getString(8));
            row.add(rs.getString(9));
            row.add(rs.getString(10));
            row.add(rs.getString(11));
            row.add(rs.getString(12));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        table.getColumnModel().getColumn(3).setMaxWidth(40);
        table.getColumnModel().getColumn(4).setMinWidth(80);
        table.getColumnModel().getColumn(5).setMinWidth(80);
        table.getColumnModel().getColumn(6).setMinWidth(80);
        table.getColumnModel().getColumn(7).setMinWidth(80);
        table.getColumnModel().getColumn(8).setMinWidth(80);
        table.getColumnModel().getColumn(9).setMinWidth(80);
        table.getColumnModel().getColumn(10).setMaxWidth(30);
        table.getColumnModel().getColumn(11).setMinWidth(500);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
         // int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println("Selected NUmber " + table.getValueAt(table.getSelectedRow(),
                            0).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 0).toString();
                    int id = Integer.valueOf(idString);
                    StolleEntryMenu stolle = new StolleEntryMenu(id);
                    try {
                        stolle.setStolleEntryToNCR(id);
                    } catch (Exception ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    public static JPanel StolleSummaryGroupTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn
                .prepareStatement("SELECT Press, Date, Shift, Crew, Operator, Packer, SacobaDowntime as DTime, QCInspector, StolleProduction as Production, PackedEnds, ID, Comment FROM Stolle ORDER by Press");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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
            row.add(rs.getString(12));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        table.getColumnModel().getColumn(3).setMaxWidth(40);
        table.getColumnModel().getColumn(4).setMinWidth(80);
        table.getColumnModel().getColumn(5).setMinWidth(80);
        table.getColumnModel().getColumn(6).setMinWidth(80);
        table.getColumnModel().getColumn(7).setMinWidth(80);
        table.getColumnModel().getColumn(8).setMinWidth(80);
        table.getColumnModel().getColumn(9).setMinWidth(80);
        table.getColumnModel().getColumn(10).setMaxWidth(30);
        table.getColumnModel().getColumn(11).setMinWidth(500);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    public static JPanel StolleSummaryCommentsTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn.prepareStatement("SELECT Press, Shift, Comment, ID FROM Stolle ORDER by Press");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        //table.getColumnModel().getColumn(2).setMinWidth(1000);
        table.getColumnModel().getColumn(3).setMaxWidth(50);
		// table.getColumnModel().getColumn(1).setPreferredWidth(6);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    public static JPanel StolleEndsByMonthTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        psmt = conn.prepareStatement("SELECT Press, SUM(StolleProduction) as Total FROM Stolle Group by Press");
        psmt.setQueryTimeout(10);
        rs = psmt.executeQuery();

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
            row.add(rs.getInt(2));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMinWidth(500);
        table.getColumnModel().getColumn(1).setMinWidth(500);

        // table.getColumnModel().getColumn(1).setPreferredWidth(6);
        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    // Liner Data Entry Screen
    public static int LinerGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(LinerEntry.[ID]) FROM LinerEntry;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            // System.out.println("Highest ID :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] LinerReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerEntry WHERE LinerEntry.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Module " + result[4]);
            // System.out.println("Operator " + result[5]);
            // System.out.println("Liner " + result[6]);
            // System.out.println("Liner Feed " + result[7]);
            // System.out.println("Shells Spoiled " + result[8]);
            // System.out.println("Calculated Spoilage " + result[9]);
            // System.out.println("Comments " + result[10]);
            // System.out.println("TimeStamp " + result[11]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LinerReturnEntryById(int idIn) throws Exception {

        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerEntry WHERE LinerEntry.ID = \"" + idIn + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Shift " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Module " + result[4]);
            // System.out.println("Operator " + result[5]);
            // System.out.println("Liner " + result[6]);
            // System.out.println("Liner Feed " + result[7]);
            // System.out.println("Shells Spoiled " + result[8]);
            // System.out.println("Calculated Spoilage " + result[9]);
            // System.out.println("Comments " + result[10]);
            // System.out.println("TimeStamp " + result[11]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void LinerInsert(int idIn, String dateIn, int shiftIn, String crewIn, String moduleIn, String operatorIn, String linerIn,
            Double linerInfeedIn, Double shellsSpoiled, Double calculatedSpoilage, String comments) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateLiner = conn.prepareStatement("insert into LinerEntry values(?,?,?,?,?,?,?,?,?,?,?,?)");

        updateLiner.setInt(1, idIn);
        updateLiner.setString(2, dateIn);
        updateLiner.setInt(3, shiftIn);
        updateLiner.setString(4, crewIn);
        updateLiner.setString(5, moduleIn);
        updateLiner.setString(6, operatorIn);
        updateLiner.setString(7, linerIn);
        updateLiner.setDouble(8, linerInfeedIn);
        updateLiner.setDouble(9, shellsSpoiled);
        updateLiner.setDouble(10, calculatedSpoilage);
        updateLiner.setString(11, comments);
        updateLiner.setString(12, dateF);

        updateLiner.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void LinerUpdate(String dateIn, int shiftIn, String crewIn, String moduleIn, String operatorIn, String linerIn,
            Double linerInfeedIn, Double shellsSpoiledIn, Double calculatedSpoilageIn, String commentsIn, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update LinerEntry set Date=? , Shift=? , Crew=? , Module=? , Operator=? , Liner=? , "
                + "LinerInfeed=? , ShellsSpoiled=?, CalculatedSpoilage=? , Comments=?, where ID=?";

        PreparedStatement updateLiner = conn.prepareStatement(sql);

        updateLiner.setString(1, dateIn);
        updateLiner.setInt(2, shiftIn);
        updateLiner.setString(3, crewIn);
        updateLiner.setString(4, moduleIn);
        updateLiner.setString(5, operatorIn);
        updateLiner.setString(6, linerIn);
        updateLiner.setDouble(7, linerInfeedIn);
        updateLiner.setDouble(8, shellsSpoiledIn);
        updateLiner.setDouble(9, calculatedSpoilageIn);
        updateLiner.setString(10, commentsIn);
        updateLiner.setInt(16, idIn);

        updateLiner.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] LinerGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerEntry WHERE LinerEntry.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Shift " + result[2]);
            System.out.println("Crew " + result[3]);
            System.out.println("Module " + result[4]);
            System.out.println("Operator " + result[5]);
            System.out.println("Liner " + result[6]);
            System.out.println("Liner Feed " + result[7]);
            System.out.println("Shells Spoiled " + result[8]);
            System.out.println("Calculated Spoilage " + result[9]);
            System.out.println("Comments " + result[10]);
            System.out.println("TimeStamp " + result[11]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] LinerGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerEntry WHERE LinerEntry.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Shift " + result[2]);
            System.out.println("Crew " + result[3]);
            System.out.println("Module " + result[4]);
            System.out.println("Operator " + result[5]);
            System.out.println("Liner " + result[6]);
            System.out.println("Liner Feed " + result[7]);
            System.out.println("Shells Spoiled " + result[8]);
            System.out.println("Calculated Spoilage " + result[9]);
            System.out.println("Comments " + result[10]);
            System.out.println("TimeStamp " + result[11]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel LinerSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn
                    .prepareStatement("SELECT Module, Date, Shift, Crew, Operator, LinerInfeed, ShellsSpoiled, CalculatedSpoilage as Spoilage, Comments FROM LinerEntry ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT ID, Module, LinerInfeed, ShellsSpoiled, CalculatedSpoilage, Comments FROM LinerEntry Group BY Module");
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
            row.add(rs.getString(8));
            row.add(rs.getString(9));
//            row.add(rs.getString(10));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        table.getColumnModel().getColumn(3).setMinWidth(80);
        table.getColumnModel().getColumn(4).setMinWidth(80);
        table.getColumnModel().getColumn(5).setMinWidth(80);
        table.getColumnModel().getColumn(6).setMinWidth(80);
        table.getColumnModel().getColumn(7).setMinWidth(80);
        table.getColumnModel().getColumn(8).setMinWidth(500);

        // Render Checkbox
        // TableColumn tc = table.getColumnModel().getColumn(9);
        // tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        // table.addMouseListener(new MouseAdapter() {
        // public void mousePressed(MouseEvent e) {
        //
        // if (e.getClickCount() == 2) {
        // JTable target = (JTable) e.getSource();
        //
        // int row = target.getSelectedRow() + 1;
        // // int column = target.getSelectedColumn();
        //
        // // System.out.println("Clicked : " + row );
        // System.out.println(table.getValueAt(table.getSelectedRow(),
        // 0).toString());
        //
        // String idString = table.getValueAt(table.getSelectedRow(),
        // 0).toString();
        // int id = Integer.valueOf(idString);
        // ParEntry par = new ParEntry(2, "1", "August", "2014");
        // par.setParToForm(1);
        //
        // }
        // }
        // });
        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    public static JPanel LinerUsageSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn.prepareStatement("SELECT LinerNumber, Date, Crew, LeadHand, Reason, QuantityUsed, Gun1 as G1, Gun2 as G2, Gun3 as G3, Gun4 as G4, Gun5 as G5, Gun6 as G6, Gun7 as G7, Gun8 as G8, Comment, ID  FROM LinerUsage ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT LinerNumber, Date, Crew, LeadHand, Reason, QuantityUsed, Gun1, Gun2, Gun3, Gun4, Gun5, Gun6, Gun7, Gun8, Comment, ID FROM LinerUsage Group BY Module");
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
            row.add(rs.getBoolean(7));
            row.add(rs.getBoolean(8));
            row.add(rs.getBoolean(9));
            row.add(rs.getBoolean(10));
            row.add(rs.getBoolean(11));
            row.add(rs.getBoolean(12));
            row.add(rs.getBoolean(13));
            row.add(rs.getBoolean(14));
            row.add(rs.getString(15));
            row.add(rs.getString(16));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

//        table.getColumnModel().getColumn(0).setMinWidth(100);
//        table.getColumnModel().getColumn(1).setMinWidth(100);
//        table.getColumnModel().getColumn(2).setMinWidth(100);
//        table.getColumnModel().getColumn(3).setMinWidth(100);
//        table.getColumnModel().getColumn(4).setMinWidth(100);
//        table.getColumnModel().getColumn(5).setMinWidth(100);
//        
        table.getColumnModel().getColumn(6).setMaxWidth(35);
        table.getColumnModel().getColumn(7).setMaxWidth(35);
        table.getColumnModel().getColumn(8).setMaxWidth(35);
        table.getColumnModel().getColumn(9).setMaxWidth(35);
        table.getColumnModel().getColumn(10).setMaxWidth(35);
        table.getColumnModel().getColumn(11).setMaxWidth(35);
        table.getColumnModel().getColumn(12).setMaxWidth(35);
        table.getColumnModel().getColumn(13).setMaxWidth(35);

//        table.getColumnModel().getColumn(7).setMinWidth(80);
        table.getColumnModel().getColumn(14).setMinWidth(500);
        table.getColumnModel().getColumn(15).setMaxWidth(20);

        // Render Checkbox
        TableColumn tc1 = table.getColumnModel().getColumn(6);
        tc1.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc2 = table.getColumnModel().getColumn(7);
        tc2.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc3 = table.getColumnModel().getColumn(8);
        tc3.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc4 = table.getColumnModel().getColumn(9);
        tc4.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc5 = table.getColumnModel().getColumn(10);
        tc5.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc6 = table.getColumnModel().getColumn(11);
        tc6.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc7 = table.getColumnModel().getColumn(12);
        tc7.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        TableColumn tc8 = table.getColumnModel().getColumn(13);
        tc8.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
         // int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(), 15).toString());
                    String idString = table.getValueAt(table.getSelectedRow(), 15).toString();
                    int id = Integer.valueOf(idString);
                    LinerUsageEntryScreen linerUsage = new LinerUsageEntryScreen(1, 2);
                    linerUsage.setLinerUsageToId(id);

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

    // Meeting Quality Issues
    public static int MeetingQualityGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(MeetingQuality.[ID]) FROM MeetingQuality;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] MeetingQualityReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MeetingQuality WHERE MeetingQuality.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Percentage of Checks Done Days " + result[2]);
            System.out.println("Percentage of Checks Done Nights " + result[3]);
            System.out.println("Customer Complaints " + result[4]);
            System.out.println("Quality Issues Previous Day " + result[5]);
            System.out.println("Quality Issues Today " + result[6]);
            System.out.println("Shells MTD " + result[7]);
            System.out.println("HFI Create MTD " + result[8]);
            System.out.println("HFI Recover MTD " + result[9]);
            System.out.println("HFI Scrapped MTD " + result[10]);
            System.out.println("Ends In HFI " + result[11]);
            System.out.println("Quality Equipment " + result[12]);
            System.out.println("Audits Due " + result[13]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] MeetingQualityReturnEntryByID(int idIn) throws Exception {

        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MeetingQuality WHERE MeetingQuality.ID = \"" + idIn + "\";";
        //System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getDouble(3);
            result[3] = rs.getDouble(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);

//            System.out.println("ID " + result[0]);
//            System.out.println("Date " + result[1]);
//            System.out.println("Percentage of Checks Done Days " + result[2]);
//            System.out.println("Percentage of Checks Done Nights " + result[3]);
//            System.out.println("Customer Complaints " + result[4]);
//            System.out.println("Quality Issues Previous Day " + result[5]);
//            System.out.println("Quality Issues Today " + result[6]);
//            System.out.println("Shells MTD " + result[7]);
//            System.out.println("HFI Create MTD " + result[8]);
//            System.out.println("HFI Recover MTD " + result[9]);
//            System.out.println("HFI Scrapped MTD " + result[10]);
//            System.out.println("Ends In HFI " + result[11]);
//            System.out.println("Quality Equipment " + result[12]);
//            System.out.println("Audits Due " + result[13]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void MeetingQualityInsert(int idIn, String dateIn, Double percentageOfChecksDoneDaysIn, Double percentageOfChecksDoneNightIn,
            String customerComplaintsIn, String QualityIssuesPreviousDayIn, String qualityIssuesTodayIn, int shellsMTDIn, int HFICreateMTDIn,
            int HFIRecoveredMTDIn, int HFIScrappedMTDIn, int EndsInHFIIn, String QualityEquipmentIn, String AuditsDueIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateLMeetingQuality = conn.prepareStatement("insert into MeetingQuality values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        updateLMeetingQuality.setInt(1, idIn);
        updateLMeetingQuality.setString(2, dateIn);
        updateLMeetingQuality.setDouble(3, percentageOfChecksDoneDaysIn);
        updateLMeetingQuality.setDouble(4, percentageOfChecksDoneNightIn);
        updateLMeetingQuality.setString(5, customerComplaintsIn);
        updateLMeetingQuality.setString(6, QualityIssuesPreviousDayIn);
        updateLMeetingQuality.setString(7, qualityIssuesTodayIn);
        updateLMeetingQuality.setDouble(8, shellsMTDIn);
        updateLMeetingQuality.setDouble(9, HFICreateMTDIn);
        updateLMeetingQuality.setDouble(10, HFIRecoveredMTDIn);
        updateLMeetingQuality.setInt(11, HFIScrappedMTDIn);
        updateLMeetingQuality.setInt(12, EndsInHFIIn);
        updateLMeetingQuality.setString(13, QualityEquipmentIn);
        updateLMeetingQuality.setString(14, AuditsDueIn);
        updateLMeetingQuality.setString(15, dateF);

        updateLMeetingQuality.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void MeetingQualityUpdate(String dateIn, Double percentageOfChecksDoneDaysIn, Double percentageOfChecksDoneNightIn,
            String customerComplaintsIn, String QualityIssuesPreviousDayIn, String qualityIssuesTodayIn, int shellsMTDIn, int HFICreateMTDIn,
            int HFIRecoveredMTDIn, int HFIScrappedMTDIn, int EndsInHFIIn, String QualityEquipmentIn, String AuditsDueIn, int idIn)
            throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update MeetingQuality set Date=? , PercentageChecksDoneDays=? , PercentageChecksDoneNights=? , CustomerComplaints=? , QualityIssuesPreviousDays=? , QualityIssuesToday=? , "
                + "ShellsMTD=? , HFICreateMTD=?, HFIRecoverMTD=? , HFIScrapMTD=?, EndsInHFI=?, QualityEquipment=?, AuditsDue=? where ID=?";

        System.out.println(idIn);

        PreparedStatement updateLMeetingQuality = conn.prepareStatement(sql);

        updateLMeetingQuality.setString(1, dateIn);
        updateLMeetingQuality.setDouble(2, percentageOfChecksDoneDaysIn);
        updateLMeetingQuality.setDouble(3, percentageOfChecksDoneNightIn);
        updateLMeetingQuality.setString(4, customerComplaintsIn);
        updateLMeetingQuality.setString(5, QualityIssuesPreviousDayIn);
        updateLMeetingQuality.setString(6, qualityIssuesTodayIn);
        updateLMeetingQuality.setDouble(7, shellsMTDIn);
        updateLMeetingQuality.setDouble(8, HFICreateMTDIn);
        updateLMeetingQuality.setDouble(9, HFIRecoveredMTDIn);
        updateLMeetingQuality.setInt(10, HFIScrappedMTDIn);
        updateLMeetingQuality.setInt(11, EndsInHFIIn);
        updateLMeetingQuality.setString(12, QualityEquipmentIn);
        updateLMeetingQuality.setString(13, AuditsDueIn);
        updateLMeetingQuality.setInt(14, idIn);

        updateLMeetingQuality.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] MeetingQualityGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MeetingQuality WHERE MeetingQuality.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(12);
            result[13] = rs.getString(13);

            // System.out.println("ID " + result[0]);
            // System.out.println("Date " + result[1]);
            // System.out.println("Percentage of Checks Done Days " +
            // result[2]);
            // System.out.println("Percentage of Checks Done Nights " +
            // result[3]);
            // System.out.println("Customer Complaints " + result[4]);
            // System.out.println("Quality Issues Previous Day " + result[5]);
            // System.out.println("Quality Issues Today " + result[6]);
            // System.out.println("Shells MTD " + result[7]);
            // System.out.println("HFI Create MTD " + result[8]);
            // System.out.println("HFI Recover MTD " + result[9]);
            // System.out.println("HFI Scrapped MTD " + result[10]);
            // System.out.println("Ends In HFI " + result[11]);
            // System.out.println("Quality Equipment " + result[12]);
            // System.out.println("Audits Due " + result[13]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] MeetingQualityGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MeetingQuality WHERE MeetingQuality.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(12);
            result[13] = rs.getString(13);

            System.out.println("ID " + result[0]);
            System.out.println("Date " + result[1]);
            System.out.println("Percentage of Checks Done Days " + result[2]);
            System.out.println("Percentage of Checks Done Nights " + result[3]);
            System.out.println("Customer Complaints " + result[4]);
            System.out.println("Quality Issues Previous Day " + result[5]);
            System.out.println("Quality Issues Today " + result[6]);
            System.out.println("Shells MTD " + result[7]);
            System.out.println("HFI Create MTD " + result[8]);
            System.out.println("HFI Recover MTD " + result[9]);
            System.out.println("HFI Scrapped MTD " + result[10]);
            System.out.println("Ends In HFI " + result[11]);
            System.out.println("Quality Equipment " + result[12]);
            System.out.println("Audits Due " + result[13]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel MeetingQualityIssuesSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn.prepareStatement("SELECT Date, PercentageChecksDoneDays, PercentageChecksDoneNights, CustomerComplaints, ID FROM MeetingQuality ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT Date, PercentageChecksDoneDays, PercentageChecksDoneNights, CustomerComplaints, ID FROM MeetingQuality ORDER BY Date DESC");
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

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
                    System.out.println(table.getValueAt(table.getSelectedRow(), 4).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 4).toString();
                    int id = Integer.valueOf(idString);
                    try {
                        MeetingQualityIssues meetingQuality = new MeetingQualityIssues(1, 2);
                        meetingQuality.setMeetingQualityToID(id);
                    } catch (Exception ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // Production Meeting Inserts
    public static int ProductionMeetingGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(ProductionMeeting.[ID]) FROM ProductionMeeting;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static int ProductionMeetingGetLowestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MIN(ProductionMeeting.[ID]) FROM ProductionMeeting;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] ProductionMeetingReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[41];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ProductionMeeting WHERE ProductionMeeting.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            for (int i = 0; i < result.length; i++) {
                result[i] = rs.getString(i + 1);
                System.out.println("Row " + i + " : " + result[i]);
            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] ProductionMeetingReturnEntryByID(int idIn) throws Exception {

        Object[] result = new Object[41];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ProductionMeeting WHERE ProductionMeeting.ID = \"" + idIn + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            for (int i = 0; i < result.length; i++) {
                result[i] = rs.getString(i + 1);
                //System.out.println("Row " + i + " : " + result[i]);
            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void ProductionMeetingInsert(int IDIn, String DateIn, String MeetingDateIn, String SafetyIssuesIn, String ProductionActionIn,
            String HygieneManagementOfChangeIn, String EngineeringActionsIn, int NonPetUnlinedSilverShellsIn, int NonPetlinedSilverShellsIn,
            int NonPetlinedSilverShellsTotalIn, int NonPetUnlinedGoldRx219In, int NonPetlinedGoldRx219In, int NonPetlinedGoldRx219TotalIn,
            int Mod4FUnlinedSilverShellsIn, int Mod4FlinedSilverShellsIn, int Mod4FlinedSilverShellsTotalIn, int NonPetUnlinedSilver215In,
            int NonPetlinedSilver215In, int NonPetlinedSilver215TotalIn, int A03HiFiShellsIn, int A04HiFiShellsIn, int A04HiFiShellsTotalIn,
            int A03HiFiShellsRX219In, int A04HiFiShellsRX219In, int A04HiFiShellsRX219TotalIn, int A13HiFiShellsIn, int A14HiFiShellsIn,
            int A14HiFiShellsTotalIn, int A07HiFiShellsIn, int A08HiFiShellsIn, int A08HiFiShellsTotalIn, int DaysRemainingIn, int PackedEndsIn,
            int MonthlyLineLoadIn, int RemainingIn, int DailyAverageIn, Double SpoiledPercentageIn, int DaysGoneIn, int LineLoadIn,
            int PackedVsLineLoadIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateLproductionMeeting = conn
                .prepareStatement("insert into ProductionMeeting values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        updateLproductionMeeting.setInt(1, IDIn);
        updateLproductionMeeting.setString(2, DateIn);
        updateLproductionMeeting.setString(3, MeetingDateIn);
        updateLproductionMeeting.setString(4, SafetyIssuesIn);
        updateLproductionMeeting.setString(5, ProductionActionIn);
        updateLproductionMeeting.setString(6, HygieneManagementOfChangeIn);
        updateLproductionMeeting.setString(7, EngineeringActionsIn);
        updateLproductionMeeting.setInt(8, NonPetUnlinedSilverShellsIn);
        updateLproductionMeeting.setInt(9, NonPetlinedSilverShellsIn);
        updateLproductionMeeting.setInt(10, NonPetlinedSilverShellsTotalIn);
        updateLproductionMeeting.setInt(11, NonPetUnlinedGoldRx219In);
        updateLproductionMeeting.setInt(12, NonPetlinedGoldRx219In);
        updateLproductionMeeting.setInt(13, NonPetlinedGoldRx219TotalIn);
        updateLproductionMeeting.setInt(14, Mod4FUnlinedSilverShellsIn);
        updateLproductionMeeting.setInt(15, Mod4FlinedSilverShellsIn);
        updateLproductionMeeting.setInt(16, Mod4FlinedSilverShellsTotalIn);
        updateLproductionMeeting.setInt(17, NonPetUnlinedSilver215In);
        updateLproductionMeeting.setInt(18, NonPetlinedSilver215In);
        updateLproductionMeeting.setInt(19, NonPetlinedSilver215TotalIn);
        updateLproductionMeeting.setInt(20, A03HiFiShellsIn);
        updateLproductionMeeting.setInt(21, A04HiFiShellsIn);
        updateLproductionMeeting.setInt(22, A04HiFiShellsTotalIn);
        updateLproductionMeeting.setInt(23, A03HiFiShellsRX219In);
        updateLproductionMeeting.setInt(24, A04HiFiShellsRX219In);
        updateLproductionMeeting.setInt(25, A04HiFiShellsRX219TotalIn);
        updateLproductionMeeting.setInt(26, A13HiFiShellsIn);
        updateLproductionMeeting.setInt(27, A14HiFiShellsIn);
        updateLproductionMeeting.setInt(28, A14HiFiShellsTotalIn);
        updateLproductionMeeting.setInt(29, A07HiFiShellsIn);
        updateLproductionMeeting.setInt(30, A08HiFiShellsIn);
        updateLproductionMeeting.setInt(31, A08HiFiShellsTotalIn);
        updateLproductionMeeting.setInt(32, DaysRemainingIn);
        updateLproductionMeeting.setInt(33, PackedEndsIn);
        updateLproductionMeeting.setInt(34, MonthlyLineLoadIn);
        updateLproductionMeeting.setInt(35, RemainingIn);
        updateLproductionMeeting.setInt(36, DailyAverageIn);
        updateLproductionMeeting.setDouble(37, SpoiledPercentageIn);
        updateLproductionMeeting.setInt(38, DaysGoneIn);
        updateLproductionMeeting.setInt(39, LineLoadIn);
        updateLproductionMeeting.setInt(40, PackedVsLineLoadIn);
        updateLproductionMeeting.setString(41, dateF);

        updateLproductionMeeting.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void ProductionMeetingUpdate(String DateIn, String MeetingDateIn, String SafetyIssuesIn, String ProductionActionIn,
            String HygieneManagementOfChangeIn, String EngineeringActionsIn, int NonPetUnlinedSilverShellsIn, int NonPetlinedSilverShellsIn,
            int NonPetlinedSilverShellsTotalIn, int NonPetUnlinedGoldRx219In, int NonPetlinedGoldRx219In, int NonPetlinedGoldRx219TotalIn,
            int Mod4FUnlinedSilverShellsIn, int Mod4FlinedSilverShellsIn, int Mod4FlinedSilverShellsTotalIn, int NonPetUnlinedSilver215In,
            int NonPetlinedSilver215In, int NonPetlinedSilver215TotalIn, int A03HiFiShellsIn, int A04HiFiShellsIn, int A04HiFiShellsTotalIn,
            int A03HiFiShellsRX219In, int A04HiFiShellsRX219In, int A04HiFiShellsRX219TotalIn, int A13HiFiShellsIn, int A14HiFiShellsIn,
            int A14HiFiShellsTotalIn, int A07HiFiShellsIn, int A08HiFiShellsIn, int A08HiFiShellsTotalIn, int DaysRemainingIn, int PackedEndsIn,
            int MonthlyLineLoadIn, int RemainingIn, int DailyAverageIn, Double SpoiledPercentageIn, int DaysGoneIn, int LineLoadIn,
            int PackedVsLineLoadIn, int IDIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update ProductionMeeting set Date =?, MeetingDate =?, SafetyIssues =?, ProductionAction =?, "
                + "HygieneManagementOfChange =?, EngineeringActions =?, NonPetUnlinedSilverShells =?, NonPetlinedSilverShells =?,  "
                + "NonPetlinedSilverShellsTotal =?, NonPetUnlinedGoldRx219 =?, NonPetlinedGoldRx219 =?, NonPetlinedGoldRx219Total =?, "
                + "Mod4FUnlinedSilverShells =?, Mod4FlinedSilverShells =?, Mod4FlinedSilverShellsTotal =?, NonPetUnlinedSilver215 =?, "
                + "NonPetlinedSilver215 =?, NonPetlinedSilver215Total =?, A03HiFiShells =?, A04HiFiShells =?, A04HiFiShellsTotal =?, "
                + "A03HiFiShellsRX219 =?, A04HiFiShellsRX219 =?, A04HiFiShellsRX219Total =?, A13HiFiShells =?, A14HiFiShells =?, "
                + "A14HiFiShellsTotal =?, A07HiFiShells =?, A08HiFiShells =?, A08HiFiShellsTotal =?, DaysRemaining =?, PackedEnds =?, "
                + "MonthlyLineLoad =?, Remaining =?, DailyAverage =?, SpoiledPercentage = ?, DaysGone =?, LineLoad =?, PackedVsLineLoad =? where ID=?";

        // System.out.println(idIn);
        PreparedStatement updateProductionMeeting = conn.prepareStatement(sql);

        updateProductionMeeting.setString(1, DateIn);
        updateProductionMeeting.setString(2, MeetingDateIn);
        updateProductionMeeting.setString(3, SafetyIssuesIn);
        updateProductionMeeting.setString(4, ProductionActionIn);
        updateProductionMeeting.setString(5, HygieneManagementOfChangeIn);
        updateProductionMeeting.setString(6, EngineeringActionsIn);
        updateProductionMeeting.setInt(7, NonPetUnlinedSilverShellsIn);
        updateProductionMeeting.setInt(8, NonPetlinedSilverShellsIn);
        updateProductionMeeting.setInt(9, NonPetlinedSilverShellsTotalIn);
        updateProductionMeeting.setInt(10, NonPetUnlinedGoldRx219In);
        updateProductionMeeting.setInt(11, NonPetlinedGoldRx219In);
        updateProductionMeeting.setInt(12, NonPetlinedGoldRx219TotalIn);
        updateProductionMeeting.setInt(13, Mod4FUnlinedSilverShellsIn);
        updateProductionMeeting.setInt(14, Mod4FlinedSilverShellsIn);
        updateProductionMeeting.setInt(15, Mod4FlinedSilverShellsTotalIn);
        updateProductionMeeting.setInt(16, NonPetUnlinedSilver215In);
        updateProductionMeeting.setInt(17, NonPetlinedSilver215In);
        updateProductionMeeting.setInt(18, NonPetlinedSilver215TotalIn);
        updateProductionMeeting.setInt(19, A03HiFiShellsIn);
        updateProductionMeeting.setInt(20, A04HiFiShellsIn);
        updateProductionMeeting.setInt(21, A04HiFiShellsTotalIn);
        updateProductionMeeting.setInt(22, A03HiFiShellsRX219In);
        updateProductionMeeting.setInt(23, A04HiFiShellsRX219In);
        updateProductionMeeting.setInt(24, A04HiFiShellsRX219TotalIn);
        updateProductionMeeting.setInt(25, A13HiFiShellsIn);
        updateProductionMeeting.setInt(26, A14HiFiShellsIn);
        updateProductionMeeting.setInt(27, A14HiFiShellsTotalIn);
        updateProductionMeeting.setInt(28, A07HiFiShellsIn);
        updateProductionMeeting.setInt(29, A08HiFiShellsIn);
        updateProductionMeeting.setInt(30, A08HiFiShellsTotalIn);
        updateProductionMeeting.setInt(31, DaysRemainingIn);
        updateProductionMeeting.setInt(32, PackedEndsIn);
        updateProductionMeeting.setInt(33, MonthlyLineLoadIn);
        updateProductionMeeting.setInt(34, RemainingIn);
        updateProductionMeeting.setInt(35, DailyAverageIn);
        updateProductionMeeting.setDouble(36, SpoiledPercentageIn);
        updateProductionMeeting.setInt(37, DaysGoneIn);
        updateProductionMeeting.setInt(38, LineLoadIn);
        updateProductionMeeting.setInt(39, PackedVsLineLoadIn);
        updateProductionMeeting.setInt(40, IDIn);

        updateProductionMeeting.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] ProductionMeetingGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[41];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ProductionMeeting WHERE ProductionMeeting.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            for (int i = 0; i < result.length; i++) {
                result[i] = rs.getString(i + 1);
                //System.out.println("Row " + i + " : " + result[i]);
            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] ProductionMeetingGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[41];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ProductionMeeting WHERE ProductionMeeting.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            for (int i = 0; i < result.length; i++) {
                result[i] = rs.getString(i + 1);
                //System.out.println("Row " + i + " : " + result[i]);
            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel ProductionMeetingSummaryTable(int reportType) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);
        PreparedStatement psmt = conn.prepareStatement("");
        ResultSet rs = null;

        if (reportType == 1) {
            psmt = conn.prepareStatement("SELECT Date, MeetingDate, SafetyIssues, ProductionAction, HygieneManagementOfChange, EngineeringActions, ID  FROM ProductionMeeting ORDER BY Date DESC");
            psmt.setQueryTimeout(10);
            rs = psmt.executeQuery();
        } else if (reportType == 2) {
            psmt = conn.prepareStatement("SELECT Date, MeetingDate, SafetyIssues, ProductionAction, HygieneManagementOfChange, EngineeringActions, ID  FROM ProductionMeeting ORDER BY Date DESC");
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

                    int row = target.getSelectedRow() + 1;
                    System.out.println(table.getValueAt(table.getSelectedRow(), 6).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 6).toString();
                    int id = Integer.valueOf(idString);
                    try {
                        ProductionMeeting production = new ProductionMeeting(2, 2);
                        production.setProductionToID(id);
                    } catch (Exception ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // Employee Methods
    public static int EmployeeGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static boolean EmployeeExists(String IdIn) throws SQLException {

        boolean result = false;
        int count = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT Employees.ID FROM Employees WHERE Employees.ID = " + IdIn + ";";
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

    }

    public static int EmployeeGetLowestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] EmployeeReturnEntryByName(String nameIn) throws Exception {

        Object[] result = new Object[20];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Employees WHERE Employees.Name LIKE \"%" + nameIn + "%\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getBoolean(6);
            result[6] = rs.getBoolean(7);
            result[7] = rs.getBoolean(8);
            result[8] = rs.getBoolean(9);
            result[9] = rs.getBoolean(10);
            result[10] = rs.getBoolean(11);
            result[11] = rs.getBoolean(12);
            result[12] = rs.getBoolean(13);
            result[13] = rs.getBoolean(14);
            result[14] = rs.getBoolean(15);
            result[15] = rs.getBoolean(16);
            result[16] = rs.getBoolean(17);

            result[17] = rs.getString(18);
            result[18] = rs.getString(19);
            result[19] = rs.getString(20);

            // System.out.println("ID " + result[0]);
            // System.out.println("Name " + result[1]);
            // System.out.println("Address " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Job Title " + result[4]);
            // System.out.println("Phone Number " + result[5]);
            // System.out.println("Mobile Number " + result[6]);
            // System.out.println("TimeStamp " + result[7]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] EmployeeReturnEntryById(int idIn) throws Exception {

        Object[] result = new Object[15];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM Employees WHERE Employees.ID = " + idIn + ";";
        System.out.println(selTable);
        s.setQueryTimeout(3);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getBoolean(6);
            result[6] = rs.getBoolean(7);
            result[7] = rs.getBoolean(8);
            result[8] = rs.getBoolean(9);
            result[9] = rs.getBoolean(10);
            result[10] = rs.getBoolean(11);
            result[11] = rs.getBoolean(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);

        }

        // //////////////////////////////////////////////////////////////////////
        rs.close();
        s.close();
        conn.close();

        return result;

    }

    public static int EmployeeReturnIdByName(String NameIn) throws Exception {

        Object[] result = new Object[1];

        int id = 0;

        Connection conn = Connect();
        conn.setAutoCommit(false);
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT ID FROM Employees WHERE Employees.Name = \'" + NameIn + "\';";
        System.out.println(selTable);
        s.setQueryTimeout(3);
        s.execute(selTable);
        // conn.commit();

        ResultSet rs = s.getResultSet();

        id = rs.getInt(1);

        // //////////////////////////////////////////////////////////////////////
        conn.commit();
        rs.close();
        s.close();
        conn.close();

        System.out.println("ID : " + id);
        return id;

    }

    public static void EmployeeInsert(
            int idIn, String employeeIdIn, String nameIn, String addressIn, String crewIn, Boolean departmentHeadIn, Boolean shiftManagerIn,
            Boolean technicianIn, Boolean operatorIn, Boolean engineerIn, Boolean packerIn, Boolean qcInspectorIn, Boolean forkliftDriverIn, Boolean ProcessLeaderIn, Boolean ToolmakerIn, Boolean FitterIn, Boolean ElectricianIn, String phoneNumberIn,
            String mobileNumberIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement updateLproductionMeeting = conn.prepareStatement("insert into Employees values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        updateLproductionMeeting.setInt(1, idIn);
        updateLproductionMeeting.setString(2, employeeIdIn);
        updateLproductionMeeting.setString(3, nameIn);
        updateLproductionMeeting.setString(4, addressIn);
        updateLproductionMeeting.setString(5, crewIn);
        updateLproductionMeeting.setBoolean(6, departmentHeadIn);
        updateLproductionMeeting.setBoolean(7, shiftManagerIn);
        updateLproductionMeeting.setBoolean(8, technicianIn);
        updateLproductionMeeting.setBoolean(9, operatorIn);
        updateLproductionMeeting.setBoolean(10, engineerIn);
        updateLproductionMeeting.setBoolean(11, packerIn);
        updateLproductionMeeting.setBoolean(12, qcInspectorIn);

        updateLproductionMeeting.setBoolean(13, forkliftDriverIn);
        updateLproductionMeeting.setBoolean(14, ProcessLeaderIn);
        updateLproductionMeeting.setBoolean(15, ToolmakerIn);
        updateLproductionMeeting.setBoolean(16, FitterIn);
        updateLproductionMeeting.setBoolean(17, ElectricianIn);

        updateLproductionMeeting.setString(18, phoneNumberIn);
        updateLproductionMeeting.setString(19, mobileNumberIn);
        updateLproductionMeeting.setString(20, dateF);

        updateLproductionMeeting.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        updateLproductionMeeting.close();
        s.close();
        conn.close();

    }

    public static void EmployeeUpdate(
            String employeeIdIn, String nameIn, String addressIn, String crewIn, Boolean departmentHeadIn, Boolean shiftManagerIn, Boolean technicianIn,
            Boolean operatorIn, Boolean engineerIn, Boolean packerIn, Boolean qcInspectorIn, Boolean forkliftDriverIn, Boolean ProcessLeaderIn,
            Boolean ToolmakerIn, Boolean FitterIn, Boolean ElectricianIn, String phoneNumberIn, String mobileNumberIn, int idIn
    )
            throws SQLException {

        Connection conn = Connect();
        conn.setAutoCommit(false);
        Statement s = conn.createStatement();
        s.setQueryTimeout(2);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update Employees set EmployeeID=?, Name=? , Address=? , Crew=? , DepartmentHead=? , ShiftManager=? , Technician=?, Operator=?,  Engineer=?, Packer=?, QCInspector=?, ForkLiftDriver=?,  ProcessLeader=?,  ToolMaker=?,  Fitter=?,   Electrician=?, PhoneNumber=?, MobileNumber=? where ID=?";

        PreparedStatement updateEmployees = conn.prepareStatement(sql);
        updateEmployees.setQueryTimeout(10);

        updateEmployees.setString(1, employeeIdIn);
        updateEmployees.setString(2, nameIn);
        updateEmployees.setString(3, addressIn);
        updateEmployees.setString(4, crewIn);

        updateEmployees.setBoolean(5, departmentHeadIn);
        updateEmployees.setBoolean(6, shiftManagerIn);
        updateEmployees.setBoolean(7, technicianIn);
        updateEmployees.setBoolean(8, operatorIn);
        updateEmployees.setBoolean(9, engineerIn);
        updateEmployees.setBoolean(10, packerIn);
        updateEmployees.setBoolean(11, qcInspectorIn);
        updateEmployees.setBoolean(12, forkliftDriverIn);
        updateEmployees.setBoolean(13, ProcessLeaderIn);
        updateEmployees.setBoolean(14, ToolmakerIn);
        updateEmployees.setBoolean(15, FitterIn);
        updateEmployees.setBoolean(16, ElectricianIn);

        updateEmployees.setString(17, phoneNumberIn);
        updateEmployees.setString(18, mobileNumberIn);

        updateEmployees.setInt(19, idIn);

        updateEmployees.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        // updateEmployees.close();
        conn.commit();
        s.close();
        conn.close();

    }

    public static void EmployeeDelete(int idIn) throws SQLException {

        Connection conn = Connect();
        conn.setAutoCommit(false);
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String selTable = "Delete FROM Employees WHERE Employees.ID = " + idIn + ";";

        System.out.println("Error : " + selTable);

        s.execute(selTable);

        // /////////////////////////////////////////////////////////////////////////////
        conn.commit();
        s.close();
        conn.close();

    }

    public static Object[] EmployeeGetNextEntryById(String nameIn) throws SQLException {

        Object[] result = new Object[8];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable2 = "SELECT * FROM Employees WHERE Name > \"" + nameIn + "\" ORDER BY name ASC LIMIT 1";
        System.out.println("Query : " + selTable2);
        s.setQueryTimeout(10);
        s.execute(selTable2);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            String df2 = (String) rs.getObject(3);
            result[2] = df2;
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);

            for (int i = 0; i < result.length; i++) {

                System.out.println("Result : " + result[i]);

            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] EmployeeGetPreviousEntryById(String nameIn) throws SQLException {

        Object[] result = new Object[8];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable2 = "SELECT * FROM Employees WHERE Name < \"" + nameIn + "\" ORDER BY name DESC LIMIT 1";
        System.out.println("Query : " + selTable2);
        s.setQueryTimeout(10);
        s.execute(selTable2);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            String df2 = (String) rs.getObject(3);
            result[2] = df2;
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);

            for (int i = 0; i < result.length; i++) {

                System.out.println("Result : " + result[i]);

            }

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static int EmployeeGetRowCount() throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(5);
        ResultSet r = s.executeQuery("SELECT COUNT(Name) AS rowcount FROM Employees");
        r.next();
        int count = r.getInt("rowcount");
        System.out.println("Row Count : " + count);

        s.close();
        conn.close();
        r.close();

        return count;

    }

    public static Object[] EmployeeGetArraySorted() throws SQLException {

        Object[] array = new Object[EmployeeGetRowCount()];

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(5);
        ResultSet r = s.executeQuery("SELECT * FROM Employees ORDER by Name ASC");

        while ((r != null) && (r.next())) {

            System.out.println("Entry : " + r.getString(2));

        }

        s.close();
        conn.close();
        r.close();

        return array;

    }

    public static JPanel EmployeeSummaryTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement("SELECT "
                + "Name,"
                + "EmployeeID, "
                + "Address, "
                + "Crew, "
                + "DepartmentHead, "
                + "ShiftManager, "
                + "ProcessLeader, "
                + "Technician, "
                + "Operator, "
                + "Engineer, "
                + "Packer, "
                + "ForkliftDriver, "
                + "ToolMaker, "
                + "Fitter, "
                + "Electrician, "
                + "QCInspector, "
                + "PhoneNumber, "
                + "MobileNumber, "
                + "ID FROM Employees ORDER BY Name ASC");
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
            row.add(rs.getBoolean(5));
            row.add(rs.getBoolean(6));
            row.add(rs.getBoolean(7));
            row.add(rs.getBoolean(8));
            row.add(rs.getBoolean(9));
            row.add(rs.getBoolean(10));
            row.add(rs.getBoolean(11));
            row.add(rs.getBoolean(12));
            row.add(rs.getBoolean(13));
            row.add(rs.getBoolean(14));
            row.add(rs.getBoolean(15));
            row.add(rs.getBoolean(16));
            row.add(rs.getString(17));
            row.add(rs.getString(18));
            row.add(rs.getString(19));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(3).setMaxWidth(40);
        table.getColumnModel().getColumn(18).setMaxWidth(30);

        TableColumn tc = table.getColumnModel().getColumn(4);
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc1 = table.getColumnModel().getColumn(5);
        tc1.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc2 = table.getColumnModel().getColumn(6);
        tc2.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc3 = table.getColumnModel().getColumn(7);
        tc3.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc4 = table.getColumnModel().getColumn(8);
        tc4.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc5 = table.getColumnModel().getColumn(9);
        tc5.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc6 = table.getColumnModel().getColumn(10);
        tc6.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc7 = table.getColumnModel().getColumn(11);
        tc7.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc8 = table.getColumnModel().getColumn(12);
        tc8.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc9 = table.getColumnModel().getColumn(13);
        tc9.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc10 = table.getColumnModel().getColumn(14);
        tc10.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        TableColumn tc11 = table.getColumnModel().getColumn(15);
        tc11.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        psmt.close();
        stmt.close();
        conn.close();

        return outerPanel;

    }

    // Employees 2
    public static JPanel EmployeeSummaryTable2() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();

        // Read data from a table
        Connection conn = Connect();
        conn.setAutoCommit(false);
        String sql = "SELECT Name,EmployeeID, Address, Crew, DepartmentHead, ShiftManager, Technician, Operator, Engineer, Packer, QCInspector, PhoneNumber, MobileNumber, ID FROM Employees ORDER BY Name ASC";
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(5);
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();

        // Get column names
        for (int i = 1; i <= columns; i++) {
            columnNames.addElement(md.getColumnName(i));
        }

        // Get row data
        while (rs.next()) {
            Vector<Object> row = new Vector<Object>(columns);

            for (int i = 1; i <= columns; i++) {
                row.addElement(rs.getObject(i));
            }

            data.addElement(row);
        }
        conn.commit();
        rs.close();
        stmt.close();
        conn.close();

        // Create table with database data
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class getColumnClass(int column) {
                for (int row = 0; row < getRowCount(); row++) {
                    Object o = getValueAt(row, column);

                    if (o != null) {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JTable table = new JTable(model);

        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);
        return outerPanel;

    }

    public static void EmployeeDeleteByName(String nameIn) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = Connect();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from Employees where ID LIKE \'" + nameIn + "\';";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }

    public static void EmployeeDeleteByID(int idIn) {

    }

    // Liner Usage Methods
    public static int LinerUsageGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(LinerUsage.[ID]) FROM LinerUsage;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] LinerUsageReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[18];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerUsage WHERE LinerUsage.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LinerUsageReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[18];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerUsage WHERE LinerUsage.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);

            // System.out.println("ID " + result[0]);
            // System.out.println("Liner Number " + result[1]);
            // System.out.println("Date " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Lead Hand " + result[4]);
            // System.out.println("Reason " + result[5]);
            // System.out.println("Quantity Used " + result[6]);
            // System.out.println("PartNumber " + result[7]);
            // System.out.println("Gun1 " + result[8]);
            // System.out.println("Gun2 " + result[9]);
            // System.out.println("Gun3 " + result[10]);
            // System.out.println("Gun4 " + result[11]);
            // System.out.println("Gun5 " + result[12]);
            // System.out.println("Gun6 " + result[13]);
            // System.out.println("Gun7 " + result[14]);
            // System.out.println("Gun8 " + result[15]);
            // System.out.println("Comment " + result[16]);
            // System.out.println("TimeStamp " + result[17]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void LinerUsageInsert(int idIn, String linerNumberIn, String dateIn, String crewIn, String leadHandIn, String reasonIn,
            int quantityUsedIn, String partNumberIn, int gun1In, int gun2In, int gun3In, int gun4In, int gun5In, int gun6In, int gun7In, int gun8In,
            String commentIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement linerUsage = conn.prepareStatement("insert into LinerUsage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        linerUsage.setInt(1, idIn);
        linerUsage.setString(2, linerNumberIn);
        linerUsage.setString(3, dateIn);
        linerUsage.setString(4, crewIn);
        linerUsage.setString(5, leadHandIn);
        linerUsage.setString(6, reasonIn);
        linerUsage.setInt(7, quantityUsedIn);
        linerUsage.setString(8, partNumberIn);
        linerUsage.setInt(9, gun1In);
        linerUsage.setInt(10, gun2In);
        linerUsage.setInt(11, gun3In);
        linerUsage.setInt(12, gun4In);
        linerUsage.setInt(13, gun5In);
        linerUsage.setInt(14, gun6In);
        linerUsage.setInt(15, gun7In);
        linerUsage.setInt(16, gun8In);
        linerUsage.setString(17, commentIn);
        linerUsage.setString(18, dateF);

        linerUsage.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void LinerUsageUpdate(String linerNumberIn, String dateIn, String crewIn, String leadHandIn, String reasonIn, int quantityUsedIn,
            String partNumberIn, Boolean gun1In, Boolean gun2In, Boolean gun3In, Boolean gun4In, Boolean gun5In, Boolean gun6In, Boolean gun7In,
            Boolean gun8In, String commentIn, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update LinerUsage set LinerNumber=? , Date=? , Crew=? , LeadHand=? , Reason=? , "
                + "QuantityUsed=? , PartNumber=? , Gun1=? , Gun2=? , Gun3=? , Gun4=? , Gun5=? , "
                + "Gun6=?  , Gun7=? , Gun8=? , Comment=? where ID=?";

        System.out.println(idIn);

        PreparedStatement updateEmployees = conn.prepareStatement(sql);

        updateEmployees.setString(1, linerNumberIn);
        updateEmployees.setString(2, dateIn);
        updateEmployees.setString(3, crewIn);
        updateEmployees.setString(4, leadHandIn);
        updateEmployees.setString(5, reasonIn);
        updateEmployees.setInt(6, quantityUsedIn);
        updateEmployees.setString(7, partNumberIn);
        updateEmployees.setBoolean(8, gun1In);
        updateEmployees.setBoolean(9, gun2In);
        updateEmployees.setBoolean(10, gun3In);
        updateEmployees.setBoolean(11, gun4In);
        updateEmployees.setBoolean(12, gun5In);
        updateEmployees.setBoolean(13, gun6In);
        updateEmployees.setBoolean(14, gun7In);
        updateEmployees.setBoolean(15, gun8In);
        updateEmployees.setString(16, commentIn);
        updateEmployees.setInt(17, idIn);

        updateEmployees.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] LinerUsageGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[18];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerUsage WHERE LinerUsage.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);

            // System.out.println("ID " + result[0]);
            // System.out.println("Liner Number " + result[1]);
            // System.out.println("Date " + result[2]);
            // System.out.println("Crew " + result[3]);
            // System.out.println("Lead Hand " + result[4]);
            // System.out.println("Reason " + result[5]);
            // System.out.println("Quantity Used " + result[6]);
            // System.out.println("PartNumber " + result[7]);
            // System.out.println("Gun1 " + result[8]);
            // System.out.println("Gun2 " + result[9]);
            // System.out.println("Gun3 " + result[10]);
            // System.out.println("Gun4 " + result[11]);
            // System.out.println("Gun5 " + result[12]);
            // System.out.println("Gun6 " + result[13]);
            // System.out.println("Gun7 " + result[14]);
            // System.out.println("Gun8 " + result[15]);
            // System.out.println("Comment " + result[16]);
            // System.out.println("TimeStamp " + result[17]);
            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] LinerUsageGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[18];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerUsage WHERE LinerUsage.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getString(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);

            System.out.println("ID " + result[0]);
            System.out.println("Liner Number " + result[1]);
            System.out.println("Date " + result[2]);
            System.out.println("Crew " + result[3]);
            System.out.println("Lead Hand " + result[4]);
            System.out.println("Reason " + result[5]);
            System.out.println("Quantity Used " + result[6]);
            System.out.println("PartNumber " + result[7]);
            System.out.println("Gun1 " + result[8]);
            System.out.println("Gun2 " + result[9]);
            System.out.println("Gun3 " + result[10]);
            System.out.println("Gun4 " + result[11]);
            System.out.println("Gun5 " + result[12]);
            System.out.println("Gun6 " + result[13]);
            System.out.println("Gun7 " + result[14]);
            System.out.println("Gun8 " + result[15]);
            System.out.println("Comment " + result[16]);
            System.out.println("TimeStamp " + result[17]);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    // Liner And Shells Menu
    public static int LinerAndShellsGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(LinerAndShells.[ID]) FROM LinerAndShells;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] LinerAndShellsReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[12];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerAndShells WHERE LinerAndShells.DateString = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LinerAndShellsReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[18];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerAndShells WHERE LinerAndShells.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void LinerAndShellsInsert(int idIn, String dateIn, int optime2, int optime3, int optime4, int shellsTotal, int m1liner,
            int m2liner, int m3liner, int m4liner, int linersTotal) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement linerAndShells = conn.prepareStatement("insert into LinerAndShells values(?,?,?,?,?,?,?,?,?,?,?,?)");

        linerAndShells.setInt(1, idIn);
        linerAndShells.setString(2, dateIn);
        linerAndShells.setInt(3, optime2);
        linerAndShells.setInt(4, optime3);
        linerAndShells.setInt(5, optime4);
        linerAndShells.setInt(6, shellsTotal);
        linerAndShells.setInt(7, m1liner);
        linerAndShells.setInt(8, m2liner);
        linerAndShells.setInt(9, m3liner);
        linerAndShells.setInt(10, m4liner);
        linerAndShells.setInt(11, linersTotal);
        linerAndShells.setString(12, dateF);

        linerAndShells.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void LinerAndShellsUpdate(int optime2In, int optime3In, int optime4In, int shellsTotal, int m1linerIn, int m2linerIn,
            int m3linerIn, int m4linerIn, int linersTotal, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        shellsTotal = (optime2In + optime3In + optime4In);
        linersTotal = (m1linerIn + m2linerIn + m3linerIn + m4linerIn);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update LinerAndShells set Optime2=? , Optime3=? , Optime4=? , TotalShells=?, M1liner=? , "
                + "M2liner=? , M3liner=? , M4liner=? , TotalLiners=? where ID=?";

        System.out.println(idIn);

        PreparedStatement updateLinersAndShells = conn.prepareStatement(sql);

        updateLinersAndShells.setInt(1, optime2In);
        updateLinersAndShells.setInt(2, optime3In);
        updateLinersAndShells.setInt(3, optime4In);
        updateLinersAndShells.setInt(4, shellsTotal);
        updateLinersAndShells.setInt(5, m1linerIn);
        updateLinersAndShells.setInt(6, m2linerIn);
        updateLinersAndShells.setInt(7, m3linerIn);
        updateLinersAndShells.setInt(8, m4linerIn);
        updateLinersAndShells.setInt(9, linersTotal);
        updateLinersAndShells.setInt(10, idIn);

        updateLinersAndShells.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] LinerAndShellsGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[12];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerAndShells WHERE LinerAndShells.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] LinerAndShellsGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[12];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerAndShells WHERE LinerAndShells.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static int[] LinerAndShellsCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int[] total = new int[10];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(Optime2) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(Optime3) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(Optime4) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M1Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(M2Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(M3Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(M4Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total[0] = rs1.getInt(1);
            System.out.println("Optime 2 : " + total[0]);

        }

        // ///////////////////////////
        // Optime 3 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total[1] = rs2.getInt(1);
            System.out.println(total[1]);

        }

        // ///////////////////////////
        // Optime 4 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total[2] = rs3.getInt(1);
            System.out.println(total[2]);

        }

        // ///////////////////////////
        // Optime Total /////////////////
        int optimeTotal = (total[0] + total[1] + total[2]);
        System.out.println("Optime Total : " + optimeTotal);
        total[3] = optimeTotal;

        // ///////////////////////////
        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total[4] = rs4.getInt(1);
            System.out.println(total[3]);

        }

        // ///////////////////////////
        // M2Liner /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();

        while ((rs5 != null) && (rs5.next())) {

            total[5] = rs5.getInt(1);
            System.out.println(total[4]);

        }

        // ///////////////////////////
        // M3Liner /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();

        while ((rs6 != null) && (rs6.next())) {

            total[6] = rs6.getInt(1);
            System.out.println(total[5]);

        }

        // ///////////////////////////
        // M4Liner /////////////////
        s.execute(sql7);

        ResultSet rs7 = s.getResultSet();

        while ((rs7 != null) && (rs7.next())) {

            total[7] = rs7.getInt(1);
            System.out.println(total[6]);

        }

        // ///////////////////////////
        // Liners Total /////////////////
        int linersTotal = (total[4] + total[5] + total[6] + total[7]);
        System.out.println("Liners Total : " + linersTotal);
        total[8] = linersTotal;

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int LinersAndShellsGetTotal(String totalIn, String monthIn, String yearIn) throws SQLException {

        // Should be SUM of all totals for selected month.
        int total = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT SUM(TotalShells) FROM LinerAndShells WHERE LinerAndShells.DateString LIKE '%" + date + "%';";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            total = rs.getInt(1);
            // System.out.println("Total : " + total);

        }

        rs.close();
        s.close();
        conn.close();

        return total;
    }

    public static JPanel LinersAndShellsSummaryTable(int in) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement("SELECT DateString as Date, Optime2, Optime3, Optime4, TotalShells, M1Liner, M2Liner, M3Liner, M4Liner, TotalLIners, ID FROM LinerAndShells ORDER BY Date DESC");
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

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(80);

        table.getColumnModel().getColumn(10).setMaxWidth(40);

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
                    LinerAndShellsEntry linersAndShells;
                    try {
                        linersAndShells = new LinerAndShellsEntry(1, -2);
                        linersAndShells.setLinersAndShellsToID(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // Liner Defects
    public static int LinerDefectsGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(LinerDefects.[ID]) FROM LinerDefects;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] LinerDefectsReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[14];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerDefects WHERE LinerDefects.DateString = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;

            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getDouble(12);
            result[11] = rs.getDouble(12);
            result[12] = rs.getDouble(13);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] LinerDefectsReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[13];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerDefects WHERE LinerDefects.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[11] = rs.getInt(12);
            result[12] = rs.getDouble(13);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void LinerDefectsInsert(int idIn, String dateIn, int M1LinerIn, int M1DefectsIn, int M2LinerIn, int M2DefectsIn, int M3LinerIn,
            int M3DefectsIn, int M4LinerIn, int M4DefectsIn, Double linerSpoiledPercentageIn, Double totalLinedIn, Double totalDefectsIn)
            throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        Double totalLined = (M1LinerIn * 1.0 + M2LinerIn * 1.0 + M3LinerIn * 1.0 + M4LinerIn * 1.0);
        System.out.println("Total Lined : " + totalLined);
        Double totalDefects = (M1DefectsIn * 1.0 + M2DefectsIn * 1.0 + M3DefectsIn * 1.0 + M4DefectsIn * 1.0);
        System.out.println("Total Defects : " + totalDefects);
        Double linerSpoiledPercentage = ((totalDefects / totalLined * 100));
        System.out.println("Total Spoiled %  : " + linerSpoiledPercentage);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement LinerDefectsUpdate = conn.prepareStatement("insert into LinerDefects values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        LinerDefectsUpdate.setInt(1, idIn);
        LinerDefectsUpdate.setString(2, dateIn);
        LinerDefectsUpdate.setInt(3, M1LinerIn);
        LinerDefectsUpdate.setInt(4, M1DefectsIn);
        LinerDefectsUpdate.setInt(5, M2LinerIn);
        LinerDefectsUpdate.setInt(6, M2DefectsIn);
        LinerDefectsUpdate.setInt(7, M3LinerIn);
        LinerDefectsUpdate.setInt(8, M3DefectsIn);
        LinerDefectsUpdate.setInt(9, M4LinerIn);
        LinerDefectsUpdate.setInt(10, M4DefectsIn);
        LinerDefectsUpdate.setDouble(11, totalLined);
        LinerDefectsUpdate.setDouble(12, totalDefects);
        LinerDefectsUpdate.setDouble(13, linerSpoiledPercentage);
        LinerDefectsUpdate.setString(14, dateF);

        LinerDefectsUpdate.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void LinerDefectsUpdate(int M1LinerIn, int M1DefectsIn, int M2LinerIn, int M2DefectsIn, int M3LinerIn, int M3DefectsIn,
            int M4LinerIn, int M4DefectsIn, Double linerSpoiledPercentageIn, Double totalLinedIn, Double totalDefectsIn, int idIn)
            throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        Double totalLined = (M1LinerIn * 1.0 + M2LinerIn * 1.0 + M3LinerIn * 1.0 + M4LinerIn * 1.0);
        System.out.println("Total Lined : " + totalLined);
        Double totalDefects = (M1DefectsIn * 1.0 + M2DefectsIn * 1.0 + M3DefectsIn * 1.0 + M4DefectsIn * 1.0);
        System.out.println("Total Defects : " + totalDefects);
        Double linerSpoiledPercentage = ((totalDefects / totalLined * 100));
        System.out.println("Total Spoiled %  : " + linerSpoiledPercentage);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update LinerDefects set M1Liner=? , M1Defects=? , M2Liner=? , M2Defects=?, M3Liner=? , "
                + "M3Defects=? , M4Liner=? , M4Defects=? , TotalLined=? , TotalDefects=?, LinerSpoiledPercentage=?  where ID=?";

        System.out.println(idIn);

        PreparedStatement updateLinerDefects = conn.prepareStatement(sql);

        updateLinerDefects.setInt(1, M1LinerIn);
        updateLinerDefects.setInt(2, M1DefectsIn);
        updateLinerDefects.setInt(3, M2LinerIn);
        updateLinerDefects.setInt(4, M2DefectsIn);
        updateLinerDefects.setInt(5, M3LinerIn);
        updateLinerDefects.setInt(6, M3DefectsIn);
        updateLinerDefects.setInt(7, M4LinerIn);
        updateLinerDefects.setInt(8, M4DefectsIn);
        updateLinerDefects.setDouble(9, totalLined);
        updateLinerDefects.setDouble(10, totalDefects);
        updateLinerDefects.setDouble(11, linerSpoiledPercentage);
        updateLinerDefects.setInt(12, idIn);

        updateLinerDefects.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] LinerDefectsGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[13];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerDefects WHERE LinerDefects.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[11] = rs.getInt(12);
            result[12] = rs.getDouble(13);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] LinerDefectsGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[13];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM LinerDefects WHERE LinerDefects.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[11] = rs.getInt(12);
            result[12] = rs.getDouble(13);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] LinerDefectsCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        Object[] total = new Object[11];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(M1Liner) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(M2Liner) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(M3Liner) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M4Liner) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(TotalLined) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(TotalDefects) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";

        // Defects
        String sql7 = "SELECT SUM(M1Defects) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(M2Defects) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(M3Defects) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(M4Defects) FROM LinerDefects WHERE DateString LIKE '%" + date + "%';";

        // ((SQL5/SQL6)*100) = Total Defects %
        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total[0] = rs1.getInt(1);
            System.out.println("Optime 2 : " + total[0]);

        }

        // ///////////////////////////
        // M2Liner //////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total[1] = rs2.getInt(1);
            System.out.println(total[1]);

        }

        // ///////////////////////////
        // M3Liner /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total[2] = rs3.getInt(1);
            System.out.println(total[2]);

        }

        // ///////////////////////////
        // M4Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total[3] = rs4.getInt(1);
            System.out.println(total[3]);

        }

        // ///////////////////////////
        // M1 Defects /////////////////
        s.execute(sql7);

        ResultSet rs5 = s.getResultSet();

        while ((rs5 != null) && (rs5.next())) {

            total[6] = rs5.getInt(1);
            System.out.println("M1 Defects" + total[6]);

        }

        // ///////////////////////////
        // M2 Defects /////////////////
        s.execute(sql8);

        ResultSet rs6 = s.getResultSet();

        while ((rs6 != null) && (rs6.next())) {

            total[7] = rs6.getInt(1);
            System.out.println("M2 Defects" + total[7]);

        }

        // ///////////////////////////
        // M3 Defects /////////////////
        s.execute(sql9);

        ResultSet rs7 = s.getResultSet();

        while ((rs7 != null) && (rs7.next())) {

            total[8] = rs7.getInt(1);
            System.out.println("M3 Defects" + total[8]);

        }

        // ///////////////////////////
        // M4 Defects /////////////////
        s.execute(sql10);

        ResultSet rs8 = s.getResultSet();

        while ((rs8 != null) && (rs8.next())) {

            total[9] = rs8.getInt(1);
            System.out.println("M4 Defects" + total[9]);

        }

        // ///////////////////////////
        // Sum Total Lined /////////////////
        int TotalLined = ((int) total[0] + (int) total[1] + (int) total[2] + (int) total[3]);
        System.out.println("Total Lined : " + TotalLined);
        total[4] = TotalLined;

        // ///////////////////////////
        // Sum Total Defects /////////////////
        int TotalDefects = ((int) total[6] + (int) total[7] + (int) total[8] + (int) total[9]);
        System.out.println("Total Defects : " + TotalDefects);
        total[10] = TotalDefects;

        // //////////////////////////////////
        // Total Defects % /////////////////
        Double one = (int) total[4] * 1.0;
        Double two = (int) total[10] * 1.0;
        Double three = one / two;
        Double four = three / 100;

        System.out.println("One " + one);

        System.out.println("Two " + two);

        System.out.println("Three " + three);

        System.out.println("Four " + four);

        Double totalDefectsPercentage = four;

        total[6] = totalDefectsPercentage;

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        // rs5.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    // End Counts
    public static int EndCountsGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(EndCounts.[ID]) FROM EndCounts;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] EndCountsReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM EndCounts WHERE EndCounts.DateString = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] EndCountsReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[18];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM EndCounts WHERE EndCounts.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void EndCountsInsert(int idIn, String dateIn, int W11In, int W12In, int W21In, int W22In, int W31In, int W32In, int W33In,
            int total1In, int W41In, int W42In, int W43In, int W44In, int total2In, int total3In) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        int total1 = (W11In + W12In + W21In + W22In + W31In + W32In + W33In);
        int total2 = (W41In + W42In + W43In + W44In);
        int total3 = total1 + total2;

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement EndCountsInsert = conn.prepareStatement("insert into EndCounts values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        EndCountsInsert.setInt(1, idIn);
        EndCountsInsert.setString(2, dateIn);
        EndCountsInsert.setInt(3, W11In);
        EndCountsInsert.setInt(4, W12In);
        EndCountsInsert.setInt(5, W21In);
        EndCountsInsert.setInt(6, W22In);
        EndCountsInsert.setInt(7, W31In);
        EndCountsInsert.setInt(8, W32In);
        EndCountsInsert.setInt(9, W33In);
        EndCountsInsert.setInt(10, total1);
        EndCountsInsert.setInt(11, W41In);
        EndCountsInsert.setInt(12, W42In);
        EndCountsInsert.setInt(13, W43In);
        EndCountsInsert.setInt(14, W44In);
        EndCountsInsert.setInt(15, total2);
        EndCountsInsert.setInt(16, total3);
        EndCountsInsert.setString(17, dateF);

        EndCountsInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void EndCountsUpdate(String dateIn, int W11In, int W12In, int W21In, int W22In, int W31In, int W32In, int W33In, int total1In,
            int W41In, int W42In, int W43In, int W44In, int total2In, int total3In, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        int total1 = (W11In + W12In + W21In + W22In + W31In + W32In + W33In);
        int total2 = (W41In + W42In + W43In + W44In);
        int total3 = total1 + total2;

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update EndCounts set W11=? , W12=? , W21=? , W22=?, W31=? , "
                + "W32=? , W33=? , total1=? , W41=? , W42=?, W43=?, W44=?, total2=?, total3=?  where ID=?";

        System.out.println(idIn);

        PreparedStatement EndCountsInsert = conn.prepareStatement(sql);

        EndCountsInsert.setInt(1, W11In);
        EndCountsInsert.setInt(2, W12In);
        EndCountsInsert.setInt(3, W21In);
        EndCountsInsert.setInt(4, W22In);
        EndCountsInsert.setInt(5, W31In);
        EndCountsInsert.setInt(6, W32In);
        EndCountsInsert.setInt(7, W33In);
        EndCountsInsert.setInt(8, total1);
        EndCountsInsert.setInt(9, W41In);
        EndCountsInsert.setInt(10, W42In);
        EndCountsInsert.setInt(11, W43In);
        EndCountsInsert.setInt(12, W44In);
        EndCountsInsert.setInt(13, total2);
        EndCountsInsert.setInt(14, total3);
        EndCountsInsert.setInt(15, idIn);

        EndCountsInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] EndCountsGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM EndCounts WHERE EndCounts.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] EndCountsGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM EndCounts WHERE EndCounts.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] EndCountsCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        Object[] total = new Object[14];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W21) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W22) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(W31) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(W33) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Total1) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(W41) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(W42) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(W43) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql12 = "SELECT SUM(W44) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql13 = "SELECT SUM(Total2) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql14 = "SELECT SUM(Total3) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(5);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            total[0] = rs1.getInt(1);
        }

        // ///////////////////////////
        // W12 //////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            total[1] = rs2.getInt(1);
        }

        // ///////////////////////////
        // W21 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            total[2] = rs3.getInt(1);
        }

        // ///////////////////////////
        // W22 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            total[3] = rs4.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total[4] = rs5.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total[5] = rs6.getInt(1);
        }

        // ///////////////////////////
        // W33 /////////////////
        s.execute(sql7);

        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            total[6] = rs7.getInt(1);
        }

        // ///////////////////////////
        // Total1 /////////////////
        s.execute(sql8);

        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            total[7] = rs8.getInt(1);
        }

        // ///////////////////////////
        // W41 /////////////////
        s.execute(sql9);

        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            total[8] = rs9.getInt(1);
        }

        // ///////////////////////////
        // W42 /////////////////
        s.execute(sql10);

        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            total[9] = rs10.getInt(1);
        }

        // //////////////////////////////////
        // W43 /////////////////
        s.execute(sql11);

        ResultSet rs11 = s.getResultSet();
        while ((rs11 != null) && (rs11.next())) {
            total[10] = rs11.getInt(1);
        }

        // ///////////////////////////
        // W44 /////////////////
        s.execute(sql12);

        ResultSet rs12 = s.getResultSet();
        while ((rs12 != null) && (rs12.next())) {
            total[11] = rs12.getInt(1);
        }

        // ///////////////////////////
        // Total2 /////////////////
        s.execute(sql13);

        ResultSet rs13 = s.getResultSet();
        while ((rs13 != null) && (rs13.next())) {
            total[12] = rs13.getInt(1);
        }

        // ///////////////////////////
        // Total3 /////////////////
        s.execute(sql14);

        ResultSet rs14 = s.getResultSet();
        while ((rs14 != null) && (rs14.next())) {
            total[13] = rs14.getInt(1);
        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        rs9.close();
        rs10.close();
        rs11.close();
        rs12.close();
        rs13.close();
        rs14.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static JPanel EndCountsSummaryTable(int in) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement("SELECT DateString as Date, W11, W12, W21, W22, W31, W32, W33, Total1, W41, W42, W43, W44, Total2, Total3, ID FROM EndCounts ORDER BY DateString DESC");
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
            row.add(rs.getString(6));
            row.add(rs.getString(7));
            row.add(rs.getString(8));
            row.add(rs.getString(9));
            row.add(rs.getString(10));
            row.add(rs.getString(11));
            row.add(rs.getString(12));
            row.add(rs.getString(13));
            row.add(rs.getString(14));
            row.add(rs.getString(15));
            row.add(rs.getString(16));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(100);

        table.getColumnModel().getColumn(15).setMaxWidth(40);

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
                    System.out.println(table.getValueAt(table.getSelectedRow(), 15).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 15).toString();
                    int id = Integer.valueOf(idString);
                    LinerAndShellsEntry linersAndShells;
                    try {
                        EndCounts endCounts = new EndCounts(1, -2);
                        endCounts.setEndCountsToID(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // Spoilage By Day
    public static int SpoilageByDayGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(SpoilageByDay.[ID]) FROM SpoilageByDay;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            System.out.println("Highest ID Method Output:  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] SpoilageByDayReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[35];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM SpoilageByDay WHERE SpoilageByDay.DateString = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);
            result[17] = rs.getInt(18);
            result[18] = rs.getInt(19);
            result[19] = rs.getInt(20);
            result[20] = rs.getInt(21);
            result[21] = rs.getInt(22);
            result[22] = rs.getInt(23);
            result[23] = rs.getInt(24);
            result[24] = rs.getInt(25);
            result[25] = rs.getInt(26);
            result[26] = rs.getInt(27);
            result[27] = rs.getInt(28);
            result[28] = rs.getInt(29);
            result[29] = rs.getInt(30);
            result[30] = rs.getInt(31);
            result[31] = rs.getInt(32);
            result[32] = rs.getInt(33);
            result[33] = rs.getInt(34);
            result[34] = rs.getInt(35);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] SpoilageByDayReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[13];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM SpoilageByDay WHERE SpoilageByDay.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getInt(9);
            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void SpoilageByDayInsert(int idIn, String dateIn, int optime1and2In, int optime3In, int m1BBalIn, int m3BBalIn, int m1LinerIn,
            int m2LinerIn, int stolle11In, int stolle22In, int m3ABalIn, int m1ABalIn, int ovecTesterIn, int qCLabIn, int bordenNo1In,
            int m4QcAreaIn, int stolle21In, int stolle33In, int stolle12In, int stolle31In, int stolle32In, int m2BBalIn, int m2ABalIn,
            int m3LinersIn, int m3QcAreaIn, int stolle42In, int m4B2BalIn, int m4LinersIn, int qCMod1In, int stolle41In, int stolle43In,
            int stolle44In, int balancer4BIn, int balancer4AIn, int formatecIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement SpoilageByDayInsert = conn
                .prepareStatement("insert into SpoilageByDay values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        SpoilageByDayInsert.setInt(1, idIn);
        SpoilageByDayInsert.setString(2, dateIn);
        SpoilageByDayInsert.setInt(3, optime1and2In);
        SpoilageByDayInsert.setInt(4, optime3In);
        SpoilageByDayInsert.setInt(5, m1BBalIn);
        SpoilageByDayInsert.setInt(6, m3BBalIn);
        SpoilageByDayInsert.setInt(7, m1LinerIn);
        SpoilageByDayInsert.setInt(8, m2LinerIn);
        SpoilageByDayInsert.setInt(9, stolle11In);
        SpoilageByDayInsert.setInt(10, stolle22In);
        SpoilageByDayInsert.setInt(11, m3ABalIn);
        SpoilageByDayInsert.setInt(12, m1ABalIn);
        SpoilageByDayInsert.setInt(13, ovecTesterIn);
        SpoilageByDayInsert.setInt(14, qCLabIn);
        SpoilageByDayInsert.setInt(15, bordenNo1In);
        SpoilageByDayInsert.setInt(16, m4QcAreaIn);
        SpoilageByDayInsert.setInt(17, stolle21In);
        SpoilageByDayInsert.setInt(18, stolle33In);
        SpoilageByDayInsert.setInt(18, stolle12In);
        SpoilageByDayInsert.setInt(19, stolle31In);
        SpoilageByDayInsert.setInt(20, stolle32In);
        SpoilageByDayInsert.setInt(21, m2BBalIn);
        SpoilageByDayInsert.setInt(22, m2ABalIn);
        SpoilageByDayInsert.setInt(23, m3LinersIn);
        SpoilageByDayInsert.setInt(24, m3QcAreaIn);
        SpoilageByDayInsert.setInt(25, stolle42In);
        SpoilageByDayInsert.setInt(26, m4B2BalIn);
        SpoilageByDayInsert.setInt(27, m1ABalIn);
        SpoilageByDayInsert.setInt(28, m4LinersIn);
        SpoilageByDayInsert.setInt(29, qCMod1In);
        SpoilageByDayInsert.setInt(30, stolle41In);
        SpoilageByDayInsert.setInt(31, stolle43In);
        SpoilageByDayInsert.setInt(32, stolle44In);
        SpoilageByDayInsert.setInt(33, balancer4BIn);
        SpoilageByDayInsert.setInt(34, balancer4AIn);
        SpoilageByDayInsert.setInt(35, formatecIn);
        SpoilageByDayInsert.setString(36, dateF);

        SpoilageByDayInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void SpoilageByDayUpdate(int optime1and2In, int optime3In, int m1BBalIn, int m3BBalIn, int m1LinerIn, int m2LinerIn,
            int stolle11In, int stolle22In, int m3ABalIn, int m1ABalIn, int ovecTesterIn, int qCLabIn, int bordenNo1In, int m4QcAreaIn,
            int stolle21In, int stolle33In, int stolle12In, int stolle31In, int stolle32In, int m2BBalIn, int m2ABalIn, int m3LinersIn,
            int m3QcAreaIn, int stolle42In, int m4B2BalIn, int m4LinersIn, int qCMod1In, int stolle41In, int stolle43In, int stolle44In,
            int balancer4BIn, int balancer4AIn, int formatecIn, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "UPDATE SpoilageByDay SET Optime1and2=?,  "
                + "Optime3=?, M1BBal=?,  M3BBal=?, M1Liner=?,  M2Liner=?,  Stolle11=?,  Stolle22=?, "
                + "M3ABal=?,  M1ABal=?,  OvecTester=?,  QCLab=?, " + "BordenNo1=?,  M4QcArea=?,  Stolle21=?,  Stolle33=?, "
                + "Stolle12=?,  Stolle31=?,  Stolle32=?,  M2BBal=?, M2ABal=?, " + "M3Liners=?,  M3QcArea=?,  Stolle42=?,  M4B2Bal=?, m4Liners=?,  "
                + "QCMod1=?,  Stolle41=?,  Stolle43=?, " + "Stolle44=?,  Balancer4B=?,  Balancer4A=?, Formatec=? WHERE ID=?";

        System.out.println("Id Now : " + idIn);
        System.out.println("SQL : " + sql);

        System.out.println(optime1and2In);
        System.out.println(optime3In);
        System.out.println(m1BBalIn);
        System.out.println(m3BBalIn);
        System.out.println(m1LinerIn);
        System.out.println(m2LinerIn);
        System.out.println(stolle11In);
        System.out.println(stolle22In);
        System.out.println(m3ABalIn);
        System.out.println(m1ABalIn);
        System.out.println(ovecTesterIn);
        System.out.println(qCLabIn);
        System.out.println(bordenNo1In);
        System.out.println(m4QcAreaIn);
        System.out.println(stolle21In);
        System.out.println(stolle33In);
        System.out.println(stolle12In);
        System.out.println(stolle31In);
        System.out.println(stolle32In);
        System.out.println(m2BBalIn);
        System.out.println(m2ABalIn);
        System.out.println(m3LinersIn);
        System.out.println(m3QcAreaIn);
        System.out.println(stolle42In);
        System.out.println(m4B2BalIn);
        System.out.println(m4LinersIn);
        System.out.println(qCMod1In);
        System.out.println(stolle41In);
        System.out.println(stolle43In);
        System.out.println(stolle44In);
        System.out.println(balancer4BIn);
        System.out.println(balancer4AIn);
        System.out.println(formatecIn);
        System.out.println("ID " + idIn);

        PreparedStatement SpoilageByDayUpdate = conn.prepareStatement(sql);

        SpoilageByDayUpdate.setInt(1, optime1and2In);
        SpoilageByDayUpdate.setInt(2, optime3In);
        SpoilageByDayUpdate.setInt(3, m1BBalIn);
        SpoilageByDayUpdate.setInt(4, m3BBalIn);
        SpoilageByDayUpdate.setInt(5, m1LinerIn);
        SpoilageByDayUpdate.setInt(6, m2LinerIn);
        SpoilageByDayUpdate.setInt(7, stolle11In);
        SpoilageByDayUpdate.setInt(8, stolle22In);
        SpoilageByDayUpdate.setInt(9, m3ABalIn);
        SpoilageByDayUpdate.setInt(10, m1ABalIn);
        SpoilageByDayUpdate.setInt(11, ovecTesterIn);
        SpoilageByDayUpdate.setInt(12, qCLabIn);
        SpoilageByDayUpdate.setInt(13, bordenNo1In);
        SpoilageByDayUpdate.setInt(14, m4QcAreaIn);
        SpoilageByDayUpdate.setInt(15, stolle21In);
        SpoilageByDayUpdate.setInt(16, stolle33In);
        SpoilageByDayUpdate.setInt(17, stolle12In);
        SpoilageByDayUpdate.setInt(18, stolle31In);
        SpoilageByDayUpdate.setInt(19, stolle32In);
        SpoilageByDayUpdate.setInt(20, m2BBalIn);
        SpoilageByDayUpdate.setInt(21, m2ABalIn);
        SpoilageByDayUpdate.setInt(22, m3LinersIn);
        SpoilageByDayUpdate.setInt(23, m3QcAreaIn);
        SpoilageByDayUpdate.setInt(24, stolle42In);
        SpoilageByDayUpdate.setInt(25, m4B2BalIn);
        SpoilageByDayUpdate.setInt(26, m4LinersIn);
        SpoilageByDayUpdate.setInt(27, qCMod1In);
        SpoilageByDayUpdate.setInt(28, stolle41In);
        SpoilageByDayUpdate.setInt(29, stolle43In);
        SpoilageByDayUpdate.setInt(30, stolle44In);
        SpoilageByDayUpdate.setInt(31, balancer4BIn);
        SpoilageByDayUpdate.setInt(32, balancer4AIn);
        SpoilageByDayUpdate.setInt(33, formatecIn);
        SpoilageByDayUpdate.setInt(34, idIn);

        SpoilageByDayUpdate.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] SpoilageByDayGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[36];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM SpoilageByDay WHERE SpoilageByDay.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);
            result[18] = rs.getString(19);
            result[19] = rs.getString(20);
            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] SpoilageByDayGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[36];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM SpoilageByDay WHERE SpoilageByDay.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);
            result[17] = rs.getString(18);
            result[18] = rs.getString(19);
            result[19] = rs.getString(20);
            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] SpoilageByDayCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        // System.out.println("Date : " + date);
        Object[] total = new Object[14];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W21) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W22) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(W31) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(W33) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Total1) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(W41) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(W42) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(W43) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql12 = "SELECT SUM(W44) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql13 = "SELECT SUM(Total2) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql14 = "SELECT SUM(Total3) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(5);

        // W11 /////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            total[0] = rs1.getInt(1);
        }
		// ///////////////////////////

        // rs1.close();
        // rs2.close();
        // rs3.close();
        // rs4.close();
        // rs5.close();
        // rs6.close();
        // rs7.close();
        // rs8.close();
        // rs9.close();
        // rs10.close();
        // rs11.close();
        // rs12.close();
        // rs13.close();
        // rs14.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    // Spoilage By Day Table
    public static int[] SpoilageByDayGetMonthsTotal(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[33];

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(Optime1And2) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        System.out.println(sql1);
        String sql2 = "SELECT SUM(Optime3) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(M1BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M3BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(M1Liner) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(M2Liner) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(Stolle11) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Stolle22) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(M3ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(M1ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(OvecTester) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql12 = "SELECT SUM(QCLab) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql13 = "SELECT SUM(BordenNo1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql14 = "SELECT SUM(M4QcArea) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql15 = "SELECT SUM(Stolle21) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql16 = "SELECT SUM(Stolle33) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql17 = "SELECT SUM(Stolle12) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql18 = "SELECT SUM(Stolle31) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql19 = "SELECT SUM(Stolle32) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql20 = "SELECT SUM(M2BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql21 = "SELECT SUM(M2ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql22 = "SELECT SUM(M3Liners) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql23 = "SELECT SUM(M3QcArea) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql24 = "SELECT SUM(Stolle42) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql25 = "SELECT SUM(M4B2Bal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql26 = "SELECT SUM(M4Liners) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql27 = "SELECT SUM(QCMod1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql28 = "SELECT SUM(Stolle41) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql29 = "SELECT SUM(Stolle43) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql30 = "SELECT SUM(Stolle44) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql31 = "SELECT SUM(Balancer4B) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql32 = "SELECT SUM(Balancer4A) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql33 = "SELECT SUM(Formatec) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql4);
        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            totals[3] = rs4.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql5);
        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            totals[4] = rs5.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql6);
        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            totals[5] = rs6.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql7);
        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            totals[6] = rs7.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql8);
        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            totals[7] = rs8.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql9);
        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            totals[8] = rs9.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql10);
        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            totals[9] = rs10.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql11);
        ResultSet rs11 = s.getResultSet();
        while ((rs11 != null) && (rs11.next())) {
            totals[10] = rs11.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql12);
        ResultSet rs12 = s.getResultSet();
        while ((rs12 != null) && (rs12.next())) {
            totals[11] = rs12.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql13);
        ResultSet rs13 = s.getResultSet();
        while ((rs13 != null) && (rs13.next())) {
            totals[12] = rs13.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql14);
        ResultSet rs14 = s.getResultSet();
        while ((rs14 != null) && (rs14.next())) {
            totals[13] = rs14.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql15);
        ResultSet rs15 = s.getResultSet();
        while ((rs15 != null) && (rs15.next())) {
            totals[14] = rs15.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql16);
        ResultSet rs16 = s.getResultSet();
        while ((rs16 != null) && (rs16.next())) {
            totals[15] = rs16.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql17);
        ResultSet rs17 = s.getResultSet();
        while ((rs17 != null) && (rs17.next())) {
            totals[16] = rs17.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql18);
        ResultSet rs18 = s.getResultSet();
        while ((rs18 != null) && (rs18.next())) {
            totals[17] = rs18.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql19);
        ResultSet rs19 = s.getResultSet();
        while ((rs19 != null) && (rs19.next())) {
            totals[18] = rs19.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql20);
        ResultSet rs20 = s.getResultSet();
        while ((rs20 != null) && (rs20.next())) {
            totals[19] = rs20.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql21);
        ResultSet rs21 = s.getResultSet();
        while ((rs21 != null) && (rs21.next())) {
            totals[20] = rs21.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql22);
        ResultSet rs22 = s.getResultSet();
        while ((rs22 != null) && (rs22.next())) {
            totals[21] = rs22.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql23);
        ResultSet rs23 = s.getResultSet();
        while ((rs23 != null) && (rs23.next())) {
            totals[22] = rs23.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql24);
        ResultSet rs24 = s.getResultSet();
        while ((rs24 != null) && (rs24.next())) {
            totals[23] = rs24.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql25);
        ResultSet rs25 = s.getResultSet();
        while ((rs25 != null) && (rs25.next())) {
            totals[24] = rs25.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql26);
        ResultSet rs26 = s.getResultSet();
        while ((rs26 != null) && (rs26.next())) {
            totals[25] = rs26.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql27);
        ResultSet rs27 = s.getResultSet();
        while ((rs27 != null) && (rs27.next())) {
            totals[26] = rs27.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql28);
        ResultSet rs28 = s.getResultSet();
        while ((rs28 != null) && (rs28.next())) {
            totals[27] = rs28.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql29);
        ResultSet rs29 = s.getResultSet();
        while ((rs29 != null) && (rs29.next())) {
            totals[28] = rs29.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql30);
        ResultSet rs30 = s.getResultSet();
        while ((rs30 != null) && (rs30.next())) {
            totals[29] = rs30.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql31);
        ResultSet rs31 = s.getResultSet();
        while ((rs31 != null) && (rs31.next())) {
            totals[30] = rs31.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql32);
        ResultSet rs32 = s.getResultSet();
        while ((rs32 != null) && (rs32.next())) {
            totals[31] = rs32.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql33);
        ResultSet rs33 = s.getResultSet();
        while ((rs33 != null) && (rs33.next())) {
            totals[32] = rs33.getInt(1);
        }
		// --------------------------------------------------------- //

        // for (int i = 0; i < 1; i++) {
        //
        // System.out.println("Total" + totals[i]);
        //
        // }
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        rs9.close();
        rs10.close();
        rs11.close();
        rs12.close();
        rs13.close();
        rs14.close();
        rs15.close();
        rs16.close();
        rs17.close();
        rs18.close();
        rs19.close();
        rs20.close();
        rs21.close();
        rs22.close();
        rs23.close();
        rs24.close();
        rs25.close();
        rs26.close();
        rs27.close();
        rs28.close();
        rs29.close();
        rs30.close();
        rs31.close();
        rs32.close();
        rs33.close();

        for (int i = 0; i < totals.length; i++) {
            // System.out.println("Totals" + i + "  " + totals[i]);
        }

        return totals;
    }

    public static int SpoilageByDayGetMonthsTotalStolle(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(Stolle11) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(Stolle12) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(Stolle21) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(Stolle22) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(Stolle31) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(Stolle32) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(Stolle33) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Stolle41) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(Stolle42) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(Stolle43) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(Stolle44) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql4);
        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            totals[3] = rs4.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql5);
        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            totals[4] = rs5.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql6);
        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            totals[5] = rs6.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql7);
        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            totals[6] = rs7.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql8);
        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            totals[7] = rs8.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql9);
        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            totals[8] = rs9.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql10);
        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            totals[9] = rs10.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql11);
        ResultSet rs11 = s.getResultSet();
        while ((rs11 != null) && (rs11.next())) {
            totals[10] = rs11.getInt(1);
        }
        // --------------------------------------------------------- //
        for (int i = 0; i < 11; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        rs9.close();
        rs10.close();
        rs11.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayGetMonthsTotalBalancers(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(M1ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(M2ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(M3ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(Balancer4A) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(M1ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(M1BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(M3BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Balancer4A) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(Balancer4B) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(M4B2Bal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql4);
        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            totals[3] = rs4.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql5);
        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            totals[4] = rs5.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql6);
        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            totals[5] = rs6.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql7);
        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            totals[6] = rs7.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql8);
        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            totals[7] = rs8.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql9);
        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            totals[8] = rs9.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql10);
        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            totals[9] = rs10.getInt(1);
        }
        // --------------------------------------------------------- //
        for (int i = 0; i < 11; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        rs9.close();
        rs10.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayGetMonthsTotalLiners(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(M1Liner) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(M2Liner) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(M3Liners) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M4Liners) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql4);
        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            totals[3] = rs4.getInt(1);
        }
		// --------------------------------------------------------- //

        // --------------------------------------------------------- //
        for (int i = 0; i < 4; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayGetMonthsTotalShellPresses(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(Optime1And2) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(Optime3) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(Formatec) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }

        // --------------------------------------------------------- //
        for (int i = 0; i < 4; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();
        rs3.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayGetMonthsTotalBordenOvec(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(BordenNo1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(OvecTester) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }

        // --------------------------------------------------------- //
        // --------------------------------------------------------- //
        for (int i = 0; i < 2; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayGetMonthsTotalQCAreas(String monthIn, String yearIn) throws Exception {

        int[] totals = new int[11];
        int sum = 0;

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        String sql1 = "SELECT SUM(QCMod1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // System.out.println(sql1);
        String sql2 = "SELECT SUM(QCLab) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(M3QcArea) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M4QcArea) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        // Queries //////////////////////////////////////////////////////
        s.execute(sql1);
        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            totals[0] = rs1.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql2);
        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            totals[1] = rs2.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql3);
        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            totals[2] = rs3.getInt(1);
        }
        // --------------------------------------------------------- //
        s.execute(sql4);
        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            totals[3] = rs4.getInt(1);
        }
		// --------------------------------------------------------- //

        // --------------------------------------------------------- //
        for (int i = 0; i < 4; i++) {

            sum = sum + totals[i];

        }

        // --------------------------------------------------------- //
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();

        System.out.println("SUM : " + sum);

        return sum;
    }

    public static int SpoilageByDayTotalFigure(String monthIn, String yearIn) throws Exception {

        int total = 0;

        total = total + SpoilageByDayGetMonthsTotalStolle(monthIn, yearIn);
        total = total + SpoilageByDayGetMonthsTotalBalancers(monthIn, yearIn);
        total = total + SpoilageByDayGetMonthsTotalLiners(monthIn, yearIn);
        total = total + SpoilageByDayGetMonthsTotalShellPresses(monthIn, yearIn);
        total = total + SpoilageByDayGetMonthsTotalBordenOvec(monthIn, yearIn);
        total = total + SpoilageByDayGetMonthsTotalQCAreas(monthIn, yearIn);

        return total;
    }

    public static int[][] SpoilageByDayTableFigures(String dayIn, String monthIn, String yearIn) throws SQLException {

        String day = "1";
        String month = monthIn;
        String year = yearIn;

        int totals[][] = new int[36][31];

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

        String date = (day + "/" + month + "/" + year);

        // Open Connection
        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(30);

        // /////////////////////////////////////////////////////////////////////////
        // Day 1
        date = ("01" + "/" + month + "/" + year);
        String dayQuery1 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery1);
        ResultSet rs1 = s.getResultSet();

        if (rs1.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][0] = rs1.getInt(i + 1);
            }
        }
        rs1.close();

        // /////////////////////////////////////////////////////////////////////////
        // Day 2
        date = ("02" + "/" + month + "/" + year);
        // Run Query
        String dayQuery2 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery2);
        ResultSet rs2 = s.getResultSet();

        if (rs2.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][1] = rs2.getInt(i + 1);
            }
        }
        rs2.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 3
        date = ("03" + "/" + month + "/" + year);
        // Run Query
        String dayQuery3 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery3);
        ResultSet rs3 = s.getResultSet();

        if (rs3.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][2] = rs3.getInt(i + 1);
            }
        }
        rs3.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 4
        date = ("04" + "/" + month + "/" + year);
        // Run Query
        String dayQuery4 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery4);
        ResultSet rs4 = s.getResultSet();

        if (rs4.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][3] = rs4.getInt(i + 1);
            }
        }
        rs4.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 5
        date = ("05" + "/" + month + "/" + year);
        // Run Query
        String dayQuery5 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery5);
        ResultSet rs5 = s.getResultSet();

        if (rs5.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][4] = rs5.getInt(i + 1);
            }
        }
        rs5.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 6
        date = ("06" + "/" + month + "/" + year);
        // Run Query
        String dayQuery6 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery6);
        ResultSet rs6 = s.getResultSet();

        if (rs6.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][5] = rs6.getInt(i + 1);
            }
        }
        rs6.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 7
        date = ("07" + "/" + month + "/" + year);
        // Run Query
        String dayQuery7 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery7);
        ResultSet rs7 = s.getResultSet();

        if (rs7.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][6] = rs7.getInt(i + 1);
            }
        }
        rs7.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 8
        date = ("08" + "/" + month + "/" + year);
        // Run Query
        String dayQuery8 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery8);
        ResultSet rs8 = s.getResultSet();

        if (rs8.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][7] = rs8.getInt(i + 1);
            }
        }
        rs8.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 9
        date = ("09" + "/" + month + "/" + year);
        // Run Query
        String dayQuery9 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery9);
        ResultSet rs9 = s.getResultSet();

        if (rs9.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][8] = rs9.getInt(i + 1);
            }
        }
        rs9.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 10
        date = ("10" + "/" + month + "/" + year);
        // Run Query
        String dayQuery10 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery10);
        ResultSet rs10 = s.getResultSet();

        if (rs10.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][9] = rs10.getInt(i + 1);
            }
        }
        rs10.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 11
        date = ("11" + "/" + month + "/" + year);
        // Run Query
        String dayQuery11 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery11);
        ResultSet rs11 = s.getResultSet();
        if (rs11.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][10] = rs11.getInt(i + 1);
            }
        }
        rs11.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 12
        date = ("12" + "/" + month + "/" + year);
        // Run Query
        String dayQuery12 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery12);
        ResultSet rs12 = s.getResultSet();

        if (rs12.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][11] = rs12.getInt(i + 1);
            }
        }
        rs12.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 13
        date = ("13" + "/" + month + "/" + year);
        // Run Query
        String dayQuery13 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery13);
        ResultSet rs13 = s.getResultSet();

        if (rs13.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][12] = rs13.getInt(i + 1);
            }
        }
        rs13.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 14
        date = ("14" + "/" + month + "/" + year);
        // Run Query
        String dayQuery14 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery14);
        ResultSet rs14 = s.getResultSet();

        if (rs14.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][13] = rs14.getInt(i + 1);
            }
        }
        rs14.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 15
        date = ("15" + "/" + month + "/" + year);
        // Run Query
        String dayQuery15 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery15);
        ResultSet rs15 = s.getResultSet();

        if (rs15.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][14] = rs15.getInt(i + 1);
            }
        }
        rs15.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 16
        date = ("16" + "/" + month + "/" + year);
        // Run Query
        String dayQuery16 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery16);
        ResultSet rs16 = s.getResultSet();

        if (rs16.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][15] = rs16.getInt(i + 1);
            }
        }
        rs16.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 17
        date = ("17" + "/" + month + "/" + year);
        // Run Query
        String dayQuery17 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery17);
        ResultSet rs17 = s.getResultSet();

        if (rs17.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][16] = rs17.getInt(i + 1);
            }
        }
        rs17.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 18
        date = ("18" + "/" + month + "/" + year);
        // Run Query
        String dayQuery18 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery18);
        ResultSet rs18 = s.getResultSet();

        if (rs18.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][17] = rs18.getInt(i + 1);
            }
        }
        rs18.close();
        // ///////////////////////////////////////////////////////////////////////
        // Day 19
        date = ("19" + "/" + month + "/" + year);
        // Run Query
        String dayQuery19 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery19);
        ResultSet rs19 = s.getResultSet();

        if (rs19.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][18] = rs19.getInt(i + 1);
            }
        }
        rs19.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 20
        date = ("20" + "/" + month + "/" + year);
        // Run Query
        String dayQuery20 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery20);
        ResultSet rs20 = s.getResultSet();

        if (rs20.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][19] = rs20.getInt(i + 1);
            }
        }
        rs20.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 21
        date = ("21" + "/" + month + "/" + year);
        // Run Query
        String dayQuery21 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery21);
        ResultSet rs21 = s.getResultSet();

        if (rs21.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][20] = rs21.getInt(i + 1);
            }
        }
        rs21.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 22
        date = ("22" + "/" + month + "/" + year);
        // Run Query
        String dayQuery22 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery22);
        ResultSet rs22 = s.getResultSet();

        if (rs22.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][21] = rs22.getInt(i + 1);
            }
        }
        rs22.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 23
        date = ("23" + "/" + month + "/" + year);
        // Run Query
        String dayQuery23 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery23);
        ResultSet rs23 = s.getResultSet();

        if (rs23.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][22] = rs23.getInt(i + 1);
            }
        }
        rs23.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 24
        date = ("24" + "/" + month + "/" + year);
        // Run Query
        String dayQuery24 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery24);
        ResultSet rs24 = s.getResultSet();

        if (rs24.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][23] = rs24.getInt(i + 1);
            }
        }
        rs24.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 25
        date = ("25" + "/" + month + "/" + year);
        // Run Query
        String dayQuery25 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery25);
        ResultSet rs25 = s.getResultSet();

        if (rs25.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][24] = rs25.getInt(i + 1);
            }
        }
        rs25.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 26
        date = ("26" + "/" + month + "/" + year);
        // Run Query
        String dayQuery26 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery26);
        ResultSet rs26 = s.getResultSet();

        if (rs26.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][25] = rs26.getInt(i + 1);
            }
        }
        rs26.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 27
        date = ("27" + "/" + month + "/" + year);
        // Run Query
        String dayQuery27 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery27);
        ResultSet rs27 = s.getResultSet();

        if (rs27.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][26] = rs27.getInt(i + 1);
            }
        }
        rs27.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 28
        date = ("28" + "/" + month + "/" + year);
        // Run Query
        String dayQuery28 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery28);
        ResultSet rs28 = s.getResultSet();

        if (rs28.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][27] = rs28.getInt(i + 1);
            }
        }
        rs28.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 29
        date = ("29" + "/" + month + "/" + year);
        // Run Query
        String dayQuery29 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery29);
        ResultSet rs29 = s.getResultSet();

        if (rs29.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][28] = rs29.getInt(i + 1);
            }
        }
        rs29.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 30
        date = ("30" + "/" + month + "/" + year);
        // Run Query
        String dayQuery30 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery30);
        ResultSet rs30 = s.getResultSet();

        if (rs30.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][29] = rs30.getInt(i + 1);
            }
        }
        rs30.close();
        // /////////////////////////////////////////////////////////////////////////
        // Day 31
        date = ("31" + "/" + month + "/" + year);
        // Run Query
        String dayQuery31 = "SELECT * FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        s.execute(dayQuery31);
        ResultSet rs31 = s.getResultSet();

        if (rs31.next()) {
            for (int i = 2; i < 35; i++) {
                totals[i][30] = rs31.getInt(i + 1);
            }
        }
        rs31.close();
		// /////////////////////////////////////////////////////////////////////////

        // for (int i = 0; i < 31; i++) {
        // System.out.println("Totals " + (i+1) + " : " + totals[i][2]);
        // }
        // /////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

        return totals;

    }

    public static int spoilageByDayGetDayTotals(String day, String month, String year) {

        int total = 0;
        String date = (day + "/" + month + "/" + year);

        String sql1 = "SELECT SUM(*) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        System.out.println(sql1);

        return total;
    }

    public static int[] spoilageByDayGetDayTotals2(String dayIn, String monthIn, String yearIn) throws Exception {

        int[] totals = new int[33];

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

        String date = (dayIn + "/" + month + "/" + year);

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // Loop this through days and total them up
        // System.out.println(sql1);
        // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

        for (int i = 0; i < 31; i++) {

            date = (days[i] + "/" + month + "/" + year);
            String sql1 = "SELECT SUM (Optime1And2 + Optime3 + M1BBal + M3BBal + M1Liner + M2Liner + Stolle11 + "
                    + "Stolle22 +  M3ABal +  M1ABal + OvecTester + QCLab + BordenNo1 + M4QcArea + Stolle21 + Stolle33 + "
                    + "Stolle12 + Stolle31 + Stolle32 + M2BBal + M2ABal + M3Liners + M3QcArea + Stolle42 + M4B2Bal + "
                    + "m4Liners + QCMod1 + Stolle41 + Stolle43 + Stolle44 + Balancer4B + Balancer4A + Formatec) "
                    + "FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
            System.out.println(sql1);
            s.execute(sql1);

            ResultSet rs1 = s.getResultSet();
            while ((rs1 != null) && (rs1.next())) {

                totals[i] = rs1.getInt(1);
                System.out.println("Total For  Day : " + totals[i]);
                rs1.close();
            }
            // ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        }

        // Queries //////////////////////////////////////////////////////
        return totals;

    }

    public static int totalKGs(String monthIn, String yearIn) throws Exception {

        // Get SUM of SpoilageByDayGetMonthsTotal()
        int[] totalArray = SpoilageByDayGetMonthsTotal(monthIn, yearIn);
        int total = 0;

        for (int i = 0; i < 31; i++) {

            total = total + totalArray[i];

        }
        System.out.println(total);

        return total;
    }

    public static int totalEndsXKG(String monthIn, String yearIn) throws Exception {

        // Get SUM of SpoilageByDayGetMonthsTotal()
        int[] totalArray = SpoilageByDayGetMonthsTotal(monthIn, yearIn);
        int total = 0;

        for (int i = 0; i < 31; i++) {

            total = total + totalArray[i];

        }
        System.out.println(total);

        return total;
    }

    public static int SpoilageByDayTotalMonthlySum(String monthIn, String yearIn) throws Exception {

        int total = 0;

        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[0] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[1] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[2] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[3] * 404);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[4] * 404);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[5] * 404);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[6] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[7] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[8] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[9] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[10] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[11] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[12] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[13] * 405);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[14] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[15] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[16] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[17] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[18] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[19] * 404);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[20] * 408);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[21] * 404);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[22] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[23] * 405);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[24] * 460);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[25] * 460);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[26] * 361);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[27] * 405);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[28] * 405);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[29] * 405);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[30] * 460);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[31] * 465);
        total = total + (SpoilageByDayGetMonthsTotal(monthIn, yearIn)[32] * 465);

        // Sum of all SpoilageByDay Figures for MonthIn
        System.out.println("Total : " + total);

        return total;

    }

    // Production Weekly Report
    public static int ProductionWeeklyReportTotalOptime2And3ForMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;
        ;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(Optime2) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(Optime3) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // Optime 3 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        s.close();
        conn.close();

        // ///////////////
        // System.out.println("Total : " + total);
        return total;
    }

    public static int ProductionWeeklyReportTotalOptime4ForMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;
        ;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(Optime4) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        s.close();
        conn.close();

        // ///////////////
        // System.out.println("Total : " + total);
        return total;
    }

    public static int ProductionWeeklyReportTotalOptime2And3And4ForMonth(String monthIn, String yearIn) {

        int total = 0;

        try {
            total = total + ProductionWeeklyReportTotalOptime2And3ForMonth(monthIn, yearIn);
            total = total + ProductionWeeklyReportTotalOptime4ForMonth(monthIn, yearIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Total : " + total);
        return total;
    }

    public static int ProductionWeeklyReportTotalAllW1And2And3sForMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;
        ;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W21) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W22) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(W33) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // W21 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);

        }

        // ///////////////////////////
        // W22 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = total + rs4.getInt(1);

        }

        // ///////////////////////////
        // W31 /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();

        while ((rs5 != null) && (rs5.next())) {

            total = total + rs5.getInt(1);

        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();

        while ((rs6 != null) && (rs6.next())) {

            total = total + rs6.getInt(1);

        }

        // ///////////////////////////
        // W33 /////////////////
        s.execute(sql7);

        ResultSet rs7 = s.getResultSet();

        while ((rs7 != null) && (rs7.next())) {

            total = total + rs7.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        s.close();
        conn.close();

        // ///////////////
        System.out.println("Total : " + total);
        return total;
    }

    public static int ProductionWeeklyReportTotalAllW4sForMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;
        ;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W41) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W42) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W43) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W44) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W41 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W42 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // W43 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);

        }

        // ///////////////////////////
        // W44 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = total + rs4.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        System.out.println("Total : " + total);
        return total;
    }

    public static int ProductionWeeklyReportTotalAllWs(String monthIn, String yearIn) {

        int total = 0;

        try {
            total = total + ProductionWeeklyReportTotalAllW1And2And3sForMonth(monthIn, yearIn);
            total = total + ProductionWeeklyReportTotalAllW4sForMonth(monthIn, yearIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("All Ws : " + total);
        return total;
    }

    public static int ProductionWeeklyReportB64LineSpoilage(String monthIn, String yearIn) throws SQLException {

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(5);

        int total = 0;

        String sql1 = "SELECT SUM(OvecTester) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(QCLab) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(BordenNo1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(QCMod1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        // W11 /////////////////

        String sql5 = "SELECT SUM(M1ABal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(M1Liner) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(M1BBal) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Stolle11) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(Stolle12) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        s.execute(sql1);

        // OvecTester /////////////////
        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // QCLab /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // BordenNo1 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);
            total = (total / 5);

        }

        // ///////////////////////////
        // QCMod1 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = total + rs4.getInt(1);

        }

        // ///////////////////////////
        // ///////////////////////////
        // M1ABal /////////////////
        s.execute(sql5);
        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total = total + rs5.getInt(1);
        }
        // ///////////////////////////
        // M1 Liner /////////////////
        s.execute(sql6);
        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total = total + rs6.getInt(1);
        }
        // ///////////////////////////
        // M1BBal /////////////////
        s.execute(sql7);
        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            total = total + rs7.getInt(1);
        }
        // ///////////////////////////
        // Stolle 11 /////////////////
        s.execute(sql8);
        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            total = total + rs8.getInt(1);
        }
        // ///////////////////////////
        // Stolle 12 /////////////////
        s.execute(sql9);
        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            total = total + rs9.getInt(1);
        }
        // ///////////////////////////

        System.out.println(total);
        return total;
    }

    public static int ProductionWeeklyReportCdlLineSpoilage(String monthIn, String yearIn) throws SQLException {

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

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(5);

        int total = 0;

        String sql1 = "SELECT SUM(OvecTester) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(QCLab) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(BordenNo1) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(M4QCArea) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        String sql5 = "SELECT SUM(Formatec) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(balancer4A) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(M4Liners) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Balancer4B) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(Stolle41) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(Stolle42) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(Stolle43) FROM SpoilageByDay WHERE DateString LIKE '%" + date + "%';";

        s.execute(sql1);

        // OvecTester /////////////////
        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // QCLab /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // BordenNo1 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);
            total = (total / 10);
            total = (total * 3);
        }

        // ///////////////////////////
        // M4QCArea /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = total + rs4.getInt(1);

        }

        // ///////////////////////////
        // ///////////////////////////
        // Formatec /////////////////
        s.execute(sql5);
        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total = total + rs5.getInt(1);
        }
        // ///////////////////////////
        // Balancer 4A /////////////////
        s.execute(sql6);
        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total = total + rs6.getInt(1);
        }
        // ///////////////////////////
        // M4Liners /////////////////
        s.execute(sql7);
        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            total = total + rs7.getInt(1);
        }
        // ///////////////////////////
        // Balancer 4B /////////////////
        s.execute(sql8);
        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            total = total + rs8.getInt(1);
        }
        // ///////////////////////////
        // Stolle 41 /////////////////
        s.execute(sql9);
        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            total = total + rs9.getInt(1);
        }
        // ///////////////////////////
        // Stolle 42 /////////////////
        s.execute(sql10);
        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            total = total + rs10.getInt(1);
        }
        // ///////////////////////////
        // Stolle 43 /////////////////
        s.execute(sql11);
        ResultSet rs11 = s.getResultSet();
        while ((rs11 != null) && (rs11.next())) {
            total = total + rs11.getInt(1);
        }
        // ///////////////////////////

        System.out.println(total);
        return total;
    }

    public static int ProductionWeeklyReportTotalLineSpoilage(String monthIn, String yearIn) {

        int total = 0;

        try {
            total = total + ProductionWeeklyReportB64LineSpoilage(monthIn, yearIn);
            total = total + ProductionWeeklyReportCdlLineSpoilage(monthIn, yearIn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return total;
    }

    // ProductionWeeklyReport
    public static void ProductionWeeklyReportUpdate(int HFIOpeningB64In, int HFIOpeningCDLIn, int HFICreatedB64In, int HFICreatedCDLIn,
            int HFIRecoveredB64In, int HFIRecoveredCDLIn, int HFIScrappedB64In, int HFIScrappedCDLIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update ProductionWeeklyReport set HFIOpeningB64=? , HFIOpeningCDL=? , HFICreatedB64=? , HFICreatedCDL=?, HFIRecoveredB64=? , "
                + "HFIRecoveredCDL=? , HFIScrappedB64=? , HFIScrappedCDL=?  where ID=?";

        PreparedStatement productionWeeklyReport = conn.prepareStatement(sql);

        productionWeeklyReport.setInt(1, HFIOpeningB64In);
        productionWeeklyReport.setInt(2, HFIOpeningCDLIn);
        productionWeeklyReport.setInt(3, HFICreatedB64In);
        productionWeeklyReport.setInt(4, HFICreatedCDLIn);
        productionWeeklyReport.setInt(5, HFIRecoveredB64In);
        productionWeeklyReport.setInt(6, HFIRecoveredCDLIn);
        productionWeeklyReport.setInt(7, HFIScrappedB64In);
        productionWeeklyReport.setInt(8, HFIScrappedCDLIn);
        productionWeeklyReport.setDouble(9, 1);

        productionWeeklyReport.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static int ProductionWeeklyReportQuery(int type) throws SQLException {

        int total = 0;

        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        int[] array = new int[8];
        int[] arrayHFI = new int[4];

        String sql1 = "SELECT * FROM ProductionWeeklyReport WHERE ID = 1";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            array[0] = rs1.getInt(2); // B64 Opening
            array[1] = rs1.getInt(3); // CDL Opening
            array[2] = rs1.getInt(4); // B64 Created
            array[3] = rs1.getInt(5); // CDL Created
            array[4] = rs1.getInt(6); // B64 Recovered
            array[5] = rs1.getInt(7); // CDL Recovered
            array[6] = rs1.getInt(8); // B64 Scrapped
            array[7] = rs1.getInt(9); // CDL Scrapped

        }

        rs1.close();
        s.close();
        conn.close();

        // System.out.println("Array0 : " + array[0]);
        // System.out.println("Array1 : " + array[1]);
        // System.out.println("Array2 : " + array[2]);
        // System.out.println("Array3 : " + array[3]);
        // System.out.println("Array4 : " + array[4]);
        // System.out.println("Array5 : " + array[5]);
        // System.out.println("Array6 : " + array[6]);
        // System.out.println("Array7 : " + array[7]);
        if (type == 1) {
            return array[0];
        } else if (type == 2) {
            return array[1];
        } else if (type == 3) {
            return array[2];
        } else if (type == 4) {
            return array[3];
        } else if (type == 5) {
            return array[4];
        } else if (type == 6) {
            return array[5];
        } else if (type == 7) {
            return array[6];
        } else if (type == 8) {
            return array[7];
        } else {
            return 0;
        }

    }

    // Plant Spoilage
    public static int PlantSpoilageShellsProducedCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int[] total = new int[10];
        int totalInt = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(Optime2) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(Optime3) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(Optime4) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);
        System.out.println("SQL1 : " + sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total[0] = rs1.getInt(1);
            System.out.println("Optime 2 : " + total[0]);

        }

        // ///////////////////////////
        // Optime 3 /////////////////
        s.execute(sql2);
        System.out.println("SQL2 : " + sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total[1] = rs2.getInt(1);
            System.out.println(total[1]);

        }

        // ///////////////////////////
        // Optime 4 /////////////////
        s.execute(sql3);
        System.out.println("SQL3 : " + sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total[2] = rs3.getInt(1);
            System.out.println(total[2]);

        }

        // ///////////////////////////
        // Optime Total /////////////////
        int optimeTotal = (total[0] + total[1] + total[2]);
        System.out.println("Optime Total : " + optimeTotal);
        totalInt = optimeTotal;

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        s.close();
        conn.close();

        // ///////////////
        return totalInt;
    }

    public static int PlantSpoilageEndCountsCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        Object[] total = new Object[14];
        int totalInt = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W21) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W22) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql5 = "SELECT SUM(W31) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql6 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql7 = "SELECT SUM(W33) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql8 = "SELECT SUM(Total1) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql9 = "SELECT SUM(W41) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql10 = "SELECT SUM(W42) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql11 = "SELECT SUM(W43) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql12 = "SELECT SUM(W44) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql13 = "SELECT SUM(Total2) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql14 = "SELECT SUM(Total3) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(5);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();
        while ((rs1 != null) && (rs1.next())) {
            total[0] = rs1.getInt(1);
        }

        // ///////////////////////////
        // W12 //////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            total[1] = rs2.getInt(1);
        }

        // ///////////////////////////
        // W21 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            total[2] = rs3.getInt(1);
        }

        // ///////////////////////////
        // W22 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            total[3] = rs4.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total[4] = rs5.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total[5] = rs6.getInt(1);
        }

        // ///////////////////////////
        // W33 /////////////////
        s.execute(sql7);

        ResultSet rs7 = s.getResultSet();
        while ((rs7 != null) && (rs7.next())) {
            total[6] = rs7.getInt(1);
        }

        // ///////////////////////////
        // Total1 /////////////////
        s.execute(sql8);

        ResultSet rs8 = s.getResultSet();
        while ((rs8 != null) && (rs8.next())) {
            total[7] = rs8.getInt(1);
        }

        // ///////////////////////////
        // W41 /////////////////
        s.execute(sql9);

        ResultSet rs9 = s.getResultSet();
        while ((rs9 != null) && (rs9.next())) {
            total[8] = rs9.getInt(1);
        }

        // ///////////////////////////
        // W42 /////////////////
        s.execute(sql10);

        ResultSet rs10 = s.getResultSet();
        while ((rs10 != null) && (rs10.next())) {
            total[9] = rs10.getInt(1);
        }

        // //////////////////////////////////
        // W43 /////////////////
        s.execute(sql11);

        ResultSet rs11 = s.getResultSet();
        while ((rs11 != null) && (rs11.next())) {
            total[10] = rs11.getInt(1);
        }

        // ///////////////////////////
        // W44 /////////////////
        s.execute(sql12);

        ResultSet rs12 = s.getResultSet();
        while ((rs12 != null) && (rs12.next())) {
            total[11] = rs12.getInt(1);
        }

        // ///////////////////////////
        // Total2 /////////////////
        s.execute(sql13);

        ResultSet rs13 = s.getResultSet();
        while ((rs13 != null) && (rs13.next())) {
            total[12] = rs13.getInt(1);
        }

        // ///////////////////////////
        // Total3 /////////////////
        s.execute(sql14);

        ResultSet rs14 = s.getResultSet();
        while ((rs14 != null) && (rs14.next())) {
            total[13] = rs14.getInt(1);
        }

        // ///////////////////////////
        totalInt = Integer.valueOf(total[13] + "");

        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();
        rs5.close();
        rs6.close();
        rs7.close();
        rs8.close();
        rs9.close();
        rs10.close();
        rs11.close();
        rs12.close();
        rs13.close();
        rs14.close();

        s.close();
        conn.close();

        // ///////////////
        return totalInt;
    }

    public static int[] PlantSpoilageGetHFI(String monthIn, String yearIn) throws SQLException {

        int[] arrayHFI = new int[6];

        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        int[] array = new int[8];

        String sql1 = "SELECT * FROM ProductionWeeklyReport WHERE ID = 1";

        s.setQueryTimeout(10);

        // Optime 2 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            array[0] = rs1.getInt(2); // B64 Opening
            array[1] = rs1.getInt(3); // CDL Opening
            array[2] = rs1.getInt(4); // B64 Created
            array[3] = rs1.getInt(5); // CDL Created
            array[4] = rs1.getInt(6); // B64 Recovered
            array[5] = rs1.getInt(7); // CDL Recovered
            array[6] = rs1.getInt(8); // B64 Scrapped
            array[7] = rs1.getInt(9); // CDL Scrapped

        }

        rs1.close();
        s.close();
        conn.close();

        arrayHFI[0] = array[2] + array[3]; // Created
        arrayHFI[1] = array[4] + array[5]; // Recovered
        arrayHFI[2] = array[6] + array[7]; // Scrapped

        // Opening = 0 + 1
        int opening = array[0] + array[1];
        int created = arrayHFI[0];
        int recovered = arrayHFI[1];
        int scrapped = arrayHFI[2];

        arrayHFI[3] = (opening + created) - (recovered + scrapped); // Remaining
        arrayHFI[4] = (arrayHFI[3] - opening) + scrapped;
        // arrayHFI[5] = SpoilageByDayTotalFigure(monthIn, yearIn) *

        arrayHFI[5] = (arrayHFI[3] - opening) + scrapped;

        return arrayHFI;

    }

    public static int PlantSpoilageM1CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(M1Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageM2CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(M2Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageM3CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(M3Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageM4CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(M4Liner) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageOptime2CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(Optime2) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageOptime3CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(Optime3) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageOptime4CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql4 = "SELECT SUM(Optime4) FROM LinerAndShells WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // M1Liner /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = rs4.getInt(1);
            System.out.println("Total : " + total);

        }

        // ///////////////////////////
        rs4.close();
        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageStolleMod1CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W11) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W12) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageStolleMod2CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W21) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W22) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageStolleMod3CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W31) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W32) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W33) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int PlantSpoilageStolleMod4CalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        int total = 0;

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
        Statement s = conn.createStatement();

        String sql1 = "SELECT SUM(W41) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql2 = "SELECT SUM(W42) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql3 = "SELECT SUM(W43) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";
        String sql4 = "SELECT SUM(W44) FROM EndCounts WHERE DateString LIKE '%" + date + "%';";

        s.setQueryTimeout(10);

        // W11 /////////////////
        s.execute(sql1);

        ResultSet rs1 = s.getResultSet();

        while ((rs1 != null) && (rs1.next())) {

            total = total + rs1.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();

        while ((rs2 != null) && (rs2.next())) {

            total = total + rs2.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();

        while ((rs3 != null) && (rs3.next())) {

            total = total + rs3.getInt(1);

        }

        // ///////////////////////////
        // W12 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();

        while ((rs4 != null) && (rs4.next())) {

            total = total + rs4.getInt(1);

        }

        // ///////////////////////////
        rs1.close();
        rs2.close();
        rs3.close();
        rs4.close();

        s.close();
        conn.close();

        // ///////////////
        return total;
    }

    public static int[] PlantSpoilageTotals(String monthIn, String yearIn) {

        int[] totals = new int[6];

        try {

            totals[0] = PlantSpoilageOptime2CalculateTotalsByMonth(monthIn, yearIn) + PlantSpoilageOptime3CalculateTotalsByMonth(monthIn, yearIn); // B64
            // Total1
            totals[1] = PlantSpoilageM1CalculateTotalsByMonth(monthIn, yearIn) // B64
                    // Total2
                    + PlantSpoilageM2CalculateTotalsByMonth(monthIn, yearIn) + PlantSpoilageM3CalculateTotalsByMonth(monthIn, yearIn);
            totals[2] = PlantSpoilageStolleMod1CalculateTotalsByMonth(monthIn, yearIn) // B64
                    // Total3
                    + PlantSpoilageStolleMod2CalculateTotalsByMonth(monthIn, yearIn) + PlantSpoilageStolleMod3CalculateTotalsByMonth(monthIn, yearIn);
            totals[3] = totals[0] + PlantSpoilageOptime4CalculateTotalsByMonth(monthIn, yearIn);
            totals[4] = totals[1] + PlantSpoilageM4CalculateTotalsByMonth(monthIn, yearIn);
            totals[5] = totals[2] + PlantSpoilageStolleMod4CalculateTotalsByMonth(monthIn, yearIn);

            System.out.println("Totals2 : " + totals[2]);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return totals;
    }

    public static int PlantSpoilageHFISpoilage() {

        int spoilage = 0;

        return spoilage;
    }

    public static JTable OptimeProductionReturnJTable(String type, String query) throws SQLException {

        JTable table = new JTable();

        System.out.println("Query : " + query);
        System.out.println("Type : " + type);

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement(query);

//        PreparedStatement psmt = conn
//                .prepareStatement("SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRliner "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRStolle "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRShellPress "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCROther ORDER BY NCRNumber ASC");
        psmt.setQueryTimeout(10);
        ResultSet rs = psmt.executeQuery();
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

        if (type.equalsIgnoreCase("Optime Production Report")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));

                data.add(row);
            }

        } else if (type.equalsIgnoreCase("Optime Group Report")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));

                data.add(row);
            }

        }
        if (type.equalsIgnoreCase("Optime Comments Log")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));

                data.add(row);
            }

        }
        if (type.equalsIgnoreCase("Shells By Month")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(1));
                row.add(rs.getInt(2));

                data.add(row);
            }
        }

        if (type.equalsIgnoreCase("Stolle Production Report")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Stolle Group Report")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Stolle Comments Report")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Ends By Month")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(1));
                row.add(rs.getString(2));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Optime Data")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Liner Data")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Liner Usage")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getString(18));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Stolle Data")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("LSS-PM Activity")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Production Meeting")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getBoolean(18));
                row.add(rs.getBoolean(19));
                row.add(rs.getBoolean(20));
                row.add(rs.getBoolean(21));
                row.add(rs.getBoolean(22));
                row.add(rs.getBoolean(23));
                row.add(rs.getBoolean(24));
                row.add(rs.getBoolean(25));
                row.add(rs.getString(26));
                row.add(rs.getString(27));
                row.add(rs.getString(28));
                row.add(rs.getString(29));
                row.add(rs.getString(30));
                row.add(rs.getString(31));
                row.add(rs.getString(32));
                row.add(rs.getString(33));
                row.add(rs.getString(34));
                row.add(rs.getString(35));
                row.add(rs.getString(36));
                row.add(rs.getString(37));
                row.add(rs.getString(38));
                row.add(rs.getString(39));
                row.add(rs.getString(40));
                row.add(rs.getString(41));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Meeting Quality")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Liners And Shells")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Liner Defects")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("End Counts")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Spoilage By Day")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getString(18));
                row.add(rs.getString(19));
                row.add(rs.getString(20));
                row.add(rs.getString(21));
                row.add(rs.getString(22));
                row.add(rs.getString(23));
                row.add(rs.getString(24));
                row.add(rs.getString(25));
                row.add(rs.getString(26));
                row.add(rs.getString(27));
                row.add(rs.getString(28));
                row.add(rs.getString(29));
                row.add(rs.getString(30));
                row.add(rs.getString(31));
                row.add(rs.getString(32));
                row.add(rs.getString(33));
                row.add(rs.getString(34));
                row.add(rs.getString(35));
                row.add(rs.getString(36));

                data.add(row);
            }
        } else if (type.equalsIgnoreCase("Shell Press Production")) {

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
                row.add(rs.getString(9));

                data.add(row);
            }
        }

        table = new JTable(data, cols);

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        return table;

    }

    public static int ParDatabaseGetHighestForm() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(ParEntry.[Form]) FROM ParEntry;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);

            System.out.println("Highest Form :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] ParDatabaseReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[67];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ParEntry WHERE ParEntry.DateString = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            String df1 = (String) rs.getObject(1);
            result[0] = df1;
            result[1] = rs.getInt(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);

            result[9] = rs.getBoolean(10);
            result[10] = rs.getBoolean(11);
            result[11] = rs.getBoolean(12);
            result[12] = rs.getBoolean(13);
            result[13] = rs.getBoolean(14);
            result[14] = rs.getBoolean(15);
            result[15] = rs.getBoolean(16);
            result[16] = rs.getBoolean(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);
            result[25] = rs.getBoolean(26);
            result[26] = rs.getBoolean(27);
            result[27] = rs.getBoolean(28);
            result[28] = rs.getBoolean(29);
            result[29] = rs.getBoolean(30);
            result[30] = rs.getBoolean(31);
            result[31] = rs.getBoolean(32);
            result[32] = rs.getBoolean(33);
            result[33] = rs.getBoolean(34);
            result[34] = rs.getBoolean(35);
            result[35] = rs.getBoolean(36);
            result[36] = rs.getBoolean(37);
            result[37] = rs.getBoolean(38);
            result[38] = rs.getBoolean(39);
            result[39] = rs.getBoolean(40);
            result[40] = rs.getBoolean(41);

            result[41] = rs.getBoolean(42);
            result[42] = rs.getBoolean(43);
            result[43] = rs.getBoolean(44);
            result[44] = rs.getBoolean(45);

            result[45] = rs.getDouble(46);
            result[46] = rs.getDouble(47);
            result[47] = rs.getDouble(48);
            result[48] = rs.getDouble(49);
            result[49] = rs.getDouble(50);
            result[50] = rs.getDouble(51);
            result[51] = rs.getDouble(52);
            result[52] = rs.getDouble(53);
            result[53] = rs.getDouble(54);
            result[54] = rs.getDouble(55);
            result[55] = rs.getDouble(56);
            result[56] = rs.getDouble(57);
            result[57] = rs.getDouble(58);
            result[58] = rs.getDouble(59);
            result[59] = rs.getDouble(60);
            result[60] = rs.getDouble(61);

            result[61] = rs.getString(62);
            result[62] = rs.getString(63);
            result[63] = rs.getString(64);

            result[64] = rs.getString(65);
            result[65] = rs.getString(66);
            result[66] = rs.getBoolean(67);

            rs.close();
            s.close();
            conn.close();

        }
        for (int j = 0; j < result.length; j++) {
            System.out.println("Result : " + j + " : " + result[j]);
        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] ParDatabaseReturnEntryByForm(int id) throws Exception {

        Object[] result = new Object[73];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ParEntry WHERE ParEntry.Form = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i < result.length; i++) {

                //System.out.println("Column " + i + " : " + rsmd.getColumnName(i));
            }

            String df1 = (String) rs.getObject(1);
            result[0] = df1;
            result[1] = rs.getInt(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);

            result[9] = rs.getBoolean(10);
            result[10] = rs.getBoolean(11);
            result[11] = rs.getBoolean(12);
            result[12] = rs.getBoolean(13);
            result[13] = rs.getBoolean(14);
            result[14] = rs.getBoolean(15);
            result[15] = rs.getBoolean(16);
            result[16] = rs.getBoolean(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);
            result[25] = rs.getBoolean(26);
            result[26] = rs.getBoolean(27);
            result[27] = rs.getBoolean(28);
            result[28] = rs.getBoolean(29);
            result[29] = rs.getBoolean(30);
            result[30] = rs.getBoolean(31);
            result[31] = rs.getBoolean(32);
            result[32] = rs.getBoolean(33);
            result[33] = rs.getBoolean(34);
            result[34] = rs.getBoolean(35);
            result[35] = rs.getBoolean(36);
            result[36] = rs.getBoolean(37);
            result[37] = rs.getBoolean(38);
            result[38] = rs.getBoolean(39);
            result[39] = rs.getBoolean(40);
            result[40] = rs.getBoolean(41);

            result[41] = rs.getBoolean(42);
            result[42] = rs.getBoolean(43);
            result[43] = rs.getBoolean(44);
            result[44] = rs.getBoolean(45);
            result[45] = rs.getBoolean(46);
            result[46] = rs.getBoolean(47);

            result[47] = rs.getDouble(48);
            result[48] = rs.getDouble(49);
            result[49] = rs.getDouble(50);
            result[50] = rs.getDouble(51);
            result[51] = rs.getDouble(52);
            result[52] = rs.getDouble(53);
            result[53] = rs.getDouble(54);
            result[54] = rs.getDouble(55);
            result[55] = rs.getDouble(56);
            result[56] = rs.getDouble(57);
            result[57] = rs.getDouble(58);
            result[58] = rs.getDouble(59);
            result[59] = rs.getDouble(60);
            result[60] = rs.getDouble(61);
            result[61] = rs.getDouble(62);
            result[62] = rs.getDouble(63);

            result[63] = rs.getString(64);
            result[64] = rs.getString(65);
            result[65] = rs.getString(66);

            result[66] = rs.getString(67);
            result[67] = rs.getString(68);

            result[68] = rs.getBoolean(69);
            result[69] = rs.getString(70);
            result[70] = rs.getString(71);

            result[71] = rs.getString(72);

            rs.close();
            s.close();
            conn.close();

        }

        for (int j = 0; j < result.length; j++) {

            System.out.println("Result : " + j + " : " + result[j]);
        }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void ParDatabaseInsert(
            String DateStringIn, int FormIn, String CrewIn, String CodeIn, String MachineIn, String ShiftManagerIn, String TechnicianIn, String OperatorIn,
            String EngineerIn, boolean LinerHeadCheck1In, boolean LinerHeadCheck2In, boolean LinerHeadCheck3In, boolean LinerHeadCheck4In,
            boolean LinerHeadCheck5In, boolean LinerHeadCheck6In, boolean LinerHeadCheck7In, boolean LinerHeadCheck8In, boolean ShellPressCheck1In,
            boolean ShellPressCheck2In, boolean ShellPressCheck3In, boolean ShellPressCheck4In, boolean ShellPressCheck5In,
            boolean ShellPressCheck6In, boolean ShellPressCheck7In, boolean ShellPressCheck8In, boolean ShellPressCheck9In,
            boolean ShellPressCheck10In, boolean ShellPressCheck11In, boolean ShellPressCheck12In, boolean ShellPressCheck13In,
            boolean ShellPressCheck14In, boolean ShellPressCheck15In, boolean ShellPressCheck16In, boolean ShellPressCheck17In,
            boolean ShellPressCheck18In, boolean ShellPressCheck19In, boolean ShellPressCheck20In, boolean ShellPressCheck21In,
            boolean ShellPressCheck22In, boolean ShellPressCheck23In, boolean ShellPressCheck24In, boolean ShellPressCheck25In, boolean ShellPressCheck26In, boolean StolleCheck1In, boolean StolleCheck2In,
            boolean StolleCheck3In, boolean StolleCheck4In, double Score1AIn, double Score1BIn, double Score1CIn, double Score1DIn, double Score2AIn,
            double Score2BIn, double Score2CIn, double Score2DIn, double Score3AIn, double Score3BIn, double Score3CIn, double Score3DIn,
            double Score4AIn, double Score4BIn, double Score4CIn, double Score4DIn,
            String TimeStartedIn, String TimeInToolRoomIn, String TimeFinishedIn, String SignedIn, String DateSignedIn, boolean StatusIn,
            String beforeIn, String actionTaken, String afterIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement ParEntry = conn
                .prepareStatement("insert into ParEntry values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        ParEntry.setString(1, DateStringIn);
        ParEntry.setInt(2, FormIn);
        ParEntry.setString(3, CrewIn);
        ParEntry.setString(4, CodeIn);
        ParEntry.setString(5, MachineIn);
        ParEntry.setString(6, ShiftManagerIn);
        ParEntry.setString(7, TechnicianIn);
        ParEntry.setString(8, OperatorIn);
        ParEntry.setString(9, EngineerIn);
        ParEntry.setBoolean(10, LinerHeadCheck1In);
        ParEntry.setBoolean(11, LinerHeadCheck2In);
        ParEntry.setBoolean(12, LinerHeadCheck3In);
        ParEntry.setBoolean(13, LinerHeadCheck4In);
        ParEntry.setBoolean(14, LinerHeadCheck5In);
        ParEntry.setBoolean(15, LinerHeadCheck6In);
        ParEntry.setBoolean(16, LinerHeadCheck7In);
        ParEntry.setBoolean(17, LinerHeadCheck8In);
        ParEntry.setBoolean(18, ShellPressCheck1In);
        ParEntry.setBoolean(19, ShellPressCheck2In);
        ParEntry.setBoolean(20, ShellPressCheck3In);
        ParEntry.setBoolean(21, ShellPressCheck4In);
        ParEntry.setBoolean(22, ShellPressCheck5In);
        ParEntry.setBoolean(23, ShellPressCheck6In);
        ParEntry.setBoolean(24, ShellPressCheck7In);
        ParEntry.setBoolean(25, ShellPressCheck8In);
        ParEntry.setBoolean(26, ShellPressCheck9In);
        ParEntry.setBoolean(27, ShellPressCheck10In);
        ParEntry.setBoolean(28, ShellPressCheck11In);
        ParEntry.setBoolean(29, ShellPressCheck12In);
        ParEntry.setBoolean(30, ShellPressCheck13In);
        ParEntry.setBoolean(31, ShellPressCheck14In);
        ParEntry.setBoolean(32, ShellPressCheck15In);
        ParEntry.setBoolean(33, ShellPressCheck16In);
        ParEntry.setBoolean(34, ShellPressCheck17In);
        ParEntry.setBoolean(35, ShellPressCheck18In);
        ParEntry.setBoolean(36, ShellPressCheck19In);
        ParEntry.setBoolean(37, ShellPressCheck20In);
        ParEntry.setBoolean(38, ShellPressCheck21In);
        ParEntry.setBoolean(39, ShellPressCheck22In);
        ParEntry.setBoolean(40, ShellPressCheck23In);
        ParEntry.setBoolean(41, ShellPressCheck24In);
        ParEntry.setBoolean(42, ShellPressCheck25In);
        ParEntry.setBoolean(43, ShellPressCheck26In);

        ParEntry.setBoolean(44, StolleCheck1In);
        ParEntry.setBoolean(45, StolleCheck2In);
        ParEntry.setBoolean(46, StolleCheck3In);
        ParEntry.setBoolean(47, StolleCheck4In);

        ParEntry.setDouble(48, Score1AIn);
        ParEntry.setDouble(49, Score1BIn);
        ParEntry.setDouble(50, Score1CIn);
        ParEntry.setDouble(51, Score1DIn);
        ParEntry.setDouble(52, Score2AIn);
        ParEntry.setDouble(53, Score2BIn);
        ParEntry.setDouble(54, Score2CIn);
        ParEntry.setDouble(55, Score2DIn);
        ParEntry.setDouble(56, Score3AIn);
        ParEntry.setDouble(57, Score3BIn);
        ParEntry.setDouble(58, Score3CIn);
        ParEntry.setDouble(59, Score3DIn);
        ParEntry.setDouble(60, Score4AIn);
        ParEntry.setDouble(61, Score4BIn);
        ParEntry.setDouble(62, Score4CIn);
        ParEntry.setDouble(63, Score4DIn);
        ParEntry.setString(64, TimeStartedIn);
        ParEntry.setString(65, TimeInToolRoomIn);
        ParEntry.setString(66, TimeFinishedIn);
        ParEntry.setString(67, SignedIn);
        ParEntry.setString(68, DateSignedIn);
        ParEntry.setBoolean(69, StatusIn);
        ParEntry.setString(70, beforeIn);
        ParEntry.setString(71, actionTaken);
        ParEntry.setString(72, afterIn);
        ParEntry.setString(73, dateF);

        ParEntry.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void ParDatabaseUpdate(
            String DateStringIn, String CrewIn, String CodeIn, String MachineIn, String ShiftManagerIn, String TechnicianIn, String OperatorIn,
            String EngineerIn,
            boolean LinerHeadCheck1In, boolean LinerHeadCheck2In, boolean LinerHeadCheck3In, boolean LinerHeadCheck4In, boolean LinerHeadCheck5In,
            boolean LinerHeadCheck6In, boolean LinerHeadCheck7In, boolean LinerHeadCheck8In,
            boolean ShellPressCheck1In, boolean ShellPressCheck2In, boolean ShellPressCheck3In, boolean ShellPressCheck4In,
            boolean ShellPressCheck5In, boolean ShellPressCheck6In, boolean ShellPressCheck7In, boolean ShellPressCheck8In,
            boolean ShellPressCheck9In, boolean ShellPressCheck10In, boolean ShellPressCheck11In, boolean ShellPressCheck12In,
            boolean ShellPressCheck13In, boolean ShellPressCheck14In, boolean ShellPressCheck15In, boolean ShellPressCheck16In,
            boolean ShellPressCheck17In, boolean ShellPressCheck18In, boolean ShellPressCheck19In, boolean ShellPressCheck20In,
            boolean ShellPressCheck21In, boolean ShellPressCheck22In, boolean ShellPressCheck23In, boolean ShellPressCheck24In, boolean ShellPressCheck25In, boolean ShellPressCheck26In,
            boolean StolleCheck1In, boolean StolleCheck2In, boolean StolleCheck3In, boolean StolleCheck4In,
            double Score1AIn, double Score1BIn, double Score1CIn, double Score1DIn,
            double Score3AIn, double Score3BIn, double Score3CIn, double Score3DIn,
            double Score6AIn, double Score6BIn, double Score6CIn, double Score6DIn,
            double Score9AIn, double Score9BIn, double Score9CIn, double Score9DIn,
            String TimeStartedIn, String TimeInToolRoomIn, String TimeFinishedIn, String SignedIn, String DateSignedIn,
            boolean StatusIn,
            String beforeIn, String actionTaken, String afterIn,
            int FormIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update ParEntry set DateString=? , Crew=? ,  Code=? , Machine=? , ShiftManager=? , "
                + "Technician=? , Operator=? , Engineer=? , LinerHeadCheck1=? , LinerHeadCheck2=? , LinerHeadCheck3=? , "
                + "LinerHeadCheck4=? , LinerHeadCheck5=? , LinerHeadCheck6=? , LinerHeadCheck7=? , LinerHeadCheck8=? ,"
                + " ShellPressCheck1=? , ShellPressCheck2=? , ShellPressCheck3=? , ShellPressCheck4=?, ShellPressCheck5=?, "
                + "ShellPressCheck6=?, ShellPressCheck7=?, ShellPressCheck8=?, ShellPressCheck9=?, ShellPressCheck10=?, "
                + "ShellPressCheck11=?, ShellPressCheck12=?, ShellPressCheck13=?, ShellPressCheck14=?, ShellPressCheck15=?, "
                + "ShellPressCheck16=?, ShellPressCheck17=?, ShellPressCheck18=?, ShellPressCheck19=?, ShellPressCheck20=?, "
                + "ShellPressCheck21=?, ShellPressCheck22=?, ShellPressCheck23=? , ShellPressCheck24=? ,ShellPressCheck25=? ,ShellPressCheck26=? ,"
                + " StolleCheck1=? , StolleCheck2=? , StolleCheck3=? , StolleCheck4=? , Score1A=? , Score1B=? ,"
                + " Score1C=? , Score1D=? , Score3A=? , Score3B=? , Score3C=? , Score3D=? , Score6A=? , Score6B=? , Score6C=? ,"
                + " Score6D=? , Score9A=? , Score9B=? , Score9C=? , Score9D=? , TimeStarted=? , TimeInToolRoom=? , TimeFinished=? ,"
                + " Signed=? , DateSigned=? ,  Status=? , Before=?, ActionTaken=?, After=?  where Form=?";

        PreparedStatement ParEntry = conn.prepareStatement(sql);

        ParEntry.setString(1, DateStringIn);
        ParEntry.setString(2, CrewIn);
        ParEntry.setString(3, CodeIn);
        ParEntry.setString(4, MachineIn);
        ParEntry.setString(5, ShiftManagerIn);
        ParEntry.setString(6, TechnicianIn);
        ParEntry.setString(7, OperatorIn);
        ParEntry.setString(8, EngineerIn);
        ParEntry.setBoolean(9, LinerHeadCheck1In);
        ParEntry.setBoolean(10, LinerHeadCheck2In);
        ParEntry.setBoolean(11, LinerHeadCheck3In);
        ParEntry.setBoolean(12, LinerHeadCheck4In);
        ParEntry.setBoolean(13, LinerHeadCheck5In);
        ParEntry.setBoolean(14, LinerHeadCheck6In);
        ParEntry.setBoolean(15, LinerHeadCheck7In);
        ParEntry.setBoolean(16, LinerHeadCheck8In);

        ParEntry.setBoolean(17, ShellPressCheck1In);
        ParEntry.setBoolean(18, ShellPressCheck2In);
        ParEntry.setBoolean(19, ShellPressCheck3In);
        ParEntry.setBoolean(20, ShellPressCheck4In);
        ParEntry.setBoolean(21, ShellPressCheck5In);
        ParEntry.setBoolean(22, ShellPressCheck6In);
        ParEntry.setBoolean(23, ShellPressCheck7In);
        ParEntry.setBoolean(24, ShellPressCheck8In);
        ParEntry.setBoolean(25, ShellPressCheck9In);
        ParEntry.setBoolean(26, ShellPressCheck10In);
        ParEntry.setBoolean(27, ShellPressCheck11In);
        ParEntry.setBoolean(28, ShellPressCheck12In);
        ParEntry.setBoolean(29, ShellPressCheck13In);
        ParEntry.setBoolean(30, ShellPressCheck14In);
        ParEntry.setBoolean(31, ShellPressCheck15In);
        ParEntry.setBoolean(32, ShellPressCheck16In);
        ParEntry.setBoolean(33, ShellPressCheck17In);
        ParEntry.setBoolean(34, ShellPressCheck18In);
        ParEntry.setBoolean(35, ShellPressCheck19In);
        ParEntry.setBoolean(36, ShellPressCheck20In);
        ParEntry.setBoolean(37, ShellPressCheck21In);
        ParEntry.setBoolean(38, ShellPressCheck22In);
        ParEntry.setBoolean(39, ShellPressCheck23In);
        ParEntry.setBoolean(40, ShellPressCheck24In);
        ParEntry.setBoolean(41, ShellPressCheck25In);
        ParEntry.setBoolean(42, ShellPressCheck26In);

        ParEntry.setBoolean(43, StolleCheck1In);
        ParEntry.setBoolean(44, StolleCheck2In);
        ParEntry.setBoolean(45, StolleCheck3In);
        ParEntry.setBoolean(46, StolleCheck4In);

        ParEntry.setDouble(47, Score1AIn);
        ParEntry.setDouble(48, Score1BIn);
        ParEntry.setDouble(49, Score1CIn);
        ParEntry.setDouble(50, Score1DIn);
        ParEntry.setDouble(51, Score3AIn);
        ParEntry.setDouble(52, Score3BIn);
        ParEntry.setDouble(53, Score3CIn);
        ParEntry.setDouble(54, Score3DIn);
        ParEntry.setDouble(55, Score6AIn);
        ParEntry.setDouble(56, Score6BIn);
        ParEntry.setDouble(57, Score6CIn);
        ParEntry.setDouble(58, Score6DIn);
        ParEntry.setDouble(59, Score9AIn);
        ParEntry.setDouble(60, Score9BIn);
        ParEntry.setDouble(61, Score9CIn);
        ParEntry.setDouble(62, Score9DIn);

        ParEntry.setString(63, TimeStartedIn);
        ParEntry.setString(64, TimeInToolRoomIn);
        ParEntry.setString(65, TimeFinishedIn);
        ParEntry.setString(66, SignedIn);
        ParEntry.setString(67, DateSignedIn);
        ParEntry.setBoolean(68, StatusIn);
        ParEntry.setString(69, beforeIn);
        ParEntry.setString(70, actionTaken);
        ParEntry.setString(71, afterIn);

        ParEntry.setInt(72, FormIn);

        ParEntry.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] ParDatabaseGetNextEntryByForm(int formIn) throws SQLException {

        Object[] result = new Object[67];
        int nextForm = formIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ParEntry WHERE ParEntry.Form = \"" + nextForm + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            String df1 = (String) rs.getObject(1);
            result[0] = df1;
            result[1] = rs.getInt(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);

            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);
            result[25] = rs.getBoolean(26);
            result[26] = rs.getBoolean(27);
            result[27] = rs.getBoolean(28);
            result[28] = rs.getBoolean(29);
            result[29] = rs.getBoolean(30);
            result[30] = rs.getBoolean(31);
            result[31] = rs.getBoolean(32);
            result[32] = rs.getBoolean(33);
            result[33] = rs.getBoolean(34);
            result[34] = rs.getBoolean(35);
            result[35] = rs.getBoolean(36);
            result[36] = rs.getBoolean(37);
            result[37] = rs.getBoolean(38);
            result[38] = rs.getBoolean(39);
            result[39] = rs.getBoolean(40);
            result[40] = rs.getBoolean(41);

            result[41] = rs.getBoolean(42);
            result[42] = rs.getBoolean(43);
            result[43] = rs.getBoolean(44);
            result[44] = rs.getBoolean(45);

            result[45] = rs.getDouble(46);
            result[46] = rs.getDouble(47);
            result[47] = rs.getDouble(48);
            result[48] = rs.getDouble(49);
            result[49] = rs.getDouble(50);
            result[50] = rs.getDouble(51);
            result[51] = rs.getDouble(52);
            result[52] = rs.getDouble(53);
            result[53] = rs.getDouble(54);
            result[54] = rs.getDouble(55);
            result[55] = rs.getDouble(56);
            result[56] = rs.getDouble(57);
            result[57] = rs.getDouble(58);
            result[58] = rs.getDouble(59);
            result[59] = rs.getDouble(60);
            result[60] = rs.getDouble(61);

            result[61] = rs.getString(62);
            result[62] = rs.getString(63);
            result[63] = rs.getString(64);
            result[64] = rs.getString(65);
            result[65] = rs.getString(66);
            result[66] = rs.getBoolean(67);

            result[67] = rs.getString(68);
            result[68] = rs.getString(69);
            result[69] = rs.getBoolean(70);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static boolean ParDatabaseCheckIfFormExists(int formIn) throws SQLException {

        int count = 0;
        Connection conn = Connect();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            stmt = conn.prepareStatement("SELECT Count(Form) from ParEntry WHERE Form=?");
            stmt.setInt(1, formIn);
            rset = stmt.executeQuery();
            if (rset.next()) {
                count = rset.getInt(1);
            }
            return count > 0;
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object[] ParDatabaseGetPreviousEntryByForm(int formIn) throws SQLException {

        Object[] result = new Object[70];
        int nextForm = formIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM ParEntry WHERE ParEntry.Form = \"" + nextForm + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            String df1 = (String) rs.getObject(1);
            result[0] = df1;
            result[1] = rs.getInt(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);

            result[9] = rs.getInt(10);
            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);
            result[25] = rs.getBoolean(26);
            result[26] = rs.getBoolean(27);
            result[27] = rs.getBoolean(28);
            result[28] = rs.getBoolean(29);
            result[29] = rs.getBoolean(30);
            result[30] = rs.getBoolean(31);
            result[31] = rs.getBoolean(32);
            result[32] = rs.getBoolean(33);
            result[33] = rs.getBoolean(34);
            result[34] = rs.getBoolean(35);
            result[35] = rs.getBoolean(36);
            result[36] = rs.getBoolean(37);
            result[37] = rs.getBoolean(38);
            result[38] = rs.getBoolean(39);
            result[39] = rs.getBoolean(40);
            result[40] = rs.getBoolean(41);

            result[41] = rs.getBoolean(42);
            result[42] = rs.getBoolean(43);
            result[43] = rs.getBoolean(44);
            result[44] = rs.getBoolean(45);

            result[45] = rs.getDouble(46);
            result[46] = rs.getDouble(47);
            result[47] = rs.getDouble(48);
            result[48] = rs.getDouble(49);
            result[49] = rs.getDouble(50);
            result[50] = rs.getDouble(51);
            result[51] = rs.getDouble(52);
            result[52] = rs.getDouble(53);
            result[53] = rs.getDouble(54);
            result[54] = rs.getDouble(55);
            result[55] = rs.getDouble(56);
            result[56] = rs.getDouble(57);
            result[57] = rs.getDouble(58);
            result[58] = rs.getDouble(59);
            result[59] = rs.getDouble(60);
            result[60] = rs.getDouble(61);

            result[61] = rs.getString(62);
            result[62] = rs.getString(63);
            result[63] = rs.getString(64);
            result[64] = rs.getString(65);
            result[65] = rs.getString(66);
            result[66] = rs.getBoolean(67);

            result[67] = rs.getString(68);
            result[68] = rs.getString(69);
            result[69] = rs.getBoolean(70);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static void ParGenerateReport(int referenceNumber) {

    }

    public static JPanel ParEntrySummaryTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn
                .prepareStatement("SELECT Form, DateString as Date, Crew, Code, Machine, ShiftManager, Technician, Operator, Engineer, Status FROM ParEntry ORDER BY Form DESC");
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
            row.add(rs.getString(6));
            row.add(rs.getString(7));
            row.add(rs.getString(8));
            row.add(rs.getString(9));
            row.add(rs.getBoolean(10));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(40);

        table.getColumnModel().getColumn(9).setMaxWidth(50);

        // Render Checkbox
        TableColumn tc = table.getColumnModel().getColumn(9);
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
					// int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 0).toString();
                    int id = Integer.valueOf(idString);
                    ParEntry par = new ParEntry(2, "1", "August", "2014");
                    ParEntry.setParToForm(1);

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

    public static JTable PARReturnJTable(String type, String query) throws SQLException {

        JTable table = new JTable();

        System.out.println("Query : " + query);
        System.out.println("Type : " + type);

        Connection conn = Connect();
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
            //System.out.println(rs.getMetaData().getColumnName(i));

        }

        // Add Data
        Vector data = new Vector();

        if (type.equalsIgnoreCase("PAR")) {

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
                row.add(rs.getString(9));
                row.add(rs.getBoolean(10));
                row.add(rs.getBoolean(11));
                row.add(rs.getBoolean(12));
                row.add(rs.getBoolean(13));
                row.add(rs.getBoolean(14));
                row.add(rs.getBoolean(15));
                row.add(rs.getBoolean(16));
                row.add(rs.getBoolean(17));
                row.add(rs.getBoolean(18));
                row.add(rs.getBoolean(19));
                row.add(rs.getBoolean(20));
                row.add(rs.getBoolean(21));
                row.add(rs.getBoolean(22));
                row.add(rs.getBoolean(23));
                row.add(rs.getBoolean(24));
                row.add(rs.getBoolean(25));
                row.add(rs.getBoolean(26));
                row.add(rs.getBoolean(27));
                row.add(rs.getBoolean(28));
                row.add(rs.getBoolean(29));
                row.add(rs.getBoolean(30));
                row.add(rs.getBoolean(31));
                row.add(rs.getBoolean(32));
                row.add(rs.getBoolean(33));
                row.add(rs.getBoolean(34));
                row.add(rs.getBoolean(35));
                row.add(rs.getBoolean(36));
                row.add(rs.getBoolean(37));
                row.add(rs.getBoolean(38));
                row.add(rs.getBoolean(39));
                row.add(rs.getBoolean(40));
                row.add(rs.getBoolean(41));
                row.add(rs.getBoolean(42));
                row.add(rs.getBoolean(43));
                row.add(rs.getBoolean(44));
                row.add(rs.getBoolean(45));
                row.add(rs.getBoolean(46));
                row.add(rs.getBoolean(47));
                row.add(rs.getString(48));
                row.add(rs.getString(49));
                row.add(rs.getString(50));
                row.add(rs.getString(51));
                row.add(rs.getString(52));
                row.add(rs.getString(53));
                row.add(rs.getString(54));
                row.add(rs.getString(55));
                row.add(rs.getString(56));
                row.add(rs.getString(57));
                row.add(rs.getString(58));
                row.add(rs.getString(59));
                row.add(rs.getString(60));
                row.add(rs.getString(61));
                row.add(rs.getString(62));
                row.add(rs.getString(63));
                row.add(rs.getString(64));
                row.add(rs.getString(65));
                row.add(rs.getString(66));
                row.add(rs.getString(67));
                row.add(rs.getString(68));
                row.add(rs.getString(69));
                row.add(rs.getString(70));
                row.add(rs.getString(71));
                row.add(rs.getString(72));
                row.add(rs.getString(73));

                data.add(row);
            }

        }

        table = new JTable(data, cols);

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        return table;

    }

    // NCR
    public static int NCRGetHighestNCRNumber() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(NCRNumber.[NCRNumber]) FROM NCRNumber;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);

            System.out.println("Highest NCR Number :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static int NCRGetLowestNCRNumber() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MIN(NCRStolle.[NCRNumber]) FROM NCRStolle;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);

            System.out.println("Lowest NCR Number :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static int NCRGetNCRCount() throws SQLException {

        return (NCRGetHighestNCRNumber() - NCRGetLowestNCRNumber());

    }

    public static Object[] NCRReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[41];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRStolle WHERE NCRStolle.DateIssued = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);

            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);

            result[15] = rs.getBoolean(16);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);

            result[19] = rs.getString(20);
            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);

            result[36] = rs.getInt(37);

            result[37] = rs.getString(38);
            result[38] = rs.getString(39);
            result[39] = rs.getString(40);

            result[40] = rs.getString(41);

            rs.close();
            s.close();
            conn.close();

        }
		// for (int j = 0; j < result.length; j++) {
        // System.out.println("Result : " + j + " : " + result[j]);
        // }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] NCRReturnEntryByNCRNumber(int id) throws Exception {

        Object[] result = new Object[88];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRStolle WHERE NCRStolle.NCRNumber = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i < result.length; i++) {

                System.out.println("Column " + i + " : " + rsmd.getColumnName(i));

            }

            result[0] = rs.getInt(1);

            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[15] = rs.getString(16);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);

            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[40] = rs.getString(41);

            result[37] = rs.getInt(38);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[41] = rs.getString(42);

            rs.close();
            s.close();
            conn.close();

        }

        // for (int j = 0; j < result.length; j++) {
        //
        // System.out.println("Result : " + j + " : " + result[j]);
        // }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    // Return Statements for 4 Sections
    public static Object[] NCRReturnStolleByNCRNumber(int id) throws Exception {

        Object[] result = new Object[45];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRStolle WHERE NCRStolle.NCRNumber = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            // for (int i = 1; i < result.length; i++) {
            //
            // System.out.println("Column " + i + " : " +
            // rsmd.getColumnName(i));
            //
            // }
            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);

            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);
            result[37] = rs.getString(38);

            result[38] = rs.getInt(39);

            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[41] = rs.getString(42);
            result[42] = rs.getString(43);

            System.out.println("result8 : " + result[8]);

            rs.close();
            s.close();
            conn.close();

        }

        // for (int j = 0; j < result.length; j++) {
        //
        // System.out.println("Result : " + j + " : " + result[j]);
        // }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] NCRReturnLinerByNCRNumber(int id) throws Exception {

        Object[] result = new Object[47];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRLiner WHERE NCRLiner.NCRNumber = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            // for (int i = 1; i < result.length; i++) {
            //
            // System.out.println("Column " + i + " : " +
            // rsmd.getColumnName(i));
            //
            // }
            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);

            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);

            result[33] = rs.getString(34);

            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);
            result[37] = rs.getString(38);
            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[41] = rs.getString(42);

            result[42] = rs.getString(43);
            result[43] = rs.getString(44);
            result[44] = rs.getString(45);
            result[45] = rs.getString(46);
            result[46] = rs.getString(47);

            rs.close();
            s.close();
            conn.close();

        }

        // for (int j = 0; j < result.length; j++) {
        //
        // System.out.println("Result : " + j + " : " + result[j]);
        // }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] NCRReturnShellPressByNCRNumber(int id) throws Exception {

        Object[] result = new Object[49];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRShellPress WHERE NCRShellPress.NCRNumber = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            // for (int i = 1; i < result.length; i++) {
            //
            // System.out.println("Column " + i + " : " +
            // rsmd.getColumnName(i));
            //
            // }
            System.out.println("getString8 : " + rs.getString(8));

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);

            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[20] = rs.getBoolean(21);
            result[21] = rs.getBoolean(22);
            result[22] = rs.getBoolean(23);
            result[23] = rs.getBoolean(24);
            result[24] = rs.getBoolean(25);
            result[25] = rs.getBoolean(26);
            result[26] = rs.getBoolean(27);
            result[27] = rs.getBoolean(28);
            result[28] = rs.getBoolean(29);
            result[29] = rs.getBoolean(30);
            result[30] = rs.getBoolean(31);
            result[31] = rs.getBoolean(32);
            result[32] = rs.getBoolean(33);
            result[33] = rs.getBoolean(34);
            result[34] = rs.getBoolean(35);
            result[35] = rs.getBoolean(36);
            result[36] = rs.getBoolean(37);
            result[37] = rs.getBoolean(38);
            result[38] = rs.getBoolean(39);
            result[39] = rs.getBoolean(40);
            result[40] = rs.getBoolean(41);

            result[41] = rs.getString(42);
            result[42] = rs.getString(43);
            result[43] = rs.getString(44);
            result[44] = rs.getString(45);
            result[45] = rs.getString(46);
            result[46] = rs.getString(47);
            result[47] = rs.getString(48);
            result[48] = rs.getString(49);

            rs.close();
            s.close();
            conn.close();

        }

        // for (int j = 0; j < result.length; j++) {
        //
        // System.out.println("Result : " + j + " : " + result[j]);
        // }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] NCRReturnOtherByNCRNumber(int id) throws Exception {

        Object[] result = new Object[45];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCROther WHERE NCROther.NCRNumber = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            ResultSetMetaData rsmd = rs.getMetaData();

            // for (int i = 1; i < result.length; i++) {
            //
            // System.out.println("Column " + i + " : " +
            // rsmd.getColumnName(i));
            //
            // }
            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[16] = rs.getString(17);

            result[17] = rs.getString(18);
            result[18] = rs.getString(19);
            result[19] = rs.getString(20);
            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);

            System.out.println("result7 : " + result[7]);

            rs.close();
            s.close();
            conn.close();

        }

        // for (int j = 0; j < result.length; j++) {
        //
        // System.out.println("Result : " + j + " : " + result[j]);
        // }
        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void NCRStolleInsert(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn, boolean ALaneIn, boolean BLaneIn, boolean CLaneIn,
            boolean DLaneIn, String BeforeAIn, String BeforeBIn, String BeforeCIn, String BeforeDIn, String ActionTakenAIn, String ActionTakenBIn,
            String ActionTakenCIn, String ActionTakenDIn, String ResultAIn, String ResultBIn, String ResultCIn, String ResultDIn,
            String HFINumbersIn, String OvecSleevesScrappedIn, String TimeStartedIn, String Unknown1In, String Unknown2In, int TotalDowntimeIn,
            String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        System.out.println("Identified by : " + IdentifiedByIn);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement StolleInsert2 = conn.prepareStatement("insert into NCRNumber values(?,?)");

        StolleInsert2.setInt(1, NCRNumberIn);
        StolleInsert2.setString(2, "Stolle");

        StolleInsert2.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement StolleInsert = conn
                .prepareStatement("insert into NCRStolle values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        StolleInsert.setInt(1, NCRNumberIn);
        StolleInsert.setString(2, "Stolle");
        StolleInsert.setString(3, DepartmentIn);
        StolleInsert.setString(4, CrewNameIn);
        StolleInsert.setString(5, PressNameIn);
        StolleInsert.setString(6, DefectIn);
        StolleInsert.setString(7, GeneralIn);
        StolleInsert.setString(8, IdentifiedByIn);
        StolleInsert.setString(9, DateIssuedIn);
        StolleInsert.setString(10, TimeIn);
        StolleInsert.setString(11, NonConformanceIn);
        StolleInsert.setString(12, ImmediateActionTakenIn);
        StolleInsert.setString(13, PersonsAlertedIn);
        StolleInsert.setString(14, ShiftManagerIn);
        StolleInsert.setString(15, TechnicianIn);
        StolleInsert.setString(16, OperatorIn);
        StolleInsert.setString(17, EngineerIn);

        StolleInsert.setBoolean(18, ALaneIn);
        StolleInsert.setBoolean(19, BLaneIn);
        StolleInsert.setBoolean(20, CLaneIn);
        StolleInsert.setBoolean(21, DLaneIn);

        StolleInsert.setString(22, BeforeAIn);
        StolleInsert.setString(23, BeforeBIn);
        StolleInsert.setString(24, BeforeCIn);
        StolleInsert.setString(25, BeforeDIn);

        StolleInsert.setString(26, ActionTakenAIn);
        StolleInsert.setString(27, ActionTakenBIn);
        StolleInsert.setString(28, ActionTakenCIn);
        StolleInsert.setString(29, ActionTakenDIn);

        StolleInsert.setString(30, ResultAIn);
        StolleInsert.setString(31, ResultBIn);
        StolleInsert.setString(32, ResultCIn);
        StolleInsert.setString(33, ResultDIn);

        StolleInsert.setString(34, HFINumbersIn);
        StolleInsert.setString(35, OvecSleevesScrappedIn);
        StolleInsert.setString(36, TimeStartedIn);

        StolleInsert.setString(37, Unknown1In);
        StolleInsert.setString(38, Unknown2In);
        StolleInsert.setInt(39, TotalDowntimeIn);
        StolleInsert.setString(40, DepartmentHeadIn);

        StolleInsert.setString(41, DateSignedOffIn);
        StolleInsert.setString(42, LongTermActionIn);
        StolleInsert.setString(43, dateF);

        StolleInsert.executeUpdate();

        Statement s2 = conn.createStatement();
        s2.setQueryTimeout(10);

        s.close();
        conn.close();

    }

    public static void NCRLinerInsert(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            Boolean beforeHead1CheckboxIn, String beforeHead1TextIn, Boolean beforeHead2CheckboxIn, String beforeHead2TextIn,
            Boolean beforeHead3CheckboxIn, String beforeHead3TextIn, Boolean beforeHead4CheckboxIn, String beforeHead4TextIn,
            Boolean beforeHead5CheckboxIn, String beforeHead5TextIn, Boolean beforeHead6CheckboxIn, String beforeHead6TextIn,
            Boolean beforeHead7CheckboxIn, String beforeHead7TextIn, Boolean beforeHead8CheckboxIn, String beforeHead8TextIn,
            String ActionTakenIn,
            String AfterHead1In, String AfterHead2In, String AfterHead3In, String AfterHead4In, String AfterHead5In, String AfterHead6In,
            String AfterHead7In, String AfterHead8In,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement LinerInsert2 = conn.prepareStatement("insert into NCRNumber values(?,?)");

        LinerInsert2.setInt(1, NCRNumberIn);
        LinerInsert2.setString(2, "Liner");

        LinerInsert2.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement StolleInsert = conn
                .prepareStatement("insert into NCRLiner values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        StolleInsert.setInt(1, NCRNumberIn);
        StolleInsert.setString(2, "Liner");
        StolleInsert.setString(3, DepartmentIn);
        StolleInsert.setString(4, CrewNameIn);
        StolleInsert.setString(5, PressNameIn);
        StolleInsert.setString(6, DefectIn);
        StolleInsert.setString(7, GeneralIn);
        StolleInsert.setString(8, IdentifiedByIn);
        StolleInsert.setString(9, DateIssuedIn);
        StolleInsert.setString(10, TimeIn);
        StolleInsert.setString(11, NonConformanceIn);
        StolleInsert.setString(12, ImmediateActionTakenIn);
        StolleInsert.setString(13, PersonsAlertedIn);
        StolleInsert.setString(14, ShiftManagerIn);
        StolleInsert.setString(15, TechnicianIn);
        StolleInsert.setString(16, OperatorIn);
        StolleInsert.setString(17, EngineerIn);

        StolleInsert.setBoolean(18, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(19, beforeHead2CheckboxIn);
        StolleInsert.setBoolean(20, beforeHead3CheckboxIn);
        StolleInsert.setBoolean(21, beforeHead4CheckboxIn);
        StolleInsert.setBoolean(22, beforeHead5CheckboxIn);
        StolleInsert.setBoolean(23, beforeHead6CheckboxIn);
        StolleInsert.setBoolean(24, beforeHead7CheckboxIn);
        StolleInsert.setBoolean(25, beforeHead8CheckboxIn);

        StolleInsert.setString(26, beforeHead1TextIn);
        StolleInsert.setString(27, beforeHead2TextIn);
        StolleInsert.setString(28, beforeHead3TextIn);
        StolleInsert.setString(29, beforeHead4TextIn);
        StolleInsert.setString(30, beforeHead5TextIn);
        StolleInsert.setString(31, beforeHead6TextIn);
        StolleInsert.setString(32, beforeHead7TextIn);
        StolleInsert.setString(33, beforeHead8TextIn);

        StolleInsert.setString(34, ActionTakenIn);

        StolleInsert.setString(35, AfterHead1In);
        StolleInsert.setString(36, AfterHead2In);
        StolleInsert.setString(37, AfterHead3In);
        StolleInsert.setString(38, AfterHead4In);
        StolleInsert.setString(39, AfterHead5In);
        StolleInsert.setString(40, AfterHead6In);
        StolleInsert.setString(41, AfterHead7In);
        StolleInsert.setString(42, AfterHead8In);

        StolleInsert.setString(43, HFINumbersIn);
        StolleInsert.setString(44, DepartmentHeadIn);
        StolleInsert.setString(45, DateSignedOffIn);
        StolleInsert.setString(46, LongTermActionIn);
        StolleInsert.setString(47, dateF);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCRShellPressInsert(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            Boolean die1In, Boolean die2In, Boolean die3In, Boolean die4In, Boolean die5In, Boolean die6In, Boolean die7In, Boolean die8In,
            Boolean die9In, Boolean die10In, Boolean die11In, Boolean die12In, Boolean die13In, Boolean die14In, Boolean die15In, Boolean die16In,
            Boolean die17In, Boolean die18In, Boolean die19In, Boolean die20In, Boolean die21In, Boolean die22In, Boolean die23In, Boolean die24In,
            String before, String actionTaken, String result,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement ShellPressInsert2 = conn.prepareStatement("insert into NCRNumber values(?,?)");

        ShellPressInsert2.setInt(1, NCRNumberIn);
        ShellPressInsert2.setString(2, "ShellPress");

        ShellPressInsert2.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement StolleInsert = conn
                .prepareStatement("insert into NCRShellPress values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        StolleInsert.setInt(1, NCRNumberIn);
        StolleInsert.setString(2, "ShellPress");
        StolleInsert.setString(3, DepartmentIn);
        StolleInsert.setString(4, CrewNameIn);
        StolleInsert.setString(5, PressNameIn);
        StolleInsert.setString(6, DefectIn);
        StolleInsert.setString(7, GeneralIn);
        StolleInsert.setString(8, IdentifiedByIn);
        StolleInsert.setString(9, DateIssuedIn);
        StolleInsert.setString(10, TimeIn);
        StolleInsert.setString(11, NonConformanceIn);
        StolleInsert.setString(12, ImmediateActionTakenIn);
        StolleInsert.setString(13, PersonsAlertedIn);
        StolleInsert.setString(14, ShiftManagerIn);
        StolleInsert.setString(15, TechnicianIn);
        StolleInsert.setString(16, OperatorIn);
        StolleInsert.setString(17, EngineerIn);

        StolleInsert.setBoolean(18, die1In);
        StolleInsert.setBoolean(19, die2In);
        StolleInsert.setBoolean(20, die3In);
        StolleInsert.setBoolean(21, die4In);
        StolleInsert.setBoolean(22, die5In);
        StolleInsert.setBoolean(23, die6In);
        StolleInsert.setBoolean(24, die7In);
        StolleInsert.setBoolean(25, die8In);
        StolleInsert.setBoolean(26, die9In);
        StolleInsert.setBoolean(27, die10In);
        StolleInsert.setBoolean(28, die11In);
        StolleInsert.setBoolean(29, die12In);
        StolleInsert.setBoolean(30, die13In);
        StolleInsert.setBoolean(31, die14In);
        StolleInsert.setBoolean(32, die15In);
        StolleInsert.setBoolean(33, die16In);
        StolleInsert.setBoolean(34, die17In);
        StolleInsert.setBoolean(35, die18In);
        StolleInsert.setBoolean(36, die19In);
        StolleInsert.setBoolean(37, die20In);
        StolleInsert.setBoolean(38, die2In);
        StolleInsert.setBoolean(39, die22In);
        StolleInsert.setBoolean(40, die23In);
        StolleInsert.setBoolean(41, die24In);

        StolleInsert.setString(42, before);
        StolleInsert.setString(43, actionTaken);
        StolleInsert.setString(44, result);

        StolleInsert.setString(45, HFINumbersIn);
        StolleInsert.setString(46, DepartmentHeadIn);
        StolleInsert.setString(47, DateSignedOffIn);
        StolleInsert.setString(48, LongTermActionIn);
        StolleInsert.setString(49, dateF);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCROtherInsert(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            String customerIn, String vendorIn, String detailsIn, String resultsOfInvestigationIn,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement OtherInsert2 = conn.prepareStatement("insert into NCRNumber values(?,?)");

        OtherInsert2.setInt(1, NCRNumberIn);
        OtherInsert2.setString(2, "Other");

        OtherInsert2.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        PreparedStatement StolleInsert = conn
                .prepareStatement("insert into NCROther values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

        StolleInsert.setInt(1, NCRNumberIn);
        StolleInsert.setString(2, "Other");
        StolleInsert.setString(3, DepartmentIn);
        StolleInsert.setString(4, CrewNameIn);
        StolleInsert.setString(5, PressNameIn);
        StolleInsert.setString(6, DefectIn);
        StolleInsert.setString(7, GeneralIn);
        StolleInsert.setString(8, IdentifiedByIn);
        StolleInsert.setString(9, DateIssuedIn);
        StolleInsert.setString(10, TimeIn);
        StolleInsert.setString(11, NonConformanceIn);
        StolleInsert.setString(12, ImmediateActionTakenIn);
        StolleInsert.setString(13, PersonsAlertedIn);
        StolleInsert.setString(14, ShiftManagerIn);
        StolleInsert.setString(15, TechnicianIn);
        StolleInsert.setString(16, OperatorIn);
        StolleInsert.setString(17, EngineerIn);

        StolleInsert.setString(18, customerIn);
        StolleInsert.setString(19, vendorIn);
        StolleInsert.setString(20, detailsIn);
        StolleInsert.setString(21, resultsOfInvestigationIn);

        StolleInsert.setString(22, HFINumbersIn);
        StolleInsert.setString(23, DepartmentHeadIn);
        StolleInsert.setString(24, DateSignedOffIn);
        StolleInsert.setString(25, LongTermActionIn);
        StolleInsert.setString(26, dateF);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCRStolleUpdate(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            boolean ALaneIn, boolean BLaneIn, boolean CLaneIn, boolean DLaneIn,
            String BeforeAIn, String BeforeBIn, String BeforeCIn, String BeforeDIn,
            String ActionTakenAIn, String ActionTakenBIn, String ActionTakenCIn, String ActionTakenDIn,
            String ResultAIn, String ResultBIn, String ResultCIn, String ResultDIn,
            String HFINumbersIn, String OvecSleevesScrappedIn, String TimeStartedIn, String Unknown1In, String Unknown2In,
            int TotalDowntimeIn,
            String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update NCRStolle set " + "Department=?, " + "CrewName=?, " + "PressName=?, " + "Defect=?, " + "DefectSubCategory=?, "
                + "IdentifiedBy=?, " + "DateIssued=?, " + "Time=?, " + "NonConformance=?, " + "ImmediateActionTaken=?, " + "PersonsAlerted=?, "
                + "ShiftManager=?, " + "Technician=?, " + "Operator=?, " + "Engineer=?, " + "ALane=?, " + "BLane=?, " + "CLane=?, " + "DLane=?, "
                + "BeforeA=?, " + "BeforeB=?, " + "BeforeC=?, " + "BeforeD=?, " + "ActionTakenA=?, " + "ActionTakenB=?, " + "ActionTakenC=?, "
                + "ActionTakenD=?, " + "ResultA=?, " + "ResultB=?, " + "ResultC=?, " + "ResultD=?, " + "HFINumbers=?, " + "OvecSleevesScrapped=?, "
                + "TimeStarted=?, " + "Unknown1=?, " + "Unknown2=?, " + "TotalDowntime=?, " + "DepartmentHead=?, " + "DateSignedOff=?, "
                + "LongTermAction=?  "
                + "where NCRNumber=?";

        PreparedStatement StolleInsert = conn.prepareStatement(sql);

        StolleInsert.setString(1, DepartmentIn);
        StolleInsert.setString(2, CrewNameIn);
        StolleInsert.setString(3, PressNameIn);
        StolleInsert.setString(4, DefectIn);
        StolleInsert.setString(5, GeneralIn);
        StolleInsert.setString(6, IdentifiedByIn);
        StolleInsert.setString(7, DateIssuedIn);
        StolleInsert.setString(8, TimeIn);
        StolleInsert.setString(9, NonConformanceIn);
        StolleInsert.setString(10, ImmediateActionTakenIn);
        StolleInsert.setString(11, PersonsAlertedIn);
        StolleInsert.setString(12, ShiftManagerIn);
        StolleInsert.setString(13, TechnicianIn);
        StolleInsert.setString(14, OperatorIn);
        StolleInsert.setString(15, EngineerIn);

        StolleInsert.setBoolean(16, ALaneIn);
        StolleInsert.setBoolean(17, BLaneIn);
        StolleInsert.setBoolean(18, CLaneIn);
        StolleInsert.setBoolean(19, DLaneIn);

        StolleInsert.setString(20, BeforeAIn);
        StolleInsert.setString(21, BeforeBIn);
        StolleInsert.setString(22, BeforeCIn);
        StolleInsert.setString(23, BeforeDIn);

        StolleInsert.setString(24, ActionTakenAIn);
        StolleInsert.setString(25, ActionTakenBIn);
        StolleInsert.setString(26, ActionTakenCIn);
        StolleInsert.setString(27, ActionTakenDIn);

        StolleInsert.setString(28, ResultAIn);
        StolleInsert.setString(29, ResultBIn);
        StolleInsert.setString(30, ResultCIn);
        StolleInsert.setString(31, ResultDIn);

        StolleInsert.setString(32, HFINumbersIn);
        StolleInsert.setString(33, OvecSleevesScrappedIn);
        StolleInsert.setString(34, TimeStartedIn);
        StolleInsert.setString(35, Unknown1In);
        StolleInsert.setString(36, Unknown2In);

        StolleInsert.setInt(37, TotalDowntimeIn);

        StolleInsert.setString(38, DepartmentHeadIn);
        StolleInsert.setString(39, DateSignedOffIn);
        StolleInsert.setString(40, LongTermActionIn);
        StolleInsert.setInt(41, NCRNumberIn);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCRLinerUpdate(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            boolean beforeHead1CheckboxIn, boolean beforeHead2CheckboxIn, boolean beforeHead3CheckboxIn, boolean beforeHead4CheckboxIn,
            boolean beforeHead5CheckboxIn, boolean beforeHead6CheckboxIn, boolean beforeHead7CheckboxIn, boolean beforeHead8CheckboxIn,
            String beforeHead1TextIn, String beforeHead2TextIn, String beforeHead3TextIn, String beforeHead4TextIn, String beforeHead5TextIn,
            String beforeHead6TextIn, String beforeHead7TextIn, String beforeHead8TextIn,
            String ActionTakenIn,
            String AfterHead1In, String AfterHead2In, String AfterHead3In, String AfterHead4In, String AfterHead5In, String AfterHead6In,
            String AfterHead7In, String AfterHead8In,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update NCRLiner set " + "Department=?,  " + "CrewName=?, " + "PressName=?, " + "Defect=?, " + "DefectSubCategory=?, "
                + "IdentifiedBy=?, " + "DateIssued=?, " + "Time=?, " + "NonConformance=?, " + "ImmediateActionTaken=?, " + "PersonsAlerted=?, "
                + "ShiftManager=?, " + "Technician=?, " + "Operator=?, " + "Engineer=?, "
                + "BeforeHead1CheckBox=?, " + "BeforeHead2CheckBox=?, " + "BeforeHead3CheckBox=?, " + "BeforeHead4CheckBox=?, "
                + "BeforeHead5CheckBox=?, " + "BeforeHead6CheckBox=?, " + "BeforeHead7CheckBox=?, " + "BeforeHead8CheckBox=?, "
                + "BeforeHead1=?, " + "BeforeHead2=?," + "BeforeHead3=?," + "BeforeHead4=?," + "BeforeHead5=?," + "BeforeHead6=?, "
                + "BeforeHead7=?," + "BeforeHead8=?, "
                + "ActionTaken=?, " + "AfterHead1=?, " + "AfterHead2=?, " + "AfterHead3=?, " + "AfterHead4=?, " + "AfterHead5=?, " + "AfterHead6=?, "
                + "AfterHead7=?, " + "AfterHead8=?, "
                + " HFINumbers=?, " + "DepartmentHead=?, " + "DateSignedOff=?, " + "LongTermAction=?  " + "where NCRNumber=?";

        PreparedStatement StolleInsert = conn.prepareStatement(sql);

        StolleInsert.setString(1, DepartmentIn);
        StolleInsert.setString(2, CrewNameIn);
        StolleInsert.setString(3, PressNameIn);
        StolleInsert.setString(4, DefectIn);
        StolleInsert.setString(5, GeneralIn);
        StolleInsert.setString(6, IdentifiedByIn);
        StolleInsert.setString(7, DateIssuedIn);
        StolleInsert.setString(8, TimeIn);
        StolleInsert.setString(9, NonConformanceIn);
        StolleInsert.setString(10, ImmediateActionTakenIn);
        StolleInsert.setString(11, PersonsAlertedIn);
        StolleInsert.setString(12, ShiftManagerIn);
        StolleInsert.setString(13, TechnicianIn);
        StolleInsert.setString(14, OperatorIn);
        StolleInsert.setString(15, EngineerIn);

        StolleInsert.setBoolean(16, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(17, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(18, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(19, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(20, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(21, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(22, beforeHead1CheckboxIn);
        StolleInsert.setBoolean(23, beforeHead1CheckboxIn);

        StolleInsert.setString(24, beforeHead1TextIn);
        StolleInsert.setString(25, beforeHead2TextIn);
        StolleInsert.setString(26, beforeHead3TextIn);
        StolleInsert.setString(27, beforeHead4TextIn);
        StolleInsert.setString(28, beforeHead5TextIn);
        StolleInsert.setString(29, beforeHead6TextIn);
        StolleInsert.setString(30, beforeHead7TextIn);
        StolleInsert.setString(31, beforeHead8TextIn);

        StolleInsert.setString(32, ActionTakenIn);

        StolleInsert.setString(33, AfterHead1In);
        StolleInsert.setString(34, AfterHead2In);
        StolleInsert.setString(35, AfterHead3In);
        StolleInsert.setString(36, AfterHead4In);
        StolleInsert.setString(37, AfterHead5In);
        StolleInsert.setString(38, AfterHead6In);
        StolleInsert.setString(39, AfterHead7In);
        StolleInsert.setString(40, AfterHead8In);

        StolleInsert.setString(41, HFINumbersIn);
        StolleInsert.setString(42, DepartmentHeadIn);
        StolleInsert.setString(43, DateSignedOffIn);
        StolleInsert.setString(44, LongTermActionIn);
        StolleInsert.setInt(45, NCRNumberIn);

        System.out.println("NCRNumber : " + NCRNumberIn);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCRShellPressUpdate(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            Boolean die1In, Boolean die2In, Boolean die3In, Boolean die4In, Boolean die5In, Boolean die6In, Boolean die7In, Boolean die8In,
            Boolean die9In, Boolean die10In, Boolean die11In, Boolean die12In, Boolean die13In, Boolean die14In, Boolean die15In, Boolean die16In,
            Boolean die17In, Boolean die18In, Boolean die19In, Boolean die20In, Boolean die21In, Boolean die22In, Boolean die23In, Boolean die24In,
            String beforeIn, String actionTakenIn, String resultIn,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update NCRShellPress set " + "Department=?, " + "CrewName=?, " + "PressName=?, " + "Defect=?, " + "DefectSubCategory=?, "
                + "IdentifiedBy=?, " + "DateIssued=?, " + "Time=?, " + "NonConformance=?, " + "ImmediateActionTaken=?, " + "PersonsAlerted=?, "
                + "ShiftManager=?, " + "Technician=?, " + "Operator=?, " + "Engineer=?, " + "Die1=?, " + "Die2=?, " + "Die3=?, " + "Die4=?, "
                + "Die5=?, " + "Die6=?, " + "Die7=?, " + "Die8=?, " + "Die9=?, " + "Die10=?, " + "Die11=?, " + "Die12=?, " + "Die13=?, "
                + "Die14=?, " + "Die15=?, " + "Die16=?, " + "Die17=?, " + "Die18=?, " + "Die19=?, " + "Die20=?, " + "Die21=?, " + "Die22=?, "
                + "Die23=?, " + "Die24=?," + "Before=?, " + "ActionTaken=?, " + "After=?," + "HFINumbers=?, " + "DepartmentHead=?, "
                + "DateSignedOff=?, " + "LongTermAction=?  " + "where NCRNumber=?";

        PreparedStatement StolleInsert = conn.prepareStatement(sql);

        StolleInsert.setString(1, DepartmentIn);
        StolleInsert.setString(2, CrewNameIn);
        StolleInsert.setString(3, PressNameIn);
        StolleInsert.setString(4, DefectIn);
        StolleInsert.setString(5, GeneralIn);
        StolleInsert.setString(6, IdentifiedByIn);
        StolleInsert.setString(7, DateIssuedIn);
        StolleInsert.setString(8, TimeIn);
        StolleInsert.setString(9, NonConformanceIn);
        StolleInsert.setString(10, ImmediateActionTakenIn);
        StolleInsert.setString(11, PersonsAlertedIn);
        StolleInsert.setString(12, ShiftManagerIn);
        StolleInsert.setString(13, TechnicianIn);
        StolleInsert.setString(14, OperatorIn);
        StolleInsert.setString(15, EngineerIn);

        StolleInsert.setBoolean(16, die1In);
        StolleInsert.setBoolean(17, die2In);
        StolleInsert.setBoolean(18, die3In);
        StolleInsert.setBoolean(19, die4In);
        StolleInsert.setBoolean(20, die5In);
        StolleInsert.setBoolean(21, die6In);
        StolleInsert.setBoolean(22, die7In);
        StolleInsert.setBoolean(23, die8In);
        StolleInsert.setBoolean(24, die9In);
        StolleInsert.setBoolean(25, die10In);
        StolleInsert.setBoolean(26, die11In);
        StolleInsert.setBoolean(27, die12In);
        StolleInsert.setBoolean(28, die13In);
        StolleInsert.setBoolean(29, die14In);
        StolleInsert.setBoolean(30, die15In);
        StolleInsert.setBoolean(31, die16In);
        StolleInsert.setBoolean(32, die17In);
        StolleInsert.setBoolean(33, die18In);
        StolleInsert.setBoolean(34, die19In);
        StolleInsert.setBoolean(35, die20In);
        StolleInsert.setBoolean(36, die2In);
        StolleInsert.setBoolean(37, die22In);
        StolleInsert.setBoolean(38, die23In);
        StolleInsert.setBoolean(39, die24In);

        StolleInsert.setString(40, beforeIn);
        StolleInsert.setString(41, actionTakenIn);
        StolleInsert.setString(42, resultIn);

        StolleInsert.setString(43, HFINumbersIn);
        StolleInsert.setString(44, DepartmentHeadIn);
        StolleInsert.setString(45, DateSignedOffIn);
        StolleInsert.setString(46, LongTermActionIn);
        StolleInsert.setInt(47, NCRNumberIn);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void NCROtherUpdate(
            int NCRNumberIn, String DepartmentIn, String CrewNameIn, String PressNameIn, String DefectIn, String GeneralIn, String IdentifiedByIn,
            String DateIssuedIn, String TimeIn, String NonConformanceIn, String ImmediateActionTakenIn, String PersonsAlertedIn,
            String ShiftManagerIn, String TechnicianIn, String OperatorIn, String EngineerIn,
            String customerIn, String vendorIn, String detailsIn, String resultsOfInvestigationIn,
            String HFINumbersIn, String DepartmentHeadIn, String DateSignedOffIn, String LongTermActionIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update NCROther set " + "Department=?, " + "CrewName=?, " + "PressName=?, " + "Defect=?, " + "DefectSubCategory=?, "
                + "IdentifiedBy=?, " + "DateIssued=?, " + "Time=?, " + "NonConformance=?, " + "ImmediateActionTaken=?, " + "PersonsAlerted=?, "
                + "ShiftManager=?, " + "Technician=?, " + "Operator=?, " + "Engineer=?, " + "Details=?, " + "ResultsOfInvestigation=?,"
                + "HFINumbers=?, Customer=?, " + "Vendor=?, " + "DepartmentHead=?, " + "DateSignedOff=?, " + "LongTermAction=?  "
                + "where NCRNumber=?";

        PreparedStatement StolleInsert = conn.prepareStatement(sql);

        StolleInsert.setString(1, DepartmentIn);
        StolleInsert.setString(2, CrewNameIn);
        StolleInsert.setString(3, PressNameIn);
        StolleInsert.setString(4, DefectIn);
        StolleInsert.setString(5, GeneralIn);
        StolleInsert.setString(6, IdentifiedByIn);
        StolleInsert.setString(7, DateIssuedIn);
        StolleInsert.setString(8, TimeIn);
        StolleInsert.setString(9, NonConformanceIn);
        StolleInsert.setString(10, ImmediateActionTakenIn);
        StolleInsert.setString(11, PersonsAlertedIn);
        StolleInsert.setString(12, ShiftManagerIn);
        StolleInsert.setString(13, TechnicianIn);
        StolleInsert.setString(14, OperatorIn);
        StolleInsert.setString(15, EngineerIn);

        StolleInsert.setString(16, customerIn);
        StolleInsert.setString(17, vendorIn);
        StolleInsert.setString(18, detailsIn);
        StolleInsert.setString(19, resultsOfInvestigationIn);

        StolleInsert.setString(20, HFINumbersIn);
        StolleInsert.setString(21, DepartmentHeadIn);
        StolleInsert.setString(22, DateSignedOffIn);
        StolleInsert.setString(23, LongTermActionIn);
        StolleInsert.setInt(24, NCRNumberIn);

        StolleInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] NCRGetNextEntryByNCRNumber(int formIn) throws SQLException {

        Object[] result = new Object[41];
        int nextForm = formIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRStolle WHERE NCRStolle.NCRNumber = \"" + nextForm + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);

            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[15] = rs.getString(16);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);

            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[40] = rs.getString(41);

            result[37] = rs.getInt(38);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[41] = rs.getString(42);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static boolean NCRCheckIfNCRNumberExists(int formIn) throws SQLException {

        int count = 0;
        Connection conn = Connect();
        PreparedStatement stmt = null;
        stmt.setQueryTimeout(10);
        ResultSet rset = null;

        try {
            stmt = conn.prepareStatement("SELECT Count(NCRNumber) from NCRStolle WHERE NCRNumber=?");
            stmt.setInt(1, formIn);
            rset = stmt.executeQuery();
            if (rset.next()) {
                count = rset.getInt(1);
            }
            return count > 0;
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object[] NCRGetPreviousEntryByNCRNumber(int formIn) throws SQLException {

        Object[] result = new Object[41];
        int nextForm = formIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRStolle WHERE NCRStolle.NCRNumber = \"" + nextForm + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);

            result[1] = rs.getString(2);
            result[2] = rs.getString(3);
            result[3] = rs.getString(4);
            result[4] = rs.getString(5);
            result[5] = rs.getString(6);
            result[6] = rs.getString(7);
            result[7] = rs.getString(8);
            result[8] = rs.getString(9);
            result[9] = rs.getString(10);
            result[10] = rs.getString(11);
            result[11] = rs.getString(12);
            result[12] = rs.getString(13);
            result[13] = rs.getString(14);
            result[14] = rs.getString(15);
            result[15] = rs.getString(16);
            result[15] = rs.getString(16);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);

            result[20] = rs.getString(21);
            result[21] = rs.getString(22);
            result[22] = rs.getString(23);
            result[23] = rs.getString(24);
            result[24] = rs.getString(25);
            result[25] = rs.getString(26);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);
            result[26] = rs.getString(27);
            result[27] = rs.getString(28);
            result[28] = rs.getString(29);
            result[29] = rs.getString(30);
            result[30] = rs.getString(31);
            result[31] = rs.getString(32);
            result[32] = rs.getString(33);
            result[33] = rs.getString(34);
            result[34] = rs.getString(35);
            result[35] = rs.getString(36);
            result[36] = rs.getString(37);

            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);
            result[19] = rs.getBoolean(20);
            result[16] = rs.getBoolean(17);
            result[17] = rs.getBoolean(18);
            result[18] = rs.getBoolean(19);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[40] = rs.getString(41);

            result[37] = rs.getInt(38);

            result[38] = rs.getString(39);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[39] = rs.getString(40);
            result[40] = rs.getString(41);
            result[41] = rs.getString(42);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static JPanel NCRCreateSummaryTable() throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn
                .prepareStatement("SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
                        + "FROM NCRliner "
                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
                        + "FROM NCRStolle "
                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
                        + "FROM NCRShellPress "
                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
                        + "FROM NCROther ORDER BY NCRNumber ASC");
        // + "ORDER BY NCRNumber.NCRNumber ASC");
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

        JTable table = new JTable(model);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();

                    int row = target.getSelectedRow() + 1;
					// int column = target.getSelectedColumn();

                    // System.out.println("Clicked : " + row );
                    System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());

                    if (table.getValueAt(table.getSelectedRow(), 1).toString().equals("Stolle")) {
                        StolleEntryMenu stolleMenu = new StolleEntryMenu(-1);
                        try {
                            StolleEntryMenu.setStolleEntryToNCR(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (table.getValueAt(table.getSelectedRow(), 1).toString().equals("Liner")) {
                        LinerEntryMenu linerMenu = new LinerEntryMenu(-1);
                        try {
                            LinerEntryMenu.setStolleEntryToNCR(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (table.getValueAt(table.getSelectedRow(), 1).toString().equals("ShellPress")) {
                        ShellPressEntryMenu shellPressMenu = new ShellPressEntryMenu(-1);
                        try {
                            ShellPressEntryMenu.setEntryToNCR(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (table.getValueAt(table.getSelectedRow(), 1).toString().equals("Other")) {
                        OtherEntryMenu otherMenu = new OtherEntryMenu(-1);
                        try {
                            OtherEntryMenu.setEntryToNCR(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }

                    // do some action if appropriate column
                }
            }
        });

        JTableHeader header = table.getTableHeader();

        outerPanel.add(header, BorderLayout.NORTH);
        outerPanel.add(table, BorderLayout.CENTER);

        return outerPanel;

    }

    public static int NCRnextNCRType(int currentNCR) throws SQLException {

        int ncrInt = 1;

        String ncr = "";

        Object[] result = new Object[45];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM NCRNumber WHERE NCRNumber.NCRNumber = \"" + currentNCR + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);

        }

        System.out.println("Result 1 : " + result[1]);

        ncr = (result[1] + "");

        if (ncr.equals("Stolle")) {
            return 1;
        }
        if (ncr.equals("Liner")) {
            return 2;
        }
        if (ncr.equals("ShellPress")) {
            return 3;
        }
        if (ncr.equals("Other")) {
            return 4;
        } else {
            return 0;
        }
    }

    public static JTable NCRReturnJTable(String type, String query) throws SQLException {

        JTable table = new JTable();

        System.out.println("Query : " + query);
        System.out.println("Type : " + type);

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement(query);

//        PreparedStatement psmt = conn
//                .prepareStatement("SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRliner "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRStolle "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCRShellPress "
//                        + "UNION SELECT NCRNumber, NCRType, DateIssued, Defect, NonConformance, ImmediateActionTaken, PersonsAlerted, ShiftManager, Technician, Operator, Engineer "
//                        + "FROM NCROther ORDER BY NCRNumber ASC");
        psmt.setQueryTimeout(10);
        ResultSet rs = psmt.executeQuery();
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

        if (type.equalsIgnoreCase("NCRStolle")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getBoolean(18));
                row.add(rs.getBoolean(19));
                row.add(rs.getBoolean(20));
                row.add(rs.getBoolean(21));
                row.add(rs.getString(22));
                row.add(rs.getString(23));
                row.add(rs.getString(24));
                row.add(rs.getString(25));
                row.add(rs.getString(26));
                row.add(rs.getString(27));
                row.add(rs.getString(28));
                row.add(rs.getString(29));
                row.add(rs.getString(30));
                row.add(rs.getString(31));
                row.add(rs.getString(32));
                row.add(rs.getString(33));
                row.add(rs.getString(34));
                row.add(rs.getString(35));
                row.add(rs.getString(36));
                row.add(rs.getString(37));
                row.add(rs.getString(38));
                row.add(rs.getString(39));
                row.add(rs.getString(40));
                row.add(rs.getString(41));
                row.add(rs.getString(42));
                row.add(rs.getString(43));

                data.add(row);
            }

        } else if (type.equalsIgnoreCase("NCRLiner")) {

            System.out.print(type);

            while (rs.next()) {
                Vector row = new Vector(len);

                row.add(rs.getString(37));
                row.add(rs.getString(38));
                row.add(rs.getString(39));
                row.add(rs.getString(40));
                row.add(rs.getString(41));
                row.add(rs.getString(42));
                row.add(rs.getString(43));
                row.add(rs.getString(44));
                row.add(rs.getString(45));
                row.add(rs.getString(46));
                row.add(rs.getString(47));

                data.add(row);
            }

        }
        if (type.equalsIgnoreCase("NCRShellPress")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getBoolean(18));
                row.add(rs.getBoolean(19));
                row.add(rs.getBoolean(20));
                row.add(rs.getBoolean(21));
                row.add(rs.getBoolean(22));
                row.add(rs.getBoolean(23));
                row.add(rs.getBoolean(24));
                row.add(rs.getBoolean(25));
                row.add(rs.getBoolean(26));
                row.add(rs.getBoolean(27));
                row.add(rs.getBoolean(28));
                row.add(rs.getBoolean(29));
                row.add(rs.getBoolean(30));
                row.add(rs.getBoolean(31));
                row.add(rs.getBoolean(32));
                row.add(rs.getBoolean(33));
                row.add(rs.getBoolean(34));
                row.add(rs.getBoolean(35));
                row.add(rs.getBoolean(36));
                row.add(rs.getBoolean(37));
                row.add(rs.getBoolean(38));
                row.add(rs.getBoolean(39));
                row.add(rs.getBoolean(40));
                row.add(rs.getBoolean(41));
                row.add(rs.getString(42));
                row.add(rs.getString(43));
                row.add(rs.getString(44));
                row.add(rs.getString(45));
                row.add(rs.getString(46));
                row.add(rs.getString(47));
                row.add(rs.getString(48));
                row.add(rs.getString(49));

                data.add(row);
            }

        }
        if (type.equalsIgnoreCase("NCROther")) {

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
                row.add(rs.getString(9));
                row.add(rs.getString(10));
                row.add(rs.getString(11));
                row.add(rs.getString(12));
                row.add(rs.getString(13));
                row.add(rs.getString(14));
                row.add(rs.getString(15));
                row.add(rs.getString(16));
                row.add(rs.getString(17));
                row.add(rs.getString(18));
                row.add(rs.getString(19));
                row.add(rs.getString(20));
                row.add(rs.getString(21));
                row.add(rs.getString(22));
                row.add(rs.getString(23));
                row.add(rs.getString(24));
                row.add(rs.getString(25));
                row.add(rs.getString(26));

                data.add(row);
            }

        }

        table = new JTable(data, cols);

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        return table;
    }

    // Maintenance
    public static int MaintenanceShellPressProductionGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(MainShellPressProduction.[ID]) FROM MainShellPressProduction;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] MaintenanceShellPressProductionReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getString(9);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] MaintenanceShellPressProductionReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[18];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2);
            result[1] = df1;
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[7] = rs.getInt(8);
            result[8] = rs.getString(9);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void MaintenanceShellPressProductionInsert(int idIn, String DateIn, int SP01In, int SP02In, int SP03In, int FMI41In, int FMI42In, int SP04In) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement ShellPressProductionInsert = conn.prepareStatement("insert into MainShellPressProduction values(?,?,?,?,?,?,?,?,?)");

        ShellPressProductionInsert.setInt(1, idIn);
        ShellPressProductionInsert.setString(2, DateIn);
        ShellPressProductionInsert.setInt(3, SP01In);
        ShellPressProductionInsert.setInt(4, SP02In);
        ShellPressProductionInsert.setInt(5, SP03In);
        ShellPressProductionInsert.setInt(6, FMI41In);
        ShellPressProductionInsert.setInt(7, FMI42In);
        ShellPressProductionInsert.setInt(8, SP04In);
        ShellPressProductionInsert.setString(9, dateF);

        ShellPressProductionInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void MaintenanceShellPressProductionUpdate(String DateIn, int SP01In, int SP02In, int SP03In, int FMI41In, int FMI42In, int SP04In, int idIn) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = "update MainShellPressProduction set date=?, SP01=? , SP02=? , SP03=? , FMI41=?, FMI42=? , "
                + "SP04=? where ID=?";

        PreparedStatement ShellPressProductionUpdate = conn.prepareStatement(sql);

        ShellPressProductionUpdate.setString(1, DateIn);
        ShellPressProductionUpdate.setInt(2, SP01In);
        ShellPressProductionUpdate.setInt(3, SP02In);
        ShellPressProductionUpdate.setInt(4, SP03In);
        ShellPressProductionUpdate.setInt(5, FMI41In);
        ShellPressProductionUpdate.setInt(6, FMI42In);
        ShellPressProductionUpdate.setInt(7, SP04In);
        ShellPressProductionUpdate.setInt(8, idIn);

        ShellPressProductionUpdate.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] MaintenanceShellPressProductionGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2); // ID
            result[1] = df1; // Date
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[6] = rs.getInt(7);
            result[7] = rs.getString(8); // TimeStamp

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] MaintenanceShellPressProductionGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressProduction WHERE MainShellPressProduction.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            String df1 = (String) rs.getObject(2); // ID
            result[1] = df1; // Date
            result[2] = rs.getInt(3);
            result[3] = rs.getInt(4);
            result[4] = rs.getInt(5);
            result[5] = rs.getInt(6);
            result[6] = rs.getInt(7);
            result[6] = rs.getInt(7);
            result[7] = rs.getString(8); // TimeStamp

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] MaintenanceShellPressProductionCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        Object[] total = new Object[14];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
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
            total[0] = rs1.getInt(1);
        }

        // ///////////////////////////
        // W12 //////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            total[1] = rs2.getInt(1);
        }

        // ///////////////////////////
        // W21 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            total[2] = rs3.getInt(1);
        }

        // ///////////////////////////
        // W22 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            total[3] = rs4.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total[4] = rs5.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total[5] = rs6.getInt(1);
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
        return total;
    }

    public static JPanel MaintenanceShellPressProductionSummaryTable(int in) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement("SELECT Date, SP01, SP02, SP03, FMI41, FMI42, SP04, ID FROM MainShellPressProduction ORDER BY Date DESC");
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
            row.add(rs.getString(6));
            row.add(rs.getString(7));
            row.add(rs.getString(8));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(7).setMaxWidth(40);

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
                    System.out.println(table.getValueAt(table.getSelectedRow(), 7).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 7).toString();
                    int id = Integer.valueOf(idString);
                    ShellPressProduction linersAndShells;
                    try {
                        ShellPressProduction shellPressProduction = new ShellPressProduction(1, -2);
                        shellPressProduction.setShellPressProductionToID(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // Maintenance ShellPressMaintenance
    public static int MaintenanceShellPressMaintenanceGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(MainShellPressMaintenance.[ID]) FROM MainShellPressMaintenance;";
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

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static Object[] MaintenanceShellPressMaintenanceReturnEntryByDate(Date dateIn) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String df = (sdf.format(dateIn));

        // Need to format dateIn to proper Syntax ----> #2/2/2012#
        Object[] result = new Object[16];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.Date = \"" + df + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);

            String df1 = (String) rs.getObject(4);
            result[3] = df1;
            String df2 = (String) rs.getObject(5);
            result[4] = df2;
            String df3 = (String) rs.getObject(6);
            result[5] = df3;
            String df4 = (String) rs.getObject(7);
            result[6] = df4;
            String df5 = (String) rs.getObject(8);
            result[7] = df5;
            String df6 = (String) rs.getObject(9);
            result[8] = df6;
            String df7 = (String) rs.getObject(10);
            result[9] = df7;

            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getInt(18);
            result[18] = rs.getInt(19);
            result[19] = rs.getInt(20);
            result[20] = rs.getInt(21);
            result[21] = rs.getInt(22);
            result[22] = rs.getInt(23);
            result[23] = rs.getInt(24);

            result[24] = rs.getInt(25);
            result[25] = rs.getInt(26);
            result[26] = rs.getInt(27);
            result[27] = rs.getInt(28);
            result[28] = rs.getInt(29);
            result[29] = rs.getInt(30);
            result[30] = rs.getInt(31);

            String df8 = (String) rs.getObject(32);
            result[31] = df8;
            String df9 = (String) rs.getObject(33);
            result[32] = df9;
            String df10 = (String) rs.getObject(34);
            result[33] = df10;
            String df11 = (String) rs.getObject(35);
            result[34] = df11;
            String df12 = (String) rs.getObject(36);
            result[35] = df12;
            String df13 = (String) rs.getObject(37);
            result[36] = df13;
            String df14 = (String) rs.getObject(38);
            result[37] = df14;

            result[38] = rs.getInt(39);
            result[39] = rs.getInt(40);
            result[40] = rs.getInt(41);
            result[41] = rs.getInt(42);
            result[42] = rs.getInt(43);
            result[43] = rs.getInt(44);
            result[44] = rs.getInt(45);

            result[45] = rs.getInt(46);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static Object[] MaintenanceShellPressMaintenanceReturnEntryByID(int id) throws Exception {

        Object[] result = new Object[46];

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + id + "\";";
        System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);

            String df1 = (String) rs.getObject(4);
            result[3] = df1;
            String df2 = (String) rs.getObject(5);
            result[4] = df2;
            String df3 = (String) rs.getObject(6);
            result[5] = df3;
            String df4 = (String) rs.getObject(7);
            result[6] = df4;
            String df5 = (String) rs.getObject(8);
            result[7] = df5;
            String df6 = (String) rs.getObject(9);
            result[8] = df6;
            String df7 = (String) rs.getObject(10);
            result[9] = df7;

            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getInt(18);
            result[18] = rs.getInt(19);
            result[19] = rs.getInt(20);
            result[20] = rs.getInt(21);
            result[21] = rs.getInt(22);
            result[22] = rs.getInt(23);
            result[23] = rs.getInt(24);

            result[24] = rs.getInt(25);
            result[25] = rs.getInt(26);
            result[26] = rs.getInt(27);
            result[27] = rs.getInt(28);
            result[28] = rs.getInt(29);
            result[29] = rs.getInt(30);
            result[30] = rs.getInt(31);

            String df8 = (String) rs.getObject(32);
            result[31] = df8;
            String df9 = (String) rs.getObject(33);
            result[32] = df9;
            String df10 = (String) rs.getObject(34);
            result[33] = df10;
            String df11 = (String) rs.getObject(35);
            result[34] = df11;
            String df12 = (String) rs.getObject(36);
            result[35] = df12;
            String df13 = (String) rs.getObject(37);
            result[36] = df13;
            String df14 = (String) rs.getObject(38);
            result[37] = df14;

            result[38] = rs.getInt(39);
            result[39] = rs.getInt(40);
            result[40] = rs.getInt(41);
            result[41] = rs.getInt(42);
            result[42] = rs.getInt(43);
            result[43] = rs.getInt(44);
            result[44] = rs.getInt(45);

            result[45] = rs.getInt(46);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;

    }

    public static void MaintenanceShellPressMaintenanceInsert(
            int idIn,
            String MachineCodeIn,
            String MachineNameIn,
            int LastMaintenanceDate1In,
            int LastMaintenanceDate2In,
            int LastMaintenanceDate3In,
            int LastMaintenanceDate4In,
            int LastMaintenanceDate5In,
            int LastMaintenanceDate6In,
            int LastMaintenanceDate7In,
            int TargetProduction1In,
            int TargetProduction2In,
            int TargetProduction3In,
            int TargetProduction4In,
            int TargetProduction5In,
            int TargetProduction6In,
            int TargetProduction7In,
            int Production1In,
            int Production2In,
            int Production3In,
            int Production4In,
            int Production5In,
            int Production6In,
            int Production7In,
            int PlusMinus1In,
            int PlusMinus2In,
            int PlusMinus3In,
            int PlusMinus4In,
            int PlusMinus5In,
            int PlusMinus6In,
            int PlusMinus7In,
            int MaintenanceDueDate1In,
            int MaintenanceDueDate2In,
            int MaintenanceDueDate3In,
            int MaintenanceDueDate4In,
            int MaintenanceDueDate5In,
            int MaintenanceDueDate6In,
            int MaintenanceDueDate7In,
            int DaysRemaining1In,
            int DaysRemaining2In,
            int DaysRemaining3In,
            int DaysRemaining4In,
            int DaysRemaining5In,
            int DaysRemaining6In,
            int DaysRemaining7In
    )
            throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement ShellPressMaintenanceInsert = conn.prepareStatement("insert into MainShellPressMaintenance values(?,?,?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?)");

        ShellPressMaintenanceInsert.setInt(1, idIn);
        ShellPressMaintenanceInsert.setString(2, MachineCodeIn);
        ShellPressMaintenanceInsert.setString(3, MachineNameIn);

        ShellPressMaintenanceInsert.setInt(4, LastMaintenanceDate1In);
        ShellPressMaintenanceInsert.setInt(5, LastMaintenanceDate2In);
        ShellPressMaintenanceInsert.setInt(6, LastMaintenanceDate3In);
        ShellPressMaintenanceInsert.setInt(7, LastMaintenanceDate4In);
        ShellPressMaintenanceInsert.setInt(8, LastMaintenanceDate5In);
        ShellPressMaintenanceInsert.setInt(9, LastMaintenanceDate6In);
        ShellPressMaintenanceInsert.setInt(10, LastMaintenanceDate7In);

        ShellPressMaintenanceInsert.setInt(11, TargetProduction1In);
        ShellPressMaintenanceInsert.setInt(12, TargetProduction2In);
        ShellPressMaintenanceInsert.setInt(13, TargetProduction3In);
        ShellPressMaintenanceInsert.setInt(14, TargetProduction4In);
        ShellPressMaintenanceInsert.setInt(15, TargetProduction5In);
        ShellPressMaintenanceInsert.setInt(16, TargetProduction6In);
        ShellPressMaintenanceInsert.setInt(17, TargetProduction7In);

        ShellPressMaintenanceInsert.setInt(18, Production1In);
        ShellPressMaintenanceInsert.setInt(19, Production2In);
        ShellPressMaintenanceInsert.setInt(20, Production3In);
        ShellPressMaintenanceInsert.setInt(21, Production4In);
        ShellPressMaintenanceInsert.setInt(22, Production5In);
        ShellPressMaintenanceInsert.setInt(23, Production6In);
        ShellPressMaintenanceInsert.setInt(24, Production7In);

        ShellPressMaintenanceInsert.setInt(25, PlusMinus1In);
        ShellPressMaintenanceInsert.setInt(26, PlusMinus2In);
        ShellPressMaintenanceInsert.setInt(27, PlusMinus3In);
        ShellPressMaintenanceInsert.setInt(28, PlusMinus4In);
        ShellPressMaintenanceInsert.setInt(29, PlusMinus5In);
        ShellPressMaintenanceInsert.setInt(30, PlusMinus6In);
        ShellPressMaintenanceInsert.setInt(31, PlusMinus7In);

        ShellPressMaintenanceInsert.setInt(32, MaintenanceDueDate1In);
        ShellPressMaintenanceInsert.setInt(33, MaintenanceDueDate2In);
        ShellPressMaintenanceInsert.setInt(34, MaintenanceDueDate3In);
        ShellPressMaintenanceInsert.setInt(35, MaintenanceDueDate4In);
        ShellPressMaintenanceInsert.setInt(36, MaintenanceDueDate5In);
        ShellPressMaintenanceInsert.setInt(37, MaintenanceDueDate6In);
        ShellPressMaintenanceInsert.setInt(38, MaintenanceDueDate7In);

        ShellPressMaintenanceInsert.setInt(39, DaysRemaining1In);
        ShellPressMaintenanceInsert.setInt(40, DaysRemaining2In);
        ShellPressMaintenanceInsert.setInt(41, DaysRemaining3In);
        ShellPressMaintenanceInsert.setInt(42, DaysRemaining4In);
        ShellPressMaintenanceInsert.setInt(43, DaysRemaining5In);
        ShellPressMaintenanceInsert.setInt(44, DaysRemaining6In);
        ShellPressMaintenanceInsert.setInt(45, DaysRemaining7In);

        ShellPressMaintenanceInsert.setString(46, dateF);

        ShellPressMaintenanceInsert.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static void MaintenanceShellPressMaintenanceUpdate(
            String MachineCodeIn,
            String MachineNameIn,
            int LastMaintenanceDate1In,
            int LastMaintenanceDate2In,
            int LastMaintenanceDate3In,
            int LastMaintenanceDate4In,
            int LastMaintenanceDate5In,
            int LastMaintenanceDate6In,
            int LastMaintenanceDate7In,
            int TargetProduction1In,
            int TargetProduction2In,
            int TargetProduction3In,
            int TargetProduction4In,
            int TargetProduction5In,
            int TargetProduction6In,
            int TargetProduction7In,
            int Production1In,
            int Production2In,
            int Production3In,
            int Production4In,
            int Production5In,
            int Production6In,
            int Production7In,
            int PlusMinus1In,
            int PlusMinus2In,
            int PlusMinus3In,
            int PlusMinus4In,
            int PlusMinus5In,
            int PlusMinus6In,
            int PlusMinus7In,
            int MaintenanceDueDate1In,
            int MaintenanceDueDate2In,
            int MaintenanceDueDate3In,
            int MaintenanceDueDate4In,
            int MaintenanceDueDate5In,
            int MaintenanceDueDate6In,
            int MaintenanceDueDate7In,
            int DaysRemaining1In,
            int DaysRemaining2In,
            int DaysRemaining3In,
            int DaysRemaining4In,
            int DaysRemaining5In,
            int DaysRemaining6In,
            int DaysRemaining7In,
            int idIn
    ) throws SQLException {

        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(10);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        String sql = 
                "  update MainShellPressMaintenance "
                + "setMachineCode=?, \n"
                + "MachineName=?, \n"
                + "LastMaintenanceDate1=?, \n"
                + "LastMaintenanceDate2=?, \n"
                + "LastMaintenanceDate3=?, \n"
                + "LastMaintenanceDate4=?, \n"
                + "LastMaintenanceDate5=?, \n"
                + "LastMaintenanceDate6=?, \n"
                + "LastMaintenanceDate7=?, \n"
                + "TargetProduction1=?, \n"
                + "TargetProduction2=?, \n"
                + "TargetProduction3=?, \n"
                + "TargetProduction4=?, \n"
                + "TargetProduction5=?, \n"
                + "TargetProduction6=?, \n"
                + "TargetProduction7=?, \n"
                + "Production1=?, \n"
                + "Production2=?, \n"
                + "Production3=?, \n"
                + "Production4=?, \n"
                + "Production5=?, \n"
                + "Production6=?, \n"
                + "Production7=?, \n"
                + "PlusMinus1=?, \n"
                + "PlusMinus2=?, \n"
                + "PlusMinus3=?, \n"
                + "PlusMinus4=?, \n"
                + "PlusMinus5=?, \n"
                + "PlusMinus6=?, \n"
                + "PlusMinus7=?, \n"
                + "MaintenanceDueDate1=?, \n"
                + "MaintenanceDueDate2=?, \n"
                + "MaintenanceDueDate3=?, \n"
                + "MaintenanceDueDate4=?, \n"
                + "MaintenanceDueDate5=?, \n"
                + "MaintenanceDueDate6=?, \n"
                + "MaintenanceDueDate7=?, \n"
                + "DaysRemaining1=?, \n"
                + "DaysRemaining2=?, \n"
                + "DaysRemaining3=?, \n"
                + "DaysRemaining4=?, \n"
                + "DaysRemaining5=?, \n"
                + "DaysRemaining6=?, \n"
                + "DaysRemaining7=?, \n"
                + " where ID=?";

        PreparedStatement ShellPressMaintenanceUpdate = conn.prepareStatement(sql);

        ShellPressMaintenanceUpdate.setString(1, MachineCodeIn);
        ShellPressMaintenanceUpdate.setString(2, MachineNameIn);

        ShellPressMaintenanceUpdate.setInt(3, LastMaintenanceDate1In);
        ShellPressMaintenanceUpdate.setInt(4, LastMaintenanceDate2In);
        ShellPressMaintenanceUpdate.setInt(5, LastMaintenanceDate3In);
        ShellPressMaintenanceUpdate.setInt(6, LastMaintenanceDate4In);
        ShellPressMaintenanceUpdate.setInt(7, LastMaintenanceDate5In);
        ShellPressMaintenanceUpdate.setInt(8, LastMaintenanceDate6In);
        ShellPressMaintenanceUpdate.setInt(9, LastMaintenanceDate7In);

        ShellPressMaintenanceUpdate.setInt(10, TargetProduction1In);
        ShellPressMaintenanceUpdate.setInt(11, TargetProduction2In);
        ShellPressMaintenanceUpdate.setInt(12, TargetProduction3In);
        ShellPressMaintenanceUpdate.setInt(13, TargetProduction4In);
        ShellPressMaintenanceUpdate.setInt(14, TargetProduction5In);
        ShellPressMaintenanceUpdate.setInt(15, TargetProduction6In);
        ShellPressMaintenanceUpdate.setInt(16, TargetProduction7In);

        ShellPressMaintenanceUpdate.setInt(17, Production1In);
        ShellPressMaintenanceUpdate.setInt(18, Production2In);
        ShellPressMaintenanceUpdate.setInt(19, Production3In);
        ShellPressMaintenanceUpdate.setInt(20, Production4In);
        ShellPressMaintenanceUpdate.setInt(21, Production5In);
        ShellPressMaintenanceUpdate.setInt(22, Production6In);
        ShellPressMaintenanceUpdate.setInt(23, Production7In);

        ShellPressMaintenanceUpdate.setInt(24, PlusMinus1In);
        ShellPressMaintenanceUpdate.setInt(25, PlusMinus2In);
        ShellPressMaintenanceUpdate.setInt(26, PlusMinus3In);
        ShellPressMaintenanceUpdate.setInt(27, PlusMinus4In);
        ShellPressMaintenanceUpdate.setInt(28, PlusMinus5In);
        ShellPressMaintenanceUpdate.setInt(39, PlusMinus6In);
        ShellPressMaintenanceUpdate.setInt(40, PlusMinus7In);

        ShellPressMaintenanceUpdate.setInt(41, MaintenanceDueDate1In);
        ShellPressMaintenanceUpdate.setInt(42, MaintenanceDueDate2In);
        ShellPressMaintenanceUpdate.setInt(43, MaintenanceDueDate3In);
        ShellPressMaintenanceUpdate.setInt(44, MaintenanceDueDate4In);
        ShellPressMaintenanceUpdate.setInt(45, MaintenanceDueDate5In);
        ShellPressMaintenanceUpdate.setInt(46, MaintenanceDueDate6In);
        ShellPressMaintenanceUpdate.setInt(47, MaintenanceDueDate7In);

        ShellPressMaintenanceUpdate.setInt(48, DaysRemaining1In);
        ShellPressMaintenanceUpdate.setInt(49, DaysRemaining2In);
        ShellPressMaintenanceUpdate.setInt(50, DaysRemaining3In);
        ShellPressMaintenanceUpdate.setInt(51, DaysRemaining4In);
        ShellPressMaintenanceUpdate.setInt(52, DaysRemaining5In);
        ShellPressMaintenanceUpdate.setInt(53, DaysRemaining6In);
        ShellPressMaintenanceUpdate.setInt(54, DaysRemaining7In);

        ShellPressMaintenanceUpdate.setInt(55, idIn);

        ShellPressMaintenanceUpdate.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        s.close();
        conn.close();

    }

    public static Object[] MaintenanceShellPressMaintenanceGetNextEntryById(int idIn) throws SQLException {

        Object[] result = new Object[46];
        int nextId = idIn + 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);

            String df1 = (String) rs.getObject(4);
            result[3] = df1;
            String df2 = (String) rs.getObject(5);
            result[4] = df2;
            String df3 = (String) rs.getObject(6);
            result[5] = df3;
            String df4 = (String) rs.getObject(7);
            result[6] = df4;
            String df5 = (String) rs.getObject(8);
            result[7] = df5;
            String df6 = (String) rs.getObject(9);
            result[8] = df6;
            String df7 = (String) rs.getObject(10);
            result[9] = df7;

            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getInt(18);
            result[18] = rs.getInt(19);
            result[19] = rs.getInt(20);
            result[20] = rs.getInt(21);
            result[21] = rs.getInt(22);
            result[22] = rs.getInt(23);
            result[23] = rs.getInt(24);

            result[24] = rs.getInt(25);
            result[25] = rs.getInt(26);
            result[26] = rs.getInt(27);
            result[27] = rs.getInt(28);
            result[28] = rs.getInt(29);
            result[29] = rs.getInt(30);
            result[30] = rs.getInt(31);

            String df8 = (String) rs.getObject(32);
            result[31] = df8;
            String df9 = (String) rs.getObject(33);
            result[32] = df9;
            String df10 = (String) rs.getObject(34);
            result[33] = df10;
            String df11 = (String) rs.getObject(35);
            result[34] = df11;
            String df12 = (String) rs.getObject(36);
            result[35] = df12;
            String df13 = (String) rs.getObject(37);
            result[36] = df13;
            String df14 = (String) rs.getObject(38);
            result[37] = df14;

            result[38] = rs.getInt(39);
            result[39] = rs.getInt(40);
            result[40] = rs.getInt(41);
            result[41] = rs.getInt(42);
            result[42] = rs.getInt(43);
            result[43] = rs.getInt(44);
            result[44] = rs.getInt(45);

            result[45] = rs.getInt(46);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] MaintenanceShellPressMaintenanceGetPreviousEntryById(int idIn) throws SQLException {

        Object[] result = new Object[16];
        int nextId = idIn - 1;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT * FROM MainShellPressMaintenance WHERE MainShellPressMaintenance.ID = \"" + nextId + "\";";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            result[0] = rs.getInt(1);
            result[1] = rs.getString(2);
            result[2] = rs.getString(3);

            String df1 = (String) rs.getObject(4);
            result[3] = df1;
            String df2 = (String) rs.getObject(5);
            result[4] = df2;
            String df3 = (String) rs.getObject(6);
            result[5] = df3;
            String df4 = (String) rs.getObject(7);
            result[6] = df4;
            String df5 = (String) rs.getObject(8);
            result[7] = df5;
            String df6 = (String) rs.getObject(9);
            result[8] = df6;
            String df7 = (String) rs.getObject(10);
            result[9] = df7;

            result[10] = rs.getInt(11);
            result[11] = rs.getInt(12);
            result[12] = rs.getInt(13);
            result[13] = rs.getInt(14);
            result[14] = rs.getInt(15);
            result[15] = rs.getInt(16);
            result[16] = rs.getInt(17);

            result[17] = rs.getInt(18);
            result[18] = rs.getInt(19);
            result[19] = rs.getInt(20);
            result[20] = rs.getInt(21);
            result[21] = rs.getInt(22);
            result[22] = rs.getInt(23);
            result[23] = rs.getInt(24);

            result[24] = rs.getInt(25);
            result[25] = rs.getInt(26);
            result[26] = rs.getInt(27);
            result[27] = rs.getInt(28);
            result[28] = rs.getInt(29);
            result[29] = rs.getInt(30);
            result[30] = rs.getInt(31);

            String df8 = (String) rs.getObject(32);
            result[31] = df8;
            String df9 = (String) rs.getObject(33);
            result[32] = df9;
            String df10 = (String) rs.getObject(34);
            result[33] = df10;
            String df11 = (String) rs.getObject(35);
            result[34] = df11;
            String df12 = (String) rs.getObject(36);
            result[35] = df12;
            String df13 = (String) rs.getObject(37);
            result[36] = df13;
            String df14 = (String) rs.getObject(38);
            result[37] = df14;

            result[38] = rs.getInt(39);
            result[39] = rs.getInt(40);
            result[40] = rs.getInt(41);
            result[41] = rs.getInt(42);
            result[42] = rs.getInt(43);
            result[43] = rs.getInt(44);
            result[44] = rs.getInt(45);

            result[45] = rs.getInt(46);

            rs.close();
            s.close();
            conn.close();

        }

        // //////////////////////////////////////////////////////////////////////
        return result;
    }

    public static Object[] MaintenanceShellPressMaintenanceCalculateTotalsByMonth(String monthIn, String yearIn) throws SQLException {

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

        Object[] total = new Object[14];

        // Return the sum of the selected month and Line - SQL Query SUM WHERE
        // Month Contains /06/
        // Query ////////
        Connection conn = Connect();
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
            total[0] = rs1.getInt(1);
        }

        // ///////////////////////////
        // W12 //////////////////
        s.execute(sql2);

        ResultSet rs2 = s.getResultSet();
        while ((rs2 != null) && (rs2.next())) {
            total[1] = rs2.getInt(1);
        }

        // ///////////////////////////
        // W21 /////////////////
        s.execute(sql3);

        ResultSet rs3 = s.getResultSet();
        while ((rs3 != null) && (rs3.next())) {
            total[2] = rs3.getInt(1);
        }

        // ///////////////////////////
        // W22 /////////////////
        s.execute(sql4);

        ResultSet rs4 = s.getResultSet();
        while ((rs4 != null) && (rs4.next())) {
            total[3] = rs4.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql5);

        ResultSet rs5 = s.getResultSet();
        while ((rs5 != null) && (rs5.next())) {
            total[4] = rs5.getInt(1);
        }

        // ///////////////////////////
        // W32 /////////////////
        s.execute(sql6);

        ResultSet rs6 = s.getResultSet();
        while ((rs6 != null) && (rs6.next())) {
            total[5] = rs6.getInt(1);
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
        return total;
    }

    public static JPanel MaintenanceShellPressMaintenanceSummaryTable(int in) throws SQLException {

        JPanel outerPanel = new JPanel(new BorderLayout());

        Connection conn = Connect();
        Statement stmt = conn.createStatement();
        stmt.setQueryTimeout(10);

        PreparedStatement psmt = conn.prepareStatement("SELECT Date, SP01, SP02, SP03, FMI41, FMI42, SP04, ID FROM MainShellPressProduction ORDER BY Date DESC");
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
            row.add(rs.getString(6));
            row.add(rs.getString(7));
            row.add(rs.getString(8));

            data.add(row);
        }

        // Now create the table
        DefaultTableModel model = new DefaultTableModel(data, cols);

        JTable table = new JTable(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.getColumnModel().getColumn(7).setMaxWidth(40);

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
                    System.out.println(table.getValueAt(table.getSelectedRow(), 7).toString());

                    String idString = table.getValueAt(table.getSelectedRow(), 7).toString();
                    int id = Integer.valueOf(idString);
                    ShellPressProduction linersAndShells;
                    try {
                        ShellPressProduction shellPressProduction = new ShellPressProduction(1, -2);
                        shellPressProduction.setShellPressProductionToID(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

    // ANALYTICS
    public static int AnalyticsGetHighestID() throws SQLException {

        int highestID = 0;

        Connection conn = Connect();
        Statement s = conn.createStatement();

        // QUERY /////////////////////////////////////////////////////////////
        String selTable = "SELECT MAX(Analytics.[ID]) FROM Analytics;";
        // System.out.println(selTable);
        s.setQueryTimeout(10);
        s.execute(selTable);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            highestID = rs.getInt(1);
            // System.out.println("Highest ID :  " + highestID);

        }

        rs.close();
        s.close();
        conn.close();

        // //////////////////////////////////////////////////////////////////////
        return highestID;
    }

    public static void incrementViewsAnalytics(int idIn, int timesOpenedIn, int linerEntryEntriesIn, int linerUsageEntriesIn, int LSSPMEntriesIn,
            int meetingQualityIssuesIn, int optimeEntriesIn, int productionMeetingEntriesIn, int stolleEntriesIn) throws SQLException {

        Connection conn = Connect();
		// Statement s = conn.createStatement();

        // TimeStamp in String Format
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateF = format.format(date);

        // QUERY
        // //////////////////////////////////////////////////////////////////////
        PreparedStatement viewUpdate = conn.prepareStatement("insert into Analytics values(?,?,?,?,?,?,?,?,?,?)");

        viewUpdate.setInt(1, AnalyticsGetHighestID() + 1);
        viewUpdate.setInt(2, timesOpenedIn);
        viewUpdate.setInt(3, linerEntryEntriesIn);
        viewUpdate.setInt(4, linerUsageEntriesIn);
        viewUpdate.setInt(5, LSSPMEntriesIn);
        viewUpdate.setInt(6, meetingQualityIssuesIn);
        viewUpdate.setInt(7, optimeEntriesIn);
        viewUpdate.setInt(8, productionMeetingEntriesIn);
        viewUpdate.setInt(9, stolleEntriesIn);
        viewUpdate.setString(10, dateF);

        viewUpdate.executeUpdate();

        // /////////////////////////////////////////////////////////////////////////////
        conn.close();
    }

    public static int getTotalViews(String columnHeader) throws SQLException {

        int total = 0;

        String sql = "SELECT SUM(" + columnHeader + ") FROM Analytics;";
        Connection conn = Connect();
        Statement s = conn.createStatement();
        s.setQueryTimeout(2);
        s.execute(sql);

        ResultSet rs = s.getResultSet();

        while ((rs != null) && (rs.next())) {

            total = rs.getInt(1);
        }

        rs.close();
        s.close();
        conn.close();

        return total;
    }

    // General Methods
    public static String convertDatePickerToString(Date dateIn) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(dateIn);

        return date;

    }

    public static void infoBox(String infoMessage, String location) {

        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + location, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String convertDate2(Date dateIn) {

        String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateIn);

        return modifiedDate;
    }

}
