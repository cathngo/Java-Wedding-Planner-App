
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author cathy
 */
public class A_ViewGuestProfileController implements Initializable{
    @FXML
    private AnchorPane viewGuestPane;   
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    
    @FXML
    private void loadInviteGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewGuestInviteEvent.fxml"));
        viewGuestPane.getChildren().setAll(pane);
    }
    
}
