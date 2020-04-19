/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
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
 * @author jaydenso
 */
public class A_ViewAllRunsheetsController implements Initializable {

    @FXML
    private AnchorPane runsheetPane;
    @FXML
    private TableView<Event> runsheetTable;

    @FXML
    private TableColumn<Event, String> col_eName;
    @FXML
    private TableColumn<Event, String> col_eDate;
    @FXML
    private TableColumn<Event, String> col_eStartTime;
    @FXML
    private TableColumn<Event, String> col_eEndTime;

    ObservableList<Event> runsheetList = FXCollections.observableArrayList();

    private int eventId;
    private String eventName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            runsheetTable.setItems(DatabaseManager.getEventsByRunsheet());

            col_eName.setCellValueFactory(new PropertyValueFactory<>("event_name"));
            col_eDate.setCellValueFactory(new PropertyValueFactory<>("event_date"));
            col_eStartTime.setCellValueFactory(new PropertyValueFactory<>("event_start_time"));
            col_eEndTime.setCellValueFactory(new PropertyValueFactory<>("event_end_time"));

        } catch (Exception e) {
            System.out.println("table not created");
            e.printStackTrace();

        }

    }

    @FXML
    public void btnCreateRunsheetWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnViewRunsheetWasClicked(ActionEvent event) throws IOException {
        Event selectedEvent = runsheetTable.getSelectionModel().getSelectedItem();
        eventId = selectedEvent.getEvent_id();
      File dest = new File(""+System.getProperty("user.dir")+"\\runsheet" + eventId + ".pdf");
       DetectOS.open(dest);
        
    }
    
    @FXML
    public void btnEditRunsheetWasClicked(ActionEvent event) throws IOException {

        try {
            Event selectedEvent = runsheetTable.getSelectionModel().getSelectedItem();
            eventId = selectedEvent.getEvent_id();
            eventName = selectedEvent.getEvent_name();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("A_EditRunsheet.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            A_EditRunsheetController controller = loader.getController();
            controller.getEventName(eventName);
            controller.getEventId(eventId);
            runsheetPane.getChildren().setAll(pane);
            
            System.out.println("btnEditRunsheet eventId, eventName " + eventId + eventName);

            System.out.println("invite guests event id:" + eventId);
        } catch (Exception e) {
            String header = "Unable to edit runsheet";
            String content = "Please select a runsheet from the table";
            Alertbox.AlertError(header, content);
        }

    }
    
   
}
