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
    Button gameButton, helpButton, screenButton, legalButton;
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
        screenButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        legalButton.setOnAction(e -> {
            System.out.println("Hello World!");
        });
        
        
        root = new StackPane();
        
        scene = new Scene(root, 300, 250);
       
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void createSplashScreenNodes() {
        splashScreen = new Image("/invincibagelsplash.png", 640, 400, true, false, true);
        splashScreenBackplate = new ImageView(splashScreen);
        
    }
    private void addNodesToStackPane() {
        
    }
    
}
