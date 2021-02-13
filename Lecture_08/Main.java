package com.company.Lecture_8;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 6666;

        String host1 = "localhost";
        int port1 = 6667;

        String line = "First line";
        String line1 = " 1 2 3";

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> metoda(host, port, line));
        executorService.submit(() -> metoda(host1, port1, line1));

        executorService.shutdown();
    }

    private static void metoda(String host, int port, String line) {

        Socket clientSocket = null;
        String threadName = Thread.currentThread().getName();
        try {
            clientSocket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O error");
        }
        System.out.println("Connected with " + clientSocket);

        String line1 = null;
        BufferedReader brSockInput = null;
        BufferedReader brLocalInput = null;
        DataOutputStream out = null;

        try {
            out = new DataOutputStream(clientSocket.getOutputStream());
            brSockInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            brLocalInput = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.out.println("Error occured while creating server: " + e);
            System.exit(-1);
        }

        try {
            if (line != null) {
                System.out.println("Sending: " + line);
                out.writeBytes(line + '\n');
                out.flush();
            }
            if (line == null || "quit".equals(line)) {
                System.out.println("Process ending it's working process");
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
            System.exit(-1);
        }

    }
}
