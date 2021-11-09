package model;

public abstract class LevelElement {
    private int id;
    private int positionX;
    private int positionY;
    private String shapePath;
    private boolean isVisible;

    public LevelElement(int id, int positionX, int positionY, String shapePath, boolean isVisible) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.shapePath = shapePath;
        this.isVisible = isVisible;
    }
    

    public int[] getPosition() {
        return new int[]{this.positionX, this.positionY};
    }

}
