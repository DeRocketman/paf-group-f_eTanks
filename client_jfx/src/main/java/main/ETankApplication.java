package main;


import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//not used in MVVM javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import controller.*;
//not used in MVVM import model.game.Game;
import model.data.GameStatistic;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.game.logic.GameLobby;
import model.game.logic.GamePlay;
import model.game.logic.Player;
import view.GameView;
import viewmodel.GameViewModel;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class ETankApplication extends Application {

    Stage primaryStage;
    AnchorPane rootLayout;
    User signedUser;
    List<GameStatistic> gameStatistics;
    String BearerToken;
    GamePlay gamePlay;
    ObservableList<Player> playerlist;

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
        this.gameStatistics = new ArrayList<>();
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

    public void showGameView() throws IOException {

        ObservableList<Player> playerList = FXCollections.observableArrayList();

       /* UserSettings testUserSettings = new UserSettings();
        //Player braucht eigentlich folgende Punkte nicht: Statstik, Settings, Bild, passwort, username -> keine Vererbung ?
        Player testPlayer = new Player(200, "spieler200", "Spieler 200","default", "default", testUserSettings);
        Player testPlayer2 = new Player(300, "spieler300", "Spieler 300","default", "default", testUserSettings);
        Player signedPlayer = new Player(this.getSignedUser().getId(), this.getSignedUser().getUserName(), this.getSignedUser().getPublicName(), "Default", "Default", this.getSignedUser().getUserSettings());

        playerList.addAll(testPlayer, testPlayer2, signedPlayer);*/

        ViewTuple<GameView, GameViewModel> viewTuple = FluentViewLoader.fxmlView(GameView.class).load();
        Scene scene = new Scene(viewTuple.getView());
        scene.setOnKeyPressed(keyEvent -> viewTuple.getViewModel().handleKeyPressed(keyEvent));
        scene.setOnKeyReleased(keyEvent -> viewTuple.getViewModel().handleKeyReleased(keyEvent));

        viewTuple.getViewModel().setETankApplication(this);
       // viewTuple.getViewModel().getGamePlay().setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

     //   viewTuple.getViewModel().getGamePlay().setPlayerlist(playerlist);

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
      //  gameLobbyViewController.createGames();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public User getSignedUser() {
        return signedUser;
    }

    public List<GameStatistic> getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistic(List<GameStatistic> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }

    public String getBearerToken() {
        return BearerToken;
    }

    public void setBearerToken(String bearerToken) {
        BearerToken = bearerToken;
    }

    public GamePlay getGamePlay(){
        return this.gamePlay;
    }

    public ObservableList<Player> getPlayerlist() {
        return playerlist;
    }

    public void setPlayerlist(ObservableList<Player> playerlist) {
        this.playerlist = playerlist;
    }

}
