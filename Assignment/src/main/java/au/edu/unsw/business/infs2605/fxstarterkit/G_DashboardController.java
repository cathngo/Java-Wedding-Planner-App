package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
 * @author jaydenso
 */
public class G_DashboardController implements Initializable {

    @FXML
    private TableView<RSVPEventWrapper> dashboard_table;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventName;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventDate;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventRsvp;
    @FXML
    private AnchorPane dashboardPane;
    private int eventId;
    private RSVPEventWrapper selectedEvent;
    private int guestId;

    ObservableList<RSVPEventWrapper> eventList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        guestId = LoginController.guestUser.getGuest_id();
        try {
            dashboard_table.setItems(DatabaseManager.getRsvpGetEvent(guestId));

            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }

    }

    @FXML
    private void btnViewDetailsWasClicked(ActionEvent event) throws IOException, SQLException {

        try {
            selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();
            String date = selectedEvent.getEvent_date();
            String name = selectedEvent.getEvent_name().replace("'", "''");
            eventId = DatabaseManager.getGuestIdByGuestNameGuestDate(name, date);

            FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
            AnchorPane pane = (AnchorPane) viewEventloader.load();
            G_ViewEventController viewEventController = viewEventloader.getController();
            viewEventController.getEventId(eventId);
            viewEventController.passEventId(eventId);
            dashboardPane.getChildren().setAll(pane);

            System.out.println("btnViewDetails event id: " + eventId);
        } catch (Exception e) {
            String header = "Unable to view details of event";
            String content = "Please select an event from the table ";
            Alertbox.AlertError(header, content);
            e.printStackTrace();
        }

    }

    @FXML
    public void btnRsvpWasClicked(ActionEvent event) throws IOException, SQLException {
        selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();

        try {
            if (selectedEvent.getDecision() == null) {
                selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();
                String date = selectedEvent.getEvent_date();
                String name = selectedEvent.getEvent_name().replace("'", "''");
                eventId = DatabaseManager.getGuestIdByGuestNameGuestDate(name, date);
                FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_SubmitRSVP.fxml"));
                AnchorPane pane = (AnchorPane) viewEventloader.load();
                G_SubmitRSVPController controller = viewEventloader.getController();
                controller.getEventId(eventId);
                controller.passEventId(eventId);
                dashboardPane.getChildren().setAll(pane);
            } else {
                String header = "Error: You have already submitted an RSVP";
                String content = "To change your response, go to 'Edit RSVP'";
                Alertbox.AlertError(header, content);
            }
            System.out.println("btnRsvp event id: " + eventId);
        } catch (Exception e) {

            String header = "Unable to RSVP to event";
            String content = "Please select an event from the table ";
            Alertbox.AlertError(header, content);
            e.printStackTrace();

        }
    }

    @FXML
    public void btnEditRsvpWasClicked(ActionEvent event) throws IOException {
        selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();

        try {
            if (selectedEvent.getDecision() != null) {
                selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();
                String date = selectedEvent.getEvent_date();
                String name = selectedEvent.getEvent_name().replace("'", "''");
                eventId = DatabaseManager.getGuestIdByGuestNameGuestDate(name, date);
                FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_DashboardEditRSVP.fxml"));
                AnchorPane pane = (AnchorPane) viewEventloader.load();
                G_DashboardEditRSVPController controller = viewEventloader.getController();
                controller.getEventId(eventId);
                controller.passEventId(eventId);
                dashboardPane.getChildren().setAll(pane);
            } else {
                String header = "Error: You have not yet submitted an RSVP";
                String content = "To submit your response, go to 'RSVP to Event'";
                Alertbox.AlertError(header, content);
            }
            System.out.println("btnRsvp event id: " + eventId);
        } catch (Exception e) {

            String header = "Unable to edit RSVP";
            String content = "Please select an event from the table ";
            Alertbox.AlertError(header, content);
            e.printStackTrace();

        }
    }
}
