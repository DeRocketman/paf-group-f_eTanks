package model;

public abstract class LevelElement {
    private double positionX;
    private double positionY;
    private String [] shapePaths;
    private double rotations;
    private boolean isVisible;

    public LevelElement(double positionX, double positionY, String[] shapePaths, double rotations, boolean isVisible) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.shapePaths = shapePaths;
        this.rotations = rotations;
        this.isVisible = isVisible;
    }

    public double[] getPosition() {
        return new double[]{this.positionX, this.positionY};
    }

    public void setPositionX(double[] positions) {
        this.positionX = positions[0];
        this.positionY = positions[1];
    }
}
