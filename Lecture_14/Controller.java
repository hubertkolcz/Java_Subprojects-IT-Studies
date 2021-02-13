package com.company.Lecture_14;


import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Controller {
    public TextField userInput;
    public TextArea output;

    private IRmiService rmiService;

    public Controller() {
        try {
            rmiService = connectRemote();
        } catch (Exception e) {
            output.setText("Couldn't connect with RMI Service. " + e.getMessage());
        }
    }


    private IRmiService connectRemote() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("localhost", 4444);
        return (IRmiService) reg.lookup("hi server");
    }

    public void encryptInput(ActionEvent actionEvent) {
        try {
            output.setText(rmiService.encrypt(userInput.getText()));
        } catch (RemoteException e) {
            output.setText(e.getMessage());
        }
    }
}
