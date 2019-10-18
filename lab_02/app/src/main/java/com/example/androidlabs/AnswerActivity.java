package com.example.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnswerActivity extends AppCompatActivity {

    Button buttonOk;
    EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        buttonOk = findViewById(R.id.buttonOk);
        inputAnswer = findViewById(R.id.inputAnswer);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("answer",inputAnswer.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
