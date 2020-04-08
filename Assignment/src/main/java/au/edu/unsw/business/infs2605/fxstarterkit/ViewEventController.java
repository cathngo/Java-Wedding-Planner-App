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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class ViewEventController implements Initializable {
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
  
   public void passData(Event event){
        selectedEvent = event;
        eventName.setText(selectedEvent.getEvent_name());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
