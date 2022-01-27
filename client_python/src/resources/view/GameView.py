# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'GameViewpzcQGM.ui'
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
from PySide6.QtWidgets import (QApplication, QFrame, QHBoxLayout, QLCDNumber,
    QLabel, QSizePolicy, QWidget)

class Ui_gameWidget(object):
    def setupUi(self, gameWidget):
        if not gameWidget.objectName():
            gameWidget.setObjectName(u"gameWidget")
        gameWidget.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(gameWidget.sizePolicy().hasHeightForWidth())
        gameWidget.setSizePolicy(sizePolicy)
        gameWidget.setStyleSheet(u"background: #8A8557;\n"
                                 "")
        self.pitch = QWidget(gameWidget)
        self.pitch.setObjectName(u"pitch")
        self.pitch.setGeometry(QRect(0, 50, 1200, 800))
        sizePolicy.setHeightForWidth(self.pitch.sizePolicy().hasHeightForWidth())
        self.pitch.setSizePolicy(sizePolicy)
        self.pitch.setStyleSheet(u"background-color: grey;")
        self.topPanel = QWidget(gameWidget)
        self.topPanel.setObjectName(u"topPanel")
        self.topPanel.setGeometry(QRect(0, 0, 1400, 50))
        self.horizontalLayoutWidget = QWidget(self.topPanel)
        self.horizontalLayoutWidget.setObjectName(u"horizontalLayoutWidget")
        self.horizontalLayoutWidget.setGeometry(QRect(10, 0, 241, 51))
        self.horizontalLayout = QHBoxLayout(self.horizontalLayoutWidget)
        self.horizontalLayout.setObjectName(u"horizontalLayout")
        self.horizontalLayout.setContentsMargins(0, 0, 0, 0)
        self.roundTxtLbl = QLabel(self.horizontalLayoutWidget)
        self.roundTxtLbl.setObjectName(u"roundTxtLbl")

        self.horizontalLayout.addWidget(self.roundTxtLbl)

        self.roundNumberLbl = QLabel(self.horizontalLayoutWidget)
        self.roundNumberLbl.setObjectName(u"roundNumberLbl")
        sizePolicy1 = QSizePolicy(QSizePolicy.Maximum, QSizePolicy.Preferred)
        sizePolicy1.setHorizontalStretch(0)
        sizePolicy1.setVerticalStretch(0)
        sizePolicy1.setHeightForWidth(self.roundNumberLbl.sizePolicy().hasHeightForWidth())
        self.roundNumberLbl.setSizePolicy(sizePolicy1)

        self.horizontalLayout.addWidget(self.roundNumberLbl)

        self.clockTxtLbl = QLabel(self.horizontalLayoutWidget)
        self.clockTxtLbl.setObjectName(u"clockTxtLbl")

        self.horizontalLayout.addWidget(self.clockTxtLbl)

        self.clockLcd = QLCDNumber(self.horizontalLayoutWidget)
        self.clockLcd.setObjectName(u"clockLcd")
        self.clockLcd.setDigitCount(3)
        self.clockLcd.setProperty("intValue", 180)

        self.horizontalLayout.addWidget(self.clockLcd)

        self.horizontalLayoutWidget_2 = QWidget(self.topPanel)
        self.horizontalLayoutWidget_2.setObjectName(u"horizontalLayoutWidget_2")
        self.horizontalLayoutWidget_2.setGeometry(QRect(250, 0, 951, 51))
        self.horizontalLayout_2 = QHBoxLayout(self.horizontalLayoutWidget_2)
        self.horizontalLayout_2.setObjectName(u"horizontalLayout_2")
        self.horizontalLayout_2.setContentsMargins(0, 0, 0, 0)
        self.player1NameLbl = QLabel(self.horizontalLayoutWidget_2)
        self.player1NameLbl.setObjectName(u"player1NameLbl")

        self.horizontalLayout_2.addWidget(self.player1NameLbl)

        self.player1LcdOwn = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player1LcdOwn.setObjectName(u"player1LcdOwn")
        self.player1LcdOwn.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player1LcdOwn)

        self.label = QLabel(self.horizontalLayoutWidget_2)
        self.label.setObjectName(u"label")
        sizePolicy.setHeightForWidth(self.label.sizePolicy().hasHeightForWidth())
        self.label.setSizePolicy(sizePolicy)

        self.horizontalLayout_2.addWidget(self.label)

        self.player1LcdOther = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player1LcdOther.setObjectName(u"player1LcdOther")
        self.player1LcdOther.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player1LcdOther)

        self.player2NameLbl = QLabel(self.horizontalLayoutWidget_2)
        self.player2NameLbl.setObjectName(u"player2NameLbl")

        self.horizontalLayout_2.addWidget(self.player2NameLbl)

        self.player2LcdOwn = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player2LcdOwn.setObjectName(u"player2LcdOwn")
        self.player2LcdOwn.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player2LcdOwn)

        self.label_3 = QLabel(self.horizontalLayoutWidget_2)
        self.label_3.setObjectName(u"label_3")
        sizePolicy.setHeightForWidth(self.label_3.sizePolicy().hasHeightForWidth())
        self.label_3.setSizePolicy(sizePolicy)

        self.horizontalLayout_2.addWidget(self.label_3)

        self.player2LcdOther = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player2LcdOther.setObjectName(u"player2LcdOther")
        self.player2LcdOther.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player2LcdOther)

        self.player3NameLbl = QLabel(self.horizontalLayoutWidget_2)
        self.player3NameLbl.setObjectName(u"player3NameLbl")

        self.horizontalLayout_2.addWidget(self.player3NameLbl)

        self.player3LcdOwn = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player3LcdOwn.setObjectName(u"player3LcdOwn")
        self.player3LcdOwn.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player3LcdOwn)

        self.label_5 = QLabel(self.horizontalLayoutWidget_2)
        self.label_5.setObjectName(u"label_5")
        sizePolicy.setHeightForWidth(self.label_5.sizePolicy().hasHeightForWidth())
        self.label_5.setSizePolicy(sizePolicy)

        self.horizontalLayout_2.addWidget(self.label_5)

        self.player3LcdOther = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player3LcdOther.setObjectName(u"player3LcdOther")
        self.player3LcdOther.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player3LcdOther)

        self.player4NameLbl = QLabel(self.horizontalLayoutWidget_2)
        self.player4NameLbl.setObjectName(u"player4NameLbl")

        self.horizontalLayout_2.addWidget(self.player4NameLbl)

        self.player4LcdOwn = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player4LcdOwn.setObjectName(u"player4LcdOwn")
        self.player4LcdOwn.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player4LcdOwn)

        self.label_7 = QLabel(self.horizontalLayoutWidget_2)
        self.label_7.setObjectName(u"label_7")
        sizePolicy.setHeightForWidth(self.label_7.sizePolicy().hasHeightForWidth())
        self.label_7.setSizePolicy(sizePolicy)

        self.horizontalLayout_2.addWidget(self.label_7)

        self.player4LcdOther = QLCDNumber(self.horizontalLayoutWidget_2)
        self.player4LcdOther.setObjectName(u"player4LcdOther")
        self.player4LcdOther.setDigitCount(1)

        self.horizontalLayout_2.addWidget(self.player4LcdOther)


        self.retranslateUi(gameWidget)

        QMetaObject.connectSlotsByName(gameWidget)
    # setupUi

    def retranslateUi(self, gameWidget):
        self.roundTxtLbl.setText(QCoreApplication.translate("gameWidget", u"Round:", None))
        self.roundNumberLbl.setText(QCoreApplication.translate("gameWidget", u"1", None))
        self.clockTxtLbl.setText(QCoreApplication.translate("gameWidget", u"Clock:", None))
        self.player1NameLbl.setText(QCoreApplication.translate("gameWidget", u"PlayerName1:", None))
        self.label.setText(QCoreApplication.translate("gameWidget", u":", None))
        self.player2NameLbl.setText(QCoreApplication.translate("gameWidget", u"PlayerName2:", None))
        self.label_3.setText(QCoreApplication.translate("gameWidget", u":", None))
        self.player3NameLbl.setText(QCoreApplication.translate("gameWidget", u"PlayerName3:", None))
        self.label_5.setText(QCoreApplication.translate("gameWidget", u":", None))
        self.player4NameLbl.setText(QCoreApplication.translate("gameWidget", u"PlayerName4:", None))
        self.label_7.setText(QCoreApplication.translate("gameWidget", u":", None))
        pass
    # retranslateUi

