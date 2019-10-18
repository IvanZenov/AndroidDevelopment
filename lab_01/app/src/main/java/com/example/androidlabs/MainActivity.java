package com.example.androidlabs;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button respondButton;
    TextView textViewAnswer;
    static final int ANSWER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respondButton = findViewById(R.id.respondBtn);
        textViewAnswer = findViewById(R.id.textViewAnswer);

        respondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AnswerActivity.class);
                startActivityForResult(intent,ANSWER_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ANSWER_REQUEST){
            String answer =  data.getStringExtra("answer");
            textViewAnswer.setText(answer);
        }
    }
}
