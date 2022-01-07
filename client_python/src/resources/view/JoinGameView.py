# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'JoinGameViewcSQzUI.ui'
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
from PySide6.QtWidgets import (QApplication, QLabel, QListWidget, QListWidgetItem,
                               QPushButton, QSizePolicy, QWidget)

class Ui_joinGameView(object):
    def setupUi(self, joinGameView):
        if not joinGameView.objectName():
            joinGameView.setObjectName(u"joinGameView")
        joinGameView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(joinGameView.sizePolicy().hasHeightForWidth())
        joinGameView.setSizePolicy(sizePolicy)
        joinGameView.setStyleSheet(u"background: #8A8557;")
        self.label = QLabel(joinGameView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(406, 101, 391, 81))
        font = QFont()
        font.setPointSize(48)
        font.setBold(False)
        self.label.setFont(font)
        self.hostGameButton = QPushButton(joinGameView)
        self.hostGameButton.setObjectName(u"hostGameButton")
        self.hostGameButton.setEnabled(False)
        self.hostGameButton.setGeometry(QRect(470, 200, 261, 31))
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
        self.joinGameButton = QPushButton(joinGameView)
        self.joinGameButton.setObjectName(u"joinGameButton")
        self.joinGameButton.setEnabled(False)
        self.joinGameButton.setGeometry(QRect(470, 250, 261, 31))
        sizePolicy.setHeightForWidth(self.joinGameButton.sizePolicy().hasHeightForWidth())
        self.joinGameButton.setSizePolicy(sizePolicy)
        self.joinGameButton.setFont(font1)
        self.joinGameButton.setStyleSheet(u"background-color: #47452E;\n"
                                          "border-color: #111111;\n"
                                          "color: white\n"
                                          "")
        self.joinGameButton.setAutoDefault(False)
        self.toNewGameView = QPushButton(joinGameView)
        self.toNewGameView.setObjectName(u"toNewGameView")
        self.toNewGameView.setGeometry(QRect(470, 300, 261, 31))
        sizePolicy.setHeightForWidth(self.toNewGameView.sizePolicy().hasHeightForWidth())
        self.toNewGameView.setSizePolicy(sizePolicy)
        self.toNewGameView.setFont(font1)
        self.toNewGameView.setStyleSheet(u"background-color: #47452E;\n"
                                         "border-color: #111111;\n"
                                         "   \n"
                                         "")
        self.toNewGameView.setAutoDefault(False)
        self.logoutButton = QPushButton(joinGameView)
        self.logoutButton.setObjectName(u"logoutButton")
        self.logoutButton.setGeometry(QRect(50, 780, 181, 21))
        font2 = QFont()
        self.logoutButton.setFont(font2)
        self.joinSelectedButton = QPushButton(joinGameView)
        self.joinSelectedButton.setObjectName(u"joinSelectedButton")
        self.joinSelectedButton.setEnabled(False)
        self.joinSelectedButton.setGeometry(QRect(420, 580, 361, 26))
        self.gameList = QListWidget(joinGameView)
        self.gameList.setObjectName(u"gameList")
        self.gameList.setGeometry(QRect(420, 360, 261, 192))
        self.gameList.setStyleSheet(u"border: none;")
        self.seatsList = QListWidget(joinGameView)
        self.seatsList.setObjectName(u"seatsList")
        self.seatsList.setGeometry(QRect(700, 360, 71, 192))
        self.seatsList.setStyleSheet(u"border: none;")
        self.refreshGameListButton = QPushButton(joinGameView)
        self.refreshGameListButton.setObjectName(u"refreshGameListButton")
        self.refreshGameListButton.setGeometry(QRect(800, 450, 75, 24))

        self.retranslateUi(joinGameView)

        QMetaObject.connectSlotsByName(joinGameView)
    # setupUi

    def retranslateUi(self, joinGameView):
        self.label.setText(QCoreApplication.translate("joinGameView", u"Join a Battle", None))
        self.hostGameButton.setText(QCoreApplication.translate("joinGameView", u"Spiel leiten", None))
        self.joinGameButton.setText(QCoreApplication.translate("joinGameView", u"Spiel beitreten", None))
        self.toNewGameView.setText(QCoreApplication.translate("joinGameView", u"Zur\u00fcck", None))
        self.logoutButton.setText(QCoreApplication.translate("joinGameView", u"Abmelden vom Dienst", None))
        self.joinSelectedButton.setText(QCoreApplication.translate("joinGameView", u"Dieser Schlacht beitreten", None))
        self.refreshGameListButton.setText(QCoreApplication.translate("joinGameView", u"Erneuern", None))
        pass
    # retranslateUi



