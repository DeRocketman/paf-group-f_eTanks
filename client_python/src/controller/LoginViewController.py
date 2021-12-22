from PySide6.QtWidgets import QWidget, QLineEdit, QDialog
from resources.view.LoginView import Ui_loginView


class LoginViewController(QWidget):
    def __init__(self, stackedWidget):
        super().__init__()

        self.stackedWidget = stackedWidget
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
        username = self.usernameTxtField.text()
        password = self.passwordTxtField.text()
        # TODO: implement patterns, maybe
        if username != "" and password != "":
            # TODO: http request to login an user
            pass

        elif username == "" and password != "":
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
        self.stackedWidget.setCurrentIndex(2)

    def openRestorePwView(self):
        pass
