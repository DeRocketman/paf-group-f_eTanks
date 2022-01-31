import json
import requests

from model.data.User import User
from model.service.RequestCode import RequestCode


class HttpRequest:
    def __init__(self):
        self.user = User()
        self.statistics = []
        self.highscoreList = []

    def httpReq(self, requestCode = RequestCode):
        success = False
        headers = {"Accept": "application/json", "content-Type": "application/json; utf-8",
                   "Authorization": "Bearer " + self.user.authToken}
        payload = {"username": self.user.username, "password": self.user.password, "publicName": self.user.publicName}
        if requestCode == requestCode.CREATE_USER:
            print(requestCode.CREATE_USER)
            requestedURL = "/auth/register"
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
        elif requestCode == requestCode.GET_TOKEN:
            print(requestCode.GET_TOKEN)
            requestedURL = "/auth/login"
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
        elif requestCode == requestCode.LOGIN:
            print(requestCode.LOGIN)
            requestedURL = "/user/username"
            payload = self.user.username
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, data=payload)
        elif requestCode == requestCode.STATISTIC_LIST:
            print(requestCode.STATISTIC_LIST)
            url = "/user_game_statistic/"
            requestedURL = (url + str(self.user.id))
            request = requests.get("http://127.0.0.1:8080" + requestedURL, headers=headers)
        elif requestCode == requestCode.HIGHCSCORE_LIST:
            print(requestCode.HIGHCSCORE_LIST)
            listSize = "10"
            requestedURL = "/user_game_statistic/highscorelist/" + listSize
            request = requests.get("http://127.0.0.1:8080" + requestedURL, headers=headers)
        elif requestCode == requestCode.UPDATE_USER:
            print(requestCode.UPDATE_USER)
            requestedURL = "/user/save"
            payload = self.mapUserToPayload()
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
        else:
            print(requestCode.DELETE_USER)
            requestedURL = "/user/delete"
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)

        if request.status_code == 200:
            if requestCode == requestCode.CREATE_USER:
                print("User-Erstellung erfolgreich!")
                print("Beginne Autologin!")
                if self.httpReq(requestCode.GET_TOKEN):
                    success = True
                print("Ende 1. Runde")
            elif requestCode == requestCode.GET_TOKEN:
                print(request.text)
                self.user.authToken = request.text
                if self.httpReq(requestCode.LOGIN):
                    success = True
                print("Ende 2. Runde")
            elif requestCode == requestCode.LOGIN:
                print("Nur Request Text " + request.text)
                userData = request.json()
                self.mapResponseToUser(userData)
                print("Ende 3. Runde")
                success = True
            elif requestCode == requestCode.STATISTIC_LIST:
                print("Response " + request.text)
                response = request.text
                self.statistics = json.loads(response)
                success = True
            elif requestCode == requestCode.HIGHCSCORE_LIST:
                print("Response " + request.text)
                response = request.text
                self.highscoreList = json.loads(response)
                success = True
            elif requestCode == requestCode.UPDATE_USER:
                print("Update User Response:")
                print("---------------------")
                print(request.text)
                success = True
        elif request.status_code >= 400 and requestCode == requestCode.CREATE_USER:
            print("Aha: Der Benutzername ist schon vergeben")
            success = False
        elif request.status_code >= 400 and requestCode == requestCode.GET_TOKEN:
            print("Aha: Passwort oder Benutzer falsch")
            success = False
        else:
            print(request.status_code)
            success = False
        return success

    def mapResponseToUser(self, jsonData):
        self.user.id = jsonData["id"]
        self.user.username = jsonData["username"]
        self.user.publicName = jsonData["publicName"]
        self.user.userImage = jsonData["userImage"]
        self.user.userSettings.id = jsonData["userSettings"]["id"]
        self.user.userSettings.gameSoundVolume = jsonData["userSettings"]["gameSoundVolume"]
        self.user.userSettings.gameMusicVolume = jsonData["userSettings"]["gameMusicVolume"]
        self.user.userSettings.gameSoundOn = jsonData["userSettings"]["gameSoundOn"]
        self.user.userSettings.gameMusicOn = jsonData["userSettings"]["gameMusicOn"]
        self.user.userSettings.showStatisticKey = jsonData["userSettings"]["showStatisticKey"]
        self.user.userSettings.moveUpKey = jsonData["userSettings"]["moveUpKey"]
        self.user.userSettings.moveDownKey = jsonData["userSettings"]["moveDownKey"]
        self.user.userSettings.moveLeftKey = jsonData["userSettings"]["moveLeftKey"]
        self.user.userSettings.moveRightKey = jsonData["userSettings"]["moveRightKey"]
        self.user.userSettings.fireMainWeaponKey = jsonData["userSettings"]["fireMainWeaponKey"]
        self.user.userSettings.fireSecondaryWeaponKey = jsonData["userSettings"]["fireSecondaryWeaponKey"]

    def mapUserToPayload(self):
        return {
            "id": self.user.id,
            "userImage": self.user.userImage,
            "publicName": self.user.publicName,
            "username": self.user.username,
            "password": self.user.password,
            "authToken": self.user.authToken,
            "userSettings": {
                "id": self.user.userSettings.id,
                "gameSoundVolume": self.user.userSettings.gameSoundVolume,
                "gameMusicVolume": self.user.userSettings.gameMusicVolume,
                "gameSoundOn": self.user.userSettings.gameSoundOn,
                "gameMusicOn": self.user.userSettings.gameMusicOn,
                "showStatisticKey": self.user.userSettings.showStatisticKey,
                "moveUpKey": self.user.userSettings.moveUpKey,
                "moveDownKey": self.user.userSettings.moveDownKey,
                "moveLeftKey": self.user.userSettings.moveLeftKey,
                "moveRightKey": self.user.userSettings.moveRightKey,
                "fireMainWeaponKey": self.user.userSettings.fireMainWeaponKey,
                "fireSecondaryWeaponKey": self.user.userSettings.fireSecondaryWeaponKey
            },
        }
