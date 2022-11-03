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
import algonquin.cst2335.jian0076.data.ChatRoomViewModel;
import algonquin.cst2335.jian0076.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.jian0076.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {
ActivityChatRoomBinding binding;
//ArrayList<String> messages = new ArrayList<>();
ChatRoomViewModel chatModel messages = new ArrayList<>();
    //ArrayList<ChatRoomViewModel> chatModel= new ArrayList<>();
//ChatRoomViewModel chatModel;
RecyclerView.Adapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<String>());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

//
//        binding.button.setOnClickListener(click ->{
//            messages.add(binding.textInput.getText().toString());
//            myAdapter.notifyItemInserted(messages.size()-1);
//            //clear the previous text:
//            binding.textInput.setText("");
//
//        });

        binding.button.setOnClickListener(click ->{
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            messages.add(binding.textInput.getText().toString());
            myAdapter.notifyItemInserted(messages.size()-1);
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
        binding.recyclerView.setAdapter(myAdapter=new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            // given the view type, just load a MyRowHolder
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String obj = messages.get(position);
                holder.messageText.setText(obj);
                holder.timeText.setText("");
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            public int getItemViewType(int position){
                return 0;
            }
        });

    }
}