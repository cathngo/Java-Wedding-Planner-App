
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
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
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event where event_id =  " + id);

        while (rs.next()) {
            String location = rs.getString("event_address");
            String description = rs.getString("event_description");
            String instructions = rs.getString("event_instructions");
            String name = rs.getString("event_name");
            String date = rs.getString("event_date");
            String sTime = rs.getString("event_start_time");
            String eTime = rs.getString("event_end_time");

            eventAddress.setText(location);
            eventDesc.setText(description);
            eventInstructions.setText(instructions);
            eventName.setText(name);
            eventDate.setText(date);
            eventTime.setText(sTime + " - " + eTime);
        
        }
        
        conn.close();
        rs.close();

    }
    
    @FXML
    private void btnGuestListWasClicked(ActionEvent event) throws IOException, SQLException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventGuestList.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewEventGuestListController controller = loader.getController();
        controller.passEventName(eventName.getText());
        controller.getEventId(eventId);
        controller.getRsvpData(eventId);
        eventPane.getChildren().setAll(pane);
        
    }
  @FXML
    private void btnEditWasClicked(ActionEvent event) throws IOException, SQLException {
        
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

