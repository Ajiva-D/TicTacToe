package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goTo3by3Dialog(View v){
        Intent intent = new Intent(getBaseContext(),dialog3by3.class);
        startActivity(intent);

    }
    public void goTo5by5Dialog(View v){
        Intent intent = new Intent(getBaseContext(),dialog5by5.class);
        startActivity(intent);

    }
}
