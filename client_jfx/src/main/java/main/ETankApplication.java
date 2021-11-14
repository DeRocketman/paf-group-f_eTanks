package main;

import controller.LevelOneViewController;
import controller.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ETankApplication extends Application {

    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        try {
            ViewController viewController = new ViewController();
            //FXMLLoader loader = new FXMLLoader();
            //loader.setController(viewController);

            viewController.eTankApplication(this);
            viewController.switchToLevelOneView(primaryStage);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
