package model.game.elements;

import javafx.scene.image.ImageView;

public abstract class LevelElement extends ImageView {

    private String type;

    public LevelElement(double positionX, double positionY, String[] imagePaths, boolean isVisible) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.imagePaths = imagePaths;
        this.isVisible = isVisible;
    }

    public LevelElement(String imagePath, String type, double positionX, double positionY, double width, double height) {
        super();
        this.type = type;
        this.setLayoutX(positionX);
        this.setLayoutX(positionY);
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    public double[] getPositions() {
        return new double[]{this.getFitHeight(), this.getFitWidth()};
    }
}