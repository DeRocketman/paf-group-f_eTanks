from PySide6.QtWidgets import QWidget
from PySide6.QtWidgets import QTableWidgetItem

from model.service.HttpRequest import HttpRequest
from model.service.RequestCode import RequestCode
from resources.view.StatisticView import Ui_statisticView


class StatisticViewController(QWidget):
    def __init__(self, mainMenuController):
        super().__init__()

        self.httpRequest = HttpRequest()
        self.mainMenuController = mainMenuController

        self.statisticView = Ui_statisticView()
        self.statisticView.setupUi(self)

        self.statisticView.showMainMenuView.clicked.connect(self.showMainMenuView)

    def loadData(self):
        self.httpRequest.user.id = self.mainMenuController.signedUser.id
        self.httpRequest.user.authToken = self.mainMenuController.signedUser.authToken

        if self.httpRequest.httpReq(RequestCode.STATISTIC_LIST):
            self.addStatistic()

        if self.httpRequest.httpReq(RequestCode.HIGHCSCORE_LIST):
            self.addHighscoreList()

    def addStatistic(self):
        totalDeaths = 0
        totalGamePoints = 0
        totalGameWins = 0
        totalRoundWins = 0
        totalHitPoints = 0
        totalHitRate = 0
        totalKillDeathRate = 0
        totalKills = 0
        totalPlayedGames = 0
        totalShots = 0

        for gameStatistic in self.httpRequest.statistics:
            totalPlayedGames = totalPlayedGames + 1
            totalDeaths = 1
            totalDeaths = totalDeaths + gameStatistic['deaths']
            totalGamePoints = totalGamePoints + gameStatistic['gamePoints']
            totalRoundWins = totalRoundWins + gameStatistic['roundWins']
            totalHitPoints = totalHitPoints + gameStatistic['hitPoints']
            totalKills = totalKills + gameStatistic['kills']
            totalShots = totalShots + gameStatistic['shots']
            if gameStatistic['winner']:
                totalGameWins = totalGameWins + 1

        totalHitRate = round(totalHitPoints / totalShots, 2)
        totalKillDeathRate = round(totalKills / totalDeaths, 2)

        self.statisticView.shots.setText(str(totalShots))
        self.statisticView.deaths.setText(str(totalDeaths))
        self.statisticView.gamePoints.setText(str(totalGamePoints))
        self.statisticView.gameWins.setText(str(totalGameWins))
        self.statisticView.roundWins.setText(str(totalRoundWins))
        self.statisticView.hitpoints.setText(str(totalHitPoints))
        self.statisticView.hitrate.setText(str(totalHitRate))
        self.statisticView.killDeathRate.setText(str(totalKillDeathRate))
        self.statisticView.kills.setText(str(totalKills))
        self.statisticView.playedGames.setText(str(totalPlayedGames))

    def addHighscoreList(self):
        for index, highscore in enumerate(self.httpRequest.highscoreList):
            self.statisticView.tableWidget.setItem(index, 0, QTableWidgetItem(highscore['userName']))
            self.statisticView.tableWidget.setItem(index, 1, QTableWidgetItem(str(highscore['gamePoints'])))

    def showMainMenuView(self):
        self.mainMenuController.stackedWidget.setCurrentWidget(self.mainMenuController)