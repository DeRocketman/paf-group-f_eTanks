package model;

public class Tank extends LevelElement{
    private String publicName;

    public Tank(String publicName, int id, int positionX, int positionY, String shapePath, boolean isVisible) {
        super(id, positionX, positionY, shapePath, isVisible);
        this.publicName = publicName;
    }

    public void shootMainWeapon(int direction) {

    }

    public void shootSecondaryWeapon(int direction) {

    }

    public boolean loadWeapon(int loadTime) {
        return true;
    }

    public void driveUp(int course) {
            if (course == 0) {
                GamePhysics.moveTank(0);
            } else if (course == 180) {
                GamePhysics.turnTank(-180);
                GamePhysics.moveTank(0);
            } else if (course == 90) {
                GamePhysics.turnTank(-180);
                GamePhysics.moveTank(0);
            }
    }

    public void driveDown(int course, int speed) {

    }

    public void driveLeft(int course, int speed) {

    }

    public void driveRight(int course, int speed) {

    }

    public void turnTank(int direction, int speed) {

    }


}
