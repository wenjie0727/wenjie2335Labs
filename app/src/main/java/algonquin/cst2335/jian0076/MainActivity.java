package algonquin.cst2335.jian0076;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("Mydata", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("Email","");



        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.emailAddress.setText(emailAddress);

        //binding.emailAddress.setText(emailAddress);
        binding.button4.setOnClickListener(click ->
                {
                    Intent nextPage = new Intent(MainActivity.this,SecondActivity.class);
                    nextPage.putExtra("EmailAddress",binding.emailAddress.getText().toString());
                    prefs.edit().putString("Email", binding.emailAddress.getText().toString()).commit();
                    startActivity(nextPage);

                }
                );
    }
}