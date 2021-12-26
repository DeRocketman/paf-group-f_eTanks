import sys

from PySide6.QtWidgets import QApplication

from controller.MainViewController import MainViewController


class ETankApp:

    def __init__(self):
        app = QApplication(sys.argv)
        window = MainViewController()
        window.show()
        sys.exit(app.exec())


if __name__ == '__main__':
    ETankApp()
