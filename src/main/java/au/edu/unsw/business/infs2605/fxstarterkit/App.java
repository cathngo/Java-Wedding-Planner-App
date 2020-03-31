package au.edu.unsw.business.infs2605.fxstarterkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * JavaFX App
 */
public class App extends Application {
    private double angle = 0;
    
    @Override
    public void start(Stage stage){
        VBox vb = new VBox(); //Created a vbox
        Rectangle rect = new Rectangle(30, 30, 30, 60); //Created a rectangle
        rect.setFill(Color.AQUAMARINE); //filled the rectangle in aqua
        rect.setStroke(Color.BLACK); //set the outline to black
        
        Button btn = new Button("Rotate"); //created a button with text "Rotate"
        
        btn.setOnAction(e -> {
            angle += 15; //adds 15 to the var angle
            rect.setRotate(angle); //sets the rectangle to whatever angle is in angle
        });
        
        vb.getChildren().add(rect); //add rect to vbox
        vb.getChildren().add(btn); //add btn to vbox
        
        Scene scene = new Scene (vb, 200, 150); //creates the scene
        stage.setTitle("Rectangle Rotate"); //sets the title of the window
        stage.setScene(scene); //puts the scene on the stage
        stage.show(); //shows the stage on your computer

    }

}