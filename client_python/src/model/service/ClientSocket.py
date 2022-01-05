import socket


class ClientSocket:
    def __init__(self):
        self.clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.host = "localhost"
        self.port = 3333

        print("Auf Antwort warten")

    def connect(self):
        self.clientSocket.connect((self.host, self.port))
        return self.clientSocket.recv(2048).decode()

    def sendMsg(self, msg):
        try:
            self.clientSocket.send(str.encode(msg))
        except socket.error as e:
            return str(e)

    def reseiveMsg(self):
        return self.clientSocket.recv(2048).decode()
