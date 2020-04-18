
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author cathy
 */
public class Alertbox {
    
    public static void AlertError(String header, String content){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }
    
    public static void AlertInfo(String header, String content){
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();
    }
}
