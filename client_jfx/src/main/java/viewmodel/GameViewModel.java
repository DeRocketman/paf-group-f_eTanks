package viewmodel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.ETankApplication;
import model.game.logic.GameLobby;
import view.GameView;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GameViewModel implements ViewModel {
    ETankApplication eTankApplication;
    GameLobby gameLobby;

    double mapWidth = 1200;
    double mapHeight = 800;
    int speed = 5;

    ObservableList<StackPane> elementList = FXCollections.observableArrayList();
    ObservableList<ImageView> objectList = FXCollections.observableArrayList();

 //   @FXML
 //   private Pane elementPane;

    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.W) {
            movePlayer(0,0);
        }
        if (keyEvent.getCode() == KeyCode.S) {
            movePlayer(0, 180);
        }
        if (keyEvent.getCode() == KeyCode.D) {
            movePlayer(0,90);
        }
        if (keyEvent.getCode() == KeyCode.A) {
            movePlayer(0, 270);
        }
        if (keyEvent.getCode() == KeyCode.SPACE) {
            System.out.println("FEUERTASTE: " + keyEvent.getCode());
            shoot(0);
        }
    }


    public void RotateTransition(Group myTank, double newCourse) {

        RotateTransition rt = new RotateTransition(Duration.seconds(0.2), myTank);
        rt.setFromAngle(myTank.getRotate());
        rt.setToAngle(newCourse);

        rt.play();

    }

    public void movePlayer(int player, int course){
        if(course == 0){
            elementList.get(player).setRotate(0);
            elementList.get(player).setLayoutY(elementList.get(player).getLayoutY() - speed);
        } else if (course == 90){
            elementList.get(player).setRotate(90);
            elementList.get(player).setLayoutX(elementList.get(player).getLayoutX() + speed);
        } else if(course==180){
            elementList.get(player).setRotate(180);
            elementList.get(player).setLayoutY(elementList.get(player).getLayoutY() + speed);
        }else if (course==270){
            elementList.get(player).setRotate(270);
            elementList.get(player).setLayoutX(elementList.get(player).getLayoutX() - speed);
        }
    }

    public void shoot(int player){
        double startPositionX = elementList.get(player).getLayoutX();
        double startPositionY = elementList.get(player).getLayoutY();

        StackPane bulletPane = new StackPane();
        bulletPane.setLayoutX(startPositionX);
        bulletPane.setLayoutY(startPositionY);
        bulletPane.setPrefHeight(40.0);
        bulletPane.setPrefWidth(40.0);

        ImageView bullet = new ImageView();
        bullet.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/bullets/Medium_Shell.png"))));
        bullet.setFitWidth(40.0);
        bullet.setFitHeight(40.0);
        bullet.setPickOnBounds(true);
        //Korrekte Proportionen
        bullet.setPreserveRatio(true);


        bulletPane.getChildren().add(bullet);

        elementList.add(bulletPane);
        objectList.add(bullet);
        System.out.println("Bild erstellt");

        //Rotation
        double rotation = elementList.get(player).getRotate();
        moveBullet(elementList.size() -1, rotation);
    }

    public void moveBullet(int elementListNo, double rotation) {
        double positionX = elementList.get(elementListNo).getLayoutX();
        double positionY = elementList.get(elementListNo).getLayoutY();

        elementList.get(elementListNo).setRotate(rotation);

        switch ((int) rotation){
            case 0:
                System.out.println("Bullet Startposition: " + positionY);
                System.out.println("Bullet up");
                for(double newPosition = positionY; newPosition > 0.0 ; newPosition--){
                    //TODO: Pause zwischen einzelnen Positionen
                    elementList.get(elementListNo).setLayoutY(newPosition);
                    System.out.println("Bullet Position: " + newPosition);
                }
                //TODO: Remove Bullet
                break;
            case 90:
                System.out.println("Bullet right");
                System.out.println("Bullet Position: " + positionX);
                for(double newPosition = positionX; newPosition < mapWidth; newPosition++){
                    elementList.get(elementListNo).setLayoutX(newPosition);
                    System.out.println("Bullet Position: " + newPosition);
                }
                break;
            case 180:
                System.out.println("Bullet down");
                System.out.println("Bullet Startposition: " + positionY);
                for(double newPosition = positionY; newPosition < mapHeight ; newPosition++){
                    elementList.get(elementListNo).setLayoutY(newPosition);
                    System.out.println("Bullet Position: " + newPosition);
                }
                break;
            case 270:
                System.out.println("Bullet left");
                System.out.println("Bullet Position: " + positionX);
                for(double newPosition = positionX; newPosition > 0.0 ; newPosition--){
                    elementList.get(elementListNo).setLayoutX(newPosition);
                    System.out.println("Bullet Position: " + newPosition);
                }
                break;
        }
    }

    public void setElementList(ObservableList<StackPane> elementList) {
        this.elementList = elementList;
    }

    public void setETankApplication(ETankApplication eTankApplication) {
        this.eTankApplication = eTankApplication;
    }

    public void setGame(GameLobby gameLobby) {
        this.gameLobby = gameLobby;
    }
}
