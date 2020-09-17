package main.java.com.chat.edu;

import java.io.*;
import java.net.Socket;

public class MessageWriter extends Thread{
    private DataOutputStream out;
    private Socket connection;

    public MessageWriter(Socket connection){
        this.connection = connection;
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
            System.out.println("Please input your login");
            message = reader.readLine();

            do {
                message = reader.readLine();
                out.writeUTF(message);
                out.flush();
            } while (!"/exit".equals(message));
        } catch (IOException e) {
            System.out.println("Can't send message to server");
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (IOException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
        }
    }
}
