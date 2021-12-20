from PyQt6 import uic
from PyQt6.QtWidgets import QMainWindow, QApplication, QStackedWidget

from controller.LoginViewController import LoginViewController
from controller.RegisterUserViewController import RegisterUserViewController
from controller.MainMenuViewController import MainMenuViewController
from controller.ProfilViewController import ProfilViewController


class MainViewController(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi("../resources/view/MainView.ui", self)
        self.loginView = LoginViewController()
        self.registerUserView = RegisterUserViewController()
        self.mainMenuView = MainMenuViewController()
        self.profilView = ProfilViewController()
        

