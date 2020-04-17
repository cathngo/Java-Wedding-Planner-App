
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class A_ViewGuestProfileController {

    @FXML
    private Text guestName;
    @FXML
    private Text guestId;
    @FXML
    private Text guestEmail;
    @FXML
    private Text guestAccessCode;
    @FXML
    private Text guestGender;
    @FXML
    private Text guestPhone;
    @FXML
    private AnchorPane guestsPane;
    @FXML
    private TableView<RSVPEventWrapper> event_details_table;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventName;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventDate;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventRsvp;

    ObservableList<RSVPEventWrapper> eventDetailsList = FXCollections.observableArrayList();
    
    private int guest_id;
    private String guest_name;
    

    public void loadGuestData(int id) throws SQLException {
       

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM guest where guest_id =  " + id);

        while (rs.next()) {
            String fname = rs.getString("guest_fname");
            String lname = rs.getString("guest_lname");
           
            String code = rs.getString("guest_access_code");
            String email = rs.getString("guest_email");
            String phone = rs.getString("guest_phone");
            String gender = rs.getString("guest_gender");

            guestName.setText(fname + " " + lname);
            guestId.setText(Integer.toString(id));
            guestEmail.setText(email);
            guestPhone.setText(phone);
            guestGender.setText(gender);
            guestAccessCode.setText(code);

        }
        System.out.println("loadguestData: " +id);
        conn.close();
        rs.close();
    }

    public void getGuestId(int id) throws SQLException {
        this.guest_id = id;
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE g.guest_id = '" + id + "'");

            while (rs.next()) {
                eventDetailsList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
            }
            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

            event_details_table.setItems(eventDetailsList);
            
            conn.close();
            rs.close();
            
            System.out.println("getGuestId" + id);
        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }
        
    }
    public void passGuestName(String name){
        this.guest_name = name;
        System.out.println("pass guest name" + name);
    }

    @FXML
    private void btnInviteGuestWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestInviteEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewGuestInviteEventController controller = loader.getController();
        controller.passGuestName(guest_name);
        controller.getGuestId(guest_id);
       
        guestsPane.getChildren().setAll(pane);
        
        System.out.println("btninviteguest guestid " + guest_id + "guest name" + guest_name);
    }

    @FXML
    private void btnEditGuestWasClicked(ActionEvent event) throws IOException, SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EditGuest.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_EditGuestController controller = loader.getController();
            controller.loadGuestData(guest_id);
            controller.getGuestId(guest_id);
            controller.getGuestName(guest_name);
            guestsPane.getChildren().setAll(pane);
            
             System.out.println("btninviteguest guestid " + guest_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     @FXML
    public void btnGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestDashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
       
        guestsPane.getChildren().setAll(pane);
        
    }
    
    
}
