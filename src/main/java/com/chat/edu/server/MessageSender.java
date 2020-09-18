package com.chat.edu.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageSender {
    static void sendMessage(String constructedMessage) {
        try {
            for(Pair <DataInputStream, DataOutputStream> x : Server.collection){
                x.second.writeUTF(constructedMessage);
                x.second.flush();
            }
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}
