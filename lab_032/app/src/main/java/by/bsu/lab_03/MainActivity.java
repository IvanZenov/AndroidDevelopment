package by.bsu.lab_03;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

    private static  final int ANSWER_REQUEST=1;
    static final String QUESTION_KEY = "question";
    EditText askQuestion;
    Button buttonQuestion;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        askQuestion = findViewById(R.id.inputText);
        buttonQuestion = findViewById(R.id.buttonOk);

        buttonQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                intent.putExtra(QUESTION_KEY, askQuestion.getText().toString());
                startActivityForResult(intent, ANSWER_REQUEST);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ANSWER_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra("exit") != null && data.getStringExtra("exit").equals("exit")){
                    finishAndRemoveTask();
                }
                else{
                    String textOfAnswer = data.getStringExtra("answer");
                    answer = findViewById(R.id.receiveText);
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
        if (item.getItemId() == R.id.exit) {
            ExitDialog exitDialog = new ExitDialog();
            exitDialog.show(this.getSupportFragmentManager(), "dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}