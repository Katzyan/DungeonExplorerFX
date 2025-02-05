package org.example.dungeonexplorerfx.controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private MediaPlayer mediaPlayerIntro;
    @FXML
    private AnchorPane container, startScreen, dialogueScreen, room1;
    @FXML
    private Text dialogueText;

    @FXML
    private Button newGame;

    @FXML
    private Button enterDungeon;

    @FXML
    private ImageView playerCharacter;

    @FXML
    private Line topMargin, bottomMargin, rightMargin, leftMargin, leftLine1, rightLine1;

    @FXML
    private Polyline path1;

    @FXML
    private Rectangle wall1, wall2;
    private List<Rectangle> wallList = new ArrayList<>();
    private List<Line> leftLines = new ArrayList<>();
    private List<Line> rightLines = new ArrayList<>();
    private List<Line> upLines = new ArrayList<>();
    private List<Line> downLines = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        TranslateTransition translate = new TranslateTransition();
//        translate.setNode(playerCharacter);
//        translate.setByX(250);
//        translate.play();
    }

    public void createWalls() {
        Image image = new Image(getClass().getResource("/images/wallTexture.png").toExternalForm());
        ImagePattern pattern = new ImagePattern(image, 0,0,32, 32, false);
        this.wallList.add(wall1);
        this.wallList.add(wall2);
        for(Rectangle wall: wallList){
            wall.setFill(pattern);
        }

        this.leftLines.add(leftLine1);
        this.leftLines.add(leftMargin);
        this.rightLines.add(rightLine1);
        this.rightLines.add(rightMargin);
        this.upLines.add(topMargin);
        this.downLines.add(bottomMargin);
    }


    /**
     * Action Methods
     */
    public void startNewGame(ActionEvent event) {
        System.out.println("Starting new game");
        fadeOut(startScreen);
        addDialogueText();
        fadeIn(dialogueScreen);
    }

    public void enterDungeon(ActionEvent event) {
        fadeOut(dialogueScreen);
        fadeIn(room1);
    }


    public void moveUp() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(playerCharacter);
        boolean isInWall = false;
        for (Line line : upLines) {
            if (line.getBoundsInParent().intersects(playerCharacter.getBoundsInParent())) {
                isInWall = true;
                break;

            }
        }
        if (!isInWall) {
            translate.setByY(-75);
        }
        translate.play();
    }

    public void moveDown() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(playerCharacter);
        boolean isInWall = false;

            for (Line line : downLines) {
                if (line.getBoundsInParent().intersects(playerCharacter.getBoundsInParent())) {
                    isInWall = true;
                    break;

                }
            }
            if (!isInWall) {
                translate.setByY(75);
            }


        translate.play();
    }

    public void moveRight() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(playerCharacter);
        boolean isInWall = false;
        for (Line line : rightLines) {
            if (line.getBoundsInParent().intersects(playerCharacter.getBoundsInParent())) {
                isInWall = true;
                break;

            }
        }
        if (!isInWall) {
            translate.setByX(75);
        }
        translate.play();
    }

    public void moveLeft() {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(playerCharacter);
        boolean isInWall = false;
        for(int i = 0; i < 10; i++) {
            for (Line line : leftLines) {
                if (line.getBoundsInParent().intersects(playerCharacter.getBoundsInParent())) {
                    isInWall = true;
                    break;

                }
            }
            if (!isInWall) {
                translate.setByX(-75);
            }
        }
        translate.play();
    }


    /**
     * Util Methods
     */
    public void hidePanes() {
        if (dialogueScreen != null) {
            dialogueScreen.setVisible(false);
            enterDungeon.setVisible(false);
            enterDungeon.setOpacity(0);
            room1.setVisible(false);
        }
    }

    public void fadeIn(Node node) {
        node.setVisible(true);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), node);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setInterpolator(Interpolator.EASE_BOTH);
        fadeIn.play();
    }

    public void fadeOut(Node node) {
//        anchorPane.setVisible(false);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(2000), node);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setInterpolator(Interpolator.EASE_BOTH);
        fadeOut.setOnFinished(event -> node.setVisible(false));
        fadeOut.play();
    }


    public void addDialogueText() {
        URL soundPageScribbleURL = getClass().getResource("/sounds/QuillPenWriting.mp3");

        Media soundScribble = new Media(soundPageScribbleURL.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(soundScribble);

        String text = "Long ago, the Kingdom of Eldoria flourished under the rule of wise kings. " +
                "But greed and ambition led to its\ndownfall. Legends speak of an ancient dungeon, " +
                "hidden beneath the ruins of the capital, where a dark secret\nwas sealed away. " +
                "No one knows what truly lies within—only that those who enter never return. " +
                "Some say the\ndungeon holds the lost relic of the Eldorian kings, a treasure beyond imagination. " +
                "Others whisper that a\nforgotten curse lurks in the depths, waiting to consume any who dare disturb it." +
                "\n\nNow, as the last rays of daylight fade, you stand before the gaping maw of the dungeon entrance.\nThe only way forward is down.";

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(1);
        mediaPlayer.play();

        Timeline timeline = new Timeline();
        for (int i = 0; i < text.length(); i++) {
            int currentIndex = i;
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(1 * i), event -> { // 40 milisecs
                        dialogueText.setText(text.substring(0, currentIndex + 1));
                    })
            );
        }
        timeline.setOnFinished(event -> {
            mediaPlayer.stop();
            fadeIn(enterDungeon);
        });
        timeline.play();
    }


    /**
     * Getters and Setters
     */
    public MediaPlayer getMediaPlayerIntro() {
        return mediaPlayerIntro;
    }

    public void setMediaPlayerIntro(MediaPlayer mediaPlayerIntro) {
        this.mediaPlayerIntro = mediaPlayerIntro;
    }


}
