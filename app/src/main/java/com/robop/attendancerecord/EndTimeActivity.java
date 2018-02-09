package com.robop.attendancerecord;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

import io.realm.Realm;


public class EndTimeActivity extends AppCompatActivity {

    int class1Hour, class2Hour, class3Hour, class4Hour, class5Hour;
    int class1Minute, class2Minute, class3Minute, class4Minute, class5Minute;

    Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endtime);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;

        int[] endTimeHour = {class1Hour, class2Hour, class3Hour, class4Hour, class5Hour};
        int[] endTimeMinute = {class1Minute, class2Minute, class3Minute, class4Minute, class5Minute};

        switch (id) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("EndTimeHour", endTimeHour);
                intent.putExtra("EndTimeMinute", endTimeMinute);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    //TODO 1限<2限<3限...となるように設定時間の入力制限

    public void onClickButton01(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                class1Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                class1Minute = Integer.valueOf(String.format("%02d", minute));

                TextView text = (TextView) findViewById(R.id.firstTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 0, 0, true)
                .show();
    }

    public void onClickButton02(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));
                class2Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                class2Minute = Integer.valueOf(String.format("%02d", minute));

                TextView text = (TextView) findViewById(R.id.secondTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 13, 0, true)
                .show();
    }

    public void onClickButton03(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));
                class3Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                class3Minute = Integer.valueOf(String.format("%02d", minute));

                TextView text = (TextView) findViewById(R.id.thirdTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 13, 0, true)
                .show();
    }

    public void onClickButton04(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));
                class4Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                class4Minute = Integer.valueOf(String.format("%02d", minute));

                TextView text = (TextView) findViewById(R.id.fourthTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 13, 0, true)
                .show();
    }

    public void onClickButton05(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));
                class5Hour = Integer.valueOf(String.format("%02d",hourOfDay));
                class5Minute = Integer.valueOf(String.format("%02d", minute));

                TextView text = (TextView) findViewById(R.id.fiveTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 13, 0, true)
                .show();
    }

}











