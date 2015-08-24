package com.rexam.program.view;


import java.sql.SQLException;

import javax.jnlp.BasicService;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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

        new Desktop();

        // Add a view to analytics.
//        SQLiteConnection.AnalyticsUpdate("Program");
//        SQLOnlineConnection.updateTimesOpened("Program");

//        try {
//            basicService = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
//        } catch (UnavailableServiceException e) {
//            System.err.println("Lookup failed: " + e);
//        }
    }

 

}
