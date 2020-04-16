/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */

//REMINDER TO MAKE THE README
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
    
        
        A_InvitationPDFController.createNewInvPDF();
        
        scene = new Scene(loadFXML("Login"));

        stage.setScene(scene);
        stage.show();
        DatabaseManager.setupDatabaseOnFirstRun();
        //use this for testing and viewing table content
        DatabaseManager.printObjectsInTable("event");
        DatabaseManager.printObjectsInTable("admin");
        DatabaseManager.printObjectsInTable("guest");
        
        System.out.println(System.getProperty("user.dir"));
 
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {  
        launch();
    }

}