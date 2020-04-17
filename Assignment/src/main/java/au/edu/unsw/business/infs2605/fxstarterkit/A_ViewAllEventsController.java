
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author cathy
 */
public class A_ViewAllEventsController implements Initializable {

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
    private AnchorPane eventPane;

    ObservableList<Event> eventList = FXCollections.observableArrayList();

    private int eventId;

    private String eventName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from event");

            while (rs.next()) {
                eventList.add(new Event(rs.getInt("event_id"),
                        rs.getString("event_name"), rs.getString("event_date"), rs.getString("event_start_time"),
                        rs.getString("event_end_time")));
            }
            col_eId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
            col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));

            eventTable.setItems(eventList);

            conn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("table not created");

        }

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
