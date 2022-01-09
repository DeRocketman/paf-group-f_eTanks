class ExtendedSocketData:
    def __init__(self, connection):
        self.connection = connection
        self.isLobbyHost = False
        self.lobbyId = None
        self.playerPublicName = None
        self.playerImage = None
        self.playerIsRdy = None

        self.outgoingMessageBox = []