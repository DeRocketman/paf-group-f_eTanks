package main;

import controller.LevelOneViewController;
import controller.ViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.UserSettings;

import java.io.IOException;

public class ETankApplication extends Application {

    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        /**
        try {
            ViewController viewController = new ViewController();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setController(viewController);

            viewController.eTankApplication(this);
            viewController.switchToLevelOneView(primaryStage);

        } catch(Exception e) {
            e.printStackTrace();
        }
         **/
        loadLevelOne();
    }

    public void loadLevelOne() {
        try {
            // Load the fxml file and create the new stage for the popup dialog-
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ETankApplication.class.getResource("../view/LevelOneView.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage levelOneStage = new Stage();
            Scene scene = new Scene(page);
            levelOneStage.setScene(scene);

            // Set the seminar into the controller.
            LevelOneViewController controller = loader.getController();

            //  Adding EventListener to scene and push the KeyEvent in Controller
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                     controller.keyPressed(keyEvent);
                }
            });



            // Show the dialog and wait until the user closes it
            levelOneStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
