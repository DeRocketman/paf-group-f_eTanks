from model.game.elements.Tank import Tank
from model.game.logic.GamePhysics import GamePhysics

import pygame as pg


class GameView:
    def __init__(self):
        self.WIDTH = GamePhysics.WINDOW_WIDTH
        self.HEIGHT = GamePhysics.WINDOW_HEIGHT
        self.tankList = []

    def drawWindow(self):
        return pg.display.set_mode((self.WIDTH, self.HEIGHT))

    def drawLvl(self):
        pass

    def drawTanks(self, playerCount):
        if playerCount < 2:
            tank1 = Tank("../resources/images/tank/Tank_01.png", 100.0, 700.0, 40.0, 40.0, 360.0)
            self.tankList.append(tank1)
        if playerCount < 3:
            tank2 = Tank("../resources/images/tank/Tank_02.png", 1060.0, 700.0, 40.0, 40.0, 360.0)
            self.tankList.append(tank2)
        if playerCount < 4:
            tank3 = Tank("../resources/images/tank/Tank_03.png", 100.0, 60.0, 40.0, 40.0, 180.0)
            self.tankList.append(tank3)
        if playerCount == 4:
            tank4 = Tank("../resources/images/tank/Tank_04.png", 1090.0, 60.0, 40.0, 40.0, 180.0)
            self.tankList.append(tank4)

        return self.tankList

    def drawBullets(self):
        pass
