/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class A_ViewGuestInviteEventController implements Initializable {

    @FXML
    private TableView<Event> viewGuestTable;
    @FXML
    private TableColumn<Event, Integer> col_eventId;
    @FXML
    private TableColumn<Event, String> col_eventName;
    @FXML
    private TableColumn<Event, String> col_eventDate;
    @FXML
    private TableColumn<Event, String> col_startTime;
    @FXML
    private TableColumn<Event, String> col_endTime;
    @FXML
    private Text guestName;
    
    private Guest selectedGuest;
    private int guestId;
   
    

    ObservableList<Event> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from event");

            while (rs.next()) {
                oblist.add(new Event(rs.getInt("event_id"),
                        rs.getString("event_name"), rs.getString("event_date"), rs.getString("event_start_time"),
                        rs.getString("event_end_time")));
            }
        conn.close();
        rs.close();
        } catch (Exception e) {
            System.out.println("table not created");
        }

        col_eventId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
        col_startTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
        col_endTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));
        
        viewGuestTable.setItems(oblist);
    }
    
    public void passData(Guest guest) throws SQLException {
        selectedGuest = guest;
        guestName.setText(selectedGuest.getGuest_fname() + "  " + selectedGuest.getGuest_lname());

        }
    
    public void passGuestName(String name){
        guestName.setText(name);
    }
    
    public void getGuestId(int id){
        this.guestId = id;
    }
     @FXML
     public void btnInviteToEventWasClicked(ActionEvent event) throws SQLException{
         try{
         int eventId = viewGuestTable.getSelectionModel().getSelectedItem().getEvent_id();
         
         
         Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
         int rs = conn.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '"+eventId+"', '"+guestId+"','"+LoginController.adminUser.getAdmin_id()+"' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='"+eventId+"' AND guest_id ='"+guestId+"''"+LoginController.adminUser.getAdmin_id()+"')");
         
         
             conn.close();
         
         System.out.println("succesfully updated");

     }catch(Exception e){
         System.out.println("unsuccessful");
             System.out.println("eventId:" + viewGuestTable.getSelectionModel().getSelectedItem().getEvent_id());
             System.out.println("guestid:" + guestId);
             System.out.println(LoginController.adminUser.getAdmin_id());
         e.printStackTrace();
         
   
     }
}
}
