class Level:
    def __init__(self, lvlNumber):
        self.levelNumber = lvlNumber
        self.backgroundPath = ""


class LevelBuilder:
    def __init__(self):
        self.lvl1 = Level(1)
        self.lvl2 = Level(2)
        self.lvl3 = Level(3)

        self.lvl1.backgroundPath = "../images/levelBackground/Ground_Tile_02_A.png"
        self.lvl2.backgroundPath = "../images/levelBackground/Ground_Tile_01_C.png"
        self.lvl3.backgroundPath = "../images/levelBackground/Ground_Tile_02_C.png"

    def buildLvl(self, lvlNumber):
        if lvlNumber == 1:
            return self.lvl1
        elif lvlNumber == 2:
            return self.lvl2
        else:
            return self.lvl3
