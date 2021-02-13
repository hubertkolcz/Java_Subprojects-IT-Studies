package com.company.Lecture_5_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int Port = 6666;
    private ServerSocket serverSocket;
    private Thread RunServer;


    public void init()
    {
        try {
            serverSocket = new ServerSocket(Port);
            System.out.println("Server on. Port: " + Port + "\n");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        RunServer = new Thread(() -> ServerLoop());
        RunServer.start();
    }

    public void ServerLoop() {
        while (true) {
            System.out.println("Server loop");
            try {
                Socket clientSocket = serverSocket.accept();

                InputStreamReader inputstreamreader = new
                        InputStreamReader(clientSocket.getInputStream());

                BufferedReader bufferedreader = new
                        BufferedReader(inputstreamreader);

                PrintWriter printwriter = new
                        PrintWriter(clientSocket.getOutputStream(), true);

                String line = "";
                boolean done = false;
                while (((line = bufferedreader.readLine()) != null) && (!done)) {
                    System.out.println("Received from Client: " + line);

                    if (line.compareToIgnoreCase("Exit") == 0) done = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
