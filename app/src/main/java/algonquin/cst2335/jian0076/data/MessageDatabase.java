package algonquin.cst2335.jian0076.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import algonquin.cst2335.jian0076.ui.ChatMessageDAO;

@Database(entities = {ChatMessage.class}, version = 1)

public abstract class MessageDatabase extends RoomDatabase {

    public abstract ChatMessageDAO cmDAO();

}
