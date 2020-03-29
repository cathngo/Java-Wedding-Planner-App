/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiefyLogin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author jacob
 */
public class LoginScreenController {

    @FXML
    private Label loginOutput;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pword;
    
    @FXML
    private Button nextBtn;

    Database d = new Database();
    
    PageSwitchHelper pageSwitcher = new PageSwitchHelper();

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String user = username.getText().trim();
        String password = pword.getText();
        try {
            ResultSet rs = d.getResultSet("SELECT * FROM LOGIN WHERE "
                    + "USERNAME = '" + user + "' "
                    + "AND PASSWORD = '" + password + "'");
            if (!rs.next()) {
                loginOutput.setText("Incorrect username or password");
                loginOutput.setVisible(true);
            } else {
                loginOutput.setText("Login successful");
                loginOutput.setVisible(true);
                nextBtn.setVisible(true);
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    private void handleNextButtonAction(ActionEvent event) throws IOException {
        pageSwitcher.switcher(event, "MusicList.fxml");
    }

    @FXML
    public void initialize() {
        System.out.println("calling init");
        loginOutput.setVisible(false);
        nextBtn.setVisible(false);
    }

}
