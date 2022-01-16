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
import model.data.GameStatistic;
import model.data.User;
import model.data.UserSettings;
import model.data.UserStatistic;
import model.game.logic.GameLobby;
import model.game.logic.Player;
import model.service.SocketClient;
import org.boon.Str;


import java.io.IOException;

public class GameLobbyViewController {


   // SocketClient sc = new SocketClient("localhost",3333,this);
    //Thread messageReceive = new Thread(sc);
    private ETankApplication eTankApplication;
    public Player signedPlayer;

    ObservableList<GameLobby> lobbyList = FXCollections.observableArrayList();

    @FXML
    private TableView<GameLobby> lobbyTable;
    @FXML
    private TableColumn<GameLobby, Long> columnLobbyNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnLobbySeats;

    @FXML
    private ListView playerList;

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

    @FXML
    private Label lblGameNumber;

    public GameLobbyViewController() throws IOException {

    }

    @FXML
    private void initialize() {
        switchToInit();
        lobbyTable.setItems(lobbyList);
        columnLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty().asObject());
        columnLobbySeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
       // this.messageReceive.start();

    }

    @FXML
    public void switchToInit() {
        resetViews();
        vbxInit.setVisible(true);
    }

    @FXML
    public void hostGame() throws IOException {
        resetViews();
        vbxLobby.setVisible(true);
        hbxHostPanel.setVisible(true);
        GameLobby lobby = new GameLobby();
        lobby.buildLobbyID();

        lobby.addPlayer(signedPlayer);
        this.lobbyList.add(lobby);
        lblGameNumber.setText("Spielnummer: " + String.valueOf(lobby.getGameLobbyID()));

        //     User sU = eTankApplication.getSignedUser();
   //     this.signedPlayer = new Player(sU.getId(), sU.getUserName(), sU.getPublicName(), sU.getUserImage(), sU.getPassword(),
   //             sU.getUserSettings());
   //     lobby.addPlayer(this.signedPlayer);
   //     this.lobbyList.add(lobby);

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
        resetViews();
        vbxJoin.setVisible(true);
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
    public GameLobby joinSelectedGame() throws IOException {
        GameLobby selectedGameLobby = lobbyTable.getSelectionModel().getSelectedItem();
        lblGameNumber.setText("Spielnummer: " + String.valueOf(selectedGameLobby.getGameLobbyID()));
        if(selectedGameLobby.getSeatCounter() < 4) {
            System.out.println("joining game");
            selectedGameLobby.addPlayer(signedPlayer);
            resetViews();
            vbxLobby.setVisible(true);
            hbxJoinerPanel.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Schlacht ist schon voll");
            alert.setContentText("Wähle eine andere oder erstelle selbst eine");
            alert.showAndWait();
        }
        return selectedGameLobby;
    }

    @FXML
    private void setStatusReady(ActionEvent actionEvent) {
    }

    public void resetViews() {
        vbxLobby.setVisible(false);
        vbxJoin.setVisible(false);
        vbxInit.setVisible(false);
        hbxHostPanel.setVisible(false);
        hbxJoinerPanel.setVisible(false);
    }
    public void enterChatHandleS(KeyEvent keyEvent) {
    }

    public void sendMessageS(ActionEvent actionEvent) {
    }


    public void startGame () throws IOException {
       // GameLobby lobby = testLobby();
       // eTankApplication.setPlayerlist(lobby.getPlayers());
        eTankApplication.showGameView();
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }


    public void createGames() throws IOException {
        UserSettings testUserSetting = new UserSettings();
        Player testPlayer = new Player(1000, "Line-Maxi", "Rocket wo man",
                this.eTankApplication.getSignedUser().getUserImage(), "passwort", testUserSetting);
        Player testPlayer2 = new Player(10001, "Maxi-Line", "Wocketwan",
                this.eTankApplication.getSignedUser().getUserImage(), "passwort", testUserSetting);
        this.signedPlayer = new Player(this.eTankApplication.getSignedUser().getId(), this.eTankApplication.getSignedUser().getUserName(), this.eTankApplication.getSignedUser().getPublicName(), this.eTankApplication.getSignedUser().getUserImage(), "Default", this.eTankApplication.getSignedUser().getUserSettings());
        lobbyList.add(new GameLobby());
        lobbyList.add(new GameLobby());
        lobbyList.add(new GameLobby());
        lobbyList.add(new GameLobby());
        for (GameLobby gameLobby : lobbyList) {
            gameLobby.addPlayer(testPlayer);
        }
        lobbyList.get(1).addPlayer(testPlayer2);
        lobbyList.get(1).addPlayer(testPlayer2);
    }

    public GameLobby testLobby() throws IOException {
        UserSettings testUserSetting = new UserSettings();
        Player testPlayer = new Player(1000, "Line-Maxi", "Rocket wo man",
                this.eTankApplication.getSignedUser().getUserImage(), "passwort", testUserSetting);
        Player testPlayer2 = new Player(10001, "Maxi-Line", "Wocketwan",
                this.eTankApplication.getSignedUser().getUserImage(), "passwort", testUserSetting);
        this.signedPlayer = new Player(this.eTankApplication.getSignedUser().getId(), this.eTankApplication.getSignedUser().getUserName(), this.eTankApplication.getSignedUser().getPublicName(), this.eTankApplication.getSignedUser().getUserImage(), "Default", this.eTankApplication.getSignedUser().getUserSettings());

        GameLobby lobby = new GameLobby();
        lobby.buildLobbyID();

        lobby.addPlayer(signedPlayer);
        lobby.addPlayer(testPlayer);

        return lobby;
    }
}
