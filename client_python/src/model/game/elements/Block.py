from model.game.elements.LevelElement import LevelElement


class Block(LevelElement):
    def __init__(self, imagePath, positionX, positionY, width, height, rotation):
        super().__init__(imagePath, positionX, positionY, width, height, rotation)

