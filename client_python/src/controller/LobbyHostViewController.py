import socket

from PySide6.QtWidgets import QWidget

from resources.view.LobbyHostView import Ui_lobbyHostView


class LobbyHostViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()
        self.lobbyHostView = Ui_lobbyHostView()
        self.lobbyHostView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.hostname = socket.gethostname()
        self.ip = socket.gethostbyname(self.hostname)
        self.lobbyHostView.ipAdressLbl.setText(self.ip)

        self.