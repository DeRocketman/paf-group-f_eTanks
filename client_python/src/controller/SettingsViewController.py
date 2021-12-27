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
        self.setValue(ButtonType.SOUND_ON)
        self.setValue(ButtonType.MUSIC_ON)
        self.setValue(ButtonType.MOVE_UP)
        self.setValue(ButtonType.MOVE_DOWN)
        self.setValue(ButtonType.MOVE_LEFT)
        self.setValue(ButtonType.MOVE_RIGHT)
        self.setValue(ButtonType.FIRE_MAIN)
        self.setValue(ButtonType.FIRE_SECONDARY)

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

    def changeKey(self, buttonType):
        pass

    def switchMusic(self):
        if self.settingsView.musicOnButton.text() == "AN":
            self.settingsView.musicOnButton.setText("Aus")
            self.settingsView.musicOnButton.setStyleSheet("Background-Color: grey;")
        else:
            self.settingsView.musicOnButton.setText("AN")
            self.settingsView.musicOnButton.setStyleSheet("Background-Color: green;")

    def switchSound(self):
        if self.settingsView.soundOnButton.text() == "AN":
            self.settingsView.soundOnButton.setText("Aus")
            self.settingsView.soundOnButton.setStyleSheet("Background-Color: grey;")
        else:
            self.settingsView.soundOnButton.setText("AN")
            self.settingsView.soundOnButton.setStyleSheet("Background-Color: green;")

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
            if self.mainMenuController.signedUser.userSettings.gameMusicOn:
                self.settingsView.musicOnButton.setText("AN")
                self.settingsView.musicOnButton.setStyleSheet("Background-Color: green;")
            else:
                self.settingsView.musicOnButton.setText("AUS")
                self.settingsView.musicOnButton.setStyleSheet("Background-Color: grey")
        elif buttonType == buttonType.MOVE_UP:
            self.settingsView.moveUpButton.setText(self.mainMenuController.signedUser.userSettings.moveUpKey)
        elif buttonType == buttonType.MOVE_DOWN:
            self.settingsView.moveDownButton.setText(self.mainMenuController.signedUser.userSettings.moveDownKey)
        elif buttonType == buttonType.MOVE_LEFT:
            self.settingsView.moveLeftButton.setText(self.mainMenuController.signedUser.userSettings.moveLeftKey)
        elif buttonType == buttonType.MOVE_RIGHT:
            self.settingsView.moveRightButton.setText(self.mainMenuController.signedUser.userSettings.moveRightKey)
        elif buttonType == buttonType.FIRE_MAIN:
            self.settingsView.mainWeaponButton.setText(
                self.mainMenuController.signedUser.userSettings.fireMainWeaponKey)
        elif buttonType == buttonType.FIRE_SECONDARY:
            self.settingsView.mainWeaponButton.setText(
                self.mainMenuController.signedUser.userSettings.fireSecondaryWeaponKey)


class ButtonType(Enum):
    MOVE_UP = "MOVE_UP"
    MOVE_DOWN = "MOVE_DOWN"
    MOVE_LEFT = "MOVE_LEFT"
    MOVE_RIGHT = "MOVE_RIGHT"
    FIRE_MAIN = "FIRE_MAIN_WEAPON"
    FIRE_SECONDARY = "FIRE_SECONDARY_WEAPON"

    SOUND_ON = "SOUND_ON"
    MUSIC_ON = "MUSIC_ON"
