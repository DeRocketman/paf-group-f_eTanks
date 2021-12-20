from PyQt6 import uic
from PyQt6.QtWidgets import QWidget


class ProfilViewController(QWidget):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        uic.loadUi("../resources/view/ProfilView.ui", self)