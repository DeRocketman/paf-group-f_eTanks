from PyQt6 import uic
from PyQt6.QtWidgets import QMainWindow, QApplication, QStackedWidget

from LoginViewController import LoginViewController
from RegisterUserViewController import RegisterUserViewController


class MainViewController(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi("../resources/view/MainView.ui", self)
        self.loginView = LoginViewController()
        self.registerUserView = RegisterUserViewController()
        self.stackedWidget = QStackedWidget()

        self.setCentralWidget(self.stackedWidget)

#       self.mainMenuView = MainMenuViewController()
#       self.settingsView = UserSettingsViewController()
