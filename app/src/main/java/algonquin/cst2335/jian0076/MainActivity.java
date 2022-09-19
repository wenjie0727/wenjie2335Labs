package algonquin.cst2335.jian0076;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

//        TextView mytext = findViewById(R.id.textview);
//        Button btn = findViewById(R.id.mybutton );
//        EditText myedit = findViewById(R.id.myedittext);

        btn.setOnClickListener((v) -> {
                String editString = myedit.getText().toString();
                mytext.setText( "Your edit text has: " + editString);
                                   });
    }


}