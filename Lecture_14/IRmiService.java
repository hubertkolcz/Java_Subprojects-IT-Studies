package com.company.Lecture_14;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRmiService extends Remote {

    List<Integer> findDominant(int[] numbers) throws RemoteException;

    String encrypt(String text) throws RemoteException;
}
