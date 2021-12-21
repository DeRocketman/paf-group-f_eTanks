from PySide6.QtWidgets import QWidget, QLineEdit
from resources.view.LoginView import Ui_loginView


class LoginViewController(QWidget):
    def __init__(self):
        super().__init__()
        self.loginView = Ui_loginView()
        self.loginView.setupUi(self)
        self.loginBtn = self.loginView.loginButton
        self.createUserBtn = self.loginView.openUserViewButton
        self.forgotPwBtn = self.loginView.forgetPasswordButton

        self.usernameTxtField = self.loginView.usernameTextfield
        self.passwordTxtField = self.loginView.passwortField
        self.passwordTxtField.setEchoMode(QLineEdit.Password)

