/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author cathy
 */
public class A_ViewEventGuestListController {

    @FXML
    private PieChart pieChart;
    @FXML
    private ObservableList<PieChart.Data> pieChartData;
    @FXML
    private AnchorPane eventPane;
    @FXML
    private Text eventName;
    @FXML
    private TableView<RSVPGuestWrapper> rsvp_table;
    @FXML
    private TableColumn<RSVPGuestWrapper, String> col_guestName;
    @FXML
    private TableColumn<RSVPGuestWrapper, String> col_decision;

    ObservableList<RSVPGuestWrapper> rsvpList = FXCollections.observableArrayList();

    ArrayList<Integer> rsvpCount = new ArrayList<Integer>();
    private int eventId;

    public void getRsvpData(int id) throws SQLException {
        this.eventId = id;
        pieChartData = FXCollections.observableArrayList();

        try {
            //gets the number of RSVPs for Yes, No and Unsure
            rsvpCount = DatabaseManager.getRSVPCount(id);

            pieChartData.add(new PieChart.Data("Yes", rsvpCount.get(0)));
            pieChartData.add(new PieChart.Data("No", rsvpCount.get(1)));
            pieChartData.add(new PieChart.Data("Unsure", rsvpCount.get(2)));

            //sets the data into the piechart
            pieChart.setData(pieChartData);
            pieChart.setLabelsVisible(true);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @FXML
    private void btnViewEventsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventController controller = loader.getController();
        controller.passEventId(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    void btnEventsWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventController controller = loader.getController();
        controller.passEventId(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    private void btnInviteGuestsWasClicked(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEventInviteGuest.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventInviteGuestController controller = loader.getController();
        controller.passData(eventName.getText());
        controller.getEventId(eventId);
        eventPane.getChildren().setAll(pane);

    }

    @FXML
    private void btnBackWasClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("A_ViewEvent.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        A_ViewEventController controller = loader.getController();
        controller.passEventId(eventId);
        eventPane.getChildren().setAll(pane);
    }

    public void passEventName(String name) throws SQLException {

        eventName.setText(name);

    }

    public void getEventId(int id) {
        this.eventId = id;

        try {
            //sets the rsvp table
            rsvp_table.setItems(DatabaseManager.getRsvpGetGuest(id));
            //Reference: Stack Overflow
            col_guestName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RSVPGuestWrapper, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(
                        TableColumn.CellDataFeatures<RSVPGuestWrapper, String> p) {
                    return new SimpleStringProperty(p.getValue().getGuest_fname()
                            + " " + p.getValue().getGuest_lname());
                }
            });
            col_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}
