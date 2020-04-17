
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
    
    public void passEventId(int id) throws SQLException {
        
   

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
    
    public void getEventId(int id){
        this.eventId = id;
    }
    
   @FXML
   public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewEventController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
        
         System.out.println("btnBack event id: " + eventId);
   }
   @FXML
   public void btnEditWasClicked(ActionEvent event) throws IOException, SQLException{
       
       if (rsvpDecision.getText().equals("Yes") || rsvpDecision.getText().equals("No")){
       FXMLLoader loader = new FXMLLoader(getClass().getResource("G_EditRSVP.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_EditRSVPController controller = loader.getController();
        controller.getEventId(eventId);
        
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
        
         System.out.println("btnEdit event id: " + eventId);
       }else {
           String header = "Error: RSVP is null";
            String content = "Please first submit an RSVP to this event from the dashboard!";
            Alertbox.AlertError(header, content);
       }
   }
   @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
         dashboardPane.getChildren().setAll(pane);
}

    @FXML
   public void btnViewDetailsWasClicked(ActionEvent event) throws IOException, SQLException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewEventController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
        
         System.out.println("btnViewDetails event id: " + eventId);
   }
   

}
