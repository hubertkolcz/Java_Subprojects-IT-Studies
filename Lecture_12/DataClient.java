package com.company.Lecture_12;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DataClient {
    public static int sPort = 777;
    public static int kPort = 888;
    public static int bufferSize = 1024;
    public static DatagramSocket socket;
    public static byte buffer[] = new byte[bufferSize];


    public static void main(String[] args) throws Exception {
        socket = new DatagramSocket(kPort);
        Klie();
    }

    public static void Klie() throws Exception {
        System.out.println("Client");
        System.out.println("Host:" + InetAddress.getLocalHost());
        System.out.println("Message ");

        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            socket.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));
        }
    }
}
