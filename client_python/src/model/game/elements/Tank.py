from model.game.elements.LevelElement import LevelElement
from model.game.elements.LevelElementType import LevelElementType


class Tank (LevelElement):
    def __init__(self, imagePath, positionX, positionY, width, height, rotation):
        super().__init__(LevelElementType.TANK, imagePath, positionX, positionY, width, height, rotation)
        
    def moveUp(self):
        pass

    def moveDown(self):
        pass

    def moveLeft(self):
        pass

    def moveRight(self):
        pass

    def fireMainWeapon(self):
        pass

    def fireSecondaryWeapon(self):
        pass

