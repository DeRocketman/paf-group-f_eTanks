package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

    public void logout(){
        //TODO
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

}
