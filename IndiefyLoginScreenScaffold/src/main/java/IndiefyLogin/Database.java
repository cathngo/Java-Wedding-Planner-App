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
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    
    public static Connection conn;

    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:indiefyDB.db");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet(String sqlstatement) throws SQLException {
        openConnection();
        java.sql.Statement statement = conn.createStatement();
        ResultSet RS = statement.executeQuery(sqlstatement);
        return RS;
    }

    public void insertStatement(String insert_query) throws SQLException {
        java.sql.Statement stmt = null;
        openConnection();
        try {
            System.out.println("Database opened successfully");
            stmt = conn.createStatement();
            System.out.println("The query was: " + insert_query);
            stmt.executeUpdate(insert_query);
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        stmt.close();
    }
    
    public static void createLoginTable() {
        PreparedStatement createLoginTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking LOGIN table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "LOGIN", null);
            if (!rs.next()) {
                createLoginTable = conn.prepareStatement("CREATE TABLE LOGIN (USERNAME VARCHAR(100), PASSWORD VARCHAR(100))");
                createLoginTable.execute();
                System.out.println("LOGIN table created");
                insertDemoData = conn.prepareStatement("INSERT INTO LOGIN(USERNAME,PASSWORD) "
                        + "VALUES ('Pretentious','Hipster')");
                insertDemoData.execute();
            } else {
                System.out.println("LOGIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public static void createMusicTable() {
        PreparedStatement createMusicTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking MUSICLIST table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, null, "MUSICLIST", null);
            if (!rs.next()) {
                createMusicTable = conn.prepareStatement("CREATE TABLE MUSICLIST (ALBUM VARCHAR(100), ARTIST VARCHAR(100), GENRE VARCHAR(50), YEAR VARCHAR(4))");
                createMusicTable.execute();
                System.out.println("MUSIC table created");
                insertDemoData = conn.prepareStatement("INSERT INTO MUSICLIST(ALBUM,ARTIST,GENRE,YEAR) "
                        + "VALUES ('Map of the Soul','BTS','K-Pop','2020'), "
                        + "('Eternal Atake','Lil Uzi Vert','Hip-Hop/Rap','2020'), "
                        + "('After Hours','The Weeknd','R&B/Soul','2020'), "
                        + "'Prince Charming','Higher Brothers','Hip-Hop/Rap','2020'");
                insertDemoData.execute();
            } else {
                System.out.println("MUSICLIST table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
     