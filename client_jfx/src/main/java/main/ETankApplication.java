package main;

import controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ETankApplication extends Application {

    Stage primaryStage;
    AnchorPane rootLayout;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showLoginView();
    }

    public void showLoginView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        LoginViewController lvController = loader.getController();
        lvController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showMenuView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/MenuView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        MenuViewController menuController = loader.getController();
        menuController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showProfilView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ProfilView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        ProfilViewController profilController = loader.getController();
        profilController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showStatisticsView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/StatisticsView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        StatisticsViewController statisticsController = loader.getController();
        statisticsController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showSettingsView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/SettingView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        SettingViewController settingsController = loader.getController();
        settingsController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLevelOneViewX() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LevelOneView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        LevelOneViewController lvlOneController = loader.getController();
        lvlOneController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLevelOneView() {
        try {
            // Load the fxml file and create the new stage for the popup dialog-
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ETankApplication.class.getResource("../view/LevelOneView.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);

            // Set the seminar into the controller.
            LevelOneViewController controller = loader.getController();

            //  Adding EventListener to scene and push the KeyEvent in Controller
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    controller.keyPressed(keyEvent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
