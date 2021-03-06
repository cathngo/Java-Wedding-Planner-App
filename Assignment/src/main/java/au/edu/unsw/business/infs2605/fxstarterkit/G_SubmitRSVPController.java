package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author jaydenso
 */
public class G_SubmitRSVPController {

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
    private TextField guestDiet;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    private int eventId;

    private int invitationId;
    private int guestId;

    public void passEventId(int id) throws SQLException {

        this.eventId = id;
        //gets event by event id
        Event myEvent = DatabaseManager.getEventsByEventId(id);
        //loads event information
        eventName.setText(myEvent.getEvent_name());
        eventDate.setText(myEvent.getEvent_date());
        eventTime.setText(myEvent.getEvent_start_time() + " - " + myEvent.getEvent_end_time());
        eventAddress.setText(myEvent.getEvent_address());

    }

    public void getEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnSubmitWasClicked(ActionEvent event) {

        String Yes = rb1.getText();
        String No = rb2.getText();
        String diet = guestDiet.getText();
        guestId = LoginController.guestUser.getGuest_id();

        try {
            //gets invitation id by event id and guest id
            invitationId = DatabaseManager.getInvitationId(eventId, guestId);

            //if guest selects yes
            if (rb1.isSelected()) {
                //submit rsvp as yes into database
                DatabaseManager.submitRSVP(invitationId, Yes);
                //if guest selects no
            } else if (rb2.isSelected()) {
                //submit rsvp as no into database
                DatabaseManager.submitRSVP(invitationId, No);
            }
            //submit guest dietary requirement into database
            DatabaseManager.updateGuestDiet(guestId, diet);

            String header = "RSVP Success!";
            String content = "RSVP was successfully submitted!";
            Alertbox.AlertInfo(header, content);

        } catch (Exception e) {
            e.printStackTrace();
            String header = "RSVP Unsuccessful";
            String content = "Please select if you can attend this event or not";
            Alertbox.AlertError(header, content);
        }

    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewAllInvitations.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnPendingInvitations(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewAllInvitations.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }
}
