
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
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
    private TableView<Event> event_table;

    private Event selectedEvent;

    public void passData(Event event) throws SQLException {
        selectedEvent = event;
        eventName.setText(selectedEvent.getEvent_name());
        eventDate.setText(selectedEvent.getEvent_date());
        eventTime.setText(selectedEvent.getEvent_start_time() + " - " + selectedEvent.getEvent_end_time());

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event where event_id =  " + selectedEvent.getEvent_id());

        while (rs.next()) {
            String location = rs.getString("event_address");
            String description = rs.getString("event_description");
            String instructions = rs.getString("event_instructions");

            eventAddress.setText(location);
            eventDesc.setText(description);
            eventInstructions.setText(instructions);
        }

    }
}

