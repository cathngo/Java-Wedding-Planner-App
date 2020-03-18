/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    /* What should happen when you first start the program? */
    @Override
    public void start(Stage stage) throws Exception {
        //Initiate the database
        loadDatabase();
        /* Add the four lines of code to load an FXML into a scene, attach it to a stage, 
        and show the stage */
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.show();
 
    }
    

    public static void main(String[] args) {
        launch(args);
    }

    private void loadDatabase() {
       //What method should we be calling from the Database class?
       DatabaseLoader.openConnection();

    }
    


}
