package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import main.ETankApplication;
import model.data.User;
import model.game.logic.GameLobby;
import model.game.logic.Player;
import model.service.GameCreator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameCreatorViewController {

    private ETankApplication eTankApplication;

    ObservableList<Player> playerList = FXCollections.observableArrayList();
    ObservableList<GameLobby> lobbies = FXCollections.observableArrayList();

    //TODO: Nur zum Testen erst einmal und solange Backend, Sockets ect nicht implementiert sind
    GameCreator gc = new GameCreator();
    @FXML
    private TableView<GameLobby> tableGameList;

    @FXML
    private TableColumn<GameLobby, Long> columnGameNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnGameSeats;

    @FXML
    private ListView<Player> playerListView;


    public GameCreatorViewController() {
    }

    @FXML
    private void initialize() {
        columnGameNumber.setCellValueFactory(cellData -> cellData.getValue().gameIdProperty().asObject());
        columnGameSeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    @FXML
    public void hostGame() {
        GameLobby gameLobby = new GameLobby();
        gameLobby.addHost(eTankApplication.getSignedUser());

    }
    @FXML
    public void joinGame() {

    }

    @FXML
    public void switchBack() throws IOException {
        eTankApplication.showMenuView();
    }

    @FXML
    public void switchToGameView() throws IOException{
        eTankApplication.showGameView();
    }

    //TODO: Refactorn unbedingt-> Elemente vielleicht automatisch erstellen lassen foreach?!
    @FXML
    public GameLobby joinSelectedGame() {
        GameLobby selectedGameLobby = tableGameList.getSelectionModel().getSelectedItem();

        return selectedGameLobby;
    }

    public void setStatusReady(ActionEvent actionEvent) {
    }

    public void enterChatHandleS(KeyEvent keyEvent) {
    }

    public void sendMessageS(ActionEvent actionEvent) {
    }

    public void switchToInit(ActionEvent actionEvent) {
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
        tableGameList.setItems(gc.getGameList());
    }

    public void setRdyTrue(ActionEvent actionEvent) {
    }
}
