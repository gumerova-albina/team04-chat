package com.chat.edu;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable{
    final private Socket clientSocket;
    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try (final DataInputStream input = new DataInputStream(
             new BufferedInputStream(
                        clientSocket.getInputStream()));
             final DataOutputStream out = new DataOutputStream(
                     new BufferedOutputStream(
                             clientSocket.getOutputStream()))
        ) {
            // "/snd <сообщение>"
            Pair <DataInputStream, DataOutputStream> pair = new Pair<>(input, out);
            Server.collection.add(pair);
            while(true) {
                String clientLine;
                try {
                    clientLine = input.readUTF();
                } catch (EOFException e) {
                    continue;
                }
                if (clientLine.startsWith("/snd")) {
                    Message clientMessage = new Message(clientLine);
                    for(Pair <DataInputStream, DataOutputStream> x : Server.collection){
                        x.second.writeUTF(clientMessage.getDate() + ":" +  clientMessage.getText() + "\n");
                        x.second.flush();
                    }
                    // com.chat.edu.Server.clientSocketList...
                    // send com.chat.edu.Message to all other clients
                }

                if (clientLine.startsWith("/hist")) {
                    //History clientMessage = new History(clientLine);

                    // send History to exact client
                }

                if (clientLine.startsWith("/exit")) {
                    // need to remove input & out from server`s collection
                    // for that it is better to have map (user, its socket info)
                    // add when user initialising is done
                    Thread.currentThread().interrupt();
                    return;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
