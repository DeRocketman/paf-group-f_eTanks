import requests

from model.data.User import User


class HttpRequest:
    def __init__(self):
        self.user = User()

    def httpReq(self, reqForLogin):
        success = False
        headers = {"Accept": "application/json", "content-Type": "application/json; utf-8",
                   "Authorization": "Bearer " + self.user.authToken}
        payload = {"username": self.user.username, "password": self.user.password, "publicName": self.user.publicName}
        if not reqForLogin:
            requestedURL = "/auth/register"
            request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
        else:
            if self.user.authToken == "":
                requestedURL = "/auth/login"
                request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
            else:
                requestedURL = "/user/username"
                payload = self.user.username
                request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, data=payload)
                print("Letzte Runde Anfang")

        if request.status_code == 200:
            if not reqForLogin:
                print("User-Erstellung erfolgreich!")
                print("Beginne Autologin!")
                if self.httpReq(True):
                    success = True
                print("Ende 1. Runde")
            elif self.user.authToken == "" and reqForLogin:
                print(request.text)
                self.user.authToken = request.text
                if self.httpReq(True):
                    success = True
                print("Ende 2. Runde")
            else:
                print("Nur Request Text " + request.text)
                userData = request.json()
                self.mapResponseToUser(userData)

                print("Ende 3. Runde")
                success = True
        elif request.status_code == 400 and not reqForLogin:
            print("Aha:Der Benutzername ist schon vergeben")
            success = False
        elif request.status_code == 400 and reqForLogin:
            print("Aha: Passwort oder Benutzer falsch")
            success = False
        else:
            print(request.status_code)
            success = False
        return success

    def mapResponseToUser(self, jsonData):
        self.user.id = jsonData["id"]
        self.user.userImage = jsonData["username"]
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
        self.user.userStatistics = jsonData["gameStatistics"]
