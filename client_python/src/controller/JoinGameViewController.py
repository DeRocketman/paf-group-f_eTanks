import json
import threading

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget, QListWidgetItem

from model.data.Lobby import Lobby
from model.service.Message import Message
from resources.view.JoinGameView import Ui_joinGameView


class JoinGameViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()

        self.joinGameView = Ui_joinGameView()
        self.joinGameView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.lobbySocket = self.newGameViewController.clientSocket
        self.signedUser = self.newGameViewController.mainMenuViewController.signedUser
        self.gameLobbyList = []

        self.joinGameView.toNewGameView.clicked.connect(self.showNewGameView)
        self.joinGameView.refreshGameListButton.clicked.connect(self.getLobbyList)
        self.joinGameView.joinSelectedButton.clicked.connect(self.joinSelectedGameLobby)
        self.joinGameView.gameList.doubleClicked.connect(self.joinSelectedGameLobby)
        self.threadSendMsg = threading.Thread(target=self.sendMsg)
        self.threadSendMsg.start()
        self.threadReceiveMsg = threading.Thread(target=self.receiveMsg)
        self.threadReceiveMsg.start()
        self.getLobbyList()

    def joinSelectedGameLobby(self):
        self.fillLobbyList()
        selectedLobbyId = self.joinGameView.gameList.currentItem().text()
        for lobby in self.gameLobbyList:
            if lobby.id == selectedLobbyId and lobby.seats < 4:
                pass

    def fillLobbyList(self):
        for lobby in self.gameLobbyList:
            self.joinGameView.gameList.clear()
            self.joinGameView.seatsList.clear()
            self.joinGameView.gameList.addItem(self.buildLobbyListItem(lobby.id))
            self.joinGameView.seatsList.addItem(self.buildSeatItem(lobby.seats))

    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.lobbySocket.sendMsg(msgJSON)
        print("Gesendet:" + msgJSON)

    def receiveMsg(self):
        while True:
            msg = self.lobbySocket.receiveMsg()
            print(msg)
            if msg is not None:
                if msg["messageType"] == "GET_LOBBIES":
                    lobby = Lobby()
                    lobby.id = msg["gameLobbyNumber"]
                    lobby.seats = msg["payload"]
                    self.gameLobbyList.append(lobby)
                    self.fillLobbyList()

    def showNewGameView(self):
        self.newGameViewController.mainMenuViewController.stackedWidget.setCurrentWidget(self.newGameViewController)

    def getLobbyList(self):
        getLobbyMsg = Message()
        getLobbyMsg.messageType = "GET_LOBBIES"
        self.sendMsg(getLobbyMsg)

    @staticmethod
    def buildLobbyListItem(lobbyNumber):
        pixmap = QtGui.QPixmap()
        pixmap.load("../resources/images/germany_flag.png")
        pic = pixmap.scaled(40, 40, QtCore.Qt.IgnoreAspectRatio)
        return QListWidgetItem(pic, lobbyNumber)

    @staticmethod
    def buildSeatItem(takenSeats):
        value = str(takenSeats) + "/4 Plätze"
        return QListWidgetItem(value)
