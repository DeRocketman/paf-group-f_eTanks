package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.ETankApplication;
import model.data.User;
import model.game.logic.GameLobby;
import model.game.logic.Player;
import model.service.GameCreator;
import java.io.IOException;

public class GameLobbyViewController {

    private ETankApplication eTankApplication;

    ObservableList<Player> playerList = FXCollections.observableArrayList();
    ObservableList<GameLobby> lobbies = FXCollections.observableArrayList();

    //TODO: Nur zum Testen erst einmal und solange Backend, Sockets ect nicht implementiert sind
    GameCreator gc = new GameCreator();
    @FXML
    private TableView<GameLobby> tableGameList;

    @FXML
    private TableColumn<GameLobby, Long> columLobbyNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnGameSeats;

    @FXML
    private ListView<Player> playerListView;

    @FXML
    private VBox vbxJoin;

    @FXML
    private VBox vbxLobby;

    @FXML
    private VBox vbxInit;

    @FXML
    private HBox hbxHostPanel;

    @FXML
    private HBox hbxJoinerPanel;

    public GameLobbyViewController() {
    }

    @FXML
    private void initialize() {
        columLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty().asObject());
        columnGameSeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    @FXML
    public void hostGame() {
        vbxInit.setVisible(false);
        vbxJoin.setVisible(false);
        vbxLobby.setVisible(true);

        GameLobby lobby = new GameLobby();
        User sU = eTankApplication.getSignedUser();
        Player player = new Player(sU.getId(), sU.getUserName(), sU.getPublicName(), sU.getImage(), sU.getPassword(),
                                    sU.getUserSettings(), sU.getUserStatistic());
        lobby.addPlayer(player);
    }
    @FXML
    public void joinGame() {
        vbxInit.setVisible(false);
        vbxJoin.setVisible(true);
        vbxLobby.setVisible(false);
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
