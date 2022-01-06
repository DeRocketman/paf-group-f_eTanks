package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.ETankApplication;
import model.service.HttpRequest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController {

    /*private Stage stage;
    private Scene scene;
    private Parent root;*/

    ETankApplication eTankApplication;
    HttpRequest httpRequest = new HttpRequest();

    /*public void switchToLoginView(Stage primaryStage) throws IOException{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginView.fxml"));
        root = loader.load();
        System.out.println(root);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void switchToProfil() throws IOException {
        eTankApplication.showProfilView();
        System.out.println(eTankApplication.getSignedUser().getUserName());
    }

    public void switchToStatistics() throws IOException {
        eTankApplication.showStatisticsView();
    }

    public void switchToSettings() throws IOException {
        eTankApplication.showSettingsView();
    }

    public void switchToMenu() throws IOException {
        eTankApplication.showMenuView();
    }

    public void switchToGameCreator() throws IOException {
        eTankApplication.showGameCreatorView();
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }
}
