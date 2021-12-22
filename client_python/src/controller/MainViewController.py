from PySide6.QtWidgets import QMainWindow

from controller.LoginViewController import LoginViewController
from controller.MainMenuViewController import MainMenuViewController
from controller.RegisterUserViewController import RegisterUserViewController
from controller.ProfilViewController import ProfilViewController
from model.data.User import User
from resources.view.MainView import Ui_MainWindow


class MainViewController(QMainWindow):
    def __init__(self):
        super().__init__()

        self.mainView = Ui_MainWindow()
        self.mainView.setupUi(self)
        # init new views with a controller here
        self.loginView = LoginViewController(self.mainView.stackedWidget)
        self.mainMenuView = MainMenuViewController(self.mainView.stackedWidget)
        self.registerUserView = RegisterUserViewController(self.mainView.stackedWidget)
        self.profilView = ProfilViewController(self.mainView.stackedWidget)

        # add always the initialized views to the page stack
        self.mainView.stackedWidget.addWidget(self.loginView)
        self.mainView.stackedWidget.addWidget(self.profilView)
        self.mainView.stackedWidget.addWidget(self.registerUserView)










