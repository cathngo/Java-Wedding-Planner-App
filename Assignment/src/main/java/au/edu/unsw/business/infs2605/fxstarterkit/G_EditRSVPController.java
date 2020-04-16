
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
public class G_EditRSVPController {
    
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
        ResultSet eventRs = conn.createStatement().executeQuery("SELECT * FROM event where event_id ='"+id+"'");
        ResultSet rsvpRs = conn.createStatement().executeQuery("SELECT r.decision, g.diet_require " +
        "FROM invitation i " +
        "LEFT JOIN guest g ON g.guest_id = i.guest_id " +
        "LEFT JOIN rsvp r ON r.invitation_id = i.invitation_id " +
        "WHERE event_id ='"+id+"'" +
        "AND g.guest_id ='"+LoginController.guestUser.getGuest_id()+"'" );
        
        while (eventRs.next() && rsvpRs.next()) {
            
            String name = eventRs.getString("event_name");
            String date = eventRs.getString("event_date");
            String sTime = eventRs.getString("event_start_time");
            String eTime = eventRs.getString("event_end_time");
            String location = eventRs.getString("event_address");
            String diet = rsvpRs.getString("diet_require");
            String decision = rsvpRs.getString("decision");
          
           guestDiet.setText(diet);
           if (decision.equals(rb1.getText())){
               rb1.setSelected(true);
           } else if (decision.equals(rb2.getText())){
               rb2.setSelected(true);
           }
           
        
            eventName.setText(name);
            eventDate.setText(date);
            eventTime.setText(sTime + " - " + eTime);
            eventAddress.setText(location);
          
        }
    
         
        rsvpRs.close();
        eventRs.close();
        conn.close();
    

    }
     

    
    public void getEventId(int id){
        this.eventId = id;
    }
    
     @FXML
    public void btnUpdateWasClicked(ActionEvent event){
          
        
      
        String diet = guestDiet.getText();
        String yes = rb1.getText();
        String no = rb2.getText();
        
        try{
             Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
             ResultSet rs = conn.createStatement().executeQuery("SELECT invitation_id " +
            "FROM invitation " +
            "WHERE event_id ='"+eventId+"'" +
            "AND guest_id ='"+LoginController.guestUser.getGuest_id()+"'");
             invitationId = rs.getInt(1);
             System.out.println(invitationId);
            rs.close();
             String RsvpQuery = "UPDATE rsvp SET decision = ? WHERE invitation_id ='"+invitationId+"'"; 
 
             PreparedStatement rsvpPsmt = conn.prepareStatement(RsvpQuery);
             
             if (rb1.isSelected()){
                 rsvpPsmt.setString(1, yes);
             }else if (rb2.isSelected()){
                 rsvpPsmt.setString(1,no);
             }
             rsvpPsmt.execute();
             rsvpPsmt.close();
         
            
             
             
           
             String guestQuery = "UPDATE guest SET diet_require = ? WHERE guest_id ='"+LoginController.guestUser.getGuest_id()+"'"; 
            
             PreparedStatement guestPsmt = conn.prepareStatement(guestQuery);
             guestPsmt.setString(1, diet);
             
             
             guestPsmt.execute();
             guestPsmt.close();
             rs.close();
           
             conn.close();
             
             
        } catch(Exception e){
            System.out.println("data not inserted");
            e.printStackTrace();
        } 
    }
    
    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewRSVP.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewRSVPController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
        dashboardPane.getChildren().setAll(pane);
    }
    @FXML
    public void btnRsvpWasClicked(ActionEvent event) throws IOException, SQLException{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("G_ViewRSVP.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        G_ViewRSVPController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventId(eventId);
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
   }
   
  
   @FXML
    public void btnDashboardWasClicked(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("G_Dashboard.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
         dashboardPane.getChildren().setAll(pane);
}
    
   
}
