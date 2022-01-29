from model.game.elements.LevelElement import LevelElement


class Tank (LevelElement):
    def __init__(self, imagePath, positionX, positionY, width, height, rotation):
        super().__init__(imagePath, positionX, positionY, width, height, rotation)
        
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

