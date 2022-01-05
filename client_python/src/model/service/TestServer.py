import json
import socket
from _thread import *


class ExtendedConnectionInfo:
    connection = None
    isLobbyHost = None
    lobbyId = None


serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server = "localhost"
port = 3333
serverIP = socket.gethostbyname(server)
threadCount = 0
playerList = []

try:
    serverSocket.bind((server, port))
except socket.error as e:
    print(str(e))

serverSocket.listen(10)
print("Server ist bereit und heiß auf Connections")


def threaded_client(exConnInfo):
    # con.send(str.encode("Du hast die Lobby erfolgreich betreten"))
    while True:
        msg = exConnInfo.connection.recv(2048)
        reply = msg.decode("utf-8")
        print(reply)
        data_variable = json.loads(reply)
        if data_variable["messageType"] == "CHAT_MSG":
            for player in playerList:
                player.connection.send(str.encode(reply))


while True:
    extendedConnInfo = ExtendedConnectionInfo()
    extendedConnInfo.connection, address = serverSocket.accept()
    print("Connected to: ", address)
    playerList.append(extendedConnInfo)
    start_new_thread(threaded_client, (extendedConnInfo,))
    threadCount += 1
