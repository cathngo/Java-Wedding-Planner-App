
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_ViewAllEventsController {

    @FXML
    public TableView<Event> eventTable;
    @FXML
    public TableColumn<Event, Integer> col_eId;
    @FXML
    public TableColumn<Event, String> col_eName;
    @FXML
    public TableColumn<Event, String> col_eDate;
    @FXML
    public TableColumn<Event, String> col_eStartTime;
    @FXML
    public TableColumn<Event, String> col_eEndTime;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private AnchorPane eventPane;
    

    private int eventId;

    private String eventName;

    public void initialize() {
        try {
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
    private void searchEvents(){
        
        String searchString = searchField.getText();
        eventTable.setItems(DatabaseManager.getEvents());
    }
   

    @FXML
    private void btnViewDetailsWasClicked(ActionEvent event) throws IOException, SQLException {
        try {
            Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
            eventId = selectedEvent.getEvent_id();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_ViewEventController controller = loader.getController();
            controller.passEventId(eventId);
            eventPane.getChildren().setAll(pane);
            System.out.println("view details event id:" + eventId);

        } catch (Exception e) {
            String header = "Unable to view details of event";
            String content = "Please select an event from the table 'Your Events'";
            Alertbox.AlertError(header, content);
        }
    }

    @FXML
    private void btnCreateNewEventWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_CreateEvent.fxml"));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    private void btnInviteGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        try {
            Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
            eventId = selectedEvent.getEvent_id();
            eventName = selectedEvent.getEvent_name();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EventInviteGuest.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_EventInviteGuestController controller = loader.getController();
            controller.passData(eventName);
            controller.getEventId(eventId);
            eventPane.getChildren().setAll(pane);

            System.out.println("invite guests event id:" + eventId);
        } catch (Exception e) {
            String header = "Unable to invite guests for this event";
            String content = "Please select an event from the table 'Your Events'";
            Alertbox.AlertError(header, content);
        }

    }
}
