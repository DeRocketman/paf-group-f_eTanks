import json
import threading

import pygame as pg

from model.game.logic.GamePhysics import GamePhysics
from model.service.Message import Message
from resources.view.GameView import GameView


class GameViewController:
    def __init__(self, newGameViewController):
        pg.init()
        self.gameView = GameView()
        self.newGameViewController = newGameViewController
        self.signedPlayer = self.newGameViewController.mainMenuViewController.signedUser
        self.gameSocket = self.newGameViewController.clientSocket
        self.playerList = []
        self.gameId = 0
        self.roundCounter = 1
        self.FPS = GamePhysics.FRAMES_PER_SECONDS
        self.gameWindow = None
        self.threadGameLoop = None

    def initGame(self, playerList, lobbyId):
        self.playerList = playerList
        self.gameId = lobbyId
        self.gameView.drawLvl(self.roundCounter)
        self.gameView.drawTanks(self.playerList)
        self.gameView.drawWindow()
        self.threadGameLoop = threading.Thread(target=self.gameLoop())
        self.threadGameLoop.start()

    def sendMoveTankMsg(self, course):
        msg = Message()
        msg.messageType = "MOVE_TANK"
        msg.playerId = self.signedPlayer.id
        msg.playerPublicName = self.signedPlayer.publicName
        msg.playerImage = "default"
        msg.gameLobbyNumber = self.gameId
        msg.payload = str(course)
        self.sendMsg(msg)

    def sendFireMainMsg(self):
        msg = Message()
        msg.messageType = "FIRE_MAIN"
        msg.playerId = self.signedPlayer.id
        msg.playerPublicName = self.signedPlayer.publicName
        msg.playerImage = "default"
        msg.gameLobbyNumber = self.gameId
        msg.payload = "BOOM-BACK"
        self.sendMsg(msg)

    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        self.gameSocket.sendMsg(msgJSON)

    def receiveMsg(self, msg):
        if msg is not None:
            if msg.messageType == "TANK_MOVE":
                self.processMoveTankMsg(msg)
            elif msg.messageType == "FIRE_MAIN":
                self.processFireMainMsg(msg)

    def processFireMainMsg(self, msg):
        for tank in self.gameView.tanks:
            if msg.playerId == tank.ownerId:
                tank.fireMainWeapon()

    def processMoveTankMsg(self, msg):
        for tank in self.gameView.tanks:
            if msg.playerId == tank.ownerId:
                if msg["payload"] == "180.0":
                    tank.moveDown()
                elif msg["payload"] == "360.0":
                    tank.moveUp()
                elif msg["payload"] == "90.0":
                    tank.moveRight()
                elif msg["payload"] == "270.0":
                    tank.moveLeft()

    def gameLoop(self):
        clock = pg.time.Clock()
        gameIsRunning = True
        while gameIsRunning:
            clock.tick(self.FPS)
            for event in pg.event.get():
                if event.type == pg.K_DOWN:
                    if event.key == pg.QUIT:
                        gameIsRunning = False
                        pg.quit()
                        break
            pressed = pg.key.get_pressed()

            if pressed[pg.K_w]:
                self.sendMoveTankMsg("360.0")
                self.gameView.myTank.moveUp()
            if pressed[pg.K_s]:
                self.sendMoveTankMsg("180.0")
                self.gameView.myTank.moveDown()
            if pressed[pg.K_a]:
                self.sendMoveTankMsg("270.0")
            if pressed[pg.K_d]:
                self.sendMoveTankMsg("90.0")

            if pressed[pg.K_ESCAPE]:
                print("Lieber ausmachen")
                pg.quit()
                break

            self.gameView.drawLvl(self.roundCounter)
            self.gameView.allsprites.draw(self.gameView.pitch)
            self.gameView.drawWindow()
            pg.display.flip()
