from PySide6.QtWidgets import QWidget

from model.service.HttpRequest import HttpRequest
from model.service.RequestCode import RequestCode
from resources.view.StatisticView import Ui_statisticView


class StatisticViewController(QWidget):
    def __init__(self, mainMenuController):
        super().__init__()

        self.mainMenuController = mainMenuController


        self.statisticView = Ui_statisticView()
        self.statisticView.setupUi(self)

    def loadData(self):
        load = HttpRequest()
        load.user.id = self.mainMenuController.signedUser.id
        load.user.authToken = self.mainMenuController.signedUser.authToken

        if load.httpReq(RequestCode.STATISTIC_LIST):
            pass
            #self.addStatistic()
        else:
            print("Das hat nicht geklappt.")

        if load.httpReq(RequestCode.HIGHCSCORE_LIST):
            self.addHighscoreList()
        else:
            print("Das hat nicht geklappt.")

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

        for gameStatistic in self.mainMenuController.gameStatisticList:
            totalPlayedGames = totalPlayedGames + 1
            totalDeaths = totalDeaths + gameStatistic.deaths
            totalGamePoints = totalGamePoints + gameStatistic.gamePoints
            totalRoundWins = totalRoundWins + gameStatistic.roundWins
            totalHitPoints = totalHitPoints + gameStatistic.hitPoints
            totalKills = totalKills + gameStatistic.kills
            totalShots = totalShots + gameStatistic.shots
            if gameStatistic.winner == 1:
                totalGameWins = totalGameWins + 1

        totalHitRate = totalHitPoints / totalShots
        totalKillDeathRate = totalKills / totalDeaths

        self.statisticView.shots.setText(totalShots)
        self.statisticView.deaths.setText(totalDeaths)
        self.statisticView.gamePoints.setText(totalGamePoints)
        self.statisticView.gameWins.setText(totalGameWins)
        self.statisticView.roundWins.setText(totalRoundWins)
        self.statisticView.hitpoints.setText(totalHitPoints)
        self.statisticView.hitrate.setText(totalHitRate)
        self.statisticView.killDeathRate.setText(totalKillDeathRate)
        self.statisticView.kills.setText(totalKills)
        self.statisticView.playedGames.setText(totalPlayedGames)

        print("Funktioniert!")

    def addHighscoreList(self):
        print("Hier kommt die Highscore Liste.")
