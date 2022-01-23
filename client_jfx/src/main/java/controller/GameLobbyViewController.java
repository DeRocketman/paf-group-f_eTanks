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
import model.data.UserSettings;
import model.game.logic.GameLobby;
import model.game.logic.Player;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;


import java.io.IOException;

public class GameLobbyViewController {



    SocketClient sc = new SocketClient(this);
    Thread messageReceive = new Thread(sc);
    private ETankApplication eTankApplication;
    public Player signedPlayer;
    private GameLobby selectedLobby;

    ObservableList<GameLobby> lobbyList = FXCollections.observableArrayList();

    @FXML
    private TableView<GameLobby> lobbyTable;
    @FXML
    private TableColumn<GameLobby, String> columnLobbyNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnLobbySeats;

    @FXML
    private ListView playerList;

    @FXML
    private TextField textChatMsgField;

    @FXML
    private TextArea textAreaChatField;

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
        fillLobbyTable();
        this.messageReceive.start();
    }

    @FXML
    private void switchToInit() {
        resetViews();
        vbxInit.setVisible(true);
    }

    @FXML
    private void hostGame() throws IOException {
        sendExtendUserData();
        selectedLobby = new GameLobby();
        selectedLobby.buildLobbyID();
        registerLobby(selectedLobby);
        showLobbyHostView(selectedLobby);
    }

    @FXML
    private void joinGame() {
        sendExtendUserData();
        getLobbyList();
        fillLobbyTable();
        showLobbyJoinView();
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
    public void switchBackToMainMenu() throws IOException {
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
            showJoinLobbyView();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Schlacht ist schon voll");
            alert.setContentText("Wähle eine andere oder erstelle selbst eine");
            alert.showAndWait();
        }
        return selectedGameLobby;
    }

    @FXML
    private void setStatusReady() {

    }

    public void resetViews() {
        vbxLobby.setVisible(false);
        vbxJoin.setVisible(false);
        vbxInit.setVisible(false);
        hbxHostPanel.setVisible(false);
        hbxJoinerPanel.setVisible(false);
    }

   @FXML
   public void sendChatMessage() {
        if (!textChatMsgField.getText().equals("")) {
            Message msg = new Message();
            msg.setMessageType(MessageType.CHAT_MSG);
            msg.setPlayerId(eTankApplication.getSignedUser().getId());
            msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
            msg.setPlayerImage("default");
            msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
            msg.setPayload("JAVA");
            msg.setPayload(textChatMsgField.getText());
            sc.sendMsg(msg);
            textChatMsgField.clear();
        }
   }

    public void receiveLobbyMessages(Message msg) throws IOException {
        if (msg != null) {
            if (msg.getMessageType() == MessageType.GET_LOBBIES) {
                processGetLobbiesMsg(msg);
            }
            if (msg.getMessageType() == MessageType.CHAT_MSG) {
                processChatMsg(msg);
            }
            if (msg.getMessageType() == MessageType.JOINED_PLAYER) {
                processJoinedPlayerMsg(msg);
            }
            if (msg.getMessageType() == MessageType.RDY_STATUS) {
                processRdyStatusMsg(msg);
            }
            if (msg.getMessageType() == MessageType.START_GAME) {
                processStartGameMsg(msg);
            }
            if (msg.getMessageType() == MessageType.REGISTER_LOBBY) {
                processRegisterLobbyMsg(msg);
            }
        }
    }

    private void processRegisterLobbyMsg(Message msg) {
        textAreaChatField.appendText(msg.getPayload());
    }

    private void processStartGameMsg(Message msg) {


    }

    private void processRdyStatusMsg(Message msg) {
    }

    private void processJoinedPlayerMsg(Message msg) throws IOException {
        Player player = new Player(msg.getPlayerId(), msg.getPlayerPublicName(),"","", "", null);
    }

    private void processChatMsg(Message msg) {
        textAreaChatField.appendText(msg.getPlayerPublicName()+": " + msg.getPayload());
    }

    private void processGetLobbiesMsg(Message msg) {
    }

    private void showLobbyHostView(GameLobby lobby) {
        resetViews();
        vbxLobby.setVisible(true);
        hbxHostPanel.setVisible(true);
        lblGameNumber.setText("Spielnummer: " + String.valueOf(lobby.getGameLobbyID()));
    }

    private void showJoinLobbyView() {
        resetViews();
        vbxJoin.setVisible(true);
    }

    private void showLobbyJoinView() {
        resetViews();
        vbxLobby.setVisible(true);
        hbxJoinerPanel.setVisible(true);
    }

    public void getLobbyList() {
        Message msg = new Message();
        msg.setMessageType(MessageType.GET_LOBBIES);
        sc.sendMsg(msg);
    }

    public void fillPlayerList() {

    }

    public void sendExtendUserData() {
        Message msg = new Message();
        msg.setMessageType(MessageType.LOGIN);
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        msg.setPlayerImage("default");
        msg.setPlayerIsRdy(false);
        msg.setPayload("JAVA");
        sc.sendMsg(msg);
    }

    public void registerLobby(GameLobby lobby) {
        Message msg = new Message();
        msg.setMessageType(MessageType.REGISTER_LOBBY);
        msg.setGameLobbyNumber(lobby.getGameLobbyID());
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        sc.sendMsg(msg);
    }
    public void fillLobbyTable() {
        columnLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty());
        columnLobbySeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    public void startGame () throws IOException {
        eTankApplication.showGameView();
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }


}
