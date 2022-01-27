import pygame as pygame
from PySide6.QtWidgets import QWidget

from resources.view.GameView import Ui_gameWidget


class GameViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()
        self.gameView = Ui_gameWidget()
        self.gameView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.gameSocket = self.newGameViewController.clientSocket
        self.playerList = []
        self.gameId = 0
        self.clock = pygame.time

    def initGameData(self, playerList, lobbyId):
        self.playerList = playerList
        self.gameId = lobbyId

    def sendMsg(self, msg):
        pass

    def receiveMsg(self, msg):
        pass
