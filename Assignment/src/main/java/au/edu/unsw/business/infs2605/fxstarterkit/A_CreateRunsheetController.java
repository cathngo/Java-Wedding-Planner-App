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
        
        //create pdf document
     
        
        File source = new File(""+System.getProperty("user.dir")+"\\src\\main\\resources\\au\\edu\\unsw\\business\\infs2605\\fxstarterkit\\images\\event_runsheet.pdf");
        File dest = new File(""+System.getProperty("user.dir")+"\\runsheet" + eventId + ".pdf");
        Files.copy(source.toPath(), dest.toPath());
        PDDocument doc = PDDocument.load(dest);
        PDPage page = doc.getPage(0);
        
        PDFont edoFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File (""+System.getProperty("user.dir")+"\\src\\main\\resources\\edo.ttf")));
        PDFont JSFont = PDTrueTypeFont.loadTTF(doc, new FileInputStream(new File (""+System.getProperty("user.dir")+"\\src\\main\\resources\\JosefinSans-Light.ttf")));
        PDPageContentStream contentStream = new PDPageContentStream(doc, page,true,true,true);
        
        //event name font
        contentStream.beginText();
        contentStream.setFont(edoFont,45);
        contentStream.setNonStrokingColor(249,193,118);
        contentStream.newLineAtOffset(67, 650);
        contentStream.setLeading(18f);
        contentStream.showText(eventName);
        contentStream.newLine();
        //description black font
        
        contentStream.setLeading(20);
        contentStream.newLine();
        contentStream.setFont(JSFont, 14);
        contentStream.setNonStrokingColor(0, 0, 0);
       
     //wrap text
    

        for (int i = 0; i < event_time.size(); i++) {
        A_InvitationPDFController.insertTextColumn(contentStream, event_time.get(i) + ": " + event_activity.get(i), JSFont, 14, 750, 14);
        //contentStream.showText(event_time.get(i) + ": " + event_activity.get(i));
        contentStream.newLine();
        }

        //Ending the content stream
        contentStream.endText();
        
       

        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();
       

        //Saving the document
        doc.save(dest);
       
        //Closing the document
        doc.close();
        System.out.println("successfully printed pdf");
        
        //BLOB runsheet = new BLOB();
        //runsheet.updateRunsheet(eventId, "runsheet" + eventId + ".pdf");
      
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
