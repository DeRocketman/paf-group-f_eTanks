import base64
import json
import socket

from PySide6 import QtCore
from PySide6.QtGui import QPixmap
from PySide6.QtWidgets import QWidget, QListWidgetItem

from controller.GameViewController import GameViewController
from model.data.User import User
from model.service.Message import Message
from resources.view.LobbyJoinView import Ui_lobbyJoinView


class LobbyJoinViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()
        self.lobbyJoinView = Ui_lobbyJoinView()
        self.lobbyJoinView.setupUi(self)
        self.playerList = []
        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.lobbySocket = self.newGameViewController.clientSocket
        self.lobbyId = ""
        self.lobbyJoinView.ipAdressLbl.setText(socket.gethostbyname(socket.gethostname()))
        self.lobbyJoinView.gameNumberLbl.setText(self.lobbyId)
        self.lobbyJoinView.setRdyButton.clicked.connect(self.sendRdyStatus)
        self.lobbyJoinView.sendMsgButton.clicked.connect(self.sendChatMsg)

        self.playerListView = self.lobbyJoinView.playerList
        self.playerRdyListView = self.lobbyJoinView.rdyList

    def registerJoinedUserToLobby(self):
        self.lobbyJoinView.gameNumberLbl.setText(self.lobbyId)

        msg = Message()
        msg.messageType = "JOIN"
        msg.gameLobbyNumber = self.lobbyId
        msg.playerId = self.signedPlayer.id
        msg.playerPublicName = self.signedPlayer.publicName
        msg.playerImage = "default"
        msg.playerIsRdy = self.signedPlayer.isRdy
        self.sendMsg(msg)

    def fillPlayerTable(self):
        self.playerListView.clear()
        self.playerRdyListView.clear()
        for player in self.playerList:
            self.playerListView.addItem(self.buildPlayerIconItem(player))
            self.playerRdyListView.addItem(self.buildPlayerRdyIconItem(player))

    def rdyStatus(self, playerFromMsg):
        if playerFromMsg.id == self.signedPlayer.id:
            if playerFromMsg.isRdy:
                self.lobbyJoinView.setRdyButton.setText("Bereit!")
                self.lobbyJoinView.setRdyButton.setStyleSheet("Background-Color: green;")
            else:
                self.lobbyJoinView.setRdyButton.setText("Nicht Bereit!")
                self.lobbyJoinView.setRdyButton.setStyleSheet("Background-Color: grey; Color: red")

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
        rdyMsg.payload = self.lobbyJoinView.chatMsgTextField.text()
        self.sendMsg(rdyMsg)

    def sendChatMsg(self):
        print("Try send Msg with Text: " + self.lobbyJoinView.chatMsgTextField.text())
        chatMsg = Message()
        chatMsg.messageType = "CHAT_MSG"
        chatMsg.gameLobbyNumber = self.lobbyId
        chatMsg.playerId = self.signedPlayer.id
        chatMsg.playerPublicName = self.signedPlayer.publicName
        chatMsg.payload = self.lobbyJoinView.chatMsgTextField.text()
        self.sendMsg(chatMsg)
        self.lobbyJoinView.chatMsgTextField.clear()

    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.lobbySocket.sendMsg(msgJSON)
        print("Gesendet von LobbyJoinView: ", msgJSON)

    def receiveMsg(self, msg):
        if msg is not None:
            if msg.messageType == "JOINED_PLAYER":
                self.playerJoined(msg)
            elif msg.messageType == "CHAT_MSG":
                self.lobbyJoinView.chatField.append(msg.playerPublicName + ": " + msg.payload)
            elif msg.messageType == "RDY_STATUS":
                player = User()
                player.id = msg.playerId
                player.isRdy = msg.playerIsRdy
                self.rdyStatus(player)
            elif msg.messageType == "START_GAME":
                self.startGame()

    def playerJoined(self, msg):
        newPlayer = User()
        newPlayer.id = msg.playerId
        newPlayer.publicName = msg.playerPublicName
        newPlayer.playerIsRdy = msg.playerIsRdy
        newPlayer.userImage = msg.playerImage
        self.playerList.append(newPlayer)
        self.fillPlayerTable()

    def startGame(self):
        gameViewController = GameViewController(self.newGameViewController, self.playerList, self.lobbyId)
        self.newGameViewController.mainMenuViewController.stackedWidget.addWidget(gameViewController)
        self.newGameViewController.mainMenuViewController.stackedWidget.setCurrentWidget(gameViewController)
        self.close()


    @staticmethod
    def buildPlayerIconItem(user=User()):
        pixmap = QPixmap()
        if user.userImage == "default":
            pixmap.load("../resources/images/default-user-image.png")
        else:
            pixmap.loadFromData(base64.b64decode(user.userImage))
        pixmap.scaled(36, 36, QtCore.Qt.IgnoreAspectRatio)
        return QListWidgetItem(pixmap, user.publicName)

    @staticmethod
    def buildPlayerRdyIconItem(user=User()):
        pixmap = QPixmap()
        if user.isRdy:
            pixmap.load("../resources/images/rdy.png")
        else:
            pixmap.load("../resources/images/notrdy.png")
        pixmap.scaled(36, 36, QtCore.Qt.IgnoreAspectRatio)
        return QListWidgetItem(pixmap, "")
