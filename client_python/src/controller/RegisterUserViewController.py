from PySide6.QtWidgets import QWidget

from model.data.User import User
from model.service.HTTPRequest import HttpRequest
from resources.view.RegisterUserView import Ui_registerUserView


class RegisterUserViewController(QWidget):
    def __init__(self, stackedWidget):
        super().__init__()

        self.registerUserView = Ui_registerUserView()
        self.registerUserView.setupUi(self)

        self.stackedWidget = stackedWidget
        self.tempUser = User()
        self.httpRequest = HttpRequest(self.tempUser)

        self.registerUserView.registerUserButton.clicked.connect(self.registerUser)
        self.registerUserView.backToLoginBtn.clicked.connect(self.showLoginView)
        self.usernameTxtField = self.registerUserView.usernameTextfield
        self.publicNameTxtField = self.registerUserView.publicNameField
        self.passwordField = self.registerUserView.passwortField

    def registerUser(self):
        print("Erstellen Button geklickt!")
        if self.usernameTxtField.text() != "" and self.publicNameTxtField.text() != "" \
                and self.passwordField.text() != "":

            self.httpRequest.user.username = self.usernameTxtField.text()
            self.httpRequest.user.publicName = self.publicNameTxtField.text()
            self.httpRequest.user.password = self.passwordField.text()
            returnValue = False
            returnValue = self.httpRequest.httpReq(False)

            if returnValue:
                self.stackedWidget.setCurrentIndex(3)
                print("Umgeschaltet in Main Menu?")
            else:
                print("nö")
                print(returnValue)

    def showLoginView(self):
        self.stackedWidget.setCurrentIndex(0)
