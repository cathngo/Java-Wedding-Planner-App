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

/**
 *
 * @author cathy
 */
public class A_EditEventController {

    @FXML
    TextField eventName;
    @FXML
    TextField eventAddress;
    @FXML
    TextField eventDescription;
    @FXML
    TextField eventDate;
    @FXML
    TextField startTime;
    @FXML
    TextField endTime;
    @FXML
    TextField instructions;
    @FXML
    private AnchorPane eventPane;

    private int eventId;

    @FXML
    public void btnUpdateEventWasClicked(ActionEvent event) {

        String Name = eventName.getText();
        String Description = eventDescription.getText();
        String Address = eventAddress.getText();
        String Date = eventDate.getText();
        String sTime = startTime.getText();
        String eTime = endTime.getText();
        String eInstructions = instructions.getText();

        try {
            DatabaseManager.editEvent(eventId, Name, Address, Description, Date, sTime, eTime, eInstructions);

            System.out.println("data updated successfully for event id =" + eventId);
            String header = "Update Success!";
            String content = "Successfully edited event!";
            Alertbox.AlertInfo(header, content);
        } catch (Exception e) {
            System.out.println("data not inserted");
            String header = "Update Unsuccessful";
            String content = "Please fill out all contents";
            Alertbox.AlertError(header, content);
            e.printStackTrace();
        }
    }

    public void passData(int id) throws SQLException {
        this.eventId = id;

        Event myEvent = DatabaseManager.getEventsByEventId(id);

        eventName.setText(myEvent.getEvent_name());
        eventAddress.setText(myEvent.getEvent_address());
        eventDescription.setText(myEvent.getEvent_description());
        eventDate.setText(myEvent.getEvent_date());
        startTime.setText(myEvent.getEvent_start_time());
        endTime.setText(myEvent.getEvent_end_time());
        instructions.setText(myEvent.getEvent_instructions());

    }

    public void getEventId(int id) {
        this.eventId = id;
    }
    
    @FXML
    private void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {

      AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }
@FXML
    public void btnEventsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }

}
