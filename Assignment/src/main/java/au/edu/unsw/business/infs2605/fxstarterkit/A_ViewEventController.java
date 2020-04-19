
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class A_ViewEventController {

    @FXML
    private Text eventName;
    @FXML
    private Text eventDate;
    @FXML
    private Text eventTime;
    @FXML
    private Text eventAddress;
    @FXML
    private Text eventDesc;
    @FXML
    private Text eventInstructions;
    @FXML
    private Text event_id;

    @FXML
    private AnchorPane eventPane;

    private int eventId;

    public void passEventId(int id) throws SQLException {
        this.eventId = id;
        event_id.setText(Integer.toString(id));
        Event myEvent = DatabaseManager.getEventsByEventId(id);
        //sets event details of selected event
        eventAddress.setText(myEvent.getEvent_address());
        eventDesc.setText(myEvent.getEvent_description());
        eventInstructions.setText(myEvent.getEvent_instructions());
        eventName.setText(myEvent.getEvent_name());
        eventDate.setText(myEvent.getEvent_date());
        eventTime.setText(myEvent.getEvent_start_time() + " - " + myEvent.getEvent_end_time());

    }

    @FXML
    public void btnGuestListWasClicked(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventGuestList.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventGuestListController controller = loader.getController();
        controller.passEventName(eventName.getText());
        controller.getEventId(eventId);
        controller.getRsvpData(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnEditWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EditEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_EditEventController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passData(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnEventsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }
}
