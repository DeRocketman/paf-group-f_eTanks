package main;

import controller.*;
import model.data.GameStatistic;
import model.data.User;
import model.game.logic.GameLobby;
import view.GameView;
import viewmodel.GameViewModel;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ETankApplication extends Application {

    Stage primaryStage;
    AnchorPane rootLayout;
    User signedUser;
    List<GameStatistic> gameStatistics;
    String BearerToken;
    GameViewModel gameViewModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("E-Tanks 2021");
        showLoginView();
        createUser();
    }

    /**
     * Creates User and gameStatistic List
     */
    public void createUser(){
        this.signedUser = new User();
        this.gameStatistics = new ArrayList<>();
    }

    /**
     * Calls the login view
     *
     * @throws IOException
     */
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

    /**
     * Calls the  menu view
     *
     * @throws IOException
     */
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

    /**
     * Calls the profil view
     * @throws IOException
     */
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

    /**
     * Calls the statistic view
     * @throws IOException
     */
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

    /**
     * Calls the settingsView
     * @throws IOException
     */
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

    /**
     * Calls the registration view
     * @throws IOException
     */
    public void showRegisterUserView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/RegisterUserView.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);

        RegisterUserViewController registerUserViewController = loader.getController();
        registerUserViewController.setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Calls the game view
     *
     * @param lobby     the selected lobby
     * @throws IOException
     */
    public void showGameView(GameLobby lobby) throws IOException {
        ViewTuple<GameView, GameViewModel> viewTuple = FluentViewLoader.fxmlView(GameView.class).load();
        Scene scene = new Scene(viewTuple.getView());
        gameViewModel = viewTuple.getViewModel();
        gameViewModel.setLobby(lobby);
        gameViewModel.createGameStatistic();
        viewTuple.getViewModel().setETankApplication(this);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Calls the lobby view
     * @throws IOException
     */
    public void showLobbyView() throws IOException {
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

    public GameViewModel getGameViewModel(){
        return this.gameViewModel;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
