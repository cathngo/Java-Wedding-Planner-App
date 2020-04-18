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
    private static Connection sharedConnection;

    private static boolean openConnection() {
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
                    + "event_instructions TEXT, "
                    + "event_invitation BLOB, "
                    + "event_runsheet BLOB)";

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
                    + "guest_fname TEXT, "
                    + "guest_lname TEXT, "
                    + "guest_email TEXT, "
                    + "guest_phone TEXT, "
                    + "diet_require TEXT, "
                    + "guest_access_code TEXT, "
                    + "guest_gender TEXT) ";

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
                    + "invitation_id INTEGER UNIQUE, "
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
                    + " (guest_fname, guest_lname, guest_email, guest_phone, guest_access_code, diet_require, guest_gender)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] guestFname = {"Peter", "William", "Kelly", "Alex", "Jane", "Vanessa", "Phil"};
            String[] guestLname = {"Kim", "Wong", "Lu", "O'Connor", "Nguyen", "Chor", "So"};
            String[] guestEmail = {"Peter21@gmail.com ", "Will2093@gmail.com", "Kelly234@gmail.com", "Alex002@hotmail.com", "Jane_12@gmail.com", "Van23@gmail.com", "Phil.G@gmail.com"};
            String[] guestPhone = {"0409378231", "0439827365", "0427836472", "0426837645", "0498378265", "0498786375", "0401109387"};
            String[] guestCode = {"PeterKim2341", "WilliamWong9837", "KellyLu0193", "AlexOConnor9283", "JaneNguyen2938", "VanessaChor0283", "PhilSo2983"};
            String[] dietRequire = {"N/A", "Peanut Allergy", "Vegan", "N/A", "Vegetarian", "N/A", "N/A"};
            String[] guestGender = {"Male", "Male", "Female", "Male", "Female", "Female", "Male"};
            for (int i = 0; i < guestFname.length; i++) {
                psmt.setString(1, guestFname[i]);
                psmt.setString(2, guestLname[i]);
                psmt.setString(3, guestEmail[i]);
                psmt.setString(4, guestPhone[i]);
                psmt.setString(5, guestCode[i]);
                psmt.setString(6, dietRequire[i]);
                psmt.setString(7, guestGender[i]);
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
                    + " (decision, date_time, invitation_id)"
                    + " VALUES (?,?,?)";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            String[] decision = {"Yes", "Yes", "No", "No", null, "Yes", null};
            String[] date_time = {"12/3/2020", "14/2/2020", "23/5/2020", "2/4/2020", "16/6/2020", "15/8/2020", "17/9/2020"};

            int[] invitation_id = {1, 2, 3, 4, 5, 6, 7};

            for (int i = 0; i < invitation_id.length; i++) {
                psmt.setString(1, decision[i]);
                psmt.setString(2, date_time[i]);

                psmt.setInt(3, invitation_id[i]);

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
      DatabaseManager.openConnection();
        Statement st = sharedConnection.createStatement();
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
        DatabaseManager.closeConnection();
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

            while (rs.next()) {
                preparedReturn = new Guest(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
            }

        } catch (SQLException e) {
        }
        return preparedReturn;
    }

    public static Admin fetchAdminByUser(String username, String password) {
        Admin preparedReturn = null;
        DatabaseManager.openConnection();
        try {
            String sqlString = "SELECT * FROM " + DatabaseManager.TABLE_NAME_FOR_ADMIN
                    + " WHERE admin_username = ? AND admin_password = ?";
            PreparedStatement psmt = sharedConnection.prepareStatement(sqlString);
            psmt.setString(1, username);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                preparedReturn = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedReturn;
    }

    //A_ViewEventsController + A_GuestsInviteEventController + G_ViewEventController uses this to set Events table
    public static ObservableList<Event> getEvents() {
        DatabaseManager.openConnection();
        ArrayList<Event> eventList = new ArrayList<>();
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = sharedConnection.createStatement().executeQuery("select * from " + DatabaseManager.TABLE_NAME_FOR_EVENT);
            while (rs.next()) {
                Event events = new Event(rs.getInt("event_id"), rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("event_start_time"), rs.getString("event_end_time"), rs.getString("event_address"), rs.getString("event_description"), rs.getString("event_instructions"));
                eventList.add(events);
            }
            DatabaseManager.closeConnection();
            // rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(eventList);
    }

    //A_ViewEventController + A_EditEventController  uses this to pre-set fields of a selected event
    public static Event getEventsByEventId(int id) {
        DatabaseManager.openConnection();
        Event events = null;
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = sharedConnection.createStatement().executeQuery("select * from " + DatabaseManager.TABLE_NAME_FOR_EVENT + " where event_id = " + id);
            while (rs.next()) {
                events = new Event(rs.getInt("event_id"), rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("event_start_time"), rs.getString("event_end_time"), rs.getString("event_address"), rs.getString("event_description"), rs.getString("event_instructions"));

            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return events;
        }
    }

    //A_VIewEventGuestList controller uses this to set up pie chart
    public static ArrayList getRSVPCount(int id) throws SQLException {

        ArrayList<Integer> rsvp = new ArrayList<Integer>();

        try {
           DatabaseManager.openConnection();
            ResultSet rs1 = sharedConnection.createStatement().executeQuery("SELECT COUNT(rsvp_id) "
                    + "FROM rsvp r "
                    + "JOIN invitation i ON i.invitation_id = r.invitation_id "
                    + "JOIN event e ON e.event_id = i.event_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE r.decision = 'Yes' "
                    + "AND e.event_id ='" + id + "'");

            ResultSet rs2 = sharedConnection.createStatement().executeQuery("SELECT COUNT(rsvp_id) "
                    + "FROM rsvp r "
                    + "JOIN invitation i ON i.invitation_id = r.invitation_id "
                    + "JOIN event e ON e.event_id = i.event_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE r.decision = 'No' "
                    + "AND e.event_id ='" + id + "'");

            ResultSet rs3 = sharedConnection.createStatement().executeQuery("SELECT COUNT(g.guest_id) "
                    + "FROM guest g "
                    + "JOIN invitation i ON g.guest_id = i.guest_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN event e ON e.event_id = i.event_id "
                    + "WHERE e.event_id ='" + id + "'"
                    + "AND r.decision IS NULL");

            rsvp.add(rs1.getInt("COUNT(rsvp_id)"));
            rsvp.add(rs2.getInt("COUNT(rsvp_id)"));
            rsvp.add(rs3.getInt("COUNT(g.guest_id)"));

            DatabaseManager.closeConnection();
            rs1.close();
            rs2.close();
            rs3.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rsvp;
        }
    }

    public static ObservableList<RSVPGuestWrapper> getRsvpGetGuest(int id) {
        DatabaseManager.openConnection();
        ArrayList<RSVPGuestWrapper> rsvpList = new ArrayList<>();
        try {
            DatabaseManager.openConnection();
            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT g.guest_fname, g.guest_lname, r.decision "
                    + "FROM guest g "
                    + "JOIN invitation i ON g.guest_id = i.guest_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN event e ON e.event_id = i.event_id "
                    + "WHERE e.event_id ='" + id + "'");

            while (rs.next()) {
                rsvpList.add(new RSVPGuestWrapper(rs.getString("guest_fname"),
                        rs.getString("guest_lname"), rs.getString("decision")));

            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(rsvpList);
    }

    //A_ViewEventInviteGuestController + A_ViewGuestDashboard Controller uses this to set up guest table
    public static ObservableList<Guest> getGuests() {
        DatabaseManager.openConnection();
        ArrayList<Guest> guestList = new ArrayList<>();
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = sharedConnection.createStatement().executeQuery("select * from " + DatabaseManager.TABLE_NAME_FOR_GUEST);
            while (rs.next()) {
                Guest guests = new Guest(rs.getInt("guest_id"), rs.getString("guest_fname"),
                        rs.getString("guest_lname"), rs.getString("guest_email"), rs.getString("guest_phone"), rs.getString("diet_require"), rs.getString("guest_access_code"), rs.getString("guest_gender"));
                guestList.add(guests);
            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(guestList);
    }

    //A_ViewEventInviteGuestController uses this to invite guests
    public static void inviteGuest(ArrayList guestId, int eventId) throws SQLException {
        ArrayList<Integer> guest_id = guestId;

        for (int i = 0; i < guest_id.size(); i++) {
            DatabaseManager.openConnection();
            int rs = sharedConnection.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '" + eventId + "', '" + guest_id.get(i) + "','" + LoginController.adminUser.getAdmin_id() + "' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='" + eventId + "' AND guest_id ='" + guestId.get(i) + "')");

            DatabaseManager.closeConnection();

        }
    }

    public static void createEvent(String name, String address, String desc, String date, String sTime, String eTime, String inst) {
        try {
            DatabaseManager.openConnection();
            String query = "INSERT INTO event"
                    + " (event_name, event_address, event_description, event_date, event_start_time, event_end_time, event_instructions)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psmt = sharedConnection.prepareStatement(query);

            psmt.setString(1, name);
            psmt.setString(2, address);
            psmt.setString(3, desc);
            psmt.setString(4, date);
            psmt.setString(5, sTime);
            psmt.setString(6, eTime);
            psmt.setString(7, inst);

            psmt.executeUpdate();
            psmt.close();

            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //A_ViewEventINviteNewGuestController uses this to create guest code
    public static String generateGuestCode(String fname, String lname) {
        String codeName = fname + lname;
        String actualName = codeName.replaceAll("[^a-zA-Z]", "");
        int digits = (int) Math.floor(1000 + Math.random() * 9000);
        String code = actualName + digits;
        String guestCode = code;
        return guestCode;
    }

    //A_ViewEventInviteNewGuestController + A_CreateGuestController uses this to create new guest
    public static void createGuest(String fname, String lname, String phone, String email, String diet, String guestCode, String gender) throws SQLException {
        try {
            DatabaseManager.openConnection();
            String query = "INSERT INTO guest"
                    + " (guest_fname, guest_lname, guest_phone, guest_email, diet_require, guest_access_code, guest_gender)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psmt = sharedConnection.prepareStatement(query);

            psmt.setString(1, fname);
            psmt.setString(2, lname);
            psmt.setString(3, phone);
            psmt.setString(4, email);
            psmt.setString(5, diet);
            psmt.setString(6, guestCode);
            psmt.setString(7, gender);

            psmt.executeUpdate();
            psmt.close();

            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //A_ViewEventInviteNewGuestController uses this to fetch guest_id

    public static int getGuestIdByCode(String guestCode) throws SQLException {
        int guestId = 0;
           try{
               DatabaseManager.openConnection();
           
          
            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT guest_id FROM guest WHERE guest_access_code = '" + guestCode + "'");
            while(rs.next()){
            guestId = rs.getInt("guest_id");
            
             
            }
           }catch(Exception e){
               e.printStackTrace();
              
           }finally{
           return guestId;
           }
       
    }
    
   
        
       
    

    //A_EditEventController uses this to edit an event
    public static void editEvent(int eventId, String name, String address, String desc, String date, String sTime, String eTime, String inst) throws SQLException {
        DatabaseManager.openConnection();
        String query = "update event set event_name = ?, event_address = ?, event_description = ?, event_date = ?, event_start_time = ?, event_end_time = ?, event_instructions = ? where event_id ='" + eventId + "'";
        PreparedStatement psmt = sharedConnection.prepareStatement(query);

        psmt.setString(1, name);
        psmt.setString(2, address);
        psmt.setString(3, desc);
        psmt.setString(4, date);
        psmt.setString(5, sTime);
        psmt.setString(6, eTime);
        psmt.setString(7, inst);

        psmt.execute();
        psmt.close();
        DatabaseManager.closeConnection();
    }
//A_ViewGuestProfileController + A_EditGuestController uses this to pre-set fields of selected guest

    public static Guest getGuestByGuestId(int id) {
        DatabaseManager.openConnection();
        Guest guests = null;
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = sharedConnection.createStatement().executeQuery("select * from " + DatabaseManager.TABLE_NAME_FOR_GUEST + " where guest_id = " + id);
            while (rs.next()) {
                guests = new Guest(rs.getInt("guest_id"), rs.getString("guest_fname"),
                        rs.getString("guest_lname"), rs.getString("guest_email"), rs.getString("guest_phone"), rs.getString("diet_require"), rs.getString("guest_access_code"), rs.getString("guest_gender"));

            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return guests;
        }
    }

    //A_ViewGuestProfile uses this
    public static ObservableList<RSVPEventWrapper> getRsvpGetEvent(int id) {
        DatabaseManager.openConnection();
        ArrayList<RSVPEventWrapper> rsvpList = new ArrayList<>();
        try {

            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE g.guest_id = '" + id + "'");

            while (rs.next()) {
                rsvpList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(rsvpList);
    }

    //A_GuestsInviteEventController uses this to invite guest to an event
    public static void inviteToEvent(int eventId, int guestId) throws SQLException {
        try {
            DatabaseManager.openConnection();
            int rs = sharedConnection.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '" + eventId + "', '" + guestId + "','" + LoginController.adminUser.getAdmin_id() + "' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='" + eventId + "' AND guest_id ='" + guestId + "')");
            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editGuest(int guestId, String fname, String lname, String email, String phone, String diet, String gender) throws SQLException {

        DatabaseManager.openConnection();
        String query = "update guest set guest_fname = ?, guest_lname = ?, guest_email = ?, guest_phone = ?, diet_require = ?, guest_gender = ? where guest_id ='" + guestId + "'";
        PreparedStatement psmt = sharedConnection.prepareStatement(query);

        psmt.setString(1, fname);
        psmt.setString(2, lname);
        psmt.setString(3, email);
        psmt.setString(4, phone);
        psmt.setString(5, gender);
        psmt.setString(6, diet);

        psmt.execute();
        psmt.close();
        DatabaseManager.closeConnection();
    }

    public static int getGuestIdByGuestNameGuestDate(String name, String date) throws SQLException {
        DatabaseManager.openConnection();
        ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT event_id "
                + "FROM event "
                + "WHERE event_name ='" + name + "'"
                + "AND event_date='" + date + "'");

        int eventId = rs.getInt(1);
        rs.close();
        DatabaseManager.closeConnection();

        return eventId;
    }

    //G_ViewRSVPController + G_EditRSVP  uses this to pre-set rsvp for a selected guest
    public static ArrayList getRsvpByGuestIdEventId(int eventId, int guestId) throws SQLException {
        ArrayList<String> rsvp = new ArrayList<String>();
        try {
            DatabaseManager.openConnection();
            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT g.diet_require, r.decision, e.event_name, e.event_date, e.event_start_time, e.event_end_time, e.event_address "
                    + "FROM guest g "
                    + "JOIN invitation i ON g.guest_id = i.guest_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN event e ON e.event_id = i.event_id "
                    + "WHERE e.event_id ='" + eventId + "'"
                    + "AND g.guest_id ='" + guestId + "'");

            rsvp.add(rs.getString("event_name"));
            rsvp.add(rs.getString("event_date"));
            rsvp.add(rs.getString("event_start_time"));
            rsvp.add(rs.getString("event_end_time"));
            rsvp.add(rs.getString("event_address"));
            rsvp.add(rs.getString("diet_require"));
            rsvp.add(rs.getString("decision"));
            DatabaseManager.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return rsvp;
        }

    }

    //G_SubmitRSVPController uses this
    public static int getInvitationId(int eventId, int guestId) throws SQLException {
        DatabaseManager.openConnection();
        ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT invitation_id "
                + "FROM invitation "
                + "WHERE event_id ='" + eventId + "'"
                + "AND guest_id ='" + guestId + "'");
        int invitationId = rs.getInt(1);

        rs.close();
        DatabaseManager.closeConnection();
        return invitationId;
    }
    //G_SubmitRSVPController uses this

    public static void submitRSVP(int invitationId, String decision) throws SQLException {
        DatabaseManager.openConnection();
        String RsvpQuery = "INSERT OR IGNORE INTO rsvp"
                + " (decision, invitation_id)"
                + " VALUES (?, ?)";

        PreparedStatement rsvpPsmt = sharedConnection.prepareStatement(RsvpQuery);

        rsvpPsmt.setString(1, decision);

        rsvpPsmt.setInt(2, invitationId);
        rsvpPsmt.execute();
        rsvpPsmt.close();
        DatabaseManager.closeConnection();
    }
    //G_SubmitRSVPController + G_EditRSVP uses this

    public static void updateGuestDiet(int guestId, String diet) throws SQLException {
        DatabaseManager.openConnection();
        String guestQuery = "UPDATE guest SET diet_require = ? WHERE guest_id ='" + guestId + "'";

        PreparedStatement guestPsmt = sharedConnection.prepareStatement(guestQuery);
        guestPsmt.setString(1, diet);

        guestPsmt.execute();
        guestPsmt.close();
        DatabaseManager.closeConnection();
    }
//G_EditRSVP uses this
    public static void updateRSVP(int invitationId, String decision) throws SQLException {
        DatabaseManager.openConnection();

        String RsvpQuery = "UPDATE rsvp SET decision = ? WHERE invitation_id ='" + invitationId + "'";
        PreparedStatement rsvpPsmt = sharedConnection.prepareStatement(RsvpQuery);
        rsvpPsmt.setString(1, decision);
        rsvpPsmt.execute();
        rsvpPsmt.close();
        DatabaseManager.closeConnection();
    }
    
    //A_ViewAllRunsheetsController uses this for runsheet table
    public static ObservableList<Event> getEventsByRunsheet() {
        DatabaseManager.openConnection();
        ArrayList<Event> eventList = new ArrayList<>();
        try {
           
             ResultSet rs = sharedConnection.createStatement().executeQuery("select * from event WHERE event_runsheet IS NOT NULL");
            while (rs.next()) {
                Event events = new Event(rs.getInt("event_id"), rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("event_start_time"), rs.getString("event_end_time"), rs.getString("event_address"), rs.getString("event_description"), rs.getString("event_instructions"));
                eventList.add(events);
            }
            DatabaseManager.closeConnection();
            // rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(eventList);
    }
    
    
    public static ObservableList<Event> getEventsByInvitation() {
        DatabaseManager.openConnection();
        ArrayList<Event> eventList = new ArrayList<>();
        try {
           
             ResultSet rs = sharedConnection.createStatement().executeQuery("select * from event WHERE event_invitation IS NOT NULL");
            while (rs.next()) {
                Event events = new Event(rs.getInt("event_id"), rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("event_start_time"), rs.getString("event_end_time"), rs.getString("event_address"), rs.getString("event_description"), rs.getString("event_instructions"));
                eventList.add(events);
            }
            DatabaseManager.closeConnection();
            // rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(eventList);
    }
    
     public static ObservableList<RSVPEventWrapper> getNullRsvpGetEvent(int id) {
        DatabaseManager.openConnection();
        ArrayList<RSVPEventWrapper> rsvpList = new ArrayList<>();
        try {

            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE r.decision IS NULL "
                    + "AND g.guest_id ='" + id + "'");

            while (rs.next()) {
                rsvpList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(rsvpList);
    }
     
     //G_Dashboard controller uses this to view responded events
     public static ObservableList<RSVPEventWrapper> getNotNullRsvpGetEvent(int id) {
        DatabaseManager.openConnection();
        ArrayList<RSVPEventWrapper> rsvpList = new ArrayList<>();
        try {

            ResultSet rs = sharedConnection.createStatement().executeQuery("SELECT e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE g.guest_id = '" + id + "'");

            while (rs.next()) {
                rsvpList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
            }
            DatabaseManager.closeConnection();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(rsvpList);
    }
}
        

