from PySide6.QtWidgets import QWidget

from resources.view.NewGameView import Ui_newGameView


class NewGameViewController(QWidget):
    def __init__(self, mainMenuViewController):
        super().__init__()

        self.newGameView = Ui_newGameView()
        self.newGameView.setupUi(self)
        self.mainMenuViewController = mainMenuViewController

        self.newGameView.hostGameButton.clicked.connect(self.hostGame)
        self.newGameView.joinGameButton.clicked.connect(self.joinGame)
        self.newGameView.showMainMenuButton.clicked.connect(self.showMainMenu)
        self.newGameView.logoutButton.clicked.connect(self.logoutFromGame)

    def hostGame(self):
        pass

    def joinGame(self):
        pass

    def showMainMenu(self):
        pass

    def logoutFromGame(self):
        pass

