package model.service;

import java.net.Socket;

public interface Executable {

    public abstract void run(Datapackage pack, Socket socket);
}
