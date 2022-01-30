from enum import Enum


class RequestCode(Enum):
    LOGIN = "LOGIN"
    GET_TOKEN = "GET_TOKEN"
    CREATE_USER = "CREATE_USER"
    UPDATE_USER = "UPDATE_USER"
    DELETE_USER = "DELETE_USER"
    STATISTIC_LIST = "STATISTIC_LIST"
