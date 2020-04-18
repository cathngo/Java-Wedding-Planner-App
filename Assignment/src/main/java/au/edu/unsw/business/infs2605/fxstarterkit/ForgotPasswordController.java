/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

/**
 *
 * @author Mimi
 */
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class ForgotPasswordController {
    
    PageSwitcher pageSwitcher = new PageSwitcher();
    
    @FXML
    private Button btnSendEmail;
    
    @FXML 
    private Button btnBackToLogin;
    
    @FXML
    public void initialize(){

    }
    
    @FXML
    private void btnSendEmailWasClicked(ActionEvent event){
        btnSendEmail.setStyle("-fx-background-color: #8FDCA9");
        btnSendEmail.setText("Email sent!");
    }
    
    @FXML
    private void btnBackToLoginWasClicked(ActionEvent event) throws Exception{
        pageSwitcher.switchPage(event, "Login.fxml");
    }
    
    
}
