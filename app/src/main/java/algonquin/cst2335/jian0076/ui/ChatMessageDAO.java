package algonquin.cst2335.jian0076.ui;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import algonquin.cst2335.jian0076.data.ChatMessage;

//object that performs CRUD operation
@Dao
public interface ChatMessageDAO {

    @Insert
   void insertMessage(ChatMessage m);
//this matches the @Entity class name
    @Query("Select * from ChatMessage")
    List<ChatMessage> getAllMessages();

    @Delete
     void deleteMessge(ChatMessage m);
}
