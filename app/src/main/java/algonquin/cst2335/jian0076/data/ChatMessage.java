package algonquin.cst2335.jian0076.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;

    public void setMessage(String message) {
        this.message = message;
    }

    @ColumnInfo(name="message")
    public String message;
    @ColumnInfo(name="timeSent")
    public String timeSent;
    @ColumnInfo(name="isSentButton")
    public boolean isSentButton;

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }
    public boolean isSentButton() {
        return isSentButton;
    }


    public ChatMessage(){};
    public ChatMessage(String m, String t, boolean sent)
    {
        message = m;
        timeSent =t;
        isSentButton = sent;

    }
}