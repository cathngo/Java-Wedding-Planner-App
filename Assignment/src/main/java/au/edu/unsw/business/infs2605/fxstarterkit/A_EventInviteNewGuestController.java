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
       
       String codeName = FirstName + LastName;
       String actualName = codeName.replaceAll("[^a-zA-Z]", "");
       int digits = (int)Math.floor(1000 + Math.random() * 9000);
       String code = actualName + digits;
       guestCode = code;
       
       
       


        try{
             Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
             String query = "INSERT INTO guest" 
                    + " (guest_fname, guest_lname, guest_phone, guest_email, diet_require, guest_access_code, guest_gender)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        
             PreparedStatement psmt = conn.prepareStatement(query);
             
             
             psmt.setString(1, FirstName);
             psmt.setString(2, LastName);
             psmt.setString(3, Phone);
             psmt.setString(4, Email); 
             psmt.setString(5, Dietary);
             psmt.setString(6, guestCode); 
             
            if (rb1.isSelected()) {
                psmt.setString(7, Male);
            } else if (rb2.isSelected()) {
                psmt.setString(7, Female);
            }
            
          
             psmt.executeUpdate();
             psmt.close();
            
       
             conn.close();
             System.out.println("data inserted successfully");
           
             
             
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("data not inserted");
        }
        
       guestList.add(FirstName + " " + LastName);
       guestListView.setItems(guestList);
     
        
    }

    @FXML
    public void btnInviteToEventWasClicked(ActionEvent event) throws SQLException {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT guest_id FROM guest WHERE guest_access_code = '" + guestCode + "'");

            guestId = Integer.parseInt(resultSet.getString(1));
            int rs = conn.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '" + eventId + "', '" + guestId + "','" + LoginController.adminUser.getAdmin_id() + "' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='" + eventId + "' AND guest_id ='" + guestId + "')");
            resultSet.close();

            conn.close();

        } catch (Exception e) {
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
