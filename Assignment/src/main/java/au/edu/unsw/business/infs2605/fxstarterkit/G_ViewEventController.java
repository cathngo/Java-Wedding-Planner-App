
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
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
        this.eventId = id;
   

        Event myEvent = DatabaseManager.getEventsByEventId(id);
        
            eventName.setText(myEvent.getEvent_name());
            eventDate.setText(myEvent.getEvent_date());
            eventTime.setText(myEvent.getEvent_start_time() + " - " + myEvent.getEvent_end_time());
            eventAddress.setText(myEvent.getEvent_address());
            eventDesc.setText(myEvent.getEvent_description());
            eventInstructions.setText(myEvent.getEvent_instructions());
        
        }
        
       

    
    
    public void getEventId(int id){
        this.eventId = id;
        System.out.println(eventId);
    }
    @FXML
    public void btnViewRsvpWasClicked(ActionEvent event) throws IOException, SQLException{
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewRSVP.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewRSVPController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
        
         System.out.println("btnViewRsvp event id: " + eventId);
        
        
    }
    @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
         dashboardPane.getChildren().setAll(pane);
}
    
    @FXML
    public void btnViewRunsheetWasClicked(ActionEvent event) throws IOException, SQLException{
       try{
           Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + ""+System.getProperty("user.dir")+"\\runsheet" + eventId + ".pdf");
       }catch(Exception e){
           System.out.println("unsuccessful");
           String header = "Unable to view runsheet";
            String content = "Runsheet is not available for this event";
            Alertbox.AlertError(header, content);
           e.printStackTrace();
       }
        
        
        
    }
    
    @FXML
    public void btnViewInvitationWasClicked(ActionEvent event) throws IOException, SQLException{
       try{
           Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + ""+System.getProperty("user.dir")+"\\invitation" + eventId + ".pdf");
       }catch(Exception e){
           System.out.println("unsuccessful");
           String header = "Unable to view invitation";
            String content = "Invitation is not available for this event";
            Alertbox.AlertError(header, content);
           e.printStackTrace();
       }
        
    }
        
    
}

    
