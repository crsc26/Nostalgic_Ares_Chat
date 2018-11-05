package Server;

import Client.ClientThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerThread implements Runnable
{
    private Socket socket;
    // To receive data from server
    private BufferedReader serverInput;
    // To write to the user
    private BufferedReader userInput;
    // To write to the server
    private PrintWriter out;

    public ServerThread(Socket socket)
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
            // server BufferedReader with an InputStreamReader using the socket's input stream
            // and user's BufferedReader with an InputStreamReader using System.in
            out = new PrintWriter(socket.getOutputStream(), true);
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

            // While the socket is still alive (new inputs)
            while(!socket.isClosed())
            {
                if(serverInput.ready())
                {
                    String input = serverInput.readLine();
                    if(input != null)
                    {
                        // Print input to the console
                        System.out.println(input);
                    }
                }

                if(userInput.ready())
                {
                    // Print input to the server
                }
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Socket socket = null;
        int portNo = 6666;
        try {
            socket = new Socket("localhost", portNo);
            Thread.sleep(1000);
            Thread server = new Thread(new ServerThread(socket));
            server.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
