<<<<<<< HEAD:src/main/java/au/edu/unsw/business/infs2605/fxstarterkit/App.java
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
=======
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
       //DatabaseManager.createEvent();
       //DatabaseManager.eventData();
      // DatabaseManager.guestData();
      //DatabaseManager.createRsvp();
       //DatabaseManager.rsvpData();
       DatabaseManager.setupDatabaseOnFirstRun();
       DatabaseManager.printInvitation();
    }
    
}
>>>>>>> e15fe72f865cf2b5fb0b07518810e13b4c2b0885:Assignment/src/main/java/au/edu/unsw/business/infs2605/fxstarterkit/App.java
