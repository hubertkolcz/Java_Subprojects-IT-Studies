package com.company.Lecture_7;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class EchoServerThread implements Runnable {

    protected Socket socket;

    public EchoServerThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {

        BufferedReader br = null;
        DataOutputStream dataOut = null;
        String threadName = Thread.currentThread().getName();

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(threadName + " Error while creating socket: $e");
            return;
        }
        String line = null;

        while (true) {
            try {
                line = br.readLine();
                System.out.println(threadName + "| The line was read:" + line);

                if ((line == null) || "quit".equals(line)) {
                    System.out.println(threadName + "Process of working with client has finished" + socket);

                    socket.close();
                    return;
                } else {
                    dataOut.writeBytes(line + "\n\r");
                    System.out.println(threadName + " | The line was sent: " + line);

                }
            } catch (IOException e) {
                System.out.println(threadName + " | I/O error: " + e);
            }
        }
    }
}
