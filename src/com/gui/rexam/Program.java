package com.gui.rexam;

// TODO Update statistics on ChrisMaher.info with Usage / Views Etc..
import java.sql.SQLException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.database.rexam.SQLiteConnection;

public class Program {

	// Create a Splash Screen.
    public static void main(String[] args) throws SQLException {

        System.out.println("Test");

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
        SQLiteConnection.incrementViewsAnalytics(0, 1, 0, 0, 0, 0, 0, 0, 0);

    }

}
