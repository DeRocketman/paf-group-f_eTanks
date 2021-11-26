package view;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.internal.viewloader.View;
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
    @FXML
    private GridPane ground;


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
}
