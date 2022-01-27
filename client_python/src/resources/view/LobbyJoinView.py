# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'LobbyJoinViewoTOcPQ.ui'
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
    QListWidget, QListWidgetItem, QPushButton, QSizePolicy,
    QTextEdit, QWidget)

class Ui_lobbyJoinView(object):
    def setupUi(self, lobbyJoinView):
        if not lobbyJoinView.objectName():
            lobbyJoinView.setObjectName(u"lobbyJoinView")
        lobbyJoinView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(lobbyJoinView.sizePolicy().hasHeightForWidth())
        lobbyJoinView.setSizePolicy(sizePolicy)
        lobbyJoinView.setStyleSheet(u"background: #8A8557;")
        self.label = QLabel(lobbyJoinView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(313, 109, 491, 71))
        font = QFont()
        font.setPointSize(48)
        font.setBold(False)
        self.label.setFont(font)
        self.hostGameButton = QPushButton(lobbyJoinView)
        self.hostGameButton.setObjectName(u"hostGameButton")
        self.hostGameButton.setEnabled(False)
        self.hostGameButton.setGeometry(QRect(422, 200, 261, 31))
        sizePolicy.setHeightForWidth(self.hostGameButton.sizePolicy().hasHeightForWidth())
        self.hostGameButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setPointSize(12)
        self.hostGameButton.setFont(font1)
        self.hostGameButton.setStyleSheet(u"background-color: grey;\n"
"border-color: #111111;\n"
"    \n"
"\n"
"")
        self.hostGameButton.setAutoDefault(False)
        self.joinGameButton = QPushButton(lobbyJoinView)
        self.joinGameButton.setObjectName(u"joinGameButton")
        self.joinGameButton.setEnabled(False)
        self.joinGameButton.setGeometry(QRect(422, 250, 261, 31))
        sizePolicy.setHeightForWidth(self.joinGameButton.sizePolicy().hasHeightForWidth())
        self.joinGameButton.setSizePolicy(sizePolicy)
        self.joinGameButton.setFont(font1)
        self.joinGameButton.setStyleSheet(u"background-color: #47452E;\n"
"border-color: #111111;\n"
"\n"
"")
        self.joinGameButton.setAutoDefault(False)
        self.leaveLobbyButton = QPushButton(lobbyJoinView)
        self.leaveLobbyButton.setObjectName(u"leaveLobbyButton")
        self.leaveLobbyButton.setGeometry(QRect(422, 300, 261, 31))
        sizePolicy.setHeightForWidth(self.leaveLobbyButton.sizePolicy().hasHeightForWidth())
        self.leaveLobbyButton.setSizePolicy(sizePolicy)
        self.leaveLobbyButton.setFont(font1)
        self.leaveLobbyButton.setStyleSheet(u"background-color: #47452E;\n"
"border-color: #111111;\n"
"   \n"
"")
        self.leaveLobbyButton.setAutoDefault(False)
        self.chatField = QTextEdit(lobbyJoinView)
        self.chatField.setObjectName(u"chatField")
        self.chatField.setGeometry(QRect(551, 350, 401, 251))
        self.chatField.setMouseTracking(False)
        self.chatField.setReadOnly(True)
        self.chatMsgTextField = QLineEdit(lobbyJoinView)
        self.chatMsgTextField.setObjectName(u"chatMsgTextField")
        self.chatMsgTextField.setGeometry(QRect(551, 609, 321, 31))
        self.sendMsgButton = QPushButton(lobbyJoinView)
        self.sendMsgButton.setObjectName(u"sendMsgButton")
        self.sendMsgButton.setGeometry(QRect(871, 609, 81, 31))
        self.label_2 = QLabel(lobbyJoinView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(40, 260, 61, 18))
        self.label_3 = QLabel(lobbyJoinView)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(40, 280, 101, 18))
        self.ipAdressLbl = QLabel(lobbyJoinView)
        self.ipAdressLbl.setObjectName(u"ipAdressLbl")
        self.ipAdressLbl.setGeometry(QRect(144, 260, 111, 18))
        self.gameNumberLbl = QLabel(lobbyJoinView)
        self.gameNumberLbl.setObjectName(u"gameNumberLbl")
        self.gameNumberLbl.setGeometry(QRect(142, 280, 201, 18))
        self.setRdyButton = QPushButton(lobbyJoinView)
        self.setRdyButton.setObjectName(u"setRdyButton")
        self.setRdyButton.setGeometry(QRect(410, 610, 101, 31))
        self.setRdyButton.setStyleSheet(u"background-color: grey;\n"
"color: red;")
        self.playerList = QListWidget(lobbyJoinView)
        self.playerList.setObjectName(u"playerList")
        self.playerList.setGeometry(QRect(190, 350, 281, 251))
        self.playerList.setStyleSheet(u"border-style: none")
        self.rdyList = QListWidget(lobbyJoinView)
        self.rdyList.setObjectName(u"rdyList")
        self.rdyList.setGeometry(QRect(480, 350, 51, 251))
        self.rdyList.setStyleSheet(u"border-style: none;")
        self.rdyList.setFlow(QListView.TopToBottom)
        self.rdyList.setViewMode(QListView.ListMode)

        self.retranslateUi(lobbyJoinView)

        QMetaObject.connectSlotsByName(lobbyJoinView)
    # setupUi

    def retranslateUi(self, lobbyJoinView):
        self.label.setText(QCoreApplication.translate("lobbyJoinView", u"Joined Gamelobby", None))
        self.hostGameButton.setText(QCoreApplication.translate("lobbyJoinView", u"Spiel leiten", None))
        self.joinGameButton.setText(QCoreApplication.translate("lobbyJoinView", u"Spiel beitreten", None))
        self.leaveLobbyButton.setText(QCoreApplication.translate("lobbyJoinView", u"Lobby verlassen", None))
        self.sendMsgButton.setText(QCoreApplication.translate("lobbyJoinView", u"Senden", None))
        self.label_2.setText(QCoreApplication.translate("lobbyJoinView", u"Deine IP:", None))
        self.label_3.setText(QCoreApplication.translate("lobbyJoinView", u"Spielnummer:", None))
        self.ipAdressLbl.setText(QCoreApplication.translate("lobbyJoinView", u"255.255.255.255", None))
        self.gameNumberLbl.setText(QCoreApplication.translate("lobbyJoinView", u"1234567890123456789", None))
        self.setRdyButton.setText(QCoreApplication.translate("lobbyJoinView", u"Nicht Bereit", None))
        pass
    # retranslateUi

