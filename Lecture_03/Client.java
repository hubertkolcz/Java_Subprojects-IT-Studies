package com.company.Lecture_3;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("Executing program: Client host port");
            System.exit(-1);
        }
        String host = args[0];
        int port = 0;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument: port");
            System.exit(-1);
        }
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }
        System.out.println(socket);
    }
}