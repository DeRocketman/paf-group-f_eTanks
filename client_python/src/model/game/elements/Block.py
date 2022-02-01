from model.game.elements.LevelElementType import LevelElementType
from model.game.elements.LevelElement import LevelElement


class Block(LevelElement):
    def __init__(self, elementType, imagePath, positionX, positionY, width, height):
        super().__init__(elementType, imagePath, positionX, positionY, width, height, 360)

        if elementType == LevelElementType.BLOCK_METAL:
            self.lives = 10001
        elif elementType == LevelElementType.BLOCK_STONE:
            self.lives = 3
        elif elementType == LevelElementType.BLOCK_WOOD:
            self.lives = 1

