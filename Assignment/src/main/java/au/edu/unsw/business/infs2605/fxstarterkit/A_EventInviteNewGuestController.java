/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class A_EventInviteNewGuestController {
    
    @FXML
    private AnchorPane eventPane;

    @FXML
    private Text eventName;

    

    @FXML
    private ListView<String> guestListView;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField number;

    @FXML
    private TextField email;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private TextField diet;
    
    private int eventId;
    
    private String guestCode;
    private int guestId;

 

    ObservableList<String> guestList = FXCollections.observableArrayList();
    
    
    

    @FXML
    void btnAddToListWasClicked(ActionEvent event) {
        String FirstName = fname.getText();
        String LastName = lname.getText();
        String Phone = number.getText();
        String Email = email.getText();
        String Dietary = diet.getText();
        String Male = rb1.getText();
        String Female = rb2.getText();
       
        
        //generate guest code for guest
       guestCode = DatabaseManager.generateGuestCode(FirstName, LastName);
       
       
       
       


        try{
             
             
            if (rb1.isSelected()) {
                DatabaseManager.createGuest(FirstName, LastName, Phone, Email, Dietary, guestCode, Male);
            } else if (rb2.isSelected()) {
                 DatabaseManager.createGuest(FirstName, LastName, Phone, Email, Dietary, guestCode, Female);
            }
            
          
             
             System.out.println("data inserted successfully");
           
             
             
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("data not inserted");
            String header = "Unable to add to guest list";
            String content = "Please fill out all contents of 'create new guest'";
            Alertbox.AlertError(header, content);
        }
        
        
        
       guestList.add(FirstName + " " + LastName);
       guestListView.setItems(guestList);
        
        
    }

    @FXML
    public void btnInviteToEventWasClicked(ActionEvent event) throws SQLException {

        try {
           DatabaseManager.inviteNewGuest(guestCode, eventId);
            
            System.out.println("btninvitetoevent guestcode" + guestCode +"guest id" +guestId+ "event Id" + eventId +"adminId" + LoginController.adminUser.getAdmin_id());
            String header = "Invite success!";
            String content = "Guests have been successfully invited to event";
            Alertbox.AlertInfo(header, content);
        } catch (Exception e) {
            String header = "Invite unsuccessful";
            String content = "Please create and add a guest to the guest list first";
            Alertbox.AlertError(header, content);
            e.printStackTrace();
        }

    }
 
     @FXML
    public void btnEventsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void btnInviteGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
     
       FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EventInviteGuest.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_EventInviteGuestController controller = loader.getController();
        controller.passData(eventName.getText());
        controller.getEventId(eventId);
        eventPane.getChildren().setAll(pane);
    }
    public void getEventId(int id){
        this.eventId = id;
        
    }
    
    public void passEventName (String name){
        eventName.setText(name);
    }
    
    
    
    
}
