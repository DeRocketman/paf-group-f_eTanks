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
        registerUserView.setStyleSheet(u"background: #8A8557;")
        self.publicNameField = QLineEdit(registerUserView)
        self.publicNameField.setObjectName(u"publicNameField")
        self.publicNameField.setGeometry(QRect(480, 460, 361, 31))
        sizePolicy.setHeightForWidth(self.publicNameField.sizePolicy().hasHeightForWidth())
        self.publicNameField.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(16)
        self.publicNameField.setFont(font)
        self.publicNameField.setStyleSheet(u"background: white;\n"
                                           "    ")
        self.registerUserButton = QPushButton(registerUserView)
        self.registerUserButton.setObjectName(u"registerUserButton")
        self.registerUserButton.setEnabled(True)
        self.registerUserButton.setGeometry(QRect(530, 600, 261, 31))
        sizePolicy.setHeightForWidth(self.registerUserButton.sizePolicy().hasHeightForWidth())
        self.registerUserButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setPointSize(12)
        self.registerUserButton.setFont(font1)
        self.registerUserButton.setStyleSheet(u"background-color: #47452E;\n"
                                              "border-color: #111111;\n"
                                              "\n"
                                              "")
        self.registerUserButton.setAutoDefault(False)
        self.usernameTextfield = QLineEdit(registerUserView)
        self.usernameTextfield.setObjectName(u"usernameTextfield")
        self.usernameTextfield.setGeometry(QRect(480, 370, 361, 34))
        sizePolicy.setHeightForWidth(self.usernameTextfield.sizePolicy().hasHeightForWidth())
        self.usernameTextfield.setSizePolicy(sizePolicy)
        self.usernameTextfield.setFont(font)
        self.usernameTextfield.setStyleSheet(u"background: white;\n"
                                             "    ")
        self.label = QLabel(registerUserView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(450, 260, 431, 41))
        font2 = QFont()
        font2.setPointSize(24)
        font2.setBold(True)
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
        self.label_4.setGeometry(QRect(480, 500, 201, 31))
        self.label_4.setFont(font3)
        self.passwortField = QLineEdit(registerUserView)
        self.passwortField.setObjectName(u"passwortField")
        self.passwortField.setGeometry(QRect(480, 540, 361, 31))
        sizePolicy.setHeightForWidth(self.passwortField.sizePolicy().hasHeightForWidth())
        self.passwortField.setSizePolicy(sizePolicy)
        self.passwortField.setFont(font)
        self.passwortField.setStyleSheet(u"background: white;\n"
                                         "    ")
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

        self.retranslateUi(registerUserView)

        QMetaObject.connectSlotsByName(registerUserView)
    # setupUi

    def retranslateUi(self, registerUserView):
        self.publicNameField.setText("")
        self.publicNameField.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Anzeigenname", None))
        self.registerUserButton.setText(QCoreApplication.translate("registerUserView", u"Anmelden", None))
        self.usernameTextfield.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Username", None))
        self.label.setText(QCoreApplication.translate("registerUserView", u"Willkommen bei eTanks", None))
        self.label_2.setText(QCoreApplication.translate("registerUserView", u"Username:", None))
        self.label_3.setText(QCoreApplication.translate("registerUserView", u"Publicname:", None))
        self.label_4.setText(QCoreApplication.translate("registerUserView", u"Password:", None))
        self.passwortField.setPlaceholderText(QCoreApplication.translate("registerUserView", u"Passwort", None))
        self.backToLoginBtn.setText(QCoreApplication.translate("registerUserView", u"Zur\u00fcck", None))
        pass
    # retranslateUi

