/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jaydenso
 */
public class A_CreateInvitationController implements Initializable {

    @FXML
    private TableView<Event> invitation_table;
    @FXML
    private TableColumn<Event, String> col_eName;
    @FXML
    private TableColumn<Event, String> col_eDate;
    @FXML
    private TableColumn<Event, String> col_eStartTime;
    @FXML
    private TableColumn<Event, String> col_eEndTime;
    @FXML
    private AnchorPane invitationPane;

    private int eventId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //sets tableview of events
        try {
            invitation_table.setItems(DatabaseManager.getEventsByInvitation());

            col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));

        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }
    }

    @FXML
    public void btnCreateInvitationWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_InvitationSelectEvent.fxml"));
        invitationPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnViewInvitationWasClicked(ActionEvent event) throws IOException {
        Event selectedEvent = invitation_table.getSelectionModel().getSelectedItem();
        eventId = selectedEvent.getEvent_id();
        //Opens the Invitation PDF in a pdf viewer
        DetectOS.open("" + System.getProperty("user.dir") + File.separator + "invitation" + eventId + ".pdf");
    }
}
