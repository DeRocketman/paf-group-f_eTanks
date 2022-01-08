import base64
import json
import random
import socket
import time

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget, QListWidgetItem

from model.data.User import User
from model.service.Message import Message
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

        self.lobbyHostView.setRdyButton.clicked.connect(self.sendRdyStatus)
        self.lobbyHostView.sendMsgButton.clicked.connect(self.sendChatMsg)
        self.lobbyHostView.startGameButton.clicked.connect(self.startGame)

        # self.registerLobbyToServer()

    def fillPlayerTable(self):
        self.playerListView.clear()
        self.playerRdyListView.clear()
        for player in self.playerList:
            self.playerListView.addItem(self.buildPlayerIconItem(player))
            self.playerRdyListView.addItem(self.buildPlayerRdyIconItem(player))

    def rdyStatus(self, playerFromMsg):
        if playerFromMsg.id == self.signedPlayer.id:
            if playerFromMsg.isRdy:
                self.lobbyHostView.setRdyButton.setText("Bereit!")
                self.lobbyHostView.setRdyButton.setStyleSheet("Background-Color: green;")
            else:
                self.lobbyHostView.setRdyButton.setText("Nicht Bereit!")
                self.lobbyHostView.setRdyButton.setStyleSheet("Background-Color: grey; Color: red")
        else:
            for player in self.playerList:
                if playerFromMsg.id == player.id:
                    player.isRdy = playerFromMsg.isRdy

        self.playerListView.clear()
        self.playerRdyListView.clear()
        self.fillPlayerTable()

    def sendRdyStatus(self):
        if self.signedPlayer.isRdy:
            self.signedPlayer.isRdy = False
        else:
            self.signedPlayer.isRdy = True

        rdyMsg = Message()
        rdyMsg.messageType = "RDY_STATUS"
        rdyMsg.gameLobbyNumber = self.lobbyId
        rdyMsg.playerId = self.signedPlayer.id
        rdyMsg.playerPublicName = self.signedPlayer.publicName
        rdyMsg.playerIsRdy = self.signedPlayer.isRdy
        rdyMsg.payload = self.lobbyHostView.chatMsgTextField.text()
        self.sendMsg(rdyMsg)

    def sendChatMsg(self):
        chatMsg = Message()
        chatMsg.messageType = "CHAT_MSG"
        chatMsg.gameLobbyNumber = self.lobbyId
        chatMsg.playerId = self.signedPlayer.id
        chatMsg.playerPublicName = self.signedPlayer.publicName
        chatMsg.payload = self.lobbyHostView.chatMsgTextField.text()
        self.sendMsg(chatMsg)
        self.lobbyHostView.chatMsgTextField.clear()

    def registerLobbyToServer(self):
        registerLobbyMsg = Message()
        registerLobbyMsg.messageType = "REGISTER_LOBBY"
        registerLobbyMsg.gameLobbyNumber = self.lobbyId
        registerLobbyMsg.playerId = self.signedPlayer.id
        registerLobbyMsg.playerPublicName = self.signedPlayer.publicName
        self.sendMsg(registerLobbyMsg)

    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.lobbySocket.sendMsg(msgJSON)
        print("Gesendet in LobbyHostView: ", msgJSON)

    def receiveMsg(self, msg):
        if msg is not None:
            if msg.messageType == "REGISTER_LOBBY":
                self.lobbyHostView.chatField.append(msg.payload)
            elif msg.messageType == "JOINED_PLAYER":
                self.playerJoined(msg)
            elif msg.messageType == "CHAT_MSG":
                self.lobbyHostView.chatField.append(msg.playerPublicName + ": " + msg.payload)
            elif msg.messageType == "RDY_STATUS":
                player = User()
                player.id = msg.playerId
                player.isRdy = msg.playerIsRdy
                self.rdyStatus(player)

    def playerJoined(self, msg):
        newPlayer = User()
        newPlayer.id = msg.playerId
        newPlayer.publicName = msg.playerPublicName
        newPlayer.playerIsRdy = msg.playerIsRdy
        newPlayer.userImage = msg.playerImage
        self.playerList.append(newPlayer)
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
