import requests

from model.data.User import User


class HttpRequest:
    def __init__(self, user=User()):
        self.user = user

    def httpReq(self, reqForLogin):
        headers = {"Accept": "application/json", "content-Type": "application/json; utf-8",
                   "Authorization": "Bearer " + self.user.authToken}
        payload = {"username": self.user.username, "password": self.user.username, "publicName": self.user.password}
        if not reqForLogin:
            requestedURL = "/auth/register"
        elif self.user.authToken == "" and reqForLogin:
            requestedURL = "/auth/login"
        elif self.user.authToken != "" and reqForLogin:
            requestedURL = "/user/username"
            payload = self.user.username
            print("Letzte Runde Anfang")


        request = requests.post("http://127.0.0.1:8080" + requestedURL, headers=headers, json=payload)
        if request.status_code == 200:
            if not reqForLogin:
                print("User-Erstellung erfolgreich beginne Autologin!")
                self.httpReq(True)
            elif self.user.authToken == "" and reqForLogin:
                print(request.text)
                self.user.authToken = request.text
                self.httpReq(True)



        elif request.status_code == 400 and not reqForLogin:
            print("Aha Benutzername schon vergeben")
            return False
        elif request.status_code == 400 and reqForLogin:
            print("Passwort oder Benutzer falsch")
            return False
        else:
            print("Etwas ganz anderes ging hier in die Büx")
            print(request.status_code)
            return False
