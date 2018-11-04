package Server;

import Client.ClientThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable
{
    private Socket socket;
    private String name;
    // To receive data from server
    private BufferedReader serverInput;
    // To write to the user
    private BufferedReader userInput;
    // To write to the server
    private PrintWriter out;

    public ServerThread(Socket socket, String name)
    {
        this.socket = socket;
        this.name = name;
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
                    out.println(name + " > " + userInput.readLine());
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
        System.out.println("Please input the username you want to be displayed");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        scan.close();
        int portNo = 6666;
    }
}
