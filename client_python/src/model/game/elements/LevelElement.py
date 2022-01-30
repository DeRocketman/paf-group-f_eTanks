import pygame as pg


class LevelElement(pg.sprite.Sprite):
    def __init__(self, elementType,imagePath, positionX, positionY, width, height, rotation):
        pg.sprite.Sprite.__init__(self)

        self.type = elementType
        self.imagPath = imagePath
        self.positionX = positionX
        self.positionY = positionY
        self.rotation = rotation

        self.image, self.rect = pg.image.load(imagePath)
        pg.transform.scale(self.image, (width, height))
        pg.transform.rotate(self.image, rotation)


