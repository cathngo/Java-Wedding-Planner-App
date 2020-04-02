/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.sql.SQLException;

/**
 *
 * @author jaydenso
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
    //Method to set up database if needed to
       DatabaseManager.setupDatabaseOnFirstRun();
       //Methods to print out all entries in a table
       DatabaseManager.printEvent();
       DatabaseManager.printGuest();
       DatabaseManager.printAdmin();
       DatabaseManager.printInvitation();
       DatabaseManager.printRsvp();
    }
    
}
