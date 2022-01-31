package model.game.elements;

import javafx.scene.image.Image;
import model.data.User;

public class LevelElementFactory {


     /**
     * @param type
     * @param posX
     * @param posY
     * @param imagePaths
     * @param isVisible
     * @param course
     * @param tankCommander
     * @param firedTank
     * @param lives
     * @return
     */
    public static LevelElement createLevelElement(LevelElementType type, double posX , double posY,
                                                  String[] imagePaths, boolean isVisible, double course,
                                                  User tankCommander, Tank firedTank, int lives) {
       /* if (type == LevelElementType.TANK) {
            return new Tank(posX, posY, imagePaths, isVisible, course, tankCommander);
        } else if (type== LevelElementType.BULLETMAINWEAPON) {
            return new BulletMainWeapon(posX, posY, imagePaths, isVisible, course, firedTank);
        } else if (type== LevelElementType.ITEM) {
            return new Item(posX, posY, imagePaths, isVisible);
        } else if (type== LevelElementType.BLOCK) {
            return new Block(posX, posY, imagePaths, isVisible, lives);
        } else if (type== LevelElementType.BULLETSECONDARYWEAPON) { //Todo: Ist das wirklich notwendig?
            return new BulletSecondaryWeapon(posX, posY, imagePaths, isVisible, course, firedTank);
        }*/
        return null;
    }


    public static LevelElement createLevelElement(LevelElementType type, Image image,
                                                  double posX, double posY, double width, double height,
                                                  double rotation, int playerId, Tank firingTank) {
        if (type == LevelElementType.TANK) {
            return new Tank(image, posX, posY, width, height, rotation, playerId);
        } else if (type== LevelElementType.BULLETMAINWEAPON) {
            return new BulletMainWeapon(posX, posY, width, height, rotation, firingTank);
        } /*else if (type== LevelElementType.ITEM) {
            return new Item(posX, posY, imagePaths, isVisible);
        } else if (type== LevelElementType.BLOCK) {
            return new Block(posX, posY, imagePaths, isVisible, lives);
        } else if (type== LevelElementType.BULLETSECONDARYWEAPON) { //Todo: Ist das wirklich notwendig?
            return new BulletSecondaryWeapon(posX, posY, imagePaths, isVisible, course, firedTank);
        }
        */
        return null;
    }

    public static BulletMainWeapon createLevelElement(LevelElementType type, double posX, double posY, double width,
                                                      double height, double rotation, Tank firingTank) {
        if (type== LevelElementType.BULLETMAINWEAPON) {
            return new BulletMainWeapon(posX, posY, width, height, rotation, firingTank);
        } return null;
    }
}
