package com.chat.edu.server;

import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MessageTest {
    @Test
    public void shouldMessageBeHandled(){
        String testMessage = "/snd hello";
        Message myMessage = new Message(testMessage);
        Assert.assertEquals(" hello", myMessage.getText());
    }

//    @Test
//    public void timeTest(){
//        String text = "text";
//        Message myMessage = new Message(text);
//        int res = myMessage.getDate().compareTo(new Date());
//        assertTrue(res < 0);
//    }
}
