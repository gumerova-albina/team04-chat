package com.chat.edu.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class that receives and handles messages from clients
 */
public class MessageHandler implements Runnable {
    final private Socket clientSocket;
    private String login = "";
    private String room = "global";

    public MessageHandler(Socket clientSocket) {
        Server.rooms.put(room, new ArrayList<>());
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
            synchronized (Server.rooms) {
                Server.rooms.get("global").add(out);
            }
            sendMessageToRoom(login + " joined this room (\"" + room +"\")", room);
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
                    /*
                    if ("".equals(room)) {
                        sendMessage(clientMessage.constructedMessage(login));
                        synchronized (Server.messageList) {
                            Server.messageList.add(clientMessage);
                        }
                    } else {

                     */
                    sendMessageToRoom(clientMessage.constructedMessage(login), room);
                    // }
                    // com.chat.edu.server.Server.clientSocketList...
                    // send com.chat.edu.server.Message to all other clients
                } else if ("/hist".equals(action)) {
                    //History clientMessage = new History(clientLine);
                    synchronized (Server.messageList) {
                        for (int i = 0; i < Server.messageList.size(); i++) {
                            out.writeUTF(Server.messageList.get(i).getDate() + ":" + Server.messageList.get(i).getText());
                        }
                        out.flush();
                    }
                }
                // send History to exact client
                else if("/chid".equals(action)){
                    login = clientMessage.getText().substring(1);
                    if(Server.loginMap.containsKey(login)){
                        out.writeUTF("Login is already in use");
                    }
                    else {
                        Server.loginMap.put(login, out);
                    }
                } else if("/sdnp".equals(action)){
                    String toLoginSend = clientMessage.getText().split(" ")[1];
                    if (Server.loginMap.containsKey(toLoginSend)){
                        Server.loginMap.get(toLoginSend).writeUTF(clientMessage.constructedPersonalMessage(login, toLoginSend));
                        Server.loginMap.get(toLoginSend).flush();
                        out.writeUTF(clientMessage.constructedPersonalMessage(login, toLoginSend));
                        out.flush();
                    }
                } else if ("/chroom".equals(action)) {
                    String roomName = clientMessage.getText().substring(1);
                    if (!"global".equals(roomName) && "".equals(login)) {
                        out.writeUTF("login is required");
                        out.flush();
                        continue;
                    }
                    if (!Server.rooms.containsKey(roomName)) {
                        Server.rooms.put(roomName, new ArrayList<>());
                    }
                    if (Server.rooms.containsKey(room)) {
                        sendMessageToRoom(login + " left this room (\"" + room + "\")", room);
                        Server.rooms.get(room).remove(out);
                    }
                    if (Server.rooms.get(roomName).contains(out)) {
                        out.writeUTF("it is your current room");
                        out.flush();
                        continue;
                    }
                    Server.rooms.get(roomName).add(out);
                    sendMessageToRoom(login + " joined this room (\"" + roomName +"\")", roomName);
                    room = roomName;
                } else if ("/exit".equals(action)) {
                    // need to remove input & out from server`s collection
                    // for that it is better to have map (user, its socket info)
                    // add when user initialising is done
                    System.out.println(!"".equals(login) ? login+" left chat" : "User left chat");
                    sendMessageToRoom(login + " left this room (\"" + room +"\")", room);
                    Server.rooms.get(room).remove(out);
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

    private void sendMessageToRoom(String constructedMessage, String roomName) {
        try {
            for(DataOutputStream x : Server.rooms.get(roomName)){
                x.writeUTF(constructedMessage);
                x.flush();
            }
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}
