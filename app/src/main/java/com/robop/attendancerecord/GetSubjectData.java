package com.robop.attendancerecord;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

class GetSubjectData {

    private Realm realm;

    GetSubjectData() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(realmConfiguration);
    }

    //MainFragmentのListViewに表示するためのlistItemsを返すメソッド
    ArrayList<CustomListItem> getSubjectDataList(int currentPage){
        RealmQuery<SubjectRealmData> query = realm.where(SubjectRealmData.class);

        ArrayList<CustomListItem> listItems = new ArrayList<>();
        ArrayList<String> subjectNameList = new ArrayList<>();
        //ArrayList<Integer> attendNumList = new ArrayList<>();
        ArrayList<Integer> absentNumList = new ArrayList<>();
        ArrayList<Integer> lateNumList = new ArrayList<>();

        SubjectRealmData subjectRealmData;
                
        switch (currentPage){
            case 0: //月曜日
                query.lessThan("classId", 5);
                break;
                
            case 1: //火曜日
                query.greaterThanOrEqualTo("classId", 5).and().lessThan("classId", 10);
                break;
                
            case 2: //水曜日
                query.greaterThanOrEqualTo("classId", 10).and().lessThan("classId", 15);
                break;
                
            case 3: //木曜日
                query.greaterThanOrEqualTo("classId", 15).and().lessThan("classId", 20);
                break;
                
            case 4: //金曜日
                query.greaterThanOrEqualTo("classId", 20).and().lessThan("classId", 25);
                break;
                
            case 5: //土曜日
                query.greaterThanOrEqualTo("classId", 25).and().lessThan("classId", 30);
                break;
        }

        RealmResults<SubjectRealmData> results = query.findAll();

        if (results.size() > 0){
            for (int i=0; i<5; i++){
                subjectRealmData = results.get(i);

                //Realm上にデータがある時
                if (subjectRealmData != null){
                    subjectNameList.add(subjectRealmData.getSubjectName());
                    //attendNumList.add(subjectRealmData.getAttendNum());
                    absentNumList.add(subjectRealmData.getAbsentNum());
                    lateNumList.add(subjectRealmData.getLateNum());
                }else{
                    subjectNameList.add("未設定");
                    absentNumList.add(0);
                    lateNumList.add(0);
                }

                CustomListItem item = new CustomListItem(subjectNameList.get(i), absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);
            }
        }else{
            for (int i=0; i<5; i++){
                subjectNameList.add("未設定");
                absentNumList.add(0);
                lateNumList.add(0);

                CustomListItem item = new CustomListItem(subjectNameList.get(i), absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);
            }
        }
        return listItems;
    }

}
