package com.alarmclockapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.alarmclockapp.AlarmManagerHelper;
import com.alarmclockapp.R;

public class MainActivity extends AppCompatActivity {
    private AlarmManagerHelper alarmManagerHelper;
    private Button setAlarmButton;
    private Button snoozeButton;
    private Button dismissButton;
    private TimePicker alarmTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManagerHelper = new AlarmManagerHelper(this);

        setAlarmButton = findViewById(R.id.set_alarm_button);
        snoozeButton = findViewById(R.id.snooze_button);
        dismissButton = findViewById(R.id.dismiss_button);
        alarmTimePicker =findViewById(R.id.alarm_time_picker);

        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = alarmTimePicker.getCurrentHour();
                int minute = alarmTimePicker.getCurrentMinute();
                alarmManagerHelper.setAlarm(hour, minute);
                Toast.makeText(MainActivity.this, "Alarm set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
            }
        });

        snoozeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Snooze logic here
                Toast.makeText(MainActivity.this, "Snooze button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManagerHelper.cancelAlarm();
                Toast.makeText(MainActivity.this, "Alarm dismissed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}