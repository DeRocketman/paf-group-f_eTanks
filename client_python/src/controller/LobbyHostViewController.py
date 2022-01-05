import base64
import json
import random
import socket
import threading
import time

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget, QListWidgetItem

from model.data.User import User
from model.service import SocketMessageCode
from model.service.ClientSocket import ClientSocket
from model.service.Message import Message
from model.service.SocketMessageCode import SocketCode
from resources.view.LobbyHostView import Ui_lobbyHostView


class LobbyHostViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()
        self.lobbyHostView = Ui_lobbyHostView()
        self.lobbyHostView.setupUi(self)
        self.playerList = []
        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.lobbySocket = self.newGameViewController.clientSocket
        self.lobbyId = self.createLobbyId()
        self.hostname = socket.gethostname()
        self.ip = socket.gethostbyname(self.hostname)

        self.playerList.append(self.signedPlayer)
        self.playerListView = self.lobbyHostView.playerList
        self.playerRdyListView = self.lobbyHostView.rdyList
        self.lobbyHostView.ipAdressLbl.setText(self.ip)
        self.lobbyHostView.gameNumberLbl.setText(self.lobbyId)
        self.fillPlayerTable()

        self.lobbyHostView.setRdyButton.clicked.connect(self.setRdy)
        self.lobbyHostView.sendMsgButton.clicked.connect(self.sendChatMsg)
        self.lobbyHostView.startGameButton.clicked.connect(self.startGame)
        self.threadSendMsg = threading.Thread(target=self.sendMsg)
        self.threadSendMsg.start()
        self.threadReceiveMsg = threading.Thread(target=self.receiveMsg)
        self.threadReceiveMsg.start()
        self.registerLobbyToServer()

    def fillPlayerTable(self):
        for player in self.playerList:
            self.playerListView.addItem(self.buildPlayerIconItem(player))
            self.playerRdyListView.addItem(self.buildPlayerRdyIconItem(player))

    def setRdy(self):
        for player in self.playerList:
            if player.id == self.signedPlayer.id:
                if player.isRdy:
                    player.isRdy = False
                    self.lobbyHostView.setRdyButton.setText("Nicht Bereit!")
                    self.lobbyHostView.setRdyButton.setStyleSheet("Background-Color: grey; Color: red")
                else:
                    player.isRdy = True
                    self.lobbyHostView.setRdyButton.setText("Bereit!")
                    self.lobbyHostView.setRdyButton.setStyleSheet("Background-Color: green;")

        self.playerListView.clear()
        self.playerRdyListView.clear()
        self.fillPlayerTable()

    def sendChatMsg(self):
        print("Try send Msg with Text: " + self.lobbyHostView.chatMsgTextField.text())
        chatMsg = Message()
        chatMsg.messageType = "CHAT_MSG"
        chatMsg.gameLobbyNumber = self.lobbyHostView.gameNumberLbl.text()
        chatMsg.senderId = self.signedPlayer.id
        chatMsg.senderPublicName = self.signedPlayer.publicName
        chatMsg.payload = self.lobbyHostView.chatMsgTextField.text()
        self.sendMsg(chatMsg)
        self.lobbyHostView.chatMsgTextField.clear()

    def registerLobbyToServer(self):
        registerLobbyMsg = Message()
        registerLobbyMsg.messageType = "REGISTER_LOBBY"
        registerLobbyMsg.gameLobbyNumber = self.lobbyId
        registerLobbyMsg.senderId = self.signedPlayer.id
        registerLobbyMsg.senderPublicName = self.signedPlayer.publicName
        self.sendMsg(registerLobbyMsg)

    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.lobbySocket.sendMsg(msgJSON)
        print("Gesendet:"+msgJSON)

    def receiveMsg(self):
        while True:
            self.lobbyHostView.chatField.append(self.lobbySocket.receiveMsg())

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
