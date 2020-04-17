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
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_EditGuestController {
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private TextField number;
    @FXML
    private TextField email;
    @FXML
    private TextField diet;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private AnchorPane guestsPane;
    
    private int guestId;
    private String guestName;
    
    @FXML
    public void btnUpdateGuestWasClicked(ActionEvent event){
          
        
        String FirstName = fname.getText();
        String LastName = lname.getText();
        String Phone = number.getText();
        String Email = email.getText();
        String Male = rb1.getText();
        String Female = rb2.getText();
        String Diet = diet.getText();
       
      
        
        try{
             Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
             String query = "update guest set guest_fname = ?, guest_lname = ?, guest_email = ?, guest_phone = ?, diet_require = ?, guest_gender = ? where guest_id ='"+guestId+"'"; 
             PreparedStatement psmt = conn.prepareStatement(query);
             
             psmt.setString(1, FirstName);
             psmt.setString(2, LastName);
             psmt.setString(3, Email); 
             psmt.setString(4, Phone);
             psmt.setString(5, Diet);
             
            if (rb1.isSelected()) {
                psmt.setString(6, Male);
            } else if (rb2.isSelected()) {
                psmt.setString(6, Female);
            }

             psmt.execute();
             psmt.close();
             conn.close();
            String header = "Update Success!";
            String content = "Guest was successfully edited!";
            Alertbox.AlertInfo(header, content);
             System.out.println("data updated successfully");
             
        } catch(Exception e){
            String header = "Update Unsuccessful";
            String content = "Please fill out all contents of 'edit guest'";
            Alertbox.AlertError(header, content);
            System.out.println("data not inserted");
            e.printStackTrace();
        } 
    
     
        
    } 
     public void loadGuestData(int guestId) throws SQLException {
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM guest where guest_id =  " + guestId);


        while (rs.next()) {
       
            String FirstName = rs.getString("guest_fname");
            String LastName = rs.getString("guest_lname");
            String Email = rs.getString("guest_email");
            String Phone = rs.getString("guest_phone");
            String Diet = rs.getString("diet_require");
            String Gender = rs.getString("guest_gender");
          
            
            fname.setText(FirstName);
            lname.setText(LastName);
            email.setText(Email);
            number.setText(Phone);
            diet.setText(Diet);
            
        
            if (Gender.equals(rb1.getText())){
                rb1.setSelected(true);
            }
            
            else if (Gender.equals(rb2.getText())){
                rb2.setSelected(true);
            }
          
        }

    } 
     public void getGuestId(int id){
        this.guestId = id;
    }
     
     public void getGuestName(String name){
         this.guestName = name;
     }
    
     @FXML
     private void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_ViewGuestProfileController controller = loader.getController();
            controller.loadGuestData(guestId);
            controller.passGuestName(guestName);
            controller.getGuestId(guestId);
            guestsPane.getChildren().setAll(pane);
    }
     @FXML
     private void btnViewGuestClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_ViewGuestProfileController controller = loader.getController();
            controller.loadGuestData(guestId);
            controller.passGuestName(guestName);
            controller.getGuestId(guestId);
            guestsPane.getChildren().setAll(pane);
    }
     @FXML
    public void btnGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestDashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
       
        guestsPane.getChildren().setAll(pane);
        
    }
     
     
}
        
