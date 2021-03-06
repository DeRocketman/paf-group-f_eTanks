import json
import socket
import threading

from model.service.Message import Message


class ClientSocket:
    def __init__(self, newGameViewController):
        self.clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.host = "localhost"
        self.port = 3333

        self.newGameViewController = newGameViewController
        self.gameViewController = None
        self.connect()
        self.deliveryToHostView = False
        self.threadSendJoinMsg = threading.Thread(target=self.sendMsg)
        self.threadSendJoinMsg.start()
        self.threadReceiveJoinMsg = threading.Thread(target=self.receiveMsg)
        self.threadReceiveJoinMsg.start()
        self.incomingMsgBox = []

    def connect(self):
        self.clientSocket.connect((self.host, self.port))

    def sendMsg(self, msg):
        try:
            self.clientSocket.sendall(str.encode(msg + "\n"))
        except socket.error as e:
            return str(e)

    def receiveMsg(self):
        while True:
            msg = self.clientSocket.recv(1024)
            msgDec = msg.decode("utf-8").splitlines()
            for lines in msgDec:
                dictMsg = json.loads(lines)
                msgJson = Message()
                msgJson.messageType = dictMsg["messageType"]
                msgJson.gameLobbyNumber = dictMsg["gameLobbyNumber"]
                msgJson.playerId = dictMsg["playerId"]
                msgJson.playerPublicName = dictMsg["playerPublicName"]
                msgJson.playerIsRdy = dictMsg["playerIsRdy"]
                msgJson.playerImage = dictMsg["playerImage"]
                msgJson.payload = dictMsg["payload"]
                self.deliverMsg(msgJson)
                print("Message empfangen: ", dictMsg)

    def putMsgToBox(self, msg):
        self.incomingMsgBox.append(msg)

    def deliverMsg(self, msg):
        if msg.messageType == "REGISTER_LOBBY":
            self.newGameViewController.lobbyHostView.receiveMsg(msg)
            self.deliveryToHostView = True
        elif msg.messageType == "CHAT_MSG" or msg.messageType == "RDY_STATUS" or msg.messageType == "JOINED_PLAYER" \
                or msg.messageType == "START_GAME":
            if self.deliveryToHostView:
                self.newGameViewController.lobbyHostView.receiveMsg(msg)
            else:
                self.newGameViewController.lobbyJoinView.receiveMsg(msg)
        elif msg.messageType == "GET_LOBBIES":
            self.newGameViewController.joinGameView.receiveJoinMsg(msg)
        elif msg.messageType == "FIRE_MAIN" or msg.messageType == "MOVE_TANK":
            self.gameViewController.receiveMsg(msg)
