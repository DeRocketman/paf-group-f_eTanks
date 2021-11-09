package model;

public class Tank extends LevelElement{
    private String publicName;

    public Tank(String publicName, int id, int positionX, int positionY, String shapePath, boolean isVisible) {
        super(id, positionX, positionY, shapePath, isVisible);
        this.publicName = publicName;
    }

    public void shootMainWeapon() {

    }

    public void shootSecondaryWeapon() {

    }

    public boolean loadWeapon() {
        return true;
    }

    public void driveUp() {

    }

    public void driveDown() {

    }

    public void driveLeft() {

    }

    public void driveRight() {

    }


}
