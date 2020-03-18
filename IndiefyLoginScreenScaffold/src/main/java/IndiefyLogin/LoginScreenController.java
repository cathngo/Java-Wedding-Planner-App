/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author jacob
 */
public class LoginScreenController {

    //Initiate JavaFX nodes (visual elements), how do we connect these variables to the FXML view?

    // Initiate the database class

    /* What should happen when you click the login button?
       How do we connect this function to the FXML view? */
    private void handleLoginButtonAction(ActionEvent event) {
        
        // Get the user's input from the GUI

        if (d.tryLogin(user, password)) {
          // What should the user see when the login is successful?
        } else {
          // What should the user see when the login is unsuccessful?
        }
    }

    @FXML
    public void initialize() {
       // What should the user see when the screen loads?
    }

}
