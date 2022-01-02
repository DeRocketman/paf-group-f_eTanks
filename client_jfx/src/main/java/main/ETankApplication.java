package main;

import controller.*;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//not used in MVVM javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//not used in MVVM import model.game.Game;
import model.data.GameStatistic;
import model.data.User;
import view.GameView;
import viewmodel.GameViewModel;

import java.io.IOException;
import java.util.List;


public class ETankApplication extends Application {

    Stage primaryStage;
    AnchorPane rootLayout;
    User signedUser;
    List<GameStatistic> gameStatistic;
    String BearerToken;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        showLoginView();
        createUser();
    }

    public void createUser(){
        this.signedUser = new User();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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
        profilController.initialiseUserData();

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
        statisticsController.initialiseUserData();

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
        settingsController.initialiseUserData();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showCreateUserView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/RegisterUserView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        RegisterUserViewController registerUserViewController = loader.getController();
        registerUserViewController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showGameView() {
        ViewTuple<GameView, GameViewModel> viewTuple = FluentViewLoader.fxmlView(GameView.class).load();
        Scene scene = new Scene(viewTuple.getView());
        scene.setOnKeyPressed(keyEvent -> viewTuple.getViewModel().handle(keyEvent));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        /**
        try {


            // Load the fxml file and create the new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ETankApplication.class.getResource("../view/GameView.fxml"));
            BorderPane page = loader.load();

            // Create the dialog Stage.
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Set the controller to Stage
            GameViewController controller = loader.getController();
            //  Adding EventListener to scene and push the KeyEvent in Controller
            scene.setOnKeyPressed(keyEvent -> controller.keyPressed(keyEvent));
            scene.setOnKeyTyped(keyEvent -> controller.keyTyped(keyEvent));

        } catch (IOException e) {
            e.printStackTrace();
        }
         **/
    }

    public void showGameCreatorView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/GameLobbyView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        GameLobbyViewController gameLobbyViewController = loader.getController();
        gameLobbyViewController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public User getSignedUser() {
        return signedUser;
    }

    public List<GameStatistic> getGameStatistic() {
        return gameStatistic;
    }

    public void setGameStatistic(List<GameStatistic> gameStatistic) {
        this.gameStatistic = gameStatistic;
    }

    public String getBearerToken() {
        return BearerToken;
    }

    public void setBearerToken(String bearerToken) {
        BearerToken = bearerToken;
    }


}
