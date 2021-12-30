import random
import socket
import threading
from abc import ABC

from model.service.Datapackage import Datapackage


class SocketClient(ABC):

    def __init__(self, hostname, port, timeout, id, group):
        self.id = id,
        self.group = group
        self.errorCount = 0
        self.address = self.inetSocketAddress(hostname, port)

        self.loginSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.loginSocket.settimeout(timeout)
        self.listeningThread = threading.Thread
        self.idMethods = {}

        self.muted = False
        self.stopped = False
        self.DEFAULT_USER_ID = random.randint(1000000, 2000000)
        self.DEFAULT_GROUP_ID = "_DEFAULT_GROUP_"

    @staticmethod
    def inetSocketAddress(hostname, port):
        return {
            "hostname": hostname,
            "port": port
        }

    def isListening(self):
        return self.isConnected() and self.listeningThread is not None and self.listeningThread.isAlive \
               and self.errorCount == 0

    def isConnected(self):
        return self.loginSocket is not None

    def start(self):
        self.stopped = False
        self.login()
        self.startListening()

    def stop(self):
        self.stopped = True
        self.onLog("[Client] Stopping...")

    def repairConnection(self):
        self.onLog("[Client] [Connection-Repair] Repairing connection...")
        if self.loginSocket is not None:
            pass

    def login(self):
        if self.stopped:
            return

        self. onLog("[Client] Connecting")
        self.loginSocket.connect((self.address["hostname"], self.address["port"]))
        loginPackage = Datapackage("_INTERNAL_LOGIN_", self.id, self.group)



    def startListening(self):
        pass

    @staticmethod
    def onLog(msg):
        print(msg)
