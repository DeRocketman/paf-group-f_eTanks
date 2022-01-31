from PySide6.QtWidgets import QWidget

from controller.NewGameViewController import NewGameViewController
from controller.ProfilViewController import ProfilViewController
from controller.SettingsViewController import SettingsViewController
from controller.StatisticViewController import StatisticViewController
from model.data.User import User
from resources.view.MainMenuView import Ui_mainMenuView


class MainMenuViewController(QWidget):
    def __init__(self, stackedWidget, user=User()):
        super().__init__()

        self.mainMenuView = Ui_mainMenuView()
        self.mainMenuView.setupUi(self)

        self.stackedWidget = stackedWidget
        self.signedUser = user
        self.gameStatisticList = []
        self.checkDefaultUserImage()
        print(self.signedUser.username)
        self.stackedWidget.addWidget(self)
        self.profilViewController = ProfilViewController(self)
        self.settingsViewController = SettingsViewController(self)
        self.statisticViewController = StatisticViewController(self)
        self.newGameViewController = NewGameViewController(self)

        self.mainMenuView.newGameButton.clicked.connect(self.openNewGameView)
        self.mainMenuView.showProfilButton.clicked.connect(self.openProfilView)
        self.mainMenuView.showSettingsButton.clicked.connect(self.openSettingsView)
        self.mainMenuView.showStatisticButton.clicked.connect(self.openStatisticView)

    def openNewGameView(self):
        self.stackedWidget.addWidget(self.newGameViewController)
        self.stackedWidget.setCurrentWidget(self.newGameViewController)

    def openProfilView(self):
        self.stackedWidget.addWidget(self.profilViewController)
        self.stackedWidget.setCurrentWidget(self.profilViewController)

    def openSettingsView(self):
        self.stackedWidget.addWidget(self.settingsViewController)
        self.stackedWidget.setCurrentWidget(self.settingsViewController)

    # TODO: implement connection to SettingsView
    def openStatisticView(self):
        self.stackedWidget.addWidget(self.statisticViewController)
        self.stackedWidget.setCurrentWidget(self.statisticViewController)
        self.statisticViewController.loadData()

    # TODO: implement connection to StatisticView

    def checkDefaultUserImage(self):
        if self.signedUser.userImage == "default":
            self.signedUser.userImage = self.signedUser.convertImageToByte("../resources/images/default-user-image"
                                                                           ".png")
