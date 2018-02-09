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

        final int attendResultCode = intent.getIntExtra("AttendResult", -1);  //0 : 出席, 1 : 欠席, 2 : 遅刻
        final int attendClassNum = intent.getIntExtra("ClassNumCode", -1);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        //TODO 曜日と時限に一致したデータの出席情報を取得して、attendResultCodeの結果に応じてデータに+1する
        //RealmResults<SubjectInfoItems> results = realm.where(SubjectInfoItems.class).equalTo("classId", attendClassNum).findAll();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SubjectInfoItems subjectInfoItems = realm.where(SubjectInfoItems.class).equalTo("classId", attendClassNum).findFirst();

                switch (attendResultCode){
                    case 0:
                        //出席
                        subjectInfoItems.setAttendNum(subjectInfoItems.getAttendNum()+1);
                        Log.i("attend","出席回数 : " + subjectInfoItems.getAttendNum()+1);
                        break;

                    case 1:
                        subjectInfoItems.setAbsentNum(subjectInfoItems.getAbsentNum()+1);
                        Log.i("attend", "欠席回数 : " + subjectInfoItems.getAbsentNum()+1);
                        break;

                    case 2:
                        subjectInfoItems.setLateNum(subjectInfoItems.getLateNum()+1);
                        Log.i("attend", "遅刻回数 : " + subjectInfoItems.getLateNum()+1);
                        break;
                }
            }
        });

        /*
        if(results.size() > 0){
            for(int i=0; i<results.size(); i++){



                realm.beginTransaction();

                switch (attendResultCode){
                    case 0:
                        //出席
                        Log.i("attend","出席");
                        subjectInfoItems.setAttendNum(subjectInfoItems.getAttendNum()+1);
                        break;

                    case 1:
                        Log.i("attend", "欠席");
                        subjectInfoItems.setAbsentNum(subjectInfoItems.getAbsentNum()+1);
                        break;

                    case 2:
                        Log.i("attend", "遅刻");
                        subjectInfoItems.setLateNum(subjectInfoItems.getLateNum()+1);
                        break;
                }
            }
        }*/

    }
}
