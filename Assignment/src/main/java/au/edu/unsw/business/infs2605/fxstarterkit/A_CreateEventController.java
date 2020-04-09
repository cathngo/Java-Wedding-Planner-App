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
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_CreateEventController {
    @FXML
    TextField eventId;
    @FXML
    TextField eventName;
    @FXML
    TextField eventAddress;
    @FXML
    TextField eventDescription;
    @FXML
    TextField eventDate;
    @FXML
    TextField startTime;
    @FXML
    TextField endTime;
    @FXML
    TextField instructions;
    @FXML
    private AnchorPane eventPane;

    
     @FXML
    public void btnCreateEventWasClicked() throws SQLException{

        String Name = eventName.getText();

        String Address = eventAddress.getText();
        String Description = eventDescription.getText();
        String Date = eventDate.getText();
        String sTime = startTime.getText();
        String eTime = endTime.getText();
        String eInstructions = instructions.getText();
    
        
       
        try{
             Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
             String query = "INSERT INTO event" 
                    + " (event_name, event_address, event_description, event_date, event_start_time, event_end_time, event_instructions)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
             PreparedStatement psmt = conn.prepareStatement(query);
             
             
             psmt.setString(1, Name);
     
             psmt.setString(2, Address);
             psmt.setString(3, Description);
             psmt.setString(4, Date); 
             psmt.setString(5, sTime);
             psmt.setString(6, eTime); 
             psmt.setString(7, eInstructions); 
             
             psmt.executeUpdate();
             psmt.close();
       
             conn.close();
             System.out.println("data inserted successfully");
             
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("data not inserted");
           
        } 
    
}
    @FXML
    private void btnInviteGuestsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewEventInviteGuest.fxml"));
        eventPane.getChildren().setAll(pane);
    }
}
