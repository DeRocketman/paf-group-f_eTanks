package model.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class LevelElementImage extends ImageView {

    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LevelElementImage(String imagePath, String type, double positionX, double positionY, double width, double height) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutX(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public LevelElementImage(Image image, String type, double positionX, double positionY, double width, double height) {
        super(image);
        // this.setImage(new Image(getClass().getResource("default-user-image.png").toExternalForm()));
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutX(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

}
