package com.example.androidlabs;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button respondButton;
    EditText inputText;
    static final int ANSWER_REQUEST = 1;
    static final String QUESTION_KEY = "question";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        respondButton = findViewById(R.id.respondBtn);
        inputText = findViewById(R.id.inputText);

        respondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AnswerActivity.class);
                intent.putExtra(QUESTION_KEY, inputText.getText().toString());
                startActivityForResult(intent, ANSWER_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ANSWER_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra("exit") != null && data.getStringExtra("exit").equals("exit")){
                    finishAndRemoveTask();
                }
                else{
                    String textOfAnswer = data.getStringExtra("answer");
                    TextView answer = (TextView)findViewById(R.id.receiveText);
                    answer.setText(textOfAnswer);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.exit:
                ExitDialog exitDialog = new ExitDialog();
                exitDialog.show(this.getSupportFragmentManager(), "dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
