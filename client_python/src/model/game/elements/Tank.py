from model.game.elements.LevelElement import LevelElement
from model.game.elements.LevelElementType import LevelElementType
from model.game.logic.GamePhysics import GamePhysics


class Tank (LevelElement):
    def __init__(self, imagePath, positionX, positionY, width, height, rotation):
        super().__init__(LevelElementType.TANK, imagePath, positionX, positionY, width, height, rotation)

        self.livePoints = 5
        self.isActive = True
        self.tankerId = None
        
    def moveUp(self):
        self.rect.move(self.positionY+GamePhysics.TANK_SPEED)

    def moveDown(self):
        self.rect.move(self.positionY-GamePhysics.TANK_SPEED)

    def moveLeft(self):
        self.rect.move(self.positionX-GamePhysics.TANK_SPEED)

    def moveRight(self):
        self.rect.move(self.positionX+GamePhysics.TANK_SPEED)

    def fireMainWeapon(self):
        pass

    def fireSecondaryWeapon(self):
        pass

