import requests

from model.data.User import User


class HttpRequest:
    def __init__(self, user=User()):
        self.user = user

    def registerUser(self):
        payload = {"username": self.user.username, "password": self.user.username, "publicName": self.user.password}
        request = requests.post("http://127.0.0.1:8080/auth/register", data=payload)

        (print(request.json()))

    def loginUser(self):
        pass
