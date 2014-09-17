package com.database.rexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessConnect {
	public static void main(String[] args) throws Exception {

		// LSSPMEntryInsert("13/06/2014", "Comment");
		

	}

	public static Connection connect() throws Exception {

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:ucanaccess://D:/Drive Google/Back-up (1)/I Drive/chris.accdb");

		return conn;

	}

	// LLSPMENTRY METHODS ////////////////////////////////////////////////////////////////////////
	
	public static Object[] LSSPMEntryqueryDate(Date dateIn) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String df = (sdf.format(dateIn));

		// Need to format dateIn to proper Syntax ----> #2/2/2012#

		Object[] result = new Object[3];

		Connection conn = connect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////

		String selTable = "SELECT * FROM LSSPMEntry WHERE LSSPMEntry.EntryDate = \"#"
				+ df + "#\"";
		s.setQueryTimeout(30);
		s.execute(selTable);
		
		ResultSet rs = s.getResultSet();
		while ((rs != null) && (rs.next())) {
			result[0] = rs.getString(1);
			String df1 = convertDate((Date) rs.getObject(2));
			result[1] = df1;
			result[2] = rs.getString(3);
		}

		// /////////////////////////////////////////////////////////////////////

		return result;

	}

	public static Object[] LSSPMEntryqueryId(int idIn) throws Exception {

		Object[] result = new Object[3];
		Connection conn = connect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////

		String selTable = "SELECT * FROM LSSPMEntry WHERE LSSPMEntry.[ID] = "
				+ idIn + "";
		//System.out.println(selTable);

		s.execute(selTable);
		ResultSet rs = s.getResultSet();
		while ((rs != null) && (rs.next())) {
			
			result[0] = rs.getString(1);
			String df1 = convertDate((Date) rs.getObject(2));
			result[1] = df1;
			result[2] = rs.getString(3);
			
		}

		// /////////////////////////////////////////////////////////////////////

		return result;
	}

	public static int LSSPMEntryqueryHighest() throws Exception {
		
		// Select all IDs then return highest ID as int.

		Object[] result = new Object[3];
		String highestString;
		int highest = 1;

		Connection conn = connect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////

		String selTable = "SELECT Max(LSSPMEntry.[ID]) FROM LSSPMEntry";
		s.execute(selTable);
		ResultSet rs = s.getResultSet();
		while ((rs != null) && (rs.next())) {
			
			result[0] = rs.getString(1);
			highestString = (String)result[0];
			highest = Integer.parseInt(highestString);
			//System.out.println(highest);
		}

		// /////////////////////////////////////////////////////////////////////

		return highest;

	}

	public static int LSSPMEntryqueryLowest() throws Exception {
		
		// Select all IDs then return highest ID as int.

		Object[] result = new Object[3];
		String lowestString;
		int lowest = 1;

		Connection conn = connect();
		Statement s = conn.createStatement();

		// QUERY /////////////////////////////////////////////////////////////

		String selTable = "SELECT Min(LSSPMEntry.[ID]) FROM LSSPMEntry";
		s.execute(selTable);
		ResultSet rs = s.getResultSet();
		while ((rs != null) && (rs.next())) {
			
			result[0] = rs.getString(1);
			lowestString = (String)result[0];
			lowest = Integer.parseInt(lowestString);
			//System.out.println(highest);
		}

		// /////////////////////////////////////////////////////////////////////

		return lowest;

	}
	
	public static Object[] LSSPMEntryqueryHighestArrayDateFormatted() throws Exception {
		
		// Select all IDs then return highest ID as int.

				Object[] result = new Object[3];
				

				Connection conn = connect();
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
				
				//  Convert result[1] into 3 ints
				
				String modifiedDate = new SimpleDateFormat("yyyy,MM,dd").format((Date)result[1]);
				result[1] = modifiedDate;
				
				String dateString = result[1]+"";
				System.out.println(dateString);
				int year = Integer.parseInt(dateString.substring(0, 4)); // Correct
				int month = Integer.parseInt(dateString.substring(5, 7)); // Correct
				int day = Integer.parseInt(dateString.substring(8, 10)); // Correct
				
				// /////////////////////////////////////////////////////////////////////
				System.out.println(year + " " + month + " " + day);
				
				result[0] = (int)year;
				
				result[1] = (int)month;
				result[2] = (int)day;
				
				return result;

	}

	public static String LSSPMEntryqueryHighestCommentFormatted() throws Exception {
		
		// Select all IDs then return highest ID as int.

				String result ="";
				

				Connection conn = connect();
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
	
	public static String LSSPMEntryqueryHighestStringDate() throws Exception {
		
		// Select all IDs then return highest ID as int.

				String result = "";
				

				Connection conn = connect();
				Statement s = conn.createStatement();

				// QUERY /////////////////////////////////////////////////////////////

				String selTable = "SELECT TOP 1 EntryDate From LSSPMEntry ORDER BY LSSPMEntry.ID DESC";
				System.out.println(selTable);
				s.execute(selTable);
				ResultSet rs = s.getResultSet();
				while ((rs != null) && (rs.next())) {
					
					result = rs.getString("EntryDate");
										
				}
				
				System.out.println(result);
				
				// /////////////////////////////////////////////////////////////////////

				return result;

	}
	
	public static void LSSPMEntryInsert(String dateIn, String commentIn) throws Exception {
		

		Connection conn = connect();
		Statement s = conn.createStatement();
		

		int ID = LSSPMEntryqueryHighest()+1;
		String date = dateIn;
		String comment = commentIn;
		

		// QUERY /////////////////////////////////////////////////////////////
		
		
		//System.out.println(dateIn);
		
		String insert = "INSERT INTO LSSPMEntry(EntryDate, Comment ) VALUES ( #"+dateIn+"#,\'"+comment+"\')";
		System.out.println(insert);
		s.execute(insert);

		// /////////////////////////////////////////////////////////////////////

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String convertDate(Date dateIn) {

		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(dateIn);

		return modifiedDate;
	}
	
	public static String convertDate2(Date dateIn) {

		String modifiedDate = new SimpleDateFormat("dd/MM/yyyy").format(dateIn);

		return modifiedDate;
	}
	

	public static int [] dateToThreeInts(Date date){
			
		int [] datesArray = new int [3];
		
		String modifiedDate = new SimpleDateFormat("yyyy,MM,dd").format(date);
		System.out.println(modifiedDate);		
		String year = modifiedDate.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = modifiedDate.substring(5, 7);
		int monthInt = Integer.parseInt(month) ;
		String day = modifiedDate.substring(8, 10);
		int dayInt = Integer.parseInt(day);
				
		datesArray[0] = yearInt;
		datesArray[1] = monthInt;
		datesArray[2] = dayInt;
		
		return datesArray;
		
	}

	public static int [] stringDateToThreeInts(String date){
		
		int [] datesArray = new int [3];
		
		// String modifiedDate = new SimpleDateFormat("yyyy,MM,dd").format(date);
		System.out.println(date);		
		String year = date.substring(0, 4);
		int yearInt = Integer.parseInt(year);
		String month = date.substring(5, 7);
		int monthInt = Integer.parseInt(month) ;
		String day = date.substring(8, 10);
		int dayInt = Integer.parseInt(day);
				
		datesArray[0] = yearInt;
		datesArray[1] = monthInt;
		datesArray[2] = dayInt;
		
		return datesArray;
		
	}
	
	
	
}

