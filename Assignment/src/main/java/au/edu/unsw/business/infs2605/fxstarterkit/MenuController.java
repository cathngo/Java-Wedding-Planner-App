/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author honesyuu
 */
public class MenuController {

    @FXML
    private Button btn_dashboard;
    @FXML
    private Button btn_events;
    @FXML
    private Button btn_guests;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button btn_invitation;
    @FXML
    private Button btn_runsheets;
    @FXML
    private Button btn_abouts;
    @FXML
    private ImageView close;
  
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dashboard(MouseEvent event) {
        loadPage("admindashboard");
    }

    @FXML
    private void events(MouseEvent event) {
        loadPage("events");
    }

    @FXML
    private void guests(MouseEvent event) {
        loadPage("guests");
    }

    @FXML
    private void invitation(MouseEvent event) {
        loadPage("invitation");
    }

    @FXML
    private void runsheets(MouseEvent event) {
        loadPage("runsheets");
    }

    @FXML
    private void abouts(MouseEvent event) {
        loadPage("abouts");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

}
