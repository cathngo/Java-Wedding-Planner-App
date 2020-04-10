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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_CreateGuestController {
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
   

    
     @FXML
    public void btnCreateGuestWasClicked() throws SQLException{

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
       String guestCode = actualName + digits;



        
       
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
           
     
        
    
}
    @FXML
    private void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewGuestDashboard.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

}
