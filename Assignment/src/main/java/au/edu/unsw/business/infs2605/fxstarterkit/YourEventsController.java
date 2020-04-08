/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
/**
 *
 * @author cathy
 */
public class YourEventsController implements Initializable{
    
     @FXML
    private TableView<Event> event_table;
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
    @FXML
    private AnchorPane eventPane;
    @FXML
    private Button btnViewDetails;
    
    
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
    
    private Event selectedEvent;
    
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
        
        event_table.setItems(oblist);
    }
    
    @FXML
    private void loadViewEvent(ActionEvent event) throws IOException{
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("viewEvent.fxml"));
        eventPane.getChildren().setAll(pane);
        
                 
           
             }
    
    
  
             
              
        
                
               
            

         
         
    
  
    }
        
    
    
    
  
   

