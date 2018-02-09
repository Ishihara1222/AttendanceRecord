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
import io.realm.RealmResults;

public class ScheduleFragment extends Fragment {

    ArrayList<CustomScheduleInfoListItem> listItems;

    ArrayList<String> subjectNameList = null;
    ArrayList<Integer> attendNumList = null;
    ArrayList<Integer> absentNumList = null;
    ArrayList<Integer> lateNumList = null;

    ListView listView;

    Realm realm;

    public static ScheduleFragment newInstance(){
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        listItems = new ArrayList<>();
        subjectNameList = new ArrayList<>();
        attendNumList = new ArrayList<>();
        absentNumList = new ArrayList<>();
        lateNumList = new ArrayList<>();
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) view.findViewById(R.id.scheduleList);

        syncDataList();

        CustomScheduleListAdapter adapter = new CustomScheduleListAdapter(this.getActivity(), R.layout.schedule_list_item, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO 教科名を変更できるダイアログの表示

            }
        });
    }

    public void syncDataList(){

        RealmResults<SubjectInfoItems> results = realm.where(SubjectInfoItems.class).findAll();
        SubjectInfoItems subjectInfoItems;

        if(results.size() > 0){
            for (int i=0; i<5; i++){
                subjectInfoItems = results.get(i);
                subjectNameList.add(subjectInfoItems.getSubjectName());
                attendNumList.add(subjectInfoItems.getAttendNum());
                absentNumList.add(subjectInfoItems.getAbsentNum());
                lateNumList.add(subjectInfoItems.getLateNum());

                CustomScheduleInfoListItem item = new CustomScheduleInfoListItem(subjectNameList.get(i), absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);
            }
        }else{
            for (int i=0; i<5; i++){
                subjectNameList.add("未設定");
                absentNumList.add(0);
                lateNumList.add(0);

                CustomScheduleInfoListItem item = new CustomScheduleInfoListItem(subjectNameList.get(i), absentNumList.get(i), lateNumList.get(i));
                listItems.add(item);

                //realmデータベースに初期値を書き込み
                savedListItem("未設定",0, 0, 0, i, 0);
            }
        }
    }

    private void savedListItem(String subjectName, int attendNum, int absentNum, int lateNum, int classNum, int dayOfWeekNum){

        realm.beginTransaction();
        SubjectInfoItems items = realm.createObject(SubjectInfoItems.class);

        items.setSubjectName(subjectName);
        items.setAttendNum(attendNum);
        items.setAbsentNum(absentNum);
        items.setLateNum(lateNum);
        items.setClassId(classNum);
        items.setDayOfWeekId(dayOfWeekNum);

        realm.commitTransaction();
    }

    public void reloadList(){
        subjectNameList.clear();
        attendNumList.clear();
        absentNumList.clear();
        lateNumList.clear();

        syncDataList();
        CustomScheduleListAdapter adapter = new CustomScheduleListAdapter(this.getActivity(), R.layout.schedule_list_item, listItems);
        adapter.notifyDataSetChanged();
    }

}
