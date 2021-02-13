package com.company.Lecture_7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Błąd przy tworzeniu gniazda serwerowego " + e);
        }
        System.out.println("Inicjalizacja gniazda zakończona.");
        System.out.println("Parametry gniazda: " + serverSocket);

        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Błąd wejścia-wyjścia" + e);
            }
            System.out.println("Nawiązano połączenie...");
            System.out.println("Paramtery polączenia: " + socket);
            new Thread(new EchoServerThread(socket)).start();
        }
    }
}
