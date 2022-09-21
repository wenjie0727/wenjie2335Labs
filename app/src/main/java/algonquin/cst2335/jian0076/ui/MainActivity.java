package algonquin.cst2335.jian0076.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.jian0076.data.MainViewModel;
import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());


        TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myedittext;


        btn.setOnClickListener(click ->
        {
            model.editString.postValue(myedit.getText().toString()) ;
        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has "+ s );
        });


        model.isSelected.observe(this,isChecked ->{
            variableBinding.checkBox.setChecked(isChecked);
            variableBinding.radioButton.setChecked(isChecked);
            variableBinding.switch1.setChecked(isChecked);
        }
        );
        variableBinding.checkBox.setOnCheckedChangeListener( (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Toast.makeText(getApplicationContext(), "You clicked on the " +  "checkbox" + " and it is now: " + isChecked,Toast.LENGTH_SHORT).show();

        } );
        variableBinding.radioButton.setOnCheckedChangeListener( (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Toast.makeText(getApplicationContext(), "You clicked on the " +  "radiobutton" + " and it is now: " + isChecked,Toast.LENGTH_SHORT).show();
        } );
        variableBinding.switch1.setOnCheckedChangeListener( (button, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Toast.makeText(getApplicationContext(), "You clicked on the " +  "switch" + " and it is now: " + isChecked,Toast.LENGTH_SHORT).show();
        } );

        variableBinding.myimagebutton.setOnClickListener(click -> {
            Toast toast = Toast.makeText(getApplicationContext(), "The width =" + variableBinding.myimagebutton.getWidth() + " and height = " + variableBinding.myimagebutton.getHeight(), Toast.LENGTH_SHORT);
            toast.show();
        });

  }
}