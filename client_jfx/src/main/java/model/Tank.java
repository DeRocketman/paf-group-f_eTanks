package model;

public class Tank extends LevelElement{
    private final User tankCommander;

    public Tank(double positionX, double positionY, String[] shapePaths, double rotations, boolean isVisible, User tankCommander) {
        super(positionX, positionY, shapePaths, rotations, isVisible);

        this.tankCommander = tankCommander;
    }


    public User getTankCommander() {
        return tankCommander;
    }
}
