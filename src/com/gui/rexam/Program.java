package com.gui.rexam;

// WEBSTART 
// TODO Update statistics on ChrisMaher.info with Usage / Views Etc..
// Fill JTextfields if record Exists
// Liners and Shells Update not bringing up summary screen
// Format Percentages on JTables to 2 Decimals

 // Create Database on ChrisMaher.Info to Store Analytics.

import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.jnlp.*;
import com.database.rexam.SQLiteConnection;

public class Program {

    static BasicService basicService = null;

    //    static BasicService basicService = null;
    // Create a Splash Screen.
    public static void main(String[] args) throws SQLException {
        

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (Exception e) {

            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }

        new Gui();

        // Add a view to analytics.
        SQLiteConnection.AnalyticsUpdate("Program");

//        try {
//            basicService = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
//        } catch (UnavailableServiceException e) {
//            System.err.println("Lookup failed: " + e);
//        }
    }

 

}
