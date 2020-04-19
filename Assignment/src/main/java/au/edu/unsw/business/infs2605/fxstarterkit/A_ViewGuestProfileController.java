
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private int guest_id;
    private String guest_name;

    public void loadGuestData(int id) throws SQLException {
        this.guest_id = id;
        //gets guest by guest id
        Guest myGuest = DatabaseManager.getGuestByGuestId(id);
        //loads information of guest
        guestName.setText(myGuest.getGuest_fname() + " " + myGuest.getGuest_lname());
        guestId.setText(Integer.toString(id));
        guestEmail.setText(myGuest.getGuest_email());
        guestPhone.setText(myGuest.getGuest_phone());
        guestGender.setText(myGuest.getGuest_gender());
        guestAccessCode.setText(myGuest.getGuest_access_code());

    }

    public void getGuestId(int id) throws SQLException {
        this.guest_id = id;
        try {
            //sets tableview of events and rsvp for a certain guest
            event_details_table.setItems(DatabaseManager.getRsvpGetEvent(id));
            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eventRsvp.setCellValueFactory(new PropertyValueFactory<>("decision"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void passGuestName(String name) {
        this.guest_name = name;

    }

    @FXML
    private void btnInviteGuestWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestInviteEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewGuestInviteEventController controller = loader.getController();
        controller.passGuestName(guest_name);
        controller.getGuestId(guest_id);

        guestsPane.getChildren().setAll(pane);

    }

    @FXML
    private void btnEditGuestWasClicked(ActionEvent event) throws IOException, SQLException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EditGuest.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();

            A_EditGuestController controller = loader.getController();
            controller.loadGuestData(guest_id);
            controller.getGuestId(guest_id);
            controller.getGuestName(guest_name);
            guestsPane.getChildren().setAll(pane);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void btnGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestDashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        guestsPane.getChildren().setAll(pane);

    }

}
