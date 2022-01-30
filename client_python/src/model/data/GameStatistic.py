from model.data.User import User

class GameStatistic:

    def __init__(self):
        # self.user = User()
        self.id = 0
        self.userName = ""
        self.gameNumber = 1100101
        self.winner = 0
        self.roundWins = 0
        self.gamePoints = 0
        self.kills = 0
        self.deaths = 0
        self.shots = 0
        self.hitPoints = 0
        self.killDeathRate = self.calculateKillDeathRate
        self.hitRate = self.calculateHitRate

    def calculateKillDeathRate(self):
        if self.deaths > 0:
            return self.kills / self.deaths
        else:
            return self.kills

    def calculateHitRate(self):
        if self.deaths > 0:
            return self.hitPoints / self.shots
        else:
            return 0

    def addGamePoints(self, points):
        self.gamePoints += points

    def addKill(self):
        self.kills += 1
        self.calculateKillDeathRate()

    def addDeath(self):
        self.deaths += 1
        self.calculateKillDeathRate()

    def addShot(self):
        self.shots += 1
        self.calculateHitRate()

    def addHit(self):
        self.hitPoints += 1
        self.calculateHitRate()
