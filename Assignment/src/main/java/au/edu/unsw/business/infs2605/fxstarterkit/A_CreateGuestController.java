/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
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
    
    private String guestCode;
   

    
    @FXML
    public void btnCreateGuestWasClicked() throws SQLException{

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
            //alertbox error if fields are empty
             if(fname.getText().isEmpty()||lname.getText().isEmpty()||number.getText().isEmpty()||email.getText().isEmpty()){
            String header = "Unable to create guest";
            String content = "Please fill out all contents of 'create guest'";
            Alertbox.AlertError(header, content);
            
        } else{
            if (rb1.isSelected()) {
                DatabaseManager.createGuest(FirstName, LastName, Phone, Email, Dietary, guestCode, Male);
            } else if (rb2.isSelected()) {
                 DatabaseManager.createGuest(FirstName, LastName, Phone, Email, Dietary, guestCode, Female);
            }
            //alertbox if guest created successfully
            String header = "Guest created!";
            String content = "Guest was successfully created!";
            Alertbox.AlertInfo(header, content);
            System.out.println("data inserted successfully");
             }  
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("data not inserted");
        }  
}
    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewGuestDashboard.fxml"));
        guestsPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void btnGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestDashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        guestsPane.getChildren().setAll(pane);
    }
}
