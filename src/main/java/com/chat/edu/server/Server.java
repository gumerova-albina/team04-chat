package com.chat.edu.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    static Map<String, DataOutputStream> loginMap = new ConcurrentHashMap<String, DataOutputStream>();

    static List<Pair<DataInputStream, DataOutputStream>> collection = new ArrayList<>();

    public static Map<String, List<DataOutputStream>> rooms = new ConcurrentHashMap<>();

    public static List<Message> messageList = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify port and try again");
        } else {
            final int port = Integer.valueOf(args[0]);
            try (final ServerSocket connectionPortListener = new ServerSocket(port);) {
                while (!connectionPortListener.isClosed()) {
                    final Socket clientConnection = connectionPortListener.accept();
                    System.out.println("User joined chat");
                    new Thread(new MessageHandler(clientConnection)).start();
                }
            } catch (IOException e) {
                System.out.println("Can't connect to client");
            }
        }
    }
}