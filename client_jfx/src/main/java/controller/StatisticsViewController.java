package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    public void initialiseUserData(){
        deaths.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getDeaths()));
        gamePoints.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getGamePoints()));
        gameWins.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getGameWins()));
        roundWins.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getRoundWins()));
        hitPoints.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getHitPoints()));
        hitRate.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getHitRate()));
        killDeathRate.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getKillDeathRate()));
        kills.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getKills()));
        playedGames.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getPlayedGames()));
        shots.setText(String.valueOf(eTankApplication.getSignedUser().getUserStatistic().getShots()));
    }
}
