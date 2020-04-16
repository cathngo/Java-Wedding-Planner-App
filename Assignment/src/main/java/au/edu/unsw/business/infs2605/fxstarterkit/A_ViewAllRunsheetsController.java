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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jaydenso
 */
public class A_ViewAllRunsheetsController implements Initializable{
    @FXML
    private AnchorPane runsheetPane;
    @FXML
    private TableView<Event> runsheetTable;
  
    @FXML
    private TableColumn<Event,String> col_eName;
   @FXML
    private TableColumn<Event,String> col_eDate;
    @FXML
    private TableColumn<Event,String> col_eStartTime;
    @FXML
    private TableColumn<Event,String> col_eEndTime;
    @FXML
    private AnchorPane eventPane;

    ObservableList<Event>runsheetList = FXCollections.observableArrayList();
    
    
    private int eventId;
    
    private String eventName;
    
    

  
     
   @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from event WHERE event_runsheet IS NOT NULL");
            
            while (rs.next()){
                runsheetList.add(new Event( rs.getInt("event_id"),
                 rs.getString("event_name"),rs.getString("event_date"),rs.getString("event_start_time"),
              rs.getString("event_end_time")));
            }
           
             col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
             col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
             col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
             col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));
        
 
        
        runsheetTable.setItems(runsheetList);
        
        conn.close();
        rs.close();
        }catch(Exception e){
            System.out.println("table not created");
            e.printStackTrace();
            
        }
        
       
        
       
    }
       

    
    
    
    @FXML
    public void btnCreateRunsheetWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void btnViewRunsheetWasClicked(ActionEvent event) throws IOException {
        Event selectedEvent = runsheetTable.getSelectionModel().getSelectedItem();
        eventId = selectedEvent.getEvent_id();
        try{
           Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + ""+System.getProperty("user.dir")+"\\runsheet" + eventId + ".pdf");
       }catch(Exception e){
           System.out.println("unsuccessful");
           e.printStackTrace();
       }
       
    }
}
