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

class Ui_loginView(object):
    def setupUi(self, loginView):
        if not loginView.objectName():
            loginView.setObjectName(u"loginView")
        loginView.resize(1200, 850)
        loginView.setStyleSheet(u"background: #8A8557;\n"
"    background-image: url(C:/Users/DirkS/IdeaProjects/paf-gruppe-f_eTanks_HOT/client_jfx/src/main/resources/img/images/menuBackground/eTanksTitle.png);")
        self.usernameTextfield = QLineEdit(loginView)
        self.usernameTextfield.setObjectName(u"usernameTextfield")
        self.usernameTextfield.setGeometry(QRect(490, 470, 311, 31))
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.usernameTextfield.sizePolicy().hasHeightForWidth())
        self.usernameTextfield.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(16)
        self.usernameTextfield.setFont(font)
        self.usernameTextfield.setStyleSheet(u"background: white;\n"
"    ")
        self.passwortField = QLineEdit(loginView)
        self.passwortField.setObjectName(u"passwortField")
        self.passwortField.setGeometry(QRect(490, 510, 311, 31))
        sizePolicy.setHeightForWidth(self.passwortField.sizePolicy().hasHeightForWidth())
        self.passwortField.setSizePolicy(sizePolicy)
        self.passwortField.setFont(font)
        self.passwortField.setStyleSheet(u"background: white;\n"
"    ")
        self.forgotPwLbl = QLabel(loginView)
        self.forgotPwLbl.setObjectName(u"forgotPwLbl")
        self.forgotPwLbl.setGeometry(QRect(490, 550, 311, 16))
        font1 = QFont()
        font1.setPointSize(10)
        self.forgotPwLbl.setFont(font1)
        self.forgotPwLbl.setMouseTracking(False)
        self.forgotPwLbl.setStyleSheet(u"Color: Blue")
        self.newUserLbl = QLabel(loginView)
        self.newUserLbl.setObjectName(u"newUserLbl")
        self.newUserLbl.setGeometry(QRect(490, 570, 311, 16))
        self.newUserLbl.setFont(font1)
        self.newUserLbl.setMouseTracking(False)
        self.newUserLbl.setStyleSheet(u"Color: Blue")
        self.loginButton = QPushButton(loginView)
        self.loginButton.setObjectName(u"loginButton")
        self.loginButton.setGeometry(QRect(490, 600, 311, 31))
        sizePolicy.setHeightForWidth(self.loginButton.sizePolicy().hasHeightForWidth())
        self.loginButton.setSizePolicy(sizePolicy)
        font2 = QFont()
        font2.setPointSize(12)
        self.loginButton.setFont(font2)
        self.loginButton.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;")
        self.loginButton.setAutoDefault(False)

        self.retranslateUi(loginView)

        QMetaObject.connectSlotsByName(loginView)
    # setupUi

    def retranslateUi(self, loginView):
        self.usernameTextfield.setPlaceholderText(QCoreApplication.translate("loginView", u"Username", None))
        self.passwortField.setPlaceholderText(QCoreApplication.translate("loginView", u"Passwort", None))
        self.forgotPwLbl.setText(QCoreApplication.translate("loginView", u"Passwort vergessen?", None))
        self.newUserLbl.setText(QCoreApplication.translate("loginView", u"Neu hier?", None))
        self.loginButton.setText(QCoreApplication.translate("loginView", u"Login", None))
        pass
    # retranslateUi

