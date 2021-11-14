package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ETankApplication;

import java.io.IOException;

public class ViewController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    ETankApplication eTankApplication;

    public void switchToGame(ActionEvent event)  throws IOException{
        root = FXMLLoader.load(getClass().getResource("../resources/view/GameView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../resources/view/SettingsView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoginView(Stage primaryStage) throws IOException{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginView.fxml"));
        root = loader.load();
        System.out.println(root);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLevelOneView(Stage primaryStage) throws IOException{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LevelOneViewOnlyTank.fxml"));
        root = loader.load();
        System.out.println(root);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToStatistics(Stage primaryStage) throws IOException{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/StatisticsView.fxml"));
        root = loader.load();
        scene = new Scene(root);

        stage.show();
    }

    public void switchToProfil(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../resources/view/ProfilView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void eTankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;

    }

    public void switchToLevelOne(ActionEvent actionEvent) {
    }
}
