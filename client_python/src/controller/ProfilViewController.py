from PySide6.QtWidgets import QWidget

from model.data.User import User
from resources.view.ProfilView import Ui_profilView


class ProfilViewController(QWidget):
    def __init__(self, stackedWidget, user=User()):
        super().__init__()
        self.profilView = Ui_profilView()
        self.profilView.setupUi(self)
        self.signedUser = user
        self.stackedWidget = stackedWidget


