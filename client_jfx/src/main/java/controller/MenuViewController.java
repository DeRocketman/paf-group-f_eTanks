package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;

public class MenuViewController extends ViewController {

    /**
     * Exits the Game after alert is ok
     */
    public void exitGame(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Beenden");
        alert.setContentText("Möchtest du das Spiel wirklich beenden?");
        if(alert.showAndWait().get() == ButtonType.OK){
            eTankApplication.getPrimaryStage().close();
        }
    }

    /**
     * Logout from the Game after alert is ok and shows LoginView
     */
    public void logout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Abmelden");
        alert.setContentText("Möchtest du dich wirklich ausloggen?");
        if(alert.showAndWait().get() == ButtonType.OK){
            eTankApplication.showLoginView();
        }
    }

    /**
     * Calls the parent function setHttpRequestETankapplication()
     */
    public void setHttpRequestETankapplication(){
        super.setHttpRequestETankapplication();
    }

}
