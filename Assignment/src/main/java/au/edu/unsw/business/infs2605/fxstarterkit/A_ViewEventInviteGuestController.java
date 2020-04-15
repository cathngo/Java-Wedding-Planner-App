
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    public class A_ViewEventInviteGuestController implements Initializable{
     @FXML
    private TableView<Guest> existingGuestTable;
   
    @FXML
    private TableColumn<Integer,String> col_guestId;
    @FXML
    private TableColumn<Guest,String> col_guestName;
    @FXML
    private Text eventName;
   
    @FXML
    private AnchorPane eventPane;
    
    @FXML
    private ListView<String> guestListView;
    
    private int eventId;
    
    private String event_name;

     


   
    ObservableList<Guest>guestList = FXCollections.observableArrayList();
    ObservableList<String>newGuestList = FXCollections.observableArrayList();
    ArrayList<Integer> guestId = new ArrayList<Integer>();
    

  
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            ResultSet rs = conn.createStatement().executeQuery("select * from guest");
            
            while (rs.next()){
                guestList.add(new Guest(rs.getInt("guest_id"),rs.getString("guest_fname"),
              rs.getString("guest_lname")));
           
            }
            
            conn.close();
            rs.close();
            
            existingGuestTable.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            // go up from the target node until a row is found or it's clear the
            // target node wasn't a node.
            while (node != null && node != existingGuestTable && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            // if is part of a row or the row,
            // handle event instead of using standard handling
            if (node instanceof TableRow) {
                // prevent further handling
                evt.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                // focus the tableview
                tv.requestFocus();

                if (!row.isEmpty()) {
                    // handle selection for non-empty nodes
                    int index = row.getIndex();
                    if (row.isSelected()) {
                        tv.getSelectionModel().clearSelection(index);
                    } else {
                        tv.getSelectionModel().select(index);
                    }
                }
            }
        });
            
        }catch(Exception e){
            System.out.println("table not created");
            e.printStackTrace();
        }
        
        
        
        col_guestId.setCellValueFactory(new PropertyValueFactory<>("guest_id"));
        col_guestName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Guest, String>, ObservableValue<String>>() {
                 @Override
                 public ObservableValue<String> call(
                         TableColumn.CellDataFeatures<Guest, String> p) {
                     return new SimpleStringProperty(p.getValue().getGuest_fname()
                             + " " + p.getValue().getGuest_lname());
                 }
             });
        existingGuestTable.setItems(guestList);
        existingGuestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
    }
    
    @FXML
    void btnAddToListWasClicked(ActionEvent event) {
        
        for (TablePosition<Guest, ?> pos : existingGuestTable.getSelectionModel().getSelectedCells()) {

            int row = pos.getRow();
            Guest data = existingGuestTable.getItems().get(row);
            String fname = data.getGuest_fname();
            String lname = data.getGuest_lname();
            int id = data.getGuest_id();
            guestId.add(id);
            newGuestList.add(fname + " " + lname);
            guestListView.setItems(newGuestList);
            
            // etc etc etc
        }
    }
    
    public void getEventId(int id){
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
     private void btnViewEventsWasClicked(ActionEvent event) throws IOException, SQLException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        A_ViewEventController controller = loader.getController();
        controller.passEventId(eventId);
        eventPane.getChildren().setAll(pane);
     }
     
    
    @FXML
    public void btnInviteNewGuestWasClicked(ActionEvent event) throws IOException{
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
        
        try{
        for (int i = 0; i < guestId.size(); i++){
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
         int rs = conn.createStatement().executeUpdate("INSERT INTO invitation(event_id, guest_id, admin_id) SELECT '"+eventId+"', '"+guestId.get(i)+"','"+LoginController.adminUser.getAdmin_id()+"' WHERE NOT EXISTS(SELECT 1 FROM invitation WHERE event_id ='"+eventId+"' AND guest_id ='"+guestId.get(i)+"')");
         
         
             conn.close();
         
         System.out.println("succesfully updated");
        }
        }catch (Exception e){
            System.out.println("unable to invite");
            e.printStackTrace();
        }
       
    }
    
    
   public void passData(String name) throws SQLException {
       this.event_name = name;
        eventName.setText(name);
        
    }
   
   
}
