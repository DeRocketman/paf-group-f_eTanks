from enum import Enum


class SocketCode(Enum):
    LOGIN = "LOGIN"
    HOST = "HOST"
    JOIN = "JOIN"
    GET_LOBBY_LIST = "GET_LOBBY_LIST"
    CHAT_MSG = "CHAT_MSG"
    CLOSE_LOBBY = "CLOSE_LOBBY"
    LEAVE_LOBBY = "LEAVE_LOBBY"
    