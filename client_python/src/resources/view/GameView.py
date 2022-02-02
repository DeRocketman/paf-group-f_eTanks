import pygame.image
import pygame as pg

from model.game.elements.Tank import Tank
from model.game.logic.Level import LevelBuilder


class GameView:
    def __init__(self):
        pg.init()
        self.gameWindow = pg.display.set_mode((1400, 850), pg.SCALED)
        self.topPanel = pg.Surface((1400, 50))
        self.sidePanel = pg.Surface((200, 800))
        self.pitch = pg.Surface((1200, 800)).convert()
        self.topPanel.fill(pg.Color("#69583a"))
        self.sidePanel.fill(pg.Color("#69583a"))
        self.pitchGround = None
        self.myTank = None
        self.allsprites = None
        self.tanks = pg.sprite.Group()
        self.elements = pg.sprite.Group()

    def drawWindow(self):
        icon = pygame.image.load("../resources/images/menuBackground/eTanksTitle.png")
        pg.display.set_icon(icon)
        pg.display.set_caption("eTanks - PuF WiSe 21/22 - Gruppe F", "../resources/images/eTanksTitle.png")
        self.gameWindow.blit(self.topPanel, (0, 0))
        self.gameWindow.blit(self.pitch, (0, 50))
        self.gameWindow.blit(self.sidePanel, (1200, 50))

    def drawLvl(self, lvlNumber):
        lvlBuilder = LevelBuilder()
        self.pitchGround = pg.image.load(lvlBuilder.buildLvl(lvlNumber).backgroundPath).convert_alpha()
        self.pitchGround = pg.transform.scale(self.pitchGround, (40, 40))
        for x in range(0, 1200, 40):
            for y in range(0, 800, 40):
                self.pitch.blit(self.pitchGround, (x, y))

    def drawTanks(self, playerlist):
        playerCount = len(playerlist)
        if playerCount >= 1:
            self.myTank = Tank("../resources/images/tank/Tank_01.png", 100.0, 700.0, 40.0, 40.0, 360.0)
            self.myTank.ownerId = playerlist[0].id
            self.tanks.add(self.myTank)

        if playerCount >= 2:
            tank2 = Tank("../resources/images/tank/Tank_02.png", 1060.0, 700.0, 40.0, 40.0, 360.0)
            tank2.ownerId = playerlist[1].id
            self.tanks.add(tank2)
        if playerCount >= 3:
            tank3 = Tank("../resources/images/tank/Tank_03.png", 100.0, 60.0, 40.0, 40.0, 180.0)
            tank3.ownerId = playerlist[2].id
            self.tanks.add(tank3)
        if playerCount == 4:
            tank4 = Tank("../resources/images/tank/Tank_04.png", 1060.0, 60.0, 40.0, 40.0, 180.0)
            tank4.ownerId = playerlist[3].id
            self.tanks.add(tank4)
        self.allsprites = pg.sprite.RenderPlain()
        self.allsprites.add(self.tanks)
        self.allsprites.draw(self.pitch)

    def drawBullets(self, owner):
        pass

