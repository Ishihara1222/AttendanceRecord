package com.robop.attendancerecord;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomScheduleListAdapter extends ArrayAdapter<CustomScheduleInfoListItem> {

    private int resource;
    private List<CustomScheduleInfoListItem> scheduleInfoItems;
    private LayoutInflater layoutInflater;

    public CustomScheduleListAdapter(@NonNull Context context, @LayoutRes int resource, List<CustomScheduleInfoListItem> scheduleInfoItems) {
        super(context, resource, scheduleInfoItems);

        this.resource = resource;;
        this.scheduleInfoItems = scheduleInfoItems;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        private TextView classNumHolder;
        private TextView subjectNameHolder;
        private TextView absentNumHolder;
        private TextView lateNumHolder;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = this.layoutInflater.inflate(this.resource, null);
            viewHolder = new ViewHolder();

            viewHolder.classNumHolder = (TextView) convertView.findViewById(R.id.classNum);
            viewHolder.subjectNameHolder = (TextView) convertView.findViewById(R.id.subjectName);
            viewHolder.absentNumHolder = (TextView) convertView.findViewById(R.id.absentRecord);
            viewHolder.lateNumHolder = (TextView) convertView.findViewById(R.id.lateRecord);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomScheduleInfoListItem item = this.scheduleInfoItems.get(position);

        viewHolder.classNumHolder.setText(position+1 + "限");

        //Realmの処理はScheduleFragment.javaにある
        viewHolder.subjectNameHolder.setText("教科名 : " + item.getSubjectName());
        viewHolder.absentNumHolder.setText("欠席回数 : " + String.valueOf(item.getAbsentNum()) + "回");
        viewHolder.lateNumHolder.setText("遅刻回数 : " + String.valueOf(item.getLateNum()) + "回");

        return convertView;
    }
}