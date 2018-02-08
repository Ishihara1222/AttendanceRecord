package com.robop.attendancerecord;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Created by siilo on 2018/02/08.
 */


public class EndTimeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endtime);

        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new TimePickerDialog (EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 時間が設定されたときの処理
                        Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));

                        TextView text = (TextView)findViewById(R.id.firstTime);
                        text.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, 13, 00, true)
                .show();




            }
        });
    }


}