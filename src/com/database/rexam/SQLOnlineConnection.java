// Chris Maher Student Number: 1719602 Project 5
package com.database.rexam;

import static com.database.rexam.SQLOnlineConnection.DB_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLOnlineConnection {

//    protected static final String USERNAME = "rexam";
//    protected static final String PASSWORD = "rexam2014";
//    protected static final String CONN_STRING = "jdbc:mysql://db4free.net:3306/rexam";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://db4free.net:3306/rexam";
    static final String USER = "rexam";
    static final String PASS = "rexam2014";

    public static void main(String[] args) {

        updateTimesOpened("ShellPressProduction");

    }//end main

    // Analytics
    public static int getTimesOpened(String titleIn) {

        int timesOpened = 0;

        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);            

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt.setQueryTimeout(2);
            String sql;
            sql = "SELECT TimesOpened FROM Analytics Where Name = \'" + titleIn + "\'; ";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                timesOpened = rs.getInt("TimesOpened");

                //Display values
                System.out.println("timesOpened: " + timesOpened);
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                
                System.out.println("Error 2");
                
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                System.out.println("Error 3");
            }//end finally try
        }//end try

        System.out.println("Goodbye!");
        return timesOpened;

    }

    public static void updateTimesOpened(String titleIn) {

        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt.setQueryTimeout(2);
            String sql;
            int TimesOpened2 = getTimesOpened(titleIn) + 1;

            sql = "UPDATE Analytics SET TimesOpened = \'" + TimesOpened2 + "\' WHERE Name = \'" + titleIn + "\' ; ";
            System.out.println("SQL " + sql);
            stmt.execute(sql);

            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

        System.out.println("Goodbye!");

    }

    // Help
    public static void HelpInsert(int idIn, String sectionIn, String nameIn, String problemIn, String solutionIn) {

        try {
            Connection conn = null;
            Statement stmt = null;

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating INSERT statement...");
            Statement s = conn.createStatement();
            s.setQueryTimeout(10);

            // TimeStamp in String Format
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateF = format.format(date);

            // QUERY
            // //////////////////////////////////////////////////////////////////////
            PreparedStatement updateLproductionMeeting = conn.prepareStatement("insert into Help values(?,?,?,?,?,?)");

            updateLproductionMeeting.setInt(1, idIn);
            updateLproductionMeeting.setString(2, sectionIn);
            updateLproductionMeeting.setString(3, nameIn);
            updateLproductionMeeting.setString(4, problemIn);
            updateLproductionMeeting.setString(5, solutionIn);
            updateLproductionMeeting.setString(6, dateF);

            updateLproductionMeeting.executeUpdate();

            // /////////////////////////////////////////////////////////////////////////////
            updateLproductionMeeting.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLOnlineConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLOnlineConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void HelpUpdate(
            String sectionIn, String nameIn, String problemIn, String solutionIn, int idIn
    )
            throws SQLException {

        try {
            Connection conn = null;
            Statement stmt = null;

            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            Statement s = conn.createStatement();
            s.setQueryTimeout(2);

            // QUERY
            // //////////////////////////////////////////////////////////////////////
            System.out.print("ID : " + idIn);
            String sql = "UPDATE Help SET Section=?, Name=?, Problem=?, Solution=? WHERE ID=?";
            System.out.print("SQL : " + sql);

            PreparedStatement updateEmployees = conn.prepareStatement(sql);
            updateEmployees.setQueryTimeout(10);

            updateEmployees.setString(1, sectionIn);
            updateEmployees.setString(2, nameIn);
            updateEmployees.setString(3, problemIn);
            updateEmployees.setString(4, solutionIn);
            updateEmployees.setInt(5, idIn);
            updateEmployees.executeUpdate();

            // /////////////////////////////////////////////////////////////////////////////
            // updateEmployees.close();
            s.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLOnlineConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    // Help Methods
//    public static void HelpDelete2(int idIn) throws SQLException {
//
//        Connection conn = Connect();
//        conn.setAutoCommit(false);
//        Statement s = conn.createStatement();
//        s.setQueryTimeout(10);
//
//        // QUERY
//        // //////////////////////////////////////////////////////////////////////
//        String selTable = "Delete FROM Help WHERE Help.ID = " + idIn + ";";
//
//        System.out.println("Error : " + selTable);
//
//        s.execute(selTable);
//
//        // /////////////////////////////////////////////////////////////////////////////
//        conn.commit();
//        s.close();
//        conn.close();
//
//    }
//

}//end FirstExample

