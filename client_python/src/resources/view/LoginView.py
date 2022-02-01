# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'LoginView.ui'
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


class Ui_loginView(object):
    def setupUi(self, loginView):
        if not loginView.objectName():
            loginView.setObjectName(u"loginView")
        loginView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(loginView.sizePolicy().hasHeightForWidth())
        loginView.setSizePolicy(sizePolicy)
        icon = QIcon()
        icon.addFile(u":/background/menuBackground/eTanksTitle.png", QSize(), QIcon.Normal, QIcon.Off)
        loginView.setWindowIcon(icon)
        loginView.setStyleSheet(u"background: #8A8557;\n"
                                "")
        self.usernameTextfield = QLineEdit(loginView)
        self.usernameTextfield.setObjectName(u"usernameTextfield")
        self.usernameTextfield.setGeometry(QRect(460, 470, 311, 31))
        sizePolicy.setHeightForWidth(self.usernameTextfield.sizePolicy().hasHeightForWidth())
        self.usernameTextfield.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(16)
        self.usernameTextfield.setFont(font)
        self.usernameTextfield.setStyleSheet(u"background: white;\n"
                                             "    ")
        self.passwortField = QLineEdit(loginView)
        self.passwortField.setObjectName(u"passwortField")
        self.passwortField.setGeometry(QRect(460, 510, 311, 31))
        sizePolicy.setHeightForWidth(self.passwortField.sizePolicy().hasHeightForWidth())
        self.passwortField.setSizePolicy(sizePolicy)
        self.passwortField.setFont(font)
        self.passwortField.setStyleSheet(u"background: white;\n"
                                         "    ")
        self.loginButton = QPushButton(loginView)
        self.loginButton.setObjectName(u"loginButton")
        self.loginButton.setEnabled(True)
        self.loginButton.setGeometry(QRect(460, 600, 311, 31))
        sizePolicy.setHeightForWidth(self.loginButton.sizePolicy().hasHeightForWidth())
        self.loginButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setPointSize(12)
        self.loginButton.setFont(font1)
        self.loginButton.setStyleSheet(u"background-color: #47452E;\n"
                                       "border-color: #111111;\n"
                                       " ")
        self.loginButton.setAutoDefault(False)
        self.loginButton.setFlat(False)
        self.label = QLabel(loginView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(0, 10, 1211, 791))
        sizePolicy.setHeightForWidth(self.label.sizePolicy().hasHeightForWidth())
        self.label.setSizePolicy(sizePolicy)
        self.label.setStyleSheet(u"border-image: url(:/background/menuBackground/eTanksTitle.png) 0 0 0 0 stretch stretch;")
        self.forgetPasswordButton = QPushButton(loginView)
        self.forgetPasswordButton.setObjectName(u"forgetPasswordButton")
        self.forgetPasswordButton.setGeometry(QRect(460, 550, 141, 20))
        font2 = QFont()
        font2.setKerning(False)
        self.forgetPasswordButton.setFont(font2)
        self.forgetPasswordButton.setStyleSheet(u"color: blue\n"
                                                "")
        self.forgetPasswordButton.setFlat(True)
        self.openUserViewButton = QPushButton(loginView)
        self.openUserViewButton.setObjectName(u"openUserViewButton")
        self.openUserViewButton.setGeometry(QRect(460, 570, 71, 20))
        self.openUserViewButton.setFont(font2)
        self.openUserViewButton.setStyleSheet(u"color: blue\n"
                                              "")
        self.openUserViewButton.setFlat(True)
        self.label.raise_()
        self.usernameTextfield.raise_()
        self.passwortField.raise_()
        self.loginButton.raise_()
        self.forgetPasswordButton.raise_()
        self.openUserViewButton.raise_()

        self.retranslateUi(loginView)

        QMetaObject.connectSlotsByName(loginView)
    # setupUi

    def retranslateUi(self, loginView):
        self.usernameTextfield.setPlaceholderText(QCoreApplication.translate("loginView", u"Username", None))
        self.passwortField.setPlaceholderText(QCoreApplication.translate("loginView", u"Passwort", None))
        self.loginButton.setText(QCoreApplication.translate("loginView", u"Login", None))
        self.label.setText("")
        self.forgetPasswordButton.setText(QCoreApplication.translate("loginView", u"Passwort vergessen?", None))
        self.openUserViewButton.setText(QCoreApplication.translate("loginView", u"Neu hier?", None))
        pass
    # retranslateUi

