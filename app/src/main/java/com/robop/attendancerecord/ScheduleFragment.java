package com.robop.attendancerecord;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    ArrayList<CustomScheduleInfoListItem> listItems;

    public static ScheduleFragment newInstance(){
        ScheduleFragment fragment = new ScheduleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        listItems = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ListView scheduleListView = (ListView) view.findViewById(R.id.scheduleList);

        for (int i=0; i<5; i++){
            CustomScheduleInfoListItem item = new CustomScheduleInfoListItem("教科名", 3, 4);
            listItems.add(item);
        }

        CustomScheduleListAdapter adapter = new CustomScheduleListAdapter(this.getActivity(), R.layout.schedule_list_item, listItems);
        scheduleListView.setAdapter(adapter);
    }
}
