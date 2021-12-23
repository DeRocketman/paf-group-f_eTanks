# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'RegisterUserView.ui'
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

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
                            QMetaObject, QObject, QPoint, QRect,
                            QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
                           QFont, QFontDatabase, QGradient, QIcon,
                           QImage, QKeySequence, QLinearGradient, QPainter,
                           QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QLabel, QLineEdit, QPushButton,
                               QSizePolicy, QWidget)

class Ui_registerUserView(object):
    def setupUi(self, registerUserView):
        if not registerUserView.objectName():
            registerUserView.setObjectName(u"registerUserView")
        registerUserView.resize(1200, 850)
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(registerUserView.sizePolicy().hasHeightForWidth())
        registerUserView.setSizePolicy(sizePolicy)
        font = QFont()
        font.setFamilies([u"Stencil"])
        registerUserView.setFont(font)
        registerUserView.setStyleSheet(u"background: #8A8557;")
        self.registerUserButton = QPushButton(registerUserView)
        self.registerUserButton.setObjectName(u"registerUserButton")
        self.registerUserButton.setEnabled(True)
        self.registerUserButton.setGeometry(QRect(500, 600, 261, 31))
        sizePolicy.setHeightForWidth(self.registerUserButton.sizePolicy().hasHeightForWidth())
        self.registerUserButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setFamilies([u"Stencil"])
        font1.setPointSize(12)
        self.registerUserButton.setFont(font1)
        self.registerUserButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "\n"
                                              "")
        self.registerUserButton.setAutoDefault(False)
        self.label = QLabel(registerUserView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(450, 260, 431, 41))
        font2 = QFont()
        font2.setFamilies([u"Stencil"])
        font2.setPointSize(24)
        font2.setBold(False)
        self.label.setFont(font2)
        self.label_2 = QLabel(registerUserView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(480, 330, 151, 31))
        font3 = QFont()
        font3.setPointSize(24)
        font3.setBold(False)
        self.label_2.setFont(font3)
        self.label_3 = QLabel(registerUserView)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(480, 420, 201, 31))
        self.label_3.setFont(font3)
        self.label_4 = QLabel(registerUserView)
        self.label_4.setObjectName(u"label_4")
        self.label_4.setGeometry(QRect(480, 510, 201, 31))
        self.label_4.setFont(font3)
        self.backToLoginBtn = QPushButton(registerUserView)
        self.backToLoginBtn.setObjectName(u"backToLoginBtn")
        self.backToLoginBtn.setGeometry(QRect(30, 40, 181, 31))
        sizePolicy.setHeightForWidth(self.backToLoginBtn.sizePolicy().hasHeightForWidth())
        self.backToLoginBtn.setSizePolicy(sizePolicy)
        self.backToLoginBtn.setFont(font1)
        self.backToLoginBtn.setStyleSheet(u"background-color: #47452E;\n"
                                          "border-color: #111111;\n"
                                          " \n"
                                          "")
        self.backToLoginBtn.setAutoDefault(False)
        self.usernameField = QLineEdit(registerUserView)
        self.usernameField.setObjectName(u"usernameField")
        self.usernameField.setGeometry(QRect(480, 370, 301, 31))
        font4 = QFont()
        font4.setPointSize(16)
        self.usernameField.setFont(font4)
        self.usernameField.setStyleSheet(u"background: white;\n"
                                         "color: black;")
        self.publicNameField = QLineEdit(registerUserView)
        self.publicNameField.setObjectName(u"publicNameField")
        self.publicNameField.setGeometry(QRect(480, 460, 301, 31))
        self.publicNameField.setFont(font4)
        self.publicNameField.setStyleSheet(u"background: white;\n"
                                           "color: black;")
        self.passwordField = QLineEdit(registerUserView)
        self.passwordField.setObjectName(u"passwordField")
        self.passwordField.setGeometry(QRect(480, 550, 301, 31))
        self.passwordField.setFont(font4)
        self.passwordField.setStyleSheet(u"background: white;\n"
                                         "color: black;")

        self.retranslateUi(registerUserView)

        QMetaObject.connectSlotsByName(registerUserView)
    # setupUi

    def retranslateUi(self, registerUserView):
        self.registerUserButton.setText(QCoreApplication.translate("registerUserView", u"Anmelden", None))
        self.label.setText(QCoreApplication.translate("registerUserView", u"Willkommen bei eTanks", None))
        self.label_2.setText(QCoreApplication.translate("registerUserView", u"Username:", None))
        self.label_3.setText(QCoreApplication.translate("registerUserView", u"Publicname:", None))
        self.label_4.setText(QCoreApplication.translate("registerUserView", u"Password:", None))
        self.backToLoginBtn.setText(QCoreApplication.translate("registerUserView", u"Zur\u00fcck", None))
        self.usernameField.setText("")
        self.usernameField.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Username w\u00e4hlen", None))
        self.publicNameField.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Anzeigenname w\u00e4hlen", None))
        self.passwordField.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Passwort w\u00e4hlen", None))
        pass
    # retranslateUi

