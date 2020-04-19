/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author cathy
 */
public class A_InvitationSelectEventController implements Initializable {

    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, Integer> col_eId;
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

    ObservableList<Event> eventList = FXCollections.observableArrayList();

    private int eventId;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //sets tableview of events
            eventTable.setItems(DatabaseManager.getEvents());

            col_eId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
            col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_CreateInvitation.fxml"));
        invitationPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnInvitationsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_CreateInvitation.fxml"));
        invitationPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnCreateInvitationWasClicked(ActionEvent event) throws Exception {

        try {
            Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
            eventId = selectedEvent.getEvent_id();
            //creates invitation pdf
            A_InvitationPDFController.createNewInvPDF(eventId);
            //stores the invitation into the database
            BLOB invitation = new BLOB();
            invitation.updateInvitation(eventId, "invitation" + eventId +".pdf");
            //alertbox is invitation was successfully created
            String header = "Invitation Success!";
            String content = "Invitation was successfully created!";
            Alertbox.AlertInfo(header, content);
            
        } catch (Exception e) {
            //alertbox if the invitation is not created
            String header = "Unable to proceed";
            String content = "Either event not selected or invitation already exists";
            Alertbox.AlertError(header, content);
            e.printStackTrace();
        }

    }
}
