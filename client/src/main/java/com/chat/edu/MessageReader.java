package main.java.com.chat.edu;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageReader extends Thread{
    private DataInputStream input;

    public MessageReader(Socket connection) {
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
        while(true){
            try {
                System.out.println(input.readUTF());
            } catch (IOException e) {
                System.out.println("Can't get message from server");
                e.printStackTrace();
            }
        }
    }
}
