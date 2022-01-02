import base64
import random
import socket
import time

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget, QListWidgetItem

from model.data.User import User
from resources.view.LobbyHostView import Ui_lobbyHostView


class LobbyHostViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()
        self.lobbyHostView = Ui_lobbyHostView()
        self.lobbyHostView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.lobbyId = self.createLobbyId()
        self.hostname = socket.gethostname()
        self.ip = socket.gethostbyname(self.hostname)
        self.playerList = []
        self.playerList.append(self.newGameViewController.mainMenuViewController.signedUser)

        self.playerListView = self.lobbyHostView.playerList
        self.playerRdyView = self.lobbyHostView.rdyList
        self.lobbyHostView.ipAdressLbl.setText(self.ip)
        self.lobbyHostView.gameNumberLbl.setText(self.lobbyId)
        self.fillPlayerTable()

        self.lobbyHostView.setRdyButton.clicked.connect(self.setRdy)
        self.lobbyHostView.startGameButton(self.startGame)

    def fillPlayerTable(self):
        for player in self.playerList:
            self.playerListView.addItem(self.buildPlayerIconItem(player))
            self.playerRdyView.addItem(self.buildPlayerRdyIconItem(player))

    def setRdy(self):
        for player in self.playerList:
            allPlayerRdy = False
            if player.id == self.newGameViewController.mainMenuViewController.signedUser.id:
                if player.isRdy:
                    player.isRdy = False
                else:
                    player.isRdy = True
            if player.isRdy:
                allPlayerRdy = True
            else:
                allPlayerRdy = False
        self.playerListView.clear()
        self.playerRdyView.clear()
        self.fillPlayerTable()

    def startGame(self):
        countPlayerNotRdy = 0
        for player in self.playerList:
            if not player.isRdy:
                countPlayerNotRdy += 1
        if countPlayerNotRdy == 0:
            pass
            # todo: Add GameStart here!

    @staticmethod
    def buildPlayerIconItem(user=User()):
        pixmap = QtGui.QPixmap()
        if user.userImage == "default":
            pixmap.load("../resources/images/default-user-image.png")
        else:
            pixmap.loadFromData(base64.b64decode(user.userImage))
        pixmap = pixmap.scaled(36, 36, QtCore.Qt.IgnoreAspectRatio)

        return QListWidgetItem(pixmap, user.publicName)

    @staticmethod
    def buildPlayerRdyIconItem(user=User()):
        pixmap = QtGui.QPixmap()
        if user.isRdy:
            pixmap.load("../resources/images/rdy.png")
        else:
            pixmap.load("../resources/images/notrdy.png")
        pixmap = pixmap.scaled(36, 36, QtCore.Qt.IgnoreAspectRatio)

        return QListWidgetItem(pixmap, "")

    @staticmethod
    def createLobbyId():
        currentNanoTime = time.time_ns()
        return str(currentNanoTime * random.randint(1000, 9999))
