# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'LobbyHostViewpHtRwW.ui'
##
## Created by: Qt User Interface Compiler version 6.2.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
                            QMetaObject, QObject, QPoint, QRect,
                            QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
                           QFont, QFontDatabase, QGradient, QIcon,
                           QImage, QKeySequence, QLinearGradient, QPainter,
                           QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QLabel, QLineEdit, QListView,
                               QPushButton, QSizePolicy, QTextEdit, QWidget, QTableView)

class Ui_lobbyHostView(object):
    def setupUi(self, lobbyHostView):
        if not lobbyHostView.objectName():
            lobbyHostView.setObjectName(u"lobbyHostView")
        lobbyHostView.resize(1110, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(lobbyHostView.sizePolicy().hasHeightForWidth())
        lobbyHostView.setSizePolicy(sizePolicy)
        lobbyHostView.setStyleSheet(u"background: #8A8557;")
        self.label = QLabel(lobbyHostView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(313, 109, 491, 71))
        font = QFont()
        font.setPointSize(48)
        font.setBold(False)
        self.label.setFont(font)
        self.hostGameButton = QPushButton(lobbyHostView)
        self.hostGameButton.setObjectName(u"hostGameButton")
        self.hostGameButton.setEnabled(False)
        self.hostGameButton.setGeometry(QRect(422, 200, 261, 31))
        sizePolicy.setHeightForWidth(self.hostGameButton.sizePolicy().hasHeightForWidth())
        self.hostGameButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setPointSize(12)
        self.hostGameButton.setFont(font1)
        self.hostGameButton.setStyleSheet(u"background-color: #47452E;\n"
                                          "border-color: #111111;\n"
                                          "    \n"
                                          "\n"
                                          "")
        self.hostGameButton.setAutoDefault(False)
        self.joinGameButton = QPushButton(lobbyHostView)
        self.joinGameButton.setObjectName(u"joinGameButton")
        self.joinGameButton.setGeometry(QRect(422, 250, 261, 31))
        sizePolicy.setHeightForWidth(self.joinGameButton.sizePolicy().hasHeightForWidth())
        self.joinGameButton.setSizePolicy(sizePolicy)
        self.joinGameButton.setFont(font1)
        self.joinGameButton.setStyleSheet(u"background-color: grey;\n"
                                          "border-color: #111111;\n"
                                          "\n"
                                          "")
        self.joinGameButton.setAutoDefault(False)
        self.closeLobbyButton = QPushButton(lobbyHostView)
        self.closeLobbyButton.setObjectName(u"closeLobbyButton")
        self.closeLobbyButton.setGeometry(QRect(422, 300, 261, 31))
        sizePolicy.setHeightForWidth(self.closeLobbyButton.sizePolicy().hasHeightForWidth())
        self.closeLobbyButton.setSizePolicy(sizePolicy)
        self.closeLobbyButton.setFont(font1)
        self.closeLobbyButton.setStyleSheet(u"background-color: #47452E;\n"
                                            "border-color: #111111;\n"
                                            "   \n"
                                            "")
        self.closeLobbyButton.setAutoDefault(False)
        self.chatField = QTextEdit(lobbyHostView)
        self.chatField.setObjectName(u"chatField")
        self.chatField.setGeometry(QRect(551, 350, 401, 251))
        self.chatField.setMouseTracking(False)
        self.chatField.setReadOnly(True)
        self.chatMsgTextField = QLineEdit(lobbyHostView)
        self.chatMsgTextField.setObjectName(u"chatMsgTextField")
        self.chatMsgTextField.setGeometry(QRect(551, 600, 321, 31))
        self.sendMsgButton = QPushButton(lobbyHostView)
        self.sendMsgButton.setObjectName(u"sendMsgButton")
        self.sendMsgButton.setGeometry(QRect(871, 600, 81, 31))
        self.label_2 = QLabel(lobbyHostView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(40, 260, 61, 18))
        self.label_3 = QLabel(lobbyHostView)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(40, 280, 101, 18))
        self.ipAdressLbl = QLabel(lobbyHostView)
        self.ipAdressLbl.setObjectName(u"ipAdressLbl")
        self.ipAdressLbl.setGeometry(QRect(144, 260, 111, 18))
        self.gameNumberLbl = QLabel(lobbyHostView)
        self.gameNumberLbl.setObjectName(u"gameNumberLbl")
        self.gameNumberLbl.setGeometry(QRect(142, 280, 201, 18))
        self.startGameButton = QPushButton(lobbyHostView)
        self.startGameButton.setObjectName(u"startGameButton")
        self.startGameButton.setEnabled(False)
        self.startGameButton.setGeometry(QRect(161, 600, 87, 31))
        self.setRdyButton = QPushButton(lobbyHostView)
        self.setRdyButton.setObjectName(u"setRdyButton")
        self.setRdyButton.setGeometry(QRect(321, 600, 101, 31))
        self.setRdyButton.setStyleSheet(u"background-color: grey;\n"
                                        "color: red;")
        self.playerTableView = QTableView(lobbyHostView)
        self.playerTableView.setObjectName(u"playerTableView")
        self.playerTableView.setGeometry(QRect(160, 350, 391, 251))

        self.retranslateUi(lobbyHostView)

        QMetaObject.connectSlotsByName(lobbyHostView)
    # setupUi

    def retranslateUi(self, lobbyHostView):
        self.label.setText(QCoreApplication.translate("lobbyHostView", u"Host Gamelobby", None))
        self.hostGameButton.setText(QCoreApplication.translate("lobbyHostView", u"Spiel leiten", None))
        self.joinGameButton.setText(QCoreApplication.translate("lobbyHostView", u"Spiel beitreten", None))
        self.closeLobbyButton.setText(QCoreApplication.translate("lobbyHostView", u"Lobby schlie\u00dfen", None))
        self.sendMsgButton.setText(QCoreApplication.translate("lobbyHostView", u"Senden", None))
        self.label_2.setText(QCoreApplication.translate("lobbyHostView", u"Deine IP:", None))
        self.label_3.setText(QCoreApplication.translate("lobbyHostView", u"Spielnummer:", None))
        self.ipAdressLbl.setText(QCoreApplication.translate("lobbyHostView", u"255.255.255.255", None))
        self.gameNumberLbl.setText(QCoreApplication.translate("lobbyHostView", u"1234567890123456789", None))
        self.startGameButton.setText(QCoreApplication.translate("lobbyHostView", u"Start Game", None))
        self.setRdyButton.setText(QCoreApplication.translate("lobbyHostView", u"Nicht Bereit", None))
        pass
    # retranslateUi

