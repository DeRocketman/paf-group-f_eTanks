from enum import Enum

from PySide6.QtWidgets import QWidget

from model.service.HttpRequest import HttpRequest
from resources.view.SettingsView import Ui_settingsView


class SettingsViewController(QWidget):
    def __init__(self, mainMenuController):
        super().__init__()

        self.mainMenuController = mainMenuController
        self.httpRequest = HttpRequest()

        self.settingsView = Ui_settingsView()
        self.settingsView.setupUi(self)

        self.settingsView.musicVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameMusicVolume)
        self.settingsView.soundVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameSoundVolume)
        self.settingsView.soundOnButton.setText(self.checkValue(ButtonType.SOUND_ON))
        self.settingsView.musicOnButton.setText(self.checkValue(ButtonType.MUSIC_ON))
        self.settingsView.moveUpButton.setText(self.checkValue(ButtonType.MOVE_UP))
        self.settingsView.moveDownButton.setText(self.checkValue(ButtonType.MOVE_DOWN))

        self.settingsView.moveUpButton.clicked.connect(self.changeKey)
        self.settingsView.moveDownButton.clicked.connect(self.changeKey)
        self.settingsView.moveLeftButton.clicked.connect(self.changeKey)
        self.settingsView.moveRightButton.clicked.connect(self.changeKey)
        self.settingsView.mainWeaponButton.clicked.connect(self.changeKey)
        self.settingsView.secondaryWeaponButton.clicked.connect(self.changeKey)

        self.settingsView.soundVolSlider.sliderReleased.connect(self.changeVol)
        self.settingsView.musicVolSlider.sliderReleased.connect(self.changeVol)

        self.settingsView.musicOnButton.clicked.connect(self.switchMusic)
        self.settingsView.soundOnButton.clicked.connect(self.switchSound)

    def changeKey(self):
        pass

    def switchMusic(self):
        pass

    def switchSound(self):
        pass

    def changeVol(self):
        pass

    def checkValue(self, buttonType):
        pass

class ButtonType(Enum):
    MOVE_UP = "MOVE_UP"
    MOVE_DOWN = "MOVE_DOWN"
    MOVE_LEFT = "MOVE_LEFT"
    MOVE_RIGHT = "MOVE_RIGHT"
    FIRE_MAIN = "FIRE_MAIN_WEAPON"
    FIRE_SECONDARY = "FIRE_SECONDARY_WEAPON"

    SOUND_ON = "SOUND_ON"
    MUSIC_ON = "MUSIC_ON"

