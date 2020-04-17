package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void passEventId(int id) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet eventRs = conn.createStatement().executeQuery("SELECT * FROM event where event_id ='" + id + "'");

        while (eventRs.next()) {

            String name = eventRs.getString("event_name");
            String date = eventRs.getString("event_date");
            String sTime = eventRs.getString("event_start_time");
            String eTime = eventRs.getString("event_end_time");
            String location = eventRs.getString("event_address");

            eventName.setText(name);
            eventDate.setText(date);
            eventTime.setText(sTime + " - " + eTime);
            eventAddress.setText(location);

        }

        eventRs.close();
        conn.close();

    }

    public void getEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnSubmitWasClicked(ActionEvent event) {

        String Yes = rb1.getText();
        String No = rb2.getText();
        String diet = guestDiet.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT invitation_id "
                    + "FROM invitation "
                    + "WHERE event_id ='" + eventId + "'"
                    + "AND guest_id ='" + LoginController.guestUser.getGuest_id() + "'");
            invitationId = rs.getInt(1);

            rs.close();
            String RsvpQuery = "INSERT OR IGNORE INTO rsvp"
                    + " (decision, invitation_id)"
                    + " VALUES (?, ?)";

            PreparedStatement rsvpPsmt = conn.prepareStatement(RsvpQuery);

            if (rb1.isSelected()) {
                rsvpPsmt.setString(1, Yes);
            } else if (rb2.isSelected()) {
                rsvpPsmt.setString(1, No);
            }

            rsvpPsmt.setInt(2, invitationId);
            rsvpPsmt.execute();
            rsvpPsmt.close();

            String guestQuery = "UPDATE guest SET diet_require = ? WHERE guest_id ='" + LoginController.guestUser.getGuest_id() + "'";

            PreparedStatement guestPsmt = conn.prepareStatement(guestQuery);
            guestPsmt.setString(1, diet);

            guestPsmt.execute();
            guestPsmt.close();
            conn.close();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
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
