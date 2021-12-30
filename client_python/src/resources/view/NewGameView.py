# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'NewGameViewZLnGzy.ui'
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
from PySide6.QtWidgets import (QApplication, QLabel, QPushButton, QSizePolicy,
                               QWidget)

class Ui_newGameView(object):
    def setupUi(self, newGameView):
        if not newGameView.objectName():
            newGameView.setObjectName(u"newGameView")
        newGameView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(newGameView.sizePolicy().hasHeightForWidth())
        newGameView.setSizePolicy(sizePolicy)
        newGameView.setStyleSheet(u"background: #8A8557;")
        self.label = QLabel(newGameView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(440, 110, 351, 51))
        font = QFont()
        font.setPointSize(48)
        font.setBold(False)
        self.label.setFont(font)
        self.hostGameButton = QPushButton(newGameView)
        self.hostGameButton.setObjectName(u"hostGameButton")
        self.hostGameButton.setGeometry(QRect(470, 200, 261, 31))
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
        self.joinGameButton = QPushButton(newGameView)
        self.joinGameButton.setObjectName(u"joinGameButton")
        self.joinGameButton.setGeometry(QRect(470, 250, 261, 31))
        sizePolicy.setHeightForWidth(self.joinGameButton.sizePolicy().hasHeightForWidth())
        self.joinGameButton.setSizePolicy(sizePolicy)
        self.joinGameButton.setFont(font1)
        self.joinGameButton.setStyleSheet(u"background-color: #47452E;\n"
                                          "border-color: #111111;\n"
                                          "\n"
                                          "")
        self.joinGameButton.setAutoDefault(False)
        self.showMainMenuButton = QPushButton(newGameView)
        self.showMainMenuButton.setObjectName(u"showMainMenuButton")
        self.showMainMenuButton.setGeometry(QRect(470, 300, 261, 31))
        sizePolicy.setHeightForWidth(self.showMainMenuButton.sizePolicy().hasHeightForWidth())
        self.showMainMenuButton.setSizePolicy(sizePolicy)
        self.showMainMenuButton.setFont(font1)
        self.showMainMenuButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "   \n"
                                              "")
        self.showMainMenuButton.setAutoDefault(False)
        self.logoutButton = QPushButton(newGameView)
        self.logoutButton.setObjectName(u"logoutButton")
        self.logoutButton.setGeometry(QRect(50, 780, 181, 21))
        font2 = QFont()
        self.logoutButton.setFont(font2)

        self.retranslateUi(newGameView)

        QMetaObject.connectSlotsByName(newGameView)
    # setupUi

    def retranslateUi(self, newGameView):
        self.label.setText(QCoreApplication.translate("newGameView", u"New Battle", None))
        self.hostGameButton.setText(QCoreApplication.translate("newGameView", u"Spiel leiten", None))
        self.joinGameButton.setText(QCoreApplication.translate("newGameView", u"Spiel beitreten", None))
        self.showMainMenuButton.setText(QCoreApplication.translate("newGameView", u"Zur\u00fcck zum Hauptmen\u00fc", None))
        self.logoutButton.setText(QCoreApplication.translate("newGameView", u"Abmelden vom Dienst", None))
        pass
    # retranslateUi

