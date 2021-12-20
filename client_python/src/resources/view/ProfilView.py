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
from PySide6.QtWidgets import (QApplication, QGraphicsView, QLabel, QLineEdit,
    QPushButton, QSizePolicy, QWidget)

class Ui_profilView(object):
    def setupUi(self, profilView):
        if not profilView.objectName():
            profilView.setObjectName(u"profilView")
        profilView.resize(1200, 850)
        profilView.setStyleSheet(u"background: #8A8557;")
        self.showProfilButton = QPushButton(profilView)
        self.showProfilButton.setObjectName(u"showProfilButton")
        self.showProfilButton.setGeometry(QRect(120, 40, 201, 31))
        sizePolicy = QSizePolicy(QSizePolicy.Fixed, QSizePolicy.Fixed)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.showProfilButton.sizePolicy().hasHeightForWidth())
        self.showProfilButton.setSizePolicy(sizePolicy)
        font = QFont()
        font.setPointSize(12)
        self.showProfilButton.setFont(font)
        self.showProfilButton.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.showProfilButton.setAutoDefault(False)
        self.changePublicNameButton = QPushButton(profilView)
        self.changePublicNameButton.setObjectName(u"changePublicNameButton")
        self.changePublicNameButton.setGeometry(QRect(690, 360, 171, 31))
        sizePolicy.setHeightForWidth(self.changePublicNameButton.sizePolicy().hasHeightForWidth())
        self.changePublicNameButton.setSizePolicy(sizePolicy)
        self.changePublicNameButton.setFont(font)
        self.changePublicNameButton.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.changePublicNameButton.setAutoDefault(False)
        self.changePasswordButton = QPushButton(profilView)
        self.changePasswordButton.setObjectName(u"changePasswordButton")
        self.changePasswordButton.setGeometry(QRect(690, 450, 171, 31))
        sizePolicy.setHeightForWidth(self.changePasswordButton.sizePolicy().hasHeightForWidth())
        self.changePasswordButton.setSizePolicy(sizePolicy)
        self.changePasswordButton.setFont(font)
        self.changePasswordButton.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.changePasswordButton.setAutoDefault(False)
        self.publicNameTextField = QLineEdit(profilView)
        self.publicNameTextField.setObjectName(u"publicNameTextField")
        self.publicNameTextField.setGeometry(QRect(470, 360, 211, 31))
        self.publicNameTextField.setStyleSheet(u"background-color: white")
        self.passwordTextField = QLineEdit(profilView)
        self.passwordTextField.setObjectName(u"passwordTextField")
        self.passwordTextField.setGeometry(QRect(470, 450, 211, 31))
        self.passwordTextField.setStyleSheet(u"background-color: white")
        self.userImageView = QGraphicsView(profilView)
        self.userImageView.setObjectName(u"userImageView")
        self.userImageView.setGeometry(QRect(520, 50, 250, 250))
        self.selectUserImageBtn = QPushButton(profilView)
        self.selectUserImageBtn.setObjectName(u"selectUserImageBtn")
        self.selectUserImageBtn.setGeometry(QRect(790, 180, 121, 31))
        sizePolicy.setHeightForWidth(self.selectUserImageBtn.sizePolicy().hasHeightForWidth())
        self.selectUserImageBtn.setSizePolicy(sizePolicy)
        self.selectUserImageBtn.setFont(font)
        self.selectUserImageBtn.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.selectUserImageBtn.setAutoDefault(False)
        self.changePasswordButton_2 = QPushButton(profilView)
        self.changePasswordButton_2.setObjectName(u"changePasswordButton_2")
        self.changePasswordButton_2.setGeometry(QRect(510, 580, 291, 31))
        sizePolicy.setHeightForWidth(self.changePasswordButton_2.sizePolicy().hasHeightForWidth())
        self.changePasswordButton_2.setSizePolicy(sizePolicy)
        self.changePasswordButton_2.setFont(font)
        self.changePasswordButton_2.setStyleSheet(u"background-color: #47452E;\n"
"     text-fill:#111111;\n"
"     border-color: #111111;\n"
"     border-width: 2;\n"
"     pref-width:200;\n"
"     pref-height: 30;\n"
"")
        self.changePasswordButton_2.setAutoDefault(False)
        self.label = QLabel(profilView)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(470, 330, 391, 21))
        font1 = QFont()
        font1.setFamilies([u"Stencil"])
        font1.setPointSize(12)
        self.label.setFont(font1)
        self.label_2 = QLabel(profilView)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(470, 420, 391, 21))
        self.label_2.setFont(font)

        self.retranslateUi(profilView)

        QMetaObject.connectSlotsByName(profilView)
    # setupUi

    def retranslateUi(self, profilView):
        self.showProfilButton.setText(QCoreApplication.translate("profilView", u"Zur\u00fcck", None))
        self.changePublicNameButton.setText(QCoreApplication.translate("profilView", u"editieren", None))
        self.changePasswordButton.setText(QCoreApplication.translate("profilView", u"editieren", None))
        self.publicNameTextField.setPlaceholderText(QCoreApplication.translate("profilView", u"\u00f6ffentlicher Name", None))
        self.passwordTextField.setPlaceholderText(QCoreApplication.translate("profilView", u"passwort", None))
        self.selectUserImageBtn.setText(QCoreApplication.translate("profilView", u"Fresse w\u00e4hlen", None))
        self.changePasswordButton_2.setText(QCoreApplication.translate("profilView", u"speichern", None))
        self.label.setText(QCoreApplication.translate("profilView", u"<html><head/><body><p align=\"center\">\u00d6ffentlicher Name:</p></body></html>", None))
        self.label_2.setText(QCoreApplication.translate("profilView", u"<html><head/><body><p align=\"center\">Passwort:</p></body></html>", None))
        pass
    # retranslateUi

