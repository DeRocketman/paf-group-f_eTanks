import base64
import random
import socket
import time

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget

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
        self.model = QtGui.QStandardItemModel()

        self.lobbyHostView.playerListView.setModel(self.model)
        self.lobbyHostView.ipAdressLbl.setText(self.ip)
        self.lobbyHostView.gameNumberLbl.setText(self.lobbyId)

        self.updatePlayerList()

    def updatePlayerList(self):
        for user in self.playerList:
            self.model.appendRow(self.buildListViewItem(user))

    @staticmethod
    def buildListViewItem(user=User()):
        pixmap = QtGui.QPixmap()
        if user.userImage == "default":
            pixmap.load("../resources/images/default-user-image.png")
        else:
            pixmap.loadFromData(base64.b64decode(user.userImage))
        pixmap = pixmap.scaled(36, 36, QtCore.Qt.IgnoreAspectRatio)

        return QtGui.QStandardItem(pixmap, user.publicName + "NICHT BEREIT")

    @staticmethod
    def createLobbyId():
        currentNanoTime = time.time_ns()
        return str(currentNanoTime * random.randint(1000, 9999))
