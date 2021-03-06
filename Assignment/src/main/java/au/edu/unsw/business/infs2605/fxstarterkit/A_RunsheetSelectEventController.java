
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author cathy
 */
public class A_RunsheetSelectEventController implements Initializable {

    @FXML
    private TableView<Event> eventTable;
    @FXML
    private TableColumn<Event, Integer> col_eId;
    @FXML
    private TableColumn<Event, String> col_eName;
    @FXML
    private TableColumn<Event, String> col_eDate;
    @FXML
    private TableColumn<Event, String> col_eStartTime;
    @FXML
    private TableColumn<Event, String> col_eEndTime;
    @FXML
    private AnchorPane runsheetPane;

    ObservableList<Event> eventList = FXCollections.observableArrayList();

    private int eventId;

    private String eventName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //sets tableview of events
            eventTable.setItems(DatabaseManager.getEvents());

            col_eId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
            col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));

        } catch (Exception e) {
            System.out.println("table not created");
        }

    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnRunsheetsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnNextWasClicked(ActionEvent event) throws IOException {
        try {
            Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
            eventId = selectedEvent.getEvent_id();
            eventName = selectedEvent.getEvent_name();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_CreateRunsheet.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_CreateRunsheetController controller = loader.getController();
            
            controller.passEventId(eventId);
            controller.passEventName(eventName);
            runsheetPane.getChildren().setAll(pane);
        } catch (Exception e) {
            String header = "Unable to proceed";
            String content = "Please select an event from the table";
            Alertbox.AlertError(header, content);
        }

    }
}
