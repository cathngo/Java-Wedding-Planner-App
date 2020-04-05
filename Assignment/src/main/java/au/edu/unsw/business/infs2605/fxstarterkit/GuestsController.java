/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author honesyuu
 */
public class GuestsController implements Initializable {


    @FXML
    private TableView<?> tableGuests;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private Button btnInviteGuest;
    @FXML
    private Button btnCreateNewGuest;
    @FXML
    private Button btnViewGuests;
    @FXML
    private AnchorPane guestsPane;
    /**
     * Initializes the controller class.
     */   
    
    @FXML
    private void loadInviteGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("invitateguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadCreateGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("createguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadViewGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("viewguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

}
