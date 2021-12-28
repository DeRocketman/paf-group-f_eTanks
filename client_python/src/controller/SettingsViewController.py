from enum import Enum

import PySide6
import keyboard as keyboard
from PySide6 import QtGui
from PySide6.QtGui import QKeyEvent
from PySide6.QtWidgets import QWidget

from model.data.UserSettings import UserSettings
from model.service.HttpRequest import HttpRequest
from resources.view.SettingsView import Ui_settingsView


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
        self.setInitValue(ButtonType.SOUND_ON)
        self.setInitValue(ButtonType.MUSIC_ON)
        self.setInitValue(ButtonType.MOVE_UP)
        self.setInitValue(ButtonType.MOVE_DOWN)
        self.setInitValue(ButtonType.MOVE_LEFT)
        self.setInitValue(ButtonType.MOVE_RIGHT)
        self.setInitValue(ButtonType.FIRE_MAIN)
        self.setInitValue(ButtonType.FIRE_SECONDARY)

        self.settingsView.moveUpButton.clicked.connect(self.changeMoveUpKey)
        self.settingsView.moveDownButton.clicked.connect(self.changeMoveDownKey())
        self.settingsView.moveLeftButton.clicked.connect(self.changeMoveLeftKey())
        self.settingsView.moveRightButton.clicked.connect(self.changeMoveRightKey())
        self.settingsView.mainWeaponButton.clicked.connect(self.changeFireMainKey())
        self.settingsView.secondaryWeaponButton.clicked.connect(self.changeFireSecKey())

        self.settingsView.soundVolSlider.sliderReleased.connect(self.changeVol)
        self.settingsView.musicVolSlider.sliderReleased.connect(self.changeVol)

        self.settingsView.musicOnButton.clicked.connect(self.switchMusic)
        self.settingsView.soundOnButton.clicked.connect(self.switchSound)

    def changeMoveUpKey(self):
        self.settingsView.moveUpButton.setText("NEU")
        self.settingsView.moveUpButton.setStyleSheet("Background-Color: blue;")
        self.setValue(ButtonType.MOVE_UP)



    def changeMoveDownKey(self):
        pass

    def changeMoveRightKey(self):
        pass

    def changeMoveLeftKey(self):
        pass

    def changeFireMainKey(self):
        pass

    def changeFireSecKey(self):
        pass

    def getKey(self, pushButton):
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

    def setInitValue(self, buttonType):
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



class ButtonType(Enum):
    MOVE_UP = "MOVE_UP"
    MOVE_DOWN = "MOVE_DOWN"
    MOVE_LEFT = "MOVE_LEFT"
    MOVE_RIGHT = "MOVE_RIGHT"
    FIRE_MAIN = "FIRE_MAIN_WEAPON"
    FIRE_SECONDARY = "FIRE_SECONDARY_WEAPON"

    SOUND_ON = "SOUND_ON"
    MUSIC_ON = "MUSIC_ON"
