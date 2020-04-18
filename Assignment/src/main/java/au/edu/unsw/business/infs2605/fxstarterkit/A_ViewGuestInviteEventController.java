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
public class A_ViewGuestInviteEventController implements Initializable {

    @FXML
    private TableView<Event> viewGuestTable;
    @FXML
    private TableColumn<Event, Integer> col_eventId;
    @FXML
    private TableColumn<Event, String> col_eventName;
    @FXML
    private TableColumn<Event, String> col_eventDate;
    @FXML
    private TableColumn<Event, String> col_startTime;
    @FXML
    private TableColumn<Event, String> col_endTime;
    @FXML
    private Text guestName;
    @FXML
    private AnchorPane guestsPane;

    private int guestId;
    private String guest_name;

    ObservableList<Event> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            viewGuestTable.setItems(DatabaseManager.getEvents());
            col_eventId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
            col_eventName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eventDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_startTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_endTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));
        } catch (Exception e) {
            System.out.println("table not created");
        }

    }

    public void passGuestName(String name) throws SQLException {
        this.guest_name = name;
        guestName.setText(name);
        System.out.println("passguestName" + name);

    }

    public void getGuestId(int id) {
        this.guestId = id;
        System.out.println("getguestId " + id);
    }

    @FXML
    public void btnInviteToEventWasClicked(ActionEvent event) throws SQLException {
        try {
            int eventId = viewGuestTable.getSelectionModel().getSelectedItem().getEvent_id();

            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            int rs = conn.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '" + eventId + "', '" + guestId + "','" + LoginController.adminUser.getAdmin_id() + "' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='" + eventId + "' AND guest_id ='" + guestId + "')");

            conn.close();
            String header = "Invite Success";
            String content = "Guest successfully invited to event!";
            Alertbox.AlertInfo(header, content);
            System.out.println("succesfully updated");
            System.out.println("invitetoevent event id, guestid, admin id" + eventId + guestId + LoginController.adminUser.getAdmin_id());

        } catch (Exception e) {
            String header = "Invite Unsuccessful";
            String content = "Please select an event from the table first";
            Alertbox.AlertError(header, content);
            System.out.println("unsuccessful");
            System.out.println("eventId:" + viewGuestTable.getSelectionModel().getSelectedItem().getEvent_id());
            System.out.println("guestid:" + guestId);
            System.out.println(LoginController.adminUser.getAdmin_id());
            e.printStackTrace();

        }
    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewGuestProfileController controller = loader.getController();
        controller.loadGuestData(guestId);
        controller.getGuestId(guestId);
        controller.passGuestName(guest_name);
        guestsPane.getChildren().setAll(pane);

        System.out.println("btn back guestId" + guestId);
    }

    @FXML
    public void btnViewGuestWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestProfile.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        A_ViewGuestProfileController controller = loader.getController();
        controller.loadGuestData(guestId);
        controller.getGuestId(guestId);
        controller.passGuestName(guest_name);
        guestsPane.getChildren().setAll(pane);

        System.out.println("btn back guestId" + guestId);
    }

    @FXML
    public void btnGuestsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewGuestDashboard.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        guestsPane.getChildren().setAll(pane);

    }

}
