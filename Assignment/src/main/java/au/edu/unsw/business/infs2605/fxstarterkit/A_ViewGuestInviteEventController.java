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
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author cathy
 */
public class A_ViewGuestInviteEventController implements Initializable{
      @FXML
    private TableView<TableGuestList> existingGuestTable;
   
    @FXML
    private TableColumn<TableGuestList,String> col_fname;
    @FXML
    private TableColumn<TableGuestList,String> col_lname;
   
    @FXML
    private AnchorPane guestsPane;
     


   
    ObservableList<TableGuestList>guestList = FXCollections.observableArrayList();
    
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from guest");
            
            while (rs.next()){
                guestList.add(new TableGuestList( rs.getString("guest_fname"),
             
              rs.getString("guest_lname")));
           
            }
            
        }catch(Exception e){
            System.out.println("table not created");
        }
        
        
        col_fname.setCellValueFactory(new PropertyValueFactory<>("guest_fname"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("guest_lname"));
        
 
  
       
        
        existingGuestTable.setItems(guestList);
    }
}
