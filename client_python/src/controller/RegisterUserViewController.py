from PySide6.QtWidgets import QWidget

from resources.view.RegisterUserView import Ui_registerUserView


class RegisterUserViewController(QWidget):
    def __init__(self):
        super().__init__()
        self.registerUserView = Ui_registerUserView()
        self.registerUserView.setupUi(self)
