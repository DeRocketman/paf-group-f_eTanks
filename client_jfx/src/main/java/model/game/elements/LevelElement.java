package model.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelElement extends ImageView {

    private LevelElementType type;

    public LevelElement(Image image, LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super(image);
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    public LevelElement(LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    public LevelElement(Image image, LevelElementType type, double positionX, double positionY, double width, double height) {
        super(image);
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public LevelElementType getType() {
        return type;
    }

    public void setType(LevelElementType type) {
        this.type = type;
    }
}