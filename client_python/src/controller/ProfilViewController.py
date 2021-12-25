import base64

from PySide6 import QtGui, QtCore
from PySide6.QtWidgets import QWidget, QLineEdit, QFileDialog

from model.data.User import User
from model.service.HttpRequest import HttpRequest
from model.service.RequestCode import RequestCode
from resources.view.ProfilView import Ui_profilView


class ProfilViewController(QWidget):

    def __init__(self, mainMenuViewController):
        super().__init__()
        self.profilView = Ui_profilView()
        self.profilView.setupUi(self)
        self.tempUserChanges = User()

        self.mainMenuViewController = mainMenuViewController
        self.profilView.selectUserImageBtn.clicked.connect(self.openSelectImageDialog)
        self.profilView.changePublicNameButton.clicked.connect(self.enablePublicNameEdit)
        self.profilView.changePasswordButton.clicked.connect(self.enablePwEdit)
        self.profilView.writeChangesButton.clicked.connect(self.writeChanges)
        self.profilView.showMainMenuButton.clicked.connect(self.showMainMenuView)

        self.publicNameField = self.profilView.publicNameTextField
        self.publicNameField.setText(mainMenuViewController.signedUser.username)
        self.passwordField = self.profilView.passwordTextField
        self.passwordField.setText(mainMenuViewController.signedUser.password)
        self.passwordField.setEchoMode(QLineEdit.Password)

        self.pm = QtGui.QPixmap()
        self.pm.loadFromData(base64.b64decode(self.mainMenuViewController.signedUser.userImage))
        self.pm = self.pm.scaled(250, 250, QtCore.Qt.IgnoreAspectRatio)
        self.profilView.userImage.setPixmap(self.pm)

    def openSelectImageDialog(self):
        selectedImage, dialog = QFileDialog.getOpenFileName(parent=self, caption="Image auswählen", dir="/home/",
                                                            filter="Image Dateien (*.png *.jpg *.bmp)")

        self.tempUserChanges.userImage = self.tempUserChanges.convertImageToByte(selectedImage)
        self.pm.loadFromData(base64.b64decode(self.tempUserChanges.userImage))
        self.pm = self.pm.scaled(250, 250, QtCore.Qt.IgnoreAspectRatio)
        self.profilView.userImage.setPixmap(self.pm)

        if self.mainMenuViewController.signedUser.userImage != self.tempUserChanges.userImage:
            self.profilView.writeChangesButton.setEnabled(True)

    def enablePublicNameEdit(self):
        self.publicNameField.setEnabled(True)
        self.profilView.writeChangesButton.setEnabled(True)

    def enablePwEdit(self):
        self.passwordField.setEnabled(True)
        self.profilView.writeChangesButton.setEnabled(True)

    def writeChanges(self):
        updateChanges = HttpRequest()
        updateChanges.user = self.mainMenuViewController.signedUser

        self.tempUserChanges.publicName = self.publicNameField.text()
        self.tempUserChanges.password = self.passwordField.text()

        if self.tempUserChanges.publicName != self.mainMenuViewController.signedUser.publicName:
            updateChanges.user.publicName = self.tempUserChanges.publicName
        if self.tempUserChanges.userImage != self.mainMenuViewController.signedUser.userImage:
            updateChanges.user.userImage = self.tempUserChanges.userImage
        if self.tempUserChanges.password != self.mainMenuViewController.signedUser.password:
            updateChanges.user.password = self.tempUserChanges.password

        if updateChanges.httpReq(RequestCode.UPDATE_USER):
            self.mainMenuViewController.stackedWidget.setCurrentWidget(self.mainMenuViewController)

    def showMainMenuView(self):
        self.mainMenuViewController.stackedWidget.setCurrentWidget(self.mainMenuViewController)
