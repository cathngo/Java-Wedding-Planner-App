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
import java.sql.SQLException;
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
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */

    public class A_ViewEventInviteGuestController implements Initializable{
     @FXML
    private TableView<Guest> existingGuestTable;
   
    @FXML
    private TableColumn<Guest,String> col_fname;
    @FXML
    private TableColumn<Guest,String> col_lname;
    @FXML
    private Text eventName;
   
    @FXML
    private AnchorPane eventPane;
    
    

     


   
    ObservableList<Guest>guestList = FXCollections.observableArrayList();
    
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from guest");
            
            while (rs.next()){
                guestList.add(new Guest( rs.getString("guest_fname"),
             
              rs.getString("guest_lname")));
           
            }
            
        }catch(Exception e){
            System.out.println("table not created");
        }
        
        
        col_fname.setCellValueFactory(new PropertyValueFactory<>("guest_fname"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("guest_lname"));
        existingGuestTable.setItems(guestList);
    }
    
    
    
    @FXML
    private void btnInviteNewGuestWasClicked(ActionEvent event) throws IOException {
        
    }
    
    
   public void passData(String name) throws SQLException {
       
        eventName.setText(name);
        
    }
   
   //pageswitchbreadcrumb
   @FXML
     private void backToGuestList(ActionEvent event) throws IOException, SQLException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewEventGuestList.fxml"));
        eventPane.getChildren().setAll(pane); 
     }
    
}
