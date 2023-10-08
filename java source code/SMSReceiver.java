package dhanush.com.firestoreapp;

import static android.content.ContentValues.TAG;
import static dhanush.com.firestoreapp.R.raw.ambulance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String senderPhoneNumber = smsMessage.getDisplayOriginatingAddress();
                        Log.d(TAG, "Received SMS from: " + senderPhoneNumber);

                        // Replace "desiredPhoneNumber" with the number you want to trigger the alert
                        if ("+919942694615".equals(senderPhoneNumber)) {
                            showFullScreenAlert(context, smsMessage.getMessageBody());
                            break;

                            //Log.d(senderPhoneNumber, "onReceive: "+senderPhoneNumber);
                            // Display a toast message

                            //Toast.makeText(context,"yes",Toast.LENGTH_LONG).show();
                          //  Intent alertIntent = new Intent(context, MainActivity.class);
                          //  alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //context.startActivity(alertIntent);

                           // Toast.makeText(context, "You received a message from the desired number.", Toast.LENGTH_LONG).show();

                            // Play the special ringtone
                           // playSpecialRingtone(context);
                        }
                        else {
                            Toast.makeText(context, "You received a message from the desired number.", Toast.LENGTH_LONG).show();


                        }
                    }
                }
            }
        }
    }

    private void playSpecialRingtone(Context context) {
        // Get the URI of the special ringtone


        if(mp == null) {
            mp = MediaPlayer.create(context,ambulance);
        }
        mp.start();

    }
    private void showFullScreenAlert(Context context, String message) {
        Intent alertIntent = new Intent(context, splash.class);
        alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alertIntent.putExtra("message", message);
        context.startActivity(alertIntent);
    }


}
