from PySide6.QtWidgets import QWidget

from resources.view.JoinGameView import Ui_joinGameView


class JoinGameViewController(QWidget):
    def __init__(self, newGameViewController):
        super().__init__()

        self.joinGameView = Ui_joinGameView()
        self.joinGameView.setupUi(self)
        self.newGameViewController = newGameViewController
        self.signedUser = self.newGameViewController.mainMenuViewController.signedUser

        self.joinGameView.toNewGameView.clicked.connect(self.showNewGameView)

    def showNewGameView(self):
        self.newGameViewController.mainMenuViewController.stackedWidget.setCurrentWidget(self.newGameViewController)
