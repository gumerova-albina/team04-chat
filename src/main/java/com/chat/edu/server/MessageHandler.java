package com.chat.edu.server;

import java.io.*;
import java.net.Socket;

/**
 * Class that receives and handles messages from clients
 */
public class MessageHandler implements Runnable{
    final private Socket clientSocket;
    private String login = "";

    public MessageHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (final DataInputStream input = new DataInputStream(
             new BufferedInputStream(
                        clientSocket.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             clientSocket.getOutputStream()))
        ) {
            /*
            * Add pair of input/output in server memory
            * Allows connect with other clients
             */
            Pair <DataInputStream, DataOutputStream> pair = new Pair<>(input, out);
            Server.collection.add(pair);
            while(!clientSocket.isClosed()) {
                Message clientMessage;
                try {
                    clientMessage = new Message(input.readUTF());
                } catch (EOFException e) {
                    continue;
                }
                String action = clientMessage.getAction();
                if ("/snd".equals(action)) {
                    sendMessage(clientMessage.constructedMessage(login));
                    synchronized (Server.messageList) {
                        Server.messageList.add(clientMessage);
                    }
                    // com.chat.edu.server.Server.clientSocketList...
                    // send com.chat.edu.server.Message to all other clients
                } else if ("/hist".equals(action)) {
                    System.out.println("In history");
                    //History clientMessage = new History(clientLine);
                    synchronized (Server.messageList) {
                        for(int i = 0; i < Server.messageList.size(); i++){
                            out.writeUTF(Server.messageList.get(i).getDate() + ":" + Server.messageList.get(i).getText());
                        }
                        out.flush();
                    }
                    // send History to exact client
                } else if("/chid".equals(action)){
                    login = clientMessage.getText();
                } else if ("/exit".equals(action)) {
                    // need to remove input & out from server`s collection
                    // for that it is better to have map (user, its socket info)
                    // add when user initialising is done
                    System.out.println(!"".equals(login) ? login+" left chat" : "User left chat");
                    Server.collection.remove(pair);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Mistakes while closing client socket");
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
            return;
        }
    }

    private void sendMessage(String constructedMessage) {
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
