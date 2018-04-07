package com.robop.attendancerecord;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class AttendanceResultActivity extends AppCompatActivity {

    Realm realm;

    int attendNum = 0, absentNum = 0, lateNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_result);

        Intent intent = getIntent();
        Log.i("AttendResult", String.valueOf(intent.getIntExtra("AttendResult", -1)));
        Log.i("ClassNumResult", String.valueOf(intent.getIntExtra("ClassNumResult", -1)));

        final int attendResultCode = intent.getIntExtra("AttendResult", -1);  //0 : 出席, 1 : 欠席, 2 : 遅刻
        final int attendClassNum = intent.getIntExtra("ClassNumResult", -1);  //授業時限数の受取 1限を0としてそれ以降の時限を表す

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(realmConfiguration);

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                SubjectRealmData subjectRealmData = realm.where(SubjectRealmData.class).equalTo("classId", attendClassNum).findFirst();

                if (subjectRealmData != null) {

                    switch (attendResultCode) {
                        case 0:
                            //出席
                            attendNum = subjectRealmData.getAttendNum() + 1;
                            subjectRealmData.setAttendNum(attendNum);
                            break;

                        case 1:
                            //欠席
                            absentNum = subjectRealmData.getAbsentNum() + 1;
                            subjectRealmData.setAbsentNum(absentNum);
                            break;

                        case 2:
                            //遅刻
                            lateNum = subjectRealmData.getLateNum() + 1;
                            subjectRealmData.setLateNum(lateNum);
                            break;
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                Log.i("attend", "出席回数 : " + attendNum);
                Log.i("attend", "欠席回数 : " + absentNum);
                Log.i("attend", "遅刻回数 : " + lateNum);

                RealmResults results = realm.where(SubjectRealmData.class).findAll();

                Toast.makeText(AttendanceResultActivity.this, "出欠情報を更新しました", Toast.LENGTH_SHORT).show();

                realm.close();
            }

        }, new Realm.Transaction.OnError() {

            @Override
            public void onError(@NonNull Throwable error) {
                Toast.makeText(AttendanceResultActivity.this, "出欠情報の取得に失敗しました", Toast.LENGTH_SHORT).show();

                realm.close();
            }
        });

        //TODO GoogleHome連携用にFirebase Databaseへ保存

    }
}
