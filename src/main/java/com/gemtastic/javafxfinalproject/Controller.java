package com.gemtastic.javafxfinalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Aizic Moisen
 */
public class Controller implements Initializable {
    
    private int carSpeed;
    private int result;
    private int reactionTime = 10;
    private int kmToM = 36;
    private int friction = 1;
    private int reactionDistance,
                BreakingDistance;
    private int magicNumber = 2500;
    
    private int ten = 10;
    
    private DropShadow exitHighlight;
    private Tooltip exitTip;

    @FXML
    private ImageView exitButton;
    
    @FXML
    private Label answer;
    
    @FXML
    private TextField speed;
    
    @FXML
    private Label errorMessage;
    @FXML
    private Label beSafe;
    
    
    @FXML
    private void handleButtonClicked(){
        
        if (speed.getText().isEmpty()) {
            System.out.println("Is empty!");
            errorMessage.setVisible(true);
        } 
        else if (!speed.getText().isEmpty()) {
            try {
                carSpeed = Integer.parseInt(speed.getText());
                errorMessage.setVisible(false);
            } 
            catch (NumberFormatException e) {
                System.out.println("Oops not numbers only!");
                errorMessage.setVisible(true);
            }
        }
            
        if(carSpeed > 0 && carSpeed <= 120){
            int calcSpeed = carSpeed * ten;
            reactionDistance = (calcSpeed * reactionTime) / kmToM;

            int breakSpeed = carSpeed * ten;
            BreakingDistance = ((breakSpeed * breakSpeed)/(magicNumber * friction))/ ten;

            result = reactionDistance + BreakingDistance;

            answer.setText("It will take about "+ result +" meters \nbefore you've stopped.");
            answer.setVisible(true);
            beSafe.setVisible(true);
        }
        if(carSpeed > 120){
            System.out.println("This isn't autobahn!");
            errorMessage.setVisible(true);
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        setExitButton();
        
        
    }

    private void setExitButton() {
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
            
        });
        
        exitButton.setOnMouseEntered(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                exitHighlight = new DropShadow(9.0, Color.RED);
                exitTip = new Tooltip("Exit application");
                Tooltip.install(exitButton, exitTip);
                exitButton.setEffect(exitHighlight);
            }
        
        });
        
        exitButton.setOnMouseExited(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                exitHighlight.setRadius(0.0);
            }
            
        });
    }
    
}
