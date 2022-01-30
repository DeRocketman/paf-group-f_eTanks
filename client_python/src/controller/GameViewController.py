import pygame as pygame

from model.game.elements.Tank import Tank


class GameViewController():
    def __init__(self, newGameViewController):

        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.gameSocket = self.newGameViewController.clientSocket
        self.playerList = []
        self.gameId = 0
        self.roundCounter = 1
        self.clock = pygame.time

        player = pygame.image.load("../resources/images/tank/Tank_01.png")

    def initGameData(self, playerList, lobbyId):
        self.playerList = playerList
        self.gameId = lobbyId

    def sendMsg(self, msg):
        pass

    def receiveMsg(self, msg):
        pass

    def drawLvl(self):
        if self.roundCounter == 1:
            pass

    def drawTanks(self):
        if len(self.playerList) == 1:
            tank1 = Tank("../resources/images/tank/Tank_01.png", 100.0, 700.0, 40.0, 40.0, 360.0)
