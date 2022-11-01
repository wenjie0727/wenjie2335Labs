package algonquin.cst2335.jian0076;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ChatRoom extends AppCompatActivity {
ActivityChatRoomBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}