from PyQt6 import uic
from PyQt6.QtWidgets import *


class LoginViewController(QWidget):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        uic.loadUi("../resources/view/LoginView.ui", self)

