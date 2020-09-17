import java.util.Date;

public class Message {
    String text;

    Date date = new Date();

    public String getText() {
        return text;
    }

    public Date getDate(){
        return date;
    }

    public Message (String in){
        text = in.substring(4);
    }
}
