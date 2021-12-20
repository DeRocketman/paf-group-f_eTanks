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

class Ui_reisterUserView(object):
    def setupUi(self, reisterUserView):
        if not reisterUserView.objectName():
            reisterUserView.setObjectName(u"reisterUserView")
        reisterUserView.resize(1200, 850)
        reisterUserView.setStyleSheet(u"background: #8A8557;")
        self.publicNameField = QLineEdit(reisterUserView)
        self.publicNameField.setObjectName(u"publicNameField")
        self.publicNameField.setGeometry(QRect(480, 460, 361, 31))
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.publicNameField.sizePolicy().hasHeightForWidth())
        self.publicNameField.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(16)
        self.publicNameField.setFont(font)
        self.publicNameField.setStyleSheet(u"background: white;\n"
"    ")
        self.registerUserButton = QPushButton(reisterUserView)
        self.registerUserButton.setObjectName(u"registerUserButton")
        self.registerUserButton.setGeometry(QRect(530, 600, 261, 31))
        sizePolicy.setHeightForWidth(self.registerUserButton.sizePolicy().hasHeightForWidth())
        self.registerUserButton.setSizePolicy(sizePolicy)
        font1 = QFont()
        font1.setPointSize(12)
        self.registerUserButton.setFont(font1)
        self.registerUserButton.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.registerUserButton.setAutoDefault(False)
        self.usernameTextfield = QLineEdit(reisterUserView)
        self.usernameTextfield.setObjectName(u"usernameTextfield")
        self.usernameTextfield.setGeometry(QRect(480, 370, 361, 34))
        sizePolicy.setHeightForWidth(self.usernameTextfield.sizePolicy().hasHeightForWidth())
        self.usernameTextfield.setSizePolicy(sizePolicy)
        self.usernameTextfield.setFont(font)
        self.usernameTextfield.setStyleSheet(u"background: white;\n"
"    ")
        self.label = QLabel(reisterUserView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(480, 260, 321, 41))
        font2 = QFont()
        font2.setPointSize(18)
        font2.setBold(True)
        self.label.setFont(font2)
        self.label_2 = QLabel(reisterUserView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(480, 330, 151, 31))
        font3 = QFont()
        font3.setPointSize(24)
        font3.setBold(False)
        self.label_2.setFont(font3)
        self.label_3 = QLabel(reisterUserView)
        self.label_3.setObjectName(u"label_3")
        self.label_3.setGeometry(QRect(480, 420, 201, 31))
        self.label_3.setFont(font3)
        self.label_4 = QLabel(reisterUserView)
        self.label_4.setObjectName(u"label_4")
        self.label_4.setGeometry(QRect(480, 500, 201, 31))
        self.label_4.setFont(font3)
        self.passwortField = QLineEdit(reisterUserView)
        self.passwortField.setObjectName(u"passwortField")
        self.passwortField.setGeometry(QRect(480, 540, 361, 31))
        sizePolicy.setHeightForWidth(self.passwortField.sizePolicy().hasHeightForWidth())
        self.passwortField.setSizePolicy(sizePolicy)
        self.passwortField.setFont(font)
        self.passwortField.setStyleSheet(u"background: white;\n"
"    ")
        self.backToLoginBtn = QPushButton(reisterUserView)
        self.backToLoginBtn.setObjectName(u"backToLoginBtn")
        self.backToLoginBtn.setGeometry(QRect(30, 40, 181, 31))
        sizePolicy.setHeightForWidth(self.backToLoginBtn.sizePolicy().hasHeightForWidth())
        self.backToLoginBtn.setSizePolicy(sizePolicy)
        self.backToLoginBtn.setFont(font1)
        self.backToLoginBtn.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.backToLoginBtn.setAutoDefault(False)

        self.retranslateUi(reisterUserView)

        QMetaObject.connectSlotsByName(reisterUserView)
    # setupUi

    def retranslateUi(self, reisterUserView):
        self.publicNameField.setText("")
        self.publicNameField.setPlaceholderText(QCoreApplication.translate("reisterUserView", u"Anzeigenname", None))
        self.registerUserButton.setText(QCoreApplication.translate("reisterUserView", u"Anmelden", None))
        self.usernameTextfield.setPlaceholderText(QCoreApplication.translate("reisterUserView", u"Username", None))
        self.label.setText(QCoreApplication.translate("reisterUserView", u"Willkommen bei eTanks", None))
        self.label_2.setText(QCoreApplication.translate("reisterUserView", u"Username:", None))
        self.label_3.setText(QCoreApplication.translate("reisterUserView", u"Publicname:", None))
        self.label_4.setText(QCoreApplication.translate("reisterUserView", u"Password:", None))
        self.passwortField.setPlaceholderText(QCoreApplication.translate("reisterUserView", u"Passwort", None))
        self.backToLoginBtn.setText(QCoreApplication.translate("reisterUserView", u"Zur\u00fcck", None))
        pass
    # retranslateUi

