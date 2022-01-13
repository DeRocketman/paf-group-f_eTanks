package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.data.GameStatistic;
import model.service.HttpRequest;

public class MenuViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();

    public void newGameStatistic(ActionEvent actionEvent) {
        //GameStatistic gameStatistic = new GameStatistic(0, eTankApplication.getSignedUser(), false, 0, 200, 1, 0, 20, 1 );

        GameStatistic gameStatistic = new GameStatistic(6, eTankApplication.getSignedUser().getId(), true, 1, 55, 2, 5, 26, 45);
        //gameStatistic.setDefaultStatistic();
        gameStatistic.setUserId(eTankApplication.getSignedUser().getId());

        setHttpRequestETankapplication();

        System.out.println(eTankApplication.getSignedUser().getId());
        System.out.println(gameStatistic.getUserId());

        if(httpRequest.saveGameStatistic(gameStatistic, eTankApplication.getSignedUser().getId())){
            eTankApplication.getGameStatistics().add(gameStatistic);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen konnten nicht gespeichert werden!");
            alert.showAndWait();
        }
    }

    public void startGame(ActionEvent actionEvent){
          eTankApplication.showGameView();
    }

    public void exitGame(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Beenden");
        alert.setContentText("Möchtest du das Spiel wirklich beenden?");
        if(alert.showAndWait().get() == ButtonType.OK){
            eTankApplication.getPrimaryStage().close();
        }
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

}
