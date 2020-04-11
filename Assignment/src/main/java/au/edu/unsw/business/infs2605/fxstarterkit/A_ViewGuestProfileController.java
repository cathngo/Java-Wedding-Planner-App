
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
    @FXML
    private AnchorPane guestsPane;
    @FXML
    private TableView<RSVPEventWrapper> event_details_table;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventName;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventDate;
    @FXML
    private TableColumn<RSVPEventWrapper, String> col_eventRsvp;

    ObservableList<RSVPEventWrapper> eventDetailsList = FXCollections.observableArrayList();

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

    public void getGuestId(Guest guest) throws SQLException {
        selectedGuest = guest;

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("SELECT e.event_name, e.event_date, r.decision "
                    + "FROM event e "
                    + "JOIN invitation i ON e.event_id = i.event_id "
                    + "JOIN rsvp r ON r.invitation_id = i.invitation_id "
                    + "JOIN guest g ON g.guest_id = i.guest_id "
                    + "WHERE g.guest_id = '" + selectedGuest.getGuest_id() + "'");

            while (rs.next()) {
                eventDetailsList.add(new RSVPEventWrapper(rs.getString("event_name"),
                        rs.getString("event_date"), rs.getString("decision")));
            }
            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

            event_details_table.setItems(eventDetailsList);
        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnInviteGuestWasClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestInviteEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewGuestInviteEventController controller = loader.getController();
        controller.passGuestName(guestName.getText());
        guestsPane.getChildren().setAll(pane);
    }

    @FXML
    private void btnEditGuestWasClicked(ActionEvent event) throws IOException, SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EditGuest.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_EditGuestController controller = loader.getController();
            controller.passData(Integer.parseInt(guestId.getText()));
            controller.getGuestId(Integer.parseInt(guestId.getText()));
            guestsPane.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
