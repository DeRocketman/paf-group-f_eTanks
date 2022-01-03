package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.data.GameStatistic;
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
        setHttpRequestETankapplication();

        int totalDeaths = 0;
        int totalGamePoints = 0;
        int totalGameWins = 0;
        int totalRoundWIns = 0;
        int totalHitPoints = 0;
        double totalHitRate = 0;
        double totalKillDeathRate = 0;
        int totalKills = 0;
        int totalPlayedGames = 0;
        int totalShots = 0;

        if(httpRequest.getGameStatisticList(eTankApplication.getSignedUser().getId())){
            for(GameStatistic gameStatistic : eTankApplication.getGameStatistics()) {
                totalPlayedGames++;
                totalDeaths = totalDeaths + gameStatistic.getDeaths();
                totalGamePoints = totalGamePoints + gameStatistic.getGamePoints();
                totalRoundWIns = totalRoundWIns + gameStatistic.getRoundWins();
                totalHitPoints = totalHitPoints + gameStatistic.getHitPoints();
                totalKills = totalKills + gameStatistic.getKills();
                totalShots = totalShots + gameStatistic.getShots();

                if(gameStatistic.isWinner()){
                    totalGameWins++;
                }
            }

            totalHitRate = (double) totalHitPoints / (double) totalShots;
            totalKillDeathRate = (double) totalKills / (double)totalDeaths;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Die Statistik Daten konnten leider nicht geladen werden.");
            alert.showAndWait();
        }

        deaths.setText(String.valueOf(totalDeaths));
        gamePoints.setText(String.valueOf(totalGamePoints));
        gameWins.setText(String.valueOf(totalGameWins));
        roundWins.setText(String.valueOf(totalRoundWIns));
        hitPoints.setText(String.valueOf(totalHitPoints));

        hitRate.setText(String.valueOf(Math.round(totalHitRate*100)/100.0));
        killDeathRate.setText(String.valueOf(Math.round(totalKillDeathRate*100)/100.0));

        kills.setText(String.valueOf(totalKills));
        playedGames.setText(String.valueOf(totalPlayedGames));
        shots.setText(String.valueOf(totalShots));
    }
}
