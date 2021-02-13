package com.company.Lecture_14;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Server extends UnicastRemoteObject implements IRmiService {

    private static final char[] VOWS = new char[]{'a', 'ą', 'e', 'ę', 'i', 'o', 'u', 'ó', 'y'};

    public Server() throws RemoteException {
        super();
    }

    @Override
    public List<Integer> findDominant(int[] numbers) {
        HashMap<Integer, Integer> occurrences = new HashMap<>();

        for (int numberItem : numbers) {
            if (occurrences.containsKey(numberItem)) {
                occurrences.replace(numberItem, occurrences.get(numberItem) + 1);
            } else {
                occurrences.put(numberItem, 1);
            }
        }

        List<Integer> dominants = new LinkedList<>();
        int maxOcurrences = 0;

        for (Map.Entry<Integer, Integer> pair : occurrences.entrySet()) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
            if (pair.getValue() > maxOcurrences) {
                dominants.clear();
                dominants.add(pair.getKey());
                maxOcurrences = pair.getValue();
            } else if (pair.getValue() == maxOcurrences) {
                dominants.add(pair.getKey());
                maxOcurrences = pair.getValue();
            }
        }

        return dominants;
    }

    @Override
    public String encrypt(String text) throws RemoteException {
        StringBuilder encryptedText = new StringBuilder(text);
        char temp = '\0';
        char cur;
        boolean isVow = false;
        int firstOccurrence = -1;

        for (int i = 0; i < text.length(); i++) {
            cur = text.charAt(i);

            for (char vow : VOWS) {
                if (Character.toLowerCase(cur) == vow) {
                    isVow = true;
                    break;
                }
            }

            if (!isVow) {
                if (firstOccurrence < 0) {
                    firstOccurrence = i;
                } else {
                    encryptedText.replace(i, i + 1, Character.toString(temp));
                    System.out.println(encryptedText.toString());
                }

                temp = cur;
            }

            isVow = false;
        }

        encryptedText.replace(firstOccurrence, firstOccurrence + 1, Character.toString(temp));
        System.out.println(encryptedText.toString());

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("hi server", new Server());
            System.out.println("Server is ready.");
        } catch (RemoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
