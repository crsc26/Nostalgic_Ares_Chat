package Server;

import Client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    static int portNo;
    static ServerSocket serverSocket;
    public static ArrayList<ClientThread> clients;

    // Loop to continuously accept clients
    public static void acceptClients()
    {
        clients = new ArrayList<ClientThread>();
        while(true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                Thread thread = new Thread(client);
                thread.start();
                clients.add(client);
            }
            catch(IOException e)
            {
                System.out.println("Accept failed on: " + portNo);
                e.printStackTrace();
            }
        }
    }

    // Setting up of serverSocket
    public static void main(String[] args)
    {
        portNo = 6666;
        serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(portNo);
            acceptClients();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Could not listen to port: " + portNo);
            System.exit(1);
        }
    }
}
