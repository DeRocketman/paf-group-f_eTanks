package model.game.elements;

import model.data.User;

public class LevelElementFactory {


    //TODO: REfactorying -> verschi. Block types, Tank type: tank_1, tank_2 , ...

    /**
     *
     *
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


    public static LevelElement createLevelElement(LevelElementType type, double posX , double posY,
                                                  String[] imagePaths, boolean isVisible, double course, int lives) {
      /*  if (type == LevelElementType.TANK) {
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




}
