package model.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelElement extends ImageView {

    private String type;

    public LevelElement(Image image, String type, double positionX, double positionY, double width, double height, double rotation) {
        super(image);
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    public LevelElement(String type, double positionX, double positionY, double width, double height, double rotation) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    public LevelElement(Image image, String type, double positionX, double positionY, double width, double height) {
        super(image);
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public LevelElement(String type, double positionX, double positionY, double width, double height) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getPositions() {
        return new double[]{this.getFitHeight(), this.getFitWidth()};
    }
}