package algonquin.cst2335.jian0076.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.jian0076.R;
import algonquin.cst2335.jian0076.data.ChatMessage;
import algonquin.cst2335.jian0076.data.ChatRoomViewModel;
import algonquin.cst2335.jian0076.data.MessageDatabase;
import algonquin.cst2335.jian0076.databinding.ActivityChatRoomBinding;



public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel;
    ArrayList<ChatMessage> list = new ArrayList<>();
    RecyclerView.Adapter myAdapter;
    ChatMessage newMsg;
    ChatMessageDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//load from database
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDatabase").build();
        mDAO = db.cmDAO();


        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        list = chatModel.messages.getValue();

        if (list == null) {
            chatModel.messages.postValue(list = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            //list.addAll(mDAO.getAllMessages());
            thread.execute(() ->
            {
                list.addAll(mDAO.getAllMessages()); //Once you get the data from database

                runOnUiThread(() -> binding.recyclerView.setAdapter(myAdapter)); //You can then load the RecyclerView
            });
        }


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        binding.button.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            String m = binding.textInput.getText().toString();
            newMsg = new ChatMessage(m, currentDateandTime, true);
            list.add(newMsg);
            //wants to know which position has changed
            myAdapter.notifyItemInserted(list.size() - 1);
            //clear the previous text:
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
               newMsg.id=(int) mDAO.insertMessage(newMsg);

            });
        });

        binding.button1.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            String m = binding.textInput.getText().toString();
            ChatMessage newMsg = new ChatMessage(m, currentDateandTime, false);
            list.add(newMsg);

            myAdapter.notifyItemInserted(list.size() - 1);
            //clear the previous text:
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                mDAO.insertMessage(newMsg);

            });
        });

        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(click -> {



                    //which row was click
                    int position = getAbsoluteAdapterPosition();
                    ChatMessage thisMessage = list.get(position);

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);

                    builder.setMessage("Do you want to delete the message:" + messageText.getText());
                    builder.setTitle("Question:");

                    builder.setNegativeButton("No", (a, b) -> {});
                    builder.setPositiveButton("Yes", (a, b) -> {

                        Snackbar.make(messageText,"You deleted message #" + position, Snackbar.LENGTH_LONG)
                                        .setAction("Undo", clk ->{
                                            Executor thread = Executors.newSingleThreadExecutor();
                                            thread.execute(() -> {
                                                        mDAO.insertMessage(thisMessage);});
                                                        myAdapter.notifyItemInserted(position);
                                                        chatModel.messages.getValue().add(thisMessage);
                                                    }).show();
                        Executor thread = Executors.newSingleThreadExecutor();
                        thread.execute(() -> {
                            mDAO.deleteMessage(thisMessage);

                        });
                        myAdapter.notifyItemRemoved(position);
                        chatModel.messages.getValue().remove(position);

                    });
                    builder.create().show();
                });

               messageText = itemView.findViewById(R.id.messageText);
              timeText = itemView.findViewById(R.id.timeText);
            }
        }

        binding.recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            // given the view type, just load a MyRowHolder // what layout do you want
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View root;
                if(viewType == 0){
                    // inflate
                    root = getLayoutInflater().inflate(R.layout.sent_message,parent,false);}
                else{
                        root =  getLayoutInflater().inflate(R.layout.receive_message,parent,false);
                }
                // pass the root to constructor
                return new MyRowHolder(root);
            }

            @Override

            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = list.get(position);
                // set the textview
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());
            }
            @Override  // how many rows are there
            public int getItemCount() {
                return list.size();
            }

            public int getItemViewType(int position) {
                ChatMessage obj =list.get(position);

                if (obj.isSentButton()) {
                    return 0;  //0 represents sent, the layout is sent_message.xml
                } else {
                    return 1;  //1 represents receive, the layout is receive_message.xml
                }
            }
        });
    }
}