import socketserver


class GameSocketServer(socketserver.StreamRequestHandler):

    def handle(self):
        # self.rfile is a file-like object created by the handler;
        # we can now use e.g. readline() instead of raw recv() calls
        data = self.rfile.readline().strip()
        print("{} wrote:".format(self.client_address[0]))
        print(data)
        # Likewise, self.wfile is a file-like object used to write back
        # to the client
        self.wfile.write(data.upper())


class ThreadedTCPServer(socketserver.ThreadingMixIn, socketserver.TCPServer):
    pass


if __name__ == "__main__":
    HOST, PORT = "localhost", 9999

    # Create the server, binding to localhost on port 9999
    with socketserver.TCPServer((HOST, PORT), GameSocketServer) as server:
        # Activate the server; this will keep running until you
        # interrupt the program with Ctrl-C
        server.serve_forever()
