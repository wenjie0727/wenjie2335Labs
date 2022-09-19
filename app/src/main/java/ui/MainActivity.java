package ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;
import data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);

        //setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;

//        TextView mytext = findViewById(R.id.textview);
//        Button btn = findViewById(R.id.mybutton );
//        EditText myedit = findViewById(R.id.myedittext);
        mytext.setText((CharSequence) model.editString);
        btn.setOnClickListener(click ->
                {
//                    model.editString = mytext.getText().toString();
                    model.editString.postValue(mytext.getText().toString());});

        LifecycleOwner owner;
        model.editString.observe(this,s -> {

                    mytext.setText("Your edit text has: " + s);

                });
//                    mytext.setText("Your edit text has: " + model.editString);



//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String editString = myedit.getText().toString();
//                mytext.setText( "Your edit text has: " + editString);
//
//                                   }
//                               }
//
//        );
  }


}