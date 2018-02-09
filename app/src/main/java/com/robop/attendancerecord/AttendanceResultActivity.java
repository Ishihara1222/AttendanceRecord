package com.robop.attendancerecord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

public class AttendanceResultActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_result);

        Intent intent = getIntent();
        Log.i("AttendResult", String.valueOf(intent.getIntExtra("AttendResult", -1)));

        int attendResultCode = intent.getIntExtra("AttendResult", -1);  //1 : 出席, 2 : 欠席, 3 : 遅刻

        realm = Realm.getDefaultInstance();

        //TODO 曜日と時限に一致したデータの出席情報を取得して、attendResultCodeの結果に応じてデータに+1する
        RealmResults<SubjectInfoItems> results = realm.where(SubjectInfoItems.class).findAll();
        SubjectInfoItems subjectInfoItems;


    }
}
