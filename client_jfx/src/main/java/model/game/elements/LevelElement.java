package model.game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LevelElement extends ImageView {

    private LevelElementType type;

    /**
     * Constructor of the class LevelElement
     *
     * @param image         Image of the element
     * @param type          LevelElementType
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     */
    public LevelElement(Image image, LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super(image);
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    /**
     * Constructor of the class LevelElement
     * without image
     *
     * @param type          LevelElementType
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     * @param rotation      rotation of the ImageView
     */
    public LevelElement(LevelElementType type, double positionX, double positionY, double width, double height, double rotation) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutY(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setRotate(rotation);
    }

    /**
     * Constructor of the class LevelElement
     * without rotation
     *
     * @param image         Image of the element
     * @param type          LevelElementType
     * @param positionX     x position
     * @param positionY     y position
     * @param width         width of the ImageView
     * @param height        height of the ImageView
     */
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