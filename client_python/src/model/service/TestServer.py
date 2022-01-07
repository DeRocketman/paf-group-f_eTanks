import json
import socket
from _thread import *


class ExtendedConnectionData:
    connection = None
    isLobbyHost = None
    lobbyId = None
    playerId = None
    playerPubName = None
    playerImage = None
    playerIsRdy = None


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


def threadedClient(exConnData):
    while True:
        msg = exConnData.connection.recv(8192)
        reply = msg.decode("utf-8")
        msgJson = json.loads(reply)
        print("Eingehende MSG von: ", exConnData.playerPubName, " Inhalt:", msgJson)
        if msgJson["messageType"] == "LOGIN":
            setExPlayerData(exConnData, msgJson)
            msgJson["payload"] = "SUCCESS"

        elif msgJson["messageType"] == "REGISTER_LOBBY":
            replyAsDict = registerLobby(exConnData, msgJson)
            reply = json.dumps(replyAsDict)
            exConnData.connection.send(str.encode(reply))
            print("Ausgehende MSG an", exConnData.playerPubName, " Inhalt:", msgJson)

        elif msgJson["messageType"] == "JOIN":
            exConnData.lobbyId = msgJson["gameLobbyNumber"]
            for player in playerList:
                if player.lobbyId == exConnData.lobbyId:
                    reply = json.dumps(sendPlayerData(exConnData, msgJson, "JOINED_PLAYER"))
                    player.connection.send(str.encode(reply))
                    print("Ausgehende MSG an", player.playerPubName, " Inhalt:", msgJson)

        elif msgJson["messageType"] == "CHAT_MSG" or msgJson["messageType"] == "RDY_STATUS":
            if msgJson["messageType"] == "RDY_STATUS":
                exConnData.playerIsRdy = msgJson["playerIsRdy"]
            for player in playerList:
                if player.lobbyId == msgJson["gameLobbyNumber"]:
                    player.connection.send(str.encode(reply))
                    print("Ausgehende MSG an", player.playerPubName, " Inhalt:", msgJson)

        elif msgJson["messageType"] == "GET_LOBBIES":
            for player in playerList:
                if player.isLobbyHost:
                    seatCount = 0
                    for otherPlayer in playerList:
                        if player.lobbyId == otherPlayer.lobbyId:
                            seatCount += 1
                    replyForLobbyList = json.dumps(buildLobbyData(player, seatCount, msgJson))
                    exConnData.connection.send(str.encode(replyForLobbyList))
                    print("Ausgehende MSG an", exConnData.playerPubName, " Inhalt:", msgJson)


def sendPlayerData(exConnData, msgJson, msgType):
    msgJson["messageType"] = msgType
    msgJson["playerId"] = exConnData.playerId
    msgJson["playerPublicName"] = exConnData.playerPubName
    msgJson["playerIsRdy"] = exConnData.playerIsRdy
    msgJson["playerImage"] = exConnData.playerImage
    return msgJson


def setExPlayerData(exConnData, msgJson):
    exConnData.playerId = msgJson["playerId"]
    exConnData.playerPubName = msgJson["playerPublicName"]
    exConnData.playerImage = msgJson["playerImage"]
    exConnData.playerIsRdy = msgJson["playerIsRdy"]


def registerLobby(exConnData, msgJson):
    exConnData.lobbyId = msgJson["gameLobbyNumber"]
    exConnData.isLobbyHost = True
    msgJson["payload"] = "Server: Lobby erfolgreich erstellt"
    return msgJson


def buildLobbyData(player, seatCount, msgJson):
    msgJson["gameLobbyNumber"] = player.lobbyId
    msgJson["payload"] = seatCount
    return msgJson


while True:
    extendedConnInfo = ExtendedConnectionData()
    extendedConnInfo.connection, address = serverSocket.accept()
    print("Connected to: ", address)
    playerList.append(extendedConnInfo)
    start_new_thread(threadedClient, (extendedConnInfo,))
    threadCount += 1
