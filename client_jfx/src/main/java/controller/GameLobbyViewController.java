package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.ETankApplication;
import model.game.logic.GameLobby;
import model.game.logic.GamePlay;
import model.game.logic.Player;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.io.IOException;
import java.util.Objects;


public class GameLobbyViewController {


    SocketClient sc = new SocketClient(this);
    Thread messageReceive = new Thread(sc);
    private ETankApplication eTankApplication;
    private GameLobby selectedLobby;

    ObservableList<GameLobby> lobbyList = FXCollections.observableArrayList();

    @FXML
    private TableView<GameLobby> lobbyTable;
    @FXML
    private TableColumn<GameLobby, String> columnLobbyNumber;
    @FXML
    private TableColumn<GameLobby, Integer> columnLobbySeats;

    @FXML
    private GridPane playerGrid;

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

    @FXML
    private Button btnSetHostRdy;
    @FXML
    private Button btnSetJoinRdy;
    @FXML
    private Button btnGameStart;

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
        resetViews();
        sendExtendUserData();
        selectedLobby = new GameLobby();
        selectedLobby.buildLobbyID();
        registerLobby(selectedLobby);
        showLobbyHostView(selectedLobby);
    }

    @FXML
    private void joinGame() {
        resetViews();
        sendExtendUserData();
        getLobbyList();
        showJoinLobbyView();
        lobbyList.clear();
    }

    @FXML
    private void closeLobby() {
        switchToInit();
        for (GameLobby lobby : lobbyList) {
            if (lobby.getPlayers().get(0).getId() == eTankApplication.getSignedUser().getId()) {
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
    public void switchToGameView() throws IOException {
        Message msg = new Message();
        msg.setMessageType(MessageType.START_GAME);
        msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        msg.setPlayerIsRdy(true);
        msg.setPlayerImage("default");
        msg.setPayload("payload");
        sc.sendMsg(msg);
    }

    @FXML
    public void setRdy() {
        for (Player player : selectedLobby.getPlayers()) {
            if (player.getId() == eTankApplication.getSignedUser().getId()) {
                Message msg = new Message();
                msg.setMessageType(MessageType.RDY_STATUS);
                msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
                msg.setPlayerId(player.getId());
                msg.setPlayerPublicName(player.getPublicName());
                msg.setPlayerIsRdy(!player.isReady());
                msg.setPlayerImage("default");
                msg.setPayload("payload");
                sc.sendMsg(msg);
            }
        }
    }

    @FXML
    public void switchBackToInit() {
        switchToInit();
        for (GameLobby lobby : this.lobbyList) {
            for (Player player : lobby.getPlayers()) {
                if (player.getId() == eTankApplication.getSignedUser().getId()) {
                    lobby.removePlayer(player);
                }
            }
        }
    }

    @FXML
    private void joinSelectedGame() throws IOException {
        selectedLobby = new GameLobby();
        selectedLobby = lobbyTable.getSelectionModel().getSelectedItem();
        if (selectedLobby.getSeatCounter() < 4) {
            System.out.println("joining game");
            Message msg = new Message();
            msg.setMessageType(MessageType.JOIN);
            msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
            msg.setPlayerId(eTankApplication.getSignedUser().getId());
            msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
            msg.setPlayerIsRdy(false);
            msg.setPlayerImage("default");
            msg.setPayload("ES GEHT");
            sc.sendMsg(msg);
            resetViews();
            lblGameNumber.setText("Spielnummer: " + selectedLobby.getGameLobbyID());
            showLobbyJoinView();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Schlacht ist schon voll");
            alert.setContentText("Wähle eine andere oder erstelle selbst eine");
            alert.showAndWait();
        }

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
            msg.setPayload(textChatMsgField.getText());
            sc.sendMsg(msg);
            textChatMsgField.clear();
        }
    }

    @FXML
    private void refreshLobbyTable() {
        getLobbyList();
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

    private void processRegisterLobbyMsg(Message msg) throws IOException {
        Player player = new Player(msg.getPlayerId(), null, msg.getPlayerPublicName(), msg.getPlayerImage(), null, null);
        selectedLobby.getPlayers().add(player);
        fillPlayerGrid();
        textAreaChatField.appendText(msg.getPayload()  + "\n");
    }

    private void processStartGameMsg(Message msg) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    eTankApplication.showGameView(selectedLobby);
                    eTankApplication.getGameViewModel().setSocketClient(sc);
                   // eTankApplication.getGameViewModel().setLobby(selectedLobby);
                    sc.setGameViewModel(eTankApplication.getGameViewModel());
                    eTankApplication.getGameViewModel().startGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void processRdyStatusMsg(Message msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Player player : selectedLobby.getPlayers()) {
                    if (player.getId() == msg.getPlayerId()) {
                        player.setReady(msg.isPlayerIsRdy());
                    }

                    if (eTankApplication.getSignedUser().getId() == msg.getPlayerId()) {
                        if (player.isReady()) {
                            btnSetHostRdy.setText("Bereit!");
                            btnSetJoinRdy.setText("Bereit!");
                            btnSetHostRdy.setStyle("-fx-background-color: green;");
                            btnSetJoinRdy.setStyle("-fx-background-color: green;");
                        } else {
                            btnSetHostRdy.setText("Nicht Bereit!");
                            btnSetJoinRdy.setText("Nicht Bereit!");
                            btnSetHostRdy.setStyle("-fx-background-color: red;");
                            btnSetJoinRdy.setStyle("-fx-background-color: red;");
                        }
                    }
                }
                fillPlayerGrid();
                checkAllPlayerRdy();
            }
        });
    }

    private void checkAllPlayerRdy() {
        int playerNotRdy = 0;
        for (Player player : selectedLobby.getPlayers()) {
            if (!player.isReady()) {
                playerNotRdy++;
            }
        }
        btnGameStart.setDisable(playerNotRdy != 0);
    }

    private void processJoinedPlayerMsg(Message msg) throws IOException {
        Player player = new Player(msg.getPlayerId(), "", msg.getPlayerPublicName(), msg.getPlayerImage(), "", null);
        selectedLobby.addPlayer(player);
        fillPlayerGrid();
    }

    private void processChatMsg(Message msg) {
        textAreaChatField.appendText(msg.getPlayerPublicName() + ": " + msg.getPayload() + "\n");
    }

    private void processGetLobbiesMsg(Message msg) {
        GameLobby lobby = new GameLobby();
        lobby.setGameLobbyID(msg.getGameLobbyNumber());
        lobby.setSeatCounter(Integer.parseInt(msg.getPayload()));
        lobbyList.add(lobby);
        fillLobbyTable();
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
        msg.setPlayerImage("default");
        msg.setGameLobbyNumber(lobby.getGameLobbyID());
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        sc.sendMsg(msg);
    }

    public void fillLobbyTable() {
        columnLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty());
        columnLobbySeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    private void fillPlayerGrid() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int row = 0; row < selectedLobby.getPlayers().size(); row++) {
                    ImageView playerImage = new ImageView();
                    ImageView playerIsRdy = new ImageView();
                    playerImage.setFitHeight(40.0);
                    playerImage.setFitWidth(40.0);
                    playerIsRdy.setFitHeight(40.0);
                    playerIsRdy.setFitWidth(40.0);

                    Label playerNameLbl = new Label();
                    playerNameLbl.setText(selectedLobby.getPlayers().get(row).getPublicName());

                    if (selectedLobby.getPlayers().get(row).getUserImage().equals("default")) {
                        playerImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/default-user-image.png"))));
                    } else {
                        playerImage.setImage(getImageFromBase64String(selectedLobby.getPlayers().get(row).getUserImage()));
                    }

                    if (selectedLobby.getPlayers().get(row).isReady()) {
                        playerIsRdy.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/lobby/rdy.png"))));
                    } else {
                        playerIsRdy.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/lobby/notrdy.png"))));
                    }
                    playerGrid.add(playerImage, 0, row);
                    playerGrid.add(playerNameLbl, 1, row);
                    playerGrid.add(playerIsRdy, 2, row);
                }
            }
        });
    }

    private Image getImageFromBase64String(String userImage) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(userImage));
        return new Image(inputStream);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}
