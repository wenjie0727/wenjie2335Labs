package algonquin.cst2335.jian0076.data;
public class ChatMessage {
    String message;
    String timeSent;

    public boolean isSentButton() {
        return isSentButton;
    }

    boolean isSentButton;

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }



    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent =t;
        isSentButton = sent;

    }
}