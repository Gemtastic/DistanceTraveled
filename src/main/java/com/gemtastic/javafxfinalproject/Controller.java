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
 * This is the controller for the application and also the place where
 * the magic happens in terms of the application's purpose.
 * 
 * @author Aizic Moisen
 */
public class Controller implements Initializable {
    
    //Just all the different integers we need to perform the calculations.
    private int carSpeed;
    private int result;
    private int reactionTime = 10;
    private int kmToM = 36; // converter for turning km into meters
    private int friction = 1; 
    private int reactionDistance,
                BreakingDistance;
    private int magicNumber = 2500; // Another magic number necessary for the calculation. I did not make it up.
    
    // Ten because ten doesn't want to be a magic number.
    private int ten = 10;
    
    // Just two objects to help with my exit button.
    private DropShadow exitHighlight;
    private Tooltip exitTip;

    // The custom exit button.
    @FXML
    private ImageView exitButton;
    
    // The user's input
    @FXML
    private TextField speed;
    
    // The three lables to be tampred with through buttonpressing!
    @FXML
    private Label answer;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private Label beSafe;
    
    /**
     * This is the method called when the button is clicked.<p>
     * 
     * The button fires the calculations for distance traveled after breaking.
     */
    @FXML
    private void handleButtonClicked(){
        
        System.out.println("You clicked the button!");
        
        // If the textfield is null there will be errormessage in the console as well as the application.
        if (speed.getText().isEmpty()) {
            System.out.println("But you didn't type anything!");
            errorMessage.setVisible(true);
        } 
        /**
         * If the text field is not empty, it will try to parse the content, 
         * but catches the NFE if it's not a string of numbers and display the
         * error message.
         */
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
            
        /**
         * Since I don't want the calculation to fire if the user didn't type 
         * a number I made these if cases.
         * 
         * the calculations include the distance traveled while you are reacting
         * in order to hit the breaks. think the thought to hit the breaks.
         */
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

    /**
     * The custom made exit button. On hover it glows red and displays a 
     * tool tip, on clicked it exits 0the application and when mouse leaves 
     * it it stops glowing.
     */
    private void setExitButton() {
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Bye bye!");
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
