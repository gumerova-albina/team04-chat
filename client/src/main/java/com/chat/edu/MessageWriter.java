package main.java.com.chat.edu;

import java.io.*;
import java.net.Socket;

public class MessageWriter {

    private DataOutputStream out;

    public MessageWriter(Socket connection){
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            connection.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Can't get your message");
            e.printStackTrace();
        }
    }

    private void sendMessage(DataOutputStream out){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    }

}
