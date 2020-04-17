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
    private AnchorPane guestsPane;
    @FXML
    private TableView<Guest> guest_table;
    @FXML
    private TableColumn<Guest, Integer> col_guestId;
    @FXML
    private TableColumn<Guest, String> col_firstName;
    @FXML
    private TableColumn<Guest, String> col_lastName;
    @FXML
    private TableColumn<Guest, String> col_guestEmail;
    @FXML
    private int guestId;
    private String guestName;

    ObservableList<Guest> guestList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            guest_table.setItems(DatabaseManager.getGuests());
            col_firstName.setCellValueFactory(new PropertyValueFactory<>("guest_fname"));
            col_lastName.setCellValueFactory(new PropertyValueFactory<>("guest_lname"));
            col_guestEmail.setCellValueFactory(new PropertyValueFactory<>("guest_email"));
            col_guestId.setCellValueFactory(new PropertyValueFactory<>("guest_id"));

        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void loadInviteGuest(ActionEvent event) throws IOException, SQLException {
        try {
            Guest selectedGuest = guest_table.getSelectionModel().getSelectedItem();
            guestId = selectedGuest.getGuest_id();
            String guestFname = selectedGuest.getGuest_fname();
            String guestLname = selectedGuest.getGuest_lname();
            guestName = guestFname + " " + guestLname;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_GuestsInviteEvent.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_GuestsInviteEventController controller = loader.getController();
            controller.passGuestName(guestName);
            controller.getGuestId(guestId);
            guestsPane.getChildren().setAll(pane);

            System.out.println("load invite guest guest id: " + guestId + " guest name:" + guestName);
        } catch (Exception e) {
            String header = "Unable to invite guest to an event";
            String content = "Please select a guest from the table 'Guests'";
            Alertbox.AlertError(header, content);
        }
    }

    @FXML
    private void loadCreateGuest(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_CreateGuest.fxml"));
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadViewGuest(ActionEvent event) throws IOException, SQLException {
        try {
            Guest selectedGuest = guest_table.getSelectionModel().getSelectedItem();
            guestId = selectedGuest.getGuest_id();
            String guestFname = selectedGuest.getGuest_fname();
            String guestLname = selectedGuest.getGuest_lname();
            guestName = guestFname + " " + guestLname;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_ViewGuestProfileController controller = loader.getController();
            controller.loadGuestData(guestId);
            controller.passGuestName(guestName);
            controller.getGuestId(guestId);
            guestsPane.getChildren().setAll(pane);

            System.out.println("load view guest guest id: " + guestId + " guest name:" + guestName);
        } catch (Exception e) {
            String header = "Unable to view details of guest";
            String content = "Please select a guest from the table 'Guests'";
            Alertbox.AlertError(header, content);
        }

    }
}
