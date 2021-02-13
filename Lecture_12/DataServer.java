package com.company.Lecture_12;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DataServer {
    public static int sPort = 777;
    public static int kPort = 888;
    public static int bufferSize = 1024;
    public static DatagramSocket socket;
    public static byte buffer[] = new byte[bufferSize];


    public static void main(String[] args) throws Exception {
        socket = new DatagramSocket(sPort);
        Serw();
    }

    public static void Serw() throws Exception {
        System.out.println("Server ");
        System.out.println("Host: " + InetAddress.getLocalHost());
        System.out.println("Message ");

        int pos = 0;
        while (true) {
            int c = System.in.read();
            switch (c) {
                case -1:
                    System.out.println("Server STOP");
                    return;
                case '\r':
                    break;
                case '\n':
                    socket.send(new DatagramPacket(buffer, pos, InetAddress.getLocalHost(), kPort));
                    pos = 0;
                    break;
                default:
                    buffer[pos++] = (byte) c;
            }
        }
    }
}
