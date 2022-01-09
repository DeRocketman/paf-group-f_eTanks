import json

from PySide6.QtWidgets import QWidget

from controller.JoinGameViewController import JoinGameViewController
from controller.LobbyHostViewController import LobbyHostViewController
from controller.LobbyJoinViewController import LobbyJoinViewController
from model.service.ClientSocket import ClientSocket
from model.service.Message import Message
from resources.view.NewGameView import Ui_newGameView


class NewGameViewController(QWidget):
    def __init__(self, mainMenuViewController):
        super().__init__()

        self.newGameView = Ui_newGameView()
        self.newGameView.setupUi(self)
        self.mainMenuViewController = mainMenuViewController
        self.clientSocket = ClientSocket(self)

        self.joinGameView = JoinGameViewController(self)
        self.lobbyHostView = LobbyHostViewController(self)
        self.lobbyJoinView = LobbyJoinViewController(self)

        self.newGameView.hostGameButton.clicked.connect(self.hostGame)
        self.newGameView.joinGameButton.clicked.connect(self.joinGame)
        self.newGameView.showMainMenuButton.clicked.connect(self.showMainMenu)
        self.newGameView.logoutButton.clicked.connect(self.logoutFromGame)

        self.sendExtendUserData()

    def hostGame(self):
        self.lobbyHostView.registerLobbyToServer()
        self.mainMenuViewController.stackedWidget.addWidget(self.lobbyHostView)
        self.mainMenuViewController.stackedWidget.setCurrentWidget(self.lobbyHostView)

    def joinGame(self):
        self.joinGameView.getLobbyList()
        self.mainMenuViewController.stackedWidget.addWidget(self.joinGameView)
        self.mainMenuViewController.stackedWidget.addWidget(self.lobbyJoinView)
        self.mainMenuViewController.stackedWidget.setCurrentWidget(self.joinGameView)

    def showMainMenu(self):
        self.mainMenuViewController.stackedWidget.setCurrentWidget(self.mainMenuViewController)

    def logoutFromGame(self):
        pass

    def sendExtendUserData(self):
        msg = Message()
        msg.messageType = "LOGIN"
        msg.playerId = self.mainMenuViewController.signedUser.id
        msg.playerPublicName = self.mainMenuViewController.signedUser.publicName
        msg.playerImage = "default"
        msg.playerIsRdy = self.mainMenuViewController.signedUser.isRdy

        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.clientSocket.sendMsg(msgJSON)
        print("Gesendet in NewGameViewController: ", msgJSON)
