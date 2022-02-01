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
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.io.IOException;
import java.util.Objects;

import main.ETankApplication;
import model.game.logic.GameLobby;
import model.game.logic.Player;
import model.service.HttpRequest;
import model.service.Message;
import model.service.MessageType;
import model.service.SocketClient;
import org.boon.core.Sys;

public class GameLobbyViewController {

    SocketClient sc = new SocketClient(this);
    HttpRequest httpRequest = new HttpRequest();
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

    /**
     * Instantiates a new Game lobby view controller
     *
     * @throws IOException the io exception
     */
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
    private void hostGame() {
        resetViews();
        sendExtendUserData();
        selectedLobby = new GameLobby();
        selectedLobby.buildLobbyID();
        lobbyList.add(selectedLobby);
        registerLobby(selectedLobby);
        showLobbyHostView(selectedLobby);
    }

    @FXML
    private void joinGame() {
        resetViews();
        sendExtendUserData();
        getLobbyList();
        showJoinedLobbyView();
        lobbyList.clear();
    }

    /**
     * Closes the lobby if the host requested it
     */
    @FXML
    private void closeLobby() {

        GameLobby toRemove = new GameLobby();

        for (GameLobby lobby : lobbyList) {
            if (lobby.getPlayers().get(0).getId() == eTankApplication.getSignedUser().getId()) {

                toRemove = lobby;

                removeLobbyFromServer(lobby.getGameLobbyID());
            }
        }
        lobbyList.remove(toRemove);
        switchToInit();
    }

    /**
     * Switches back to main menu
     *
     * @throws IOException the io exception
     */
    @FXML
    public void switchBackToMainMenu() throws IOException {
        eTankApplication.showMenuView();
        sc.closeConnection();
    }

