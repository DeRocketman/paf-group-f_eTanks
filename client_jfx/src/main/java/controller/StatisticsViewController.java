package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.service.HttpRequest;

public class StatisticsViewController extends ViewController {

    @FXML
    private Label deaths;
    @FXML
    private Label gamePoints;
    @FXML
    private Label gameWins;
    @FXML
    private Label roundWins;
    @FXML
    private Label hitPoints;
    @FXML
    private Label hitRate;
    @FXML
    private Label killDeathRate;
    @FXML
    private Label kills;
    @FXML
    private Label playedGames;
    @FXML
    private Label shots;

    public void initialiseUserData() {
    }
}
