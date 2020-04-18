/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author mimi
 */
public class LoginController {
    public static Guest guestUser;
    public static Admin adminUser;
    @FXML 
    private TextField txtUsername;
    
    @FXML
    private PasswordField pwfPassword;
    
    @FXML
    private PasswordField pwfAccessCode;
    
    @FXML
    private Label lblIncorrectCredentials;
    
    @FXML
    private Label lblIncorrectAccessCode;
    
    @FXML
    private Button btnLogin;
    
    
    PageSwitcher pageSwitcher = new PageSwitcher();
    DatabaseManager database = new DatabaseManager();

        
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    public void initialize(){
        lblIncorrectCredentials.setVisible(false);
        lblIncorrectAccessCode.setVisible(false);
    }
    
    @FXML
    private void btnHovered(MouseEvent event) throws Exception {
        btnLogin.setStyle("-fx-background-color: #8FDCA9");
    }
    
        @FXML
    private void btnExit(MouseEvent event) throws Exception {
        btnLogin.setStyle("-fx-background-color: #232323");
    }

    
    @FXML
    private void btnLoginWasClicked(ActionEvent event) throws Exception {
        String username = txtUsername.getText();
        String password = pwfPassword.getText();
        String accessCode = pwfAccessCode.getText();
        pwfAccessCode.setStyle("-fx-border-color:TRANSPARENT");
        txtUsername.setStyle("-fx-border-color:TRANSPARENT");
        pwfPassword.setStyle("-fx-border-color:TRANSPARENT");
        lblIncorrectCredentials.setVisible(false);
        lblIncorrectAccessCode.setVisible(false);
        
        //if user enters guest access code
        if(!accessCode.isEmpty() && (username.isEmpty() && password.isEmpty())){

            if (DatabaseManager.fetchGuestByCode(accessCode)== null){
                //line below is for testing
                lblIncorrectAccessCode.setText("Incorrect access code!");
                lblIncorrectAccessCode.setVisible(true);
                pwfAccessCode.setStyle("-fx-border-color:RED");
                          
            }
            else {
                //returns the guest object
                guestUser = DatabaseManager.fetchGuestByCode(accessCode);
                System.out.println(guestUser.getGuest_id());
                pageSwitcher.switchPage(event, "G_Menu.fxml");
            }
        }       
         //if user enters admin details
        else if (accessCode.isEmpty() && (!username.isEmpty() && !password.isEmpty())){
            
            if(DatabaseManager.fetchAdminByUser(username, password)==null){
                lblIncorrectCredentials.setVisible(true);
                txtUsername.setStyle("-fx-border-color:RED");
                pwfPassword.setStyle("-fx-border-color:RED");
            }
            else {
                //returns admin object
                adminUser = DatabaseManager.fetchAdminByUser(username, password);
                pageSwitcher.switchPage(event, "A_Menu.fxml");

            }
        }
        else{
            pwfAccessCode.setStyle("-fx-border-color:RED");
            txtUsername.setStyle("-fx-border-color:RED");
            pwfPassword.setStyle("-fx-border-color:RED");
            lblIncorrectAccessCode.setText("Error: enter credentials");
            lblIncorrectAccessCode.setVisible(true);
        }
    }
    
    @FXML
    private void btnForgotPasswordWasClicked(ActionEvent event) throws Exception{
        pageSwitcher.switchPage(event, "ForgotPassword.fxml");
    }
}
