package by.bsu.lab_4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputNumber;
    private Button buttonOk;
    private TextView amountOfPrimeNumbers;
    private TextView listOfPrimeNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputNumber = findViewById(R.id.inputNumber);
        this.buttonOk = findViewById(R.id.submitButton);
        this.amountOfPrimeNumbers = findViewById(R.id.amountOfPrimeNumbers);
        this.listOfPrimeNumbers = findViewById(R.id.listOfPrimeNumbers);

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("MainActivity"));

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainIntentService.class);
                intent.putExtra("number",Integer.valueOf(inputNumber.getText().toString()));
                buttonOk.setEnabled(false);
                startService(intent);

            }
        });

    }
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int amount = intent.getIntExtra("amountOfPrimeNumbers", 0);
            String list = intent.getStringExtra("listOfPrimeNumbers");

            amountOfPrimeNumbers.setText(Integer.toString(amount));
            listOfPrimeNumbers.setText(list);

            buttonOk.setEnabled(true);

        }
    };

}

