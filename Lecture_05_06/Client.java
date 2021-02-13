package com.company.Lecture_5_6;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Client {

    private Socket Client;
    private String userName;
    private String ipAddress;
    private java.net.Socket socket = null;

    public static void main(String[] args) {

        try {
            String line = System.in.toString();
            if (line.equals("quit")) {
                try {
                    Socket socket = new Socket();
                    throw new Exception();
                } catch (UnknownHostException e) {
                    System.out.println("Given address doesn't exist");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Unable to connect with server. Possible reasons: server turned off, wrong port");
                } catch (Exception e) {
                    System.out.println("Unexpected error");
                }

            }
        } finally {

        }
    }

    public void SendButton_Click(ActionEvent e) {
        String sendMessage = "hello \n";
        try {
            sendMessageToServer(Client, sendMessage);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Message to server: " + sendMessage);
    }

    private void sendMessageToServer(Socket socket, String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new
                OutputStreamWriter(socket.getOutputStream()));

        writer.write(message);
        writer.flush();
    }
}
