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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author honesyuu
 */
public class G_MenuController implements Initializable {
 
    @FXML
    private BorderPane gbp;
    
    @FXML
    private AnchorPane gap;
    
    PageSwitcher pageSwitcher = new PageSwitcher();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
    
    @FXML
    private void home(MouseEvent event) {
        gbp.setCenter(gap);
        
    }
    
    @FXML
    private void openGuestDashboard(MouseEvent event) {
        loadPage("G_Dashboard");
        
    }
    
    @FXML
    private void openGuestEvents(MouseEvent event) {
        loadPage("G_ViewEvent");
        
    }
    
    @FXML
    private void openGuestInvitation(MouseEvent event) {
        loadPage("G_ViewAllInvitations");
        
    }
    
    
    @FXML
    private void btn_Abouts(MouseEvent event) {
        loadPage("About");
        
    }
    
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(G_MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    gbp.setCenter(root);
}
    @FXML
    private void btnLogOutWasClicked(ActionEvent event) throws IOException{
        LoginController.adminUser = null;
        LoginController.guestUser = null;
        pageSwitcher.switchPage(event, "Login.fxml");
        
    }

}



