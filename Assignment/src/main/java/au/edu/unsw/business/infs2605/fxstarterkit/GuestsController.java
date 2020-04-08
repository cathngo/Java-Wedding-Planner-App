
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
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author honesyuu
 */
public class GuestsController implements Initializable {
    
    @FXML
    private TableView<?> tableGuests;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;


   
    @FXML
    private Button btnInviteGuest;
    @FXML
    private Button btnCreateNewGuest;
    @FXML
    private Button btnViewGuests;
    @FXML
    private AnchorPane guestsPane;
      @FXML
    private TableView<TableGuest> guest_table;
   
    @FXML
    private TableColumn<TableGuest,String> col_firstName;
   @FXML
    private TableColumn<TableGuest,String> col_lastName;
    @FXML
    private TableColumn<TableGuest,String> col_guestEmail;
   
    ObservableList<TableGuest>guestList = FXCollections.observableArrayList();
    
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from guest");
            
            while (rs.next()){
                guestList.add(new TableGuest( rs.getString("guest_fname"),
                 rs.getString("guest_lname"),
              rs.getString("guest_email")));
            }
            
        }catch(Exception e){
            System.out.println("table not created");
        }
        
        
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("guest_fname"));
        
 
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("guest_lname"));
        col_guestEmail.setCellValueFactory(new PropertyValueFactory<>("guest_email"));
       
        
        guest_table.setItems(guestList);
    }
        
   
    /**
     * Initializes the controller class.
     */   
    
    @FXML
    private void loadInviteGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("invitateguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadCreateGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("createguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadViewGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("viewguest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }
    
}
