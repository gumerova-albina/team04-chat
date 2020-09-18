package com.chat.edu.server;

import java.util.Date;

/**
 * Class that constructs a message
 *
 */

public class Message {
    private final String text;

    private final String action;

    private final Date date;

    public String getText() {
        return text;
    }

    public Date getDate(){
        return date;
    }

    public String getAction() {
        return action;
    }

    public Message (String in) {
        String delims = " ";
        String tokens[] = in.split(delims);
        action = tokens[0];
        text = in.substring(action.length());
        date = new Date();
    }

    public String constructedMessage(String login) {
        if(!"".equals(login)){
            return login + " (" + date + ") " + ":" + text;
        }
        return "(" + date + ") " + ":" + text;
    }

    public String constructedPersonalMessage(String fromLogin, String toLogin) {
        String personalText = text.substring(text.split(" ")[1].length()+1);
        if(!"".equals(fromLogin)){
            return "from: " + fromLogin + " (" + date + ") " + "[Personal to "+ toLogin + "]:" + personalText;
        }
        return "(" + date + ") " + "[Personal to "+ toLogin + "]:" + personalText;
    }
}
