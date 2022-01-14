import json
import socket
import struct
from _thread import *

import jpysocket as jpysocket

from model.service.ExtendedSocketData import ExtendedSocketData


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
            msg = exSockData.connection.recv(2048)
            reply = msg.decode("utf-8")
            msgJson = json.loads(reply)
            print("Server: Nachricht empfangen von ", exSockData.playerPublicName, " ", msgJson)

            if msgJson["messageType"] == "LOGIN":
                exSockData.setPlayerData(msgJson)

            elif msgJson["messageType"] == "REGISTER_LOBBY":
                exSockData.registerLobby(msgJson)
                self.lobbyList.append(exSockData.lobbyId)

            elif msgJson["messageType"] == "JOIN":
                exSockData.joinLobby(msgJson)
                for player in self.connSocketList:
                    if player.lobbyId == msgJson["gameLobbyNumber"] and player.playerId != exSockData.playerId:
                        exSockData.sendData(player, msgJson, "JOINED_PLAYER")
                    if player.lobbyId == msgJson["gameLobbyNumber"]:
                        player.sendData(exSockData, msgJson, "JOINED_PLAYER")

            elif msgJson["messageType"] == "CHAT_MSG" or msgJson["messageType"] == "RDY_STATUS":
                if msgJson["messageType"] == "RDY_STATUS":
                    exSockData.setRdy(msgJson)
                for player in self.connSocketList:
                    if player.lobbyId == msgJson["gameLobbyNumber"]:
                        player.putInMessageBox(msgJson)

            elif msgJson["messageType"] == "GET_LOBBIES":
                for lobby in self.lobbyList:
                    seatCount = 0
                    for player in self.connSocketList:
                        if lobby == player.lobbyId:
                            seatCount += 1
                    exSockData.getLobbyData(lobby, seatCount, msgJson)

            for player in self.connSocketList:
                while len(player.outgoingMessageBox) != 0:
                    for msg in player.outgoingMessageBox:
                        if len(msg) > 2048:
                            print("Server: Message ist zu groß: ", len(msg))
                            # todo: Lösung finden falls Puffer zu klein ist!
                        else:
                            player.connection.send(struct.pack(">H", len(msg)))
                            reply = msg.decode("utf-8")
                            msg = jpysocket.jpyencode(reply)
                            player.connection.send(msg)
                            reply = jpysocket.jpydecode(msg)
                            msgJson = json.loads(reply)
                            print("Server: Nachricht gesendet an ", player.playerPublicName, "Größe: ", len(msg), " "
                                  , msgJson)
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
