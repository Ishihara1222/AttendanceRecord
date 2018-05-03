package com.robop.attendancerecord;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {

    ArrayList<ListItemModel> listItems = new ArrayList<>();    //ListViewのAdapterに入れる情報

    ListAdapter adapter;
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
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //ListItem初期化
        ListItemModel listItemModel = new ListItemModel();
        for (int i=0; i<5; i++){
            listItemModel.setSubjectName("未設定");
            listItemModel.setAttendNum(0);
            listItemModel.setAbsentNum(0);
            listItemModel.setLateNum(0);
            listItems.add(listItemModel);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, final Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.listView);

        adapter = new ListAdapter(activity.getApplicationContext(), R.layout.list_item, listItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.activity_subjectdialog, null);
        builder.setView(dialogView)
                .setPositiveButton("決定", (dialogInterface, i) -> {

                    EditText editText = dialogView.findViewById(R.id.edit_subject_name);

                    ListItemModel listItemModel = new ListItemModel();
                    listItemModel.setSubjectName(editText.getText().toString());
                    listItems.set(position, listItemModel);
                    adapter.notifyDataSetChanged();

                })
                .setNegativeButton("キャンセル", (dialogInterface, i) -> {

                })

                .show();

    }

}
