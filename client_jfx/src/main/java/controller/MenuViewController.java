package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.service.HttpRequest;

import java.io.IOException;

public class MenuViewController extends ViewController {

    HttpRequest httpRequest = new HttpRequest();

    /**
     * Exits the Game after alert is ok
     *
     * @param actionEvent
     */
    public void exitGame(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Beenden");
        alert.setContentText("Möchtest du das Spiel wirklich beenden?");
        if(alert.showAndWait().get() == ButtonType.OK){
            eTankApplication.getPrimaryStage().close();
        }
    }

    /**
     * Logout from the Game after alert is ok and shows LoginView
     *
     * @param actionEvent
     */
    public void logout(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Abmelden");
        alert.setContentText("Möchtest du dich wirklich ausloggen?");
        if(alert.showAndWait().get() == ButtonType.OK){
            eTankApplication.showLoginView();
        }
    }

    public void setHttpRequestETankapplication(){
        httpRequest.setETankApplication(eTankApplication);
    }

}
