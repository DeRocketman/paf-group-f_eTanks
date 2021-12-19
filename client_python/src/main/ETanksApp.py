import sys


from PySide6.QtCore import QFile, QIODevice
from PySide6.QtUiTools import QUiLoader
from PySide6.QtWidgets import QApplication


class ETankApp:

    def __init__(self):
        app = QApplication(sys.argv)
        loginUI = QFile("../resources/view/loginView.ui")
        if not loginUI.open(QIODevice.ReadOnly):
            sys.exit(-1)
        loader = QUiLoader()
        window = loader.load(loginUI)

        if not window:
            sys.exit(-1)
        window.show()
        sys.exit(app.exec())

if __name__ == '__main__':
    app = ETankApp()
