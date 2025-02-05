package org.example.dungeonexplorerfx;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.example.dungeonexplorerfx.controllers.Controller;

import java.io.IOException;
import java.net.URL;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/scenes/01.startScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Dungeon Explorer");
        stage.setFullScreen(true);

        String css = this.getClass().getResource("/style/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        Controller controller = fxmlLoader.getController();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
              String keyPress = keyEvent.getCode().toString();
              switch (keyPress){
                  case "W":
                      controller.moveUp();
                      break;
                  case "A":
                      controller.moveLeft();
                      break;
                  case "S":
                      controller.moveDown();
                      break;
                  case "D":
                      controller.moveRight();
                      break;
              }
            }
        });


        controller.hidePanes();
        controller.createWalls();

        stage.setScene(scene);
        URL soundTheme = getClass().getResource("/sounds/introTheme.mp3");
        Media soundIntro = new Media(soundTheme.toExternalForm());
        MediaPlayer mediaPlayerIntroTheme = new MediaPlayer(soundIntro);
        mediaPlayerIntroTheme.setCycleCount(MediaPlayer.INDEFINITE);
        controller.setMediaPlayerIntro(mediaPlayerIntroTheme);
        mediaPlayerIntroTheme.setVolume(0.45);
        mediaPlayerIntroTheme.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}