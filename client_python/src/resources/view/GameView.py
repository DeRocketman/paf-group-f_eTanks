import pygame.image
import pygame_gui as pgGUI
import pygame as pg
from pygame import QUIT, K_ESCAPE
from pygame.constants import KEYDOWN

from model.game.elements.Tank import Tank


class GameView:
    def __init__(self):
        pg.init()
        self.gameWindow = pg.display.set_mode((1400, 850))
        self.topPanel = pg.Surface((1400, 50))
        self.sidePanel = pg.Surface((200, 800))
        self.pitch = pg.Surface((1200, 800))
        self.topPanel.fill(pg.Color("#69583a"))
        self.sidePanel.fill(pg.Color("#69583a"))
        self.pitch.fill(pg.Color("#808080"))

        self.tanks = pg.sprite.Group()

        self.drawWindow()
        self.buildTanks(1)

    def drawWindow(self):
        icon = pygame.image.load("../images/eTanksTitle.png")
        pg.display.set_icon(icon)
        pg.display.set_caption("eTanks - PuF WiSe 21/22 - Gruppe F", "../images/eTanksTitle.png")
        self.gameWindow.blit(self.topPanel, (0, 0))
        self.gameWindow.blit(self.pitch, (0, 50))
        self.gameWindow.blit(self.sidePanel, (1200, 50))

    def drawLvl(self):
        for tank in self.tankList:
            pass

    def buildTanks(self, playerCount):
        if playerCount < 2:
            tank1 = Tank("../../resources/images/tank/Tank_01.png", 100.0, 700.0, 40.0, 40.0, 360.0)
        if playerCount < 3:
            tank2 = Tank("../resources/images/tank/Tank_02.png", 1060.0, 700.0, 40.0, 40.0, 360.0)
        if playerCount < 4:
            tank3 = Tank("../resources/images/tank/Tank_03.png", 100.0, 60.0, 40.0, 40.0, 180.0)
        if playerCount == 4:
            tank4 = Tank("../images/tank/Tank_04.png", 1090.0, 60.0, 40.0, 40.0, 180.0)

    def drawBullets(self):
        pass


if __name__ == '__main__':
    gv = GameView()
    while True:
        for event in pg.event.get():
            if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                pg.quit()

        pg.display.update()
