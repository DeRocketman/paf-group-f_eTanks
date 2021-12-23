from PySide6.QtWidgets import QWidget, QLineEdit, QDialog

from controller.MainMenuViewController import MainMenuViewController
from model.service.HttpRequest import HttpRequest
from resources.view.LoginView import Ui_loginView


class LoginViewController(QWidget):
    def __init__(self, stackedWidget):
        super().__init__()

        self.stackedWidget = stackedWidget
        self.httpRequest = HttpRequest()

        self.loginView = Ui_loginView()
        self.loginView.setupUi(self)

        self.loginBtn = self.loginView.loginButton
        self.loginBtn.clicked.connect(self.login)

        self.createUserBtn = self.loginView.openUserViewButton
        self.createUserBtn.clicked.connect(self.openCreateUserView)

        self.forgotPwBtn = self.loginView.forgetPasswordButton
        self.forgotPwBtn.clicked.connect(self.openRestorePwView)

        self.usernameTxtField = self.loginView.usernameTextfield
        self.passwordTxtField = self.loginView.passwortField
        self.passwordTxtField.setEchoMode(QLineEdit.Password)

    def login(self):

        # TODO: implement patterns, maybe
        if self.usernameTxtField.text() != "" and self.passwordTxtField.text() != "":
            self.httpRequest.user.username = self.usernameTxtField.text()
            self.httpRequest.user.password = self.passwordTxtField.text()
            if self.httpRequest.httpReq(True):
                self.buildAndChangeView()

        elif self.usernameTxtField.text() == "" and self.passwordTxtField.text() != "":
            dlg = QDialog(self)
            dlg.setWindowTitle("Hast du keinen Usernamen?")
            dlg.setToolTip("Dann Bitte gib diesen ein")
            dlg.exec()
        else:
            dlg = QDialog(self)
            dlg.setWindowTitle("Fällt dir dein Passwort nicht ein?")
            dlg.setWindowIconText("Doch? Dann bitte eingeben")
            dlg.exec()

    def openCreateUserView(self):
        self.stackedWidget.setCurrentIndex(1)

    def openRestorePwView(self):
        pass

    def buildAndChangeView(self):
        mainMenuViewController = MainMenuViewController(self.stackedWidget, self.httpRequest.user)
        self.stackedWidget.addWidget(mainMenuViewController)
        self.stackedWidget.setCurrentIndex(2)
