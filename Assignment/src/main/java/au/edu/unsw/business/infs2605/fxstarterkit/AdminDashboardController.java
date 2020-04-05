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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author honesyuu
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button btn_guestspg;
    @FXML
    private Button btn_invitationpg;
    @FXML
    private Button btn_eventpg;
    @FXML
    private AnchorPane dashboardPane;
    
    @FXML
    private TableColumn<?, ?> UpcomingEventsColumn;
    @FXML
    private TableView<?> upcomingEventsTable;

    //link buttons to change the anchorpane
    @FXML
    private void loadInvitation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("invitation.fxml"));
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadGuests(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("guests.fxml"));
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadEvent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("events.fxml"));
        dashboardPane.getChildren().setAll(pane);
    }
    
    //load data into the upcoming events table
    //@FXML private TableView<DatabaseManager> tableView;
    //@FXML private TableColumn<DatabaseManager, String> UpcomingEventsColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //UpcomingEventsColumn.setCellValueFactory(new PropertyValueFactory<DatabaseManager, String>("event_name"));
    }
    

}
