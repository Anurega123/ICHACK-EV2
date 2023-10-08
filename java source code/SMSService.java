package dhanush.com.firestoreapp;



import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
//import android.support.annotation.Nullable;

import androidx.annotation.Nullable;

public class SMSService extends Service {
    private SMSReceiver smsReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        smsReceiver = new SMSReceiver();
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, filter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsReceiver);
    }
}

