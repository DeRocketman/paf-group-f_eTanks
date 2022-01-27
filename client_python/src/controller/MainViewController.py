from PySide6.QtWidgets import QMainWindow

from controller.LoginViewController import LoginViewController
from controller.RegisterUserViewController import RegisterUserViewController
from resources.view.MainView import Ui_MainWindow


class MainViewController(QMainWindow):
    def __init__(self):
        super().__init__()

        self.mainView = Ui_MainWindow()
        self.mainView.setupUi(self)
        self.loginView = LoginViewController(self.mainView.stackedWidget)
        self.registerUserView = RegisterUserViewController(self.mainView.stackedWidget)
        self.mainView.stackedWidget.addWidget(self.loginView)
        self.mainView.stackedWidget.addWidget(self.registerUserView)










