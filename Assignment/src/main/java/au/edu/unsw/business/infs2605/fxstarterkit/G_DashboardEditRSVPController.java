package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class G_DashboardEditRSVPController {

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
    private ArrayList<String> rsvp = new ArrayList<String>();

    public void passEventId(int id) throws SQLException {
        guestId = LoginController.guestUser.getGuest_id();

        rsvp = DatabaseManager.getRsvpByGuestIdEventId(eventId, guestId);

        eventName.setText(rsvp.get(0));
        eventDate.setText(rsvp.get(1));
        eventTime.setText(rsvp.get(2) + " - " + rsvp.get(3));
        eventAddress.setText(rsvp.get(4));
        guestDiet.setText(rsvp.get(5));

        if (rsvp.get(6).equals(rb1.getText())) {
            rb1.setSelected(true);
        } else if (rsvp.get(6).equals(rb2.getText())) {
            rb2.setSelected(true);
        }

    }

    public void getEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnUpdateWasClicked(ActionEvent event) {

        String diet = guestDiet.getText();
        String yes = rb1.getText();
        String no = rb2.getText();

        try {
            invitationId = DatabaseManager.getInvitationId(eventId, guestId);

            if (rb1.isSelected()) {

                DatabaseManager.updateRSVP(invitationId, yes);
            } else if (rb2.isSelected()) {

                DatabaseManager.updateRSVP(invitationId, no);
            }

            DatabaseManager.updateGuestDiet(guestId, diet);

            String header = "Update Success!";
            String content = "RSVP was successfully edited!";
            Alertbox.AlertInfo(header, content);

        } catch (Exception e) {
            System.out.println("data not inserted");
            String header = "Update Unsuccessful";
            String content = "Please select if you can attend this event";
            Alertbox.AlertError(header, content);
            e.printStackTrace();
        }
    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        dashboardPane.getChildren().setAll(pane);
    }
}
