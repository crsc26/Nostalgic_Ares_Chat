package Client;

import Server.Server;

import java.net.Socket;

public class ClientThread extends Server implements Runnable {

    Socket socket;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
