package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class A_CreateRunsheetController {

    @FXML
    private AnchorPane runsheetPane;
    @FXML
    private Text event_name;
    @FXML
    private TextField time;
    @FXML
    private TextField activity;
    @FXML
    private ListView<String> runsheetList;

    ObservableList<String> runsheet = FXCollections.observableArrayList();
    ArrayList<String> event_time = new ArrayList<String>();
    ArrayList<String> event_activity = new ArrayList<String>();

    private String eventName;
    private int eventId;

    public void passEventName(String name) {
        this.eventName = name;
        event_name.setText(name);
    }

    public void passEventId(int id) {
        this.eventId = id;
    }

    @FXML
    public void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnAddItemsWasClicked(ActionEvent event) throws IOException {
        //Reference: StackOverflow
        runsheetList.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);

                } else {
                    setMinWidth(param.getWidth());
                    setMaxWidth(param.getWidth());
                    setPrefWidth(param.getWidth());
                    // allow wrapping
                    setWrapText(true);
                    setText(item.toString());
                }
            }
        });

        runsheet.add(time.getText() + ":  " + activity.getText());
        runsheetList.setItems(runsheet);
        event_time.add(time.getText());
        event_activity.add(activity.getText());
    }

    @FXML
    public void btnCreateRunsheetWasClicked(ActionEvent event) throws IOException, SQLException, Exception {
        //alertbox error is runsheet list is empty
        if (runsheetList.getItems().isEmpty()) {
            String header = "Unable to create runsheet ";
            String content = "Please add items to the runsheet";
            Alertbox.AlertError(header, content);
        } else {
            try {

                //creates runsheet PDF    
                A_RunsheetPDFController.createNewRunsheetPDF(event_time, event_activity, eventName, eventId);
                //alertbox if runsheet suncessfully created
                String header = "Runsheet Success!";
                String content = "Runsheet was successfully created!";
                Alertbox.AlertInfo(header, content);
                //stores the runsheet into the database
                BLOB runsheet = new BLOB();
                runsheet.updateRunsheet(eventId, "runsheet" + eventId + ".pdf");
            } catch (Exception e) {
                //alertbox error is runsheet already exists
                String header = "Unable to create Runsheet";
                String content = "Runsheet already exists";
                Alertbox.AlertError(header, content);
            }
        }
    }

    @FXML
    public void btnInviteGuestsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    public void btnRunsheetsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }
}
