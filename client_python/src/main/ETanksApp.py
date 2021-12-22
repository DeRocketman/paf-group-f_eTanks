import sys

from PySide6.QtWidgets import QApplication

from controller.MainViewController import MainViewController
from model.data.User import User
from model.service.HTTPRequest import HttpRequest


class ETankApp:

    def __init__(self):
        app = QApplication(sys.argv)
        window = MainViewController()

        self.signedUser = User()
        self.httpRequest = HttpRequest(self.signedUser)

        window.show()
        sys.exit(app.exec())


if __name__ == '__main__':
    ETankApp()
