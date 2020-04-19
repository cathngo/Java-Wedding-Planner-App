/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.IOException;
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

/**
 *
 * @author cathy
 */
public class A_EditRunsheetController {
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
    @FXML
    private Stage primaryStage;
    
    ObservableList<String>runsheet = FXCollections.observableArrayList();
    ArrayList<String> event_time = new ArrayList<String>();
    ArrayList<String> event_activity = new ArrayList<String>();
    
    private String eventName;
    private int eventId;
    
    public void getEventId(int id){
        this.eventId = id;
    }
    
    public void getEventName(String name){
                            
        this.eventName = name;
        event_name.setText(name);
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
    void btnBackWasClicked(ActionEvent  event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    void btnRunsheetsWasClicked(ActionEvent  event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("A_ViewAllRunsheets.fxml"));
        runsheetPane.getChildren().setAll(pane);
    }

    @FXML
    void btnUpdateWasClicked(ActionEvent  event) throws Exception {
        
        File file = new File(""+System.getProperty("user.dir")+File.separator+"runsheet" + eventId + ".pdf"); 
        try{  
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
        } 
        }catch(Exception e){
            
            e.printStackTrace();
        }   
        
        if (runsheetList.getItems().isEmpty()){
            String header = "Update Unsuccessful";
            String content = "Please add items to the runsheet";
            Alertbox.AlertError(header, content);
        }else if (file.delete() == false){
         String header = "Unable to edit runsheet";
          String content = "Please close runsheet if opened in another program before editing";
          Alertbox.AlertError(header, content);
        } else if (file.delete() == true){
        String header = "Update Success!";
        String content = "Runsheet was successfully updated!";
        Alertbox.AlertInfo(header, content);
        A_RunsheetPDFController.editRunsheetPDF(event_time, event_activity, eventName, eventId);
        System.out.println("btn update eventId eventName " + eventName + eventId);
        }
    }
}
