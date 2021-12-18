import base64
import UserSettings

from UserSettings import *


class User:

    def __init__(self):
        self.id = 1100101
        self.username = "username"
        self.publicName = "publicName"
        self.password = "password"
        self.userSettings = UserSettings()
        self.userStatistics = []
        self.userImage = "../../resources/images/default-user-image.png"

    def convertImageToByte(self):
        with open(self.userImage, "rb") as image:
            return base64.encodebytes(image.read()).decode("utf-8")

    def buildUserJSON(self):
        userDict = {
            "username": self.username,
            "publicName": self.publicName,
            "password": self.password,
            "userImage": self.convertImageToByte(),
        }

        return json.dumps(userDict)


if __name__ == '__main__':
    user = User()
    print(user.buildUserJSON())
    print(user.userSettings.buildSettingJson())
