
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author jaydenso
 */
public class G_ViewEventController {
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
    private AnchorPane dashboardPane;
    
    private int eventId;
    
    public void passEventId(int id) throws SQLException {
        
   

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event where event_id ='"+id+"'");

        while (rs.next()) {
            
            String name = rs.getString("event_name");
            String date = rs.getString("event_date");
            String sTime = rs.getString("event_start_time");
            String eTime = rs.getString("event_end_time");
            String location = rs.getString("event_address");
            String description = rs.getString("event_description");
            String instructions = rs.getString("event_instructions");
            
        
            eventName.setText(name);
            eventDate.setText(date);
            eventTime.setText(sTime + " - " + eTime);
            eventAddress.setText(location);
            eventDesc.setText(description);
            eventInstructions.setText(instructions);
        
        }
        
        conn.close();
        rs.close();

    }
    
    public void getEventId(int id){
        this.eventId = id;
        System.out.println(eventId);
    }
    @FXML
    public void btnViewRsvpWasClicked(ActionEvent event) throws IOException, SQLException{
        System.out.println(eventId);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewRSVP.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewRSVPController controller = loader.getController();
        controller.getEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
        
        
    }
}
