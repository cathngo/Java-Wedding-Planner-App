/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    
    public static Connection conn;
    
    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:Indiefy.db");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /* Note that this method is called from the LoginScreen Controller */
    public boolean tryLogin(String username, String password) {
        
        // Assume that the user will enter incorrect credentials
        boolean loginSuccessful = false;
        
        try {
            // Open the connection

            // Use a Prepared Statement to query the database to check entered credentials

            
            // Check the Result Set to see if the query returned a tuple, what should happen then?
            
            // Close the Result Set
               
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return loginSuccessful;
    }
    

    
    public static void createLoginTable() throws SQLException {
        
        // Initialise your Prepared Statement to create the LOGIN table
        PreparedStatement createLoginTable = null;

       //Initialise your Prepared Statement to add data to the LOGIN table
        PreparedStatement insertData = null;
        // Initialise your Result Set
        ResultSet rs = null;
        // Open the connection
        openConnection();
        
        try {
            System.out.println("Checking LOGIN table ");
            // Check if the Login Table exists
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "LOGIN", null);
            if (!rs.next()) {
                // Use the connection to create the Prepared Statement that will create the LOGIN table, and then and execute it
                createLoginTable = conn.prepareStatement("CREATE TABLE LOGIN (USERNAME VARCHAR(100), PASSWORD VARCHAR(100))");
                createLoginTable.execute();
                System.out.println("LOGIN table created");
                insertData = conn.prepareStatement("INSERT INTO LOGIN(USERNAME,PASSWORD) " + "VALUES ('Pretentious', 'Hipster')");
                insertData.execute();
               
            } else {
                System.out.println("LOGIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
