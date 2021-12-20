from PySide6.QtWidgets import QWidget

from resources.view.ProfilView import Ui_profilView


class ProfilViewController(QWidget):
    def __init__(self):
        super().__init__()
        self.profilView = Ui_profilView()
        self.profilView.setupUi(self)
