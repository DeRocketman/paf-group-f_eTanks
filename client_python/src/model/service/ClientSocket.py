import json
import socket


class ClientSocket:
    def __init__(self):
        self.clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.host = "localhost"
        self.port = 3333

    def connect(self):
        self.clientSocket.connect((self.host, self.port))

    def sendMsg(self, msg):
        try:
            self.clientSocket.send(str.encode(msg))
        except socket.error as e:
            return str(e)

    def receiveMsg(self):
        msg = self.clientSocket.recv(4096)
        msgDec = msg.decode("utf-8")
        return json.loads(msgDec)
