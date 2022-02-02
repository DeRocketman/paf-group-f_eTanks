import json
import socket
import struct
from _thread import *

from ExtendedSocketData import ExtendedSocketData
from ServerLobby import ServerLobby


class SocketServer:
    def __init__(self):
        self.serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server = "localhost"
        self.port = 3333
        self.serverIP = socket.gethostbyname(self.server)
        self.connSocketList = []
        self.lobbyList = []

        try:
            self.serverSocket.bind((self.server, self.port))
        except socket.error as e:
            print(str(e))

        self.serverSocket.listen(10)
        print("Server ist bereit und heiß auf Connections")

    def threadedClient(self, exSockData):
        while True:
            msg = exSockData.connection.recv(1024)
            jsonDec = msg.decode("utf-8").splitlines()

            for line in jsonDec:
                msgJson = json.loads(line)
                print("Server: Nachricht empfangen von ", exSockData.playerPublicName, " ", msgJson)

                if msgJson["messageType"] == "LOGIN":
                    exSockData.setPlayerData(msgJson)

                elif msgJson["messageType"] == "REGISTER_LOBBY":
                    exSockData.registerLobby(msgJson)
                    lobby = ServerLobby(exSockData.lobbyId)
                    lobby.playerCount += 1
                    lobby.addPlayerConn(exSockData)
                    lobby.hostConnection = exSockData
                    self.lobbyList.append(lobby)

                elif msgJson["messageType"] == "JOIN":
                    exSockData.joinLobby(msgJson)
                    for lobby in self.lobbyList:
                        if lobby.lobbyId == msgJson["gameLobbyNumber"]:
                            lobby.addPlayerConn(exSockData)
                            lobby.playerCount += 1
                            for player in lobby.playerConnectionList:
                                if player.playerId != exSockData.playerId:
                                    player.sendData(exSockData, msgJson, "JOINED_PLAYER")
                                    exSockData.sendData(player, msgJson, "JOINED_PLAYER")
                            exSockData.sendData(exSockData, msgJson, "JOINED_PLAYER")

                elif msgJson["messageType"] == "GET_LOBBIES":
                    for lobby in self.lobbyList:
                        seatCount = lobby.playerCount
                        exSockData.getLobbyData(lobby.lobbyId, seatCount, msgJson)

                else:
                    if msgJson["messageType"] == "RDY_STATUS":
                        exSockData.setRdy(msgJson)
                    if msgJson["messageType"] == "REMOVE_LOBBY":
                        for lobby in self.lobbyList:
                            if lobby.lobbyId == msgJson["gameLobbyNumber"]:
                                self.lobbyList.remove(lobby)
                                exSockData.lobbyId = None
                    for player in self.connSocketList:
                        if player.lobbyId == msgJson["gameLobbyNumber"]:
                            player.putInMessageBox(msgJson)

                for player in self.connSocketList:
                    while len(player.outgoingMessageBox) != 0:
                        for msg in player.outgoingMessageBox:
                            if len(msg) > 1024:
                                print("Server: Message ist zu groß: ", len(msg))
                            else:
                                if player.clientLanguage == "JAVA":
                                    player.connection.send(struct.pack(">H", len(msg)))
                                player.connection.send(msg)
                                reply = msg.decode("utf-8")
                                msgJson = json.loads(reply)
                                print("Server: Nachricht gesendet an ",
                                      player.playerPublicName, "Größe: ", len(msg), " ",
                                      msgJson)
                            player.outgoingMessageBox.remove(msg)

    def buildSocketConnection(self):
        while True:
            extSockData = ExtendedSocketData()
            extSockData.connection, address = self.serverSocket.accept()
            print("Connected to: ", address)
            self.connSocketList.append(extSockData)
            start_new_thread(self.threadedClient, (extSockData,))


if __name__ == '__main__':
    socketServer = SocketServer()
    socketServer.buildSocketConnection()
