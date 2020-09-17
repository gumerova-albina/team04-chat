package test.java.com.chat.edu;

import main.java.com.chat.edu.MessageReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class MessageReaderTest implements SysoutCaptureAndAssertionAbility{
    @Test
    public void shouldReadMessageWhenItSends() {
        try {
            ServerSocket connectionPortListener = mock(ServerSocket.class);
            DataInputStream input = mock(DataInputStream.class);
            when(connectionPortListener.getInputStream()).thenReturn(input);
            when(input.readUTF()).thenReturn("Test string");

            new MessageReader().start();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
