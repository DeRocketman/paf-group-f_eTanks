import collections
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
        self.connect()
        self.deliveryToHostView = False
        self.threadSendJoinMsg = threading.Thread(target=self.sendMsg)
        self.threadSendJoinMsg.start()
        self.threadReceiveJoinMsg = threading.Thread(target=self.receiveMsg)
        self.threadReceiveJoinMsg.start()
        self.incomingMsgBox = collections.deque()

    def connect(self):
        self.clientSocket.connect((self.host, self.port))

    def sendMsg(self, msg):
        try:
            self.clientSocket.sendall(str.encode(msg))
        except socket.error as e:
            return str(e)

    def receiveMsg(self):
        while True:
            # todo: json.decoder.JSONDecodeError: Extra data:
            msg = self.clientSocket.recv(4096)
            print("Message mit der Länge ", len(msg), " eingegangen")
            msgDec = msg.decode("utf-8")
            dictMsg = json.loads(msgDec)
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
        elif msg.messageType == "CHAT_MSG" or msg.messageType == "RDY_STATUS" or msg.messageType == "JOINED_PLAYER":
            if self.deliveryToHostView:
                self.newGameViewController.lobbyHostView.receiveMsg(msg)
            else:
                self.newGameViewController.lobbyJoinView.receiveMsg(msg)
        elif msg.messageType == "GET_LOBBIES":
            self.newGameViewController.joinGameView.receiveJoinMsg(msg)
