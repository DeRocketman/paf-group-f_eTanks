package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.ETankApplication;
import model.Game;
import model.GameCreator;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class GameCreatorViewController {

    @FXML
    private Label labelUserName1;
    @FXML
    private Label labelUserName2;
    @FXML
    private Label labelUserName3;
    @FXML
    private Label labelUserName4;
    @FXML
    private GridPane gridPaneHosts;
    @FXML
    private TableView<Game> tableGameList;
    @FXML
    private Button btnGameStart;
    @FXML
    private Button btnJoin1;
    @FXML
    private Button btnJoin2;
    @FXML
    private Button btnJoin3;
    @FXML
    private Button btnJoinSelectedGame;
    @FXML
    private ImageView imgViewHost;
    @FXML
    private ImageView imgViewMember1;
    @FXML
    private ImageView imgViewMember2;
    @FXML
    private ImageView imgViewMember3;
    @FXML
    private TableColumn<Game, Integer> columnGameNumber;
    @FXML
    private TableColumn<Game, Integer> columnGameSeats;


    private ETankApplication eTankApplication;
    //TODO: Nur zum Testen erst einmal und solange Backend, Sockets ect nicht implementiert sind
    GameCreator gc = new GameCreator();

    public GameCreatorViewController() {
    }

    @FXML
    private void initialize() {
        columnGameNumber.setCellValueFactory(cellData -> cellData.getValue().gameIdProperty().asObject());
        columnGameSeats.setCellValueFactory(cellData -> cellData.getValue().seatCounterProperty().asObject());
    }

    @FXML
    public void hostGame() {
        Game game = new Game(100003);
        game.addHost(gc.getSignedUser());
        setDefaultView();
        gridPaneHosts.setVisible(true);
        boolean startIsDisabled = (game.getParticipants().size() == 0 && gc.getSignedUser() != game.getHost().get(0));
        btnGameStart.setDisable(startIsDisabled);

        labelUserName1.setText(game.getHost().get(0).getPublicName());
        imgViewHost.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(game.getHost().get(0).getImage()))));
    }
    @FXML
    public void joinGame() {
        setDefaultView();
        tableGameList.setVisible(true);
        btnJoinSelectedGame.setVisible(true);
        btnJoinSelectedGame.setDisable(false);
    }

    @FXML
    public void switchBack() throws IOException {
        eTankApplication.showMenuView();
    }

    @FXML
    public void switchToGameView() throws IOException{
        //TODO: Muss gefixt werden, da Views Übereinander liegen!
        eTankApplication.showGameView();
        eTankApplication.showLevelOneView();
    }

    @FXML
    public void joinAsMember1() {
        Game selectedGame = tableGameList.getSelectionModel().getSelectedItem();
        selectedGame.addParticipants(gc.getSignedUser());
        btnJoin1.setDisable(true);
        labelUserName2.setText(gc.getSignedUser().getPublicName());
        imgViewMember1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(gc.getSignedUser().getImage()))));
    }

    @FXML
    public void joinAsMember2() {
        Game selectedGame = tableGameList.getSelectionModel().getSelectedItem();
        selectedGame.addParticipants(gc.getSignedUser());
        btnJoin2.setDisable(true);
        imgViewMember2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(gc.getSignedUser().getImage()))));
    }

    @FXML
    public void joinAsMember3() {
        Game selectedGame = tableGameList.getSelectionModel().getSelectedItem();
        selectedGame.addParticipants(gc.getSignedUser());
        btnJoin3.setDisable(true);
        imgViewMember3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(gc.getSignedUser().getImage()))));
    }
    //TODO: Refactorn unbedingt!!
    @FXML
    public void joinSelectedGame() {
        Game selectedGame = tableGameList.getSelectionModel().getSelectedItem();
        if (selectedGame.getSeatCounter() < 4) {
            setDefaultView();
            gridPaneHosts.setVisible(true);
            btnJoin1.setDisable(false);
            btnJoin2.setDisable(false);
            btnJoin3.setDisable(false);
            labelUserName1.setText(selectedGame.getHost().get(0).getPublicName());
            imgViewHost.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getHost().get(0).getImage()))));
            btnGameStart.setDisable(true);
            switch(selectedGame.getParticipants().size()) {
                case 3: labelUserName4.setText(selectedGame.getParticipants().get(2).getPublicName());
                        imgViewMember3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(2).getImage()))));
                        btnJoin3.setDisable(true);
                case 2: labelUserName3.setText(selectedGame.getParticipants().get(1).getPublicName());
                        imgViewMember2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(1).getImage()))));
                        btnJoin2.setDisable(true);
                case 1: labelUserName2.setText(selectedGame.getParticipants().get(0).getPublicName());
                        imgViewMember1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(0).getImage()))));
                        btnJoin1.setDisable(true);
                        break;
                default: break;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Game ist schon voll");
            alert.setHeaderText("Panzerfahrer, du kannst kein volles Game joinen");
            alert.setContentText("Bitte wählen ein anderes Game in der Tabelle aus oder hoste selbst eins");
            alert.showAndWait();
        }
    }

    @FXML
    public void setDefaultView() {
        gridPaneHosts.setVisible(false);
        tableGameList.setVisible(false);
        btnJoinSelectedGame.setVisible(false);
        btnJoinSelectedGame.setDisable(true);
        labelUserName1.setText("Free");
        labelUserName2.setText("Free");
        labelUserName3.setText("Free");
        btnJoin1.setDisable(true);
        btnJoin2.setDisable(true);
        btnJoin3.setDisable(true);
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
        tableGameList.setItems(gc.getGameList());
    }
}
