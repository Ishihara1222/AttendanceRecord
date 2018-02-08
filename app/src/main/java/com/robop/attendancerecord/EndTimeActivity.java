package com.robop.attendancerecord;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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


/**
 * Created by siilo on 2018/02/08.
 */



public class EndTimeActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endtime);

        final EditText beforeAlertTime = (EditText)findViewById(R.id.editText);

        beforeAlertTime.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN
                        && keyCode == KeyEvent.KEYCODE_ENTER) {

                    SpannableStringBuilder sp = (SpannableStringBuilder)beforeAlertTime.getText();

                    beforeAlertTime.setText(sp + "分前" );

                }

                return false;
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }



    public void onClickButton01(View view) {
        new TimePickerDialog(EndTimeActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // 時間が設定されたときの処理
                Log.i("Time", String.format("%02d:%02d", hourOfDay, minute));

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

                TextView text = (TextView) findViewById(R.id.fiveTime);
                text.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, 13, 0, true)
                .show();
    }



    }











