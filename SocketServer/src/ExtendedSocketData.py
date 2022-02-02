import json


class ExtendedSocketData:
    def __init__(self):
        self.connection = None
        self.clientLanguage = None
        self.isLobbyHost = False
        self.lobbyId = None
        self.playerId = None
        self.playerPublicName = None
        self.playerIsRdy = None

        self.outgoingMsgCounter = 0
        self.incomingMsgCounter = 0
        self.outgoingMessageBox = []

    def setPlayerData(self, msgJson):
        self.clientLanguage = msgJson["payload"]
        self.playerId = msgJson["playerId"]
        self.playerPublicName = msgJson["playerPublicName"]
        self.playerIsRdy = msgJson["playerIsRdy"]

        msgJson["payload"] = "SUCCESS"
        self.putInMessageBox(msgJson)

    # TODO Refactor playerImage -> get from RestApi!
    def sendData(self, exSockData, msgJson, msgType):
        msgJson["messageType"] = msgType
        msgJson["playerId"] = exSockData.playerId
        msgJson["playerPublicName"] = exSockData.playerPublicName
        msgJson["playerIsRdy"] = exSockData.playerIsRdy
        msgJson["playerImage"] = "default"
        self.putInMessageBox(msgJson)

    def registerLobby(self, msgJson):
        self.lobbyId = msgJson["gameLobbyNumber"]
        self.isLobbyHost = True

        msgJson["payload"] = "Server: Lobby erfolgreich erstellt"
        self.putInMessageBox(msgJson)

    def getLobbyData(self, lobbyId, seatCount, msgJson):
        msgJson["gameLobbyNumber"] = lobbyId
        msgJson["payload"] = seatCount
        self.putInMessageBox(msgJson)

    def joinLobby(self, msgJson):
        self.lobbyId = msgJson["gameLobbyNumber"]

    def setRdy(self, msgJson):
        self.playerIsRdy = msgJson["playerIsRdy"]

    def putInMessageBox(self, msgJson):
        self.outgoingMessageBox.append(str.encode(json.dumps(msgJson)+"\n"))
