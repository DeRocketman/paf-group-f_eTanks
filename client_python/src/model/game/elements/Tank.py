from model.game.elements.LevelElement import LevelElement
from model.game.elements.LevelElementType import LevelElementType
from model.game.logic.GamePhysics import GamePhysics


class Tank(LevelElement):
    def __init__(self, imagePath, positionX, positionY, width, height, rotation):
        super().__init__(LevelElementType.TANK, imagePath, positionX, positionY, width, height, rotation)

        self.ownerId = None
        self.livePoints = 5
        self.isActive = True
        self.newPos = self.rect

    def moveUp(self):
        self.newPos = self.rect.move((0, -GamePhysics.TANK_SPEED))
        self.update()

    def moveDown(self):
        self.newPos = self.rect.move((0, GamePhysics.TANK_SPEED))
        self.update()

    def moveLeft(self):
        self.newPos = self.rect.move((-GamePhysics.TANK_SPEED, 0))
        self.update()

    def moveRight(self):
        self.newPos = self.rect.move((GamePhysics.TANK_SPEED, 0))
        self.update()

    def update(self):
        self.rect = self.newPos

    def fireMainWeapon(self):
        pass

    def fireSecondaryWeapon(self):
        pass
