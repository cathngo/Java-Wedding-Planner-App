package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author jaydenso
 */
public class G_ViewRSVPController {

    @FXML
    private AnchorPane dashboardPane;

    @FXML
    private Text eventName;

    @FXML
    private Text eventDate;

    @FXML
    private Text eventTime;

    @FXML
    private Text eventAddress;

    @FXML
    private Text rsvpDecision;

    @FXML
    private Text guestDiet;

    private int eventId;
    
    private int guestId;
    
    private ArrayList<String> rsvp = new ArrayList<String>();

    public void passEventId(int id) throws SQLException {
        
        guestId = LoginController.guestUser.getGuest_id();
        
        //guets rsvp by guest id and event id
        rsvp = DatabaseManager.getRsvpByGuestIdEventId(eventId, guestId);
        
        //loads the information for the event, alongside the guest's rsvp and dietary requirements
        eventName.setText(rsvp.get(0));
        eventDate.setText(rsvp.get(1));
        eventTime.setText(rsvp.get(2) + " - " + rsvp.get(3));
        eventAddress.setText(rsvp.get(4));
        guestDiet.setText(rsvp.get(5));
        rsvpDecision.setText(rsvp.get(6));

    }

    public void getEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        
        G_ViewEventController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnEditWasClicked(ActionEvent event) throws IOException, SQLException {
        //if rsvp already exists
        if (rsvpDecision.getText().equals("Yes") || rsvpDecision.getText().equals("No")) {
            //edit the rsvp
            FXMLLoader loader = new FXMLLoader(getClass().getResource("G_EditRSVP.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            G_EditRSVPController controller = loader.getController();
            controller.getEventId(eventId);

            controller.passEventId(eventId);
            dashboardPane.getChildren().setAll(pane);

        } else {
            //alertbox if rsvp has not yet been submitted
            String header = "Error: RSVP is null";
            String content = "Please first submit an RSVP to this event from the dashboard!";
            Alertbox.AlertError(header, content);
        }
    }

    @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnViewDetailsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        
        G_ViewEventController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);

    }

}
