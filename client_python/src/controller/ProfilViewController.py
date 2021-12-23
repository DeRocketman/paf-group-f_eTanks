from PySide6.QtWidgets import QWidget, QLineEdit, QFileDialog
from resources.view.ProfilView import Ui_profilView


class ProfilViewController(QWidget):

    def __init__(self, mmViewController):
        super().__init__()
        self.profilView = Ui_profilView()
        self.profilView.setupUi(self)

        self.mainMenuViewController = mmViewController
        self.profilView.selectUserImageBtn.clicked.connect(self.openSelectImageDialog)
        self.profilView.changePublicNameButton.clicked.connect(self.enablePublicNameEdit)
        self.profilView.changePasswordButton.clicked.connect(self.enablePwEdit)
        self.profilView.writeChangesButton.clicked.connect(self.writeChanges)
        self.profilView.showMainMenuButton.clicked.connect(self.showMainMenuView)

        self.publicNameField = self.profilView.publicNameTextField
        self.passwordField = self.profilView.passwordTextField
        self.passwordField.setEchoMode(QLineEdit.Password)
        self.publicNameField.setText(mmViewController.signedUser.username)

    def openSelectImageDialog(self):
        selectedImage, dialog = QFileDialog.getOpenFileName(parent=self, caption="Image auswählen", dir="/home/",
                                                            filter="Image Dateien (*.png *.jpg *.bmp)")

        self.profilView.userImage.setStyleSheet("border-image: url(" + selectedImage + ") 0 0 0 0 stretch stretch;")
        self.mainMenuViewController.signedUser.userImage = \
            self.mainMenuViewController.signedUser.convertImageToByte(selectedImage)

    def enablePublicNameEdit(self):
        self.publicNameField.setEnabled(True)
        self.profilView.writeChangesButton.setEnabled(True)

    def enablePwEdit(self):
        self.passwordField.setEnabled(True)
        self.profilView.writeChangesButton.setEnabled(True)

    def writeChanges(self):
        pass

    def showMainMenuView(self):
        self.mainMenuViewController.stackedWidget.setCurrentWidget(self.mainMenuViewController)
