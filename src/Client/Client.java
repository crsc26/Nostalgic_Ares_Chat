package Client;

import Server.ServerThread;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    public static void main(String[] args)
    {
        Socket socket = null;
        int portNo = 6666;

        try
        {
            socket = new Socket("localhost", portNo);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Error in the connection!");
            e.printStackTrace();
        }
    }
}
