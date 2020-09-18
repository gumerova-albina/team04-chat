package com.chat.edu.server;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class MessageTest {
    @Test
    public void shouldMessageReturnRightActionAndText(){
        String testSendMessage = "/snd hello";
        String testExitMessage = "/exit";

        /*
         * Test command /snd
         *
         */
        Message myMessage = new Message(testSendMessage);

        Assert.assertEquals("/snd", myMessage.getAction());
        Assert.assertEquals(" hello", myMessage.getText());

        /*
         * Test command functional command
         *
         */
        myMessage = new Message(testExitMessage);

        Assert.assertEquals("/exit", myMessage.getAction());
        Assert.assertEquals("", myMessage.getText());
    }

    @Test
    public void shouldMessageConstructedRight(){
        /*
        * Test non-personal Message without login
        *
         */
        String testNoPersonalMessage = "/snd Hello";
        Date date = new Date();
        Message myMessage = new Message(testNoPersonalMessage);

        Assert.assertEquals("(" + date + ") " + ":" + " Hello", myMessage.constructedMessage(""));

        /*
         * Test non-personal Message with login
         *
         */
        Assert.assertEquals("login (" + date + ") " + ":" + " Hello", myMessage.constructedMessage("login"));

        /*
         * Test personal Message without login
         *
         */
        String testPersonalMessage = "/sdnp otherLogin hi!";
        myMessage = new Message(testPersonalMessage);
        date = new Date();
        Assert.assertEquals("(" + date + ") " + "[Personal to otherLogin]: hi!", myMessage.constructedPersonalMessage("","otherLogin"));

        /*
         * Test personal Message with login
         *
         */
        Assert.assertEquals("from: login (" + date + ") " + "[Personal to otherLogin]: hi!", myMessage.constructedPersonalMessage("login","otherLogin"));
    }


    @Test
    public void shouldMessageGetRightDate(){
        Message myMessage = new Message("/snd message");
        int res = myMessage.getDate().compareTo(new Date());
        assertTrue(res == 0);
    }
}
