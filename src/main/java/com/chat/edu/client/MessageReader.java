package com.chat.edu.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

/**
 * Class that represents input messages
 *
 */

public class MessageReader extends Thread{
    private DataInputStream input;
    private Socket connection;

    public MessageReader(Socket connection) {
        this.connection = connection;
        try {
            input = new DataInputStream(
                    new BufferedInputStream(
                            connection.getInputStream()));
        } catch (IOException e) {
            System.out.println("Can't connect to get messages");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(!connection.isClosed()){
            try {
                System.out.println(input.readUTF());
            } catch (EOFException e) {

            } catch (IOException e) {
                System.out.println("Can't get message from server");
                e.printStackTrace();
            }
        }
    }
}
