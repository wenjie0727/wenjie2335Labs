package algonquin.cst2335.jian0076.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.jian0076.data.ChatMessage;
import algonquin.cst2335.jian0076.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    private ChatMessage selected;
    DetailsLayoutBinding binding;

    public MessageDetailsFragment(){

    }
    public MessageDetailsFragment(ChatMessage m){
        selected = m;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
         binding = DetailsLayoutBinding.inflate(inflater);
        binding.textView.setText(selected.getMessage());
        binding.timeView.setText(selected.getTimeSent());
       // binding.sendView.setEnabled(selected.isSentButton);
       // binding.idView.setText(selected.getId());
        return binding.getRoot();
  //View inflated = inflater.inflate(R.layout.details_layout,container,false);
       //return inflated;
    }
}
