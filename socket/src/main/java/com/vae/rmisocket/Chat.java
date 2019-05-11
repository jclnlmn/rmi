package com.vae.rmisocket;

import java.rmi.*;
import java.rmi.server.*;

public class Chat extends UnicastRemoteObject implements ChatInterface {

    public String name;
    public ChatInterface client = null;

    public Chat(String n) throws RemoteException {
        this.name = n;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public void setClient(ChatInterface c) {
        client = c;
    }

    @Override
    public ChatInterface getClient() {
        return client;
    }

    @Override
    public void send(String s) throws RemoteException {
        System.out.println(s);
    }
}
