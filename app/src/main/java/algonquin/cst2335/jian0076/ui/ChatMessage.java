package algonquin.cst2335.jian0076.ui;


public class ChatMessage {

    String message;
    String timeSent;
    boolean isSentButton;

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent =t;
        isSentButton = sent;

    }


}
