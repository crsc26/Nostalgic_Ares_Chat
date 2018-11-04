package Client;

import Server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Allow to send and receive data
public class ClientThread extends Server implements Runnable
{
    private Socket socket;
    // To receive data from clients
    private BufferedReader in;
    // To write to the client
    private PrintWriter out;

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        // Make thread relay messages
        try
        {
            // Initialize PrintWrite with the socket's output stream
            // and BufferedReader with an InputStreamReader using the
            // socket's input stream
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // While the socket is still alive (new inputs)
            while(!socket.isClosed())
            {
                String input = in.readLine();
                if(input != null)
                {
                    for(ClientThread client : clients)
                    {
                        client.getWriter().write(input);
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter()
    {
        return out;
    }
}
