from enum import Enum

import keyboard as keyboard
from PySide6.QtWidgets import QWidget

from model.data.UserSettings import UserSettings
from model.service.HttpRequest import HttpRequest
from model.service.RequestCode import RequestCode
from resources.view.SettingsView import Ui_settingsView


class SettingsViewController(QWidget):
    def __init__(self, mainMenuController):
        super().__init__()

        self.mainMenuController = mainMenuController
        self.tempSetting = UserSettings()
        self.settingsView = Ui_settingsView()
        self.settingsView.setupUi(self)

        self.settingsView.musicVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameMusicVolume)
        self.settingsView.soundVolSlider.setValue(self.mainMenuController.signedUser.userSettings.gameSoundVolume)
        self.setInitValue(ButtonType.SOUND_ON)
        self.setInitValue(ButtonType.MUSIC_ON)
        self.settingsView.moveUpButton.setText(self.mainMenuController.signedUser.userSettings.moveUpKey)
        self.settingsView.moveDownButton.setText(self.mainMenuController.signedUser.userSettings.moveDownKey)
        self.settingsView.moveLeftButton.setText(self.mainMenuController.signedUser.userSettings.moveLeftKey)
        self.settingsView.moveRightButton.setText(self.mainMenuController.signedUser.userSettings.moveRightKey)
        self.settingsView.mainWeaponButton.setText(self.mainMenuController.signedUser.userSettings.fireMainWeaponKey)
        self.settingsView.secondaryWeaponButton.setText(
            self.mainMenuController.signedUser.userSettings.fireSecondaryWeaponKey)

        self.settingsView.moveUpButton.clicked.connect(self.changeMoveUpKey)
        self.settingsView.moveDownButton.clicked.connect(self.changeMoveDownKey)
        self.settingsView.moveLeftButton.clicked.connect(self.changeMoveLeftKey)
        self.settingsView.moveRightButton.clicked.connect(self.changeMoveRightKey)
        self.settingsView.mainWeaponButton.clicked.connect(self.changeFireMainKey)
        self.settingsView.secondaryWeaponButton.clicked.connect(self.changeFireSecKey)

        self.settingsView.musicOnButton.clicked.connect(self.switchMusic)
        self.settingsView.soundOnButton.clicked.connect(self.switchSound)

        self.settingsView.writeChangesButton.clicked.connect(self.writeChanges)

    def writeChanges(self):
        updateChanges = HttpRequest()
        updateChanges.user = self.mainMenuController.signedUser

        updateChanges.user.userSettings.moveUpKey = self.settingsView.moveUpButton.text()
        updateChanges.user.userSettings.moveDownKey = self.settingsView.moveDownButton.text()
        updateChanges.user.userSettings.moveLeftKey = self.settingsView.moveLeftButton.text()
        updateChanges.user.userSettings.moveRightKey = self.settingsView.moveRightButton.text()
        updateChanges.user.userSettings.fireMainWeaponKey = self.settingsView.mainWeaponButton.text()
        updateChanges.user.userSettings.fireSecondaryWeaponKey = self.settingsView.secondaryWeaponButton.text()

        updateChanges.user.userSettings.gameMusicVolume = self.settingsView.musicVolLcd.value()
        updateChanges.user.userSettings.gameSoundVolume = self.settingsView.soundVolLcd.value()

        if self.settingsView.musicOnButton.text() == "AN":
            updateChanges.user.userSettings.gameMusicOn = True
        else:
            updateChanges.user.userSettings.gameMusicOn = False

        if self.settingsView.soundOnButton.text() == "AN":
            updateChanges.user.userSettings.gameSoundOn = True
        else:
            updateChanges.user.userSettings.gameSoundOn = False

        if updateChanges.httpReq(RequestCode.UPDATE_USER):
            self.mainMenuController.stacked.Widget.setCurrentWidget(self.mainMenuController)

    def changeMoveUpKey(self):
        newValue = ""
        self.settingsView.moveUpButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.moveUpButton.setText(newValue)

    def changeMoveDownKey(self):
        newValue = ""
        self.settingsView.moveDownButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.moveDownButton.setText(newValue)
        self.tempSetting.moveUpKey = newValue

    def changeMoveRightKey(self):
        newValue = ""
        self.settingsView.moveRightButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.moveRightButton.setText(newValue)
        self.tempSetting.moveDownKey = newValue

    def changeMoveLeftKey(self):
        newValue = ""
        self.settingsView.moveLeftButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.moveLeftButton.setText(newValue)
        self.tempSetting.moveLeftKey = newValue

    def changeFireMainKey(self):
        newValue = ""
        self.settingsView.mainWeaponButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.mainWeaponButton.setText(newValue)
        self.tempSetting.fireMainWeaponKey = newValue

    def changeFireSecKey(self):
        newValue = ""
        self.settingsView.secondaryWeaponButton.setText("NEU")
        while newValue == "":
            newValue = str.capitalize(keyboard.read_key())

        self.settingsView.secondaryWeaponButton.setText(newValue)
        self.tempSetting.fireSecondaryWeaponKey = newValue

    def switchMusic(self):
        if self.settingsView.musicOnButton.text() == "AN":
            self.settingsView.musicOnButton.setText("Aus")
            self.settingsView.musicOnButton.setStyleSheet("Background-Color: grey;")
            self.tempSetting.gameMusicOn = False
        else:
            self.settingsView.musicOnButton.setText("AN")
            self.settingsView.musicOnButton.setStyleSheet("Background-Color: green;")
            self.tempSetting.gameMusicOn = True

    def switchSound(self):
        if self.settingsView.soundOnButton.text() == "AN":
            self.settingsView.soundOnButton.setText("Aus")
            self.settingsView.soundOnButton.setStyleSheet("Background-Color: grey;")
            self.tempSetting.gameSoundOn = False
        else:
            self.settingsView.soundOnButton.setText("AN")
            self.settingsView.soundOnButton.setStyleSheet("Background-Color: green;")
            self.tempSetting.gameSoundOn = True

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
