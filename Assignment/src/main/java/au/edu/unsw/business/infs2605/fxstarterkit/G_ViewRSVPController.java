
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
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
    
    
    public void getEventId(int id) throws SQLException {
        
   

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT g.diet_require, r.decision, e.event_name, e.event_date, e.event_start_time, e.event_end_time, e.event_address " +
        "FROM guest g " +
        "JOIN invitation i ON g.guest_id = i.guest_id " +
        "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id " +
        "JOIN event e ON e.event_id = i.event_id " +
        "WHERE e.event_id ='"+id+"'" +
        "AND g.guest_id ='"+LoginController.guestUser.getGuest_id()+"'" );

        while (rs.next()) {
            
            String name = rs.getString("event_name");
            String date = rs.getString("event_date");
            String sTime = rs.getString("event_start_time");
            String eTime = rs.getString("event_end_time");
            String address = rs.getString("event_address");
            String diet = rs.getString("diet_require");
            String decision= rs.getString("decision");
           
            
        
            eventName.setText(name);
            eventDate.setText(date);
            eventTime.setText(sTime + " - " + eTime);
            eventAddress.setText(address);
            guestDiet.setText(diet);
            rsvpDecision.setText(decision);
            
            System.out.println(decision);
        
        }
        
        conn.close();
        rs.close();

    }

}
