package com.gemtastic.javafxfinalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This is my simple application for calculating how many meters a car
 * will travel when you slam the breaks on an icy road.
 * 
 * @author Aizic Moisen
 */
public class Start extends Application{
    
    public static void main(String... ignored){
        launch(ignored);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(Start.class.getResource("/com/gemtastic/fxml/Frame.fxml"));
        loader.setController(new Controller());
        
        AnchorPane root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/com/gemtastic/styles/frame.css");
        scene.setFill(null);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        
    }
    
}
