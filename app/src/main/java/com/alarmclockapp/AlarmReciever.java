package com.alarmclockapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Play alarm sound or vibration here
        Toast.makeText(context, "Alarm is ringing!", Toast.LENGTH_SHORT).show();
    }
}