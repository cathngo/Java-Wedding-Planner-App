# EPlanner, Event Planning Application

## What is this?
As one of Sydney’s largest event-planning companies, we are excited to present our new EPlanner Event Planning Application. Traditionally, we have helped people plan events including weddings, birthday parties, and weddings, and now we are bringing these services onto your own screens. We hope that you take full advantage of EPlanner and that this application is helpful for your future events.

## Getting Started
This codebase will run on both macOS and Windows. Prior to running the application, ensure that you Clean and Build in NetBeans.

## Sample Login Credentials
**Admins:**

Username: *jean.v*

Password: *admin*


Username: *patrick.oc*

Password: *admin*


Username: *blair.wang*

Password: *admin*


**Guests:**

You can login as Guests with any of the Access Code presented in the 'My Guests' page when you login as Admin. Below are some examples:

Access Code: *JeanValjean7093*


Access Code: *PatrickOConnor0944*


Access Code: *BlairWang8203*


Access Code: *TerryMatthews0937*


Access Code: *HenryCooper3186*


Access Code: *PeterOConnell0571*



## Significant Files
1. `AboutUs.fxml` and `AboutUsGuest.fxml` - Contain the given copyright statement and location of multimedia items (MVC "view" files).
2. `AboutUsController.java` and `AboutUsGuestController.java` - MVC "Controller" classes.
3. `Admin.java` - MVC "Model" class.
4. `AdminLoginController.java` - MVC "Controller" class.
5. `AdminLogin.fxml` - MVC "View" file.
6. `AdminDatabase.java`. `EventDatabase.java`, `GuestDatabase.java`, `InvitationDatabase.java`, and `RSVPDatabase.java` - Database files, manage JDBC connection.
7. `App.java` - JavaFX main class.
8. `Event.java` - MVC "Model" class.
9. `CreateEventController.java` - MVC "Controller" class.
10. `CreateEvent.fxml` - MVC "View" file.
11. `EditEventController.java` - MVC "Controller" class.
12. `EditEvent.fxml` - MVC "View" file.
13. `EventPageController.java` - MVC "Controller" class.
14. `EventPage.fxml` - MVC "View" file.
15. `Guest.java` - MVC "Model" class.
16. `GuestLoginController.java` - MVC "Controller" class.
17. `GuestLogin.fxml` - MVC "View" file.
18. `CreateGuestController.java` - MVC "Controller" class.
19. `CreateGuest.fxml` - MVC "View" file.
20. `EditGuestController.java` - MVC "Controller" class.
21. `EditGuest.fxml` - MVC "View" file.
22. `GuestListPageController.java` - MVC "Controller" class.
23. `GuestListPage.fxml` - MVC "View" file.
24. `Invitation.java` - MVC "Model" class.
25. `InvitationPageController.java` - MVC "Controller" class.
26. `InvitationPage.fxml` - MVC "View" file.
27. `LoginPageController.java` - MVC "Controller" class.
28. `LoginPage.fxml` - MVC "View" file.
29. `PageSwitchHelper.java` - Helper class, to manage page switching.
30. `RSVP.java` - MVC "Model" class.
25. `User.java` - MVC "Controller" class.
26. `RespondtoEvent.fxml` - MVC "View" file.
27. `RespondtoEvent.java` - MVC "Model" class.

## Significant Parts of the database (using `GuestDatabase.java` as an example)
1. `openConnection()` and `closeConnection()` act as convenient wrappers for the JDBC open/close cycle.
2. `createGuestTable()` is required by the system for the initial set up of the table. 
3. `setupGuestDatabaseOnFirstRun()` will create an empty guest table if it does not already exist.
4. `addData()` enables the administrator to create new guests on the application and save their information in the database.
5. `editData()` enables the administrator to update guest details, simultaneously updating the guest database.
6. `deleteData()` enables an administrator to remove a guest's records from the database.

## How to use EPlanner
1. Login as Admin or Guest with the above Login Credentials.
2. When logging in as Admin, there is a side pane menu with 4 main pages - Events, Guests, My Account and About Us. 
3. In the 'Events' page, admins can create an event by clicking on the '+' sign at the top right of the page. This may appear as an  ellipsis(...). They can also edit and delete events by clicking on the button on the buttom of the page.
4. Same goes for the 'Guests' page, which involves creating, deleting and editing guests.
5. The 'About Us' page is a copyright statement with a list of third-parties items included in our project. 
6. There is also a 'Log Out' button at the bottom of the side pane menu that brings you back to the first login page. 
7. When logging in as Guest, there is also a side pane menu with 3 main pages - Invitations, My Account and About Us. 
8. In the 'Invitations' page, guests can see the events. 

## Acknowledgements
- These instructions were written by students at UNSW Business School for INFS2605.
- The starter kit codebase is adapted from a previous project, IndiefyLoginScreenScaffold, and Blair Wang's Planet Demo, available at https://github.com/blairw/javafx-planets-dbdemo
- All icons are taken from https://www.flaticon.com/
