from enum import Enum

from PySide6.QtWidgets import QWidget

from model.data.UserSettings import UserSettings
from model.service.HttpRequest import HttpRequest
from resources.view.SettingsView import Ui_settingsView
import keyboard

class SettingsViewController(QWidget):
    def __init__(self, mainMenuController):
        super().__init__()

        self.mainMenuController = mainMenuController
        self.httpRequest = HttpRequest()
        self.tempSetting = UserSettings()
        self.settingsView = Ui_settingsView()
        self.settingsView.setupUi(self)

        self.settingsView.musicVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameMusicVolume)
        self.settingsView.soundVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameSoundVolume)
        self.settingsView.soundOnButton.setText(self.setValue(ButtonType.SOUND_ON))
        self.settingsView.musicOnButton.setText(self.setValue(ButtonType.MUSIC_ON))
        self.settingsView.moveUpButton.setText(self.setValue(ButtonType.MOVE_UP))
        self.settingsView.moveDownButton.setText(self.setValue(ButtonType.MOVE_DOWN))

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

    def setValue(self, buttonType):
        if buttonType == buttonType.SOUND_ON:
            if self.mainMenuController.signedUser.userSettings.gameSoundOn:
                self.settingsView.soundOnButton.setText("AN")
                self.settingsView.soundOnButton.setStyleSheet("Background-Color: green;")
            else:
                self.settingsView.soundOnButton.setText("AUS")
                self.settingsView.soundOnButton.setStyleSheet("Background-Color: grey;")
        elif buttonType == buttonType.MUSIC_ON:
            pass
        elif buttonType == buttonType.MOVE_UP:
            pass
        elif buttonType == buttonType.MOVE_DOWN:
            pass
        elif buttonType == buttonType.MOVE_LEFT:
            pass
        elif buttonType == buttonType.MOVE_RIGHT:
            pass
        elif buttonType == buttonType.FIRE_MAIN:
            pass
        elif buttonType == buttonType.FIRE_SECONDARY:
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
