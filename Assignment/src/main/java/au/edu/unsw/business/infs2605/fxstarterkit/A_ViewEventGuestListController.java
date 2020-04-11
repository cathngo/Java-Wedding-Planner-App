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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author cathy
 */
public class A_ViewEventGuestListController {
    
    @FXML
    private PieChart pieChart;
    @FXML
    private ObservableList<PieChart.Data> pieChartData;
    @FXML
    private AnchorPane eventPane;
    @FXML
    private Text eventName;
    @FXML
    private TableView<RSVPGuestWrapper> rsvp_table;
    @FXML
    private TableColumn <RSVPGuestWrapper, String> col_guestName;
    @FXML
    private TableColumn <RSVPGuestWrapper, String> col_decision;
    
    ObservableList<RSVPGuestWrapper>rsvpList = FXCollections.observableArrayList();
    
  
    
    
   
       public void getRsvpData(int id) throws SQLException {
        pieChartData = FXCollections.observableArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) " +
            "FROM rsvp r " +
            "JOIN invitation i ON i.invitation_id = r.invitation_id " +
            "JOIN event e ON e.event_id = i.event_id " +
            "JOIN guest g ON g.guest_id = i.guest_id " +
            "WHERE r.decision = 'Yes' " +
            "AND e.event_id ='"+id+"'");

            ResultSet rs2 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) " +
            "FROM rsvp r " +
            "JOIN invitation i ON i.invitation_id = r.invitation_id " +
            "JOIN event e ON e.event_id = i.event_id " +
            "JOIN guest g ON g.guest_id = i.guest_id " +
            "WHERE r.decision = 'No' " +
            "AND e.event_id ='"+id+"'");

            ResultSet rs3 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) " +
            "FROM rsvp r " +
            "JOIN invitation i ON i.invitation_id = r.invitation_id " +
            "JOIN event e ON e.event_id = i.event_id " +
            "JOIN guest g ON g.guest_id = i.guest_id " +
            "WHERE r.decision = '' " +
            "AND e.event_id ='"+id+"'");

            pieChartData.add(new PieChart.Data("Yes", rs1.getInt("COUNT(rsvp_id)")));
            pieChartData.add(new PieChart.Data("No", rs2.getInt("COUNT(rsvp_id)")));
            pieChartData.add(new PieChart.Data("Unsure", rs3.getInt("COUNT(rsvp_id)")));

            pieChart.setData(pieChartData);
            pieChart.setLabelsVisible(true);
          
       


        } catch (Exception e) {
            System.out.println("unsuccessful");
            e.printStackTrace();
        }
        

    }
    //pageswitchbreadcrumb
    @FXML
     private void backToViewEvent(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewEvent.fxml"));
        eventPane.getChildren().setAll(pane); 
     }
    
    //pageswitchbutton
    @FXML
    private void btnInviteGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
     
       FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventInviteGuest.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewEventInviteGuestController controller = loader.getController();
        controller.passData(eventName.getText());
        eventPane.getChildren().setAll(pane);
    }
    @FXML
    private void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewEvent.fxml"));
        eventPane.getChildren().setAll(pane);
    }
    public void passData(String name) throws SQLException {
       
        eventName.setText(name);
        
    }
    
    public void getEventId(int id){
      
         try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT g.guest_fname, g.guest_lname, r.decision " +
            "FROM guest g " +
            "JOIN invitation i ON g.guest_id = i.guest_id " +
            "JOIN rsvp r ON r.invitation_id = i.invitation_id " +
            "JOIN event e ON e.event_id = i.event_id " +
            "WHERE e.event_id ='"+id+"'");
            
          

            
            
             while (rs.next()) {
                 rsvpList.add(new RSVPGuestWrapper(rs.getString("guest_fname"),
                         rs.getString("guest_lname"), rs.getString("decision")));
             }
             col_guestName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RSVPGuestWrapper, String>, ObservableValue<String>>() {
                 @Override
                 public ObservableValue<String> call(
                         TableColumn.CellDataFeatures<RSVPGuestWrapper, String> p) {
                     return new SimpleStringProperty(p.getValue().getGuest_fname()
                             + " " + p.getValue().getGuest_lname());
                 }
             });
             col_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
            
        
 
        
        rsvp_table.setItems(rsvpList);
        }catch(Exception e){
            System.out.println("table not created");
            e.printStackTrace();
        }
        
        
       
    }
    
}
