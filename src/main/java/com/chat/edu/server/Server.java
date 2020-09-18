package com.chat.edu.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket> clientSocketList = new ArrayList<>();
    public static List<Pair <DataInputStream, DataOutputStream>> collection = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please specify port and try again");
        } else {
            final int port = Integer.valueOf(args[0]);
            try (final ServerSocket connectionPortListener = new ServerSocket(port)) {
                while (true) {
                    final Socket clientConnection = connectionPortListener.accept();
                    System.out.println("User joined chat");
                    new Thread(new MessageHadler(clientConnection)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}