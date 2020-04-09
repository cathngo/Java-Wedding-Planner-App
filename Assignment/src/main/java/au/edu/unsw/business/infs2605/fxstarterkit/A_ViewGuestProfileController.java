
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author cathy
 */
public class A_ViewGuestProfileController {

    
    @FXML
    private Text guestName;
    @FXML
    private Text guestId;
    @FXML
    private Text guestEmail;
    @FXML
    private Text guestAccessCode;
    @FXML
    private Text guestGender;
    @FXML
    private Text guestPhone;
    private Guest selectedGuest;
    
    

    public void passData(Guest guest) throws SQLException {
        selectedGuest = guest;
        

        Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM guest where guest_id =  " + selectedGuest.getGuest_id());

        while (rs.next()) {
            String fname = rs.getString("guest_fname");
            String lname = rs.getString("guest_lname");
            String id = Integer.toString(rs.getInt("guest_id"));
            String code = rs.getString("guest_access_code");
            String email = rs.getString("guest_email");
            String phone = rs.getString("guest_phone");
            String gender = rs.getString("guest_gender");
            
           
     
            guestName.setText(fname + " " + lname);
            guestId.setText(id);
            guestEmail.setText(email);
            guestPhone.setText(phone);
            guestGender.setText(gender);
            guestAccessCode.setText(code);
           
        }
        
       
    }

}    
