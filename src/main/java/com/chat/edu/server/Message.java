package com.chat.edu.server;

import java.util.Date;

/**
 * Class that constructs a message
 *
 */

public class Message {
    private final String text;

    private final String login;

    private final String action;

    private final Date date;

    public String getText() {
        return text;
    }

    public Date getDate(){
        return date;
    }

    public String getLogin(){
        return login;
    }

    public String getAction() {
        return action;
    }

    public Message (String in){
        String delims = " ";
        String tokens[] = in.split(delims);
        login = tokens[0];
        action = tokens[1];
        text = in.substring(login.length()+action.length()+1);
        date = new Date();
    }

    public String constructedMessage() {
        if(!"".equals(login)){
            return date + "." + login + ":" + text;
        }
        return date + "." + text + System.lineSeparator();
    }
}
