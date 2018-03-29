package com.robop.attendancerecord;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainFragment extends Fragment implements RealmChangeListener {

    ArrayList<CustomListItem> listItems;    //ListViewのAdapterに入れる情報

    //listItemsの中身
    ArrayList<String> subjectNameList = null;   //教科名
    ArrayList<Integer> attendNumList = null;    //出席数(ListView上には現状表示しないので使ってない)
    ArrayList<Integer> absentNumList = null;    //欠席数
    ArrayList<Integer> lateNumList = null;      //遅刻数

    ListView listView;
    private CustomListAdapter adapter;

    int currentPageNum = 0; //現在のfragmentのページ数

    Realm realm;

    public static MainFragment newInstance(ArrayList<CustomListItem> listItems, int position){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentListItem", listItems);
        bundle.putInt("currentViewPage", position);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        listItems = new ArrayList<>();
        subjectNameList = new ArrayList<>();
        attendNumList = new ArrayList<>();
        absentNumList = new ArrayList<>();
        lateNumList = new ArrayList<>();

        Bundle bundle = getArguments();
        listItems = (ArrayList<CustomListItem>) bundle.getSerializable("currentListItem");
        currentPageNum = bundle.getInt("currentViewPage");

        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(this);

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        adapter = new CustomListAdapter(this.getActivity(), R.layout.list_item, listItems);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DialogFragment newFragment = new AlertDialogFragment(position);
                newFragment.show(getFragmentManager(), "missiles");
            }
        });
    }

    @Override
    public void onDestroyView(){
        realm.close();
        super.onDestroyView();
    }

    private void savedListItem(String subjectName, int attendNum, int absentNum, int lateNum, int classNum, int dayOfWeekNum){

        realm.beginTransaction();
        SubjectRealmData items = realm.createObject(SubjectRealmData.class);

        items.setSubjectName(subjectName);
        items.setAttendNum(attendNum);
        items.setAbsentNum(absentNum);
        items.setLateNum(lateNum);
        items.setClassId(classNum);
        items.setDayOfWeekId(dayOfWeekNum);

        realm.commitTransaction();
    }

    //Realm情報が変更されたときに呼ばれる
    @Override
    public void onChange(@NonNull Object o) {
        GetSubjectData getSubjectData = new GetSubjectData();

        //新しいlistItems情報でAdapterを更新
        adapter = new CustomListAdapter(this.getActivity(), R.layout.list_item, getSubjectData.getSubjectDataList(currentPageNum));
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
