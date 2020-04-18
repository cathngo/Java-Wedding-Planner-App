/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.net.URL;
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
 * @author jaydenso
 */
public class A_CreateInvitationController implements Initializable{
    @FXML
    private TableView<Event> invitation_table;
  
    @FXML
    private TableColumn<Event,String> col_eName;
   @FXML
    private TableColumn<Event,String> col_eDate;
    @FXML
    private TableColumn<Event,String> col_eStartTime;
    @FXML
    private TableColumn<Event,String> col_eEndTime;
   

   private int eventId;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        try{
            invitation_table.setItems(DatabaseManager.getEventsByInvitation());
            
           
             col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
             col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
             col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
             col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));
        
        }
        
        
        
       
        catch(Exception e){
            System.out.println("table not created");
            e.printStackTrace();
            
        }
        
       
        
       
    }
}

