import com.chat.edu.Message;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {
    @Test
    public void messageTest(){
        String text = "text";
        Message myMessage = new Message(text);
        assertEquals(myMessage.getText() , text);
    }
    @Test
    public void timeTest(){
        String text = "text";
        Message myMessage = new Message(text);
        assertTrue(true  );
        assertTrue(myMessage.getDate().compareTo(new Date()) < 0);
    }

}
