package view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.internal.viewloader.View;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import viewmodel.GameViewModel;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameView implements FxmlView<GameViewModel>, Initializable {
    @InjectViewModel
    private GameView gameView;

    @FXML
    private GridPane ground;
    @FXML
    private Group tank1;
    @FXML
    private Group tank2;
    @FXML
    private Group tank3;
    @FXML
    private Group tank4;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initLevel();
    }

    private void initLevel() {
        //Here you can build the 3 different levels!
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 30; col++) {
                ImageView cell = new ImageView();
                cell.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../img/images/levelBackground/Ground_Tile_02_A.png"))));
                cell.setFitHeight(40.0);
                cell.setFitWidth(40.0);
                ground.add(cell, col, row);
                System.out.println(col + " " +row);
            }
        }
    }

    private void initTank() {
        //Hier sollen die Panzer an die jeweiligen Level angepasst werden

    }

    private void initElements() {
        //Hier sollen die übrigen starren Items an die jeweiligen Level angepasst werden
    }
}
