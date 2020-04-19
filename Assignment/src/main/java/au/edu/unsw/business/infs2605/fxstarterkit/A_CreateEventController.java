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
public class A_CreateEventController {

    @FXML
    TextField eventId;
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

    @FXML
    public void btnCreateEventWasClicked() throws SQLException {

        String Name = eventName.getText();
        String Address = eventAddress.getText();
        String Description = eventDescription.getText();
        String Date = eventDate.getText();
        String sTime = startTime.getText();
        String eTime = endTime.getText();
        String eInstructions = instructions.getText();

        try {
            //alertbox error if fields are empty
            if (eventName.getText().isEmpty() || eventAddress.getText().isEmpty() || eventDescription.getText().isEmpty() || eventDate.getText().isEmpty() || startTime.getText().isEmpty() || endTime.getText().isEmpty()) {
                String header = "Unable to add to create event";
                String content = "Please fill out all contents of 'create event'";
                Alertbox.AlertError(header, content);
            } else {
                //creates event
                DatabaseManager.createEvent(Name, Address, Description, Date, sTime, eTime, eInstructions);

                String header = "Event created!";
                String content = "Event was successfully created!";
                Alertbox.AlertInfo(header, content);
                System.out.println("data inserted successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String header = "Unable to create event";
            String content = "Please fill out all contents of 'create event'";
            Alertbox.AlertError(header, content);
            System.out.println("data not inserted");

        }

    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnEventsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }
}
