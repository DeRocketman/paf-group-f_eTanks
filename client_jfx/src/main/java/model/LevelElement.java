package model;

public abstract class LevelElement {
    private double positionX;
    private double positionY;
    private String [] imagePaths;
    private boolean isVisible;

    public LevelElement(double positionX, double positionY, String[] imagePaths, boolean isVisible) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.imagePaths = imagePaths;
        this.isVisible = isVisible;
    }

    public double[] getPosition() {
        return new double[]{this.positionX, this.positionY};
    }


}
