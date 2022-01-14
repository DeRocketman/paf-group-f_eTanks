class ServerLobby:
    def __init__(self, lobbyId):
        self.lobbyId = lobbyId
        self.hostConnection = None
        self.playerConnectionList = []
        self.playerCount = len(self.playerConnectionList)

    def addPlayerConn(self, exSocketData):
        self.playerConnectionList.append(exSocketData)

    def removePlayerConn(self, exSockData):
        for player in self.playerConnectionList:
            if exSockData.playerId == player.playerId:
                self.playerConnectionList.remove(player)
                if player.playerId == self.hostConnection:
                    # close Lobby to server side and write BroadcastMsg
                    self.closeLobby()
                    pass

    def closeLobby(self):
        pass

