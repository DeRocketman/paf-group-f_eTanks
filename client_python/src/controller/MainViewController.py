from PySide6.QtWidgets import QMainWindow

from controller.LoginViewController import LoginViewController
from controller.MainMenuViewController import MainMenuViewController
from controller.RegisterUserViewController import RegisterUserViewController
from controller.ProfilViewController import ProfilViewController
from resources.view.MainView import Ui_MainWindow


class MainViewController(QMainWindow):
    def __init__(self):
        super().__init__()

        self.mainView = Ui_MainWindow()
        self.mainView.setupUi(self)

        # init new views with a controller here
        self.loginView = LoginViewController()
        self.mainMenuView = MainMenuViewController()
        self.registerUserView = RegisterUserViewController()
        self.profilView = ProfilViewController()

        # add always the initialized views to the page stack
        self.mainView.stackedWidget.addWidget(self.loginView)
        self.mainView.stackedWidget.addWidget(self.registerUserView)
        self.mainView.stackedWidget.addWidget(self.mainMenuView)
        self.mainView.stackedWidget.addWidget(self.profilView)

