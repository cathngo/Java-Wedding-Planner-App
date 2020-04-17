package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

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
    
    ObservableList<String>runsheet = FXCollections.observableArrayList();
    ArrayList<String> event_time = new ArrayList<String>();
    ArrayList<String> event_activity = new ArrayList<String>();
    
    private String eventName;
    private int eventId;
   

    
    public void passEventName(String name){
        this.eventName = name;
        event_name.setText(name);
    }
    
    public void passEventId(int id){
        this.eventId = id;
    }
    
   
    @FXML
    void btnBackWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }
    
    @FXML
    void btnAddItemsWasClicked(ActionEvent event) throws IOException {
        runsheetList.setCellFactory(param -> new ListCell<String>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                    setText(null); 
                    // other stuff to do...

                }else{

                    // set the width's
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
    void btnCreateRunsheetWasClicked(ActionEvent event) throws IOException, SQLException, Exception {
        
        
     A_RunsheetPDFController.createNewRunsheetPDF(event_time, event_activity, eventName, eventId);
            
        
        
        String header = "Runsheet Success!";
        String content = "Runsheet was successfully created!";
        Alertbox.AlertInfo(header, content);
        BLOB runsheet = new BLOB();
        runsheet.updateRunsheet(eventId, "runsheet" + eventId + ".pdf");
      
    }
    

    @FXML
    void btnInviteGuestsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_RunsheetSelectEvent.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }
    
    @FXML
    void btnRunsheetsWasClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

}
