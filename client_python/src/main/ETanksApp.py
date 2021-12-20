from PyQt6.QtWidgets import QApplication

from controller.MainViewController import MainViewController


class ETankApp:

    def __init__(self):
        app = QApplication([])
        window = MainViewController()
        window.show()
        app.exec()


if __name__ == '__main__':
    ETankApp()
