package au.edu.unsw.business.infs2605.fxstarterkit;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseManager {

    private static final String TABLE_NAME_FOR_EVENT = "event";
    private static final String TABLE_NAME_FOR_GUEST = "guest";
    private static final String TABLE_NAME_FOR_ADMIN = "admin";
    private static final String TABLE_NAME_FOR_INVITATION = "invitation";
    private static final String TABLE_NAME_FOR_RSVP = "rsvp";
    private static Connection conn;
    private static Connection sharedConnection;

    static boolean openConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.sharedConnection = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean closeConnection() {
        boolean wasThisMethodSuccessful = false;
        try {
            sharedConnection.close();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }
    
    private static boolean createEvent() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_EVENT + " ("
                    + "event_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "event_name TEXT, "
               
                    + "event_address TEXT, "
                    + "event_description TEXT, "
                    + "event_date TEXT, "
                    + "event_start_time TEXT, "
                    + "event_end_time TEXT, "
                 + "event_instructions TEXT)";

            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean createGuest() throws SQLException {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_GUEST + " ("
                    + "guest_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "guest_name TEXT, "
                    + "guest_email TEXT, "
                    + "guest_phone TEXT, "
                    + "guest_access_code TEXT) ";

            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }

    }

    private static boolean createAdmin() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_ADMIN + " ("
                    + "admin_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "admin_name TEXT, "
                    + "admin_username TEXT, "
                    + "admin_password TEXT) ";

            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean createInvitation() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_INVITATION + " ("
                    + "invitation_id INTEGER PRIMARY KEY AUTOINCREMENT, "
             
                    + "event_id INTEGER, "
                    + "guest_id INTEGER, "
                    + "admin_id INTEGER, "
                    + "FOREIGN KEY(event_id) REFERENCES event(event_id), "
                    + "FOREIGN KEY(guest_id) REFERENCES guest(guest_id), "
                    + "FOREIGN KEY(admin_id) REFERENCES admin(admin_id)) ";

            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean createRsvp() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String createTableSql = "CREATE TABLE " + DatabaseManager.TABLE_NAME_FOR_RSVP + " ("
                    + "rsvp_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "decision TEXT, "
                    + "date_time TEXT, "
                    + "diet_require TEXT, "
                    + "invitation_id INTEGER, "
                    + "FOREIGN KEY(invitation_id) REFERENCES invitation(invitation_id)) ";

            Statement smt = sharedConnection.createStatement();
            wasThisMethodSuccessful = smt.execute(createTableSql);
            DatabaseManager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean eventData() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + TABLE_NAME_FOR_EVENT
                    + " (event_name, event_address, event_description, event_date, event_start_time, event_end_time, event_instructions)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] eventName = {"Alan's Birthday", "Fiona's Wedding", "Friday Disco", "Jack's Party", "Surprise Party", "Julie's Wedding", "Liam's Party"};
            
            String[] eventAddress = {"21 Hop St, Yellow Hills NSW 2831 ", "1 Cropley Rd, Cropley NSW 2314", "Pira Rd, Pyrmont NSW 2009", "2 Mun Ave, Carl NSW 2128", "23 Hill Rd, Leign NSW 2134", "2 Line St, Viole NSW 2134", "23 Liam Rd, Sunny NSW 2342"};
            String[] eventDescription = {"It’s my birthday party! Reserve the date. Don’t arrive late!", "It’s my wedding! Reserve the date. Don’t arrive late!", "It’s my disco party! Reserve the date. Don’t arrive late!", "It’s my party! Reserve the date. Don’t arrive late!", "It’s Jack's birthday party! Reserve the date. Don’t arrive late!", "It’s my wedding! Reserve the date. Don’t arrive late!", "It’s my birthday party! Reserve the date. Don’t arrive late!"};
            String[] eventDate = {"12/4/2020", "4/4/2020", "12/6/2020", "15/8/2020", "5/5/2020", "30/9/2020", "28/12/2020"};
            String[] startTime = {"12:00pm", "10:00am", "9:00pm", "7:00pm", "4:00pm", "11:00am", "7:00pm"};
            String[] endTime = {"3:00pm", "3:00pm", "12:00am", "11:00pm", "7:00pm", "4:00pm", "11:00pm"};
            String[] eventInstructions = {"Please leave shoes at the door", "", "Please Bring Your ID", "No need to bring a present!", "Please arrive on time!", "", ""};
            for (int i = 0; i < eventName.length; i++) {
                psmt.setString(1, eventName[i]);

                psmt.setString(2, eventAddress[i]);
                psmt.setString(3, eventDescription[i]);
                psmt.setString(4, eventDate[i]);
                psmt.setString(5, startTime[i]);
                psmt.setString(6, endTime[i]);
                psmt.setString(7, eventInstructions[i]);
                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);
            }

            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }

    }

    private static boolean guestData() throws SQLException {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + DatabaseManager.TABLE_NAME_FOR_GUEST
                    + " (guest_name, guest_email, guest_phone, guest_access_code)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] guestName = {"Peter Kim", "William Wong", "Kelly Lu", "Alex O'Connor", "Jane Nguyen", "Vanessa Chor", "Phil So"};
            String[] guestEmail = {"Peter21@gmail.com ", "Will2093@gmail.com", "Kelly234@gmail.com", "Alex002@hotmail.com", "Jane_12@gmail.com", "Van23@gmail.com", "Phil.G@gmail.com"};
            String[] guestPhone = {"0409378231", "0439827365", "0427836472", "0426837645", "0498378265", "0498786375", "0401109387"};
            String[] guestCode = {"PeterKim2341", "WilliamWong9837", "KellyLu0193", "AlexOConnor9283", "JaneNguyen2938", "VanessaChor0283", "PhilSo2983"};

            for (int i = 0; i < guestName.length; i++) {
                psmt.setString(1, guestName[i]);
                psmt.setString(2, guestEmail[i]);
                psmt.setString(3, guestPhone[i]);
                psmt.setString(4, guestCode[i]);
                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);

            }
            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }

    }

    private static boolean adminData() {
        boolean wasThisMethodSuccessful = false;
        try {
            
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + DatabaseManager.TABLE_NAME_FOR_ADMIN
                    + " (admin_name, admin_username, admin_password)"
                    + " VALUES (?, ?, ?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] adminName = {"Blair Wang", "Cat Ngo", "Jayden So", "Sandy Qiu", "Mimi Chen", "Kathy Xu", "Sam Smith"};
            String[] adminUsername = {"admin462", "admin29138", "admin239", "admin2019", "admin39108", "admin2020", "admin1239"};
            String[] adminPassword = {"hello21", "chocolate23", "yellowblue34", "2019admin434", "password0394", "apples231", "secure092"};

            for (int i = 0; i < adminName.length; i++) {
                psmt.setString(1, adminName[i]);
                psmt.setString(2, adminUsername[i]);
                psmt.setString(3, adminPassword[i]);

                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);
            }

            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }

    }

    private static boolean invitationData() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + DatabaseManager.TABLE_NAME_FOR_INVITATION
                    + " (event_id, guest_id, admin_id)"
                    + " VALUES (?,?,?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
           
            int[] event_id = {1, 2, 3, 4, 5, 6, 7};
            int[] guest_id = {1, 2, 3, 4, 5, 6, 7};
            int[] admin_id = {1, 2, 3, 4, 5, 6, 7};

            for (int i = 0; i < event_id.length; i++) {
     
                psmt.setInt(1, event_id[i]);
                psmt.setInt(2, guest_id[i]);
                psmt.setInt(3, admin_id[i]);

                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);
            }

            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    private static boolean rsvpData() {
        boolean wasThisMethodSuccessful = false;
        try {
            DatabaseManager.openConnection();
            String sqlString = "INSERT INTO " + DatabaseManager.TABLE_NAME_FOR_RSVP
                    + " (decision, date_time, diet_require, invitation_id)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] decision = {"Yes", "Yes", "No", "No", "", "Yes", "No"};
            String[] date_time = {"12/3/2020", "14/2/2020", "23/5/2020", "2/4/2020", "16/6/2020", "15/8/2020", "17/9/2020"};
            String[] diet_require = {"N/A", "Peanut Allergy", "Vegan", "N/A", "Vegetarian", "N/A", "N/A"};
            int[] invitation_id = {1, 2, 3, 4, 5, 6, 7};

            for (int i = 0; i < invitation_id.length; i++) {
                psmt.setString(1, decision[i]);
                psmt.setString(2, date_time[i]);
                psmt.setString(3, diet_require[i]);
                psmt.setInt(4, invitation_id[i]);

                boolean wasThisRoundSuccessful = psmt.execute();
                wasThisMethodSuccessful = (wasThisMethodSuccessful && wasThisRoundSuccessful);
            }

            DatabaseManager.closeConnection();
            wasThisMethodSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return wasThisMethodSuccessful;
        }
    }

    public static void setupDatabaseOnFirstRun() throws SQLException {
        // check if we need to setup database
        DatabaseManager.openConnection();
        DatabaseMetaData dbmd = DatabaseManager.sharedConnection.getMetaData();

        ResultSet rs = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_EVENT, null);
        ResultSet rs1 = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_GUEST, null);
        ResultSet rs2 = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_ADMIN, null);
        ResultSet rs3 = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_INVITATION, null);
        ResultSet rs4 = dbmd.getTables(null, null, DatabaseManager.TABLE_NAME_FOR_RSVP, null);

        if (!rs.next()) {
            DatabaseManager.createEvent();
            DatabaseManager.eventData();

        } else {
            System.out.println("Event already exists");
            DatabaseManager.closeConnection();
        }
        if (!rs1.next()) {
            DatabaseManager.createGuest();
            DatabaseManager.guestData();
        } else {
            System.out.println("Guest already exists");
            DatabaseManager.closeConnection();
        }
        if (!rs2.next()) {
            DatabaseManager.createAdmin();
            DatabaseManager.adminData();
        } else {
            System.out.println("Admin already exists");
            DatabaseManager.closeConnection();
        }
        if (!rs3.next()) {
            DatabaseManager.createInvitation();
            DatabaseManager.invitationData();
        } else {
            System.out.println("Invitation already exists");
            DatabaseManager.closeConnection();
        }
        if (!rs4.next()) {
            DatabaseManager.createRsvp();
            DatabaseManager.rsvpData();
        } else {
            System.out.println("Rsvp already exists");
            DatabaseManager.closeConnection();
        }

        DatabaseManager.closeConnection();

    }

    public static String printObjectsInTable(String table) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        Statement st = conn.createStatement();
        ResultSet resultSet = st.executeQuery("SELECT * from " + table);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) {
                    System.out.print("| ");
                }
                String columnValue = resultSet.getString(i);
                System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }
        return table;
    }


    
    public static Guest fetchGuestByCode(String accessCode) {
        Guest preparedReturn = null;
        try {
            DatabaseManager.openConnection();
            String sqlString = "SELECT * FROM " + DatabaseManager.TABLE_NAME_FOR_GUEST
                    + " WHERE guest_access_code = ?";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            psmt.setString(1, accessCode);
            ResultSet rs = psmt.executeQuery();
            
            while (rs.next()){
                preparedReturn = new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            
        }
        catch (SQLException e) {
        }
        return preparedReturn;
    }
    
    public static Admin fetchAdminByUser(String username, String password){
        Admin preparedReturn = null;
        DatabaseManager.openConnection();
        try{
            String sqlString = "SELECT * FROM " + DatabaseManager.TABLE_NAME_FOR_ADMIN
                    + " WHERE admin_username = ? AND admin_password = ?";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            psmt.setString(1, username);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();
            
            while (rs.next()){
                preparedReturn = new Admin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedReturn;
    }
}
