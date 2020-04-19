package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author cathy
 */
public class A_ViewEventInviteGuestController implements Initializable {

    @FXML
    private TableView<Guest> existingGuestTable;

    @FXML
    private TableColumn<Integer, String> col_guestId;
    @FXML
    private TableColumn<Guest, String> col_guestName;
    @FXML
    private Text eventName;

    @FXML
    private AnchorPane eventPane;

    @FXML
    private ListView<String> guestListView;

    private int eventId;

    private String event_name;

    ObservableList<Guest> guestList = FXCollections.observableArrayList();
    ObservableList<String> newGuestList = FXCollections.observableArrayList();
    ArrayList<Integer> guestId = new ArrayList<Integer>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //sets tableview of existing guests
            existingGuestTable.setItems(DatabaseManager.getGuests());

            existingGuestTable.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
                Node node = evt.getPickResult().getIntersectedNode();

                while (node != null && node != existingGuestTable && !(node instanceof TableRow)) {
                    node = node.getParent();
                }

                if (node instanceof TableRow) {

                    evt.consume();
                    TableRow row = (TableRow) node;
                    TableView tv = row.getTableView();

                    tv.requestFocus();

                    if (!row.isEmpty()) {

                        int index = row.getIndex();
                        if (row.isSelected()) {
                            tv.getSelectionModel().clearSelection(index);
                        } else {
                            tv.getSelectionModel().select(index);
                        }
                    }
                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        }

        col_guestId.setCellValueFactory(new PropertyValueFactory<>("guest_id"));
        //Reference: Stack Overflow
        col_guestName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Guest, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    TableColumn.CellDataFeatures<Guest, String> p) {
                return new SimpleStringProperty(p.getValue().getGuest_fname()
                        + " " + p.getValue().getGuest_lname());
            }
        });

        existingGuestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    void btnAddToListWasClicked(ActionEvent event) {

        try {
            //alertbox error is guest is not selected
            if (existingGuestTable.getSelectionModel().isEmpty()) {
                String header = "Unable to add to guest list";
                String content = "Please select existing guests first!";
                Alertbox.AlertError(header, content);
            } else {
                //adds selected guests into the guest list
                for (TablePosition<Guest, ?> pos : existingGuestTable.getSelectionModel().getSelectedCells()) {

                    int row = pos.getRow();
                    Guest data = existingGuestTable.getItems().get(row);
                    String fname = data.getGuest_fname();
                    String lname = data.getGuest_lname();
                    int id = data.getGuest_id();
                    guestId.add(id);
                    newGuestList.add(fname + " " + lname);
                    guestListView.setItems(newGuestList);

                }
            }
        } catch (Exception e) {

        }

    }

    public void getEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventGuestList.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventGuestListController controller = loader.getController();
        controller.passEventName(event_name);
        controller.getEventId(eventId);
        controller.getRsvpData(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnGuestListWasClicked(ActionEvent event) throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventGuestList.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventGuestListController controller = loader.getController();
        controller.passEventName(event_name);
        controller.getEventId(eventId);
        controller.getRsvpData(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    private void btnViewEventsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
                
        A_ViewEventController controller = loader.getController();
        controller.passEventId(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnInviteNewGuestWasClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventInviteNewGuest.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventInviteNewGuestController controller = loader.getController();
        controller.getEventId(eventId);
        controller.passEventName(event_name);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    public void btnEventsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllEvents.fxml"));
        eventPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnInviteToEventWasClicked(ActionEvent event) throws SQLException {

        try {
            if (guestListView.getItems().isEmpty()) {
                //alertbox if guest list is empty
                String header = "Invite unsuccessful";
                String content = "Please select and add existing guests to guest list first!";
                Alertbox.AlertError(header, content);
                System.out.println("unable to invite");

            } else {
                //invite guest to event
                DatabaseManager.inviteGuest(guestId, eventId);
                //alertbox if invite was successful
                String header = "Invite success!";
                String content = "Guests have been successfully invited to event";
                Alertbox.AlertInfo(header, content);
            }
        } catch (Exception e) {
            //alertbox is invit was unsuccessful
            String header = "Invite unsuccessful";
            String content = "Please select and add existing guests to guest list first!";
            Alertbox.AlertError(header, content);

            e.printStackTrace();

        }

    }

    public void passData(String name) throws SQLException {
        this.event_name = name;
        eventName.setText(name);

    }

    //method to remove guest from guest list
    @FXML
    public void btnRemoveWasClicked(ActionEvent event) {
        int index = guestListView.getSelectionModel().getSelectedIndex();
        guestListView.getItems().remove(index);
        guestId.remove(index);
    }
}
