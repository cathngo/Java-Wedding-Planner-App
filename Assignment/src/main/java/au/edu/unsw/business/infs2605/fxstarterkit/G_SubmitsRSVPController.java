
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jaydenso
 */
public class G_SubmitsRSVPController {
    @FXML
    private AnchorPane guestPane;
    
    
    
    
    @FXML
    private void btnBackClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("G_ViewAllInvitations.fxml"));
        guestPane.getChildren().setAll(pane);

    }
    
    @FXML
    private void txtRsvpToEvents(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("G_ViewAllInvitations.fxml"));
        guestPane.getChildren().setAll(pane);

    }
    
    @FXML
    private void txtDashboardClicked(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("G_Dashboard.fxml"));
        guestPane.getChildren().setAll(pane);

    }
    

    
}
