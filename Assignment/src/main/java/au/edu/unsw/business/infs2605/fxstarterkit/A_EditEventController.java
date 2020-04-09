/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_EditEventController {
    
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
    
    private int eventId;
 
    
    
    @FXML
    public void btnUpdateEventWasClicked(ActionEvent event){
          
        
        String Name = eventName.getText();
        String Description = eventDescription.getText();
        String Address = eventAddress.getText();
        String Date = eventDate.getText();
        String sTime = startTime.getText();
        String eTime = endTime.getText();
        String eInstructions = instructions.getText();
        
        
                   
   
        
        try{
             Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
             String query = "update event set event_name = ?, event_address = ?, event_description = ?, event_date = ?, event_start_time = ?, event_end_time = ?, event_instructions = ? where event_id ='"+eventId+"'"; 
             PreparedStatement psmt = conn.prepareStatement(query);
             
             psmt.setString(1, Name);
             psmt.setString(2, Address);
             psmt.setString(3, Description); 
             psmt.setString(4, Date);
             psmt.setString(5, sTime);
             psmt.setString(6, eTime); 
             psmt.setString(7, eInstructions);
             
             
             psmt.execute();
             psmt.close();
             conn.close();
             
             System.out.println("data updated successfully");
             
        } catch(Exception e){
            System.out.println("data not inserted");
            e.printStackTrace();
        } 
    }
    public void passData(int eventId) throws SQLException {
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event where event_id =  " + eventId);


        while (rs.next()) {
       
            String name = rs.getString("event_name");
            String address = rs.getString("event_address");
            String desc = rs.getString("event_description");
            String date = rs.getString("event_date");
            String sTime = rs.getString("event_start_time");
            String eTime = rs.getString("event_end_time");
            String instruct = rs.getString("event_instructions");
            
            eventName.setText(name);
            eventAddress.setText(address);
            eventDescription.setText(desc);
            eventDate.setText(date);
            startTime.setText(sTime);
            endTime.setText(eTime);
            instructions.setText(instruct);
          
        }

    } 
    public void getEventId(int id){
        this.eventId = id;
    }
    
}