    /**
     * Switches to game view
     * and sends a START_GAME message to the SocketClient
     */
    @FXML
    public void switchToGameView() {
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

    /**
     * Sends a RDY_STATUS message to the SocketClient
     * with the information if player is ready or not
     */
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

    /**
     * Gets the Lobbylist
     */
    @FXML
    private void refreshLobbyTable() {
        getLobbyList();
    }

    /**
     * Sends a request to join the signedPlayer to the selected lobby
     */
    @FXML
    private void joinSelectedGame() {
        selectedLobby = new GameLobby();
        selectedLobby = lobbyTable.getSelectionModel().getSelectedItem();
        if (selectedLobby.getSeatCounter() < 4) {
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
            showJoinLobbyView();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Schlacht ist schon voll");
            alert.setContentText("Wähle eine andere oder erstelle selbst eine");
            alert.showAndWait();
        }
    }

    /**
     * Sends chat message to the SocketClient
     */
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

    /**
     * Hides all HBoxes and VBoxes
     */
    public void resetViews() {
        vbxLobby.setVisible(false);
        vbxJoin.setVisible(false);
        vbxInit.setVisible(false);
        hbxHostPanel.setVisible(false);
        hbxJoinerPanel.setVisible(false);
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

    /**
     * Sends a REGISTER_LOBBY message to the SocketClient
     *
     * @param lobby     the new lobby
     */
    public void registerLobby(GameLobby lobby) {
        Message msg = new Message();
        msg.setMessageType(MessageType.REGISTER_LOBBY);
        msg.setPlayerImage("default");
        msg.setGameLobbyNumber(lobby.getGameLobbyID());
        msg.setPlayerId(eTankApplication.getSignedUser().getId());
        msg.setPlayerPublicName(eTankApplication.getSignedUser().getPublicName());
        sc.sendMsg(msg);
    }

    public void removeLobbyFromServer(String lobbyNumber){
        System.out.println("in removeLobbyFromServer");
        Message msg = new Message();
        msg.setGameLobbyNumber(lobbyNumber);
        msg.setMessageType(MessageType.REMOVE_LOBBY);
        sc.sendMsg(msg);
    }

    /**
     * Receive lobby messages from the SocketClient
     * and calls the appropriate functions to handle the message
     *
     * @param msg           the message
     * @throws IOException  the IO exception
     */
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
            if (msg.getMessageType() == MessageType.WHAT_IS_YOUR_STATUS) {
                processWhatIsYourStatus(msg);
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

    /**
     * is called when a WHAT_IS_YOUR_STATUS Message is received, sends the own Status to the
     * Server if you are not the one who asked for it
     *
     * @param msgIn
     */
    private void processWhatIsYourStatus(Message msgIn) {
        if (msgIn.getPlayerId() != eTankApplication.getSignedUser().getId()) {
            for (Player player : selectedLobby.getPlayers()) {
                if (player.getId() == eTankApplication.getSignedUser().getId()) {
                    Message msg = new Message();
                    msg.setMessageType(MessageType.RDY_STATUS);
                    msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
                    msg.setPlayerId(player.getId());
                    msg.setPlayerPublicName(player.getPublicName());
                    msg.setPlayerIsRdy(player.isReady());
                    msg.setPlayerImage("default");
                    msg.setPayload("payload");
                    sc.sendMsg(msg);
                }
            }
        }
    }

    /**
     * sends a Message to the Server and asks the other participants for their Status
     */
    public void whatIsYourStatus() {
        for (Player player : selectedLobby.getPlayers()) {
            if (player.getId() == eTankApplication.getSignedUser().getId()) {
                Message msg = new Message();
                msg.setMessageType(MessageType.WHAT_IS_YOUR_STATUS);
                msg.setGameLobbyNumber(selectedLobby.getGameLobbyID());
                msg.setPlayerId(player.getId());
                msg.setPlayerPublicName(player.getPublicName());
                msg.setPlayerIsRdy(false);
                msg.setPlayerImage("default");
                msg.setPayload("payload");
                sc.sendMsg(msg);
            }
        }
    }

    /**
     * Assigns a new player to a lobby
     *
     * @param msg   the message from SocketClient
     */
    private void processRegisterLobbyMsg(Message msg) {
        Player player = new Player(msg.getPlayerId(), null, msg.getPlayerPublicName(), msg.getPlayerImage(), null, null);
        selectedLobby.getPlayers().add(player);
        fillPlayerGrid(false);
        textAreaChatField.appendText(msg.getPayload()  + "\n");
    }

    /**
     * Switches to GameView,
     * hands over the selectedLobby and SocketClient
     * and starts the Game
     *
     * @param msg   the message from SocketClient
     */
    private void processStartGameMsg(Message msg) {
        Platform.runLater(() -> {
            try {
                eTankApplication.showGameView(selectedLobby);
                eTankApplication.getGameViewModel().setSocketClient(sc);
                sc.setGameViewModel(eTankApplication.getGameViewModel());
                eTankApplication.getGameViewModel().startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Sets the status of a player by changing the button color
     *
     * @param msg   the message from SocketClient
     */
    private void processRdyStatusMsg(Message msg) {
        Platform.runLater(() -> {
            for (Player player : selectedLobby.getPlayers()) {
                if (player.getId() == msg.getPlayerId()) {
                    player.setReady(msg.isPlayerIsRdy());
                }

                if (eTankApplication.getSignedUser().getId() == msg.getPlayerId() && eTankApplication.getSignedUser().getId() == player.getId()) {
                    if (player.isReady()) {
                        btnSetHostRdy.setStyle("-fx-background-color: red;");
                        btnSetJoinRdy.setStyle("-fx-background-color: red;");
                    } else {
                        btnSetHostRdy.setStyle("-fx-background-color: green;");
                        btnSetJoinRdy.setStyle("-fx-background-color: green;");
                    }
                }
            }
            fillPlayerGrid(true);
            checkAllPlayerRdy();
        });
    }

    /**
     * Adds a player to the selected lobby
     *
     * @param msg   the message from SocketClient
     */
    private void processJoinedPlayerMsg(Message msg) {
        Player player = new Player(msg.getPlayerId(), "", msg.getPlayerPublicName(), msg.getPlayerImage(), "", null);
        selectedLobby.addPlayer(player);
        whatIsYourStatus();
        fillPlayerGrid(false);
    }

    /**
     * Displays a new chat message
     *
     * @param msg   the message from SocketClient
     */
    private void processChatMsg(Message msg) {
        textAreaChatField.appendText(msg.getPlayerPublicName() + ": " + msg.getPayload() + "\n");
    }

    /**
     * Adds new Lobby to the lobbylist
     * and adds it to the lobby table
     *
     * @param msg   the message from SocketClient
     */
    private void processGetLobbiesMsg(Message msg) {
        lobbyList.clear();
        GameLobby lobby = new GameLobby();
        lobby.setGameLobbyID(msg.getGameLobbyNumber());
        lobby.setSeatCounter(Integer.parseInt(msg.getPayload()));
        lobbyList.add(lobby);
        fillLobbyTable();
    }

    /**
     * Checks if the status of all players is ready
     */
    private void checkAllPlayerRdy() {
        int playerNotRdy = 0;
        for (Player player : selectedLobby.getPlayers()) {
            if (!player.isReady()) {
                playerNotRdy++;
            }
        }
        btnGameStart.setDisable(playerNotRdy != 0);
    }

    /**
     * Displays the lobby from host perspective
     *
     * @param lobby
     */
    private void showLobbyHostView(GameLobby lobby) {
        resetViews();
        vbxLobby.setVisible(true);
        hbxHostPanel.setVisible(true);
        lblGameNumber.setText("Spielnummer: " + lobby.getGameLobbyID());
    }

    /**
     * Displays the joined lobby
     */
    private void showJoinedLobbyView() {
        resetViews();
        vbxJoin.setVisible(true);
    }

    /**
     * Shows the Join Lobby View
     */
    private void showJoinLobbyView() {
        resetViews();
        vbxLobby.setVisible(true);
        hbxJoinerPanel.setVisible(true);
    }

    /**
     * Sends a request to the SocketClient to get the list of lobbies
     */
    public void getLobbyList() {
        Message msg = new Message();
        msg.setMessageType(MessageType.GET_LOBBIES);
        sc.sendMsg(msg);
    }

    /**
     * Fills lobby table
     */
    public void fillLobbyTable() {
        columnLobbyNumber.setCellValueFactory(cellData -> cellData.getValue().gameLobbyIDProperty());
        columnLobbySeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    /**
     * Fills the PlayerGrid with data from the Player List of a lobby
     */
    private void fillPlayerGrid(boolean setReady) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                httpRequest.setETankApplication(eTankApplication);
                for (int row = 0; row < selectedLobby.getPlayers().size(); row++) {

                    ImageView playerImage = new ImageView();
                    ImageView playerIsRdy = new ImageView();
                    playerImage.setFitHeight(40.0);
                    playerImage.setFitWidth(40.0);
                    playerIsRdy.setFitHeight(40.0);
                    playerIsRdy.setFitWidth(40.0);
                    Label playerNameLbl = new Label();
                    playerNameLbl.setText(selectedLobby.getPlayers().get(row).getPublicName());

                    if(!setReady){
                        String image = httpRequest.getImageById(selectedLobby.getPlayers().get(row).getId());
                        if (image.equals("default")) {
                            playerImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/default-user-image.png"))));
                        } else {
                            playerImage.setImage(decodeImage(image));
                        }
                        playerGrid.add(playerImage, 0, row);
                        playerGrid.add(playerNameLbl, 1, row);
                    }

                    if (selectedLobby.getPlayers().get(row).isReady()) {
                        playerIsRdy.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/lobby/rdy.png"))));
                    } else {
                        playerIsRdy.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("img/images/lobby/notrdy.png"))));
                    }
                    playerGrid.add(playerIsRdy, 2, row);
                }
            }
        });
    }

    /**
     * Decodes the base64Image to an Image
     *
     * @param base64Image   readable String of an image
     * @return              new Image from the String
     */
    private Image decodeImage(String base64Image) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(base64Image));
        return new Image(inputStream);
    }

    /**
     * Sets e tank application.
     *
     * @param eTankApplication the e tank application
     */
    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }
}
