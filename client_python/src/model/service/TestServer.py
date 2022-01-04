import socket
from _thread import *

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

serverSocket.listen(4)
print("Server ist bereit und heiß auf Connections")


def threaded_client(con):
    con.send(str.encode("Du hast die Lobby erfolgreich betreten"))
    while True:
        msg = con.recv(2048)
        reply = msg.decode("utf-8")
        # con.send(str.encode(reply))
        print(reply)
        for player in playerList:
            player.send(str.encode(reply))


while True:
    connection, address = serverSocket.accept()
    print("Connected to: ", address)
    playerList.append(connection)
    start_new_thread(threaded_client, (connection,))
    threadCount += 1
