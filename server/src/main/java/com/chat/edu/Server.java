package com.chat.edu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static int PORT = 10_000;

    public static List<Socket> clientSocketList = new ArrayList<>();
    public static List<Pair <DataInputStream, DataOutputStream>> collection = new ArrayList<>();

    public static void main(String[] args) {
        try (final ServerSocket connectionPortListener = new ServerSocket(PORT);) {
            while (true) {
                final Socket clientConnection = connectionPortListener.accept();
                System.out.println("User joined chat");
                new Thread(new Connection(clientConnection)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}