package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.File;
import java.io.IOException;
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
    void btnCreateRunsheetWasClicked(ActionEvent event) throws IOException {
        
        //create pdf document
     
        PDDocument document = new PDDocument();
        
        //create page
        PDPage my_page = new PDPage();
        document.addPage(my_page);
        document.save("C:\\Users\\cathy\\OneDrive\\Desktop\\test\\runsheet" + eventId + ".pdf");
        document.close();
        //load document
        File file = new File("C:\\Users\\cathy\\OneDrive\\Desktop\\test\\runsheet" + eventId + ".pdf"); 
        PDDocument doc = PDDocument.load(file);
        //get page
        PDPage page = doc.getPage(0);
        //preparing content stream
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        contentStream.beginText();
        //set font
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        //setting position for line
        contentStream.newLineAtOffset(25, 500);
        //set leading
        contentStream.setLeading(14.5f);
      
    
        contentStream.showText("Runsheet for: " + eventName);
        contentStream.newLine();
        
        for (int i = 0; i < event_time.size(); i++){
        contentStream.showText(event_time.get(i) + ": " + event_activity.get(i));
        contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();

        //Saving the document
        doc.save(new File(("C:\\Users\\cathy\\OneDrive\\Desktop\\test\\runsheet" + eventId + ".pdf")));

        //Closing the document
        doc.close();
        System.out.println("successfully printed pdf");
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
