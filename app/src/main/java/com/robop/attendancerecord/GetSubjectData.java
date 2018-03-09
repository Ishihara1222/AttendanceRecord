package com.robop.attendancerecord;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

class GetSubjectData {

    private Realm realm;

    GetSubjectData() {
        realm = Realm.getDefaultInstance();
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
                query.lessThan("listId", 5);
                break;
                
            case 1: //火曜日
                query.greaterThanOrEqualTo("listId", 5).and().lessThan("listId", 10);
                break;
                
            case 2: //水曜日
                query.greaterThanOrEqualTo("listId", 10).and().lessThan("listId", 15);
                break;
                
            case 3: //木曜日
                query.greaterThanOrEqualTo("listId", 15).and().lessThan("listId", 20);
                break;
                
            case 4: //金曜日
                query.greaterThanOrEqualTo("listId", 20).and().lessThan("listId", 25);
                break;
                
            case 5: //土曜日
                query.greaterThanOrEqualTo("listId", 25).and().lessThan("listId", 30);
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
