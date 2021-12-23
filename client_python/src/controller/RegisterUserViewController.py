from PySide6.QtWidgets import QWidget

from controller.MainMenuViewController import MainMenuViewController
from model.service.HttpRequest import HttpRequest
from resources.view.RegisterUserView import Ui_registerUserView


class RegisterUserViewController(QWidget):
    def __init__(self, stackedWidget):
        super().__init__()

        self.registerUserView = Ui_registerUserView()
        self.registerUserView.setupUi(self)

        self.stackedWidget = stackedWidget
        self.httpRequest = HttpRequest()

        self.registerUserView.registerUserButton.clicked.connect(self.registerUser)
        self.registerUserView.backToLoginBtn.clicked.connect(self.showLoginView)
        self.usernameTxtField = self.registerUserView.usernameField
        self.publicNameTxtField = self.registerUserView.publicNameField
        self.passwordField = self.registerUserView.passwordField

    def registerUser(self):
        if self.usernameTxtField.text() != "" and self.publicNameTxtField.text() != "" \
                and self.passwordField.text() != "":

            self.httpRequest.user.username = self.usernameTxtField.text()
            self.httpRequest.user.publicName = self.publicNameTxtField.text()
            self.httpRequest.user.password = self.passwordField.text()

            if self.httpRequest.httpReq(False):
                self.buildAndChangeView()

    def showLoginView(self):
        self.stackedWidget.setCurrentIndex(0)

    def buildAndChangeView(self):
        mainMenuViewController = MainMenuViewController(self.stackedWidget, self.httpRequest.user)
        self.stackedWidget.addWidget(mainMenuViewController)
        self.stackedWidget.setCurrentIndex(2)
