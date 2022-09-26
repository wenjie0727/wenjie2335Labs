package algonquin.cst2335.jian0076.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import algonquin.cst2335.jian0076.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());}}


