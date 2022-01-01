import socket


class LobbySocketServer:
    def __init__(self):
        self.socketServer = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server = "localhost"
        self.port = 55550
        self.currentId = 0

    def runServer(self):
        try:
            self.socketServer.bind((self.server, self.port))
        except socket.error as e:
            print(str(e))

        self.socketServer.listen(4)
        print("[Server] grats i am runnin")
        print("[Server] Waiting for connection")

    def threaded_client(self, connection):
        connection.send(int.encode(self.currentId))
        self.currentId += 1
        reply = ""
        while True:
            data = connection.recv(4096)
            reply = data.decode("utf-8")
            if not data:
                connection.send(str.encode("Goodbye"))
                break
