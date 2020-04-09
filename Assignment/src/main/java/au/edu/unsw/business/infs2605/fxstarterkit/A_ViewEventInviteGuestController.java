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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author cathy
 */

    public class A_ViewEventInviteGuestController implements Initializable{
    
     @FXML
    private TableView<Event> viewGuestTable;
   @FXML
    private TableColumn<Event,Integer> col_eventId;
    @FXML
    private TableColumn<Event,String> col_eventName;
   @FXML
    private TableColumn<Event,String> col_eventDate;
    @FXML
    private TableColumn<Event,String> col_startTime;
    @FXML
    private TableColumn<Event,String> col_endTime;
    
    ObservableList<Event>oblist = FXCollections.observableArrayList();
    
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from event");
            
            while (rs.next()){
                oblist.add(new Event( rs.getInt("event_id"),
                 rs.getString("event_name"), rs.getString("event_date"), rs.getString("event_start_time"),
                rs.getString("event_end_time")));
            }
            
        }catch(Exception e){
            System.out.println("table not created");
        }
        
          col_eventId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
        
 
        col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
        col_startTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
        col_endTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));
        
        viewGuestTable.setItems(oblist);
    }
}
