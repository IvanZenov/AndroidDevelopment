package by.bsu.lab_4;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class MainIntentService extends IntentService  {

    public MainIntentService() {
        super("MainIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int number = intent.getIntExtra("number",1);
        int counter = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < number;i++){
            if(isPrime(i)){
                if(i>2){
                    result.append(",");
                }
                result.append(i);
                counter++;
            }
        }
        Intent sendIntent = new Intent("MainActivity");
        sendIntent.putExtra("listOfPrimeNumbers",result.toString());
        sendIntent.putExtra("amountOfPrimeNumbers",counter);
        LocalBroadcastManager.getInstance(this).sendBroadcast(sendIntent);

    }

    private boolean isPrime(int number){
        if(number<2) return false;
        for (int i = 2;i<number;i++){
            if (number%i==0)return false;
        }
        return true;
    }
}
