# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'ProfilView.ui'
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
from PySide6.QtWidgets import (QApplication, QLabel, QLineEdit, QPushButton,
    QSizePolicy, QWidget)
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
        self.changePublicNameButton = QPushButton(profilView)
        self.changePublicNameButton.setObjectName(u"changePublicNameButton")
        self.changePublicNameButton.setGeometry(QRect(690, 360, 171, 31))
        sizePolicy.setHeightForWidth(self.changePublicNameButton.sizePolicy().hasHeightForWidth())
        self.changePublicNameButton.setSizePolicy(sizePolicy)
        self.changePublicNameButton.setFont(font)
        self.changePublicNameButton.setStyleSheet(u"background-color: #47452E;\n"
                                                  "border-color: #111111;\n"
                                                  "\n"
                                                  "")
        self.changePublicNameButton.setAutoDefault(False)
        self.changePasswordButton = QPushButton(profilView)
        self.changePasswordButton.setObjectName(u"changePasswordButton")
        self.changePasswordButton.setGeometry(QRect(690, 450, 171, 31))
        sizePolicy.setHeightForWidth(self.changePasswordButton.sizePolicy().hasHeightForWidth())
        self.changePasswordButton.setSizePolicy(sizePolicy)
        self.changePasswordButton.setFont(font)
        self.changePasswordButton.setStyleSheet(u"background-color: #47452E;\n"
                                                "border-color: #111111;\n"
                                                "\n"
                                                "")
        self.changePasswordButton.setAutoDefault(False)
        self.publicNameTextField = QLineEdit(profilView)
        self.publicNameTextField.setObjectName(u"publicNameTextField")
        self.publicNameTextField.setEnabled(False)
        self.publicNameTextField.setGeometry(QRect(470, 360, 211, 31))
        self.publicNameTextField.setStyleSheet(u"background-color: white")
        self.passwordTextField = QLineEdit(profilView)
        self.passwordTextField.setObjectName(u"passwordTextField")
        self.passwordTextField.setEnabled(False)
        self.passwordTextField.setGeometry(QRect(470, 450, 211, 31))
        self.passwordTextField.setStyleSheet(u"background-color: white")
        self.selectUserImageBtn = QPushButton(profilView)
        self.selectUserImageBtn.setObjectName(u"selectUserImageBtn")
        self.selectUserImageBtn.setGeometry(QRect(790, 180, 121, 31))
        sizePolicy.setHeightForWidth(self.selectUserImageBtn.sizePolicy().hasHeightForWidth())
        self.selectUserImageBtn.setSizePolicy(sizePolicy)
        self.selectUserImageBtn.setFont(font)
        self.selectUserImageBtn.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "   ")
        self.selectUserImageBtn.setAutoDefault(False)
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
        self.label = QLabel(profilView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(470, 330, 391, 21))
        self.label.setFont(font)
        self.label_2 = QLabel(profilView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(470, 420, 391, 21))
        self.label_2.setFont(font)
        self.userImage = QLabel(profilView)
        self.userImage.setObjectName(u"userImage")
        self.userImage.setGeometry(QRect(530, 70, 250, 250))
        self.userImage.setStyleSheet(u"border-image: url(:/userImage/default-user-image.png) 0 0 0 0 stretch stretch;\n"
                                     "")

        self.retranslateUi(profilView)

        QMetaObject.connectSlotsByName(profilView)
    # setupUi

    def retranslateUi(self, profilView):
        self.showMainMenuButton.setText(QCoreApplication.translate("profilView", u"Zur\u00fcck", None))
        self.changePublicNameButton.setText(QCoreApplication.translate("profilView", u"editieren", None))
        self.changePasswordButton.setText(QCoreApplication.translate("profilView", u"editieren", None))
        self.publicNameTextField.setPlaceholderText(QCoreApplication.translate("profilView", u"\u00f6ffentlicher Name", None))
        self.passwordTextField.setPlaceholderText(QCoreApplication.translate("profilView", u"passwort", None))
        self.selectUserImageBtn.setText(QCoreApplication.translate("profilView", u"Fresse w\u00e4hlen", None))
        self.writeChangesButton.setText(QCoreApplication.translate("profilView", u"speichern", None))
        self.label.setText(QCoreApplication.translate("profilView", u"<html><head/><body><p align=\"center\">\u00d6ffentlicher Name:</p></body></html>", None))
        self.label_2.setText(QCoreApplication.translate("profilView", u"<html><head/><body><p align=\"center\">Passwort:</p></body></html>", None))
        self.userImage.setText("")
        pass
    # retranslateUi
