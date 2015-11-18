/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author johanwendt
 */
public class InvinciBagel extends Application {
    public static final double WIDTH = 640, HEIGHT = 400;
    private boolean up, down, left, right, wKey, aKey, sKey, dKey;
    private Bagel iBagel;
    private Prop iPR0, iPR1;;
    private PropH iPH0;
    private PropV iPV0, iPV1;
    private PropB iPB0;
    private Scene scene;
    private StackPane root;
    private Image splashScreen, instructionLayer, legalLayer, scoresLayer;
    private Image iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8, iP0, iP1;
    private ImageView splashScreenBackplate, splashScreenTextArea;
    private Button gameButton, helpButton, scoreButton, legalButton;
    private HBox buttonContainer; 
    private Insets buttonContainerPadding;
    private GamePlayLoop gamePlayLoop;
    CastingDirector castDirector;
    private AudioClip iSound0, iSound1, iSound2, iSound3, iSound4, iSound5;
    private URL iAudioFile0, iAudioFile1, iAudioFile2, iAudioFile3, iAudioFile4, iAudioFile5;
    
    @Override
    public void start(Stage primaryStage) {
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.WHITE);
        primaryStage.setTitle("InvinciBagel");
        primaryStage.setScene(scene);
        primaryStage.show();
        createSceneEventHandling();
        loadAudioAssets(); 
        loadImageAssets();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
        createSplashScreenNodes();
        addNodesToStackPane();
        createStartGameLoop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void createSplashScreenNodes() {
        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        gameButton = new Button();
        gameButton.setText("PLAY GAME");
        gameButton.setOnAction(e -> {
            splashScreenBackplate.setVisible(false); 
            splashScreenTextArea.setVisible(false);
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction((ActionEvent e) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionLayer);
        });
        scoreButton = new Button();
        scoreButton.setText("HIGHSCORES");
        scoreButton.setOnAction(e -> {
            splashScreenBackplate.setVisible(true); 
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(scoresLayer);
        });
        legalButton = new Button();
        legalButton.setText("LEGALS & CREDITS");
        legalButton.setOnAction(e -> {
            splashScreenBackplate.setVisible(true); 
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(legalLayer);
        });
        buttonContainer.getChildren().addAll(gameButton, helpButton, scoreButton, legalButton);
        splashScreenBackplate = new ImageView(splashScreen);
        splashScreenTextArea = new ImageView(instructionLayer);
        
    }
    private void addNodesToStackPane() {
        root.getChildren().addAll(splashScreenBackplate, splashScreenTextArea, buttonContainer);
    }

    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: up = true; break;
                case DOWN: down = true; break;
                case RIGHT: right = true; break;
                case LEFT: left = true; break;
                case W: wKey = true; break;
                case S: sKey = true; break;
                case A: aKey = true; break;
                case D: dKey = true; break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP: up = false; break;
                case DOWN: down = false; break;
                case RIGHT: right = false; break;
                case LEFT: left = false; break;
                case W: wKey = false; break;
                case S: sKey = false; break;
                case A: aKey = false; break;
                case D: dKey = false; break;
            }
        });
    }
    private void loadImageAssets() {
    splashScreen = new Image("/invincibagelsplash.png", 640, 400, true, false, true);
    instructionLayer = new Image("/invincibagelinstruct.png", 640, 400, true, false, true); 
    legalLayer = new Image("/invincibagelcreds.png", 640, 400, true, false, true); 
    scoresLayer = new Image("/invincibagelscores.png", 640, 400, true, false, true);
    iB0 = new Image("/sprite0.png", 81, 81, true, false, true);
    iB1 = new Image("/sprite1.png", 81, 81, true, false, true);
    iB2 = new Image("/sprite2.png", 81, 81, true, false, true);
    iB3 = new Image("/sprite3.png", 81, 81, true, false, true);
    iB4 = new Image("/sprite4.png", 81, 81, true, false, true);
    iB5 = new Image("/sprite5.png", 81, 81, true, false, true);
    iB6 = new Image("/sprite6.png", 81, 81, true, false, true);
    iB7 = new Image("/sprite7.png", 81, 81, true, false, true);
    iB8 = new Image("/sprite8.png", 81, 81, true, false, true);
    iP0 = new Image("/prop0.png", 72, 32, true, false, true);
    iP1 = new Image("/prop1.png", 496, 92, true, false, true);
    }

    private void createGameActors() {
        iBagel = new Bagel(this, "M57,10 L46,25 30,26 30,41,18,41 18,44 27,56 37,57 35,75 39,81 43,81 45,53 54,40 63,43 72,26 Z", 0, 0, iB0,iB1,iB2,iB3,iB4,iB5,iB6,iB7,iB8);
        iPR0 = new Prop("M0 0 L0 32 72 32 72 0 Z", 0, 148, iP0);
       // iPR1 = new Prop("M150 0 L75 200 L225 200 Z", 0, -150, iP1);
        iPH0 = new PropH("M0 0 L0 32 72 32 72 0 Z", 72, 148, iP0);
        iPV0 = new PropV("M0 0 L0 32 72 32 72 0 Z", 72, 148, iP0);
       // iPV1 = new PropV("M150 0 L75 200 L225 200 Z", 0, -58, iP1);
        iPB0 = new PropB("M0 0 L0 32 72 32 72 0 Z", 0, 116, iP0);
    }
    private void addGameActorNodes() { 
        //, iPR1.spriteFrame, iPV1.spriteFrame
        root.getChildren().addAll(iPR0.spriteFrame, iPH0.spriteFrame, iPV0.spriteFrame, iPB0.spriteFrame);
        root.getChildren().add(iBagel.spriteFrame);
    }
    private void createCastingDirection() { 
        //, iPR1, iPV1
        castDirector = new CastingDirector();
        castDirector.addCurrentCast(iPR0, iPH0, iPV0, iPB0);
        //castDirector.addCurrentCast(iBagel);
    }
    private void createStartGameLoop() { 
        gamePlayLoop = new GamePlayLoop(this); 
        gamePlayLoop.start();
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Bagel getiBagel() {
        return iBagel;
    }

    public boolean iswKey() {
        return wKey;
    }

    public void setwKey(boolean wKey) {
        this.wKey = wKey;
    }

    public boolean isaKey() {
        return aKey;
    }

    public void setaKey(boolean aKey) {
        this.aKey = aKey;
    }

    public boolean issKey() {
        return sKey;
    }

    public void setsKey(boolean sKey) {
        this.sKey = sKey;
    }

    public boolean isdKey() {
        return dKey;
    }

    public void setdKey(boolean dKey) {
        this.dKey = dKey;
    }

    public StackPane getRoot() {
        return root;
    }
    

    private void loadAudioAssets() {
        iAudioFile0 = getClass().getResource("/leftmono.wav");
        iSound0 = new AudioClip(iAudioFile0.toString());
        iAudioFile1 = getClass().getResource("/rightmono.wav"); 
        iSound1 = new AudioClip(iAudioFile1.toString()); 
        iAudioFile2 = getClass().getResource("/upmono.wav"); 
        iSound2 = new AudioClip(iAudioFile2.toString());
        iAudioFile3 = getClass().getResource("/downmono.wav");
        iSound3 = new AudioClip(iAudioFile3.toString());
        iAudioFile4 = getClass().getResource("/wmono.wav");
        iSound4 = new AudioClip(iAudioFile4.toString());
        iAudioFile5 = getClass().getResource("/smono.wav");
        iSound5 = new AudioClip(iAudioFile5.toString());
    }

    public void playiSound0() {
        this.iSound0.play();
    }

    public void playiSound1() {
        this.iSound0.play();
    }

    public void playiSound2() {
        this.iSound2.play();
    }

    public void playiSound3() {
        this.iSound3.play();
    }

    public void playiSound4() {
        this.iSound4.play();
    }

    public void playiSound5() {
        this.iSound5.play();
    }
    
}
