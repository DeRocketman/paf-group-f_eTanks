import json


class UserSettings:
    def __init__(self):
        self.id = 1
        self.gameSoundVolume = 100
        self.gameMusicVolume = 100
        self.gameSoundOn = True
        self.gameMusicOn = True
        self.moveUpKey = "W"
        self.moveLeftKey = "A"
        self.moveDownKey = "S"
        self.moveRightKey = "D"
        self.fireMainWeaponKey = "SPACE"
        self.fireSecondaryWeaponKey = "CTL"
        self.showStatisticKey = "TAB"

    def buildSettingJson(self):
        settingsDict = {
            "gameSoundVolume": self.gameSoundVolume,
            "gameMusicVolume": self.gameMusicVolume,
            "gameSoundOn": self.gameSoundOn,
            "gameMusicOn": self.gameMusicOn,
            "moveUpKey": self.moveUpKey,
            "moveLeftKey": self.moveLeftKey,
            "moveDownKey": self.moveDownKey,
            "moveRightKey": self.moveRightKey,
            "fireMainWeaponKey": self.fireMainWeaponKey,
            "fireSecondaryWeaponKey": self.fireSecondaryWeaponKey,
            "showStatisticKey": self.showStatisticKey
        }
        return json.dumps(settingsDict)
