import pygame as pygame
from PySide6.QtWidgets import QWidget

from resources.view.GameView import Ui_gameWidget


class GameViewController(QWidget):
    def __init__(self, newGameViewController, playerList, lobbyId):
        super().__init__()
        self.gameView = Ui_gameWidget()
        self.gameView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.lobbyId = lobbyId
        self.playerList = playerList
        self.gameSocket = self.newGameViewController.clientSocket
        self.clock = pygame.time

    def sendMsg(self, msg):
        pass

    def receiveMsg(self, msg):
        pass
