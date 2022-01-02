import socket


class ClientSocket:
    def __init__(self):
        self.clientSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.host = "localhost"
        self.port = 9910
        self.response = self.clientSocket.recv(1024)

        print("Waiting for connection")
        try:
            self.clientSocket.connect((self.host, self.port))
        except socket.error as e:
            print(str(e))

    def sendMsg(self, msg):
        while True:
            self.clientSocket.send(str.encode(msg))
            responseMsg = self.clientSocket.recv(1024)
            print(responseMsg.decode("utf-8"))
            return responseMsg.decode("utf-8")
