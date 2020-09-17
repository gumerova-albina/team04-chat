package main.java.com.chat.edu;

import java.io.*;
import java.net.Socket;

public class MessageWriter extends Thread{
    private DataOutputStream out;

    public MessageWriter(Socket connection){
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            connection.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Can't connect to send messages");
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        String message;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            do {
                message = reader.readLine();
                out.writeUTF(message);
            } while (!"/exit".equals(message));
        } catch (IOException e) {
            System.out.println("Can't send message to server");
            e.printStackTrace();
        }
    }
}
