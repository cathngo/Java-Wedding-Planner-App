
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.robot.Robot;

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

    ObservableList<RSVPEventWrapper> eventList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT e.event_id, e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE g.guest_id = '" + LoginController.guestUser.getGuest_id() + "'");
            
            while (rs.next()) {
                eventList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
                
                
            }
            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

            dashboard_table.setItems(eventList);
            
            conn.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }
         
      
    }
    
    @FXML
    private void btnViewDetailsWasClicked(ActionEvent event) throws IOException, SQLException{
        selectedEvent = dashboard_table.getSelectionModel().getSelectedItem();
        String date = selectedEvent.getEvent_date();
        String name = selectedEvent.getEvent_name().replace("'","''");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT event_id "
                    + "FROM event "
                    + "WHERE event_name ='"+name+"'"
                    + "AND event_date='"+date+"'");
            
            eventId = rs.getInt(1);
        }catch (Exception e){
                    e.printStackTrace();
                    }
        
        FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane)viewEventloader.load();
        G_ViewEventController viewEventController = viewEventloader.getController();
        viewEventController.getEventId(eventId);
        viewEventController.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
       
        
        
        
        }
    
        
    
    @FXML
    public void btnRsvpWasClicked(ActionEvent event) throws IOException, SQLException{
        FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_SubmitRSVP.fxml"));
        AnchorPane pane = (AnchorPane)viewEventloader.load();
        G_SubmitRSVPController controller = viewEventloader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
    }
    
    
}
