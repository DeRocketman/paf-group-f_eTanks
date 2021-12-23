# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'MainMenuView.ui'
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


class Ui_mainMenuView(object):
    def setupUi(self, mainMenuView):
        if not mainMenuView.objectName():
            mainMenuView.setObjectName(u"mainMenuView")
        mainMenuView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(mainMenuView.sizePolicy().hasHeightForWidth())
        mainMenuView.setSizePolicy(sizePolicy)
        mainMenuView.setStyleSheet(u"background: #8A8557;")
        self.newGameButton = QPushButton(mainMenuView)
        self.newGameButton.setObjectName(u"newGameButton")
        self.newGameButton.setGeometry(QRect(470, 280, 261, 31))
        sizePolicy.setHeightForWidth(self.newGameButton.sizePolicy().hasHeightForWidth())
        self.newGameButton.setSizePolicy(sizePolicy)
        font = QFont()
        font.setFamilies([u"Stencil"])
        font.setPointSize(12)
        self.newGameButton.setFont(font)
        self.newGameButton.setStyleSheet(u"background-color: #111111;\n"
                                         "color: rgb(171, 171, 0);\n"
                                         "border-color: #47452E;\n"
                                         "\n"
                                         "")
        self.newGameButton.setAutoDefault(False)
        self.label = QLabel(mainMenuView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(480, 210, 241, 51))
        font1 = QFont()
        font1.setFamilies([u"Stencil"])
        font1.setPointSize(48)
        font1.setBold(False)
        self.label.setFont(font1)
        self.showProfilButton = QPushButton(mainMenuView)
        self.showProfilButton.setObjectName(u"showProfilButton")
        self.showProfilButton.setGeometry(QRect(470, 330, 261, 31))
        sizePolicy.setHeightForWidth(self.showProfilButton.sizePolicy().hasHeightForWidth())
        self.showProfilButton.setSizePolicy(sizePolicy)
        self.showProfilButton.setFont(font)
        self.showProfilButton.setStyleSheet(u"background-color: #47452E;\n"
                                            "border-color: #111111;\n"
                                            "    \n"
                                            "\n"
                                            "")
        self.showProfilButton.setAutoDefault(False)
        self.showStatisticButton = QPushButton(mainMenuView)
        self.showStatisticButton.setObjectName(u"showStatisticButton")
        self.showStatisticButton.setGeometry(QRect(470, 380, 261, 31))
        sizePolicy.setHeightForWidth(self.showStatisticButton.sizePolicy().hasHeightForWidth())
        self.showStatisticButton.setSizePolicy(sizePolicy)
        self.showStatisticButton.setFont(font)
        self.showStatisticButton.setStyleSheet(u"background-color: #47452E;\n"
                                               "border-color: #111111;\n"
                                               "\n"
                                               "")
        self.showStatisticButton.setAutoDefault(False)
        self.showSettingsButton = QPushButton(mainMenuView)
        self.showSettingsButton.setObjectName(u"showSettingsButton")
        self.showSettingsButton.setGeometry(QRect(470, 430, 261, 31))
        sizePolicy.setHeightForWidth(self.showSettingsButton.sizePolicy().hasHeightForWidth())
        self.showSettingsButton.setSizePolicy(sizePolicy)
        self.showSettingsButton.setFont(font)
        self.showSettingsButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "   \n"
                                              "")
        self.showSettingsButton.setAutoDefault(False)
        self.logoutButton = QPushButton(mainMenuView)
        self.logoutButton.setObjectName(u"logoutButton")
        self.logoutButton.setGeometry(QRect(50, 780, 141, 21))
        font2 = QFont()
        font2.setFamilies([u"Stencil"])
        self.logoutButton.setFont(font2)

        self.retranslateUi(mainMenuView)

        QMetaObject.connectSlotsByName(mainMenuView)

    # setupUi

    def retranslateUi(self, mainMenuView):
        self.newGameButton.setText(QCoreApplication.translate("mainMenuView", u"Neues Spiel", None))
        self.label.setText(QCoreApplication.translate("mainMenuView", u"ETANKS", None))
        self.showProfilButton.setText(QCoreApplication.translate("mainMenuView", u"Profil", None))
        self.showStatisticButton.setText(QCoreApplication.translate("mainMenuView", u"Statisik", None))
        self.showSettingsButton.setText(QCoreApplication.translate("mainMenuView", u"Einstellungen", None))
        self.logoutButton.setText(QCoreApplication.translate("mainMenuView", u"Abmelden vom Dienst", None))
        pass
    # retranslateUi
