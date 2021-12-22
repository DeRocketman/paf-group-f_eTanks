import requests

from model.data.User import User


class HttpRequest:
    def __init__(self):
        self.user = User()

    def httpReq(self, reqForLogin):
        success = False
        headers = {"Accept": "application/json", "content-Type": "application/json; utf-8",
                   "Authorization": "Bearer " + self.user.authToken}
        payload = {"username": self.user.username, "password": self.user.username, "publicName": self.user.password}
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
                print(request)

                print(request.text)
                print("Ende 3. Runde")
                self.getSignedUser(request.json)
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

    def getSignedUser(self, json):
        pass
        #self.user.username = json("username")
        #self.user.publicName = json("publicName")
        #self.user.userImage = json("userImage")
        #self.user.id = json("id")
