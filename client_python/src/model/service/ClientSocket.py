import json
import socket
import threading

from model.service.Message import Message


class ClientSocket:
    def __init__(self, newGameViewController):
        self.clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.host = "localhost"
        self.port = 3333
        self.newGame = newGameViewController

        self.connect()
        self.threadSendMsg = threading.Thread(target=self.sendMsg)
        self.threadSendMsg.start()
        self.threadReceiveMsg = threading.Thread(target=self.receiveMsg)
        self.threadReceiveMsg.start()


    def connect(self):
        self.clientSocket.connect((self.host, self.port))


    def sendMsg(self, msg):
        data_as_dict = vars(msg)
        msgJSON = json.dumps(data_as_dict)
        try:
            self.clientSocket.send(str.encode(msgJSON))
        except socket.error as e:
            return str(e)

    def receiveMsg(self):
        while True:
            msg = Message()
            receiveData = self.clientSocket.recv(8192)
            dataDec = receiveData.decode("utf-8")
            jsonData = json.loads(dataDec)

            msg.messageType = jsonData["messageType"]
            msg.gameLobbyNumber = jsonData["gameLobbyNumber"]
            msg.playerId = jsonData["playerId"]
            msg.playerPublicName = jsonData["playerPublicName"]
            msg.playerIsRdy = jsonData["playerIsRdy"]
            msg.playerImage = jsonData["playerImage"]
            msg.payload = jsonData["payload"]
            


            print("Msg Empfangen: ", jsonData)


