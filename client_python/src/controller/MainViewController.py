from PySide6.QtWidgets import QMainWindow, QDialog

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
        self.loginView.loginBtn.clicked.connect(self.login)
        self.loginView.createUserBtn.clicked.connect(self.openCreateUserView)

        self.mainMenuView = MainMenuViewController()

        self.registerUserView = RegisterUserViewController()
        self.registerUserView.registerUserButton.clicked.connect(self.registerUser)
        self.registerUserView.backButton.clicked.connect(self.openLoginView)
        self.profilView = ProfilViewController()

        # add always the initialized views to the page stack
        self.mainView.stackedWidget.addWidget(self.loginView)
        self.mainView.stackedWidget.addWidget(self.profilView)
        self.mainView.stackedWidget.addWidget(self.registerUserView)
        self.mainView.stackedWidget.addWidget(self.mainMenuView)

    def login(self):
        username = self.loginView.usernameTxtField.text()
        password = self.loginView.passwordTxtField.text()
        if username != "" and password != "":
            print(username, "  ", password)

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

    def registerUser(self):
        print("sehr toll")

    def openLoginView(self):
        self.mainView.stackedWidget.setCurrentIndex(0)

    def openCreateUserView(self):
        self.mainView.stackedWidget.setCurrentIndex(2)

    def openRestorePwView(self):
        print("toll")
