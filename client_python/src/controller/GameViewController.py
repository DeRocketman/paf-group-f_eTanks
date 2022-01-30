import pygame as pg
import pygame.event

from pygame.constants import QUIT, KEYDOWN, K_ESCAPE
from model.game.logic.GamePhysics import GamePhysics
from resources.view.GameView import GameView


class GameViewController:
    def __init__(self, newGameViewContoller):
        pg.init()
        self.gameView = GameView()
        self.newGameViewController = newGameViewContoller
        # self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        # self.gameSocket = self.newGameViewController.clientSocket
        self.playerList = []
        self.gameId = 0
        self.roundCounter = 1
        self.clock = pg.time.Clock()
        self.FPS = GamePhysics.FRAMES_PER_SECONDS
        self.gameWindow = None
        #self.gameLoop()

    def initGameData(self, playerList, lobbyId):
        self.playerList = playerList
        self.gameId = lobbyId

    def sendMsg(self, msg):
        pass

    def receiveMsg(self, msg):
        pass

    def gameLoop(self):
        self.gameWindow = self.gameView.drawWindow(), pg.SCALED
        for tank in self.gameView.drawTanks(4):
            tank.drawElement(self.gameWindow)

        pg.display.set_caption("eTanks - PuF WiSe 21/22 - Gruppe F", "/../../resources/images/eTanksTitle.png")
        while True:
            for event in pygame.event.get():
                if event.type == QUIT or (event.type == KEYDOWN and event.key == K_ESCAPE):
                    pygame.quit()

            pg.display.flip()
            self.clock.tick(self.FPS)


if __name__ == '__main__':
    gvc = GameViewController()
