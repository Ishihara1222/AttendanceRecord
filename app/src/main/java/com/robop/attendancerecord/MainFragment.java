package com.robop.attendancerecord;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    ArrayList<ListItemModel> listItems = new ArrayList<>();    //ListViewのAdapterに入れる情報

    //listItemsの中身
    ArrayList<String> subjectNameList = new ArrayList<>();   //教科名
    ArrayList<Integer> attendNumList = new ArrayList<>();    //出席数
    ArrayList<Integer> absentNumList = new ArrayList<>();    //欠席数
    ArrayList<Integer> lateNumList = new ArrayList<>();      //遅刻数

    ListView listView;

    Activity activity;

    public static MainFragment newInstance( int position){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("currentViewPage", position);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        activity = (Activity)context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        /*
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setSubjectName("未設定");
        listItemModel.setAttendNum(0);
        listItemModel.setAbsentNum(0);
        listItemModel.setLateNum(0);
        listItems.add(listItemModel);
        */

        listView = view.findViewById(R.id.listView);

        ListAdapter adapter = new ListAdapter(activity.getApplicationContext(), R.layout.list_item, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EditSubjectNameDialog editSubjectNameDialog = EditSubjectNameDialog.newInstance(position);

                editSubjectNameDialog.show(getFragmentManager(), null);
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

}
