from PySide6.QtWidgets import QWidget

from resources.view.LoginView import Ui_loginView
from resources.view import rc_ressourcesETanks


class LoginViewController(QWidget):
    def __init__(self):
        super().__init__()
        self.loginView = Ui_loginView()
        self.loginView.setupUi(self)

        self.loginBtn = self.loginView.loginButton
        self.loginBtn.setDisabled(True)
        self.loginBtn.clicked.connect(self.login)

        self.createUserLbl = self.loginView.newUserLbl
        # self.createUserLbl.clicked.connect()

    def login(self):
        print("Ich muss raus!")
