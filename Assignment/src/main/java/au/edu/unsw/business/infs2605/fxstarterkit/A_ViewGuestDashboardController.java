
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
public class A_ViewGuestDashboardController implements Initializable {


   
    @FXML
    private Button btnInviteGuest;
    @FXML
    private Button btnCreateNewGuest;
    @FXML
    private Button btnViewGuests;
    @FXML
    private AnchorPane guestsPane;
      @FXML
    private TableView<Guest> guest_table;
   @FXML
    private TableColumn<Guest,Integer> col_guestId;
    @FXML
    private TableColumn<Guest,String> col_firstName;
   @FXML
    private TableColumn<Guest,String> col_lastName;
    @FXML
    private TableColumn<Guest,String> col_guestEmail;
   
    ObservableList<Guest>guestList = FXCollections.observableArrayList();
    
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from guest");

            while (rs.next()) {
                guestList.add(new Guest(rs.getString("guest_fname"),
                        rs.getString("guest_lname"), rs.getString("guest_email"),
                        rs.getInt("guest_id")));
            }
            col_firstName.setCellValueFactory(new PropertyValueFactory<>("guest_fname"));
            col_lastName.setCellValueFactory(new PropertyValueFactory<>("guest_lname"));
            col_guestEmail.setCellValueFactory(new PropertyValueFactory<>("guest_email"));
             col_guestId.setCellValueFactory(new PropertyValueFactory<>("guest_id"));
            guest_table.setItems(guestList);
        } catch (Exception e) {
            System.out.println("table not created");
        }

    }
        
   
    /**
     * Initializes the controller class.
     */   
    
    @FXML
    private void loadInviteGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewGuestInviteEvent.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadCreateGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_CreateGuest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadViewGuest(ActionEvent event) throws IOException, SQLException {
        
        
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        A_ViewGuestProfileController controller = loader.getController();
        controller.passData(guest_table.getSelectionModel().getSelectedItem());
        guestsPane.getChildren().setAll(pane);
    }
    
       

}
