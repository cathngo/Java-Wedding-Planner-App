/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author honesyuu
 */
public class A_MenuController implements Initializable {
 
    @FXML
    private BorderPane bp;
    
    @FXML
    private AnchorPane ap;

    @FXML 
    private Button btn_home;
    
    PageSwitcher pageSwitcher = new PageSwitcher();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    
    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
        
    }
    
    @FXML
    private void openDashboard(MouseEvent event) {
        loadPage("A_Dashboard");
        
    }
    
    @FXML
    private void openEvents(MouseEvent event) {
        loadPage("A_ViewAllEvents");
        
    }
    
    @FXML
    private void openGuests(MouseEvent event) {
        loadPage("A_ViewGuestDashboard");
        
    }
    
    @FXML
    private void openInvitation(MouseEvent event) {
        loadPage("A_CreateInvitation");
        
    }
    
    @FXML
    private void openRunsheets(MouseEvent event) {
        loadPage("A_ViewAllRunsheets");
        
    }
    
    @FXML
    private void openAbouts(MouseEvent event) {
        loadPage("About");
        
    }
    
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(A_MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    bp.setCenter(root);
}
    @FXML
    private void btnLogOutWasClicked(ActionEvent event) throws IOException{
        LoginController.adminUser = null;
        LoginController.guestUser = null;
        pageSwitcher.switchPage(event, "Login.fxml");
        
    }
}



