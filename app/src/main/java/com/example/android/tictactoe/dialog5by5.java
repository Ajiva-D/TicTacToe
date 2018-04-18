package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class dialog5by5 extends AppCompatActivity {
    EditText SendValue;
    EditText SendValue2;
    Button SendEditTextValue;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog5by5);
        SendEditTextValue = (Button)findViewById(R.id.play);
        SendValue = (EditText)findViewById(R.id.editText1);

        SendValue2 = (EditText)findViewById(R.id.editText2);

        SendEditTextValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(),fiveByfive.class);
                intent.putExtra("EdiTtEXTvALUE", SendValue.getText().toString());
                intent.putExtra("EdiTtEXTvALUE2", SendValue2.getText().toString());
                startActivity(intent);
            }

        });
    }
}
