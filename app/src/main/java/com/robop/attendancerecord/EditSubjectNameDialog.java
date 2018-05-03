package com.robop.attendancerecord;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class EditSubjectNameDialog extends DialogFragment {

    Realm realm;
    private int position;
    View view;
    Activity activity;

    public EditSubjectNameDialog(int clickPosition){
        this.position = clickPosition;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        activity = (Activity)context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(realmConfiguration);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        //xmlと紐づけ
        view = activity.getLayoutInflater().inflate(R.layout.activity_subjectdialog,null);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setSubjectName(realm);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       EditSubjectNameDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

    private void setSubjectName(Realm realm){
        EditText editText = view.findViewById(R.id.customDlg_name);
        final String subjectName = editText.getText().toString();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                SubjectRealmData subjectRealmData = realm.where(SubjectRealmData.class).equalTo("classId", position).findFirst();

                if (subjectRealmData != null){
                    subjectRealmData.setSubjectName(subjectName);
                }
            }
        });
    }

}