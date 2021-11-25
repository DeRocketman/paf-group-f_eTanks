package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.ETankApplication;
import model.Game;
import model.GameCreator;
import java.io.IOException;
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
    private TableColumn<Game, Long> columnGameNumber;
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
        Game game = new Game(10000000);
        game.addHost(eTankApplication.getSignedUser());
        setDefaultView();
        gridPaneHosts.setVisible(true);
        boolean startIsDisabled = (game.getParticipants().size() == 0  && gc.getSignedUser().getId() == game.getHost().get(0).getId());
        System.out.println("Anzahl User:" + game.getSeatCounter() + " Hosts:" + game.getHost().size() + " Part:" + game.getParticipants().size());
        System.out.println(startIsDisabled);
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
        eTankApplication.showGameView();
    }

    //TODO: Refactorn unbedingt-> Elemente vielleicht automatisch erstellen lassen foreach?!
    @FXML
    public Game joinSelectedGame() {
        Game selectedGame = tableGameList.getSelectionModel().getSelectedItem();
        if (selectedGame.getSeatCounter() < 4) {
            selectedGame.addParticipants(gc.getSignedUser());
            setDefaultView();
            gridPaneHosts.setVisible(true);
            labelUserName1.setText(selectedGame.getHost().get(0).getPublicName());
            imgViewHost.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getHost().get(0).getImage()))));
            btnGameStart.setDisable(true);
            if (selectedGame.getParticipants().size() == 1) {
                labelUserName2.setText(selectedGame.getParticipants().get(0).getPublicName());
                imgViewMember1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(0).getImage()))));
            } else if (selectedGame.getParticipants().size() == 2) {
                labelUserName2.setText(selectedGame.getParticipants().get(0).getPublicName());
                imgViewMember1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(0).getImage()))));
                labelUserName3.setText(selectedGame.getParticipants().get(1).getPublicName());
                imgViewMember2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(1).getImage()))));
            } else if (selectedGame.getParticipants().size() == 3) {
                labelUserName2.setText(selectedGame.getParticipants().get(0).getPublicName());
                imgViewMember1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(0).getImage()))));
                labelUserName3.setText(selectedGame.getParticipants().get(1).getPublicName());
                imgViewMember2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(1).getImage()))));
                labelUserName4.setText(selectedGame.getParticipants().get(2).getPublicName());
                imgViewMember3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(selectedGame.getParticipants().get(2).getImage()))));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Game ist schon voll");
            alert.setHeaderText("Panzerfahrer, du kannst das Game joinen");
            alert.setContentText("Bitte wählen ein anderes Game aus oder hoste selbst eins");
            alert.showAndWait();
        }
        return selectedGame;
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
        labelUserName3.setText("Free");
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
        tableGameList.setItems(gc.getGameList());
    }
}
