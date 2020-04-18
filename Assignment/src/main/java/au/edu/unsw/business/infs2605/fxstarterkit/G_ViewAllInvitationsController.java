
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
public class G_ViewAllInvitationsController implements Initializable{
    @FXML
    private TableView<RSVPEventWrapper> rsvp_table;
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
            rsvp_table.setItems(DatabaseManager.getNullRsvpGetEvent(guestId));

            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }

    }
    
    @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        dashboardPane.getChildren().setAll(pane);

    }
    
    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        dashboardPane.getChildren().setAll(pane);

    }
    
    
            
    @FXML
    public void btnRsvpWasClicked(ActionEvent event) throws IOException, SQLException {
        
        try{
        selectedEvent = rsvp_table.getSelectionModel().getSelectedItem();
                String date = selectedEvent.getEvent_date();
                String name = selectedEvent.getEvent_name().replace("'", "''");
                eventId = DatabaseManager.getGuestIdByGuestNameGuestDate(name, date);
                FXMLLoader viewEventloader = new FXMLLoader(getClass().getResource("G_SubmitRSVP.fxml"));
                AnchorPane pane = (AnchorPane) viewEventloader.load();
                G_SubmitRSVPController controller = viewEventloader.getController();
                controller.getEventId(eventId);
                controller.passEventId(eventId);
                dashboardPane.getChildren().setAll(pane);
        }catch (Exception e){
             String header = "Unable to RSVP to event";
            String content = "Please select an event from the table ";
            Alertbox.AlertError(header, content);
        }
    }
    
    
}
