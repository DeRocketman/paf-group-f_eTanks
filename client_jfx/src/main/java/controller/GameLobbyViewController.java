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
    ObservableList<GameLobby> lobbyList = FXCollections.observableArrayList();

    //TODO: Nur zum Testen erst einmal und solange Backend, Sockets ect nicht implementiert sind
    GameCreator gc = new GameCreator();
    @FXML
    private TableView<GameLobby> tableLobbyList;

    @FXML
    private TableColumn<GameLobby, Long> columnLobbyNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnLobbySeats;

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
        vbxInit.setVisible(true);
        vbxJoin.setVisible(false);
        vbxLobby.setVisible(false);
        //tableLobbyList.setItems(gc.getGameList());
        tableLobbyList.setItems(lobbyList);
        columnLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty().asObject());
        columnLobbySeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    @FXML
    public void switchToInit() {
        vbxInit.setVisible(true);
        vbxJoin.setVisible(false);
        vbxLobby.setVisible(false);
    }
    @FXML
    public void hostGame() {
        vbxInit.setVisible(false);
        vbxJoin.setVisible(false);
        vbxLobby.setVisible(true);
        hbxHostPanel.setVisible(true);

        GameLobby lobby = new GameLobby();

        User sU = eTankApplication.getSignedUser();
        Player player = new Player(sU.getId(), sU.getUserName(), sU.getPublicName(), sU.getImage(), sU.getPassword(),
                                    sU.getUserSettings(), sU.getUserStatistic());
        lobby.addPlayer(player);
        this.lobbyList.add(lobby);
    }

    @FXML
    private void closeLobby() {
        switchToInit();
        for (GameLobby lobby: lobbyList) {
            if(lobby.getPlayers().get(0).getId() == eTankApplication.getSignedUser().getId()) {
                lobbyList.remove(lobby);
                break;
            }
        }
    }
    @FXML
    public void joinGame() {
        vbxJoin.setVisible(true);
        vbxInit.setVisible(false);
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


    @FXML
    public void setRdyTrue() {
    }

    @FXML
    public void switchBackToInit() {
        switchToInit();
        for (GameLobby lobby: this.lobbyList) {
            for (Player player: lobby.getPlayers()) {
                if(player.getId() == eTankApplication.getSignedUser().getId()) {
                    lobby.removePlayer(player);
                }
            }
        }
    }

    @FXML
    public GameLobby joinSelectedGame() {
        GameLobby selectedGameLobby = tableLobbyList.getSelectionModel().getSelectedItem();


        return selectedGameLobby;
    }

    public void setStatusReady(ActionEvent actionEvent) {
    }

    public void enterChatHandleS(KeyEvent keyEvent) {
    }

    public void sendMessageS(ActionEvent actionEvent) {
    }



    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }


}
