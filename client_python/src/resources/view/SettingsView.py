# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'SettingsViewYvEgHj.ui'
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
from PySide6.QtWidgets import (QApplication, QFormLayout, QLabel, QPushButton,
                               QSizePolicy, QSlider, QWidget)
from resources.view import rc_ressourcesETanks

class Ui_profilView(object):
    def setupUi(self, profilView):
        if not profilView.objectName():
            profilView.setObjectName(u"profilView")
        profilView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(profilView.sizePolicy().hasHeightForWidth())
        profilView.setSizePolicy(sizePolicy)
        profilView.setStyleSheet(u"background: #8A8557;")
        self.showMainMenuButton = QPushButton(profilView)
        self.showMainMenuButton.setObjectName(u"showMainMenuButton")
        self.showMainMenuButton.setGeometry(QRect(120, 40, 201, 31))
        sizePolicy.setHeightForWidth(self.showMainMenuButton.sizePolicy().hasHeightForWidth())
        self.showMainMenuButton.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(12)
        self.showMainMenuButton.setFont(font)
        self.showMainMenuButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "  \n"
                                              "")
        self.showMainMenuButton.setAutoDefault(False)
        self.writeChangesButton = QPushButton(profilView)
        self.writeChangesButton.setObjectName(u"writeChangesButton")
        self.writeChangesButton.setEnabled(False)
        self.writeChangesButton.setGeometry(QRect(510, 580, 291, 31))
        sizePolicy.setHeightForWidth(self.writeChangesButton.sizePolicy().hasHeightForWidth())
        self.writeChangesButton.setSizePolicy(sizePolicy)
        self.writeChangesButton.setFont(font)
        self.writeChangesButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "\n"
                                              "")
        self.writeChangesButton.setAutoDefault(False)
        self.moveUpButton = QPushButton(profilView)
        self.moveUpButton.setObjectName(u"moveUpButton")
        self.moveUpButton.setGeometry(QRect(560, 454, 51, 51))
        self.moveDownButton = QPushButton(profilView)
        self.moveDownButton.setObjectName(u"moveDownButton")
        self.moveDownButton.setGeometry(QRect(560, 504, 51, 51))
        self.moveRightButton = QPushButton(profilView)
        self.moveRightButton.setObjectName(u"moveRightButton")
        self.moveRightButton.setGeometry(QRect(610, 504, 51, 51))
        self.moveLeftButton = QPushButton(profilView)
        self.moveLeftButton.setObjectName(u"moveLeftButton")
        self.moveLeftButton.setGeometry(QRect(510, 504, 51, 51))
        self.mainWeaponButton = QPushButton(profilView)
        self.mainWeaponButton.setObjectName(u"mainWeaponButton")
        self.mainWeaponButton.setGeometry(QRect(700, 504, 51, 51))
        self.secondaryWeaponButton = QPushButton(profilView)
        self.secondaryWeaponButton.setObjectName(u"secondaryWeaponButton")
        self.secondaryWeaponButton.setGeometry(QRect(750, 504, 51, 51))
        self.label_5 = QLabel(profilView)
        self.label_5.setObjectName(u"label_5")
        self.label_5.setGeometry(QRect(565, 434, 41, 18))
        font1 = QFont()
        font1.setPointSize(8)
        self.label_5.setFont(font1)
        self.label_6 = QLabel(profilView)
        self.label_6.setObjectName(u"label_6")
        self.label_6.setGeometry(QRect(508, 484, 51, 18))
        self.label_6.setFont(font1)
        self.label_7 = QLabel(profilView)
        self.label_7.setObjectName(u"label_7")
        self.label_7.setGeometry(QRect(556, 555, 61, 18))
        self.label_7.setFont(font1)
        self.label_8 = QLabel(profilView)
        self.label_8.setObjectName(u"label_8")
        self.label_8.setGeometry(QRect(613, 484, 51, 18))
        self.label_8.setFont(font1)
        self.label_9 = QLabel(profilView)
        self.label_9.setObjectName(u"label_9")
        self.label_9.setGeometry(QRect(701, 484, 51, 18))
        self.label_9.setFont(font1)
        self.label_10 = QLabel(profilView)
        self.label_10.setObjectName(u"label_10")
        self.label_10.setGeometry(QRect(754, 484, 51, 18))
        self.label_10.setFont(font1)
        self.widget = QWidget(profilView)
        self.widget.setObjectName(u"widget")
        self.widget.setGeometry(QRect(510, 280, 291, 141))
        self.formLayout = QFormLayout(self.widget)
        self.formLayout.setObjectName(u"formLayout")
        self.formLayout.setContentsMargins(0, 0, 0, 0)
        self.label = QLabel(self.widget)
        self.label.setObjectName(u"label")
        font2 = QFont()
        font2.setPointSize(14)
        self.label.setFont(font2)

        self.formLayout.setWidget(0, QFormLayout.LabelRole, self.label)

        self.soundOnButton = QPushButton(self.widget)
        self.soundOnButton.setObjectName(u"soundOnButton")

        self.formLayout.setWidget(0, QFormLayout.FieldRole, self.soundOnButton)

        self.label_2 = QLabel(self.widget)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setFont(font2)

        self.formLayout.setWidget(1, QFormLayout.LabelRole, self.label_2)

        self.musicOnButton = QPushButton(self.widget)
        self.musicOnButton.setObjectName(u"musicOnButton")

        self.formLayout.setWidget(1, QFormLayout.FieldRole, self.musicOnButton)

        self.label_4 = QLabel(self.widget)
        self.label_4.setObjectName(u"label_4")
        self.label_4.setFont(font2)

        self.formLayout.setWidget(2, QFormLayout.LabelRole, self.label_4)

        self.musicVolSlider = QSlider(self.widget)
        self.musicVolSlider.setObjectName(u"musicVolSlider")
        self.musicVolSlider.setMaximum(100)
        self.musicVolSlider.setValue(100)
        self.musicVolSlider.setOrientation(Qt.Horizontal)
        self.musicVolSlider.setTickPosition(QSlider.TicksBothSides)

        self.formLayout.setWidget(2, QFormLayout.FieldRole, self.musicVolSlider)

        self.label_3 = QLabel(self.widget)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setFont(font2)

        self.formLayout.setWidget(3, QFormLayout.LabelRole, self.label_3)

        self.soundVolSlider = QSlider(self.widget)
        self.soundVolSlider.setObjectName(u"soundVolSlider")
        self.soundVolSlider.setMaximum(100)
        self.soundVolSlider.setValue(100)
        self.soundVolSlider.setOrientation(Qt.Horizontal)
        self.soundVolSlider.setTickPosition(QSlider.TicksBothSides)

        self.formLayout.setWidget(3, QFormLayout.FieldRole, self.soundVolSlider)


        self.retranslateUi(profilView)

        QMetaObject.connectSlotsByName(profilView)
    # setupUi

    def retranslateUi(self, profilView):
        self.showMainMenuButton.setText(QCoreApplication.translate("profilView", u"Zur\u00fcck", None))
        self.writeChangesButton.setText(QCoreApplication.translate("profilView", u"speichern", None))
        self.moveUpButton.setText(QCoreApplication.translate("profilView", u"W", None))
        self.moveDownButton.setText(QCoreApplication.translate("profilView", u"S", None))
        self.moveRightButton.setText(QCoreApplication.translate("profilView", u"D", None))
        self.moveLeftButton.setText(QCoreApplication.translate("profilView", u"A", None))
        self.mainWeaponButton.setText(QCoreApplication.translate("profilView", u"N", None))
        self.secondaryWeaponButton.setText(QCoreApplication.translate("profilView", u"M", None))
        self.label_5.setText(QCoreApplication.translate("profilView", u"move up", None))
        self.label_6.setText(QCoreApplication.translate("profilView", u"move left", None))
        self.label_7.setText(QCoreApplication.translate("profilView", u"move down", None))
        self.label_8.setText(QCoreApplication.translate("profilView", u"move right", None))
        self.label_9.setText(QCoreApplication.translate("profilView", u"fire main", None))
        self.label_10.setText(QCoreApplication.translate("profilView", u"fire sec", None))
        self.label.setText(QCoreApplication.translate("profilView", u"Sound", None))
        self.soundOnButton.setText(QCoreApplication.translate("profilView", u"An", None))
        self.label_2.setText(QCoreApplication.translate("profilView", u"Hintergrundmusik", None))
        self.musicOnButton.setText(QCoreApplication.translate("profilView", u"An", None))
        self.label_4.setText(QCoreApplication.translate("profilView", u"Sound-Lautst\u00e4rke", None))
        self.label_3.setText(QCoreApplication.translate("profilView", u"Musik-Lautst\u00e4rke", None))
        pass
    # retranslateUi

