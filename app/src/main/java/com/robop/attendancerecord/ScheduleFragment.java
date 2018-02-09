package com.robop.attendancerecord;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ScheduleFragment extends Fragment {

    ArrayList<CustomScheduleInfoListItem> listItems;

    ArrayList<String> subjectNameList = null;
    ArrayList<Integer> absentNumList = null;
    ArrayList<Integer> lateNumList = null;

    Realm realm;

    public static ScheduleFragment newInstance(){
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        listItems = new ArrayList<>();
        subjectNameList = new ArrayList<>();
        absentNumList = new ArrayList<>();
        lateNumList = new ArrayList<>();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfig);
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ListView scheduleListView = (ListView) view.findViewById(R.id.scheduleList);

        //TODO Realmから取得
        RealmResults<SubjectInfoItems> results = realm.where(SubjectInfoItems.class).findAll();
        SubjectInfoItems subjectInfoItems;

        if(results.size() > 0){
            for (int i=0; i<5; i++){
                subjectInfoItems = results.get(i);
                absentNumList.add(subjectInfoItems.getAbsentNum());
                lateNumList.add(subjectInfoItems.getLateNum());

                CustomScheduleInfoListItem item = new CustomScheduleInfoListItem("教科名", absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);
            }
        }else{
            for (int i=0; i<5; i++){

                absentNumList.add(0);
                lateNumList.add(0);

                CustomScheduleInfoListItem item = new CustomScheduleInfoListItem("未設定", absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);
            }
        }


        CustomScheduleListAdapter adapter = new CustomScheduleListAdapter(this.getActivity(), R.layout.schedule_list_item, listItems);
        scheduleListView.setAdapter(adapter);

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void savedInitListitem(){
        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        SubjectInfoItems items = realm.createObject(SubjectInfoItems.class);


        items.setSubjectName("教科未設定");
        items.setAbsentNum(0);
        items.setLateNum(0);
    }

}
