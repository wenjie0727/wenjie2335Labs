package algonquin.cst2335.jian0076.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.jian0076.R;
import algonquin.cst2335.jian0076.data.ChatMessage;
import algonquin.cst2335.jian0076.data.ChatRoomViewModel;
import algonquin.cst2335.jian0076.databinding.ActivityChatRoomBinding;



public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel;
    ArrayList<String> messages;
    ArrayList<ChatMessage> list = new ArrayList<>();
    //RecyclerView.Adapter myAdapter;
    RecyclerView.Adapter myAdapter;
    ChatMessage newMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        list = chatModel.messages.getValue();

        if (list == null) {
            chatModel.messages.postValue(list = new ArrayList<>());
        }


        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        binding.button.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            String m = binding.textInput.getText().toString();
            newMsg= new ChatMessage(m, currentDateandTime, true);
            list.add(newMsg);
            //wants to know which position has changed
            myAdapter.notifyItemInserted(list.size() - 1);
            //clear the previous text:
            binding.textInput.setText("");

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

        });

        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;

            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
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
                    root = getLayoutInflater().inflate(R.layout.sent_message,parent,false);}
                else{
                        root =  getLayoutInflater().inflate(R.layout.receive_message,parent,false);
                }


                //SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder(root);
            }

            @Override

            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                ChatMessage obj = list.get(position);
                holder.messageText.setText(obj.getMessage());
                holder.timeText.setText(obj.getTimeSent());


            }
            @Override
            public int getItemCount() {
                return list.size();
            }

            public int getItemViewType(int position) {
                ChatMessage obj =list.get(position);

                if (obj.isSentButton()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}