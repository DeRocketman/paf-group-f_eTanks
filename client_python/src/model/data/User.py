import base64
import json

from model.data.UserSettings import UserSettings


class User:

    def __init__(self):
        self.id = 1100101
        self.username = "username"
        self.publicName = "publicName"
        self.password = "password"
        self.userSettings = UserSettings()
        self.userImage = "default"
        self.authToken = ""
        self.isRdy = False

    @staticmethod
    def convertImageToByte(path):
        with open(path, "rb") as image:
            return base64.encodebytes(image.read()).decode("utf-8")

    def buildUserJSON(self):
        userDict = {
            "username": self.username,
            "publicName": self.publicName,
            "password": self.password,
            "userImage": self.userImage,
        }

        return json.dumps(userDict)


if __name__ == '__main__':
    user = User()
    print(user.buildUserJSON())
    print(user.userSettings.buildSettingJson())
