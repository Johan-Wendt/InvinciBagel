/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author johanwendt
 */
public class InvinciBagel extends Application {
    Scene scene;
    StackPane root;
    Image splashScreen, instructionlayer, legalLayer, screenLayer;
    ImageView splashScreenBackplate, splashScreenTextArea;
    Button gameButton, helpButton, scoreButton, legalButton;
    HBox buttonContainer; 
    Insets buttonContainerPadding;
    
    
    @Override
    public void start(Stage primaryStage) {
        createSplashScreenNodes();
        addNodesToStackPane();
        primaryStage.setTitle("InvinciBagel");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        gameButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        helpButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        scoreButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        legalButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        
        
        root = new StackPane();
        
        scene = new Scene(root, 301, 250);
       
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void createSplashScreenNodes() {
        root = new StackPane();
        scene = new Scene(root, 640, 400);
        gameButton = new Button();
        gameButton.setText("PLAY GAME");
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        scoreButton = new Button();
        scoreButton.setText("HIGHSCORES");
        legalButton = new Button();
        legalButton.setText("LEGALS & CREDITS");
        
        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        
        
        splashScreen = new Image("/invincibagelsplash.png", 640, 400, true, false, true);
        splashScreenBackplate = new ImageView(splashScreen);
        
    }
    private void addNodesToStackPane() {
        
    }
    
}
