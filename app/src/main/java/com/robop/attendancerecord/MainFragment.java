package com.robop.attendancerecord;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    ArrayList<CustomListItem> listItems;    //ListViewのAdapterに入れる情報

    //listItemsの中身
    ArrayList<String> subjectNameList = null;   //教科名
    ArrayList<Integer> attendNumList = null;    //出席数(ListView上には現状表示しないので使ってない)
    ArrayList<Integer> absentNumList = null;    //欠席数
    ArrayList<Integer> lateNumList = null;      //遅刻数

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

        listItems = new ArrayList<>();
        subjectNameList = new ArrayList<>();
        attendNumList = new ArrayList<>();
        absentNumList = new ArrayList<>();
        lateNumList = new ArrayList<>();

        Bundle bundle = getArguments();
        if (bundle != null){
            int currentPageNum = bundle.getInt("currentViewPage");
            Log.d("currentPage", String.valueOf(currentPageNum));

            GetSubjectData getSubjectData = new GetSubjectData(getContext());
            listItems = getSubjectData.getSubjectDataList(currentPageNum);
        }

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        CustomListAdapter adapter = new CustomListAdapter(activity.getApplicationContext(), R.layout.list_item, listItems);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EditSubjectNameDialog editSubjectNameDialog = new EditSubjectNameDialog(position);
                editSubjectNameDialog.show(activity.getFragmentManager(), "subjectName");
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    //TODO 画面に戻ってきたときのデータ更新

}
