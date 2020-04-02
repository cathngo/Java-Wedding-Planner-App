/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jaydenso
 */
public class App {

    /**
     * @param args the command line arguments
     * 
     * 
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       //DatabaseManager.createEvent();
       //DatabaseManager.eventData();
      // DatabaseManager.guestData();
      //DatabaseManager.createRsvp();
       //DatabaseManager.rsvpData();
       DatabaseManager.setupDatabaseOnFirstRun();
       DatabaseManager.printInvitation();
      
    }
    
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Scene scene;

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
