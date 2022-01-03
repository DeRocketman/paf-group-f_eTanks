from enum import Enum


class SocketCode(Enum):
    LOGIN = "LOGIN"
    CLOSE_LOBBY = "CLOSE_LOBBY"
    LEAVE_LOBBY = "LEAVE_LOBBY"
    