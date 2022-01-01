package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import main.ETankApplication;
import model.data.GameStatistic;
import model.service.HttpRequest;

import java.io.IOException;

public class MenuViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();

    public void newGameStatistic(ActionEvent actionEvent) {
        //GameStatistic gameStatistic = new GameStatistic(0, eTankApplication.getSignedUser(), false, 0, 200, 1, 0, 20, 1 );

        GameStatistic gameStatistic = new GameStatistic();
        gameStatistic.setDefaultStatistic();

        eTankApplication.getSignedUser().setGameStatistics(gameStatistic);

        setHttpRequestETankapplication();

        //if(httpRequest.saveGameStatistic(gameStatistic, eTankApplication.getSignedUser())){
        if(httpRequest.saveGameStatistic(gameStatistic, eTankApplication.getSignedUser().getId())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen wurden erfolgreich gespeichert!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deine Änderungen konnten nicht gespeichert werden!");
            alert.showAndWait();
        }
    }

    public void logout(){
        //TODO
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

}
