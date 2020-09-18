package com.chat.edu.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 10_000;

    public static Map<String, DataOutputStream> loginMap = new ConcurrentHashMap<String, DataOutputStream>();

    public static List<Pair <DataInputStream, DataOutputStream>> collection = new ArrayList<>();

    public static List<Message> messageList = new ArrayList<>();

    public static void main(String[] args) {
        try (final ServerSocket connectionPortListener = new ServerSocket(PORT);) {
            while (!connectionPortListener.isClosed()) {
                final Socket clientConnection = connectionPortListener.accept();
                System.out.println("User joined chat");
                new Thread(new MessageHandler(clientConnection)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}