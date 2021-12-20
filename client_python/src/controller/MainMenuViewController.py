from PySide6.QtWidgets import QWidget

from resources.view.MainMenuView import Ui_mainMenuView


class MainMenuViewController(QWidget):
    def __init__(self):
        super().__init__()
        self.mainMenuView = Ui_mainMenuView()
        self.mainMenuView.setupUi(self)
