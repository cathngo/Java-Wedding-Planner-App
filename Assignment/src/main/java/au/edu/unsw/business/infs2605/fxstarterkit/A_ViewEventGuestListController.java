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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 *
 * @author cathy
 */
public class A_ViewEventGuestListController implements Initializable{
    
    @FXML
    private PieChart pieChart;
    @FXML
    private ObservableList<PieChart.Data> pieChartData;

       public void getRsvpData() throws SQLException {
        pieChartData = FXCollections.observableArrayList();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) FROM rsvp where decision = 'Yes' ");

            ResultSet rs2 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) FROM rsvp where decision = 'No' ");

            ResultSet rs3 = conn.createStatement().executeQuery("SELECT COUNT(rsvp_id) FROM rsvp where decision = '' ");

            pieChartData.add(new PieChart.Data("Yes", rs1.getInt("COUNT(rsvp_id)")));
            pieChartData.add(new PieChart.Data("No", rs2.getInt("COUNT(rsvp_id)")));
           pieChartData.add(new PieChart.Data("Unsure", rs3.getInt("COUNT(rsvp_id)")));
          
       ;


        } catch (Exception e) {
            System.out.println("unsuccessful");
            e.printStackTrace();
        }
        

    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            getRsvpData();
        } catch (SQLException ex) {
            Logger.getLogger(A_ViewEventGuestListController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       pieChart.setData(pieChartData);
       pieChart.setLabelsVisible(true);
      
        
        
      
        
    }
    
}
